# Critter Application Server Contract

Status: five typed reads and guarded exact-view generation activated; cancellation unavailable

Owners: Application Server transport, Critter Image Batch domain truth, Object Sing & Dance Factory
presentation

## Deployment truth

Manifest v8 activates the five typed reads as `AVAILABLE` under
`CRITTER_READ_PROJECTION_ONLY`. The manifest is hot-read, but this activation also replaces the production
Java composition with `SrdMonsterApplicationReadService`; the managed process therefore required one
guarded restart to load the audited facade.

The completed activation order is:

1. Land this implementation with `NO_DATABASE_AUTHORITY` and delegated reads.
2. Load the landed binary through a separately authorized Application Server restart. The production
   Critter source remains lazy while the reads are delegated.
3. Land the SRD-owned `SrdMonsterApplicationReadService` facade and replace the controller-startup
   composition in the Application Server production source.
4. Change exactly the five reads to `AVAILABLE`, change the body mode to
   `CRITTER_READ_PROJECTION_ONLY`, and activate the read-model and shared page-package services.
5. Guardedly restart managed `3002` from exact landed Main so the manifest and Java facade advance
   together. Its first admitted read constructs the canonical SRD-owned read projection.

Managed-host receipt (2026-09-01): the guarded `4217` restart replaced PID `8920`, instance
`1d289919-b3af-4b52-84a5-ed6920f2cfcf`, with PID `99523`, instance
`62ba570b-0046-4d6e-a39f-6a170133a3a5`. The new process loaded manifest v7 at digest
`1e61de3756a3ea73b23018c5bdf14d3b7e8b2ca06b89e6070c646849892394bc`. Health is ready;
Gallery and Creature return their hosted HTML with all five shared reads resolved but still delegated.

Gallery and Creature presentations serve their exact landed owner bundles. Manifest v9 serves the separate
SRD-owned Batch status bundle with only `critter.operation-progress.read.v1`; its queue/provider-changing
controls remain disabled and its client contains no mutation transport. Both mutation capabilities remain
unavailable. Legacy `8766` remains an untouched rollback host with `cutover_authorized: false`.

Activation receipt (2026-09-01): exact Main `5524d23a863891ab8420daac35ede398b68045c0`
replaced PID `99523`, instance `62ba570b-0046-4d6e-a39f-6a170133a3a5`, with PID `12542`, instance
`8bc9a755-37b5-4920-8175-64203520301d`. The process loaded manifest v8 at digest
`41fcdba23dcb1240c84f7a92158c8724c138263e7ebecbce76ed99bd5e9dbdd9`. Gallery visibly rendered 280
creatures and 558 current roles; Aboleth opened with one managed image plus populated Shader and Puppeteer
workspaces and no visible read errors.

A preliminary catalog probe reached the previous binary before its Java age was checked. It returned the
same 558 current assets through the former production source, whose known prompt-default synchronization
may therefore have run. No provider, admission, cancellation, database mutation, or `8766` action ran.

Batch registration receipt (2026-09-01): unchanged PID `12542`, instance
`8bc9a755-37b5-4920-8175-64203520301d`, hot-observed manifest v9 at digest
`4d8f95b9d5309eb1d77775ca1fc462fa506d448ad8c7c3b71692acda8d441065`. The Batch page returned HTTP 200
with four page-local assets, a two-capability bootstrap containing presentation plus progress, bounded empty
progress truth, and six disabled provider/queue-changing actions. No restart or action invocation occurred.

Exact-view activation receipt (2026-09-01): exact Main `f35b2dbb6c588bbf601b3b1c519a1430bc266bf7`
first loaded the generation-authority seam through one guarded restart, replacing PID `12542` with PID
`24969`, instance `4c780b91-99a0-49a0-b260-dd581b8fa749`, while manifest v9 still blocked both mutations.
The landed fail-closed Object Factory client then cleared the separate hot-configuration gate. Unchanged PID
`24969` now serves manifest v10, body mode `CRITTER_READ_AND_EXACT_VIEW_GENERATION`, with generation
available and cancellation unavailable. No generation control, provider, admission, database mutation,
Batch action, or `8766` route was invoked during activation proof.

Object Factory Shelf response receipt (2026-09-01): exact Main
`96e39151d333b5466472b798f315a03debe1ec74` changed only owner CSS/JavaScript. The unchanged managed PID
`24969` hot-serves JavaScript digest `826927be5a3f308e209541cc8e94e90012850674d5e8d32593240669f2296a1b`
and CSS digest `e77a5a226370f48de901c9da3a55704a3b93401507edadfad1d08beb212aeb95` in Creature
bundle `11d7c4b6878525c8f3912cf627bf268dd59392d9d41bb40945efcf92d1542a1f`. No restart or action
invocation was required.

The activation adds no provider call, batch admission, cancellation, database mutation, duplicate catalog,
or browser-local Body Form, texture-map, or Puppeteer session API. Product reads use the canonical SRD
projection and its existing managed-media authorization.

## Canonical pages

| Order | Page ID | Route | Presentation capability |
| --- | --- | --- | --- |
| 1 | `critter.image-batch` | `/critter-image-batch/` | `critter.image-batch.page.v1` |
| 2 | `critter.gallery` | `/critter-gallery/` | `critter.gallery.page.v1` |
| 3 | `critter.creature` | `/monster-detail.html` | `critter.creature.page.v1` |

These are separate first-class Suite pages. Batch never embeds Gallery, projects at most 24 compact latest
rows, and does not claim queue order or retained history. Its control capabilities remain deferred.
Creature identity is the `entity_key` query member; the registered page route is path-only.

Each read has one canonical `capability_definitions` record. Gallery and Creature both use explicit
`capability_refs` to receive the same five reads in their page bootstraps, while global health deduplicates
the shared records. Duplicate definitions, duplicate page references, and unknown references fail closed.
The managed binary predating `capability_refs` could not activate this contract through hot configuration.
The v8 process now contains both canonical references and the audited read facade.

Each page first calls `GET /api/page-bootstrap?page_id={exact page_id}` and retains the HttpOnly
`TALISMAN_APP_SESSION` cookie through same-origin credentials. The cookie value never appears in JSON.

## Canonical reads

- `critter.current-asset-catalog.read.v1`: `GET /api/critter/current-assets`, result
  `critter.current-asset-catalog/v1`;
- `critter.monster-detail.read.v1`: `GET /api/monster-detail`, result
  `critter.monster-detail/v1`;
- `critter.managed-image.read.v1`: `GET /api/managed-image`, result `critter.managed-media/v1`;
- `critter.operation-progress.read.v1`: `GET /api/critter/operation-progress`, result
  `critter.operation-progress/v1`;
- `critter.direct-operation.read.v1`: `GET /api/critter/direct-operations`, result
  `critter.direct-operation/v1`.

Manifest routes are literal paths. Query members are transport inputs, not part of a manifest route:

- monster detail: `entity_key` required;
- managed image: `media_id` and `sha256` required;
- operation progress: optional `entity_key`; absent means current progress for all exact creature-role
  operations;
- direct operation: `operation_id` required.

All five reads are `GET`, `APP_SESSION`, `csrf: NOT_APPLICABLE`, `replay_policy: NONE`,
`epoch_binding: MANIFEST_VERSION_AND_SERVER_INSTANCE`, and `cancellation_policy: NONE`.

Every JSON success contains the SRD contract payload plus `capability_id`, `server_instance_id`,
`server_generation`, `manifest_version`, `manifest_digest`, `workspace_session_id`, and
`workspace_session_generation`. Field names are lower snake case. The host maps only the landed
`SrdMonsterApplicationContract` records; it does not reselect raw service maps or invent domain truth.

The typed DTOs exclude filesystem paths, prompts, prompt hashes, provider request identities, SQL,
credentials, and raw exceptions by construction. Media success returns the owner-authorized bytes with
`Cache-Control: no-store` and these headers:

```text
X-Talisman-Capability-Id
X-Talisman-Content-SHA256
X-Talisman-Server-Instance
X-Talisman-Manifest-Version
```

## Mutation contracts

The frozen mutation capabilities are:

- `critter.individual-generation.request.v1` at
  `POST /api/critter/individual-generation-requests`, owned by
  `SrdMonsterImageBatchService.admitIndividualGeneration`;
- `critter.direct-operation.cancel.v1` at
  `POST /api/critter/direct-operations/cancel`, owned by
  `SrdMonsterImageBatchService.cancelDirectOperation`.

Both use `APP_SESSION`, `csrf: REQUIRED`, `MANIFEST_VERSION_AND_SERVER_INSTANCE`, and
`talisman.problem/v1`. Bootstrap returns a process-memory `csrf_token`; POST sends it as
`X-Talisman-CSRF-Token` with the HttpOnly session cookie. POST also sends the bootstrap values in
`X-Talisman-Server-Instance` and `X-Talisman-Manifest-Version`. A missing/stale token returns
`csrf_token_rejected`; an epoch mismatch returns retryable `stale_server_epoch`.
The body must be `application/json` and contain exactly the frozen fields for its route. Cancellation and
legacy generation requests are at most 64 KiB. A guided generation request is at most 576 KiB so the
transport can carry its individually validated 48-KiB control-metadata and 192-KiB silhouette packages,
including JSON-string escaping; each package retains its own tighter parse limit. Duplicate object keys,
trailing JSON values, non-string members, and unknown or missing fields fail closed as `invalid_request`
before SRD admission.

The generation request carries `entity_key`, `role`, `additional_instructions`,
`client_operation_id`, and `expected_asset_revision`; guided requests additionally carry exact
`control_metadata_package` and `silhouette_guide_package`. Its replay identity is `client_operation_id`.
Cancellation carries `operation_id` and `expected_operation_revision`. Stale guards return
`stale_asset_revision` or `stale_operation_revision` with `retryable: true`. Admission returns HTTP 202;
cancellation returns HTTP 200. The handlers are implemented and fake-proven. The generation successor is
also wired to a lazy SRD-owned generation facade: only an already validated POST can atomically close the
read facade, construct that facade, admit the request, and retain it for every later read. The manifest
v9 advertised both mutations as `UNAVAILABLE`, so loading the binary alone could not reach the transition.
After the fail-closed browser transport landed, v10 made only generation `AVAILABLE`. Cancellation remains
unavailable. No page-load or GET path can reach either mutation.

## Delegated Body Form persistence contract

`object-factory.body-form.save.v1` and `object-factory.body-form.save-as.v1` are declared POST
capabilities at `/api/object-factory/body-forms/save` and `/api/object-factory/body-forms/save-as`.
They require the same app session, CSRF, manifest/server epoch, and explicit idempotency identity as other
host mutations, but remain `DELEGATED`: the managed host has no admitted browser complete-package transfer
or database lease-owning composition. Each route therefore fails closed as `capability_unavailable` today.

The injected `FactoryBodyFormApplicationServerGateway` is the typed activation seam. Save accepts one
immutable complete `BODY_FORM` Native Package plus the exact existing Object identity/revision; Save As
accepts the same complete package plus the selected parent identity/revision. It delegates directly to
`FactoryObjectOperationService`, whose `FactoryNativePackageService` transaction retains all Form, Rig,
Appearance, Mapping silhouette, stance, optional reference, and motion members. The gateway does not accept
browser recovery data, open a database, replace the Factory service's idempotency/lifecycle truth, or
interpret the legacy `ancient-dragon` migration alias; that alias continues to normalize to `adult-dragon`
before package construction.
