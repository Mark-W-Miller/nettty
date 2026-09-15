# Tassy Rapid Publication Handoff

Status: current local rapid JavaScript publication procedure

Owner: Tassy — Talisman Application Server

## Purpose

The local `3002` host can replace the complete declared Object Factory Creature browser bundle without
restarting Tassy. This is an operator shell command, not an HTTP deployment endpoint and not a chat message.
It is for a coherent JavaScript/CSS/HTML/resource update only. A Java service, manifest schema, capability,
or new declared asset requires the managed host upgrade path instead.

## What the page manifest declares

Each hosted page is one component. Its entry in
`src/main/resources/app/application-server/page-manifest.yaml` must provide these human-facing fields:

```yaml
page_version: 1
component_version: alpha 1.66.5
new_features:
  - One concise user-visible feature or correction.
```

`page_version` is the registered page revision. `component_version` is the owning component's independent
human-facing release line. `new_features` is a non-empty list of at most eight concise notes, each no longer
than 180 characters. Tassy copies those fields into `/api/manifest`; Home and About Tassy refresh from that
read-only projection. Owners update only their own component metadata as part of a coherent body. Older
fixtures without this display metadata retain the honest fallback `Page <page_version>` and no release notes.

The rapid Creature seam uses its own source-root metadata because a rapid Factory snapshot deliberately does
not modify Main's manifest. Place this file beside the Factory browser assets:

```properties
# src/main/resources/app/critter-image-creation/rapid-publication.properties
component_version=alpha 1.66.6
feature_count=2
feature.0=One concise visible change.
feature.1=One concise correction.
```

The file is required, carries one to eight notes, and is captured in the same private receipt as the bundle.
When the rapid snapshot is active, its version and notes replace only the Creature component's manifest
projection; no other page metadata changes.

## Rapid Object Factory publication

The operator first states the exact source root that contains every already-declared `critter.creature`
asset. From the Application Server source root, run:

```text
scripts/talisman-application-server.sh preview-object-factory-activate <source-root>
```

Tassy validates the source root, copies every asset already declared for the Creature page into a private
snapshot, hashes each asset, computes the complete bundle digest, and atomically selects the receipt. The
next request receives the whole snapshot or Main; it never receives a mixture of files. The receipt records
the source root, snapshot root, Main manifest digest, bundle digest, asset count, and per-asset identifiers
and digests. Read the current receipt at:

```text
~/.talisman/application-server/object-factory-preview/current.properties
```

To return to the Main-declared bundle:

```text
scripts/talisman-application-server.sh preview-object-factory-rollback
```

## Required handoff note

Before a rapid publication, provide Tassy with:

1. The component name and the declared page ID (`critter.creature` for the current rapid seam).
2. The exact source-root path whose declared assets are complete and ready to snapshot.
3. The updated `rapid-publication.properties` component version and concise feature notes.
4. The focused browser/resource check that passed.
5. Confirmation that the body changes no Java service, capability, manifest structure, route, or page asset
   list.

Tassy returns the selected snapshot receipt, byte equality for the served assets, and current health. A
successful source edit alone is not deployment evidence.

## Managed host upgrade boundary

If a body adds or changes Java code, a typed service/gateway, capability declaration, route, manifest schema,
or the list of declared page assets, do not use the rapid seam. Land the coherent body on a clean exact Main,
record the current health and prior clean source baseline, restart Tassy from that exact Main, then verify the
new process identity, health, manifest capability/resource identity, and the smallest safe live contract.
The rapid-preview rollback can undo only the static Creature snapshot; it cannot roll back Java services.
