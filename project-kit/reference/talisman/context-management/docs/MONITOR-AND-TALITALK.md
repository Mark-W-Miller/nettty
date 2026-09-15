# Monitors and TaliTalk

## Monitors window

The existing **Monitors** menu and the combined-window title bar open one
modeless shared window styled like the main play window. CM-03 establishes its
tab container and the **Context** tab. Existing Activity, Memory, and DTDT
monitor windows keep their current owners and entry points; migrating or
registering them as shared tabs is later reviewed work.

The **Context** tab renders `ContextMonitorState` and should show:

- snapshot ID and capture time;
- application area, activity, admitted role inputs, and effective target;
- hierarchical nodes with type, stable ID, relationship, importance, relevance,
  and disclosure;
- summaries and facts;
- provider provenance;
- response mode, allowed actions, and expected output fields;
- bounded identity-safe policy/budget omission details and fixed role counts;
- the added, removed, and changed nodes since the previous snapshot.

The current control is pause/live. Copy sanitized payload, pin snapshot,
compare with pinned, and provider/type filters remain future monitor behavior,
not part of the core composition engine.

The Context presentation subscribes only while the shared window is visible.
Registration atomically replays installed truth, listener callbacks queue Swing
work, and a generation guard drops callbacks queued before detach. Hiding the
window detaches and releases current/previous snapshot references; reopening
reconstructs from the service's current immutable truth. Overview, hierarchy,
detail, diff, selection, and omission text have fixed presentation ceilings in
addition to the snapshot policy ceilings.

Copying or handing the snapshot to a later monitor adapter uses the canonical
`SNAPSHOT` `ApplicationContextPayload`. It does not serialize the raw request or
reconstruct excluded metadata.

The monitor sees only the immutable admitted snapshot. It never receives raw
metadata from a disclosure-denied or over-budget pointer, focus, selection, or
explicit target. A suppressed detail count explains when the omission-detail
budget could not retain every safe row.

## Ask AI pop-up

Any meaningful contextual object may offer **Ask AI** from its context menu.
The small annotation-inspired pop-up is optimized for a quick question and
answer. CM-04 supplies the reusable pop-up/action session. CM-05 registers it on Created Things
Characters in Assets Manager. Activation immediately displays **Preparing context…**,
then builds the request and captures exactly once at submit time. Success means
the separate destination accepted the canonical `ASK_AI` payload; it does not
mean an AI answer exists. Closing the owner before queued work begins prevents
capture and delivery, while a generation guard suppresses stale UI completion.

## Open in TaliTalk

The same context menu offers **Open in TaliTalk**. It creates a
`TaliTalkHandoff` containing:

- the exact immutable starting snapshot;
- the effective target;
- the response contract;
- an optional opening question; and
- a conversation title derived from the target.

The question and derived title are disclosure-classified `ContextText`. Their
combined size plus the starting snapshot must fit its total payload policy, so
the handoff cannot restore excluded metadata or bypass snapshot budgeting.
`ApplicationContextPayload.forTaliTalk(...)` preserves those exact values in the
canonical `TALI_TALK` transport shape.

CM-04 routes that exact payload to an injected immediate destination and reports
the same bounded acknowledgement states as Ask AI. It does not open or mutate a
TaliTalk conversation; the context-session adapter remains separate. Both
actions install their captured snapshot through the one application service, so
an attached Context Monitor passively converges on the identical snapshot ID.

For the CM-05 Assets pilot, the production destinations remain unavailable. **Ask AI** therefore opens
the reusable question surface and proves safe preparation without calling an AI. **Open in TaliTalk**
shows the same surface, immediately submits the reusable handoff, and reports that the destination is
unavailable without opening a conversation. Focused fake destinations prove exact payload identity.

TaliTalk opens its green screen with this snapshot visible as the conversation
origin. It may investigate, run permitted experiments, or request additional
information before answering. Any later context refresh must be explicit and
recorded as a new snapshot; it must not rewrite the starting snapshot.

Experiments must remain bounded by the response contract and capability layer.
Exploratory reads can be recorded directly. Proposed or executed changes should
identify the snapshot they were based on and require confirmation whenever the
contract says so.

CM-08 makes that last boundary concrete without adding a destination or feature mutation. A structured
proposal repeats the exact installed snapshot, response contract, effective target, and allowlisted action.
Proposal-only mode returns no confirmation token. Confirmed-action mode also requires a currently
registered feature descriptor, a read-only owner stamp, and one explicit one-use confirmation. Context
Monitor remains a passive view of the installed snapshot; a proposal or action status does not rewrite
Context truth or claim that an owning feature committed a change.

CM-09 preserves that separation across the Character and Body Form screens. Proposal preparation installs
the same submit-time snapshot that Monitor observes, but proposal text and confirmation state are not
Monitor nodes. A foreign, hidden, stale, or closed screen cannot confirm an owner's pending proposal, and
screen close discards it without changing Context truth or opening TaliTalk.
