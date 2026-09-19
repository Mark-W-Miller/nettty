# Tassy admission reference — sanitized owner-supplied contract

Supplied by Tassy on 2026-09-16. This is a source-specific request reference, not an executable fixture,
validated candidate, successful import example or permission to write. No credentials or live session
values are included. Native document schema: `talisman.core-morph/v1`.

Logical destination: Talisman's canonical shared Core Morph catalog, reusable TEMPLATE scope. It is
not an Insect-Wars-private store. Mark/Atlas destination selection is still pending.

- Save envelope: `object-factory.core-morph-save/v1`.
- Capability: `object-factory.core-morph.save.v1`.
- Save: `POST /api/object-factory/morphs/save`.
- Session bootstrap: `GET /api/page-bootstrap?page_id=critter.creature`.
- Required header names: `Cookie`, `Content-Type`, `X-Talisman-CSRF-Token`,
  `X-Talisman-Server-Instance`, `X-Talisman-Manifest-Version`.

Tassy alone obtains and retains actual session/auth values from a fresh local bootstrap. Never copy
live values into the Kit, returned dataset, provenance or receipt examples.

## TEMPLATE/NEW request shape

The `candidate` placeholder below must be replaced with the complete native object, not a string.

```json
{
  "contract": "object-factory.core-morph-save/v1",
  "scope": "TEMPLATE",
  "action": "NEW",
  "idempotency_key": "<stable per-item operation id>",
  "entity_key": "",
  "creature_identity_sha256": "",
  "morph_id": "<candidate canonical id>",
  "expected_revision": 0,
  "expected_content_sha256": "",
  "master_morph_id": "",
  "master_revision": 0,
  "master_content_sha256": "",
  "replace_base": false,
  "candidate": "<complete talisman.core-morph/v1 object>"
}
```

Owning chain: ApplicationServerController request decoder → FactoryCoreMorphApplicationServerGateway
→ CoreMorphRevisionService TEMPLATE/NEW → CoreMorphCatalogService native validation. One item commits
atomically and returns a per-item receipt. No set atomicity or game-scoped destination is established.
Use exact native constraints; this envelope supplies no missing stances, keyframes, media or provenance.

## Required before writes and activation

1. Mark/Atlas explicitly selects the shared catalog destination.
2. Morph owner approves conversion semantics, particularly interpolation and wing geometry.
3. All ten complete candidates pass actual native validation, not merely staging validation.
4. Tassy is freshly running and retains all real session/auth values locally.
5. Follow each successful receipt with catalog and exact per-ID readback; check exact revision/digest.
   Activate the game only after all ten and their dependency closure are verified. Record partial
   progress/resume explicitly; a per-item receipt is not a set receipt.

At report time: destination approval absent, Tassy stopped, no authenticated request attempted and
no database writes. Exact readback operations, full response/error examples and a native-validated
worked fixture remain required evidence before this becomes a complete authoring-to-import example.
See [delivery evidence](DELIVERY-GATES.md) and [standard objects](../contracts/objects.md).

## Subsequent same-ID UPDATE clarification

The NEW shape above is not the only supported service action. Read-only local service inspection
confirms guarded TEMPLATE UPDATE preserving the same ID and replacing its current definition while
retaining history. See [exact source-confirmed behavior and gaps](NEW-UPDATE-PROFILE.md).
