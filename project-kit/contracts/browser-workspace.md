# Browser workspace and portable archive

Status: MPK 0.1.13 release contract. It defines portable behavior; it does not supply a
runtime store, database writer, deployment gate or consumer acceptance.

## Required workflow and applicability

Every delivered application that creates, changes or retains user work supports its declared form of:

> Open content → edit or play → save in this browser → close and reopen → continue → archive to a
> user-owned file → reinflate in a clean workspace → continue.

Classify each separately delivered surface. A canonical or document workspace requires durable browser
work plus Archive/Reinflate. A read-only view or presentation records why authored storage is not
applicable. A service/tool classifies its own retained configuration/output separately. Unknown or
unreviewed applications remain `UNKNOWN`; missing SQL is not an exemption. Reclassify when editing is
added.

This contract complements the [runtime profiles](persistence.md). Public Tassy and Moonbeam editors use
browser-owned workspaces over read-only canonical baselines. A selected local project workshop may use
its explicit database writer. Entitlement, host address and login do not select storage authority.

## Trusted application profile

The application ships a bounded versioned profile from trusted code. It includes stable `projectId`,
`applicationId` and `storageNamespace`; build/artifact identity; classification and authored kinds;
native codecs and dependency enumerators; supported browser/delivery profiles; actual storage adapters;
canonical readers and admissions; archive profile and limits; retention/exclusion policy; and owner,
design and test references. Do not infer identity from a title, route, port, branch or archive claim.

One origin may host several products. Use a stable application namespace and multiple stable workspace
IDs; changing the route must not create an empty store. Different schemes, hosts, ports, browser profiles
or devices are different origins/stores. Explain this and offer Archive/Reinflate or an explicitly
authorized transfer. Same-origin namespaces organize data but do not isolate it from other scripts with
execution on that origin.

## Store and transaction invariants

IndexedDB is the baseline substantial browser store, including Blob assets. `localStorage` is suitable
for small preferences and legacy import only; session memory, Cache Storage, thumbnails and render
objects are not the sole copy of authored work. A compliant adapter preserves:

- stable workspace/record IDs, monotonically checked local revisions and authority-qualified source pins;
- complete native payloads or a lossless owned delta with its exact retained base;
- immutable asset bytes/digests and dependency edges;
- atomic record/head/receipt commits, operation identity, replay and unknown-result recovery;
- multi-tab revision guards and writer fencing; notifications are not commit proof;
- schema migrations that retain old work and never reset to an empty database on failure; and
- explicit tombstone/revert semantics that never delete canonical source content.

Report Saved only after the storage transaction completes. Autosave completions are generation/revision
checked and cannot acknowledge newer edits or change a newer selection. Quota, transaction abort,
corruption and unavailable persistence preserve the previous committed head and offer a live draft or
archive where possible. A session-only fallback is labelled **Archive before closing** and does not count
as persistent-browser acceptance.

## Archive and Reinflate

Archive captures one pinned workspace generation with a manifest, application/profile identity, native
records, source pins, complete required asset/dependency closure, digests, provenance, exclusions and
limits. Context, credentials, tokens, raw captures and hidden/private state are excluded unless the user
explicitly saves an authorized artifact. Later edits remain outside the pinned archive.

Reinflate stages and validates the complete archive before atomic activation. Reject traversal,
duplicates, invalid digests/types, unsupported codecs, executable/URL payloads and archive bombs without
changing the old workspace. Cancellation or quota failure leaves the old generation active. Reimport,
copy, merge and fork are distinct dispositions; titles and array positions never authorize overwrite.
An incomplete/reference-dependent archive names missing dependencies and preserves local work.

Offline claims include the application shell, validators/workers/fonts and selected content closure, not
only a saved document. A remote engine/provider being unavailable must not prevent saving or restoring
the authored document.

## Acceptance

Test exact supported browser/origin/device profiles with native fixtures. Required cases include fresh
reopen, canonical-reader outage, two-tab stale writes, delayed/aborted transactions, quota failure,
corrupt data, origin change, hot application update, schema migration, archive/reinflate into a clean
profile, missing dependency, malicious archive, cancelled multi-record activation, baseline upgrade
conflict, offline startup, accessibility and representative large content. Public profiles also prove
that every direct and indirect canonical write route rejects while local editing continues.

Shared adapter tests do not replace application acceptance. Each owner records exact build/profile,
native fixture, commands/results, archive/read-back identity, exclusions and physical-device limits.

