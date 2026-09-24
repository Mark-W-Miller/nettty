# Typed AI context

Context questions and answers are explicit, typed data exchange. Private data, hidden authority and
implicit mutation are excluded. Build the smallest sufficient self-contained request, not the smallest
payload after removing information needed to answer correctly. A receiver must not depend on hidden
conversation state, local files or IDs it cannot resolve.

## Envelope

Every provider-independent context envelope identifies:

- request/task type, instruction, target IDs and exact baseline/working revisions;
- units, coordinate frames, selected editable scope, locked content and permitted operations;
- required semantic and numeric facts, dependency identities and attached bytes when needed;
- omitted material, sampling/resolution/precision and limits that affect the answer;
- versioned answer schema, allowed mutations and validation/acceptance requirements; and
- authoring destination and current runtime profile without granting stronger authority.

Answers use the requested typed schema and cite the target revisions they address. Reject unknown IDs,
stale revisions, non-finite/out-of-bounds coordinates and operation types outside the authorized scope.
Provider output remains a proposal: preview, validate and explicitly accept it through the owning editor,
with undo. It cannot choose a stronger storage, database, tool or execution authority.

Keep context construction independent from provider adapters. Built-in adapters apply their model,
upload, token and byte limits without changing the semantic envelope. The **What will be sent** view
shows the target/scope, attachments, approximate transmitted size, important omissions and answer type.
Progressive disclosure keeps the ordinary UI compact while allowing exact review.

## Task profiles

**Motion authoring** supplies relevant control points/joints, parent frames, rest/current pose,
constraints, motion channels, timing and contact information. Include body geometry or obstacles only
when collision or clearance affects the request. Return native Motion/pose changes, not an application
rewrite or unrelated creature replacement.

**Citadel/building authoring** supplies the selected region, origin/units, terrain samples and boundary
context, existing structures, exclusions, access and foundation rules. A scoped tile plus coarse overview
may be sufficient for a local structure; drainage, routes or regional layout may require the full
heightmap. Do not crop away a determining constraint without recording the omission. Return structure
definitions/transforms and only the specifically authorized terrain changes.

Other editors define equally explicit profiles rather than sending a generic project archive. Reuse
shared envelope fields, answer validation and authority rules while selecting task-specific content.

## Bytes, references and limits

An ID is context only when the receiver has an authorized, version-pinned way to resolve its exact bytes.
A detached Patcher or provider otherwise needs the bytes or a complete derived representation. Images,
full heightmaps and geometry are permitted when the task requires them; unrelated assets are omitted.

Do not confuse archive size, transmitted bytes, decoded memory, image dimensions, numeric sample count
and model input tokens. For example, a 1024 by 1024 heightmap has 1,048,576 samples: about 1 MiB at
8 bits, 2 MiB at 16 bits or 4 MiB at float32 before metadata, while numeric JSON may be much larger.
Validate adapter-specific limits and show upload/processing stages. A large source ZIP is not evidence
that any provider accepts it as one request, and exceeding a limit never authorizes silent truncation.

Offline authoring keeps context assembly and validation available for local work. Remote AI is visibly
unavailable offline unless a declared local provider is configured. Context retention follows the
editor's authored-data rules; transcripts and accepted proposals are not silently treated as cache.

See [AI provider dialogue](ai-provider-dialogue.md) for inspection, session isolation and explicit
acceptance, and [persistence](persistence.md) for runtime profiles and authored browser overlays.
