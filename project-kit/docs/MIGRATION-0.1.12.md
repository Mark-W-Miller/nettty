# Adopt MPK 0.1.12

Update using the published-release installer. `VERSION-0.1.12.md` identifies the copied Kit.

Review [3D terrain navigation and nested Place continuity](../contracts/terrain-navigation.md). Consumers
with a 3D view should compare their current controls, orbit-center behavior, cursor zoom, child-detail
loading, seam treatment and Place transitions with the new contract. Preserve project-specific controls
only where they are deliberate and documented; do not treat copying the Kit as runtime adoption.

For each affected view, record focused evidence for real pointer routing, rendered focus/zoom behavior,
failure and reversal, authored dirty-state separation and save/reopen. Database-backed Places still need
their native owner adapter, guarded acceptance receipt and canonical read-back.

Review [stable authoring state](../contracts/authoring-state.md) and the
[Slice curation record](SLICE-FINDINGS-0.1.12.md). Compare local findings item by item and retain their
provenance while recording promoted, local, deferred and unresolved dispositions. Confirm that:

- Save stays within its authority and Import uses exact guarded requests plus recovery/read-back;
- feature/raster presence, renderer support and visual acceptance are reported separately;
- stale asynchronous reads cannot commit identity or reset drafts, camera or newer receipts;
- controls edit the displayed structural source and tests exercise real layout/geometry/consumer paths;
- motion Preview/Add/Save, rule outcomes and animation state remain distinct;
- coarse parent Places, detailed children and shared instance projections are not confused with crops.

This release also includes the active-development-safe MPK publisher update made after 0.1.11. Delivery
may update the exact vendored Kit on a repository's `origin` default branch while unrelated staged and
working files remain in active owner worktrees. `project-kit-local/` remains separate and is never swept
into the delivery commit.
