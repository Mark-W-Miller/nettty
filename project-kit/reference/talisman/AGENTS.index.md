# GPT Repository Index

## Planned Metamorph Gallery consolidation

- `design/Active Designs/Object Sing & Dance Factory__/METAMORPH-GALLERY.md`: active design-only
  all-object Gallery, Slice only scope, visual read-only cards and in-place Edit in the Slice workspace.
- `design/Active Designs/Object Sing & Dance Factory__/TEST-PLAN.md`: MG-01 through MG-19 focused
  acceptance; implementation/manual proof remains pending.
- `websites/moondance-web/talisman/`: current four-badge application page; planned Database Viewer badge
  replacement must share the real Slice entry. Source remains unchanged in this design body.
- `src/test/resources/app/application-server/managed-handler/slice/`: synthetic host fixture only.
  The current Slice Studio/editor source is not included in this Main snapshot; do not extend the fixture.


## TAS Database Viewer

- `src/main/resources/app/database-viewer/`: read-only JavaScript component tree, inspector and safe previews.
- `src/main/java/com/moondance/talisman/app/database/componentviewer/`: allowlisted source/relationship SQL
  and immediate typed reads; no schema ownership, migration, file-media resolution or name merging.
- `src/main/java/com/moondance/talisman/app/services/app/applicationserver/DatabaseViewerApplicationServerGateway.java`:
  closed GET projection; existing TAS controller owns App Session and post-read epoch revalidation.
- `design/Active Designs/Application Server/DATABASE-VIEWER.md`: source coverage, truth limits, lifecycle,
  focused commands, proof receipts and required native host adoption.
- `src/test/python/`: synthetic owner-schema, SQLite wire and optional browser proofs for this viewer only.


## Slice-native document storage

- `src/main/java/com/moondance/talisman/app/services/app/factory/ObjectFactorySlicePackage.java`:
  exact combined Slice source, derived inner projections and bounded explicit PNG reference closure.
- `src/main/java/com/moondance/talisman/app/database/userassets/FactorySliceDocumentStore.java`:
  package-private transaction implementation under FactoryNativePackageService; no independent writer.
- `FactoryNativePackageService.SliceSaveRequest` and `FactoryObjectOperationService.newSliceSave`:
  exact-destination typed save, durable receipt replay and checked historical/current readback.
- `src/test/java/com/moondance/talisman/app/database/userassets/FactorySliceDocumentTest.java`:
  synthetic storage and existing-operation proof, not live import or consumer compatibility.
- `scripts/object-factory/prove-isolated-slice.mjs`:
  disposable-index Main-plus-patch build overlay and exact input assertions; no checkout or live write.

Use this index to locate likely code and documentation before searching. It is a curated map of
stable ownership boundaries, not a complete generated file listing. Confirm behavior in current
source before editing.

## Search boundaries

- Search and edit only this repository unless the user explicitly authorizes another project.
- Start in tracked source. Ignore `.apt_generated*/`, `.gradle/`, `artifacts/`, `bin/`, `build/`,
  `runtime/`, and `tmp/` unless a task explicitly concerns generated or local runtime state.
- Prefer the mirrored package under `src/test/java/` when looking for focused coverage.
- Read the nearest `package-info.java` when entering an unfamiliar package.
- Treat removed historical design trees as Git history, not current specification.

## Moondance project adoption

[Moondance Project Profile v4](design/MOONDANCE-PROJECT-PROFILE.md) defines seven portable design languages,
local semantic bindings/deviations, executable workshops and candidate-only cross-game learning.
Detailed current Talisman contracts remain authoritative for implemented behavior.

## Top-level directory index

| Path | GPT routing guidance |
| --- | --- |
| `branding/` | Packaged application icons and brand assets. |
| `context-management/` | Contextual Ask AI/TaliTalk contracts, canonical version-one payload, reusable
  submit and structured-proposal coordinators, root subproject, and focused tests; AppServices owns one
  application service, separate action/proposal services, and the CM-05 Assets, CM-06 Adventure/Battle,
  and CM-07 Factory providers. |
| `development-atlas/` | Named Atlas seat and authoritative current ownership, behavior, service, and validation routes. |
| `design/` | Current design, architecture, product, and specification documentation. |
| `gradle/` | Gradle wrapper support; normally do not edit. |
| `how-to-branch.md` | Required main-based task branch, worktree, reconciliation, and handoff workflow. |
| `moondance-protocol/` | Toolkit-independent Java 21 relay controls and binary pane codec. |
| `moondance-server/` | Standalone Java 21 relay server, deployment, and tests. |
| `SRD_Def_Files/` | Tracked SRD source artifacts and canonical display-ready Markdown corpus. |
| `scripts/` | Developer utilities and AI-service launch/install scripts. |
| `scripts/status-dashboard/` | Local Switchboard status/detail server, guarded Master refresh,
  embedded Critter Image Creation controls, and focused tests. |
| `design/Active Designs/SRD Monster Image Batch/` | Critter six-view creation and focused plan. |
| `design/Active Designs/Application Server/` | Shared `3002` host, page/capability schema, lifecycle,
  interlock, migration, and cutover plan. |
| `design/Active Designs/Talisman Forest__/` | Forest semantic accents, shared presentation, and proof. |
| `design/Active Designs/Hosted Campaign Service/` | Retired hosted-platform research retained only as an
  idea source; it is not an active owner, worker, body queue, dependency, or release authority. |
| `design/Active Designs/Hosted Campaign Service/OWNER-AND-BODY-LOCK.md` | Explicit retirement notice and
  route to the controlling Moondance Web Talisman Online Server programme. |
| `design/Active Designs/Hosted Campaign Service/ARCHITECTURE.md` | HCS-00 hosted-domain
  boundary, state machines, data/privacy/retention contracts, role matrix, authority split, and minimal
  per-campaign composition; documentation only, with no full desktop `AppServices` graph. |
| `design/Active Designs/Talisman Geography/TERRAIN-DATA-RESEARCH.md` | Pilot order and input gates. |
| `design/Active Designs/Talisman Geography/SEASONS-RELEASE-CONTRACT.md` | SEA-00 immutable
  Seasons artifact structure, exact identity/compatibility, size/digest, rights/rollback, prohibited
  private material, and synthetic-fixture-only contract; documentation only, with no live Seasons material. |
| `design/Active Designs/Application Server/PAGE-PLUGIN-CONTRACT.md` | Exact owner resource, page entry,
  bootstrap, capability, registration, and Easy Tale URL contract. |
| `design/Active Designs/Application Server/HOT-PUBLICATION-HANDOFF.md` | Exact local rapid Creature
  publication command, receipt-backed component version/features, and Java-upgrade boundary. |
| `design/Active Designs/Application Server/CRITTER-READ-CONTRACT.md` | Exact Batch/Gallery/Creature order,
  read payloads, media/problem shapes, delegated activation, and frozen mutation successor contract. |
| `design/Active Designs/Application Server/MANAGED-HANDLER-RUNTIME-DESIGN.md` | Component-agnostic
  Tassy handler bundle, private-child, readiness, replacement, public-prefix dispatch, and receipt-derived
  Home shelf/root-route projection contract; Slice is the first example. |
| `design/Active Designs/Application Server/MOONBEAM-DELTA-PUBLICATION-DESIGN.md` | Authenticated,
  one-way Tassy-to-Moonbeam Gallery/SRD SQLite delta contract, stable sender commands, refusal rules,
  and server-release boundary. |
| `design/Active Designs/Application Server/GCS-01-GATEWAY-DESIGN.md` | Dependency-gated private GM
  gateway and complete-envelope Shelf presentation-memory adapter boundary; no route or store is installed. |
| `scripts/macos/install-talisman-desktop-launcher.sh` | Installs the guarded canonical-Main Talisman Desktop applet. |
| `scripts/macos/test-install-talisman-desktop-launcher.sh` | Isolated Desktop launcher installer/composition proof. |
| `scripts/macos/install-performance-workbench-desktop-launcher.sh` | Installs the guarded canonical-Main Performance Workbench Desktop applet. |
| `scripts/macos/test-install-performance-workbench-desktop-launcher.sh` | Isolated Workbench Desktop launcher installer/composition proof. |
| `src/main/java/` | Java production source. |
| `src/main/java/com/moondance/talisman/sdk/script/` | Stable API v1 exposed to imported Java Script Packages. |
| `src/main/resources/` | Packaged DTDT UI definitions and other runtime resources. |
| `src/main/resources/app/control-screen/` | GCS-01 private Control page: admitted current Place,
  GM-access request/redemption, public Object navigation and retained/saved Shelf layout. Renderer,
  selection/shield and Context integration remain separately gated. |
| `src/main/resources/app/control-screen/control-presentation.js` | Complete Control presentation binding
  to shared Shelf memory v2: seven arrangements, real left Box weights, component divider/disclosure and focus. |
| `src/main/resources/app/control-screen/control-layout.js` | One bootstrap-bound layout interaction,
  immediate busy state, explicit restore/save, conflict/unknown read-back and late/disposal rejection. |
| `src/main/resources/app/control-screen/control-layout-transport.js` | Fixed GM-private memory route and
  exact whole-envelope/key/stamp adapter over the existing Control client, without a new storage authority. |
| `src/main/resources/app/control-screen/control-context.js` | Volatile Java-admitted Tally Talk display,
  canonical clipboard interaction and Box/expiry disposal; never proposal or feature authority. |
| `src/main/resources/app/control-screen/control-context-transport.js` | Fixed admitted Context Copy/Paste,
  admission/snapshot currentness checks over one borrowed Control bootstrap lease. |
| `src/main/resources/app/control-screen/control-context-contract.js` | Trusted Java output display/correlation
  checks only; incoming proposal JSON is decoded solely by the existing Java codec. |
| `src/main/resources/app/control-screen/control-context-text.js` | Stable English presentation keys,
  separate from unchanged Java-admitted domain labels. |
| `src/main/resources/app/control-screen/README.md` | Private Control package boundaries and current versus
  deferred renderer/version/Context composition. |
| `src/test/js/control-layout.test.mjs` | Actual public Shelf store and Control adapter with synthetic
  transport: complete persistence, revision, privacy, migration, lost/conflict, stale/disposal and bounds proof. |
| `src/test/js/control-offline-browser.mjs` | Explicit local module-binding test mode; no production CSP,
  HTTP, live session or device acceptance claim. |
| `design/Active Designs/GM Control Screen/CONTROL-SAVED-LAYOUT-REVIEW.md` | Retained/saved-layout increment
  self-review, exact source, owner dependencies, focused evidence and remaining full-screen gates. |
| `src/main/resources/app/critter-image-creation/` | OF-UI-MIGRATE-02/ACTIVATE-06 hosted root Destination
  Shelf plus Creature Multi-open Morph/Morph Editor/Shader/Object presentation, with Puppeteer nested inside Morph Editor,
  and its checked owner DTDT over typed Critter capabilities. |
| `src/main/resources/app/factory/morphs/` | Source-authoritative complete Core Morph catalog and immutable
  revision documents; catalog revision 2 contains twelve independent current Morphs while Ancient Dragon
  revision 1 remains immutable source history. |
| `src/main/resources/app/talisman-shelf/` | Frozen Shelf Contract v1 compatibility validator, explicit v2
  browser dispatch, generic policy helpers, and the neutral `USER_CONTEXT_DURABLE` whole-envelope
  presentation-memory store; product Shelf choices remain in owner DTDT definitions. |
| `src/main/resources/app/easy-tale/` | Easy Tale-owned browser-first Shelf package, authenticated read
  client, fail-closed presentation state, bounded resizable deck workspace, and read-only character/event/
  place projections registered in place by the shared host. |
| `src/main/resources/app/application-server/page-manifest.yaml` | Canonical shared host service/page/
  entry/bootstrap/asset/capability/interlock/cutover schema and current owner registrations. |
| `src/main/java/com/moondance/talisman/app/services/app/applicationserver/ApplicationServerCommand.java` |
  Local/LAN lifecycle plus explicit loopback-only hosted outer-gate mode propagation. |
| `src/main/java/com/moondance/talisman/app/services/app/applicationserver/ApplicationServerController.java` |
  Shared HTTP adapter, App Session guards, local GM admission, hosted root-gate caller projection and
  unsafe-method admission, selected managed-handler public-prefix dispatch, plus the service-key-gated
  Moonbeam delta surface. |
| `src/main/java/com/moondance/talisman/app/services/app/applicationserver/MoonbeamDatabaseDeltaService.java` |
  Canonical-database-only Gallery/SRD inventory, receipt, zero-delete package admission, atomic apply,
  integrity/Gallery proof, and immutable idempotency receipts. |
| `src/main/java/com/moondance/talisman/app/services/app/applicationserver/MoonbeamDatabaseDeltaCommand.java` |
  Stable Tassy operator fetch/build/push client using configured HTTPS origin and service-key file. |
| `src/main/java/com/moondance/talisman/app/services/app/applicationserver/ManagedHandlerBundle.java` |
  Strict complete-file descriptor admission for versioned component-managed handler bundles. |
| `src/main/java/com/moondance/talisman/app/services/app/applicationserver/ManagedHandlerRuntime.java` |
  Generic private-child install/start/readiness/replacement/rollback/status/shutdown authority over the
  canonical project SQLite handoff and per-component operational state. |
| `src/main/java/com/moondance/talisman/app/services/app/applicationserver/ManagedHandlerHttpProxy.java` |
  Narrow HTTP transport from one selected registered public prefix to its active private managed child. |
| `src/test/java/com/moondance/talisman/app/services/app/applicationserver/ManagedHandlerRuntimeTest.java` |
  Synthetic Slice-fixture bundle/readiness/dispatch/rollback/isolation/host-shutdown proof. |
| `src/test/resources/app/application-server/managed-handler/slice/` | First complete versioned Slice managed
  handler fixture descriptor, private Python entry point, and browser asset for focused proof only. |
| `design/Active Designs/Application Server/MANAGED-HANDLER-RUNTIME-DESIGN.md` | Generic Tassy-managed private
  handler bundle, lifecycle, routing, shared-project-SQLite, rollback, and first-Slice contract. |
| `scripts/talisman-application-server.sh` | Fixed lifecycle and managed-handler commands for the sole
shared LAN host, plus receipt fetch, zero-delete Gallery/SRD delta build, and receipt-gated Moonbeam push. |
| `src/test/java/` | Tests, organized to mirror production packages. |
| `tasks/` | Backlog, active execution log, completion archive, and approved plans. |
| `testData/` | Versioned DTDT, reference-preference, web-image, and runnable walkthrough fixtures. |
| `testData/walkthrough/` | User-facing safe rehearsal YAML and Workbench playback instructions. |
| `websites/moondance-web/` | Public static sites plus secure relay join/Player client and routing. |
| `websites/moondance-web/deployment/build-managed-static-site.py` | Immutable complete-file package builder
  for a static site using Tassy's managed-handler contract. |
| `websites/moondance-web/deployment/serve-managed-static-site.py` | Generic read-only private static handler
  included in Mandy-managed Tassy bundles. |
| `websites/moondance-web/tests/test_managed_static_site_bundle.py` | Focused exact-closure, receipt, route, and
  safety proof for the managed Moondance Games package. |
| `config/canonical-test-exclusions.txt` | Explicit Python, i18n, theme, and isolated Performance workload exclusions from canonical `test`. |
| `config/canonical-test-retained-mixed.txt` | Functional tests retained despite incidental excluded-area setup. |

## Production package index

All Java paths below are relative to `src/main/java/com/moondance/talisman/app/`.

| Path | Ownership |
| --- | --- |
| `core/activity/` | Active-only user-operation presentation diagnostics; no receipt/history authority. |
| `core/observation/` | Opaque salted process-local semantic-operation correlation values. |
| `core/credentials/` | Environment-first resolution, exact secure-store adapters/guidance, safe status. |
| `core/bus/` | Explicit typed delivery policy, lifecycle/current-state transport, and bounded diagnostics. |
| `core/i18n/` | Locale selection, bundle/format fallback diagnostics, and glyph-aware UI fonts. |
| `core/help/` | Core/module Help catalogs, reviewed topic inventories, search/diagnostics, links, and window. |
| `core/log/` | Application logging and debug-console plumbing. |
| `core/settings/` | Settings dialogs, secure-credential guidance, and default-safe raster policy. |
| `core/talitalk/` | TaliTalk panels, per-window provider sessions, and text/image response history. |
| `adventure/` | Planning context, isolated workbench sessions, review, and semantic execution. |
| `database/assets/` | Named Asset Collections and typed semantic/media membership. |
| `database/adventure/` | World/Adventure aggregate and checked exact references. |
| `database/` | Database lifecycle, configuration, and database bus integration. |
| `database/campaignpopulation/` | Campaign population ingest, validation, approval, and queries. |
| `database/projectstorage/` | Project versions, content BLOBs, migration, and backup. |
| `database/randtable/` | Random-table domain, import, generation, and storage. |
| `database/region/` | Region persistence, geometry, resolution, LOD, and streaming. |
| `database/srd/` | Versioned SRD documents, exact-document extractors, definitions, import, and provenance. |
| `database/userassets/` | Asset sources, scanning, ingest, nodes, and persistence. |
| `dtdt/` | DTDT factories and UI-description integration. |
| `dtdt/builder/` | DTDT document-to-component construction and container ownership. |
| `dtdt/components/` | Reusable AppAware controls and product/debug components. |
| `dtdt/panels/` | Popup and stub panel hosts. |
| `mapeditor/` | Map domain, semantic raster/Feature Layers, geometry, snapshots, and tools. |
| `mapeditor/behavior/` | Bus-owned Map Editor mutations and workflow behavior. |
| `mapeditor/document/` | Versioned map document sessions, persistence, and update state. |
| `mapeditor/script/` | Script Manager package catalog/import, typed manifests, Python/Java runners, protocol codecs, and atomic execution. |
| `mapimport/` | External map readers, bounded raster decoding, and canonical preview documents. |
| `mapimport/earth/` | Reviewed numerical input, canonical height preparation, bounded exchange evidence and detached W1 context. |
| `services/` | Application service composition, registry, and probes. |
| `services/operation/` | Shared operation values and bounded lifecycle registry. |
| `services/observation/` | Passive bounded owner-private operation evidence and incident summaries. |
| `walkthrough/` | Versioned demo YAML, validation/playback, semantic target, cues, and HTTP client. |
| `services/app/` | Cross-feature application services. |
| `services/app/applicationserver/` | LAN or hosted-loopback `3002` host, hot typed page manifest,
  exact process
  identity, guarded lifecycle, bounded client sessions, path-free health/bootstrap/status inventory,
  strict lazy Critter adapter, snapshot-verified full Creature-page Object Factory rapid publication,
  authenticated Core Morph reads and checked Factory Body Form saves, plus the separate loopback Talisman
  Online Server with immutable database admission, bounded real Seasons catalog, root/Player access, and
  Player-safe Present streaming. |
| `services/app/relay/` | Outbound GM relay lifecycle, safe status, controls, and pane upload. |
| `services/performance/` | Synthetic laboratory safety, raster rebuild, and reporting-only contracts. |
| `tools/` | Developer entry points and the Workbench, including scripted walkthrough orchestration. |
| `tools/srdmonsterbatch/` | Critter role lanes, serialized writer, and read-only canonical facade for the
  five shared Application Server queries, with explicit hosted artifact-root relocation. |
| `ui/` | Application startup UI, shell behavior, menus, and shared rendering. |
| `ui/adventure/` | Standalone Worlds-to-Adventures product surface and compact workflows. |
| `ui/assets/` | User Asset browser/import workspace, preview, and native chooser UI. |
| `ui/controlgallery/` | Additive control-bar icon catalog, registry, and developer gallery. |
| `ui/fx/` | Shared daemon-owned JavaFX startup and asynchronous Swing/JavaFX thread dispatch. |
| `ui/image/` | Shared image clipboard abstractions and copy service. |
| `ui/macos/` | macOS-specific native UI bridges. |
| `ui/mapeditor/` | Map Editor panels, raster/Feature canvas, inspectors, state, and rendering. |
| `ui/mapeditor/viewer3d/` | Immutable 3D captures, terrain transforms/meshes, and saved views. |
| `ui/mapruntime/` | GM/player runtime map presentation and interaction. |
| `ui/theme/` | Typed persisted themes, including Forest, plus preview tokens and shared delegates. |
| `ui/theme/gallery/` | Forest registration showcase and deterministic offscreen evidence route. |
| `ui/windows/` | Product-window lifecycle and role management. |
| `ui/zoom/` | Shared precise-wheel accumulation and center/pointer-preserving 2D zoom math. |
| `utils/` | Small general-purpose collections/helpers. |

## Shared relay protocol index

All paths below are relative to
`moondance-protocol/src/main/java/com/moondance/talisman/relay/protocol/`.

| Path | Ownership |
| --- | --- |
| `RelayProtocol.java` | Protocol version and capability vocabulary. |
| `RelayMessage.java` | Versioned JSON control envelope. |
| `RelayBinaryFrameCodec.java` | Bounded binary Player-safe pane-frame envelope. |
| `PlayerPresentationPaneFrame.java` | Complete Player-safe 2D or 3D PNG value. |
| `PresentationPane.java` | Stable 2D/3D pane identity. |
| `EndpointDisplayMode.java` | Endpoint-owned 2D, 3D, or split display selection. |

## Standalone relay server index

All paths below are relative to `moondance-server/src/main/java/com/moondance/talisman/relay/`.

| Path | Ownership |
| --- | --- |
| `MoondanceServer.java` | Linux process entry point and safe configuration startup. |
| `MoondanceServerConfig.java` | Environment configuration validation and credential redaction. |
| `server/GmAuthenticator.java` | Constant-time GM bearer verification. |
| `server/RelayGameSessionRegistry.java` | In-memory session, listing, reconnect, and expiry authority. |
| `server/RelayEndpointTransportRegistry.java` | Hashed invitations/endpoints and latest pane authority. |
| `server/LatestEndpointNoticeQueue.java` | One-in-flight plus one-pending endpoint notice queue. |
| `server/MoondanceRelayApplication.java` | Public HTTP and authenticated GM/player WebSocket routes. |

## Public relay website index

All paths below are relative to `websites/moondance-web/`.

| Path | Ownership |
| --- | --- |
| `moondance.com/join/index.html` | Invitation redemption presentation; no endpoint credential access. |
| `moondance.com/player/index.html` | Authenticated 2D/3D/split Player-safe presentation shell. |
| `moondance.com/assets/player.mjs` | Endpoint session, WebSocket, mode, reconnect, and pane lifecycle. |
| `moondance.com/assets/player-core.mjs` | Pure protocol helpers and latest-only pane request queue. |
| `moondance.games/index.html` | Six-card launcher, same-tab shell entry, honest availability, and
  environment-specific administrative-editing status. |
| `moondance.games/assets/site.css` | Responsive launcher, admission-state, and per-game presentation. |
| `moondance.games/play/index.html` | Presentation-only full-viewport game frame with persistent local-origin home control. |
| `moondance.games/assets/play-core.mjs` | Exact allowlisted game title and frame-destination values. |
| `moondance.games/assets/play.mjs` | Same-tab shell projection and invalid-doorway fallback. |
| `moondance.games/assets/play.css` | Full-viewport frame and touch-safe Morris Roman `M` home presentation. |
| `deployment/Caddyfile.player-ui.snippet` | Clean static entry routing and document security headers. |
| `deployment/HOST-MAINTENANCE-BASELINE.md` | Audited Level 1 backup, reboot, preservation, and endpoint
  acceptance transaction. |
| `deployment/talisman-online-server.service` | Immutable private Java 25, systemd credential, loopback-only,
  read-only selected database, and Level 1 resource bounds. |
| `deployment/talisman-online-server.tmpfiles` | Reboot-stable root-only Online Server deployment lock
  directory and lock file. |
| `deployment/install-talisman-online-server-foundation.sh` | Exact package/runtime admission through one
  inode-stable root lock, unique transaction-owned staging, exact locked service identity, safe cleanup,
  credential/private directories, tmpfiles, and an inactive unit. |
| `deployment/migrate-talisman-online-server-foundation.sh` | One-time fail-closed service identity/path
  migration retaining credentials/runtime and preserving the old hosted release as non-active legacy data. |
| `deployment/install-talisman-online-server.sh` | Full Git/JAR/unit identity, shared-lock serialization,
  unique immutable staging, complete pre-start validation, health-gated activation, and durable predecessor
  recovery. |
| `deployment/rollback-talisman-online-server.sh` | Target-free predecessor swap with exact unit/drop-in,
  runtime, selector, manifest, embedded-JAR and metadata validation before start, plus fail-closed stopped
  recovery on ambiguity. |
| `deployment/publish-talisman-online-database.sh` | UI-independent one-way local backup, exact admission,
  capacity preflight, private transfer, and root-promoter invocation. |
| `deployment/install-talisman-online-database.sh` | Root-only immutable database release admission,
  expected-current selection, predecessor retention, service-health gate, and rollback. |
| `deployment/probe-talisman-online-server.py` | Root-local exact release/JAR/database protocol acceptance
  without printing credentials, invitations, cookies, or payload. |
| `deployment/talisman-online-operator.sh` | Local fixed-action SSH console requiring exact current
  release/database identities; no general remote shell. |
| `deployment/run-talisman-online-operator-action.sh` | Root-only status, validation, activation, restart, and
  application/database predecessor rollback while preserving boot-disabled state. |
| `deployment/test-talisman-online-operator.sh` | Private-mount Linux proof for all six Operator actions
  without changing the live service, application selector, or database selector. |
| `deployment/talisman-application-server.service` | Actual TAS/AppServices graph on hosted loopback `3002`,
  explicit outer-root-gate trust, externally configured Moonbeam delta key, canonical writable SQLite,
  boot-disabled lifecycle, and bounded memory. |
| `deployment/install-talisman-application-server.sh` | Immutable JAR/manifest-asset release installation,
  admitted database cloning or explicit database-retaining code release, health-gated selection, snapshot,
  and paired release/database rollback. |
| `deployment/publish-tassy-server-code.sh` | Clean current-Main Tassy server-code publisher that transfers
  the JAR and declared baseline pages while excluding database publication and preserving boot-disabled. |
| `deployment/publish-tassy-managed-handler.sh` | Generic Tassy-to-Moonbeam immutable component publisher;
  suppresses macOS sidecars and performs no Java restart or database publication. |
| `deployment/install-tassy-managed-handler.sh` | Root-side service-owned component staging, local-operator
  admission, exact identity check, private route proof, cleanup, and path-free terminal receipt. |
| `deployment/publish-moondance-static-site.sh` | Exact-source publisher for the bounded Moondance public
  document roots, with public HTML identity proof and no server, component, Caddy, or database mutation. |
| `deployment/install-moondance-static-site.sh` | Root-side validated static-tree promotion with bounded
  domain allowlisting, transactional restoration on failure, and no retained predecessor backup. |
| `deployment/test-moondance-static-site-publication.sh` | Focused offline contract proof for the public
  static publisher and installer safety boundaries. |
| `deployment/Caddyfile.talisman-application-server.snippet` | Root-session-gated TAS pages/components,
  service-key-gated Moonbeam delta route, and retained Online GM/Player fallback on `4220`. |
| `deployment/test-talisman-application-server-deployment.sh` | Focused hosted unit, installer, asset,
  outer-gate, rollback, route, and forbidden-public-surface contract. |
| `deployment/test-talisman-application-server-database-safety.sh` | Offline snapshot/restore fault
  injection for SQLite consistency, capacity refusal, corrupt backup rejection, and stopped recovery. |
| `deployment/receipts/moonbeam-inventory-2026-09-11.json` | Dated read-only Moonbeam server,
  component-selection, public-doorway, boot-policy, and separate-database-stream evidence. |
| `deployment/receipts/moondance-games-0.1.4-2026-09-11.json` | Exact managed-component and public-root
  receipt for the cross-host Slice-doorway correction, with explicit no-database boundaries. |
| `tests/player-core.test.mjs` | Focused protocol, token, redirect, reconnect, and queue proof. |
| `tests/games-launcher.test.mjs` | Focused five-destination, availability, copy, and company-link proof. |

Deployment verification:

The path below is relative to
`src/test/java/com/moondance/talisman/app/services/app/relay/`.

| Path | Ownership |
| --- | --- |
| `MoondanceRelayDeploymentE2ETest.java` | Opt-in production HTTPS/WSS relay lifecycle probe. |

The Online Server deployment lint path is relative to
`src/test/java/com/moondance/talisman/app/services/app/applicationserver/`.

| Path | Ownership |
| --- | --- |
| `TalismanOnlineDeploymentArtifactLintTest.java` | Focused static proof for complete deployment digests,
  canonical lock ordering, transaction-owned staging/cleanup, exact service/database identity, one-way
  publication, foundation migration, and pre-start rollback/recovery validation. |

## Resource and design index

| Path | Contents |
| --- | --- |
| `src/main/resources/app/dtdt/UIStructure.dtdt.yaml` | Default host/editor sample; not the product shell. |
| `src/main/resources/app/scripts/` | Bundled indexed Python Scripts plus the separate built-in `java/` manifest branch. |
| `src/main/resources/app/dtdt/Product-Windows.dtdt.yaml` | Current product-window declarations. |
| `src/main/resources/app/i18n/` | UTF-8 keyed application-interface source locale resources. |
| `src/main/resources/app/help/` | Versioned locale indexes, stable topic graphs, Markdown, and truthful fallback. |
| `src/main/resources/app/dtdt/` | Declarative UI structures with optional `titleKey` and `helpId` metadata. |
| `src/main/resources/app/player-screen/` | Local Player modes and bounded wake/reconnect UI. |
| `src/main/resources/app/random-encounters/` | SRD-safe ecology and wandering tables. |
| `src/main/resources/app/adventure/` | Reloadable Adventure prompt, YAML contract, fixture, and rubric. |
| `src/main/resources/app/dtdt/components/app/` | Product component DTDT definitions. |
| `src/main/resources/app/dtdt/components/debug/` | Debug and monitor DTDT definitions. |
| `src/main/resources/app/icons/controls/` | Semantic control icon catalog and production PNG targets. |
| `scripts/generate_cursor_mode_icons.py` | Deterministic Freeform/Grid masters from the approved pointer. |
| `scripts/generate_layout_mode_icons.py` | Deterministic matching standalone 2D/3D layout-control masters. |
| `version.properties` | Canonical raw version/build inputs, creation date, and release notes. |
| `src/main/resources/workbench-version.properties` | Forward-only Workbench version and current changes shown by About Workbench. |
| `design/README.md` | Active design entry point and priorities. |
| `design/Active Designs/README.md` | Active Designs permanent-owner/topic folder index. |
| `design/Active Designs/Talisman Architecture/` | Operations Atlas design: current task contracts, evidence
  receipts, runtime truth, implementation plan, and the bounded Moondance Project Kit initiative. |
| `design/Active Designs/Talisman Architecture/MOONDANCE-PROJECT-KIT.md` | Controlling design for a
  versioned, physically vendored shared standards/template/validator kit, deliberate safe upgrades, and
  staged bootstrap and adoption across independent Moondance game repositories. |
| `design/Active Designs/Context Management/` | Context Management framework design and focused test plan. |
| `design/Active Designs/Context Management/SHELF-CONTEXT-FRAMEWORK.md` | Proposed cross-application
  Java-authoritative/browser-client architecture, CM-01 through CM-09 audit, Easy Tale mockup, adoption
  seam, and review-gated implementation bodies. |
| `design/Active Designs/Context Management/SHELF-CONTEXT-CONTRACT-V1.md` | Proposed runtime-neutral Shelf
  Context references, preview/result/cancel envelopes, privacy/lifecycle rules, and shared Java/JavaScript
  conformance vectors; not a current transport or production authority. |
| `design/Active Designs/Context Management/GM-CONTROL-SCREEN-ADAPTER-PROFILE.md` | Selected GCS-01
  manual text-only same-schema Copy/Paste profile for admitted Place/object/Morph scalars, canonical
  semantic-base comparison, stale-owner rejection, and non-applying typed-delta review. The Java GM
  provider, strict codec, direct service, and shared fixture are current; browser/Application Server and
  mutation authority remain absent. |
| `design/Active Designs/Moondance Web/TALISMAN-ONLINE-SERVER.md` | Controlling serial Talisman Online
  Server implementation/operations programme, permanent Moondance Web ownership, one-way immutable SQLite
  publication boundary, and explicit private-operation/public-release gates. |
| `design/Active Designs/Moondance Web/MOONBEAM-ADMINISTRATIVE-EDITING.md` | Public-view and one-root-session
  shared-mutation proof, including hosted enforcement, local Tassy behavior, and browser-local exceptions. |
| `design/Active Designs/External Acquisition/` | Lawful 2D/3D provider plan and living test plan. |
| `design/Active Designs/Adventure Authoring/` | Adventure Authoring living test plan. |
| `design/Active Designs/Adventure Authoring/CHARACTER-SHEETS.md` | Character pane boundary. |
| `design/Active Designs/Authoring Screen/STACKED-PLACES.md` | Approved stack review; no implementation. |
| `design/Active Designs/Object Sing & Dance Factory__/` | Object Factory system/UI design, Cet and
  Dwarf War reference audit, visual screen plates, and living test plan; landed baselines run through
  OF-12A and OF-P01A/B, with the nested-Shelf Body Form compendium workbench and OF-SHADER-CAGE-50
  non-destructive returned-image registration active. |
| `design/Active Designs/Object Sing & Dance Factory__/MORPH-DESIGN.md` | Fixed complete source-authoritative
  Core Morph objects, stable Point/Joint graph, exactly Mapping
  and Resting, reversible two-endpoint motion pairs, copy-then-modify without inheritance, isolated Adult
  Dragon evidence, catalog editor Shelf-conversion seam, and implemented database materialization/read
  integration. |
| `design/Active Designs/Object Sing & Dance Factory__/SIGN-LANGUAGE-MORPH-CAPABILITY.md` | Parked root Morph
  sign-language capability contract: native questions and proposals, structural compatibility, language
  identity, foreign-notation ingestion, deterministic source materialization, and a bounded first slice. |
| `design/Active Designs/Object Sing & Dance Factory__/workshops/core-morph-catalog/` | Verified twelve-form
  complete-object interchange, reorderable owner `morph-puppeteer-workbench.dtdt.json`, artificial-space
  `morph-puppeteer-influence.dtdt.json`, stacked-Shelf 3D review workbench, combined Point hierarchy, focused
  validators, and deterministic production-resource adapter. |
| `design/Active Designs/Object Sing & Dance Factory__/workshops/ancient-dragon-morph-workshop-3d.html` |
  Standalone source-generated Ancient Dragon 3D authoring surface with direct form, stance, head, wing,
  digit, motion, and camera controls. |
| `design/Active Designs/Object Sing & Dance Factory__/workshops/build-ancient-dragon-workshop.mjs` |
  Deterministic bridge from the canonical Ancient Dragon source functions to the standalone workshop's
  immutable embedded data. |
| `design/Active Designs/__Make Java/` | Native Java migration parity contract and cumulative focused
  test plan for the permanent `__Make Java` owner. |
| `design/Active Designs/Talisman Editor Integration/` | Local editor-session design and test plan. |
| `design/Active Designs/Talisman Forest__/` | Forest ledger, shared presentation, gallery and tests. |
| `development-atlas/` | Authoritative current ownership and behavior routes. |
| `development-atlas/operations-contract.md` | Operations Atlas record semantics and TA-01 decision boundary. |
| `development-atlas/operations/` | Version-one schema and synthetic offline conformance examples; not an operational store. |
| `development-atlas/operations/CURRENT-TRUTH-STORE.md` | TA-02 JSON receipt/pointer architecture, proof separation, update procedure, and limitations. |
| `development-atlas/operations/current-truth-resolver.mjs` | Deterministic read-only current-pointer resolver; no mutation or runtime inspection. |
| `development-atlas/operations/query-current.mjs` | Minimal command-line query for one Operations Atlas node. |
| `development-atlas/operations/store/` | Append-only immutable records plus compact mutable current pointers. |
| `development-atlas/service-architecture.md` | Current service, command, operation, and scriptability map. |
| `development-atlas/validation.md` | Deterministic local reference validation. |
| `design/Active Designs/Easy Tale/` | Easy Tale plan, specification, studies, and tests. |
| `design/Active Designs/Talisman Shelf__/` | Canonical Shelf, Shelf Label, and Shelf Box
  successor vocabulary, current v1/v2 contracts, browser-first parallel-shell design, ordered migration
  bodies, and living test plan. |
| `design/Active Designs/Talisman Shelf__/DTDT-V3-SHELF-DESIGN.md` | Sole future DTDT language:
  Shelf-owned sorting/open-box policy, pinned leading Close, allocation-axis expansion glyphs, nested Shelf
  inside a Shelf Box, v1/v2 removal, and adoption order. |
| `design/Active Designs/Talisman Shelf__/SHELF-CONTRACT-V1.md` | Landed behavioral input whose
  v1 names and declaration shape remain frozen historical compatibility and are superseded for future v3
  adoption. |
| `design/Active Designs/Talisman Shelf__/SHELF-CONTRACT-V2.md` | Additive browser/DTDT successor
  for explicit group presentation policies, content, child groups, memory scope, and fail-closed version
  selection; current Object Factory browser definitions use it. |
| `design/Active Designs/GM Control Screen/UNIFIED-CONTROL-SCREEN.md` | Governing one-screen browser
  product design: one page/route/shell/state owner, Shelf-first Forest composition, opaque registered
  component contracts, retained Control views, selection-only Place Objects, compact stable-ID context,
  manual Tally Talk copy/paste preview, current runtime authority, and ordered successors. |
| `design/Active Designs/GM Control Screen/GCS-01-IMPLEMENTATION-BRIEF.md` | Exact current-Main browser
  handoff to `Make JavaScript__`, including one-shell authoring mode, Shelf/Forest composition, opaque
  component hosting, private admission, retained renderers, Place Objects shielding, landed Context-profile
  adoption, non-applying Tally Talk preview, exclusions, and focused gates. |
| `design/Active Designs/GM Control Screen/TEST-PLAN.md` | Living native GM checks plus the planned GCS-01
  browser and GCS-02 Context acceptance boundaries. |
| `design/Active Designs/Talisman Shelf__/shelf-contract-v1.fixtures.json` | Synthetic shared
  cross-runtime vectors for automatic sorting/reorder, replacement, takeover, overflow, persistence, and
  focus outcomes. |
| `src/test/js/shelf-contract-v1.test.mjs` | Fixture-integrity proof for canonical Shelf vocabulary,
  historical definition compatibility, atomic state outcomes, and evidence classification. |
| `src/test/js/shelf-contract-v2.test.mjs` | Explicit successor identity, Object Factory definition,
  cross-version rejection, and richer group-policy contradiction proof. |
| `src/main/resources/app/talisman-shelf/shelf-presentation-memory.js` | Shelf-owned compatible v1/v2
  durable presentation envelopes, exact composed-state declaration, no-write v1 migration, and neutral
  complete read/optimistic-write store boundaries. |
| `src/test/js/shelf-presentation-memory.test.mjs` | Shelf-owned key, v1 state, v2 selected display plus
  component dividers/disclosures, whole migration/restore/save, rejection, and transport exclusion proof. |
| `src/main/java/com/moondance/talisman/app/services/app/applicationserver/GmControlApplicationServerGateway.java` | Admission-bound GM snapshot,
  Context, and opaque Shelf v1/v2 envelope gateway; atomically replaced bounded state-root receipt binds
  browser keys to the admitted principal and survives process reconstruction. |
| `src/test/java/com/moondance/talisman/app/services/app/applicationserver/GmControlApplicationServerGatewayTest.java` | Restart-durable opaque v2
  envelope, storage-revision conflict, and state-definition key-isolation proof. |

## High-signal file index

| Path | Why to start here |
| --- | --- |
| `database/userassets/UserAssetsStoreIdentity.java` | Managed-store marker and portable root locator. |
| `database/userassets/UserAssetsStoreRebindPreview.java` | Full-store validation and stale preview token. |
| `database/srd/CreatedThingsResetService.java` | Checked selective non-Place Created Things preview/reset. |
| `database/srd/CharacterSheetService.java` | Type-derived reviewed Character Stats/Age snapshots and deterministic rerolls. |
| `database/srd/CharacterSheetDocument.java` | Bounded lowercase Character sheet/Quick Import codec. |
| `database/srd/GameObjectService.java` | Type-linked Character/Creature sheet snapshots. |
| `database/srd/SrdDocumentExtractionRegistry.java` | Exact ruleset/document table-extraction routing. |
| `database/srd/SrdLibraryService.java` | Reviewed preview, stale guard, import, and provenance. |
| `database/adventure/AdventureRepository.java` | Checked aggregate, History, and Relationship persistence. |
| `database/easytale/EasyTaleRepository.java` | Easy Tale schema, snapshots, revisions, raw corpus,
  evidence, and transactions. |
| `database/adventure/AdventureApplicationService.java` | Adventure CRUD, reviewed work, History, and Relationships. |
| `database/adventure/AdventureReviewedPlanCoordinator.java` | Reviewed session/composite lifecycle. |
| `database/adventure/AdventureEncounterPreparationCoordinator.java` | Bounded encounter preparation owner. |
| `adventure/AdventureContextPackage.java` | Bounded World/ancestry/Character context and entries. |
| `adventure/AdventureAiProvider.java` | Exact Codex, OpenAI, OpenAI Image, and Ollama choices. |
| `adventure/AdventurePlanningWorkbenchProvider.java` | Injected capability and inspected-turn seam. |
| `adventure/AdventurePlanningWorkbenchSession.java` | Isolated conversation, inspector, and candidate. |
| `adventure/TalitalkAdventurePlanningProvider.java` | TaliTalk provider-session Adventure adapter catalog. |
| `core/talitalk/OpenAiResponsesClient.java` | Bounded official OpenAI Responses text client. |
| `core/talitalk/TalitalkPlanningProviderSession.java` | Isolated OpenAI/Image/Ollama config and transport. |
| `adventure/AdventurePlanCodec.java` | Bounded YAML/Markdown grounding and semantic allowlist. |
| `adventure/AdventurePlanSession.java` | Internal exact artifacts and one-at-a-time step state. |
| `adventure/CanonicalAdventureActionExecutor.java` | Canonical Character creation and Adventure linking. |
| `ui/adventure/AdventureAuthoringPanel.java` | Adventure aggregate and guarded review projection. |
| `database/adventure/AdventureCharacterPresentationReader.java` | Exact read-only CS-03 image consumer. |
| `ui/adventure/AdventureCharacterPane.java` | Character Focus Bar and CS-02–CS-05 focused workspaces. |
| `ui/adventure/AdventurePresentationSelection.java` | Content-free resident selection receipt and
readiness. |
| `ui/adventure/AdventurePlanningWorkbenchLauncher.java` | Exact named modeless workbench windows. |
| `ui/adventure/AdventurePlanningWorkbenchPanel.java` | Provider/context dialogue and Accept Plan UI. |
| `ui/adventure/AdventureArtifactViewer.java` | Exact modeless reviewed-draft/canonical result viewers. |
| `ui/adventure/AdventureCharacterMediaGateway.java` | Exact Assets media-session adapter. |
| `ui/adventure/AdventureReviewLayoutStore.java` | Persisted and clamped review-region dividers. |
| `ui/assets/CreateCharacterDialog.java` | Focus-committed Guided Builder and Quick Import. |
| `src/main/java/com/moondance/talisman/app/TalismanApp.java` | Desktop application entry point. |
| `src/main/java/com/moondance/talisman/app/TalismanVersion.java` | Raw build and derived display release. |
| `TalismanBusTopicPolicies.java` | Exact trusted APP, DTDT, and USER topic policy catalog. |
| `mapeditor/ActiveRasterExtent.java` | Grid-owned inset resolved to exact Script raster bounds. |
| `src/main/java/com/moondance/talisman/app/services/AppServices.java` | Service composition root; owns the
  application-lifetime Context, action, and proposal services, CM-05 Assets, CM-06 Adventure/Battle, and
  CM-07 Object Factory providers, and shutdown. |
| `services/app/AdventureBattleContextProvider.java` | Pure bounded PROJECT-classified Adventure, World,
  Place-ancestry, Battle, turn, participant, condition, selection, focus, and exact-target projection. |
| `services/app/AssetsManagerContextProvider.java` | Pure bounded PROJECT-classified Created Things
  Character reference-to-hierarchy projection. |
| `ui/assets/AssetsManagerContextPilot.java` | EDT submit-time Character role capture and stale/close-safe
  reusable Ask AI/Open in TaliTalk plus guarded proposal screen adapter. |
| `services/app/ObjectFactoryContextProvider.java` | Pure bounded PROJECT-classified Factory scalar-to-
  hierarchy projection. |
| `services/app/easytale/EasyTaleAuthorityContract.java` | Body 1 epoch-millisecond, temporal-order,
  calendar-stamp, presentation-stamp, immediate-feedback, zero-duration Focus, and no-playback contract. |
| `services/app/easytale/EasyTaleService.java` | Toolkit-neutral checked Easy Tale persistence boundary. |
| `services/app/easytale/EasyTaleImportService.java` | Immutable source extraction/admission,
  cancellation, exact-calendar validation, idempotency, and explicit candidate review. |
| `services/app/easytale/EasyTaleCorpusService.java` | Resumable raw literary-corpus admission, exact
  evidence spans, conservative narrative derivation, and possible-duplicate/contradiction retention. |
| `services/app/easytale/EasyTaleWritingCorpusCommand.java` | Manifest-pinned local DOC/DOCX-and-image
  Seasons corpus adapter with complete admitted/excluded accounting and honest binary handling. |
| `services/app/easytale/EasyTaleSeasonsDocumentMaterializer.java` | Exact admitted-text projection into
  ten distinct source editions, anchors, derivatives, and a Winter/Spring/Summer manuscript hierarchy. |
| `services/app/easytale/EasyTaleSeasonsMaterializationCommand.java` | Reviewed local command for one
  checked, idempotent canonical Seasons document materialization transaction and immutable receipt. |
| `services/app/easytale/EasyTaleSeasonsSemanticMaterializer.java` | Deterministic source-addressed
  character, appearance, unresolved-time event, timeline, place, relationship-candidate, and review
  projection over the exact Seasons editions. |
| `services/app/easytale/EasyTaleSeasonsSemanticMaterializationCommand.java` | Reviewed local command for
  one checked, idempotent canonical Seasons semantic transaction and immutable receipt. |
| `services/app/easytale/EasyTaleCorpusBounds.java` | Shared exact byte readers for local corpus adapters. |
| `services/app/easytale/EasyTaleCharacterService.java` | Source-backed literary identities, reviewed
  detail, current-calendar timelines, optional non-destructive game links, and review queues. |
| `services/app/easytale/EasyTaleManuscriptService.java` | Versioned manuscript hierarchy, exact links,
  local history, explicit save, and interrupted-save recovery authority. |
| `services/app/easytale/EasyTaleRevisionService.java` | Provider-free exact-scope dictation/revision
  admission, provenance, name review, and full/selective manuscript acceptance authority. |
| `services/app/easytale/EasyTaleTimelineService.java` | Canonical comparative-lane projection, semantic
  scales, synchronized mark selection, Seasons coverage, and exact-revision named-set authority. |
| `services/app/easytale/EasyTaleTimelineGateway.java` | UI/headless seam for immutable comparative
  projections and checked named timeline sets. |
| `services/app/easytale/EasyTaleHistoryService.java` | Canonical recorded-history projection,
  continuity inspection, and explicit cited propose/review/commit correction authority. |
| `services/app/easytale/EasyTaleHistoryGateway.java` | UI/headless seam for immutable history workspaces
  and exact-revision correction commands. |
| `services/app/easytale/EasyTaleBrowserReadContract.java` | Immutable bounded browser-safe workspace,
  source-page, manuscript-page, complete-story, stamp, provenance, review-authority, and error contract. |
| `services/app/easytale/EasyTaleBrowserReadGateway.java` | Pure owner-local query seam for future
  authenticated TAS Easy Tale GET adapters. |
| `services/app/easytale/EasyTaleBrowserReadService.java` | Deterministic canonical
  work/corpus/manuscript/semantic projection over the sole repository, with no transport or mutation. |
| `services/app/easytale/EasyTaleBrowserCommandContract.java` | Immutable bounded ET-WEB-03 exact-save,
  reviewed-recovery, revision, replay-identity, terminal-result, and safe-error vocabulary. |
| `services/app/easytale/EasyTaleBrowserCommandGateway.java` | Owner-local manuscript save/recovery seam for
  a future authenticated TAS POST adapter. |
| `services/app/easytale/EasyTaleBrowserCommandService.java` | Caller-thread adapter to the sole Body 8
  manuscript service; no transport, timer, provider, replay cache, or second persistence route. |
| `services/app/easytale/WorldCalendarService.java` | Exact Winter calendar definition, reversible
  epoch-millisecond projection, stale-stamp guard, and source-wording parser. |
| `ui/easytale/EasyTaleFocusShellPanel.java` | First-class DTDT Focus shell and stale-safe client lifecycle. |
| `ui/easytale/EasyTaleTimelinePanel.java` | Localized aligned-lane timeline, scale/lane controls,
  accessible marks, crosshair state, and evidence inspector. |
| `ui/easytale/EasyTaleHistoryPanel.java` | Localized History/Review list-detail and explicit correction
  client with immediate feedback and exact lifecycle/revision installation. |
| `ui/easytale/EasyTaleReadPanel.java` | Localized list/detail projection for real Seasons manuscript,
  character, timeline, source, history, and review content. |
| `database/easytale/EasyTaleRepository.java` | Sole Easy Tale SQL/transaction owner for works,
  immutable editions, manuscript snapshots, stable anchors, editable derivatives, review candidates, and
  canonical document/semantic receipts, normalized named timeline sets, cited recorded-history revisions,
  and coherent complete-story reads. |
| `ui/assets/ObjectFactoryContextPilot.java` | EDT submit-time Factory target revalidation, passive Monitor,
  reusable Ask AI/Open in TaliTalk, and guarded proposal adapter. |
| `services/operation/SemanticOperation.java` | Bounded operation identity, lifecycle, and result values. |
| `services/operation/SemanticOperationRegistry.java` | Owner-authorized registry/query contract. |
| `services/operation/BoundedSemanticOperationRegistry.java` | Bounded process-local registry. |
| `core/observation/SemanticOperationSafeJoin.java` | Salted process-local correlation token. |
| `services/observation/SemanticOperationObservation.java` | Bounded OWNER_PRIVATE lifecycle evidence. |
| `services/observation/SemanticOperationObservationProjector.java` | Salted bounded evidence projector. |
| `services/observation/SemanticOperationIncidentHub.java` | Bounded active/terminal incident summaries. |
| `services/observation/ObservingSemanticOperationRegistry.java` | Passive delegate-first decorator. |
| `services/app/GmPreparedEncounterAdmissionService.java` | Owner-private prepared-encounter
  admission operation. |
| `services/app/ArenaEncounterAdmission.java` | Immutable checked Arena graph admission request and result. |
| `core/bus/BusDeliveryPolicy.java` | Delivery kind, lifecycle, state stamp, and TTL. |
| `core/bus/BusTopicPolicy.java` | Trusted topic owner, privacy, and policy contract. |
| `core/bus/BusTopicPolicyResolver.java` | Trusted mixed-payload policy discriminator. |
| `core/bus/BusSubscriptionOptions.java` | Exact subscriber lifecycle and retained-state request. |
| `core/bus/BusDeliveryDiagnostic.java` | Bounded metadata-only transport evidence. |
| `core/bus/BusDeliveryAttemptContext.java` | Stack-safe optional join for one recipient attempt. |
| `core/bus/BusObserver.java` | Safe diagnostic-record observer contract. |
| `core/bus/BusService.java` | Policy-enforced inline/RESOURCE delivery and bounded current state. |
| `core/log/DebugConsole.java` | Bounded redacted delivery-activity projection. |
| `dtdt/components/BusMonitorScopeState.java` | Bounded redacted bus-monitor model. |
| `dtdt/components/BusMonitorEventTableView.java` | Safe diagnostic table projection. |
| `dtdt/components/BusMonitorEventDetailView.java` | Safe diagnostic detail projection. |
| `database/DataBaseBusService.java` | Bounded async safe-diagnostic persistence adapter. |
| `core/activity/ActivityMonitor.java` | Active-only presentation-neutral operation diagnostics. |
| `walkthrough/WalkthroughTarget.java` | Narrow correlated walkthrough target contract. |
| `services/probe/ProductDataStateCaptureRegistry.java` | Bounded asynchronous product-state query fan-out. |
| `core/settings/WorkingRasterDimensionPolicy.java` | Released 1536 policy and reviewed preset scaling. |
| `services/performance/PerformanceLaboratoryService.java` | Synthetic lab lifecycle and reset. |
| `services/performance/PerformanceLaboratoryManifest.java` | Exact lab identity and digest. |
| `services/performance/PerformanceLaboratoryPaths.java` | Private paths and live-DB rejection. |
| `services/performance/PerformanceWorkingRasterRebuildService.java` | Guarded lab raster rebuild. |
| `services/performance/PerformanceAnalysisReport.java` | Typed supplied analysis evidence schema. |
| `services/performance/PerformanceAnalysisReportService.java` | Bounded atomic Markdown report owner. |
| `services/performance/PerformanceAnalysisWorkloadCatalog.java` | Closed reviewed workload routes. |
| `services/performance/PerformanceAnalysisRunManifest.java` | Exact authorized run identity. |
| `services/performance/PerformanceAnalysisRunLifecycle.java` | Guarded persisted run state. |
| `services/performance/PerformanceAnalysisRunStore.java` | Atomic private run JSON owner. |
| `services/performance/PerformanceAnalysisStatistics.java` | Primary repetition statistics. |
| `services/performance/PerformanceAnalysisExecutor.java` | Owned isolated 4 GiB workload runner. |
| `services/performance/PerformanceAnalysisManualRunService.java` | Exact manual synthetic run author. |
| `services/performance/PerformanceAnalysisMeasurementStore.java` | Bounded atomic run samples. |
| `services/performance/PerformanceAnalysisReportSynthesizer.java` | Terminal focused-run report adapter. |
| `services/performance/PerformanceRasterExperimentCatalog.java` | Closed 4 GiB raster-role matrix. |
| `services/performance/PerformanceRasterExperimentEvidence.java` | Objective role quality/viability evidence. |
| `services/performance/PerformanceJfrProfileContract.java` | Owned bounded sampled-JFR plan/analyzer. |
| `services/performance/PerformanceSubsystemCounters.java` | Checked dormant run counters. |
| `services/performance/PerformanceScriptedProfileContract.java` | Pinned walkthrough profile binding/evidence. |
| `services/performance/PerformanceScriptedProfileArtifact.java` | Checked scripted result/checkpoint owner. |
| `services/performance/PerformanceAnalysisBaselineService.java` | Supervised baseline history/comparison owner. |
| `services/performance/PerformanceAnalysisRegressionPolicy.java` | Reviewed materiality/noise classifier. |
| `services/performance/PerformanceAnalysisThresholdCatalog.java` | Exact workload/metric/unit rules. |
| `services/performance/PerformanceAnalysisTriageService.java` | Stable bounded finding nomination. |
| `services/performance/PerformanceAnalysisFindingReviewService.java` | Append-only supervised finding decisions. |
| `services/performance/PerformanceAnalysisFixVerificationService.java` | Exact before/after fixed-proof gate. |
| `tools/MapPerformanceAnalysisReportPanel.java` | Manual run/report/baseline review controls. |
| `services/app/ArenaObjectDefinition.java` | Campaign-wide named Arena Object repository definition. |
| `services/app/ArenaObjectCreatureImport.java` | Immutable exact Created Thing import snapshot. |
| `services/app/ArenaObjectSourceUpdate.java` | Exact checked repository definition/source refresh pair. |
| `services/app/GmRuntimePlayLayer.java` | Stable synthetic per-Place Control Play overlay identity. |
| `services/app/ArenaCombatMovement.java` | Exact immutable runtime move guards, result, and structured
  local failure truth. |
| `services/app/GmCombatMoveOperationService.java` | App-scoped UI/headless
  `gm-control:move-current-combatant:v1` authority. |
| `services/app/GmStagedPresentationOperationService.java` | App-scoped UI/headless
  `gm-control:present-staged-map:v1` authority. |
| `services/app/GmStagedPresentationPreparer.java` | Worker-only Player-safe imagery/terrain preparation
  with exact read-only User Assets content recheck. |
| `services/app/combat/CombatRuntimeSessionService.java` | GM encounter revision, exact checked activation
  Move spent truth, and tactical visibility owner. |
| `services/app/combat/CombatEffectCatalog.java` | Validated data-driven Talisman Basic effect definitions. |
| `services/app/combat/TalismanBasicCombatRules.java` | Pure initiative ordering and ten-segment scheduler. |
| `services/app/combat/CombatGradientField.java` | Immutable exact-Place GM terrain-gradient field. |
| `services/app/combat/TalismanBasicMovementCost.java` | Pure physical-distance and uphill combat movement cost. |
| `services/app/ArenaObjectPresentationPayload.java` | Durable canonical mesh/Icon/diffuse-texture payload with one-time legacy STL rewrite and retained legacy Silhouette. |
| `services/app/ArenaObjectModelTexture.java` | Bounded private-free ARGB diffuse texture copied into Arena runtime definitions. |
| `services/app/ArenaObjectStlParser.java` | Bounded toolkit-independent binary/ASCII STL admission. |
| `services/app/Model3DImportService.java` | Worker-only bounded STL/OBJ admission to canonical indexed Arena3D meshes. |
| `services/app/UnityFsBundleArchive.java` | Bounded reviewed UnityFS block/node container reader. |
| `services/app/UnityFsLzmaDecoder.java` | Pure-Java bounded raw-LZMA block decoder. |
| `services/app/UnitySerializedFile.java` | Embedded-type-tree serialized-object reader. |
| `services/app/UnityFsBundleConverter.java` | Reviewed UnityFS package converter. |
| `services/app/ArenaObjectObjParser.java` | Bounded OBJ `v`/`vt`/`vn` parser with seam-safe face admission and ear clipping. |
| `services/app/ObjMeshSource.java` | Immutable bounded OBJ source geometry between parsing and canonical seam normalization. |
| `services/app/Model3DDisplayFitter.java` | Pure footprint-envelope scale policy independent of source-model units. |
| `services/app/CanonicalModel3DObjWriter.java` | Deterministic canonical OBJ derivative writer for Viewer/Assets handoff. |
| `services/app/CanonicalModel3DStlWriter.java` | Geometry-only canonical STL writer. |
| `services/app/factory/FactoryIndexedMesh.java` | Validated Factory indexed-mesh authority. |
| `services/app/factory/ObjectFactoryImportedModelStagingService.java` | OF-P01A checked path-free
  STL/OBJ-family staging, deterministic conversion, metrics, artifacts, and unsigned receipt authority. |
| `services/app/factory/ObjectFactoryImportedModelPackage.java` | OF-P01B exact reviewed-import
  revalidation and immutable source/request/derivative/receipt Native Package profile. |
| `services/app/factory/FactoryModelCandidate.java` | Shared Factory mesh and UV candidate. |
| `services/app/factory/FactoryPhysicalFaceUv.java` | Shared physical-repeat UV face contract. |
| `services/app/factory/ObjectFactoryNativeFormRecipe.java` | OF-01 Native Form recipe contract. |
| `services/app/factory/ObjectFactoryNativeFormCompiler.java` | Pure deterministic OF-01 compiler. |
| `services/app/factory/ObjectFactoryNativeFormCandidate.java` | Immutable compiled Native Form result. |
| `services/app/factory/ObjectFactoryRig.java` | OF-02 Rig, binding, profile, site, and limit authority. |
| `services/app/factory/ObjectFactoryPoseSnapshot.java` | Complete canonical parent-local Pose record. |
| `services/app/factory/ObjectFactoryForwardKinematics.java` | Pure parent-first FK and posed derivative. |
| `services/app/factory/ObjectFactoryPoseEvaluation.java` | Immutable FK frames, mesh, and OBJ result. |
| `services/app/factory/ObjectFactorySkeletonPresentation.java` | Toolkit-neutral joints, bones, and sites. |
| `services/app/factory/ObjectFactoryBodyFormSession.java` | OF-03 transient typed edit/history authority. |
| `services/app/factory/ObjectFactoryReferenceImage.java` | Exact source and orthographic registration. |
| `services/app/factory/ObjectFactoryAppearanceSession.java` | OF-04 UV/projection/evidence authority. |
| `services/app/factory/ObjectFactoryAppearanceGenerationSession.java` | OF-10 reviewed request,
  untrusted-result validation, preview, and exact-revision acceptance authority. |
| `services/app/factory/ObjectFactoryImageControlMetadataPackage.java` | OF-GENMETA-11A strict bounded
  canonical Creature/role Body Form, stance, camera, semantic-control, and envelope package. |
| `services/app/factory/ObjectFactorySilhouetteGuidePackage.java` | OF-SILHOUETTE-GUIDE-30 strict bounded
  exact-view Mapping PNG, Body Form identity, canonical hash, and provider-first-image package. |
| `services/app/factory/ObjectFactoryNativePackage.java` | OF-05A/OF-08A/OF-08B/OF-P01B Native Package
  graph and profile dispatch. |
| `services/app/factory/BodyFormCaptureIntent.java` | Bounded complete semantic Body Form graph plus exact
  owner-session, Object, revision, Save/Save-As, idempotency, and correlation request. |
| `services/app/factory/FactoryBodyFormCaptureGateway.java` | Factory-owned authoritative edit-session to
  immutable Java-compiled Native Package transfer boundary. |
| `services/app/factory/ObjectFactoryContextCapture.java` | CM-07 bounded immutable Factory scalars. |
| `services/app/factory/ObjectFactoryTwoSegmentIk.java` | OF-06 pure analytic arm IK. |
| `services/app/factory/ObjectFactoryPoseSession.java` | OF-06 transient Pose/history/save-intent owner. |
| `services/app/factory/ObjectFactoryPoseObservationDeck.java` | Bounded six-role Critter observation DTO. |
| `services/app/factory/ObjectFactoryPoseMatcher.java` | Deterministic deck-to-Pose candidate matcher. |
| `services/app/factory/ObjectFactoryPoseField.java` | OF-07 deterministic complete-Pose Field compiler. |
| `services/app/factory/ObjectFactoryPerformancePath.java` | OF-08A canonical 60-tick Path authority. |
| `services/app/factory/ObjectFactorySolvedClip.java` | OF-08A explicit full-TRS bake contract. |
| `services/app/factory/ObjectFactoryPuppeteerSession.java` | OF-07/OF-08A transient authoring authority. |
| `services/app/factory/ObjectFactoryTemplate.java` | OF-09A immutable Template family data. |
| `services/app/factory/ObjectFactoryTemplateEngine.java` | OF-09A pure Form/Rig/Atlas compiler. |
| `services/app/factory/ObjectFactoryCreatureTemplateLibrary.java` | OF-09B immutable quadruped and
  `dragon.basic` family content. |
| `services/app/factory/ObjectFactoryBehavior.java` | OF-08B immutable exact-Rig Behavior contract. |
| `services/app/factory/ObjectFactoryBehaviorComposer.java` | OF-08B pure ordered Layer composer. |
| `services/app/factory/ObjectFactoryBipedBehaviorLibrary.java` | Bounded step/wave/dance fixtures. |
| `services/app/factory/ObjectFactoryRuntimePublication.java` | OF-11 exact immutable runtime publication,
  bounded invocation, and articulated outcome authority. |
| `services/app/factory/ObjectFactoryRuntimeConsumers.java` | OF-11 read-only Viewer, GM, Player, and
  Scripted Walkthrough delivery adapters. |
| `services/app/factory/ObjectFactoryLizardMappingBodyType.java` | OF-12A reusable fixed-graph Lizard
  mapping family, bounded proportions, mapping/Standing poses, and stable Form/Atlas identities. |
| `services/app/factory/ObjectFactorySixViewMapping.java` | OF-12A creature-owned independent six-face
  landmark and detached-geometry revision authority. |
| `services/app/factory/ObjectFactorySixViewTextureBaker.java` | OF-12A deterministic local fixed-Atlas
  bake, exact settled-deck/current-Front guard, and per-texel source/evidence authority. |
| `src/main/resources/app/critter-image-creation/six-view-texture-mapping.js` | OF-UI-01 declarative
  Morph-owned vertical Body Form selector/selected-Form editor, left contextual Proportions drawer with
  camera-preserving edits, one live bilateral Mapping control graph across
  2D and Object, complete Adult Dragon source fixture, distinct complete 73-Point Ancient Dragon source,
  deterministic triangle surface with atomic selected-current catalog hydration and explicit Core Morph Webs,
  one root six-face projection navigator and a global
  title-bar legend, one shared Landmarks/Labels/Segments/Outline/Overlay projection across Morph, Shader,
  and Object,
  bounded edge drags, continuous fixed-inset wing webbing, full-span Object camera fit, stance-aware Object
  control overlay, delegated same-segment double-press insertion, bounded shared-Form Undo, projected-floor
  Object Fit, capture-owned iPad/LAN wheel zoom, panel-local selectable/thick wing membranes, named 2D
  cross-sections, independent
  Wireframe/labels, canonical
  toggle-open/reorderable Morph/Morph Editor/Shader/Object Boxes, with the approved Puppeteer controls
  nested inside Morph Editor, and
  exact projected-Form/image-outline overlays, deterministic natural splayed Y-up Mapping stance,
  source-defined Adult Dragon forward Resting digits, folded Resting wings, complete Mapping digit
  comparisons,
  reversible Resting/Mapping and shuffle-walk motion pairs, canonical
  six-face camera presets, source-tab-only active-view truth, and pure
  anatomical opposite-face landmark copy, canonical silhouette-to-control analysis with explicit
  confidence/unresolved evidence, strict image-control metadata composition, and synchronous exact Mapping
  color-zone silhouette raster guidance over the managed Application Server; Morph owns the neutral
  programmatic reference while
  Shader alone supplies accepted Creature texture through the six draggable image/outline first-claim cards,
  each with independent fixed-guide pan, zoom, and one-sided axis registration plus a revisioned
  immutable-source control cage, deterministic silhouette first fit, confidence/unresolved evidence,
  bounded non-inverting local landmark warp, and exact reset; Shader preview and Object first-claim texture
  sampling use the same inverse cage coordinates; Object switches between
  anatomical paint-by-numbers
  and the assembled generated surface
  while retaining Object-local Wireframe and projecting shared root semantic visibility controls; and
  browser-local state is not Native Package or Creature authority. |
| `src/main/resources/app/critter-image-creation/object-surface-webgl.js` | OF-OBJECT-GPU-51 retained
  presentation-only Object renderer: at most six canonical view batches plus unmapped, stable first-claim UVs,
  camera-uniform-only orbit, pose-position-only updates, per-source opacity, context-loss/disposal handling,
  and exact Canvas2D fallback without a second service, persistence, media, or Morph authority. |
| `src/main/resources/app/critter-image-creation/morph-puppeteer-editor.js` | Production host-local
  Morph/Puppeteer editor projection over authenticated immutable Core Morph reads; owns stale-selection epochs,
  genuine session drafts, orientation-aware stage/Shelf split memory, mount/disposal behavior, and the nested
  workbench instance API without global DOM ownership. |
| `src/main/resources/app/critter-image-creation/morph-puppeteer-editor.html` | Registered production markup
  template for the approved Morph/Puppeteer workbench and its v4/v4/v2/v1 contracts. |
| `src/main/resources/app/critter-image-creation/morph-puppeteer-editor.css` | Morph Editor host-scoped
  styling and container-responsive wide-width/stacked-height workbench layout; must not style sibling
  Factory controls or the page root. |
| `src/main/resources/app/critter-image-creation/six-view-texture-mapping.css` | OF-UI-01 expected-width
  shared-object, shelf, compendium, proportion, stance, mapping, and open-count-aware tablet responsive
  composition without empty peer-drawer cells or trailing workspace gaps, with fixed Morph labels, a vertical
  Body Form catalog beside its selected editor, the wider contextual Proportions drawer, enlarged global
  influence legend, a centered resizable root six-view drawer, translucent-dark Morph influence canvas,
  layered fixed-guide Shader registration with edge bars, and
  contained
  Object-stage overscroll behavior. |
| `src/main/resources/app/critter-image-creation/critter-hosted-state.js` | Pure hosted bootstrap,
  lifecycle, bounded card, structured Body Form, exact display-name resolution, operation state, and strict
  seven-field guided-generation request. |
| `src/main/resources/app/critter-image-creation/critter-hosted-client.js` | Receiver-safe typed
  page-bootstrap and path-free Critter capability client with exact-stamp stale rejection, private-CSRF
  guarded generation transport, immutable operation validation, unavailable cancellation, and close-only
  abort, plus the route-derived root Destination Shelf projection. |
| `src/main/resources/app/critter-image-creation/critter-gallery.html` | Separate first-class Gallery
  Shelf page `critter.gallery` at `/critter-gallery/`. |
| `src/main/resources/app/critter-image-creation/monster-detail.html` | Canonical Creature document for
  `critter.creature`; loads Factory resources including the host-scoped Morph Editor stylesheet while
  `critter-editor.js` remains the production JavaScript entry. |
| `src/test/resources/app/critter-image-creation/application-server-parity-v1.json` | Versioned exact
  Critter bundle, three-page target, typed owner-capability, retained-authority, and security migration
  evidence; not shared-server registration. |
| `tools/srdmonsterbatch/SrdMonsterApplicationContract.java` | Immutable path-free Application Server
  composition values for current assets, seven-role detail, opaque media, compact latest per-role progress,
  exact operation lookup, stale-guarded admission with canonical control and silhouette guidance, and exact
  cancellation. |
| `tools/srdmonsterbatch/SrdMonsterApplicationGenerationService.java` | Lazily activated shared-host
  facade keeping five reads, exact polling, and individual generation on one SRD authority; no cancellation. |
| `src/test/java/com/moondance/talisman/app/tools/srdmonsterbatch/` |
  `SrdMonsterApplicationServerParityTest` owns the offline digest, route, individual-generation,
  deferred-batch, and no-browser-secret/transport parity gate. |
| `design/Active Designs/SRD Monster Image Batch/APPLICATION-SERVER-MIGRATION.md` | Staged shared-server
  surface, capability, authority, security, opaque-media, parity, rollback, and cutover contract. |
| `src/test/resources/app/critter-image-creation/application-server-page-proposal.properties` |
  Superseded proposal retained as the exact canonical Creature-page owner-bundle handoff fixture. |
| `src/test/resources/app/critter-image-creation/application-server-parity-fixture.json` | Fake path-free
  route/render/role/health/cutover parity input for the managed Application Server migration. |
| `src/main/resources/app/critter-image-creation/image-batch.html` | First-class read-only Critter Image
  Creation Batch Shelf entry; separate from Gallery and Creature. |
| `src/main/resources/app/critter-image-creation/image-batch.css` | Bounded Batch scope, disabled controls,
  provider queue, operation rows, and collapsed statistics presentation. |
| `src/main/resources/app/critter-image-creation/image-batch.js` | Progress-only hosted client projection,
  polling, render, and page-close disposal; no mutation transport. |
| `src/main/resources/app/critter-image-creation/image-batch-state.js` | Pure bounded Batch operation and
  disabled-control presentation contract. |
| `src/test/java/com/moondance/talisman/app/tools/srdmonsterbatch/` |
  `ObjectFactoryApplicationServerBundleProposalTest` owns the canonical Creature registration, exact-digest,
  capability-boundary, parity, and no-cutover proof for the superseded proposal fixture. |
| `services/app/applicationserver/CritterApplicationServerGateway.java` | Narrow UI-independent catalog,
  creature, operation-progress, direct-operation, managed-media, and exact guided-generation transport
  boundary. |
| `services/app/applicationserver/SrdMonsterApplicationServerGateway.java` | Path-free transport projection
  and lazy production source over the SRD-owned typed Application Server contract. |
| `tools/srdmonsterbatch/DatabaseCritterProjectionService.java` | Read-only production Critter catalog,
  seven-role detail, and exact slot/hash media projection over canonical SQLite content. |
| `database/userassets/SrdMonsterImageRecordResolver.java` | Bounded active-role metadata inventory and
  direct exact-slot verified content reads for Critter hosting and generation authority. |
| `database/userassets/CanonicalCritterClosureService.java` | Operator-only accepted-artifact to canonical
  Creature-role preview/token/apply reconciliation; uses existing content and association authorities. |
| `services/app/applicationserver/CoreMorphApplicationServerGateway.java` | Typed read-only current catalog
  and complete selected Core Morph host boundary with stable transport rejections. |
| `services/app/applicationserver/FactoryCoreMorphApplicationServerGateway.java` | Source synchronization and
  exact database-backed Core Morph catalog/package wire projection. |
| `src/test/java/com/moondance/talisman/app/tools/srdmonsterbatch/` |
  `ObjectFactoryHostedCritterResourceTest` owns OF-UI-MIGRATE-02 separate-page, Morph-Form-first, typed
  bootstrap/media, stale/close, and forbidden fallback/authority proof. |
| `src/test/js/morph-puppeteer-editor.test.mjs` | Focused Core Morph adapter, stale-selection, mount-lease,
  resource-scope, and production contract proof. |
| `src/test/js/morph-puppeteer-editor-browser.test.mjs` | Headless-browser Factory-style mount fixture covering
  sibling control/style isolation, first interaction, stale A/B selection, collapse/reopen, responsive layout,
  abandoned initial reads, and disposal when a usable Chromium environment is available. |
| `src/test/js/critter-hosted-state.test.mjs` | Pure hosted client/state proof for exact bootstrap stamps,
  safe card/Body Form projection, name resolution, opaque media, unavailable mutation, stale rejection,
  and close behavior. |
| `src/test/js/object-factory-surface.test.mjs` | Pure deterministic three-Box Shelf ordering,
  open/close toggling, triangle-density, independent surface/Wireframe state, distinct Arachnid
  standing/mapping, natural immutable splayed-chain proof, and OF-SHADER-CAGE-50 immutable-source first-fit,
  cage topology, bounded landmark, stale/reset, narrow-invalidation, Shader/Object sampler parity, exact
  six-view Object basis, retained/fallback route selection, and Morph-only playback ownership proof. |
| `src/test/js/object-surface-webgl.test.mjs` | Pure OF-OBJECT-GPU-51 retained-batch packing, upload-plan,
  opacity/color, fallback/context-loss, restoration, and graphics-resource disposal proof. |
| `database/userassets/FactoryNativePackageService.java` | OF-05A/OF-08A/OF-P01B atomic package
  persistence, imported derivation, manifest, and reload authority. |
| `database/userassets/CoreMorphCatalogService.java` | Digest-checked source-to-database Core Morph
  materialization plus immutable current catalog and complete selected-package reads. |
| `database/userassets/CoreMorphRevisionService.java` | Interactive immutable private/base Morph Save,
  atomic Creature association, optimistic guards and durable idempotent receipt. |
| `src/main/resources/app/critter-image-creation/creature-morph-save-session.js` | Applied-origin private
  Save and frozen reviewed Base Save orchestration over the typed hosted gateway. |
| `database/userassets/FactoryRuntimePublicationService.java` | OF-11 exact-current durable-load adapter. |
| `services/app/factory/FactoryWallGenerator.java` | Versioned ZOF wall/profile-opening generator. |
| `services/app/factory/FactorySurfaceSpec.java` | Versioned Floor/Ceiling recipe. |
| `services/app/factory/FactorySurfaceGenerator.java` | Floor/Ceiling profile extrusion. |
| `services/app/factory/FactorySurfaceCandidate.java` | Floor/Ceiling candidate wrapper. |
| `services/app/factory/FactoryFurnitureSpec.java` | Versioned Rectangular/Round Table recipes. |
| `services/app/factory/FactoryFurnitureGenerator.java` | Deterministic straight/splayed-leg table meshes. |
| `services/app/factory/FactoryFurnitureCandidate.java` | Shared table candidate wrapper. |
| `services/app/factory/FactoryFurnitureFaceUv.java` | Stable furniture part/face physical-UV evidence. |
| `services/app/factory/FactoryStarterMaterialCatalog.java` | Offline Poly Haven CC0 manifest. |
| `services/app/LocalFileApplicationGateway.java` | Exact local-app discovery and caller-file open. |
| `database/userassets/CharacterAssetPackageService.java` | Transactional Type/Character/Creature generated and Character file admission. |
| `database/userassets/SrdMonsterImageRecordResolver.java` | Exact Creature identity and active role. |
| `database/userassets/CharacterStandardPackageService.java` | Transactional sheet/Icon/Token/3D slots and reviewed completion. |
| `database/userassets/CreatedThingsDeletionService.java` | Exact Created Things preview-token deletion; Places and managed bytes protected. |
| `database/userassets/CreatedThingAttachmentService.java` | Checked attachment/delta owner. |
| `database/userassets/ManagedModelDerivationService.java` | Exact OBJ association/legacy-STL resolver. |
| `database/userassets/ManagedObjAdmissionService.java` | Checked STL/OBJ source provenance and atomic new OBJ/material/texture commit. |
| `database/userassets/UnityFsBundleOperationService.java` | Checked UnityFS operation. |
| `database/assets/AssetCollectionService.java` | Stable DB-owned collection and exact membership authority. |
| `database/userassets/FactoryMediaDestination.java` | Closed reviewed Factory Media destinations. |
| `database/userassets/FactoryModelAdmissionService.java` | Factory admission and destination membership. |
| `database/userassets/FactoryRecipeCodec.java` | Strict versioned Factory recipe decoder. |
| `database/userassets/FactoryObjectLibraryService.java` | Factory Object/version authority. |
| `database/userassets/FactoryObjectOperationService.java` | App-scoped Factory Save and OF-P01B exact
  imported-model commit lifecycle. |
| `database/userassets/CharacterPackageImportService.java` | Reviewed bulk Character-package transaction owner. |
| `database/userassets/PresentationAssociationLifecycle.java` | Exact active presentation slots, stable numbered history, and checked promotion/removal. |
| `database/userassets/EffectivePresentationResolver.java` | Instance/Type/generic presentation resolver. |
| `database/userassets/ManagedAssetDeletionPreview.java` | Complete exact-usage inventory and stale-state token for global managed deletion. |
| `database/userassets/GeneratedAssetRequestProvenance.java` | Exact accepted-generation request metadata projection. |
| `ui/assets/CharacterMediaGenerationBus.java` | Public exact Character Icon/Token generation with secret-free current Assets settings/readiness, review, Keep/reject/cancel, and change/reveal. |
| `ui/assets/CharacterFilesInspectorPanel.java` | DB-owned Markdown/source/provenance and prompt-context transfer. |
| `ui/assets/CreatedThingsProjection.java` | Type/Character/Creature/Place owned-content projection. |
| `ui/assets/CreatureCatalogPanel.java` | Exact-ID trees with targeted committed-association subtree patches. |
| `ui/assets/CreatureDataImportPanel.java` | Prepared SRD preview/count/diagnostic and checked import UI. |
| `ui/assets/AssetManagerWorkspaceSelection.java` | Activates the containing Asset Manager Acquisition tab for exact workspace reveals. |
| `ui/assets/UserAssetsWorkspacePickerPanel.java` | Managed Media selection with an independent internal Preview/Metadata lane. |
| `ui/assets/UserAssetsWorkspaceCandidateDeck.java` | External Files candidates/control bar and import-to-managed-Media routing. |
| `ui/assets/UserAssetsWorkspacePreviewPanel.java` | Read-only exact-format Media/Files preview for images, text, folders, STL, and OBJ. |
| `ui/assets/AssetsFactoryObservationSession.java` | Toolkit-neutral resident Assets/Factory exact-ID observation, readiness, restoration, and bounded Viewer-orbit handle. |
| `ui/assets/AssetsFactoryObservationHosts.java` | Owner adapters over already-rendered Media/Factory projections with no new I/O or mutation authority. |
| `dtdt/builder/containers/AssetManagerObservationTabs.java` | Exact Asset Manager safe-tab allowlist and preference-free opaque restoration owner. |
| `ui/assets/UserAssetsGenerative3DPanel.java` | Single-selection Media/Type/Created Thing STL/OBJ creation and revision workspace. |
| `ui/assets/FactoryWorkspacePanel.java` | First-class Factory Objects, Body Form, and import composition. |
| `ui/assets/UserAssetsFactoryPanel.java` | Transient Factory draft, preview, and candidate preparation. |
| `ui/assets/ObjectFactoryImportPanel.java` | OF-P01A grouped source/candidate review plus OF-P01B
  single-flight exact-current atomic acceptance and imported reload surface. |
| `ui/assets/ObjectFactoryBodyFormEditorPanel.java` | Retained native Views/Morph/Shader/Puppeteer/Object
  composition over the existing Body Form, Appearance, Pose, and Puppeteer sessions. |
| `ui/assets/ObjectFactoryWorkspaceState.java` | Immutable UI-local native Object Factory surface
  disclosure and focus truth. |
| `ui/assets/ObjectFactoryCanonicalViewsState.java` | Validated UI-local Front/Back/Left/Right/Top/Bottom
  selection, image/empty, enablement, scale, and Shader-priority order truth. |
| `ui/assets/ObjectFactoryCanonicalViewsPanel.java` | One EDT-only root six-card Views drawer with direct
  selection, enablement, pointer drag, and accessible keyboard reorder. |
| `ui/assets/ObjectFactoryNativeWorkspacePanel.java` | EDT-only retained Swing projection of the five
  native Object Factory surfaces. |
| `ui/assets/ObjectFactoryPosePanel.java` | OF-06 manual/deck Pose review and exact Save Pose UI. |
| `ui/assets/ObjectFactoryPuppeteerPanel.java` | OF-07/OF-08A Field, Record, and live preview UI. |
| `ui/assets/ObjectFactoryBehaviorMixerPanel.java` | OF-08B Layer edit/review/save projection. |
| `ui/assets/ObjectFactoryRuntimePublicationPanel.java` | OF-11 grouped Publish / Use review projection. |
| `src/test/java/com/moondance/talisman/app/services/app/factory/ObjectFactoryPoseAuthoringTest.java` |
  OF-06 deterministic domain and package proof. |
| `src/test/java/com/moondance/talisman/app/ui/assets/ObjectFactoryWorkspaceStateTest.java` |
  Immutable native workspace normalization and transition proof. |
| `src/test/java/com/moondance/talisman/app/ui/assets/ObjectFactoryNativeWorkspacePanelTest.java` |
  Retained component identity, accessibility, empty-state, and lifecycle projection proof. |
| `src/test/java/com/moondance/talisman/app/ui/assets/ObjectFactoryPosePanelTest.java` |
  OF-06 UI/lifecycle proof. |
| `src/test/java/com/moondance/talisman/app/services/app/factory/ObjectFactoryPoseFieldTest.java` |
  OF-07 deterministic Field/session proof. |
| `src/test/java/com/moondance/talisman/app/services/app/factory/ObjectFactoryPerformancePathTest.java` |
  OF-08A recording/dependency/edit/bake proof. |
| `src/test/java/com/moondance/talisman/app/services/app/factory/ObjectFactoryTemplateEngineTest.java` |
  OF-09A Template/biped/signature/density/evolution proof. |
| `src/test/java/com/moondance/talisman/app/services/app/factory/` | The
  ObjectFactoryCreatureTemplateLibraryTest OF-09B quadruped/dragon/signature/Appearance/evolution proof. |
| `src/test/java/com/moondance/talisman/app/services/app/factory/` | The
  ObjectFactoryAppearanceGenerationSessionTest OF-10 request/validation/acceptance/provenance proof. |
| `src/test/java/com/moondance/talisman/app/ui/assets/ObjectFactoryAppearanceGeneratorPanelTest.java` |
  OF-10 review/progress/preview/lifecycle/visual proof. |
| `src/test/java/com/moondance/talisman/app/services/app/factory/` | The
  ObjectFactoryImportedModelStagingServiceTest OF-P01A intake/conversion/receipt proof. |
| `src/test/java/com/moondance/talisman/app/ui/assets/ObjectFactoryImportPanelTest.java` |
  OF-P01A preview plus OF-P01B accept-progress/duplicate/stale/close proof. |
| `src/test/java/com/moondance/talisman/app/database/userassets/FactoryNativePackageServiceTest.java` |
  OF-05A/OF-08A/OF-P01B exact Native Package lifecycle and rollback proof. |
| `src/test/java/com/moondance/talisman/app/database/userassets/CoreMorphCatalogServiceTest.java` |
  Twelve-Morph materialization/order, no-op, revision-1 upgrade retention, complete reads, and revision-drift
  proof. |
| `src/test/java/com/moondance/talisman/app/services/app/applicationserver/`
  `ApplicationServerCoreMorphReadTransportTest.java` |
  Authenticated exact twelve-entry catalog/current fixture and typed query/not-found transport proof. |
| `src/test/java/com/moondance/talisman/app/database/userassets/FactoryObjectOperationServiceTest.java` |
  Factory semantic-save lifecycle plus OF-P01B exact-current commit proof. |
| `src/test/java/com/moondance/talisman/app/services/app/factory/FactoryBodyFormCaptureGatewayTest.java` |
  Exact-current authoritative Body Form session-to-transfer capture and fail-closed proof. |
| `src/test/java/com/moondance/talisman/app/ui/assets/ObjectFactoryPuppeteerPanelTest.java` |
  OF-08A UI/visual/lifecycle proof. |
| `src/test/java/com/moondance/talisman/app/services/app/factory/ObjectFactoryBehaviorComposerTest.java` |
  OF-08B Behavior/composition/biped proof. |
| `src/test/java/com/moondance/talisman/app/ui/assets/ObjectFactoryBehaviorMixerPanelTest.java` |
  OF-08B UI/visual/lifecycle proof. |
| `src/test/java/com/moondance/talisman/app/services/app/factory/ObjectFactoryRuntimePublicationTest.java` |
  OF-11 publication/invocation/consumer proof. |
| `src/test/java/com/moondance/talisman/app/database/userassets/FactoryRuntimePublicationServiceTest.java` |
  OF-11 exact-current durable adapter proof. |
| `src/test/java/com/moondance/talisman/app/ui/assets/ObjectFactoryRuntimePublicationPanelTest.java` |
  OF-11 grouped UI/visual/close proof. |
| `src/test/java/com/moondance/talisman/app/services/app/factory/ObjectFactoryLizardMappingBodyTypeTest.java` |
  OF-12A Lizard reuse, exact camera/mapping independence, opposite seed, and deterministic bake proof. |
| `src/test/java/com/moondance/talisman/app/tools/srdmonsterbatch/SrdMonsterSixViewTextureMappingResourceTest.java` |
  OF-12A browser-evidence proof for separate observation/geometry projection, exact envelope fit, and orbit. |
| `src/test/java/com/moondance/talisman/app/ui/assets/ObjectFactoryContextCaptureTest.java` | CM-07 proof. |
| `src/test/java/com/moondance/talisman/app/services/app/ObjectFactoryContextProviderTest.java` | CM-07
  provider/disclosure/budget proof. |
| `src/test/java/com/moondance/talisman/app/ui/assets/ObjectFactoryContextPilotTest.java` | CM-07 guarded
  action/passive-Monitor proof. |
| `src/test/java/com/moondance/talisman/app/services/app/AdventureBattleContextProviderTest.java` | CM-06
  hierarchy, role-precedence, disclosure, determinism, and capture-contract proof. |
| `context-management/src/main/java/com/moondance/talisman/context/ContextProposalService.java` | CM-08
  bounded structured proposals, explicit confirmation, and feature-authority delegation. |
| `context-management/src/test/java/com/moondance/talisman/context/ContextProposalServiceTest.java` | CM-08
  core disclosure, bounds, stale-state, confirmation, authority, replay, and close proof. |
| `src/test/java/com/moondance/talisman/app/services/app/ContextProposalSubsystemIntegrationTest.java` |
  CM-08 CM-06/CM-07 request and fake-authority integration proof. |
| `src/test/java/com/moondance/talisman/app/ui/assets/ContextualActionRolloutTest.java` | CM-09 agreed-screen
  privacy, lifecycle, stale-state, close-disposal, and cross-screen fake-authority proof. |
| `ui/assets/ObjectFactoryBodyFormCanvas.java` | Lightweight Form/reference/Skeleton/Skin surface. |
| `ui/assets/ObjectFactoryReferenceImageIntake.java` | Metadata-first bounded PNG/JPEG admission. |
| `ui/assets/ObjectFactoryAppearanceImageIntake.java` | Exact metadata-first 1024-square PNG admission. |
| `ui/assets/ObjectFactoryAppearanceProvider.java` | OF-10 injected transport-only provider adapter. |
| `ui/assets/ObjectFactoryAppearanceGeneratorPanel.java` | OF-10 grouped review/progress/decision UI. |
| `ui/assets/ObjectFactoryUvAtlasCanvas.java` | Flattened atlas and reciprocal UV selection surface. |
| `ui/assets/FactoryModelExportService.java` | Reviewed Factory filesystem export and rollback recovery. |
| `ui/assets/ManagedMediaCollection.java` | Immutable managed-collection projection. |
| `ui/assets/ModelAttachmentTargetChooser.java` | Temporary Type/Creature/Character/Place OBJ destination chooser. |
| `ui/assets/UserAssetsWorkspaceImageSupport.java` | Separate bounded selected-preview and thumbnail decode worker lanes. |
| `ui/assets/StlObjPreviewContract.java` | Path-free immutable STL/OBJ editor DTO and callback boundary. |
| `ui/assets/StlObjPreviewSession.java` | Monotonic STL/OBJ preparation, stale-result rejection, and close invalidation. |
| `ui/assets/StlObjPreviewPane.java` | Neutral STL/verified OBJ texture review and checked attachment. |
| `ui/assets/texturemapping/TextureGenerationDialog.java` | Themed Front/arbitrary camera-view/current-map review, per-view registration/lock coordination, and final Apply/Accept/Cancel owner. |
| `ui/assets/texturemapping/TextureGenerationRequestPanel.java` | Separate associated/captured-view galleries with capture/color/approve actions, accumulated-texture previews, full-image copy, and review controls. |
| `ui/assets/texturemapping/TextureGenerationWorkflowState.java` | Revisioned Front plus appendable exact-camera approval/evidence/context and bake gate. |
| `ui/assets/texturemapping/UvModelInspectionSurface.java` | Generation-safe Viewer surface contract for UV/model hover markers and wire overlay. |
| `ui/assets/texturemapping/UvProjectionPaintSurface.java` | Revision-safe source registration, stroke evidence, and transient texture state. |
| `ui/assets/texturemapping/TextureModelCutout.java` | Geometry-mask alpha that preserves legitimate black model detail. |
| `ui/assets/texturemapping/TextureGenerationSettingsSnapshot.java` | Immutable provider/model/size/seed values captured from shared Generative state for one reviewed request. |
| `ui/assets/texturemapping/` | UV inspector, explicit Character choice, and shared Generative settings UI. |
| `services/app/texturemapping/` | Deterministic UV atlas, calibrated software views, multi-view projection baking, material, and post-processing core. |
| `services/app/texturemapping/TextureProjectionRenderer.java` | Headless neutral/accumulated-texture camera render, depth, visibility, provider-mask, and perspective-correct screen-to-UV rasterizer. |
| `services/app/texturemapping/TextureProjectionBaker.java` | First-visible colored-view-to-UV projection, signed confidence blending, bounded fill, and unresolved coverage. |
| `services/app/texturemapping/TextureProjectionPainter.java` | Viewport-correct UV paint, evidence, Undo/Redo/reset/clear. |
| `services/app/texturemapping/TextureProjectionReprojectionValidator.java` | Calibrated baked-atlas render-back mismatch diagnostics for exact source views and silhouettes. |
| `services/app/texturemapping/UvLayoutValidator.java` | Output-resolution non-overlap gate for imported one-to-one UV layouts. |
| `services/app/texturemapping/UvMeshInspection.java` | Immutable all-candidate UV/triangle barycentric correspondence for the centered preview mesh. |
| `services/app/texturemapping/TextureModelOrientation.java` | Exact path-free model generation/selection/pitch/yaw Front identity. |
| `ui/mapeditor/viewer3d/StlObjPreviewSurface.java` | Preview grid, guarded camera/Showcase orbit, UV paint. |
| `ui/mapeditor/viewer3d/CanonicalModelFxMeshes.java` | Shared model-to-JavaFX mesh conversion. |
| `ui/mapeditor/viewer3d/Model3DPreviewAdmission.java` | Canonical metadata dispatch for managed OBJ and legacy STL inspector meshes. |
| `ui/assets/CharacterSheetEditorPanel.java` | Full Selected Item Character sheet and Creature override editor. |
| `ui/assets/ExternalDocumentPromptSupport.java` | Bounded UTF-8 external Reference Media loading, hashing, and stale-file validation. |
| `ui/assets/GenerativeImageReferenceSupport.java` | Unified document/image prompt review with provider image-input filtering. |
| `ui/assets/GenerativeReferenceCatalog.java` | Read-only exact-association image/document reference discovery snapshot. |
| `ui/assets/GenerativeReferencePickerPanel.java` | Searchable managed/external Reference Media hierarchy and review. |
| `ui/assets/GenerativeImageSizePolicy.java` | Fail-closed provider/model/role canvas sizes and explicit W/H presentation. |
| `ui/assets/UserAssetsAcquisitionPanel.java` | Target-bound Generative and 2D/3D acquisition. |
| `ui/assets/ExternalAssetPreviewLoader.java` | Bounded provider preview cache and stale owner. |
| `ui/assets/ExternalAssetResultPresentation.java` | Human external-result card labels. |
| `ui/assets/ExternalAssetDiscovery.java` | External rights and fail-closed payload policy. |
| `ui/assets/ExternalAssetFederation.java` | Provider isolation and raw evidence. |
| `ui/assets/ExternalAssetBenchmark.java` | Versioned provider benchmark contract. |
| `ui/assets/OpenverseAssetProvider.java` | Official authenticated-or-anonymous link-only Openverse catalog. |
| `ui/assets/PolyHavenAssetProvider.java` | Official Poly Haven acquisition. |
| `ui/assets/SketchfabAssetProvider.java` | Official v3 JSON and resolved-URI Sketchfab 3D discovery. |
| `ui/assets/ThingiverseAssetProvider.java` | Official credentialed, link-only Thingiverse 3D discovery. |
| `ui/assets/MyMiniFactoryAssetProvider.java` | Official credentialed, link-only MyMiniFactory 3D discovery. |
| `ui/assets/SmithsonianOpenAccessProvider.java` | Official key-gated Smithsonian Open Access acquisition. |
| `ui/assets/WikimediaCommonsAssetProvider.java` | Commons rights and payload acquisition. |
| `ui/assets/FreeSearchResultSaveService.java` | Atomic exact-byte 2D/3D result save with conflict and rollback safety. |
| `ui/assets/FreeSearchSiteStore.java` | Enabled identities for fixed code-owned 2D/3D providers. |
| `ui/assets/FreeSearchSitesDialog.java` | Built-in enable/disable plus exact website double-click handoff. |
| `mapimport/UniversalVttReader.java` | Streaming `.dd2vtt` parser, bounded embedded-image decode, normalization, and diagnostics. |
| `mapimport/MapRasterDecoder.java` | Shared bounded PNG/JPEG/GIF/BMP/WebP/TIFF map-raster decode and signature diagnostics. |
| `mapimport/earth/NumericalElevationTiffDecoder.java` | Bounded Float32 read; no RGB or mutation. |
| `mapimport/earth/ElevationReadRequest.java` | Reviewed input identity and geospatial evidence. |
| `mapimport/earth/GrandCanyonElevationProfile.java` | Fixed USGS crop and candidate-query recipes. |
| `mapimport/earth/SeasonsHeightContext.java` | Stamped height evidence; no real-world adapter. |
| `mapimport/earth/EarthHeightPreparation.java` | Source-height resampling and canonical U16 preparation. |
| `mapimport/earth/EarthHeightPng.java` | Existing Heightmap codec adapter and exact round trip. |
| `mapimport/earth/EarthPackageArchive.java` | Strict fixed-role ZIP bounds; no path extraction. |
| `mapimport/earth/EarthPackagePng.java` | Preflight before image allocation and inflation. |
| `mapimport/earth/EarthPackageManifestJson.java` | Closed bounded JSON using the existing dependency. |
| `mapimport/earth/EarthPackageHeightValidator.java` | Re-derived heights and binary-mask evidence only. |
| `mapimport/earth/EarthTerrainPackageCodec.java` | Wire adapter; not an importer or content store. |
| `mapimport/CanonicalMapDocument.java` | Format-neutral transient map/image/barrier/portal/light preview model. |
| `mapimport/CanonicalToPersistentMapMapper.java` | Best-effort persistent Place candidate mapping. |
| `mapimport/PersistentMapImportService.java` | Checked atomic complete/partial Place import. |
| `ui/assets/PersistentMapImportWorkflow.java` | Reviewed create-new-Place options and checked Asset Manager import delegation. |
| `mapeditor/SpatialFeature.java` | Persistent typed constructed-spatial Feature contract and exact Source binding. |
| `mapeditor/ConstructedFeatureLayerProperties.java` | Durable Constructed Features scale, defaults, and import metadata. |
| `ui/mapeditor/MapEditorFeatureSupport.java` | Native 2D paint, hit selection, and edit preview for persistent contour and constructed Features. |
| `database/projectstorage/SqliteMapDocumentRepository.java` | Normalized persistence and durable raster-history preparation. |
| `database/projectstorage/CurrentPlaceRasterMigrationService.java` | Raster/background migration. |
| `ui/assets/UniversalVttImportPanel.java` | Transient VTT preview plus explicit reviewed new-Place import and result routing. |
| `ui/assets/UniversalVttPreviewCanvas.java` | Canonical image/grid/LOS/portal/light 2D renderer with pan, zoom, fit, and hit testing. |
| `ui/assets/CharacterPackageImportDialog.java` | Per-package target/action and file-role review. |
| `ui/FocusedSemanticDeleteSupport.java` | Shared exact-focus platform Delete routing without global-delete authority. |
| `ui/assets/CharacterStlInspectorPanel.java` | OBJ/STL inspector with direct orbit. |
| `services/app/ArenaObjectTokenImage.java` | Durable toolkit-independent custom Arena Object token image. |
| `services/app/ArenaObjectMovementProvenance.java` | Generated versus user movement ownership. |
| `services/app/MapRuntimeSessionService.java` | Runtime session, object repository, Place presences,
  persistence-first checked activation movement, checked staged-Present generation install, atomic
  prepared-encounter admission, one synchronized bounded GM Control snapshot, and
  Authoring-to-Control structural Place transfer. |
| `services/app/GmControlSnapshot.java` | Immutable GM-private exact Place/hierarchy/Layer
  and bounded selected-first Arena/Group/resident capture for allowlisted Control adapters. |
| `services/app/GmControlRenderProjection.java` | Byte-free private GM retained-render manifest with
  exact owner/Place/revision stamps, stable Overview/2D/3D identities, normalized camera, admitted
  object transforms, and opaque digest-bound asset descriptors. |
| `services/app/GmControlContextProvider.java` | Pure bounded PROJECT-classified projection of one sealed
  GM Control capture into the application-lifetime Context Service and passive Monitor truth. |
| `services/app/GmControlContextExchange.java` | Canonical
  `talisman.gm-control-context-exchange/v1` model, fixed limits, explicit unavailable fields, and
  proposal vocabulary without production authority. |
| `services/app/GmControlContextExchangeCodec.java` | Strict 65,536-byte Java codec accepting harmless
  JSON object-member reordering while rejecting schema, bounds, duplicate, trailing, and malformed input. |
| `services/app/GmControlContextExchangeService.java` | Direct read-only GM Copy/Paste coordinator using
  fresh owner capture, one Context Service, full-base stale checks, and non-applying typed review. |
| `services/app/GmControlContextDelta.java` | Pure typed proposal comparison with exact copied/current base,
  target, field, range, receipt, and per-presence revision checks; no Apply route. |
| `src/test/resources/contracts/context/gm-control-context-exchange-v1.json` | Shared Java/JavaScript GCS-01
  exchange fixture with exact product naming, admitted labels, explicit unavailable revisions, and empty
  production change authority. |
| `services/app/relay/MoondanceRelayClient.java` | Relay client and invitation retry guard. |
| `services/app/relay/MoondanceRelayStatus.java` | Credential-free management lifecycle snapshot. |
| `services/app/relay/MoondanceManagedInvitation.java` | GM-private active invitation rehosting projection. |
| `services/app/ArenaObjectResetPreview.java` | Exact complete-Arena reset graph and preview token. |
| `services/app/ArenaObjectResetMaintenanceService.java` | Verified-backup and protected-data reset gate. |
| `services/app/ArenaObjectStoreResetState.java` | Store generation and migrated-legacy reset context. |
| `database/projectstorage/ArenaResetProtectedDataService.java` | Preserved-category counts and row proofs. |
| `services/app/ArenaObjectMovementState.java` | Stationary/default speed and ordered Arena Object waypoint-path state. |
| `services/app/ArenaObjectWaypoint.java` | Immutable grid-relative Arena Object route waypoint. |
| `src/main/java/com/moondance/talisman/app/ui/AppUiBootstrap.java` | Main UI wiring/bootstrap. |
| `ui/MarkdownDocumentViewer.java` | Shared read-only Help and payload Markdown presentation. |
| `src/main/java/com/moondance/talisman/app/ui/AppAwareMenuBarService.java` | Top-menu ownership. |
| `ui/AppWideWorkspaceNavigationService.java` | Exact global Authoring/Assets workspace shortcut owner. |
| `ui/ProductWorkspaceNavigationBus.java` | Typed Assets Files/Media activation request seam. |
| `ui/assets/AssetManagerWorkspaceSelection.java` | Combined Media tab and semantic lane activation. |
| `ui/maintenance/ArenaObjectResetMaintenanceController.java` | Gated reset workflow and Help-linked failure UI. |
| `ui/windows/ApplicationWindowManager.java` | Product-shell lifecycle, migration, and transient card selection. |
| `ui/windows/CombinedProductContent.java` | Retained product cards, selector order, and cycle route. |
| `ui/monitors/MonitorsWindow.java` | Application-owned modeless shared monitor shell and detach/reopen lifecycle. |
| `ui/monitors/ContextMonitorPanel.java` | EDT-safe bounded projection of immutable current context and diffs. |
| `ui/context/` | Reusable lifecycle-safe Ask AI/Open in TaliTalk Swing actions and bounded acknowledgement
  pop-up plus opaque-receipt proposal surface; no feature registration or destination transport authority. |
| `ui/context/ContextualActionSurface.java` | CM-09 shared screen ownership, confirmation isolation, and
  close-disposal boundary. |
| `ui/windows/ProductShellMode.java` | Persisted product-shell presentation choice. |
| `src/main/java/com/moondance/talisman/app/ui/ProductNavigationActions.java` | Shared Navigation-menu product adapter contract. |
| `ui/mapeditor/AuthoringNavigationActions.java` | Authoring Grid toggle and semantic Layers-inspector reveal adapter. |
| `ui/mapeditor/MovePlaceDialog.java` | Reviewed exact-revision Place subtree reparent and Parent Capture detachment presentation. |
| `ui/theme/TalismanFileChooser.java` | Platform-preserving themed Swing chooser boundary. |
| `core/settings/TalismanTheme.java` | Name-persisted theme identity, label, load/save, and fallback. |
| `core/settings/TalitalkSettingsDialog.java` | Theme enumeration and explicit Apply ownership. |
| `ui/theme/TalismanThemePalette.java` | Exhaustive typed palette mapping, including Forest. |
| `ui/theme/TalismanThemeInstaller.java` | Normal shared UI-default and delegate installation path. |
| `ui/theme/TalismanThemeTokens.java` | Forest semantic visual-token contract. |
| `ui/theme/TalismanForestPreviewTheme.java` | Restoring gallery seam reusing the registered palette. |
| `ui/theme/TalismanShelfPresentation.java` | Appearance-only Shelf state projection. |
| `ui/theme/gallery/TalismanForestGalleryPanel.java` | Named-state Swing showcase and renderer. |
| `ui/theme/gallery/TalismanForestGalleryLauncher.java` | Live gallery and PNG entry point. |
| `ui/theme/TalismanButtonUIs.java` | ORC Blue semantic disabled-text painters for button controls. |
| `ui/theme/TalismanTabbedPaneUI.java` | Shared tab spacing, separator, centering, and selected underline. |
| `src/test/java/com/moondance/talisman/app/ui/theme/` | Forest registration, installer, and gallery proof. |
| `src/test/java/com/moondance/talisman/app/ui/theme/ForestScreenTestTheme.java` |
  Test-only Forest setup and Swing-default restoration for audited headed screen fixtures. |
| `src/test/java/com/moondance/talisman/app/core/settings/TalismanSettingsDialogTest.java` |
  Theme enumeration, Apply persistence, fresh reload, startup-style install, and fallback proof. |
| `core/activity/ActivityMonitor.java` | Concurrent logical user-operation registry. |
| `ui/activity/ActivityMonitorWindow.java` | Transient active-operation window and preferences. |
| `ui/activity/ApplicationStatusLine.java` | Retained shell summary and detailed-monitor route. |
| `ui/windows/WorkspaceLayout.java` | Versioned named shell/window/splitter/tab snapshot. |
| `ui/windows/WorkspaceLayoutService.java` | Named UI-only shell mode, geometry, splitter, and tab layouts. |
| `ui/windows/CombinedMapLayoutController.java` | Shared responsive six-choice 2D/3D selector, retained roots, legacy migration, axis dividers, and optional larger-surface action. |
| `dtdt/builder/containers/DTDTSplitter.java` | Resizable docks, fixed control-bar semantics, and declarative exact drawer captions. |
| `dtdt/builder/containers/DTDTFocusWorkspace.java` | DTDT v2 Focus Bar/Rail packing, adjacent dividers, restoration, accessibility, and EDT lifecycle owner. |
| `src/main/resources/app/dtdt/components/debug/Focus-Workspace.dtdt.yaml` | Reusable DTDT v2 Focus Workspace developer fixture. |
| `dtdt/panels/AppAwarePopupWindow.java` | Managed bounds, safe macOS maximize, and layout-profile owner. |
| `src/main/java/com/moondance/talisman/app/dtdt/DTDTComponentFactory.java` | DTDT component dispatch. |
| `dtdt/builder/containers/DTDTVerticalTabGroup.java` | Left/right named DTDT tabs. |
| `dtdt/builder/containers/DTDTStackPanel.java` | Divider-free leading-control/content DTDT host. |
| `src/main/java/com/moondance/talisman/app/mapeditor/MapEditorBus.java` | Map Editor command/event API. |
| `mapeditor/SpaceMetadataSample.java` | Shared revision-stamped Authoring/GM spatial hover values. |
| `src/main/java/com/moondance/talisman/app/mapeditor/MapPerformanceLog.java` | Bounded correlated Map Editor performance measurements. |
| `mapeditor/SemanticOperationMapPerformanceAdapter.java` | Typed factual operation-stage log adapter. |
| `services/memory/MemoryTelemetryService.java` | Process-scoped passive semantic large-byte telemetry, snapshot, and bounded history owner. |
| `services/memory/LargeBytesResidencyManager.java` | Process cache owner with LRU and manual release. |
| `ui/memory/MemoryLargeBytesMonitorPanel.java` | Shared snapshot-driven grouped summary used by Workbench and the retained Talisman monitor. |
| `ui/memory/MemoryLargeBytesMonitorWindow.java` | Retained modeless Talisman monitor lifecycle and usable-screen geometry. |
| `services/memory/MemoryTelemetrySnapshotPublisher.java` | Cached localhost and bounded atomic instance-file semantic snapshot publication. |
| `services/memory/MemoryTelemetryAdapter.java` | Payload-free existing-cache/view bridge for stable telemetry owners, entries, jobs, coverage, and releases. |
| `services/memory/OperationJobMemoryTelemetry.java` | Payload-free live jobs and bounded aggregate outcomes/temporary-peak counters for existing heavy operations. |
| `services/memory/MemoryByteEstimator.java` | Stateless, non-retaining large-payload byte estimates for telemetry adapters. |
| `services/app/ControlRuntimeMemoryTelemetry.java` | Passive aggregate Control Arena Object projection churn and estimated transient bytes. |
| `mapeditor/document/MapWorkspaceMemoryInventory.java` | Raster/mask sharing inventory. |
| `mapeditor/document/MapDocumentMemoryTelemetry.java` | Document snapshot/history telemetry. |
| `ui/assets/AssetMemoryTelemetry.java` | Asset cache, preview, candidate, and source-job telemetry. |
| `ui/assets/VttMemoryTelemetry.java` | VTT job, decoded-preview, geometry, and release telemetry. |
| `src/main/java/com/moondance/talisman/app/services/probe/MapPerformanceProbeServer.java` | Localhost-only scenarios, independent UI-loop heartbeat, and cached read-only semantic memory endpoint. |
| `services/probe/ScriptedWalkthroughProbeServer.java` | Localhost semantic demo transport. |
| `services/probe/ProductDataStateCapture.java` | Revision-stamped privacy allowlist. |
| `services/probe/ProductDataStateCaptureRegistry.java` | Adapter timeout/stale owner. |
| `services/probe/ProductDataStateCaptureCodec.java` | Bounded versioned localhost capture-bundle protocol. |
| `src/main/java/com/moondance/talisman/app/tools/MapPerformanceWorkbenchLauncher.java` | Workbench shell. |
| `tools/MapPerformanceAnalysisReportPanel.java` | Report plus isolated-run/baseline review controls. |
| `tools/WorkbenchBuildIdentity.java` | Embedded Workbench revision and Talisman release/build identity. |
| `tools/WorkbenchAboutDialog.java` | Canonical modeless native/in-window Workbench identity owner. |
| `tools/WorkbenchReleaseService.java` | Guarded exact-Main Alpha preview, validation, metadata transaction, rollback, and publication. |
| `tools/WorkbenchReleaseReadinessAppearance.java` | Theme-safe Main-newer-than-release control presentation. |
| `tools/WorkbenchReleaseReadinessButton.java` | Full-surface release-ready paint across button interaction states. |
| `tools/WorkbenchFullSurfaceButton.java` | Shared semantic fill beneath normal button interaction/focus painting. |
| `tools/WorkbenchReleaseConfirmationDialog.java` | Bounded wrapping resizable confirmation for exact release previews. |
| `tools/WorkbenchDividerPreferences.java` | Durable clamped divider positions for every Workbench split pane. |
| `tools/ReleaseMetadataEditor.java` | Deterministic build increment and complete lossless About-history shift. |
| `apply-patch.sh` | ZIP-only validation, normalized bundle retention, isolated commit, and push. |
| `patches/APPLIED-PATCHES.md` | Append-only applied-patch evidence consumed by release inspection. |
| `scripts/test-apply-patch-ledger.sh` | Focused ZIP contract, isolation, and publication proof. |
| `tools/ScriptedWalkthroughWorkbenchPanel.java` | YAML editor, timeline, playback, and reports. |
| `walkthrough/ResidentWalkthroughCatalog.java` | Immutable resident Showcase source catalog. |
| `walkthrough/ShowcasePresentationAdapter.java` | Exact owner-session Showcase choreography. |
| `walkthrough/WalkthroughPlaybackEngine.java` | Validation, timing, waits, checkpoints, and reports. |
| `walkthrough/TalismanWalkthroughTarget.java` | Safe role/control/state adapter and guided cue route. |
| `tools/MapPerformanceMemorySnapshotSource.java` | Bounded live/file semantic snapshot discovery, validation, de-duplication, and staleness source. |
| `tools/MapPerformanceWorkbenchMemoryPanel.java` | Multi-instance rich Memory host, stale/read-only gating, live controls, and exact snapshot save. |
| `tools/PermanentBranchStateSource.java` | Finder-safe Git execution, Git-owned stale-worktree pruning, Talisman worktree discovery, and exact Seasons/Spelunk main state. |
| `tools/MainLandingHistoryParser.java` | Bounded 24-hour Main commit history grouped by prefixed work-area owner. |
| `tools/PermanentBranchStatePanel.java` | Filterable compact two-row work areas, wrapping row actions, exact task links/status, guarded Git actions, metadata, and Markdown designs. |
| `tools/PermanentBranchVisibilityPreferences.java` | Hidden-row view preferences. |
| `tools/PermanentBranchStatusAppearance.java` | Theme-safe bold work-area and truthful semantic integration-status presentation. |
| `tools/PermanentBranchChangedFilesSource.java` | Bounded read-only working and merge-base-relative changed-path inspection. |
| `tools/PermanentBranchCommitter.java` | Exact reviewed dirty-state parser and isolated-index local-only commit transaction. |
| `services/codex/CodexAppServerClient.java` | Shared injectable bounded JSON-RPC app-server session. |
| `services/codex/CodexExecutableResolver.java` | Override/PATH/macOS-bundle Codex executable resolution. |
| `services/codex/CodexConversationAdapter.java` | Public text-only dedicated Codex conversation seam. |
| `services/codex/package-info.java` | Shared Codex process/protocol and consumer ownership contract. |
| `tools/CodexTaskThreadMapping.java` | Reviewed exact permanent worktree/branch to Codex thread-ID preference. |
| `tools/CodexTaskLinkDiscovery.java` | Paginated unique exact-worktree bulk task-link discovery. |
| `tools/CodexTaskStatusService.java` | Stored/live status plus guarded deferred read-only owner-summary turns. |
| `tools/CodexTaskStatusReport.java` | Immutable streamed and retained task-status presentation state. |
| `tools/CodexTaskChatService.java` | Exact mapped-task chat using idle turns or intentional active-turn steering. |
| `tools/WorkbenchOpenChatService.java` | One dedicated resumable plain-text Codex conversation for Open Chat. |
| `tools/WorkbenchOpenChatDialog.java` | Modeless streamed Open Chat composition, cancellation, and transcript. |
| `tools/WorkbenchProcessEnvironment.java` | Finder-safe child-process PATH plus mandatory Java 21 Gradle-host resolution. |
| `tools/WorkbenchActionStatusArea.java` | Bounded wrapping selectable lifecycle progress and failure-detail presentation. |
| `tools/ProjectZipCreator.java` | Full and compact project-named tracked-HEAD ZIP creation in user Downloads. |
| `tools/PermanentBranchUpdater.java` | Rechecked clean strictly-behind fast-forward and matching push. |
| `tools/ActiveDesignDocumentSource.java` | Exact human-folder Markdown Active Designs reader. |
| `tools/MapPerformanceReliabilityMonitor.java` | Sustained UI-heartbeat target-state classification and recovery. |
| `tools/MapPerformanceDiagnosticCapture.java` | Bounded incident bundle, manifest, and optional follow-up. |
| `tools/MapPerformanceWorkbenchDiagnostics.java` | Atomic read-only external Workbench status snapshot. |
| `tools/MapPerformanceRecentChangesPathResolver.java` | Canonical Workbench checklist path resolution. |
| `tools/MapPerformanceRecentChangesParser.java` | Structured checklist parsing and line ownership. |
| `tools/MapPerformanceManualEntryEditor.java` | Modeless manual-entry editor and image intake. |
| `tools/MapPerformanceComplaintDataStateCapture.java` | Safe async complaint context consumer. |
| `tools/MapPerformanceSwitchboardClarityDialog.java` | Copy-only Switchboard clarity handoff UI. |
| `tools/MapPerformanceChecklistReviewDialog.java` | Modeless checklist review and canonical actions. |
| `tools/MapPerformanceChecklistSemanticAppearance.java` | Shared More Clarity/Wait visual precedence. |
| `tools/MapPerformanceWorkbenchDialogLifecycle.java` | Modeless dialog reuse, bounds, and clamping. |
| `tools/MapPerformanceWorkbenchDialogPreferences.java` | Process-persistent dialog bounds. |
| `tools/MapPerformanceWorkbenchRestarter.java` | Reusable self-invocation and explicit ready handoff. |
| `tools/MapPerformanceWorkbenchSelfUpdater.java` | Canonical-main update/build/verified Workbench replacement sequence. |
| `tools/MapPerformanceWorkbenchBuilder.java` | Build-only Gradle launch-spec boundary for a new Workbench JVM. |
| `tools/MapPerformanceImageViewer.java` | Scrollable full-resolution Workbench attachment viewer. |
| `tools/WorkbenchDictation.java` | Shared off-EDT Workbench macOS Dictation launcher. |
| `ui/macos/MacSystemDictation.java` | Optional standard macOS Dictation startup bridge. |
| `ui/macos/MacSystemDictationSession.java` | Identity-safe isolated-capture Dictation session boundary. |
| `ui/macos/MacLocalFileApplicationGateway.java` | Exact macOS app/file handoff adapter. |
| `tools/MapPerformanceWorkspaceUpdater.java` | Scoped canonical-state preservation around Workbench updates. |
| `src/main/java/com/moondance/talisman/app/tools/MacMemoryPressureSampler.java` | Throttled macOS system memory, swap allocation, and live swap-I/O sampler for the Performance Workbench. |
| `src/main/java/com/moondance/talisman/app/tools/MacProcessMemorySampler.java` | Throttled bounded matching-PID RSS, footprint/peak, and swapped-memory sampler for Workbench Copy All. |
| `src/main/java/com/moondance/talisman/app/mapeditor/TerrainRegion.java` | Map region/layer model and checked exact-Layer replacement boundary. |
| `mapeditor/GridGeometry.java` | Logical cell geometry with streamed viewport iteration for rendering. |
| `mapeditor/RegionCellGeometry.java` | Canonical physical square-cell predicate and tolerance. |
| `mapeditor/LegendLayerProperties.java` | Persisted generated Legend content and normalized geometry. |
| `src/main/java/com/moondance/talisman/app/mapeditor/RegionLayerRaster.java` | Typed raster payload boundary. |
| `mapeditor/SavedSelectionTarget.java` | Saved-Selection Layer/addressing identity for compatible restore. |
| `mapeditor/RasterPageHandle.java` | Exact payload-free durable raster content identity. |
| `src/main/java/com/moondance/talisman/app/mapeditor/ImageLayerRaster.java` | Owned compressed image Layer payload. |
| `src/main/java/com/moondance/talisman/app/mapeditor/HeightLayerRaster.java` | Unsigned-16 height payload. |
| `mapeditor/HeightRasterSnapshot.java` | Immutable exact-raster document-worker delta. |
| `mapeditor/RenderInvalidation.java` | Shared lightweight visible 2D/3D revision identity. |
| `database/projectstorage/ProjectRasterContentStore.java` | Exact raster admission, page-in, and residency leases. |
| `ui/mapeditor/AuthoritativePlaceRasterResidency.java` | Authoring/Control child-parent-root page protection. |
| `mapeditor/HeightmapRasterPngCodec.java` | Lossless unsigned-16 Heightmap PNG interchange with exact domain and elevation metadata. |
| `mapeditor/source/HeightmapRasterExportService.java` | Lossless managed-only Heightmap Place-Source artifact admission. |
| `mapeditor/HeightWaterLevelAdjustment.java` | Water shoreline-derived ground leveling. |
| `mapeditor/GeologyClass.java` | Closed Geo Tables class codes, labels, and categorical palette. |
| `mapeditor/GeologyCategoricalMapImporter.java` | Strict compatible categorical-image decoder. |
| `mapeditor/EstimatedGeologyGenerator.java` | Deterministic broad-province geology estimator. |
| `mapeditor/GeologyGenerationDetail.java` | Geo detail choices and provenance migration. |
| `mapeditor/GeologyWaterExclusion.java` | Typed Water Mask and terrain-water no-data boundary. |
| `mapeditor/RegionMaskKind.java` | Persisted Water, Building, Road, Forest, and generic Masks. |
| `src/main/java/com/moondance/talisman/app/mapeditor/TerrainLayerRaster.java` | Byte terrain-class payload. |
| `mapeditor/TerrainHeightFieldCapture.java` | Shared bounded Control/Player 3D height capture. |
| `mapeditor/TerrainTableGenerator.java` | Deterministic Terrain request/context, five feature scales with legacy migration, and optional exact Water-mask constraint. |
| `mapeditor/GeographyAcquisitionMode.java` | Shared Generate/Capture-from-Parent/Import candidate route identity. |
| `mapeditor/TerrainAcquisitionCandidate.java` | Detached Terrain candidate and exact source ancestry provenance. |
| `mapeditor/GeologyAcquisitionCandidate.java` | Detached Geo Tables candidate and exact source ancestry provenance. |
| `mapeditor/PiTerrainTableGenerator.java` | Pi-backed domain-warped Terrain fields with smooth altitude/tree-line affinity and mask-boundary weighting. |
| `src/main/java/com/moondance/talisman/app/mapeditor/GeologyLayerRaster.java` | Byte geology-class payload. |
| `mapeditor/PiGeologyTableGenerator.java` | Pi-backed Geo category adapter. |
| `mapeditor/GeographyOperationMode.java` | Versioned Refine, Replace, and Fill Gaps semantics. |
| `mapeditor/GeographySurfaceGenerator.java` | Scale-sensitive Java Terrain/Geo Tables patina. |
| `mapeditor/GeographySurfaceCandidate.java` | Exact revision-bound detached Surface preview. |
| `mapeditor/SurfaceAcquisitionCandidate.java` | Exact revision-bound Surface import and ancestral-capture candidate. |
| `mapeditor/GeologyGenerationPlan.java` | Immutable EDT-captured Geo Tables worker input. |
| `mapeditor/GeographySurfacePlan.java` | Immutable EDT-captured Surface worker input. |
| `src/main/java/com/moondance/talisman/app/mapeditor/MapWorkspaceSnapshot.java` | Persisted map state. |
| `mapeditor/script/ScriptRegistry.java` | Bundled-plus-user immutable Script catalog and runtime-specific resolution. |
| `mapeditor/script/ScriptPackageManager.java` | Bounded staged folder/ZIP/Python package admission, discovery, replacement, and removal. |
| `mapeditor/script/ExternalJavaScriptCompiler.java` | Content-addressed imported-Java source compilation cache. |
| `src/main/java/com/moondance/talisman/sdk/script/TalismanJavaScript.java` | Public API-version-1 entry contract for trusted Java Script Packages. |
| `ui/mapeditor/ScriptWindowDialog.java` | Place-pinned searchable Script Manager and package actions. |
| `mapeditor/script/ScriptExecutionRevisionToken.java` | Async Script dependency guard. |
| `mapeditor/script/AuthoringScriptOperationService.java` | Owner-private manifest Script lifecycle. |
| `mapeditor/script/MapScriptExecutionService.java` | Frozen-package Java/Python executor. |
| `mapeditor/script/JavaBlurHeightmapScript.java` | Dependency-free Java Gaussian Heightmap blur. |
| `mapeditor/script/JavaGuessHeightmapScript.java` | Dependency-free image-to-Heightmap calculation. |
| `mapeditor/script/JavaExtractWaterScript.java` | Dependency-free blue-water extraction to a managed PNG Source. |
| `development-atlas/Development-Architecture-Atlas.md` | Atlas authority. |
| `development-atlas/action-routing.md` | Action routes. |
| `development-atlas/events-threading.md` | Events and threads. |
| `development-atlas/persistence-and-content.md` | Storage lifecycle. |
| `development-atlas/focused-tests.md` | Focused gates. |
| `development-atlas/validation.md` | Validator workflow. |
| `src/test/java/com/moondance/talisman/app/architecture/` | Atlas validator tests. |
| `src/test/java/com/moondance/talisman/app/architecture/OperationsAtlasContractValidator.java` | Pure-JDK structural and cross-record Operations Atlas validator. |
| `src/test/js/operations-atlas-current-truth.test.mjs` | Focused TA-02 resolver, conflict, traversal, and evidence-class proof. |
| `src/test/java/com/moondance/talisman/app/core/bus/BusTest.java` | Core delivery and ordering proof. |
| `src/test/java/com/moondance/talisman/app/core/bus/BusDeliveryPolicyTest.java` | Policy lifecycle proof. |
| `context-management/src/main/java/com/moondance/talisman/context/` | Toolkit-neutral context request,
  provider composition, immutable snapshot, strict canonical payload codec, monitor model, TaliTalk
  handoff, and application action-coordinator package. |
| `context-management/src/test/java/com/moondance/talisman/context/` | Exact isolated first-slice context,
  policy, monitor, response-contract, canonical payload, TaliTalk handoff, and contextual-action proof. |
| `ui/mapeditor/MapEditorScopeState.java` | Pinned Layer editor, ordered working/Selection history, categorical and image-raster brush operations, checked directional Heightmap transactions, and publication owner. |
| `mapeditor/ImageBrushEngine.java` | Immutable Surface/Image Overlay Paint, neighborhood, shape, falloff, and Selection-clipped raster operations. |
| `mapeditor/MapRegionResampler.java` | Exact sampling and area-aware finer-to-coarser Heightmap aggregation. |
| `ui/mapeditor/MapEditorLayersInspector.java` | Layers/Place/Sources/Flavour inspector composition. |
| `ui/mapeditor/MapEditorLayerStackView.java` | Independent Layer browsing/visibility/opacity, ordering, creation/reset, and explicit pinned-Layer Edit. |
| `ui/mapeditor/MapEditorLayerEditorRegistry.java` | Explicit in-place editor capability and provider identity by Layer kind. |
| `ui/mapeditor/MapEditorActiveLayerPanel.java` | Compact retained-2D projection and exact Accept route for the scope-owned editor. |
| `ui/mapeditor/MapEditorHeightmapEditorPanel.java` | In-place nine-operation Heightmap provider; contextual parameters and working-only Apply route. |
| `ui/mapeditor/MapEditorGeologyEditorPanel.java` | In-place Geology acquisition, generation, category-editing, and working-only Apply route. |
| `ui/mapeditor/MapEditorTerrainEditorPanel.java` | In-place Terrain acquisition, Water-mask generation, land-category editing, and working-only Apply route. |
| `ui/mapeditor/MapEditorSurfaceEditorPanel.java` | In-place Surface import, ancestral capture, exact-input generation, and shared working-pixel editor route. |
| `ui/mapeditor/MapEditorImageOverlayEditorPanel.java` | In-place Image Overlay accepted Source status and shared working-pixel editor route. |
| `ui/mapeditor/MapEditorImagePaintPanel.java` | Shared pinned Surface/Image Overlay pixel-operation controls and exact working-raster command route. |
| `ui/mapeditor/MapEditor2dMemoryTelemetry.java` | Managed Authoring display-cache access/limit plus mask-alias, height-derivative, and composition-job telemetry. |
| `ui/mapeditor/MapEditorSnapshotMemoryTelemetry.java` | Pending child-backup telemetry. |
| `mapeditor/document/MapDocumentService.java` | Revision-aware structural/display/Height-delta coalescing worker and durable-first owned Source reads. |
| `ui/mapeditor/RecentPlacesHistory.java` | Scope-keyed shared Authoring/GM recent-Place preference owner. |
| `ui/mapeditor/MapEditorParentContextView.java` | Shared Parent Overview identity and exact 2D/3D navigation owner. |
| `ui/mapruntime/MapRuntimeBus.java` | Scoped Control commands and revisioned Overview transfer. |
| `ui/mapeditor/MapEditorRenderSupport.java` | Bounded 2D and attached-3D Authoring working-Layer presentation. |
| `ui/mapeditor/PlaceOverviewPresentationStore.java` | Product/scope overview mode, pan, zoom, and divider preferences. |
| `ui/mapeditor/MapEditorPlaceOverview3D.java` | Overview 3D camera, progress, semantic-hit stamp, FX lifecycle, and stale-scene owner. |
| `ui/mapeditor/AuthoringProductDataStateCaptureAdapter.java` | Privacy-safe exact-frame Authoring complaint snapshot adapter. |
| `ui/mapeditor/MapEditorHeightmapExportCoordinator.java` | Scope-owned checked Heightmap Place-Source naming, conflict, history, and export workflow. |
| `ui/mapeditor/MapEditorWindowBus.java` | Authoring-owned auxiliary window commands from shell menus. |
| `ui/mapeditor/GeoTablesAcquisitionDialog.java` | Themed Geo Tables acquisition. |
| `ui/mapeditor/GeographyCandidatePreview.java` | Shared generated-candidate preview and Accept owner. |
| `ui/mapeditor/TerrainAcquisitionDialog.java` | Terrain import/generation and explicit exact-Place Water-mask presentation. |
| `mapeditor/SelectionEditModel.java` | Exact-mask Selection geometry and bounded controls. |
| `mapeditor/ContourSelectionRasterizer.java` | Contour-to-Selection exact pixel fill and parity. |
| `ui/mapeditor/MapEditorTopBar.java` | Ordered adaptive-group Authoring toolbar, four spatial tools plus Micro, and authoritative unified-edit controls. |
| `ui/mapeditor/AdaptiveTopToolbarLayout.java` | Experimental single-line top-toolbar group compaction and most-recently-used expansion owner. |
| `ui/mapeditor/MapEditorCanvas.java` | Map hits, padding deselect, unified Selection, and Contour route. |
| `ui/mapeditor/MapEditorSurfaceContextMenu.java` | Exact-hit popup and directional parent/child Heightmap action owner. |
| `ui/mapeditor/MapSpaceMetadataPanel.java` | Shared selectable Space hover metadata consumer. |
| `mapeditor/RegionsLayerProperties.java` | Persisted independent 2D/3D child-footprint appearance. |
| `ui/mapeditor/MapEditor3DCanvas.java` | Authoring Arena3D surface; retained-rehost-aware FX scene lifecycle. |
| `ui/mapeditor/Map3DSceneCapture.java` | Canonical general/runtime capture and transient Authoring working-Layer 3D projection. |
| `ui/mapeditor/MapEditor3DTopBar.java` | Pane-local Authoring Arena3D controls; the workspace row owns the shared six-choice display selector. |
| `ui/mapeditor/MapLegendRenderer.java` | Shared grid-distance/adaptive-elevation Legend painter. |
| `ui/mapeditor/LegendNumberFormatter.java` | Locale-aware integral/fractional Legend labels. |
| `ui/mapeditor/LegendScaleTicks.java` | Exact grid-distance and adaptive elevation tick policy. |
| `ui/mapruntime/MapCanvasPresentation.java` | Runtime map projection with axis-specific cell scale. |
| `ui/mapruntime/RuntimeCanvasView.java` | Runtime 2D decoded-image cache, GM Gradient, exact combatant reveal, and Swing presentation owner. |
| `ui/mapruntime/Runtime2dMemoryTelemetry.java` | Passive runtime image, static-token, temporary-map, limit, and build-job telemetry. |
| `ui/mapruntime/ArenaObject2DRenderer.java` | Runtime object painter and process-lifetime static token-image cache. |
| `ui/mapruntime/MapRuntimePresentationProjector.java` | Pinned projection and canonical Place ordering. |
| `ui/mapeditor/Map3DViewerDialog.java` | Arena3D window, frozen Spawn, reusable programmed views, refresh, and scope-lease owner. |
| `ui/mapeditor/GmRuntime3DCanvas.java` | GM Control Arena3D surface; exact-Place programmed-view host and visible-only FX scene lifecycle. |
| `ui/mapeditor/viewer3d/Arena3DSceneHost.java` | Shared revision-gated nonblank Swing loading holder above an Arena3D JFX panel. |
| `ui/mapeditor/viewer3d/ProgrammedViewControls.java` | Reusable six-slot exact-Place camera controls with overwrite and stale-capture guards. |
| `ui/mapeditor/viewer3d/TerrainMeshBuilder.java` | Shared exact-top and independent slope-aware side-island terrain UV builder. |
| `ui/mapeditor/viewer3d/TerrainTextureAtlasLayout.java` | Bounded deterministic local-evidence terrain atlas packing and worker-side bake. |
| `ui/mapeditor/viewer3d/TerrainRenderData.java` | Exact cached mesh plus freshly baked current-composition atlas pair. |
| `ui/mapeditor/viewer3d/TerrainRenderCache.java` | Bounded exact-Heightmap geometry reuse across texture-only composition changes. |
| `ui/mapeditor/viewer3d/TerrainSurfaceRevision.java` | Immutable final-composite and Heightmap revision pair. |
| `ui/mapruntime/GmRegionNavigationSelection.java` | Stamped private GM child-Region navigation target. |
| `ui/mapeditor/viewer3d/Map3DSceneController.java` | JavaFX scene, reusable terrain mesh, texture, camera, hit-test, and overlay owner. |
| `ui/mapeditor/viewer3d/ConstructedSceneSnapshot.java` | Immutable persistent Barrier/Portal/Light 3D capture. |
| `ui/mapeditor/viewer3d/ConstructedSceneGeometryBuilder.java` | Pure wall/opening/Light scene geometry conversion. |
| `ui/mapeditor/viewer3d/Map3DMemoryTelemetry.java` | Passive per-controller 3D resource and lifecycle telemetry. |
| `ui/mapeditor/viewer3d/Map3DContextMenuGesture.java` | Exact-terrain scene-stamped secondary-click DTO. |
| `ui/mapeditor/viewer3d/Map3DCohortOverlay.java` | Scene-stamped cohort bounds and handle. |
| `ui/mapeditor/viewer3d/Map3DCohortDestinationGesture.java` | Accepted cohort destination. |
| `ui/mapeditor/viewer3d/Map3DTerrainHover.java` | Scene-stamped exact terrain-hit callback. |
| `ui/mapeditor/viewer3d/RegionFootprintSnapshot.java` | 3D child-Region capture. |
| `ui/mapeditor/viewer3d/TerrainPerimeterMeshBuilder.java` | Raised child-Region solid geometry. |
| `ui/mapruntime/GmRuntime3DTopBar.java` | Runtime Arena3D camera plus canvas-owned programmed Views; no local Follow/Spawn route. |
| `ui/mapruntime/PlayerScreenWindowLauncher.java` | Distinct Player popup route; never browser lifecycle or GM component inspection. |
| `ui/mapruntime/PlayerScreenView.java` | Display-only stamped Follow consumer and legacy independent Spawn shell. |
| `ui/mapruntime/PlayerFollowViewState.java` | Exact Place/document-stamped Control mode/layout value for local Follow. |
| `ui/mapruntime/PlayerRuntime3DCanvas.java` | Player-safe Arena3D and Control-camera follower. |
| `ui/mapruntime/PlayerScreenStateStore.java` | Per-popup Player mode, Place, viewport, camera, and layout preferences. |
| `ui/mapruntime/RuntimeMap3DRegionPresentation.java` | Immutable runtime-only Arena3D Region input. |
| `ui/mapruntime/RuntimeMap3DSceneCapture.java` | Runtime-only projection-to-scene capture. |
| `ui/mapruntime/TemporaryBattleMapMaterializer.java` | Exact runtime-only combat crop and resident admission. |
| `ui/mapruntime/TemporaryBattleMapPresentation.java` | Shared GM 2D/Arena3D temporary combat presentation. |
| `ui/mapruntime/GmRandomEncounterPanel.java` | Legacy wandering encounter plus guarded prepared-admission
  projection. |
| `ui/mapruntime/GmArenaObjectRepositoryPanel.java` | Repository activation, exact folder deletion, and placement drag source. |
| `ui/mapruntime/GmFocusedDeleteSupport.java` | Exact-focus semantic Delete binding for GM views. |
| `ui/mapruntime/ArenaObjectCreatureImportResolver.java` | Exact creature/stat/size/movement/token adapter. |
| `ui/mapruntime/ArenaObjectCreatedThingPresentationResolver.java` | Worker-only checked canonical-OBJ/texture association resolution and media admission. |
| `ui/mapruntime/GmControlWorkspaceSelection.java` | Nested PLAY/EDIT workspace activation helper. |
| `ui/mapruntime/GmPlayerScreenTopBar.java` | GM header, repeatable Follow action, stamped shell producer, and Player manager entry. |
| `ui/mapruntime/GmPlayLayersPanel.java` | Embedded Play/Combat/Background disclosure and exact visibility commands. |
| `ui/mapruntime/GmCombatPanel.java` | Control Play combat lifecycle, roster reveal/inspection, Move, and activation navigation. |
| `ui/mapruntime/CombatRuntimeBehavior.java` | Typed combat command-to-session adapter and state publisher. |
| `ui/mapruntime/PlayerPresentationManagerPanel.java` | Manager actions and resident tab leases. |
| `ui/mapruntime/PlayerPresentationObservationSession.java` | Payload-free manager-tab lease. |
| `ui/mapruntime/PlayerPresentationInvitationQrWindow.java` | Reused GM-private Internet invitation QR surface. |
| `ui/mapruntime/PlayerPresentationFramePublisher.java` | Newest-only Local/relay frame publisher. |
| `ui/mapruntime/PlayerPresentationFrameRenderer.java` | Player-safe Local and Internet PNG composition. |
| `ui/mapruntime/BrowserPlayerJoinWindow.java` | Modeless LAN Player-browser QR join surface; token remains encoded, not printed. |
| `ui/mapruntime/GmControlNavigationActions.java` | Navigation adapter for GM Grid and Layers. |
| `ui/mapruntime/GmProductDataStateCaptureAdapter.java` | Privacy-safe retained GM complaint snapshot adapter. |
| `ui/mapruntime/GmControlRenderProjectionSource.java` | Pull-only existing-Control 2D/3D adapter with
  exact-current owner/manifest rechecks, defensive payload copies, and close invalidation. |
| `ui/windows/ProductMapViewBehavior.java` | Role-local 2D/3D retention, hidden-content camera join,
  atomic scalar capture, and exact-coherent immutable GM renderer handoff. |
| `ui/windows/ProductMapViewCaptureState.java` | Presentation-free Place/revision/camera scalar identity. |
| `database/randtable/encounter/RandomEncounterCatalog.java` | SRD wandering-table catalog. |
| `database/randtable/encounter/RandomEncounterProvenance.java` | Encounter pi replay identity. |
| `database/randtable/generate/PiRandomSource.java` | Portable pi random-number source. |
| `database/randtable/encounter/CreatureEcologyCatalog.java` | Complete SRD ecology lookup. |
| `ui/fx/Arena3DMouseControls.java` | Shared middle-pan and unclaimed-secondary 3D pointer policy. |
| `src/main/java/com/moondance/talisman/app/ui/fx/JavaFxRuntime.java` | Off-EDT FX startup. |
| `src/main/java/com/moondance/talisman/app/tools/Arena3DLauncher.java` | Arena3D launcher. |
| `src/main/java/com/moondance/talisman/app/ui/mapeditor/AuthoringControlTooltips.java` | Authoring-wide descriptive tooltip policy and dynamic-control coverage. |
| `src/main/java/com/moondance/talisman/app/database/userassets/UserAssetsService.java` | Canonical asset API and association read projection. |
| `database/userassets/ManagedAssetDisplayNames.java` | Presentation-only human asset names and deterministic collision labels. |
| `database/userassets/ManagedAssetNamePreview.java` | Read-only exact name/collision checkpoint without metadata mutation. |
| `src/main/java/com/moondance/talisman/app/database/userassets/CreatedThingPresentationReadinessService.java` | Private exact-ID Token/Icon/Model readiness projection. |
| `database/userassets/CreatedThingPresentationMediaService.java` | Bounded presentation payload API. |
| `database/userassets/UserAssetsRepository.java` | Source persistence and exact provenance repair. |
| `src/main/java/com/moondance/talisman/app/ui/assets/UserAssetsWorkspaceState.java` | Asset UI state. |
| `ui/assets/UserAssetsMonitorBehavior.java` | Assets worker command coordinator, including off-EDT file ingestion. |
| `ui/assets/UserAssetsWorkspaceTreeView.java` | Indexed Media-tree and managed-collection patch owner. |
| `ui/assets/UserAssetSourcesDialog.java` | Named source rename/location/stop/restore UI. |
| `ui/assets/UserAssetsProductDataStateCaptureAdapter.java` | Safe Assets complaint adapter. |
| `src/main/java/com/moondance/talisman/app/ui/assets/UserAssetsTreePreferences.java` | Stable expansion-state preference codec shared by UA trees. |
| `build.gradle` | Dependencies, entry class, test, coverage, and packaging tasks. |
| `tasks/TODO.md` | Prioritized work queue. |
| `tasks/Doing.md` | Current work and interruption-recovery breadcrumbs. |
| `tasks/Done.md` | Completed-work archive. |
| `tasks/CODEX-USAGE-LEDGER.md` | Append-only event-boundary shared-account usage observations. |

| `src/main/resources/app/talisman-forest/v1/` | Public opt-in browser Forest tokens, fonts and specimen. |
| `src/test/js/talisman-forest-browser.test.mjs` | Java palette parity and offline font contract proof. |
| `src/test/js/talisman-forest-browser-visual.test.mjs` | Isolated specimen layout and font fallback proof. |

## Verification routing

- Java implementation default: run the full Java 21 headed command specified in `AGENTS.md`.
- Bug mode: run only focused tests until the user requests the broader test-and-fix pass.
- Documentation-only changes: validate links, line length, and `git diff --check`; no Java build is
  required unless the documentation affects generated or runtime behavior.

## Geography GCP-01 research tooling and input-proof route

- `design/Active Designs/Talisman Geography/GCP-01-INPUT-PROOF.md` records exact evidence and blockers.
- `scripts/geography/acquire-gcp01.py` acquires fixed raw proof into one new folder, never Seasons.
- `scripts/geography/test-gcp01-input.sh` runs only the pure numerical package's headless checks.
- `scripts/geography/test_gcp01_acquisition.py` owns offline helper safety/recipe tests.
- `src/test/resources/fixtures/geography/SYNTHETIC-GCP-01.properties` is explicitly invented test data.
- `NumericalElevationInputTest` is the product-toolchain adapter for those shared numerical checks.


## Geography real-input and height-exchange proof

- `design/Active Designs/Talisman Geography/GCP-02-HEIGHT-AND-PACKAGE-PROOF.md` owns current proof/gates.
- `scripts/geography/test-gcp02-height.sh` runs pure-JDK height, PNG and envelope checks only.
- `scripts/geography/verify-gcp01-independent.py` independently audits exact public TIFF/PNG samples.
- `src/test/resources/mapimport/earth/gcp01-real-small/` retains the real small TIFF and exact evidence.
- `RealElevationInputTest`, `EarthHeightPreparationTest`, `EarthPackageArchiveTest`,
  `EarthPackageHeightTest` and `EarthPackageWireTest` are the new root-scoped configured checks.

## Geography GCP-03 — authored Water and shared source preview

| Path | Ownership and purpose |
| --- | --- |
| `src/main/java/com/moondance/talisman/app/mapimport/earth/AuthoredWaterRecipe.java` | Bounded immutable editable banks; revisioned source-area polygon semantics. |
| `src/main/java/com/moondance/talisman/app/mapimport/earth/AuthoredWaterRasterizer.java` | Deterministic exact binary area-cell coverage; no height mutation or hydrology inference. |
| `src/main/java/com/moondance/talisman/app/mapimport/earth/AuthoredWaterMask.java` | Immutable Water coverage and canonical image projection. |
| `src/main/java/com/moondance/talisman/app/mapimport/earth/EarthTerrainPreview.java` | Shared CPU mesh query and bounded registered Height/Water preview. |
| `src/main/java/com/moondance/talisman/app/mapimport/earth/EarthPackageWaterValidator.java` | Exact re-materialized Water and preview verification after independent height proof. |
| `src/main/java/com/moondance/talisman/app/mapeditor/TerrainAreaCellSampler.java` | Opt-in area-cell-to-outer-mesh capture; ordinary Place vertex sampling unchanged. |
| `testData/geography/grand-canyon/water-recipe.json` | Public authored real-canyon draft, not observed hydrography or private world data. |
| `scripts/geography/test-gcp03-water-preview.sh` | Focused standalone new checks and optional read-only real-input preparation. |
| `scripts/geography/prepare-gcp03-probe.py` | Test-only bounded fixture literal adapter, not the product JSON decoder. |
| `scripts/geography/test_gcp03_probe.py` | Six offline literal-adapter boundary checks. |
| `scripts/geography/gcp03-configured-proof.init.gradle` | Root configured read-only exact-package admission probe. |
| `design/Active Designs/Talisman Geography/GCP-03-WATER-AND-PREVIEW-PROOF.md` | Current proof, exact remaining configured operations and later-stage gate. |

The existing Earth package codec now exposes a checked prepared-source query. Its result is not a Place,
source-trust authority, host context or import authorization. The source-only Water editing engine is
implemented; native editing and import controls belong to the later authorized interface body.

## Forest shared display font

- `src/main/java/com/moondance/talisman/app/ui/theme/TalismanForestTypography.java` loads the bundled
  Morris Roman Black OTF for the shared Java display role; browser WOFF2 comes from that same face.

## BERT Control implementation and proof

| Path | Ownership and purpose |
| --- | --- |
| `design/Active Designs/BERT — Make JavaScript__/IMPLEMENTATION.md` | Permanent browser owner Control composition and saved-layout baseline. |
| `design/Active Designs/BERT — Make JavaScript__/TEST-PLAN.md` | Cumulative Control client, layout, shell, page and registration proof; remaining live gates. |

### Atlas Project Kit lifecycle review
- `design/Active Designs/LIFECYCLE-INVENTORY.md` — document-by-document Active Designs lifecycle classification.
- `design/Active Designs/Talisman Architecture/ACTIVE-DESIGNS-MIGRATION-MANIFEST.md` — preservation-first proposed moves and gates.
- `development-atlas/review/index.html` — offline, read-only Project Kit Architecture Atlas review surface; Markdown architecture remains authoritative.
