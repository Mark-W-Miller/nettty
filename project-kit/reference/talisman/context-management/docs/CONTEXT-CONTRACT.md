# Context contract

## The unit of truth is a snapshot

Every Ask AI invocation captures an immutable `ContextSnapshot`. If the user
changes screens or selections while the AI is working, the request and answer
remain tied to the original snapshot ID and capture time.

`AppServices` owns the one application-lifetime `ContextService`. CM-05, CM-06, and CM-07 compose pure
Assets Manager Character, Adventure/Battle, and Object Factory providers. Their feature owners still own
live capture, revision, privacy, selection, and stale-state truth. Shutdown clears current/listener state
and rejects new work, so a closed application cannot produce a plausible stale snapshot.

A snapshot contains:

- application area and current activity;
- policy-admitted pointer target, keyboard focus, selection, and explicit
  invocation target;
- the effective interaction target recomputed from those admitted roles;
- a hierarchical forest of context nodes;
- the response contract;
- provider provenance;
- bounded identity-safe omissions caused by policy or budget; and
- stable identity and capture time.

## Target precedence

The effective target is resolved consistently:

1. explicit right-click/Ask AI target;
2. pointer target;
3. keyboard focus;
4. first selected object.

These inputs stay distinct in `ContextRequest`. Every reference carries a
disclosure classification. The engine evaluates the roles in precedence order,
admits each complete reference only when both disclosure and the aggregate
reference/character budgets permit it, and then stores only admitted values in
the snapshot. A caller must not overwrite the selection merely because the
pointer is temporarily over something else.

Denied roles do not silently disappear. Detailed omission rows retain only a
closed reason, a safe source role/provider ID, and a non-raw subject digest.
Fixed role/provider counts remain available when the detailed omission budget
is exhausted. Labels, stable IDs, and hints from a denied reference never enter
the snapshot, envelope, handoff, or monitor model.

## Context nodes

Every node has:

- `ContextNodeKey(type, id)`: stable identity across snapshots;
- label and relationship: monitor-facing meaning;
- summary and string facts: AI-facing representation;
- importance and relevance: admission priority;
- disclosure level: policy filtering;
- one or more provider IDs: provenance; and
- immutable children.

Providers may contribute to the same node. Contributions merge by stable key.
Conflicting parents are rejected because an ambiguous hierarchy would make the
payload misleading. More complex cross-links belong in facts or a future
explicit relations collection; the primary tree remains inspectable.

## Providers

Each domain owner implements `ContextProvider`:

```java
public interface ContextProvider {
    String id();
    int order();
    List<ContextFragment> contribute(ContextRequest request);
}
```

Provider output must be deterministic for the supplied request and domain
state. Providers should emit concise AI-safe summaries rather than serialized
entities, UI controls, database sessions, or mutable model objects.

Examples of ownership:

| Provider | Contributes |
|---|---|
| Main shell | application area, activity, open project |
| Adventure | adventure, world, place ancestry, scene state |
| Battle | battle, turn/round, creatures, effects |
| Assets Manager | generic asset, versions, metadata |
| Object Factory | object, model, part/joint ancestry, selection, edit state |
| Permissions | disclosure classification and permitted representations |

## Required versus relevant

`REQUIRED` nodes survive size budgets. Use this only for the target, the
minimum path needed to interpret it, and mandatory response constraints.
`RELEVANT` and `SUPPORTING` nodes are admitted by relevance while preserving
their ancestor paths.

Disclosure filtering happens before budgeting. A child whose parent is removed
by policy is also omitted. Omission counts remain recorded for the Context
Monitor even when individual safe detail rows cannot fit.

## Total payload policy

`ContextPolicy` separately bounds nodes, admitted references, detailed
omissions, total snapshot characters, and total payload characters. Snapshot
character accounting jointly includes safe application/activity identifiers,
the response schema, provider IDs, every admitted reference, every admitted
node, and detailed omission evidence. Accounting uses overflow-safe totals and
the immutable snapshot validates the limits again at construction.

Application/activity, provider, omission-source, and response-schema fields are
structural contract metadata with fixed syntax and per-field bounds. Domain and
user-authored content enters only through disclosure-classified references,
nodes, and `ContextText` values. `ContextEnvelope` questions and TaliTalk opening
questions/titles must pass the snapshot disclosure allowlist and the aggregate
payload-character budget. A rejected extension fails construction.

`ApplicationContextPayloadCodec` supplies the separate total transport boundary.
Its canonical version-one JSON has strict shape/version admission, deterministic
ordering, pre-parse byte/depth/string/number ceilings, and reconstructs the
immutable values so their disclosure and aggregate budgets are revalidated. See
[Application context payload version 1](APPLICATION-CONTEXT-PAYLOAD-V1.md).

## Response contracts

Context includes the shape and authority of the answer, not only its subject.
A `ResponseContract` identifies:

- `ANSWER`, `PROPOSE_CHANGES`, or `EXECUTE_ALLOWED_ACTIONS` mode;
- the exact allowed actions;
- output fields the caller can understand; and
- whether confirmation is required.

An answer-only surface cannot accidentally declare executable actions. A proposal contract must carry a
nonempty exact action allowlist and cannot produce an executable receipt. An executable contract must also
require explicit confirmation.

CM-08 adds `ContextProposalService` as the bounded process-local coordinator for those two action modes.
Every input repeats the installed snapshot ID, response-contract ID, effective target, and exact allowlisted
action. Its summary and deterministic parameter map are independently disclosure-classified and jointly
checked against both fixed proposal bounds and the snapshot payload policy.

For executable contracts, a registered feature authority declares the exact application area, target
types, and required/optional parameter names it accepts. Its read-only review returns an opaque bounded
current-state stamp. Context retains at most 64 pending proposals and issues an opaque one-use confirmation
token. Confirmation rechecks current snapshot, contract, target, allowlist, authority registration, and
token, then consumes the pending value before at most one dispatch. The feature authority must atomically
revalidate its own stamp before mutation or semantic-operation admission and remains the only owner that
can report the semantic result. Dispatch, destination acceptance, and Context status never prove a commit.

CM-09 adds an exact non-dispatching discard operation for screen lifecycle cleanup. It requires both the
proposal ID and snapshot ID. The shared Swing surface retains only that opaque pair, rejects confirmation
from a different screen surface, recaptures and compares the originating roles at confirmation, rechecks
owner visibility, and discards outstanding pairs when the originating screen closes. Proposal content,
authority stamps, and confirmation tokens remain in
the bounded application service rather than screen-owned state.
