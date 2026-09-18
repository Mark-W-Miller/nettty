# Project-local guidance and upstream review

## Standard project and Patcher procedure

Every consuming Git repository maintains `project-kit-local/` beside the immutable copied `project-kit/`.
Create a local README index even when there are no findings yet. Existing project-specific guidance may
remain in its current location: link it from the local index rather than duplicating competing authority.
Preserve the project's root AGENTS, identity, designs, original evidence and owner publishing rules.
The local folder is a normal part of the project/Patcher workflow, not a replacement shared Kit release.

For each supplement record owner, date/revision, adopted Kit version/digest, scope, relevant evidence,
local decision and rationale, remaining dependency/owner, and status. Distinguish:

- REQUIREMENT or proposed behavior.
- Observed source capability, with exact version or file digest and inspection limits.
- Owner-reported capability, with source/date and limits.
- Executed validation/admission/readback results.
- Selected deployment and actual browser/visual acceptance.

Do not call a proposal implemented, a candidate native-validated or app-owned data shared-catalog
acceptance. Older reports remain historical; avoid silently presenting their service status as current.
Use [the local supplement form](../lifecycle/LOCAL-SUPPLEMENT.template.md).

## During a project or Patcher pass

Read the base Kit, local index and relevant supplements alongside the active project design. Maintain
local guidance as ambiguity, missing bindings or tested lessons arise. Record the problem, local choice,
evidence and proposed shared improvement. Continue independent authorized work while naming exact
unsupported codecs, adapters, operations and acceptance gates; do not fabricate support.

Include the current local guidance and its change list in the Patcher's context and return. A source-code
handoff includes the relevant project context; a content-only Morph return remains a Morph-only package
with required dependencies. Send documentation feedback as an accompanying clearly identified supplement
or owner handoff, not by forcing full application source into every content return. Preserve previously
dispatched ZIP bytes; issue a new named snapshot or explicit supplement for later guidance.

## Handoff to Merlin, then shared review

At each completed Patcher/project pass, the project owner reviews the local changes and hands the
updated supplements and generalizable findings to Merlin. Include complete referenced evidence needed
for review, source project/revision or exact working-file hashes, prior/current Kit identity, change list,
local acceptance limits and proposed disposition. If nothing changed, state that in the return receipt.
Do not include credentials or unnecessary private payloads. Use the
[upstream handoff form](../lifecycle/KIT-FEEDBACK.template.md).

Merlin acknowledges the handoff, records each finding and reviews it with Atlas/relevant contract owners.
Each finding receives a disposition: promote to shared guidance, retain locally, defer pending evidence,
or reject with rationale. Project-specific game assignments, dimensions, asset counts and publishing
rules remain local. An owner's native contract is not changed merely by wording in a Kit supplement.
Shared guidance may describe required future behavior without claiming it already works.

## Release and adoption close the loop

Merlin packages accepted general lessons in the next version, with source provenance, What's new,
migration notes, inventory and digest. Preserve prior releases and frozen archives. Documentation release
checks concern links/inventory/bytes; they do not require new executable tools or application suites.

Deliver the actual published release to every current consumer repository in the owner registry,
including Dwarf War, Insect Wars, Rougish and Slice. A branch is not a separate repository recipient. Collect exact installation receipts:
version/digest/path, accepting owner/date, source revision and committed/uncommitted/pushed state,
preserved root/local guidance, and remaining implementation gaps. Delivery is not adoption.

On adoption, compare each local finding with the shared change list. Mark incorporated guidance as
superseded-by-version and retain its provenance/history; preserve project-specific parts and unresolved
dependencies. Avoid both deleting local decisions blindly and maintaining conflicting duplicate rules.
This procedure does not authorize database writes, runtime changes or publishing a consumer repository
where the user/owner retains those actions.

## Collecting all pinned owners — standard procedure

An owner instruction to “update the MPK” means prepare local findings for shared curation unless Mark
explicitly requests adoption of a published version. Keep released files/pins separate from proposals.
Maintain `project-kit-local/README.md` and `project-kit-local/MPK-HANDOFF.json` using the
[receipt template](../lifecycle/MPK-HANDOFF.template.json); report changes or no changes explicitly.
Follow [collection instructions](../docs/COLLECTING-PROJECT-UPDATES.md). Merlin's collector reports missing
policy elements and snapshots available notes without modifying consumers. The registry covers all pinned
owner projects, excluding Merlin and companion chats; grouped repository identity does not discard
worktree-specific findings. A bundle with gaps requires review and is not a release-ready certification.
