# Slice-native document storage

## Source-only candidate

This independent Factory body preserves the original complete Slice `{morph, appearance}` UTF-8
document. It does not convert that authority to Core Morph, a Body Form recipe, OBJ, rig, or atlas.
Existing consumers need separate explicit adaptation. Storage is not rendering compatibility.
The older Factory A–E acceptance gates remain unchanged. This body authorizes no live import,
Type replacement, alias, Character association, startup, server route, or deployment.

## Caller contract

`ObjectFactorySlicePackage.fromCompleteModel(namespace, displayName, originalUtf8, media, references)`
returns one validated immutable candidate. The original combined bytes occupy the required
`SLICE_NATIVE_DOCUMENT` role with codec `slice-complete-model-raw`. The unversioned source wrapper
is not falsely labelled as a Slice package schema. The Talisman-owned manifest uses
`talisman.slice-native-package/v1`, kind `SLICE_MORPH`.

MORPH/APPEARANCE members use `slice-derived-json-v1`; they are derived projections checked for parsed
equality to the original subtrees. Unknown outer and inner members, whitespace and ordering survive in
the original bytes. No script, URL, unknown field or codec is executed. The source schemas are
`slice-morph/v1` and `slice-appearance/v1`; required identity/revision fields are strictly typed,
nonnegative and pair-consistent. Morph units are retained, not converted.

Media entries are `Component(uid, SLICE_NATIVE_MEDIA, "image/png", "raw", bytes)`. This first bounded
profile admits PNG only: signature/chunk/CRC checks, legal IHDR fields and metadata-first edge/pixel
bounds precede bounded complete zlib validation and full decode with matching dimensions. Original PNG
bytes are retained unchanged; decoded admission does not claim rendered fidelity. The manifest and
receipt name this `png-v1` profile. Optional `SLICE_NATIVE_SOURCE`
members are inert provenance, never media or geometry.
References are `Reference("complete-model", "/appearance/regions/0/texture/url", original, mediaUid)`.
Only that indexed v1 resource slot is interpreted. Every present slot must resolve exactly once to a
typed media member. Repeated original paths must resolve to one identity. Only relative `assets/` paths
with ASCII letters, digits, underscore, hyphen and dot segments are admitted. Empty/dot/dot-dot segments,
Unicode (including composed/decomposed aliases), schemes, query/fragment, escapes and controls reject.
Other reference shapes require a separately reviewed profile; they are not guessed.

Bounds: 64 components, 16 MiB per component/combined original, 8 MiB per inner document, 64 MiB aggregate,
256 KiB manifest, 1,024 reference slots, depth 64 and 200,000 JSON nodes per document. PNG edges are at
most 8,192 with at most 16,777,216 pixels. Reads check stored length before retrieving payloads.

`FactoryNativePackageService.SliceSaveRequest(projectId, objectId, expectedVersion,
expectedFingerprint, candidate, idempotencyKey)` freezes the exact destination. New identities use the
`slice-object:` namespace, version zero and an empty expected fingerprint. Existing identities require
their exact current version and complete manifest fingerprint. Caller-selected IDs are not Type aliases.
The exact active project is required; ambiguity fails closed.

`FactoryObjectOperationService.newSliceSave(request, correlationId)` prepares the invocation; `submit`
uses the existing serialized worker and semantic registry. `OperationCompletion.sliceReceipt()` is the
immutable committed result. Pre-commit cancellation retains the existing lifecycle; after entry to the
commit phase cancellation may be too late. No EDT/JavaFX bridge, new executor, subscription or listener
is introduced. Closing a view does not cancel app-scoped accepted work.

`FactoryNativePackageService.loadSliceDocument(projectId, objectId, version)` reads one consistent
snapshot; zero selects current and positive versions select immutable history. Its candidate returns
`originalBytes()`, media, references and verified derived projections.

## Durable authority

`FactoryNativePackageService` owns the sole public persistence route; package-private
`FactorySliceDocumentStore` implements its transaction using the existing project database/content store.
Additive document/version/member/source-revision/receipt tables avoid altering legacy model-required
tables or storing fake OBJ/rig hashes. Nothing becomes a managed model or Creature association.

One transaction checks destination CAS, source revisions and exact bytes, stores immutable content and
complete manifest, appends the document version, advances its pointer, records a receipt and verifies
readback. Failure rolls all of that back. Namespace and paired source identities cannot change on an
exact Save. Lower source revisions reject; the same source revision with different document bytes rejects.
The full source-pair identity also binds the entire manifest, including media/reference identities;
changing that manifest without advancing a source revision is a collision (even a display-name change).

Same-key/different-request rejects. Exact replay survives replacement of the service/registry and
returns the original checked receipt, even after later versions. An exact-current unchanged candidate
returns NO_CHANGE without appending a document version. Its receipt is durable, not an in-memory guess.
The semantic registry remains process-local; durable domain replay does not promise cross-process
registry observation. All component reads rehash bytes and revalidate the envelope and membership order.

## Proof and isolation

`FactorySliceDocumentTest` uses invented documents and disposable SQLite only. It covers exact combined
bytes, media/unknown fields, immutable history, no-change, durable replay, collisions, stale revisions,
complete rollback, closure/path validation, corruption rejection and the existing semantic-operation route.
`FactoryNativePackageOperationTest` covers the shared legacy completion path. Atlas validation is focused.
Owner-branch results do not prove execution on Main plus the isolated patch. Patch applicability and
dependency identity against exact supported Main are separate read-only evidence, recorded at handoff.

Follow-ups, not delivered here: live operator admission, transport, Type replacement/retirement/aliases,
source synchronization policy for those Types, broader document resource profiles, consumer rendering,
or all-domain Place/Gallery atomic import.
