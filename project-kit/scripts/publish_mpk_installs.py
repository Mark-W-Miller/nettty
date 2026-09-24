#!/usr/bin/env python3
"""Commit/push verified installed MPK files only. Preview by default; --apply writes."""
import argparse
import json
from pathlib import Path
import shutil
import subprocess
import sys

from install_mpk import atomic_json, release_files, sha, tree_state


def git(root, *args, check=True):
    p = subprocess.run(['git', '-C', str(root), *args], capture_output=True, text=True, timeout=90)
    if check and p.returncode:
        raise ValueError(p.stderr.strip() or p.stdout.strip() or 'Git command failed')
    return p.stdout.strip() if p.returncode == 0 else None


def validate_install(entry, release):
    root = Path(entry['path']).expanduser().absolute()
    commit, manifest, files = release
    if entry.get('status') not in ('installed', 'already-installed'):
        raise ValueError('installation receipt does not confirm installation')
    if entry.get('source_revision') != commit or entry.get('content_digest') != manifest['content_digest']:
        raise ValueError('installation receipt differs from verified release')
    expected = {name: sha(data) for name, data in files.items()}
    if tree_state(root / 'project-kit') != expected:
        raise ValueError('installed Kit differs from exact release; reinstall/review first')
    lock = root / 'moondance.lock.json'
    if lock.is_symlink(): raise ValueError('release pin is a symlink')
    data = json.loads(lock.read_text())
    for key, val in [('source_revision', commit), ('content_digest', manifest['content_digest']),
                     ('kit_version', manifest['kit_version']), ('source_tag', 'v' + manifest['kit_version'])]:
        if data.get(key) != val: raise ValueError('release pin differs: ' + key)
    # Local guidance is collected separately and is never part of MPK delivery commits.
    return root, ['project-kit', 'moondance.lock.json']


def repository_identity(root):
    """Return the origin URL and its advertised default branch."""
    origin = git(root, 'remote', 'get-url', 'origin')
    advertised = git(root, 'ls-remote', '--symref', 'origin', 'HEAD').splitlines()
    symbolic = [line for line in advertised if line.startswith('ref: refs/heads/') and line.endswith('\tHEAD')]
    heads = [line for line in advertised if not line.startswith('ref:') and line.endswith('\tHEAD')]
    if len(symbolic) != 1 or len(heads) != 1:
        raise ValueError('origin must advertise exactly one default branch')
    ref = symbolic[0].split()[1]
    return origin, ref, heads[0].split()[0]


def verify_release_commit(root, commit, release, lock_bytes):
    names = git(root, 'ls-tree', '-r', '--name-only', commit, '--', 'project-kit').splitlines()
    expected = {'project-kit/' + name for name in release[2]}
    if set(names) != expected:
        raise ValueError('committed Kit membership differs from release (possibly ignored files)')
    for name, data in release[2].items():
        raw = subprocess.check_output(['git', '-C', str(root), 'show', commit + ':project-kit/' + name], timeout=20)
        if raw != data: raise ValueError('committed Kit bytes differ: ' + name)
    raw = subprocess.check_output(['git', '-C', str(root), 'show', commit + ':moondance.lock.json'], timeout=20)
    if raw != lock_bytes: raise ValueError('committed release pin differs')


def publish_repository(root, member_ids, release, lock_bytes, tag, apply, workspace):
    """Publish one exact Kit commit on a repository's default branch via an isolated worktree."""
    origin, ref, advertised_head = repository_identity(root)
    branch = ref.removeprefix('refs/heads/')
    git(root, 'fetch', '--no-tags', 'origin', '+' + ref + ':refs/remotes/origin/' + branch)
    remote_head = git(root, 'rev-parse', 'refs/remotes/origin/' + branch)
    if remote_head != advertised_head:
        raise ValueError('origin default branch moved during preparation; retry')

    # Include existing local commits on the default branch when they are a fast-forward
    # continuation of origin. Diverged local work remains untouched and is not guessed at.
    local_ref = 'refs/heads/' + branch
    local_head = git(root, 'rev-parse', '--verify', local_ref, check=False)
    base = remote_head
    included_local_head = None
    merge_remote = False
    if local_head and git(root, 'merge-base', '--is-ancestor', remote_head, local_head, check=False) is not None:
        base = local_head
        included_local_head = local_head
    elif local_head and git(root, 'merge-base', '--is-ancestor', local_head, remote_head, check=False) is None:
        # Reconcile a diverged local default branch in isolation. Active worktrees and
        # their uncommitted files are never checked out, stashed or reset.
        base = local_head
        included_local_head = local_head
        merge_remote = True

    context = {'repository': origin, 'default_branch': branch, 'remote_ref': ref,
               'remote_parent': remote_head, 'members': member_ids, 'tag': tag}
    if workspace.exists():
        git(root, 'worktree', 'remove', '--force', str(workspace), check=False)
        shutil.rmtree(workspace, ignore_errors=True)
    workspace.parent.mkdir(parents=True, exist_ok=True)
    git(root, 'worktree', 'add', '--detach', str(workspace), base)
    try:
        if merge_remote:
            git(workspace, 'merge', '--no-edit', remote_head)
        kit = workspace / 'project-kit'
        if kit.exists() or kit.is_symlink():
            if kit.is_symlink() or kit.is_file(): kit.unlink()
            else: shutil.rmtree(kit)
        kit.mkdir()
        for name, data in release[2].items():
            target = kit / name; target.parent.mkdir(parents=True, exist_ok=True); target.write_bytes(data)
        (workspace / 'moondance.lock.json').write_bytes(lock_bytes)
        changed = git(workspace, 'status', '--porcelain=v1', '--untracked-files=all', '--',
                      'project-kit', 'moondance.lock.json')
        if not changed:
            verify_release_commit(workspace, base, release, lock_bytes)
            if base == remote_head:
                return {**context, 'status': 'already-published', 'commit': base,
                        'included_local_head': included_local_head}
            result = {**context, 'status': 'would-push-existing-default-head', 'commit': base,
                      'included_local_head': included_local_head}
            if not apply: return result
            git(workspace, 'push', 'origin', base + ':' + ref)
            observed = git(workspace, 'ls-remote', '--heads', 'origin', ref).splitlines()
            if len(observed) != 1 or observed[0].split()[0] != base:
                raise ValueError('push not confirmed at exact remote commit')
            return {**result, 'status': 'published'}
        result = {**context, 'status': 'would-commit-and-push', 'parent': base,
                  'included_local_head': included_local_head}
        if not apply: return result
        git(workspace, 'add', '-f', '-A', '--', 'project-kit', 'moondance.lock.json')
        git(workspace, 'commit', '--only', '-m', 'Adopt Moondance Project Kit ' + tag[1:], '--',
            'project-kit', 'moondance.lock.json')
        commit = git(workspace, 'rev-parse', 'HEAD')
        verify_release_commit(workspace, commit, release, lock_bytes)
        git(workspace, 'push', 'origin', commit + ':' + ref)
        observed = git(workspace, 'ls-remote', '--heads', 'origin', ref).splitlines()
        if len(observed) != 1 or observed[0].split()[0] != commit:
            raise ValueError('push not confirmed at exact remote commit')
        return {**result, 'status': 'published', 'commit': commit}
    finally:
        git(root, 'worktree', 'remove', '--force', str(workspace), check=False)
        shutil.rmtree(workspace, ignore_errors=True)


def main():
    p = argparse.ArgumentParser(description=__doc__)
    p.add_argument('--distribution', type=Path, required=True, help='Applied installer distribution.json')
    p.add_argument('--source', type=Path, default=Path(__file__).resolve().parents[1])
    p.add_argument('--state-dir', type=Path, required=True, help='External durable journal directory; reuse for retries')
    p.add_argument('--only', nargs='+')
    p.add_argument('--apply', action='store_true')
    a = p.parse_args()
    try:
        distribution = json.loads(a.distribution.read_text())
        if distribution.get('apply') is not True: raise ValueError('a preview is not installation evidence')
        release = release_files(a.source, distribution['tag'])
        if distribution['commit'] != release[0]: raise ValueError('distribution source commit mismatch')
        entries = distribution['projects']
        if a.only:
            if set(a.only) - {e['id'] for e in entries}: raise ValueError('unknown project ID')
            entries = [e for e in entries if e['id'] in a.only]
        roots = [Path(e['path']).expanduser().resolve() for e in entries]
        if len(roots) != len(set(roots)): raise ValueError('duplicate destinations')
        if any(a.state_dir.resolve().is_relative_to(r) for r in roots + [a.source.resolve()]):
            raise ValueError('journal must be outside project/source roots')
        a.state_dir.mkdir(parents=True, exist_ok=True)
        journal = a.state_dir / 'publication.json'
        identity = sha(a.distribution.read_bytes())
        state = json.loads(journal.read_text()) if journal.exists() else {'distribution_sha256': identity, 'projects': {}}
        if state['distribution_sha256'] != identity: raise ValueError('journal belongs to another distribution')
        outcomes = []
        groups = {}
        for entry in entries:
            key = entry['id']
            try:
                root, _ = validate_install(entry, release)
                if root.resolve() == a.source.resolve(): raise ValueError('publisher source cannot be a consumer')
                top = git(root, 'rev-parse', '--show-toplevel', check=False)
                if top is None:
                    outcomes.append({'id': key, 'status': 'skipped-non-git'})
                    continue
                origin, _, _ = repository_identity(root)
                groups.setdefault(origin, []).append((entry, root))
            except (OSError, ValueError, KeyError, subprocess.SubprocessError) as ex:
                outcomes.append({'id': key, 'status': 'blocked', 'error': str(ex)})

        for number, (origin, members) in enumerate(groups.items(), 1):
            ids = [entry['id'] for entry, _ in members]
            root = members[0][1]
            key = 'repository:' + sha(origin.encode())
            prior = dict(state['projects'].get(key, {}))
            lock_bytes = (root / 'moondance.lock.json').read_bytes()
            try:
                result = publish_repository(root, ids, release, lock_bytes, distribution['tag'], a.apply,
                                            a.state_dir / 'worktrees' / str(number))
                if a.apply:
                    state['projects'][key] = result
                    atomic_json(journal, state)
            except (OSError, ValueError, KeyError, subprocess.SubprocessError) as ex:
                result = {'status': 'blocked', 'repository': origin, 'members': ids, 'error': str(ex)}
                if a.apply:
                    state['projects'][key] = {**prior, 'last_error': str(ex)}
                    atomic_json(journal, state)
            for entry, _ in members:
                outcomes.append({'id': entry['id'], **result})
        report = a.state_dir / ('result-apply.json' if a.apply else 'result-preview.json')
        atomic_json(report, {'apply': a.apply, 'projects': outcomes})
        print(json.dumps({'report': str(report.resolve()), 'projects': outcomes}))
        return 2 if any(e['status'] in ('blocked', 'skipped-non-git') for e in outcomes) else 0
    except (OSError, ValueError, KeyError, subprocess.SubprocessError) as ex:
        print(json.dumps({'status': 'error', 'error': str(ex)})); return 1


if __name__ == '__main__': sys.exit(main())
