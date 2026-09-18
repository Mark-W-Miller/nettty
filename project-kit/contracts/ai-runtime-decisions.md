# AI at bounded runtime decision points

Moondance deliberately integrates AI into specific runtime decisions. The campaign heartbeat selects
eligible characters, obtains one bounded structured decision per selected decision slot, validates it,
and commits accepted Intention/Path/action state. Deterministic Morph, Place and game machinery executes
that state between calls. AI is part of the runtime architecture; it is not merely an optional authoring
front end. Natural-language authoring remains a separate optional way to propose typed content.

This is a required design, not implemented runtime or measured spending evidence. See the
[campaign coordinator](campaign-simulation.md), [behavior layers](behavior.md) and
[Derek scenario](../examples/derek-campaign-heartbeat.md).

## Scheduler, sleeping and decision slots

GM-authorized logical time advancement admits a bounded heartbeat. Its deterministic scheduler selects
eligible active characters using next-due state, elapsed world time, relevance, player proximity,
narrative importance and account spending limits. Pin policy/version, ordering and tie-breaks. Most
characters remain asleep; visible bodies and active rendering do not automatically require AI decisions.
Record selected/deferred actors, reasons, last resolved campaign time and next eligibility. Declare
starvation/aging policy; budget pressure must remain visible as backlog, not fictitious resolved history.

Use separate limits for actors, decision calls, tokens, latency, concurrent requests, deterministic work
and monetary spend. A logical world tick/heartbeat is distinct from a render frame or physics step.
**AI calls on render or physics ticks are forbidden.** Those loops execute admitted state; a material
event may enqueue a later scheduler decision, never invoke an inline model call. Frequency belongs to
the budgeted scheduler. Scene opening, camera movement and cache expiry do not authorize new calls.

## Smallest sufficient typed packet

Build each decision from an authorized snapshot, not the full database or an accumulating transcript.

| Packet part | Required content |
| --- | --- |
| Identity and guards | Operation/decision/attempt IDs; actor and instance revision; Morph definition, world and Place revisions; logical time/frontier |
| Current state | Relevant Morph capabilities, Place/presence and admitted nearby facts; existing Intention/Path progress, resources and commitments |
| Goals and events | Relevant dispositions/intention; recent material events since the last decision, with identity and ordering; bounded explicit omissions |
| Authority | Permitted typed actions/targets and preconditions; visible knowledge, rule/schema versions and decision scope |
| Budget | This decision's action horizon, input/output limits, deadline and allotted spending bound; no credentials or unrelated account information |

Read guards include all authoritative dependencies even when the model does not need their full payload.
Resolve permitted facts before dispatch. Data descriptions cannot override rules, invent capabilities,
request tools or grant hidden knowledge. If a minimal packet cannot safely support a decision, defer or
admit a separately budgeted richer slot; do not truncate required state silently.

## One direct call, then admission

1. Reserve budget, persist the exact packet/prefix identity and attempt record, then make **one direct
   singular API decision call** for the slot. The request has a fixed structured output schema, bounded
   output/deadline and no tools, recursive agents or conversational repair loop. Disable hidden SDK retries.
2. Receive a typed proposal: subject and guard references, permitted action/target, proposed Intention/
   Path request/action parameters, declared conditions and next-decision suggestion. A concise reason
   may be retained; opaque conversation state is never the executable program or source of authority.
3. Validate schema, limits, references, allowed actions, knowledge, permissions and game preconditions.
   A syntactically valid answer can still be rejected. Rules/planners validate feasibility and resolve
   contests; a model cannot declare another actor's compliance, a successful dice result or world truth.
4. Recheck revisions/authority at commit. Commit accepted typed state through the campaign's atomic
   world transition. Persist proposed versus accepted effects separately with provenance. Stale, refused,
   malformed, cancelled or late output has no authority to change the world.
5. On failure, sleep/defer or continue an already accepted safe plan under declared policy. Every fresh
   call needs an explicit admitted retry attempt and new budget reservation within the original limits.
   Never silently resample until a desired answer appears or use an unbounded model fallback.

Do not hold a database transaction open during network inference. Stage immutable proposals, then use
checked atomic commit. Paid attempts remain chargeable even if a world commit fails or is undone. Keep
the spending ledger durable independently of world rollback and link it to the world audit receipt.
After a timeout/crash with unknown provider completion, preserve the reservation and mark reconciliation
pending. Reuse a recorded completed proposal when valid; do not assume provider request idempotency.

## Stable prefix and dynamic tail

Keep a versioned rules/instructions/output-schema prefix byte-stable, including serialization and
ordering. Put actor state, recent events, time, request IDs and other varying fields in the dynamic tail.
Record the prefix digest and provider/model/cache configuration. Change the prefix when rules change;
never preserve stale rules merely to improve cache hits. Share prefixes only within authorized knowledge
and account boundaries. Do not pad small packets simply to meet a cache threshold.

Prompt caching reuses computation for matching prompt prefixes. It is **not retained conversation
context**, durable NPC memory, an omitted-input retrieval mechanism or a guarantee of identical decisions.
Each request supplies its complete required prefix and typed packet; authoritative memory is explicit
saved world state. No prior response/conversation chain is required by this decision contract.

Provider binding must verify supported caching modes, breakpoints, minimum length, retention and billing
for the selected model. Stable input alone does not prove a cache hit or a saving. Measure actual reuse
and cache writes; a miss, eviction or new prefix must fit the admitted budget. The OpenAI guide documents
model-dependent cache behavior and returned `cached_tokens`/`cache_write_tokens` usage. Treat these as
provider fields, not a universal wire schema. [Official prompt-caching documentation](https://developers.openai.com/api/docs/guides/prompt-caching)

## Usage, cost and hard spending limits

Retain per-call provider/request identity, requested and returned model/version, prefix/packet digests,
timestamps and latency, attempt/outcome, raw returned usage and normalized billing categories:

- Total input, ordinary uncached input, cache-write input, cached-input reads and output tokens, including
  billable reasoning/output categories where applicable. Distinguish reported zero, unavailable and
  not-applicable. Missing usage is never evidence of a free call.
- Price schedule/version/effective date, currency and relevant cache/service-tier rates; estimated,
  reserved and reconciled cost. Use actual returned categories and their documented billing semantics.
- For disjoint categories, cost is ordinary input times its rate, plus cache writes times their rate,
  plus cached reads times their rate, plus output times its rate. Convert rate units consistently.
  Subtract cached/write categories from total input only when the provider defines those as disjoint
  subsets. Do not count writes twice or silently equate all uncached input with ordinary-price input.

Aggregate by heartbeat/world tick, world and account/day: calls, attempts, selected/deferred actors,
all token categories, cache-hit fraction (cached reads / total input, or unavailable for zero/unknown
denominator), latency and total cost. Include failed, rejected and stale attempts. Distinguish provider
usage-derived cost from later billing reconciliation. Record unknown coverage instead of inventing totals.

Enforce **hard per-world-tick and daily world/account monetary limits before dispatch**. Define the daily
window/time zone and charge-attribution policy across midnight. All worlds sharing an account use one
atomic spending coordinator: reconciled spend plus outstanding reservations plus the candidate worst-case
reservation must fit every applicable limit. A smaller per-call limit also applies. Reserve a conservative
maximum for the complete bounded request/output, assuming cache misses and any cache-write premiums.
If the provider's maximum bill cannot be bounded, or the price/profile is unknown, do not dispatch.

Reconcile actual usage and release only the proven unused reservation. Unknown/time-out usage retains
its bound until resolved; hard limits are not based on delayed dashboards or hoped-for cache discounts.
Concurrency, retries and multi-step time advances cannot reset a tick's remaining budget. At exhaustion,
record deferral and next eligibility, continue only permitted deterministic work, and report unresolved
time/actors. Budget increases require the owning authority; no automatic model escalation or overspend.

## Replay and later acceptance evidence

Replay injects the **recorded AI proposal** and retained random draws into pinned deterministic rules,
planners and controllers. It makes no new AI call. Regenerating an answer from the same prompt/seed is
not proof of deterministic AI output. Idempotent application must not repeat effects or incur new spend.
Undo follows the campaign contract; incurred API charges and their audit are not undone.

Before claiming implementation, demonstrate sleeping/eligibility and frequency, one-call dispatch,
typed rejection and stale-commit handling, durable accepted state, offline execution between decisions,
replay without inference, measured hit/write/miss costs, concurrent reservation enforcement, midnight
policy, timeout recovery and hard daily/tick exhaustion. Prove zero inference from render/physics ticks.
No model call, benchmark, provider charge or consumer adoption was performed for this documentation.

[Authored behavior modules](behavior-executors.md) may request a decision through the host scheduler.
They cannot obtain provider credentials, call inference directly or bypass these eligibility, admission,
telemetry and spending limits. Render/physics hooks can enqueue eligible later work, never invoke AI.

Provider selection follows the current [fixed provider-options policy](ai-provider-dialogue.md#fixed-provider-options-for-now):
providers, models, endpoints and exposed options are hard-coded by us. Runtime callers cannot register
custom providers or override those definitions through content, a dialogue or an executor.
