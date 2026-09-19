# Using document examples

Place v2 is an incomplete authoring form for the [Place contract](../contracts/place.md). Null height
and texture bindings intentionally mark missing data; they must not be fabricated. Units/axes/extent
must be explicitly authored to match both maps. A deliberate flat height requires actual numeric data.
The companion schema describes required shape only, not semantic/native validation or admission.
Character remains a reference example. Neither file is an installed database record or universal default.
Use the owning native codec and retain identities/revisions/provenance and explicit resolved dependencies.


`voice-lab-package.json` is a package-receipt template for the [Voice Lab contract](../contracts/voice-lab.md).
It names candidate STT/TTS engines, voice ids, deployment targets, recording policy and acceptance status. It
is not an engine installer and must not contain private recordings, model bytes, credentials or local paths.

[MPK-HANDOFF.template.json](../lifecycle/MPK-HANDOFF.template.json) is the machine-readable owner receipt for
[collecting project updates](COLLECTING-PROJECT-UPDATES.md). Fill placeholders before use.
