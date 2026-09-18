# Voice authoring and conversational assistance

Status: **MPK 0.1.10 shared guidance**, curated from Lee's local Voice Lab design. These are
requirements and proposed portable semantics, not proof of a working shared engine or accepted sound.
The [Voice Lab boundary](voice-lab.md) and [fixed AI provider options](ai-provider-dialogue.md) remain.

## Saved voice recipe and authored speech

Separate vocal identity (who), language/locale/pronunciation (linguistic realization), and performance
(how a line is delivered). Engine identity does not substitute for any of these. Translation is explicit;
speak the authored text. Persist typed recipe/configuration and, for a separately admitted performer,
versioned conditioning references. Generated audio is disposable cache output unless deliberately saved
or exported as a finished artifact. An accepted saved recording remains an explicit durable artifact.

Only authored utterance text enters a synthesis text field. Prompts, conversations, voice descriptions,
UI labels, recipe summaries, diagnostics and control instructions must never become audible text.
Send admitted voice/blend/rate parameters through typed engine fields and compose pauses through the
host's declared timing model. If a control lacks a supported engine field, retain it as inactive metadata
or reject it visibly; do not smuggle it into speech as inline tags. Authored bracketed text itself must
not be silently rewritten merely because the adapter does not generate control tags.

## Defaults, overrides and exact take identity

A passage/stanza provides defaults; each line either inherits them or has an explicit local recipe.
Editing an inherited control creates an override, with an explicit return-to-defaults action. Display
the effective recipe and retain source text identity/ranges. Model inheritance clearly enough that a
change to defaults cannot silently overwrite an independent line override.

Key each take to exact authored text, effective recipe, language/pronunciation, engine/model/voice
revisions and relevant synthesis settings. A changed effective input invalidates affected audio and any
aggregate take that uses it. Preserve unchanged override takes when unrelated defaults change. Never
present a stale take as current, and never let a cached take lock editing. Line preview renders only the
needed line; a matching cached take may be reused. Whole-passage rendering may reuse valid line takes.

Maintain working text/recipe separately from committed content. Preview and audition do not commit.
Explicit actions promote text/recipe, or text/recipe/audio together, through the owning feature's guarded
save boundary. Reload/navigation restore the correct draft and committed state; publishing source files
is not evidence that browser-stored authored content was saved or accepted.

## Application-owned conversation and proposal history

Voice assistance can use a conversation bound to an exact authoring object (for example, a stanza).
Persist user turns, replies, explanations, structured proposals and audition references under an explicit
local persistence/retention policy. Reopening or refreshing restores it; **Start fresh** explicitly resets
that conversation. Keep credentials out of saved records. The application owns history; provider-retained
sessions are not required, and prompt caching is not conversation memory.

Each explicit send includes bounded relevant history, the current typed context, latest message and the
proposal being refined. Apply the [outbound inspector](ai-provider-dialogue.md); disclose omitted history
rather than silently sending unbounded accumulated text. Switching among our hard-coded providers keeps
local history but sends nothing automatically: the next explicit Send uses reviewable context under the
newly selected provider. A switch also invalidates outstanding response generations from the prior session.

Relative requests such as “yes, but slower” refine the selected/latest proposal while preserving other
preferences and fields. Show what changed, permit an explanation-only reply with no recipe mutation, and
retain prior proposals for comparison/audition. **Accept** applies the chosen validated proposal to working
recipe state; committing the authored content/render remains a separate action. Reject stale targets.
A recipe suggestion is neither text mutation nor direct audio generation.

Label a deterministic local helper as rule-based assistance. Do not impersonate an AI provider, silently
fall back from a selected provider or imply a connection merely because an option exists. The synthesis
engine and recipe-assistance provider have different roles. Provider/model/endpoint choices remain
hard-coded by us, with no arbitrary provider entry or per-request credentials in the shared dialogue.
Conversation support here does not change the runtime heartbeat's singular-call decision contract.

## Playback and evidence

The host owns transport and content navigation; Voice Lab supplies ordered playback/cancellation and
bounded timing cues tied to the exact take/line identity. Active presentation surfaces can highlight the
spoken words from those cues. Declare estimated versus measured alignment; stale/cancelled takes cannot
update current highlighting. Do not duplicate transport controls simply because several views share audio.

Different audio hashes do not establish an audible blend or successful voice quality. Verify blend amount,
language/pronunciation and voice identity independently, including endpoints and intermediate settings.
Owner audition and browser/device acceptance remain separate from generated bytes and automated checks.

## Future capabilities

Lee's Kokoro Design → later performer workflow is a useful separation of identity design and delivery.
Chatterbox remains a local future candidate, not an admitted shared implementation or a new engine
requirement. A later performer needs exact dependencies, supported controls, provenance and acceptance;
saved conditioning should avoid regenerating the design reference for every runtime utterance.
Non-speech creature vocalizations are a separate future capability; speech TTS does not prove them solved.

Required later checks include authored-only synthesis payloads, inheritance/override restoration, exact
cache invalidation, conversation restoration/reset/switching, no-send-on-switch, stale completion rejection,
proposal comparison/acceptance and real audible language/blend behavior. None were rerun during curation.
