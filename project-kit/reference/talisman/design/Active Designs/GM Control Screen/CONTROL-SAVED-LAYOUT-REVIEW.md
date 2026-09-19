# GCS-01 — Retained display and saved Control layout

Owner: Make JavaScript__. Patcher: __Make Javascript. Date: 2026-09-08.
Status: reviewed source increment for local integration; not landed, served or full GCS completion.

## Exact source and scope

Input: GCS-01-Full-Control-Fresh-Source-2026-09-08.zip.
Baseline: `e70eb018457221d822cfe51a2f98419e56cfe755`.
Archive SHA-256: `95b9c87a8d9d6dffcb698f842378f3184208394cfe6bc2bf8b81872ceb43aaa8`.
All 3,058 supplied source digests, archive CRC and supplemental digests were checked. No superseded
implementation was inspected or applied. The continuation explicitly permits reviewed increments.

This increment connects explicit Save layout / Restore layout to the existing admitted durable preference
route and shared Shelf composed-memory v2. The seven display arrangements, public focus, real left Box
weights, display divider and Overview metadata disclosure are complete presentation values. The same
opaque mount nodes are retained, including the single Overview. The actual production content remains
P0's current-Place summary and public Object link until renderer/asset handoffs arrive. No fixture data
is added to production and no blank mount is described as a completed map.

The separate Control preview version is removed. A shared site release is not hardcoded or taken from
Tassy's host label. The proposed Factory-owned version module is absent from this source, so its actual
public receipt is required before adding a consumer.

## Contracts read and applied

- Root AGENTS.md, AGENTS.project.md and AGENTS.index.md: normal route, exact source, descriptive body,
  focused verification, shared Forest tokens/typography and no independent semantic authority.
- Development-Architecture-Atlas.md: current service matrix; source and focused tests outrank dated designs.
- service-architecture.md: immediate presentation controls need no artificial bus; TSR-05/TSR-07 own
  complete envelopes, admission and storage stay with the Application Server.
- action-routing.md: Control is a consumer, not a runtime/renderer/Context authority.
- events-threading.md: request generation, stale/close rejection, no EDT/JavaFX synchronous bridge,
  immutable results and owner-specific cleanup.
- persistence-and-content.md: person/context-bound opaque whole-value backend, optimistic storage revision,
  shared no-write v1 migration, no domain or Context in preferences.
- focused-tests.md and validation.md: smallest route/store/projection checks; separate synthetic, source,
  native-DOM, real HTTP, Gradle and physical-device evidence.
- GCS-01-IMPLEMENTATION-BRIEF.md and UNIFIED-CONTROL-SCREEN.md: one /control/ page, three retained mounts in seven arrangements,
  independent Shelves, no authoring from saved open state, public components only and honest omissions.
- Talisman Shelf__ SHELF-CONTRACT-V2.md sections 5.1/5.2 and public shelf-presentation-memory.js:
  exact adapter keys, separate state definition, whole-envelope revision rules and no second store.
- Application Server package-info.java, GmControlApplicationServerGateway.read/write and Controller dispatch:
  admitted caller/application binding, exact v1/v2 keys, all-string bounded write, opaque preference storage.

## Reviewed route and lifecycle

Page presentation -> Control layout session -> actual shared Shelf v2 store -> fixed Control layout
transport -> existing Control bootstrap/fetch -> ApplicationServerController -> existing GM-admitted
GmControlApplicationServerGateway -> immutable FOUND/EMPTY/SAVED/CONFLICT envelope and revisions.

The layout adapter borrows one exact bootstrap object and compares capability, server instance/generation,
manifest version/digest, workspace ID/generation on every successful response. Mutation sends the existing
CSRF, server/manifest and idempotency headers. No browser principal or application-binding override is sent.
The GM cookie remains HttpOnly and host-owned; no browser role or saved flag can create admission.

Only explicit Save writes. Initial restore reads v2, and only the shared store may read legacy v1 when v2
is empty. Invalid/non-empty v2 never falls back. Compatible legacy state migrates in memory without a write
or inherited storage revision; Control also rejects disabled views or inconsistent legacy/default selection.
Conflicts and unknown write outcomes never merge or automatically retry. The landed gateway write method
has no idempotency-key argument and returns optimistic conflict rather than the same receipt on replay;
this client therefore requires explicit read-back before a new Save instead of claiming receipt replay.

All browser state mutation stays on the page event loop. No backend worker, bus, session, provider, clock,
EDT/JavaFX bridge, renderer method or application cancellation is created. Busy state is synchronous and
uses a typed operation indicator, stable captions and a reduced-motion-aware spinner. New local intent
wins over a pending restore. A pending save cannot claim later edits were saved. Read replies are bounded
while streaming to 256 KiB UTF-8; writes are limited to the existing 64 KiB request boundary.

Disposal invalidates the lease and aborts only its pending fetch. It does not cancel or roll back a host
preference write. Page disposal retains the existing client/shell cleanup. Bootstrap replacement or GM
denial clears the private Place display through the existing page error route. Public focus state contains
only declared Shelf label/Box IDs, never private descendant values. Restoration validates before DOM
change, cannot open or close an admitted editor, and cannot revive disposed state from a layout callback.

## Findings corrected before delivery

| Severity | Exact source location | Concrete failure and correction |
| --- | --- | --- |
| P2 | control-layout.js, createControlLayoutSession | Passing lease helpers to the public store violated its exact read/write-only adapter contract. Initial focused execution rejected it; the caller now supplies only those two functions and retains lifecycle outside Shelf. |
| P2 | control-shell.js, restoreSavedLayout | Closing the display by saved state could strand focus inside hidden content. Capture the previous public region and return focus only when it becomes hidden. External user focus is not stolen. |
| P2 | control-shell.js, restoreSavedLayout focus-only and metadata paths | Focus-only preferences were skipped as geometry no-ops, and collapsing metadata could hide the focused control. Remember declared focus without moving it or requesting resize; return hidden metadata focus to its owned summary. |
| P2 | control-shell.js, getSavedLayout and root focusin | Focusing Save could erase the last meaningful Shelf focus. Remember only the exact public label/Box; closed/hidden Box focus is normalized and private child state is never read. |
| P2 | control-shell.js, restoreSavedLayout | Identical restore unnecessarily notified renderer resize. Compare supported presentation first; no-op returns without layout notification. Hidden/inert/unavailable component and editor restoration rejects atomically. |
| P2 | control-client.js, responseText | A post-text length check allocated an oversized response first. Use bounded streamed UTF-8 decoding/cancellation, retaining a byte-counted fallback for response implementations without a reader. |
| P2 | control-layout.js, failure | An owner resize callback could throw after placement; claiming the old layout was unchanged would be false. The error now asks the user to review the current layout. Pre-validation failures still install nothing. |
| P2 | page-manifest.yaml and ApplicationServerManifestStoreTest | New module/capability references and changed source digests require one coherent registration. Register the unchanged public Shelf module in place, advance manifest epoch/page revision, and update only affected Control pins plus an exact private-capability test. The input HTML test digest was already stale; only that Control pin is aligned, with no HTML change. |

Locations above are in src/main/resources/app/control-screen/ unless the filename is page-manifest.yaml
(src/main/resources/app/application-server/) or ApplicationServerManifestStoreTest.java
(src/test/java/com/moondance/talisman/app/services/app/applicationserver/).

## Focused executed evidence and limits

- Node: control-client.test.mjs 7/7 retained; control-layout.test.mjs 14/14 new cases. These execute the
  actual shared validators/store and Control transport/session with synthetic fetch and shell fixtures.
- Native Chromium, explicit offline mode: control-shell.test.mjs reports 17/17 behavioral cases; the
  four control-page.test.mjs flows pass using production page/module bodies, CSS and synthetic HTTP replies.
- Assembled screenshots reviewed at 1600, 1024, 768 and 360 pixels. Left split, Home, saved-layout controls,
  bold shared typography and semantic color are readable, without horizontal overflow. Touch targets
  are at least 44px. The touch fixture saves with crypto.subtle absent, without a secure-context dependency.
- Normal URL-based browser tests were attempted: Page.goto returned net::ERR_BLOCKED_BY_ADMINISTRATOR.
  Policy was not changed. Offline mode injects CSS/local Blob module imports and fake transport; it is
  not proof of served imports, CSP, cookies, real GM admission, real maps, persisted state-root restart,
  WebKit or a physical iPad. No font file is added to the patch.
- Java 21 is available; Java 25 is absent. The required offline Gradle Atlas task cannot obtain its
  uncached Gradle 9.1 distribution (UnknownHostException: services.gradle.org). The Java manifest tests
  are supplied and digest-aligned, not claimed compiled or executed here. No broad suite substituted.
- The actual repository Atlas validator is also compiled separately on Java 21 and compared on untouched
  reference versus patched source. Both have the same three baseline issues: two incomplete Java index
  paths for GmControlApplicationServerGateway/GmControlApplicationServerGatewayTest, and the unavailable
  workstation path at AGENTS.project.md:41. No new validator issue; not a passing Gradle task.
- Final replay checks every changed path and every untouched reference digest, not merely patch syntax.
  Its initial reference check caught three archive-listed icons excluded by gitignore during synthetic Git
  initialization. Their verified original ZIP bytes were restored to the verification copy only; no icon
  or original source was changed. Every one of the 3,058 reference files is now checked.
  The separate delivery verification manifest contains exact paths/checksums and replay results.

## Exact remaining owner gates

1. Vera: public browser renderer module/resources and exact mount/update/resize/dispose signatures over
   GmControlRenderProjection. Existing opaque Control mounts and onLayout notification are ready; do not
   invent a component method or reach into Factory/Player implementation.
2. Tassy: admitted HTTP render-manifest and digest-bound asset resolver routes, bootstrap capability/asset
   registration and composition over GmControlRenderProjectionSource.capture/resolve. Those routes are
   absent from this baseline. Do not construct MapRuntimeUiCoordinator, Behavior, session or clock in a server.
3. GM: exact immutable Overview presentation; the current render source advertises EMPTY. A current-Place
   text summary or active 2D map is not that parent Overview.
4. Factory/Tassy: actual shared site-version publication receipt. The proposed module is not a dependency
   until it exists under an approved public contract.
5. Local owner: Java 25/Gradle manifest/Atlas and real HTTP/storage checks, served-browser/CSP and physical
   desktop/iPad-LAN acceptance. Review memory retry guarantees separately; client synthetic CAS is not a
   substitute for server-owned replay, authorization timing or durable filesystem proof.

Real hierarchy/navigation; selection-only Place Objects plus tested input shield; admitted compact Context;
manual canonical Copy / strict non-applying Paste; full repository/assets; and independent Present/Follow/
Player endpoint composition remain later GCS increments through their existing owners. Apply, provider
calls, object authoring, new catalogs, server lifecycle and Main/deployment operations remain excluded.

## Local owner intake supplement — September 8, 2026

BERT applied the exact returned ZIP (SHA256 f36450334f17076a34eb3472711b21de476b42637cbd11614d2426ad5404f78b).
Current Main Home/Gallery metadata is preserved; manifest epoch is28 and Control page revision is3.
The owner corrected one expired-bootstrap lease gap: a successful refreshed Place now discards a stale
layout session before creating its replacement. The added browser case proves Save still succeeds after
expiry and renewal. Ordinary current-session refresh retains its layout lease. No new service authority.

Local evidence: 7client +14layout cases,17shell cases and5page flows passed. Browser mode uses intercepted
fixture URLs, actual module/CSS loading and isolated Chrome; it is still synthetic, not live server/device
acceptance. Desktop and360px saved-layout screenshots reviewed with current shared Forest fonts.
Java manifest class8/8 and required Gradle Atlas validation passed. Stale unrelated literal asset pins
were reconciled only after proving their files equal to current Main; no adjacent product source changed.
An initial unqualified Gradle test selector reached subprojects with no matching tests; use root :test.
No broad test suite, service restart, real persistence mutation or deployment was performed.

Architecture friction: the public Shelf v2 store and fixed GM memory route fitted without domain changes.
Exact read/write adapter shape and bootstrap lease replacement needed care; shared manifest digest pins
for unrelated mutable pages made local verification unnecessarily coupled. These are observed seams,
not authorization to redesign adjacent owners.
