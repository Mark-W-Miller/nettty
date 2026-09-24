# 0.1.2 current scope

## Release 0.1.13: read-only delivery and local authoring

The [next-candidate migration](READ-ONLY-DELIVERY-MIGRATION.md) and updated
[persistence](../contracts/persistence.md) and [context](../contracts/context.md) contracts supersede the
older assumption that ordinary Tassy editor Save writes canonical SQL. Local project servers are the
database-capable development workshops. Tassy rehearses the Moonbeam read-only baseline while its
editors save to browser-owned overlays. Moonbeam uses the same delivery boundary. This is documentation
curation only.

Proof order is Slice, then Rougish, then a Tassy-to-Moonbeam rehearsal. Each requires server-side
mutation rejection, unchanged canonical read-back, fresh browser-overlay reopen, explicit local-workshop
database read-back, offline closure, quota/export recovery, baseline conflict handling, origin separation
and two-tab stale-save checks. Motion and Citadel-building AI requests require distinct self-contained
typed context packets and rejection of stale/out-of-scope answers.

On 24 September the inspected Tassy Slice capability endpoint still declared writable legacy SQLite;
no write was attempted. Slice standalone was unavailable, Rougish was not freshly retested and Moonbeam
was uninspected. None of these acceptance gates is therefore complete.

Component publication has an additional gate. The exact plan must pass
`scripts/publish_component.py`, then the compatible owner adapter must return the same component,
version, owner package digest, inventory digest and descriptor digest with an immediate destination
check, remote selection receipt and public served-byte proof. Workboard's four-field call and Tassy's
reusable adapter have focused source tests, but the concrete operator-machine transport and live
Moonbeam receipt do not. Configuration or a successful lower-level upload cannot satisfy this gate.

The [portable return cycle](../contracts/morph-return-cycle.md) supersedes earlier new-definition-only
guidance and limited two-lab sequence. Three labs must adopt before further software; all three plus
Slice need hot deployment and database access. [Source inspection](NEW-UPDATE-PROFILE.md) confirms
single-item guarded UPDATE in the local service source, not live application/package integration.
Automatic plural ZIP handling, native compendia and end-to-end Slice display remain implementation work.
Earlier evidence below is historical and does not describe current application state.

# Current release status

**0.1.1 is a documentation release.** Mark's latest direction supersedes the earlier requirement to
wait for successful import before releasing guidance. Import, validated examples and the complete
all-Morph snapshot remain outstanding goals, not claimed contents. Earlier reports below preserve
what was known at each step; stopped-service statements are report-time observations, not fresh probes.
No database writes or native-validator runs were performed by MPK. Consumer adoption remains separately
confirmable. [What's new](../CHANGELOG.md) describes the released scope.

# Evidence history and current gaps

Corrected direction received 2026-09-16: **Insect Wars import lessons → refine MPK → release for Rougish**.
Slice's independent correction work continues; Slice remains a catalog-source contact, not a second
MPK proving project or release adoption gate. All-Morph reference collection remains required.

1. Beetle/Tassy supply the actual Insect Wars Morph-set import evidence: exact source/package/build,
   owning admission operation, validator results, receipt/readback identities/revisions/digests,
   preservation/replay/conflict/recovery observations, runtime result and bounded unresolved gaps.
2. MPK/Atlas refine shared instructions from observed results. Proposed guidance alone cannot satisfy
   this gate. Ask the owning importer for the exact blocking operation or unsupported record type.
3. Slice/Tassy supply all current accepted Morphs with complete dependencies and authoritative coverage.
4. MPK checks inventory, links and byte digests; publishes a new release only with truthful evidence.
5. Rougish adopts exact version/digest, preserves local guidance, and records results using its new
   Place/object/creature forms. Documentation adoption and game/database acceptance stay separate.

Earlier draft status (superseded for documentation release): documentation draft prepared; Insect Wars import evidence not received by MPK; actual
catalog export absent; final release and Rougish adoption pending. No unrun import results are claimed.
No live database mutation is authorized by this documentation work.

## First-practice evidence received

Beetle reports ten local revision-1 Morph candidates in the current uncommitted Riverwild tree,
IW-DEMO-02 revision 2: ant, beetle, ladybird, stag, spider, caterpillar, wasp, dragonfly, moth and midge.
Two originals and eight local descendants; accepted native catalog ancestry remains unverified.
MPK read the manifest and confirmed nativeAdmission/databaseImport/databaseReadBack are NOT_RUN;
destinationProject and owningImporter are null. Initial blocker was destination and admission binding. Subsequent owner reports below identify a
single-object operation; destination, candidate compatibility and coherent-set handling remain unresolved.
The visible game uses bundled candidates according to Beetle; no native receipt/readback/runtime proof
was supplied. This is an observed preparation gap, not an import attempt or import failure.

Rougish reports its 0.1.0 copy verified and remains preparation-only. No unreleased draft adopted;
Place/object/creature practice awaits the evidence-led release and Mark's game design.

The [standard-object contract](../contracts/objects.md) and complete worked import/readback example
remain pending actual Slice source/codec confirmation, sharing approval and first-practice results.

## Receiver capability report — 2026-09-16

Beetle and Atlas relay Tassy receiver inspection (owner-reported, not independently exercised by MPK):
`POST /api/object-factory/morphs/save`, contract `object-factory.core-morph-save/v1`, accepts one
validated complete independent `talisman.core-morph/v1` Morph. TEMPLATE/NEW requires a new ID, empty
guards, no entity/master, revision 1 and empty `texture_maps`. The operation is session/CSRF/epoch
bound, with canonical digest, guarded append-only save, atomic single-object content/version/head/receipt
and per-item idempotency. These constraints are source-specific; confirm exact current owner contracts
before constructing a request. This report is not authorization to invoke the endpoint.

No set manifest/preflight, whole-set transaction/receipt, MPK provenance binding, game-scoped destination
or partial-series rollback is established. Insect Wars' selected database/destination and native
candidate compatibility are still unresolved. No transformation, native validation, import, receipt,
readback or database-backed runtime result exists for the ten candidates.

Beetle requested a bounded set-admission contract rather than treating ten individual writes as proof.
Atlas asks the owners first to assess the minimal existing per-item path with explicit inventory,
readback, resume and partial progress, activating only after complete dependency closure. That approach
may be sufficient if proven; the Kit does not require a new importer or whole-set atomicity by default.
Morph/Factory and Insect Wars must decide and evidence the plan; Tassy supplies live host/security binding.
If per-item admission cannot preserve required provenance, reject conflicts, recover safely or establish
a coherent installed set, record the precise missing capability and agree its owner. Per-item receipts
must not be presented as an existing set receipt. No database writes are part of Kit assembly.

## Subsequent Tassy confirmation, relayed by Atlas

Tassy confirms the per-item route is viable for first proof using an explicitly selected shared Core
Morph catalog destination, all-member native preflight, deterministic per-item keys, receipts/inventory,
complete exact readback/digests and activation only after dependency closure. A bulk endpoint is not
a prerequisite. No inheritance/deltas or nonempty `texture_maps` are supported; the operation remains
not game-scoped and does not supply set provenance. The service is currently stopped and destination
choice is pending. These are owner-reported capabilities/conditions, not an executed candidate proof.
The earlier assessment request is resolved as viable in principle; native candidate compatibility,
actual destination selection, service availability and import/readback results remain open.

## Candidate incompatibilities reported by Beetle — static comparison

Beetle compared all ten Riverwild candidate JSONs with local Talisman
`CoreMorphCatalogService.validateMorphDocument`, `_x_funcStances` and `_x_funcMotions` source.
All ten lack the top-level `stances` array; motion `stance_references` consequently cannot resolve
there. Motions specify `SMOOTHSTEP` instead of the required `linear-preview` and supply endpoints
without the required two keyframes. All ten have empty `texture_maps`. No `geometry_attachments`
were shown; the local wing representation still requires native geometry review.

This is an owner-reported static contract comparison, not native-validator execution. The local staging
validator passed documents that omit mandatory native structures, so that pass does not establish
native compatibility. No saves were performed. Destination selection is pending and Tassy is stopped.

Proposed correction, pending Morph-owner agreement: materialize complete Mapping and motion endpoint
stances and two native keyframes carrying `ordinal`, `offset_ms`, `stance_id` and `positions`. Resolve
interpolation semantics explicitly; do not silently relabel SMOOTHSTEP as linear-preview. Then run the
actual native preflight on all ten before any save and record precise source/build and candidate digests.

Required MPK learning: the eventual worked object/creature example must include a real native-validated
fixture and codec-specific motion example. A lookalike schema or endpoint-only example is insufficient.
Pin validation evidence and semantic limitations; static comparison and native execution remain separate.

Tassy subsequently supplied a [sanitized admission reference](TASSY-ADMISSION-REFERENCE.md) with
request shape, header names and owning chain. It contains no live session values and does not change
the pending approvals or establish executed validation/admission/readback.

## Structural conversion example inspected for 0.1.1

The current Insect Wars native-conversion-review README describes an ant example with complete named
stances and two native-shaped keyframes, retaining authored positions and durations. It deliberately
retains SMOOTHSTEP and would still fail the inspected interpolation rule. Native validation and writes
are NOT RUN. The example remains local and is not included as a validated shared fixture.

Portable conversion guidance: preserve original bytes, record source/output digests and a per-field
transformation/loss ledger, resolve destination IDs/conflicts and motion semantics with the owning
authority, then execute the actual native validator. Unknown fields surviving parsing do not prove
geometry/material/renderer semantics. This work remains independent of the 0.1.1 documentation release.

## Release 0.1.11 DTDT/importer/theme gates

The release-11 contracts define review language and friction tests only. A consumer must not claim
implementation merely by copying this Kit. Evidence for implementation requires, as applicable:

- a running Control-Tick overlay that exposes DTDT specifications for meaningful Cards, Shelves, Boxes,
  Bars, Arenas, Six-View Bars, tree shelves, tool bars and floating windows;
- project packets that can be exported from patch-only/chat, local runtime or direct database modes
  without losing stable identity, provenance or canonical Morph/Place shape;
- a registered project adapter feeding the shared canonical importer without manual interpretation;
- importer compare/readback receipts showing incoming versus canonical records and explicit reviewer
  dispositions;
- Forest Green and Forest Blue applied through semantic Theme roles, with non-color cues and focus/contrast
  preserved.

No such runtime evidence is included in this documentation release.

## Release 0.1.12 terrain-navigation gates

The terrain-navigation contract is shared behavior, not an implementation receipt. A consumer must show:

- real pointer routing for left select/edit, middle pan, right orbit, double-right orbit focus and wheel zoom;
- preserved cursor world point, yaw, pitch, scale and active parent link across inward/outward handover;
- bounded progressive loading, honest approach/loading/failure state and stale-completion rejection;
- continuous child-owned geometry and parent-owned seams without changing physical samples for display repair;
- no authored dirty state or history from navigation/cache work, plus save/reopen for deliberate edits;
- native guarded save and canonical read-back before claiming database persistence.

Rougish source inspection and focused checks informed the contract. Cross-project and physical-device
acceptance remain outstanding until each consumer records them.

## Release 0.1.12 Slice authoring/admission gates

Slice source and focused tests informed shared rules, but adoption still requires the actual owner paths:

- exact Save/Import authority, operation recovery, before-images, partial-package disposition and read-back;
- stored FEATURE/RASTER inventory plus renderer and browser/GPU/iPad presentation evidence;
- stale/cancelled read rejection with preserved selection, row metrics, drafts, camera and receipts;
- displayed-source editing through the real replacement bridge and geometric clearance checks;
- consumer load and Gallery/reopen of exact model, texture/media and transform dependencies;
- admitted higher-detail child terrain before calling a crop or resample multiresolution refinement.

The complete local-save → import → deployed-edit → Gallery-reopen chain remains unproved.
