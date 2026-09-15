# Tassy Managed Handler Runtime

Status: generic runtime and local hot-install command are implemented; no live component deployment is
claimed by this document

Owner: 🟢 Tassy — Talisman Application Server

## Purpose

Tassy is the sole public Application Server endpoint. A component may supply a complete packaged handler
that Tassy starts, supervises, and dispatches to. The handler remains the authority for that component's
requests and edits; Tassy retains the public endpoint, component inventory, lifecycle, sessions, and
deployment receipts.

This is a reusable component-runtime seam. Slice is the first example, not a special case and not a
singleton. No later component is designed here.

## Slice as the first example

Slice supplies one complete, versioned Python bundle: browser assets plus the Slice Python handler. Tassy
installs that bundle, starts its handler privately, and dispatches public `/slice/` requests to it. The
browser talks only to Tassy's Application Server endpoint.

Tassy's Java host and the Slice Python handler share the existing local Talisman project SQLite database.
Slice's catalog, models, revisions, and blobs are durable database data. The bundle must not introduce a
second Slice database or a file-based persistence fallback.

## Generic handler-bundle contract

Every managed component supplies a complete directory with `tassy-managed-handler-bundle.json`:

```json
{
  "schema": "tassy-managed-handler-bundle/v1",
  "component": {
    "id": "slice.metamorph-editor",
    "version": "1.1.50",
    "public_prefix": "/slice/"
  },
  "handler": {
    "kind": "python",
    "entry_point": "serve_tassy_slice.py",
    "health_path": "/health",
    "database_contract": "slice-project-sqlite/v1"
  },
  "access": {
    "schema": "tassy-managed-handler-access/v1",
    "routes": [
      {"path": "/*", "methods": ["GET", "HEAD"], "required_access": "READ_ONLY"},
      {"path": "/types/*", "methods": ["POST", "PUT", "PATCH", "DELETE"], "required_access": "ADMIN"}
    ]
  },
  "files": [
    {"path": "serve_tassy_slice.py", "bytes": 123, "sha256": "..."},
    {"path": "app/index.html", "bytes": 456, "sha256": "..."}
  ]
}
```

The runtime verifies every declared file, rejects links and paths outside the bundle, retains complete
installed versions for rollback, and never accepts an arbitrary command line from a bundle.

### Hosted access declaration

The `access` declaration is required before a new bundle version may become selected.  It is an ordered
relative-path/method policy with four names: `PRIVATE`, `READ_ONLY`, `READ_WRITE`, and `ADMIN`.  An
undeclared or unmatched route is `PRIVATE`: it has no hosted public route.  A more-specific matching path
wins; duplicate path/method declarations are rejected at admission.

The first hosted identity tranche maps an anonymous request to `READ_ONLY` and the existing trusted,
loopback-projected Talisman root session to `ADMIN`.  `ADMIN` inherits `READ_WRITE`, which inherits
`READ_ONLY`.  No ordinary `READ_WRITE` identity is issued yet, but the declaration and proxy vocabulary
already support it without changing component bundles.  Local/LAN Tassy remains trusted-open: it does not
apply this hosted gate or add a login ceremony.

For the server cutover only, an already selected receipt-v1 legacy bundle retains its pre-existing hosted
shape: `GET`/`HEAD` are `READ_ONLY`, unsafe methods are `ADMIN`, and every other method is private.  That
compatibility profile is tied to the old selection receipt; it is never inferred for a new activation,
replacement, rollback candidate, or undeclared bundle.

## Local hot installation

The running Tassy host admits a complete bundle through one local-only operator command:

```text
scripts/talisman-application-server.sh deploy-managed-handler <bundle-source-root>
```

The command sends the source directory only to Tassy's loopback
`POST /api/managed-handlers/deploy` route, carrying the existing private lifecycle token. It is not a
LAN route and cannot be used by a browser or a component child. Tassy validates and copies the whole
declared closure, starts and proves the candidate privately, and atomically activates it only after
readiness. Its receipt returns the selected component ID, version, public prefix, and package digest.
A compatible replacement therefore needs no Java-host restart. A Tassy Java/runtime upgrade remains a
normal host upgrade and restart boundary.

## Managed handler contract

Tassy starts a selected handler with only its declared fixed entry point, its assigned private loopback
endpoint, an isolated component state directory, the existing project SQLite database location, and the
selected package identity. The handler returns a bounded readiness response containing ready state,
component ID, and component version.

Tassy allocates endpoint/state/log/receipt ownership separately for every component. A second compatible
handler can later run beside Slice without sharing a port, process, state directory, logs, version receipt,
or lifecycle handle. This is an extension rule, not a design for another component.

The fixed Python launch contract is `--host`, `--port`, `--state-dir`, `--project-db`, `--component-id`,
`--component-version`, `--public-prefix`, `--package-digest`, and `--database-contract`. The executable is
resolved by Tassy, never supplied by the bundle, and the child does not inherit the host process environment.
Readiness is an exact bounded JSON object containing only `ready`, `component_id`, and `component_version`.

## Public request dispatch

Tassy's Java host remains the only LAN-facing endpoint. It maps a registered component prefix to the active
Tassy-managed handler and forwards requests to that handler's private endpoint. It never forwards to an
unregistered process. The handler owns Slice request and edit behavior; Java does not reimplement it.

The proxy preserves method, public path, query, ordinary headers, body, status, response headers, and response
body while removing host hop-by-hop transport state, Talisman/Tassy authority headers, forwarded-address
claims, reusable Authorization, and Talisman cookies. Tassy injects the admitted component ID, version, and
public prefix into the private request. A selected prefix with no ready child returns bounded `503` rather
than falling through to another host route.

In hosted loopback mode, Tassy evaluates the bundle declaration before forwarding. A private/unmatched route
returns `404`; a known route above the caller's access returns `403`. The outer web host remains responsible
for authenticating the root marker and never forwards a client-supplied marker as authority.

## Home component projection

Tassy Home reads the live managed-handler inventory and adds one shelf label for every `READY` handler.
The label's title is derived from the admitted component ID; its version and public route come from the
active handler receipt. Home never carries a component-specific link or assumes that Slice is the only
handler. A non-ready or absent handler has no launch label.

The public `GET /` route is the Moondance Games entry and redirects to its managed prefix. The technical
Tassy inventory is the manifest page at `/tassy/`; `/applications/` redirects there as the retained public
component-register entry. These route aliases do not alter page assets, APIs, handler admission, or handler
public prefixes.

## Installed runtime state

Complete verified versions are retained beneath the private Application Server state root. Per-component
state, logs, and one atomic selection receipt are separate from installed package bytes. The receipt records
the active version, retained predecessor, public prefix, and package digest. It is operational selection
state only. The component state directory is likewise operational state and must never become a replacement
for project semantics. The existing canonical project SQLite path remains the only durable Slice data store.

Replacement starts and proves a candidate before atomically changing selection. A failed candidate leaves
the prior ready child and receipt unchanged. On host startup, Tassy restores each selected version and may
roll back to its retained predecessor if the selected version cannot become ready. Host close terminates all
managed children; installed versions and receipts remain for the next start.

## Patcher body

1. Reconcile the Tassy branch with current Main before implementation.
2. Implement the generic managed-handler runtime and strict bundle validation.
3. Implement generic private-child start, readiness, stop, replacement, rollback, status, and shutdown.
4. Implement registered public-prefix dispatch only to the active managed handler.
5. Make Slice the first fixture descriptor and prove its independent component version.
6. Add the smallest focused fixture proof for invalid bundle rejection, private child readiness, route
   dispatch, replacement rollback, two-component isolation, and host shutdown.
7. Add a loopback-and-token-bound operator deployment command that admits a complete bundle into the
   running runtime without a Java-host restart.
8. Do not make a live component or database claim without a separate live activation receipt.

## First Slice package gate

The first real Slice bundle must provide the declared managed-handler descriptor and a Python entry point
that accepts Tassy's assigned endpoint, state location, project database, and selected package identity.
If the current Slice package lacks that contract, update its packaging. Do not add a compatibility
workaround in Tassy.
