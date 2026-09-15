# Object Factory Test Plan

## Metamorph Gallery — browse-first, in-place edit (2026-09-13)

Authority: [METAMORPH-GALLERY.md](METAMORPH-GALLERY.md), requirements MG-01 through MG-19.
Status: design-only. Cases below specify future implementation evidence, not tests already passing.
Current source inspection, documentation/patch verification and toolchain limits are recorded separately
in the patch return. All runtime fixtures must be invented, with temporary SQLite where storage is needed.
No browser/UI automation, live database, app/server launch, deployment, model reset or provider call.

Documentation evidence on 2026-09-13: all 15 added local Markdown links/anchors and diff whitespace passed.
The 13-Markdown-file patch applied to a fresh archive and matched all 3,203 resulting file hashes; 3,190
original files remained unchanged. The unmodified repository Atlas validator, called in a JDK-only harness,
reported the same one missing absolute Mac worktree path in unchanged AGENTS.project.md on both trees;
there were no new findings. The configured Gradle/Java 25 gate was not run because its cached distribution
and toolchain were absent. These results are documentation/source checks, not runtime or visual proof.

### Required focused cases

1. **Browse-first selection (MG-01/03/11):** fresh entry, restored selected identity and deep link show
   the read-only card with no edit session/write. Disclosure changes only expansion. Edit of A replaces
   its card in the same mount after checked hydration; late A cannot replace a subsequently selected B.
2. **Retained Gallery (MG-01/02/04):** open/close, preview takeover, clean Edit, Save and Cancel preserve
   scope, selection, expanded paths, page/scroll and reachable focus. Hidden Gallery does not cancel a
   draft. There is one active selected-object surface, not simultaneous card/editor copies.
3. **Whole catalog (MG-05/07/14):** exercise every registered source projection, all 30 listed catalogs
   and 10 nested kinds, explicit relations and admitted current Slice kinds. Same-name objects from
   different authorities stay separate. Record an editor/read/write/preview coverage row for every kind;
   demonstrate actual saved edits for non-Slice supported editable families, not only read-only rows.
4. **Slice only (MG-05/06/17):** default unchecked from both entry points; explicit membership and shared
   reviewed Slice use are included, unrelated and unknown membership are not falsely included. Apply
   scope before paging/counts; delayed Everything response is rejected after a filter change. Retain
   ancestors, label out-of-scope related cards and avoid hidden selected-editor targets.
5. **Real card media (MG-08/09/10):** character with icon/token/images but no Morph remains visual;
   missing one role does not suppress another. Cover actual preview mounting, multiple roles/order,
   unavailable schema, dangling links, corrupt bytes, unsupported formats and unknown-vs-empty state.
   Stored Morph stances/motions/camera do not create a draft or write. Dispose the replaced renderer.
6. **One complete-model technology (MG-07/10/14):** one Morph Types section; preserve authored Humanoid,
   Dragon, Dwarven and all individual fixtures. Exact type and individual round-trips preserve unknown
   fields, appearance, geometry, Webs, units, texture bindings and motions/stances. No fallback geometry
   or automatic source reload. Unsupported legacy types stay inspect-only with a specific reason.
7. **Exact edit target (MG-07/10/11):** a character card displaying a linked Morph still edits the
   character; following View Morph opens that Morph's card first. Individual edit never targets its
   source type. Historical revisions and duplicate labels cannot redirect writes to a current peer.
8. **Cold-start Make/attach (MG-15/16):** zero model cache plus actual logical-Gallery flag retains valid
   metadata-derived choices. Checked source hydration precedes confirmation without changing selection.
   Cancel, changed source/target, closed form and changed revision reject delayed completion. Test the
   real current selectors, including retained `.gallery-modes`/`#gallery-type`/`#gallery-individual`/
   `#gallery-place` when present, not invented data attributes. Keep Make/attach and visible focus.
9. **Fallback (MG-15/16):** capability absence or first-page failure leaves a usable, truthfully labelled
   local/file-mode Gallery when supported, never a disabled empty roster or a false Everything result.
   A later success removes actual obsolete controls only after safe takeover, with no duplicate tree.
10. **Save/Cancel/conflict (MG-11/12/13/14):** detached draft, exact CAS/digest, single atomic save,
    durable receipt/readback and same-identity card refresh. No-change, failed write, lost response/replay,
    late rejection and concurrent update retain truthful status. Cancel/discard writes nothing; accepted
    work is not declared rolled back on close. Failure cannot silently navigate away or clear the draft.
11. **Dirty navigation (MG-13/18):** target change, excluding scope change, in-app Back, frame teardown
    and return to Slice/Talisman all use Save/Discard/Keep editing. Keep editing restores selection and
    controls; saving failure keeps the editor. Collapse/hide Gallery does not invoke this guard.
12. **Privacy/lifecycle (MG-14/17):** expired/revoked admission and server/database/principal changes reject
    pending results and clear private projections. No private payload/draft/token in presentation memory.
    No synchronous EDT/JavaFX bridge. Counts, media reads and writes all use the correct own-page binding.
13. **Shared entry and retirement (MG-18/19):** both doorway declarations resolve to the same installed
    component/entry in Browse/Everything. No default `editor=humanoid`, copied editor, old-viewer frame,
    second chooser or untrusted return URL. Old viewer GET and old tool query resolve safely only after
    replacement readiness. Services remain read-only; no mutation authority appears through an alias.
14. **Slice preservation (MG-07/14/19):** apply to the newly supplied current source, not a synthetic
    handler or old 2D package. Preserve game/terrain/course/model bytes outside scope. Saved supported
    Morphs remain usable by the existing Slice game through the same authority; no second model store.

### Manual review for the later working application

After a separately authorized launch, inspect desktop and iPad widths without automation. From the
Talisman applications page open Metamorph, select a non-Slice character, inspect its imagery/metadata,
Edit and Cancel, then Edit/Save/reopen. Close/reopen the Gallery. Test stored Morph stance and motion
controls without a dirty indicator. Repeat from Slice; compare the same selection and editor. Toggle
Slice only, follow a labelled outside-scope relation, test a dirty target change and the return controls.
Check keyboard focus, touch targets, independent scrolling, takeover restoration and readable Forest
contrast. These are pending manual cases, not source-derived visual acceptance.

Global detail streaming, additional-region UI, legacy raster decoding and old Core conversion remain
unfinished outside this body. A source-level Gallery result does not complete them or prove deployment.

## Slice-native storage — independent source body, 2026-09-11

See [the storage contract](SLICE-NATIVE-STORAGE.md). Focused selectors:
`FactorySliceDocumentTest` and the existing `FactoryNativePackageOperationTest`.
Use only synthetic complete source/media and disposable SQLite. Prove exact original bytes plus unknown
fields, source identity and media closure, unchanged saves, stale/CAS rejection, immutable history,
durable replay after service recreation, source/key collision rejection, corruption detection and complete
content/member/receipt rollback. The operation selector must return the addressed Slice receipt while
the legacy Native selector still returns its original SaveResult. Atlas references are separately validated.
No app, listener, paid operation, real source package, live database or device test belongs to this body.
Main applicability/dependency checks are not isolated-Main execution proof. A–E gates remain open.

Owner-branch audit result: twelve Slice tests plus one existing Native operation test passed (zero failures/skips),
and focused Atlas validation passed. Synthetic corruption changes a same-length payload to prove hash
verification beyond SQLite's length constraint. CRC-correct malformed IHDR, empty/corrupt/trailing zlib
raster reject; indexed, grayscale, 16-bit and interlaced PNG fixtures retain exact original bytes.
PNG dimension bounds are admitted before full decode. Safe-ASCII paths reject composed/decomposed Unicode.
Unsupported media/CRC and excessive JSON nesting reject.
Dependency inventory: 1,217 Java source files, no pre-existing source differing from exact Main. Initial
three documentation-context mismatches required exact-baseline temporary-index application before
exporting a Main-context patch. This changes neither Main nor prior owner history.

Audit correction: the same two selectors passed on the exact Main-plus-patch build overlay, twelve
Slice tests plus one unchanged Native operation test. All 2,711 supplied build-input blobs matched the
tree before and after execution; outputs/classpaths were isolated from owner A–E outputs. The handoff
binds exact Main, patch, resulting tree, harness and input inventory. This is not live import acceptance.

## Per-pose surface coverage and picker repair (2026-09-08)

- `src/test/js/morph-picker-layout.test.mjs`: actual production picker/Apply handlers and CSS;
  pending selection stays open, Apply/header close only catalog, stances/motions remain full width.
- `src/test/js/object-map-facing.test.mjs`: grazing Front/Back cannot monopolize four other faces;
  own source pose, first-hit occlusion, enablement, equal-facing order, immutable exposure presets,
  and no Object floor calls. No database writes or paid generation in either check.
- Live acceptance: all six sources must retain meaningful claims while orbit reuses the same assignments;
  map replacements and pose/registration changes invalidate them. Check new presets explicitly without
  silently switching existing image assignments or claiming stored base Morph revision changes.

## Home first — management navigation (2026-09-08)

The seven static port-3002 page entries must expose a native `Home` link to `/` before their other
visible navigation/content. The link must remain usable before JavaScript initialization, retain a
44px minimum touch height, and show keyboard focus. Creature navigation moves before the title;
Gallery and Batch retain their existing destination behavior. The delivery label is alpha 1.66.4.
Check each page at desktop and 768px iPad widths after publication, click Home, and verify the same
origin's root opens. Compare served changed assets with the exact reviewed source bytes.
Generated-root navigation and the one-time old-publisher compatibility refresh belong to Tassy.
Source whitespace/resource checks and live publication are reported separately; no renderer, save,
Unified Workbench, broad regression, provider or database acceptance is implied by this body.

Date: 2026-09-05
Status: living plan; Adult Dragon retained; Ancient Dragon source and workshop focused proof passing
Owner: Object Sing & Dance Factory

## Purpose

This is the cumulative focused-test and product-review plan for Object Factory. The detailed normative
proof inventory lives in `SYSTEM-DESIGN.md`, section 32. The fixed-complete-object Core Morph workshop,
copy-then-modify rule, and deferred materialization/read contract live in
[`MORPH-DESIGN.md`](MORPH-DESIGN.md). This file records the evidence expected from each body and the evidence
actually obtained.

No broad Talisman suite, application launch, provider call, production-database mutation, or running
SRD-batch change is authorized by this plan.

## OF-UI-00 — Experience design

Required review evidence:

- Complete inventory of 17 screens and required dialogs
- Labeled static image plate for each screen
- Build hierarchy visibly reads Control Point, attached Shapes, and child Control Points
- Build properties say Position, Rotation, and Scale relative to parent
- Canonical feet and explicit display conversion are visible
- Skeleton Setup shows Locked, Animatable, Solver-controlled, and Derived channel policies
- Skeleton Setup does not imply a universal fixed-length skeleton
- Skeleton and Mesh Wireframe are separate presentations
- Skin Map shows the exact 3D/flattened-atlas correspondence
- Puppeteer dots are complete poses rather than joints
- Paid appearance generation requires explicit reviewed action
- Save and Save As consequences are distinct
- Empty, busy, unsaved, invalid, stale, incompatible, cancelled, and failure states are designed
- Keyboard, focus, non-color-only state, busy/live, and expected-width behavior are reviewed
- Build to Reference to Skin to Skeleton to Pose to Puppeteer to Save journey is reviewed

Evidence produced on 2026-08-29:

- `UI-DESIGN.md`
- `APPROVED-UI-REFERENCE.md`
- `images/ui/00-screen-overview.png`
- `images/ui/01-object-library.png` through `images/ui/17-language-review.png`

Result: **approved by Mark on 2026-08-29** after review of the complete multi-screen gallery. The hashes in
`APPROVED-UI-REFERENCE.md` freeze the visual reference. This is design evidence, not UI code or runtime
verification.

## OF-01 — Native Form and deterministic arm fixture

Focused automated evidence must prove:

- canonical-foot serialization and display-unit round trips;
- stable Control Point/Space Node and Part identities;
- zero-to-many Shape attachment under one Control Point;
- exact parent-relative TRS and descendant inheritance;
- positive scale and inverse-transpose normal realization;
- deterministic cylinder/ellipsoid topology, winding, seams, and UVs;
- exact Surface and Material Region ownership;
- deterministic atlas packing, PNG masks, `regions.json`, OBJ, and generated MTL;
- byte-identical artifacts for equal normalized input; and
- rejection of invalid transforms, scales, topology, UVs, and attachments.

Manual evidence must show the checker-textured 3D arm beside its flattened atlas.

Result: **passed in the permanent owner worktree on 2026-08-29**. The exact focused selector
`ObjectFactoryNativeFormCompilerTest` passes nine tests. It proves 456 outward-wound triangles across five
independently closed shells, canonical-foot/display conversion, stable identities and attachment,
parent-relative inherited TRS, inverse-transpose normals, exact named region/material ownership, rear-seam
UV expansion, nine non-overlapping fixed integer islands, deterministic OBJ/MTL/PNG/`regions.json` bytes,
equal-input byte repeatability, dimension-only topology/atlas stability, and invalid-input rejection. The
test emits `build/of-01/arm-atlas-inspection.png`, visually checked as the checker-textured 3D arm beside its
flattened atlas. No broad suite or application run was performed.

## OF-02 — Rig, Binding, Pose, and forward kinematics

Focused automated evidence must prove:

- cycle and invalid-parent rejection;
- complete rest local TRS;
- parent-first affine composition;
- multiple Parts and descendants inherit a node's translation, rotation, and scale exactly once;
- the initial locked translation/scale profile preserves its declared segment distance;
- a contrasting admitted profile permits bounded parent-local translation and positive scale;
- locked/derived channel changes reject;
- complete Pose Snapshot save/load; and
- posed static OBJ remains derivative rather than Rig authority.

Result: **passed in the permanent owner worktree on 2026-08-29**. The exact focused selector
`ObjectFactoryRigForwardKinematicsTest` passes eight tests. It proves the complete exact-Form Rig, five
rigid bindings, stable Controls/Sites/reference segments, complete rest TRS and channel profiles, hard
limits, parent-first affine composition, exact single inheritance across multiple Parts and descendants,
locked/derived rejection, bounded translation and positive-scale admission, canonical complete Pose byte
round trips, rest geometry equality, immutable Skeleton state, deterministic posed mesh/OBJ, and unchanged
topology, UVs, regions, materials, and Appearance artifacts. No broad suite or application run was
performed.

## OF-03 — Body Form editor and reference registration

Focused automated evidence must prove:

- typed node/Part commands recompile through OF-01 and requalify OF-02 deterministically;
- continuous edits retain topology, UV, regions, and stable semantic identities;
- Front/Back movement preserves depth and Orbit movement requires an explicit 3D transform;
- reference source bytes and Front/Side/Back registration remain independent and defensive;
- whole-draft undo/redo and both explicit reparent modes preserve their declared truth;
- invalid edits are atomic and close releases history/reference/presentation resources;
- Build, Reference, and Skeleton are grouped at expected width with bounded controls; and
- the real Factory host retains its existing Object route beside the new Body Form mode.

Result: **passed in the permanent owner worktree on 2026-08-29 and corrected under independent audit**.
The exact focused selectors `ObjectFactoryBodyFormSessionTest` and
`ObjectFactoryBodyFormEditorPanelTest` cover seven domain cases and nine editor/composition/worker cases.
They prove typed OF-01/OF-02 routing, stable semantics, view-plane truth, defensive source bytes,
independent registration, bounded history, explicit reparenting, atomic failure, grouped Swing projection,
mode/presentation survival, and the exact production Factory mode composition. The real chooser/worker
proof admits exact PNG/JPEG bytes/digests, rejects hostile edge and total-pixel metadata before decode,
rejects unsupported/corrupt input, keeps immediate busy feedback stable with duplicates disabled, rejects
superseded generations, and rejects a successful late completion after close without session/reference
mutation. The replacement-tip final from-scratch rerun passed all 16 cases, and the offline Architecture
Atlas validator passed all seven checks. No broad suite, application,
database/provider action, or later Object Factory body ran.

## OF-04 through OF-06 — Skin, persistence, and pose

Each body adds its smallest service-outcome and UI-projection tests plus manual review against the approved
OF-UI-00 image. Required cases include explicit unresolved coverage, mutual 3D/atlas selection, checked
save rollback/stale guards, and fixed-segment versus translation/scale-admitted IK profiles.

OF-04 result: `ObjectFactoryAppearanceSessionTest` proves byte-repeatable orthographic projection, explicit
unresolved Front-only coverage, semantic-region-bounded propagation totals, reciprocal model/atlas
triangle and region identity, exact compatible PNG byte retention, Form invalidation, and close rejection.
`ObjectFactoryAppearancePanelTest` proves the production Skin composition, immediate stable projection
busy state, exact metadata-first 1024-square PNG intake, and the rendered Form/atlas inspection plate at
`build/of-04/arm-skin-inspection.png`. Manual review confirms the two views remain visible together at the
expected width. No broad suite or application ran.

OF-05A result: `FactoryNativePackageServiceTest` proves deterministic complete component capture/load,
missing/tampered fail-closed rejection, Save New, exact append-only Save, stale rejection, distinct Save As
with source provenance, listing, pre-transaction candidate rejection, and forced late-transaction rollback
with no partial object/assets/memberships. `FactoryNativePackageOperationTest` proves the same package
commits through the AppServices-owned typed operation lifecycle and reconstructs to the exact fingerprint.
`ObjectFactoryBodyFormEditorPanelTest` proves immediate stable Save busy feedback, duplicate disablement,
committed identity installation, and late completion rejection after close. Existing Factory library and
operation selectors pass; the relevant workspace Save/Save As selector passes. The rendered production
Body Form/Skin plate confirms that every durable action remains visible at the expected width. The offline
Architecture Atlas validator passes. One unrelated pre-panel
workspace fixture timed out waiting for its User Assets projection prerequisite and was not changed by
OF-05A. No broad suite or application ran.

### OF-06 six-face pose-observation evidence

OF-06 is the smallest affected implementation body because it owns Pose Handles, deterministic IK,
candidate diagnostics, and exact Pose acceptance. Its focused proof establishes:

- exact ordered admission of `token`, `token_back`, `token_left`, `token_right`, `token_top`, and
  `token_bottom`, with `icon` and unknown roles rejected;
- the frozen camera bases and one common canvas/orientation/root registration/uniform visual scale;
- same-Creature identity/revision and exact accepted-Front asset/revision/digest ancestry for every
  non-front observation, with mixed Creature, stale Front, mirroring, roll, crop, and nonuniform stretch
  rejected or explicitly downgraded according to the canonical registration contract;
- `WAITING_FOR_FRONT` with zero matcher/queue/provider mutation while Front is missing, queued, or in flight;
- deterministic partial-deck matching when any subset of the five non-front views is absent, with exact
  missing-view, occlusion, per-observation, per-joint, residual, and unresolved-channel evidence;
- equal Form/Rig/deck/registration/matcher/solver inputs producing an equal reviewed Pose candidate;
- contradictory or low-confidence observations remaining visible rather than being silently averaged into
  certainty or invented joint/depth truth;
- candidate preview and adjustment changing no Form, Rig, persistent Pose, history, selection, external
  queue, or managed asset; and
- exact-revision explicit acceptance creating one complete Pose Snapshot, while stale deck/Form/Rig/session/
  matcher/solver identity rejects atomically.

Manual review must show the six fixed face slots, truthful waiting/missing/stale states, joint/Site/silhouette
overlays, per-view and per-joint confidence/residuals, unresolved channels, and the visible distinction
between a transient Pose Candidate and Save Pose. Manual posing must remain usable without any deck.

Result: `ObjectFactoryPoseAuthoringTest` proves deterministic reachable, unreachable, and limit-clamped IK;
unchanged topology/UVs; exact six-role/Icon exclusion and Front waiting; Creature/Front ancestry rejection;
repeatable full/partial-deck candidates; missing/confidence/residual/unresolved truth; transient candidate
review; stale exact-revision rejection; complete Pose acceptance; and byte-stable optional Native Package
round trip. `ObjectFactoryPosePanelTest` proves the fixed deck composition, manual fallback, immediate busy
feedback, duplicate disablement, monotonic stale-result and close rejection, exact loaded-revision Save Pose,
and the reviewed plate at `build/of-06/arm-pose-token-deck-review.png`. The directly affected grouped-editor
and scalar-Context selectors also pass. No broad suite, application, provider, live data, Critter queue,
managed-asset, or later Object Factory body ran.

## CM-07 — Object Factory feature capture collaboration

Focused automated evidence must prove:

- exact optional Object/native-package identity plus current model/Form/session/Rig/rest-Pose scalars;
- stable semantic type, identity, label, ancestry, and local transform without mesh/image/track data;
- ordered bounded multi-selection and a separately stamped invocation target;
- current workspace, view, presentation, Appearance coverage, dirty, undo/redo, and busy activity truth;
- explicit absence of then-unimplemented OF-06 Pose plus animation, Puppeteer Field, clip, and state-machine
  capabilities;
- hidden, closed, unknown-target, and changed lifecycle/session/Appearance/durable rejection; and
- no Factory session, selection, persistence, or revision mutation on rejection.

Result: `ObjectFactoryContextCaptureTest` passes four focused cases. The record-surface proof rejects
arrays, images, paths, maps, and byte/path/digest/provenance/payload fields. The live EDT seam proves current
scalar identities, a real ordered node/region selection, a distinct exact semantic Part invocation target,
complete ancestry, busy capability suppression, every revision-stale dimension, hidden/closed/unknown
rejection, and unchanged Factory revision on every denied capture. The affected grouped-panel selector also
passes. The production provider, contextual actions, passive monitor, payload, and composition remain the
Context Management owner's CM-07 body. No broad suite, application, provider, live data, persistence,
OF-06, or later Object Factory body ran.

## OF-07 — Puppeteer Field editing and preview

`ObjectFactoryPoseFieldTest` proves exact one/two/collinear/Delaunay evaluation, shared-edge continuity,
nearest-hull projection, stable serialization-order and four/five/six-dot cocircular triangulation,
sign-invariant quaternion log/exp interpolation, explicit chart-radius rejection, parent-local
translation/positive-scale interpolation, canonical round trip, malformed/too-close/ill-conditioned
rejection, and atomic transient dot/history/close behavior. `ObjectFactoryPuppeteerPanelTest` proves the
approved Teach/Perform composition, complete-Pose dot capture, visible normalized weights, disabled OF-08A
Record action, live articulated result, and reviewed plate at
`build/of-07/arm-puppeteer-field-review.png`. The directly affected grouped-editor and scalar-Context
selectors prove the sixth workspace and current Field-edit capability. No broad suite, application,
provider, Critter queue, live data, recording, Field persistence, or later body ran.

## OF-08A — Recording and exact-Rig persistence

Focused evidence must cover deterministic triangulation, exact-dot behavior, continuous log/exp rotation
charts, parent-local translation/scale interpolation, canonical 60-tick recording, repeat endpoints, exact
dependency pinning, full-TRS Solved Clip bake/reload, channel masks, layer order, root ownership, and visible
conflicts.

Result: `ObjectFactoryPerformancePathTest` passes four focused cases for zero/sub/exact/over-tick ceiling,
same-timestamp last-input truth, stationary holds, byte-stable canonical round trip, frame-rate-independent
shared-time evaluation, integer trim/split endpoints, old-Field playback after current-Field editing, C0
qualification, and every parent-local translation/rotation/positive-scale channel through explicit Solved
Clip bake/reload. `FactoryNativePackageServiceTest` adds one isolated same-store case for exact Field,
motion-Pose, Path, and Clip membership/reconstruction plus missing-Path fail-closed rejection.
`ObjectFactoryPuppeteerPanelTest` passes two cases for Record composition, named 60-tick replay/edit/loop/
bake truth, immediate stable Save feedback, duplicate disablement, close-time completion rejection, and the
reviewed expected-width plate at `build/of-08a/arm-puppeteer-recording-review.png`. OF-08B masks/layers/root
composition, walking, and dance remain absent. No broad suite, application, provider, Critter queue, or live
data ran.

## OF-08B — Layered behavior and dance

Focused evidence must add channel masks, deterministic layer order, root ownership, and visible conflicts
over the landed OF-08A exact Path/Clip baseline.

Result: focused offline proof passes. `ObjectFactoryBehaviorComposerTest` covers canonical equal-input
bytes, exact-Rig decode/retarget rejection, R=1/finite/indefinite endpoint mapping, zero-duration and
before/after contribution, component masks, unmasked invariance, fractional TRS, q/-q and exact-pi branch
stability, ordered visible conflicts, duplicate order, absolute-root conflict, explicit additive-root truth,
source-pin rejection, bounded biped step plus upper-body wave, and the short whole-body dance.
`FactoryNativePackageServiceTest` now proves both exact Behavior membership over the authoritative OF-08A
Solved Clip and full standard-biped step/wave/dance Clip/Pose/Behavior save, reload, and composition.
`ObjectFactoryBehaviorMixerPanelTest`
proves the grouped live preview, masks/compatibility/conflict facts, immutable edit revision, immediate Save
feedback, duplicate disablement, close rejection, and reviewed expected-width plate at
`build/of-08b/biped-behavior-mixer-review.png`. No broad suite, application, provider, Critter queue, live
data, or Journey action ran.

## OF-09A — Template engine and bounded biped

Focused evidence must prove immutable parameter normalization, equal-input canonical bytes, the complete
segmented biped Form/Rig/Atlas graph, closed-shell compilation, stable semantic identity, cardinality-neutral
schema behavior, explicit structural evolution revisions, independent compatibility signatures,
geometry-dependent sites, and broad/slender density drift within the declared Template threshold. The
landed arm's fixed atlas and bytes remain a direct compatibility gate.

Result: `ObjectFactoryTemplateEngineTest` passes four focused cases for the 16-node/21-Part biped,
21 regions, 37 islands, exact complete Rig/bindings/channels/controls/segments/sites, byte-stable normalized
parameters/OBJ/regions, rest-FK identity, standard/broad/slender signature boundaries, recomputed chest site,
bounded density evidence, unknown/out-of-range rejection, same-revision structural rejection, explicit
next-revision evolution, and a reduced two-node/one-Part non-biped compile. The deterministic review plate is
`build/of-09a/bounded-biped-template-review.png`. Direct OF-01 Native Form and OF-02 Rig/FK selectors remain
the compatibility proof for the unchanged arm compiler/Rig route.

## OF-09B — Quadruped and dragon families

Focused evidence must prove complete bounded quadruped and `dragon.basic` family graphs, equal-input bytes,
stable semantic nodes/Parts/regions/Atlas islands, complete cardinality-neutral Rigs, compatible dimension
variants, explicit structural Template/Atlas evolution, and several Appearance PNGs over one unchanged Form
without invalidating its exact Pose/Behavior truth.

Result: `ObjectFactoryCreatureTemplateLibraryTest` passes four focused cases. The 17-node/17-Part
quadruped compiles with 33 islands, 15 controls, five sites, and 16 reference segments. The
25-node/33-Part dragon compiles with 33 regions, 71 islands, 33 bindings, 22 controls, nine sites, and 24
reference segments, including exact semantic torso/head/snout, limb, neck/tail, wing, horn, eye, and mouth
parts. Reordered equal parameters reproduce recipe, Rig, signatures, OBJ, and `regions.json`. Lean and
long-winged variants preserve declared topology/Atlas/Rig/exact-motion/Rig-family signatures and bounded
density while changing Geometry/Bind identity. Same-revision structural change rejects; an explicit next
Template and Atlas revision qualifies. Two distinct 1024-square PNGs retain one exact Form/Rig, rest Pose
bytes, FK positions, and Behavior compatibility signature. The deterministic reviewed plate is
`build/of-09b/basic-dragon-template-review.png`.

## OF-10 — Reviewed AI atlas painting and revision loop

Result: `ObjectFactoryAppearanceGenerationSessionTest` proves equal reviewed inputs produce the same
exact-Atlas fingerprint without provider activity; valid PNG becomes a locally masked, four-pixel region-
safe dilated transient preview; wrong dimensions, corrupt bytes, transparent covered texels, mismatched
model, cancelled calls, changed Appearance, and stale results fail closed; Reject preserves the prior
revision; and exact acceptance creates one `GENERATED` Appearance
whose provenance reconstructs exactly. `ObjectFactoryAppearanceGeneratorPanelTest` proves review-only
silence, immediate `Generating…` and duplicate disablement, fake-provider preview/accept/reject, close-time
late-result rejection, and the expected-width grouped review plate at
`build/of-10/appearance-generator-review.png`. The focused
`FactoryNativePackageServiceTest.generatedAppearanceProvenanceSurvivesExactNativeTransaction` case proves
the same immutable provenance and PNG survive the existing checked OF-05A database/content transaction.
All provider proof uses injected fakes; no live or paid call occurred.

## OF-P01A — Imported-model staging, conversion, and preview

Result: `ObjectFactoryImportedModelStagingServiceTest` proves inspected STL/OBJ format and extension truth,
safe OBJ/MTL/PNG companion references, exact untouched source bytes and individual hashes, canonical-feet
unit normalization, explicit orientation, capability/loss inventory, deterministic repair/collapse/
planarization/faceting/UV recipe bytes, separate reduction metrics, error-budget rejection, closed native
mesh validation, normalization-aware domain-separated identity, equal candidate artifacts, valid complete
unsigned receipt JSON, and missing/unsafe/corrupt fail-closed behavior. Its narrow-octahedron fixture proves
a real edge-collapse reduction remains separately measured from planarization.

`ObjectFactoryImportPanelTest` proves immediate stable `Building…` feedback, duplicate disablement, current-
generation-only adoption, changed-source/close-time result rejection, transient source/candidate release,
disabled OF-P01B acceptance, third-mode Factory workspace composition, and the grouped 1420 x 780 plate at
`build/of-p01a/import-convert-review.png`. The preview route has no AppServices, persistence, provider,
Critter, Viewer-owned source, Blender, Dwarf War, or live-data dependency.

## OF-P01B — Atomic imported-model commit

Result: `FactoryNativePackageServiceTest` proves exact source/receipt/candidate reload, imported-kind and
manifest identity, source membership, revalidation against tampered source bytes, stale-current rejection,
and complete rollback after a forced pre-commit transaction failure. `FactoryObjectOperationServiceTest`
proves the dedicated bounded semantic-operation route produces stale terminal truth without mutation and
one committed transaction when the exact review remains current. `ObjectFactoryImportPanelTest` proves
immediate stable `Accepting…` feedback, duplicate disablement, changed-generation invalidation, late-result
rejection, and close-release currentness rejection. These tests use only deterministic local fixtures and an
ephemeral SQLite database; no provider, live data, Critter lane, or application process is involved.

## OF-12A — Lizard Mapping Body Type and deterministic six-view texture bake

The next body requires these smallest focused proofs:

1. Equal Lizard body-type definitions compile byte-identical skeleton, geometry, UV, semantic-region, and
   default six-plane mapping artifacts.
2. Front, Back, Left, Right, Top, and Bottom retain their exact camera bases, head-up orientation,
   anatomical handedness, and common scale contract.
3. A 2D landmark drag changes only that face's mapping revision; the 3D body and five other observations
   remain equal.
4. A 3D geometry edit changes creature geometry without silently moving any image observation.
5. Copy Opposite acts directly, applies the declared camera transform once, then leaves source and target
   independently editable without changing either image or the 3D geometry.
6. Left and Right expose only their near-side anatomical chains for direct mapping while retaining the full
   3D skeleton.
7. Giant Crocodile and one second lizard-family fixture reuse one immutable default while retaining
   independent creature edits.
8. The region-ID palette, mesh topology, UV identities, and seam ownership remain stable across admitted
   compatible Lizard proportions.
9. The deterministic six-view bake produces byte-equal output for equal inputs and reports exact direct,
   blended, and unresolved source evidence per texel.
10. Save/reload preserves the body-type revision, creature geometry, six independent mappings, image
    digests, bake provenance, and unresolved truth.
11. Focused tests use checked local fixtures only: no provider, live controller, paid request, live database,
    or Seasons mutation.

## OF-11, OF-12B, and OF-13

`ObjectFactoryRuntimePublicationTest` proves exact Character/package/Behavior identity, equal-input
invocation IDs and posed OBJ bytes, ordered start/progress/end outcomes, unchanged static OBJ/diffuse
compatibility, four exact read-only consumer adapters, visible stale/missing/incompatible rejection, and
bounded progress admission. `FactoryRuntimePublicationServiceTest` proves the durable adapter publishes
only the exact current Object/Native Package version and visibly refuses stale or missing Characters.
`ObjectFactoryRuntimePublicationPanelTest` proves the grouped Character/Behavior/target/capability/result
review, every consumer target, close release, and the expected-width review plate at
`build/of-11/runtime-publication-review.png`.

OF-12B and OF-13 remain deferred behind their separate demonstrated-need and reviewed-governance gates.

## Current safety statement

OF-12A proof is deterministic and local. It does not run Talisman, mutate live data, call a provider, touch
the Critter queue/controller/database, alter Seasons, or start OF-12B/OF-13. The JavaScript Critter mapper is
proving-ground evidence only; Object Factory owns the production body-type, geometry, UV, mapping, bake, and
persistence contracts.

2026-08-31 implementation checkpoint: the pure Lizard body type compiles one stable 25-joint Form/Atlas,
separate mapping and Standing poses, bounded Giant Crocodile and second-family variants, six exact camera
bases, independent creature geometry and face observations, and one-time opposite seeding. The
deterministic bake accepts only an exact settled deck and paired local pixels, rejects waiting or mixed
Creature/Front ancestry, and returns byte-equal PNG plus direct/blended/unresolved, role, asset/pixel digest,
confidence, and provenance truth. The browser evidence now keeps the image landmarks and green form outline
on separate projections, fits the exact joint/volume envelope without a hidden inset or arbitrary scale, and
uses object-relative orbit direction. Focused proof is the five-case Lizard domain selector and four-case
browser resource selector; no application, service restart, provider, queue, or live data was touched.

2026-09-01 browser presentation amendment: Copy Opposite, Save Mapping Default, Reset Mapping, and Remake
Selected Images are direct commands with no permission dialog. Existing disabled, busy, duplicate, stale,
Front-first prerequisite, request, queue, and terminal-result guards remain authoritative. Body-type names,
stance names, and pose instructions remain explicit data entry rather than confirmations.

## OF-UI-01 — Object Factory Shelf and Body Form compendium workbench

The smallest focused browser-resource proof must establish:

1. The shared workbench declares the Creature Shelf and independent Shader/Puppeteer/Object Boxes.
2. Each Shelf Item and Box has stable identity, accessible toggle/expanded state, independent collapse, and
   no collapse-time domain-state reset.
3. The declarative compendium contains stable revisions for Humanoid, Avian, Generic Quadruped,
   Horse-like, Lizard, Winged Lizard, Dragon, Serpentine, Arachnid, and Tentacled types.
4. Every type declares family, exact optional parent lineage, generated semantic controls, bounded named
   parameters, and at least one named stance.
5. Every type metadata record declares its stable Morph Form and Skeleton IDs, ovoid/cylinder envelope
   vocabulary, and bounded stance commands; each card leads with that neutral generated Morph Form cover.
6. Equal type plus parameter plus stance inputs produce equal generated Skeleton projection data.
7. Changing type, proportions, or stance cannot mutate the six independent observation map; Shader
   mapping/UV/Appearance actions cannot mutate the selected Form recipe.
8. Manufacture-before-texture order is visible and a changed Form visibly qualifies existing Appearance
   as compatible, pending, or migration-required rather than silently adopting it.
9. The current six-view mapping, UV/coverage modes, cameras, independent landmark editing, and reviewed
   paid image action remain present in the Shader Box.
10. Shader contains no Constrained Paint Guide card or preset; independent Outline, Projected rig,
   landmark, label, segment, and mask review remains without moving image observations or geometry.
11. Stable Shelf/Shelf Item/Box `data-of-*` identities and typed intent/result translation comments exist
   for later DTDT/Java adoption without granting JavaScript Native Package or Creature-assignment authority.
12. The 235-Creature assignment route is visibly later/reviewed and this body contains no controller,
    database, provider, queue, or automatic bulk-mutation call.
13. Object contains the sole switchable surface/control-point 3D workspace plus camera, Pose/Shape,
    grounding, and selection controls; Puppeteer contains no duplicate viewport.
14. The Morph label bar and Body Form chooser remain fixed while only the complete morphotype card deck
    scrolls; Morph Views stays a visible sibling and cannot be covered by catalog overflow.
15. Every open peer combination renormalizes its admitted shares to fill the complete workspace row without
    a trailing unowned gray region.
16. Creature startup retains the stable Object-stage host through interaction installation, reaches the
    exact-stamped detail refresh, and cannot leave a valid Gallery-selected Creature as the initial empty
    shell because a replaceable stage variable is out of scope.

Expected verification is the directly affected `SrdMonsterSixViewTextureMappingResourceTest` plus offline
Atlas validation and repository hygiene. No application launch, service restart, broad suite, provider, or
live-data action is authorized.

## OF-UI-ACTIVATE-03 — Triangulated Puppeteer studio surface

The pure JavaScript and directly affected resource selectors must prove:

1. equal control/profile graphs and mesh density emit byte-for-byte equal stable triangle records;
2. increasing bounded mesh density increases tessellation without changing semantic ownership IDs;
3. Lit and Texture each admit Wireframe both off and on, while an old Wireframe mode restores as Lit plus
   the independent overlay;
4. result rendering uses the triangle surface and never substitutes Skeleton bones as wireframe;
5. the Labels / Legend control labels every visible semantic point independently;
6. all four Arachnid feet on each side remain distinct forward-to-rear in standing and mapping stances,
   and mapping feet are flat and more widely splayed;
7. Puppeteer exposes four native Shelf Item buttons with exact Box IDs, expanded/pressed state, persisted
   independent visibility, and a one-row representative-width layout;
8. Gallery and Creature Morph Form cards consume structured type/revision/Skeleton/control/profile/
   parameter/override/stance truth and render deterministic mesh plus Skeleton first at the same size as
   neighboring cards, with no visible text except **Morph not assigned** when unassigned;
9. unavailable or not-yet-loaded Critter reads never claim an image was not generated; and
10. exact display-name deep links resolve to one canonical entity key or fail closed on unavailable,
    missing, or ambiguous current-assets truth;
11. Gallery double-click opens the same canonical exact-key Creature route as the explicit link;
12. face tabs plus active image state occupy the orthographic-image bar, and one persisted drawer-open
    state follows Front/Back/Left/Right/Top/Bottom changes; and
13. visibility/confidence help is plain-language while regeneration remains disabled unless the exact
    Critter mutation capability is advertised.

Expected verification is `object-factory-surface.test.mjs`, `critter-hosted-state.test.mjs`, the two
directly affected Java resource selectors, offline Atlas validation, and diff/convention hygiene. Managed
`4217` visual review occurs only after normal Main landing and Application Server reconciliation; Object
Factory performs no server lifecycle action.

## OF-UI-MIGRATE-02 — Hosted Critter Gallery and Creature surfaces

`ObjectFactoryHostedCritterResourceTest` and `critter-hosted-state.test.mjs` must prove:

1. Gallery and Creature are separate hosted Shelf pages and Batch never embeds Gallery;
2. Gallery reads current assets rather than operation history and leads each Creature with Morph Form;
3. one all-current progress read feeds Gallery aggregate/cards while Creature role subdecks show the same
   compact operation truth;
4. bootstrap admits only exact page/capability descriptors and immutable host/session stamps;
5. default native browser transport is receiver-safe for bootstrap and declared reads while injected
   focused fakes remain deterministic;
6. media resolution uses opaque media ID plus SHA-256 and no browser path;
7. unavailable adapters fail closed, stale read results are rejected, and close never cancels work;
8. generation/cancellation remain disabled throughout the exact read-only TAS-CRITTER-02 contract; and
9. no controller token, old direct endpoint, database/provider transport, private provenance, or `8766`
   fallback enters the hosted bytes.

The focused Java selectors also retain the six-view Shader/Puppeteer and landed parity fixture contract.
Offline Atlas validation and repository hygiene complete the body. No server process action, provider,
database, live-data mutation, broad suite, or standalone-server retirement is authorized.

## OF-UI-ACTIVATE-04 — Guarded exact-view Regenerate client

`critter-hosted-state.test.mjs` must prove:

1. connection and page load perform bootstrap/read work only and admit no generation;
2. Regenerate stays disabled when the capability is absent, unavailable, lacks private CSRF, or has any
   wrong method, authentication, CSRF, epoch, or replay metadata;
3. normalized bootstrap/stamp/public client serialization never exposes `csrf_token`;
4. an admitted request contains exactly the five frozen strings, same-origin/no-store transport, and exact
   content-type, CSRF, server-instance, and manifest-version headers;
5. success requires current capability/session/server/manifest stamps plus a matching immutable direct
   operation identity, Creature, and role, followed by same-stamp bootstrap revalidation;
6. remote structured rejection is preserved, a changed revalidation stamp fails stale, and close aborts
   only the in-flight browser request;
7. cancellation remains unavailable and no provider, paid call, live admission, queue, database, Seasons,
   server lifecycle, or `8766` action is part of proof.

Run only the JavaScript syntax/state selector, directly affected hosted-resource/parity selectors, offline
Atlas validation, and repository hygiene. TAS's Java authority transition must remain unavailable while it
lands; a later separately audited manifest-only body owns the actual availability flip and no-click browser
proof.

## OF-UI-ACTIVATE-05 — Reorderable three-Box Shelf and exact image overlays

`object-factory-surface.test.mjs` and `SrdMonsterSixViewTextureMappingResourceTest` must prove:

1. Shader, Object, and Puppeteer each have stable Shelf Item and Box identity plus independent open state;
2. valid order round-trips exactly while missing, duplicate, unknown, or malformed identities restore the
   default Shader/Puppeteer/Object order;
3. pointer movement below eight pixels remains activation, while a crossed threshold exposes a textual
   insertion target and commits one pair-order change only on a valid release;
4. Escape, pointer cancellation, invalid target, and release outside the Shelf preserve the prior order and
   cannot accidentally toggle a Box;
5. named Move Earlier/Move Later and Alt+Shift+Left/Right produce pointer-equivalent order while ordinary
   Arrow/Home/End moves roving Shelf Item focus;
6. open Box order derives from Shelf Item order, and existing Box nodes are moved without content rebuild;
7. Projected rig draws enclosing Form cylinders and ovoids in addition to the separate Skeleton reference;
   and
8. Outline is disabled without active image pixels, uses alpha or bounded background contrast for loaded
   pixels, and reports no usable silhouette instead of drawing invented geometry.

Expected verification is JavaScript syntax plus the pure surface selector, the directly affected Java
resource selector, offline Atlas validation, and diff/convention hygiene. No managed-server action, provider
or paid call, Critter/database/live-data mutation, application launch, broad suite, or Journey is authorized.

## OF-UI-ACTIVATE-06 — Canonical Destination and Creature Shelf structure

The smallest focused browser/resource proof must establish:

1. Batch, Gallery, and Creature each project the same fixed-order `SINGLE_CURRENT`/
   `SELECT_OR_FOCUS` Destination Shelf and expose one route-sized labeled current Box;
2. exactly one root item has `aria-current=page` and `aria-expanded=true`; reactivation focuses its Box,
   while Arrow/Home/End changes focus only and Enter/Space retains native activation;
3. Creature declares one reorderable `MULTI_OPEN`/`TOGGLE_OPEN` Shelf containing Shader, Puppeteer, and
   Object with `aria-controls`, exact expanded/pressed/open truth, position/set size, and stable identities;
4. repeated nested activation deterministically closes then opens the same retained Box, and open Box order
   remains item order filtered to open IDs;
5. the existing eight-pixel drag threshold, named/keyboard reorder alternatives, session stamp, invalid
   order rejection, splitters, and redundant Box Close remain;
6. the historical flat Puppeteer row remains absent from OF-UI-ACTIVATE-06;
7. an exact Creature key is retained only for the browser session and restored when returning from Batch or
   Gallery, while an explicit new key replaces it and malformed/oversized values fail closed;
8. direct keyless Creature remains empty, offers active Choose Creature plus unavailable New and Save,
   emits no domain mutation, uses no durable local storage, and cannot claim a saved result;
9. Surface visibility independently hides the rendered mesh or Form envelope while retaining Wireframe,
   Skeleton, Control Points, camera, selection, and underlying Form state; and
10. the Active Design records the exact later bounded Body Form/Stances composition without implementing it
   in this historical body.

Focused selectors are `object-factory-surface.test.mjs`, `critter-hosted-state.test.mjs`,
`ObjectFactoryHostedCritterResourceTest`, `SrdMonsterSixViewTextureMappingResourceTest`, and
`SrdMonsterImageBatchPageResourceTest`, plus exact owner-bundle digest/parity selectors if changed assets
require them. No broad suite, application launch, provider call, queue/database action, or server lifecycle
operation is authorized.

## OF-UI-SHELF-14 — Bounded Puppeteer Shelf composition

The smallest focused browser proof must establish:

1. Creature/Puppeteer mounts the exact `object-factory.puppeteer` Shelf containing Body Form, with the
   current Stance controls in the Puppeteer label bar;
2. Body Form mounts the exact `object-factory.puppeteer.body-form` Shelf containing fixed sibling labels
   Type, Proportions, and Transformation, with no flattened five-label row;
3. every label toggles one `REGION` Box through its twist state, no Box duplicates that route with a Close
   control, and each Shelf applies independent `MULTI_OPEN` occupancy without moving controls across levels;
4. close-through-label returns focus to the owning label; closing Puppeteer while descendant focus is active
   hides but
   retains both child open sets and returns focus to Puppeteer;
5. a versioned owner/contract/fingerprint envelope admits the complete root plus both child states, while
   missing, foreign, duplicate, stale, cross-level, or illegal fixed-order evidence rejects the whole graph;
6. fixed label order is the sole Box order at each child level and narrow horizontal overflow changes no
   identity, order, open state, focus target, or Close availability;
7. existing Body Form Type, proportion, Transformation, stance, Skeleton, image, and Object state survive
   open/close and parent close/reopen; and
8. the conversion adds no provider, queue, database, Native Package, generation, domain, Java/DTDT, or
   generic shared-Shelf authority.

Focused selectors are JavaScript syntax, `object-factory-surface.test.mjs`, the direct browser resource and
exact owner-bundle digest/parity selectors, Atlas validation, and hygiene. No broad suite, application launch,
provider call, live data, database/queue action, or server lifecycle operation is authorized.

## OF-UI-DTDT-18 — Declarative Shelf groups and Shader composition

The smallest focused proof must establish:

1. the shared v1 validator preserves the exact historical v1 group/label/REGION/layout shapes, while the
   explicit v2 validator rejects contradictory reorder/disclosure policy, illegal content,
   unbounded/cyclic child groups, cross-version identity, and stale definition structure;
2. Object Factory loads one checked DTDT before mounting the workbench and fails closed when it is invalid;
3. the compact root labels remain visible above Boxes and derive reorder, twist, occupancy, orientation,
   overflow, and adornment behavior only from their Shelf Group;
4. Shader/Puppeteer/Object remain one reorderable root label cluster and the six shared display controls
   mount after a visible divider from declared root Shelf Group content;
5. the Image Generation label contains Reset active mapping, its Box retains the six mapping views, and
   no duplicate Six-view Texture Mapping label remains;
6. no current twist Shelf renders a duplicate top Close control, no second 3D viewport returns, and no
   provider, queue, database, Native Package, or server-process authority changes; and
7. selecting one Shader face aligns and fits the sole Object viewport to that exact canonical face, with no
   duplicate Object orientation bar, while direct orbit remains available; and
8. Object exposes no plane/X/Y/Z toolbar, Plant/Release/Low-broad actions live in Puppeteer, the current Type
   summary renders inside the Type label, and the compact image strip contains only minus/Fit/plus/Copy.

Focused selectors are `shelf-contract-v1.test.mjs`, `shelf-contract-v2.test.mjs`,
`object-factory-surface.test.mjs`,
`SrdMonsterSixViewTextureMappingResourceTest`, `ObjectFactoryHostedCritterResourceTest`,
`ApplicationServerManifestStoreTest`, Atlas validation, and hygiene. No broad suite is authorized.

## OF-UI-DESTINATION-SHELF-16 — Manual destinations and one Object viewport

The smallest focused browser proof must establish:

1. Gallery, Creature, and Batch admit only one complete duplicate-free Manual order and default to Gallery,
   Creature, Batch when stored evidence is missing or malformed;
2. dragging the destination labels and Alt+Arrow keyboard reordering produce the same checked move and
   persist it once for reuse on every destination page;
3. no visible arrow, Move Earlier/Move Later, or instructional reorder controls remain;
4. activating a destination preserves the existing retained exact Creature-key route behavior;
5. Object surface and Pose/Shape choices replace the sole mounted viewport child rather than mounting or
   hiding two simultaneous 3D stages; and
6. no Creature, provider, queue, database, Native Package, generation, or server lifecycle authority changes.

Focused proof is JavaScript syntax, `critter-hosted-state.test.mjs`, `object-factory-surface.test.mjs`,
`ObjectFactoryHostedCritterResourceTest`, `ApplicationServerManifestStoreTest`, Atlas validation, and
hygiene. No broad suite, provider call, queue/database/live-data action, or server restart is authorized.

## OF-UI-STANCE-08 — Dramatic mapping specimen stance

The pure surface and directly affected resource proof must establish:

1. equal input joint records produce byte-for-byte equal immutable mapping layouts without mutating input;
2. identity, order, parent, mirror, label, and radius metadata survive the projection;
3. a representative Dragon mapping layout is more than 2.25 times its Standing width, less than five
   percent of its Standing vertical depth, and more than 1.25 times its axial span;
4. representative Humanoid Mapping rotates the upright axis into a longitudinal specimen span;
5. left/right distal controls remain on their declared sides and every Body Form uses the same pure route;
6. Mapping leaves feet at their flattened mapped coordinates rather than forcing standing ground pins;
7. Standing and non-Mapping named stances retain the existing code path; and
8. Shader contains no Constrained Paint Guide card, action, state, render class, or style while independent
   Outline and Projected rig remain.

Focused proof is JavaScript syntax, `object-factory-surface.test.mjs`,
`SrdMonsterSixViewTextureMappingResourceTest`, exact bundle/parity/manifest digest selectors, Atlas
validation, and hygiene. No broad suite, application/server lifecycle, provider, queue, database/live-data,
Shelf/DTDT implementation, or Journey action is authorized.

## OF-UI-STANCE-10 — Natural splayed Mapping

The corrected pure surface proof must establish:

1. equal input records still produce equal immutable output without input mutation or metadata loss;
2. the centerline remains ordered with shallow mapping relief rather than total depth collapse;
3. Humanoid arms become extended lateral chains with distinct shoulder, elbow, wrist, and hand positions;
4. Humanoid legs extend down and apart with ordered hip, knee, ankle, and foot positions;
5. quadruped front and rear chains extend outward in distinct forward/rearward natural rays;
6. wing roots, elbows, and tips remain ordered and substantially separated;
7. Arachnid leg indices and radial Tentacle directions remain distinct; and
8. runtime Mapping uses only this pure route while Standing and other stances remain unchanged.

Focused proof remains JavaScript syntax, `object-factory-surface.test.mjs`, the direct browser resource and
exact bundle/digest selectors, Atlas validation, and hygiene. No broad suite or excluded action is added.

## OF-UI-MAPPING-18 — Canonical Mapping axes and face cameras

The focused proof must establish:

1. longitudinal quadruped input becomes Y-up with head above hips and tail below hips;
2. already upright input stays Y-up instead of being laid into another plane;
3. Mapping remains naturally splayed, shallow on Z, deterministic, immutable, and metadata-preserving;
4. Front/Back, Left/Right, and Top/Bottom resolve to the exact opposing canonical camera presets;
5. unknown camera identities reject rather than falling back to a perspective view; and
6. the source-image bar contains only the six selected-state tabs, with no redundant orthographic heading
   or green current/status badge.

Focused proof is JavaScript syntax, `object-factory-surface.test.mjs`,
`SrdMonsterSixViewTextureMappingResourceTest`, exact owner-bundle digest checks, Atlas validation, and live
managed-page inspection without a process restart. No broad suite or excluded action is added.

## OF-GENMETA-11A — Optional control-metadata package contract

Focused owner-domain proof must establish:

1. strict equal input canonicalizes to equal JSON and SHA-256;
2. exact Creature/role, view/camera, stable control ancestry, normalized coordinates, radii, and envelopes
   survive;
3. unknown members, unsafe/path-like text, duplicate or missing ancestry, incompatible role/view, trailing
   JSON,
   non-orthonormal camera bases, excessive counts/bytes, and out-of-range numbers fail closed;
4. the legacy five-argument request keeps metadata absent and the provider prompt unchanged;
5. present metadata reaches one latched fake provider with exact accepted Front evidence for a non-front role;
6. exact retry admits no second provider call, while changed package evidence under the same client operation
   ID returns nonretryable `client_operation_metadata_conflict`; and
7. checkpoint evidence retains only the package SHA-256, with no raw package, path, image, or mesh payload.

Run only `ObjectFactoryImageControlMetadataPackageTest`, the exact generation-facade selector, offline Atlas
validation, and diff/convention hygiene. Do not run a real provider, server, live queue/database, application,
browser refresh, or broad suite.

## OF-UI-COPY-12 — Anatomical opposite-face copy

The pure browser proof must use deliberately asymmetric coordinates and establish:

1. Front→Back preserves exact `.L/.R` semantic IDs for shoulders, hips, front feet, and rear feet;
2. each destination screen X is exactly `1 - sourceX`, so anatomical Right moves from viewer-left in Front to
   viewer-right in Back and anatomical Left does the converse;
3. Back→Front returns every semantic point within numeric tolerance;
4. source observations remain unchanged and destination records are immutable and independently editable;
5. Left/Right retains the existing near-side mirror-identity mapping and outlines use the same canonical point
   transform; and
6. no image, 3D geometry, stance, source asset, or domain authority changes.

Focused proof is JavaScript syntax, `object-factory-surface.test.mjs`, the direct mapping resource and exact
bundle/digest selectors, Atlas validation, and hygiene. No broad suite or excluded action is added.

## OF-UI-DIRECT-13 — Confirmation-free explicit commands

The focused browser-resource proof must establish:

1. Copy Opposite calls the existing exact mapping transform without `window.confirm`;
2. Save Mapping Default and Reset Mapping call their existing routes without `window.confirm`;
3. Remake Selected Images calls `beginRemakeAll` with the already captured bounded role selection and has no
   permission dialog or stale second selection read;
4. the existing disabled, busy, duplicate, Front-first, hosted-client, stale, failure, and terminal-result
   paths remain present; and
5. body-type name, stance-name, and pose-instruction data entry remains available.

Run only JavaScript syntax and pure surface proof, the direct resource/digest selectors, offline Atlas
validation, and diff/convention hygiene. Do not launch or refresh the application, manage the server, submit
a provider or paid request, mutate the queue/database/live data, or run a broad suite.

## OF-MAPPING-ANATOMY-22 — Canonical image alignment

The focused browser proof must establish:

1. one canonical named-control graph plus a bounded silhouette returns immutable bounds, confidence, source,
   visibility, and unresolved evidence without changing either input;
2. terminal wing and tail controls use their parent-directed silhouette boundary while internal controls retain
   the canonical anatomical relationship;
3. malformed, duplicate, parent-incomplete, out-of-range, or too-small evidence rejects;
4. install is guarded by exact image digest, asset revision, Body Form type/revision, face, and Mapping stance;
5. locked controls are unchanged, and accepted controls update the one shared Form and all derived projections;
6. the prepared generation package uses the landed strict contract and exact canonical camera basis; and
7. the Analyze image control acknowledges activation before work and reports confidence/unresolved truth.
8. after an analyzed guide exists, a host without the optional control-metadata admission refuses the paid
   generation request and reports that no generation started.

Run only JavaScript syntax, `object-factory-surface.test.mjs`, the two direct hosted-resource selectors,
offline Atlas validation, and diff/convention hygiene. Hot-publish only the owner browser assets and inspect
the managed page without restarting Java. Do not call a provider, mutate a queue/database, or run a broad suite.

## OF-MAPPING-KIT-23 — Canonical Dragon Mapping kit

The focused browser proof must establish:

1. Dragon Front semantic anchors are deterministic, immutable, and place head, jaw, tail, paired limbs, wing
   supports, four wing digits, and foot digits into the reviewed splayed layout;
2. the projected canonical mesh produces a bounded deterministic silhouette before any Creature image exists;
3. Shader presents Front, Back, and Side while the six canonical projection functions remain available to
   rendering and free Object rotation;
4. Reset active mapping reconstructs the Dragon type and authored Front instead of restoring browser geometry;
5. Dragon does not silently fall back to the current Creature's old images for missing Back or Side templates;
6. Object stance changes render from a cloned control graph and do not mutate Mapping controls; and
7. the reviewed Front asset is packaged with the hosted page and is announced as authored rather than analyzed.

Run only JavaScript syntax, `object-factory-surface.test.mjs`, the two exact hosted-resource selectors, offline
Atlas validation, and diff hygiene. Hot-publish the owner browser assets and inspect port 3002 without restarting
Java. Do not call a provider, mutate a queue/database, publish Main, or run a broad suite.

## OF-MORPH-INFLUENCE-39 — Deterministic anatomical Morph canvases

The focused browser proof must establish:

1. Morph Front, Back, and true Side contain a 1,000-square canvas generated only from the current Form mesh;
2. the canvas uses one frozen anatomical palette and classifies head, neck, torso, pelvis, forelimbs, hind
   limbs, wings/webbing, and tail from stable triangle ownership;
3. each canonical camera supplies an explicit finite depth order before flat triangle painting, while Side
   retains equal horizontal and vertical world scale;
4. the programmatic occupied-mesh boundary is the visible canvas outline, and Form edits repaint the same
   canvas before the 2D rig and synchronized Object projection;
5. Morph loads no prior creature or canonical reference raster, while Shader retains the accepted
   Creature-specific image as its only Object texture source;
6. the exact guided-generation package now contains the 512-square flat color matrix, and instructions state
   that its silhouette and anatomical boundaries are placement constraints rather than desired colors;
7. the visible influence legend uses the same palette and names, with no provider or paid request on load;
8. Body Form uses a full-width horizontal selected-Morph shelf header above its one-card-per-row left selector
   and right editor, and moves the selected Form to the top; and
9. activating that shelf header hides or restores the whole existing selector/editor layout in place, leaves
   Morph Views available, and does not change the working Form, editor state, or view transform.

Run only `object-factory-surface.test.mjs`, `SrdMonsterSixViewTextureMappingResourceTest`,
`ObjectFactoryHostedCritterResourceTest`, offline Atlas validation, and diff hygiene. Hot-publish only the
three owner browser resources and inspect alpha 1.27 at port 3002 without restarting Java. Do not submit a
provider request, mutate a queue/database/live Creature asset, run a broad suite, or claim permanent Body Form
Save while the delegated host capability remains unavailable.

## OF-OBJECT-SURFACES-40 — Composed Object surfaces

The focused browser proof must establish:

1. Object offers mutually exclusive Paint by numbers and Generated surface controls; Generated surface is
   unavailable without an enabled loaded image;
2. Paint by numbers colors the actual triangulated Object mesh with the same stable anatomical ownership
   palette as Morph;
3. Wireframe remains an Object rendering style in both surface modes, but the root Geometry control is its
   parent visibility; semantic controls continue to follow their corresponding root controls;
4. Shader presents Front, Back, Left side, Right side, Top, and Bottom as one checked ordered list whose
   default order is stable;
5. every projection can be enabled or disabled and reordered by pointer or accessible step controls;
6. generated texture selection walks enabled, loaded projections in list order, rejects triangles facing away
   from that camera, and stops at the first non-degenerate mapping, so a later image cannot overwrite an
   earlier claim and transparent pixels cannot reject an otherwise valid triangle;
7. selecting a Shader projection changes only Shader preview/Image Data, not Morph's active edit view or the
   Object camera; and
8. projection order, enabled set, Shader selection, and Object surface preference round-trip only through
   browser recovery.

Run only `object-factory-surface.test.mjs`, `SrdMonsterSixViewTextureMappingResourceTest`, and
`ObjectFactoryHostedCritterResourceTest`, plus diff/line hygiene. Hot-publish only the three Object Factory
browser resources and inspect alpha 1.28 at port 3002 without restarting Java. Do not submit a provider
request, mutate a queue/database/live Creature asset, run a broad suite, or claim new persistence authority.

## OF-SHARED-VISIBILITY-41 — Shared visibility controls

The focused browser proof must establish:

1. Landmarks, Labels, Segments, Outline, and Overlay share one normalized browser-local state;
2. Morph, Shader, and Object all repaint immediately when any shared display value changes;
3. Labels remain visible independently of landmark markers, and each semantic layer can be hidden without
   changing selection, camera, Form, Creature image, projection priority, or Object surface mode;
4. Object Outline follows the union of projected surface triangles rather than the control graph or every
   internal mesh edge, and Object semantic control points follow the shared Landmarks control without a
   contradictory local master checkbox;
5. the anatomical influence legend appears once in the Creature title bar with larger keys and no duplicate
   inside Morph; and
6. the Morph Proportions drawer retains a 300-pixel working width without covering or overflowing its own
   controls.

Run only `object-factory-surface.test.mjs`, `SrdMonsterSixViewTextureMappingResourceTest`, and
`ObjectFactoryHostedCritterResourceTest`, plus offline Atlas validation and diff/line hygiene. Hot-publish
only the three Object Factory browser resources and inspect alpha 1.29 at port 3002 without restarting Java.
Do not submit a provider request, mutate a queue/database/live Creature asset, run a broad suite, or claim
new persistence authority.

## OF-UNIFIED-VIEWS-43 — Unified Views drawer

The focused browser proof must establish:

1. exactly one Front, Back, Left side, Right side, Top, and Bottom card deck exists;
2. its separate Views Shelf Item appears immediately before Morph and opens one full-width drawer directly
   below the root bar;
3. all six cards remain centered in one non-stretching row, and the size slider clamps from one quarter to
   full card size;
4. cards retain accepted-image/empty truth, enablement, direct drag priority, and accessible keyboard
   reordering without step buttons;
5. selecting a card chooses the same face in the large Morph and Shader surfaces and aligns Object without
   changing its stance or surface;
6. the former nested Morph Views and Shader Views labels and duplicate compact decks do not exist;
7. active face, scale, priority, and enablement round-trip only through browser recovery; and
8. the large Morph influence canvas uses a translucent dark background, while anatomical colors and shared
   opacity remain visible on the large canvas and compact card.

Run only `object-factory-surface.test.mjs`, `SrdMonsterSixViewTextureMappingResourceTest`, and
`ObjectFactoryHostedCritterResourceTest`, plus offline Atlas validation and diff/line hygiene. Hot-publish
only the exact changed Object Factory browser resources and inspect alpha 1.31 at port 3002 without restarting
Java. Do not submit a provider request, mutate a queue/database/live Creature asset, run a broad suite, or
claim new persistence authority.

## OF-SHADER-REGISTRATION-42 — Shader image registration

The focused browser proof must establish:

1. Morph and Shader each expose the same six canonical projection identities in stable default order;
2. Morph cards show the deterministic anatomical-color projection, while Shader cards show Image/Empty and
   an optional fixed outline;
3. Shader cards retain independent enablement, direct pointer drag priority, and chrome-free keyboard
   reordering without earlier/later step buttons;
4. every Shader image owns an independent bounded zoom/pan/axis-scale transform; wheel zoom preserves its
   pointer anchor, drag pans, and each edge bar resizes one axis while keeping the opposite edge anchored;
5. the registered image coordinates drive generated-surface texture sampling, while Fit image resets only
   that view;
6. changing Shader views restores the saved image transform while the shared semantic SVG remains fixed;
7. order, enabled views, selection, and all six transforms round-trip only through browser recovery; and
8. a stale hosted-page stamp hides the paid action and gives a reload explanation rather than claiming the
   live generation capability is unavailable.
9. Morph has no unowned trailing layout row; its working canvas receives all space below the resizable
   control shelf, catalog covers use Mapping stance, and shared opacity reaches both six-card sets.
10. an accepted image without an optional digest still receives a stable sampler identity and enables the
    generated surface after its managed pixels load.
11. Geometry is one root control beside Landmarks, Labels, Segments, and Outline. Turning it off removes the
    editable Form overlay from Morph, Shader, and Object, gates Object Wireframe, and leaves all three
    underlying image/surface layers unchanged.

Run only `object-factory-surface.test.mjs`, `SrdMonsterSixViewTextureMappingResourceTest`, and
`ObjectFactoryHostedCritterResourceTest`, plus offline Atlas validation and diff/line hygiene. Hot-publish
only the three Object Factory browser resources and inspect alpha 1.30 at port 3002 without restarting Java.
Do not submit a provider request, mutate a queue/database/live Creature asset, run a broad suite, or claim
new persistence authority.

## OF-MORPH-WORKSHOP-44 — Fixed-form Adult Dragon workshop

The focused source and standalone-browser proof must establish:

1. the Adult Dragon source fixture remains one complete fixed form with exactly 66 Points, one root, 65
   parent Joints, four declared limb chains, fifteen profiles, ten front digits, ten rear digits, and eight
   wing digits;
2. Resting points every front and rear digit forward, folds both wings against the body, and preserves the
   reviewed `head` and `tail.tip` coordinates;
3. Mapping and Resting remain the only default poses;
4. `adultDragonMappingComparisons(...)` returns the three named comparisons in stable order, and every
   comparison supplies a complete frozen Mapping state rather than an inherited child object or persisted
   delta;
5. `mapping-digits-wide` increases front/rear digit span relative to `mapping-digits-balanced`, while
   `mapping-wing-fingers-forward` sweeps the wing digits farther forward;
6. selecting Baseline restores the exact source Mapping state and repeated comparison changes cannot drift;
7. the Morph knows exactly the `resting-mapping` and `shuffle-walk` motion pairs in this workshop revision;
8. every motion has exactly two endpoints and the same record plays forward or backward;
9. `shuffle-walk` alternates left-fore/right-rear and right-fore/left-rear, retains folded wings, and does not
   alter the head or tail shape;
10. one generic pair selector, endpoint buttons, play, pause, loop, and scrubber operate both motion pairs;
11. the compact workshop moves statistics into a closed Details disclosure and retains orbit, pan, zoom,
    fit/reset, projection presets, baseline overlay, and keyboard/pointer/touch camera controls;
12. camera, comparison selection, playback progress, and disclosure state remain presentation-only;
13. fixed Core Morphs are never edited in place: the design requires copy-then-modify of a complete
    independent object and forbids behavioral parent links, base-plus-overrides object resolution, prototype
    inheritance, variant stacking, automatic rebase, and later source propagation into a copy;
14. the workshop patch registers no web-server page, adds no route or database table, and changes no
    provider, Shelf, Java-conversion, Creature, Appearance, generated-asset, or live database authority; and
15. the cumulative patch applies cleanly to the supplied source archive and contains no image handoff.

Run only `object-factory-surface.test.mjs`, standalone-workshop JavaScript syntax and browser interaction
checks, offline Atlas validation where available, and diff/line hygiene. Do not launch providers, mutate a
live database, register the workshop with the Application Server, or run a broad unrelated suite.

### Ancient Dragon editor acceptance — IMPLEMENTED

The focused source/workshop proof establishes:

1. one compact top-line Morph type dropdown exposes the complete source catalog and initially selects Ancient
   Dragon;
2. selecting an enabled type opens that complete fixed source object for authoring without inheritance or
   changing the future copy-before-user-edit rule; types without a standalone snapshot remain disabled;
3. the primary editor no longer depends on the Adult Dragon comparison menu;
4. Ancient Dragon is larger than the retained Adult Dragon form by explicit textual dimensions/coordinates,
   not by a camera trick or display-only scale;
5. Mapping preserves meaningful depth and the head faces forward under front, side, top, and orbit inspection;
6. the head graph includes head/cranium, upper jaw, lower jaw, muzzle/nose tip, horn roots/tips, optional
   adornment anchors, mouth opening/closing, and head/neck fore-aft articulation;
7. Standing is the Resting default, while Step 1 and Step 2 are complete named stance/motion endpoints inside
   the same complete Morph object;
8. the walk can play Step 1 → Step 2 repeatedly and in reverse without accumulating coordinate drift;
9. To Mapping/From Mapping operates from the currently selected stance rather than only Resting;
10. the right rail is reserved for form, stance, head/jaw, wing, digit, and motion authoring controls;
11. camera diagnostics, motion diagnostics, source-contract prose, and always-visible statistics are absent
    from the rail, while direct stage orbit/pan/zoom and a closed Details disclosure remain; and
12. every authoring control makes a visible geometry or coordinate change and does not merely light up an
    otherwise unchanged point set.

Focused evidence is `object-factory-surface.test.mjs`, source and embedded-workshop JavaScript syntax checks,
the generated-data contract check, offline Atlas validation, and diff/line hygiene. No Application Server
route, database materialization, provider work, Java conversion, or live application launch is part of this
isolated workshop proof.

2026-09-05 focused receipt: source syntax passed; embedded workshop syntax passed; the one focused JavaScript
test passed, including generated-data and direct-control coordinate checks; offline
`validateDevelopmentArchitectureAtlas` passed; and `git diff --check` passed. A live application/browser
launch was intentionally not performed.

## OF-MORPH-CATALOG-45 — Ancient Dragon materialization and read verification

Run only `CoreMorphCatalogServiceTest`, `ApplicationServerCoreMorphReadTransportTest`, the focused
`object-factory-surface.test.mjs` selector, source/workshop syntax checks, Atlas validation, and diff hygiene.
They prove that:

1. the initial authoritative catalog contains one fully extracted Ancient Dragon document and does not infer
   entries from empty Factory object tables or legacy browser-local morphotypes;
2. the document is self-contained and requires no parent Morph, variant resolver, or inherited delta;
3. source and stored bytes match the catalog SHA-256, same-revision drift fails closed, and a repeated exact
   synchronization is a true no-op;
4. the database projection retains 73 Points, ordered Standing/Mapping/Step 1/Step 2 stances, and all four
   named motions with exact revision, duration, loop policy, stance references, and complete keyframes;
5. both GET routes require an App Session, reject malformed queries, and return a typed not-found problem;
6. the authenticated catalog and selected package match the generated exact response fixtures; and
7. the focused transport starts only an ephemeral test controller and performs no live application/server,
   provider, user database, copy, Save, or Save As action.

OF-MORPH-COPY-46 retains separate future proof for remaining complete-source extraction and atomic
independent copy-then-modify behavior.

## OF-MORPH-PUPPETEER-SHELF-47 — DTDT and canonical Shelf front-side workbench

Run only the focused Core Morph catalog validator, executable-script syntax check, one isolated Chromium
interaction pass over the self-contained workbench, the canonical Shelf v2 definition validator, Atlas
validation where available, and diff hygiene. The proof must establish:

1. `morph-puppeteer-workbench.dtdt.json` validates under the repository's
   `talisman.shelf-contract/v2` validator with the exact owner identity and definition fingerprint;
2. the DTDT declares one fixed vertical, scrolling, multi-open group using `TWIST_LABEL`, disabled reorder,
   the nine ordered owner editor labels, and one exact Box region per label;
3. the one-page DOM contains exactly those nine stable Shelf Item/Box pairs, in declared order, and no
   top-level native `<details>` substitute or duplicate Box-close control;
4. Complete textual Morph is the sole declared-open Box when no valid browser-local presentation memory is
   admitted;
5. the persistent label changes between canonical `shelf.box.open/v1` and `shelf.box.close/v1` intent
   identities, updates `aria-expanded` and `aria-pressed`, opens one Box without closing peers, moves focus
   into the opened Box, and restores focus to the label after Escape or close;
6. Up/Down/Home/End provide roving label focus across currently available Items and hidden body-plan tuning
   never remains open or focusable for a Morph that does not support it;
7. open, close, focus, clear-focus, and divider intents use `APP.SHELF.UI_BUS.PRIMARY` with
   `PAGE_LOCAL_ONLY` scope and cause no network, application-service, database, provider, or production
   event-bus action;
8. the stage/editor divider supports equivalent bounded pointer and keyboard resizing, publishes
   `shelf.divider.resize/v1`, updates separator value semantics, and changes presentation geometry only;
9. browser-local memory admits one complete payload only when contract, owner, definition, fingerprint,
   complete keys, current Box IDs, selected Morph, and ranges validate; stale, foreign, partial, or invalid
   payloads fail closed as a whole without restoring any field;
10. All skeletal lengths and All geometry parameters remain complete hierarchical Point-parent trees inside
    their Boxes, with incoming-link, radius, and profile leaves before child groups and ancestor-preserving
    filtering;
11. all twelve complete Morphs still select and render, the stage camera and existing Morph controls keep
    their feature ownership, and no Morph bytes, catalog digest, pose, stance, motion, or geometry attachment
    changes in this Shelf-only body; and
12. the patch changes only the owner DTDT, one-page workbench, focused validator, and directly controlling
    Morph design/test records, with no Application Server, production Object Factory, Java, database,
    synchronization, provider, or unrelated Shelf implementation changes.

## OF-MORPH-PUPPETEER-48 — combined Point parameters, reorderable Shelves, and live stance field

Focused owner-workbench proof must establish:

1. the editor declares one canonical Shelf v2 group using manual ordering and `POINTER_AND_KEYBOARD`
   reordering, and user Shelf order persists only as presentation memory;
2. `Point parameters` replaces separate skeletal/geometry collections and each Point branch contains its
   incoming length plus its own radius/profile controls before child Point branches;
3. single 3D Point selection opens/focuses the exact combined Point branch and selection emphasizes both the
   Point's local geometry and outgoing edge geometry through immediate children;
4. the `Puppeteer` Shelf is declared in DTDT as an artificial 2D control space with no model X/Y/Z semantics;
5. every stance has an inclusion checkbox, every checked stance has one labeled draggable Puppeteer point,
   and one direct-edit stance remains selected independently;
6. dragging a stance point changes only the unrecorded Puppeteer layout;
7. dragging the red circle-plus target changes the live 3D pose by normalized inverse-square influence over
   all checked complete stance endpoints, with exact-hit resolution to one stance;
8. closing the Puppeteer Box disables the live field and returns rendering to the ordinary stance/motion
   state; and
9. no Puppeteer layout, target path, Morph edit, database row, provider action, Application Server route, or
   Java implementation is added by this workshop body.

## OF-MORPH-ORBIT-49 — orbit-widget hover and pointer priority

Run only the focused Morph Puppeteer workbench validator, executable-script syntax check, one isolated
browser interaction over the self-contained workbench, and diff hygiene. The proof must establish:

1. the visible axis/orbit widget exposes its complete circular mouse-over hit region and visible hover
   feedback;
2. dragging inside that region always changes camera orbit, even with Shift or Control held, and does not
   change the pan target;
3. Shift-dragging empty stage space still pans without changing yaw, while ordinary empty-stage dragging
   still orbits; and
4. the correction remains browser-local presentation with no Morph, persistence, service, bus, database,
   provider, Application Server, or lifecycle change.

## OF-SHADER-CAGE-50 — Non-destructive Shader control-cage registration

Run only the focused Object Factory surface JavaScript proof, the two directly owning resource checks,
JavaScript syntax, offline Atlas validation, and diff hygiene. The proof must establish:

1. source pixels, managed identity, and digest remain unchanged through automatic fit, landmark drag, view
   switching, browser recovery, and Reset;
2. every canonical view owns an independent normalized snapshot with the matching source key, monotonic cage
   revision, bounded offsets, and explicit visible/confident/unresolved observation result;
3. equal source outline, target Mapping silhouette, viewport, observations, and registration inputs produce
   the same first fit, points, triangle order, unresolved list, and derived registered canvas;
4. Front and Back use their complete visible observations most strongly, while hidden, low-confidence,
   missing, and occluded controls on every face remain unresolved rather than receiving invented positions;
5. one landmark drag changes only its bounded offset and adjacent piecewise-affine triangles, preserves fixed
   boundary anchors and shared edges, and cannot invert or collapse a triangle;
6. Reset removes the view's global transform and cage offsets and restores the untouched source presentation;
7. a draft for another image or view is rejected, while geometry, global transform, viewport, orientation,
   and cage revisions invalidate the stale derived mesh;
8. Shader's registered preview and Object's first-claim sampler use the same current derived registered canvas
   while Object retains canonical first-claim UVs, including explicit unresolved points outside admitted cage
   triangles;
9. source, cage, checked-view priority, global registration, viewport/orientation, and geometry changes
   invalidate only dependent texture work; orbit, stance, and motion reuse it without repaint blinking; and
10. pointer release and pane disposal end drag and pending paint work without network, paid generation,
    provider, database, Core Morph, accepted-media, or server lifecycle action.

Do not run the broad suite or a live provider request. A runtime review may follow only under the owner's
separate launch or hot-publish authority.

## OF-OBJECT-GPU-51 — Retained six-view Object surface

Run `object-surface-webgl.test.mjs`, the directly affected Object Factory surface resource proof, JavaScript
syntax, offline Atlas validation, and diff hygiene only. The focused proof must establish:

1. one packed topology is partitioned exactly once into at most seven stable draw batches—six canonical view
   owners plus unmapped—with no missing or duplicated triangle;
2. the exact ordered triangle roster and IDs survive every named stance and representative motion midpoint,
   including a pose that makes a valid face temporarily edge-on or nearly degenerate;
3. Front/Back, Left/Right, and Top/Bottom each use their assigned Shader inspection pose, the two opposing
   cards in a pair share that pose, and changing one pair assignment invalidates only its dependent claims;
4. the defaults expose breadth for Front/Back, asymmetric one-forelimb-up/one-forelimb-down coverage for
   Left/Right, and depth-spread coverage for Top/Bottom; each assignment is editable through Shader View
   Parameters without becoming Morph playback state;
5. canonical claim baking rejects reverse-facing and truly edge-on source triangles, applies bounded
   first-hit/depth ownership, retains small finite non-degenerate projections, and joins claims to the current
   posed Object by stable triangle identity;
6. source, cage, geometry, checked-view membership, enabled order, and pair-pose assignment are the only
   claim/UV rebuild inputs;
7. camera orbit changes uniforms and uploads no topology, UV, color, or image data, while Morph stance/motion
   uploads positions only and preserves triangle identity, claims, and UVs;
8. per-source opacity and mapped-but-image-free card colors survive the retained path, and checked sources
   appear together without selected-card or camera override;
9. the transparent WebGL layer stays between the Canvas2D ground plane and SVG annotations, and unavailable,
   lost, or failed WebGL selects the exact Canvas2D surface fallback without a blank frame;
10. context restoration can recreate retained resources, while page disposal releases buffers, textures, and
   scheduled frame work; and
11. Object exposes no Animation Shelf, stance chooser, motion chooser, Play, Loop, or second playback state;
    Morph remains the sole playback owner and Object renders its current pose;
12. Shader's picture consumes the remaining takeover height without a blank flexible row; and
13. the named-view HUD updates on every orbit frame, points inward from the selected viewpoint, and stays
    inside visible bounds for Front, Back, Left, Right, Top, Bottom, and representative free-orbit positions.

No broad suite, application restart, live provider, persistence, or database action belongs to this proof.

## OF-MORPH-SAVE-52 — Template/private Morph save authority

This body begins only after live alpha 1.66.1 acceptance. Its future focused proof must establish:

1. the top-left Template control exposes ON as canonical Morph-type scope and OFF as current-Creature private
   scope, with an accessible name and state that do not rely on color;
2. switching scope preserves the exact unsaved draft and performs no save, copy, reset, association change,
   linked-Creature propagation, or image change;
3. first Save presents only context-valid New/Update choices and their exact target before admission;
4. every New or Update creates a complete new immutable revision and leaves all earlier revisions unchanged;
5. every Creature receives and pins a private Morph copy/revision at creation, with exact master ancestry;
6. Template Update advances the canonical master only for future copies and changes no existing Creature,
   private Morph revision, identity, image, or association;
7. Creature Update advances only the current Creature's private Morph association and cannot change the
   canonical Morph type or another Creature; and
8. stale target, stale base revision, failure, or cancellation leaves the draft,
   canonical revision, Creature associations, identities, images, and prior revisions unchanged.

A later migration body's proof must separately establish the informational **Master template changed; review
update** state, explicit preview/acceptance, creation of a new private revision, preservation and recovery of
the prior private revision, and complete absence of automatic propagation. None of that migration behavior is
implemented or accepted by OF-MORPH-SAVE-52.

No AI/provider call, projection-card work, OBJ intake, sound authoring, broad suite, application restart, or
live-database exercise belongs to this queued body's focused proof without separate authority.

The deferred New projection card, lock/unlock, OBJ candidate-view/control estimation, and text-only
OpenAI/Codex/Ollama authoring sequence has no current implementation acceptance. Focused tests for
OF-OBJECT-GPU-51 must reject accidental arbitrary-card persistence, imported-control authority, AI transport,
binary authoring payloads, Morph mutation, or sound generation introduced through this active body.

### Morph Puppeteer Web/local-transform acceptance

- A Web source object has a unique `web_id`, at least three unique stable Point anchors, complete local offsets, tension, sag, thickness, and edge-tension coverage.
- Point selection reveals the exact Point Parameters branch, its incoming length, local solid geometry, and every shared Web anchored there.
- Selected Web geometry obeys the same geometry-opacity multiplier as ordinary geometry.
- The local gizmo exposes exactly six signed model-axis translation handles and two rotation rings; none rotate the whole Morph.
- Translation preserves the selected Point's incoming segment length and rigidly carries its downstream subtree. Rotation rings preserve the selected Point and all tested downstream segment lengths.
- Right-click Duplicate stance produces another independently movable Puppeteer instance referencing the same complete stance pose.
- Mapping shortcut and Mapping selection in the Stances Shelf converge on the same Mapping state.
- Embedded DTDT documents are byte/structure equivalent to their checked external workshop definitions.


### Locked-chain gizmo checks

- Verify gizmo hit targets receive pointer events before the stage orbit/pan handler and expose the transform hover detector.
- Multi-select a Humanoid hip and same-side foot, make the foot primary, move it with a gizmo axis, and verify the hip stays fixed, the knee/ankle are free to bend, and every edge length in the solved chain remains unchanged.
- Verify an already bent chain continues to bend on the same side; an exactly straight ambiguous chain resolves deterministically until the owner introduces a bend.
- Verify Point Parameters opens all selected Point paths and focuses the primary Point incoming length leaf.

### Puppeteer graph-navigation and digit-spacing checks

- Plain-click the primary selected Point twice and verify the second click hides/shows only the gizmo while preserving the selection and Point Parameters reveal.
- With a multi-selection, plain-click another already-selected Point and verify it becomes primary without dropping the lock set; click an unselected Point and verify it replaces selection and moves the gizmo.
- With stage focus, verify Up selects the parent, Down selects all direct children, and Left/Right select logical siblings with wraparound; verify these keys do not alter gizmo transforms.
- With Mirror L/R enabled, rotate a unilateral joint using each gizmo ring and verify the counterpart receives the reflected opposite-signed rotation; with mirroring disabled, verify the counterpart remains unchanged.
- Verify rotation-ring hover hit paths remain close to their visible rings and present stronger hover feedback without allowing stage orbit to capture the same pointer sequence.
- Select all digit children of one parent, right-click a selected digit, and verify the Digit spacing popup appears. Increase and Decrease must operate in the group's best-fit average plane.
- With `Adjust skeletal geometry` off, verify every parent-to-digit length remains unchanged. With it on, verify only the selected parent-to-digit skeletal lengths may change; parents, other skeletal edges, solid geometry authority, and Web authority remain unchanged while render geometry follows Point motion.

## OF-MORPH-PRODUCTION-46 — Production Morph/Puppeteer JavaScript and Factory integration

Focused acceptance proves that:

1. `critter-editor.js` remains the production entry and the Creature Factory primary Shelf retains fingerprint
   `object-factory.creature-shelves/v18`;
2. the primary Shelf order is Morph, Morph Editor, Shader, Object, with no primary Puppeteer item;
3. Morph Editor loads the currently selected Core Morph through the authenticated current-revision read
   client and rejects stale asynchronous selections. When that initial asynchronous catalog read resolves,
   an untouched legacy fallback is atomically replaced in Shader/Object by the same selected Morph identity,
   profiles, chains, and explicit Webs; only a compatible genuine draft or intervening edit is preserved;
4. the production adapter retains the approved Morph/Puppeteer, Point-selection, influence-field, Web geometry,
   complete Point hierarchy, stances, motions, geometry attachments, and source-revision data;
5. the embedded Puppeteer remains inside Morph Editor with Webs, gizmos, lock-chain, mirror, digit-spacing, and
   artificial influence-field behavior intact;
6. selection changes preserve honest in-memory session drafts while browser teardown disposes selection epochs,
   playback, and editor listeners;
7. no Save revision or Save-as control is shown because no authoritative Core Morph mutation contract exists;
   no Body Form persistence route, browser-local storage, or immutable-source rewrite is substituted; and
8. the new JavaScript/CSS assets are registered in the Creature page manifest and load through the existing Factory
   shell without replacing Views or global display controls; and
9. the browser fixture mounts beside colliding sibling controls, proves style/control isolation and multi-instance
   ownership, switches A→B with a stale A read, returns B→A after a real geometry-radius edit, verifies that the
   session draft survives exactly once without reapplying the parameter, and verifies collapse/reopen, an
   unclipped toolbar, the same visible orientation-aware splitter in wide and stacked layouts, independent
   divider allocations, failed/abandoned mount cleanup, and listener disposal. The retired authority strip,
   opacity value, Selected complete object, Display key, and Point Parameters explanation remain absent.

Run `morph-puppeteer-editor.test.mjs`, `morph-puppeteer-editor-browser.test.mjs`,
`critter-hosted-state.test.mjs`, `object-factory-surface.test.mjs`, JavaScript
syntax/diff hygiene, and the focused `ObjectFactoryHostedCritterResourceTest` and
`SrdMonsterSixViewTextureMappingResourceTest` when the repository's Gradle distribution is available. Do not run a
live deployment, restart the server, or mutate live data.
