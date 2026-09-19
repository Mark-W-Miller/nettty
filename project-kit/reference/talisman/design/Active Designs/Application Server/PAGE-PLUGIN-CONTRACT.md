# Application Server Page Plugin Contract

Status: schema v1; Easy Tale and Shelf available; Critter three-page read contract implemented

Owner: Application Server

## Owner bundle rule

Production browser resources live beneath `src/main/resources/app/<owner-package>/`. For Easy Tale the exact
root is `src/main/resources/app/easy-tale/`. Design-source files may remain in the Easy Tale Active Design,
but the registered production sources must be explicit files under that resource root.

The Application Server owner reviews and installs one page fragment in the canonical
`src/main/resources/app/application-server/page-manifest.yaml`. The server does not scan for manifests,
merge owner YAML dynamically, infer routes, or start another process.

## Component catalog metadata

Every registered page is a user-visible component. The Application Server projects the following
source-controlled display metadata in `/api/manifest`; Tassy Home and its About panel refresh from this
read-only catalog:

```yaml
page_version: 1
component_version: Dev 1.1
new_features:
  - One concise visible change.
```

`page_version` remains the registered page revision. `component_version` is the component owner's own
release line, and `new_features` contains one to eight concise release notes. A legacy fixture that does
not yet declare the display fields projects `Page <page_version>` and no notes; a new production component
must declare them. This metadata changes no route, capability, feature authority, persistence, or browser
mutation behavior.

For the exact local rapid-publication procedure, receipt contents, and Java-upgrade boundary, see
[HOT-PUBLICATION-HANDOFF.md](HOT-PUBLICATION-HANDOFF.md).

The fixed lifecycle command is:

```text
scripts/talisman-application-server.sh start
```

The resulting Easy Tale URL is:

```text
http://192.168.68.105:3002/easy-tale/
```

Critter uses three separate owner pages rather than one workbench page. Their declaration order is Batch
`critter.image-batch` at `/critter-image-batch/`, Gallery `critter.gallery` at `/critter-gallery/`, and
Creature `critter.creature` at `/monster-detail.html`. Batch does not embed Gallery. Gallery and Creature
bind the same SRD-owned operation-progress read. Batch binds only that shared progress read. Exact payloads,
availability, media headers, mutation successor shapes, and the no-false-availability deployment sequence
are in
[CRITTER-READ-CONTRACT.md](CRITTER-READ-CONTRACT.md).

Mutation-capable owner clients must use bootstrap's `csrf_token` as `X-Talisman-CSRF-Token` and echo the
bootstrap server instance and manifest version in their named Talisman headers. These fields are inert while
the mutation capabilities remain unavailable.

## Exact page fragment

```yaml
- page_id: easy-tale.story-workspace
  owner: Easy Tale
  page_version: 1
  route: /easy-tale/
  state: AVAILABLE
  lifecycle: owner-page-session
  bootstrap_contract: application-server.page-bootstrap/v1
  development_reload: NEXT_REQUEST_ATOMIC
  entry_asset_id: easy-tale.index
  summary: Browser-first Easy Tale Shelf and Box workspace over typed Java/AppServices capabilities.
  assets:
    - asset_id: easy-tale.index
      route: /easy-tale/index.html
      media_type: text/html; charset=utf-8
      source: src/main/resources/app/easy-tale/index.html
    - asset_id: easy-tale.styles
      route: /easy-tale/easy-tale.css
      media_type: text/css; charset=utf-8
      source: src/main/resources/app/easy-tale/easy-tale.css
    - asset_id: easy-tale.application
      route: /easy-tale/easy-tale.js
      media_type: text/javascript; charset=utf-8
      source: src/main/resources/app/easy-tale/easy-tale.js
    - asset_id: easy-tale.state
      route: /easy-tale/easy-tale-state.js
      media_type: text/javascript; charset=utf-8
      source: src/main/resources/app/easy-tale/easy-tale-state.js
  capabilities:
    - capability_id: easy-tale.story-workspace.page.v1
      owner: Easy Tale
      kind: PAGE_PRESENTATION
      method: GET
      route: /easy-tale/
      availability: AVAILABLE
      authority: ApplicationServerManifestStore
      authentication: NONE_LOOPBACK
      csrf: NOT_APPLICABLE
      replay_policy: NONE
      epoch_binding: MANIFEST_VERSION
      error_contract: talisman.problem/v1
      cancellation_policy: NONE
```

Easy Tale adds each feature contract to this page's `capabilities` list. A read declaration has this exact
shape until its typed Java/AppServices adapter is installed:

```yaml
- capability_id: easy-tale.<capability-name>.read.v1
  owner: Easy Tale
  kind: READ_ONLY_QUERY
  method: GET
  route: /api/easy-tale/<capability-route>
  availability: DELEGATED
  authority: Easy Tale <existing Java/AppServices owner>
  authentication: APP_SESSION
  csrf: NOT_APPLICABLE
  replay_policy: NONE
  epoch_binding: MANIFEST_VERSION
  error_contract: talisman.problem/v1
  cancellation_policy: NONE
```

A mutation declaration is inert until authentication and its typed owner adapter are installed:

```yaml
- capability_id: easy-tale.<capability-name>.request.v1
  owner: Easy Tale
  kind: MUTATION_COMMAND
  method: POST
  route: /api/easy-tale/<capability-route>
  availability: UNAVAILABLE
  authority: Easy Tale <existing Java/AppServices owner>
  authentication: APP_SESSION
  csrf: REQUIRED
  replay_policy: IDEMPOTENCY_KEY_REQUIRED
  epoch_binding: MANIFEST_VERSION_AND_SERVER_INSTANCE
  error_contract: talisman.problem/v1
  cancellation_policy: EXPLICIT_OPERATION_ONLY
```

Registration never makes a delegated or unavailable capability callable. It returns a structured
`talisman.problem/v1` response until a separately reviewed typed adapter is installed. Browser JavaScript
receives no service instance, repository, database handle, path, credential, provider transport, or raw
exception.

## Bootstrap and refresh

The page reads:

```text
GET /api/page-bootstrap?page_id=easy-tale.story-workspace
```

The response contains exact page/version/route/lifecycle/reload/bundle identity, server instance and
protocol/generation, manifest identity, process locale, truthful `system` theme, opaque workspace-session
identity/generation/expiry, and declared capability descriptors. The HttpOnly SameSite cookie secret is not
in the response. `NEXT_REQUEST_ATOMIC` means changes to the manifest or any registered owner file become
one complete new snapshot on the next request without restarting `3002`.

The canonical Shelf registration is `talisman.shelf-workspace` at `/shelf/`. It remains
`MIGRATION_PENDING` with no entry asset until Shelf P1 deposits its separately reviewed owner bundle. Its
available harmless read is `application-server.status.read.v1` at
`GET /api/application-server/status`; the route requires the bootstrap app-session cookie and returns only
bounded server/manifest/session/count truth.

Easy Tale Bodies 1–8 remain Java/AppServices domain truth. The browser package may project their immutable
results and submit bounded typed intents only after the corresponding adapters are separately implemented.
Switchboard confirmed the corrected Easy Tale owner and Main were clean/equal at
`57e6ef5289d0bbecdcdc1a60bfe6bacfc139c309`. The central registration consumes those four exact files in
place; no byte is copied into an Application Server directory.

The Easy Tale bundle now also includes `easy-tale-client.js`, the pure authenticated session/revision client.
The manifest tests pin complete current SHA-256 values for all five owner assets so hot reload cannot silently
serve a mixed browser generation.

## GM local admission

The shared LAN host is HTTP. A GM page must never send `MOONDANCE_GM_CREDENTIAL` to it or place that
credential in a browser asset, URL, log, or response. Production resolves the credential only in the host
process to establish that a local GM operator is configured.

An App Session creates a five-minute pending admission request with CSRF, current server/manifest proof, and
a valid `X-Talisman-Idempotency-Key`. Retrying that exact App Session/workspace/generation/server/key tuple
returns the same pending request. Retrying redemption for an approved request returns the same bounded GM
session receipt while that session is live.
Only the `list-gm-admissions` and `approve-gm --request-id=<id>` local commands can view or approve a
pending request. They read the state-directory lifecycle token and call loopback with that non-browser
header; the controller also rejects any `Origin` header. A loopback source address or `Host` header alone
is never operator proof. The original bound App Session may then redeem it once for a separate 30-minute
HttpOnly `TALISMAN_GM_SESSION` cookie. That GM session binds the opaque workspace-session ID, App Session
generation, and server instance; all pending and GM sessions disappear on restart. GM reads require a
current GM session; GM mutations retain GM session, CSRF, current epoch, and explicit replay requirements.
Owner gateways receive only the immutable `GmAdmissionAuthority.GmCaller` stamp, never the credential or
cookie.
