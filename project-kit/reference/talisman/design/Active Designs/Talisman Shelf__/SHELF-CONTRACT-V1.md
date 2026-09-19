# Shelf Contract v1

Status: landed design input; superseded for v3 vocabulary and declaration shape

Owner: Talisman Shelf Redesign

TSR-02's [`DTDT-V3-SHELF-DESIGN.md`](DTDT-V3-SHELF-DESIGN.md) is the current language decision. It uses
only Shelf, Shelf Label, and Shelf Box as Shelf product nouns. This file's `ShelfLabelBoxDefinition`,
`ShelfGroupDefinition`, `SurfacePolicy`, generic surface terminology, and v1 fixture identifiers must be
replaced or removed before any v3 adapter is accepted. They are not compatibility aliases for DTDT v3.

## 1. Purpose and boundary

Shelf Contract v1 defines one behavioral vocabulary for native Workboard JavaScript and an eventual
Talisman Java implementation. It describes presentation state and expected outcomes without prescribing a
DOM, CSS, Swing, JavaFX, bus transport, service, or persistence implementation.

The companion
`shelf-contract-v1.fixtures.json` (Talisman source reference: `shelf-contract-v1.fixtures.json`)
contains synthetic machine-readable scenario vectors. The fixtures are independent of the missing P6
Java-impression artifact. They neither reconstruct that artifact nor authorize P6, J1, J2, Java, or DTDT
work.

Shelf actions are local presentation intents. Feature leaves continue to use their existing typed Behavior,
adapter, service, revision, persistence, cancellation, and result authorities. A Shelf transition never
claims feature-operation acceptance, cancellation, rollback, commit, or success.

## 2. Canonical concepts

### `ShelfLabelBoxDefinition`

Defines one stable label and its optional expanded surface. It contains:

- a stable label ID, localized-name key, and stable definition position;
- its owning group ID;
- one `SurfacePolicy`;
- deterministic label, surface-entry, and Close-control focus identities; and
- accessible label, expanded-state, position, and transition-announcement identities.

The label remains the activation and reorder identity. A surface is never an independent order authority.

### `ShelfGroupDefinition`

Defines one ordered collection of labels. It contains:

- a stable group ID and declared label IDs;
- allowed and default sort modes;
- one occupancy policy;
- replacement peers or takeover scope when required; and
- a stable group position in the enclosing Shelf.

Sort mode and occupancy are independent. A sortable group may be multi-open, and a fixed group may use a
replacement policy.

### `ShelfDefinition`

Defines the complete Shelf identity, its ordered groups, definition fingerprint, presentation-persistence
owner, and responsive layout policy. Group membership is exclusive: one label belongs to exactly one group.

### `SurfacePolicy`

Defines whether a label expands and where that surface is placed:

- `REGION` names one region, placement axis, and deterministic entry focus;
- `NONE` has no open state, expanded accessibility semantics, region, or Close control.

Expansion target and placement are orthogonal to occupancy. A region does not imply multi-open,
replacement, or takeover behavior.

Every `REGION` surface uses its label as a toggle and has an explicit top Close control. Both close routes
produce the same closed presentation state and return focus to the owning label unless an owner-approved
dirty-close guard rejects before mutation.

### `ShelfPresentationState`

Contains only reconstructible presentation truth:

- contract, Shelf, definition-fingerprint, and persistence-owner identities;
- the active sort mode per group;
- exact Manual order per group only while that group is `MANUAL`;
- bounded recent-use sequence per sortable group as local ordering evidence;
- open and visible surface IDs in authoritative resolved-label order;
- retained occluded open state during takeover;
- stable focused identity; and
- a monotonic local presentation revision.

Activity rank, localized display text, feature-domain state, toolkit objects, exceptions, credentials,
paths, and service instances never enter this state.

## 3. Sort modes and resolved order

| Mode | Resolved label order |
| --- | --- |
| `FIXED` | Declared definition position, then stable label ID. |
| `RECENT_USE` | Last successful use sequence descending, then normalized Name, then stable label ID. |
| `ACTIVITY` | Comparable activity rank descending, then normalized Name, then stable label ID. |
| `NAME` | Normalized Name ascending, then stable label ID. |
| `MANUAL` | Exact checked Manual order. |

Name normalization is Unicode NFKC, surrounding-whitespace removal, and locale-neutral case folding. A
runtime may retain the original localized text for display, but only normalized text participates in the
technology-neutral comparison.

The Workboard activity projection maps active/working to rank 2, waiting to rank 1, and idle to rank 0.
Activity is read-only owner/domain projection input. It is never persisted as Shelf presentation state.
In `ACTIVITY`, a refreshed projection re-resolves label order immediately. Open-card order is the resolved
label order filtered to open IDs.

Focus follows stable identity rather than position during an automatic re-sort. If the focused label moves,
the fixture outcome includes a structured `SHELF_AUTOMATIC_MOVE` announcement with label, group, position,
and set-size identities.

## 4. Reorder transition

A successful pointer or keyboard reorder in `MANUAL` applies one checked move to the current Manual order
and persists the resulting state once.

A successful reorder from `RECENT_USE`, `ACTIVITY`, or `NAME` is one atomic transition:

1. materialize the currently visible resolved order;
2. apply the checked move to that order;
3. switch the group to `MANUAL`;
4. install the moved order and one new presentation revision; and
5. persist the accepted Manual state once.

No observer may see a Manual mode with the pre-move order or an automatic mode with a persisted Manual
order. Pointer Escape, pointer cancellation, an invalid target, a drop outside the label box, or a keyboard
boundary no-op changes no state, mode, revision, focus, announcement, or persisted bytes.

## 5. Occupancy

### `MULTI_OPEN`

Opening a `REGION` surface adds it without closing another surface allowed in that region. Open and visible
surface order remains the resolved label order filtered to open IDs.

### `EXCLUSIVE_REPLACE`

The policy declares a target region and replacement-peer set. Opening one target is a single committed
transition that:

- opens the target;
- closes all currently open declared replacement peers;
- preserves open surfaces outside the peer set;
- updates each affected label's expanded linkage;
- moves focus to the target's declared entry identity; and
- announces the replacement.

The scope is never global by implication. Focus inside an automatically closed peer is evacuated before
commit. Replacement does not cancel or reset domain work.

### `TAKEOVER`

The policy requires a declared screen-dominant target region. Opening it:

- opens the takeover surface;
- occludes the declared underlying region and peers without destroying their open order or domain state;
- retains the underlying open presentation state for restoration;
- moves focus to the takeover entry identity; and
- announces the takeover.

Closing the takeover reveals the retained underlying state in its prior authoritative order, returns focus
to the takeover label, and announces the reveal. Focus cannot remain in occluded content.

### Compatibility terminology

The only v1 occupancy values are `MULTI_OPEN`, `EXCLUSIVE_REPLACE`, and `TAKEOVER`. Existing owner-local
terms require an explicit adapter mapping:

- `SINGLE_CURRENT` maps to `EXCLUSIVE_REPLACE` only when the adapter declares the exact target region and
  replacement-peer set and proves the complete replacement/focus outcome;
- `TOGGLE_OPEN` describes label activation and Close parity, not occupancy; and
- `SELECT_OR_FOCUS` describes activation of an already-open surface, not occupancy.

An implementation must not add these compatibility terms to the canonical occupancy enum or infer an
occupancy policy from activation vocabulary.

## 6. Persistence admission

Persistence is device-local presentation evidence, not product or domain truth. One envelope contains the
contract version, Shelf ID, persistence owner, definition fingerprint, group modes, Manual orders, open
surface IDs, bounded recent-use sequences, retained takeover state when applicable, and local presentation
revision.

Restoration checks the entire envelope before live mutation. Unknown keys or IDs, foreign Shelf or owner,
unsupported contract, stale definition fingerprint, illegal mode, incomplete/duplicate Manual order,
`NONE` surface in open state, invalid takeover state, or non-finite/out-of-range values reject the whole
envelope. Rejection installs one fresh declared default and never salvages a partial order or open set.

Activity ranks, recent-use source events, localized names, focus, announcements, viewport geometry, and
feature-domain values are not persisted. Implementations may persist the derived recent-use sequence only
as bounded local ordering evidence; they never treat it as owner activity or feature truth.

## 7. Responsive geometry

A label box declares its visible row limit and overflow axis. The Workboard profile has two visible label
rows and vertical overflow only beyond that limit. Reflow retains authoritative resolved order.

Normal-width owner surfaces are vertical cards on a horizontal `MULTI_OPEN` comparison rail. Below minimum
card width, the rail retains horizontal scrolling. A breakpoint may change geometry only; it never changes
group membership, sort mode, resolved label order, occupancy, open state, focus identity, or the one
authoritative surface order.

Overflow containers retain pointer and keyboard reorder access, visible focus, deterministic insertion
position, and structured accessible position/set-size outcomes.

## 8. Conformance evidence boundary

The accepted Workboard dogfood run proves its current `MULTI_OPEN` profile at the exercised desktop
viewport: fixed Feature/Landed order, Recent use/Name/Manual selection, keyboard Manual reorder, persistence
reload, context-menu routes, multiple-card order, label/Close parity, and the normal comparison rail.

It does not yet behavior-prove Activity live re-sort and ties, pointer automatic-to-Manual commit or
cancellation, exclusive replacement, takeover, corrupt/foreign persistence rejection, responsive
threshold behavior, or the complete announcement/focus contract. TSR-01 records those as required shared
fixtures. A runtime becomes conformant only after it executes the same vectors through its own native
adapter and produces the expected semantic outcomes.

## 9. Implementation gates

- Workboard may add native focused proof against the shared vectors without changing this contract.
- A bounded Java body requires Mark's explicit approval before commissioning.
- The Java patch counterpart is `__Talisman Shelf Redesign`; exact artifact intake follows
  [`PATCH-BUNDLE-INSTRUCTIONS.md`](../../../PATCH-BUNDLE-INSTRUCTIONS.md).
- A patch artifact applies to the permanent owner branch first and must be independently verified.
- The unresolved P6 result artifact remains missing and must not be inferred from this contract.
