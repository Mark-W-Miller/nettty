# Voice Lab package

MPK 0.1.8 adds a portable Voice Lab package shape for projects that need local or Moondance-hosted speech
capture, speech-to-text, text-to-speech, and explicit saved voice artifacts.

The minimal decision is one sidecar service behind the product host:

```text
browser Voice Lab
  -> Tassy local host or Moondance Linux HTTPS/WebSocket boundary
    -> admitted STT engine: Whisper/whisper.cpp
    -> admitted TTS engine: Kokoro-82M first, Piper only as a recorded alternate
    -> explicit recording artifact store when the user saves audio
```

This keeps the browser small and prevents every application field from inventing a recognizer, provider
account, microphone policy, or retention rule. It also lets Tassy and the Linux Moondance server share the
same package identity while using different native builds.

## What the package does

- Names the accepted speech-to-text and text-to-speech engine families.
- Records exact engine, model, voice, licence, checksum, platform, and memory receipts.
- Defines a small Voice Lab surface: Record, Transcribe, Speak.
- Keeps recordings explicit: start, stop, play, save/export/delete, retention, and optional transcription.
- Keeps generated transcript and generated speech as reviewable user artifacts.
- Requires local and Linux deployment evidence before a target is claimed.

## What it does not do

- It does not ship Whisper, Kokoro, Piper, model weights, voices, containers, or binaries.
- It does not clone Mark's voice or anyone else's voice.
- It does not treat a recording as consent to train, clone, identify, or authenticate a speaker.
- It does not use speech as a command path; speech becomes editable text first.
- It does not make browser Web Speech API, cloud APIs, or provider accounts the required implementation.

## First-stack note

`whisper.cpp` is the first speech-to-text family because it already fits the Voice Recognition active design:
local/offline execution, Apple Silicon first body, and Linux/Moondance deployment after receipt checks. Exact
model choice remains measured per target; start with a small/base quantized candidate and promote only after
latency, memory, and accuracy are recorded.

Kokoro-82M is the first text-to-speech family for the shared package because its surfaced licence posture is
simpler for Moondance deployment than current voice-cloning packages, and because voice choice can be recorded
as package data. Piper remains useful, especially for CPU-only deployments, but the package must distinguish
archived MIT Rhasspy Piper, current GPL-3 Open Home Foundation Piper, and each voice model's licence.

Voice cloning packages such as XTTS-v2 stay outside this release because the shared Kit needs deployable,
licence-clean doctrine. Recording yourself belongs in Voice Lab as an artifact workflow first; custom voice
training or cloning is a later body.

## Files added

- [Voice Lab package contract](../contracts/voice-lab.md)
- [Voice Lab package schema](../schemas/voice-lab-package.schema.json)
- [Voice Lab package template](../templates/voice-lab-package.json)
- [Voice Lab package example](../examples/voice-lab-package.json)

## Single-page consumer build recipe

A consumer that already owns its page, text, stanza sequencing, and animation should not rebuild those pieces.
Adopt Voice Lab at one narrow boundary: the page hands the text it already intends to speak to a tiny JavaScript
adapter, and the adapter hands that text to the local Voice Lab service. The page remains authoritative for *when*
a stanza starts and ends; Voice Lab is authoritative only for synthesis and playback state.

For a Poetry Lab-style single-page HTML/CSS/JavaScript consumer, the minimum build is:

1. Keep the existing stanza model and movie/animation timing unchanged.
2. Add one page-side module such as `voice-lab.js`. It may be written or placed wherever that project normally
   keeps JavaScript; the Kit does not prescribe filenames or a framework.
3. Configure one service base URL. For a local standalone page, use the consumer's local server/sidecar route
   (for example a same-origin `/voice/` proxy or an admitted loopback endpoint). For Moondance deployment, route
   the same adapter to the Moondance Linux Voice Lab service. Do not put engine-specific code in stanza logic.
4. When the consumer starts a stanza, call the adapter with the exact text the consumer already selected and a
   stable admitted `voiceId`. The adapter requests synthesis, receives playable audio, and reports playback state.
5. If the next stanza chooses another voice, pass the new `voiceId` on that next call. Voice changes are data at
   the stanza boundary; no Poetry Lab code needs to know Kokoro, Piper, model paths, or native build details.
6. On stanza skip, restart, page teardown, or user Stop, call `stop()` before starting another utterance. The
   consumer decides whether animation waits for speech, speech waits for animation, or both run together.
7. Treat failure as non-fatal to the page. The poem and animation remain usable if synthesis is unavailable.

The page-side contract is intentionally tiny:

```js
// Consumer-owned adapter shape; implementation may use fetch, Audio, Web Audio, etc.
const voiceLab = createVoiceLab({ baseUrl: '/voice' });

await voiceLab.speak({
  text: stanza.text,
  voiceId: stanza.voiceId ?? 'default',
  language: stanza.language ?? 'en',
});

voiceLab.stop();
```

A conforming adapter exposes at least:

- `speak({ text, voiceId, language? }) -> Promise` — cancels or rejects overlapping playback according to the
  consumer's declared policy, synthesizes the supplied text, and starts playback;
- `stop()` — immediately stops current playback and cancels an in-flight synthesis request when possible;
- optional state callbacks/events for `synthesizing`, `playing`, `stopped`, `ended`, and `failed`.

The service boundary may expose any internal route shape the consumer prefers, but a minimal HTTP mapping is
`POST /voice/synthesize` with text, voice id, and language, returning an audio response plus admitted
engine/model/voice identity in headers or metadata. The consumer may instead use another route or streaming
transport if it preserves the same product-level contract.

### What the consuming builder is responsible for

The consuming project chooses its own filenames, module structure, local server, bundling (or no bundling), and
playback implementation. It may run as a plain single page served by a tiny local HTTP server. It does **not**
need Node, a front-end framework, or a build system merely to adopt Voice Lab. If the selected TTS engine cannot
run inside the browser, the local server/sidecar owns the native engine and model while the page remains ordinary
HTML/CSS/JavaScript.

Following this recipe is sufficient for the page integration layer: existing stanza code supplies `text` and
`voiceId`; Voice Lab supplies synthesized speech. Recording and speech-to-text can be added later through the same
service without changing the stanza/movie contract.

## Consumer adoption

A consumer should copy the template into its local guidance area, fill only receipts it has actually verified,
and keep private recordings outside `project-kit/`. The shared Kit describes the package shape; the consuming
project owns server installation, native builds, runtime hardening, model acquisition, acceptance evidence, and
user-facing launch.

## Lessons from Lee — 0.1.10

See [voice authoring](../contracts/voice-authoring.md) for the shared refinement and
[the curation record](LEE-VOICE-LAB-FINDINGS.md) for evidence limits. These 0.1.10 additions preserve
the earlier release history and do not establish a tested shared runtime.
