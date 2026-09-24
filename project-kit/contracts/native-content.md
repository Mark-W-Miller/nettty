# Local-first native content authoring and admission

1. Choose an accepted database Morph in the appropriate family using its complete definition and exact
   revision, not its display name alone. Record the authoritative source and provenance.
2. Edit the SAME Morph under its stable ID; use NEW with a new ID only for a deliberate clone/new
   object. Keep a complete independent native body, source provenance and all geometry/poses/motions
   and appearance dependencies. Preserve offline original experiments. Use the same native definitions
   for local render/edit/save and database payloads; no hidden inheritance or lookalike dialect.
3. Author Places and objects in the receiving authority's native contract. Separate definitions from
   instances/transforms, Character references and game-owned rules/session state. Height/texture/media
   are supporting data with their own identities, not necessarily skeletal Morph JSON.
4. Validate locally using the actual native codecs and supported validators. Record tool/contract
   versions and validation limits. A local fixture is not installed database content.
5. Agree the content package with the owning importer: exact identities/revisions, byte digests, roles,
   complete dependencies and target requirements. Preview before admission. The receiver validates the
   bytes; a client hash or an upload response is insufficient. Never invent endpoints or write tables.
6. Preserve unrelated records and immutable historical revisions; UPDATE the selected current identity
   using exact revision/digest guards. Do not force edits into duplicate IDs. Reuse identical replays.
   Specify stale-state, replay, interruption and recovery behavior. Claim atomicity only with evidence;
   otherwise stage the package and activate after complete admission.
7. Obtain a durable receipt and read back accepted identities/revisions/digests through supported reads.
   Activate only the coherent installed dependency set, then prove runtime loading after restart.
   Show missing/incompatible content; keep standalone fixture mode explicitly labelled.

Ordinary Save writes within the current authority; Import deliberately crosses authority. Do not make Save
silently contact a database or make viewing/navigation import content. Before each import persist the exact
request, immutable dependency-byte hashes, target and conditional guards. Use before-images/conflict stops
and endpoint-specific size/count limits. After an ambiguous transport result, recover by operation identity,
receipt and canonical read-back rather than blind resubmission. Per-item atomic writes do not make the whole
package atomic; retain a recoverable activation plan and report partial disposition honestly.

## Binding record and proof

For authoring → Morph, Morph → instance, terrain → Place, appearance → geometry, content → package,
package → database and database → game, record producer, consumer, owner, exact operation/codec,
source/server/build versions, input/output shape, dependencies, errors/replay and proof status.
Unresolved is an honest value. Import support remains unverified until the owning service provides proof.

First implementation proof: one textured numeric terrain/Place, a placed object Morph, a moving creature
and applicable Character data. Validate, preview/admit, read back and reload; demonstrate repeat import,
conflict/missing-reference rejection and recovery, plus geometry, texture alignment and ground contact.
These are content/integration acceptance checks, not application tests required for documentation assembly.

Native/database validity alone is not consumer acceptance. Exercise the real consumer display/game path
with exact model and texture/media bytes, full socket/ancestor transforms and cache identity that includes
asset bytes. Reopen through the ordinary catalog/gallery after restart. A source fixture, upload response,
database read-back or simplified renderer proves only its own gate.

## Source-specific candidate seams, not confirmed capabilities

Talisman examples supplied in Beetle/Atlas review: CoreMorphCatalogService reads/validates a catalog;
FactoryNativePackageService persists native packages but is not a universal game importer;
PersistentMapImportService concerns checked .dd2vtt/source-lease map admission through
MapDocumentService.commitIsolated, not arbitrary terrain packages; HeightmapRasterPngCodec encodes
heights but does not persist a Place; CreatedThingPresentationMediaService is a read projection.
GameObjectService/CharacterSheetService need confirmed Character bindings. Confirm actual supported
operations with Morph/Factory, terrain/Place, Character/media and host/security owners respectively.
These names do not establish callable APIs, current server support or cross-project portability.

See [Place](place.md), [Morph collection](morph-catalog.md) and [source evidence](../docs/UPDATE-SOURCES.md).

## Insect Wars receiver finding

The [first-practice report](../docs/DELIVERY-GATES.md) records an owner-reported single-Morph save
operation with per-item receipts, not a universal set importer. Owners are assessing per-item admission
with inventory/readback/resume and activation only after closure. Do not assume that set-wide atomicity
or a new importer is required, or that individual successful saves alone prove coherent-set acceptance.
Destination, native candidate compatibility and provenance handling still need owner evidence.

Tassy subsequently confirmed that per-item route is viable in principle for the first proof, with
all-member native preflight, deterministic per-item keys/receipts, inventory and exact full readback.
The shared Core Morph catalog must be explicitly selected; it is not a game-scoped destination.
No inheritance/deltas or nonempty texture_maps are supported and no set provenance is supplied.
At that earlier report time the service was stopped and destination choice pending; this is not a
current live probe. No successful import is established by this document.

The [portable return cycle](morph-return-cycle.md) specifies plural Morph-only ZIPs, automatic designated
import and same-ID UPDATE. It supersedes the 0.1.1 new-definition-only interpretation.

## Shared language and source materialization

Use [universal Morph semantics](morph.md) and [stage Metamorph composition](metamorph.md). Complete
native definitions do not imply full-body Stances; required Mapping/Resting fields belong to specific
codec profiles. Domain views share geometry/motion semantics. Core/default definitions and importable
packages remain in the source tree with explicit repeatable [materialization/readback](persistence.md).
