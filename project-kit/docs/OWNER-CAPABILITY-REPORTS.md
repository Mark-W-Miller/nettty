# Owner capability reports incorporated in 0.1.3

Historical sanitized Tassy/Slice reports received after 0.1.2 was frozen. Incorporated as owner evidence,
not a fresh live probe, implemented acceptance or permission for writes. Source hashes are in
[provenance](UPDATE-SOURCES.md). The original 0.1.2 ZIP and separate supplement remain unchanged.

## Tassy: supported final per-item authority

One complete independent `talisman.core-morph/v1` TEMPLATE is admitted through
`POST /api/object-factory/morphs/save`. Required native structure includes canonical identity/revision,
point tree, chains/profiles/geometry, top-level stances/default poses, native motion keyframes and
permitted interpolation. Inheritance/deltas and nonempty `texture_maps` are rejected.

NEW requires an absent ID, expected_revision 0, empty expected_content_sha256 and canonical revision 1.
UPDATE retains the selected existing ID, requires exact current revision/content guards, and advances
to expected_revision+1. It appends an immutable revision and moves only that ID's current head.
No blind overwrite, same-revision replacement, deletion or historical rewrite is supported.

Public reads expose current state only:
- `GET /api/object-factory/morphs/catalog`: current heads/summaries, stances/motions and digests.
- `GET /api/object-factory/morphs/current?morph_id=<id>`: complete current native definition.

Stored history does not imply public history-list/historical-read or rollback/select-old-version APIs;
none are currently provided according to Tassy. Preserve offline originals independently.

Each item uses a stable unique idempotency key tied to its exact request. Replay identical requests;
changed requests under the same key reject. Earlier source inspection computes identity from normalized
request content; callers should preserve exact requests and not assume alternate encodings are replay-safe.
Receipts identify request digest, scope/action, Morph ID, committed revision, canonical content digest,
catalog revision and creation time. Persist sanitized receipt fields and source ZIP/member digests,
never session/auth values.

After each save, reacquire/revalidate server epoch; read the catalog and require the current ID/revision/
digest to match the receipt. Read complete current-by-ID and verify native schema, ID, revision and
canonical identity. Track admitted, rejected, stale, uncertain and readback-failed states explicitly.
Activate only after all selected members and dependency closure pass; concurrent changes are not silently
accepted as proof of the returned package.

## Tassy: reported missing capabilities (not all are mandatory additions)

- Morph-only dependency-complete ZIP schema and manifest verifier.
- Owner-maintained authenticated intake CLI/helper.
- Public read-only native validator/dry-run endpoint.
- Batch transaction, set receipt or set rollback.
- Public historical-revision readback.
- Selected NEW/UPDATE planner and configurable user-reviewed replacement policy.
- Automatic hot-deploy/intake coupling for Slice or the three labs.
- Native converter/resolved semantics for endpoint-only motions, SMOOTHSTEP and local wing webs.

Missing history APIs or batch rollback are not automatically new implementation requirements.
A designated automatic intake may orchestrate supported per-item calls without requiring bulk atomicity.
However it still must be implemented and proven; current admission is not evidence of a ready ZIP workflow.
Native preflight requires an actual owning codec/validator binding, not an invented HTTP dry-run route.

## Slice: source-only scope report

At report time Slice had Kit 0.1.0; it subsequently confirmed adopting 0.1.2. Preserve root AGENTS,
docs/design/Places, docs/design/Morphs and local supplements during updates.

`import_morph_bundle.py` has reviewed-plan, atomic/idempotent same-ID update/conflict handling, history
and native-reader verification, but `morph_bundle.py` limits that path to sign-language-asl → metamorph
capability bundles. It is not demonstrated as general cross-lab Morph/dependency-set intake.
`tools/slice-import.cjs` describes inspect/admitAtomically/readReceipt contracts but lacks a production
owner adapter; its CLI apply fails before writing. Ordinary per-model saves do not prove complete set
admission or activation. Place format/body-reader coverage remains unresolved; attempt002 is a current
Patcher correction, not accepted completed capability.

## Required separate acceptance evidence

For Slice and each lab, record separately:
1. Actual native format validation and complete dependency/asset pins.
2. User-selected stable-ID NEW/UPDATE policy and revision/digest guards.
3. Committed receipt and exact readback.
4. Explicit runtime content selection/activation.
5. Actual rendered acceptance, including appearance/motion/Place behavior.
6. Hot-deployed source/component identity and served-byte/browser proof, independent of content admission.

Three labs retain complete native offline compendia. Slice collects and visually proves their returned
content. No runtime implementation, database mutation, inventory/payload export or status completion is
authorized or established by these messages. All-Morph catalog completeness remains a separate goal.
