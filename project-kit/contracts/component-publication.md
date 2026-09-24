# Component publication

Status: MPK 0.1.13 release contract with an executable shared validator and tested owner-adapter
boundary.

Component publication moves one already selected, immutable component from Tassy rehearsal to Moonbeam.
It is separate from database deltas, server-core upgrades, routes and other components. Selection on Tassy
does not prove Moonbeam publication.

## Exact input

The shared `moondance.component-publication/v1` plan pins:

- component ID, selected version and the observed local Tassy process instance;
- the owner's canonical package digest, exact inventory SHA-256 and every regular file's path, byte count
  and SHA-256;
- descriptor path and SHA-256.

The file list is the complete closure. Its canonical JSON inventory supplies `inventory_sha256`; it does
not replace the owner's Java-compatible package digest formula. Symlinks, undeclared files, missing files,
digest drift and unsafe paths are rejected before the owner adapter runs. Credentials, private hostnames
and machine-specific commands remain local.

## Shared validator and owner adapter

Run `scripts/publish_component.py --plan PLAN` for a read-only preview. An authorized publication adds
`--adapter LOCAL_EXECUTABLE --apply`. The Kit invokes the reviewed four-field interface:

```text
LOCAL_EXECUTABLE COMPONENT_ID SELECTED_VERSION PACKAGE_DIGEST LOCAL_TASSY_PROCESS_INSTANCE
```

The owner adapter rechecks the local process, selection receipt, descriptor and exact immutable bundle.
It captures the Moonbeam process and prior component tuple, stages only the descriptor closure, rechecks
local selection, and sends those guards plus an idempotency key to the externally configured transport.
The transport must re-read the destination immediately before mutation, activate only this component,
then return the exact remote selection and public served-byte evidence. A configured flag or successful
process exit is not a publication receipt.

## Receipt and acceptance

The `tassy-moonbeam-component-publish-receipt/v1` status is `selected` or `already_selected`. It repeats
the operation/idempotency identity, component, version, owner package digest, public prefix, destination
instance and exact previous and verified component tuples. `server_restart`, `database_publication` and
`unrelated_components_changed` are all false.

`public_served` repeats component, version, package digest and prefix, and records an HTTPS URL, HTTP 200,
positive byte count and SHA-256. The shared tool rejects identity or authority drift. The owner retains
the full receipt in its durable operation journal. Public browser/device acceptance remains a separate
product gate when the component has interactive behavior.

## Owner integration status

Workboard commit `cdd0971f65c5f2e9b9209433313b59c341f88f8b` captures and rechecks the four fields,
passes them without shell evaluation and leaves the operator disabled. Tassy implementation commit
`31b47b6b803db6a653c3b23952d7dc4878dbaf02`, reconciled at
`0db121a5923d942a8000c78cb74296ccdcbf0d97`, supplies the reusable adapter and eight focused
publisher/staging scenarios. It performs the local selection, bundle, destination receipt and returned
public-proof checks described here.

The concrete operator-machine transport remains external configuration. It must perform private
Moonbeam inventory, atomically recheck the expected destination process/prior tuple, invoke managed
handler activation, reread health, fetch the public response and emit the exact receipt. Workboard remains
disabled and no production deployment was attempted. These source implementations and focused tests prove
the boundary; they do not prove a configured transport or live Moonbeam delivery.

## Authorization boundary

A validated plan is preparation, not deployment authority. `--apply` is only used under the component
owner's existing authorization. The command does not publish content databases, update server core,
restart the service, change access declarations, or sweep unrelated components.
