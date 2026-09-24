# Place package exchange and structural edits

Status: MPK 0.1.13 release contract curated from Rougish package/construction findings. It does
not define the native Place codec or authorize database writes.

## Complete export and explicit changes

A Place exchange carries complete identified records and required asset closure plus an immutable export
baseline manifest containing exact original values and asset hashes. Comparison uses stable object IDs and
content digests, never titles. Legacy id-less bindings require explicit reviewed positional/category
mapping; they cannot silently become stable identity.

A change-only return uses a typed changes document. Listed changes are authoritative; differences in
unlisted accompanying full records are not applied. Omission is never deletion, including array members.
Deletion names an exact stable-ID path. Numeric arrays are atomic for conflict detection unless an owned
scientific merge codec defines sample semantics. Empty sparse returns may be no-ops; they cannot create a
new child without complete geometry and assets.

Before commit, verify the unmodified export-baseline digest, active working fingerprint and affected
parent/child fingerprints. Planning and preview are mutation-free. No-op results create no save/history.
Failure or conflict leaves the active Place unchanged. Recovery retains before-images, operation identity
and affected records; Undo checks for later saves and preserves the state it replaces as a checkpoint.

## Geometry and structure

Packages declare physical bounds, axes/origin/units, size/depth and numeric height coverage. View
exaggeration remains projection-only. Bounds, height coverage and asset alignment must agree. Stretch or
resampling requires explicit opt-in, method and loss record; otherwise retain original world-space bounds.

Structural records preserve stable Place, building, room, component, passage, gate, marker and connection
identities. Component edits name exact component IDs and bounded transforms/deletion/damage. Recipe
redesign cannot silently rebind an old face ordinal to new geometry. Rooms/passage solids retain closed
floor, walls and roofs as required; Boolean/merge output keeps source-component identity and removes only
proved internal/duplicate surfaces. View-only roof fade, clipping aids and control points never mutate
the authored Place.

Connections retain finite physical paths, width, clearance, kind, endpoints and elevations. They do not
prove runtime traversal, collision or cross-Place native links. Terrain and structures remain separately
targeted; structural edits do not rewrite the numeric height field unless that terrain mutation is an
explicit authorized change.

New-child import creates new local Place/object identities, remaps supported internal references and
retains original database identity only as provenance. It never grants write authority to the source.
Unknown fields are retained or rejected explicitly; local projection data is not relabelled as native.

Endpoint limits are declared and measured for record count, uncompressed bytes, samples and processing
memory. ZIP size is not decoded size. Parsing/validation should move off the UI thread when measurements
require it, with visible bounded progress and cancellation. Native admission still requires the owning
codec, authorization, receipt, exact read-back and coherent activation.

