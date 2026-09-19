# Future physics resolver — Morph/Place capability boundary

Status: post-0.1.4 doctrine, documented in 0.1.5. Required future adapter seam, not an engine selection,
implemented feature or acceptance result. First harden Place coordinates, surfaces/volumes, admitted
active regions and world clock. A physics engine cannot honestly bind to an unspecified world contract.

## Engine-neutral declarations

Morph may supply optional physical capabilities: stable bodies/body groups attached to Control Points,
collision proxies separate from render geometry, mass/inertia and material properties, static/kinematic/
dynamic mode, joints/limits, sensors/triggers and declared force/actuator inputs. Specify frames, units,
shape versions and physical parameters. These are not mandatory for every Morph; a renderable surface
does not imply soft-body or fluid capabilities. Preserve semantic IDs rather than engine object handles.

Place supplies exact world identity/revision, coordinate units/axes/handedness/origin, bounds, versioned
collision/navigation surfaces and volumes, gravity/other fields, environmental material regions, fixed
simulation clock/step policy and admitted active region. Declare relationships/differences between
navigation, rendering and collision geometry; they are not interchangeable evidence of one another.
Invalid or missing bindings are a capability gap, not a license to guess scale or gravity.

## Interchangeable resolver and authority

An adapter consumes exact Morph-instance physical state and an exact Place/world revision plus ordered
admitted inputs, then returns a BOUNDED PROPOSED next physical state, contacts/triggers, constraint
outcomes and diagnostics. Keep opaque engine IDs/serialized caches outside canonical Morph/Place
payloads; map them through disposable versioned adapter state. Restore from engine-neutral authority
or a labelled compatible checkpoint, never silently substitute an opaque engine dump as native source.

Authored Motion may animate kinematically. Path/Controller requests desired world movement; physics
resolves feasible displacement and contacts under the declared profile. Game systems decide authority
and gameplay consequences. Contact alone does not cause damage, permit entry, win combat or mutate
campaign truth. Sensor events are proposals/observations for admitted rules, not unrestricted commands.

An authoritative coordinator checks input/world/state revisions and admission before committing accepted
results. Reject stale or cancelled results; a late adapter response cannot move an actor after its plan
was cancelled or Place changed. Source Morph definitions are not rewritten every physical frame.

## Hybrid control without conflicting writers

Support declared profiles for kinematic presentation, dynamic simulation, ragdoll/secondary motion and
controlled blending. Give each transform/channel a single resolved owner at each step. Specify authored
baseline, physics contribution, blend operators/priority, transition timing and momentum/pose transfer.
Switching to ragdoll or back to control has explicit authority and state mapping; two independent writers
must not race over the same transform. Secondary detail cannot silently change gameplay collision state.

AI calls on physics or render ticks are forbidden. Material events can enqueue a later eligible
[budgeted runtime decision](ai-runtime-decisions.md); physics continues to execute admitted inputs.

## Step, replay and resource contract

Record fixed-step clock identity, step size/index, engine/version/platform and adapter/profile identity,
initial-state digest, exact world/input revisions, ordered inputs, seed/random state where applicable,
bounded substeps/solver budget, sleeping/waking policy, accepted outputs and diagnostics. Define overload,
non-convergence and missed-step behavior rather than unbounded catch-up. Cancellation/stale-world handling
must preserve the last accepted state and report whether any earlier steps committed.

Declare deterministic support and its scope/tolerance, or explicitly declare non-determinism. Do not
promise identical cross-platform replay from a seed alone. Retain checkpoints/output/contact traces
sufficient to diagnose or replay according to the supported profile; distinguish exact re-execution from
playback of recorded outcomes. Adapter interchangeability means the same authority contract, not a claim
that different engines produce identical dynamics.

## Materialization controls level of detail

Dormant campaign Morphs receive no frame simulation. Strategic actors use coarse campaign rules;
tactical admitted Place regions may receive physics; detailed bodies may enable secondary dynamics.
Transitions reconcile the SAME identity/state and commitments, with no double-applied travel, contacts
or effects. Sleeping physics bodies are an engine/runtime state, not the campaign's entire dormant model.
A newly visible actor does not retroactively acquire a continuously simulated physical history.

## Eventual examples and acceptance

- Walking: controller requests travel; ground collision resolves feasible movement, which drives gait
  phase through actual distance. Navigation/game authority still controls permitted movement.
- Chair: admit seating interaction, align to a versioned chair attachment/seat proxy, declare kinematic
  versus dynamic body ownership and cancellation/stand-up transition.
- Door/chest: native hinge/limit/proxy references and authored opening control interact through the
  adapter; game rules own lock permission and successful interaction outcomes.
- Projectile/contact: resolve trajectory/contact in the admitted active region; game rules determine
  hit/damage/ownership effects and record them separately from the contact event.
- Stream/ocean: a procedural Control Point surface may provide versioned collision or buoyancy inputs
  where supported. It does not promise fluid/soft-body simulation; that requires a separate capability.

Prove Place contract, units/frames, proxy/visual alignment, mode transitions, single transform ownership,
bounded stepping, replay declaration, stale/cancel rejection and game-rule acceptance before claiming
physics integration. No implementation, engine evaluation, benchmark or simulation is performed here.
