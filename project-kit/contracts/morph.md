# Morph — universal articulated and spatial content

## Normative semantics; implementation coverage is separate

A Morph is the universal logical unit for articulated or spatial content: creature, prop, building,
terrain region, ocean, stream or other Place-scale content. It has stable identity and revision.
These semantics are not restricted by a current creature codec, database schema or editor widget.
Do not relabel an incompatible payload as native; unsupported semantics remain explicit adapter/codec work.

## Control Points, transforms and constraints

A Morph contains stable semantic Control Points, each defining a local coordinate frame with declared
units, axes, origin and orientation. A Point can exist independently; not all Points must be connected.
An optional primary parent relation forms an acyclic transform hierarchy (a forest when disconnected).
Roots resolve against the Morph instance frame. State how scale and transforms compose; traversal order
must be deterministic. Reject transform cycles and ambiguous parents rather than guessing a transform.

Additional restrictions and relationships belong in a separate general Constraint graph. Coupled behavior
may contain cycles without making the transform hierarchy cyclic. Pin each constraint's identity/type,
endpoints, parameters, priority, evaluator version and deterministic solve/order/convergence/failure policy.
Do not force every semantic relationship into a tree or silently accept an unsatisfied constraint.

## Geometry and nested content

Geometry hangs from Control Points in local space. It may be arbitrary mesh, surface, curve, volume,
procedural geometry or exact versioned geometry/material/texture asset references. A simple prop can
have one Point; a deformable surface can have a field/net of Points. Geometry is not limited to bones,
links or creature silhouettes. Pin asset identities/revisions/digests and procedural evaluator/seed/input
versions; a render cache is a projection, not the authoritative native definition.

Morphs can contain/reference Morph instances without flattening them. Each instance retains its own
addressable instance identity, definition ID/exact revision, local transform, parent attachment/Control
Point relationship and independently addressable behavior. Define copy/reference/clone intent explicitly.
Guard recursive expansion; reject infinite containment while allowing declared non-containment graph links.

## Stances: partial and composable

A Stance is a named arrangement or parameter state over any declared subset, subtree or region of a
Morph. It need not be a full-body pose. Identify scoped Point/constraint/geometry/procedural channels;
pin region membership or its deterministic selection rule. Unspecified channels retain the prior/default
resolved value. Composition declares baseline, order, precedence/priority, blend operator and weight per
channel, including transform space and rotation/interpolation convention. Reject ambiguous overlaps or
unsupported blends. Given the same baseline and inputs, composition yields the same state.

Complete definition/dependency closure does NOT require every Stance to restate every Point. Existing
Mapping/Resting/full-stance requirements in a legacy profile are codec limits, not universal semantics.

## Motions: deterministic control over time

A Motion controls Points, constraints, Stances, geometry parameters or procedural parameters over time.
It declares time domain, clocks, duration/loop rules, interpolation, events and deterministic evaluation
order. Motions can be collected, composed, sequenced and blended with explicit scope and conflict rules.
Pin evaluator, seeds, initial state and inputs; same inputs/time must resolve reproducibly within the
profile's declared numeric tolerance. Do not infer semantics by renaming an interpolation label.

A [Morph Controller/Behavior Plan](behavior.md) synchronizes whole-instance world movement along a
Place-bound Path with local Motion using shared phase/cadence/distance rules. Motion itself is not a route.
For walking, derive phase from accumulated travel distance and declared stride length; specify pause,
reverse, discontinuity/teleport and turning behavior. A shared clock is not permission to couple all
instances indiscriminately. See [six semantic examples](../examples/morph-language.md).

## Identity, domain views and editors

Edits keep the same Morph ID and use checked revision updates; NEW IDs are for deliberate new content
or clones with provenance. Place, Character, object, terrain and building are well-known typed domain
views/classifications over Morph content and links. Preserve domain authority, metadata and queries
without introducing incompatible geometry/motion dialects or assuming identical persistence tables.

Slice and Thinian/Rougish are editors/projections over this SAME language: Slice currently authors
articulated/object Morphs; Thinian is becoming a world builder for Place-scale/spatial compositions.
This direction does not certify either editor's complete implementation.

[Metamorph](metamorph.md) defines the assembled stage; [persistence](persistence.md) defines storage
invariants without prescribing a database layout. Keep default/core native definitions and importable
packages in the source tree with repeatable explicit materialization/readback. Offline preview is not
admission. The [catalog](../catalog/README.md) remains incomplete; no examples here are fabricated exports.

Character/campaign dispositions and goals are typed extensions over a persistent Morph instance. Its
dormant presence, map marker and detailed body are projections, not new identities. Base Morph has no
implicit social authority, hidden knowledge or unrestricted agency. See [campaign simulation](campaign-simulation.md).

Optional [physical declarations/proxies](physics.md) attach to stable Control Points while remaining
engine-neutral and distinct from render geometry. Not every Morph is a simulated physical body.

Morphs may bind freely authored [behavior executors](behavior-executors.md) for motion generators,
procedural geometry, materials, interactions and other declared behavior. Pin executor identity/version/
digest and typed configuration/events. A module is a separately admitted dependency, never ambient
execution authority or engine-private live state embedded in the canonical Morph.
