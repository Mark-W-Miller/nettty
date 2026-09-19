# Lee Voice Lab findings — MPK 0.1.10 curation

Status: incorporated in MPK 0.1.10. Distribution receipts are recorded separately.

## Source evidence

Read current working-file bytes from LHO. Source HEAD at inspection: `a94cbf70d9dc3b1072bd55aa970c5f559333dc79`.
Hashes below identify the actual documents even where their edits are uncommitted. The local profile
reports VP-02J implementation; the active design explicitly leaves owner browser acceptance pending.
No source implementation, live provider request, browser or audible acceptance was independently tested.

- `project-kit-local/README.md` — SHA-256 `abe9bfc274b0ce57c468743a0bcbf90a61bf9f205a80ebb6df23c2925e733c85`.
- `project-kit-local/voice-lab-interface-profile.md` — SHA-256 `e263da9a5b4a55ee220ffd8aa01a88ba2fc3dcfd88622f77d72893b7ceae2ad6`.
- `design/Active Designs/Voice Performance/ACTIVE-DESIGN.md` — SHA-256 `beadaf663a8a5515bb4600dcf534abdc59548d2053a44eabfb17bb5473075f1f`.
- `design/Active Designs/Voice Performance/TEST-PLAN.md` — SHA-256 `b58a1bf1a84eb7137001caac70b986d8c60bfb01b77d08798e715eafe5ddfb79`.

## Dispositions

| Finding | Disposition and shared treatment |
| --- | --- |
| Authored-only synthesis text and typed recipe controls | Promote to voice-authoring contract; prompts/metadata never become speech |
| Recipe authority, explicit saved recordings, language versus identity/performance | Promote with independent capability/admission boundaries |
| Default/line overrides and effective-input cache invalidation | Promote; preserve unaffected override takes and never lock editing |
| Local conversation restoration, relative refinement, proposal comparison | Promote for authoring dialogues with bounded context and explicit retention/reset |
| Provider switching keeps local history | Promote with explicit Send, disclosure and stale-response invalidation |
| Local Assistance/OpenAI/Ollama/Codex catalog suggestions | Retain as local candidates; no newly supported provider roster is asserted |
| Arbitrary provider/endpoint/model/API-key fields reported in VP-02I | Do not promote; Mark's newer hard-coded-options policy controls shared guidance |
| Source labels a local heuristic as fallback | Require visibly rule-based assistance with no silent selected-provider substitution |
| Playback cues and audible blend verification | Promote as requirements; different hashes are not perceptual acceptance |
| Poem shelf arrangement, colors, exact paths/ports and VP body history | Retain locally; not universal Kit layout or runtime truth |
| Chatterbox performer and creature sounds | Defer implementation/admission; retain design seams without a shared-engine promise |
| Editing the copied local Kit alongside development | Preserve LHO's owner-reported local workflow; changes remain proposals, not releases. Keep exact diffs/provenance in project-kit-local for collection |

See [voice authoring](../contracts/voice-authoring.md) and [AI provider dialogue](../contracts/ai-provider-dialogue.md).
The copied LHO Kit matched its 0.1.9 inventory at this inspection; the new findings came from its local
supplements and active design/test-plan documents. Source inspection did not change LHO files; later release distribution is recorded separately.
