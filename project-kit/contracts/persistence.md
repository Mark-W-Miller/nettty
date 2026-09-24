# Persistence invariants and database architecture

## Normative contract; physical storage is non-normative

Database engine, normalized tables, JSON/BLOB layout, content-addressed files and indexes are
implementation choices. Storage must not redefine Morph, Stance, Motion or Metamorph semantics.
The same service contract must preserve:

- Stable definition/instance IDs and immutable revisions; exact native payload round-trip, provenance
  and source/committed digests. Retain authored source bytes if an explicit native canonicalization
  creates different accepted bytes; do not silently lose semantic fields during normalization.
- Typed/versioned edges with exact endpoint identity/revision and declared attachment/relationship data.
- Deterministic traversal/resolution, ordering, cycle policy and complete dependency closure.
- Atomic checked updates of each declared update unit using revision/digest guards; retained history,
  idempotent replay, visible conflicts and recoverable materialization. Staging several per-item commits
  is not set atomicity. Publish a composition's selected head only when its coherent closure is verified.
- Well-known indexed domain views/search results for Characters, Places and other requested categories;
  explicit projection version and source revision, with detectable stale/drifted indexes.
- Immutable asset bytes/digests as part of cache and equality identity. Equal model fields do not make two
  revisions visually equal when an asset URL resolves to different bytes.

## Runtime profiles and write authority

Tassy and Moonbeam delivery profiles serve a versioned canonical SQL baseline read-only to editor
clients. Their editors remain useful: ordinary edits and new records persist in a browser-owned overlay
bound to the exact baseline identity. A disabled Save control is not the security boundary; the serving
principal and every create/update/delete/import or indirect mutation route must reject canonical writes.
A separate privileged publisher may install a reviewed baseline, but that operation is not an editor
capability and has its own package, receipt and read-back.

The individual project server is the development workshop. An explicitly selected owner-controlled
local authoring/development profile may write a selected local database through validated services, so
editing a database-backed object does not require a disk export/import ceremony for every Save. Offline
disk workspaces and browser-only work remain supported and existing disk content remains intact. Show
the opened object's source, ID/revision and selected Save destination.

Tassy is the assembled rehearsal of Moonbeam: its server and editors stay usable while its canonical
database writer is disabled. The user-facing progression is **work locally → assemble and rehearse on
Tassy → publish to Moonbeam**. Component publication and content-baseline publication keep separate
receipts. Permissions follow authenticated runtime profiles and effective capabilities, never a
hostname, port, loopback address, CORS result, browser toggle or paid-plan flag. Reaching Tassy through
`127.0.0.1` does not make its delivery database writable.

| Runtime profile | Baseline | Ordinary edit destination | Canonical write authority |
|---|---|---|---|
| Tassy or Moonbeam delivery | Versioned permitted SQL records and assets | Browser-owned overlay | None for editor clients |
| Offline browser | Retained application and complete selected closure | Browser-owned store and portable export | None |
| Offline disk workspace | Local files and retained assets | Explicit disk workspace | None unless a writer is separately selected |
| Local project workshop | Selected local database and/or files | Explicit selected destination | Validated writes granted by the local profile |
| Optional LAN coordinator | Public baseline plus private synchronized workspace | Private user store under an explicit protocol | No implied baseline write |

Save stays within the current profile and labels its destination, for example **Saved in this browser**,
**Saved to disk**, or **Saved to local database**. Import, export, synchronization and privileged baseline
publication are separate named operations. Switching profiles never silently moves or discards work.

## Baseline plus authored browser overlay

Resolve displayed content as a pinned baseline plus the user's applicable overlay. Record baseline ID,
revision/digest and codec, overlay identity/revision, authoring origin and dependency references. A local
change may be a compact typed delta or a full geometry/asset chunk; preservation matters more than forcing
every edit into a small JSON patch. New objects retain stable identities and native relationships.

Separate disposable fetched/derived cache from authored data. Cache cleanup must never remove the only
application-controlled copy of edits. Pin exact dependencies required by local work or provide a
recoverable export. A new public baseline must retain the old base long enough to offer a checked
rebase, conflict or fork choice; it must not overwrite or silently reinterpret the overlay.

Use IndexedDB or another appropriate browser file store for large authored content rather than large
`localStorage` strings. Define origin/project/user namespaces, multi-tab conflict handling and schema
migration. Browser stores are scoped to an origin and device/profile: Tassy, Moonbeam, localhost ports
and LAN addresses do not share data automatically. Login alone does not change that. Continuity requires
portable export/import or an explicit synchronization protocol.

Browser storage can hit quota, be evicted or be cleared. Request persistent storage where available,
report failed writes honestly, preserve recovery information and provide portable export. Authored
browser work is not disposable cache, and persistence permission is not a backup.

Offline claims require the retained application plus the complete selected content and asset closure.
A page left open, cached thumbnail or unresolved content ID is insufficient. Network-only capabilities,
including remote AI, remain visibly unavailable unless a local replacement is configured.

An optional paid LAN coordinator cannot read another browser's IndexedDB directly. It needs explicit
authenticated upload/download/synchronization packets, private durable storage, revision/conflict rules
and recovery. Its private physical store is an implementation choice and grants no canonical-baseline
authority.

## Architecture decision: keep SQLite for canonical local storage

Remain on SQLite for canonical local authoring and packaged delivery baselines unless measurements demonstrate need for multiple concurrent writers that cannot be
served acceptably by serialized transactions, multi-server scale, or unacceptable graph-query latency.
Delivery copies on Tassy and Moonbeam are read-only to editor clients under the profile above. Measure contention, write throughput/latency, traversal depth/fanout and query plans against agreed
budgets before choosing a migration. No benchmark or current capacity conclusion was produced here.
SQLite permits one writer per database file; its official guidance discusses client/server engines for
write concurrency and multi-server workloads. [SQLite guidance](https://www.sqlite.org/whentouse.html).

Recommended hybrid logical store: immutable native Morph payload/content blobs; relational identity,
revision and type records; a generic typed edge relation with versioned endpoints; well-known indexed
Character/Place projections; full-text and spatial indexes. These are logical responsibilities, not
mandatory SQL table names. SQLite offers [FTS5](https://www.sqlite.org/fts5.html) and
[R*Tree](https://www.sqlite.org/rtree.html); availability/configuration and projection maintenance need
implementation proof. Indexes accelerate queries; they do not replace authoritative payloads.

Keep PostgreSQL as the server-scale migration target behind the SAME service contract when measured
requirements justify it. Preserve IDs, payloads, edges, history, guards, queries and round-trip behavior
through migration. This is architecture direction, not a premature rewrite, a separate Morph language,
an installed schema or authorization to migrate a live database.

## Source-tree definitions and explicit materialization

Default/core content must retain native definitions and importable packages in the source tree, with
pinned codec/evaluator versions, complete required dependency bytes, provenance and manifest digests.
A database row is not the only source definition. Name the authority of each default and subsequent
user edit; a source update must not silently replace a user's selected current revision.

Materialization is an explicit repeatable operation: validate package/profile/closure, review target
NEW/UPDATE guards, apply supported checked writes, retain receipts, read back exact committed payloads,
IDs/revisions/edges and projection results, then select/activate the coherent result. Replay identical
content safely and report conflicts. Default seeding cannot erase experiments or bypass update guards.
No materializer, SQL schema or executable import tool is created by this documentation release.

Endpoint limits are typed by operation/content class; do not enlarge every request limit because one model
needs more space. Collision preflight, before-images, exact per-operation receipts and read-back belong in
the owning adapter. Never rewrite an imported seed manifest to impersonate later admitted assets.

Paths, Intentions and Behavior Plans follow the [persistence-neutral information contract](behavior.md).
Declare authored reusable versus transient/checkpoint ownership and lifecycle; retain exact Place and
instance references. Saved state does not bypass fresh authority checks or redefine game-system outcomes.

## Dormant campaign state and world commits

Persist [campaign](campaign-simulation.md) instance state, last-resolved times, next-due conditions,
commitments and audit records through the same storage-neutral identities/typed edges. A heartbeat
requires atomic checked world effects plus clock progress, next-due state and summary/receipt. Current
per-Morph save receipts do not establish this multi-actor transaction. Retained source Morph history
and mutable campaign-instance revisions have explicit, separate owners; never revise a definition for
each NPC event. Seed/draw logs, input revisions and undo/replay policy support reproducibility.

## Executable behavior bindings

Persist [executor](behavior-executors.md) identity/version/digest, typed configuration/events and
accepted durable state. Keep module artifacts as integrity-pinned dependencies with provenance;
import/storage is separate from activation and grants. Never persist opaque closures or engine-private
live handles as the only program/state. Restoration revalidates exact dependencies, schema versions,
authority and host policy; code/state migration is explicit and revisioned.
