# Unified Morph Workbench Handoff

## Scoped successor — 2026-09-13

For the newly assigned `__Slice` Gallery consolidation, [METAMORPH-GALLERY.md](METAMORPH-GALLERY.md)
takes precedence over this historical handoff's separate `/morphs/` destination, older baseline and editor
composition instructions. The next body uses current Slice JavaScript, all admitted Talisman objects,
Browse-first cards and in-place Edit in one retained Gallery. This does not authorize porting Java UI,
replaying the old patch, bulk exchange or model migration. Existing domain-service invariants remain.

Date: 2026-09-06
Owner: Object Sing & Dance Factory
Implementation companion: `__Object Sing & Dance Factory`
Baseline: `4c67e122ef21838593398a3c81d912d775747685`
Status: approved implementation assignment

## Outcome

Extend the existing Core Morph catalog and editor so Morphs can describe creatures, props,
architecture, or any other physical scene object. This is deliberately a small conceptual extension:
there is one Morph model, one validator, one revision system, one catalog service, one editor, and one
database authority.

Add a database/catalog section discriminator with the initial values `CREATURE` and `OBJECT`, plus an
open-ended type value for Object Morphs (`TABLE`, `CHAIR`, `WALL`, and so on). Existing Morphs default or
migrate to `CREATURE`. New physical-object Morphs use `OBJECT`. Do not create a second object catalog,
object editor, object persistence system, or incompatible schema.

Deliver the feature as a complete returned patch without asking the user routine design questions. Make
reasonable decisions from existing conventions. Report only a genuine missing authority or a contradiction
that cannot be resolved from current source.

## Product surface authority

The Talisman Application served by the Talisman Application Server is the primary product surface and the
UI source of truth for this assignment. Build and prove the Shelf-based JavaScript Morph Gallery and editor
there against live typed services and shared database authorities. Preserve existing desktop Java editors as
useful references and temporary capability hosts, but do not redesign or extend their screens as the target
of this body.

Keep semantic and service ownership presentation-neutral. After the browser application and contracts are
mature, Make Java will mechanically reproduce the proven Talisman Application in desktop Java, piece by
piece; that later translation is not a second independent design exercise and is outside this body.

## User workflow

### Morphs destination

Add **Morphs** to the hosted Object Factory navigation. It opens a first-class `/morphs/` page on the
existing Application Server. The page is a Morph gallery and bulk database workbench.

The Morph gallery is type agnostic. Creature and Object are catalog sections and filters inside one
presentation, not separate gallery applications. The same gallery card, preview, comparison, search,
selection, and editor-entry behavior applies to any Morph; labels may use Creature or Object when the
selected section makes that language clearer.

The new page uses a two-sided object browser:

- the left rail contains search and twist-open object types such as Tables, Chairs, Walls, Doors,
  Windows, Bar Sections, Stages, Barrels, Crates, and Wagons;
- selecting a type shows that type's current Morphs on the right;
- selecting one Morph shows one Morph; selecting several shows those Morphs together in a comparison
  layout with independent useful 3D views;
- All Objects remains available, and bulk selection may cross types;
- shows name, section, family, current revision, control count, stance count, and motion count;
- opens one Morph detail screen without assuming it is a monster;
- remains usable on iPad: fixed compact header/filter rail and one independently scrolling results pane;
- uses stable fingerprinted rendering so catalog polling does not erase and recreate unchanged cards.

Clicking a gallery image expands that image within its Morph card. Every image and the complete Morph card
also has the established green target control: activating it takes that exact surface over the available
workspace, and activating it again returns to the retained gallery layout. **Edit Creature** replaces the
ambiguous **Open Creature** action for Creature cards and opens the existing editor; equivalent Object
cards use **Edit Object**. Preview/takeover is presentation-only and never enters edit mode implicitly.

The compact header and left category/search rail remain fixed. Only the results/comparison region scrolls.
Selecting several Morphs shows each chosen Morph in an independent 3D comparison view without conflating
their camera, stance, motion, or texture state.

### Observe and Edit

One Morph detail screen has two explicit modes:

- **Observe** is the default. Show the useful 3D object, available views/images or color projections,
  stances, motions, description, section/family, revision, and other metadata. Hide authoring controls.
- **Edit** mounts the existing Morph Editor/Shader/Object workbench for the selected Morph. Reuse the
  current host-scoped editor and six-view implementation; remove creature-only wording where it is not
  semantically required.

Switching modes changes presentation only. It must not change selection, geometry, pose, revision, or
dirty state. Closing/disposal must stop timers, animation, pending reads, and editor leases.

### Bulk export

The user can select any set of Morphs and choose **Copy selected Morphs** or **Download package**. Produce
one deterministic UTF-8 JSON package suitable for pasting directly into an AI chat and parsing again.
The package contains:

- a schema/version and package purpose;
- export time only when it is excluded from the deterministic content fingerprint;
- source catalog revision and package fingerprint;
- each complete current Morph document in stable catalog order;
- each Morph's ID, section, object type when applicable, current revision, content/source digest, and
  full editable payload;
- no local paths, credentials, database IDs, browser draft state, or derived STL/OBJ bytes.

Copy acknowledges immediately, reports count and package size, and fails clearly if the clipboard is not
available. Download is the compatible fallback. Export is a direct read query and never mutates state.

### Bulk import

The user pastes or uploads one returned package and chooses **Review import**. Review performs no mutation.
It parses the entire package, validates every complete Morph through the existing canonical validation,
and presents each candidate as:

- add a new Morph;
- replace the current Morph by creating a new immutable revision;
- unchanged;
- conflict or invalid, with a precise diagnostic.

The user may change each admissible candidate between Add and New revision when identity rules permit.
One **Import reviewed Morphs** action commits the whole admitted set in one database transaction. If any
candidate is stale, invalid, conflicting, or fails to persist, commit none of them. Never overwrite old
revision bytes and never leave a partial import.

For a new revision, the package identifies the expected current Morph revision/digest; the server assigns
the next revision and advances the current pointer only after all bytes are stored and re-read successfully.
For a new Morph, the requested slug must be unique and begins at revision 1. Replay of the same accepted
operation is idempotent and returns the same result. A changed payload under the same operation identity
fails closed.

Successful import returns one immutable result containing old and new catalog revisions, exact accepted
Morph IDs/revisions/digests, unchanged items, and the new complete catalog fingerprint. The page refreshes
from that result and then confirms with one authoritative catalog read.

## Existing architecture to reuse

Use and extend these existing owners rather than replacing them:

- `CoreMorphCatalogService`: one Morph catalog, immutable revisions, current pointers, validation, and
  transaction authority;
- `CoreMorphApplicationServerGateway` and `FactoryCoreMorphApplicationServerGateway`: typed path-free
  browser transport;
- `critter-editor.js`, `six-view-texture-mapping.js`, and `morph-puppeteer-editor.js`: existing Morph
  projection and editing surfaces;
- the Application Server owner manifest/bootstrap/capability route;
- the existing content-addressed store and database writer lease.

The current checked-in Morph resources remain system seed/history. Extend materialization so a later seed
sync cannot silently replace an imported current revision. An imported revision remains immutable and
current until an explicit reviewed import advances it. Preserve every existing historical revision.

The browser must not write SQLite, allocate authoritative revisions, or treat localStorage as persistence.
Local state may hold only selection, draft text, and presentation preferences.

## Typed operation boundary

Use a small UI-independent service boundary, named to match current code conventions. The implementation
must provide equivalent typed contracts for:

1. current catalog query with section filtering;
2. complete multi-Morph export query;
3. import review request/result with expected catalog and Morph revisions;
4. checked atomic import command with review receipt, idempotency identity, and exact terminal result.

The Application Server adapter owns authentication, bounded request size, JSON transport, safe error
projection, and database-writer-lease admission. The Core Morph service owns validation, stale checks,
revision allocation, transaction commit/rollback, and final readback. The page owns only immediate busy
feedback and current-result projection.

Use direct queries for catalog/export/review when they are immediate and read-only. The commit may use the
existing semantic-operation pattern if it is asynchronous; do not add a bus round trip merely for ceremony.
No EDT or JavaFX bridge is required. Application Server worker threads must never synchronously call a UI.

## Object section starter Morphs

Populate useful first entries as complete ordinary Morphs. Prefer a small deterministic generator/build
script over hand-maintaining repeated JSON, but commit the validated current resource documents used by the
catalog. Use simple geometry and meaningful control names. Add only motions that change the object.

Initial set:

- wooden wall section;
- stone wall section;
- brick wall section;
- wooden wall with window;
- stone wall with window;
- wooden door in wooden wall, with Closed and Open stances plus Open and Close motions;
- wooden door in stone wall, with the same motion contract;
- rectangular wooden table;
- round wooden table;
- wooden stool;
- wooden chair;
- barrel;
- crate / modular wooden box;
- straight modular bar section;
- corner modular bar section;
- wooden stage module;
- small wagon.

The left-rail taxonomy is derived from Object Morph type metadata, not a hard-coded closed list. The first
resources establish the visible plural groups Walls, Windows, Doors, Tables, Stools, Chairs, Barrels,
Crates, Bar Sections, Stages, and Wagons. Adding a later Object Morph with a new admitted type makes that
type available without a schema or editor rewrite.

Treat door plus wall as one Morph where requested; the door controls are children of the wall/root graph.
Static objects may have one Resting stance and no motion. Moving objects use the same stance/motion schema
as creatures. Use Morph metadata for collision, line-of-sight blocking, exterior-shell behavior, and future
interior load-zone hints when those fields already fit safely; do not invent a second runtime-object model.

## UI and accessibility

- Add Morphs to relevant hosted headers without restoring redundant navigation on the detail screen.
- Use existing Shelf visual language, target controls, compact header, and immediate busy feedback.
- Every asynchronous action changes its own label immediately (`Reviewing…`, `Importing…`, `Copying…`),
  disables duplicate activation, and exposes `aria-busy` plus a live status.
- Keep bounded text fields bounded. The package textarea may expand and scroll.
- Provide keyboard multi-selection, select all/none, and explicit visible selection counts.
- Do not auto-import on paste. Review and commit are separate explicit actions.
- Preserve iPad touch sizes and do not let a 3D gesture scroll its containing rail.

## Required focused proof

Add the smallest tests that prove:

1. existing twelve Morphs migrate/read as `CREATURE` without byte or revision loss;
2. starter object Morphs materialize as `OBJECT` and each complete document passes the canonical validator;
3. export order and fingerprint are deterministic and include complete selected documents only;
4. review rejects malformed, duplicate, cross-package, stale, or incomplete Morphs without mutation;
5. one mixed Add/New-revision import commits atomically and preserves all predecessor bytes;
6. an injected failure rolls the entire import back;
7. replay is idempotent and changed-payload replay fails;
8. the Application Server route is authenticated, bounded, path-free, and exposes stable safe errors;
9. the Object Morph Gallery searches, twist-opens dynamic types, selects one or many Morphs, compares the
   exact chosen set, copies/reviews/imports, and gives immediate busy feedback;
10. Observe/Edit mode preserves selection and state, and disposal cancels leases/timers;
11. unchanged Gallery polling preserves existing DOM/card identity and independent scroll ownership;
12. iPad-size layout and Morph 3D gesture ownership remain valid.

Run only directly affected focused checks. Do not run a broad suite or restart the managed Application
Server. If live proof is requested, use the existing owner hot-publication seam.

## Documentation and returned delivery

Update the current Architecture Atlas authority matrix, service route, persistence/revision rules,
focused-test routing, validation references, `AGENTS.index.md`, the page manifest/parity contract, and this
Active Design where source changes make them current.

Return one ZIP containing:

- a manifest naming baseline `4c67e122ef21838593398a3c81d912d775747685`;
- one ordered patch (or an explicitly ordered minimal patch series) that applies to that exact baseline;
- all new/changed source and resource files necessary to review independently;
- focused test commands and exact results;
- a concise note distinguishing implemented behavior, deferred ideas, and any real blocker.

Do not merge or push Main, restart services, mutate the user's live database, invoke paid providers, or
claim deployment. The permanent owner will inspect, apply, verify, publish, and coordinate landing.
