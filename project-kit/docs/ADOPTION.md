# Include and update the Kit

Copy the complete release folder to project-kit/ and retain manifest.json. Link it from the project root
README/AGENTS while preserving existing project guidance. Compare inventories and release notes for
updates; replace only the copied base deliberately and keep local additions outside it.

The adoption/ownership unit is a **Git repository, not a branch**. Branches of that repository share one
repository identity and Kit adoption responsibility. Separate worktree copies or differing pinned release
revisions do not create new consumer projects; record their exact revisions when verifying delivery.
Recipients are registered pinned project owners, including Dwarf War, Insect Wars, Rougish and Slice.

For 0.1.11 read [migration](MIGRATION-0.1.11.md) and [known gaps](DELIVERY-GATES.md). Catalog coverage
is incomplete. Record the adoption receipt; commit/publish only under the consumer’s own authority (Mark handles
Dwarf War Git/publication). Report:

- Exact Kit version and manifest content_digest; adoption date and accepting owner.
- Installed path and project source revision; preserved local extension paths and deviations.
- Documentation verification, remaining native-format/import gaps and runtime acceptance separately.

The [consumer-lock example](../examples/consumer-lock.json) is a blank receipt, not proof of adoption.
A ZIP, digest or reachable catalog does not prove database installation. Dwarf War, Insect Wars, Rougish and Slice are all full repository recipients; their confirmations are recorded separately from this release artifact.

Create or maintain the project-kit-local index and follow [local supplement upkeep/upstream handoff](../contracts/local-supplements.md).

## Provisional use before release

Unreleased candidate documents may guide provisional experiments under clearly labelled local/pending
supplements. They do not change the official release pin or establish publication/acceptance. This applied
to the earlier 0.1.8 Voice Lab draft; preserve its local provenance rather than relabelling old bytes as
this final release. Adoption of published 0.1.8 uses its exact tag, commit and final digest.

Use [the installer](INSTALLING-PROJECT-KIT.md) for verified direct project delivery. It preserves root/local
guidance and records installation separately from consumer commits and runtime acceptance.

## Shared Talisman Console

Read [the bidirectional console contract](../contracts/talisman-console.md).
All four projects use the same explicit upload, fetch, domain organization and guarded identity rules.
The console is independently accessible; local hosting is not the Talisman connection.
This is a documentation requirement, not proof of live integration.
