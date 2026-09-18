# Complete reusable database Morph snapshot

The completed Kit must physically carry ALL current accepted Morph identities from the authoritative
database, each at its exact accepted revision, with complete native definitions and required dependency
closure: geometry, poses, motions, materials, media and provenance. Every historical revision is not
required; exact older revisions referenced by included records remain required dependencies.
Names, screenshots, source-repository examples and selected starter subsets do not satisfy this contract.

The export inventory must identify source database and authority, consistent snapshot time, selection
rule, authoritative total/count by family, every selected identity/revision, relative file paths, byte
sizes and SHA-256 digests. Record dependency edges, exact resolved revisions and included bytes. Provide
a browsable inventory grouped by family with links to definitions and dependencies. Preserve source
provenance and reuse/license restrictions. Copies never authorize mutation of their origins.

Reconcile the export against the authoritative inventory, including inaccessible families and unresolved
references. Do not silently filter failures. Missing definitions, media, motions or inaccessible families
make coverage incomplete. Unknown source totals remain unknown, never zero. Snapshot consistency must
be evidenced; an export assembled from changing live revisions cannot claim one coherent snapshot.

Read-only source export is coordinated with Slice/Tassy; MPK checks packaging and records evidence.
Native validation and importer proof remain the respective owner's responsibility. No direct live
mutation or new executable importer is part of documentation assembly.

Current status: [catalog coverage](../catalog/README.md). The release does not include database Morphs.
