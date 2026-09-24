#!/usr/bin/env python3
"""Validate an exact component closure and invoke its local Moonbeam owner adapter."""
import argparse
import hashlib
import json
import os
from pathlib import Path, PurePosixPath
import subprocess


SCHEMA = 'moondance.component-publication/v1'
RECEIPT_SCHEMA = 'tassy-moonbeam-component-publish-receipt/v1'


def sha(data):
    return hashlib.sha256(data).hexdigest()


def load_plan(path):
    plan = json.loads(path.read_text())
    if plan.get('schema') != SCHEMA:
        raise ValueError('unsupported publication plan schema')
    for key in ('component_id', 'selected_version', 'local_tassy_process_instance', 'package'):
        if key not in plan:
            raise ValueError('missing plan field: ' + key)
    package = plan['package']
    for key in ('root', 'package_digest', 'inventory_sha256', 'descriptor_path', 'descriptor_sha256', 'files'):
        if key not in package:
            raise ValueError('missing package field: ' + key)
    if not isinstance(package['files'], list) or not package['files']:
        raise ValueError('package files must be a non-empty array')
    return plan


def verify_closure(plan, plan_path):
    package = plan['package']
    root = Path(package['root']).expanduser()
    if not root.is_absolute():
        root = (plan_path.parent / root).resolve()
    else:
        root = root.resolve()
    if not root.is_dir() or root.is_symlink():
        raise ValueError('package root must be a real directory')
    declared = {}
    digest_rows = []
    for entry in package['files']:
        name = entry.get('path')
        posix = PurePosixPath(name) if isinstance(name, str) else None
        if not posix or posix.is_absolute() or '..' in posix.parts or str(posix) != name or name in declared:
            raise ValueError('unsafe or duplicate package path: ' + repr(name))
        path = root / name
        if path.is_symlink() or not path.is_file():
            raise ValueError('package file missing or not regular: ' + name)
        data = path.read_bytes()
        actual = {'path': name, 'bytes': len(data), 'sha256': sha(data)}
        if actual != entry:
            raise ValueError('package file differs from plan: ' + name)
        declared[name] = actual
        digest_rows.append(actual)
    actual_files = {str(p.relative_to(root)) for p in root.rglob('*') if p.is_file() and not p.is_symlink()}
    if actual_files != set(declared):
        raise ValueError('declared files are not the exact package closure')
    descriptor = package['descriptor_path']
    if descriptor not in declared or declared[descriptor]['sha256'] != package['descriptor_sha256']:
        raise ValueError('descriptor is absent from closure or digest differs')
    closure_digest = sha(json.dumps(digest_rows, sort_keys=True, separators=(',', ':')).encode())
    if closure_digest != package['inventory_sha256']:
        raise ValueError('package closure digest differs')
    return root, closure_digest


def validate_receipt(plan, receipt):
    if receipt.get('schema') != RECEIPT_SCHEMA:
        raise ValueError('adapter returned an unsupported receipt')
    expected = {
        'component_id': plan['component_id'],
        'selected_version': plan['selected_version'],
        'package_digest': plan['package']['package_digest'],
    }
    for key, value in expected.items():
        if receipt.get(key) != value:
            raise ValueError('receipt differs from publication plan: ' + key)
    if receipt.get('status') not in ('selected', 'already_selected'):
        raise ValueError('adapter did not return a selected component receipt')
    selection = receipt.get('verified_remote_component', {})
    served = receipt.get('public_served', {})
    if selection.get('component_id') != expected['component_id'] or \
       selection.get('component_version') != expected['selected_version'] or \
       selection.get('package_digest') != expected['package_digest'] or selection.get('state') != 'READY':
        raise ValueError('remote selection differs from publication plan')
    for key in ('component_id', 'selected_version', 'package_digest'):
        if served.get(key) != expected[key]:
            raise ValueError('public verification differs: ' + key)
    if receipt.get('server_restart') is not False or receipt.get('database_publication') is not False or \
       receipt.get('unrelated_components_changed') is not False:
        raise ValueError('receipt exceeds component-only authority')
    if not receipt.get('server_instance_id') or not receipt.get('selected_at') or \
       not served.get('url') or served.get('status') != 200 or type(served.get('bytes')) is not int or \
       served['bytes'] < 1 or not served.get('sha256'):
        raise ValueError('receipt lacks remote selection or public served proof')
    return receipt


def publish(plan_path, adapter=None, apply=False):
    plan_path = plan_path.expanduser().resolve()
    plan = load_plan(plan_path)
    root, closure_digest = verify_closure(plan, plan_path)
    preview = {
        'status': 'verified-preview' if not apply else 'prepared',
        'component_id': plan['component_id'],
        'selected_version': plan['selected_version'],
        'package_digest': plan['package']['package_digest'],
        'inventory_sha256': closure_digest,
        'package_root': str(root),
        'local_tassy_process_instance': plan['local_tassy_process_instance'],
    }
    if not apply:
        return preview
    if adapter is None:
        raise ValueError('--adapter is required with --apply')
    adapter = adapter.expanduser().resolve()
    if not adapter.is_file() or not os.access(adapter, os.X_OK):
        raise ValueError('adapter must be an executable local file')
    result = subprocess.run([
        str(adapter), plan['component_id'], plan['selected_version'],
        plan['package']['package_digest'], plan['local_tassy_process_instance'],
    ], check=True, text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, timeout=1800)
    return validate_receipt(plan, json.loads(result.stdout))


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--plan', required=True, type=Path)
    parser.add_argument('--adapter', type=Path,
                        help='Local owner adapter; credentials and machine bindings stay outside MPK')
    parser.add_argument('--apply', action='store_true')
    args = parser.parse_args()
    try:
        print(json.dumps(publish(args.plan, args.adapter, args.apply), sort_keys=True))
        return 0
    except (OSError, ValueError, KeyError, json.JSONDecodeError, subprocess.SubprocessError) as exc:
        print(json.dumps({'status': 'error', 'error': str(exc)}))
        return 1


if __name__ == '__main__':
    raise SystemExit(main())
