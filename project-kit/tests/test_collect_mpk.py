import importlib.util
import json
from pathlib import Path
import subprocess
import tempfile
import unittest

spec = importlib.util.spec_from_file_location('collector', Path(__file__).resolve().parents[1] / 'scripts/collect_mpk.py')
c = importlib.util.module_from_spec(spec)
spec.loader.exec_module(c)


class CollectorTests(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        self.base = Path(self.temp.name).resolve()
        self.root = self.base / 'project'
        self.root.mkdir()
        subprocess.run(['git', 'init', '-q', str(self.root)], check=True)
        (self.root / 'project-kit-local').mkdir()
        (self.root / 'project-kit').mkdir()
        self.note = self.root / 'project-kit-local/README.md'
        self.note.write_text('Local finding\n')
        lock = {'kit_version': '0.1.7', 'content_digest': 'example-digest'}
        (self.root / 'moondance.lock.json').write_text(json.dumps(lock))
        (self.root / 'project-kit/manifest.json').write_text(json.dumps(lock))
        self.handoff = self.root / 'project-kit-local/MPK-HANDOFF.json'
        self.handoff.write_text(json.dumps({'schema': c.POLICY, 'owner': 'Test', 'updated_at': '2026-09-18',
            'kit_version': '0.1.7', 'kit_content_digest': 'example-digest', 'summary': 'A finding',
            'status': 'changes', 'files': ['project-kit-local/README.md']}))
        self.config = {'projects': [{'id': 'test', 'owners': ['Test'], 'path': str(self.root)}]}

    def tearDown(self):
        self.temp.cleanup()

    def run_collect(self, name='out', previous=None):
        return c.collect(self.config, self.base / name, previous)

    def test_snapshot_and_read_only(self):
        before = {str(p): p.read_bytes() for p in self.root.rglob('*') if p.is_file()}
        r = self.run_collect()
        self.assertTrue(r['policy_ok'])
        self.assertEqual(before, {str(p): p.read_bytes() for p in self.root.rglob('*') if p.is_file()})
        for f in r['projects'][0]['files']:
            self.assertEqual(c.digest((self.base / 'out' / f['snapshot']).read_bytes()), f['sha256'])
        with self.assertRaises(FileExistsError): self.run_collect()

    def test_missing_owner_does_not_stop_other_projects(self):
        self.config['projects'].insert(0, {'id': 'missing', 'owners': ['Absent'], 'path': str(self.base / 'absent')})
        r = self.run_collect()
        self.assertFalse(r['policy_ok'])
        self.assertTrue(r['projects'][1]['files'])

    def test_symlink_and_traversal_excluded(self):
        secret = self.base / 'secret.md'; secret.write_text('PRIVATE')
        (self.root / 'project-kit-local/leak.md').symlink_to(secret)
        h = json.loads(self.handoff.read_text()); h['files'].append('project-kit-local/../../secret.md')
        self.handoff.write_text(json.dumps(h))
        r = self.run_collect()
        self.assertFalse(r['policy_ok'])
        self.assertFalse(any('leak' in f['path'] or 'secret' in f['path'] for f in r['projects'][0]['files']))

    def test_deltas_and_no_changes_conflict(self):
        self.run_collect()
        self.note.write_text('Changed\n')
        h = json.loads(self.handoff.read_text()); h['status'] = 'no-changes'
        self.handoff.write_text(json.dumps(h))
        r = self.run_collect('next', self.base / 'out/collection.json')
        self.assertIn('project-kit-local/README.md', r['projects'][0]['changes']['added_or_changed'])
        self.assertTrue(any('conflicts' in x for x in r['projects'][0]['issues']))

    def test_output_inside_consumer_rejected(self):
        with self.assertRaises(ValueError): c.collect(self.config, self.root / 'output')

    def test_malformed_receipt_still_collects_notes(self):
        self.handoff.write_text('[]')
        r = self.run_collect()
        self.assertFalse(r['policy_ok'])
        self.assertEqual(len(r['projects'][0]['files']), 1)

    def test_lock_mismatch_flagged(self):
        (self.root / 'project-kit/manifest.json').write_text('{}')
        self.assertFalse(self.run_collect()['policy_ok'])


if __name__ == '__main__': unittest.main()
