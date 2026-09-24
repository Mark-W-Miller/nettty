import hashlib
import importlib.util
import json
from pathlib import Path
import tempfile
import unittest


spec = importlib.util.spec_from_file_location('component_publisher', Path(__file__).resolve().parents[1] / 'scripts/publish_component.py')
p = importlib.util.module_from_spec(spec); spec.loader.exec_module(p)


class ComponentPublisherTests(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory(); self.base = Path(self.temp.name)
        self.package = self.base / 'package'; self.package.mkdir()
        data = b'{"id":"example"}\n'; (self.package / 'component.json').write_bytes(data)
        entry = {'path':'component.json', 'bytes':len(data), 'sha256':hashlib.sha256(data).hexdigest()}
        digest = hashlib.sha256(json.dumps([entry], sort_keys=True, separators=(',', ':')).encode()).hexdigest()
        self.plan = {'schema':p.SCHEMA, 'component_id':'example', 'selected_version':'1.2.3',
                     'local_tassy_process_instance':'instance-1234',
                     'package':{'root':str(self.package), 'package_digest':'owner-digest',
                                'inventory_sha256':digest, 'descriptor_path':'component.json',
                                'descriptor_sha256':entry['sha256'], 'files':[entry]},
                     }
        self.plan_path = self.base / 'plan.json'; self.plan_path.write_text(json.dumps(self.plan))

    def tearDown(self): self.temp.cleanup()

    def test_preview_verifies_exact_closure(self):
        result = p.publish(self.plan_path)
        self.assertEqual(result['status'], 'verified-preview')
        self.assertEqual(result['package_digest'], 'owner-digest')
        self.assertEqual(result['inventory_sha256'], self.plan['package']['inventory_sha256'])

    def test_undeclared_file_is_rejected(self):
        (self.package / 'extra').write_text('drift')
        with self.assertRaisesRegex(ValueError, 'exact package closure'):
            p.publish(self.plan_path)

    def test_receipt_requires_exact_remote_and_public_identity(self):
        receipt = {'schema':p.RECEIPT_SCHEMA, 'status':'selected', 'component_id':'example',
                   'selected_version':'1.2.3', 'package_digest':'owner-digest',
                   'server_instance_id':'moonbeam-1234', 'selected_at':'2026-09-24T00:00:00Z',
                   'server_restart':False, 'database_publication':False, 'unrelated_components_changed':False,
                   'verified_remote_component':{'component_id':'example', 'component_version':'1.2.3',
                                                'package_digest':'owner-digest', 'state':'READY'},
                   'public_served':{'component_id':'example', 'selected_version':'1.2.3',
                                    'package_digest':'owner-digest',
                                    'url':'https://example.invalid/component', 'status':200, 'bytes':4,
                                    'sha256':'a'*64}}
        self.assertEqual(p.validate_receipt(self.plan, receipt)['status'], 'selected')
        receipt['public_served']['selected_version'] = 'wrong'
        with self.assertRaisesRegex(ValueError, 'public verification differs'):
            p.validate_receipt(self.plan, receipt)

    def test_apply_invokes_reviewed_four_field_owner_interface(self):
        adapter = self.base / 'adapter.py'
        adapter.write_text('''#!/usr/bin/env python3
import json,sys
component,version,digest,instance=sys.argv[1:]
print(json.dumps({
 "schema":"tassy-moonbeam-component-publish-receipt/v1","status":"selected",
 "component_id":component,"selected_version":version,"package_digest":digest,
 "server_instance_id":"moonbeam-1234","selected_at":"2026-09-24T00:00:00Z",
 "server_restart":False,"database_publication":False,"unrelated_components_changed":False,
 "verified_remote_component":{"component_id":component,"component_version":version,
   "package_digest":digest,"state":"READY"},
 "public_served":{"component_id":component,"selected_version":version,"package_digest":digest,
   "url":"https://example.invalid/component","status":200,"bytes":4,"sha256":"a"*64}
}))
''')
        adapter.chmod(0o755)
        result = p.publish(self.plan_path, adapter, True)
        self.assertEqual(result['component_id'], 'example')
        self.assertEqual(result['package_digest'], 'owner-digest')


if __name__ == '__main__': unittest.main()
