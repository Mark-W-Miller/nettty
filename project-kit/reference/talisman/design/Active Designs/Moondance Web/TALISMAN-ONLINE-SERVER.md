# Talisman Online Server

## Planned application doorway change — 2026-09-13

[Metamorph Gallery](<../Object Sing & Dance Factory__/METAMORPH-GALLERY.md>) defines a design-only change
to the four-badge `/talisman/` user application page: the Database Viewer badge becomes Metamorph and opens
the same `/slice/studio.html` Browse-first workspace as Slice's editor control. It does not change the
Moondance Games homepage or the other three application badges. Existing admission still controls reads
and writes; a visible Edit button cannot grant public mutation access. Navigation cutover waits for the
working replacement; this design neither publishes a component nor changes Caddy, the server or a database.

Date: 2026-09-07

Status: controlling implementation and operations design; MW-TAS-03 is live and complete, MW-TAS-04 active

Permanent owner: **Moondance Web**

Canonical origin: `https://talisman.moondance.games/`

## 1. Product and first useful proof

The artifact is the **Talisman Online Server**. It is not a separate demo product. Its first useful proof is
small: Mark runs one campaign from the Moondance server while invited Players use ordinary browsers.

The first tranche deliberately optimizes for seeing trustworthy real screens and real Seasons data. It is
not a multi-tenant service, a commercial release, or a browser rewrite of heavyweight Java authoring.

Mark is the single root Operator and the first GM. Root may create one-use Player links or QR codes and
select Player-safe presentation. A Player may read the safe presentation and reconnect. Player mutation,
general registration, and remote-to-local synchronization do not exist yet.

## 2. Ownership

Moondance Web owns the website and the Online Server programme, including:

- the browser experience and public product identity;
- the additive Online Server Java profile;
- the one-way local-to-Moondance database publication command;
- packaging, private deployment, update, rollback, and operational evidence;
- the Caddy route at the canonical origin; and
- the current MW-TAS implementation and durable-operations bodies.

Existing Talisman services retain semantic authority. HTTP and deployment adapters may validate and route;
they do not copy feature rules into JavaScript or shell code. The former separate server patch conversation
and old many-body proposal are retired idea sources, not owners, workers, dependencies, or queues.

## 3. Roles in the first tranche

### Root Operator and GM

One preconfigured root principal represents Mark. Root is the application Operator, the only GM, the only
principal allowed to create Player invitations, and the only principal allowed to Present Player-safe state.
The root secret is a root-owned systemd credential. It never appears in source, environment variables,
browser storage, URLs, command output, or logs.

### Player

A Player receives an expiring, single-use invitation as a copied link or QR code. Redemption removes the
bearer from the visible URL and establishes a short-lived secure browser session. The Player cannot call a
mutation route and never receives Operator, GM, database, path, Source, hidden-Layer, or credential state.

### Deferred roles

Observer, separately administered GM, Java publisher as an application role, service operator as an
application role, recovery operator, public self-registration, and email access are deferred. SSH, Caddy,
and deployment access remain operating-system concerns.

## 4. Runtime shape

```text
Mark or Player browser
  -> Caddy HTTPS at talisman.moondance.games
  -> loopback-only Talisman Online Server on 127.0.0.1:4220
       -> root login, GM presentation, Player invitation, Player screen
  -> root-session gate
  -> loopback-only Talisman Service Core on 127.0.0.1:3002
       -> current ApplicationServerCommand and AppServices graph
       -> current registered HTML, CSS, and JavaScript screens
  -> typed Java intent or direct typed query
  -> existing or deliberately narrow Java authority
  -> one service-owned SQLite project database
  -> immutable Operator, GM, or Player-safe result
```

The browser never opens SQLite. SQLite is not a network server. Java owns every database connection and
every semantic change. The existing Online Server and the actual TAS are separate processes because they own
different HTTP/session responsibilities. Caddy provides one public origin and uses the existing root session
as the outer admission gate for every TAS page and capability. The desktop application, public relay, and
static game sites remain separate.

### MW-ADM-01 — public observation, one hosted administrative mutation identity

Moonbeam TAS pages, assets, declared GET/HEAD reads, and ordinary viewer interaction are publicly observable.
They may create the bounded TAS App Session needed for CSRF/epoch continuity, but that session is not an
administrator identity. Every non-GET/HEAD request, including a WebSocket Upgrade handshake, is a hosted mutation
boundary: Caddy first validates the existing `TALISMAN_ONLINE_ROOT` session through loopback `4220`, erases
any browser-supplied outer-gate marker, and writes the fixed marker only on its explicit mutation allowlist.
TAS independently accepts that marker only in `--trust-outer-gate` loopback mode. Direct, unmarked, or forged
public requests fail before managed-handler dispatch or any typed gateway.

The current explicit mutation families are Critter generation/cancel, Easy Tale manuscript commands, Body
Form save/save-as/capture, Core Morph save, GM Control admission/context/presentation-memory commands, and
non-GET/HEAD traffic to a selected managed component prefix. Lifecycle, component deployment, process
identity, local GM approval, health, and the service-key Moonbeam delta receiver are never browser admission
routes. Local/LAN Tassy remains trusted-open; the hosted-only fixture proves the lock.

`/login/?return=/` is the sole supported public post-login return. The login page accepts exactly that root
return and otherwise goes to `/gm/`; it is not a general redirector.

The Level 1 host uses the private Java 25 runtime at
`/opt/talisman-java-25/releases/openjdk-25.0.4.1-deb13u1-amd64`. The relay continues to use system Java 21.
The Online Server runs as `talisman-online-server` from immutable releases beneath
`/srv/talisman-online-server`, with private state beneath `/var/lib/talisman-online-server`, its credential
beneath `/etc/talisman-online-server`, and one root-only lock beneath `/run/lock/talisman-online-server`.

The existing service is `talisman-online-server.service`. MW-TAS-01 added
`talisman-application-server.service` without replacing it. Both remain boot-disabled. Mark approved the
exact Caddy public hostname and matching launcher card on 2026-09-06. Boot persistence and controlled reboot
remain the later MW-TAS-02 body.

## 5. Database authority and direction

The canonical local database is selected by `talisman.projectStorage.database` and defaults to
`${user.home}/.talisman/talisman-project.sqlite`. The legacy `talisman-app.db` is not the baseline. Mark
authors locally and periodically replaces the online baseline.

The Online Server retains its admitted immutable read-only database. The TAS receives a separate writable
copy at `${service.home}/.talisman/talisman-project.sqlite`, because current Application Server services and
Core Morph startup may write. The first installation clones the already admitted online database and never
points a writer at `current-database`. Later local publication may deliberately replace the TAS copy only
after a backup and quiesced service boundary. Reverse synchronization does not exist yet.

Synchronization is one way:

```text
local canonical database -> verified online backup -> Moondance immutable release
```

There is no reverse path. Future local publication may replace remote-only changes after retaining the
previous remote database as a rollback candidate.

Never copy the live SQLite file. `publish-talisman-online-database.sh` invokes the existing
`ProjectStorageCommand backup` route. `ProjectStorageBackupService` performs the SQLite online backup,
records a manifest, and verifies source and result integrity, foreign keys, current schema, content-object
hashes, byte count, and SHA-256.

The local publisher then:

1. verifies the exact clean Java artifact and embedded release;
2. creates one private temporary online backup;
3. admits it through `TalismanOnlineDatabaseCommand`;
4. proves the host has space for the candidate, predecessor, validation, and operating reserve;
5. uploads only the backup, verifier artifact, and reviewed installer through Mark's SSH key;
6. invokes the root-only remote promotion transaction; and
7. deletes temporary local and remote transfer copies after a terminal result.

The remote installer verifies the expected current identity before mutation, serializes with application
release work on the one deployment lock, independently admits the candidate, and publishes it beneath
`database-releases/<database-sha256>`. The release contains exactly `project.sqlite` and a five-line
root-owned manifest. `current-database` and `previous-database` are direct absolute selectors.

If the service is active, promotion stops it, changes the selector, starts it, and requires health to report
the new exact database SHA. Failure or interruption restores the proved predecessor and prior enabled/active
state. Ambiguous rollback leaves the Online Server stopped and disabled. If the service is inactive during
the one-time name migration, database selection completes without starting it; the subsequent reviewed JAR
installation performs the first exact health gate.

The root-only publisher fully hashes and checks SQLite, foreign keys, current schema, content-object count and
bytes before creating the immutable database release. At startup `TalismanOnlineDatabaseAuthority` follows
exactly one direct selector, admits its bounded release manifest, opens SQLite read-only, and verifies the
schema plus matching backup identity without repeating those multi-gigabyte scans. Health exposes only
bounded database identity: backup ID, project ID/generation, schema, content count/bytes, Merkle root, file
SHA, and file size. It exposes no path, SQL, row content, or credential.

## 6. Current body queue

Only one body is active at a time. Each independently valid body receives focused proof, one prefixed commit,
branch publication, Switchboard handoff and automatic landing, and permanent-owner equality before the next
body starts. The old MW-HOST and MW-ONLINE bodies are completed history and no longer appear in the active
queue.

### MW-TAS-01 — Online Talisman Service Core

Install the actual current `ApplicationServerCommand` and `AppServices` graph on the Level 1 host with its
complete registered manifest/assets tree. Expose the inventory, Critter Image Batch, Critter Gallery,
Creature, Easy Tale, and Shelf screens through the existing root login. Give TAS its own writable clone of
the admitted Seasons database. Preserve the existing Online Server, Player, and rollback path. GM Control,
general registration, reverse synchronization, boot enablement, and host reboot remain excluded.

The browser doorway lives on the authenticated root/GM console. Caddy sends only the explicit TAS page,
asset, and declared capability route allowlist to port `3002`; `/api/online/*`, `/player/`, invitation, SSE,
and every other route remain on port `4220`. Application lifecycle and private health routes are not public.
HTML, CSS, and JavaScript are installed as an immutable page release so later screens can be replaced without
restarting Java; Java changes still require a process restart.

The hosted service relocates the Critter artifact roots into its private writable state directory. The first
release intentionally started without Mac-only Critter media. MW-TAS-03 subsequently published the complete
canonical database/media closure into the hosted content-object authority and proved real current assets.
The immutable release also includes the tracked SRD 5.2.1 monster definition consumed by that catalog; it
does not rely on an external checkout.

### MW-TAS-04 — Current TAS Mirror Release

Publish current Main's TAS graph and all current registered browser screens without changing the local TAS
development experience. Local/LAN TAS retains its current App Session plus trusted-local GM request,
approval, and redemption flow. The hosted unit opts into a distinct outer-gate mode and must bind to exact
loopback; the process rejects that mode on its normal all-LAN-interface bind.

Caddy remains the sole public authentication boundary. After the existing Online Server validates Mark's
root session, Caddy overwrites one private request marker before forwarding an allowlisted TAS route. Hosted
TAS accepts that marker only from loopback and projects a fixed `moondance-root` GM caller bound to the
current App Session and server instance. The browser still uses the existing HttpOnly App Session, CSRF,
manifest/server epoch, idempotency, typed gateway, and Java service/domain guards, but it sees no second
password, approval screen, or identity ceremony. A direct browser-supplied marker cannot bypass Caddy's
root check because Caddy replaces it, and direct public access to port `3002` remains impossible.

The public allowlist adds only current manifest pages, assets, and browser capabilities. It excludes GM
admission-request creation, local admission listing/approval, lifecycle, health, and process identity.
Deployment retains the current writable database and complete media closure, takes recoverable release,
unit, Caddy, and database backups, restarts only TAS, and leaves both services boot-disabled.

### MW-MOONBEAM-01 — Asymmetric Server, Component, and Database Releases

Moonbeam now has three intentionally independent publication streams:

1. **Server code** installs one immutable current-Main JAR plus its declared baseline page closure, swaps
   only the server release and unit, restarts only TAS, retains the exact working database file identity,
   and keeps the prior server release as rollback. It neither packages nor publishes a database.
2. **Managed components** install one immutable bundle into the already-running TAS managed-handler runtime.
   Selection is readiness-gated and next-request atomic. Ordinary component publication does not restart
   Java and does not invoke database publication.
3. **Database content** remains a separately authorized one-way Tassy-to-Moonbeam release. A component may
   be installed before its database dependency exists, but it must remain unselected until honest readiness
   succeeds against a separately published compatible database.

`publish-tassy-server-code.sh` owns the first stream and
`publish-tassy-managed-handler.sh` owns the second. Both reject undeclared macOS AppleDouble sidecars,
retain boot-disabled operation, and print bounded terminal receipts. The existing database publisher alone
owns the third stream.

### Tassy server version in every Moonbeam receipt

Every server, component, or database publication report names the destination first and reports the **Tassy
server version** separately from page and component versions. The server version is the canonical packaged
`TalismanVersion.releaseIdentifier()` — currently `Alpha 1.94` for the current Main server body — never the
Tassy Home page's development label and never a managed component's version.

Each receipt therefore states, in this order:

1. destination and publication stream (`server`, `component`, or `database`);
2. Tassy server version and immutable Main release identity;
3. active JAR digest, manifest version, and server instance when the server is involved;
4. selected component ID/version/digest only for a component release; and
5. database outcome stated explicitly (`retained`, `published`, or `not touched`).

This makes it impossible to mistake a successful Slice, Dwarf War, or page update for a Tassy server upgrade.

The public same-origin manifest receipt exposes that canonical identity as `release_identifier` on
`GET /api/manifest`.  `GET /api/process-identity` exposes the same field for direct local operations, and
`GET /api/health` repeats the exact process receipt under `process`.  `manifest_version` remains a separate
runtime-manifest epoch and must not be presented as the Tassy server version.

### MW-TAS-02 — Durable Online Operations

After Mark accepts the real TAS browser proof, add reviewed boot persistence and prove a controlled VPS
reboot. Exercise guarded core, screen, and database updates; predecessor rollback; backup/restore; and
disaster recovery. Add the first bounded continuous-integration publisher while keeping production promotion
explicit.
Measure the safe Level 1 service envelope before increasing concurrency or enabling external providers.
Cold database admission on the Level 1 disk may take several minutes, so install, database-promotion, and
rollback health gates allow at least 30 minutes while still requiring the exact ready identity.

## 7. Level 1 capacity

The 2026-09-03 audit found Debian 13 on one CPU, 1.9 GiB RAM with no swap, and a 25 GB disk. Builds occur
off-host. The separate TAS JVM uses a 128 MiB initial heap, 640 MiB maximum heap, 864 MiB systemd soft
memory limit, and 896 MiB hard limit. The first full cold start stabilized below the soft limit with no
systemd maximum-memory event or OOM kill while the existing Online Server remained healthy.

The canonical local database measured `3,230,330,880` bytes on 2026-09-06. Three uncompressed copies need
about 9.7 GB. The current host had about 21 GiB free before MW-ONLINE-04, so the body appears to fit, but the
publisher must remeasure immediately before transfer and retain at least 1 GiB beyond two candidate-sized
copies. The projected future 50 GB world does not fit this server.

## 8. Security and release gates

- Java binds only to loopback; Caddy owns any future public TLS.
- The root secret remains a root-owned systemd credential and is preserved through the name migration.
- Root and Player cookies are `Secure`, `HttpOnly`, and `SameSite=Strict`.
- Root mutations require a session-bound CSRF token.
- Player invitations are random, expiring, single-use, and stored only as digests.
- Root and Player projections are different Java types.
- Deployment health binds exact process, Git release, JAR digest, and database identity.
- Release and database selectors are immutable, direct, checked, and serialized by one root-only lock.
- Existing Caddy, DNS, relay, game sites, and the local databases are never implicit deployment targets.
- No broad test suite, provider, Python authoring, desktop launch, or reverse synchronization belongs here.

Source completion and private installation did not authorize the public route. Mark granted that separate
approval on 2026-09-06 for the exact canonical hostname and launcher card. Boot enablement and a controlled
reboot remain separately visible MW-ONLINE-06 safety gates. Every host mutation begins with an exact
recoverable backup.

## 9. Architecture route

- Typed intents: checked publication plus one fixed Operator action with expected application/database
  identity.
- UI-independent adapter: the local publisher owns backup, admission, capacity, transfer, lifecycle routing,
  and terminal receipt assembly.
- Authorities: `ProjectStorageBackupService` owns the local online snapshot and integrity; the remote
  installer owns serialized immutable promotion; `TalismanOnlineDatabaseAuthority` owns process
  admission/read identity.
- Real presentation: `TalismanOnlineSeasonsService` owns the bounded direct read projection;
  `TalismanOnlinePresentationService` owns Present revision/replay and Player-safe fan-out.
- Result: immutable publication and health identity with no source path or secret.
- Threads: CLI and deployment work stays off Swing EDT and JavaFX; HTTP uses bounded server workers.
- Stale/replay: expected-current database identity rejects stale publication; same digest is idempotent;
  deployment markers and direct selectors recover interruption; changed reuse fails closed.
- Cancellation: temporary backup/transfer may stop before promotion; cancellation is unavailable after the
  locked stop/select/start transaction begins.
- Disposal: publisher staging is removed, HTTP streams close with their clients, and controller shutdown owns
  its executor and process-memory sessions.
- Focused proof: database/catalog authority, controller/presentation, static deployment contract,
  isolated release/database/Operator transactions, shell/Python syntax, Atlas validation, and private host
  evidence only.
