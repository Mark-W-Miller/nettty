# Stable authoring state, asynchronous reads and evidence

Status: portable documentation requirement for MPK 0.1.12, curated from Slice findings. It applies to
editors, galleries, inspectors and preview surfaces without prescribing one UI framework.

## Commit identity only after a checked read

Selection, navigation, focused Card and editor identity become current only after the requested body has
been read, validated and matched to the still-current request generation. Until then, retain the last
complete usable view. A late, cancelled or superseded result may warm a cache but cannot replace current
identity, selection, draft, camera, receipt or status.

Bind every request to exact target identity/revision and the source digest or fingerprint needed to
replay it. A captured view is not current model geometry. Generic appearance or a label cannot repair a
wrong object binding; each target kind needs an explicit read adapter that either supplies immutable,
revision-bound content or reports that the context is unavailable.

When an exact identity is missing from a cached catalog, refresh that catalog once and retry the exact
identity. Do not reset drafts, camera or newer receipts, and do not silently choose a similarly named
record. Replacing a list must preserve the intended selected alias and must not swallow clicks made
during the read.

## Stable layout and loading feedback

Ordinary selection and status updates preserve node identity, row height, target position and ancestor
scroll. Reserve space for warnings and progress rather than inserting transient rows that move controls
under the pointer. Intentional tree expansion is a separate action.

Foreground work uses balanced loading lifecycle tokens covering response headers, body reads, decoding
and presentation. Loading, failure and Retry remain accessible and respect reduced motion. Quiet
background synchronization does not display a blocking overlay on every fetch; use explicit background
intent and a short display delay so brief work does not create perpetual false loading. Cleanup failure
must not masquerade as a source-selection failure.

## Displayed structural source

An editor changes the structure currently displayed, not a hidden Mapping or stale captured pose.
Resolve the visible structural base before applying a continuous edit. Exact source guards, cancellation,
no-op detection, stable nodes and bounded frame coalescing protect press-and-hold or drag controls.
Lightweight geometry preview remains separate from adopting a model, rebuilding UI, fitting dependent
content and saving.

Deliberate historical forms keep explicit same-object references and an explicit Update operation.
Compatibility reading does not automatically persist old structure. For derived work, the newest complete
result wins; discard superseded jobs and do not publish an intermediate shape as final.

## Evidence matches the real claim

Tests load actual stylesheet order and density rules, count the real controls and preserve visible labels,
touch targets and bounded scrolling. A mock that skips the real replacement bridge cannot prove that the
live editor avoids refresh or flicker.

Numeric finiteness does not prove valid geometry. Measure clearance against real source thickness, face
samples, extreme forms, bindings and worker round trips. A fixture can establish a defect without proving
the exclusive cause of a screenshot or proving browser, GPU or device acceptance.

Database-valid content is not consumer-accepted until the actual display/game path loads it with its
model, texture/media bytes, transforms and required dependencies, then survives reopen. Record source,
focused checks, service read-back, served version and visible acceptance as separate gates.
