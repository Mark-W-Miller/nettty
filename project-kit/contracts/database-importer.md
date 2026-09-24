# Portable database packets and canonical importer

This contract applies to any project that creates or edits Morphs, Places, spaces, objects, characters,
views, tools or other database-facing content, including Rogish (Rougish in older kit notes), Insect Wars, Slice, Dwarf War /
Dwarven Warfare Simulator and Moondance/Talisman tools.

## Database-compatible before database-connected

A project is database-compatible before it is database-connected. Even when a chat or browser has no
access to the database, the project should shape its core data so it can later be admitted by the shared
canonical importer.

Storage location is not identity. The same Morph, Place, View or object may be carried in browser
`localStorage`, a local file, a patch, a project package, a virtual repository, local disk or the
canonical database. Moving it must preserve stable identity, metadata, provenance and canonical shape.

## Operating modes

A project may support any or all of these modes. Not every project must support all three immediately,
but its packet/export design should not block them.

### Mode 1: patch-only / chat mode

The authoring environment has no database access and no reliable filesystem. It can still produce
repository-compatible patches, JSON packets, fixtures, schemas or package files. These outputs must carry
canonical IDs when known, NEW/UPDATE intent, domain metadata and enough shape for later importer review.

### Mode 2: local runtime mode

The project runs in a browser, local server or local filesystem environment. It may store work in
browser `localStorage`, project folders, export packages, virtual Git patches or local files. Direct
canonical database access is absent or optional, so export/import packages are the bridge.

On Tassy or Moonbeam delivery profiles, browser-owned storage is the ordinary edit destination and
canonical database mutation is unavailable to editor clients. A package may still be exported for
review; Import cannot be used as a hidden delivery-host write path.

### Mode 3: direct database mode

The project runs in its explicitly selected local development workshop with direct access to the canonical database through the approved local service
surface. Checked writes, readback, guarded updates and review receipts are required. Direct access does
not bypass the canonical importer/admission rules.

## Canonical content concepts

A **Morph** is the universal acyclic articulated/spatial content definition. It may be as small as one
control point with no geometry or as complex as an acyclic skeletal/control-point construction with
geometry, skin/renderable stocking, behavior and views.

A **Place** is a database component/domain view whose defining geometry includes a controlled height map.
A Place may also carry texture map, background color, size, scale, nested spaces, water surfaces,
procedural geometry and defaults such as a deliberately small flat numeric space.

A **Geometry** may be fixed mesh/shape data, height map, aligned texture-backed terrain, control-point
surface, procedural/code-driven geometry, water surface, animated geometry or another supported form.
Rendering caches and previews are projections, not authoritative geometry.

A **Domain** organizes human understanding: Slice objects, Insect Wars things, Dwarf War caverns,
Rogish spaces and so on. Domain membership is not a hard database boundary. The database must also
support global views such as all Places, all Morphs and all objects regardless of origin.

A **Space inside a Space** may represent a higher-resolution version of part of a larger space. Edges of
an unresolved high-resolution region may show the broader lower-resolution scheme translucently. This is
a supported authoring/composition concept, not a separate private storage dialect.

For combat-scale interaction, do not require finer default granularity than a coarse 5x5 square/cube or
voxel. Average humanoid Morphs fit approximately in that scale. Collision defaults to coarse gameplay
interaction unless a project explicitly declares a finer physical contract.

## Shared canonical database importer

There should be one shared **Canonical Database Importer** screen rather than bespoke importers per
project. The preferred strong runtime is a controlled local authoring/system-administrator profile with
direct access to registered projects and the canonical database. It may use Talisman Application Server
code, but it is not the Tassy delivery profile. Tassy rehearses the Moonbeam read-only baseline and does
not expose importer writes to ordinary editor clients.

The design must still allow weaker situations: a project-local screen may validate/export packages, and
offline tools may prepare an import report without committing to the database.

## Project registration

Projects register with the importer. A registration declares:

- project/domain name;
- object types produced;
- import and export support;
- packet locations or handoff folders;
- adapter/normalizer identity;
- canonical ID strategy;
- whether direct writes are permitted;
- required review/admission evidence.

The importer can then list projects that have import/export/sync work pending.

## Importer responsibilities

For each incoming packet/object, the importer must:

- detect object type;
- normalize the project-specific packet into canonical shape;
- match by stable ID when possible;
- distinguish new, existing, conflicting, incomplete, stale and malformed records;
- show incoming and canonical versions side by side;
- highlight field differences and missing required data;
- show provenance, domain membership and source/admission status;
- let the reviewer choose an explicit disposition.

Supported dispositions include: import as new, update existing, do not update, merge selected fields,
split as separate object, quarantine for review, reject as malformed and export a canonical record back
to a project packet.

Project-specific formats are allowed. Canonical admission shape is not. A project fails the database
friction test if its created objects cannot be handed to the shared importer without manual
interpretation.
