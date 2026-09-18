#!/usr/bin/env python3
"""Commit/push verified installed MPK files only. Preview by default; --apply writes."""
import argparse
from datetime import datetime, timezone
import json
from pathlib import Path
import subprocess
import sys

from install_mpk import atomic_json, release_files, sha, tree_state

INITIAL_INDEX = ('# Local MPK guidance\n\nKeep project-specific decisions here. When asked to update MPK, record findings and\n'
                 'prepare MPK-HANDOFF.json using the forms in ../project-kit/lifecycle/.\n'
                 'Read ../project-kit/docs/COLLECTING-PROJECT-UPDATES.md. No findings or acceptance are inferred\n'
                 'from this initial index. Preserve root project guidance and release authority.\n')


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
    paths = ['project-kit', 'moondance.lock.json']
    if entry.get('created_local_index'):
        index = root / 'project-kit-local/README.md'
        if index.is_symlink() or index.read_text() != INITIAL_INDEX:
            raise ValueError('installer-created local index changed; owner review required')
        paths.append('project-kit-local/README.md')
    return root, paths


def publish_project(root, paths, tag, apply, prior, save, verify_commit=None):
    top = git(root, 'rev-parse', '--show-toplevel', check=False)
    if top is None: return {'status': 'skipped-non-git'}
    if Path(top).resolve() != root.resolve(): raise ValueError('not a repository root')
    branch = git(root, 'symbolic-ref', '--quiet', '--short', 'HEAD', check=False)
    if not branch: raise ValueError('detached HEAD; no branch selected')
    tracking = git(root, 'for-each-ref', '--format=%(upstream:remotename)|%(upstream:remoteref)', 'refs/heads/' + branch)
    remote, ref = tracking.split('|')
    if remote != 'origin' or not ref.startswith('refs/heads/'):
        raise ValueError('an existing origin tracking branch is required')
    origin = git(root, 'remote', 'get-url', 'origin')
    head = git(root, 'rev-parse', 'HEAD')
    remote_lines = git(root, 'ls-remote', '--heads', 'origin', ref).splitlines()
    if len(remote_lines) != 1: raise ValueError('tracking branch missing on origin')
    remote_head = remote_lines[0].split()[0]
    context = {'branch': branch, 'remote_ref': ref, 'head': head, 'origin': origin, 'paths': paths, 'tag': tag}
    if git(root, 'diff', '--cached', '--name-only'):
        raise ValueError('staged changes present; left untouched')
    # Never mutate a checkout already in the middle of another Git operation.
    for marker in ('MERGE_HEAD', 'CHERRY_PICK_HEAD', 'REVERT_HEAD', 'rebase-merge', 'rebase-apply'):
        location = Path(git(root, 'rev-parse', '--git-path', marker))
        if not location.is_absolute(): location = root / location
        if location.exists(): raise ValueError('Git operation in progress: ' + marker)
    resume = (prior.get('commit') == head and prior.get('parent') == remote_head
              and all(prior.get(k) == context[k] for k in ('branch', 'remote_ref', 'origin', 'paths', 'tag')))
    if head != remote_head and not resume:
        raise ValueError('HEAD differs from origin; reconcile unrelated unpublished/ahead/behind work first')
    if resume:
        if git(root, 'rev-parse', head + '^') != remote_head:
            raise ValueError('resume commit has unexpected parent')
        changed = git(root, 'diff-tree', '--no-commit-id', '--name-only', '-r', head).splitlines()
        if any(not any(n == p or n.startswith(p + '/') for p in paths) for n in changed):
            raise ValueError('resume commit contains out-of-scope paths')
        if git(root, 'status', '--porcelain=v1', '--', *paths):
            raise ValueError('installed paths changed since recorded commit')
        result = {**context, 'commit': head, 'parent': remote_head, 'status': 'would-resume-push'}
    else:
        if not git(root, 'status', '--porcelain=v1', '--untracked-files=all', '--', *paths):
            if verify_commit: verify_commit(head)
            return {**context, 'status': 'already-published', 'commit': head}
        result = {**context, 'status': 'would-commit-and-push', 'parent': head}
    if not apply: return result
    # Journal before writes, allowing a retry to recognize only this operation's commit.
    save({**result, 'status': 'preparing'})
    if not resume:
        if git(root, 'rev-parse', 'HEAD') != head or git(root, 'diff', '--cached', '--name-only'):
            raise ValueError('HEAD or index changed during preparation')
        git(root, 'add', '-A', '--', *paths)
        try:
            git(root, 'commit', '--only', '-m', 'Adopt Moondance Project Kit ' + tag[1:], '--', *paths)
        except Exception:
            # Preserve state for inspection; never reset another process's index.
            save({**result, 'status': 'commit-failed-review-index'})
            raise
        result['commit'] = git(root, 'rev-parse', 'HEAD')
        save({**result, 'status': 'committed-push-pending'})
        changed = git(root, 'diff-tree', '--no-commit-id', '--name-only', '-r', result['commit']).splitlines()
        if git(root, 'rev-parse', result['commit'] + '^') != head or any(not any(n == p or n.startswith(p + '/') for p in paths) for n in changed):
            raise ValueError('unexpected commit contents/parent; not pushed')
    if verify_commit: verify_commit(result['commit'])
    # Push the recorded commit, never a newer moving HEAD; normal fast-forward checks apply.
    git(root, 'push', 'origin', result['commit'] + ':' + ref)
    observed = git(root, 'ls-remote', '--heads', 'origin', ref).splitlines()
    if len(observed) != 1 or observed[0].split()[0] != result['commit']:
        raise ValueError('push not confirmed at exact remote commit')
    result['status'] = 'published'
    save(result)
    return result


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
        for entry in entries:
            key = entry['id']; prior = dict(state['projects'].get(key, {}))
            def save(value):
                state['projects'][key] = value
                atomic_json(journal, state)
            try:
                root, paths = validate_install(entry, release)
                if root.resolve() == a.source.resolve(): raise ValueError('publisher source cannot be a consumer')
                def verify_commit(commit):
                    names = git(root, 'ls-tree', '-r', '--name-only', commit, '--', 'project-kit').splitlines()
                    expected = {'project-kit/' + name for name in release[2]}
                    if set(names) != expected: raise ValueError('committed Kit membership differs from release (possibly ignored files)')
                    for name, data in release[2].items():
                        raw = subprocess.check_output(['git','-C',str(root),'show',commit+':project-kit/'+name], timeout=20)
                        if raw != data: raise ValueError('committed Kit bytes differ: ' + name)
                    for name in paths[1:]:
                        raw = subprocess.check_output(['git','-C',str(root),'show',commit+':'+name], timeout=20)
                        if raw != (root/name).read_bytes(): raise ValueError('committed metadata differs: ' + name)
                result = publish_project(root, paths, distribution['tag'], a.apply, prior, save, verify_commit)
                if a.apply: save(result)
            except (OSError, ValueError, KeyError, subprocess.SubprocessError) as ex:
                result = {'status': 'blocked', 'error': str(ex)}
                if a.apply:
                    # Retain any committed-push-pending identity for a safe resume.
                    retained = state['projects'].get(key, prior)
                    save({**retained, 'last_error': str(ex)})
            outcomes.append({'id': key, **result})
        report = a.state_dir / ('result-apply.json' if a.apply else 'result-preview.json')
        atomic_json(report, {'apply': a.apply, 'projects': outcomes})
        print(json.dumps({'report': str(report.resolve()), 'projects': outcomes}))
        return 2 if any(e['status'] in ('blocked', 'skipped-non-git') for e in outcomes) else 0
    except (OSError, ValueError, KeyError, subprocess.SubprocessError) as ex:
        print(json.dumps({'status': 'error', 'error': str(ex)})); return 1


if __name__ == '__main__': sys.exit(main())
