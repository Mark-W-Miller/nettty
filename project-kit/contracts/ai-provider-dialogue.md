# AI provider dialogue

Status: **MPK 0.1.8 documentation contract**, collected from Rougish and corrected by Mark on 2026-09-18.
This specifies a shared interaction pattern; it is not evidence of implemented provider access of runtime acceptance.

## Fixed provider options for now

We supply the AI providers and hard-code all available provider options in application code for now.
The supported providers, model choices, endpoint bindings and exposed capability/options are defined by
us. Users may select only from those built-in choices where the application offers a selector. There is
no user-defined provider, arbitrary model/endpoint entry, dynamic provider discovery, adapter installation
or imported provider configuration. Adding or changing the available options requires a code change by
us. An internal adapter registry, if used, contains only that fixed built-in set; it is not an extension UI.

Readiness may change with service or credential availability, but does not expand the configured choices.
An unavailable option stays visibly unavailable; do not silently switch providers. Keep credentials in
the host's credential mechanism, never in the transcript, content or provider-options display. This contract
does not invent a provider/model roster or claim that any particular provider is connected.

## Recommended dialogue pattern

A feature that wants AI assistance should expose one explicit action such as **Talk to AI** or **Plan with AI**. Activating it opens a **separate modeless dialogue/workbench**, leaving the originating editor visible and unchanged. The provider conversation is exploratory until the user deliberately accepts something back into the owning feature.

The dialogue should have these visible parts:

1. **Provider and capability header.** Show the selected provider and its readiness. Provider choices come only from the fixed, hard-coded set above. An unavailable provider is visibly unavailable; the UI must not silently substitute another provider. Selection among exposed built-in choices belongs to this dialogue's isolated session; provider/model/endpoint definitions are not editable here. Credentials are never displayed, copied into the transcript, or persisted with feature content.
2. **Context selection.** Show the bounded application context that may be sent and let the user include or exclude reviewable entries. Context entries retain stable source identity/revision/provenance stamps. Binary assets, unrelated database rows, secrets and arbitrary metadata are excluded unless an explicit provider contract admits them.
3. **Conversation.** Present a familiar scrolling transcript with user turns and provider replies, plus one current-message editor and a clear **Send** action. Multiple modeless dialogues may coexist, but each owns an independent conversation, selected context, provider session, response history and candidate. Sending in one window must never alter another window's conversation.
4. **Talk Inspector / What will be sent.** Before or alongside Send, provide an exact inspectable rendering of the outbound turn: provider/system instructions, feature request and answers, selected context and stamps, relevant prior conversation, current message and output contract. This is the decisive trust feature: the user can see what the provider receives instead of trusting an invisible prompt assembly.
5. **Response review.** A provider reply remains conversation. It does not directly mutate the feature, database, Morph, Place, object or other canonical content. Text replies may be copied into a distinct editable **Candidate/Response** review area; image replies remain bounded binary presentation with an identity-only transcript representation unless the feature has a separate reviewed image-admission contract.
6. **Explicit acceptance.** Validate the reviewed candidate through the owning feature's codec/schema and against the exact originating object identity/revision. Only an explicit action such as **Accept**, **Accept Plan**, or another feature-owned reviewed commit may transfer the candidate into feature state. If further execution is consequential, keep that behind a second explicit action (for example **Approve Plan** / **Do It**) rather than treating AI acceptance as execution authority.

A compact dialogue layout can therefore read conceptually as:

```text
+ Talk to AI -----------------------------------------------------------+
| Provider: [provider v]   Ready        [Health]                        |
| Context:  [x] current object  [x] selected references  [Review...]   |
|----------------------------------------------------------------------|
| You:      Help me refine the selected dungeon room...                |
| Provider: ...                                                        |
| You:      Keep the wall Morphs simple and texture-driven.            |
| Provider: ...                                                        |
|----------------------------------------------------------------------|
| Message: [________________________________________________________]   |
|                                      [What will be sent] [Send]       |
|----------------------------------------------------------------------|
| Reviewed candidate (optional, feature-owned):                        |
| [editable response / plan _______________________________________]   |
|                                      [Validate] [Accept]              |
+----------------------------------------------------------------------+
```

The exact labels can follow the consuming application's vocabulary; the separation of **conversation → reviewed candidate → validation → explicit acceptance/execution** is the important contract.

## Lifecycle and threading recommendation

Each dialogue owns one isolated provider session. **Send**, **Repeat**, **Health**, and any provider call acknowledge immediately in their initiating controls, become busy/duplicate-disabled, and run off the UI thread. Completion may update only the still-current dialogue/session generation. Reset or Close invalidates that window's outstanding UI generation and releases its provider clients; a late response cannot populate or accept a candidate after the window has moved on. Provider transport failures remain bounded visible errors in that dialogue and never trigger an alternate provider automatically.

The originating feature supplies an immutable, bounded context package; it does not hand the provider a live repository, database handle, event bus, arbitrary filesystem path, or mutation callback. Provider responses are suggestions, not authority. Conversation state may be volatile unless a separate transcript persistence contract is deliberately defined.

## Ownership and runtime boundary

The pattern applies to Adventure planning, Morph authoring, dungeon/content generation and character
creation. Built-in adapters own transport/capability; the dialogue owns temporary conversation; the
feature owns validation; canonical repositories/services retain mutation authority.

This interactive authoring dialogue is separate from [bounded AI runtime decisions](ai-runtime-decisions.md).
A conversation here does not turn a campaign decision slot into a multi-turn loop or grant automatic
world-write authority. Runtime scheduling, spending limits and the ban on render/physics inference remain.
The fixed provider-options policy also applies to provider selection for those runtime decisions.

## Later implementation checks

Use fake providers to verify session isolation, busy/duplicate-send handling, exact inspected/sent
content, credential exclusion, reset/close late-response rejection, unavailable-provider behavior,
rejection of custom provider/model/endpoint values and explicit revision-checked candidate acceptance.
These are acceptance requirements, not tests executed or capabilities implemented by this contract.

## Source and curation

Collected from Rougish's `project-kit-local/AI-PROVIDER-DIALOGUE.md`. Its owner describes reference
Talisman Adventure/TaliTalk behavior and Mark's approval of the interaction pattern; it does not prove
live Rougish provider calls. Mark's 2026-09-18 correction supersedes the proposal's extensible-provider
and optional model/endpoint-edit wording: all provider options are hard-coded by us for now.
The source recommendation remains unchanged in Rougish. Included in MPK 0.1.8; provider implementation remains unverified.

## Voice-assistance refinement — 0.1.10

For persisted authoring conversations, follow [voice authoring](voice-authoring.md): bind history to the
exact authoring object, define retention/reset, preserve history across built-in provider selection,
inspect bounded outbound context and retain proposals for relative refinement/comparison. Switching
never sends automatically; acceptance changes working recipes only. The fixed provider-options policy
above remains authoritative despite configurable provider fields reported in Lee's local implementation.
