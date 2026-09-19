# Operations Atlas record contract

## Current scope

This page is the TA-01 contract for compact Operations Atlas records. It defines identities, fields,
vocabularies, lifecycle truth, source-authority rules, and contradictory states that the focused Atlas
validator rejects.

TA-01 does **not** create an operational receipt store, current-pointer reader, Workboard adapter, task
executor, server controller, or runtime mutation path. The JSON files in the
operations fixture directory (Talisman source reference: `operations/examples/`) are synthetic conformance vectors. Their encoding is
not the TA-02 decision about whether real repository records use JSON, YAML, a generated manifest, or both.

The versioned machine schema (Talisman source reference: `operations/operations-atlas-v1.schema.json`) owns field names, required and
optional properties, primitive constraints, and enumerated vocabulary. This page owns cross-record meaning.
The offline validator consumes both and rejects drift between the declared shape and executable rules.

## Identity and scalar rules

- Every declared owner, node, task, source receipt, result receipt, runtime receipt, relationship, gate, and
  route ID is lower-case kebab text and is unique within one record document.
- References use those exact IDs. Missing current references fail closed; an open gate may reserve a future
  receipt ID, but a satisfied gate may reference only receipts that exist.
- Git identities are full 40- or 64-character lower-case hexadecimal values. Display shortening belongs to a
  view, never to the stored record.
- Artifact digests use the form `sha256:` followed by 64 lower-case hexadecimal characters.
- Times are RFC 3339 instants parseable as UTC instants. Records do not use locale text or an unstated local
  timezone.
- Required text contains at least one non-whitespace character. Process IDs are positive integers, ports are
  in the range 1–65535, and recorded HTTP statuses are in the range 100–599.
- Repository paths are relative, normalized, and contain neither a parent traversal nor a backslash. A
  current route and every source receipt point to paths that exist in the same repository snapshot.
- Unknown properties fail validation. In particular, a node may not store a hand-written status property.

## Fixture envelope

Each TA-01 vector has schema version 1, one stable fixture ID, one example kind, a plain description, and
arrays for owners, nodes, tasks, source receipts, result receipts, runtime receipts, relationships, gates,
and capability routes. Empty arrays are explicit. The envelope exists only to validate a coherent graph in
one small file.

The required example kinds are source-only, landed-equal, session-gated, and restart-required. Additional
vectors may be added later without changing the record model.

## Owner declaration

An owner declaration gives a stable owner ID, display name, one or more roles, and an optional authority
path. Roles are permanent, patch-companion, landing, and runtime.

A permanent owner must name an existing repository authority path. A task's ordinary owner must have the
permanent role. A patch chat reference must resolve to an owner with the patch-companion role. Runtime and
landing roles grant no automatic lifecycle action; they identify who may create the corresponding receipt.

## Architecture node

An architecture node identifies a durable capability, service, screen, source, store, or design.

| Field | Contract |
| --- | --- |
| `id`, `name`, `kind` | Stable identity, readable name, and declared node kind. |
| `ownerId` | Permanent owner declaration. |
| `sourceAuthorityId` | Existing authoritative source receipt; never a materialization or compiled artifact. |
| `currentTaskId` | Optional current task owned by the same permanent owner. |
| `runtimeId` | Optional runtime receipt for the host node or a receipt that names this capability. |
| `relationshipIds` | Exact relationships touching this node; both endpoints list the same relationship. |

Node status is derived. A task state, source result, and runtime capability state remain distinct inputs to
that derivation.

## Current task contract

A task records one bounded current body: stable ID and title, one permanent owner, optional patch companion,
state, goal, exact input receipt IDs, expected output, active design path, optional paired worktree and
branch, optional one next gate, optional current result receipt, and update time.

The lifecycle vocabulary is:

`idle` → `planned` → `active` → `waiting` → `published` → `landed` → `equal`

Paused, blocked, superseded, and cancelled are separate exception or terminal states. A later body should
normally receive a new task identity instead of weakening an old terminal receipt.

Only active may drive a spinner. Waiting, published, landed, equal, paused, blocked, superseded, cancelled,
and idle never spin. A task does not store a spinner flag.

Published, landed, and equal tasks require exactly one current result receipt at the same stage. Other task
states may not use a result receipt to imply completion. Waiting, published, landed, and blocked tasks require
one open next gate. Equal, idle, superseded, and cancelled tasks have no open next gate.

## Source receipt and source-authority vocabulary

A source receipt identifies the exact material admitted to a bounded body. It records task and owner, scope,
source kind, repository, optional paired worktree and branch, optional base commit, one or more exact source
paths, optional artifact digest, test and validation evidence, evidence links, and recording time. Its owner
matches the task owner, and the task names the source receipt as an input.

Scopes are documentation, ledger, schema-validator, product, runtime, and mixed. The scope says what the
receipt proves; it is not inferred from a branch name.

Source kinds have these meanings:

| Kind | Meaning | May be a node source authority? |
| --- | --- | --- |
| repository-source | Versioned authored source in the repository. | Yes. |
| design-contract | Versioned design or contract that owns planned semantics. | Yes. |
| supplied-source-archive | Exact bounded source snapshot admitted for an external patch body. | Yes, for that bounded body. |
| database-materialization | Derived database representation of an authored source. | No. |
| compiled-artifact | Derived build or package output. | No. |

Repository source and design contract receipts require a base commit. A supplied source archive requires an
artifact digest. Database materializations and compiled artifacts remain linkable evidence but cannot replace
source authority.

## Result receipt

A result receipt records one bounded outcome and one exact stage: published, landed, or equal. It carries the
same task and owner, explicit scope, repository and optional paired worktree/branch, full commit identities,
changed source paths, exact test and validation outcomes, runtime effect, optional runtime receipt, evidence
links, and recording time.

Commit fields are conditional and never treated as aliases:

| Stage | Required commit fields | Forbidden commit fields |
| --- | --- | --- |
| published | base and published | landed Main and equal |
| landed | base, published, and landed Main | equal |
| equal | base, published, landed Main, and equal | none |

An equal receipt additionally requires the equal commit to be exactly the landed Main commit. Equality proves
a fresh owner fetch, not runtime availability.

Runtime effect is one of none, hot-materialized, restart-required, or restarted. Hot-materialized and
restarted results name the runtime receipt that proves the effect. Restart-required may name the stale runtime
receipt, but it never upgrades that capability to live.

## Runtime receipt and capability truth

A runtime receipt identifies one observed host process: host node, runtime owner, optional PID/process kind,
bind, port, health route, server and manifest versions, exact running source commit, health state, capability
observations, verification time, and exact health result.

Health state is ready, degraded, stopped, or unknown. Ready requires a successful 2xx health result. Stopped
requires an unavailable result. Unknown requires an explicit not-checked result. Process health alone proves
no capability.

Each runtime capability references an architecture capability node, route, state, optional required landed or
equal result, and exact request verification. Its state is one of:

- **live** — the runtime source equals the required landed/equal source and the request succeeded with 2xx;
- **session-gated** — the source is current, but a request without an application session truthfully returns
  401 or 403 with code `app_session_required`;
- **restart-required** — required source is landed/equal, the running source is different, and the request is
  unknown-route, unavailable, or deliberately not checked;
- **unavailable** — exact request evidence shows unavailable or unknown-route without a stronger source claim;
- **unknown** — no capability request was checked.

A 401/403 application-session gate is not live anonymous access and is not an unknown route. A healthy process
running an older commit is not evidence that newly landed source is live.

## Relationship, gate, and capability route

A relationship has one stable ID, two existing node IDs, and one unambiguous type: owns, implements, reads,
materializes, serves, depends-on, lands, or presents. Self-relationships and one-sided node listings fail.

A gate belongs to one task, has one owner, state, plain label and explanation, required receipt IDs, and update
time. Gate states are open, satisfied, superseded, and cancelled. The task's next gate must be open and owned
by the named gate owner. A satisfied gate resolves every required receipt ID.

A capability route records status, recognized intent, permanent owner, starting design, required contracts,
source entry points, runtime dependencies, focused evidence, one bounded first implementation step, patch
handoff, anti-patterns, and explicit route gaps. Route status is draft, current, partial, deprecated, or
blocked.

Each runtime dependency names an existing service node, its observed route state (`live`, `pending`,
`unavailable`, or `unknown`), its purpose, and an exact runtime receipt only when it claims `live`. Pending,
unavailable, and unknown dependencies cannot carry a runtime receipt and require an explicit
`runtimeDependencies` route gap. Each focused-evidence reference names its kind (`test-selector` or
`manual-scenario`), state, exact repository path, selector, and purpose. Test selectors must already resolve
in checked-in test source. Manual scenarios remain explicitly `pending`; a route is not a result receipt and
cannot claim that a future screen review passed.

`primaryOwnerId`, `startingDesignPath`, and `handoffOwnerId` may be absent only when a non-current route names
the unresolved field in `routeGaps` with a concrete reason and resolution. Supplied designs, contracts,
source entry points, and focused-evidence paths resolve even on partial or blocked routes. A `current` route
has no gaps and all required entry guidance resolves. `current` means verified entry guidance, not a live
capability; live runtime truth still requires its own runtime receipt. A partial or blocked route requires at
least one explicit gap. A resolved handoff owner must have the patch-companion role.

Gap kinds are field-scoped. `missing-owner` describes `primaryOwnerId` or `handoffOwnerId`;
`missing-design` describes `startingDesignPath`; `missing-source` describes contracts or source entry
points; `pending-runtime` describes runtime dependencies; `pending-proof` describes focused evidence; and
`contract-conflict` describes the starting design or required contracts. `implementation-gap` is limited to
bounded contract/source/first-body/handoff/anti-pattern guidance rather than runtime or owner identity.

## TA-01 conformance vectors

- Source-only body (Talisman source reference: `operations/examples/source-only.json`) proves exact admitted sources without a result or
  runtime claim.
- Landed/equal feature (Talisman source reference: `operations/examples/landed-equal.json`) proves stage-specific commit fields and shows
  that equality alone is not live state.
- Session-gated route (Talisman source reference: `operations/examples/session-gated.json`) proves registered exact-source capability
  truth without weakening the application-session gate.
- Restart-required capability (Talisman source reference: `operations/examples/restart-required.json`) proves that healthy stale runtime
  and landed source remain separate.

## TA-01A capability route seed

The Shelf-native Puppeteer route (Talisman source reference: `operations/examples/shelf-native-puppeteer-route.json`) is a fifth,
route-specific conformance seed. It keeps the original four TA-01 scenarios and semantic checks intact.
The seed resolves the permanent Object Factory owner, approved starting design, exact Shelf Contract v2
successor plus inherited v1 vocabulary, the manifest capability
`object-factory.core-morph-current.read.v1`, the exact
`object-factory.core-morph-current-read/v1` service contract, native and browser source entry points, and
existing focused test selectors. It remains `partial`: the starting design retains one stale Box-header
Close sentence that conflicts with the v18 DTDT's `TWIST_LABEL` policy and Shelf Contract v2; the checked
DTDT has no Puppeteer Label/Box; no live Application Server receipt was observed; four manual scenarios
remain pending; and the supplied source contains no verified Object Factory patch-companion identity.

The focused validator also constructs malformed and contradictory temporary vectors. It rejects missing and
unknown fields, bad IDs and paths, duplicate declarations, broken references, source-authority substitution,
stage/commit mismatch, false equality, false live/session claims, false restart freshness, a current route
with gaps, unacknowledged owner/design/runtime gaps, unresolved contract/source/evidence references,
misclassified route gaps, evidence-state masquerading, and a live dependency without an exact runtime
receipt.

## TA-02 decision gate

TA-02 may begin only after review of these validated examples. The remaining choice is the format of actual
append-only records and compact current pointers: repository JSON, repository YAML, a generated manifest, or
both. That decision must retain readable diffs, immutable historical receipts, stable references, offline
validation, and a read projection that does not make the Workboard authoritative.
