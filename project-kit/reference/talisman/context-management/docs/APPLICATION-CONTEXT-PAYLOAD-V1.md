# Application context payload version 1

## Purpose and ownership

`ApplicationContextPayloadCodec` is the stable serialized boundary for the already-admitted
application context used later by Ask AI, TaliTalk, and Context Monitor adapters. It serializes an
immutable `ContextSnapshot`; it does not capture live state, call a provider, authorize an action, or
send data to an AI service.

The schema name is `talisman.application-context` and the only admitted version is `1`. The payload
kind fixes its extension shape:

| Kind | Snapshot | Question | Handoff descriptor |
| --- | --- | --- | --- |
| `SNAPSHOT` | required | `null` | `null` |
| `ASK_AI` | required | required | `null` |
| `TALI_TALK` | required | optional | required |

The TaliTalk descriptor contains only the handoff UUID, creation instant, and classified conversation
title. The exact starting snapshot remains the payload snapshot.

## Canonical JSON

Encoding produces compact UTF-8 JSON with no insignificant whitespace. Object fields use the fixed
schema order implemented by the codec. String-keyed maps, provider/source/action sets, and disclosure
sets are sorted lexically. Selection, root, child, and omission lists retain their semantic order.
Enums use their exact Java names, UUIDs use their standard lowercase text, and instants use canonical
ISO-8601 `Instant` text. Absent question, handoff, and role references are explicit JSON `null` values.

The version-one top level has exactly these fields:

```json
{
  "schema": "talisman.application-context",
  "version": 1,
  "kind": "SNAPSHOT",
  "snapshot": {},
  "question": null,
  "handoff": null
}
```

The complete nested field contract is pinned by
`ApplicationContextPayloadCodecTest.canonicalSnapshotBytesAreExactAndStable`. A breaking field,
meaning, enum, ordering, or shape change requires a new payload version. A decoder for version 1 must
not guess at a newer or older representation.

## Strict admission

Decoding fails closed for an unknown schema, version, kind, field, enum, or malformed value; a missing
field; duplicate JSON field, set item, or context-node key; trailing JSON; non-canonical structural
type; unregistered node provenance; or a value outside the existing Context Management bounds. Parsed
values are reconstructed through the immutable domain records, so disclosure, omission evidence,
reference totals, node totals, response-contract rules, and aggregate character accounting are
revalidated before admission.

The encoder applies the same transport-specific node and provenance checks before emitting bytes. It
therefore cannot publish a payload that violates the version-one decoder contract merely because a
caller manually assembled an in-memory snapshot.

## Disclosure, privacy, and redaction

Serialization starts only after `ContextEngine` has admitted the snapshot. Denied pointer, focus,
selection, explicit-target, or provider data is absent; only its bounded `ContextOmission` digest,
closed reason, safe source, and fixed summary count may remain. The codec never restores or derives raw
denied IDs, labels, hints, facts, or provider values.

Every admitted reference and node, plus Ask AI questions and TaliTalk question/title text, must be
allowed by the snapshot policy. Extensions are rechecked against the aggregate payload-character
budget. Node source IDs must be nonempty and belong to the snapshot's admitted provider set. The
Context Monitor receives the `SNAPSHOT` form of this same sanitized contract; it is not a route to the
raw request.

## Aggregate bounds

Two layers apply together:

1. `ContextPolicy` bounds admitted references, nodes, omission details, estimated snapshot characters,
   and snapshot-plus-extension characters.
2. `ContextPayloadLimits` bounds the complete encoded document before parsing or publication, plus JSON
   nesting depth, individual JSON string length, and JSON number length.

The standard transport limits are 1,500,000 UTF-8 bytes, nesting depth 256, string length 100,000
characters, and number length 20 characters. Constructor hard ceilings prevent callers from raising
those values above 16,000,000 bytes, depth 1,024, string length 1,000,000, or number length 64. A custom
transport limit may be smaller but never disables the snapshot policy.

The byte limit is checked before decode and after encode. Parser constraints apply before a document is
materialized. These transport limits include JSON structure and escaping, which the in-memory character
estimate intentionally does not approximate.

## Integration boundary

CM-02 registers `context-management/` as a root subproject and composes one provider-empty service in
`AppServices`; it does not change the CM-01 codec or canonical bytes. CM-04's `ContextActionService`
constructs `ASK_AI` or `TALI_TALK` payloads at the owning direct-call seam from exactly one freshly
installed snapshot. Its destination receives only the already-admitted immutable payload and cannot
weaken this contract. Integrations must not bypass feature authority, retain mutable domain objects in
the payload, treat decoding or destination acceptance as authorization/success, or introduce provider,
live-data, persistence, or paid AI work as part of this contract.

CM-05 later composes one Assets Manager Character provider and screen adapter without changing these
version-one bytes. Their bounded PROJECT-classified references and nodes pass through the same codec;
production destinations remain unavailable.
