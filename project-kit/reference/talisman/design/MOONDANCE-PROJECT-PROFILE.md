# Moondance Project Profile

Version: 4 — seven-language core with Materialization.
Status: adopted design doctrine; no runtime implementation in this body.

Moondance projects share interoperable design languages. Talisman is the current reference implementation,
not a required library, host, database or repository. Independent games implement the contracts locally,
record their semantic bindings and intentional deviations, and prove each adopted capability. This profile
links detailed contracts rather than replacing them. Reference behavior, approved direction and proven local
adoption must remain distinguishable. A shared word alone does not establish compatible runtime behavior.

## Seven languages and their connections

Presentation reveals objects and invokes bounded intent. Object/world identity supplies references to
Context. Context supplies admitted evidence and required return shapes to Tally Talk. Services alone admit
mutations. Identity/revision/provenance connects inputs to committed outcomes. Journey and verification
establish what was actually reviewed, implemented and observed across the other six languages.

### 1. DTDT and Shelf

Use Shelf, Shelf Label and Shelf Box as the structural vocabulary. A rail is a layout arrangement of
presentation, not an additional domain owner or replacement Shelf noun. DTDT expresses declarative
presentation intent; projects may implement an equivalent local declaration without importing Talisman's
DTDT version numbers, parsers, migrations or classes.

- A Shelf owns `MULTI_OPEN` or `EXCLUSIVE_REPLACE` occupancy. Label order and open chronology are independent.
  Reordering labels never rewrites when Boxes opened. Close removes an opening; reopening is a new opening.
- Keep Close pinned at the inline leading edge and keyboard reachable. Return focus to its owning Label,
  subject to an explicitly reviewed dirty-close policy. Content must yield space before Close disappears.
- Expand/Restore follows the allocation axis and preserves visible peers. Takeover is a separate temporary
  peer-occlusion action with exact return-state restoration; it is not an occupancy mode or another Expand.
- Distinguish the label-region/open-Box boundary divider from dividers between adjacent open Boxes. The former
  changes cross-axis label extent; the latter allocates Box space along the region axis. Preserve minima,
  orientation-specific values, equivalent keyboard actions and accessible value descriptions.
- A Box may contain a Shelf. Validate exclusive ownership, cycles and declared depth before presentation.
  Open/close, orientation, resize, focus and restoration are independent, explicitly owned transitions.
- Persist only validated presentation structure/state. Bind complete envelopes to schema, definition,
  identity/fingerprint and declared person/context or local scope. Reject incompatible/partial state;
  migrations require a separately specified whole-envelope rule, never an opportunistic partial merge.
  Layout storage never contains domain authority, private prompts/results, credentials or application jobs.
- Use shared semantic tokens and fixtures. Purposeful color requires words/icons/borders or other noncolor
  cues. Typography roles distinguish display, headings, narrative and controls; dense text stays readable.
  Verify keyboard/focus, narrow screens, touch and iPad expectations, zoom and visible active control states.
  Same-action async feedback appears immediately, protects against duplicates and ends on confirmed outcome.

Reference: [Shelf v1](<Active Designs/Talisman Shelf__/SHELF-CONTRACT-V1.md>),
[Shelf v2](<Active Designs/Talisman Shelf__/SHELF-CONTRACT-V2.md>) and
[v3 successor design](<Active Designs/Talisman Shelf__/DTDT-V3-SHELF-DESIGN.md>).
The first two describe versioned Talisman contracts; the v3 document explicitly remains a reviewed successor
with no runtime authorized. Its portable design semantics do not prove an application cutover has occurred.
Forest supplies Talisman's theme binding, not universal font names, palette values or branding.

### 2. Theme

Theme is a separate language of semantic palette, typography, spacing, shape, texture, emphasis, motion,
sound cues, density, accessibility and platform adaptation. Shelf structure declares relationships and
interaction; it does not encode a theme. Theme expresses presentation intent without domain authority.
Use semantic roles with readable contrast and redundant noncolor cues, not raw per-widget styling.
Motion and sound cues need accessible alternatives and locally reviewed control; they must not be the
only evidence that an action occurred. Platform adaptation preserves meaning, focus and interaction.

Forest is one Talisman binding. Its palette, font choices and brand treatment are not universal defaults.
The current [browser theme contract](<Active Designs/Talisman Forest__/BROWSER-THEME-CONTRACT.md>) and
[Forest design](<Active Designs/Talisman Forest__/ACTIVE-DESIGN.md>) provide partial reference ingredients,
not proof that the entire portable Theme language, including sound, is implemented everywhere.

### 3. Morph and object/world

A Morph is a complete, self-contained versioned object. Stable Points and Joints define a connected acyclic
semantic graph, with a single root and one incoming Joint per non-root Point in the reference contract.
Parts, surface/material identities, sockets/fittings and sites give named structure and attachment intent;
projects must specify their exact local representation before claiming these extension points work.

Exactly Mapping and Resting are complete default poses. Named stances, reversible motion pairs, constraints
and reversible deltas carry explicit endpoints, dependencies and applicability guards. A delta is not hidden
inheritance: it resolves against an exact complete revision and must not create a behaviorally dependent
partial Morph. Copy provenance is descriptive; a later base change never silently alters an existing copy.
Sound-linked behavior is an explicit proposal bound to a stance/motion and its locally adopted sound
identity; it grants no implied playback, asset access or new behavior authority.
Declare axes, handedness and units explicitly. The current Talisman binding uses X-right, Y-up, Z-forward
and finite coordinates in feet; that particular unit choice is not a universal requirement.

A complete source-authoritative catalog defines fixed base Morphs. Deterministic reads and geometry derive
from exact complete data. Define a deterministic indexed surface and render actual indexed-triangle edges
for Mesh Wireframe, distinct from the semantic Skeleton. An outline or skeleton must not masquerade as Mesh
Wireframe. Appearance, images, camera state and generated render artifacts remain separate from Morph
semantic authority; material identity does not turn an image into skeletal authority.

Complete Morphs may attach to or contain other complete Morphs as Moondance composition doctrine, spanning
creatures, equipment, furniture, doors, terrain and Places. Fittings are extension points. Universal Place
or object composition is reference direction, not a claim of current universal runtime capability. Each
adoption must define containment versus attachment, site frames, constraints, lifecycle and identity rules,
cycle handling, persistence and focused proof. This profile does not invent missing semantics.

Reference: [Morph design](<Active Designs/Object Sing & Dance Factory__/MORPH-DESIGN.md>),
complete source catalog (Talisman source reference: `../src/main/resources/app/factory/morphs/`), and
[current Atlas](../development-atlas/Development-Architecture-Atlas.md).
The reviewed authored-save reconciliation direction does not make an unshipped save service current, and
Talisman's database materialization or revision IDs are not exported as universal storage requirements.

### 4. Context and Tally Talk

Context is an immutable Snapshot composed by the owning areas: stable references, exact revisions,
disclosure/redaction, budgets, provenance, freshness and explicit omissions. Capture a bounded intent and
user instruction with admitted context, not an uncontrolled private corpus. Withheld metadata must not leak
through omission descriptions, labels or diagnostics.

The canonical Moondance spelling is **Tally Talk**: language + admitted context + required return shape.
Existing Talisman TaliTalk names and protocol identifiers remain compatible local bindings. Answers and typed
proposals are never AI mutation authority. A feature owner rechecks currentness, permissions and expected
revisions when the user accepts a proposal; a model cannot authorize its own result.

Manual copy/paste, a local model and a provider API should carry the same semantic envelope and review rules.
This is cross-route doctrine, not a claim that every current provider route implements one common codec.
Keep context and results volatile by default; durable retention requires an explicit owner contract.
Private prompts and results never belong in Shelf presentation memory.

Reference: [Context contract](../context-management/docs/CONTEXT-CONTRACT.md),
[version-one payload](../context-management/docs/APPLICATION-CONTEXT-PAYLOAD-V1.md), and
[current integration](../context-management/docs/APPLICATION-INTEGRATION.md).
These specify admitted snapshots and bounded local consumers; they do not authorize a new provider route.

### 5. Services and capabilities

The normal route is bounded typed intent → Behavior/adapter → one service/domain authority → immutable
state, progress, event or terminal result. An immediate read-only direct query is the deliberate exception.
A bus delivering an intent proves neither acceptance nor commit. Keep one durable owner and its revision
guard; a screen, browser or generic dispatcher must not become a duplicate mutation authority.

Expose named bounded allowlisted capabilities. Browsers receive reviewed scalars/projections, not raw
SQL, filesystem paths, credentials, process authority or a general service locator. Validate whole
semantic envelopes and fail closed. Bind correlation, lifecycle/generation, expected revisions and
idempotency to the owning operation. Cancellation requested is not cancelled terminal truth.
Composition, startup/shutdown and subscription disposal are explicit. Stale callbacks cannot update a
replacement owner. Do not invent an ad-hoc server or second store merely to connect an interface.

Reference: [service architecture](../development-atlas/service-architecture.md) and
[events/threading](../development-atlas/events-threading.md). The portable ownership rules do not require
Java, a process-local bus, a particular concurrency toolkit or Talisman's transport/authentication schemes.

### 6. Identity, revision, provenance and import

Semantic IDs outlive labels. Distinguish source originals, admitted native materialization, derived render
results and live presentation. Immutable revisions and dependency stamps/digests bind exact content;
signatures, when supplied, retain their verification result and trust scope rather than implying trust from
a hash alone. Writes use atomic transactions and expected-revision checks. Provenance must reconstruct
which source, normalization, dependencies and transformation produced the result.

Preserve originals and rights information. Preview axes, units and name normalization before admitting
native materialization. A preview is not an import commit. Imported standards can enrich the vocabulary
without imposing hidden network services, source-file availability or foreign runtime coupling. State
unsupported fields and omissions explicitly. Regenerating a projection must not overwrite authored truth.

Reference: [persistence and content](../development-atlas/persistence-and-content.md) and
[import action routes](../development-atlas/action-routing.md). Specific SQLite schemas, content-store
layouts, asset IDs and CoreMorph source/materialization identities remain Talisman bindings.

### 7. Journey and verification

Journey is user examination of actual behavior. Projects assign durable architecture responsibility to
owners and organize bounded bodies with reviewable patch/evidence returns. The responsibility is portable;
Talisman's named owners, Patcher title syntax, branches and task governance are not universal mandates.

Distinguish designed, patched, published locally, landed, equal, live, served and physically accepted.
A patch's existence does not prove integration. Equality does not prove the running source. Served bytes
do not prove the user has accepted behavior on their device. Status labels and transport receipts are not
completion evidence. Tie narrow boundary proofs and observed failures to exact source and environment.

Reference: [Operations evidence contract](../development-atlas/operations-contract.md) and
[focused verification routes](../development-atlas/focused-tests.md). Projects bind these evidence meanings
to their own release process, with explicit proof of where source, runtime and user acceptance differ.

## Materialization: purpose and outcomes across the seven languages

Materialization carries one understandable stable semantic world into useful forms: checked browser or
desktop software; local/remote services; screens/scenes; printable cards, boards, miniatures, terrain and
assemblies; Blender/animation/cinematic/replay artifacts; sound/voice; and blocking, performance or dramatic
scenes. It is an outcome axis across all seven languages, not an eighth language and not merely database
materialization. These are target families, not claims that every target already has an implementation.

Every materializer consumes versioned admitted source and declares its target profile and capabilities:
units, coordinates, scale, tolerances, materials, colors, textures, rigs and limitations where applicable.
It produces a derived artifact with provenance. An impressive screen, mesh, film or physical object never
becomes source authority merely by being produced.

- Keep authored semantic source separate from outputs. Preserve stable semantic identities across forms,
  including an explicit mapping when a target cannot carry the original IDs itself.
- Preview and validate before commit or manufacture. Retain source revision, materializer/generator version,
  input identities/licenses, target profile and output digest. Rights and target limits remain inspectable.
- Define deterministic regeneration and inspectable differences. Capture seeds and external inputs where
  applicable; if reproducibility cannot be established, report that limitation rather than claim it.
- Declare all losses. Never silently promote an edited derivative back into semantic source; re-admission
  requires its own reviewed owner operation and provenance.
- Keep adapters at the edge. AI proposes a materialization plan; the owning bounded capability applies it.
  A renderer, printer, host or external editor is not a second domain owner.
- Provide an accessible useful preview/fallback without Blender, a printer, hosting or voice. Missing target
  software must not prevent understanding the source or erase the proposal and comparison evidence.

### Talisman foundations and limits

- Desktop/browser screens and Shelf/Factory projections are existing reference ingredients, not a generic
  software generator. See [current Atlas](../development-atlas/Development-Architecture-Atlas.md).
- [Managed application host](<Active Designs/Application Server/PAGE-PLUGIN-CONTRACT.md>) and
  [Online service design](<Active Designs/Moondance Web/TALISMAN-ONLINE-SERVER.md>) have separate ownership.
  [Player relay routes](../development-atlas/service-architecture.md) remain separately admitted delivery;
  neither a local screen nor this profile proves remote hosting/relay acceptance.
- 2D/Arena3D and Factory solid, Skeleton, actual Mesh Wireframe and textured projections provide distinct
  views. [Factory design](<Active Designs/Object Sing & Dance Factory__/SYSTEM-DESIGN.md>) and
  [export evidence](<Active Designs/Object Sing & Dance Factory__/REFERENCE-AUDIT.md>) describe existing
  OBJ/MTL/texture foundations. Geometry-only STL carries no UV/material authority; future manufacturing
  profiles still require scale, tolerance and physical suitability validation. Export availability is not
  proof that a model is printable or that the roadmap fixture below has passed.
- [Editor Integration](<Active Designs/Talisman Editor Integration/LOCAL-EDITOR-INTEGRATION.md>) includes
  a Blender design candidate, not a landed generic materializer or approved universal editor round trip.
- Provider-free sound/cue work is active direction, not a completed Main sound materializer. Adventure,
  Easy Tale and Walkthrough [service foundations](../development-atlas/service-architecture.md) do not
  constitute a generic cinematic/replay/performance pipeline. General print materialization is unverified.
- [Morph authority](<Active Designs/Object Sing & Dance Factory__/MORPH-DESIGN.md>),
  [import/provenance](../development-atlas/persistence-and-content.md) and
  [verification evidence](../development-atlas/operations-contract.md) remain controlling boundaries.

### Smallest future materialization proof — roadmap only

Select ONE saved deterministic Factory wall or rectangular-table fixture. From the same exact admitted
source, demonstrate A: interactive solid/textured/actual Mesh Wireframe; B: OBJ + MTL + approved texture;
and C: geometry-only STL. Do not create the fixture, manifest, exporter or product proof in this body.

A future bounded manifest should bind source ID/revision, materializer and profile version, units/axes/scale,
material identity, input/output digests, rights, capabilities and losses. Explicitly record STL's UV/material
loss and any target identity mapping. Compare deterministic regeneration, show the same source across all
three outputs, reject derivative authority, and provide useful inspection without external software.
Manufacturing tolerances remain a separate validation requirement before any physical-production claim.

Return observed strengths, losses and failures as learning candidates only. Another independent project
must review and prove its own adoption before anything enters the accepted learnings ledger.

## Interpretation: the governing design method

Moondance should be a marvelous interpreter, not a complicated instrument panel. Natural intent translates
into the seven languages, an understandable preview, authority checks and then application. AI is the
principal translator in this design direction, never mutation authority and never permission to call a
provider. Direct manipulation remains where it is delightful and clearer. Avoid exposing internal ceremony
merely because the implementation needs it.

Each interpreted flow must show what was understood and which context/objects were used; preview the visual
or structural proposal; identify omissions and uncertainty; permit natural-language refinement; retain exact
source/revision identities; and apply only through the owning capability. Provide comparison, safe failure
and the locally supported undo route. Never promise universal undo: name irreversible effects and missing
undo support before approval. Neither universal AI translation nor cross-feature undo is currently proven.

Design test: **Could the user state the desired outcome naturally, see what Moondance understood, and
approve or refine it?** Prefer that route when possible. Otherwise provide the smallest honest direct
control and record the future interpreted route without pretending it already works.

Existing ingredients include Shelf structure, Forest semantic tokens/typography, admitted Context snapshots
and proposals, Adventure reviewed plans, Factory reviewed generation, and revision/history/comparison.
See [service and reviewed-operation routes](../development-atlas/service-architecture.md),
[Context integration](../context-management/docs/APPLICATION-INTEGRATION.md) and
[persistence/history](../development-atlas/persistence-and-content.md). Reuse their authority boundaries.
They do not yet constitute one cross-feature Interpretation contract.

Smallest future adoption question: which ONE existing bounded capability should demonstrate a natural
instruction → admitted snapshot → typed previewable proposal → owner-checked application, and what minimal
shared envelope is actually missing? Answer that through a separate reviewed body; this profile does not
specify a new executor, schema, provider integration or universal composition system.

Talisman opportunities below are gaps/candidates only, not delivery claims or implementation assignments:

- Morph: “make the dragon wary” or “give the door a slow creak” → exact stance/motion/sound proposal;
  complete Morph identity and local fitting/sound support must be established first.
- GM: “show this room to these players” → current room/audience context, Player-safe preview and GM Present.
- Authoring: “spread these tables evenly” → selected objects/bounds, deterministic layout and owner placement.
- Geography: natural terrain refinement → exact Layer/source and bounded raster/feature preview, then editor
  authority; no direct AI raster mutation.
- Easy Tale/Context: “compare spring and winter” → admitted timeline/source, read-only comparison.
- Theme: “a torchlit dungeon” → semantic token proposal, never raw CSS/widget mutation or domain authority.

## Adoption and executable workshop flow

Idea → executable HTML/CSS/JS wireframe → user review → bounded implementation → focused proof → publication.
Reviewed patches, live/served evidence and human/physical acceptance remain separate later gates; publication
alone cannot close them.
A workshop must be interactive proof, not a static mockup: exercise representative state transitions,
empty/error/stale cases, focus and narrow/device behavior using explicit fixture data. A workshop does not
claim real persistence, provider or domain execution merely because a button animates. Review records name
accepted behavior, remaining gaps and exact workshop source before implementation begins.

Each independent project records its adopted profile version, seven-language semantic bindings, owners of
local authority, source contracts and fixtures, intentional deviations with rationale, and acceptance
proof. Mark unsupported capabilities as unsupported. Revisit deviations when evidence changes; never
silently copy Talisman code or assume a shared word imports its runtime. Talisman's existing contracts win
for current behavior; this profile's direction requires explicit local implementation and review.

| Canonical portable commitment | Talisman-specific binding, not an export requirement |
| --- | --- |
| Shelf structure, transitions, declarative intent, accessible state | DTDT versions/migrations/classes |
| Theme: semantic color, typography, spacing, cues, adaptation | Exact Forest values, fonts, branding |
| Complete Morphs, explicit frames, deterministic surfaces | Catalog IDs, CoreMorph/SQLite materialization |
| Admitted Context and reviewed Tally Talk | Java codecs, TaliTalk names, provider adapters |
| Typed intent and one bounded service authority | Java/Swing/JavaFX, package layout and bus implementation |
| Capability admission and lifecycle truth | TAS port 3002, routes, manifest, CSRF and hot publication |
| Identity, provenance, originals-first native import | Talisman assets, database/revision formats |
| Review and source/runtime/acceptance evidence | Owners, branches, worktrees, release/task governance |
| Locally defined game semantics | GM/Player/Control relays, game rules and feature names |

## Cross-game learning exchange

Return candidate learnings with originating project/body, problem, exact evidence, proposed portable rule,
known counterexamples, adoption cost and review status. Receiving games evaluate locally; transmission is
not adoption. Only an explicit review supported by evidence may promote a candidate into accepted learning.
No automatic cross-repository edits, copied private context or new runtime dependency follows an exchange.

Initial candidates, not accepted cross-game findings:

- Materialization: one exact semantic source, explicit target losses and reproducible derived outputs.
- Interpretation: natural intent, explicit understanding, preview and owner-checked application.
- Theme roles independent of structure; Context disclosure and reviewed proposals.
- Label order versus open chronology; Expand versus Takeover.
- Fail-atomic presentation envelopes and fingerprints without private/domain content.
- Complete Morphs without inheritance, Mapping/Resting and reversible motion pairs.
- Bus delivery versus operation acceptance; the source/runtime/user-acceptance evidence ladder.
- Originals-first import with reviewed normalization.

## Moondance learnings

Accepted evidence-backed entries: **none yet**. This ledger is intentionally empty. Candidate doctrine
above is not evidence of a successful transfer between games. Future entries must identify both source and
receiving project evidence, review decision, adopted scope/deviations and the resulting verification.
