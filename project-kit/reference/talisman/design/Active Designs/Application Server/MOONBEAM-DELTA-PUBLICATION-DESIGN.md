# Moonbeam one-way delta publication

## Intent

Tassy publishes a small, explicit SQLite data delta from the local project database to Moonbeam.
It is a server-to-server route with a service key, not a browser operation, file copy, remote shell,
or full-database replacement. Local Tassy is authoritative; Moonbeam is a one-way follower.

This design deliberately separates three things that have different deployment rules:

1. **Tassy server release.** A Java host or receiver change is landed on Main and installed on
   Moonbeam once. It needs a restart because the Java server changed.
2. **Data delta.** A received `tassy.gallery-srd-delta/v1` package changes only the named data.
   It does not restart the server.
3. **Component bundle.** A separate, versioned browser/handler component goes through Tassy's
   component admission contract. It is neither a server release nor a database delta.

## Receiver contract

The Moonbeam Tassy receiver is an operator-owned Java service. It accepts one authenticated,
versioned package format and always selects its own canonical project database. A sender never
supplies a filesystem path or executable SQL.

The receiver must:

1. Authenticate the service key from configuration that is absent from source, artifacts, logs,
   and receipts.
2. Provide a read-only receipt endpoint that describes the accepted protocol, current scoped
   receipt, and deterministic inventory identity.
3. Refuse a package whose protocol, content digest, expected receipt, expected inventory, scope,
   schema, or table allowlist does not match the current target.
4. Accept only explicit upserts and explicitly authorized delete entries. Default operation retains
   every remote row/blob absent from the package. Initial Gallery/SRD deployment refuses deletes.
5. Apply accepted rows in one SQLite transaction to the canonical project database, then prove
   foreign-key validity, quick integrity, and a Gallery read before emitting the terminal receipt.
6. Record/reveal an idempotent apply receipt keyed by immutable package digest, so an interrupted
   sender can ask what happened before retrying.

No backup, local copy, iCloud copy, or remote archive is created by this route unless Mark separately
asks for one. Transactional failure must leave the database uncommitted.

## Stable operator commands

Once implemented, these are the only routine commands an operator needs:

```text
# Fetch one complete deterministic Moonbeam receipt and inventory.
scripts/talisman-application-server.sh fetch-moonbeam-gallery-receipt \
  <output-receipt.json>

# Build an immutable package from local Tassy against a fresh Moonbeam receipt.
scripts/talisman-application-server.sh build-moonbeam-gallery-delta \
  <local-project.sqlite> <moonbeam-receipt.json> <output-package.sqlite>

# Authenticate to Moonbeam and push the immutable package exactly once.
scripts/talisman-application-server.sh push-moonbeam-gallery-delta <package.sqlite>
```

The URL and service key come from the Tassy deployment configuration, not arguments, source,
shell history, or package content. The sender must obtain a fresh remote receipt first and stop on a
mismatch. The remote terminal receipt is the proof of completion.

## Operator sequence

1. If Moonbeam does not already expose the receiver, install the Main-landed Tassy server release,
   configure its service key, restart once, and read the first receipt.
2. Build one package from the local authoritative database using that exact receipt.
3. Inspect its manifest: protocol, target receipt/inventory, digest, scope, upsert counts, and any
   delete count. Stop for an unapproved delete or unknown scope.
4. Push it with the named sender command. The server accepts it atomically or refuses it without
   changing the database.
5. Record the apply receipt and confirm the expected Gallery read. A package whose predecessor
   receipt changed is stale and must be rebuilt.

## Current state

The generic receiver and stable fetch/build/push commands are implemented on Mandy's release body and
focused proof covers accepted apply, repeat idempotency, drift refusal, retained absent rows, integrity,
and Gallery read-back. The receiver remains disabled unless its service-key file is explicitly configured.
Moonbeam installation and the first live zero-delete publication are the remaining release gates; no one
uses manual SQL or a raw database replacement.
