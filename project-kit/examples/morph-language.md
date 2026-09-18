# Morph language examples — semantic designs, not validated payloads

These six examples illustrate the shared language. IDs/revision notation are illustrative labels,
not invented accepted catalog records or wire schemas. Owning codecs/evaluators must implement and
validate them before native admission can be claimed. Each example pins units, axes and exact assets.

| Example | Control Points and geometry | State, composition and observable result |
| --- | --- | --- |
| One-point prop | lantern/r1 has one semantic Point `body`; local mesh and versioned material attach to it | Partial Stance `lit` sets an emissive parameter only; instance transform places the lantern without creating a second geometry dialect |
| Articulated creature | creature/r1 has a rooted body/limb transform hierarchy; geometry hangs from joints | `look-left` scopes head channels while `crouch` scopes legs. Declared layer order/blend resolves overlaps; a separate coupled-foot constraint graph preserves ground contact |
| Deformable ocean/stream | water/r1 has a Point net whose independent roots are in the instance frame; local surface references the net | Wave Motion controls surface/Point parameters from pinned time/seed; flow constraints and bank references are graph links, not fabricated skeleton parents. Specify boundary and solver policy |
| Building with nested Morphs/layers | building/r1 references door/r2 and lantern/r1 instances attached to named doorway/socket Points; floor/wall geometry attaches locally | Structural, appearance and interaction layers have explicit ordering. Door opening and lantern behavior remain independently addressable, with exact referenced revisions |
| Place/stage Metamorph | harbor-stage/r1 contains terrain/r3, water/r1, building/r1 and creature/r1 instances with transforms/attachments | Typed Place metadata supplies bounds/navigation/search; stage clock and declared interaction rules synchronize content. No flattening or inheritance defines the stage |
| Travel-synchronized walk | walking creature has instance path translation/orientation plus internal leg Motion | One phase rule drives body travel and gait; stopping travel freezes gait unless an explicit idle transition applies |

## Worked composition choices

For the creature, start from declared rest defaults. Apply crouch to the listed hip/knee channels,
then look-left to the neck/head. If a later aim Stance overlaps the neck, its declared priority and
rotation blend operator determine the result; missing channels retain the already resolved values.
A complete native Morph contains the definitions/dependencies, not necessarily a full pose in each Stance.

For the water net, one seeded wave evaluator samples time and Point coordinates. Constraints link bank
Points to declared bounds and shared crest parameters. A cycle in those constraints requires a pinned
solver/order/convergence policy, while the transform forest stays acyclic. Failure is visible; no solver
or cycle handling is assumed from a list of control points alone.

For the walk, let stride length L = 1 metre and signed travel increment Δs follow the chosen path.
Advance phase by Δφ = Δs/L cycles, modulo one; orient the instance from the declared path tangent.
At 2 metres/second cadence is 2 cycles/second; at zero speed phase holds. This example declares negative
travel to reverse phase, and teleport to reposition without advancing phase. Blends to idle/turn motions
have explicit durations and channel precedence. Given the same initial phase, time/path inputs and
profile, both editor projections resolve the same stance samples and whole-instance transform.

## Required eventual proof

Native codec round-trip, preserved stable instance/Point IDs, deterministic evaluation and constraints,
partial Stance composition, exact asset/edge resolution, nested-instance addressing, checked update and
materialization/readback must be demonstrated. Slice and Thinian/Rougish should load the same versioned
examples and preserve their meaning through edits. No such execution is claimed by this document.

Whole-instance travel in these examples is controller execution of a Place-bound Path, not skeletal
Motion. See [admitted intentions and behavior examples](behavior-plans.md) for tired walking, following
and search/invitation outcomes owned by the game system.
