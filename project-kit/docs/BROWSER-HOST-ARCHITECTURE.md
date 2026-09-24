# Candidate Tassy product simplification

Status: candidate architecture direction from Mark, 2026-09-17; not implemented acceptance.
Tassy remains the Java service/database/native-integration host. Its primary user interface becomes
browser-hosted HTML/CSS/JavaScript. Any remaining native UI may be a small launcher/operator surface
(start/stop/status/open browser/configuration/recovery), or eventually none.

The unreleased 24 September direction refines authority: local project servers are the writable
development workshops; Tassy is the assembled rehearsal of Moonbeam and serves its canonical baseline
read-only to editor clients while browser-owned edits remain enabled. Browser hosting therefore does not
grant database writes. See [persistence profiles](../contracts/persistence.md) and the
[migration sequence](READ-ONLY-DELIVERY-MIGRATION.md).

Before claiming Java UI removal, inventory existing screens/operator functions, native integrations,
authentication/permissions and user-data flows; identify replacements and remaining dependencies.
Plan any required data/configuration migration and recovery, preserve existing user state, implement
browser equivalents with accessible behavior, and verify served versions plus browser acceptance.
A candidate direction does not authorize deleting Java UI or migrating live data in this Kit task.

Keep the Java service contract and native content semantics stable across UI changes. Browser-hosted
UI is not a new database authority or a private Morph dialect. Local/offline authoring, operator recovery
and remote access constraints need explicit profiles; do not assume browser availability removes the
need for boot/recovery controls. Record retained native functions and their removal gates separately.
No screen inventory, replacement, migration or completed browser proof is supplied by this release.
