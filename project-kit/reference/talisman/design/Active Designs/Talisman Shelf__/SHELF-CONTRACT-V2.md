# Shelf Contract v2

Status: additive browser/DTDT successor used by Object Factory

Owner: Talisman Shelf__

## 1. Compatibility boundary

Shelf Contract v2 has the exact identity `talisman.shelf-contract/v2` and `schema_version: 2`.
It extends, but never reinterprets, the frozen Shelf Contract v1 vocabulary. A v1 definition keeps its
historical shape and is admitted only by the explicit v1 validator. A v2 definition is self-identifying and
is admitted by version dispatch. Missing, unknown, or mismatched identities fail before visible mutation.

There is no implicit in-memory or persisted-state upgrade. An owner that adopts v2 changes its definition
fingerprint and persistence contract identity; a prior v1 presentation envelope is rejected and replaced by
the owner's declared v2 defaults. Feature or domain state is not affected.

## 2. Additive v2 definition fields

Every v2 `ShelfDefinition` adds:

- `contract`, fixed to `talisman.shelf-contract/v2`;
- `schema_version`, fixed to `2`; and
- `layout_policy.maximum_nesting_depth`, an integer from zero through eight.

Every v2 `ShelfDefinition` also declares one `presentation_memory_policy`:

- `BROWSER_LOCAL` restores the complete Shelf presentation only in the same browser profile and origin.
  It is the correct policy for a web tool such as the Workboard, where each browser is its own working
  place; closing, reopening, refreshing, or returning to that page restores the person's local setup.
- `USER_CONTEXT_DURABLE` restores the complete Shelf presentation for one authenticated person and one
  declared application-context key. It is the correct policy for Talisman, where the person's current
  workspace is part of their durable context and must follow the same person between Talisman surfaces.

The declared memory policy names only the scope and restoration route. It never changes who owns product,
domain, operation, or server data.

Every v2 `ShelfGroupDefinition` adds:

- `reorder_policy`: `DISABLED` or `POINTER_AND_KEYBOARD`;
- `disclosure_policy`: `NONE`, `LABEL_TOGGLE_WITH_TOP_CLOSE`, or `TWIST_LABEL`;
- `orientation`: `HORIZONTAL` or `VERTICAL`;
- `overflow_policy`: `NONE` or `SCROLL`;
- a unique ordered `label_adornments` subset of `STATUS`, `BADGE`, `SUMMARY`, `BUSY`, and
  `CONTEXT_ACTIONS`; and
- optional bounded `group_content` mounted after the complete label cluster.

Every v2 `ShelfLabelBoxDefinition` adds nullable `label_content` and `child_group_id` fields.

## 3. Disclosure and content

`NONE` is valid only for labels whose surface target is `NONE`.
`LABEL_TOGGLE_WITH_TOP_CLOSE` requires one exact `top_close_control_id` on every REGION surface.
`TWIST_LABEL` uses the persistent label as its only open/close control and forbids a second Close control.

Label and group content use `INLINE` or `DISTRIBUTED` layout and one through twelve unique component
references. Component slots are `AFTER_TOGGLE`, `CENTER`, or `END`. `AFTER_TOGGLE` label content is passive
inside the disclosure label; other label content and all group content are sibling presentation leaves.
Content never participates in label order, disclosure, occupancy, or feature-command authority.

A label may name one child group. Child ownership is exclusive and acyclic, and the declared maximum nesting
depth is enforced before presentation.

## 4. Reorder and layout

`DISABLED` exposes no pointer, keyboard, contextual, restored, or persisted reorder route and cannot allow
the `MANUAL` sort mode. `POINTER_AND_KEYBOARD` requires `MANUAL` and keeps equivalent pointer and keyboard
transitions. Reorder, disclosure, occupancy, orientation, overflow, and adornments remain independent.

Orientation and overflow affect presentation geometry only. Product owners declare their choices in DTDT;
the generic validator checks them but does not infer or override them.

## 5. Presentation-memory contract

The memory payload contains the whole reconstructible Shelf Workspace setup: orientation, label-box and
divider extents, group sort modes and manual order, open/closed Shelf Items and Boxes, nested-Shelf
disclosure, selected presentation identity, expanded-to-fill choice, bounded recent-use evidence, and
card/detail disclosures. A runtime restores only values that still validate against the exact contract,
owner, definition fingerprint, user/context key, and current available Shelf IDs. Invalid, stale, foreign,
or partial payloads fail closed to declared defaults; they are never partially merged.

`BROWSER_LOCAL` payloads are held only by the browser's local presentation storage and are not sent to an
application service. `USER_CONTEXT_DURABLE` payloads are held by the authenticated user's typed Talisman
context-preference store, keyed by the declared application context and Shelf definition. They may be
restored by another Talisman surface for that same user, but not by another user or by an unrelated
workspace. Neither route stores or restores feature-domain truth, drafts, database rows, jobs, credentials,
or server state.

Every open/close, disclosure, reorder, orientation, or divider action updates its applicable valid payload
atomically. A live projection refresh may redraw labels or cards, but it must reapply the accepted
presentation state rather than silently closing or collapsing anything the person left open.

### 5.1 `USER_CONTEXT_DURABLE` browser boundary v1

The reusable browser envelope is `talisman.shelf-presentation-memory/v1`, schema version `1`, with policy
`USER_CONTEXT_DURABLE`. Shelf validates it only after the public Shelf v2 validator accepts the complete
owner definition. The envelope contains exactly:

- `application_context_key`, admitted for the current authenticated person by the Application Server;
- the definition's exact `persistence_owner`, `definition_id`, and `definition_fingerprint`;
- a positive monotonic `presentation_revision`; and
- one complete normalized presentation state.

The normalized state contains every declared Group in definition order. Each Group carries its exact ID,
declared orientation, allowed sort mode, complete label order, ordered open-Box identities, a positive
bounded weight for every Box, and a nullable expanded Box that must be open. The root state carries nullable
focused Label and Box identities; a focused Box must be open and owned by the focused Label. Closed state is
the complement of the complete declared Box set, so no separate partial closed list is admissible.

The neutral browser store calls an injected adapter only through whole-value operations:

- `read(key)` returns `EMPTY` or `FOUND` with one complete envelope and opaque `storage_revision`;
- `write({key, expected_storage_revision, envelope})` returns `SAVED` with the next opaque storage revision,
  or `CONFLICT` with current storage-revision truth; and
- invalid responses, adapter failure, stale presentation revision, conflict, and foreign or partial state
  leave the last accepted state unchanged. The client does not merge, patch, delete, reset, retry, or fall
  back to browser-local persistence.

The key is exactly application context, persistence owner, definition ID, and definition fingerprint. It
contains no browser-asserted user, principal, credential, GM caller, or session identity. The Application
Server later binds that key to its admitted person and current App Session, owns authenticated transport and
durable storage, requires normal CSRF/epoch/replay checks for writes, and treats the complete JSON value as
opaque Shelf-owned presentation semantics. This Shelf body adds no HTTP route, persistence schema, or
authenticated storage implementation.

### 5.2 Complete composed-presentation boundary v2

The additive successor envelope is `talisman.shelf-presentation-memory/v2`, schema version `2`. It retains
the v1 key family, policy, revision, Group state, and focus meanings. Its key adds the state-definition ID
and fingerprint so an older record cannot block or masquerade as the successor. A separate Shelf-owned
`talisman.shelf-presentation-state-definition/v1` declaration has its own stable ID and fingerprint and is
cross-bound to the exact validated Shelf definition ID and fingerprint. The declaration contains exactly:

- a complete allowlist of selectable presentation IDs, each naming a declared REGION Shelf Label; and
- ordered component-state definitions, each naming a declared REGION ID plus complete stable divider and
  disclosure ID lists.

Changing that declaration requires a new state-definition fingerprint; changing the Shelf definition still
requires a new Shelf definition fingerprint. Component state never changes Shelf topology. A component
entry binds presentation memory to existing content in a declared region; it does not create a child Shelf,
Shelf Label, Shelf Box, feature owner, or transport route. Component REGION IDs are unique, and divider and
disclosure IDs are globally unique within one state definition.

The complete v2 state retains every v1 field and adds exactly `selected_presentation_id` and ordered
`component_states`. Selection may name an allowed presentation whose Shelf Box is closed and is independent
of focus and open state. Every declared component state contains:

- its exact REGION ID;
- one complete `divider_weights` object with positive bounded `leading_weight` and `trailing_weight` for
  every declared internal divider; every pair is a ratio summing exactly to `10000`; and
- the unique open subset of declared component disclosure IDs in their canonical declaration order.

Shelf Box weights still describe allocation between actual open Shelf Boxes. Component divider weights
describe only an existing component's internal panes. A real nested Shelf continues to persist through its
own Group state; implementations must not invent a child Group to store component geometry or disclosures.
An undeclared selection, component, divider, disclosure, extra member, or partial weight pair rejects the
complete state.

The v2 store uses the same principal-free key family and injected whole-envelope read/write adapter as v1.
It reads the extended v2 key first and, only when that key is empty before any state was accepted, may read
one completely valid v1 envelope from the legacy key. Migration is one deterministic in-memory value: every
checked v1 field is retained and only the new fields are copied from the already validated v2 default. It
retains the v1 presentation revision, performs no write, and has no storage revision for the still-empty v2
key. The next explicit successful save increments the presentation revision exactly once and writes one
complete v2 envelope with a null expected storage revision. A v1 envelope found under the v2 key is a
rejected downgrade. Any non-empty but invalid v2-key read or envelope fails closed and never falls back to
the legacy key; corrupt or stale successor state cannot resurrect an older v1 value. Presentation revision
is Shelf-owned semantic history. Storage revision is the adapter's opaque optimistic-write token, and only
the former crosses the in-memory v1 migration.

This successor adds no authenticated transport or storage implementation. The Application Server continues
to treat either complete envelope version as opaque Shelf-owned bytes under the same admitted principal,
application-context, key family, CSRF, epoch, replay, optimistic-revision, and size boundaries. It accepts
the two v2 state-definition key members on the existing route and store; it must not add a second route,
store, or presentation-state interpretation for v2.

## 6. Object Factory binding

Object Factory is the first v2 consumer. Its owner DTDT declares the three peer drawers, the nested Shader
labels, twist disclosure, manual peer reordering, fixed nested order, and the shared display-control group
content. Its browser-local presentation envelope carries the same v2 contract identity and an owner-specific
definition fingerprint. A stale v1 envelope fails closed to current v2 defaults.

Shelf presentation remains local UI state. Creature, Form, Rig, Native Package, database, generation,
provider, and application-service authority remain outside this contract.
