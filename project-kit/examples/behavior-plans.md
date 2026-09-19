# Behavior examples — design scenarios, not executed native fixtures

## Walk to the well while tired and looking down

1. Interpret a proposed directive with exact subject instance and Morph revision, admitted Place and
   navigation revision, destination query/identity, authority, preconditions and completion policy.
2. The game system admits the operation and resolves the well from permitted Place knowledge. Ambiguous
   or inaccessible targets remain unresolved; the controller must not invent a destination.
3. Plan a Place-space Path with the declared coordinate system and navigation rules. Replan on allowed
   obstacle/target/context changes; report unreachable or invalidated context explicitly.
4. Move the whole instance along the Path using the controller. Integrate actual traveled distance to
   drive internal walk phase/cadence. Local Motion is the gait, not the route.
5. Compose tired posture and look-down controls over their declared partial scopes using explicit blend
   priority/weights. Apply any foot/ground constraints separately from transform parenting. At zero
   travel, pause gait or transition to the declared idle; do not continue pretending the actor advanced.
6. Arrival within the admitted tolerance succeeds; loss of authority, failure or cancellation has an
   explicit outcome. The reusable creature definition stays unchanged throughout.

## Follow a particular individual

Admit the exact target instance and allowed observations. The controller replans to maintain the
rule-defined follow distance as that target moves. Losing knowledge/access follows the declared wait,
search-or-fail policy; it does not grant omniscient tracking. The target's own controller remains
independent. A saved following directive restores only after permission and Place revision checks.

## Search, approach and invite someone to follow

For a phrase such as “find a girl and see if she will follow,” first admit the search criteria and
scope under the game system's knowledge/privacy rules. Do not infer hidden attributes or select from
unauthorized records. Bind a permitted result to an exact target identity, then separately admit an
approach Path and typed invitation interaction. The game system/target decision determines acceptance,
refusal or another outcome. Create a follower relationship only when that outcome authorizes it;
otherwise report refusal/failure without manufacturing compliance or state changes.

## Authored behavior versus checkpoint

A reusable “seek water” behavior can have its own identity/revision, owner/provenance and parameterized
Place/target rules. Each admission binds an instance and actual permitted Place revision. A runtime
checkpoint records current Path progress, controller phase, input state and directive lifecycle; it is
not a mutation of the shared authored behavior or Morph definition. Cancellation/restore and relationship
outcomes remain explicit. These examples need actual planner/codec/controller and game-rule proof.
