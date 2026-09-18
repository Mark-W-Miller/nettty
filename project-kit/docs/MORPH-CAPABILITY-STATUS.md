# Morph semantic requirements versus evidence

The universal Morph/Metamorph language is authoritative design direction from Mark, recorded 2026-09-17.
It extends the known narrower creature/native profiles; it does not retroactively claim their codecs,
editors or receivers support the full model. Historical references retain their original scope.

| Area | Required semantics | Current evidence / remaining proof |
| --- | --- | --- |
| Control Points / geometry | Stable local frames, optional acyclic forest, arbitrary attached geometry and nets | Complete cross-editor native codec/evaluator coverage unproven |
| Constraint graph | Typed coupled relationships separate from transform hierarchy; deterministic solving | Solver/edge profiles and failure behavior need implementation proof |
| Stances/Motions | Partial composable scope, deterministic blends/time, travel synchronization | Earlier full-stance/two-keyframe/linear-preview profile limits are not universal language constraints |
| Composition | Nested addressable Morph instances and stage/world Metamorph graphs | Existing products named MetaMorph/ASL capability packages do not prove this full stage model |
| Domain projections | One language; Place/Character/object metadata and queries preserve authority | Place/media adapters and Slice/Rougish world/editor parity remain explicit work |
| Checked persistence | Stable revisions/edges, exact native round-trip, atomic checked update units/history | Source-inspected per-item NEW/UPDATE is narrower than stage/set activation and public history APIs |
| Materialization | Core/default native source packages, repeatable checked import and readback | Full authoritative catalog, native worked fixtures and general intake remain incomplete |
| Console | Preserved shared bidirectional review/transfer/local activation contract | No new live connection, admission or four-consumer acceptance was executed |
| Storage | SQLite default; measured triggers for PostgreSQL behind same services | Hybrid schema, indexes and performance require owner implementation/measurement |

Reject unsupported semantics explicitly; do not drop a constraint, force a partial Stance into an
incompatible interpretation, flatten instances or disguise a Place as a creature to pass a legacy codec.
A supported migration/adapter must preserve meaning and record source/output identities and losses.
Documentation/schema examples are not native fixtures. Consumer adoption requires deliberate receipts;
no consumer source, database, deployment or installed Kit version changes in this release assembly.

## Additional runtime/product directions

Motion/Path/Intention/game-system separation and the runtime controller are specified in
[behavior](../contracts/behavior.md); executable planner, admission and multi-actor result proof remain
unproven. The [browser-first Java host direction](BROWSER-HOST-ARCHITECTURE.md) is a candidate, not a
claim of removed Java UI. Inventory, replacement, any data migration and browser acceptance are pending.

## Lazy campaign simulation and AI decisions — 0.1.6 correction

Typed dormant intentions, GM logical time, bounded event resolution, atomic world commits, identity-
preserving projections and bounded AI decision slots are specified in [campaign simulation](../contracts/campaign-simulation.md)
and [runtime AI](../contracts/ai-runtime-decisions.md). One direct call per admitted decision produces a
validated proposal; deterministic machinery executes accepted state between calls. Prompt-cache reuse,
write charges, cost per heartbeat and hard account/world daily/tick limits require measured evidence.
No campaign interpreter, AI API call, cache/cost measurement, scheduler, deterministic replay/undo,
transaction coordinator or Derek scenario
was executed or demonstrated by this documentation release. Existing per-item APIs are insufficient
evidence of atomic multi-actor updates. Consumer adoption is separately deliberate and unclaimed.

## Physics — explicit future seam

[Physics](../contracts/physics.md) defines optional proxies, a Place-driven world contract and a proposed-
result adapter. No engine is selected or implemented. Fixed-step/replay profiles, hybrid ownership,
contact/game-rule separation and soft-body/fluid capabilities require separate evidence. Harden Place
coordinates/surfaces/volumes/clock/active region first; procedural water is not fluid simulation proof.

## Authored executors — 0.1.7 requirement

[Behavior executors](../contracts/behavior-executors.md) permit freely authored modules with JavaScript
as the primary browser/Tassy language. Exact registry identities, typed inputs/outputs/state, lifecycle,
composition, provenance and replay declarations are requirements. Trusted built-in and imported tiers
need real host-approved capability isolation, logging, cancellation and time/memory enforcement.
The [pulse example](../examples/behavior-executor.md) is illustrative and non-installable. No registry,
sandbox, native binding codec, executor execution or consumer integration was implemented or tested.
