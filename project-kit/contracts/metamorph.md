# Metamorph — assembled stage/world

A Metamorph is a stage/world composition: a collection/graph of exact versioned Morph instances plus
transforms, attachments, interaction/rule relationships, clocks and synchronization. It is the assembled
stage the user sees. Morphs interact through declared rules. Metamorph is neither inheritance nor one
giant flattened Morph; existing products named MetaMorph may not yet implement this full semantic model.

Give the composition stable identity/revision and each instance a stable address. Preserve definition
ID/revision, instance-local transform and Control Point attachment. Keep transform containment acyclic,
separate from typed interaction/constraint links. Specify deterministic ordering, clock/phase domains,
rule evaluator/version, initial state and external input sequence. Detect unresolved references and
unbounded recursive expansion; never resolve an unspecified revision as whatever happens to be latest.

Allow independently addressable nested Morph behavior and layered composition. Document layer purpose,
ordering/masks/blends and visibility separately from identity and containment. Changing visibility does
not delete content. A Place can be held/rendered through this Morph composition while Place metadata,
spatial queries and gameplay ownership remain typed projections. Transient simulation state is not a
new immutable source revision every frame; checkpoints record the necessary state and pinned inputs.

Stage changes use checked atomic publication of a coherent selected composition. Immutable dependency
bytes may be staged in advance. Do not advertise a half-resolved stage as activated. Existing per-item
save operations do not by themselves implement atomic stage activation or a general constraint solver.
See [Morph semantics](morph.md), [storage invariants](persistence.md), [examples](../examples/morph-language.md)
and [current limits](../docs/MORPH-CAPABILITY-STATUS.md).

[Intentions and Behavior Plans](behavior.md) execute only under game-system admission. Stage interaction
links do not transfer permissions or ownership of battle/social outcomes to Morph execution. World Paths
remain Place-bound; local Motions articulate instances. Persist runtime checkpoints separately from
canonical definitions, retaining exact references and restoration/admission checks.

A stage may materialize dormant campaign actors as map markers or detailed bodies while preserving
the same identity/revision/capabilities. [Campaign heartbeats](campaign-simulation.md) own authorized
logical-time world transitions; rendering or opening a Metamorph does not itself advance the campaign.

Metamorphs may bind [authored executors](behavior-executors.md) to stage behavior and synchronization.
Their exact scope, typed inputs/outputs, lifecycle and order are part of the admitted composition; stage
bindings do not override per-instance/channel ownership, game authority or sandbox capability limits.
