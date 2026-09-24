# MPK 0.1.13: read-only delivery with local edits

Status: migration and acceptance guidance for MPK 0.1.13. Published 0.1.12 bytes and
tag remain unchanged. Copying this document does not change a running service or migrate user data.

## Superseded assumption

Earlier Slice guidance required ordinary Tassy authoring to write the selected project database. Mark's
24 September 2026 direction supersedes that requirement: Tassy and Moonbeam delivery profiles expose a
read-only canonical SQL baseline, while browser editors save changes and new records in browser-owned
storage over that identified baseline. The local project server is the normal development workshop and
may write its explicitly selected local database. Offline disk and browser modes remain supported, but
disk export/import is not a compulsory step for each ordinary database edit.

The product progression is **work locally → assemble and rehearse on Tassy → publish to Moonbeam**.
Tassy keeps its editors running while rehearsing the Moonbeam read-only database profile. Component and
content-baseline publication retain separate receipts.

## Migration order

1. Inventory existing file, database and browser work by identity/revision/digest. Export or otherwise
   preserve it before changing runtime profiles. Do not classify authored drafts as disposable cache.
2. Add explicit trusted runtime profiles and report effective capabilities. Never infer permission from
   `127.0.0.1`, a port or a client-side toggle.
3. Make delivery-host database credentials and every direct/indirect mutation route read-only. Keep the
   privileged baseline publisher separate from editor sessions and record its package/receipt/read-back.
4. Add a baseline-bound browser overlay with schema migration, multi-tab guards, quota errors and
   portable export/import. Keep old baselines available for checked rebase/conflict/fork handling.
5. Retain the project workshop's direct supported database Save and the disk workspace. Show object
   source, stable ID/revision and Save destination; never substitute a same-named disk copy silently.
6. Add task-specific typed AI context profiles. Start with Slice Motion and Rougish Citadel-building;
   validate exact revisions, units, allowed operations and required bytes before preview/acceptance.
7. Prove Slice first, Rougish next, then rehearse Tassy before Moonbeam. Defer LAN synchronization,
   elaborate cache policy and external-tool connectors until the minimal path is accepted.

## Acceptance matrix

| Proof | Required observation |
|---|---|
| Delivery baseline | Read works; create/update/delete/import and indirect writes are server-rejected; canonical revision/content remains unchanged |
| Browser overlay | Edit an existing Morph and Place, add a local record, save, close and freshly reopen exact work over the same baseline |
| Local workshop | Explicit local profile saves the selected database object directly and reads back exact identity/revision/digest |
| Offline closure | Application and complete selected content/assets reopen without network; remote-only actions are unavailable honestly |
| Recovery | Quota/write failure preserves diagnosis; portable export/import restores authored work |
| Baseline change | Old work is preserved and checked rebase/conflict/fork is shown; no silent overwrite |
| Origin change | UI explains origin separation and offers transfer/sync; it does not claim data loss merely because another origin has another store |
| Concurrency | Two tabs detect stale saves and do not silently overwrite each other |
| AI context | Motion and building packets are self-contained, typed and revision-bound; invalid/stale/out-of-scope answers are rejected |
| SQLite integrity | Checks account for WAL and service-level read-back rather than one database-file checksum |

## Current evidence and remaining proof

A read-only GET on 24 September 2026 to `http://127.0.0.1:3002/slice/editor/storage` reported
`mode=legacy-sqlite`, `write=true`, `readOnly=false`, authority `tassy-project-database`, with the message
“Tassy saves to the selected project database.” No write was attempted. This is a declared-capability
mismatch with the new delivery profile, not proof that every mutation route is writable. Slice standalone
port 3003 refused connection and was not started. Rougish records disk-first Citadel save/reopen in its
own design evidence but was not freshly retested. Moonbeam was not inspected.

Do not claim migration complete until the acceptance matrix is exercised on the exact selected builds.
