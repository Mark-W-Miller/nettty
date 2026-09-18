# Adopt of MPK 0.1.8

This release adds the Voice Lab package contract. It preserves all 0.1.7 behavior-executor doctrine and prior
release artifacts.

Replace any provisional candidate with exact published 0.1.8 bytes; preserve draft history separately.
Use [the installer](INSTALLING-PROJECT-KIT.md) and record the final commit/digest, not the old candidate
base revision. Adopt [fixed provider options](../contracts/ai-provider-dialogue.md) and the
[owner handoff/collection procedure](COLLECTING-PROJECT-UPDATES.md). No runtime acceptance is implied.

- Add `contracts/voice-lab.md` to any consumer planning speech capture, speech-to-text, text-to-speech, or
  saved voice artifacts.
- Use `templates/voice-lab-package.json` as the local starting point; keep private recordings, model weights,
  native binaries, credentials, and generated audio outside the Kit.
- Prefer the first shared stack decision unless local evidence overrides it: Whisper/`whisper.cpp` for STT,
  Kokoro-82M for TTS, and Piper only as a recorded alternate with exact code/voice licence receipts.
- Do not promote voice cloning, speaker identification, voice authentication, automatic commands, provider-only
  APIs, or hidden recording as part of this release.
- For Linux Moondance deployment, prove authenticated HTTPS/WebSocket admission, origin checks, duration/size
  limits, worker/concurrency limits, overload rejection, retention truth, and no cross-user disclosure.
- Record separate acceptance for local Tassy, Moondance Linux, LAN/mobile browser routes, and public web routes.

No server, browser controller, engine binary, model, voice, consumer adoption, or live deployment is supplied
by this documentation release.
