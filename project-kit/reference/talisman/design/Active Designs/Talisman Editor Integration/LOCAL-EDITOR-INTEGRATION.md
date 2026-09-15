# Local Editor Integration

Status: active design; optional one-way local-application gateway implemented
Owner: Talisman Editor Integration  
Reviewed: 2026-08-26

## Outcome

Talisman should let a user edit a safe temporary working copy in a chosen local macOS editor, return
to Talisman, press **Check**, compare the original with the candidate, and explicitly import a new
derived asset. The original managed bytes, external file, and existing association remain unchanged
until a checked transaction commits the derivative and any separately reviewed role replacement.

The first implementation body should prove this lifecycle with a selected managed PNG and a local
Photoshop-compatible editor. Blender is the first deep 3D design, but its import/export and validation
contract is larger and should follow only after the generic session boundary is proven. The separately
authorized product-neutral application gateway now supports truthful availability and one-way exact-app
open requests. It is not an editor session or reviewed round trip. No editor-session body in this
document is approved for implementation until Mark explicitly approves it.

## Research basis

This design incorporates the preserved 471-line source study at:

`/Users/mmiller/Documents/Codex/2026-08-24/talisman-blender-integration/outputs/`
`talisman-local-3d-pipeline-design.md`

That source remains unchanged outside this repository. This canonical design retains its conclusions
about immutable originals, Blender LTS support, allowlisted local commands, deterministic headless
finalization, previewed optimization, provenance, crash containment, and atomic admission. It narrows
the first body after mapping the proposal onto Talisman's current media and association authorities.

Current external facts were checked against official sources through 2026-08-26:

- Apple `NSWorkspace` discovers applications by bundle identifier or by the URL/content type they can
  open, and can open URLs in a chosen application without a shell command.
  [Apple NSWorkspace](https://developer.apple.com/documentation/appkit/nsworkspace)
- Apple's open configuration allows a different already-running installation by default. An exact
  user choice therefore disables substitution and verifies the callback's application URL.
  [Apple open configuration](https://developer.apple.com/documentation/appkit/nsworkspace/openconfiguration)
- Photoshop supports local PNG editing and export; PSD/PSB preserve richer Photoshop state that PNG
  cannot retain.
  [Photoshop formats](https://helpx.adobe.com/photoshop/using/file-formats.html)
  and [saving PNG](https://helpx.adobe.com/photoshop/using/saving-files-graphics-formats.html)
- Illustrator can open/save its native formats and export local PNG, SVG, PDF, and other screen
  formats. Talisman does not currently have a vector-asset authority.
  [Illustrator formats](https://helpx.adobe.com/illustrator/using/supported-file-formats.html)
  and [local screen export](https://helpx.adobe.com/illustrator/using/collect-assets-export-for-screens.html)
- Blockbench's desktop workflow writes local project files and exports OBJ/glTF, making it a plausible
  third candidate for intentionally low-poly work.
  [Blockbench formats](https://blockbench.net/wiki/blockbench/formats/)
- Fusion Personal Use permits selected local exports including STL and OBJ, but is a qualifying
  home-based, non-commercial offering, limited to users under USD 1,000 annual revenue, with
  account-bound cloud data and ten active editable documents.
  [Fusion Personal Use terms](https://www.autodesk.com/products/fusion-360/personal) and
  [Limits](https://www.autodesk.com/products/fusion-360/blog/changes-to-fusion-360-for-personal-use/)

## Current Talisman fit

The integration must extend current authorities rather than introduce a parallel media library.

| Concern | Current authority | Editor integration use |
| --- | --- | --- |
| Managed bytes | `content_object` and `project_asset` | Source and derivative remain immutable exact bytes. |
| User Asset mutation | `UserAssetsService` | New workflow enters through a narrow service boundary. |
| Derivation | `content_derivation` | Records exact source/output hashes and editor recipe. |
| External provenance | `external_provenance` | Source evidence, never write authority. |
| Role history | `entity_asset_attachment` | Optional replacement uses exact active-role history. |
| 3D parsing | `Model3DImportService` | STL/OBJ output must pass the existing bounded parser. |
| 3D admission | `ManagedObjAdmissionService` | Later Blender/Blockbench output reuses its atomic pattern. |
| 3D preview | `StlObjPreviewSession` and Viewer | Reuse generation/stale-result rules. |
| Local app handoff | `LocalFileApplicationGateway` | Optional exact `.app` discovery/open only. |

The current extension classification treats PNG as the first-class image format and STL as the
external 3D source. Canonical OBJ is already a durable derivative and Viewer input. PSD, AI, SVG,
GLB, TIFF, and JPEG are not truthful first-body managed-image or model contracts today.

### Required ownership shape

The eventual implementation should keep four boundaries separate:

1. An app-facing editor-session service owns source capture, manifest state, change detection,
   validation dispatch, recovery, and the reviewed commit request.
2. A macOS adapter owns `NSWorkspace` discovery and opening only. It does not own feature state,
   persistence, or editor-specific policy.
3. Format adapters own bounded validation, metrics, and preview values. They never admit content.
4. The User Assets transaction owner admits the derivative, records provenance, and optionally
   replaces one exact reviewed presentation role.

No editor callback may write directly to the project database. No preview object may contain a live
database connection, mutable User Asset, or authority to change an association.

## Editor candidate order

This is an implementation-research order, not a claim that one creative tool is universally better.

| Order | Editor | Initial exchange | Decision |
| ---: | --- | --- | --- |
| 1 | Adobe Photoshop | PNG working copy and PNG result | First implementation proof. |
| 2 | Blender | STL/OBJ input; validated canonical OBJ first | First deep 3D design. |
| 3 | Blockbench | Local project; OBJ/glTF export | Later low-poly candidate. |
| 4 | Adobe Illustrator | AI/SVG authoring; reviewed PNG export first | Defer until vector policy exists. |
| Evaluate only | Autodesk Fusion | STL/OBJ export | Paid/eligible-use evaluation, not baseline. |

Plasticity and Bforartists remain useful authoring experiments from the preserved study. They do not
need dedicated adapters before the generic chosen-application route and Blender finalization contract
are proven.

### Photoshop

Body 1 opens an exact PNG working copy. The user saves that PNG, returns to Talisman, and presses
**Check**. Talisman does not automate Photoshop, inspect Photoshop documents, or infer that returning
focus means the file is complete.

PNG is intentionally limiting. Layers, masks, adjustment state, and other Photoshop-native content
belong in PSD/PSB, while Talisman currently owns only the flattened PNG derivative. A later two-file
session may retain a PSD authoring source and require a separate PNG deliverable, but that needs a new
format/provenance policy and is outside Body 1.

### Blender

Blender remains optional, user-installed, free, and local. Opening STL or OBJ in Blender is not enough
to define a safe round trip: Blender imports those formats into a scene, while ordinary Save produces
a `.blend` document. Talisman must use an explicit file/process boundary rather than embedded UI, a
live bridge, or a required Blender dependency. It must never mistake an open request or a changed
`.blend` file for a reviewed runtime model.

A future safe interactive round trip should create a session-owned editable `.blend`, require an
explicit user Save, and produce a separate checked export to a new GLB candidate. Talisman would then
validate and preview that independent candidate before reviewed atomic promotion, leaving the accepted
original untouched. GLB/glTF 2.0 is the preferred future static runtime derivative; OBJ plus MTL is the
fallback and STL is geometry-only. GLB admission remains deferred until Viewer, persistence,
association, and Player paths support it end to end.

Important future runner capabilities and limits remain grounded in official Blender 5.2 documentation:

- the macOS bundle's internal executable, background execution, factory settings, disabled
  auto-execution, offline mode, explicit exit codes, and runner arguments after `--`:
  [command-line arguments](https://docs.blender.org/manual/en/5.2/advanced/command_line/arguments.html);
- STL/OBJ and other local import/export support:
  [import/export](https://docs.blender.org/manual/en/5.2/files/import_export/index.html);
- Collapse/Un-Subdivide/Planar decimation and triangle-ratio semantics:
  [Decimate modifier](https://docs.blender.org/manual/en/5.2/modeling/modifiers/generate/decimate.html);
- deterministic modeling previews:
  [Workbench](https://docs.blender.org/manual/en/5.2/render/workbench/index.html);
- unrestricted Python and embedded-script risk:
  [scripting security](https://docs.blender.org/manual/en/5.2/advanced/scripting/security.html).

A future Blender runner must use `--factory-startup`, `--disable-autoexec`, and `--offline-mode`, pass
arguments as an array, run only a fixed integrity-checked packaged script, and return explicit script
exit codes. It must never execute asset-, user-, or download-supplied Python, add-ons, startup files,
or model-supplied command arguments. Compatibility discovery, scripting, `.blend` lifecycle, GLB
conversion, and checked return are not capabilities of the implemented one-way gateway.

### Blockbench

Blockbench is the third candidate because it is local-file friendly and naturally produces low-poly
OBJ/glTF. It may avoid expensive decimation for stylized props. Its eventual proof should use the
same barrel, chair, one-meter wall, and door fixtures proposed in the preserved study. No Blockbench
plugin or automation is required before ordinary local export proves useful.

### Illustrator

Illustrator is the fourth candidate. Its strongest Talisman use is source-quality vector art for
icons, tokens, maps, masks, and UI assets, followed by a reviewed PNG derivative. Body 1 must not call
an AI or SVG file a managed image. A later vector body must first decide whether AI/SVG is merely
authoring provenance or a new canonical asset kind, define fonts/linked-resource handling, bound SVG
features, and preserve the exported PNG's exact source relationship.

### Autodesk Fusion assessment

Fusion technically fits local STL/OBJ interchange and offers offline work, but its free Personal Use
terms do not fit a general Talisman development baseline. Talisman may become commercial, Personal Use
is limited to qualifying non-commercial home projects below USD 1,000 annual revenue, and design data
remains account/cloud based even when local export is available. Fusion should therefore be an
optional authoring tool only when the user has a license appropriate to the actual work. Talisman must
not encode Personal Use entitlement, log in, upload, or treat Autodesk cloud state as local authority.

## macOS discovery and launch

### Discovery

1. Start with the user's exact persisted application choice for the exchange format.
2. Ask `NSWorkspace` for applications registered to open the exact staged file URL or its content
   type. Do not scan process lists or assume a versioned `/Applications` path.
3. For known presets, use bundle identity only as a ranking hint. Validate the chosen application URL,
   bundle identity, display name, version, and ability to open the staged file.
4. If zero or several plausible applications remain, show **Choose Editor…** with discovered apps and
   a native `.app` picker fallback.
5. Persist a relocatable application choice appropriate to the eventual sandbox/signing model. If it
   becomes unavailable, rediscover; never silently launch a different editor.

The design deliberately does not hardcode Adobe versioned paths or invoke `/usr/bin/open`. The macOS
adapter should call `NSWorkspace.open(_:withApplicationAt:configuration:completionHandler:)` through a
narrow native bridge. Completion proves only that macOS accepted the open request, not that the user
saved valid output.

### Implemented one-way dependency boundary — 2026-08-26

`LocalFileApplicationGateway` and `MacLocalFileApplicationGateway` now provide the smallest reusable
product-neutral seam:

- discover registered applications for one exact caller-owned regular file, optionally filtered by
  an exact `.app` path and/or bundle-identifier hint;
- return immutable secret-free application identities containing the exact application path, bundle
  identifier, display name, version, and bounded revision fingerprint;
- preserve unavailable, incompatible, ambiguous, unsupported, wrong-thread, request-rejected, and
  platform-failure truth without selecting a default;
- revalidate the exact file and selected application identity immediately before asking `NSWorkspace`
  to open that file in that app, disable running-application substitution, and reject a callback for
  another app URL; and
- report success only when the operating system accepts the request.

The caller owns every export or temporary file and all UI, model, review, revision, persistence, and
cleanup state. The gateway does not stage or delete files, persist editor choices, observe unsaved
edits, manage processes, import results, run Check, validate returned content, recover sessions, or
promote an asset. It does not assess Blender version/capability compatibility or create a `.blend` or
GLB derivative.

The revision fingerprint covers the canonical app path, bundle identity/version, bounded Info.plist
content, and main-executable file identity/size/modification time. It detects ordinary replacement
without reading a large editor executable on every discovery. It is not code-signing validation, an
adversarial whole-bundle integrity guarantee, or a Blender/editor compatibility verdict.

For Factory Body 1 this seam can support an optional **Open in Blender** action only after Factory has
created a caller-owned export and the user has selected one exact discovered app. It cannot support a
reviewed Blender return. Factory wall creation, preview, and Accept must remain complete without
Blender.

### Returning to Talisman

An editor may already be running and may remain open for days. Process exit is therefore not a session
completion signal. Talisman may notice that its own window became active and make **Check** prominent,
but only the user's explicit Check starts hashing and validation.

No file watcher is commit authority. A watcher may later enable an unobtrusive “working copy changed”
hint, but Check must reread the exact session path and validate stable bytes.

## File-session lifecycle

### Session directory

Create a unique private directory beneath Talisman's disposable application cache, not beside the
source and not inside the managed media store. Use directory permissions no broader than the current
user and file permissions no broader than read/write for that user. Names contain a random session ID
and sanitized display label, never credentials, entity names that expose private campaign data, or an
external absolute path.

The directory contains:

- `session.json.partial` followed by atomically promoted `session.json`;
- the editor working copy;
- immutable before-preview material when needed;
- staged candidate bytes and preview products;
- a bounded diagnostic log with sanitized paths;
- for later Blender work, declarative job input and exact runner output.

### Manifest

The versioned manifest records:

- session ID and manifest version;
- exact source asset ID/revision and SHA-256, or exact reviewed external-file evidence;
- source and exchange formats;
- working-copy relative path and initial SHA-256/size/modification time;
- chosen editor bundle identity, version, and application revision fingerprint;
- state, state revision, and timestamps;
- latest candidate SHA-256, metrics, validation result, and warning codes;
- optional exact association/role preview token;
- no credential, file contents, arbitrary command arguments, or durable temporary absolute path.

### States

```text
CREATING -> READY -> OPEN_REQUESTED -> WAITING_FOR_CHECK
  -> CHECKING -> REVIEWING -> COMMITTING -> COMPLETED
                         \-> WAITING_FOR_CHECK
Any non-commit state -> CANCELLED | FAILED | RECOVERY_REQUIRED
```

Every transition writes a new manifest revision atomically. `COMMITTING` is not success. Startup must
reconcile it against the canonical database transaction outcome before presenting completion.

### Creating the working copy

1. Capture exact source identity and revision.
2. Read source bytes through their current authority.
3. Hash and validate the source before copying.
4. Write a partial file in the private session directory, sync as required by the platform contract,
   then atomically rename it to the working filename on the same volume.
5. Hash the working copy and require equality with the captured source before launch.
6. Record the initial metadata and atomically promote the READY manifest.

Never modify, rename, lock, or chmod the original. Never put the temporary copy beside an external
source. For Body 1 the source must already be a selected, available, DB-owned managed PNG.

### Checking for changes

Check runs off the Swing event-dispatch thread:

1. Require the exact current session and expected relative working path.
2. Open without following a substituted symbolic link and require a regular file within the session
   directory.
3. Read bounded bytes and metadata, then reread metadata after the read. If size or modification time
   changed during the read, report “still saving” and do not validate or import.
4. Compute SHA-256. If it equals the initial or last-reviewed hash, report “no saved changes.”
5. Dispatch to the exact format validator captured by the session.
6. Publish a candidate only if the session revision is still current.

Save As to another location is not discovered automatically. The UI should explain that the user must
save the staged working file. A later explicit **Choose Result…** route may admit another reviewed path,
but it is not part of Body 1.

## Validation and preview

### PNG Body 1

The validator must enforce the existing image safety ceiling and verify:

- PNG signature and bounded byte count;
- successful bounded decode with positive dimensions;
- width, height, pixel count, color model, alpha, and embedded-profile diagnostics;
- no trailing replacement, symbolic-link escape, or concurrent-save evidence;
- an independently owned immutable preview value.

The review shows original and candidate at the same fit/zoom on a shared checkerboard, plus dimensions,
byte size, alpha/profile warnings, and changed/unchanged truth. Pixel-difference visualization is useful
but not required for Body 1. Metadata changes without changed decoded pixels remain a real derivative
and should be described honestly rather than silently discarded.

### Blender optimization

The later Blender review retains the preserved study's before/after contract:

- identical isometric, top, and optional silhouette/wireframe cameras;
- vertices, triangles, objects, components, materials, textures, dimensions, and bytes;
- non-manifold, boundary, degenerate, normal, unit, and scale warnings;
- exact Blender version/build and recipe;
- retry from the untouched staged source at 10k, 25k, 50k, or custom triangle targets.

Blender's Decimate ratio is a triangle ratio, not a maximum geometric-error guarantee. The UI must
never promise that no point moved more than a physical tolerance. Automatic base inference, destructive
hole filling, arbitrary component deletion, rig-aware reduction, morph-target reduction, and texture
baking remain deferred.

## Approval and atomic import

The review offers:

- **Keep Derived** — admit a new canonical managed asset and derivation only;
- **Keep & Replace** — available only with an exact still-current entity/role preview; retain the old
  association in ordinary role history and activate the derivative;
- **Check Again** — replace only the staged review candidate;
- **Cancel** — admit nothing and leave source/association unchanged.

The transaction must recheck source asset/revision/hash, candidate hash/validation, session revision,
and any target/role/association revision. It then:

1. admits or reuses immutable candidate bytes;
2. creates the derivative `project_asset` with truthful format/metrics metadata;
3. records `content_derivation` with source/output hashes, operation, recipe version, editor identity,
   editor version, manifest version, parameters, warnings, and approval time;
4. optionally performs one exact existing role-history replacement;
5. commits once and publishes only committed state.

Any failure rolls back content, derivative asset, provenance, and association together. The source
asset and every external file remain unchanged. Retry must be content-idempotent and must not create
duplicate visible assets for the same exact derivative and recipe.

The database schema may require a new editor-session provenance projection or metadata extension.
That is an implementation design choice for the approved body; this research does not claim that the
current schema already stores every proposed field.

## Cancellation, crashes, and recovery

Cancel never kills a generic editor or closes its documents. It invalidates the session revision and
prevents later work from publishing. If the working file can be removed safely, delete the session.
If cleanup fails, mark it cancelled and retry bounded cleanup at next startup without importing.

On startup, scan only Talisman's editor-session cache root:

- READY or WAITING with unchanged bytes: offer **Reopen** or **Discard**;
- changed stable bytes: offer **Review**, **Reopen**, or **Discard**;
- REVIEWING: rebuild validation/preview from exact working bytes, never trust serialized preview pixels;
- COMMITTING: query canonical durable evidence before showing completed or retrying;
- FAILED: show sanitized diagnostics and **Discard**, with no automatic import;
- malformed manifest, symlink escape, or missing working file: quarantine as unusable and admit nothing.

Successful and explicitly discarded sessions are cleaned immediately. Failed bundles are retained only
when the user chooses **Keep Diagnostics**. Unresolved crash-recovery sessions remain visible until the
user resolves them; no age-based policy may silently import or delete the only changed working copy.

## Security and privacy

- Local-only is the default and is stated in the UI.
- Launch a chosen `.app` through Launch Services; never concatenate a shell command.
- Canonicalize and authorize every session path; reject symlinks and paths outside the private root.
- Treat editor output and 3D/image parsers as untrusted input.
- Keep byte, dimension, pixel, mesh, time, and diagnostic limits.
- Never pass credentials, project database paths, or unrelated campaign context to an editor.
- Never execute file-embedded scripts or arbitrary user Python through Talisman.
- Never upload to Adobe, Autodesk, Blender, or another service as part of local editing.
- Store editor identity and derivation evidence, not a durable temporary path.
- Logs contain phases, timings, hashes only when needed for diagnosis, safe names, validation codes, and
  exit/open-request status; no file contents or credentials.

Editor privacy promises remain bounded: opening a local file in a third-party application subjects that
application to its own settings and account behavior. Talisman can avoid initiating network actions but
cannot claim that a signed-in editor is offline unless the editor-specific runner enforces it.

## Performance targets to validate

These are proposed targets for implementation proof, not current guarantees:

| Operation | Proposed target |
| --- | --- |
| Create/hash a 25 MiB PNG working copy | under 2 s on the reference developer Mac |
| Launch request dispatch after READY | under 250 ms, excluding editor startup |
| Check/decode/preview a 4096 x 4096 PNG | under 2 s, off the EDT |
| UI responsiveness | no EDT task over 50 ms for file/hash/decode work |
| Cancel/stale result | no later UI publication or durable admission |
| Successful cleanup | no session payload retained after committed review closes |

The PNG body uses the existing 64 MiB image-safety ceiling unless an approved implementation body
establishes a smaller editor-session bound. Blender retains provisional 10k/25k/50k per-asset profiles,
but real acceptance requires end-to-end Viewer, battlefield, and Player measurements. Triangle count
alone is not a performance proof.

## Test fixtures

Only generated, license-clear, or explicitly owned fixtures may enter the repository.

PNG fixtures:

- opaque and transparent RGBA images;
- indexed, grayscale, interlaced, and profile-bearing PNGs;
- unchanged bytes, pixel change, metadata-only change, and atomic file replacement;
- empty, truncated, corrupt, oversized, extreme-dimension, and decompression-pressure inputs;
- Unicode, spaces, long labels, read-only source evidence, and disappearing session files.

Blender/3D fixtures retained from the preserved study:

- binary and ASCII STL with known dimensions and counts;
- clean organic and hard-surface meshes;
- disconnected parts, holes, non-manifold edges, inverted normals, duplicate/zero-area faces;
- 100k, 1M, and 5M triangle stress models;
- later OBJ materials/UVs, multi-object GLB, vertex colors, rigs, animation, and morph targets.

## Smallest useful editor-session body — awaiting explicit approval

### Body 1: macOS PNG external edit session

In scope:

- selected, available, DB-owned managed PNG only;
- macOS Launch Services discovery, explicit chosen-editor preference, and shell-free open;
- Photoshop as the named reference editor, while allowing another registered PNG editor chosen by the
  user through the same generic boundary;
- private session directory and versioned atomic manifest;
- exact source/revision/hash capture and verified working-copy equality;
- explicit Check, stable-read detection, bounded PNG validation, side-by-side review;
- Keep Derived and exact-context Keep & Replace through current immutable content and role history;
- cancellation, stale-session rejection, startup recovery, cleanup, and focused tests;
- Atlas/index/package/class/test-plan maintenance required by the implementation.

Out of scope:

- automatic save detection as authority, editor process monitoring, or editor termination;
- Photoshop UXP/ExtendScript/actions, PSD/PSB/TIFF/JPEG, cloud documents, or account control;
- editing external unmanaged files directly;
- Blender, Blockbench, Illustrator, Fusion, Windows, or Linux;
- file watchers beyond a non-authoritative later hint;
- database schema migration unless the approved body demonstrates it is unavoidable;
- application launch during automated tests or any broad test suite.

This body is independently useful: it creates a reviewed, provenance-bearing variant without altering
the original and proves the session, recovery, stale-state, preview, and atomic-import boundaries that
every later editor needs.

## Roadmap after Body 1

### Body 2: Blender canonical 3D finalization

Define one coherent Blender session/export/return boundary before implementation: exact compatibility
probing, a session-owned editable `.blend`, explicit user Save, a fixed packaged
offline/no-autoexec runner, separate GLB candidate export, independent validation/preview, provenance,
retry/cancel/recovery, and reviewed atomic promotion. If Talisman still lacks end-to-end GLB support,
use the existing canonical OBJ plus MTL path as a truthful fallback rather than implying GLB admission.
Do not split this into an open-only feature that presents itself as a round trip.

### Body 3: Blender interactive session

Add a session-scoped capability and private OS-local endpoint, allowlisted revisioned commands,
Talisman-owned simple modeling controls, explicit Blender checkpoints, and Talisman-side undo for
Talisman commands. Do not embed Blender's native window or claim continuous mirroring of arbitrary
Blender edits.

### Body 4: Photoshop native-source pair

Consider PSD/PSB authoring-source retention with an explicit PNG deliverable, profile/bit-depth policy,
and reliable export contract. The authoring source and flattened runtime derivative remain distinct.

### Body 5: Blockbench and Illustrator

Prove local Blockbench OBJ/glTF export on the four-prop fixture. Define Illustrator AI/SVG source and
font/linked-resource policy, then derive reviewed PNG output. Add a vector asset kind only if a real
Talisman consumer needs canonical vector data.

### Later

- GLB runtime admission after complete Viewer/Assets/Control/Player support;
- Blender texture/UV/baking, LOD, instancing, collision/footprint, and top-sprite generation;
- Windows/Linux launch adapters;
- optional Fusion workflow only for appropriately licensed users;
- other chosen editors through the generic format/session registry.

## Decision ledger

### Settled for design

- Originals are immutable; every accepted edit is a new derivative.
- The first body is managed PNG on macOS, not the full Blender companion.
- Explicit Check is authority; focus return, file watching, and process exit are not.
- Launch through Launch Services with an exact chosen app; never through a shell string.
- The implemented application gateway owns only exact discovery and one-way open-request truth.
- Caller-owned file lifecycle, editor session, review, import, recovery, and persistence stay outside
  the gateway.
- Temporary files live in a private disposable Talisman cache session.
- Preview and approval precede admission and any association change.
- User Assets, `content_derivation`, and existing role history remain durable authorities.
- Photoshop is the first implementation proof; Blender is the first deep 3D design.
- Blockbench is third and Illustrator fourth in candidate order.
- Fusion Personal Use is not a Talisman baseline.
- GLB/glTF 2.0 is the preferred future static derivative; OBJ plus MTL is the current fallback and STL
  remains geometry-only.
- Blender discovery/version support, scripting, `.blend` lifecycle, GLB export, and reviewed return
  require a future explicit coherent body.
- No editor-session or Blender-return implementation is authorized by this research body; only the
  bounded local-application gateway dependency was separately authorized.

### Unresolved before Body 1 approval

- Exact UI entry points: Media context action only, association leaf action, or both.
- Whether Keep Derived creates a sibling display-name convention or asks for a name.
- Exact editor-choice persistence representation under the intended macOS signing/sandbox model.
- Whether metadata-only PNG changes warrant an optional decoded-pixel equality notice.
- Exact diagnostic-retention presentation and user-controlled export location.
- Whether the proposed provenance fits metadata JSON or warrants normalized schema fields.
- Reference Mac and fixture sizes for ratifying performance targets.

### Unresolved before Blender implementation

- Talisman coordinate/forward-axis, grid, reference height, and source-unit default.
- Intel Mac requirement and therefore the maintained Blender family matrix.
- Legal packaging/license boundary for the Blender-side runner.
- Real Viewer/battlefield/Player geometry, material, texture, and byte budgets.
- Whether the top render becomes a preview, a managed derivative, or an active 2D role.
- When GLB becomes a canonical runtime format instead of a future exchange artifact.

### Deferred deliberately

- Automatic base inference/removal and destructive mesh repair.
- Error-bounded decimation promises.
- Arbitrary editor automation or generally listening local services.
- Continuous mirroring of unrestricted Blender edits.
- PSD/AI/SVG/GLB as new canonical managed kinds.
- Rigged, animated, or morph-target optimization.
- Cloud editing, upload, marketplace publication, or license-entitlement automation.
- Editor installation, updates, login, subscription management, or app termination.
