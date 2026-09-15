# Metamorph Gallery — browse first, edit in place

Date: 2026-09-13
Revision: 1
Status: active design; documentation patch only; implementation and runtime acceptance remain pending.
Worker: existing `__Slice` Patcher. Product/model authority: Object Sing & Dance Factory.
Host, admission and shared read transport: Tassy. Existing domain owners retain all persistence authority.

## 1. Decision and precedence

Metamorph becomes the shared Talisman object-browsing and editing workspace. Its implementation remains
native HTML/CSS/JavaScript in the current Slice application component. Slice the game remains a consumer
and an immediate playtest environment around that workspace; it is not the boundary of the catalog.
This is product-surface consolidation, not a replacement of Talisman's services, stores or identities.

Mark's final interaction clarification is normative:

> The Gallery twists open and selects things. Selection first displays a read-only card. An Edit button
> on that card replaces it with the editor. The Gallery stays available and can be closed and reopened.

There are **two independent choices**, never one ambiguous mode flag:

- **Catalog scope:** Everything by default; the checkbox **Slice only** narrows the same Gallery.
- **Detail mode:** Browse by default; explicit **Edit** replaces the selected read-only card in place.

The earlier Slice terrain work order is not the next assignment. Preserve its working game, terrain,
course editing and fine-hex movement; do not extend its unfinished features here. This design supersedes
conflicting Gallery, default-edit and separate-destination directions in the September 6
[Unified Morph Workbench handoff](UNIFIED-MORPH-WORKBENCH-HANDOFF.md). It does not commission that
handoff's bulk-import, bulk-export, comparison or Core migration work. Existing working actions stay intact.
The historical 2D prototype and September 12 terrain candidate are not current source for this body.

The requested order is **design patch -> Mark applies the design -> current-source implementation patch**.
This document settles the experience; implementation should not repeat a general planning/approval cycle.
Source intake and actual write-capability gaps still require truthful evidence, not invented compatibility.

## 2. Source evidence and the implementation intake boundary

This design was inspected against the supplied Main archive, not a running application:

```text
archive: talisman-main-main-2026-09-13-113825-e77137332d32.git.zip
Git archive comment: e77137332d32c6832adf8170aa0affe563b55025
archive SHA-256: 106c5fa56d3a10ca4e390cbd6216e091648f8c20e653652a5defb1e542e7fd92
archive bytes: 51280552
regular source files: 3202
```

The archive has repository-relative files at its root and no Git working-copy metadata. These identifiers
record inspected source; they are not an exact-HEAD application lock or a claim about live deployment.
Patch applicability is checked by Git against actual file context.

### Current, directly inspected seams

Repository-relative paths below are source evidence, not proposed replacements:

- `websites/moondance-web/talisman/index.html` and `assets/talisman-core.mjs` in that same directory:
  the four application badges are Gallery, Easy Tale, Talisman Control Screen and Talisman Database
  Viewer. The last badge currently opens `/database-viewer/`; Gallery opens `/critter-gallery/`.
- `websites/moondance-web/talisman/assets/talisman.mjs`: one existing tool frame, same-window history
  and return to `/talisman/`. It currently resolves `?tool=database-viewer` to the old page.
- `src/main/resources/app/database-viewer/`: the disclosure browser, selected inspector, source family
  and search controls, read client, safe-preview module and view-only Core Morph renderer.
- `src/main/resources/app/database-viewer/database-viewer.js`: a component selection calls
  `renderDetail`; only current `core-morphs` mount the dedicated Morph renderer. The generic preview
  module exists, but this page does not wire it into all selected cards. All-kind media rendering is
  therefore a required integration change, not a claim of already-proven visual parity.
- `src/main/java/com/moondance/talisman/app/database/componentviewer/`: the reviewed 40 source
  projections, of which 30 are listed catalogs and 10 nested readers, and 74 explicit relation routes.
- `src/main/java/com/moondance/talisman/app/services/app/applicationserver/`
  `DatabaseViewerApplicationServerGateway.java`: bounded, path-free read projection.
- `src/main/resources/app/application-server/page-manifest.yaml`: existing viewer page, five named
  database-read capabilities and current-Core-Morph read capabilities.
- `design/Active Designs/Object Sing & Dance Factory__/SLICE-NATIVE-STORAGE.md`: exact complete
  Slice document storage, not automatic rendering compatibility or an already-enabled HTTP editor.
- `src/test/resources/app/application-server/managed-handler/slice/`: a **synthetic handler fixture**.
  Its descriptor identifies `slice.metamorph-editor` and the `/slice/` prefix. It is not Metamorph source.
- Checked-in Moondance deployment receipts record `/slice/studio.html?editor=humanoid`. They are
  historical route evidence only, not verification of the currently selected bundle or editor behavior.

**The current Slice Studio, its library/editor modules and its actual game/editor launch control are
not included in this Main archive.** No production `studio.html` was supplied. Do not implement against
that test handler or silently use either September archive as the missing current editor.

Before the code pass, the handoff must include the current populated Slice source and its own manifest,
including changes beyond Git HEAD when present. Reconcile that source's actual Gallery mount, complete-model
reader/writer, Make/attach controls, terrain integration, entry button and managed descriptor. Record exact
paths and versions in the implementation result. This is a source dependency of implementation, not a
blocker to applying this design-only patch. No external checkout or live database was inspected here.

## 3. One retained Gallery, one selected-object area

The default desktop composition is:

```text
Metamorph                         [Gallery open/close]  [ ] Slice only
+--------------------------------+------------------------------------+
| Gallery                        | Selected object                    |
| Search                         | Name, kind, revision               |
| > expandable logical groups    | Read-only picture / Morph          |
|   > object / relationships     | Images, token, icon, stances/motion |
|     selected exact object      |                             [Edit] |
|                                | Metadata and relationships below   |
+--------------------------------+------------------------------------+
```

After Edit, the right-hand area contains that exact object's existing editor instead of its card. There
is not a second editor page, a second selected-object store, a modal full editor or a card left alongside
a competing editable copy. The application may reuse a retained renderer internally, but its read-only
and authoring input modes must be mutually exclusive.

**MG-01 — Gallery behavior.** Keep one persistent Gallery Shelf/label and one hierarchy. Its disclosure
arrow opens/closes a branch; tapping the item name selects it. Neither action enters Edit. Preserve search,
expanded branches, page positions, scroll, selection and keyboard focus through detail-mode changes.
Keep a selected identity distinguishable from an expanded branch. Relations may link the same identity
from several parents, but must not manufacture duplicate records or merge same-named objects.

**MG-02 — Close and reopen.** Closing the Gallery hides only that panel, leaving a reachable Gallery
label/button. It does not discard the selected card, cancel an editor draft, change catalog scope or stop
visible selected-object playback. Reopening restores the same state. Closing the entire workspace,
navigating away and closing the Gallery are different operations.

**MG-03 — Detail before editing.** A fresh entry, ordinary selection, deep link or reopened saved
selection starts in Browse. Refreshing a card keeps Browse; refreshing while editing preserves its
session and draft guards. A restored presentation preference must never reopen a writable session
automatically. No row click, double-click, preview enlargement, stance, motion or takeover action
is an implicit Edit command. Existing editor-specific shortcuts may operate only after explicit Edit.

**MG-04 — Responsive and accessible.** At narrow widths the Gallery may be a closable drawer or stacked
panel, but the same state model and always-reachable toggle remain. Use the current supported Shelf
runtime, not a new Shelf engine or a forced DTDT migration. The Gallery and selected content have bounded,
independent scrolling. Takeover/restore preserves the current content mode and the pre-takeover layout.
Use labelled buttons, visible focus, keyboard disclosure/selection, meaningful headings and touch targets
of at least 44 CSS pixels. Apply Forest tokens, bold hierarchy, readable contrast and words/icons with
color. Keep footer/version and return controls reachable; no faint gray-on-gray substitute.

The [Shelf v2 contract](<../Talisman Shelf__/SHELF-CONTRACT-V2.md>) is current reference material; the
[DTDT v3 design](<../Talisman Shelf__/DTDT-V3-SHELF-DESIGN.md>) is not proof of runtime adoption. Retain the
actual Slice host's supported contract and document the adapter. Pure presentation traffic stays local.
No gallery toggle or splitter movement needs a server business-operation round trip.

## 4. Everything and Slice only

**MG-05 — Whole Talisman catalog.** On a fresh entry from either doorway, **Slice only is unchecked**.
Everything covers all reviewed, admitted object authorities in the host-selected Talisman database,
including non-Slice editable objects. It is not limited to the Slice model cache, Core Morph catalog or
whatever geometry has already loaded. The checkbox changes scope, not the application or its editor.

Retain the existing read coverage as a minimum, not an assertion that all future schemas are discovered:

- Definitions and Things: Collections, Types, rulesets, Created Things, Thing groups, features and
  character profiles; preserve exact source Type, membership and recorded media links.
- Morphs and Factory: reusable Morph Types, individual/private Morphs, Factory objects, Slice-native
  documents, immutable revisions, manifest components and exact pinned source/master links.
- Worlds and Places: projects, map documents, active/inactive Place identities, layers, sources,
  Worlds, Adventures/references, residents/groups, plus admitted native Slice worlds and courses.
- Stories: works, literary characters, events, timeline sets, manuscript nodes and blocks; use
  reviewed literary-to-game-character references, never name matching.
- Media and appearance: managed and project assets, generated media, visual fields, content,
  attachments, presentation roles and retained history.

These are logical groupings over source authorities. Revisions/content may remain nested rather than
become top-level clutter. Available/editable/read-only/unavailable/corrupt/unmapped are distinct states.
A source that is unavailable is not an empty source. Render unsupported identities honestly; do not hide
them to make the Everything count appear complete. Authorization still bounds every count and item.

The code pass must inventory the current Slice schema alongside these 40 reviewed projections. Every
source kind gets a coverage record naming its identity, read owner, membership rule, preview support,
editor support and save owner. Registered editable kinds must have functioning editors; a generic
"unsupported" default for all non-Slice objects does not meet this requirement. New unmapped schemas
are reported as gaps until an explicit adapter is reviewed; no unrestricted SQL or schema-dump UI.

**MG-06 — Membership, not a spelling heuristic.** A Slice-only result needs explicit evidence of Slice
ownership, imported source provenance, designated game membership or a reviewed Slice usage relation.
The provider inventory must name the exact admitted relation for each kind. A shared reusable Morph
explicitly used by Slice may be included; unrelated Types are not all included merely because Slice can
potentially instantiate them. Do not use display-name substrings, guessed prefixes, current selection,
loaded-cache membership or the launch doorway to determine membership.

Project membership as `INCLUDED`, `EXCLUDED` or `UNKNOWN`, with a bounded reason/evidence reference.
Only INCLUDED enters the Slice-only result count. Display unavailable/unknown membership coverage
separately. Retain necessary grouping ancestors. Related objects outside the scope may be followed as
explicitly labelled references; they do not silently expand the entire filtered catalog. Such navigation
keeps the checkbox and shows "Related object outside Slice-only results" on that card.

Apply scope and search before pagination and counts in the owning query/projection, not by filtering one
already-fetched page. Page/cursor cache keys include principal/workspace, source, scope, search and relevant
snapshot stamps. Restore each scope's tree state independently. A late Everything response must never
populate Slice-only results. If the selected object disappears under a new scope, display a clear empty
selection state; never show a different object's editor under the old heading.

Scope changes that would replace a dirty target use the same draft guard as selection. Merely closing a
branch or hiding the Gallery does not change the target. Existing session-local scope preference may be
retained while open; a fresh launch still defaults to Everything unless an explicit caller selection or
an admitted same-session return context says otherwise.

## 5. One Morph Types section; identities are not display names

**MG-07 — One semantic section.** There is exactly one **Morph Types** section in the logical Gallery.
It contains the reusable model types from the admitted authorities. Complete-model individual Morphs
appear as individuals, including through their exact owner relationships. Do not recreate parallel
"Core Types", "Slice Types" and "Factory Types" selectors as competing top-level Morph Types sections.
Source/kind badges and nested compatibility entries may distinguish representation without duplicating
that section. Ordinary game definitions remain Types; being a game Type does not itself make a record a
reusable Morph Type.

Reusable types and individuals use the same complete-model technology and checked preview/edit adapters
when their format is supported. Preserve authored Humanoid, Dragon, Dwarven and every individual model,
including unknown retained source fields, appearance, meshes, skeleton segments, Webs, units, motion,
stances, texture bindings and provenance. Never replace current database models with bundled fixtures.
Do not change canonical identifiers or merge records by label. Selecting an individual must not select
or edit its source type instead.

Older Core/private representations that cannot round-trip completely remain labelled inspect-only.
Their retained form can still have a supported read-only renderer. This body neither converts nor deletes
older models, retires Adult/Ancient Dragon records, nor changes source assets to mimic a fallback. Historical
retirement/conversion proposals remain separate owner work. Storage admission is not renderer parity.

An object reference is an opaque, server-admitted tuple of database/workspace scope, source authority and
exact key. Composite keys stay composite; do not concatenate ambiguous user strings. Its revision/digest
is a separate authority-specific stamp. A media digest identifies bytes, not every owning object. A
read-only historical revision is never accidentally edited as the current revision.

## 6. The read-only card is useful before Edit

**MG-08 — Card composition.** Lead with the selected name/kind/revision and its useful visual content.
Show the Morph where supported, and the explicitly associated images, icon and token with role labels
and thumbnail/selected-image controls. Put detailed metadata in the lower part of the card, followed by
expandable relationships/history. Keep compact status badges near the heading; do not replace the visual
content with a page of raw fields or counts. Every editable card has a clearly labelled **Edit** action.
For unsupported or unauthorized targets, keep an explanatory non-actionable editing status instead of
pretending to enable a full editor.

At minimum the lower metadata includes source/namespace, exact identity, revision/current-or-historical
state, description and domain facts, media roles, association states, provenance and exact related IDs
where admitted. Do not leak filesystem paths, credentials, hidden Player information or unbounded raw
storage blobs. Missing facts say not recorded; unavailable sources say unknown/unavailable.

**MG-09 — Media and fallback.** A character without a Morph still shows its actual icon, token and
pictures. Missing Morph is not an empty card. Preserve multiple images and explicit role/order metadata;
a valid image in one role must not be suppressed because another role is broken. Distinguish no recorded
association, missing target, missing bytes, unsupported format and integrity failure. A relation alone is
not proof of renderable media. Do not claim "No image" before checked associations have been resolved.

Resolve only reviewed direct/derived-default relationships through the owner. Show direct and inherited
or source-type media with their actual provenance; do not invent inheritance. No near-name joins and no
silent fallback to a different character or type. Previews use bounded, checked references and revision-
matched bytes. Prefer available checked visual content; show an honest placeholder only for the missing
or unsupported portion. Missing texture means untextured geometry; missing sound means silence, with no
substitute generated or fetched.

**MG-10 — Morph inspection without mutation.** Support orbit/zoom, selection of stored stances and
Play/Pause/Stop for stored motions in the read-only card where the format supports them. Those controls
change transient playback only, never skeleton data, database revision or an edit draft. Use the exact
selected complete model and all renderable authored geometry; do not drop Webs/appearance or start with
a legacy fallback while the real model is loading. Mark unsupported geometry/motion explicitly.

A card for a character may display that character's linked Morph. Its primary Edit still targets the
character, not the reusable source type or linked Morph. An explicit "View Morph" relationship opens the
Morph's own read-only card; Edit there targets that exact Morph. Do not silently change the main editor
target because a preview happens to show a linked model.

Stop/dispose the former preview's animation, object URLs, subscriptions and requests when its selected
content is replaced. Pause when the document is hidden and do not advance by accumulated hidden time.
Keep image/stance/camera state only as bounded presentation context, scoped to exact object/revision.

## 7. Browse -> Edit -> Browse lifecycle

**MG-11 — Checked edit entry.** Edit acknowledges immediately with `Opening editor…`, an accessible busy
state and duplicate-activation protection. Resolve the current descriptor and hydrate/validate the exact
source asynchronously. Only a successful identity/revision/capability check replaces the read-only card.
On failure retain that card and a retryable diagnostic. No transient wrong model and no blank editor.

The owning editor session freezes the target reference, current revision/digest, editor kind, save owner
and a detached complete draft. The render cache is not the source of truth. A query URL is not authority
to edit. Selecting B while A loads invalidates A's opening ticket; an ignored abort cannot install A.
A changed server, database, principal, capability epoch or selected revision also invalidates that ticket.

**MG-12 — Save and Cancel.** The editor replaces the card within the same selected-object area. Keep the
Gallery label and return path available. Save and Cancel have the following observable outcomes:

- Save sends one checked, exact-target command through the existing domain writer. While saving, keep
  the draft and show `Saving…`. A committed immutable receipt, followed by checked readback, returns
  to the read-only card for the same identity at the committed revision. Preserve the Gallery state.
- A stale revision, rejected command, lost response or validation/persistence failure leaves the editor
  and draft intact with an actionable message. Do not show "Saved" on dispatch or HTTP acceptance.
  Reconcile a lost response by the same idempotency/operation identity before any retry; do not duplicate.
- Cancel before a save discards only the local draft and returns to that object's card without writing.
  If the owner changed while editing, reread its card truth and explain the newer revision; do not label
  obsolete bytes current. No fixture reload and no implicit "reset source model" operation.
- During an accepted save, close/cancel cannot assert rollback. Either keep navigation pending until
  the terminal result, or detach only through the existing operation/session lifecycle and reconcile
  its exact result. A committed save is not undone merely because its page closed.

After Save/Cancel focus returns to a visible control on the selected card. Preserve expanded ancestors,
search, scope, list position and Gallery open/closed state. Never reopen an unrelated default Humanoid.
Undo/Redo remains the current editor's domain-local draft/history mechanism, not Gallery history.

**MG-13 — Draft guard.** Changing the target, changing scope to exclude it, back-navigation or leaving
Metamorph while dirty prompts **Save changes / Discard changes / Keep editing**. Save must finish
successfully before navigation; failure stays on the draft. Discard performs no domain write. Keep editing
restores the original selection/control value. Clean editors may return directly. Hiding the Gallery or
viewing its disclosures must not trigger a discard prompt. Browser unload uses the platform's available
unsaved-change protection; an in-app Back/return route must not bypass the same guard.

A dirty edit must not be overwritten by a catalog refresh, default model load, scoped filter response,
hot bundle reload or asynchronous geometry hydration. Explicitly present remote changes as a conflict.
Do not keep private drafts in a generic Gallery preference or silently restore them on another account.

## 8. Read, editor and write contracts

**MG-14 — Native JS presentation, existing authority.** Reproduce the approved behavior in Slice's native
JavaScript architecture. Do not port, wrap, translate or preserve Java UI code as the solution. Existing
Java services remain valid backend authorities; this is not permission to rewrite them or duplicate
business rules. The Gallery is a read projection and never a new object database or universal SQL editor.

The following are proposed adapter interfaces, not claims of currently registered capabilities:

```text
GalleryReadAdapter
  readCatalog({scope, query, source, page, admittedContext})
  readCard({target, expectedReadStamp, admittedContext})
  readRelations({target, relation, scope, page, admittedContext})
  readPreview({target, previewRef, expectedReadStamp, admittedContext})

EditorAdapter (registered per supported source/kind/format)
  describe({target, admittedContext}) -> editor descriptor
  open({target, expectedRevision, expectedDigest, selectionTicket}) -> detached edit session
  validateDraft({session, draft}) -> bounded field/format diagnostics
  save({session, expectedRevision, expectedDigest, operationId, draft}) -> owner terminal result
  dispose({session}) -> presentation/draft cleanup, not assumed cancellation of accepted work
```

A catalog entry/selected card carries: exact target and read stamp, display name and kind, source label,
membership evidence, child/relation paging, current/history state, preview references and availability,
metadata, and editing state. The descriptor names a registered editor, source format/version, permitted
operations, write-capability reference and precise unavailability reason. Descriptor state is at least
`EDITABLE`, `READ_ONLY`, `UNSUPPORTED_FORMAT`, `UNAVAILABLE` or `UNAUTHORIZED`. It never grants server
permission. Expected revisions and digests are compared only by their own authority, not as one clock.

The browser orchestrator owns selection, tickets, layout and mode, not persistence. A UI-independent
component adapter validates/routes each intent to the existing domain service or checked component store.
Domain authorities own complete-model validation, authorization, revision conflicts, transactions,
idempotency and committed results. Close/unsubscribe releases UI observers without creating a second
operation owner. The normal route is UI -> typed adapter -> existing authority -> immutable result.

Immediate read-only queries stay ordinary bounded queries, not artificial bus operations. Browser-internal
Gallery/selection/layout notifications use the existing front-side UI bus or local state. Host reads/writes
use the separate typed gateway and existing worker boundaries. No synchronous Swing EDT/JavaFX bridge,
UI-thread database work or newly required visible Java screen. Component worker lifecycle remains with
the current Slice/Tassy managed runtime; do not create another listener or coordinator.

### Coverage and write-authority inventory required before implementation completion

- Native reusable and individual complete models: use their actual current Slice library read/write
  contract after source intake. Prove full model round-trip, not a renderer projection or source reset.
- Stored Slice-native packages: use the existing checked Factory Slice document authority for those
  records, retaining the original combined document and content closure. A new transport adapter is
  required where not already exposed. Do not redirect all Slice library saves into this store by guess.
- Core/private/Factory records: preserve their current pointers, master pins and existing owning service.
  Only enable a complete editor when its adapter can preserve the full format; others stay inspect-only.
- Game Types, Created Things/characters, groups and media/associations: map supported edits to their
  existing GameObject/User Assets/domain services. Read-only media inspection is not proof of a media
  writer. Preserve explicit owner/role/association revisions and review-required attachments.
- Places/worlds/courses: retain the current native terrain editor where supported, separate from
  movable-object editing. Legacy map/Place records keep their map-document revision and layer/source
  authority; unsupported raster editing/decoding is not enabled by adding a card.
- Story and other records: use an admitted owner editor adapter when supported; otherwise expose exact
  structure and the specific missing editor/write capability. Never edit raw retained revisions/content
  as if they were live root objects.

This inventory is a deliverable, not permission to mark every non-Morph row unsupported. The definition of
complete includes at least a non-Slice supported editable object in each admitted editable family, with
its exact save/reopen proof. A genuinely missing family editor or host capability stays an explicit open
requirement. Read coverage, view fidelity and editability are separate columns and acceptance claims.

### Host admission and persistence invariants

Reuse the five existing database viewer GET services for their admitted source coverage or an additive
owner-reviewed projection over those services. Their capability IDs need not change when the old screen
is retired. Register the Metamorph consumer's own checked bootstrap/capability binding; do not impersonate
`talisman.database-viewer` just to borrow its session or route access.

No browse request, preview or scope toggle performs a write, schema initialization, import, migration or
model materialization. Default queries retain bounded pages (40 by default, at most 100), literal search
(up to 160 characters), checked preview limits and source allowlists. Declared model limits belong to the
specific complete-model contract; generic image/text bounds are not an excuse to truncate a model.

Writes are separately admitted through the correct typed writer with current permission, CSRF/session
and server/workspace/manifest checks where required by the host. The existing shared writer lease and
atomic durable-before-visible rule stay in force. No new catalog store, duplicate persistence route,
implicit live conversion, database chooser path, attached secondary database or open mutation wildcard.
Everything does not grant write access; public/read-only Moonbeam access remains read-only. Capability
absence must be visible, not bypassed with direct browser SQL or a hidden old editor window.

Keep complete original bytes or validated complete drafts, unknown retained fields and checked media
closure according to the owning format. No cross-source transaction claim unless one actual authority
provides it. Service acceptance, bus delivery, committed receipt and verified readback are separate facts.
No Java service or source fixture may reset authored models on Gallery startup or refresh.

## 9. Cold start, stale work and fallback

**MG-15 — Lazy does not mean absent.** Populate reusable-type availability and Make/attach choices from
admitted catalog metadata. Zero hydrated geometry must still show valid types. Hydrate and validate the
chosen exact complete model asynchronously before Make/attach confirmation, without changing the active
selection or editor. Cancellation, changed source/target, closed form or newer source revision invalidates
that read. Preserve existing source/target draft guards and initial selected-model restoration; initial
restoration now produces its read-only card, not automatic Edit.

**MG-16 — One visible Gallery after successful takeover.** Replace the actual old Gallery control group,
not guessed selectors. The prior Slice integration used `.gallery-modes`, `#gallery-type`,
`#gallery-individual` and `#gallery-place`; recheck those against the supplied current source. Retire old
roster/mode controls only after logical Gallery capability discovery and a successful first page. Keep
Make and unassigned-individual attach reachable outside any hidden roster. An already-open form retains
its values and focus. No invisible list controlled by orphaned buttons and no duplicate Morph Types.

If logical Gallery/host services are unavailable, show an explicit unavailable or disconnected state and
retry. Preserve current usable local/file-mode library fallback instead of an empty disabled roster.
Its scope label must say local Slice data only; it cannot claim Everything. No automatic remote data import
or cache masquerading as live truth. A failed read must not destroy an active valid draft.

**MG-17 — Epoch and lifetime safety.** Cancel superseded reads and independently reject stale completions
by target/revision, scope/query, request ticket and admitted server/workspace identity. Page teardown
releases renderer listeners/timers, object URLs and subscriptions. Expired/revoked authority clears private
read projections and disables new writes; a pending save is reconciled through its owner. Do not persist
private object payloads, tokens or drafts in Gallery/Shelf presentation preferences. Only checked layout
and bounded identity preferences follow the existing authenticated context contract. A new principal or
workspace cannot inherit a prior one's selected data or draft. Principal/workspace invalidation disposes
the old private editor context; it never auto-saves or copies it into the new context.

## 10. Two doorways, one actual application

**MG-18 — Canonical route.** Preserve managed component identity `slice.metamorph-editor` and its
`/slice/` public prefix. The proposed canonical entry is **`/slice/studio.html`**, in Browse/Everything
by default. It is the route recorded by the supplied receipts; the current source intake must confirm
that it is the real entry, not invent an additional app. Both entry controls resolve through the same
route contract and component, with only an allowlisted return-origin context differing.

The optional new deep-link vocabulary is `mode=browse`, `scope=all|slice`, an opaque selected-object
reference and `entry=talisman|slice`. These are proposed inputs to validate during implementation, not
existing endpoint claims. Omitted inputs mean Browse/Everything. Do not ship `?editor=humanoid` as a
badge default: it preselects one model and may enter editing. Legacy editor hints resolve to a checked
selection in Browse; malformed, unknown or unauthorized references show a safe diagnostic. A URL cannot
turn editing on or confer permission. No arbitrary return URL.

**Talisman application landing page:** replace the **Talisman Database Viewer** badge with **Metamorph**
and a description such as "Browse and edit Talisman objects". This is the four-badge `/talisman/` page,
not the Moondance Games landing page, Tassy technical register or a redesign of all navigation. Keep the
other three badges and their functions unchanged. The existing creature-image Gallery is not the new
all-object Gallery and must not be mistaken for another Metamorph instance.

**Slice game:** its existing editor control opens the same Metamorph entry, not an embedded second
library/editor implementation. Retain a selected saved-object reference when explicitly requested, but
show its card before Edit. Launching from Slice must not force Slice-only scope or reset the game. Return
to Slice through its existing saved/session context. Do not auto-save an unfinished round on editor entry.

The existing Talisman single-tool frame may remain a presentation shell; it must load that identical
entry directly, not the Slice game first, a second chooser, the old viewer, a copied editor or nested
application frames. In-app return/history must honor dirty-draft guards before destroying the editor
frame. A scoped message bridge, if needed, admits only the expected same-origin frame and typed return/
dirty-state messages; it cannot grant writes. Both entry paths retain their expected Home/Back return.

**MG-19 — Retire the old user-facing screen, not its data services.** After the consolidated surface
passes acceptance and is available, `/database-viewer/` becomes a compatibility redirect/entry alias to
Metamorph, not a second browser UI. Map `/talisman/?tool=database-viewer` to the same new destination.
Forward only validated exact selection context; no untrusted URL redirect. Remove obsolete user-facing
viewer navigation and dead page-only controls/resources only when their consumers are accounted for.

The five read services, semantic identities and useful safe-rendering behavior do not disappear merely
because the page is retired. Keep compatibility capability names until an explicit owner-reviewed API
retirement. This body does not delete unrelated desktop diagnostic database tools. Do not point the
badge to an absent bundle or retire the working page before the replacement is ready. Rollback of a
failed code adoption restores navigation without changing database content.

## 11. Delivery and implementation acceptance

This patch changes documentation only. It does not redirect a badge, install a handler, remove a screen,
edit a model, apply the old terrain candidate, import a database, publish or deploy anything.
The [living test plan](TEST-PLAN.md#metamorph-gallery--browse-first-in-place-edit-2026-09-13) owns the
acceptance cases. Tests are pure programmatic fixtures and temporary databases; no browser/UI automation,
real database, network deployment or paid generation is authorized by this design.

The subsequent scoped code return must contain a specifically named replacement patch against the then-
authorized supplied source, changed-file/source identity, adapter coverage matrix, actual focused results,
manual acceptance steps and a truthful omissions list. Do not return only this specification again.
Do not claim the code applied to Mark's app merely because a patch file was produced.

Required end-to-end proof: enter from each doorway -> Everything -> select a non-Slice object -> inspect
its visual card -> Edit that exact object in place -> save through its owner -> reopen its committed card;
then repeat with Slice only and an individual Morph, preserve the Gallery through close/reopen, and use
the saved model in the unchanged Slice game. Include a character with no Morph but checked icon/token,
a full complete-model type/individual round-trip, a rejected stale save, an unavailable-service fallback
and a dirty-navigation cancellation. Do not label source checks as visual acceptance.

Still outside this body and not newly completed: automatic global terrain-detail streaming, additional-
region UI, legacy Place raster decoding, old Core conversion/retirement, unfinished hole-reordering and
full-round terrain acceptance. Thirty Pieces, movable-object placement and deployments remain separate.
These gaps must not be used to postpone the scoped Gallery integration when its source/adapters are ready.
