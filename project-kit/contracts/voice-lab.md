# Voice Lab package contract

Status: portable documentation requirement for MPK 0.1.8. It records the minimal shared shape for a
Voice Lab package. It does not include third-party engines, model weights, recordings, binaries, a server,
a browser controller, or an accepted consumer implementation.

## Decision

Use one small self-hosted voice sidecar behind Tassy or the Linux Moondance server. The Moondance browser
surface talks to the local product host, not directly to arbitrary recognizers or synthesis providers.
That host admits a package, checks licences and checksums, then dispatches to selected local engines.

The first portable package is deliberately narrow:

- **Speech to text:** Whisper through `whisper.cpp` or an equivalent service wrapper that preserves the same
  Moondance recognition semantics: admitted session, bounded audio, partial/final transcript, cancellation,
  retention truth, and no automatic command execution.
- **Text to speech:** Kokoro-82M is the preferred first self-hosted synthesis family because its model and
  common inference library are Apache-2.0 surfaced, lightweight enough for a lab server, and voice selection
  is explicit package data rather than a hidden provider account.
- **Recording:** browser or lab recording is a first-class artifact type with explicit start, stop, playback,
  export, delete, and retention state. A recording is not training data, a voiceprint, or consent to clone a
  voice.
- **Voice control:** control means selecting an admitted voice/style and bounded synthesis parameters such as
  speed, sample format, normalization, and optional style/voice id. It does not mean imitating a person or
  changing licensing/consent rules.

Voice cloning, speaker identification, authentication by voice, emotion inference, persistent passive
recording, provider-only APIs, and automatic speech commands are out of scope for this package. A later
custom-voice body may use the same recording artifacts only after a new consent, licence, safety, and review
contract is accepted.

## Runtime topology

A consumer may expose two deployment targets with the same package identity.

### Local Tassy target

- Runs on the user's trusted local machine.
- May use local loopback or trusted local HTTPS as required by the browser microphone.
- May keep recordings in a local lab store when the user explicitly saves them.
- Must remain usable offline once engines and models are installed.

### Linux Moondance target

- Runs as a sandboxed Linux service or sidecar controlled by the Moondance server.
- Requires authenticated HTTPS/WebSocket ingress, same-origin or declared-origin checks, size/duration limits,
  concurrency limits, and overload rejection before accepting audio.
- Stores no raw audio or generated audio by default. If the user saves a recording or generated sample, the
  server records owner, purpose, retention, digest, and deletion status.
- Must not expose engine upload, model loading, file paths, shell commands, or arbitrary provider credentials
  to browser clients.

## Package contents

A Voice Lab package is documentation and configuration, not a model bundle. It may name candidate engines and
voices, but model files and binaries remain separately acquired dependencies with their own receipts.

Required records:

- package id, title, version, owner, source revision, and package digest;
- deployment targets and whether each target is local-only, LAN, or public-web eligible;
- speech-to-text engine receipts: engine name, upstream project, licence id, exact version/build, supported
  platforms, model id, model licence, model checksum, memory estimate, and supported languages;
- text-to-speech engine receipts: engine name, upstream project, licence id, exact version/build, supported
  platforms, model id, model licence, model checksum, speaker/voice list, and synthesis parameters;
- voice entries with stable ids, display names, language, engine binding, licence/provenance, allowed uses,
  and controls exposed to the user;
- recording policy covering capture trigger, retention default, saved artifact shape, transcript relation,
  deletion, export, and diagnostic logging;
- UI and accessibility requirements for visible listening/speaking state, keyboard operation, live status,
  non-color-only indicators, cancellation, and review-before-send;
- acceptance evidence for local Mac/Tassy, Linux Moondance, and any physical mobile browser/LAN route claimed.

Do not put secrets, private recordings, raw training audio, local filesystem paths, or unreviewed model bytes
in a portable package.

## Licence admission rules

Every engine, model, voice, wrapper, and container image has an independent licence receipt. A permissive
engine licence does not admit every model loaded by that engine, and a permissive model licence does not admit
every wrapper or container.

- `whisper.cpp` and OpenAI Whisper weights are acceptable first speech-to-text candidates when the exact
  binary/model receipts are retained and the deployment target can meet latency and memory limits.
- Kokoro-82M is the preferred first text-to-speech candidate for the shared package because its primary model
  card and common inference library surface Apache-2.0 licensing. Retain the exact model hash and any voice
  list revision used by the package.
- Piper may be admitted as a consumer-local alternate only when the specific code lineage, voice model,
  licence, and conveyance obligations are recorded. The older Rhasspy repository was MIT and archived; the
  current Open Home Foundation lineage is GPL-3.0, which is acceptable only with the corresponding source and
  distribution obligations understood.
- Coqui XTTS-v2 and other non-commercial or custom voice-cloning weights are not admitted into the default
  shared package. They may be evaluated in a separate private lab only if their non-commercial, consent, and
  output restrictions are carried explicitly and no project treats the result as deployable shared doctrine.

When a licence cannot be verified, the package marks the component `blocked: licence-unverified` rather than
falling back silently.

## Service semantics

The host exposes a stable product-level contract, regardless of engine.

### Speech to text

1. `start` admits or rejects a user-bound utterance with protocol, format, language, limits, engine/model,
   retention policy, and server session id.
2. Audio frames are ordered, bounded, authenticated to the utterance, and reject duplicates, late frames,
   oversized buffers, unsupported formats, and foreign identities.
3. Events are ordered and terminal: admitted, partial transcript, final transcript, cancelled, failed, and
   closed. Only one terminal outcome is valid.
4. Final transcript becomes editable text. It never submits, sends, approves, saves, or executes.

### Text to speech

1. `synthesize` accepts text, voice id, language, output format, and bounded controls.
2. It returns generated audio plus engine/model/voice identity and retention disposition.
3. Generated audio is a transient response unless the user explicitly saves it as a Voice Lab artifact.
4. Logged metadata excludes full text by default; diagnostic text logging must be an explicit mode.

### Recording

1. `record` captures only after explicit user activation and browser permission.
2. Stop/cancel is always visible and keyboard reachable.
3. Saved recordings receive id, owner, purpose, mime type, duration, digest, created time, retention rule,
   transcript relation, and deletion state.
4. Recordings are not silently sent to STT/TTS, AI providers, training pipelines, or remote storage.

## UI contract

The Voice Lab surface has three plain modes:

- **Record:** capture, play back, save/export/delete, and optionally transcribe the selected recording.
- **Transcribe:** microphone or selected recording to editable transcript.
- **Speak:** typed text to selected admitted voice, with explicit preview/play/save/export.

The surface shows route and privacy before capture or synthesis, for example `Local Tassy`, `Moondance Linux`,
`Whisper`, `Kokoro`, `raw audio discarded`, or `saved recording`. The status is expressed with words and
accessible state, not color alone. It must be clear whether the app is listening, transcribing, synthesizing,
playing, saving, stopped, cancelled, or failed.

## Single-page application integration contract

Voice Lab must be adoptable by a consumer that is already a complete single-page HTML/CSS/JavaScript experience.
The consumer must not be required to move its text, stanza sequencing, animation, or presentation model into
Voice Lab. The integration boundary is one-way and minimal: consumer text plus an admitted voice selection go in;
playable synthesized speech plus bounded playback state come back.

Required page-side semantics:

- The consumer owns the source text and the moment at which a stanza/segment is spoken.
- The consumer calls a small adapter equivalent to `speak({text, voiceId, language?})` and `stop()`.
- Voice selection may change on every call. Voice ids are stable package ids, not engine/model paths.
- `stop()` is safe during synthesis or playback and is used on skip, restart, replacement utterance, or teardown.
- Synthesis failure never blocks the consumer's non-voice experience; the page may continue silently.
- Animation/movie synchronization is consumer-owned. Voice Lab exposes state/events but does not control the movie.
- The adapter hides engine details. Stanza code never imports Kokoro/Piper/Whisper libraries or native bindings.

A minimal local deployment therefore has two pieces: ordinary browser JavaScript plus an admitted local voice
service. A project may serve both from one local HTTP origin, proxy `/voice/*` to a sidecar, or use a declared
loopback origin. A Linux Moondance deployment uses the same page contract against the Moondance-hosted service.
The consuming project owns exact filenames, module layout, server technology, and bundling choice. No framework,
Node runtime, or front-end build step is required by this contract.

The minimal synthesis operation is equivalent to `POST /voice/synthesize` carrying `text`, `voiceId`, and optional
`language`, returning playable audio and enough metadata to identify the admitted engine/model/voice used. Exact
HTTP paths are illustrative rather than normative; the stable requirement is the page-level `speak`/`stop`
behavior and engine-neutral voice ids.

## Acceptance

A consumer may claim Voice Lab package adoption only after proving:

- exact package file validates against the schema and all dependency receipts are present;
- no third-party binaries, weights, voices, or recordings were smuggled into the Kit;
- local target can record, transcribe, synthesize, cancel, save/export/delete, and recover from denial/failure;
- Linux Moondance target can run the same package identity with HTTPS/WebSocket admission, authentication,
  limits, overload handling, retention truth, and no cross-user disclosure;
- first physical browser routes that are claimed, including iPad Safari if claimed, have separate evidence;
- generated transcripts and generated speech remain user-reviewed artifacts, not authority to mutate game,
  project, AI, or account state.

## Voice-authoring refinement — 0.1.10

[Voice authoring](voice-authoring.md) adds recipe/language/performance separation, authored-only synthesis
payloads, line inheritance and effective-take invalidation, application-owned proposal conversations and
playback-cue acceptance. Read [Lee's curation record](../docs/LEE-VOICE-LAB-FINDINGS.md) for source hashes,
local exceptions and deferred capabilities. These requirements have not been runtime-validated.
