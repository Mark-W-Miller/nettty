# Moondance Project Kit 0.1.11

Portable architecture documentation, contracts and reference examples for physical inclusion in a
project. Start with [the Kit guide](MOONDANCE-PROJECT-KIT.md), [What's new](CHANGELOG.md), or the
[expandable contents](inspect/index.html). Follow [adoption](docs/ADOPTION.md) and
[migration from 0.1.10](docs/MIGRATION-0.1.11.md). Reading the Kit requires no installation; the optional collection script is described below.

## Known limitations

This is a **documentation release**, not a completed database-content or UI-runtime release. The
[all-Morph catalog remains incomplete](catalog/README.md): no database Morph export is included.
The exact Slice-derived object format and native-validated worked fixture are pending. No successful
Insect Wars import, readback, database-backed gameplay, shared importer screen or Control-Tick overlay is claimed. See [evidence and gaps](docs/DELIVERY-GATES.md).

Projects retain their own root guidance, ownership, implementation and acceptance requirements.
Talisman references describe their recorded source; they are not evidence of another project's behavior.
Keep local extensions separate and preserve earlier releases and dispatched archives.

## Release-11 DTDT, importer and theme contracts

Read [Shelf, Card, Box and View Bars](contracts/shelf.md), [DTDT UI inspection](contracts/dtd-ui.md),
[portable database packets and the canonical importer](contracts/database-importer.md), and
[the Theme contract](contracts/theme.md). Release 0.1.11 defines the shared words for Cards, Shelves,
Boxes, Arenas, Six-View Bars, tear-off tool bars and twist-open tree shelves; requires Control-Tick
inspection for compliant UI structure; defines database-compatible project packets before live database
connection; and formalizes Forest Green / Forest Blue as Talisman Forest theme variants. These are
contracts and friction tests, not proof of implementation in any consumer.

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

## Voice Lab package

Read [the Voice Lab contract](contracts/voice-lab.md) and [Voice Lab package guide](docs/VOICE-LAB-PACKAGE.md).
The shared decision is a minimal self-hosted voice sidecar behind Tassy or the Linux Moondance server:
Whisper/`whisper.cpp` for speech-to-text, Kokoro-82M as the first text-to-speech family, Piper only as a
recorded alternate, and explicit recording artifacts that are not training or cloning consent. No engine,
model, voice, recording, server, browser controller or deployment proof is included in this documentation
release.

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
over the same language. Current narrower codecs do not redefine it. SQLite remains the local/Tassy
default; database layout is non-normative and PostgreSQL is a measured scale-up option behind the same
services. No consumer upgrade or new implementation proof is implied by this release.

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

[VERSION-0.1.11.md](VERSION-0.1.11.md) makes this Kit version visible in a directory listing.

## Shared voice-authoring refinements

[Lee's Voice Lab findings](docs/LEE-VOICE-LAB-FINDINGS.md) and [voice authoring](contracts/voice-authoring.md)
are included in 0.1.10. Source reports remain distinct from shared runtime acceptance.
