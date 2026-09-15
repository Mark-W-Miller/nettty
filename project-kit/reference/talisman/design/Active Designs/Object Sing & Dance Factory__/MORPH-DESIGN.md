# Morph Design

The separate [Slice-native storage contract](SLICE-NATIVE-STORAGE.md) preserves complete Slice documents
without converting them to this Core Morph format. It adds no Type replacement, alias or Creature link.

Date: 2026-09-05
Status: Complete twelve-Morph source catalog, database materialization, and read path implemented
Owner: permanent **Object Sing & Dance Factory** task
Companion authority: [`SYSTEM-DESIGN.md`](SYSTEM-DESIGN.md)

## 1. Decision

A Morph is a **complete object**. A fixed Core Morph owns its complete skeletal definition: stable Points,
Joints, simple geometry/profile attachments, exactly two complete default poses named `mapping` and
`resting`, and its complete collection of reversible motion pairs.

There is no Morph inheritance. A Morph does not inherit from another Morph, resolve a parent chain, apply a
prototype, or remain linked to a source object through overrides. Family labels and copy provenance may be
recorded as descriptive metadata, but they have no behavioral effect.

A user never edits a fixed Core Morph in place. Selecting one for editing means **copy the complete object,
then edit the copy**. The copy receives a new identity and contains all Points, Joints, attachments, poses,
and motions present at copy time. Later changes to the Core Morph do not propagate into existing copies.

Core Morph source files are the authoring authority. The database is mandatory materialization of complete
source revisions, never an independent authoring source. The verified 2026-09-05 twelve-form patch is the
current catalog input. No database inventory may be invented independently of those checked source objects.

No image is part of a Morph handoff. Silhouettes, generated meshes, textures, and cameras are presentation
results derived from a complete Morph object.

## 2. Current workshop boundary

This chat is an isolated Morph workshop. Until the owner explicitly says otherwise, work here may change:

- the source-side fixed Morph definition being reviewed;
- temporary comparison generators used to inspect possible complete forms;
- linked motion-pair data owned by that Morph;
- the standalone browser workshop and its focused tests; and
- this Morph Design and its focused test plan.

The workshop itself must not wire an Application Server route, alter broad Factory interface behavior,
change provider work, write a database, or disturb unrelated Shelf work merely to make review available.
Periodic cumulative `.patch.zip` backups preserve workshop work for the separate permanent Morph Patch
integration owner.

The downloaded workshop patch remains isolated input. The implemented production adapter and typed service
described below deliberately perform its source-authoritative catalog, database, and read-path integration.

### 2.1 Authoring workshop versus product editing

This owner workshop edits fixed Core Morph source objects directly. That is how the canonical forms are made
and corrected before they are handed to the rest of the application. It does not weaken the product rule:
an application user still cannot edit a fixed Core Morph in place and must copy the complete object first.

The workshop therefore has two deliberately different roles:

- **Core-form authoring here:** select one source Morph type and edit that complete fixed source object; and
- **future product editing:** select a fixed Core Morph, copy it under a new identity, and edit only the
  independent copy.

Neither role introduces inheritance. Every saved Morph remains one complete object.

### 2.2 Current workshop revision: complete Core Morph catalog

The current source and runnable catalog workshop implement all twelve complete forms. **Ancient Dragon**
remains the initial selection and the largest dragon-family form; Adult Dragon and Dragon are independent
complete objects with the same normalized graph and motions at smaller uniform sizes.

The editor:

1. place one **Morph type** dropdown in the compact top line, populated with all source catalog types;
2. open the selected enabled Core Morph type as the object currently being authored in this owner workshop;
3. make Ancient Dragon the initial selection and first form brought to review quality;
4. store the form as textual, source-controlled skeletal/geometry data wherever practical—Points, Joints,
   profiles, simple geometry attachments, poses, stance keyframes, and motions—with no texture maps or image
   authority;
5. preserve a true three-dimensional object in Mapping as well as Resting, so orbiting shows meaningful depth
   instead of a flattened diagram;
6. keep the Ancient Dragon head facing forward in Mapping;
7. expand the head graph to support a cranium/head control, upper jaw, lower jaw, muzzle/nose tip, horn
   roots/tips, optional adornment anchors, mouth opening/closing, and useful head/neck fore-aft articulation;
8. expose **Standing**, **Step 1**, and **Step 2** as the first authored stance set, with Standing being the
   Resting default rather than a third default pose;
9. preserve exactly Mapping and Resting as the two default poses while allowing named stance/motion endpoints
   such as Step 1 and Step 2 inside the complete Morph object;
10. support a walk assembled as Step 1 → Step 2 → Step 1 → Step 2, with the same motion data playable in
    either direction;
11. provide a To Mapping/From Mapping transition from the currently selected stance rather than assuming that
    Resting is the only non-Mapping state;
12. replace the Adult Dragon comparison dropdown as the primary workflow with direct, clearly labelled
    controls for the selected Morph's form, stances, head/jaw, wings, digits, and motions; and
13. devote the right rail to those authoring controls. Camera diagnostics, motion diagnostics, source-contract
    prose, and statistics must not consume permanent rail space. Pointer orbit/pan/zoom remains available on
    the stage, while statistics stay inside a closed Details disclosure.

The prior Adult Dragon comparisons remain useful backup evidence, but `mapping-digits-balanced`,
`mapping-digits-wide`, and `mapping-wing-fingers-forward` are not the organizing model for this editor.
A control must visibly alter geometry or coordinates; highlighting unchanged points is not an acceptable
substitute for a discernible form change.

The catalog workshop under `workshops/core-morph-catalog/` contains the reviewed interchange objects and one
dropdown-driven 3D surface for every form. The deterministic import adapter emits the production
`talisman.core-morph/v1` documents. Ancient Dragon revision 2 contains 74 Points, twelve complete named
positions, nine reversible motion pairs, and six membrane attachments. The other eleven forms are likewise
complete and selectable; none reads coordinates, geometry, or motion data from another Morph.

## 3. Canonical terms

| Term | Exact meaning |
| --- | --- |
| **Core Morph** | One fixed, source-controlled, complete Morph object offered as a reusable starting form. |
| **Core Morph revision** | One immutable complete source document for a Core Morph identity. |
| **User Morph** | A complete independent object created by copying one Core Morph or another complete user object. |
| **Copy provenance** | Informational metadata stating what complete object/revision was copied. It creates no link and no inheritance. |
| **Point** | A stable semantic skeletal/control identity. Coordinates belong to poses or motion endpoints. |
| **Joint** | A stable directed parent-to-child relation between two Points inside the same Morph object. |
| **Geometry/profile attachment** | Simple non-image geometry data owned by the Morph, such as radius, taper, loft, membrane, or profile dimensions. |
| **Mapping** | The complete authored inspection arrangement. Applicable digits and surfaces are separated for mapping and review. |
| **Resting** | The complete neutral supported arrangement. Where a standing distinction matters, Resting is the standing form. |
| **Motion pair** | One reversible motion owned by a Morph, with endpoint A and endpoint B. The same pair plays forward or backward. |
| **Workshop comparison** | A temporary complete review state used to compare possible forms. It is not a stored child object or inherited delta. |
| **Materialized Morph** | One exact complete source revision stored in the database for runtime reads. |
| **Body Form** | An existing complete saved object snapshot. It is not a live child of a Core Morph. |

## 4. Complete-object invariant

Every persisted or synchronized Morph object is self-contained. A reader needs only that object's bytes to
obtain its complete definition. The following are forbidden:

- `parent_morph_id` with behavioral meaning;
- base-plus-overrides object resolution;
- variant stacking;
- prototype or class inheritance between Morphs;
- automatic rebasing;
- propagation from a Core Morph into a copied user Morph;
- a database-only partial Morph; and
- a read route that needs another Morph object to complete the selected object.

Internal code may share ordinary implementation helpers while constructing source fixtures, but the emitted
Core Morph documents and copied user objects must be flat and complete. Code reuse is not object inheritance.

Copy provenance may contain values such as:

```json
{
  "copied_from": {
    "object_id": "adult-dragon",
    "source_revision": 1,
    "source_sha256": "<digest>"
  }
}
```

That block is audit information only. Deleting or revising the source object does not change the copy.

## 5. Morph object contents

A complete Morph owns:

1. stable Point identities and labels;
2. the complete Joint graph;
3. optional chains and semantic groups;
4. simple geometry/profile attachments;
5. exactly complete `mapping` and `resting` coordinate maps;
6. a sorted collection of reversible motion pairs;
7. derivation options calculated from its own data; and
8. optional non-behavioral provenance.

The graph has exactly one root, is connected and acyclic, and gives each non-root Point exactly one incoming
Joint. Both default poses contain every Point exactly once. Coordinates are finite canonical feet in the
existing X-right, Y-up, Z-forward convention.

No Morph object contains a raster, SVG guide, texture, generated mesh file, camera, provider payload, or
browser recovery state.

## 6. Exactly Mapping and Resting

A Morph has exactly two default poses:

- `resting`; and
- `mapping`.

`standing` is a legacy synonym only during source extraction and becomes `resting`. `step-1`, `step-2`,
`wings-spread`, `heroic`, `coiled`, and similar arrangements are not extra default poses. They belong inside
a motion pair when deliberately authored.

Resting is the neutral supported form. Mapping is the inspection form. Digits that need independent mapping
regions should be visibly separated in Mapping, while Resting may use a physically plausible forward fan.

## 7. Motion-pair contract

Every known motion belongs to the Morph object that knows it. A motion has one identity and exactly two
ordered endpoints. The same pair runs in either direction; no duplicate reverse-motion object is created.

An endpoint may reference `resting` or `mapping` as an internal base and provide local point changes as a
compact encoding. That is composition inside one complete Morph object, not inheritance between Morphs. The
validator must be able to resolve each endpoint to a complete coordinate map using only the same Morph
object.

The generic workshop controller provides:

- motion-pair selection;
- endpoint A and endpoint B buttons;
- forward or reverse playback;
- pause;
- optional loop/ping-pong; and
- a scrubber.

The first Adult Dragon collection contains:

- `resting-mapping`: Resting ⇄ Mapping; and
- `shuffle-walk`: Step 1 ⇄ Step 2, alternating left-fore/right-rear and right-fore/left-rear.

## 8. Prior comparison contract

The current Adult Dragon snapshot may generate several clearly named comparisons rapidly. These are review
instruments, not persistent object inheritance. Each comparison presented to the workshop contains a complete
Mapping state for the same reviewed skeleton. Selecting Baseline restores the exact source Mapping state.

The first Adult Dragon Mapping comparisons are:

- `mapping-digits-balanced`;
- `mapping-digits-wide`; and
- `mapping-wing-fingers-forward`.

When a comparison is accepted, one of two things happens:

1. it replaces data in a new complete immutable Core Morph revision; or
2. it becomes a new complete independent Morph object with its own identity.

It is never saved as a child delta against Adult Dragon.

## 9. Audited complete source catalog

The verified 2026-09-05 patch supplies twelve reviewed complete objects: `ancient-dragon`, `adult-dragon`,
`dragon`, `winged`, `lizard`, `horse`, `quadruped`, `humanoid`, `avian`, `serpentine`, `arachnid`, and
`tentacled`. Its SHA-256 is
`f3eef6573313313e7bff923d25057f84453792dc32bb495d518167350d94d5c7`.

The deterministic adapter verifies every workshop-source digest and emits twelve flat complete production
documents. Shared import code is not object inheritance. Each emitted document owns its full Point graph,
positions, profiles, geometry attachments, and motions.

The live Factory object tables contain zero rows. Therefore the initial inventory is not discovered from the
database, and no SQLite upload is a prerequisite.

## 10. Retained Adult Dragon workshop baseline

`adultDragonBodyForm()` is the current detailed source baseline. It contains:

- 66 Points;
- one root and 65 parent Joints;
- four declared limb chains;
- fifteen profiles;
- ten front digits;
- ten rear digits; and
- eight wing digits.

The reviewed Resting form:

- points front and rear digits forward;
- folds both wings against the body;
- preserves the reviewed head coordinates; and
- preserves the reviewed tail coordinates.

Mapping opens the form for inspection and includes applicable digits. The three named comparisons alter only
the complete Mapping state shown for review. The shuffle motion keeps the wings folded and preserves the
head/tail shape while alternating its diagonal steps.

The retained standalone snapshot provides perspective orbiting, zooming, panning, fit/reset presets, baseline
overlay, collapsible details, and generic motion-pair controls. Camera and playback state are presentation
state only. It remains preserved as Adult Dragon evidence beside the implemented Ancient Dragon editor.

## 11. Implemented initial source catalog contract

Source authority now belongs under:

```text
src/main/resources/app/factory/morphs/
  catalog.json
  ancient-dragon/revisions/0001.core-morph.json
  ancient-dragon/revisions/0002.core-morph.json
  ancient-dragon/revisions/0003.core-morph.json
  <eight revised morph identities>/revisions/0002.core-morph.json
  <three unchanged morph identities>/revisions/0001.core-morph.json
```

All twelve Core Morphs are fully extracted source-authoritative documents. Catalog revision 3 points to
Ancient Dragon revision 3; Adult Dragon, Dragon, Winged, Lizard, Horse, Quadruped, Humanoid, and Avian
revision 2; and Serpentine, Arachnid, and Tentacled revision 1. Earlier Ancient Dragon revisions 1 and 2
remain immutable source history. There is no variant schema or variant directory. Motions are part of each
complete Core Morph document. A changed fixed form creates a new complete numbered revision and moves the
catalog pointer; prior complete revisions remain checked in.

The revision-3 workshop import places Mapping digits in the inspection plane for every changed digit-bearing
Morph. Humanoid additionally declares exhaustive segment-length, point-radius, three-axis profile, and walk
tuning rules and supplies capsule geometry for every parent-to-child segment. These declarations are part of
the complete source document and survive source-to-database materialization.

The catalog contains stable source order, identity, display/family metadata, current complete revision,
resource path, canonical SHA-256, and ordered human-readable stance and motion summaries. It contains no
behavioral parent reference.

## 12. Implemented database materialization

`CoreMorphCatalogService` materializes complete Core Morph source revisions through an identity table and a
complete-version table plus existing `content_object` bytes:

| Table | Purpose |
| --- | --- |
| `factory_core_morph` | Ordered current complete source revision and descriptive catalog metadata for one
  fixed Core Morph. |
| `factory_core_morph_version` | One immutable complete Core Morph document and digest. |

There are no variant tables and no inheritance-resolution tables. Motion pairs remain inside the complete
Morph document.

Synchronization must:

1. load and validate every complete source document before writing;
2. verify canonical SHA-256 values;
3. insert missing immutable complete versions;
4. update current pointers to match the source catalog;
5. verify the relational projection; and
6. commit atomically.

An unchanged rerun is a true no-op. Reusing one identity/revision with different bytes aborts the transaction.
The database never creates or repairs authoring content independently.

## 13. Implemented read path and deferred copy path

The authenticated read-only Application Server contract is:

```text
GET /api/object-factory/morphs/catalog
GET /api/object-factory/morphs/current?morph_id=<catalog Morph identity>
```

The catalog returns stable ordered stance and motion lists. The selected read returns the complete
materialized
object: identity and revision, Points, chains, profiles, exactly two default poses, every named static stance,
and every named motion. Each motion includes its identity/revision, duration, interpolation, loop policy,
ordered stance references, and complete ordered keyframes so Play, Pause, and Loop are deterministic. It does
not accept `variant_id` and does not resolve another Morph.

The later user-copy workflow remains:

```text
read fixed complete Core Morph
  -> copy all Morph-owned data under a new object identity
  -> persist one complete independent user object
  -> open only that copy in the editor
```

The copy operation must be atomic. It may store copy provenance, but the resulting object is immediately
self-contained. Editing the copy changes only the copy. Reading the copy never reads the Core Morph again.

The read path is direct and immutable. Atomic copy-then-modify remains a separate deferred mutation body.

## 14. DTDT and Shelf construction contract

The Shelf interface philosophy is the product-level interaction law for the owner workbench. DTDT is the
underlying declarative composition layer. The workshop must therefore use the same named Shelf vocabulary,
policies, identities, intents, accessibility, and restoration rules as the rest of Talisman rather than a
local visual approximation made from unrelated disclosure widgets.

`workshops/core-morph-catalog/morph-puppeteer-workbench.dtdt.json` is the controlling declarative definition.
It validates as `talisman.shelf-contract/v2`, has the exact owner identity
`object-factory.morph-puppeteer-workbench`, and declares one manually ordered vertical editor group with:

- `reorder_policy: POINTER_AND_KEYBOARD`;
- `default_sort_mode: MANUAL`;
- `disclosure_policy: TWIST_LABEL`;
- `occupancy: MULTI_OPEN`;
- `orientation: VERTICAL`; and
- `overflow_policy: SCROLL`.

The declared Shelf Items begin in this stable order: Stances, Puppeteer, Motion pair, Form controls,
Point parameters, Body-plan motion tuning, and Complete textual Morph. Redundant Selected complete object
and Display key Boxes are not part of the editor Shelf. Each Item owns one stable Box region. The persistent
label is the only open/close control; there is no
duplicate Close control. Multiple Boxes may remain open, closing a Box restores focus to its label, opening a
Box moves focus into that Box, and Up/Down/Home/End move among available labels. Escape from an open Box
closes only that Box. Pointer drag and the equivalent keyboard route may change the current presentation
order without changing Item or Box identity.

The one-page HTML is a bounded front-side projection of that owner DTDT. It exposes the exact definition,
owner, fingerprint, group, Shelf Item, Box, child-group, and intent identities in stable `data-of-*`
attributes. Open, close, focus, clear-focus, reorder, and divider-resize use the canonical Shelf intent type
strings on `APP.SHELF.UI_BUS.PRIMARY` with `PAGE_LOCAL_ONLY` scope. They are reduced locally and must not issue
an HTTP request, mutate a Morph, or acquire application-service authority.

The declared default has only Complete textual Morph open. Browser-local presentation memory may restore the
person's complete valid local setup: Item order, open Box identities, selected Morph identity, and independent
bounded wide-width and stacked-height divider extents. The payload is admitted only when its contract, owner,
definition identity,
fingerprint, complete key set, current Shelf IDs, selected Morph, and ranges all validate. Invalid, stale,
foreign, or partial memory fails closed as one whole payload to the declared default; no partial merge is
allowed. Presentation memory never contains points, geometry, poses, motions, draft edits, database rows,
jobs, or server state.

The single stage/editor splitter is presentation geometry owned by the Shelf Workspace. It is a vertical
width divider in wide layout and remains visible as a horizontal height divider in stacked layout. Each
orientation retains its own bounded allocation. Pointer and orientation-appropriate Arrow controls,
Shift+Arrow, Home, End, Enter, `0`, and double-click reset publish the same divider intent. Resizing does not
alter camera, Morph, stance, motion, or editor values.

The combined Point-parameter editor is a feature-specific tree inside one Box, not a nested product Shelf. It
is derived from the selected complete Morph's actual acyclic Point-parent graph. Every child Point owns one
exact incoming-link length leaf; every Point owns its radius leaf and any available profile-axis leaves before
its child group. Filtering retains and opens matching ancestor paths rather than flattening the graph. These
controls remain Morph editor controls and never acquire Shelf disclosure or ordering authority merely because
they appear inside a Box.

The stage camera toolbar, direct orbit/pan/zoom, stance controls, motion playback, and Morph parameter edits
retain their existing feature ownership. The production conversion may replace this rapid HTML and its local
projection with hardened JavaScript and later Java, but it must consume the same DTDT identity, Shelf
policies, item/Box identities, local intent semantics, focus behavior, restoration contract, and complete
Morph domain data. This workshop is design evidence, not code to transliterate line for line.

### 14.1 Production JavaScript Morph Editor integration

The approved owner workbench now has a production JavaScript projection. `critter-editor.js` remains the
Creature page entry and `six-view-texture-mapping.js` remains the Factory shell. The shell keeps the primary
Shelf fingerprint `object-factory.creature-shelves/v18` and the primary order is Morph, Morph Editor, Shader,
Object. Morph remains the catalog/selection surface. Morph Editor follows that current selection through the
authenticated Core Morph read client and contains the complete workbench Shelf, including Puppeteer; Puppeteer
is not a primary Factory Shelf peer. Views and the global display controls remain independent.

The production editor consumes the current catalog and selected complete revision dynamically. Its adapter
must preserve the approved workbench v4, point-selection v4, influence-field v2, and web-geometry v1 data,
including Point hierarchy, Webs, geometry attachments, source revision identity, stances, motions, and editor
metadata.
Browser presentation memory may continue to remember only presentation state. Morph edits are session drafts in
memory and never rewrite immutable Core Morph source revisions. When the asynchronous current catalog arrives,
the shell atomically replaces a legacy fallback projection with the selected current package, including its
explicit Webs and source identity. It then reapplies only a source-compatible genuine local draft or an edit
made while that read was pending; an automatically stored untouched fallback is not a user draft.

There is currently no authoritative mutation route for **Core Morph Save revision** or **Core Morph Save as**.
Production therefore presents no Core Morph Save or Save-as control and must not claim durable success, use
`localStorage` as a substitute for authoritative persistence, or redirect those operations to Body Form
routes. Those Body Form routes persist BODY_FORM objects and are not Core Morph revision authority. Durable
Save and Save as remain a named server-contract gate.

## 15. Focused patch and backup workflow

A Morph workshop patch contains only the affected source Morph/helper, standalone workshop, focused tests,
and directly related design documents. It contains no database rows, SQLite file, generated image, provider
payload, or unrelated application work.

Starting with this design, periodic backup handoffs are cumulative `.patch.zip` bundles cut against the
supplied source archive. A separate worker may archive or apply them. The backup destination is outside this
chat's responsibility.

## 16. Focused verification

Current catalog verification must prove:

1. the imported catalog contains exactly the twelve declared identities in stable source order;
2. each object is complete, independent, geometry-only, and has one connected acyclic Point graph;
3. every default pose, named stance, and motion keyframe contains every declared Point exactly once;
4. Mapping and Resting are the only two default poses;
5. every motion has exactly two complete endpoints and one record serves both directions;
6. the three dragon-family objects share the reviewed normalized form while each owns its own complete bytes;
7. only the three declared uniform dragon-family sizes differ in that normalized comparison;
8. the workbench selects and renders all twelve forms without page or console errors;
9. stance, motion, local editing, textual-object, orbit, pan, and zoom controls remain presentation-only;
10. Mapping digits for every revised digit-bearing Morph lie in the intended inspection plane;
11. Humanoid exposes every segment length, point radius, profile axis, and declared walk-tuning control, and
    every parent-child segment has capsule geometry;
12. every editor section is a stable, independent twist-open Shelf Item/Box pair and the skeleton controls
    preserve the Point-parent hierarchy, including filtered ancestor paths;
13. source digests and the exact incoming patch SHA-256 are reproducible;
14. generated production packages pass the same browser normalizer consumed by Object Factory;
15. no persisted or synchronized Morph contract requires a parent Morph, inherited delta, variant stack, or
    rebase;
16. all twelve current Morphs materialize atomically with digest, catalog-order, and revision guards; an
    unchanged repeat is a true no-op and upgrading an existing revision-1 database retains immutable Ancient
    Dragon revision 1 bytes;
17. authenticated catalog and selected reads match the checked response fixtures, including every ordered
    stance, motion, duration, loop policy, stance reference, and keyframe; and
18. the read route introduces no mutation, provider, bus, subscription, cancellation, or live-server action.

Atomic copy-then-modify retains its own focused proof obligations and is not implied by this read body.

## 17. DTDT + Shelf Puppeteer workshop composition

The Morph owner workbench is now explicitly governed by two layers: DTDT supplies stable declarative
identity, state/intent seams, and testable composition; the canonical Shelf interface philosophy governs the
visible editor organization and interaction model. The editor is one reorderable vertical Shelf group using
`talisman.shelf-contract/v2`, `MANUAL` sort, and `POINTER_AND_KEYBOARD` reorder. Reordering, disclosure, and
the stage/editor split are presentation state only.

The former separate skeletal-length and geometry editors are one `Point parameters` Shelf. The hierarchy is
owned by the Morph's acyclic Point graph. Every Point branch contains its incoming parent-to-Point length
control when applicable, its local radius/profile geometry controls, and its direct child Point branches.
Single Point selection from the 3D stage opens and focuses that one combined branch; the selected Point owns
its local Point geometry and its outgoing geometry through the next immediate control Point(s), never the
incoming parent edge.

The `Puppeteer` Shelf is deliberately a two-dimensional artificial control space, not a model X/Y/Z view and
not a return to the old 2D image workflow. Each stance remains a complete 3D stance endpoint. A checkbox in
the Stances Shelf decides whether that stance appears as a labeled draggable Puppeteer point. One stance is
always selected for direct stance editing independently of Puppeteer inclusion.

While the Puppeteer Box is open, a small red circle-plus target computes the live complete 3D pose from all
checked stance points. Initial influence is normalized inverse-square distance. An exact target hit on a
stance resolves exactly to that stance. Stance-point positions and target motion are workshop presentation
state and are intentionally not recorded in this body. Recording, path capture, and additive fields such as
broad wave/undulation influences are deferred until the owner specifies that contract.
## Puppeteer Web geometry and local joint manipulation

Web geometry is a first-class, complete-Morph multi-anchor object. A Web has one stable `web_id`; every anchoring Point may reveal it under `Point Parameters -> Point -> Geometry -> Web geometry`, but every reference edits the same shared Web object. Anchors identify stable Morph Points plus a point-local site and offset. Generated mesh vertices and world coordinates are never Web authority. A surface requires at least three unique anchors. Its initial textual parameters are ordered anchors, tension, signed sag/bulge, thickness, and one edge-tension value per boundary edge. Webs follow their anchor Points through complete stances and motion endpoints. Selecting any anchor highlights and reveals the shared Web.

The selected-Point transform gizmo is parent-relative only. It never rotates the whole Morph. Its six model-axis translation handles request +/- X, +/- Y, and +/- Z movement, then constrain the selected Point back to its fixed parent-distance sphere while carrying descendants rigidly. Its two large rotation rings rotate outgoing child subtrees about the selected Point: one about model Y and one about the incoming parent-to-selected normal. Existing skeletal segment lengths are locked. Gizmo hit targets are deliberately larger than visible strokes and expose explicit hover feedback. Geometry opacity applies equally to selected and unselected solid/Web geometry.

Puppeteer stance instances are control-space objects. A right-click Duplicate action may create another independently draggable stance instance referencing the same complete stance coordinates. Duplicates have independent influence positions but do not create inherited Morph data. Double-click selects the referenced stance and places the red target exactly on the clicked instance. The Mapping stage shortcut is required to invoke the same stance-selection path as the Stances Shelf.


### Puppeteer locked-chain manipulation

In the owner Puppeteer, multi-selected control Points form a lock set rather than frozen world Points. The primary selected Point may itself be moved or rotated. Other selected Points are fixed constraints for that manipulation, and the solver preserves all skeletal parent-child lengths while bending only the unlocked Points on the minimal path to the next selected lock. Selection therefore supports interactions such as locked hip + locked foot with an unlocked knee bending to satisfy a moved foot. Existing bend direction is preserved; an exactly straight ambiguous chain uses a deterministic initial side until the user establishes the bend.

### Puppeteer point navigation, mirrored rotation, and digit spacing

The 3D control-point DAG is also the keyboard navigation graph. With a selected Point and stage focus, Up replaces selection with the parent, Down replaces selection with every direct child, and Left/Right moves to the previous/next sibling in source order with wraparound. These arrow keys navigate selection only; they never manipulate the transform gizmo. Shift+Arrow remains camera pan. A plain second click on the primary selected Point toggles only transform-gizmo visibility and preserves selection. Clicking another already-selected Point makes it primary without dropping the existing lock set; clicking an unselected Point replaces the selection.

The Mirror L/R checkbox applies to gizmo rotations as well as the existing mirrored form controls. A rotation on a left/right Point produces the reflected counterpart rotation with opposite signed angle when mirroring is enabled; when disabled, the counterpart remains untouched. Rotation-ring hit paths remain close to the visible rings and provide stronger hover feedback without enlarging the local transform space itself.

When the active selection consists only of every digit child for one or more parent Points, right-click exposes a Digit spacing popup. Increase/Decrease alters angular separation on a best-fit average plane for each parent group. By default parent-to-digit skeletal lengths remain fixed. An explicit `Adjust skeletal geometry` checkbox permits those selected parent-to-digit skeletal lengths to change; it never stretches point-attached solid or Web geometry as separate authority. All other skeletal edges and parent Points remain fixed, and attached geometry follows the resulting Point positions.
