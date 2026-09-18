# Native NEW/UPDATE profile — source evidence and remaining bindings

## Source-confirmed behavior, not live deployment proof

Read-only inspection on 2026-09-16 of Talisman Main CoreMorphRevisionService shows:

| TEMPLATE operation | Required behavior |
| --- | --- |
| NEW | Target absent, expected_revision 0, expected_content_sha256 empty; committed revision 1 |
| UPDATE | Same morph_id, positive expected_revision and required expected_content_sha256 matching current head; commits next revision and replaces that ID's current pointer |
| Target fields | Empty entity/creature/master fields, master_revision 0, replace_base false |
| Content | Complete candidate canonicalized with target morph_id and committed revision, then native validated |
| Transaction | One item's content, version, current head and receipt committed atomically |
| Replay | Same idempotency key and identical normalized request returns original receipt; different request rejects |

UPDATE preserves history AND changes the selected current definition. `replace_base` is not the
TEMPLATE overwrite switch: it remains false; action UPDATE and current guards select the replacement.
Observed rejection codes include template_already_exists, template_not_found, template_revision_stale,
template_revision_conflict and idempotency_key_reused. Canonicalization may change source bytes; retain
both submitted digest and canonical committed digest and verify the latter against exact readback.

The previously owner-supplied HTTP save route/envelope is in
[Tassy admission reference](TASSY-ADMISSION-REFERENCE.md). For a TEMPLATE UPDATE the action is UPDATE,
the morph_id is the chosen existing target, and expected guards come from its exact current read.
A live end-to-end UPDATE request/response was not executed here. Do not prescribe unknown response
fields, readback routes, destination bindings or an implemented ZIP upload endpoint.

## Remaining owner confirmation / implementation

Confirm exact current deployed profile, session/capability/epoch and readback bindings, native motion and
geometry/media support, hot-deploy registration for all four apps, and automatic plural ZIP orchestration.
Earlier Tassy reports limited native content to complete definitions without inheritance/deltas and
empty texture_maps. Necessary texture/material/media payloads therefore need explicit supported
admission adapters; their inclusion in a ZIP does not establish support. Different native source/receiver
profiles require a supported semantic adapter, not an approximate local dialect.

No credentials were requested or copied. No service was started and no database write was performed.
Source evidence is pinned in [provenance](UPDATE-SOURCES.md); exact live owner confirmation remains pending.

Subsequent sanitized owner reports supply current-head catalog/per-ID read routes and explicit missing
capabilities: [owner reports](OWNER-CAPABILITY-REPORTS.md). Those reports are not live execution proof.
