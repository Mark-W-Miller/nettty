# Architecture

Atlas structure is documentation authority. Consumers keep project application code local. Shared Kit files are vendored and version-pinned.

Morph is the universal articulated/spatial content unit; Metamorph is the assembled stage graph.
Domain projections retain their authority over shared semantics. See [Morph](morph.md),
[Metamorph](metamorph.md) and [persistence architecture](persistence.md).

AI is deliberately integrated into [bounded runtime decision points](ai-runtime-decisions.md).
The [campaign scheduler](campaign-simulation.md) admits eligible actors within hard spending limits,
validates singular-call proposals and commits durable state. Deterministic machinery executes accepted
state between calls; no inference occurs on render/physics ticks. This is design, not implementation proof.

Morphs and Metamorphs also support [freely authored executable modules](behavior-executors.md).
Their registry, typed binding, composition, provenance, replay and host capability contract allows new
JavaScript behavior without making unrestricted script execution part of the database contract.
