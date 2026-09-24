# Motion, Path, Intention and authoritative behavior

## Four layers

| Layer | Meaning and authority |
| --- | --- |
| Motion | Local time-varying articulation/parameter change within a Morph: walk cycle, breathing, looking down, tired posture. It is not a world route. |
| Path | Place-bound world-space route/evolving trajectory for one exact Morph instance, pinned to Place/navigation revision and coordinate system; waypoints, spline or navigation result are representations, not skeletal Motions. |
| Intention/Directive | Semantic goal which may continually plan/replan a Path and compose controls. Its expression does not itself admit or authorize it. |
| Game system | Owns permissions, hidden knowledge, target selection/admission, interaction rules, contests, battle/social outcomes and authoritative state mutations. |

A runtime Morph Controller/Behavior Plan translates an ADMITTED Intention into Place navigation and
composable local Morph controls. Pin the subject definition/instance and Place context. Keep runtime
walking/following state separate from the reusable canonical definition; do not revise that definition
because one instance is walking toward a target. The controller uses admitted world inputs and cannot
invent knowledge, choose an unauthorized target or decide game outcomes outside the rule authority.

## Persistence-neutral information contract (not an implemented wire schema)

| Record | Required information |
| --- | --- |
| Motion reference | Definition ID/revision, local channel scope, clock/phase mapping, blend/precedence/evaluator versions |
| Path | Identity/revision or explicit transient handle; subject instance; Place/navigation revision; units/axes/origin; waypoint/spline/trajectory representation; planner/version; permitted traversal; progress and invalidation/replan conditions |
| Intention/Directive | Identity/revision or transient handle; subject; exact target or admitted query; Place scope; preconditions; policy/authority and admission result; termination conditions; replanning rules; failure/cancel outcomes |
| Behavior Plan / Controller state | Intention and instance refs; pinned context; path ref/state; composed controls and priorities; shared phase/clock; input/event ordering; progress; lifecycle/outcome and checkpoint provenance |
| Typed interaction | Relationship/directive type and version; exact participant IDs/instances and roles; authority; admitted target bindings; rule/result refs; lifecycle and cancellation |

Each authored or saved record identifies owner, provenance, lifecycle, revision and dependency pins.
A Path/Intention may be reusable authored behavior or transient runtime state: declare which. Reusable
behavior with query parameters binds concrete instances and permitted results at admission time; record
that binding. A transient handle is not a new canonical Morph ID. Saved plans cannot smuggle an old
permission into a new session: restoration rechecks authority, world revisions and preconditions.
No database tables, JSON field spelling or generic live endpoint is mandated by this information model.

## Evaluation and invalidation

Declare states such as proposed, admitted, planning, executing, replanning, succeeded, failed and
cancelled, with explicit allowed transitions and responsible authority. Choose deterministic planner,
controller/evaluator versions, input ordering, clocks and seeds. The same admitted state/input stream
must resolve reproducibly within declared numeric tolerances; changing live observations is new input.

A changed Place/navigation revision, blocked path or moved target triggers the declared replan policy;
never silently reinterpret old coordinates. Preserve the last safe state while resolving invalidation.
Actual traveled distance, rather than requested speed alone, drives walk cadence when blocked or paused.
Cancellation stops the directive's owned controls/relationships and reports already committed outcomes;
it is not an implicit rollback of game state. Replan budgets, unreachable targets and lost authority have
explicit failure outcomes, not endless invisible retries or invented success.

An authoring surface keeps one source of editable Motion state while allowing named selections,
independent rate drafts and per-Point contributor blending. Arrival/display order does not erase those
independent rates. Previewing a Motion is distinct from adding it to a workspace or saving it; display
speed is not automatically canonical content.

Resolved game-rule receipts own damage, defeat and other outcomes. Animation follows accepted results.
Refreshing an actor may retain deliberate queue/reaction fields, but must not copy an obsolete Path or
otherwise preserve movement that conflicts with the new authoritative state.

## Multi-actor intentions and knowledge boundaries

Interactions are typed directives/relationships with exact participant identities and authority.
Following tracks the admitted target and replans from observations the game permits. Searching uses
admitted criteria and visible/permitted information; it must not expose hidden individuals or infer
private traits as established facts. Natural-language phrasing is input to interpretation and admission,
not authority for reads, writes, social compliance or gameplay decisions.

An approach/invitation may request a social interaction. The game system decides its result through its
authorized rules/contest/participant decisions. Only an accepted outcome can create the corresponding
follower relationship. Never assume another actor follows merely because the speaker asked.
See [worked examples](../examples/behavior-plans.md), [Morph](morph.md), [Metamorph](metamorph.md) and
[persistence invariants](persistence.md). All are requirements; full controller implementation is unproven.

## Campaign use of Intentions

Intentions also drive [lazy campaign simulation](campaign-simulation.md): typed dispositions and goals
remain dormant until GM-authorized logical time advancement. A bounded heartbeat resolves admitted
actors and commits audited atomic world changes; it is not a perpetual NPC process.
[AI is built into bounded runtime decision slots](ai-runtime-decisions.md): one direct call proposes
structured Intention/Path/action state, authoritative rules validate it, and accepted state is durable.
Deterministic controllers execute between calls. AI-dependent replanning returns to the budgeted scheduler;
path following, render/physics ticks and replay never call AI. Optional language authoring is separate.

[Future physics](physics.md) resolves feasible movement/contact for admitted controller requests; local
Motion may remain kinematic. Only game rules authorize resulting damage, interactions and world changes.

## Extensible executable behavior

[Versioned executors](behavior-executors.md) may implement controller policies, generators and
interactions. JavaScript is the primary browser/Tassy language. Canonical bindings carry typed parameters
and events plus exact executor identity; the host owns capability grants, lifecycle/order, validation,
resource limits and durable commit. Deterministic/replay declarations and imported-code isolation are
required. Freely authoring a policy does not bypass AI budgets, game authority or native codec support.
