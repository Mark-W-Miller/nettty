# Moondance Project Kit 0.1.13

## Purpose and authority

The Kit records architecture, contracts, requirements and examples. Each project owns implementation,
source/data authority and acceptance. It is not an installer or runtime dependency. Grand Pubah includes
the Kit in projects; Atlas curates architecture and owner feedback; Merlin packages shared documentation.
Local root guidance wins over historical Talisman references. Preserve local identities and working paths.

## Read first

- [What's new](CHANGELOG.md), [adoption](docs/ADOPTION.md), [migration](docs/MIGRATION-0.1.13.md).
- [Place](contracts/place.md): actual numeric height and aligned texture; optional refining layers.
- [Objects](contracts/objects.md): actual Slice native forms required; exact example/codec binding pending.
- [Native authoring/admission](contracts/native-content.md): derive locally, validate, admit, read back, activate.
- [Morph snapshot requirements](contracts/morph-catalog.md) and [incomplete catalog](catalog/README.md).
- [Sanitized Tassy contract](docs/TASSY-ADMISSION-REFERENCE.md): owner-supplied single-item save reference.
- [Field-trial lessons](docs/FIELD-TRIAL-LESSONS.md) and [evidence/gaps](docs/DELIVERY-GATES.md).
- [Voice Lab package](contracts/voice-lab.md): self-hosted STT/TTS/recording package shape; no runtime claim.
- [Shelf/Card UI](contracts/shelf.md), [DTDT inspection](contracts/dtd-ui.md), [database importer](contracts/database-importer.md) and [theme](contracts/theme.md): release-11 design contracts and friction tests.
- [3D terrain navigation](contracts/terrain-navigation.md): shared pointer mapping, orbit focus, cursor zoom,
  progressive nested-Place detail, seam ownership and continuous scale transitions.
- [Stable authoring state](contracts/authoring-state.md): checked asynchronous identity, stable loading,
  displayed-source edits and evidence through the real consumer path.
- [Persistence profiles](contracts/persistence.md), [typed AI context](contracts/context.md) and
  [next-candidate migration](docs/READ-ONLY-DELIVERY-MIGRATION.md): local database workshops followed by
  read-only Tassy rehearsal and Moonbeam delivery with browser-owned edits. These are unreleased contracts.
- [Browser workspace](contracts/browser-workspace.md), [compiled Morph packages](contracts/compiled-morph-packages.md),
  [radial menu](contracts/press-hold-radial-menu.md) and [Place exchange](contracts/place-package-exchange.md):
  the additional 0.1.13 contracts and their explicit acceptance boundaries.
- [Component publication](contracts/component-publication.md): exact Tassy selection closure, guarded
  Moonbeam owner-adapter execution and remote/public receipts; current owner binding remains pending.

## Contents

The [seven-language profile](reference/talisman/design/MOONDANCE-PROJECT-PROFILE.md) covers Shelf,
Theme, Morph, Context, services, identity/provenance and Journey/verification; Materialization spans them.
The [architecture overview](reference/talisman/development-atlas/Development-Architecture-Atlas.md)
and companions give source-specific depth. Portable summaries live in contracts/. templates/ and
schemas/ contain proposed document shapes, not validated live objects or universal defaults.
lifecycle/ provides current-design, Patcher handoff and verification-plan forms. themes/ contains semantic
examples requiring local bindings and non-color accessibility cues. reference/ retains labelled historical
source documentation. inspect/ is a local contents page; manifest.json inventories exact packaged bytes.

Release 0.1.13 adds read-only delivery/local-workshop profiles, typed AI context, browser
Archive/Reinflate, compiled Morph packages, radial menus, Place exchange and a guarded component-publication
adapter boundary. Release 0.1.12 adds the shared 3D terrain-navigation and nested-Place continuity contract derived from
Rougish owner feedback plus Slice's shared authoring, admission, representation and evidence findings.
Release 0.1.11 adds explicit DTDT UI structure language, including Cards, Shelves, Boxes, Arenas,
Six-View Bars, tear-off tool bars, twist-open tree shelves and the required Control-Tick debug overlay.
It also adds a portable database-packet/importer contract for projects that create Morphs, Places and
other database-facing content before or after they can reach the canonical database.

Voice Lab adds a package contract, schema, template and example for speech capture, Whisper/`whisper.cpp` STT, Kokoro-first TTS, optional recorded Piper alternate, and explicit recording artifacts. Engines, models, voices and deployments remain consumer-owned receipts.

## Work and acceptance

Keep genuinely current work behind an ACTIVE-DESIGN.md front door and preserve history separately.
A Patcher ZIP includes complete context and a root README pointing to assignment, scope and expected
return. Preserve dispatched archives; send later guidance as an explicit supplement or next attempt.
Record receipt, review, acceptance and publication separately. Log observed friction, local decisions,
responsible owners and evidence for Atlas; local decisions do not alter another owner's contract.

0.1.12 adds 3D navigation, focus, progressive Place detail and transition rules. 0.1.11 adds DTDT shelf/view, importer and theme contracts. 0.1.8 adds the Voice Lab package shape and preserves 0.1.7's freely authored versioned behavior
executors, the 0.1.6 AI runtime correction, console, universal Morph and future physics contracts.
Packaging checks cover inventory, links and exact bytes. The component-publication preview/receipt
validator is executable, but the machine-bound Tassy/Moonbeam owner adapter is not yet delivered. Voice
servers, models, recordings and browser controllers remain consumer-owned. Product, browser and
physical-device acceptance remain separate.

## Portable return and update cycle

Read the [required cycle](contracts/morph-return-cycle.md) and [NEW/UPDATE evidence](docs/NEW-UPDATE-PROFILE.md).
Dwarf War, Insect Wars and Rougish adopt this Kit before further software work; Slice is the fourth
consumer and collector/visual proving surface. All four require supported hot deployment and database
connection. Full offline native compendia, plural Morph-only returns and automatic same-ID guarded
updates are requirements, not claimed implemented features.

## Standard local guidance and upstream handoff

Maintain `project-kit-local/` and its index alongside the immutable Kit. Each project/Patcher pass
returns updated local guidance and its change list to the project owner for handoff to Merlin.
Merlin curates general lessons into a versioned shared release; project-only decisions stay local.
Read [the procedure](contracts/local-supplements.md), [curation decisions](docs/LOCAL-FINDINGS-0.1.3.md)
and [incorporated owner evidence](docs/OWNER-CAPABILITY-REPORTS.md).

## Repository delivery during active development

MPK delivery must not require an owner to stop coding or clean the working tree. Installations replace
the shared `project-kit/` and release pin while preserving `project-kit-local/`. Publication groups
worktrees by Git repository, commits the exact release once on the repository's `origin` default branch,
and leaves unrelated working and staged files untouched. Local MPK findings are collected separately;
they are never swept into a delivery commit. See [the publishing procedure](docs/PUBLISHING-INSTALLED-KITS.md).

## Shared Talisman Console

Read [the bidirectional console contract](contracts/talisman-console.md).
All four projects use the same explicit upload, fetch, domain organization and guarded identity rules.
The console is independently accessible; local hosting is not the Talisman connection.
This is a documentation requirement, not proof of live integration.

## One Morph language, stage composition and storage

[Morph](contracts/morph.md) is the universal unit for articulated and spatial content;
[Metamorph](contracts/metamorph.md) is the assembled stage/world graph. Read the
[six semantic examples](examples/morph-language.md), [storage guidance](contracts/persistence.md) and
[requirements versus evidence](docs/MORPH-CAPABILITY-STATUS.md). Slice and Thinian/Rougish are editors
over the same language. Current narrower codecs do not redefine it. SQLite remains the canonical local
authoring default; Tassy and Moonbeam delivery profiles use read-only baseline copies. Database layout is
non-normative and PostgreSQL is a measured scale-up option behind the same services. No consumer upgrade
or new implementation proof is implied by this release.

[Behavior layers](contracts/behavior.md) distinguish local Motion, Place-bound Path, admitted Intention
and game-system authority; [examples](examples/behavior-plans.md) show their controller composition.
The [browser-first Tassy host](docs/BROWSER-HOST-ARCHITECTURE.md) is a candidate product simplification.

## Lazy campaign worlds

[Campaign Intentions](contracts/campaign-simulation.md) preserve dormant NPC goals and resolve a bounded
set only on GM-authorized logical time advancement. Read [Derek’s example](examples/derek-campaign-heartbeat.md).
[AI runtime decisions](contracts/ai-runtime-decisions.md) are built into bounded, budgeted heartbeat slots:
minimal typed snapshots, one direct decision call, validation and durable accepted state. Deterministic
Morph/Place/game machinery executes between calls; rendering and physics never call AI. Measured cache
usage and hard tick/daily spending limits are requirements, not completed runtime proof.

[Future physics](contracts/physics.md) is an engine-neutral Morph/Place resolver seam. Place world
coordinates, collision/navigation surfaces, active region and fixed-step clock must be hardened first.
No engine choice or implemented physics is claimed.

## Freely authored executable behavior

Morphs and Metamorphs may bind [versioned behavior executors](contracts/behavior-executors.md), primarily
JavaScript for browser/Tassy hosts. Canonical data pins executor identity and typed parameters/events;
modules run only under host-approved capabilities, typed outputs and enforceable resource limits.
Read the [material-pulse example](examples/behavior-executor.md). Imported modules remain inert until
admitted; this release specifies the registry/sandbox/replay boundaries without claiming implementation.

## AI provider dialogue — 0.1.8

[AI provider dialogue](contracts/ai-provider-dialogue.md) collects the Rougish interaction pattern:
separate conversations, selected context, outbound inspection and reviewed acceptance. All provider
options are hard-coded by us for now; users choose only among the built-in choices. This addition is
documentation guidance; runtime implementation remains unverified.

## Collect project updates — release tooling

Use the [owner handoff and collector](docs/COLLECTING-PROJECT-UPDATES.md) to gather local MPK findings
from pinned owner projects into a review bundle. It checks policy gaps and preserves working-file hashes
without editing consumers or publishing. A stable command is available for later administrator UI wiring.

[Direct project installation](docs/INSTALLING-PROJECT-KIT.md) verifies the published tag and replaces
only the vendored Kit, preserving local supplements and recording delivery separately from runtime proof.

[VERSION-0.1.13.md](VERSION-0.1.13.md) makes this candidate version visible in a directory listing.
