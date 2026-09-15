# Persistence and Content Lifecycle

Slice-native document storage remains inside FactoryNativePackageService, with package-private SQL in
FactorySliceDocumentStore. Additive factory_slice_document/version/component/source_revision/receipt rows
reference shared content_object bytes without model-required columns or fake OBJ/rig hashes. One checked
transaction appends immutable source/manifest/media, verifies readback, advances exact destination CAS and
records durable replay. Exact combined document bytes remain authority; inner JSON is a labelled projection.
Same source-pair revisions with changed manifest/media reject. Existing Types/Creature associations are untouched.

## Application theme preference

`TalismanTheme` is the typed application-theme identity. `TalismanTheme.save` writes its enum name
through the existing `UserPrefs` key `ui.theme`; Settings changes that value only on explicit Apply.
A fresh preferences instance and application startup both restore the value through
`TalismanTheme.load`, then install it through `TalismanThemeInstaller`. Talisman Forest uses this same
route and adds no second registry, settings file, or startup path.

Missing values and retired or unknown identifiers fall back deterministically to Midnight Slate.
The enum name remains the durable identifier so existing values stay compatible. This preference is
user presentation state, not service, database, domain, or semantic-operation authority.

## World and Adventure aggregate

`AdventureRepository` owns the additive `adventure_world`, `adventure`, and `adventure_reference`
tables in the project SQLite database. Schema initialization idempotently seeds stable World ID
`seasons` with display name Seasons. An Adventure belongs to exactly one World and stores bounded
name, summary, notes, created/updated time, and a monotonic revision. Every metadata or reference
write includes the expected Adventure revision and increments it atomically; stale callers mutate
nothing. `loadSnapshot` reads one Adventure row and all of its references on one connection and
transaction, giving selection a coherent aggregate rather than independently timed metadata and
attachment projections.

`adventure_character_history_event` and `adventure_character_relationship` are Adventure-owned context.
Relationships are bounded per source Character reference and persist exact target reference kind, canonical
identity/source revision when present, display identity, target provenance, and Adventure-authored
provenance. A checked save validates both source and target references in one transaction and advances the
Adventure revision with the row; rollback leaves neither effect. Placeholder targets retain empty canonical
identity/revision and never imply geometry. These rows do not copy or mutate Character sheets, Assets media,
Place geometry, or runtime state.

`AdventureApplicationService` is the app-scoped UI/headless owner for ordinary reads and exactly four
owner-private v1 mutations. Create checks the exact World revision and preallocated Adventure ID in one
transaction. Update captures the committed snapshot in that same transaction and reports `NO_CHANGE`
without incrementing revision. Exact-reference admission rechecks the caller-reviewed source kind, ID,
and Place revision or game-object `updated_at` in the same transaction, including a no-change existing
reference. Placeholder admission is a separate geometry-free operation with empty source identity and
revision. Each mutation is all-or-nothing; no partial or uncertain result is allowed and no schema changed.

Resident observation receipts from `AdventurePresentationSelection` are bounded process-memory UI
state only. They persist no selected row, layout, Adventure field, reference, or provider value. READY
contains only exact Adventure identity/revision and reference-kind counts; Summary, Notes, Request,
Answers, provider responses, Character sheets, reference notes, and context payloads are excluded.

CM-06 adds no table, row, content object, operation receipt, provider transcript, or persisted Context
payload. `AdventureBattleContextProvider.Capture` is an immutable process-local scalar value; it may contain
only bounded identities, revisions, labels, turn facts, hit-point totals, allegiance, condition expiry, and
interaction roles. AdventureRepository, Place storage, runtime/Arena persistence, and combat state remain
authoritative, and constructing or composing Context cannot advance any revision or durable state.

CM-08 adds no codec, table, receipt store, provider transcript, durable proposal, or restart replay.
Structured proposals, opaque feature review stamps, confirmation tokens, and the maximum 64 pending rows
exist only in bounded process memory and are cleared on shutdown. The Context coordinator cannot advance
an Adventure, Battle, Object Factory, database, runtime, or persistence revision. A later registered
feature authority must atomically revalidate its own stamp inside its existing durable or semantic-
operation boundary; Context dispatch is not commit truth.

CM-09 adds no durable proposal, UI transcript, screen-owned proposal text, or restart behavior. Each agreed
screen surface retains only opaque proposal and snapshot UUIDs while open. Closing calls the exact
non-dispatching discard boundary and releases those identifiers. Cross-screen rejection, stale snapshot,
privacy rejection, and fake-authority execution cannot write feature or Context persistence.

An exact reference stores one Adventure-local reference ID, kind, display name, canonical entity ID,
source revision, and bounded provenance. Place and Created Thing references are admitted only after
the owning Place/SRD schema reports the candidate identity and revision. Adventure rows do not copy
Place geometry, Character sheets, or media. A placeholder Place stores only kind, name, and provenance;
its entity ID and source revision are empty and context projects `geometryAvailable: false`.

`AdventureContextPackage` derives a bounded JSON snapshot from one exact Adventure revision. It emits
schema version, Adventure/World identity and metadata, exact reference provenance, complete Place
ancestry, allowlisted noteworthy/terrain/geography metadata, and available canonical Character-sheet
facts. Placeholder Places remain explicit without geometry. Raster bytes, geometry payloads, unrelated
World rows, and arbitrary metadata keys are excluded. Inspectable entries carry exact source identity,
revision/provenance, default selection, and bounded read-only details. A SHA-256 stamps the exact JSON.

Planning resources and frozen fixtures are versioned classpath content, not compiled prompt strings or
database mutations. A context package is a reviewable request value; it is not a durable provider
transcript or mutation authority. Each Adventure workbench keeps selected context, inspector,
conversation, provider response, image bytes, and candidate only in its isolated in-memory session.
Provider responses are bounded human-readable YAML/Markdown or bounded image results. The exact original
and edited reviewed copy are separate identity-owned UI review values; neither becomes canonical data
merely because it was generated, edited, validated, accepted, or approved. Divider positions persist as
user presentation preference, not Adventure aggregate data.

The remembered Adventure workbench provider identity is also a user presentation preference. Each
workbench reads existing TaliTalk Ollama and OpenAI Image defaults into an isolated session; model and
endpoint edits are not written back by Adventure. OpenAI credentials are resolved in memory through the
canonical credential provider and never enter preferences, Adventure state, context, transcript, status,
or capability values. Closing the window releases its provider clients and discards its configuration.

Optional Icon/Token choices, exact created Character identity/revision, parent attempts, exact Assets
session/result references, and completed-role retry state belong to the bounded process-local reviewed
session in `AdventureApplicationService`. They are reconstructable only within that service epoch and are
not restart replay authority. Adventure persists no image provider settings, prompt, candidate, asset bytes,
or association substitute. Assets remains the canonical settings, generation, review, admission, role
association, replacement/history, and Created Things authority.

## Semantic-operation retention is not domain persistence

`BoundedSemanticOperationRegistry` retains only bounded process-local receipts, snapshots, terminal
summaries, history, idempotency evidence, and expired-identity tombstones. Active work is never evicted;
terminal count/age and retained-history limits are explicit. UNKNOWN and EXPIRED remain distinct while a
bounded tombstone exists. Closing/evicting registry metadata does not cancel work, roll back a transaction,
or delete domain content.

Feature repositories remain authoritative for committed documents, objects, assets, associations,
provenance, and revisions. A feature that needs restart-safe operation lookup owns and verifies its durable
receipt/result, then may rehydrate the same globally unambiguous operation ID through its registered owner
handle. The shared package defines no codec, table, repository, generic persistence, restart replay, or
offline catch-up. Registry history is authoritative only for lifecycle reporting within its declared
retention window. The exact owner may release an expired registry tombstone only after certifying that its
feature-owned durable guard retains replay protection; registry eviction alone never grants that authority.

`SemanticOperationIncidentHub` likewise retains owner-private joined evidence in bounded process memory
only. Its process epoch, salted digests, active summaries, terminal TTL, and counters have no codec, table,
restart recovery, or feature replay meaning. Existing bus-diagnostic database rows and memory-snapshot JSON
do not persist the semantic join. `MapPerformanceLog` may record the opaque join only through its deliberate
typed factual adapter; that diagnostic trace never becomes operation or domain persistence.

Prepared encounter admission adds no schema and never reads Adventure persistence directly. The GM
service consumes an immutable aggregate and coherent Adventure snapshot through
`AdventureApplicationService`. Prepared revision/digest, Adventure revision, catalog identity, and
committed rolled counts remain source truth; admission does not regenerate or advance Pi state.

The only durable runtime mutation is one optimistic `ArenaObjectStore.save` invoked by
`MapRuntimeSessionService.admitPreparedEncounterGraph` after the complete definition/presence/Group graph
and formation fit validate. Success is followed by one in-memory install, history entry, and publication.
Conflict and pre-save guards are `NOT_COMMITTED`; checked local save failure is `ROLLED_BACK`. No chained
create/place/group commands, maintenance SQL, partial graph, Viewer clamping, Player staging, or new
durable operation table participates.

Current-combatant Move adds no schema or durable operation journal. Its registry identity and terminal
truth are bounded and process-local; restart never replays a retained idempotency key. The only durable
mutation is one optimistic `ArenaObjectStore.save` invoked by
`MapRuntimeSessionService.commitCombatMovement` after exact combat/runtime guards and detached route-cost
calculation succeed. Success installs one position/retained-route candidate, one Arena history entry, and
one Arena/store revision advance before combat movement-spent truth is published coherently. No-change
does not save or spend movement. Stale/precommit cancellation/deadline is `NOT_COMMITTED`; checked save
failure is `ROLLED_BACK`; there is no partial or external outcome.

Move registry evidence contains only bounded owner-private operation/scope IDs, revisions, digests,
phase/cancellation truth, result stamps, and safe movement scalars. Combatant names, stats, effects, full
routes, map/terrain payloads, paths, SQL/raw errors, UI/Viewer objects, and Player-private state remain
outside it. Move never calls Present, advances `liveGeneration`, starts transport, or changes schema.

Adventure CRUD/reference operation evidence is process-local and bounded. Restart UNKNOWN/EXPIRED never
authorizes replay or retry. Registry state contains no Adventure/placeholder/reference prose, database path,
SQL or raw exception, Character/media/provider/planning content, credential, or UI object. Authorized typed
owner results may return the coherent Adventure snapshot outside registry metadata. Normalized-project mode
uses the exact project ID in its opaque scope; legacy database mode uses one fixed owner-defined legacy
project identity plus the exact database digest rather than accepting a caller-authored scope.

Reviewed-step registry evidence is likewise owner-private, process-local, and bounded. It contains only
exact session/action/Adventure identities and revisions, digests, approval/idempotency guards, bounded role
names, lifecycle truth, and exact result references. Plan prose, quick-import YAML, provider/media payload,
Adventure or placeholder notes, database paths/SQL/raw exceptions, credentials, and UI objects stay outside
the registry. The typed service result may retain the immutable reviewed session and Assets components within
the service epoch. A component uses null child operation plus the exact Assets session reference until Assets
publishes a semantic identity; unknown or expired evidence never authorizes a blind replay.

Reviewed encounter preparation adds dedicated `adventure_prepared_encounter` and
`adventure_prepared_encounter_member` tables owned only by `AdventureRepository`. Exact approval commits
one header plus the complete ordered member set and advances the checked Adventure revision exactly once
in one transaction. Durable truth retains the selected candidate and approval identities, semantic terrain
and level band, catalog version/digest, source catalog-qualified creature IDs, display/quantity review
facts, rolled counts, situation/disposition, request/selected/quantity/final Pi states, prepared digest,
prepared revision, and post-commit Adventure revision. It is not encoded as a reference or prose.

Candidate review remains bounded owner-local service state. Registry evidence retains only private IDs,
revisions, digests, codes, counts, lifecycle, and exact prepared/Adventure result references. Encounter
prose, creature/source names, quantity expressions, Pi traces, SQL/path/raw failure, provider/media/UI, and
GM/Player runtime state are excluded. Decline, precommit cancellation, stale input, or repository failure
leaves no prepared aggregate; a checked repository failure rolls back both member/header rows and the
Adventure revision. Process restart, UNKNOWN, or EXPIRED never authorizes reroll or recommit.

## Durable authority

The verified project SQLite file is authoritative for canonical maps, version membership, normalized
Places/Layers/Sources, content objects, selection masks, parent-capture recipes, workspace state,
Arena Objects/Groups, and project assets. External paths are provenance or import inputs, not a
reload requirement for admitted map content.

| Schema seam | Java owner | Stored meaning |
| --- | --- | --- |
| `map_document`/`map_version` | `SqliteMapDocumentRepository` | active immutable version |
| Place revision/version rows | same repository | Place graph and stable local IDs |
| `place_footprint` | same repository | child geometry/links/order |
| `map_source`/Source revisions | same repository | Source identity/provenance/content SHA |
| `map_layer`/Layer revisions | same repository | semantics/order/display/content SHA |
| `content_object` | `ProjectContentObjectStore` | exact immutable SHA-256 bytes |
| raster content reference | `ProjectRasterContentStore` | typed encoded raster object |
| selection content reference | `ProjectSelectionMaskStore` | compact mask bits/cutter identity |
| `place_parent_capture` | map repository | capture choices/provenance/status |
| `map_workspace_state` | map repository | active Place/Layer/tool/editor state |
| Arena object tables | `SqliteArenaObjectStore` | definitions/presences/routes/revisions |
| Arena Group tables | `SqliteArenaObjectStore` | Groups/members/presences/formations |
| User Asset rows | `UserAssetsRepository` | DB-owned content links, sources/nodes/tags/associations |
| Adventure World/aggregate/reference rows | `AdventureRepository` | checked authoring metadata/references |
| Adventure Character History/Relationship rows | `AdventureRepository` | checked bounded Character context |
| Adventure prepared encounter rows | `AdventureRepository` | reviewed header/member/Pi provenance |

Current Place working rasters share an aspect-preserving 1,536-pixel longest-side ceiling. The
guarded offline `CurrentPlaceRasterMigrationService` previews the exact active version, requires a
complete independently verified backup, resamples every affected current Place payload with its
semantic interpolation rule, and publishes one checked successor through
`SqliteMapDocumentRepository.storeIfActiveVersion`. The checked store rejects a concurrent active
version change inside the publication transaction. Immutable prior versions, imported Source
originals, User Assets, generated media, and unrelated content objects remain unchanged; reclaiming
their physical database pages is a separate retention decision.

`SqliteArenaObjectStore` has its own feature-schema version. It upgrades runtime feature tables
without changing the project-storage schema version.

Complete Arena reset treats both schema-11 normalized rows and already-migrated legacy
`arena_object_definition` / `arena_object_presence` rows as explicit targets. The store reports a
deterministic legacy-row fingerprint to the session preview and revalidates it with the optimistic
document generation inside one transaction. An accepted reset deletes normalized definitions,
presences/routes, Groups/members/presences/routes, and matching legacy rows, then advances generation
once. Place/map/project/source/layer/footprint, User Asset, Media Store, project-asset, and immutable
content-object tables are outside that transaction.

The in-app maintenance workflow adds no schema and never issues reset SQL. Before execution it uses
`ProjectStorageBackupService` to create one dated online backup, then independently verifies the
finished file's SHA-256, size, schema, SQLite integrity, foreign keys, and content-object hashes.
`ArenaResetProtectedDataService` separately counts and fingerprints Places, maps,
Layers/Sources/footprints, runtime Place data, User Assets, managed media/content, and all remaining
project content. Those same fingerprints must still match after the checked reset; operational bus,
backup-manifest, and Arena generation rows are deliberately outside that preserved-row proof.

The Talisman Online Server publisher reuses this online-backup authority for the canonical local project
database. Its UI-independent command adapter admits the completed backup, checks the expected remote current
identity and capacity, and transfers only the immutable candidate to the root-only remote promoter. The
promoter independently admits the candidate and atomically selects one content-digest-named release while
retaining the predecessor. Data flows local-to-Moondance only; no remote-to-local synchronization or network
SQLite listener exists. `TalismanOnlineDatabaseAuthority` opens the selected release read-only and exposes
only bounded immutable database identity to loopback health.

`TalismanOnlineSeasonsService` receives the internally admitted real database path, opens it in SQLite
read-only/query-only mode, and projects only the canonical Easy Tale work's bounded chapter presentation.
It neither copies the database nor creates a second catalog. Its public stable scene ID is a truncated SHA-256
of the private chapter identity; raw node IDs, Source records, paths, SQL, and authoring state remain private.
Application startup fails closed when the canonical work or a nonempty bounded scene set is unavailable.

## Database-owned User Assets and recovery

Accepted User Asset bytes are immutable `content_object` rows linked by `project_asset`; the
canonical project SQLite database is their sole durable authority. `UserAssetsRepository` admits
the payload, optional thumbnail, compatibility metadata, and normalized asset link in one
transaction. `UserAssetsService` projects verified DB bytes into a disposable derived cache for
legacy file-oriented UI/provider consumers. `user_asset.local_path`, root/store markers, original
names, and external paths are compatibility/provenance only and never required for ordinary reads.

Ordinary resolution never searches filenames, provenance paths, roots, or external Asset Sources.
Missing normalized content remains honestly unavailable. `UserAssetsContentRecoveryService`
searches only explicitly authorized recovery roots, accepts exact SHA-256 matches, and creates a
complete read-only preview token. Apply rejects stale previews, then admits each available exact
asset in its own transaction; one absent asset does not block unrelated exact recovery. Legacy
managed-root rebind remains compatibility tooling, not the normal ownership/read boundary.

Generative Reference Media discovery reads every exact `entity_asset_attachment` association and
joins it to canonical image or readable-document `user_asset` rows without mutation. The hierarchy
keeps association IDs and revisions for stale selection checks; Media-only choices keep the exact
asset revision. Provider dispatch rereads `content_object` bytes by exact asset ID and verifies
SHA-256 only for image inputs. Managed document text enters the reviewed prompt. An external text
reference is request-only and retains path, size, modification time, and SHA-256 for stale checks; it
is never admitted, persisted, or treated as ownership authority. Ordering and purpose remain request
state only.

Named external `asset_source` roots are independent import/scan inputs. Home-contained roots use a
portable locator and retain their existing explicit per-source Locate/Change Location workflow.
Their absence is reported as `external source unavailable` and cannot make an admitted asset
unavailable. A supported verified project-database backup contains all accepted User Asset bytes.

Repository reads resolve a persisted `${user.home}` external Source locator before availability or
scan checks. A legacy absolute `/Users/<prior-user>/...` locator may automatically rebase only to
the identical suffix beneath current `user.home`, only when that directory exists, at least one
remembered relative file is SHA-256 exact, and no present remembered file conflicts. No other path
is searched; Locate Source remains the explicit fallback and uses the same persisted portable
locator. An unavailable root retains its last-indexed children with `external source unavailable`;
only a completed readable scan may label an absent child "Removed."

`entity_asset_context` stores only durable inclusion of one still-associated managed document in an
exact Character's curated Context. It stores no copied text, prompt draft, provenance path, or
generated fact. Reads join it to `entity_asset_attachment`; toggles reject stale document revisions
or missing associations, and final detachment removes the orphaned context row. Included document
bytes continue to resolve solely from canonical DB-owned content. Supported database backup and
selective Created Things reset include this table with the rest of Character-owned association
state.

## Workbench dialog geometry

The New editor and Checklist Review dialog persist complete bounds in separate Java Preferences
children named `manual-entry` and `checklist-review`. Move, resize, and close update only the owning
child. Restart Workbench and Update & relaunch Workbench snapshot live bounds and flush both nodes
before replacement launch; ordinary in-screen geometry restores exactly, while unavailable-screen
geometry is clamped to the invoking display's usable bounds. Dialog preferences contain no manual
entry or checklist prose.

## Workbench memory presentation state

`MemoryLargeBytesMonitorWindow` retains its Talisman open state and usable-screen-clamped geometry.
`MemoryLargeBytesMonitorPanel` can store every monitor table's order as stable model-column indexes
plus bounded widths when its Workbench host supplies presentation preferences. A schema addition
absent from an older preference remains visible after the restored columns; malformed or unknown
saved columns are ignored. Presentation preferences contain no telemetry snapshot, raster bytes,
document state, cache eligibility, or eviction policy.

## Performance laboratory and working-raster policy

`WorkingRasterDimensionPolicy` is the canonical working-raster maximum. Normal product callers always
receive the released 1536-pixel value. Reading an absent, malformed, or higher laboratory-only
preference writes nothing, and changing the normal preference does not scan, decode, rebuild, or
publish raster data. The reviewed 2048, 3072, and 4096 identities are available only through an exact
verified Performance laboratory manifest.

`PerformanceLaboratoryService` owns the private local foundation under the runtime Workbench
performance-analysis laboratory directory. This body admits only caller-supplied synthetic
SQLite sources. Preview creates nothing; establishment rechecks the source digest, creates durable lab
and baseline copies, runs integrity verification, and atomically writes manifests containing exact
lineage, schema/build, state, database digest, size, and raster-preset identity. The canonical live
project path, same-file identities, symlink aliases, path escapes, tampered bytes, and stale operation
tokens fail closed.

Reset to Baseline is explicit and replaces only the verified lab from the verified checkpoint through
a staged copy with rollback. Destructive experiments use one verified temporary copy and cleanup may
remove only that owned scope. `PerformanceWorkingRasterRebuildService` accepts exact selected Place
IDs and the current manifest preset. Its read-only preview binds the laboratory digest/state, document
version, Place/layer/source revisions, target geometry, canonical-source availability, truthful
working-raster fallback, and conservative heap/disk requirements into one token.

Rebuild is worker-only. It revalidates the complete preview and available capacity, then mutates only
one verified experiment copy. Image layers use immutable owned Source bytes when they are present,
readable, and identity-valid; other layers resample the current working derivative and report that an
upscale cannot recover detail. Exact Place extent and aligned raster/mask registration are retained.
Cancellation, stale state, capacity failure, decode/store failure, or integrity failure cleans the
experiment without changing the durable lab. A verified successor reaches the lab only through
rollback-protected database-plus-manifest replacement; the baseline, source content, and earlier map
versions remain intact. Normal Talisman remains fixed at 1536 and has no route to this operation.

This sequence does not create a representative private laboratory, open the live project, start an
analysis runner, or run a profiler. A future approved source-snapshot route must add its own visible
authority checkpoint without weakening these guards.

## Performance Analysis report publication

`PerformanceAnalysisReportService` owns the stable local `PERFORMANCE-RUNNING.md` report within the
runtime Workbench performance-analysis directory. It accepts immutable typed evidence supplied by a
caller; it does not collect evidence, start a workload, inspect a database, or control a process.
Schema, terminal state, correctness, laboratory/settings identity, evidence coverage,
comparison, stable findings, review state, limitations, and exact next action remain explicit even when
coverage is unavailable.

The service validates before publication, sanitizes secret assignments, URLs, and user-home paths,
bounds collections and Markdown size, and atomically replaces only the primary report. Run directories
remain intact. Available artifacts must be relative to the exact run directory, regular non-symlink
files with matching declared size and a SHA-256 identity; traversal and unsafe links make the report
invalid. Detailed artifacts are linked, never embedded. Invalid evidence produces a small explicit
INVALID report and cannot be rendered as healthy.

`PerformanceAnalysisRunManifest` is the immutable execution contract for one future manually started
run. It records exact catalog, workload, laboratory lineage/digests/state, build/Main/environment,
heap/raster/mesh/cache policy, warm-up/repetition/settle policy, requested measurements, artifact
ceiling, and separately authorized intrusiveness. Only synthetic laboratory identity is admissible.
`PerformanceAnalysisRunStore` creates one private run directory and atomically persists bounded
`RUN-MANIFEST.json` and generation-checked `RUN-STATE.json`; duplicate identities, traversal, symlink
roots/files, malformed JSON, and non-successor state replacement fail closed. This control-plane body
does not create/open/reset the laboratory or produce measurement artifacts.

`PerformanceAnalysisManualRunService` verifies the already-established synthetic laboratory and
authors one exact 4 GiB manifest from its current lab/baseline digests and state revision. It never
establishes, resets, or selects a laboratory and never accepts non-synthetic privacy. The executor
re-verifies that exact identity immediately before workload launch. A mismatch becomes a persisted
failed run before any selected workload process starts.

`PerformanceAnalysisMeasurementStore` atomically replaces one bounded MEASUREMENTS.json artifact inside
exact run directory. Every sample repeats run/workload/iteration identity, correctness, external wall
time, best-effort owned-process CPU, exact heap ceiling, exit status, and one stable coverage record for
each requested semantic metric. Unavailable adapters store `UNAVAILABLE/-1`, never a fabricated zero.
`PerformanceAnalysisReportSynthesizer` accepts only terminal state and turns those records into the
bounded primary report, preserving partial coverage and the measurement artifact digest.

Sampled-JFR artifacts live only below the exact private run directory, use one run/workload/repetition
identity, and are capped at 64 MiB. Missing, symlinked, oversized, comma-unsafe, or size-changing files
fail closed. `PerformanceJfrProfileContract` retains only bounded event-type counts and scalar CPU-sample,
allocation, monitor, file-I/O, GC, duration, and recording-size evidence; it never retains event stacks
or payload objects and never commits the binary recording to Git.
Each repetition stores profile coverage and bounded scalars with its measurement record. Terminal report
synthesis revalidates every declared JFR file below the exact run root, including byte size and digest,
before linking it. Allocation/GC and process-wide file-I/O summaries remain explicitly sampled evidence;
they are not silently relabelled as retained heap, semantic database work, native memory, or GPU use.

The subsystem-counter snapshot has one fixed SUBSYSTEM-COUNTERS.json name below the exact private run
root. Activation rejects absent roots, path escape, symlink ancestors, and pre-existing output. The
allowlist stores only enum identity, occurrence count, and byte count; arbitrary labels, names, Places,
paths, objects, and payloads cannot enter the file. Rows follow enum order, remain structurally bounded,
and atomic replacement supports an explicit flush plus the owned terminal shutdown flush.
Every workload repetition owns a distinct snapshot directory. The parent rereads no more than 32 KiB,
checks schema/timestamp/unique allowlisted rows, computes SHA-256, and persists that exact digest, size,
relative path, and bounded rows with the repetition. Terminal synthesis repeats those checks before
linking the artifact and aggregates only correct measured repetitions; absence is never converted to zero.

`PerformanceAnalysisBaselineService` owns only the bounded private
`baselines/BASELINE-SELECTIONS.json` ledger. An exact terminal run and workload first produce a
compatibility/snapshot digest; preview does not write. Acceptance rechecks ledger generation, run files,
snapshot digest, compatibility digest, and prior selected run before appending one immutable selection.
Old selections remain in revision order. Missing ledgers mean no selected baseline; malformed, oversized,
changed, escaped, or symlinked roots/files fail closed and never become an empty or healthy baseline.

Previous-run comparison reads checked terminal run directories newest-first but accepts only an older
exact compatibility digest. Report synthesis never mutates the baseline ledger. It may compare current
measurements read-only, while baseline selection remains a separate explicit supervised service action.
Measurement units participate in the compatibility identity. Only reviewed exact workload/metric/unit
rules can produce non-wall regression claims, and only from complete measured stable repetitions.
Workbench preview reads the newest checked run containing the selected workload; acceptance appends only
after a second explicit action and exact stale-token revalidation.

Comparison records also retain bounded typed workload, metric, unit, reference kind/run,
classification, confidence, and evidence fields separately from their Markdown display text.
`PerformanceAnalysisTriageService` consumes only those checked fields. It blocks attribution on a
correctness failure, deduplicates exact workload/metric regressions, and emits stable evidence-only
candidate IDs, owner, reproduction workload, confidence, impact, and one bounded next review. Numeric
evidence and reference-run changes do not alter finding identity. It nominates at most one actionable
candidate, keeps additional supported regressions informational, and states no action when no gate
fires. It stores no review state, changes no baseline, runs no workload, and modifies no product source.

`PerformanceAnalysisFindingReviewService` owns `finding-reviews/CURRENT-FINDINGS.json` as the replaceable
generated candidate snapshot and `FINDING-REVIEWS.json` as bounded append-only human decision history.
Candidate publication never creates a decision. Preview binds exact candidate/run/evidence digest,
candidate and ledger generations, prior state, target decision, and reason; acceptance rechecks all of
them before appending. Only accepted for work, not actionable, and superseded are admitted. Report
synthesis projects the newest decision onto current stable finding identity while retaining newly
generated evidence. Fixed and verified has no Body 8B write route.

`PerformanceAnalysisFixVerificationService` owns one immutable `FIX-VERIFICATION.json` below the exact
after-run directory. The checked harness supplies bounded cost-shift, resource-release, and quality gate
coverage/evidence; missing, partial, failed, changed, oversized, or unsafe evidence never means success.
The service resolves stable finding identity back to the closed workload/metric catalog, requires the
latest append-only state to be accepted for work, and compares only an exact-compatible chronological
before/after pair. Fixed and verified appends only after a second stale-safe review and stores the exact
verification digest. Generated and reviewed finding snapshots remain available to report synthesis.

Scripted-profile source remains repository-owned walkthrough YAML, never copied into a database by the
Performance adapter. `PerformanceScriptedProfileContract` admits only fixed one-segment candidate paths
under `testData/walkthrough`, rejects symlinks and changed content digests, and records the exact source
revision with fixture/mode identity. Successful checkpoint evidence must be a regular non-symlink file
below the supplied run-owned checkpoint directory, at most 8 MiB each and 32 MiB total, and receives an
exact size and SHA-256 identity. Candidate inclusion alone cannot launch or mutate anything.

The one reviewed synthetic smoke workload writes `SCRIPTED-PROFILE.json` atomically below its unique
run/workload/repetition directory and caps it at 256 KiB. The parent stable-reads the file, binds the same
digest-pinned source, and checks ordered step identity, correctness, timing totals, checkpoint paths,
sizes, and digests. Measurements retain the exact result digest and scalar identity. Terminal report
synthesis repeats every check before linking the JSON and checkpoint artifacts; missing, pre-existing,
changed, oversized, escaped, or symlinked evidence fails closed. No database stores scripted evidence.

`PerformanceRasterExperimentCatalog` is the only accepted source of the four-by-four role matrix. Its
image and composite dimensions remain independent from continuous Heightmap and categorical Terrain/
Geo working dimensions. `PerformanceAnalysisManualRunService` rejects a row whose image setting does
not equal the verified synthetic laboratory manifest; it never changes that setting itself. Supplied
`PerformanceRasterExperimentEvidence` must match the row's maxima and role-specific resampler before
the synthesizer may project quality or viability into the primary report. Cache policy remains an
independent manifest/evidence identity.

## Workbench checklist state

The canonical Markdown queue owns Approved in the checkbox marker, More Clarity in
`  - More clarity: requested`, and blocked review in `  - Wait: blocked`. These three states are
independent. `MapPerformanceRecentChangesParser` excludes their child lines from structured
instruction text, and `MapPerformanceRecentChangesLoader` rereads, reparses, and atomically rewrites
only recognized child state after the exact structured range. Wait does not remove a check, create a
manual complaint, alter Why/Image state, or change branch/workflow order. There is no Preferences or
database mirror for checklist state.

## Authoring save, history, and runtime publication

`MapDocumentSession` owns one canonical snapshot, one dirty working snapshot, working revision,
document version, and two-step Undo/Redo history. Before a full-snapshot candidate enters working
state, exact type, dimension, revision, and byte comparison reuses each unchanged raster from the
prior snapshot. Bounded Undo/Redo therefore retains only genuinely changed raster versions plus
shared unchanged identities; a coincident revision never aliases different bytes.
Place normalization also owns the singular semantic Layer baseline. It deterministically repairs
missing/duplicate core roles and their relative Surface, Terrain, Geology, Heightmap, Background
anchor order while retaining auxiliary identities, payloads, relative order, and occupied slots.
Reset to Neutral is an ordinary revisioned mutation that keeps the exact core Layer identity and
position; save/reload therefore cannot reinterpret it as removal or auxiliary replacement.
Ordinary structural edits capture a raster-sharing snapshot and monotonic source revision on the EDT.
Heightmap adjustment instead captures
one immutable `HeightRasterSnapshot` and exact Place/Layer/elevation revisions; it never copies or compares
the other Places. `MapDocumentService` coalesces the newest structural snapshot per scope, newest Height
raster per exact Place/Layer, and newest Layer-display command per exact Place/Layer on its single document
worker. It orders structural and Height commands by the shared source revision, replaces only the changed
Region for a Height delta, and rejects an older source or Layer revision. A burst therefore retains the
active persistence input plus at most its latest successor rather than a queue of obsolete raster copies.
Visibility changes likewise produce one latest-state transaction rather than a repository-flush queue.
The SQLite repository keeps weak, exact-object admission memos for immutable raster and selection-mask
content. A display-safe snapshot shares those unchanged payloads, so its durable version reuses their
existing content references without re-encoding every Place; a changed raster revision always admits a
new immutable content object. The memo never owns historical payload lifetime.

Each SQLite raster reference is also a payload-free `RasterPageHandle` over the exact content SHA,
semantic kind, encoded length, and dimensions. Startup and durable-version installation create typed
Height, Terrain, Geology, or Image facades without decoding the object. A read pages that one exact
object through `ProjectRasterContentStore` and the process `LargeBytesResidencyManager`; a first write
copy-on-writes to local bytes and drops the clean handle. The repository returns a separate clean,
handle-backed snapshot only after its transaction commits and never mutates the submitted snapshot.
Failed persistence therefore cannot replace dirty local pixels with a durable handle. Undo and Redo
install and persist the same exact page identities, so paging cannot change their results. Before a
successful user-history commit, `MapDocumentRepository.prepareHistorySnapshot` gives SQLite a
non-versioning content-admission boundary: it admits each exact raster object and returns a structural
snapshot containing only durable page handles. Only after that preparation succeeds may the new
canonical version publish. Startup compatibility rasters and genuinely changed prior pixels therefore
remain exact Undo records without leaving their decoded arrays resident while idle. Repositories
without independently addressed raster content keep their existing session-owned history snapshot.

Passive document telemetry inspects only object identity, dimensions, revision, encoded length, and
already-retained mask-index arrays. It counts each unique payload once across canonical, working,
transaction-base, Undo, and Redo roles, while recording reference sharing and independent mutable
copies. It never builds a lazy mask component index, decodes an image, walks raster samples, or keeps a
snapshot/payload reference. Dirty working payloads remain marked dirty. Current authoritative payloads
and history from a non-paging repository explicitly report their protection reason. SQLite-backed
history rows retain durable handles and report zero managed bytes; if a page is decoded, the
residency-manager row owns that decoded payload once regardless of how many canonical, working, or
history facades reference it. Authoring child-creation
rollback backups are a separate scope owner because they are independent snapshots outside the
document session; completion, rollback, and scope close release only their telemetry rows through the
existing backup lifecycle.

The scope already owns the live local mutation it submits. Its matching document-worker completion only
advances the applied revision and composition publication; it does not reinstall a second independent
raster copy. Derived 2D display images use two facades over one process-scoped access-ordered cache.
Their combined clean residency trims from 128 MiB to 96 MiB. Cache eviction changes no document
state.

Clean decoded authoritative pages have an independent 192 MiB high watermark and trim to 128 MiB by
oldest access. Authoring and GM Control maintain separate, deduplicated leases for their current child
Place, direct parent, and topmost root/overview Place. Siblings and every other inactive Place are
eligible as soon as the latest successful document version supplies their clean handles. Dirty local
bytes stay outside the manager, while lease release immediately permits pressure trimming.

A repository store creates/reuses normalized revision rows and advances `map_document` only in the
same successful transaction. Failure leaves the prior canonical version active, keeps dirty work,
publishes `UPDATE_FAILED`, and prevents `closeIfPersisted` from discarding the session. A flush that
is overtaken by a newer working revision may publish its durable version and immediately flush the
newer candidate; it must not roll the live workspace backward.

Undo flushes dirty work first, installs the prior snapshot as a new durable version, and moves one
history entry to Redo. Redo is unavailable while dirty. A new mutation after Undo clears Redo.
Selection-only history is separate but participates in `MapEditorScopeState` editor action order.
Runtime Arena Object/Group history is a third, independent history owned by
`MapRuntimeSessionService`.

Manifest Script operation metadata is not another document store. `AuthoringScriptOperationService`
retains bounded process-local receipt, snapshot, cancellation, and terminal truth through the shared
registry. `MapEditorScopeState` still captures the exact accepted dependencies and owns the detached
candidate. `MapDocumentService.commitIsolated` and `MapDocumentSession` remain the only checked mutation,
Undo/Redo, persistence, and canonical version route. A successful terminal result cites the committed
document/working revision and exact post-apply changed Layer/Source revisions; stale, failed, or effectively
cancelled work cites no result and commits nothing. Reload proof comes from the normal document repository,
not from operation-registry reconstruction.

Arena definition rows persist source Created Thing UID/name, resident kind, and
`ArenaObjectMovementProvenance` beside
canonical physical movement. A definition imported from an Asset Manager Created Thing additionally
persists that exact Created Thing identity, a bounded source-stat snapshot, and a bounded owned ARGB
Token snapshot. It also persists one bounded, versioned private-free presentation blob containing the
distinct 2D Icon snapshot, admitted canonical mesh, optional accepted diffuse pixels, retained
compatibility Silhouette pixels, truthful fallback state, and source association revision. Version 2
adds the Icon, version 3 replaces legacy STL triangles with the canonical indexed mesh/UV form, and
version 4 adds bounded diffuse pixels. Versions 1-3 remain readable with an empty texture and rewrite
in the current form on the next definition save. Retained Silhouette bytes do not participate in
current 2D or 3D representation choice. Normalized `arena_game_object` feature schema 11 and the
compatibility Arena
store add these fields without changing project schema 4. Schema-10 and older rows gain an empty
presentation payload; their existing footprint, movement, and provenance meanings are unchanged.
Older movement rows
migrate to the unspecified provenance state, which deliberately makes them ineligible for automatic
zero repair; only newly stamped generated rows can be repaired, while Object Editor saves persist the
user-override state. Imported source identity and appearance survive Object Editor saves. Repository
import writes no Place presence; activation/placement is separate. Only an exact checked
multi-selection Update refreshes source-owned values, and it persists the accepted definition set in
one Arena snapshot.

Footprint mode is stored by stable enum name in the existing Arena definition column. Playable adds
no schema or per-Place copy: configured tactical cells and canonical physical feet remain the durable
inputs, and each Place projection derives the nonlinear effective footprint from its current
axis-specific cell scale. Existing Physical, Exact configured minimum, and Legacy cells rows retain
their prior meanings.

Runtime sees a successfully stored canonical version. Display-safe changes may refresh the pinned
runtime snapshot immediately. Structural changes leave the pinned snapshot unchanged and set
reconciliation pending until the GM explicitly reconciles. Exact tree/header/canvas navigation IDs
absent from that pinned snapshot are rejected rather than being persisted as a broken GM active
Place.

## Source lifecycle

### Admission

1. A file, User Asset, Media Store selector, capture, or Script produces bytes plus provenance.
2. `SourceImportTransaction` stages bytes into a document-owned `.part` file.
3. Validation enforces size, supported type/format, dimensions, frame limits, and exact SHA-256.
4. `prepare` derives a content-addressed `OwnedSourceArtifactRef`.
5. `promote` crash-safely installs/reuses the immutable blob and admits the exact bytes to the
   canonical `SourceContentAdmission` boundary.
6. A lease protects the promoted blob while an isolated document commit is attempted.
7. Successful commit marks the artifact retained until the next live-reference generation.
8. Failure/close rolls back staging and any newly orphaned promoted blob.

The normalized SQLite repository refuses to store a current Source without admitted owned content.
Garbage collection is generation-aware and opportunistic; it retains current references, active
leases, and committed-but-not-yet-published blobs.

### Source identity and provenance

`RegionSource` is a non-rendering immutable record owned by one Place. It contains stable local ID,
label, content type, immutable owned artifact, generation metadata, origin, and
`RegionSourceProvenance`. Provenance may name a historical file path, User Asset/node, or Media Store
selector, but reload resolves the owned bytes. `MapDocumentService.sourceContent` reads admitted
owned content by exact SHA-256 from the durable repository before consulting the legacy per-document
filesystem artifact. A missing migrated filesystem cache therefore cannot hide normalized Source
content from previews, image decode, or Script materialization.

`RegionLayer` is a separate mutable semantic owner. It can reference a Source while independently
owning visibility, order, opacity, storage shape, typed raster/feature payload, mask metadata,
generation metadata, and content revision. Removing or clearing a Layer never silently deletes its
Source. A Source referenced by a Layer is not removable as an orphan operation.

### Current edit/export/write-back behavior

Editing a materialized Heightmap, Terrain, Geo Tables, image, or selection payload changes the Layer
payload and creates a new document version. It does not implicitly mutate the immutable Source
artifact or an external provenance file.

Selected materialized Heightmap metadata has one explicit Place-Source export boundary:

- `Export…` encodes the current authoritative unsigned-16 samples, `HeightSampleDomain`, and Place
  elevation range into one lossless PNG artifact inside the document-owned content store. It never
  asks for or writes an ordinary filesystem destination.
- An unused proposed name creates a new Source identity and associates the Heightmap. A name
  conflict requires an explicit exact-Source replacement or a separately named copy; cancellation
  changes nothing. Replacement preserves the stable target Source ID through history while changing
  its managed artifact and truthful generated origin. It never overwrites an external provenance
  file and is rejected for ambiguous names, non-Heightmap content, or use by another Layer.

`MapEditorHeightmapExportCoordinator` owns the scope's immutable export plan and calls
`HeightmapRasterExportService` to prepare only the managed artifact off the EDT. It applies the
result on the EDT only if Place, working revision, Layer revision, raster revision, current Source
association, chosen target, name conflict, and target usage still match, then returns one
status/save/publication mutation through `MapEditorScopeState`. Closing an uncommitted preparation
retracts an unreferenced artifact. Undo/Redo snapshots keep both old and new content-addressed
artifacts live until history no longer references them. Export does not resample or mutate the live
Heightmap, its range/grid, Parent Capture recipe, or any captured raster.

`Write Heightmap to Parent` and the parent-marker `Pull Heightmap from Child…` route share one
document operation. The request captures the exact parent, child, footprint, Heightmap Layer
identities/revisions, document revision, and optional child Selection revision. It projects the
authoritative child semantic raster directly through the persisted footprint into exact parent
semantic-raster pixels, using continuous interpolation for enlargement and pixel-area aggregation
for reduction. No Place-grid, display, Selection-mask, or nearest-resized intermediate is admitted.
It preserves normalized elevation and smooth edge blending, then persists one isolated Undo/Redo
history commit before publishing replacement state. Parent pixels outside the footprint remain
byte-identical. Cancellation,
stale inputs, locked/missing Heightmaps, preparation failure, or commit failure leave both Places
unchanged. It is not Source-file write-back.
Finer child samples are reduced into the coarser parent footprint without point-sampled blocks or
invented extrema. The inverse
Selection-owned pull samples the exact parent footprint into only selected child pixels. With no
matching Heightmap editor it uses the same isolated document history and revision boundary. With the
exact child Heightmap editor open it reuses that sampler against the isolated working raster, leaves
canonical content unchanged until checked Accept, and records one ordered working-history position
for top Undo/Redo.
For an independent child with the same finite parent extent, the established synthesized full-parent
capture mapping is equally authoritative. The pull records one exact Heightmap before/after
transaction; Undo/Redo does not consume or replace transient Selection state. A working pull becomes
dirty editor state, so Layer retarget and Place navigation must resolve through Save, Discard, or
Stay/Cancel before changing identity.

## Typed and materialized rasters

`RegionLayerRaster` is sealed to four semantic payloads:

- `ImageLayerRaster`: owned compressed image content;
- `HeightLayerRaster`: mutable unsigned-16 samples plus `HeightSampleDomain` and revision;
- `TerrainLayerRaster`: unsigned-byte terrain-class samples;
- `GeologyLayerRaster`: unsigned-byte fixed Geo Tables classes, with 0 as water/no-data.

Raster content is encoded and admitted separately from Layer metadata. A metadata-only edit reuses
the content object. A changed raster produces a replacement content object; imported originals remain
separate. Rendering caches key on payload/Layer revision and must not survive a mismatched revision,
size, Place, or elevation span.

The shared 3D terrain product is derived presentation state only. It consumes the exact final visible
2D composition plus immutable Heightmap capture, keys bounded CPU reuse by both revision and exact
content identity, and never becomes a Layer, Source, raster payload, document history entry, or
persisted flattened image. Slope-aware cliff treatment changes only 3D UV sampling; canonical Layer
pixels, ordering, opacity, registration, and Heightmap samples remain unchanged.

The in-place Geology provider stages imported, captured, generated, painted, and class-majority
smoothed categorical pixels in one isolated working `GeologyLayerRaster`. Generation seed,
instruction, detail, operation, and acquisition provenance travel with that working Layer. No
working result enters document history or persistence until the exact checked Accept transaction
replaces the canonical Layer; Discard and rejected stale results leave durable state unchanged.

The in-place Terrain provider applies the same staging contract to `TerrainLayerRaster`, including
square/round Paint and class-majority Smooth operations clipped to the active Selection. Exact
Water-mask identity, internal seed, instruction, feature scale, Operation, enabled land categories,
and generation provenance remain on the working Layer. Import and parent-capture provenance use the
same working route. Only checked Accept replaces the canonical Terrain Layer; stale or cancelled
work leaves document history and persistence unchanged.

The in-place Surface and Image Overlay providers stage immutable working `ImageLayerRaster`
replacements for direct Paint/Blur/Sharpen/edge operations. Each working mutation remains in the
pinned session's bounded history and cannot change accepted pixels before checked Accept. Image
Overlay percentage opacity uses the same exact isolated `RegionLayer`; Source identity, Layer
identity, kind, and legal stack position remain unchanged until common Accept replaces that Layer
once. Add Layer remains the canonical Source-admission boundary; dirty editor removal cannot delete
the Overlay or its Source.

The active Place persists user-owned Layer order. Legend, Grid, and Regions remain structural
controls; content Layers may be reordered without kind-specific stack bands. Every newly created
Place owns exactly one Background, Heightmap, Geo Tables, Terrain, and Surface Layer, with default
bottom-to-top order Background, Heightmap, Geo Tables, Terrain, Surface. The SQLite load/edit
boundary repairs a missing semantic role with neutral content while retaining every persisted Layer,
identity, payload, and relative order. Background is colour and raster geometry only; actual map
artwork belongs to an unlocked Image Overlay immediately above it. The checked current-Place
maintenance pass moves legacy Background image payload/source identity to that overlay without
copying its raster or changing imported originals. Ordinary source and map-import routes create the
overlay directly.
Heightmap's neutral payload is a zero FEET_OFFSET raster, so a negative Place base elevation remains
representable without signed stored samples. Semantic roles are expressed by Layer kind and stable
Layer identity, not inferred from filenames; lower-stack targets remain singular.

`LegendLayerProperties` persists only Legend content choices, display units, normalized geometry,
adaptive elevation-interval intent, and the bounded Peak marker foreground colour/line width.
Legacy content receives the canonical gold/2.3-pixel marker default. One Legend update is one
document history/save mutation; both 2D and 3D consume that foreground style while 2D retains its
contrast halo. `MapLegendRenderer` derives the distance ruler from the rendered Place's X/Y grid
dimensions and physical cell scale; every grid boundary tick is transient presentation state and
never enters document persistence.

## Heightmap, Terrain, and Geo Tables persistence

Geography owns three singular raster roles. Geo Tables and Terrain remain categorical semantic
authorities. Surface is a locked materialized `ImageLayerRaster` presentation derived from exact
Terrain and Geo Tables Layers and ordered immediately above Terrain. Surface generation metadata
binds both input Layer IDs and records their raster revisions, physical distance-per-pixel scale,
seed, algorithm, and scale policy. A stale candidate never replaces the last complete Surface.

Refine, Replace, and Fill Gaps are versioned persisted generation parameters. Refine preserves
protected/manual meaning and preferentially retains sampled parent-footprint categories; Replace
reclassifies under canonical water/elevation/slope/geology/scale constraints; Fill Gaps preserves
every assigned target cell. Generated candidates are transient UI state until explicit Accept.

Heightmap samples and elevation domain/range commit together. Height operations that expand range
must preserve effective elevations and restore raster, range, preview, and reload state through
Undo/Redo. A branch after Redo/Undo is a new coherent transaction.

Terrain acquisition imports the strict categorical payload into the singular Terrain Layer. Geo
Tables supports strict categorical import or deterministic pi-backed generation. One shared dialog
opens without a persistence mutation; target creation and lower-stack isolation occur only inside the
successful import/generation transaction. Generation records
raw/normalized seed, generator/corpus/selection versions, Geo data version, detail policy, target and
actual dimensions, feature size, and native/scaled mode in `LayerGenerationMetadata`. Generation is
off-EDT and commits no partial raster on cancellation, failure, or revision conflict.

Terrain generation metadata also records whether an exact Water mask governed the request and, when
used, its source Place, Layer, and revision. The checked request owns a pixel snapshot for deterministic
generation, but commit rejects a changed or replaced live mask rather than mixing revisions. The mask
is optional: unchecked or unavailable generation retains manual Water-category semantics.
New Terrain provenance writes the canonical five-scale detail name. The request boundary continues
to accept legacy STANDARD and FINE, normalizing them to BALANCED and DETAILED, so replay does
not depend on presenting obsolete choices in the acquisition UI.

Water remains code 0/no-data for Geo Tables; the eight land classes are stable. The currently landed
detail policy and provenance in source/tests supersede removed historical `v1` prose, which remains
available through Git history.

## Selection and mask persistence

The live rectangle/freeform `RasterSelection` is transient workspace state. It is revision-stamped,
clipped to the active raster, and cleared or rejected when Place/grid/raster identity changes.
Add Contour to Selection converts an exact current closed semantic Contour through its bound visible
Heightmap's pixel-center transform and unions the result only into this transient Selection history.
Its captured Place, Selection, Contour, Heightmap, and source-binding revisions are rechecked before
install. Conversion writes no document, Layer, raster, Source, saved Mask, or Contour content.

A saved selection or calculated mask is a Layer-owned `SelectionMaskIndex`. A saved Selection also
owns `SavedSelectionTarget`: exact Place, target Layer ID/kind/label/revision, logical Grid or raster
addressing, dimensions, and Grid identity. The project store writes this association in row metadata
and only compact occupancy plus grid-cutter identity as content; derived component/run caches are
rebuilt. A mask change can reuse the raster content object. A raster or Source identity change clears
incompatible selection content rather than applying it to different pixels.

Saved selection restore replaces the live selection but does not require a Heightmap. Clearing saved
selections preserves Mask Layers themselves. Water-mask selection and height operations require
matching dimensions and current Layer revision.

## Parent Capture lifecycle

Place physical/grid geometry is persisted exactly as authored. Existing unequal-cell Places remain
readable and are never normalized during load; the Region inspector requires an explicit previewed
repair and checked Apply. New Place creation and Parent Capture require square parent/child geometry.
Footprint bounds and the parent's cell scale determine child world extent. Captured Background pixels
are fitted, cropped, or padded by the raster owner and cannot silently redefine world extent or grid
counts.

Parent Capture materializes independent child content; it is not a live view of the parent.
Semantic Background is the raster exception: capture copies only color and opacity into the child's
stable Background identity. Image bytes/source metadata, pointer eligibility, and Selection-mask
state are neither captured nor retained as Background semantics.

Reviewed Move Place is one structural document transaction. It replaces only hierarchy-derived
parent/level/order state for the moved subtree, retains every stable Place/content/link identity,
and clears the former-parent capture profile/reference only on the moved root. Materialized captured
Sources and rasters are never cleared. Unchanged descendants keep their direct-parent capture
recipes. The ordinary document snapshot boundary provides one-step Undo/Redo and durable reload.

1. The child stores selected parent Layer IDs, sensitivity, prior capture revision/status, and child
   target IDs in `ParentCaptureProfile`.
2. Capture reads one immutable parent composition and slices/resamples each valid selected Layer.
3. Any new Source bytes are admitted before the child snapshot may reference them.
4. Typed raster, selection-mask content, semantic metadata, and provenance are installed together in
   one isolated commit.
5. Reload resolves only child-owned Source/raster content; the parent relationship remains a recipe
   for later recapture.

Data-loss rules:

- unchecked choices retain their exact child Layer/Source/raster/revision identity;
- a missing/stale parent ID does not remove the last successful child content;
- a failed selected input does not roll back other valid selected inputs;
- if every selected input is invalid, no structure or content changes;
- save failure retains dirty work and admitted bytes required by that work;
- independent child Places may capture without a footprint;
- an all-unchecked profile update may change recipe choices without advancing composition.

## Universal VTT Phase 1 transient preview

The Asset Manager `VTT Import` tab has no durable content authority in Phase 1.
`UniversalVttReader` treats the selected `.dd2vtt` file as read-only external input and produces one
`CanonicalMapDocument` held only by `UniversalVttImportPanel`. The bounded decoded preview image,
barrier paths, portals, lights, environment values, unknown-field previews, SHA-256, and diagnostics
are temporary inspection state. They are not admitted to the Media Store, Source store, User Assets
library, project content store, Place graph, map document, or SQLite database.

`MapRasterDecoder` is the one transient map-raster codec boundary. Explicit WebP and TIFF plugins plus
the JDK PNG/JPEG/GIF/BMP readers provide the supported format set; byte-signature diagnostics describe
unknown or unreadable input precisely. Preview decoding subsamples to its explicit pixel ceiling and
does not replace, transcode, or retain the embedded source bytes.

Open and superseding-open replace only the panel's in-memory document. Clear or close cancels the
reader, invalidates pending publication, and releases the canonical document and decoded preview.
Passive memory telemetry observes that existing lifecycle with one panel owner, one parse/decode job,
a decoded-preview byte estimate, and canonical path/point/portal/light counts. Telemetry retains no
document, image, path geometry, or durable-content authority.
Phase 2 must define the reviewed durable mapping and provenance contract before any commit action is
added; Phase 1 code must not infer that mapping or silently persist selected files.

## Persistent constructed-spatial storage

`TerrainRegion` owns at most one `CONSTRUCTED_FEATURES` Feature Layer. Its immutable
`SpatialFeature` values carry an explicit barrier, portal, or light kind, typed properties, exact
Source occurrence binding, and world points with optional elevation. `SqliteMapDocumentRepository`
stores those values in the existing normalized Layer, Feature, Feature-point, and Source rows; Layer
and Universal VTT import metadata remain versioned JSON properties. Legacy polyline Features reload
with zero elevation when no stored elevation or spatial kind exists.

This storage contract does not admit a `.dd2vtt` file, create a Place, or change Phase 1 preview
ownership. A later checked mapper and commit boundary must construct these durable values from a
reviewed canonical document.

`CanonicalToPersistentMapMapper` is that pure mapping boundary. It converts reviewed feet-per-grid
assumptions into Place-local miles, builds typed constructed Features and provisional Source
identities, safely collapses consecutive duplicate geometry points, skips only individual geometry
that remains unusable or outside the Place, and returns canonical plus mapping diagnostics. Its
provisional Sources deliberately lack owned artifacts, so the project repository rejects accidental
persistence until the separate transactional admission boundary supplies exact project-owned content.

`PersistentMapImportService` supplies that checked boundary. It captures the current document
revision, streams and verifies the original `.dd2vtt` DATA artifact, streams the exact embedded image
into the ordinary immutable IMAGE admission path, replaces provisional Sources with owned artifact
references, and commits one new Place snapshot through `MapDocumentService.commitIsolated`. Source
leases become committed only after the document commit. Recoverable source-item defects produce a
partial candidate and do not discard its substantive Place, raster, or valid Features. Stale
revisions, admission errors, and document-store failures close the leases without publishing a
partial Place or Source reference. The embedded artwork is a 100%-opacity `IMAGE_OVERLAY` bound to
that admitted image Source;
it never replaces or acquires the new Place's neutral semantic Background. Ordinary image-file map
imports use the same Source-bound overlay classification. Existing Places whose imported artwork was
previously stored as Background are not converted without a separate checked preview/safety boundary.
DATA admission retains a bounded 512 MiB ceiling while image safety remains at 64 MiB.
The admitted embedded image retains its exact PNG/JPEG/GIF/BMP/WebP/TIFF bytes and detected MIME; the
transient raster used to populate the overlay comes from the same shared map decoder.

## User Assets lifecycle

`UserAssetsService` owns roots, stable Asset Source identities, scans, reviewed ingest, managed files,
tags, entity attachments, and Media Store image materialization. `UserAssetsWorkspaceState` owns one
workspace's searches, selections, scan generations, and preview state. External Asset Source files
remain user-owned.

Scanned Content Root folders use exact normalized paths contained by a configured Asset Source for
read-only tree selection, restoration, and preview. Managed-root confinement remains a separate gate for
rename, move, delete, folder creation, and managed admission. An external source selection therefore
cannot become mutation authority merely because it is visible in Media.

Scoped Asset panels release their workspace registries on the EDT. The Adventure application-service
adapter defers acquiring its canonical Character-media Assets session until first use after the current
AppServices epoch has been installed. Closing an unused adapter creates no scope, and a restarted
AppServices lifetime cannot capture the prior epoch's closed telemetry or bus owners.

The 2D / 3D web-search save route is deliberately outside this persistence authority.
`ExternalAssetDiscovery` retains provider, creator, license, listing, price/access, terms, format, and
fetch evidence in the transient reviewed candidate. Poly Haven candidates additionally retain the
reviewed `files_hash`, primary file size/MD5/path, dependency count, and package-manifest SHA-256.
Smithsonian candidates retain record/media/resource identity, source institution/unit, exact media
access, advertised formats, and the canonical record link; credentials are never candidate evidence.
Wikimedia candidates retain exact page/title identity, creator/attribution requirement, license,
canonical listing, MIME/media type, byte size, and SHA-1 evidence. The contact-bearing User-Agent is
request policy, not persisted provenance.
Openverse candidates retain only catalog source/provider, creator, claimed-license, attribution,
approved thumbnail/detail, rate-budget, and exact upstream-listing evidence. Upstream content URLs
are non-actionable evidence, not candidate payloads. Optional client credentials and bearer tokens
remain outside preferences, candidate metadata, benchmark evidence, provenance, and persistence.
Sketchfab candidates retain only public model, creator/profile, canonical listing, license,
price/access, downloadable flag, advertised archive, approved thumbnail, provider credit, rate, and
fetch evidence. They contain no OAuth state, payload URL, or temporary archive link; the legacy
Sketchfab API-token identity is not treated as end-user OAuth.
Thingiverse candidates retain only Thing identity, creator, canonical listing, preview, declared
license, file count, advertised format, account handoff, and fetch evidence. MyMiniFactory candidates
retain only object/designer identity, canonical listing, preview, license/use signals, store/account
state, advertised format, and fetch evidence. Their registered credentials remain outside preferences,
candidate metadata, benchmark evidence, and persistence; both candidate families contain no payload.
Provider preferences persist only enabled built-in IDs. Labels, exact endpoints, adapter types, and
supported media are restored from code and cannot be replaced by saved custom values.
Link-only candidates retain no source payload. `FreeSearchResultSaveService` writes an eligible
original payload or verified dependency ZIP only to user-reviewed filesystem
destinations after the behavior rechecks its eligibility marker. It creates no content object,
project asset, User Asset, Source occurrence, association, Media projection, or derived OBJ; a later
Files/Content Root import is the separate durable provenance/admission action.

The distinct 2D / 3D Attach action is inside canonical persistence authority. It retains provider,
creator, license, canonical listing, original filename, and hash evidence while atomically admitting
or reusing the exact downloadable image/STL bytes and associating them to the captured Type or Created
Thing role through `CreatedThingAttachmentService`. Download never implies Attach; Attach never writes
the reviewed filesystem destination. Exact-byte reuse preserves independent target associations.

Provider-approved card/selected preview images are transient presentation cache entries only. Their
bounded positive/negative cache creates no content object, project asset, User Asset, Source,
association, acquisition provenance, or entitlement. Candidate IDs, provider IDs, asset IDs, and
hashes remain available to the checked save/provenance routes but are deliberately absent from
ordinary card labels.

`ManagedModelDerivationService` participates in those existing transactions for STL content. New STL
admission and the first checked 3D-role attachment of an older managed STL ensure one deterministic
canonical OBJ `project_asset`; `content_derivation` retains the exact source/child hashes, operation,
and converter version. The Created Thing association continues to identify the immutable source STL,
so its occurrence provenance and current STL presentation remain intact. Exact association/revision
resolution returns the already-owned OBJ to the separate Viewer owner; repeated and cross-target
attachments reuse it without conversion.

`ManagedObjAdmissionService` is the Files Make OBJ transaction. It rechecks the exact source length
and SHA-256 plus the prepared OBJ, material, and optional accepted-texture identities; stores the
original STL only as immutable content with a root-relative `external_provenance` locator; and
persists or reuses the UV-enabled canonical OBJ as the visible managed Media asset. The same
transaction records derivations for its MTL, UV preview, UV guide, and optional diffuse PNG; retains
fallback color plus bounded provider/Character/request provenance in asset metadata; retains the
source occurrence; and may replace one exact reviewed `model_3d` role through
`CreatedThingAttachmentService`. Stale selection/target state or any write failure rolls back the
complete content, asset, material, UV, texture, provenance, derivation, and association change. The
diffuse PNG remains a derived `content_object`;
`UserAssetsService.readOwnedDerivedArtifactContent` verifies its exact parent, child hash, operation,
and payload without creating a `project_asset` or catalog entry.

The checked UnityFS entry point on `ManagedObjAdmissionService` extends that same transaction authority;
it does not add a schema or alternate model store. The exact original bundle content is admitted first and
is the parent of canonical OBJ, MTL, five decoded PNG maps, optional separately bounded Viewer diffuse,
and digest manifest through `content_derivation`. Reviewed source/provider/creator/listing/license/terms
evidence is retained in `external_provenance` without secrets or absolute paths. The canonical OBJ alone
becomes the visible managed User Asset. One transaction also retains the source occurrence and optional
exact target association. Every payload/hash, source identity, provenance row, derivation, managed asset,
and association is checked before success; stale input, cancellation before commit, or failure rolls back
the whole graph. Same-byte retry reuses immutable content and managed identity without collapsing an
independent association.

Factory Wall, Floor, Ceiling, Rectangular Table, and Round Table live draft and preview are
deliberately outside persistence. The floor/reference grid is a separate Viewer scene node and never
enters a recipe, mesh, artifact, hash, content object, provenance row, or recreation parameter. One
immutable versioned ZOF recipe produces a validated indexed mesh, common
`FactoryModelCandidate`,
transient canonical OBJ/material candidate, geometry-only STL derivative, and shared Viewer frame.
ZOF means zero degrees of freedom: exact kind, dimensions, orientation or square physical grid,
openings, segmentation, furniture parts, material definition/version, and physical tile scale
mechanically reproduce the candidate. A Surface is centered in X/Z and occupies `Y=0..thickness`;
Floor and Ceiling remain distinct recipe/provenance identities even when geometry bytes match. When
enabled, the square grid requires exact normalized multiples for outer dimensions and every opening
offset/span. A `talisman.factory-furniture` version-1 Rectangular Table recipe defines one centered,
grounded box top and four stable square straight legs. Its 30 quads/60 triangles form five
independently closed outward-wound shells. Version 2 Round Table defines one faceted-cylinder top plus
four deterministic outward-splayed square-prism legs, with bounded radial-segment and angle controls.
It retains the same five-shell truth rather than claiming a Boolean-unioned solid. Its default Dark
Walnut material is a versioned path-free MTL uniform color, not a generated image or third-party
texture; selecting an exact managed image replaces it through the established diffuse-material route.
Reviewed filesystem export stages and atomically writes an exact OBJ/MTL/optional-texture package or
one geometry-only STL, but creates no managed content, Source, User Asset, or association.

OF-03 Body Form edits and optional Front/Side/Back references remain transient until an explicit OF-05A
save. The session keeps an immutable compiled candidate, requalified Rig, bounded history, selection, and
defensively copied reference bytes/registration in memory only. Registering or aligning an image never
changes its source bytes and creates no content object, project asset, Source, User Asset, provenance row,
Factory Object version, or association. Closing the editor clears unsaved bytes and history.

OF-04 Skin state is equally transient until explicit save. `ObjectFactoryAppearanceSession` holds one
current checker, projected, or exact imported atlas plus compact coverage evidence and reciprocal selection.
Projection never rewrites registered reference bytes; compatible PNG import retains its admitted bytes but
creates no managed content, provenance, Factory Object version, or component membership. A changed Form
recipe invalidates the local result back to its canonical checker, and close releases unsaved
Appearance/evidence.

OF-05A persists the complete accepted snapshot through `FactoryNativePackageService` in the existing
project database and content store. Additive native-version and ordered component-membership rows reference
immutable `content_object`/`project_asset` payloads; the canonical OBJ remains the visible `user_asset`, and
the exact diffuse remains its derivation. One transaction writes manifest/component assets, append-only
logical/native versions, source-object/version provenance for Save As, memberships, derivations, and the
current Object pointer. Exact Save checks the prior identity. Load verifies stored hashes and component
cardinality/schema/revision/ordinal truth, recompiles the Form/Rig derivatives, verifies Appearance and
reference compatibility, and only then returns one immutable draft. Failure rolls back the complete graph;
there is no second package store or mutable in-place revision.

The Factory-owned Body Form capture prerequisite creates no row, content object, asset, Native version,
current Object pointer, recovery payload, or durable read path. Its strict transfer envelope is an immutable
in-memory representation of one Java-compiled, exact-current transient edit session. Only a later admitted
Application Server request may hand that candidate to the unchanged OF-05A transaction authority.

OF-06 extends that versioned graph with zero-to-eight optional complete POSE components. Each component
contains only canonical parent-local transforms for one exact Rig and is decoded and revalidated during
package load. A matched candidate, target, pole, pin, residual, confidence, deck observation, and undo/redo
history remain transient and create no component or content row. Only explicit exact-revision Save Pose
captures one complete snapshot and appends it through the existing OF-05A transaction. Critter images,
queue state, generation assets, and private provenance remain owned and stored by Critter Image Creation.

OF-07 adds no persisted component, row, content object, asset, provenance, or package membership. The
current Field definition, complete-Pose dot captures, compiled triangulation, cursor, weights, selection,
and undo/redo history are screen-lifetime transient state. Record/Performance Path and exact-Rig Field
membership begin only in OF-08A; ordinary Native Package Save and Save Pose do not capture OF-07 Field
state.

OF-08A extends the existing immutable component graph with optional repeatable `MOTION_POSE` and
`PUPPETEER_FIELD` roles plus one `PERFORMANCE_PATH` and optional `SOLVED_CLIP`. Current and Path-pinned old
Field revisions may coexist; Pose identity/revision/digest pins resolve both without rewriting either.
Ordinary Native Save, Save Pose, and Save Performance preserve the full admitted motion graph through the
same append-only version and content transaction. Reload decodes, recompiles, and cross-checks every exact
dependency before returning motion. The Path remains authoritative; Clip bytes are a source/versioned
derivative. No behavior layer/mask, provider provenance, Critter asset, or second store is persisted.

OF-09A adds no database row, content object, project asset, Native Package role, provenance record, current
Object pointer, or mutable latest-Template link. `ObjectFactoryTemplate` is immutable program data and one
compile returns a detached Form/Rig/Atlas result plus canonical parameter and compatibility evidence. Broad
and slender results absorb their exact Template revision and values in the returned snapshot; changing the
shared definition affects only later compiles and requires an explicit revision. Durable biped package
membership and production Create Form adoption are not claimed by this body.

OF-09B likewise adds only immutable program data for quadruped and dragon families. Compiles remain detached
Form/Rig/Atlas candidates with exact parameter/signature evidence; no Template registry, mutable latest
pointer, package role, database row, content object, asset, provenance, or save transaction is added.
Multiple local Appearance PNGs may be reviewed against one exact dragon candidate, but only the already
existing OF-05A transaction can later make accepted package state durable. OF-09B performs no such save.

OF-UI-01 adds no row, content object, managed asset, Native Package component, Template registry, mutable
latest pointer, Creature assignment, provenance record, or transaction. The browser may retain the selected
catalog type, proportion values, named stance draft, Box expansion, camera, and existing mapping draft in
its established creature-scoped local recovery payload. That state is explicitly disposable presentation
review and cannot be loaded as a durable Form, accepted Appearance, or Creature-body assignment. A later
Morph Form cover and Paint Guide are generated projections of that same disposable catalog/recovery state;
they add no image, mesh, asset, package component, or provider payload. A later
235-Creature adoption body must carry exact Creature and Body Form revisions through a separately reviewed
checked authority; it cannot promote these browser values implicitly.

OF-UI-BODY-FORM-31 advances the disposable shelf fingerprint to v13. Older nested or separately disclosed
Type/Proportions presentation envelopes fail closed to the complete direct Body Form/Views/Image Data
defaults. The left Proportions drawer's open flag is creature-scoped disposable browser recovery. The removed
symmetry preference is admitted only as legacy Morph Exchange input and cannot disable the graph's declared
bilateral constraint; no Body Form, Morph, Creature, or asset authority changes.

OF-UI-INTERACTION-32 keeps Undo history in memory and bounds it to 50 complete transient Form snapshots.
Membrane thickness participates in the existing browser recovery draft and Morph Exchange document but is
not durable Morph authority. The UI names its local action Save recovery draft and says it is not the
permanent database Save. Clear saved draft deletes only that browser recovery payload and preserves the
working Form. The delegated Application Server Body Form Save/Save As adapter preserves a complete Native
Package through the checked Factory Object transaction, but accepts no recovery payload and remains inactive
until a bounded transfer and lease-owning composition are separately reviewed. The separate authenticated
capture adapter is not a persistence route: it returns only Factory's strict immutable transfer envelope and
has no database, durable read, or Save/Save-As authority. A permanent Morph Save/load
remains an Application Server-owned checked Factory Object/Native Package database transaction and cannot
be simulated by local storage.

OF-UI-TABLET-33 persists nothing new. The open-count attribute is derived from the already admitted complete
Shelf envelope and is never saved. Tablet rows do not create a second order, open state, layout snapshot, or
content identity; invalid/stale recovery still falls back to the complete Shelf defaults.

OF-UI-MIGRATE-02 persists no page, manifest, Source, Creature, Native Package, operation, capability grant,
or process state. Its Gallery/Creature resources remain ordinary owner-packaged classpath content. Safe card
projections contain bounded scalar identity/status, opaque media ID and digest, and optional body-form
summary only. Exact asset and operation revisions remain transient stale guards. They contain no filesystem
path, provider credential/payload, private provenance, prompt,
controller token, or live-data reference. Browser selection/filter/progress state is disposable.

OF-10 review, invocation, raw provider payload, transient preview, rejection, cancellation, and revision
draft remain in memory and create no row, content object, project asset, Source, User Asset, or Critter
record. Accept creates one immutable generated Appearance revision in the existing session. Its path-free
provenance contains only reviewed request/settings/prompt digests, provider/model/request identity, accepted
diffuse digest, and bounded reference count. `ObjectFactoryNativePackage` embeds that evidence in the
existing Appearance component; the unchanged OF-05A transaction stores it atomically with the exact diffuse
and coverage evidence. Reload verifies source/provenance presence and digest compatibility before returning
the draft. No raw prompt, credential, local path, reference bytes, raw provider response, or second store is
persisted as provenance.

OF-P01A retains original STL/OBJ/MTL/PNG bytes, individual/source-set digests, normalized source preview,
candidate mesh, deterministic OBJ/MTL/report artifacts, metrics, capability losses, provenance scalars, and
an unsigned receipt only in the screen-lifetime staging result. Preview creates no Source, content object,
User Asset, managed model, association, logical Factory Object, Native Package component, current-version
pointer, membership, database row, or filesystem artifact. The receipt is commit-ready evidence, not a
commit token or signature. OF-P01B must revalidate exact bytes, candidate, receipt, and expected revision
before using the existing checked Assets/Factory transaction; it must not reinterpret this transient state
as durable merely because hashes exist.

CM-07 adds no persisted component, row, content object, artifact, Source, User Asset, provenance, or Context
payload. `ObjectFactoryContextCapture` projects only already-resident bounded scalars and optional resolvable
Object/model identities. The Factory panel remains the exact session/selection/stale authority, and Context
adoption cannot advance a Native Package version or make a transient draft durable. The Context-owned
provider, passive Monitor update, and reusable actions serialize only the admitted scalar snapshot under the
existing CM-01 payload contract; production destinations remain unavailable and create no conversation,
receipt, provider result, or durable record.

The strict version-1 `FactoryStarterMaterialCatalog` is packaged with eight reviewed Poly Haven CC0
construction materials and their exact offline map bytes. Stable manifest entries retain material and
provider asset IDs, version, creator, listing and CC0-1.0 license truth, physical repeat dimensions,
compatible/default families, and Diffuse/OpenGL-Normal/Roughness-or-ARM map source URL, MIME, size,
upstream MD5, and SHA-256. Catalog load rejects unknown or duplicate definitions and any bundled-byte
mismatch; ordinary Factory use needs no network request. Only Diffuse drives the current Viewer/MTL.
The other reviewed maps remain explicit retained source artifacts rather than a false PBR-use claim.

`FactoryModelAdmissionService` is the sole checked immutable Factory model/recipe admission
implementation. Its exact factory-kind descriptor carries
the Wall, Floor, Ceiling, Rectangular Table, or Round Table value through recipe MIME/codec,
derivation operation, source metadata, managed metadata, and recreation evidence rather than
classifying every Factory
model as a wall. It rechecks kind, candidate generation, recipe, OBJ/material/artifact/STL hashes,
material definition ID/version, and an optional exact managed texture ID/revision/content hash. One
transaction stores or reuses the versioned recipe and model package, records recipe/material/texture/
STL derivations, and creates or reuses one normal managed OBJ. The STL remains derived content rather
than a
second managed asset and never claims material/texture semantics. Managed identity derives
from recipe SHA plus canonical OBJ SHA, while the canonical OBJ content object remains exact-byte
deduplicated. Different kinds or recreation recipes therefore cannot overwrite each other's managed
metadata merely because their current geometry matches. Byte-identical content transforms use
recipe-neutral derivation parameters; each exact recipe-to-OBJ ancestry row and managed-asset metadata
retain the kind-specific recreation truth. Existing Wall, Floor, Ceiling, and version-1 Rectangular
Table rows retain their exact established identity and semantics. Table Accept records exact
furniture recipe/kind, material version, project-owned provenance, and recreation parameters and
never auto-attaches it. Round Table Accept also records the Dark Walnut uniform color without a false
texture/source identity, or the exact managed texture override when selected. For a selected starter
material, the same transaction admits or reuses every map as immutable content plus a project asset
and exact external provenance, while Diffuse additionally receives the visible User Asset used by the
model-material derivation. Existing stable IDs are accepted only when stored bytes, provider, license,
and source identity agree. Factory model output has project-owned provenance and no invented external
Source; starter and existing managed textures retain canonical provider/license evidence. Stale
input, collision mismatch, cancellation, or failure leaves no recipe, model, material map, artifact,
project asset, User Asset, collection membership, association, or partial export package.

`FactoryObjectLibraryService` adds reference identity, not another byte store. `factory_object`
retains one stable project-scoped Object ID, exact kind, optimistic revision, and current version.
`factory_object_version` is append-only and points to the existing immutable recipe, canonical OBJ,
managed asset, material definition, and version identities. Save creates or advances the exact Object;
Save As creates a distinct Object and leaves the source unchanged. The same transaction admits/reuses
the candidate, appends the version, advances the guarded current pointer, and ensures current
membership in the existing Objects collection. When an Object changes assets, the old asset remains
immutable version evidence and loses Objects membership only when no active Object currently points
to it. Existing Factory assets already in Objects require no data migration: deterministic legacy
Object IDs project them read-only and explicit first Save adopts one with its original version and
provenance intact. Arena reset classifies both Factory Object tables as User Assets data.

Objects collection membership is a projection, not a new storage authority. Equal normalized
specification, material version, and artifact bytes retain one managed identity across logical Object
versions. `UserAssetsWorkspaceState` projects each committed membership change as one exact in-memory
delta. `UserAssetsWorkspaceTreeView` patches only those synthetic managed collection nodes, preserves
the current Media selection/expansion, and does not start an owner-wide reread or tree rebuild. A later
explicit Reveal command may navigate to the exact asset; successful Factory Save itself never changes
the selected Factory workspace.

Public managed-asset reads derive human names without rewriting durable metadata. Candidate IDs,
UUIDs, hashes, and generated technical suffixes remain internal; equal human names receive
deterministic numeric collision labels. `UserAssetsWorkspaceState` loads exact associated asset IDs
beside the canonical asset rows. `UserAssetsWorkspaceTreeView` then projects three separate views:
Assignment Inbox for assets with no Character/Creature/Place association, Catalog for every canonical
asset, and Provenance Sources for external occurrences. Exact-ID Reveal always selects Catalog.
`previewManagedAssetNames()` exposes an exact read-only token, safe labels, changed-name count, and
collision groups. Because ordinary reads derive the correction, no durable-name migration or live
data execution gate is required.

`CreatedThingAttachmentService` is the checked write boundary for Attach Selected. Its destination is
the exact Created Things selection, not a mutable global Character: a presentation leaf replaces that
active slot through stable role history, a document leaf replaces only its exact association, and an
additional-content collection adds without promotion. A broad Character/Creature destination requires
an explicit reviewed role. Managed assets are reused by exact canonical ID/revision. Available Source
occurrences are admitted through `CharacterAssetPackageService` into database-owned content before
association in the same caller-owned transaction; legacy roots and provenance paths are never write
authority. Preview and apply recheck target, slot/association, asset/occurrence, and UI selection
revisions. Rollback leaves no admitted orphan or partial association. Before commit, the service reads
back only the exact new association or affected presentation-role history in the same transaction;
success returns that checked delta with the committed asset for write-through projection.

Ingest copies accepted content into the managed library and records Source-relative provenance.
Identical bytes from different Asset Sources retain distinct occurrence/provenance nodes. Each
external source has an immutable ID, independent editable display name, mutable root path, and
tracked/stopped state. Add creates the named identity before scanning. Change/Locate Location changes
only its root mapping after exact relative-path/hash verification; it never moves external files.
Rename changes only presentation. Stop Tracking removes a source from active scans/selectors while
retaining external files, imported assets, nodes, tags, associations, managed content, and provenance;
Restore Tracking re-enables that same identity. Removing an attachment preserves both target and asset.

DB-owned bytes remain the sole managed-content authority. Within one process, verified immutable
materializations and verified absent thumbnails are reused from the disposable owned-content cache;
workspace refresh does not reread the same blob on every snapshot. After Files import, only the exact
accepted occurrences are reclassified. A complete Content Root scan remains an explicit refresh or
source-change operation rather than an automatic second pass after every import.

The workspace keeps disposable projections of managed-record metadata, associated asset IDs, and
retired Source identities. These are read-through snapshots and committed-delta write-through caches,
not persistence authorities. Attach completion reuses cached managed metadata when present, falls
back to one exact-ID read when absent, and replaces only the association ID or presentation-role slice
returned by the checked transaction. It does not reread the owner or resolve unchanged effective
roles. Payload bytes remain lazy behind the existing content/materialization APIs. Startup and explicit
recovery may perform a complete canonical reconciliation; an ordinary association commit does not.

Synthetic pre-source occurrences never appear as an active source named `Legacy Imports`. Startup,
ingest, Add Source, Restore Tracking, and Change/Locate run one idempotent repair: an occurrence moves to a
named source only when exactly one normalized relative path resolves to bytes whose SHA-256 equals
the stored asset identity. Reassignment preserves the asset ID, node ID, tags, attachments, managed
content, derivation, and timestamps. Ambiguous, changed, or unavailable evidence remains under the
explicit hidden `Unassigned Source` state and appears under `Provenance Unresolved`; it is never
guessed from a filename and does not imply that entity ownership is unassigned. Empty synthetic
registrations are removed transactionally.

A missing managed copy remains a visible broken record. It may be reconstructed from a mapped Source
occurrence only after exact SHA-256 verification. Files Restore never overwrites a reappeared external
file. Purge/delete commands are explicit and guarded; purging managed data never deletes external
Asset Source files, and shared content remains while another Source/asset still references it.

`UserAssetsService` also guards every direct asset/folder delete and all/source/node purge before
filesystem or repository mutation. `MapDocumentUserAssetDependencyResolver` projects only current
Background references from structural working-snapshot copies and resolves durable occurrence IDs
to their current User Asset identity. A Background dependency blocks deletion atomically and is
reported as `used by Background` with the owning Place and the Authoring `Clear Background Image`
repair action. Clearing the Background removes only its image/provenance; a later Assets deletion
may proceed when no other dependency remains. A failed dependency read blocks safely rather than
allowing a dangling reference. Removing a duplicate source occurrence is blocked only when that
exact occurrence is the Background provenance or its content would otherwise become orphaned.

Map Editor import from a User Asset first resolves/materializes canonical content and stores User
Asset/node provenance in the new immutable Region Source. A later `UserAssetsBus.EVT_CONTENT_CHANGED`
event makes `MapEditorSourceBehavior` refresh linked raster Layers on the EDT and publish one
display-safe document mutation. This is a checked application event, not invisible filesystem
write-through, and it does not overwrite an external Asset Source file.

Presentation-role state lives with each `entity_asset_attachment`. One exact entity/role may have
one explicit `active` association; retained associations carry stable monotonic history numbers and
human labels. `entity_presentation_role_sequence` prevents reuse after removal. Keep &amp; Replace
archives the prior active row and activates the new exact asset in one transaction. Exact-content
retry returns the same active association. Rename and promotion require exact association/revision;
promotion to the base role fails while an active slot exists. Removing active never promotes
history, deleting middle history never renumbers survivors, and none of these operations deletes
canonical asset bytes. Legacy role rows are adopted deterministically before unique active/history
constraints are installed.

Ordinary association removal is exact-row and revision checked; it never deletes the managed asset
or another entity/role association. Complete managed deletion instead starts from
`ManagedAssetDeletionPreview`, which fingerprints the asset plus every association, context row,
Source occurrence, Media/project projection, normalized attachment, and accepted-request reference.
`UserAssetsService` adds external dependency blockers to the public token. Apply accepts only that
unchanged token and deletes the complete graph plus now-unreferenced canonical content in one
transaction. A stale token, dependency, or exception leaves all rows and bytes unchanged.

`CreatedThingPresentationMediaService` owns the separate blocking payload projection used only by
explicit checked admission boundaries. It resolves an exact persistent active Character/Creature ID,
the durable global presentation-association revision, and the Created Thing's current Silhouette and
3D Model/STL attachments in one SQLite read transaction. The revision table is incremented by
database triggers only for completed Icon, Token, Silhouette, and 3D Model association mutations, so
adjacent exact-role consumers retain one globally meaningful mutation stamp across those roles and
restarts.

The projection reads only the explicit active role and resolves immutable canonical DB-owned bytes
for supported active PNG/JPEG/WebP Silhouettes up to 16 MiB and STL models up to 64 MiB; missing,
inactive, unsupported, oversized, or unreadable content has a fixed non-content
state. No asset identity, path, hash, prompt, provider/provenance, Type default, source label, or
other private field crosses the API. The read never generates, selects, repairs, refreshes, attaches,
mutates, or caches media, and a returned snapshot remains a historical admission input after a newer
association revision exists.

Type presentation defaults are durable `entity_asset_attachment` rows owned by the exact
`srd_definition` identity. `EffectivePresentationResolver` reads an exact instance override, then the
current primary Class or Creature Type default, then the existing Token-to-Icon fallback. Character
and Creature creation persist the exact primary Type identity but do not copy Type media rows or
bytes. Type changes therefore affect only instances without overrides; removing an instance override
reveals the current Type default. Unavailable or incompatible assets fall through truthfully without
deleting shared content. Historical Silhouette and portrait associations remain compatible managed
relationships but are not standard Type defaults.

Assets reads one exact association snapshot to project direct Type-owned role leaves in the Types
tree. That read adds no persistence authority: it neither copies Type media into instances nor changes
the resolver order. Committed attachment refresh replaces only the exact Type's in-memory association
slice while canonical rows and managed bytes remain owned by the existing User Assets services.

Effective presentation revisions combine the existing durable Created Thing association revision
with the exact relevant Type association/asset/content state. The readiness roster combines all Type
default states into one global roster token. No schema or data migration is required.

`CharacterStandardPackageService` participates in that same Character-creation transaction after
the accepted sheet and primary Type link exist. It creates four stable typed slots under the
exact Character: Icon, distinct Token, optional 3D Model, and lowercase `character sheet.md`.
Service construction owns the slot table/index initialization. Ordinary one- and all-Character slot
projections are SELECT-only: they neither run schema DDL nor repair rows. Bulk projection first closes
its active-Character cursor and then resolves every exact owner through that same connection, avoiding
nested connection and schema-lock escalation under a live SQLite result set.
Empty presentation slots are durable replacement identities rather than missing asset rows. Token
presentation reads Icon as a fallback only when Token is empty; the two stored roles and their
replacement histories remain independent. The document is immutable DB-owned
`content_object`/`project_asset` content with one exact association. Editing validates the versioned
YAML front matter before one Character/document/association-revision-checked transaction replaces
the canonical bytes and normalized Character projections together.

Existing Characters use an explicit completion preview. Its token covers the exact Character
revision plus every standard slot, association, asset, state, and revision. Apply adds only absent
slots/documents, preserves all active roles, history, documents, and managed bytes, and is
idempotent. A changed Character or association invalidates the preview before mutation.

Prepared SRD import retains generic heading-derived definitions and routes only exact known table
structures through `SrdDocumentExtractionRegistry`, keyed by ruleset and document identity. The SRD
5.2.1 `06-equipment.md` extractor adds all 38 ordinary Weapon rows and 13 Armor rows to the existing
103 Equipment definitions. Stable semantic source keys and categories project each row exactly once
under Weapons/Simple-or-Martial/Melee-or-Ranged or Armor/Light-or-Medium-or-Heavy/Shields, without
classifying them as Magic Items. Each row retains exact document, top-level section, source page, and
structured Equipment facts. Preview reports add/update/unchanged definition counts and malformed
table diagnostics before mutation. Its token binds the selected document hashes and their imported
hashes; import rereads and rechecks the reviewed source before one existing document transaction.
No schema or source-document replacement authority is added.

`CharacterSheetDocument` is the version-1 codec for lowercase `character sheet.md`. Its bounded safe
YAML front matter is the canonical structured authority while trailing Markdown remains preserved
narrative. Quick Import accepts incomplete-but-valid sheets, reports syntax line/column and semantic
field paths, and preserves unknown top-level values under `extensions.importedTopLevel`. It produces
one exact canonical source before creation; no exact installed SRD match is required for custom
Species, Class, or Background names.

`GameObjectService` is the canonical reviewed Character/Creature instance-data boundary. Character
creation admits the exact validated sheet and writes its normalized `game_object` and
`character_profile` projections in the same transaction. Canonical reads prefer the managed sheet;
legacy `data_yaml` remains a compatibility read only until an explicit reviewed standard-package
completion creates a missing sheet. A checked sheet save validates syntax/semantics and all exact
Character, association, asset, and content revisions before replacing document bytes and projections
together. Invalid or stale edits preserve the last valid revision. Guided Builder still produces its
reviewed snapshot through the same document codec; it is an adapter, not a second authority.

Creature YAML similarly records only explicit instance overrides plus its inherited Type identity.
The Type's facts remain read-only in Assets; a checked exact-revision replacement cannot silently
copy or overwrite inherited facts. A document named **stats.md** is reserved for a future DB-owned
readable export and is never a second persistence authority.

`CreatedThingPresentationReadinessService` exposes a private scalar roster for active persistent
Characters and Creatures. It returns only exact ID, kind, the global association revision, and
Token/Icon/3D Model PRESENT or MISSING state after verifying canonical DB content. Readiness requires
Icon for 2D and the exact 3D route: supported Model first, otherwise Token alpha-cutout standee,
otherwise Missing. Silhouette does not participate. The roster performs no
generic-Illustration fallback, label join, generation, repair, or byte/provenance disclosure.

Adventure Character Sheets CS-03 is a read-only consumer of the existing Assets association/content
authority. It does not add a table, copy an association, or promote a fallback. Its bounded projection
retains exact current Icon, Full Portrait, Token, Silhouette, and Best Illustration association/asset
revisions plus presentation PNGs, while excluding managed paths, source/provider metadata, and credentials.
Its metadata-first intake admits only positive PNG/JPEG dimensions up to a 4,096-pixel edge and 16,000,000
total pixels before raster decode, then requires decoded dimensions to match and scales to a 960-pixel
presentation bound. Physical Description and Visual Direction remain canonical Character-sheet facts;
Adventure persists no competing description document.

Adventure Character Sheets CS-04 adds `adventure_character_history_event` as Adventure-owned project-SQLite
context for one exact attached Character reference. Each bounded row retains an opaque event identity,
Adventure/reference/Character/source identities, stable sequence, event revision, and the focused chronology,
title, location, description, and consequences. It is not a canonical Character sheet or Assets document.
One checked transaction revalidates the Adventure revision, exact reference, active Character ID, and source
revision; it inserts or updates the event and advances the Adventure aggregate revision together. No-change
advances neither revision, while stale or failed work rolls back both.

Explicit Character file admission is the adjacent checked mutation boundary. Markdown, supported
images, and bounded-valid STL enter `CharacterAssetPackageService` with one explicit active
Character key and a full preview token. One transaction inserts or reuses immutable canonical
content, compatibility/project-asset metadata, distinct source-occurrence provenance, and
idempotent document/image/model associations. Exact content may be shared by several Characters;
source labels and prior import status never grant or deny permission. Selected Item inspectors read
only verified DB-owned bytes. The STL inspector reuses `ArenaObjectStlParser` and does not own Arena
runtime persistence or a parallel renderer/importer contract.

The public `CharacterMediaGenerationBus` adds no persistence authority. Its retained client exposes
only exact Character/revision/role/session commands and identity/hash-only candidate review. A
secret-free current-settings descriptor exposes readiness without credentials, while its bounded
default start lets Assets construct the provider request from the canonical shared Generative 2D
settings. Keep delegates to the same `CharacterAssetPackageService` transaction, then reports the
exact admitted asset and association; reject, cancel, provider failure, and stale/cross-target
commands admit no bytes and create no association.

Bulk Character package admission coordinates that same authority without creating another content
store. `CharacterPackageImportService` groups only currently available supported Files leaves by
their first source-relative folder. A reviewed row captures an exact existing Character ID/revision
or a fully prepared new Character snapshot, per-image roles, README Context choice, and reviewed
Description. Its token covers every file hash/size/occurrence, target or new snapshot, role-slot
state, and accepted text. Apply owns one transaction per row: new `game_object`/profile/definition
links, canonical content and source occurrences, associations, Context, and Description all commit
together or all roll back. Exact bytes deduplicate across packages while distinct Source occurrences
remain separate. Failed or unresolved rows do not mutate or block independent reviewed rows.

Generated Character and Creature results enter that same checked boundary. The candidate's exact
bytes, request ID, separate user-authored Additional Instructions, assembled/submitted prompts,
provider/model metadata, target kind/revision, and captured attachment role commit with canonical
content and the association in one transaction. Role validation reads the just-admitted managed row
inside that transaction. A failure cannot leave content, User Asset, project-asset, or association
orphans and never consumes the retryable candidate. Exact-content retries reuse canonical rows and
the exact active association. A different result for the same role archives the prior association
under the next never-reused role number and becomes the explicit active renderer/readiness slot.
`GeneratedAssetRequestProvenance` is a read-only projection of that existing
metadata authority; it does not create a second request store and treats absent historical fields as
unavailable.

`CreatedThingsResetService` is the operator-only selective reset boundary for Make Stuff data. Its
preview fingerprints every targeted Created Thing, Group, membership, definition/profile row,
presentation association and its history sequence, normalized content attachment, visual profile,
and derived search row.
Execution requires both that exact preview token and the literal confirmation phrase, rechecks the
fingerprint inside the transaction, and deletes only those owned rows. Places, map/version/Source
rows, admitted runtime Arena definitions and Place presences, User Assets, Media Store/project asset
records, generated media, and content objects are explicitly preserved. The headless project-storage
tool exposes preview and execution; operators must create and verify an online project backup and
close Talisman before execution.

## Cameras, layouts, and presentation persistence

Camera, Arena3D height/exaggeration, programmed views, combined layout, splitter, popup mode, and
viewport preferences are presentation/workspace state. They do not advance map working revision,
document version, or document Undo/Redo.

`RecentPlacesHistory` is a scope-keyed `UserPrefs` owner shared by the Authoring and GM Places-tree
consumers. It stores a bounded deterministic move-to-front list of stable Place IDs plus the last
observed Place per product. The observed identities keep Clear stable across same-Place refresh or
component reconstruction. Deleted IDs are pruned against the current map projection. Recent Places
never enters `MapWorkspaceSnapshot`, project SQLite, document history, selection, or camera state.

`GmRegionNavigationSelection` is a private runtime-view target, not a saved preference or canonical
selection. It is repaired from the current GM Place/document/hotspot projection and is discarded on
Place change, document replacement, target removal, component close, or process restart. It never
enters map/Arena storage, Undo/Redo, Authoring selection, Player content, or camera state.

`GmRuntimeStateStore` schema 3 persists the GM active Place, its explicit visible-Layer ID set,
whether
visibility still follows Authoring. Absent state initially copies the active Place's Authoring
visibility. The first exact checkbox change in the embedded Control Play Background Layers section enters
custom mode and persists that set, including an intentionally all-hidden set, across reconstruction
and restart. The Control screen exposes no competing Match Author control; this preference state
does not mutate Authoring Layers or the staged Player presentation.
The same document-scoped value retains exact Place IDs whose synthetic `Play Objects` overlay is
hidden. Schema 1/2 values upgrade with that set empty (visible everywhere). This preference never
enters `MapWorkspaceSnapshot`, Arena object storage/history, or Player content; deleted Place IDs are
pruned during runtime reconciliation.

`PlaceOverviewPresentationStore` persists only product/scope mode, exact combined arrangement,
bounded zoom, normalized pan, and combined-divider ratio. Its legacy mode-plus-arrangement encoding
maps deterministically to the shared six-choice presentation. `CurrentCameraViewStore` keeps
separate Authoring and GM Place Overview
camera owners. Neither store retains rendered frames, Layer payloads, runtime state, map selection,
or document history, and the two product profiles cannot overwrite one another.

`WorkspaceLayoutService` owns versioned named product-shell/window/splitter/tab snapshots. Each
snapshot includes separate/combined mode, the active combined product surface, and only the window
geometry belonging to that mode. `ApplicationWindowManager` additionally pins the complete
independent separate-window rectangles across an in-process visit to combined mode.
`AppAwarePopupWindow` separately owns last-session normal/maximized configurations, and
`DTDTSplitter` owns each declared anchored side's expanded pixel allocation across resize,
collapse/restore, temporary minimum clamps, and reopen. Authoring and Control outer docks keep this
ordinary resizable persistence. Their local map/view control strips instead use fixed first-side
splitters whose non-draggable semantic dividers change presentation only; the same live control and
canvas roots survive collapse/reopen. Named/automatic layouts never contain map,
runtime, selection, field-input, history, or camera content. `WorkspaceLayoutService`,
`ApplicationWindowManager`, `AppAwarePopupWindow`, and `DTDTSplitter` are the current source-level
owners of the exact behavior and display fallback rules.

`CurrentCameraViewStore`, `ProgrammedViewStore`, `Arena3DHeightStore`, and
`PlayerScreenStateStore` own their respective preferences. Follow does not save a competing source
camera. Spawn/pinned consumers save independent state. Parent camera transfer initializes an unsaved
child only.

## Talisman Basic encounter runtime exclusion

`CombatRuntimeSessionService` retains encounter Place binding, combatants, initiative, schedule,
round, activation completion, and inspected/current identities only in the running application.
Start, Cancel, End, initiative, navigation, effects, and tactical visibility never call
`ArenaObjectStore` or change definitions, presences, positions, Places, maps, Layers, Assets, or
project content. Cross-boundary operations stop global playback and select one exact existing Arena
presence. The explicit Move operation is the sole mutation exception: it freezes exact encounter/runtime
guards, computes a detached current-route candidate, persists one ordinary Arena snapshot before live
install, then installs combat movement-spent truth without ending the activation or starting playback.
No-change does not persist or spend movement; checked save failure preserves position, route, history,
revisions, and spent truth.
Effect definitions load read-only from `app/combat/talisman-basic-effects.yaml`; effect instances,
current HP, selected action, Gradient visibility, and remaining schedule duration are encounter-only
and are never written to the project database, Created Things, reusable definitions, or Place
presences.

## Playback persistence and no offline catch-up

Runtime routes/destinations, speed/time basis, paused state, definitions, presences, Groups, and Group
presences persist through the Arena store. The scheduler uses monotonic elapsed time and advances all
active Places/Groups in bounded steps. Paused time is dropped by resetting the clock. Process close and
reload restore persisted positions/state; they do not simulate elapsed wall-clock time while offline.

An active in-process scheduler delay is different: its elapsed backlog is retained and drained in
steps capped at 0.25 seconds, so one UI stall cannot cause a single unbounded teleport.
# Temporary combat Place exclusion

Temporary combat Places are render-only runtime state. Creation and End neither enter
`MapRuntimeSessionService` history nor call `ArenaObjectStore`; the source Place, definitions,
presences, routes, Groups, Layers, project content, and Assets remain the only durable truth.
Admission is rejected when the document, Arena Object, or Group revision differs from the marquee
stamp.

## Application Server hot content and receipts

Owner source files remain the page-bundle content authority. Except for the bounded private Object Factory
rapid-preview snapshot below, the Application Server stores no copied page tree or browser database. Every request admits one complete manifest plus exact current asset bytes,
computes path-free SHA-256/page digests, and releases the snapshot after response. Hot refresh is not domain
persistence and cannot change Critter, Object Factory, Asset, Native Package, provider, queue, or database
truth.

Managed-handler package state is operational host state, not domain persistence. Tassy retains complete
verified bundle versions under its private state root and writes one atomic per-component selection receipt
containing active/predecessor version, public prefix, and package digest. Per-component state and log
directories are isolated runtime facilities only. The handler is given the already selected canonical project
SQLite path from `AppServices`; Slice catalog/model/revision/blob truth therefore remains in that existing
project database. The runtime creates no component SQLite database, semantic file fallback, browser database,
or duplicate content authority.

The local `Object Factory rapid preview lane` is a narrow presentation selection inside that same source authority.
Activation copies only the current complete `critter.creature` browser assets named by Main into one private Tassy
snapshot, then atomically selects that snapshot with a receipt recording the Main-manifest digest, complete page
digest, and every asset digest. The Factory's current browser files are the rapid-development content authority;
its Git cleanliness, Git revision, and unrelated manifest evolution are not deployment gates. The normal Main
manifest still loads first. Each request then admits either every receipt-matching Creature asset from the immutable
snapshot or every Creature asset from Main; a missing/unsafe/tampered snapshot, receipt parse failure, or Main page
contract change falls back as one complete Main page. It exposes no HTTP publication route. Activation and rollback
replace/remove only the receipt and private presentation snapshot, never the process, listener, database, capability
map, or Factory source. This is an owner-requested rapid UI deployment seam, not a Main landing or domain deployment
route.

The hosted TAS installs the exact manifest-declared source tree as an immutable release beside the JAR. Its
service-owned `${user.home}/.talisman/talisman-project.sqlite` is a writable clone of the separately retained
Online Server database release. It is never the Online Server's read-only `current-database` target. The
installer snapshots an existing TAS database before replacement or rollback, stops the sole TAS writer across
selection, and restores the prior release/database pair if health does not return. Reverse synchronization is
absent; later local-to-online replacement requires a separately checked quiesced publication boundary.
Critter media is not embedded in that SQLite clone. Hosted Critter roots live beneath the TAS service state
and begin empty; a later checked media release may populate them without copying a workstation-specific path.

An available page route may project one manifest-named HTML entry asset from the same admitted snapshot.
Bootstrap publishes only path-free server/manifest/page/bundle/reload/capability identity. Easy Tale owner
resources remain repository content; its persisted story, revision, privacy, and provider truth remain with
Easy Tale Java/AppServices and are not copied into the host.

The central Easy Tale registration references the four exact landed files in place. Page-relative routes
support the owner's native ES-module imports while remaining individually allowlisted and digest-checked;
there is no directory serving or inferred file fallback.

The three Critter page declarations similarly retain owner assets in place and do not copy a browser data
store. Their delegated read adapter maps only the SRD-owned typed Application Server DTOs. Managed media
delivery uses the owner's opaque identity and exact current SHA-256 recheck before returning bytes. Paths,
prior paths, prompts, provider request IDs, and credentials cannot enter the browser contract through those
DTOs. Loading the binary while routes are delegated reads no product data; later read activation is not
mutation authority.

Shelf AS-S1 client sessions are intentionally non-durable process memory. The HttpOnly cookie is an
authentication secret; browser bootstrap receives only the separate opaque workspace identity, generation,
and expiry. Restart loses the map and therefore rejects the old cookie. Locale and system-theme bootstrap
are presentation context, not saved Talisman preference or document truth.

TSR-05's durable Shelf preference route is implemented by Tassy's existing GM-admission-bound gateway.
Shelf's v1 presentation-memory envelope is keyed by application context, persistence owner, definition ID,
and definition fingerprint; Tassy binds that key to the admitted principal instead of accepting browser user
identity. One positive presentation revision and one opaque storage revision guard complete optimistic
writes. Invalid, foreign, partial, stale, and conflicting values leave the last accepted complete state
unchanged. The gateway persists only opaque complete-envelope bytes and opaque storage revisions in its
bounded, atomically replaced Application Server state-root receipt; restart reconstructs that receipt before
the first read. No Shelf state enters feature tables, domain documents, jobs, credentials, page-local P4/P5
storage, or the non-durable App Session map.

TSR-07's v2 envelope keeps the same storage key family and whole-value adapter while extending the key with
the state-definition ID and fingerprint. Its separately fingerprinted, Shelf-cross-bound declaration makes
selected presentation, normalized internal REGION-component divider ratios, and canonically ordered
component disclosures explicit without feature storage or disguised Shelf Group state. Migration reads the
empty v2 key before one completely valid legacy-key v1 value, retains its presentation revision, occurs only
in browser memory, and does not write during restore. The next explicit optimistic save advances once and
writes the complete v2 value with no inherited legacy storage revision. The adapter still patches or
interprets nothing. This Shelf body adds no route, store, backend, or duplicate storage owner.
Any non-empty invalid v2-key record fails closed without consulting v1, so corrupt successor storage cannot
silently resurrect legacy presentation. The Shelf presentation revision migrates; the adapter's opaque
storage revision does not.

The private lifecycle token, instance receipt, and opaque GM Control Shelf-memory receipt live under the
user's Application Server state directory;
only stable safe process identity enters HTTP. `DatabaseOwnerInterlock` remains the durable owner-evidence
and OS-lock contract for database writers. Body 1 takes no database lease because it owns no database
capability. The manifest persists `cutover_authorized=false`; later retirement requires an independently
reviewed central cutover receipt rather than an inferred healthy page.

## Versioned Critter generated content

Production Critter browser reads terminate at `DatabaseCritterProjectionService`. Its catalog uses one
bounded metadata-only join across active `entity_asset_attachment`, exact Creature definitions, and
`content_object`; stale associations without canonical content are absent. Media delivery parses one
opaque exact-slot identity, queries that definition/role directly, rechecks the requested SHA-256 against
both the active association and payload bytes, and otherwise fails closed. `SrdMonsterArtifactStore`
remains the generation/recovery source and is not ordinary host read authority.

Canonical Creature closure is an explicit operator migration, never a page-load repair. The
`closure-preview` command holds the database-owner interlock while it inventories validated accepted
current artifacts and only reviewed reusable roles with an exact canonical entry mapping. Its token pins
definition, role, accepted file hash, and current database hash. `closure-apply` first completes the same
stopped, same-volume, clone-only backup gate as historical migration, then rechecks the preview and delegates
each changed slot to `CanonicalSrdMonsterAssociationService`. That existing transaction admits a
`content_object` only when its hash is absent, archives/replaces a stale active association through the
presentation lifecycle, and the closure owner directly verifies the resulting exact role/hash. An exact
repeat has zero reconcile plans and adds neither content nor association history.
Apply first freezes every confined non-symlink image and metadata file, strictly parses exactly one JSON
object with no trailing data, and verifies its entity, definition, role, image hash, target, and authority
fields against the token. One database transaction then consumes only those immutable bytes and canonical
metadata; it never reopens artifact paths. Any changed file or failing role therefore commits no content or
association from the batch.

Critter Image Creation retains its deterministic compatibility Seasons root while its inventory rows
identify every actionable database Creature exactly. Known SRD Monsters preserve their historical keys;
Animals and other Creature records use definition-UID-backed keys. The image, decoded-alpha metadata,
request/result
provenance, creature manifest, corpus inventory/status, and resumable checkpoints are canonical together.
The batch checkpoint also stores the authority-confirmed `applied_concurrency` independently of resumable
sweep state. Startup prefers that validated one-to-six value, accepts the legacy `concurrency` field when
the explicit field is absent, and otherwise fails closed to one. Reading this setting never admits work.
One-model generation installs the authoritative current role immediately; Both comparison candidates
live under an explicitly noncanonical candidate identity until chosen. Redo and monochrome replacement
archive the prior exact PNG/metadata under a hash-addressed version path before installing the new current
role. Atomic moves install exact PNG/metadata files. Admission derives alpha evidence from a bounded decoded
PNG. Later accepted status requires the unchanged exact byte hash, bounded PNG header/chunk structure, and
complete internally consistent recorded alpha evidence; it does not repeatedly decode pixels. A changed
file stamp invalidates the read cache. Provider response or database-row truth alone is never sufficient.

Provider lanes write only operation-identified private candidates in the artifact store's batch candidate
namespace; concurrent
generation never installs a canonical role or associates a Creature. The single service-owned writer
serially archives any prior version, atomically installs one canonical role, and invokes the existing exact
Creature-role database authority. Direct cancellation after provider entry retains `staged_cancelled`
candidate/checkpoint evidence without changing the current role. Exact provider rejection stores bounded
code/request/prompt-revision/hash evidence only: it writes no image bytes, canonical role, database row, or
Seasons association, and automatic inventory excludes that role until an edited manual retry is admitted.
Startup bulk-reads only active exact-role association keys for missing detection. It does not load the
catalog's image payloads; a non-front lane reads exact active Front bytes on demand when no accepted
artifact Front exists. Icon and Front/Left/Right/Top/Bottom/Back share the managed association lifecycle.
Direct admission first checkpoints its client operation UUID, direct kind, admitted time, model, prompt
revision/hashes, and no-candidate truth. An uncertain repeat reconciles that identity; it never interprets
the queued placeholder as canonical bytes or a current asset.
OF-GENMETA-11A may add one `control_metadata_sha256` scalar to that direct checkpoint. The raw canonical
package is frozen only in the in-memory operation/provider request and is never stored in the checkpoint,
public operation projection, canonical Creature asset, or database. Absence uses the empty hash marker for
legacy compatibility. Same-operation replay must match the stored or in-memory hash before it can reconcile.
OF-SILHOUETTE-GUIDE-30 similarly adds only `silhouette_guide_sha256` to the direct checkpoint. Raw PNG and
package JSON remain in the in-memory operation through provider submission and are not stored as canonical
Creature bytes, role metadata, database content, or public progress. The generated result retains the existing
Critter image provenance and lifecycle; its guide hash proves admission identity rather than becoming Form or
Native Package authority.
OF-MAPPING-ANATOMY-22 analysis bounds, per-control confidence/source, unresolved identities, and prepared
control-metadata JSON remain page-lifetime transient evidence. Refresh reanalyzes the exact current image
against the canonical Mapping Form. Existing Creature-draft storage may retain the resulting shared Form and
observations, but it is not a saved basic morph, provider receipt, canonical image association, or database
revision. Durable Form authority remains the sole Native Package/Factory save route.
The pre-upgrade role-state `failed` plus sanitized-failure-string shape remains durable evidence rather
than being rewritten. Inventory interprets it as provider-rejected only for exact known safety wording,
the exact `moderation_blocked` code, a bounded safe request ID, and no newer structured failure kind.
Queue/gallery projection exposes normalized safe fields only; canonical bytes, checkpoint bytes, database,
and Seasons associations remain unchanged until an explicit edited manual retry succeeds.

Role metadata uses `GeneratedAssetRequestProvenance` for exact assembled/submitted prompt, target,
revision, role, and request identity. The prompt mirrors Talisman's established labeled context structure
with the exact database-backed monster image identity; it never substitutes a similarly named record.

The batch-local prompts area stores independent atomic Icon and six Token-view current revisions plus
immutable history.
Each logical reservation snapshots exact generic template, fully resolved request, revision, template
SHA-256, and effective SHA-256; later edits cannot rewrite queued/running/completed provenance. The
controller displays the generic role template inline. Public Current/Next and in-progress projections
omit resolved text/hashes; persisted matching image metadata exposes them only inside that card's
technical-details disclosure.

The batch-local usage-observations area is append-only informational evidence from an external signed-in usage
observer. Each deterministic observation identity covers timestamp, exact operation identity, queue
counts, spend/request/token totals, freshness, and explicit unrelated-traffic evidence. Duplicate files
are idempotent; flagged or non-monotonic samples do not contribute deltas. This ledger is neither billing
authority nor canonical generation state and cannot gate or mutate image/database content.

The database has one visible canonical Monster collection: the exact 235 imported SRD 5.2.1 `creature`
definitions in the exact Monsters document. The separate Animals document owns 95 additional legitimate
`creature` definitions and remains outside this batch/migration scope. `CanonicalSrdMonsterAssociationService`
never creates definitions. Its token-checked
transaction inventories every active Icon/Token hash on a complete exact historical collection, reuses
only the 68 reviewed one-to-one mappings when the current role is empty, preserves differing current
roles and unmatched/group evidence, then changes all 108 owned historical rows to the non-visible
`archived_legacy_monster_table_row` kind. No managed bytes, metadata, provenance, or historical
association is deleted; any mismatch or failure rolls back the entire migration.

Current monster presentation rows admit `token_back` beside `icon` and `token` on the same exact
definition. Token Back metadata pins the accepted front Token role and SHA-256 used by its separate image
edit; this reference is provenance and recovery evidence, while source corpus/version remain provenance
only. Historical migration continues to inventory only its legacy Icon/Token roles because the legacy
collection has no Token Back role.

Those archived rows remain exact records in the same `srd_definition` table.
`SrdMonsterImageRecordResolver` also resolves any other unique exact existing `creature` definition by
display name and verifies its full persisted identity. An explicit Workboard regeneration may replace one
active Icon, Token, or Token Back association on that exact record through
`CharacterAssetPackageService`; it creates no definition and changes no definition kind,
ruleset, source key, or provenance. Source corpus is therefore descriptive evidence, not mutation scope.
Dynamic direct identities are stored in the existing role checkpoint, enabling exact UUID/terminal
reconciliation after controller replacement without adding another database or persistence authority.

### OF-08B exact Behavior membership

`ObjectFactoryNativePackage.ComponentRole.BEHAVIOR`, `BEHAVIOR_CLIP`, and `BEHAVIOR_POSE` are optional
repeatable canonical components. Behavior bytes
retain the exact motion signature, composer/projector versions, ordered Layers, source identities/revisions/
digests/durations/C0 truth, masks, interval/repeat, mode, opacity, additive reference Pose, and root policy.
Capture decodes each Behavior against the package Rig. Load also requires every Path or Solved Clip pin and
every additive reference Pose pin to match a present component by exact identity, revision, and digest.
Missing or changed membership fails before one immutable loaded draft is returned. The loader admits the
exact
landed standard-biped Template compiler/Rig alongside the legacy arm and preserves the legacy arm Atlas
Contract bytes. The existing Native Package
version/component transaction remains the only durable writer; no Behavior table, second store, path,
provider provenance, or runtime publication is introduced.

OF-11 adds no table, content object, manifest member, mutable latest pointer, runtime checkpoint, semantic
event log, glTF/GLB artifact, or second store. `FactoryRuntimePublicationService` performs one verified
exact-current Native Package read and passes its immutable candidate to the pure publication boundary.
Runtime publication, invocation outcomes, posed OBJ frames, and consumer deliveries remain process-local
derivatives. The original canonical OBJ/MTL/diffuse component bytes are copied unchanged into the delivery;
authoring package, Behavior, and persistence authority remain with their existing owners.
## Easy Tale immutable source imports

`EasyTaleRepository` is the sole SQL and transaction owner. Body 6 adds immutable source editions keyed by
stable source/content digest identity, paragraph-equivalent anchors, one separate editable derivative per
edition, and source-linked review candidates. Same-edition admission is idempotent; changed content retains
the earlier edition. Import and review each advance the owning work revision atomically, while stale,
cancelled, relationally invalid, or interrupted operations commit neither partial rows nor a revision.

The bounded Seasons corpus route retains each original file as immutable bytes with category, internal
title, historical path/name, media type, extraction encoding, repository commit, byte digest, extracted
text, and extraction digest. The exact approved source-commit manifest contains 10 DOC/DOCX documents and
82 image/source-art files. It excludes PDFs, Pages, RTF, Markdown, and spreadsheets. Manifest identity is
verified before document extraction. Binary sources carry empty extracted text, so admission never invents
captions, OCR, events, or other semantic claims. A deterministic batch and manifest digest make the complete
transaction resumable; duplicate-looking editions remain distinct.
The reviewed source-owned manifest separately accounts for all 143 tracked paths as either admitted or
excluded with exact reason, provenance class, size, and digest. Its 51 exclusions are 49 user-scope files
and two tracked directory placeholders. Five observed `.DS_Store` files are environmental Finder metadata,
ignored before manifest construction, and cannot alter corpus authority.

The canonical document-materialization transaction is a separate durable checkpoint from raw admission.
It verifies the exact admitted and extraction manifests, all ten document extraction digests, the source
commit, 82-image preservation, and the current work revision before writing. It creates one distinct plain-
text source edition and editable derivative per document, paragraph-equivalent exact-text anchors, and a
Part/Chapter/Scene/block manuscript whose blocks rejoin to every extracted character. Alternate/archive
documents remain distinct. One `easy_tale_corpus_materialization` row seals text, structure, counts,
epoch-millisecond commit time, and committed work revision. Existing partial state, stale identity, changed
text, relational failure, or pre-commit validation failure rolls back every row and the revision. Repeating
the exact checkpoint returns its existing receipt without rewriting raw documents or images.

This checkpoint deliberately creates no candidate, character, alias, relationship, place, event, date,
timeline, contradiction, caption, or OCR claim. Those require the separate source-backed semantic review
body.

The canonical semantic transaction consumes that exact document receipt and source-text digest. A reviewed
source-name catalog creates discovered literary identities and aliases only from exact anchor occurrences;
appearances retain their exact wording and anchor. Temporal sentences become source-addressed corpus events
with exact spans/evidence and contiguous source-relative order, while absolute time and calendar stamps
remain null. Date, place, relationship, and unmatched-name claims remain pending review. A single
`easy_tale_corpus_semantic_materialization` row seals the semantic digest, source/document identities,
image count, all semantic row counts, commit time, and resulting work revision. Existing partial state,
stale source/revision, foreign-key failure, or pre-commit failure rolls back every semantic row and the
revision; exact rerun recomputes the live semantic digest and returns the same receipt without rewriting
document, manuscript, raw, or image rows. Same-count row drift rejects the receipt.

## Easy Tale literary characters

Body 7 enriches the existing Easy Tale character identity with source-backed profiles, appearances,
aliases, relationships, personal timeline items, missing facts, optional game links, duplicate review, and
unresolved mentions. Every transaction checks the owning work revision. Source anchors and referenced
characters must belong to that work. Link changes preserve literary rows; rejected duplicate review keeps
both identities; resolved timeline rows retain canonical epoch milliseconds plus exact calendar stamps.

## Easy Tale manuscript persistence

Body 8 stores the work/part/chapter/scene/block hierarchy, exact source/entity/event links, distinct event
treatments, and ordered compilation references under the existing work revision. A complete recovery draft
is staged and digest-checked before canonical commit; matching recovery clears only after the transaction
succeeds. Compilations reference scenes and never copy or rewrite manuscript text.

## Easy Tale browser-read persistence projection

`EasyTaleRepository.loadBrowserReadSnapshot` is the sole read seam for the typed browser projection. It
loads the canonical work, exact corpus stamp, browser-safe source fields, and current manuscript within one
transaction, verifies source count and manuscript revision coherence, then closes the connection. Its source
record omits raw bytes, original path/name, timestamps, and mutable repository handles. The service further
bounds and types all returned labels, excerpts, identities, revisions, digests, counts, availability, and
errors. No read advances a revision, changes a row, or opens a second store.

`EasyTaleBrowserCommandService` adds no table or transaction. It submits the complete bounded draft to the
existing `EasyTaleManuscriptService`; that service stages the digest-checked recovery record before the sole
repository manuscript transaction and returns the exact committed snapshot. Stale or failed save cannot
claim a revision advance. Recovery reads are revision-checked before and after service decoding, are exposed
for review only, and clear only when a matching explicit save commits successfully.

`loadStoryReadSnapshot` is the complete-story seam. In one checked read transaction it joins that browser
snapshot to the exact document and semantic receipts, all anchors and candidates, character/review
snapshots, corpus spans/events/evidence/participants/places/contradictions, and canonical recorded events.
The service verifies receipt identity, revision, source-text digest, counts, and work ownership before
constructing an immutable path-free projection. Images expose metadata only; raw bytes and source paths
remain repository-owned. Pending or inferred semantic rows remain review/evidence data and are never
promoted by a read.

Body 9A adds no table, provider store, transcript history, proposal log, or second transaction. Captures,
requests, provenance, name reviews, and proposals are immutable process-local values. Rejection,
cancellation, malformed results, uncertain names, and stale revisions write nothing. Accept constructs one
complete revised manuscript draft, rebases unaffected manuscript links, preserves source anchors, scene
event bindings, treatments, and compilations, and delegates the exact captured revision to the existing
`EasyTaleManuscriptService` recovery-plus-repository transaction.

Body 10 extends the existing `easy_tale_timeline_set` parent with one semantic scale and normalized ordered
`easy_tale_timeline_set_lane` children. A save validates unique lane kind/target identities, exact work
ownership, and the expected work revision; it replaces only that set's lanes, advances the work once,
constructs the immutable snapshot before commit, and returns no fallible post-commit read. Stale, relational,
or injected pre-commit failure rolls back the parent, children, and revision together. Foundation replacement
deletes dependent lane rows before timeline-set parents under enforced foreign keys. Timeline projection
reads only the already-landed complete-story value and writes no story, event, treatment, or source row.

Body 11 extends canonical recorded events with review status, event revision, description, precision,
uncertainty, original source-date wording, and exact calendar-definition identity/revision/digest. Separate
correction, correction-citation, event-revision, and revision-citation tables retain the complete
propose/review/commit trail and work-local immutable-anchor provenance. Proposal and review never modify the
canonical event. Commit appends one immutable revision and then updates the current event inside the same
exact-revision transaction; the complete history snapshot is validated before commit. Unresolved dates,
stale event/work revisions, foreign anchors, relational failure, and injected snapshot failure roll back.
The one-time document and semantic receipts must be no newer than the current work revision; later history
or presentation-set revisions do not falsely invalidate their immutable source evidence.

## Core Morph source materialization

`src/main/resources/app/factory/morphs/catalog.json` and its revisioned complete documents are the Core Morph
authoring authority. `CoreMorphCatalogService` validates the entire source catalog and every complete
document,
verifies canonical SHA-256, then atomically materializes `factory_core_morph` current pointers and immutable
`factory_core_morph_version` rows backed by existing `content_object` bytes. An exact rerun inserts or updates
nothing. Reusing one Morph identity/revision with different bytes aborts before changing durable truth.

Catalog revision 2 materializes twelve independent complete source objects in declared catalog order. Ancient
Dragon revision 2 contains 74 Points, twelve complete named positions, nine reversible motion pairs, and six
membrane attachments; its revision 1 source remains immutable history. Existing revision-1 databases gain the
catalog-order column additively, retain the old content/version bytes, insert every new current version, and
advance current pointers in the same checked transaction. Neither empty Factory object tables nor browser
recovery state may invent catalog entries. Catalog and selected GETs read this materialization without writing
it.


## Authored Core Morph and Creature-private revisions

The authored-save body adds `CoreMorphRevisionService` as the only interactive authored revision authority.
Immutable private content/version, Creature association/head and idempotent receipt commit in one checked
transaction. Save rollback preserves all previous rows and pointers. Explicit base publication appends an
authored `factory_core_morph_version` and advances only its head and the separate
`factory_core_morph_authored_catalog.change_sequence`; it never rewrites `source_catalog_revision` to an
authored counter. Existing Creature private pins/history stay unchanged. Authored bytes are durable primary
data, not a source cache. The legacy source-named hash columns retain compatibility keys and carry exact
content identity; `revision_origin` and `current_authority` distinguish SOURCE/TEMPLATE/IMPORT.
Startup validates the incoming source catalog, rejects changed existing SOURCE identities, verifies
protected stored content, does not roll source heads backward, and records protected updates/numeric
collisions in `factory_core_morph_source_pending` for explicit reconciliation. It neither imports over an
authored head nor fabricates source provenance. Catalog GET exposes the pending notices without writing.

## Geography scientific input evidence — GCP-01

`NumericalElevationTiffDecoder` retains Float32 metre samples and a validity mask in memory, not RGB
brightness or normalized stored Heightmap pixels. `ElevationReadRequest` identifies exact source bytes
and caller-reviewed geospatial/vertical evidence. TIFF bytes are rehashed; the metadata digest must be
bound to retained raw metadata by the later source authority. `NumericalElevationGrid` clones its data.
`SeasonsHeightContext` is path-free detached W1 evidence, with distinct revisions and verified bounded
sample content; synthetic fixture identities cannot be admitted as private local context.
No map document, Source, database schema, asset store, parent raster or project Place is changed here.
The research acquisition helper writes one explicit new evidence directory, not a product persistence
route. This decoder itself admits no Source or world mutation; the GCP-02A section below describes
canonical conversion added subsequently. Authored river and safe import remain separate gates.


## Geography canonical height and portable evidence — GCP-02A

`PreparedEarthHeight` retains source identity, normalized unsigned-16 codes and explicit Earth-derived
feet bounds. `EarthHeightPng` delegates to the existing `HeightmapRasterPngCodec`; it does not create a
height format or set a Seasons altitude. `EarthPackageArchive` checks fixed regular roles and resource
limits entirely in memory and never extracts a path. `EarthPackagePng` checks exact geometry and inflation
before image decode. `EarthPackageHeightValidator` independently re-derives TIFF-to-canonical pixels and
checks binary Water geometry, but not the author's river recipe or destination. The wire adapter uses
the existing Jackson dependency with closed fields and no polymorphic loading. Retained URLs are inert.
No Source admission, database change, alternate store, parent carving or checked import is introduced;
future admission must use existing Source leases and the checked map document authority.

## Geography authored Water and prepared source exchange — GCP-03

`AuthoredWaterRecipe` stores bounded editable source-area polygons, authored provenance, a draft revision
and explicit center/even-odd/boundary-wet coverage semantics. `AuthoredWaterMask` produces exact binary
coverage and a canonical image raster; it does not create an owned Source, attach a layer, infer water
from elevation, supply water level/depth, or alter ground samples. Future import assigns the existing
Water mask role through the normal transaction; no second persistent water catalog is introduced.

The existing twelve-role Earth exchange now includes a real authored river recipe, corresponding Water
Map and registered preview. `EarthPackageWaterValidator` re-rasterizes and compares every Water pixel,
then regenerates and compares every preview pixel after the existing independent height validation.
Updated hashes and a matching wet-cell count cannot legitimize moved pixels or a different shoreline.
`EarthPackageManifestJson.readWaterRecipe` uses the existing closed, bounded Jackson configuration.

The package contains original public Earth evidence and derived source-area data only. Retained URLs are
inert provenance, not fetch instructions. The raw acquisition receipt stays unchanged even though its
historical status predates subsequent input proof. Private Seasons IDs, parent heights, placement and
altitude remain outside this package. GCP-04 must revalidate the exact source and local context before any
normal owned Source/document admission; this source-only codec itself grants no import approval.

## Control binding of composed Shelf memory

Control stores one complete public v2 envelope under application context talisman.control-screen, its
unchanged Shelf definition identity and separate control.screen.presentation/v1 state-definition fingerprint.
The host, not the browser, supplies principal/application binding. Groups keep fixed order, open/closed
state, supported weights and public focus identities. Component state contains the normalized display
split and Overview metadata disclosure; selected display stays separate from open display.

`src/main/resources/app/control-screen/control-presentation.js` admits only presentation. Place, selection,
visibility, Arena, camera values, Context labels/bytes/digests, proposals, credentials and operation state
are never copied into that envelope. No localStorage, sessionStorage or second persistence engine is added.
Invalid, unavailable, downgraded or stale whole values do not partially restore. Shared valid v1 migration
performs no write and carries no legacy storage revision. Restoring editor-open state remains unavailable
until the existing admission/shield owner can perform explicit entry; saved preference cannot bypass it.

The landed memory gateway uses optimistic storage-revision arbitration. Its write signature receives no
idempotency key, so this client does not claim replay of an accepted receipt: unknown writes force explicit
read-back rather than retransmission. Same-receipt replay guarantees require a separate server-owner receipt.

## Volatile Control Context and canonical clipboard transfer

Only public Shelf presentation flags and sizes enter the accepted saved-layout envelope. Context labels,
canonical bytes, owner/request stamps, digests, external text and review proposals never enter Shelf memory,
localStorage, sessionStorage, IndexedDB, URL parameters or logs. The browser leases the existing host client;
it owns no Context store. Copy returns Java's exact encoded bytes; incoming text is wrapped unchanged for the
existing strict Java codec. Object-member reordering follows the Java contract; semantic reorder, changed base,
unknown/duplicate/stale/oversized input rejects there before any review. A rejected response clears the volatile
preview. No Apply, provider transport, media byte fetch or feature persistence is introduced.

The plain-HTTP clipboard fallback exposes canonical text only during explicit transfer and discards it on
close/hide/expiry. The OS clipboard is an intentional external transfer destination, not application persistence;
the page cannot recall data the person has copied to another application.


## Read-only component inspection

The TAS Database Viewer opens only the existing host-selected SQLite file in URI read-only/query-only
mode, uses one short read transaction per query, and closes it without writes. It avoids initializing
repository constructors and performs no schema migration. Existing component owners remain authoritative;
current-version pins, retained history, document-scoped identity and raw attachment roles are preserved.
Image/text previews require checked database content, and Slice part reads recheck their parent manifest.
No managed file path is opened or returned. Unmapped/missing sources are not represented as empty catalogs.
The separate Slice developer database and runtime-only objects are outside the admitted database scope.
See [Database Viewer](<../design/Active Designs/Application Server/DATABASE-VIEWER.md>).
