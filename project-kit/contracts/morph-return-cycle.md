# Portable Morph edit and return cycle

## REQUIREMENTS — not a claim of implementation

Dwarf War, Insect Wars and Rougish each retain a complete independent offline/local compendium for
all their adopted content and dependencies. Slice is the collector and visual proving surface. All four
must support hot deployment through the actual host contract and a supported database connection.
Source/component publication and content/database publication are separate operations and receipts.
Adopt this Kit in all three labs before further software work; this documentation does not authorize
live database mutation, deployment or unrelated source changes.

### One native definition across the cycle

The SAME complete real native Morph definitions must drive local rendering, editing, saving and the
returned database payload. There must be no separate lookalike candidate dialect masquerading as native.
Native data may produce render projections/caches, but those projections are not the semantic originals.
Pin the actual owning codecs and contract versions. Convert existing local content while preserving
appearance, geometry, placement and motion; record unsupported fields and semantic losses explicitly.
Renaming fields or interpolation labels cannot prove equivalence. Keep original sources and offline
saved experiments independently usable. Missing codec/adapter support is an implementation dependency.

### Edit versus clone

An edit to the SAME Morph keeps its stable identity. The user selects which existing database records
will be replaced/updated. Use supported revision-checked UPDATE with the selected ID and expected
revision/content digest. Supply a complete replacement definition where the receiver requires it.
A new identity is appropriate only for deliberate cloning or genuinely new content, using NEW.

Retaining immutable historical versions is compatible with replacing the current selected definition
under the SAME ID. History must not become an excuse to forbid edits or force duplicate identities.
Preserve unrelated records. Report stale/conflicting revisions for review; never bypass guards with
unconditional force or direct SQL. Selection of an overwrite policy is explicit user intent, not an
implicit permission inferred from a package filename.

### Morph-only plural return ZIP

Content-only edits return changed Morph definitions and necessary dependent motions/materials/media,
not an obligatory full application/source ZIP. A package diff selects changed records; it does NOT
mean a partial native body. Include the complete replacement body for every changed Morph when required.

The receiver-agreed manifest must carry:

- Package identity/version, native profile/codec versions, source provenance and intended destination.
- Every changed source identity and target identity, NEW/UPDATE intent, expected target revision/digest,
  native definition path, byte size and digest. NEW targets must satisfy absence/empty-guard rules.
- All required dependency identities/revisions/digests and paths. Include bytes needed for portable
  offline use and import; distinguish already accepted destination references with explicit readback.
- The selected replacement policy and reviewed scope, plus any proposed deletions ONLY when separately
  authorized and supported. Omission never means deletion. Default deletion set is empty.
- Conversion/unsupported-field evidence and exact original/returned bytes; retain originals separately.

This is a required information contract, not a newly invented accepted wire schema. The actual ZIP
layout/parser and media-admission bindings require owner implementation and a pinned compatible profile.
Do not claim that the current single-Morph endpoint accepts ZIPs or arbitrary dependencies.

### Designated automatic import workflow

Returning/plugging the reviewed package into the designated import workflow triggers automatic
validate → apply → exact readback → current-definition update under the chosen destination/replacement
policy. It must not require repeated manual per-object ceremony. The user reviews destination and
replacement scope once; the workflow orchestrates supported operations and surfaces decisions/errors.

Before any writes, validate every member and dependency with owning native validators, inspect paths,
verify bytes/digests, confirm supported kinds and guard compatibility. Reject unsupported content and
conflicts clearly. Recheck guards during each save to catch races after preflight. Where admission is
per-item, maintain durable progress/receipts and deterministic idempotency keys; replay identical requests
safely and recover interruptions without duplicating records. Never claim whole-set atomicity without
support. Expose partial progress, preserve offline originals and activate only after coherent dependency
closure and exact readback. A stale conflict requires refreshed review, not automatic forced overwrite.

## Required next implementation proof

Demonstrate a real offline local edit/save, a portable plural Morph ZIP containing deliberate NEW and
same-ID UPDATE, designated automatic import, exact identity/revision/digest readback and Slice display
of the returned content. Check visual appearance/motion preservation, original offline experiments,
unrelated-record preservation, stale conflict rejection, replay and interrupted partial-series recovery.
For all four applications, separately verify selected hot-deployed component identity/served bytes,
database connection and actual browser behavior. Do not equate connection reachability with content proof.

## IMPLEMENTED / EVIDENCE STATUS

The Kit supplies requirements and reference evidence only. Source inspection shows guarded single-item
TEMPLATE NEW/UPDATE and per-item transaction/receipt support in Talisman; see
[receiver profile](../docs/NEW-UPDATE-PROFILE.md). No plural ZIP importer, all-four hot deployment,
offline-native roundtrip, same-ID application integration or Slice visual acceptance was executed or
established by this release. The full authoritative [Morph catalog](../catalog/README.md) is still absent.

## Offline authoring and hosted authority

An explicit offline authoring context preserves native definitions and experiments independently. Where
the hosted game's authority is the database, offline content must not silently substitute for unavailable
database content. Label the context and failure clearly. App-owned records or prepared heightfields
are inputs/provenance, not evidence of shared native Place/Morph/Gallery acceptance. Pin the actual
codecs, Place/texture/media adapters and plural guarded intake; record missing support locally and
hand general lessons upstream through the [standard supplement procedure](local-supplements.md).

## Shared console integration

Use [Talisman Console](talisman-console.md) for both upload and database-to-project transfers,
independent console access, domain organization and the patcher/local acceptance split.

## Universal semantics and persistence boundary

The native language includes arbitrary Point-attached geometry, partial composable Stances, deterministic
Motions, constraints and nested instances. Stage/world compositions are Metamorphs. Pin typed/versioned
edges and evaluator dependencies in returns; do not flatten content to satisfy a narrower receiver.
Follow [Morph](morph.md), [Metamorph](metamorph.md) and [persistence invariants](persistence.md). Atomic
checked update units and coherent stage activation are required semantics; current per-item services
still do not establish set-wide transactions. Storage engine/layout never defines the language.
