# Talisman Architecture Operations Atlas

## Status and purpose

**Status:** active design; TA-01/TA-01A contract foundation plus TA-02 compact reader are implemented in this review body.

**Owner:** Talisman Architecture.

**Implementation path:** Talisman Architecture Work receives this design, creates bounded implementation bodies, and hands each code body to Talisman Architecture Patch Chat. The Patch Chat returns a reviewable patch; the Work owner records the result and routes landing through the normal Main process.

**Purpose:** make the Development Architecture Atlas the fast, trustworthy answer to operational questions about Talisman and Moondance work. It must let a person or an agent find the current owner, exact source, live state, evidence, and next gate for a system without reconstructing that truth from long conversations, stale cards, or unrelated Git history.

This is the architecture and coordination counterpart of the Workboard. The Workboard is the working shelf presentation; the Operations Atlas is the navigable model and evidence trail behind it.

The [Moondance Project Kit](MOONDANCE-PROJECT-KIT.md) is a separate bounded Atlas initiative for making
each Moondance game repository independently understandable, buildable, validatable, and suitable for
full-source Patcher exchange. Its first body is inventory and exact design only; it is not an Operations
Atlas runtime feature or an authorization to create the Kit repository or migrate a game.

## The problem to solve

Today, a correct answer commonly requires joining several separate facts:

- a task conversation says what was intended;
- a worktree and branch show what was produced;
- Main and the remote show whether it landed;
- focused tests show what was proved;
- a running server shows what is actually live;
- the Workboard summarizes this for use during work.

Each source is useful, but they are not yet joined by stable identities and short current receipts. The result is expensive repeated interpretation. It also risks false spinners, duplicate ownership, stale server claims, and confusing `published`, `landed`, and `live`.

The goal is not a new workflow engine, a generic ticket system, or an AI controller. The goal is a compact, read-first operational map that points to existing authorities and makes their current truth easy to retrieve and verify.

## Desired user experience

From an Atlas node or Workboard card, someone should be able to answer these questions in one view:

1. What system or capability is this?
2. Who owns the current body of work?
3. What is the exact current task and its one next gate?
4. What source is authoritative: a repository path, design, database materialization, or runtime capability?
5. What is the exact result: published branch tip, landed Main tip, or permanent-owner equality tip?
6. What evidence exists: focused tests, validation, visual proof, or no proof yet?
7. What is live now: process, bind/port, manifest/capability version, health, and freshness?
8. What must happen next, and which owner may do it?

The usual answer must fit in a compact current card. History remains available as a twist-open receipt trail rather than consuming the operational view.

## Capability how-to routes

The Atlas must also be a practical starting point for new work. A request such as “create a new Puppeteer interface” should not require rediscovering the whole architecture or guessing which library, owner, test, or Shelf rule applies.

A `CapabilityRoute` is a compact, maintained how-to for a recurring kind of work. It does not create a new owner or replace the detailed design; it gives the smallest safe path to the existing authority.

```text
routeId                    stable capability/how-to identity
intent                     plain-language request the route recognizes
primaryOwnerId             verified permanent owner, or an explicit gap
startingDesignPath         verified first design, or an explicit gap
requiredContracts[]        DTDT, service, persistence, Shelf, or other applicable contracts
sourceEntryPoints[]        exact code/resource paths to inspect first
runtimeDependencies[]      service node, observed state, optional live receipt, and purpose
focusedEvidence[]          typed path/selector references: existing tests or pending manual scenarios
firstImplementationStep    one bounded first body, not a broad programme
handoffOwnerId             verified patch companion, or an explicit gap
handoffRule                how implementation returns to the permanent owner
antiPatterns[]             known shortcuts or boundary violations to avoid
routeGaps[]                unresolved field, gap kind, exact reason, and resolution
```

The route is an entry ramp, not a substitute for design judgment. It must make uncertainty visible: a missing design, unknown owner, unimplemented runtime capability, or conflicting contract is a route result, not something silently guessed.

### Example: create a Puppeteer interface

The Atlas should answer this request approximately as follows:

1. **Start with the Objects/Future Puppeteer capability node** and identify its permanent owner.
2. **Read the applicable DTDT how-to** for a Shelf-native interface: retain context/location/selection, declare the Shelf Label and Shelf Box behavior, preserve close/expand rules, and avoid creating a second ad hoc workspace model.
3. **Read the Puppeteer design and current source entry points** named by the route. If no current design exists, create a bounded Puppeteer design body before coding.
4. **Check dependent authority:** the selected Body Form/Morph package, motion/stance data, the Object presentation seam, and any session/runtime requirement. A route distinguishes source-landed from live capability.
5. **Create one bounded first body:** for example, a read-only Puppeteer Shelf that projects an existing selected Morph/motion contract. Do not begin with a new persistence store, autonomous executor, or a replacement of the existing Object selection authority.
6. **Use the route's focused proof:** retained Shelf control identity, selection/currentness checks, the smallest typed read fixture, and a real-screen scenario where required.
7. **Hand code to the named patch companion** and return the exact source/result receipt to the ordinary owner for landing.

The same form applies to a new Easy Tale reader, a server capability, a map editor surface, or a new Core Morph tool: plain-language intent first, then the minimum authoritative contracts, paths, proof, and next owner action.

### TA-01A verified seed: create a Shelf-native Puppeteer interface

The checked CapabilityRoute seed (Talisman source reference: `../../../development-atlas/operations/examples/shelf-native-puppeteer-route.json`)
answers the exact intent **create a Shelf-native Puppeteer interface** without pretending implementation or
runtime evidence exists.

Its verified starting authority is Object Factory's
UI design (Talisman source reference: `../Object%20Sing%20%26%20Dance%20Factory/UI-DESIGN.md`), backed by its
system design (Talisman source reference: `../Object%20Sing%20%26%20Dance%20Factory/SYSTEM-DESIGN.md`). The owner DTDT identifies
`talisman.shelf-contract/v2`; Shelf Contract v2 (Talisman source reference: `../Talisman%20UI%20Shelf%20Rework/SHELF-CONTRACT-V2.md`)
is therefore the applicable successor and retains the frozen v1 Label/Box vocabulary. The existing typed
read is the manifest capability `object-factory.core-morph-current.read.v1` with the exact service payload
contract `object-factory.core-morph-current-read/v1`; the route names its gateway contract without claiming
that a process is live. It also names the service architecture, Application Server/manifest entry points,
exact v18 DTDT, browser client/workbench, and existing `ObjectFactoryBodyFormSession`,
`ObjectFactoryPoseSession`, `ObjectFactoryPuppeteerSession`, `ObjectFactoryBodyFormEditorPanel`,
`ObjectFactoryPuppeteerPanel`, `ObjectFactoryWorkspaceState`, and `ObjectFactoryNativeWorkspacePanel`
authorities.

The first bounded implementation body is not a second Puppeteer model. After Object Factory assigns a real
patch companion and reconciles one stale owner-design sentence, that body adds the missing Puppeteer peer
Label/Box and explicit Body Form child group to the existing v2 owner DTDT, then moves only the existing
stance and motion projection into the retained Box. The stale sentence says an explicit Box-header Close
remains available; the exact v18 DTDT selects `TWIST_LABEL`, and Shelf Contract v2 makes the persistent label
the sole open/close control. The future body follows v2. It preserves the current Form, Morph,
location/context, selection, and shared Object presentation authorities; retains child state across parent
close; and leaves service, persistence, worker, and provider authority where it already lives.

The seed remains `partial` for five explicit reasons: the owner design and v2 close rule require
reconciliation, v18 does not yet declare Puppeteer, no Application Server runtime receipt was inspected, the
implementing screen scenarios are pending, and no repository-owned Object Factory patch-companion identity
was found. `current` continues to mean verified route guidance rather than a live screen; a future live claim
requires a separate exact runtime receipt.

## How-to route maintenance

- An owner adds or amends a route when a capability is first made reusable or when a repeated discovery cost becomes clear.
- Each route links to exact authoritative documents and source paths; it does not copy large designs into the route.
- Routes are validated for resolvable links, known owner IDs, state vocabulary, and focused-evidence references.
- A route may be `draft`, `current`, `partial`, `deprecated`, or `blocked`. `current` means its entry links and first-body guidance are verified, not that every possible implementation is finished.
- The Atlas current-operation page presents matching routes beside the owner/node result so a person or agent can begin with a small checklist instead of long-history search.

## Design principles

### One identity, many views

Every operational item has a stable ID. Atlas graphs, Workboard shelves, task receipts, validation reports, and runtime records refer to that ID rather than copying prose. Views may organize the same item differently, but must not create separate truths.

### Existing owners remain owners

The Atlas observes and links authority; it does not take over a feature's repository, database, server lifecycle, or business decisions. An owner writes its own task and result receipts. Switchboard remains responsible for landing. A running service remains responsible for runtime health.

### Receipts, not reconstructed stories

A current claim needs a small structured receipt. A conversation, screenshot, browser tab, or task title can be useful evidence, but it is not enough by itself to claim landing, equality, test success, or live service capability.

### Current truth is explicit

The Atlas must distinguish:

`planned` → `active` → `waiting` → `published` → `landed` → `equal`

with separate terminal/exception states: `paused`, `blocked`, `superseded`, and `cancelled`.

Only `active` means that the owner is currently doing work and may spin. `waiting`, `published`, `landed`, `equal`, `paused`, and `blocked` never spin.

### Source authority and materialization stay separate

An authored Core Morph source package, a derived database row, a compiled application artifact, and a live route are linked stages, not interchangeable proof. The Atlas must name each stage and never treat a browser cache or direct database inspection as source authority.

### Cheap current reading, deep history on demand

The first read retrieves compact records and immutable references. Large task histories, logs, diffs, screenshots, and prose audits load only when the reader opens their evidence link. This is as important for agent efficiency as for screen clarity.

## Canonical operational records

The following records are the minimum common vocabulary. They are deliberately small and reference existing owners rather than duplicating full feature state.

### Architecture node

An `ArchitectureNode` identifies a durable system, capability, design, service, store, or screen.

Required fields:

```text
id                         stable, human-readable identity
name                       user-facing name
kind                       capability | service | screen | source | store | design
ownerId                    permanent owning task/project
sourceAuthority            reference to the authoritative source record
currentTaskId              optional current operational task
runtimeId                  optional live runtime record
status                     derived, never hand-written separately
relationships[]            typed links to other node IDs
```

Examples: `core-morph-catalog`, `object-factory-morph-reader`, `talisman-application-server`, and `development-architecture-atlas`.

### Current task contract

Each active or waiting task has one compact `TaskContract`. It replaces broad status reconstruction, not the task's working conversation.

```text
taskId                      stable board/task identity
title                       concise user-facing body name
ownerId                     the one ordinary owner
patchChatId                 optional patch-chat companion
state                       lifecycle vocabulary above
goal                        one bounded outcome
inputs[]                    exact source/design/receipt dependencies
output                      expected source, patch, receipt, or runtime result
activeDesignPath            repository-relative design authority
worktree                    permanent named worktree, when applicable
branch                      named branch, when applicable
nextGate                    one concrete next condition or action
updatedAt                   last confirmed state change
```

The contract must not claim completion merely because a related task is complete. A task with no current body is `idle` or absent, not `active`.

### Source and result receipt

`SourceReceipt` identifies what can be safely read or patched. `ResultReceipt` records what happened after a bounded body completes.

```text
sourceId / resultId
taskId
repository, worktree, branch
baseCommit, publishedCommit, landedMainCommit, equalCommit  (only when applicable)
sourcePaths[]
artifactDigest              optional, for supplied bundles
tests[]                     exact focused selectors and results
validation[]                exact validators and results
runtimeEffect               none | hot-materialized | restart-required | restarted
evidenceLinks[]             compact references; heavy material is opened on demand
recordedAt
```

The receipt must state its scope. A documentation commit, ledger-only commit, and product-body commit are distinct. Short Git IDs may be displayed, but stored receipts retain full IDs.

### Runtime receipt

`RuntimeReceipt` answers whether a capability is actually available now.

```text
runtimeId
nodeId
ownerId
processIdentity             optional PID and expected process kind
bind, port, healthPath
serverVersion, manifestVersion, sourceCommit
capabilities[]              exact registered capability IDs/routes
healthState                 ready | degraded | stopped | unknown
verifiedAt
verification                exact health/request result, including truthful 401/403 gates
```

A route returning `app_session_required` is registered but session-gated; it is not `unknown_route`. A capability whose source landed but whose process predates it is `restart-required`, not live.

### Relationship and gate

`AtlasRelationship` describes a non-ambiguous edge such as `owns`, `implements`, `reads`, `materializes`, `serves`, `depends-on`, `lands`, or `presents`.

`Gate` records why a task may not advance:

```text
gateId, taskId, state, label, ownerId, requiredReceiptIds[], explanation, updatedAt
```

Gates are readable circles/shelf details in the Workboard. They must say the actual condition in plain language—e.g. “clean exact-Main archive required”—rather than an internal nickname alone.

## Authority and update protocol

1. A task owner creates or updates its `TaskContract` when it begins, pauses, waits, publishes, or completes a body.
2. The patch chat produces a bounded patch/result receipt. It does not silently change the ordinary owner's contract.
3. Switchboard records the landing receipt. The feature owner records a fresh-fetch equality receipt afterward.
4. A server owner records runtime replacement, health, manifest, and capability evidence after lifecycle actions.
5. The Atlas derives node status from receipts; the Workboard reads the same derived state for cards and spinners.
6. A reader may show stale/unknown truth, but may never infer a stronger state. Missing receipt means `unverified`, not `landed` or `live`.

The update protocol is append-only for receipts and replaces only the compact current pointer. This preserves history while making the common read inexpensive.

## Workboard and Shelf presentation

The Operations Atlas and Workboard share IDs and status vocabulary, not a forced shared UI implementation.

- The Workboard presents current `TaskContract`s as owner shelves.
- Each card shows owner, active design, state, next gate, and the latest small result summary before optional detail.
- Spinner state is derived only from `active` task state.
- A landed/published/equal receipt becomes a “Landed” shelf item inside the owning shelf, not a second owner or a duplicate User Work card.
- The Atlas opens from a card into the node graph and receipt trail; it may also be navigated independently for architecture work.
- Shelf behavior remains governed by the Shelf contract. This plan adds data/identity needs, not a competing Shelf layout policy.

## Efficiency wish list, in priority order

### P0 — Compact current contracts and receipts

Provide a repository-owned, schema-validated place for current task contracts, result receipts, runtime receipts, and gates. Give every record a stable ID and exact links.

**Why:** this removes the highest-cost repeated work: rereading conversations and reconciling vague claims across owners.

### P0 — Atlas current-operation page

Add an Atlas page that starts with a searchable capability/owner/task list and opens a node's compact current truth, relationships, sources, latest receipts, gates, and runtime state.

**Why:** this is the one-screen answer to “what is going on?” before deep investigation begins.

### P0 — Capability how-to routes

Add validated plain-language routes from common requested work to the owning design, DTDT and other required contracts, source entry points, focused evidence, first bounded body, and patch handoff.

**Why:** this converts the Atlas from a map one must interpret into a reliable starting procedure for ordinary work.

### P1 — Workboard read adapter

Make the Workboard consume the same validated current records rather than maintain parallel task truth. Keep Workboard-specific shelf arrangement and user interaction local.

**Why:** it prevents a card from saying `active` while the owner receipt says `equal`, or from losing a real runtime state.

### P1 — Receipt producer helpers

Provide small owner-facing helpers/templates for creating a task contract, publishing a result receipt, recording Switchboard landing, recording owner equality, and recording server verification. They must be explicit actions, not background guesses.

**Why:** correct updates need to be easier than narrative-only updates.

### P1 — Exact runtime capability directory

Expose each managed server's verified version, source commit, manifest, bind/port, health, and capability status in the Atlas. Link an unavailable capability to its responsible runtime/restart gate.

**Why:** it distinguishes landed source from the code currently serving a page.

### P2 — Provenance-friendly historical import

Import selected known landed bodies as receipts only when their exact Git and validation evidence are available. Leave uncertain history explicitly unverified.

**Why:** historical context is valuable, but fabricated certainty would defeat the system.

### P2 — Evidence search and agent read budget

Support an evidence index keyed by task ID, node ID, commit, capability ID, and design path. The default query returns the current contract plus latest receipts; opening a history, diff, or log is deliberate.

**Why:** it makes the cheapest truthful answer the normal answer for people and agents.

## Phased implementation plan

### Body TA-01 — Contract and schema

Define record schemas, lifecycle/state rules, ID conventions, source-authority vocabulary, and validator fixtures. Include examples for a source-only body, a landed/equal feature body, a session-gated server route, and a restart-required capability.

**Acceptance:** malformed or contradictory records fail validation; `published`, `landed`, `equal`, and `live` cannot be conflated; Atlas validation remains focused and offline.

**TA-01 implementation:** the current repository body adds the
[Operations Atlas record contract](../../../development-atlas/operations-contract.md), a version-one JSON
schema, four synthetic conformance vectors, and a pure-JDK extension of the existing focused Atlas validator.
The vectors are test data only. TA-01 adds no record store, current-pointer format, reader, projection,
command, event, worker, subscription, UI, process inspection, or runtime mutation.

### Body TA-02 — Repository receipt store and reader

Implement the repository-owned append-only receipt store and a compact current-pointer reader. Do not introduce a second business database, task executor, or server control path.

**Acceptance:** a node can resolve its owner, current task, exact source, result, gate, and optional runtime receipt using stable IDs; historical receipts remain immutable.

**TA-02 implementation:** `development-atlas/operations/store/receipts/` holds immutable JSON records;
`store/current/` holds one compact mutable pointer per node; `current-truth-resolver.mjs` performs the
deterministic read-only join and `query-current.mjs` exposes the compact query. The first records are bounded
to evidence visible in the supplied snapshots and bundle metadata. Missing result/runtime evidence stays
missing, and synthetic test evidence is labeled explicitly. Owner write helpers, UI, Workboard projection,
runtime inspection, and history migration remain later bodies.

### Body TA-03 — Atlas operations view

Add the read-only Atlas page/navigation for current operational truth and relationship traversal. Heavy evidence opens on demand. Include empty, stale, gated, and conflicting-receipt states.

**Acceptance:** the page can truthfully represent a missing receipt, a session-gated capability, and a source-landed/runtime-stale capability without calling any of them live.

### Body TA-03A — Capability route reader

Add the read-only route index and detail view. Seed only a small, verified set of routes—beginning with the Shelf-native Puppeteer-interface example—rather than inventing broad coverage.

**Acceptance:** a request can resolve from an intent to its owner, required contracts, source entry points, focused evidence, first bounded body, and patch handoff; unresolved authority is shown as a route gap.

### Body TA-04 — Workboard adapter

Let the Workboard read the validated compact projection. Retain local shelf controls, drag ordering, and user-specific layout separately.

**Acceptance:** board cards and Atlas current view agree for a fixture set; a task becomes non-spinning immediately when its current receipt is no longer `active`.

### Body TA-05 — Owner and runtime receipt helpers

Add bounded tools/templates for known owner transitions and managed-server verification. They must require supplied evidence and fail closed on unknown state.

**Acceptance:** an owner can record a clean equality result and a server owner can record a registered-but-session-gated capability without manual copy/paste drift.

### Body TA-06 — Selective history migration

Bring in only high-value, evidence-complete histories and link the existing Atlas design pages. Do not block current operational use on complete archival import.

**Acceptance:** imported history identifies its original evidence and has no stronger state than that evidence proves.

## Non-goals and protections

- No automatic task starting, resuming, stopping, landing, or server restart.
- No replacement of Git, Switchboard, feature owners, or service lifecycle owners.
- No direct database mutation, provider calls, credential handling, or broad-suite requirement.
- No interpretation of a chat claim, browser screen, or card as proof of an exact commit or live route.
- No generic workflow engine or universal issue tracker.
- No duplicate source of truth for feature business data.

## TA-01 handoff boundary

TA-01 is the documentation/schema/validator body described above. Talisman Architecture Patch Chat should
review and land it as one bounded patch only after the focused validator and whitespace checks pass. No TA-02
store or reader choice is implied by the use of JSON for deterministic conformance vectors.

## Decision requested after TA-01

Before TA-02, review the validated schema examples and decide whether the first operational records are stored as repository JSON/YAML documents, a generated manifest, or both. The choice must preserve readable diffs, stable references, offline validation, and a clear route for the Workboard to consume a projection without becoming the authoritative store.
