# Application integration

## Main shell

`AppServices` owns one application-lifetime `ContextService`, exposes it through the typed `context()`
accessor, and registers that same instance as `app.context` for diagnostic discovery. CM-02 starts it with
the standard policy. CM-05, CM-06, and CM-07 compose the pure Assets Manager Character, Adventure/Battle,
and Object Factory providers into that engine. CM-08 adds one initially empty `ContextProposalService`,
exposes it through `contextProposals()`, and registers the same instance as `app.contextProposals`.
CM-09 passes that exact service to the existing Character and Body Form pilots through one shared
`ContextualActionSurface`; it constructs no screen-local proposal service in production.

The reviewed composition and CM-03 shell supply steps 1 and 2. CM-04 supplies the reusable action
boundary in steps 4 and 5; later feature wiring provides only classified request inputs and destination
adapters:

1. Extend the provider composition only through reviewed
   application composition; do not construct a second service in a screen.
2. Keep the CM-03 Context tab attached through `addListenerWithCurrent`; screens must not create a
   second monitor model or service.
3. Publish a `ContextRequest` after application-area, activity, focus, or
   selection changes settle.
4. On **Ask AI**, invoke `ContextActionService.askAi` with the submit-time request, classified question,
   and a separate immediate destination.
5. On **Open in TaliTalk**, invoke `ContextActionService.openInTaliTalk` with the submit-time request,
   optional classified opening question, and a separate immediate destination.

Each invocation calls `ContextService.update` exactly once, then builds the landed CM-01 `ASK_AI` or
`TALI_TALK` payload from that same immutable snapshot. The installed snapshot is therefore also the one
seen by passive Context Monitor listeners. The destination receives the admitted payload, action ID, and
kind only; it never receives `ContextService`, a raw request, mutable screen state, or provider authority.
Destination acceptance means only that the destination accepted the handoff, not that an AI answered, a
conversation completed, or a mutation succeeded.

`ContextActionService.evidence()` is a fixed-capacity owner-private diagnostic view. It contains only
sequence/action IDs, action kind/phase/time, optional admitted snapshot ID, audience, a fixed
framework-owned code, and a dropped-row count. Destination text is never admitted. It retains no
question, target label/hints, node fact, payload bytes, destination exception, provider result, or answer.

Every pointer, focus, selection, and explicit-target reference must be assigned
the disclosure level of its complete label/key/hints representation. Questions
and other payload extensions use `ContextText` with their own disclosure level.
The convenience string constructors classify text as project data; callers
needing a different classification must use the typed form.

Snapshot listeners are passive observers. `ContextService` installs current
truth before notification and exception-isolates each listener, so a closed or
faulty presentation neither falsifies a successful update nor blocks later
observers. `addListenerWithCurrent` registers and replays installed truth under
the same update lock, preventing a reopen race. Integrations should still close
their listener registrations.
`AppServices.shutdown()` closes the proposal coordinator before the action and Context services, then
clears current snapshot/listener truth and makes later work fail closed. Closing more than once is
harmless. No production action authority is registered in CM-08. A later reviewed feature adapter
registers one fixed descriptor, performs a read-only currentness review, and atomically revalidates the
returned opaque stamp inside its existing mutation or semantic-operation owner. Screens and destinations
must not register a generic action, infer authority from the response contract, or treat a confirmation
token as a revision.

CM-09 does not change that authority boundary. The two agreed screen pilots can prepare and confirm only
through the shared surface and only while their feature-owned capture remains available. Each surface
retains opaque proposal/snapshot identity, rejects foreign receipts, and discards its own pending receipts
on close. The application still registers no production authority, so the shipped menus remain answer-only
and proposal acceptance is exercised with fakes only.

## Proposed Shelf/browser integration

The generic Shelf Context framework is a reviewed design checkpoint, not a current integration. GCS-01 is
separately authorized to implement only the manual Control Screen Copy/Paste profile described in
`GM-CONTROL-SCREEN-ADAPTER-PROFILE.md`; that adapter is not landed product behavior yet. A later generic
browser Context Workspace sends only stable typed reference intents through a separately owned Application
Server adapter. Feature-owned Java resolvers establish current immutable scalars and disclosure
classification; the existing `ContextService` performs final admission. JavaScript renders only the
returned sanitized preview and never constructs `talisman.application-context` payloads.

The proposed generic envelope family, exact role vocabulary, privacy/persistence rules, conformance
vectors, and Easy Tale first-consumer mockup are in the Context Management active-design folder. Current
Main still has no Context browser route, provider, database read, response/action identifier, or mutation
registered. The GCS-01 manual exchange must use the exact profile name and display-label contract when its
separately owned implementation returns for review.

The main-window **Monitors** button opens the shared monitor window. The UI
shell supplies the tab container; Context Management supplies the passive,
bounded **Context** tab over the one `AppServices.context()` instance. The
window detaches on hide and closes before AppServices shutdown.

## Adventure and battle

Adventure context should form the enclosing chain:

```text
Adventure → World → Parent Place(s) → Current Place → Encounter/Battle
```

Battle then contributes round/turn state, every relevant creature, conditions,
and the explicit target. Selecting Rusha in battle therefore includes the
battle and its participants. Selecting Rusha in Assets Manager does not: the
asset provider emits only generic asset ancestry and metadata.

Providers must avoid loading unbounded collections. Prefer a battle summary and
compact creature nodes; large journals, histories, or maps should be available
as references that an authorized AI adapter may retrieve on demand.

## Object Sing & Dance Factory

The Factory provider should expose stable keys for at least:

- factory document/object;
- 3D model and model version;
- scene or edit state;
- part/bone/joint ancestry;
- animation/state-machine state;
- selection set;
- explicit clicked target; and
- available read-only or mutating capabilities.

Suggested key forms are semantic, not Java class names:

```text
factory-object:<object-id>
model:<model-id>
joint:<model-id>/<joint-id>
selection:<document-id>/<selection-revision>
animation-state:<controller-id>/<state-id>
```

For multiple selected joints, contribute the model and common ancestry once,
one selection-set node, and one child per selected joint. The explicit
right-click target remains distinguishable from the rest of the selection.

Do not put live mesh buffers, images, or complete animation tracks into facts.
Use IDs, concise summaries, counts, transforms where relevant, and resolvable
references for larger data.

## Registration and compatibility

Providers should be registered through composition rather than hard-coded into
`ContextEngine`. Keep provider IDs and `ContextNodeKey` types stable. Evolve
response contract IDs and external serialization with explicit versions.

CM-01 fixes the canonical `talisman.application-context` version-one JSON
contract in `ApplicationContextPayloadCodec`. Later adapters must use its
`SNAPSHOT`, `ASK_AI`, or `TALI_TALK` shapes rather than inventing host-specific
maps. See [the complete serialized contract](APPLICATION-CONTEXT-PAYLOAD-V1.md).
CM-02 registers the module and the one application service without changing
that payload. CM-03 adds only the shared shell and passive Context projection.
CM-04 adds the reusable coordinator, Swing action session, and acknowledgement
pop-up without changing payload bytes. CM-05 composes the Assets Character provider and first screen
adapter without changing payload bytes. CM-06 and CM-07 add the Adventure/Battle and guarded Factory
scalar seams. CM-08 adds only structured proposal/confirmation coordination and explicit request overloads
for those two providers. CM-09 adopts one guarded surface in the two already-agreed pilots without adding
production authorities or production mutation IDs. Actual AI transport,
the TaliTalk conversation/session adapter, proposal-review UI, and live action adapters remain separate
reviewed integration bodies.

## CM-05 Assets Manager Character pilot

`CreatureCatalogPanel` offers **Ask AI** and **Open in TaliTalk** only for a Created Things Character.
At submit time, its screen adapter reads the already-loaded immutable Character projection on the EDT and
constructs separate explicit, pointer, focus, and selection references. The explicit right-click identity
and revision must still appear in the exact current selection; otherwise capture fails closed. Screen
close also invalidates every queued capture and closes its modeless pop-ups.

Settled visible Created Things selection changes publish the same immutable capture directly to
`ContextService.update`, deduplicating equal captures. This lets passive Monitors follow clicks without
making the monitor authoritative. A hidden Types/non-Character state publishes no Character references.
The reusable action always performs its own fresh update at submit.

The reference factory admits only PROJECT-classified name, exact revision, level, species, background,
bounded class labels, and status. `AssetsManagerContextProvider` transforms those values into one
deterministic Assets Manager/Character hierarchy without reading live data itself. It ignores every other
application area and activity. Documents, media bytes, paths, campaign identifiers, Adventure/Battle
state, and hidden provider data are not part of the pilot.

Both production destination adapters return unavailable in CM-05. Focused fakes prove that the same fresh
snapshot is installed for passive Monitors and delivered inside the reusable Ask AI or TaliTalk payload;
destination acceptance, provider completion, and conversation state never become Context truth.
