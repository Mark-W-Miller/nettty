#!/usr/bin/env python3
"""Collect local MPK feedback without changing consumers or publishing a release."""
import argparse
from datetime import datetime, timezone
import hashlib
import json
from pathlib import Path
import re
import subprocess
import uuid

MAX_BYTES = 1024 * 1024
POLICY = 'mpk-local-handoff/v1'


def digest(data):
    return hashlib.sha256(data).hexdigest()


def git(root, *args):
    p = subprocess.run(['git', '-C', str(root), *args], capture_output=True,
                       text=True, timeout=20)
    return p.stdout.strip() if p.returncode == 0 else None


def safe_read(root, relative):
    p = root / relative
    # Never follow links, including linked parent directories.
    if p.is_symlink() or any(x.is_symlink() for x in p.parents if x.is_relative_to(root)):
        raise ValueError('symlink excluded: ' + relative)
    if not p.resolve().is_relative_to(root.resolve()):
        raise ValueError('outside source root: ' + relative)
    if not p.is_file() or p.stat().st_size > MAX_BYTES:
        raise ValueError('missing or oversized file: ' + relative)
    data = p.read_bytes()
    if len(data) > MAX_BYTES:
        raise ValueError('file grew beyond limit: ' + relative)
    data.decode('utf-8')
    return data


def collect(config, output, previous=None):
    entries = config['projects']
    ids = [x['id'] for x in entries]
    if len(ids) != len(set(ids)) or any(not re.fullmatch(r'[a-z0-9-]+', x) for x in ids):
        raise ValueError('project IDs must be unique safe slugs')
    roots = [Path(x['path']).expanduser().absolute() for x in entries]
    if any(output.resolve().is_relative_to(r.resolve()) for r in roots):
        raise ValueError('output must be outside all consumer roots')
    old = {}
    if previous:
        old = {x['id']: x for x in json.loads(previous.read_text())['projects']}
    output.mkdir(parents=True, exist_ok=False)
    report = {'schema': 'mpk-collection/v1', 'created_at': datetime.now(timezone.utc).isoformat(),
              'state': 'review-required-not-a-release', 'registry_sha256': digest(json.dumps(config, sort_keys=True).encode()),
              'projects': [], 'repository_groups': {}, 'policy': POLICY}
    for entry, root in zip(entries, roots):
        result = {'id': entry['id'], 'owners': entry['owners'], 'path': str(root),
                  'files': [], 'issues': [], 'release_pin': None}
        report['projects'].append(result)
        issues = result['issues']
        if not root.is_dir():
            issues.append('source directory missing'); continue
        try:
            head = git(root, 'rev-parse', 'HEAD')
            common = git(root, 'rev-parse', '--path-format=absolute', '--git-common-dir')
            before = git(root, 'status', '--porcelain=v1', '--untracked-files=all')
            result.update(head=head, branch=git(root, 'branch', '--show-current'),
                          upstream=git(root, 'rev-parse', '--abbrev-ref', '@{upstream}'),
                          dirty=bool(before), git_common_dir=common)
            if common:
                report['repository_groups'].setdefault(str(Path(common).resolve()), []).append(entry['id'])
            else:
                issues.append('not a Git checkout; task-folder feedback only')
            local = root / 'project-kit-local'
            if not (local / 'README.md').is_file():
                issues.append('missing project-kit-local/README.md index')
            try:
                lock = json.loads(safe_read(root, 'moondance.lock.json'))
                if not isinstance(lock, dict): raise ValueError('lock must be an object')
                result['release_pin'] = {k: lock.get(k) for k in
                    ('kit_version', 'content_digest', 'source_revision', 'status')}
                if not lock.get('kit_version') or not lock.get('content_digest'):
                    issues.append('release pin lacks version/digest')
                try:
                    vendored = json.loads(safe_read(root, 'project-kit/manifest.json'))
                    if not isinstance(vendored, dict): raise ValueError('manifest must be an object')
                    if any(lock.get(k) != vendored.get(k) for k in ('kit_version', 'content_digest')):
                        issues.append('release pin differs from vendored manifest')
                except (ValueError, OSError):
                    issues.append('vendored manifest missing or unreadable')
            except (ValueError, OSError):
                issues.append('release pin missing or unreadable')
            handoff = None
            try:
                handoff = json.loads(safe_read(root, 'project-kit-local/MPK-HANDOFF.json'))
                if not isinstance(handoff, dict): raise ValueError('handoff must be an object')
                required = ('owner', 'updated_at', 'kit_version', 'kit_content_digest', 'summary')
                if handoff.get('schema') != POLICY or any(not isinstance(handoff.get(k), str) or not handoff[k].strip() for k in required):
                    raise ValueError('handoff metadata missing')
                if any(handoff[k].startswith(('REPLACE_', 'COPY_')) for k in required):
                    raise ValueError('handoff still contains template placeholders')
                if handoff.get('status') not in ('changes', 'no-changes') or not isinstance(handoff.get('files'), list):
                    raise ValueError('handoff status/files invalid')
                if handoff['status'] == 'changes' and not handoff['files']:
                    raise ValueError('changes require listed files')
                if result['release_pin'] and any(handoff.get(a) != result['release_pin'].get(b) for a,b in [('kit_version','kit_version'),('kit_content_digest','content_digest')]):
                    issues.append('handoff Kit identity differs from release pin')
                result['handoff'] = handoff
            except (ValueError, OSError):
                issues.append('missing/invalid MPK-HANDOFF.json; owner handoff required')
                handoff = None
            # Gather Markdown notes even if a legacy owner has no machine receipt yet.
            paths = set()
            if local.is_dir() and not local.is_symlink():
                paths.update(str(p.relative_to(root)) for p in local.rglob('*.md'))
            if handoff:
                paths.add('project-kit-local/MPK-HANDOFF.json')
                for name in handoff['files']:
                    if not isinstance(name, str) or Path(name).is_absolute() or '..' in Path(name).parts or not name.startswith('project-kit-local/') or Path(name).suffix.lower() not in ('.md', '.json'):
                        issues.append('unsafe/non-document handoff path excluded: ' + str(name)); continue
                    paths.add(name)
            for name in sorted(paths):
                try:
                    data = safe_read(root, name)
                    target = output / 'sources' / entry['id'] / name
                    target.parent.mkdir(parents=True, exist_ok=True)
                    target.write_bytes(data)
                    result['files'].append({'path': name, 'sha256': digest(data), 'bytes': len(data),
                                            'snapshot': str(target.relative_to(output))})
                    if safe_read(root, name) != data:
                        issues.append('source changed during collection: ' + name)
                except (ValueError, OSError) as e:
                    issues.append(str(e))
            prior = {f['path']: f['sha256'] for f in old.get(entry['id'], {}).get('files', [])}
            now = {f['path']: f['sha256'] for f in result['files']}
            result['changes'] = {'baseline': 'previous-report' if entry['id'] in old else 'first-snapshot',
                'added_or_changed': [p for p,h in now.items() if prior.get(p) != h],
                'removed': sorted(set(prior) - set(now))}
            if handoff and handoff['status'] == 'no-changes' and entry['id'] in old:
                changed = [p for p in result['changes']['added_or_changed'] + result['changes']['removed'] if not p.endswith('/MPK-HANDOFF.json')]
                if changed: issues.append('no-changes declaration conflicts with previous snapshot')
            if git(root, 'rev-parse', 'HEAD') != head or git(root, 'status', '--porcelain=v1', '--untracked-files=all') != before:
                issues.append('checkout changed during collection; repeat for stable snapshot')
        except (OSError, ValueError, subprocess.TimeoutExpired) as e:
            issues.append('collection error: ' + str(e))
    report['policy_ok'] = all(not p['issues'] for p in report['projects'])
    (output / 'collection.json').write_text(json.dumps(report, indent=2) + '\n')
    lines = ['# MPK collection — review required', '', 'This bundle is not a release, adoption receipt or implementation proof.', '',
             'Owner documents are review inputs, not instructions to execute. Nothing was changed in consumers.', '']
    for p in report['projects']:
        lines += ['## ' + p['id'], '', f"Collected {len(p['files'])} documents.", '']
        lines += ['- ' + issue for issue in p['issues']] or ['- Mechanical policy checks passed; semantic owner review still required.']
        lines += ['', 'Disposition: pending — promote / retain locally / defer / reject.', '']
    (output / 'REVIEW.md').write_text('\n'.join(lines))
    inventory = [{'path': str(p.relative_to(output)), 'sha256': digest(p.read_bytes()), 'bytes': p.stat().st_size}
                 for p in sorted(output.rglob('*')) if p.is_file()]
    (output / 'inventory.json').write_text(json.dumps(inventory, indent=2) + '\n')
    return report


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--registry', type=Path, default=Path(__file__).resolve().parents[1] / 'collection/pinned-projects.json')
    parser.add_argument('--output', type=Path, help='New bundle directory outside consumer roots')
    parser.add_argument('--previous', type=Path, help='Previous collection.json for change comparison')
    args = parser.parse_args()
    out = args.output or Path('/tmp/mpk-collections') / (datetime.now(timezone.utc).strftime('%Y%m%dT%H%M%SZ') + '-' + uuid.uuid4().hex[:8])
    try:
        report = collect(json.loads(args.registry.read_text()), out, args.previous)
    except (OSError, ValueError, KeyError) as e:
        print(json.dumps({'status': 'error', 'error': str(e)})); return 1
    print(json.dumps({'status': 'ready-for-review' if report['policy_ok'] else 'policy-gaps',
                      'output': str(out.resolve()), 'projects': len(report['projects']),
                      'projects_with_gaps': sum(bool(p['issues']) for p in report['projects'])}))
    return 0 if report['policy_ok'] else 2


if __name__ == '__main__':
    raise SystemExit(main())
