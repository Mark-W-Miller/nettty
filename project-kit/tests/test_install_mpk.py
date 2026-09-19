import importlib.util
import json
from pathlib import Path
import tempfile
import subprocess
import unittest
from unittest.mock import patch

spec = importlib.util.spec_from_file_location('installer', Path(__file__).resolve().parents[1] / 'scripts/install_mpk.py')
i = importlib.util.module_from_spec(spec); spec.loader.exec_module(i)


class InstallTests(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory(); self.base = Path(self.temp.name).resolve()
        self.root = self.base / 'project'; self.root.mkdir()
        (self.root / 'project-kit').mkdir(); (self.root / 'project-kit/obsolete.md').write_text('old')
        (self.root / 'project-kit-local').mkdir(); (self.root / 'project-kit-local/README.md').write_text('Keep me')
        (self.root / 'AGENTS.md').write_text('Local authority')
        (self.root / 'moondance.lock.json').write_text('{"custom":"preserve"}')
        self.files = {'README.md': b'new', 'manifest.json': b'{}'}
        self.release = ('a' * 40, {'kit_version':'0.1.8', 'content_digest':'test'}, self.files)

    def tearDown(self): self.temp.cleanup()

    def test_tagged_source_verification(self):
        repo = self.base / 'release'; repo.mkdir()
        (repo / 'README.md').write_bytes(b'released')
        entries = [{'path':'README.md', 'bytes':8, 'sha256':i.sha(b'released')}]
        manifest = {'kit_version':'0.1.8', 'files':entries,
                    'content_digest':i.sha(json.dumps(entries,sort_keys=True,separators=(',',':')).encode())}
        (repo / 'manifest.json').write_text(json.dumps(manifest))
        def g(*args):
            return subprocess.check_output(['git','-C',str(repo),*args],stderr=subprocess.PIPE)
        g('init','-q'); g('add','.'); g('-c','user.name=Test','-c','user.email=test@example.invalid','commit','-qm','fixture')
        g('tag','v0.1.8')
        loaded = i.release_files(repo,'v0.1.8',remote=False)
        self.assertEqual(loaded[2]['README.md'],b'released')
        (repo / 'README.md').write_text('dirty working copy')
        self.assertEqual(i.release_files(repo,'v0.1.8',remote=False)[2]['README.md'],b'released')
        with self.assertRaises(subprocess.CalledProcessError): i.release_files(repo,'v0.1.8')
        g('tag','v0.1.9')
        with self.assertRaises(ValueError): i.release_files(repo,'v0.1.9',remote=False)

    def test_preview_is_read_only(self):
        before = i.tree_state(self.root)
        r = i.install(self.root, self.release, self.base / 'state')
        self.assertEqual(r['status'], 'would-install'); self.assertEqual(before, i.tree_state(self.root))
        self.assertFalse((self.base / 'state').exists())

    def test_replace_preserve_backup_and_idempotence(self):
        r = i.install(self.root, self.release, self.base / 'state', True)
        self.assertEqual(r['status'],'installed')
        self.assertFalse((self.root / 'project-kit/obsolete.md').exists())
        self.assertEqual((self.base / 'state/previous-project-kit/obsolete.md').read_text(),'old')
        self.assertEqual((self.root / 'project-kit-local/README.md').read_text(),'Keep me')
        self.assertEqual((self.root / 'AGENTS.md').read_text(),'Local authority')
        self.assertEqual(json.loads((self.root / 'moondance.lock.json').read_text())['custom'],'preserve')
        self.assertEqual(i.install(self.root,self.release,self.base/'again',True)['status'],'already-installed')

    def test_new_version_replaces_old_marker(self):
        (self.root / 'project-kit/VERSION-0.1.8.md').write_text('old marker')
        self.files['VERSION-0.1.9.md'] = b'# Moondance Project Kit 0.1.9'
        self.release[1]['kit_version'] = '0.1.9'
        i.install(self.root,self.release,self.base/'state',True)
        self.assertEqual([p.name for p in (self.root/'project-kit').glob('VERSION-*.md')],['VERSION-0.1.9.md'])
        self.assertEqual((self.root/'project-kit-local/README.md').read_text(),'Keep me')

    def test_symlink_destination_rejected(self):
        (self.root / 'project-kit/leak').symlink_to(self.root / 'AGENTS.md')
        with self.assertRaises(ValueError): i.install(self.root,self.release,self.base/'state',True)

    def test_invalid_lock_preserved(self):
        (self.root / 'moondance.lock.json').write_text('[]')
        before=i.tree_state(self.root)
        with self.assertRaises(ValueError): i.install(self.root,self.release,self.base/'state',True)
        self.assertEqual(before,i.tree_state(self.root))

    def test_lock_failure_rolls_back(self):
        before = i.tree_state(self.root); original=i.atomic_json
        def fail(path, value):
            if path.name=='moondance.lock.json': raise OSError('simulated write failure')
            return original(path,value)
        with patch.object(i,'atomic_json',side_effect=fail):
            with self.assertRaises(OSError): i.install(self.root,self.release,self.base/'state',True)
        self.assertEqual(before,i.tree_state(self.root))


if __name__=='__main__': unittest.main()
