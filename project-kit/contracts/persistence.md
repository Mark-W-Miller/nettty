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

## Architecture decision: keep SQLite locally and on Tassy

Remain on SQLite unless measurements demonstrate need for multiple concurrent writers that cannot be
served acceptably by serialized transactions, multi-server scale, or unacceptable graph-query latency.
Measure contention, write throughput/latency, traversal depth/fanout and query plans against agreed
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
