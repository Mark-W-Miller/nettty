# Moondance Project Kit

## Controlling scope correction from Mark — documentation framework only

The Moondance Project Kit is an organized, self-contained block of DOCUMENTATION. It defines rigorous
structures, architecture contracts, boundaries, conventions, document templates and acceptance rules.
Coders choose and build implementations within that framework. Observed friction informs deliberate
revisions to the framework. This is not a package of executable installers, validators, materializers,
runtime libraries or tests. Examples and validation rules are documented requirements, not shipped
executable machinery. Physical adoption means including the versioned documentation in a project.

This direction supersedes executable construction/validation demands elsewhere in this document and
older handoffs. Those demands were Atlas's scope error. First release requires coherent organized
coverage, readable entry/navigation, local document links, provenance/version and clear project-specific
extension boundaries. No software execution or generated database objects are release gates.


## Status and authority

**Status:** active design initiative; inventory and exact design are the first body. No Project Kit
repository, release, game migration, executable implementation, database materialization, server, or
deployment is authorized by this document.

**Commissioning authority:** the Grand Pubah commissions the work, directs its scope, and defines
acceptance.

**Design authority:** Talisman Architecture performs the cross-Talisman inventory and authors the exact
Project Kit design. Sally reviews and publishes controlling shared documentation through Main.

**Future implementation authority:** only after design acceptance, create a dedicated Moondance Project
Kit repository and permanent owner for implementation and releases.

## 1. Goal

Every new or existing Moondance game repository should be independently understandable, buildable,
validatable, and suitable for a full-source Patcher ZIP without also supplying Talisman Main.

The Moondance Project Kit is a versioned, physically vendored package of shared project knowledge and
validation assets. Its human front door is `MOONDANCE-PROJECT-KIT.md`. A consuming repository must remain
comprehensible and locally validatable when disconnected from every other Moondance repository.

The Kit prevents two opposite failures:

- copying fragments of Talisman without the contracts that make them safe; and
- turning every game into a live dependency on Talisman Main or another central repository.

The Kit is not a universal application framework. It supplies shared contracts, templates, validation,
and deliberate migration machinery while each game keeps ownership of its own application.

## 2. Required Kit contents

A released Kit contains a complete, versioned set of:

- the human front door, `MOONDANCE-PROJECT-KIT.md`;
- machine-readable standards and schemas;
- templates and documented defaults;
- deterministic fixtures and validators;
- explicit version-to-version migrations;
- portable theme packages;
- project bootstrap and update tooling;
- a Kit manifest and consuming-project lock format; and
- setup guidance for people, agents, builds, validation, and full-source Patcher exchange.

The shared subject matter includes:

- Development Architecture Atlas structure and navigation conventions;
- DTD and declarative UI contracts;
- native Morph contracts;
- Shelf composition contracts;
- context question and answer contracts;
- service, process, and database ownership boundaries;
- accessibility requirements;
- provenance, validation, review, and acceptance vocabulary;
- standard themes such as Talisman Forest where they are portable; and
- repository and agent setup guidance.

The Kit does not contain project-specific application code, screens, game rules, campaigns, worlds, or
content. It may define an extension point used by those things, but must not smuggle one game's behavior
into the canonical base.

## 3. Physical snapshot rule

Each consuming repository vendors a complete physical snapshot of the selected Kit version. Understanding,
building, or validating that repository must not require:

- a filesystem symlink;
- a Git submodule;
- a cross-repository relative path;
- a checkout of Talisman Main; or
- a package-manager-only dependency whose source contracts are absent from the repository.

The vendored snapshot is intentionally inspectable. Generated caches or installed packages may accelerate a
build, but cannot be the sole copy of the standards, schemas, templates, migrations, or validation rules.

One Kit release must have one canonical content digest over its declared physical file inventory. A consumer
must be able to prove that its vendored base matches that release while separately reporting local extension
and divergence state.

## 4. Manifest, lock, and compatibility truth

Every consuming repository records at least:

| Field | Meaning |
| --- | --- |
| `kit_version` | Selected semantic Project Kit release. |
| `source_revision` | Exact source revision from which the release was built. |
| `source_date` | Recorded release-source date, not adoption time. |
| `content_digest` | Digest of the complete declared Kit snapshot. |
| `adopted_at` | When this repository accepted the snapshot. |
| `compatibility_state` | Current validated relationship to the selected release. |
| `local_extensions` | Explicit stable identities and paths owned by this project. |

The manifest describes the Kit release and its inventory. The consuming-project lock selects one exact
release, records adoption, and names local extensions. Neither file may claim successful validation that did
not run.

The initial compatibility vocabulary is:

- `CURRENT`: exact supported Kit release with no unrecorded divergence;
- `SUPPORTED_OLD`: validated older release still inside the support window;
- `UPGRADE_AVAILABLE`: a newer compatible release exists but has not been applied;
- `DIVERGED`: canonical Kit files differ outside declared extension points; and
- `MIGRATION_REQUIRED`: the selected update requires an explicit migration before validation can succeed.

Workboard may later present these states as **Current**, **Supported old**, **Upgrade available**,
**Diverged**, and **Migration required**. No Workboard implementation belongs to this body.

## 5. Deliberate update lifecycle

Project Kit adoption and updates are reviewable transactions, never silent synchronization:

1. identify the current lock, complete vendored inventory, and local extensions;
2. compare the selected current and candidate Kit releases;
3. preview the exact file and semantic changes;
4. identify declared extensions and any undeclared local divergence;
5. select and preview the required migrations;
6. apply into a recoverable candidate workspace;
7. validate schemas, templates, source boundaries, compatibility, and project-specific extensions;
8. produce an immutable result or typed failure; and
9. commit the reviewed snapshot, lock, migration receipts, and compatible project changes together.

An updater must never silently overwrite local extensions. A conflict, unknown migration path, missing
required input, stale lock, digest mismatch, or invalid result returns a typed failure and leaves the accepted
repository state unchanged.

## 6. Template architecture

The canonical formula is:

> Template = required components + documented defaults + validation rules + allowed extension points.

Templates establish a complete valid base without forbidding game-specific composition. Hints replace
documented defaults when valid. Missing required values receive defined defaults; missing values for which no
safe default exists fail validation rather than being guessed.

Stable identities survive deterministic rematerialization and version-aware migration. A project may add
components through declared extension points without modifying the canonical base template.

### 6.1 Place template

A new Place receives:

- stable identity, type, and name;
- hierarchy and world relationship;
- dimensions;
- altitude and elevation;
- coordinate and orientation information;
- required areas and regions;
- default terrain and surface state;
- empty inhabitants, objects, and connections collections;
- provenance metadata; and
- revision metadata.

Valid supplied hints replace the documented default for the corresponding field. Unrelated hints do not
silently alter other defaults. The template declares which relationships may remain unresolved and which are
required for admission.

### 6.2 Character template

A new Character receives:

- stable identity, type, and name;
- a complete character-sheet component;
- a Morph assignment or explicit unresolved Morph reference;
- appearance;
- stance and motion state;
- inventory and equipment;
- relationships and affiliations;
- Place and location;
- visibility and ownership; and
- provenance and revision metadata.

The base template does not require one game's rules, class system, combat model, or screen. Games add those
through declared components without rewriting the Character base.

### 6.3 Source and database authority

Shipped templates and defaults are source-controlled authority. A deterministic, version-aware,
identity-stable importer may materialize them into the operational database.

The importer must:

- validate the selected Kit version, source revision, file inventory, and content digest;
- preserve stable template and component identities;
- distinguish canonical base data, declared local extensions, and user-authored data;
- preserve user-authored records and modifications;
- preview additions, changes, migrations, and conflicts;
- apply one checked transaction or leave the database unchanged;
- return a receipt for the exact accepted source and resulting database revision; and
- return typed validation, compatibility, stale-state, and conflict failures.

The operational database is a materialization and runtime authority, not a replacement for the shipped
source templates. A clean repository plus its selected Kit snapshot must be sufficient to reproduce shipped
defaults without hand-created database state.

## 7. Validation plan

The inventory/design body defines exact checks before implementation. The eventual validator suite should
prove, using only the consuming repository and its vendored Kit snapshot:

1. the human front door and every local link resolve;
2. the manifest, lock, schemas, templates, migrations, and fixture inventory are complete;
3. the declared content digest is byte-reproducible;
4. forbidden symlink, submodule, cross-repository, and hidden package-only dependencies are absent;
5. clean bootstrap produces deterministic valid Place and Character instances;
6. supplied hints replace only their documented defaults;
7. allowed extensions validate without modifying canonical template files;
8. an unchanged reinstall is idempotent;
9. an update previews exact changes and preserves declared local extensions and user-authored data;
10. stale locks, digest mismatch, undeclared divergence, missing migrations, and conflicts fail closed;
11. database materialization is deterministic, transactional, identity-stable, and rollback-safe;
12. accessibility, context, provenance, service/database, DTD, Morph, Shelf, and theme contracts remain
    internally consistent; and
13. a full-source Patcher ZIP contains everything required for independent comprehension and validation.

Project-specific validators may add rules, but cannot weaken the selected Kit's required checks. A project
must report unsupported or locally extended behavior honestly rather than editing the canonical fixture to
make a failure disappear.

## 8. Repository shape for design review

The Atlas inventory body should return a proposed tree comparable to:

```text
moondance-project-kit/
  MOONDANCE-PROJECT-KIT.md
  manifest.json
  standards/
  schemas/
  templates/
    place/
    character/
  defaults/
  fixtures/
  validators/
  migrations/
  themes/
  tools/
  docs/
```

This tree is a review target, not authorization to create the repository. The inventory must decide exact
formats, naming, ownership, extension boundaries, and which existing Talisman assets are portable before an
implementation body is commissioned.

## 9. Order of battle

1. **Grand Pubah:** commission, direct, and define acceptance.
2. **Atlas:** inventory cross-Talisman shared contracts and produce the exact Project Kit design.
3. **Sally:** review and publish controlling shared documentation.
4. **Dedicated owner:** after design acceptance, create the Project Kit repository and own implementation
   and releases.
5. **Third game:** prove a clean bootstrap in Mark's new third game.
6. **Dwarf War Combat Simulator:** perform the first existing-project migration.
7. **Talisman:** reconcile with the published Kit.
8. **Slice:** reconcile last because it already contains evolved shared concepts.
9. **Workboard:** add version visibility only after the mechanism works.

Each numbered step has its own acceptance and may expose a blocker. Later steps are not authorized merely
because this order is recorded.

## 10. Atlas Patcher boundary

The Atlas Patcher's first body is repository-wide inventory and Project Kit design only. It must not:

- create the Project Kit repository;
- migrate a game;
- modify executable source;
- materialize or alter a database;
- change a server or deployment;
- implement Workboard UI; or
- infer acceptance for later order-of-battle steps.

The Patcher works from a fresh full ZIP of exact clean Talisman Main prepared and attached by Mark. It returns
one reviewable documentation patch containing:

- the exact source baseline identity;
- the proposed physical tree and file authority map;
- the versioning, lock, compatibility, and migration model;
- the Place and Character template contracts;
- the deterministic validation plan;
- staged bootstrap and adoption;
- explicit gaps and decisions still required; and
- changed-path, link, line, and source-boundary proof.

This document does not create that ZIP, send a Patcher message, or start the body.

## 11. Acceptance for this initiative definition

This controlling direction is accepted when it:

- remains navigable from the Active Designs and repository indexes;
- clearly separates the Kit from project-specific application and content ownership;
- requires a complete physical vendored snapshot and independent validation;
- makes version, source, digest, adoption, compatibility, and extension truth explicit;
- specifies deliberate previewed migration with no silent overwrite;
- defines complete extensible Place and Character base templates;
- preserves source authority and checked database materialization;
- records the order of battle without starting later steps; and
- keeps the first Patcher body documentation-only.

No executable test, runtime acceptance, migration result, or Kit release is claimed here.

## 12. Prepared manual Patcher handoff — 2026-09-15

The ready-to-paste instruction is [Project Kit handoff](handoffs/PROJECT-KIT-HANDOFF.md).
Atlas prepared it against the exact Main commission. Mark creates and attaches the fresh full Main ZIP
and pastes the instruction into the existing `__Architecture Atlas` companion. No ZIP was created,
no Patcher was contacted, and no inventory implementation or later adoption stage started in this body.

## 13. Restored Atlas commission and lifecycle convention — 2026-09-15

The [current Atlas front door](ACTIVE-DESIGN.md) adds the shared Active Designs lifecycle and supersedes
earlier companion routing with `__Talisman Architecture`. Atlas works on its permanent branch, not Main.
The Kit must teach and specify validation for one current ACTIVE-DESIGN.md per design, honest indexing,
preserved inactive/completed/history outside Active Designs, and safe archive/reactivation. Existing
Kit requirements remain. Use the new [lifecycle handoff](handoffs/LIFECYCLE-HANDOFF.md); the earlier handoff
is retained historical preparation. No Patcher delivery or broad cleanup is claimed.

## Queued Patcher bodies

Apply the [Patcher Queue contract](PATCHER-QUEUE-CONTRACT.md). Active Designs may declare stable queued
bodies; the Kit teaches and validates identity, dependencies, exact source/ZIP evidence, manual ceremony,
receipt, return, intake and separate completion/landing. The current cleanup is the first declared body,
QUEUED for `__Talisman Architecture`, not dispatched. Sam owns the queue-page implementation.

## New-project bootstrap authority

Mark assigns new Moondance project creation to the Grand Pubah. Every new project must receive the
approved Moondance Project Kit as a complete physical vendored snapshot, alongside its project-specific
files. The Grand Pubah ensures the front door, architecture/agent context, templates, schemas, validation
assets and required dependencies are present; records the exact release/source/digest and adoption in the
project lock; and obtains repository-local validation evidence before declaring Kit setup complete.
No symlink, sibling checkout or implicit chat context substitutes for the included Kit. Projects retain
their own source, data and runtime authority. If no approved release exists or validation fails, record
Kit setup as pending/blocked with the exact reason; never claim installation or invent a release.
This standing bootstrap requirement is not an instruction to create a project or distribute a Kit now.

## ZIP-only ceremony and root discovery

Mark requires the prepared full-source ZIP to be sufficient on its own. Its root README.md must identify
the exact queued assignment and link directly to the controlling ACTIVE-DESIGN.md, complete instructions,
required reading and return/acceptance criteria. No separate pasted brief or directory hunt is required.
Mark uploads the ZIP and may simply say “find it”; later discussion may refine the work.
Preparation checks root README discovery and all required local targets in a fresh extraction. Include
root PATCHER-SOURCE.json with exact source revision, body/attempt, file inventory and packaging overlays;
record ZIP SHA-256 outside the archive. Do not silently replace an existing project README: preserve its
human navigation and integrate the assignment entry, or declare the reviewed packaging overlay explicitly.
READY_FOR_MARK requires this actual verified package. An instructions-only draft is not a prepared ZIP.

## 14. Active Designs lifecycle package

Every vendored Kit carries an `active-designs/` context layer with the same lifecycle vocabulary as Talisman:
`current`, `completed`, `inactive-history`, `enduring-reference`, and `unresolved`. A current design has exactly
one concise `ACTIVE-DESIGN.md` front door naming status, authority, objective, evidence-backed implemented
baseline, remaining work/gates, dependencies and acceptance. Supporting evidence does not become current merely
because a current page links to it.

Completion is a preservation move, never deletion: move the front door and retained supporting evidence to the
consumer's completed-design archive, record old/new paths and digests, repair inbound links, and remove the current
index entry in the same reviewed change. Inactive/history uses a separately labelled archive and is never presented
as completed. Reactivation restores a front door with retained provenance and new authority evidence. Unknown
external status remains `unresolved` until the consumer obtains evidence; Kit validation must not guess.

### Front-door template

```markdown
# <design name> — active design
Status: <current state>
Owner/authority: <human owner and technical authority>

## Objective
## Implemented baseline and evidence
## Remaining work and gates
## Dependencies
## Acceptance
```

### Deterministic lifecycle validator specification

A future validator walks only the vendored snapshot and its manifest. It fails on: a current indexed folder without
exactly one front door; duplicate front doors for one declared design; a front door absent from the current index;
missing status/owner/baseline/gates; broken local links; a completed/history path presented as current; a migration
entry whose source/destination digest does not preserve bytes; or a lock/manifest path outside the vendored Kit.
It reports external evidence as `UNRESOLVED`, never as pass/fail by inference.

Required fixtures and expected outcomes:

| Fixture | Expected result |
| --- | --- |
| one indexed current folder + one complete front door | PASS |
| current folder missing front door | FAIL `MISSING_FRONT_DOOR` |
| two front doors for one declared design | FAIL `DUPLICATE_FRONT_DOOR` |
| front door not present in current index | FAIL `UNINDEXED_CURRENT_DESIGN` |
| missing owner, baseline, or gate field | FAIL corresponding required-field code |
| completed/history destination still indexed current | FAIL `HISTORY_PRESENTED_CURRENT` |
| moved bytes differ without an explicit content-change record | FAIL `MIGRATION_DIGEST_MISMATCH` |
| external project status unavailable | UNRESOLVED `EXTERNAL_EVIDENCE_MISSING` |

## 15. Self-contained architecture/context coverage

A distributable Kit is not only templates. Its manifest must physically inventory: architecture overview and service
boundaries; ownership and authority map; current design front doors; enduring contracts and decisions; agent setup
and repository navigation; validation routes and focused proof conventions; source/database materialization rules;
Patcher/full-ZIP exchange ceremony; version/lock/migration records; and implementation-friction feedback with its
resolution evidence. Consumers adapt project-specific names and authorities locally while retaining Kit provenance,
release identity and technical identities. Symlinks, sibling repositories and chat history do not satisfy coverage.

The owner-to-Atlas feedback loop is evidence preserving: record observed friction, responsible authority, actionable
finding, disposition, and resolution proof. A friction report alone neither rejects landed work nor authorizes an
unrelated change.
