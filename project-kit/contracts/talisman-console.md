# Shared Talisman Console contract

MPK 0.1.4. REQUIREMENTS, not implemented-service evidence. Applies to Insect Wars (Beetle),
Rougish, Dwarf War Combat Simulator (Dara), and Slice. Existing Slice connectivity is retained;
adoption does not replace working owner integration or establish new acceptance evidence.

## Meaning and interface

The console connects to the **Talisman application server** to transfer native content in both
directions. Connection to a project's already-serving local host is not this capability. Local save
infrastructure and diagnostics remain separate. Projects retain their own runtime and offline authoring.
The shared contract specifies common behavior and terminology, not a supplied executable widget.

Use a top-level action with accessible name and tooltip **Open Talisman console**. Open an independent
Shelf rail/panel, not a subsection of the content Compendium. Game/View, Compendium and Talisman Console
can be opened and closed independently. In projects with different layouts, retain the same independent
console access and semantics. Closing its rail preserves operations and results; explicit cancellation
reports any writes already completed. Never require opening the Compendium to reach the console.

Common actions: **Connect**, **Browse Talisman**, **Bring into project**, **Upload to Talisman**,
**Review changes**, **Retry**, and **Disconnect**. Icon-only launch controls need tooltips and keyboard
names. Show destination server identity, authentication, capabilities, selected domain and content,
local changes, transfer progress, conflicts and receipts. Do not conflate connected with uploaded.

States must distinguish disconnected, connecting, authenticated/ready, authentication expired,
unsupported capability, reviewing a plan, transferring, verifying, complete and partial/failed.
An error names the failed operation and preserves local work. Reopening shows retained transfer state.

## Authority and organization

Prefer the shared Talisman catalog as storage destination, with origin and domain associations for
organization. Permit explicitly selected game-domain storage only where the owner supports it. Show
the actual destination and organizational effects before transfer. Insect Wars-origin content remains
identifiable as such; Seasons does not show it by default unless explicitly associated with Seasons.
The Talisman-wide view spans all domains the current user is authorized to see. Reuse references one
identity across domains rather than duplicating bodies merely for organization.

Origin/provenance, domain membership, native Morph type and access permissions are distinct. A shared
catalog is not authorization for anonymous/public internet access. Resolve real owner fields and filter
contracts; these semantics do not invent schema columns or authorize a permission change.

Distinguish reusable type/template, specific authored definition/variant, project binding/placement,
and transient live actor. A reusable type may evolve through guarded same-ID revisions. A named
specific variant has its own identity when deliberately created; rename alone never creates an ID.
Actors' per-frame health/position belong in supported session checkpoints, not catalog revision spam.
Type changes show affected dependents; existing projects remain pinned until an explicit coherent
upgrade. Use the native owners' actual kind/relationship contracts, not a competing type catalog.

## Common transfer plan

Every operation pins direction, server identity, local project/checkpoint identity, native contract and
codec versions, selected IDs/revisions/digests, full dependency closure, origin/domain intent, proposed
creates/updates/reuses, expected destination guards and compatibility results. Changing content,
destination, guards or policy invalidates the reviewed plan. Display unsupported kinds and missing
dependencies before any writes. Names are labels, never identity matching keys. A connection or
selection alone never transfers or activates content.

Both directions use complete native definitions, not render caches or project-specific lookalikes.
Geometry, materials, motions, behavior references and required assets retain identities and provenance.
Pin exact supported codecs; locally approximated validators do not prove native acceptance.

## Upload to Talisman

Select local definitions and dependencies, review NEW/UPDATE/no-op/conflicts and the intended catalog
organization, then explicitly apply once for the reviewed selection. Use supported owning save APIs
with fresh authorization, revision/digest guards, deterministic retry identities and durable per-item
receipts. No direct SQL, guessed proxy routes, forced conflict overwrites or inferred deletion.

Recheck guards at each write. Preserve partial results and resume safely after interruption. Do not
claim whole-package atomicity unless the owner supplies it. Verify exact IDs/revisions/digests and
organizational metadata by read-back. Admission, project/character binding and runtime activation are
separate outcomes; report pending steps. For example, an uploaded body is not yet a registered Insect
Wars critter if its game binding remains unsupported. Keep original local experiments available.

## Bring into project

Browse/search the authorized server catalog by domain, origin, native kind, name and ID. Inspect a
specific revision and dependencies. Choose **reference/use the existing identity**, **refresh a local
copy under that identity**, or **deliberate independent clone** where the owners support these modes.
Explain the consequence before apply; do not silently mint IDs or overwrite local edits.

Fetch a coherent, version-pinned definition/dependency set. Verify native compatibility and returned
digests before installing it locally. Stage the complete set, preserve a recoverable local checkpoint,
and activate only when closure is verified. On missing assets, incompatible runtime, interruption or
conflicting local edits, leave the previously usable project intact and report the blocker. A clone
gets a new identity and source provenance; it is not written back to Talisman until separately uploaded.

After local installation, place/use the content through project-owned bindings: a critter/spawn in
Insect Wars, a creature/object in Rougish or Dwarf War, or an editable/viewable item in Slice. Catalog
download alone is not proof of placement or gameplay. Distinguish installed, bound and visible states.
Fetching content does not write to the server or change domain membership. Any such change needs a
separate reviewed upload operation. Retain sufficient native content for the declared offline context;
do not silently fall back to local drafts in a database-authoritative hosted context.

## Service integration and security

Use actual server authentication/session/bootstrap, authorization and origin/CSRF protections. Resolve
cross-origin transport with the owning host, not an unauthenticated general proxy. Display the selected
server explicitly. No credentials in source returns, content exports, fixtures or receipts. Disconnect
ends this console's connection/use of credentials without deleting project content. Save, launch,
reconnect, patch apply and closing a panel must not trigger an unselected transfer.

An implementation binding sheet must identify real operations for discovery/authentication, catalog
query, native validation, versioned fetch, dependency/media resolution, guarded save, read-back, domain
classification and project binding. Record owners, contract versions, sanitized examples and error
semantics. The historical TEMPLATE save reference is not an all-kinds or universal import API.
Missing capabilities are explicit owner dependencies, never fake success or fabricated routes.

## Patcher and local acceptance

Patchers may lack the real application server. Supply the binding sheet and deterministic fixtures;
test both directions without claiming live integration. Cover authentication expiry, wrong destination,
unsupported kind, dependency failure, local/remote conflicts, changed plans, no-op replay, partial
recovery, read-back mismatch, clone identity, interrupted download and atomic local activation. Verify
zero writes on connect/fetch/save/reconnect, and independent console operation with Compendium closed.
Fixtures must be marked as simulated; native validator acceptance requires the actual codec.

Return exact source/build identities, complete project or agreed checked patch, changed guidance,
reproducible checks, fixtures, known gaps and a local integration runbook. Preserve local checkpoints.
The project owner then tests here against the actual selected Talisman server. Live writes wait for
the user's reviewed upload selection. Prove local edit/play -> upload -> exact read-back -> catalog
organization; then database selection -> fetch -> dependency verification -> local use/placement.
Exercise guarded update, no duplicate replay, stale conflict, interruption and offline preservation.
Source delivery, connection, native admission, domain organization and visible project use have
separate evidence. Shared documentation does not claim all four implementations already comply.

## Universal language compatibility

Plans and transfers preserve [Morph](morph.md) and [Metamorph](metamorph.md) semantics, exact instance/edge
revisions, partial Stance scopes, geometry/procedural dependencies and clocks. Slice and Thinian/Rougish
are different projections over that common language. The console must report unsupported profiles rather
than flatten stages, lose constraints or invent a private dialect. Physical [storage](persistence.md)
choices are hidden behind stable service contracts. This addition does not certify current receiver support.
