# TAS Database Viewer — component structure

## Planned consolidation into Metamorph — 2026-09-13

The active successor is [Metamorph Gallery](<../Object Sing & Dance Factory__/METAMORPH-GALLERY.md>).
Mark requests that this page's expandable browsing and read-only card behavior become the Gallery inside
Slice's native-JavaScript Metamorph workspace, with all admitted Talisman objects, a Slice only filter and
explicit Edit replacing a selected card in place. After successful implementation/adoption, the standalone
`/database-viewer/` screen and its Talisman badge retire in favor of that shared workspace. The five typed
read capabilities and their existing authorities remain valid; screen retirement is not API deletion.
This documentation patch changes no route or runtime. The sections below describe current viewer code;
the new contract explicitly records its missing all-kind preview wiring and the absent current Slice source.

Owner: Tassy — Talisman Application Server. Body: Read-only component browser.

## Product boundary

`/database-viewer/` is a JavaScript page served by TAS, registered in its existing page manifest and
technical application register. It is one place to inspect existing game-component authorities, not a
new canonical store, an SQL console, an editor, or a replacement Morph/Place/Character model.

The left browser uses native keyboard/touch disclosures: source family, component, explicit relationship,
linked component. Its first available component opens automatically, so the inspector is visibly useful on
first load. Selecting a name opens its right-hand inspector. That inspector shows Morph, token,
icon and character-sheet links, retained attachment history, known missing targets, source identity,
revision, other relationships, and a checked preview where supported. Same-named records remain separate;
reviewed IDs and exact composite identities, never names, join them. A missing linked icon is not shown as
an available icon. Linked media identity is not proof that its bytes exist.

Search is literal stored name/identity search across the listed catalogs; it is not full-text search of
all attachment bodies. Source families can be filtered. Component and relation lists page in groups of 40,
with explicit Load more. Native disclosure focus, selection borders, labeled state colors, large controls,
Back, responsive layout and a skip link keep the structure usable without relying on color alone. The page is a
fixed-height application surface: its Tassy/Database Viewer Dev 1.2 footer stays visible, while the component
browser and the selected viewer own their separate scroll areas. The trailing Shelf takeover mark has a white
ring and green center; its horizontal bar appears only while the viewer is fully taking over. It leaves that
footer visible and gives the selected item's viewer the remaining screen; image, text and Morph previews use
their existing safe renderers, while unsupported kinds remain truthful structural views until a dedicated viewer
exists.

## Morph presentation

Selecting a current Core Morph mounts a dedicated **read-only 3D Morph** before the general preview. It reads the
existing typed Core Morph capability under the ordinary page session, then projects the stored Points, radii, and
Web surfaces on a locally orbitable canvas. Its named Stances and timed Motion keyframes alter that canvas only.
The Database Viewer neither creates a draft nor exposes a save route; the Morph authority remains the only owner of
the document and revision lifecycle.

## Covered component authorities

There are 40 allowlisted component projections: 30 source catalogs and 10 nested revision/content kinds.
There are 74 explicit directed relationship projections. These are reviewed schema mappings, not an
assertion that unknown future schemas have been discovered automatically.

| Family | Visible component catalogs | Important structure retained |
| --- | --- | --- |
| Definitions & Things | Collections, all Types/rulesets, Created Things, Thing groups | Exact Type links, membership, profile/sheet, rules features, direct presentation associations and visual fields |
| Morphs & Factory | Core Morphs, Creature-private Morphs, Factory objects, Slice-native documents | Current pointers, retained revisions, exact private master pins, native component roles and Slice manifest ordinals |
| Worlds & Places | Projects, map documents, Map Places, layers, sources, Worlds, Adventures/references, Arena residents/groups | Active map-version selection, inactive Place identities, exact document-scoped residents and reviewed references |
| Stories | Works, literary characters, recorded events, timeline sets, manuscript nodes | Reviewed game-character IDs, participants, hierarchy and stored blocks; no inferred literary-to-game name join |
| Media & appearance | Media assets, project assets, generated media, visual fields, content attachments, presentation associations | Role/state/history/provenance, reverse ownership and exact database-backed content links |

Nested readers include immutable Core/private/Factory/Slice revisions, native and Slice components,
content objects, Type features, character profiles, and manuscript blocks. Unknown collection member
kinds and unreviewed typed targets remain visible as unresolved identities rather than being dropped or
mapped to a same-named record. Raw source codes/identities remain source facts, not viewer-owned IDs.

Current Morph, Factory and Slice records follow their stored current pointers, not maximum revision.
Place detail follows the map document's active version, including an Undo-selected older version.
A private Morph's pinned master stays pinned. Type defaults are accessible through the explicit Source
Type link; the viewer does not invent an effective inheritance algorithm.

**Coverage limits:** only the database selected by this TAS host is read. Slice-native imports and any
Slice-origin Types already in it are covered through those existing authorities. A separate Slice
developer database is not opened or federated; this Main supplies no reviewed adapter for it. Runtime-only
objects, unreviewed schemas, provider state and file-only media are not silently counted as covered.
Unavailable source schemas are reported separately from empty catalogs. Active-version terrain layers
are identifiable, but this body does not implement a terrain renderer or replace Geography.

## Service, transport and lifecycle

The browser calls five named GET capabilities under `/api/database-viewer/`: catalog, components, detail,
links and preview. They require the ordinary, transparently established App Session plus server instance and
exact manifest version/digest; there is no GM approval step because this is a read-only local viewer, not an
editor. The controller rechecks the App Session and current manifest after the read and after bounded JSON
encoding. It stamps successful output with the existing six server/workspace epoch fields.

`DatabaseViewerApplicationServerGateway` validates the closed query shape and projects typed service
records into path-free JSON. `DatabaseViewerReadService` is an immediate UI-independent read service;
there is no semantic operation, provider job, event subscription or reason for an artificial bus round trip.
The existing TAS HTTP worker performs the query. No EDT/JavaFX bridge or new executor is introduced.

The database path comes only from TAS's existing DatabaseService composition. Every read opens the
existing file using SQLite URI read-only mode, installs query-only, opens one read transaction, and rolls
back/closes it. No schema-initializing repository constructor, migration, attach-database statement,
managed-file resolver, bundled fallback, save route or persistence authority is introduced.

The browser maintains volatile page state only. New selections cancel superseded request channels;
sequence guards also reject stale responses when a transport ignores cancellation. Session expiry or stale
epochs clear the tree, selected detail, preview and navigation history. Announced session expiration has a
local timer; there is no polling or local/session storage. Page close disposes requests and preview observers;
client abort is best effort and **does not claim server SQL cancellation**.

## Query and preview bounds

Page size is 1–100, default 40. Search is at most 160 characters; it treats `%`, `_` and SQL punctuation
literally. Source, relationship and query-field names are allowlisted. Keys use canonical base64url over
bounded UTF-8 identities; composite keys use JSON arrays rather than ambiguous separator concatenation.
Facts, labels, link notes, role summaries and encoded HTTP responses have separate bounds.

Each statement requests a two-second JDBC query timeout, with a short SQLite busy timeout. Catalog
collection checks an eight-second budget between sources. These are requested driver/between-query
bounds, not a proven hard wall-clock interruption deadline. Native driver timing/locking remains a host
gate. Counts and detail/expanded pages are separately observed read snapshots, not one indefinitely held
global transaction. Refresh rereads explicitly after another tool changes data.

Previews accept only the exact selected component version. Changed selection content fails rather than
substituting newer data. Database content must match both its stored byte length and SHA-256. Slice
manifests must be checked valid component documents, including when a nested part is fetched directly;
manifest/component link mismatch stays visible and refuses preview.

PNG/JPEG/GIF/WebP delivery is bounded to 4 MiB; safe text/JSON/Markdown/YAML/OBJ previews are bounded to
256 KiB. Unsupported formats remain identifiable, with no invented fallback. Markup uses text nodes,
never HTML execution. No arbitrary file path, provider call, iframe or executable content is opened.
The generic stored-text Core Morph preview remains a labeled rotatable **topology schematic**, not a meshed or
textured render. The dedicated current-Core-Morph viewer instead reads its typed current document and projects its
stored radii and Web surfaces as a view-only form. Audio/video playback and native STL/OBJ mesh rendering are not
implemented in this body.

## Focused verification

The patch-worker environment provided Java 21, Node and Python SQLite. It had no configured Java 25
compiler/dependency cache and the Gradle wrapper distribution was unavailable offline. Chromium blocked
HTTP navigation. Consequently the full TAS Java body, native Xerial driver, production HTTP/CSP/module
loading, managed binary adoption and physical iPad operation were **not** claimed verified.

Executed successfully against synthetic data only:

- 101 production Java read-service/gateway checks through a test-only JDBC wire seam to actual Python
  SQLite, including byte-for-byte unchanged fixture files. This is real production SQL and projection
  logic, not proof of the native JDBC driver's timeout/locking implementation.
- 19 Node browser-client/topology tests, including all epoch stamps, stale selection, admission clearing,
  bounded responses, safe retries and the existing Ancient Dragon's 74 points and two explicit Webs.
- 12 Python schema/manifest/integrity tests, including preparation of all 40 component and 74 relation
  queries against owner-derived DDL, missing schemas, corrupt content and parent-manifest validation.
- 14 Chromium offline DOM checks using the real Java gateway and synthetic admission: disclosure and
  pagination, duplicate names, text/image/topology previews, revoked access clearing, desktop/tablet/phone
  layouts and no horizontal page overflow. Screenshots were reviewed. Offline injection uses unchanged
  module bodies in isolated scopes but does not prove HTTP assets, CSP or production authentication.

Reproduce the dependency-light checks from the repository root, writing all generated output outside
tracked source:

```bash
scratch=$(mktemp -d)
pkg=src/main/java/com/moondance/talisman/app
jpkg=src/test/java/com/moondance/talisman/app
javac -d "$scratch/classes" \
  "$pkg"/database/componentviewer/*.java \
  "$jpkg"/database/componentviewer/ViewerSqliteBridge.java \
  "$pkg"/services/app/applicationserver/DatabaseViewerApplicationServerGateway.java \
  "$pkg"/services/app/applicationserver/ApplicationServerJson.java \
  "$jpkg"/services/app/applicationserver/DatabaseViewerOfflineProbe.java
PYTHONDONTWRITEBYTECODE=1 python3 src/test/python/database_viewer_fixture.py "$scratch/synthetic.db"
java -cp "$scratch/classes" \
  com.moondance.talisman.app.services.app.applicationserver.DatabaseViewerOfflineProbe \
  src/test/python/database_viewer_sqlite_bridge.py "$scratch/synthetic.db"
node --test src/test/js/database-viewer/client.test.mjs
PYTHONDONTWRITEBYTECODE=1 TAS_VIEWER_TEST_CLASSES="$scratch/classes" \
  python3 -m unittest discover -s src/test/python -p 'test_database_viewer_sql.py' -v
```

Optional browser proof requires Python Playwright and Chromium already installed. Run the checked-in
browser test with `--classes "$scratch/classes" --output "$scratch/browser"`; `--chromium` selects the
installed executable. Use `--dom-only` only for the explicitly weaker, network-free DOM proof described
above. Do not install/download dependencies or open a live database as part of these tests.

On the configured host, run only the new native/HTTP tests, the manifest class, and the existing focused
Atlas gate before acceptance:

```bash
./gradlew --offline --no-daemon test \
  --tests 'com.moondance.talisman.app.database.componentviewer.DatabaseViewerNativeReadTest' \
  --tests 'com.moondance.talisman.app.services.app.applicationserver.DatabaseViewerHttpProofTest' \
  --tests 'com.moondance.talisman.app.services.app.applicationserver.ApplicationServerManifestStoreTest'
./gradlew --offline --no-daemon validateDevelopmentArchitectureAtlas
```

Use the repository's documented Java 21 Gradle host with its configured Java 25 compilation toolchain.
The native tests check read-only URI handling, a missing database remaining absent and unchanged bytes.
The in-memory HTTP tests cover admission before SQL, revocation/epoch changes during a read, and no
POST/PUT/DELETE viewer dispatch. They start no server. These JUnit additions are supplied for the host
but were not executed by this patch worker.

## Adoption and operator acceptance

Apply the patch through the repository patch-ZIP procedure. This body changes Java host composition;
static publication alone does not make the new routes available. The responsible owner must rebuild
and adopt/restart the managed TAS Java body under its existing deployment procedure, then confirm the
viewer page/capabilities and ordinary page-session flow. No restart, Main merge/push, private database inspection, schema
change or provider access was performed while making this patch.

After host adoption, check one real named component in each installed family, retained/current pins,
links with missing media, and the specific Slice import represented in that selected host database.
Record any unmapped authority as an explicit follow-on mapping, not as a silently empty family. Full
mesh/media renderers, separate Slice-database federation and editing are distinct future bodies.
