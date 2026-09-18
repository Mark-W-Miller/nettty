import importlib.util
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest
from unittest.mock import patch

sys.path.insert(0, str(Path(__file__).resolve().parents[1] / 'scripts'))
import publish_mpk_installs as pub


class PublishTests(unittest.TestCase):
    def setUp(self):
        self.temp=tempfile.TemporaryDirectory(); self.base=Path(self.temp.name).resolve()
        self.remote=self.base/'origin.git'; self.root=self.base/'project'
        subprocess.run(['git','init','--bare','-q',str(self.remote)],check=True)
        subprocess.run(['git','clone','-q',str(self.remote),str(self.root)],check=True,stderr=subprocess.PIPE)
        self.g('config','user.name','Test'); self.g('config','user.email','test@example.invalid')
        (self.root/'unrelated.md').write_text('original')
        self.g('add','.');self.g('commit','-qm','initial');self.g('push','-qu','origin','HEAD')
        self.paths=['project-kit','moondance.lock.json']
        (self.root/'project-kit').mkdir();(self.root/'project-kit/README.md').write_text('Kit')
        (self.root/'moondance.lock.json').write_text('{}')
        self.journal={}

    def g(self,*args):return pub.git(self.root,*args)
    def tearDown(self):self.temp.cleanup()
    def run_publish(self,apply=True):
        return pub.publish_project(self.root,self.paths,'v0.1.9',apply,dict(self.journal),lambda r:self.journal.update(r))

    def test_preview_and_scoped_commit(self):
        original=self.g('rev-parse','HEAD')
        (self.root/'unrelated.md').write_text('keep dirty')
        self.assertEqual(self.run_publish(False)['status'],'would-commit-and-push')
        self.assertEqual(original,self.g('rev-parse','HEAD'))
        result=self.run_publish();self.assertEqual(result['status'],'published')
        names=self.g('diff-tree','--no-commit-id','--name-only','-r','HEAD').splitlines()
        self.assertEqual(set(names),{'project-kit/README.md','moondance.lock.json'})
        self.assertEqual((self.root/'unrelated.md').read_text(),'keep dirty')
        self.assertEqual(self.run_publish()['status'],'already-published')

    def test_staged_work_blocks_without_changes(self):
        self.g('add','unrelated.md')
        (self.root/'unrelated.md').write_text('staged');self.g('add','unrelated.md')
        index=self.g('diff','--cached');head=self.g('rev-parse','HEAD')
        with self.assertRaisesRegex(ValueError,'staged'):self.run_publish()
        self.assertEqual(index,self.g('diff','--cached'));self.assertEqual(head,self.g('rev-parse','HEAD'))

    def test_unrelated_ahead_commit_blocks(self):
        (self.root/'unrelated.md').write_text('committed');self.g('add','unrelated.md');self.g('commit','-qm','unpublished')
        with self.assertRaisesRegex(ValueError,'HEAD differs'):self.run_publish()

    def test_push_failure_can_resume_recorded_commit(self):
        real=pub.git
        def fail(root,*args,**kwargs):
            if args[0]=='push':raise ValueError('simulated network failure')
            return real(root,*args,**kwargs)
        with patch.object(pub,'git',side_effect=fail):
            with self.assertRaisesRegex(ValueError,'network'):self.run_publish()
        commit=self.g('rev-parse','HEAD')
        self.assertEqual(self.journal['commit'],commit)
        self.assertEqual(self.run_publish()['status'],'published')
        self.assertEqual(self.g('rev-parse','HEAD'),commit)

    def test_non_git_skipped(self):
        folder=self.base/'non-git';folder.mkdir()
        r=pub.publish_project(folder,[], 'v0.1.9',True,{},lambda r:None)
        self.assertEqual(r['status'],'skipped-non-git')

    def test_verification_failure_never_pushes(self):
        original=self.g('rev-parse','HEAD')
        def reject(commit):raise ValueError('wrong committed bytes')
        with self.assertRaisesRegex(ValueError,'wrong committed'):
            pub.publish_project(self.root,self.paths,'v0.1.9',True,{},lambda r:self.journal.update(r),reject)
        self.assertTrue(self.g('ls-remote','--heads','origin').startswith(original))


if __name__=='__main__':unittest.main()
