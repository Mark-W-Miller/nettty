# GCS-01 — Java-admitted Tally Talk

Owner: BERT — Make JavaScript__. Patcher: __Make Javascript. Date: 2026-09-08.
Status: source-reviewed increment; local Java/served/device acceptance still required. Not full GCS completion.

## Source and scope

Exact supplied baseline: 06a86c2a8b3a567c053d69b179692268346923c9.
Input: GCS-01-After-Saved-Layout-2026-09-08.zip; 49,845,177 bytes; 3,093 tracked files.
Archive SHA-256: dd2d14c5c55f77184ba26eb4734ce40600fda772f0ead3950e8e85c27838c70a.
Archive hash/CRC and every source digest matched before editing. Only this accepted input was used.
The saved-layout lease-renewal correction and its regression are preserved. No superseded implementation
was inspected or applied. The continuation explicitly authorizes independently reviewable increments.

This increment opens the existing Tally Talk Shelf Box on the existing /control/ page. It adds a compact
admitted Context, details, exact canonical Copy and strict Java-validated non-applying Paste review. It
uses the same bootstrap/client, public Shell/Forest resources, and existing server-owned Context routes.
It does not enable editor entry, input shielding, real renderers, hierarchy commands, Apply or providers.

## Current contracts reviewed

- AGENTS.md Communication with Mark, normal service route, strict scope, artifact and test instructions;
  AGENTS.project.md and AGENTS.index.md; PATCH-BUNDLE-INSTRUCTIONS.md.
- Development-Architecture-Atlas.md current/deferred split and service matrix; action-routing.md Context
  route; service-architecture.md GM provider/exchange and immediate calls; events-threading.md currentness
  and callback disposal; persistence-and-content.md Context exclusion from Shelf memory; focused-tests.md.
- GCS-01-IMPLEMENTATION-BRIEF.md, UNIFIED-CONTROL-SCREEN.md and living TEST-PLAN.md.
- Context Management GM-CONTROL-SCREEN-ADAPTER-PROFILE.md: exact naming, Java-only admission, sealed owner
  stamp, semantic array ordering, canonical bytes, strict Paste, safe omissions and non-applying scope.
- Existing GmControlContextExchangeService/Codec/Delta/Provider and ContextService; actual gateway and
  Controller request/response binding; Application Server package contract; public Shelf v2 memory.

The generic Shelf Context Workspace/SDK, production action authority and future Morph composition are
not current contracts. The landed manual Java exchange is current and is the only proposal decoder here.
No application-context document is constructed in JavaScript. This is not a browser Context engine.

## Exact route and owner behavior

Control action -> page Context lease -> existing Control client -> fixed GM-admitted HTTP route ->
GmControlApplicationServerGateway -> GmControlContextExchangeService -> existing sealed runtime capture
and Context admission -> exact encoded Context or Java delta -> checked volatile presentation.

Current public methods reused:
- GmControlApplicationServerGateway.copy(caller, binding, requestId) and paste(caller, binding, bytes, bytes).
- GmControlContextExchangeService.copy(workspaceStamp, requestId) and paste(copiedBytes, proposedBytes).
- MapRuntimeSessionService.captureGmControlSnapshot remains the existing atomic source through AppServices.

The browser only calls existing admission status, snapshot, Context Copy and Context Paste. It sends no
principal, arbitrary route, runtime payload, domain object, provider instruction or feature command.
The host owns cookies, App/GM admission and workspace binding. ApplicationServerCommand and admission
implementations are unchanged. Java codec/service/delta are unchanged; the host gateway now uses that codec
to reject copied OR proposed workspace/Box/generation stamps inconsistent with the caller's binding.

Context-only HTTP proof adds current AppSession/GM-cookie, CSRF and manifest digest checks after bounded
body input and again after response encoding. Global admission routes and manifest policy remain intact.
The existing outer request bound is 12 times the 64 KiB exchange limit for JSON string escaping; each
inner envelope is decoded by Java within 64 KiB. The response shares the client's 256 KiB UTF-8 bound.

Before a Copy/Paste result becomes usable, the browser rechecks admitted GM expiry and compares all thirteen
owner-stamp values against the existing snapshot query. It accepts exactly the current transport stamps.
There is no continuous subscription: the display says captured snapshot, not live feed. Next operation
recaptures/checks currentness; Place refresh proactively clears Context.

The admitted summary and labels are rendered verbatim. Missing extra ancestry/media fields use safe fixed
prose, never raw IDs/paths or private component state. English presentation keys are a registered separate
catalog, not domain-label authority. Other locales remain unimplemented, not silently called localized parity.
The raw canonical clipboard buffer intentionally includes admitted exchange IDs/stamps; these are not label
fallbacks and never enter preference storage. External explanation text is not used as a Context label.

Production authorized_changes is empty: current per-presence action revisions and Morph context are
unavailable. The UI says so and keeps Apply unavailable. A same-base no-change result can be reviewed;
object-change proposals remain rejected by Java. Future authorized deltas have a bounded display/correlation
seam, but no browser authorization map, feature dispatcher, command replay or Apply implementation exists.

## Lifecycle, clipboard and resources

One panel owns only volatile DOM, one borrowed client lease, one pending operation and an expiry timer.
The timer is the earlier known App/GM expiry, not an app clock. Close/hide/refresh/expiry/disposal clears
Context and preview, invalidates the Box epoch and aborts only that lease's fetch. It never closes Context
services, cancels a feature operation or changes Player state. After a hidden page resumes, it captures anew.
A saved Tally Talk open flag can disclose the Box but cannot bypass fresh admission/capture.

Copy is explicit. Java canonical text is never repaired, trimmed, reordered or re-encoded. Secure clipboard
write is optional; plain LAN HTTP/denial uses a temporary read-only manual Copy target. Paste Result opens
a temporary clipboard-only target, prevents typing/drop/cut authoring and forwards the original plain text
to Java. Browser checks on that input are transport Unicode/byte limits only, not JSON/schema interpretation.
The empty acquisition target is not a free-text prompt composer. Native OS clipboard/touch menus need device
verification. Already-issued platform clipboard writes cannot be recalled; late callbacks cannot revive UI.
Escape/discard restores focus only from owned acquisition controls. Closing the Box uses existing Shell focus.

Context/proposals/labels/bytes/digests never enter browser storage or shared Shelf persistence. Only existing
public disclosure/order/size/focus presentation persists. The existing saved layout implementation is unchanged.
Copy/Paste key headers meet existing metadata, but those owner methods have no keyed receipt replay surface;
this consumer performs no automatic retry and never promises replayed success. An explicit new Copy recaptures.

## Self-review findings corrected

| Severity | Source/function | Failure scenario | Correction/contract |
| --- | --- | --- | --- |
| P1 | applicationserver/GmControlApplicationServerGateway.java, paste | A valid foreign workspace envelope could be compared using its own supplied binding rather than the caller's. | Decode both with the existing Java codec and require exact server-created WorkspaceStamp before recapture. Context profile sections 2/5 and host admission binding. |
| P1 | applicationserver/ApplicationServerController.java, Context dispatch and sendContextProjection | Admission/resource epoch could change during body read or response encoding, allowing private output after the initial check. | Revalidate current bound App/GM session, CSRF, instance/version/digest before Context service and immediately before output; bounded encoded response. Atlas private worker/lifecycle boundary. |
| P2 | control-context.js, expire | A stale bootstrap made the timer return before clearing already displayed private data. | Clear on expired lease as well as GM deadline; re-entry requires new admission. Atlas stale/disposal contract. |
| P2 | control-context.js, clearAcquisition/renderReview | Closing a focused clipboard/preview control could strand focus in a hidden or removed subtree. | Restore only owned focus to the retained public details summary. Shelf accessibility/lifecycle contract. |
| P2 | control-context-contract.js, readAdmittedDelta | A shape-valid Java response with a foreign request/owner/target/receipt could be displayed. | Correlate with the one retained Java Copy and require admitted target/receipt/field identity; Java still alone evaluates proposals. |
| P2 | control-context-text.js and control-context.js | New fixed prose was inline rather than keyed; raw owner reasons could become accidental labels. | Stable registered English presentation keys, verbatim admitted domain labels, fixed safe dispositions. Context naming/display matrix. |
| P2 | page-manifest.yaml and ApplicationServerManifestStoreTest | New module imports/capability references could be absent or mixed with old generation. | One atomic Control resource registration, manifest epoch/page revision and exact affected asset pins. No policy weakening or private Factory import. |

Java source prefixes are src/main/java/com/moondance/talisman/app/services/app/. Browser prefixes are
src/main/resources/app/control-screen/. The manifest is under app/application-server; focused Java tests
are under the matching src/test/java package. This review covers all increment production files, tests,
registration, current Atlas entries, README, package contract and task/test-plan changes.

## Focused proof and local gates

Executed here: 32/32 client/layout/Context tests; 17/17 native Shell checks; five existing page flows and
four new Context flows (10 browser test entries, including the Shell harness), all without skips.
Twenty-four JavaScript modules pass syntax checks; six changed Java units parse on Java 21, which is
syntax-only, not type compilation. Exact checksums/replay results are in the separate delivery manifest.
Browser/client tests
use synthetic fixtures from the checked-in Java exchange example; they do not certify real Java admission.
The assembled browser runs production code/CSS under explicit offline Blob imports and fake HTTP replies.
Normal route-intercepted navigation was attempted and failed with net::ERR_BLOCKED_BY_ADMINISTRATOR. No
browser policy or server was altered. Offline mode does not prove served module loading, CSP or real cookies.

Existing client/layout tests and all five page flows remain selected, including BERT's expired-bootstrap
layout lease renewal. The seventeen native Shell cases retain all seven mount/layout arrangements. New
Context cases cover canonical bytes, current stamp families, denial, late close, private cleanup, no typing,
no Apply, native clipboard events, exact route/headers and actual saved-layout exclusion. The synthetic delta
fixture is no-change because production action authority is empty, not a fabricated live edit.

Java 21 is installed; Java 25 and the cached Gradle 9.1 distribution are unavailable. The focused wrapper
invocation through bash fails with UnknownHostException: services.gradle.org. Actual Java gateway/codec/
HTTP/manifest JUnit cases are supplied but not executed here. No broad suite or substitute green claim.
The standalone Atlas validator executes on Java 21. Reference and patched source return exactly one
identical issue: AGENTS.project.md:41 points to the unavailable workstation directory
/Users/mmiller/Git/talisman-git/. No new issue or unrelated repair is introduced; this is not a passing
Gradle task. Clean replay validates all 3,093 reference digests, checks/applies the one numbered patch, and compares all
3,103 resulting source files against the reviewed tree. Twenty-nine paths change; 3,074 baseline files
remain byte-identical. No Factory, Forest, admission authority or shared Shelf implementation changes.

Local acceptance still needs actual Java 25/Gradle tests, served browser/CSP, real GM cookie/Context data,
LAN HTTP/iPad Safari native clipboard selection/Paste menus, and supported translations. Representative
1600/1024/768/360 images are synthetic, not live data or physical-device proof.

## Remaining full GCS dependencies

1. Vera: actual public browser renderer module/resources and mount/update/resize/dispose signatures over
   GmControlRenderProjection. No such public receipt exists in this input; do not invent it.
2. Tassy: admitted render-manifest and digest-bound asset routes/registration over the existing
   GmControlRenderProjectionSource.capture/resolve. The Java producer exists but its HTTP/browser integration
   is absent. It does not authorize Behavior/UI/session/clock creation.
3. GM: a current Overview input supplier for the headless public route is not composed. The existing
   ui.mapruntime.GmControlRenderProjectionSource can encode a supplied MapCanvasPresentation; null input
   yields an empty component. This consumer must not create a native UI lifecycle to populate it.
4. Fergus/Tassy: actual shared site-version public consumer receipt. No parallel Control version is added.
5. Full hierarchy/navigation, selection-only Place Objects with tested input shield, complete repositories,
   imagery/models/picking, independent Player endpoint/Present/Follow integration and device lifecycle remain
   open. Native Control and all existing domain/action owners remain intact.

No deployment, restart, provider call, live-data operation, Player publication or Main action was performed.

## BERT owner intake proof — September 8, 2026

The Patcher execution limits above are historical. BERT applied this delivery onto current Main,
preserving hosted admission, Home navigation, Factory sources and shared Shelf/Forest behavior.
The two overlapping metadata/manifest-test paths were reconciled. Three outdated unrelated asset
fingerprints were aligned only after comparing each asset byte-for-byte with current Main.

Owner execution passed: Java 25 type compilation; 19 focused Java tests (exchange 5, workspace binding
3, HTTP proof 2, manifest 9); required Atlas validator 15; 42 Node tests (32 client/layout/Context,
9 page/Context browser flows and one shell harness containing 17 checks). Browser screenshots were
produced at 1600, 1024, 768 and 360px; desktop and narrow captures were reviewed. Browser transport and
Java HttpExchange remain synthetic. No broad suite, real HTTP/CSP, physical iPad, live clipboard or
managed restart was performed. Server admission changes require managed binary adoption before the
increment is described as fully live. Full GCS renderer/navigation/editor/repository/Present-Follow
completion remains open. The canonical BERT implementation and test-plan documents record intake.
