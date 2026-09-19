# Patcher Queue contract

Status: commissioned design contract; Sam owns the Workboard implementation. Atlas owns this contract.
An Active Design may declare zero or more queued bodies. A task or design is not itself a queue body.

## Identity and evidence

Each body has immutable body_id, design_id, project technical identity and human name, title, permanent
owner task/branch, exact Patcher conversation identity, priority/order and creation event. Keep identities
stable through rename, return, review and archive. Each preparation/dispatch attempt has its own attempt_id.
Record dependency body IDs and satisfied-result evidence; expected source repository/ref/revision,
required ancestor or overlay and clean-tree requirements; instructions path/digest; ZIP path/name/digest,
file inventory and exact source identity; missing inputs; actor/timestamp and evidence for each transition.
Null evidence means unavailable, never successful. Source changes invalidate stale preparation readiness.

The owner declares work and accepts scope. Sam projects and manages queue workflow; the queue is not a
provider, source, review, database or landing authority. Mark performs the manual attach/paste ceremony.
Owner confirms received evidence and owns intake; Sally retains Main integration authority. No queue
transition authorizes product implementation, provider calls or landing beyond the declared body scope.

## States and transitions

| State | Meaning and evidence gate |
| --- | --- |
| QUEUED | Declared pending body, not sent; prerequisite evaluation still visible. |
| BLOCKED | Named prerequisite/input/authority gate prevents preparation or delivery. |
| PREPARING | Exact eligible body/attempt reserved; source and instruction evidence being assembled. |
| READY_FOR_MARK | Source, ZIP, instructions and dependencies verified; manual ceremony still pending. |
| DISPATCHED | Confirmed manual send evidence for exact attempt/attachment; not proof of receipt or start. |
| RETURNED | Exact returned artifact captured; removed from active preparation queue, history retained. |
| INTAKE | Owner reviewing/applying/checking exact return; review, applied, published and landed separate. |
| COMPLETED | Declared acceptance and required landing/reconciliation evidenced, not merely returned. |
| CANCELLED | Explicit authority withdrew body; preserve reason and evidence, remove from active queue. |
| ARCHIVED | Retained terminal record; preserve prior terminal outcome and all events. |

Record dispatched_at/evidence and received_at/evidence separately. Display “receipt unconfirmed” until
confirmed; do not show worker running merely because a send occurred. A return may establish receipt but
must not fabricate an earlier receipt timestamp. Unexpected or mismatched returns are quarantined for
identity resolution, never silently paired to the latest body. Review rejection may create a new attempt
under the same body with linked history; it never erases the old return.

`Prepare next queued Patcher` considers QUEUED bodies in explicit priority then creation sequence/body-ID
order, skips unsatisfied dependencies and selects the first unblocked body. It records skipped reasons,
reserves one attempt against the current queue revision, and prepares instructions/evidence only. If none
qualify, report no eligible body. Duplicate activation/stale revision must not create duplicate attempts.
A failure records a typed reason and BLOCKED state; it never advances to READY_FOR_MARK on partial proof.
The control must acknowledge work immediately and remain busy until preparation succeeds or fails.
It never calls a provider, sends/attaches a message, or claims delivery. Existing incomplete preparation
may resume only for the exact unchanged body/source/attempt; source changes require a new preparation.

A return immediately leaves the active preparation/dispatch list and enters return/intake presentation.
Keep one append-only event history with unique event IDs and monotonic revision, before/after state,
actor, timestamp provenance and immutable evidence references. Retried identical events are idempotent;
conflicts fail without rewriting history. Reconstruct current projection from that history. Missing
observations remain gaps. Cancel/archive and design moves preserve resolvable body/design identities.

## First declared body

- body_id: `atlas-active-designs-project-kit-001`; design_id: `atlas-project-kit-lifecycle`.
- title: Active Designs cleanup and Moondance Project Kit review.
- project: Talisman; branch: `codex/development-architecture-atlas`; queue order: 1.
- owner: Atlas — Talisman Architecture__; task `01a070ba-189e-7892-8aa5-4aa5cffe73af`.
- Patcher: `__Talisman Architecture`; conversation `6a9c606b-6c14-83eb-a3e1-4cbf4c913985`.
- declaration state: QUEUED. No dispatch, receipt, return or completion evidence exists.
- source requirement: clean published Atlas tip containing this contract and ACTIVE-DESIGN.md;
  preserve ancestor `24ff246f590ada42fa10d85f2d8d3ad21cd300db`; pin exact preparation tip externally.
- instructions: `handoffs/LIFECYCLE-HANDOFF.md`; no ZIP/digest yet; not READY_FOR_MARK.
- dependencies: no preceding body. Mark confirms the Patcher has an existing Atlas website; identify/reuse it
  during Patcher work. Its missing local source is not a preparation blocker. Eligible for preparation.
- package requirement: full source plus maintained Atlas, contracts, navigation, feedback and agent context;
  verify inventory, source identity and digest before READY_FOR_MARK. No delivery is implied.
- acceptance: exact returned documentation and integrated existing review artifact reviewed by Mark;
  owner intake and any later authorized landing/reconciliation separately evidenced.

This declaration registers the body in repository documentation. Sam must acknowledge his queue projection
before Atlas claims it appears on the Workboard. No dispatch is performed by this declaration.

## Focused acceptance for Sam and Project Kit

Specify fixtures for ordered selection with blocked dependencies, no eligible body, concurrent prepare,
stale source/queue revision, missing ZIP, manual-send-without-receipt, duplicate evidence, mismatched
return, return removal from active list, review rejection/new attempt, cancellation/archive and history
reconstruction. Prove no provider/send action is reachable from Prepare. Check accessible busy/error
feedback and explicit source/receipt/return/review/landing labels. The Kit ships this schema/state contract,
front-door queue declaration pattern and validator fixtures; this body implements no Workboard code.

## ZIP-only ceremony and root discovery

Mark requires the prepared full-source ZIP to be sufficient on its own. Its root README.md must identify
the exact queued assignment and link directly to the controlling ACTIVE-DESIGN.md, complete instructions,
required reading and return/acceptance criteria. No separate pasted brief or directory hunt is required.
Mark uploads the ZIP and may simply say “find it”; later discussion may refine the work.
Preparation checks root README discovery and all required local targets in a fresh extraction. Include
root PATCHER-SOURCE.json with exact source revision, body/attempt, file inventory and packaging overlays;
record ZIP SHA-256 outside the archive. Do not silently replace an existing project README: preserve its
human navigation and integrate the assignment entry, or declare the reviewed packaging overlay explicitly.
READY_FOR_MARK requires this actual verified package. An instructions-only draft is not a prepared ZIP.
