from pathlib import Path
import subprocess
import sys
import tempfile
import unittest

sys.path.insert(0, str(Path(__file__).resolve().parents[1] / 'scripts'))
import publish_mpk_installs as pub


class PublishTests(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory(); self.base = Path(self.temp.name).resolve()
        self.remote = self.base / 'origin.git'; self.root = self.base / 'project'
        subprocess.run(['git', 'init', '--bare', '-q', str(self.remote)], check=True)
        subprocess.run(['git', 'clone', '-q', str(self.remote), str(self.root)], check=True,
                       stderr=subprocess.PIPE)
        self.g('config', 'user.name', 'Test'); self.g('config', 'user.email', 'test@example.invalid')
        (self.root / 'unrelated.md').write_text('original')
        self.g('add', '.'); self.g('commit', '-qm', 'initial'); self.g('push', '-qu', 'origin', 'HEAD')
        branch = self.g('branch', '--show-current')
        subprocess.run(['git', '-C', str(self.remote), 'symbolic-ref', 'HEAD', 'refs/heads/' + branch], check=True)
        self.release = ('source', {'kit_version': '0.1.9'}, {'README.md': b'Kit\n'})
        self.lock = b'{"kit_version":"0.1.9"}\n'

    def g(self, *args): return pub.git(self.root, *args)
    def tearDown(self): self.temp.cleanup()

    def publish(self, apply=True):
        return pub.publish_repository(self.root, ['example'], self.release, self.lock, 'v0.1.9',
                                      apply, self.base / 'delivery-worktree')

    def test_dirty_and_staged_application_files_do_not_block(self):
        (self.root / 'unrelated.md').write_text('active coding')
        self.g('add', 'unrelated.md')
        before_index = self.g('diff', '--cached')
        result = self.publish()
        self.assertEqual(result['status'], 'published')
        self.assertEqual((self.root / 'unrelated.md').read_text(), 'active coding')
        self.assertEqual(self.g('diff', '--cached'), before_index)
        remote = self.g('ls-remote', '--heads', 'origin', result['remote_ref']).split()[0]
        self.assertEqual(remote, result['commit'])

    def test_existing_local_default_branch_commits_are_included(self):
        (self.root / 'unrelated.md').write_text('committed work')
        self.g('add', 'unrelated.md'); self.g('commit', '-qm', 'active project commit')
        local = self.g('rev-parse', 'HEAD')
        result = self.publish()
        self.assertEqual(result['included_local_head'], local)
        self.assertEqual(pub.git(self.root, 'merge-base', '--is-ancestor', local, result['commit'], check=False), '')

    def test_existing_local_release_commit_is_pushed(self):
        (self.root / 'project-kit').mkdir()
        (self.root / 'project-kit/README.md').write_bytes(self.release[2]['README.md'])
        (self.root / 'moondance.lock.json').write_bytes(self.lock)
        self.g('add', 'project-kit', 'moondance.lock.json')
        self.g('commit', '-qm', 'local MPK delivery')
        local = self.g('rev-parse', 'HEAD')
        result = self.publish()
        self.assertEqual(result['status'], 'published')
        self.assertEqual(result['commit'], local)
        self.assertEqual(self.g('ls-remote', '--heads', 'origin', result['remote_ref']).split()[0], local)

    def test_preview_does_not_publish(self):
        before = self.g('ls-remote', '--heads', 'origin').split()[0]
        result = self.publish(False)
        self.assertEqual(result['status'], 'would-commit-and-push')
        self.assertEqual(self.g('ls-remote', '--heads', 'origin').split()[0], before)

    def test_second_run_is_already_published(self):
        self.publish()
        self.assertEqual(self.publish()['status'], 'already-published')


if __name__ == '__main__': unittest.main()
