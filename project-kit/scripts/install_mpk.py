#!/usr/bin/env python3
"""Preview/apply an exact published MPK tag into registered project directories."""
import argparse
from datetime import datetime, timezone
import hashlib
import json
from pathlib import Path, PurePosixPath
import re
import shutil
import subprocess
import tempfile
import uuid


def sha(data):
    return hashlib.sha256(data).hexdigest()


def git(repo, *args):
    return subprocess.check_output(['git', '-C', str(repo), *args], stderr=subprocess.PIPE, timeout=60)


def release_files(repo, tag, remote=True):
    if not re.fullmatch(r'v\d+\.\d+\.\d+', tag):
        raise ValueError('an exact release tag vN.N.N is required')
    commit = git(repo, 'rev-parse', tag + '^{commit}').decode().strip()
    if remote:
        refs = git(repo, 'ls-remote', 'origin', 'refs/tags/' + tag, 'refs/tags/' + tag + '^{}').decode().splitlines()
        refs = dict(line.split()[::-1] for line in refs)
        if refs.get('refs/tags/' + tag + '^{}', refs.get('refs/tags/' + tag)) != commit:
            raise ValueError('origin release tag missing or differs from local tag')
    modes = {}
    for row in git(repo, 'ls-tree', '-r', '-z', commit).split(b'\0'):
        if row:
            meta, name = row.split(b'\t', 1)
            modes[name.decode()] = meta.split()[0]
    raw = git(repo, 'show', commit + ':manifest.json')
    manifest = json.loads(raw)
    if manifest['kit_version'] != tag[1:]:
        raise ValueError('tag and manifest version differ')
    if sha(json.dumps(manifest['files'], sort_keys=True, separators=(',', ':')).encode()) != manifest['content_digest']:
        raise ValueError('manifest digest mismatch')
    files = {'manifest.json': raw}
    for entry in manifest['files']:
        name = entry['path']; parts = PurePosixPath(name)
        if parts.is_absolute() or '..' in parts.parts or str(parts) != name or name in files or '.git' in parts.parts:
            raise ValueError('unsafe or duplicate manifest path: ' + name)
        if modes.get(name) not in (b'100644', b'100755'):
            raise ValueError('non-regular release file: ' + name)
        data = git(repo, 'show', commit + ':' + name)
        if len(data) != entry['bytes'] or sha(data) != entry['sha256']:
            raise ValueError('release file mismatch: ' + name)
        files[name] = data
    if tuple(map(int, tag[1:].split('.'))) >= (0, 1, 9):
        markers = [name for name in files if re.fullmatch(r'VERSION-.*\.md', name)]
        if markers != ['VERSION-' + tag[1:] + '.md']:
            raise ValueError('release must have exactly one matching version marker')
    if set(modes) != set(files):
        raise ValueError('tagged tree and release inventory differ')
    return commit, manifest, files


def tree_state(path):
    if path.is_symlink():
        raise ValueError('symlink destination: ' + str(path))
    if not path.exists(): return None
    if path.is_file(): return sha(path.read_bytes())
    result = {}
    for p in sorted(path.rglob('*')):
        if p.is_symlink(): raise ValueError('symlink in existing Kit: ' + str(p))
        if p.is_file(): result[str(p.relative_to(path))] = sha(p.read_bytes())
    return result


def atomic_json(path, value):
    temp = path.with_name(path.name + '.' + uuid.uuid4().hex + '.tmp')
    try:
        temp.write_text(json.dumps(value, indent=2) + '\n')
        temp.replace(path)
    finally:
        temp.unlink(missing_ok=True)


def install(root, release, state, apply=False):
    commit, manifest, files = release
    if not root.is_dir() or root.resolve() == Path.home().resolve():
        raise ValueError('destination must be an existing project directory, not home')
    if root.is_symlink(): raise ValueError('linked project directory requires an explicit real path')
    target, lock = root / 'project-kit', root / 'moondance.lock.json'
    local = root / 'project-kit-local'
    if local.is_symlink() or (local.exists() and not local.is_dir()):
        raise ValueError('local supplements path is not a regular directory')
    if target.exists() and not target.is_dir(): raise ValueError('project-kit is not a directory')
    old_tree, old_lock = tree_state(target), tree_state(lock)
    lock_data = json.loads(lock.read_text()) if lock.exists() else {}
    if not isinstance(lock_data, dict): raise ValueError('existing lock is not an object')
    desired = {p: sha(b) for p,b in files.items()}
    pin = {'kit_version': manifest['kit_version'], 'content_digest': manifest['content_digest'], 'source_revision': commit,
           'source_tag': 'v' + manifest['kit_version']}
    same = old_tree == desired and all(lock_data.get(k) == v for k,v in pin.items())
    report = {'path': str(root), 'status': 'already-installed' if same else 'would-install', **pin,
              'replaced_files': len(old_tree or {}), 'release_files': len(files),
              'consumer_git': 'not committed or pushed by installer', 'runtime_acceptance': 'not verified'}
    if same or not apply: return report
    state.mkdir(parents=True, exist_ok=False)
    if target.exists(): shutil.copytree(target, state / 'previous-project-kit')
    if lock.exists(): shutil.copy2(lock, state / 'previous-moondance.lock.json')
    atomic_json(state / 'receipt.json', {**report, 'status': 'prepared', 'previous_lock': lock_data})
    stage = Path(tempfile.mkdtemp(prefix='.mpk-stage-', dir=root))
    displaced = root / ('.mpk-previous-' + uuid.uuid4().hex)
    had_target, installed = False, False
    index = local / 'README.md'
    made_index = False
    try:
        for name, data in files.items():
            p = stage / name; p.parent.mkdir(parents=True, exist_ok=True); p.write_bytes(data)
        if tree_state(stage) != desired: raise ValueError('staged files failed verification')
        if tree_state(target) != old_tree or tree_state(lock) != old_lock:
            raise ValueError('destination changed while preparing; nothing replaced')
        # Journal and retained backup make interrupted installation diagnosable.
        atomic_json(state / 'receipt.json', {**report, 'status': 'replacing', 'displaced_path': str(displaced)})
        if target.exists(): target.rename(displaced); had_target = True
        stage.rename(target); installed = True
        new_lock = dict(lock_data); new_lock.update(pin)
        new_lock.update(installed_at=datetime.now(timezone.utc).isoformat(),
                        status='installed-documentation-runtime-unverified', installed_path='project-kit',
                        implementation_acceptance='unverified')
        atomic_json(lock, new_lock)
        local.mkdir(exist_ok=True)
        if not index.exists():
            with index.open('x') as out:
                out.write('# Local MPK guidance\n\nKeep project-specific decisions here. When asked to update MPK, record findings and\nprepare MPK-HANDOFF.json using the forms in ../project-kit/lifecycle/.\nRead ../project-kit/docs/COLLECTING-PROJECT-UPDATES.md. No findings or acceptance are inferred\nfrom this initial index. Preserve root project guidance and release authority.\n')
            made_index = True
        if tree_state(target) != desired: raise ValueError('installed files failed verification')
        if any(json.loads(lock.read_text()).get(k) != v for k,v in pin.items()): raise ValueError('installed pin differs')
        report.update(status='installed', backup=str(state), created_local_index=made_index)
        atomic_json(state / 'receipt.json', report)
    except Exception:
        if installed:
            shutil.rmtree(target)
            if had_target: displaced.rename(target)
            if old_lock is None: lock.unlink(missing_ok=True)
            else: shutil.copy2(state / 'previous-moondance.lock.json', lock)
            if made_index: index.unlink()
        elif had_target:
            displaced.rename(target)
        raise
    finally:
        if stage.exists(): shutil.rmtree(stage)
    if displaced.exists(): shutil.rmtree(displaced)
    return report


def main():
    p = argparse.ArgumentParser(description=__doc__)
    p.add_argument('--source', type=Path, default=Path(__file__).resolve().parents[1])
    p.add_argument('--tag', required=True)
    p.add_argument('--registry', type=Path)
    p.add_argument('--state-dir', type=Path, help='New directory for receipts/backups; defaults to /tmp')
    p.add_argument('--only', nargs='+', help='Registry IDs to include')
    p.add_argument('--apply', action='store_true')
    p.add_argument('--include-workspaces', action='store_true', help='Also install into registered non-Git task folders')
    a = p.parse_args()
    source = a.source.expanduser().resolve()
    state = (a.state_dir or Path('/tmp/mpk-installations') / uuid.uuid4().hex).expanduser().resolve()
    try:
        config = json.loads((a.registry or source / 'collection/pinned-projects.json').read_text())
        entries = config['projects']
        if a.only:
            if set(a.only) - {e['id'] for e in entries}: raise ValueError('unknown project ID')
            entries = [e for e in entries if e['id'] in a.only]
        ids = [e['id'] for e in entries]
        if len(set(ids)) != len(ids) or any(not re.fullmatch(r'[a-z0-9-]+', i) for i in ids): raise ValueError('invalid project IDs')
        roots = [Path(e['path']).expanduser().absolute() for e in entries]
        if any(state.is_relative_to(r.resolve()) for r in roots) or state.is_relative_to(source):
            raise ValueError('state directory must be outside source and destination projects')
        release = release_files(source, a.tag)
        state.mkdir(parents=True, exist_ok=False)
        results, seen = [], set()
        for e,root in zip(entries, roots):
            result = {'id': e['id'], 'owners': e['owners']}
            try:
                if root.resolve() == source or root.resolve() in seen: raise ValueError('source or duplicate destination excluded')
                seen.add(root.resolve())
                try: top = Path(git(root, 'rev-parse', '--show-toplevel').decode().strip()).resolve()
                except subprocess.CalledProcessError: top = None
                if top is None and not a.include_workspaces:
                    result.update(status='skipped-non-git-workspace', path=str(root))
                elif top is not None and top != root.resolve():
                    raise ValueError('registry path must be the checkout root')
                else:
                    result.update(install(root, release, state / e['id'], a.apply))
            except (OSError, ValueError, subprocess.SubprocessError) as ex:
                result.update(status='error', error=str(ex), path=str(root))
            results.append(result)
            atomic_json(state / 'distribution.json', {'tag': a.tag, 'commit': release[0], 'apply': a.apply, 'projects': results})
        print(json.dumps({'output': str(state), 'apply': a.apply, 'projects': results}))
        return 2 if any(r['status'] in ('error','skipped-non-git-workspace') for r in results) else 0
    except (OSError, ValueError, KeyError, subprocess.SubprocessError) as ex:
        print(json.dumps({'status': 'error', 'error': str(ex)})); return 1


if __name__ == '__main__': raise SystemExit(main())
