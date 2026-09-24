# Compiled Morph packages

Status: MPK 0.1.13 release contract curated from Slice findings. A compiled package is an
immutable derived representation, not authoring authority or evidence of consumer performance.

## Identity and closure

Each independently authored Morph owns a self-contained compiled package with complete source and asset
closure, compiler identity/version/digest, compile profile and immutable package digest. Package identity
changes when native source, required asset bytes, compiler or profile changes. A display name, source ID
alone or stale cache entry cannot establish freshness.

The package may contain multiple visual distance/quality levels and one shared articulation description.
All levels retain semantic Morph identity, attachment/socket frames, named controls and compatible pose
and motion bindings. Visual detail changes do not change gameplay collision, Actor identity or canonical
revision. A level may use meshes, connected-edge/control representations or lossless samples, but it must
declare what structure it retains and any measured quality miss.

Compiled bytes are reproducible derivatives. Preserve the complete editable native source separately;
never reconstruct authoring authority from a coarse mesh or renderer cache. Unknown-but-retainable native
fields and asset provenance remain in the source closure.

## Build, invalidation and recovery

Compilation validates the native source and dependency closure, builds into a temporary immutable
generation, verifies member hashes and profile requirements, then atomically activates one complete
package. Failed or cancelled builds leave the prior active package and source untouched. Multiple
records may stage independently, but a released cast/set activates only after its declared closure is
complete.

Consumers compare exact source, assets, compiler and profile fingerprints. A missing or stale derivative
may be compiled only in an authorized authoring/local-workshop operation. Observation-only GET and public
read-only delivery never trigger hidden compilation or canonical writes. Repeated failure is recorded and
does not create an automatic retry loop. Missing bytes may be restored only when their expected digests
prove the exact immutable member; mismatched corruption is not overwritten silently.

Store admission, selected release and served consumption are separate gates. A database/store receipt
does not publish a package. Public/read-only consumers load admitted prepared representations and may keep
reproducible browser caches, but those caches do not establish package correctness.

## Distance selection and timing

Selection uses declared screen-space/error thresholds with hysteresis so levels do not chatter. Smaller
screen size is not proof of cheaper geometry. Bounding volumes, articulation range and attachment frames
cover every admitted motion; otherwise the level is rejected or visibly constrained.

Report inventory, source/asset hashing, queue, compilation, package transfer, decode, graphics submission
and first usable frame separately. Warm cache timing and JavaScript buffer length are not physical disk,
network, GPU-memory or heap measurements. Whole-cast claims require the exact current cast, cold/warm
conditions, hardware/browser/build, package inventory and visible correctness.

## Acceptance

Use real native compilers and clients with disposable file/database fixtures. Verify source invalidation,
asset-only invalidation, compiler/profile change, atomic activation, stale/current bypass, failed-build
recovery, missing-member repair, read-only refusal, level identity/attachments, sampled motion bounds,
visual comparison and end-to-end timing. Browser/GPU/physical-device and downstream-consumer acceptance
remain separate from source or isolated HTTP tests.

