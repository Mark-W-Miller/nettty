# Intentions as lazy campaign-world simulation

## Typed dormant state and one persistent identity

A Character/NPC Morph instance may carry enduring typed dispositions, goals and intention templates.
These are Character/campaign extensions over Morph identity/control, not unrestricted abilities of the
base Morph. An actor can remain dormant indefinitely: no continuous process or 24/7 NPC loop is required.

Preserve exact actor/instance identity, instance-state revision, Morph definition revision, last resolved
campaign time, current Place/presence, relationships, dispositions/goals/intentions, unresolved commitments
and next eligibility/due conditions. Pin typed capability/rule profiles and provenance. Dormant state
records unresolved work honestly; it does not imply that every moment since its timestamp was simulated.

One persistent instance may be an abstract semantic presence or “ideal” located in a city, a campaign-map
marker, a detailed 3D body sitting in a chair, or an active Path/Motion projection. These materializations
share stable identity, revision and capability state. Instantiating a detailed view does not clone the
NPC, grant knowledge or produce a second authority. A stale view must refresh or report its pinned older
state. A sitting pose is local articulation; city presence is campaign/Place state, not a skeletal route.

## GM-authorized logical time

The campaign/adventure owns a logical clock. A GM explicitly authorizes an Advance time / heartbeat
operation for a stated time range, actor-selection scope, rules and resource budget. This is a domain
operation, NOT an instruction to schedule a background automation or continuously run real-world time.
A deliberate recurring policy, if any, needs its own authority; opening a scene does not authorize time.

The GM programs a world whose rule-driven results may surprise its author. Surprise comes from admitted
state, goals, interactions, validated AI proposals and declared randomness, never invented authority or hidden facts.

## Bounded heartbeat and atomic world transition

1. Pin campaign start/end time, expected world revision, input actor/Place/navigation/relationship
   revisions, rules/planner/evaluator versions, authority and selection policy. Identify actors eligible
   by next-due conditions and admitted relevance; order/tie-break deterministically.
2. Select a bounded eligible set by elapsed world time, next-due state, relevance, proximity, narrative
   importance and account limits. Most actors sleep. Bound actors, events, replanning, calls, tokens,
   concurrency, latency and monetary spend; reserve hard tick/daily budgets before dispatch. Newly involved
   actors require admission and remaining budget; otherwise defer with an explicit next-due reason.
3. At each admitted AI decision slot, send the smallest sufficient typed packet in one direct singular
   API call under the [AI runtime contract](ai-runtime-decisions.md). Validate the structured proposal
   before rule/planner evaluation. Resolve admitted plans/Paths/actions through deterministic game rules,
   contests and recorded randomness. Social outcomes, permissions and hidden knowledge remain with the
   game system. Retain call usage/cost even if the proposal or eventual world commit is rejected.
4. Validate all effect preconditions and checked revisions, then commit ONE atomic world update containing
   accepted actor/relationship/state effects, logical-clock progress, next-due/unresolved state, audit
   receipt and readable summary. Staged immutable bytes are not visible world changes until commit.
   A service with only independent per-item commits needs an actual transactional world coordinator;
   it cannot claim this atomic heartbeat capability. No partial world effects on conflict/failure.
5. On stale inputs, reject the proposed commit and report conflicts; replan only under the admitted retry
   policy. Idempotent retry returns the same committed outcome rather than rolling the dice again.

A long requested interval may use explicitly bounded atomic steps. Each step records its committed
range/frontier; remaining work/time is not marked resolved. If coarse-resolution policy advances the
campaign clock while actors remain dormant, retain each actor's last resolved time and due backlog.
Do not imply that unselected actors were processed or that a whole multi-step request was atomic.

## Interest-driven resolution

Far/dormant actors advance in coarse event steps when due; nearby/materialized actors may receive
detailed Paths, Motions and interaction resolution. Declare the selected resolution policy and changes
between levels. Preserve shared state/commitment consistency across transitions; do not double-apply
coarse outcomes when a detailed view appears. Record omitted detail and uncertainty. No claim that
skipped time was continuously or frame-by-frame simulated is permitted.

## Audit, replay and undo

Retain operation ID, campaign time range/frontier, actor set and selection/deferral reasons, exact input
revisions, authority, rules/evaluator versions, random algorithm/version and seed/draw stream (including
draw ordering), proposals, accepted/rejected effects, failures/conflicts, next-due state and result revision.
The readable summary links to that evidence and distinguishes committed effects from hypothetical plans.

Replay is explicit: inject retained AI proposals and random draws into the pinned deterministic
machinery without new AI calls or world writes. Do not claim that regenerating a model answer is
deterministic. Reapplying the same operation uses idempotency and must not duplicate effects or spend. Undo policy must specify
supported scope and dependent later events. Use checked compensating updates or an explicit campaign
branch/restore policy where supported; never silently rewrite retained history or erase later outcomes.
If undo is unsupported, say so before accepting an operation that requires it. No public historical API
is presumed from retained storage; the coordinator must have a real supported audit/read binding.

## AI decisions and deterministic execution between calls

Moondance hardens AI into bounded runtime decision points. The scheduler prepares a typed character
snapshot; a singular API call proposes an Intention/Path/action; authoritative validation and atomic
commit establish durable accepted state. Read the [AI decision contract](ai-runtime-decisions.md) for
stable-prefix/dynamic-tail caching, measured usage, hard spending limits and failure handling.

Deterministic Morph/Place/game machinery executes accepted state between calls, including while offline.
The next AI-dependent decision may wait for connectivity, eligibility and budget. Rendering, physics,
path following and replay must not trigger inference. Optional natural-language authoring is a separate
front end; it does not define the scope of runtime AI. Unsupported constructs reject or await authoring,
never silently expand into unbounded model agency.

See [behavior layers](behavior.md), [stage composition](metamorph.md), [persistence](persistence.md) and
[Derek's two-week example](../examples/derek-campaign-heartbeat.md). This contract is required behavior,
not proof that current editors, services or campaign runtime implement it.

[Physical resolution](physics.md) follows materialization: no frame simulation for dormant actors, coarse
rules for strategic activity, optional bounded physics in admitted tactical regions. This is a future
adapter seam, not a reason to run dormant NPCs continuously or imply skipped frames were simulated.
