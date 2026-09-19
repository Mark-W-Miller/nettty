# Talisman Architecture test plan

## Scope and authority

This living plan owns focused proof for Operations Atlas implementation bodies. The implemented bodies are
TA-01 (documentation, versioned record schema, four synthetic conformance examples, and deterministic
offline validation) and TA-01A (one verified partial Shelf-native Puppeteer CapabilityRoute seed plus route
reference validation). Neither body adds a production service, command, event, persistence store, reader,
UI, worker, provider, server lifecycle action, or application launch.

The current contract is [Operations Atlas record contract](../../../development-atlas/operations-contract.md).
Its machine schema, four TA-01 examples, and TA-01A route seed remain conformance data and entry guidance.
TA-02 adds the separate repository current-truth store and read-only resolver; its checked-in seeds are
evidence-backed only to the strength stated in their source receipts.

## TA-01 and TA-01A — focused contract validation

Run only the existing focused Atlas task:

```text
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew --offline --no-daemon \
  validateDevelopmentArchitectureAtlas
```

The task remains restricted to `DevelopmentArchitectureAtlasValidatorTest` and its local repository files.
It performs no network request and modifies no source or operational state.

Required TA-01 selectors:

- `currentAtlasAndIndexReferencesResolve` proves current Markdown/index references plus all checked-in
  operations schema examples.
- `operationsAtlasExamplesSatisfyVersionedContract` proves the required source-only, landed/equal,
  session-gated, and restart-required vectors remain present and valid, together with any additional
  route-specific conformance seed.
- `operationsAtlasContractRejectsMalformedRecords` proves missing/unknown fields, bad IDs, duplicate IDs,
  invalid scalar values, and missing repository paths fail closed.
- `operationsAtlasContractSeparatesSourceResultAndRuntimeTruth` proves materialization cannot replace source
  authority; task/result stages and commit fields cannot disagree; equal is not live; session-gated is not
  anonymous live access; and stale runtime cannot satisfy restart-required source.

Required TA-01A selectors:

- `operationsAtlasPuppeteerRouteSeedResolvesVerifiedReferences` proves the seed's owner, starting design,
  inherited v1 and exact v2 Shelf contracts, service contract, native/browser source paths, runtime service
  node, existing automated test selectors, and pending-manual scenario references resolve.
- `operationsAtlasRouteGapsRepresentTruthfulMissingOwnerAndDesign` proves a partial route can omit an
  unverified permanent owner or exact starting design only when it carries the corresponding explicit gap;
  the design gap directs the owner to write the interface design before coding.
- `operationsAtlasPuppeteerRouteSeedRejectsFalseResolution` proves a current route cannot retain gaps;
  omitted or unresolved owner/design/handoff, contract/source/evidence references, a feature owner posing as
  a patch companion, and evidence-state masquerading cannot appear resolved; a pending runtime requires an
  explicit gap and cannot be relabeled live without an exact runtime receipt.

Also run `git diff --check`. No broad test suite, application launch, server launch, browser run, or manual
screen claim is authorized by TA-01A.

## TA-01A pending implementation scenarios

These scenarios are referenced by the route as `manual-scenario` / `pending`. They are not TA-01A test
results. The future Object Factory implementation body owns their execution and evidence.

### TA-01A-PUPPETEER-RETAINED-CONTROLS

Close and reopen the Puppeteer Shelf Item through the declared twist label. The exact admitted controls,
Body Form child disclosure state, stance, motion selection, and local edit state remain retained. No second
Close control appears.

### TA-01A-PUPPETEER-CURRENT-SELECTION

Change the existing current Form or semantic Object selection, then open/focus Puppeteer. Puppeteer projects
that same current authority; it does not create a second selected Form, selected joint, workspace currentness,
or location/context model. Closing Puppeteer does not discard Object selection.

### TA-01A-PUPPETEER-TYPED-READ

From the managed hosted route, admit `object-factory.core-morph-current.read.v1` and project its exact
`object-factory.core-morph-current-read/v1` Form, stance, and motion data into the Puppeteer Box.
Browser presentation state may retain disclosure, but browser storage does not become Creature, Form,
motion, stance, Object, or persistence authority.

### TA-01A-PUPPETEER-REAL-SCREEN

Review the real managed Application Server screen at ordinary desktop and narrow widths. Confirm the peer
Morph/Shader/Puppeteer/Object order, twist-label close/expand behavior, explicit Body Form child Shelf,
shared Object viewport, current selection continuity, keyboard focus, and absence of duplicate workspace,
selection, persistence, executor, or owner-bypassing controls.

## TA-02 — current-truth reader

Run only:

```text
node --test src/test/js/operations-atlas-current-truth.test.mjs
node development-atlas/operations/query-current.mjs development-architecture-atlas
node development-atlas/operations/query-current.mjs shelf-native-puppeteer-route
```

The focused test proves the evidence-backed source-only projection, an owner route with no invented current
body, fail-closed pointer conflict, path-traversal rejection, and explicit synthetic evidence labeling.
No server, database, provider, application, or network action is part of TA-02.

## TA-03 and later

TA-03/TA-03A add read-only UI/route projection, TA-04 adds the Workboard adapter, TA-05 adds bounded
owner/runtime receipt helpers, and TA-06 imports only evidence-complete history. None is implemented here.

## Local patch application verification — 2026-09-05

The supplied TA-01 patch is applied after current-Main reconciliation. The focused offline
`validateDevelopmentArchitectureAtlas` gate passes locally; no broad suite or runtime action ran.
That original checkpoint lacked the Puppeteer seed. TA-01A now supplies it as an explicitly partial
route. Local focused validation passes 16/16. Reconciliation with newer Main and landing remain pending;
TA-02 remains gated. Pending future-interface scenarios are not failures of the route-record contract.

## Agent context migration strategy

The external-policy retirement body must prove:

- root `AGENTS.md` is an ordinary tracked file, not a symbolic link;
- its content includes the complete policy imported from the former local Agents checkout;
- `AGENTS_SETUP.md` describes only repository-owned reading, clone, archive, and update routes;
- current policy and setup guidance contain no operational dependency on `/Users/mmiller/Git/agents` or
  `../agents`; historical references remain clearly labeled as superseded evidence;
- a clean `git archive` contains `AGENTS.md` as ordinary bytes and a clean extraction passes the documented
  file, non-link, and tracked-member checks; and
- documentation hygiene and the offline Development Architecture Atlas validator pass.

This body changes no source/runtime contract. It runs no broad suite, application, server, browser,
provider, or database action.

## Preserved earlier work-owner notes

# Talisman Architecture test plan

## Scope and authority

This living plan owns focused proof for Operations Atlas implementation bodies. The implemented bodies are
TA-01 (documentation, versioned record schema, four synthetic conformance examples, and deterministic
offline validation) and TA-01A (one verified partial Shelf-native Puppeteer CapabilityRoute seed plus route
reference validation). Neither body adds a production service, command, event, persistence store, reader,
UI, worker, provider, server lifecycle action, or application launch.

The current contract is [Operations Atlas record contract](../../../development-atlas/operations-contract.md).
Its machine schema, four TA-01 examples, and TA-01A route seed remain test data and entry guidance, not live
task, test-result, implementation, or runtime claims.

## TA-01 and TA-01A — focused contract validation

Run only the existing focused Atlas task:

```text
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew --offline --no-daemon \
  validateDevelopmentArchitectureAtlas
```

The task remains restricted to `DevelopmentArchitectureAtlasValidatorTest` and its local repository files.
It performs no network request and modifies no source or operational state.

Required TA-01 selectors:

- `currentAtlasAndIndexReferencesResolve` proves current Markdown/index references plus all checked-in
  operations schema examples.
- `operationsAtlasExamplesSatisfyVersionedContract` proves the required source-only, landed/equal,
  session-gated, and restart-required vectors remain present and valid, together with any additional
  route-specific conformance seed.
- `operationsAtlasContractRejectsMalformedRecords` proves missing/unknown fields, bad IDs, duplicate IDs,
  invalid scalar values, and missing repository paths fail closed.
- `operationsAtlasContractSeparatesSourceResultAndRuntimeTruth` proves materialization cannot replace source
  authority; task/result stages and commit fields cannot disagree; equal is not live; session-gated is not
  anonymous live access; and stale runtime cannot satisfy restart-required source.

Required TA-01A selectors:

- `operationsAtlasPuppeteerRouteSeedResolvesVerifiedReferences` proves the seed's owner, starting design,
  inherited v1 and exact v2 Shelf contracts, service contract, native/browser source paths, runtime service
  node, existing automated test selectors, and pending-manual scenario references resolve.
- `operationsAtlasRouteGapsRepresentTruthfulMissingOwnerAndDesign` proves a partial route can omit an
  unverified permanent owner or exact starting design only when it carries the corresponding explicit gap;
  the design gap directs the owner to write the interface design before coding.
- `operationsAtlasPuppeteerRouteSeedRejectsFalseResolution` proves a current route cannot retain gaps;
  omitted or unresolved owner/design/handoff, contract/source/evidence references, a feature owner posing as
  a patch companion, and evidence-state masquerading cannot appear resolved; a pending runtime requires an
  explicit gap and cannot be relabeled live without an exact runtime receipt.

Also run `git diff --check`. No broad test suite, application launch, server launch, browser run, or manual
screen claim is authorized by TA-01A.

## TA-01A pending implementation scenarios

These scenarios are referenced by the route as `manual-scenario` / `pending`. They are not TA-01A test
results. The future Object Factory implementation body owns their execution and evidence.

### TA-01A-PUPPETEER-RETAINED-CONTROLS

Close and reopen the Puppeteer Shelf Item through the declared twist label. The exact admitted controls,
Body Form child disclosure state, stance, motion selection, and local edit state remain retained. No second
Close control appears.

### TA-01A-PUPPETEER-CURRENT-SELECTION

Change the existing current Form or semantic Object selection, then open/focus Puppeteer. Puppeteer projects
that same current authority; it does not create a second selected Form, selected joint, workspace currentness,
or location/context model. Closing Puppeteer does not discard Object selection.

### TA-01A-PUPPETEER-TYPED-READ

From the managed hosted route, admit `object-factory.core-morph-current.read.v1` and project its exact
`object-factory.core-morph-current-read/v1` Form, stance, and motion data into the Puppeteer Box.
Browser presentation state may retain disclosure, but browser storage does not become Creature, Form,
motion, stance, Object, or persistence authority.

### TA-01A-PUPPETEER-REAL-SCREEN

Review the real managed Application Server screen at ordinary desktop and narrow widths. Confirm the peer
Morph/Shader/Puppeteer/Object order, twist-label close/expand behavior, explicit Body Form child Shelf,
shared Object viewport, current selection continuity, keyboard focus, and absence of duplicate workspace,
selection, persistence, executor, or owner-bypassing controls.

## TA-02 and later

TA-02 must add focused append-only store/current-pointer fixtures before any reader implementation. TA-03 and
TA-03A must add read-only projection tests for empty, stale, gated, conflicting, and route-gap states. TA-04
must prove Workboard and Atlas projections agree without moving authority into the board. TA-05 must prove
owner/runtime receipt helpers require supplied evidence and fail closed. TA-06 imports only evidence-complete
history and must preserve the source strength of every imported claim.

## Local patch application verification — 2026-09-05

The supplied TA-01 patch is applied after current-Main reconciliation. The focused offline
`validateDevelopmentArchitectureAtlas` gate passes locally; no broad suite or runtime action ran.
That original checkpoint lacked the Puppeteer seed. TA-01A now supplies it as an explicitly partial
route. Local focused validation passes 16/16. Reconciliation with newer Main and landing remain pending;
TA-02 remains gated. Pending future-interface scenarios are not failures of the route-record contract.

## Agent context migration strategy

Planning-only body: verify the strategy and preserved brief are tracked, links resolve, and the staged
acceptance criteria distinguish application modules, agent context, historical records, and external
recovery dependencies. No source/runtime contract changed; no Java tests required for this plan.

## Local TA-02 review correction — 2026-09-06

Reproduced synthetic, evidence-free result/runtime records returning equal/live as evidence-backed.
Added admission guards and regression tests for synthetic promotion, missing commit proof, missing/stale
runtime evidence, source mismatch, failed requests and valid session-gated projection. The focused reader
suite now has seven tests. These are validation checks on supplied evidence, not external verification.

## Self-contained guidance verification — 2026-09-07

- Verify original guidance commit has mode 100644 for root AGENTS.md and ordinary archive entry files.
- Verify independent local clone has no object alternates and policy files are tracked, not symlinks.
- Verify setup reading uses repository files; archives do not require Git metadata.
- Verify the recovery entry resolves and machine attachment recovery remains with Switchboard.
- Original body whitespace and added-line length checks pass; only seven policy/documentation files changed.
- Focused offline validateDevelopmentArchitectureAtlas passes using Java 21; no broad suite or launch.
- Preserve three untracked drafts and guidance-before-Geography ancestry; defer cleanup and Main landing.

## Moondance Project Profile v3 — documentation acceptance (2026-09-09)

- Confirm one profile covers all seven languages and links exact existing reference contracts.
- Keep Shelf v1/v2 contracts distinct from v3 successor design and broader Morph composition doctrine.
- Check complete Morph/Mapping/Resting, true indexed-triangle Mesh Wireframe versus Skeleton, and
  explicit local adoption for fitting/composition semantics; do not imply new runtime support.
- Check Tally Talk envelope direction, private-data exclusion, single mutation authority and evidence ladder.
- Confirm local bindings/deviations, executable workshop flow and canonical-versus-Talisman table.
- Accepted learnings ledger starts empty; candidate list is separate and carries no acceptance claim.
- Verify local links, changed-line lengths, whitespace and required focused offline Atlas/index validator.
- No runtime, visual workshop, provider, data-write or broad-suite proof is claimed for this document body.
- Preserve protected drafts and Main's separate uncommitted archive policy; no unrelated owner-folder rename.
- Verify Theme is distinct from structure and includes semantic cues/platform adaptation without authority.
- Verify Interpretation's understanding/preview/refinement/source/owner/safe-failure boundaries and named
  future adoption question; all six Talisman opportunities remain candidates, not implementation claims.

V3 result: all new links, changed/new line lengths and diff whitespace pass. Required Java 21 offline
Atlas validator passes (2 executed, 10 up-to-date); no broad suite, runtime change or launch.

## Profile v4 Materialization amendment — documentation acceptance

- Keep seven languages; Materialization is an outcome axis, not language eight or database-only storage.
- Check source/output separation, target capabilities/units/scale/tolerances, rights/provenance, explicit
  losses, deterministic comparison, owner admission and accessible external-software-free fallback.
- Keep existing screen/export foundations distinct from Blender, sound, cinematic and print candidates.
- Wall/table A/B/C and bounded manifest remain roadmap only; STL UV/material loss is explicit.
- Validate added links, line lengths and diff; no exporter, manifest, fixture or product proof is executed.

## Pending planning records preservation — 2026-09-12

Verify all three formerly untracked drafts are tracked with dated snapshot qualifiers. Preserve historical
content and proposal gates; do not infer current worker activity. Check whitespace and added-line lengths.
No Atlas/index route or runtime change; no build, test suite or launch required for preservation.

## Project Kit manual handoff preparation — 2026-09-15

Documentation-only: verify the prepared instruction covers every controlling section, all thirteen
proposed validation cases, exact archive identity, all template fields, source/database authority and
staged adoption. Confirm no executable changes or dispatch; Mark retains ZIP creation and attachment.
Check added-line lengths, local handoff link resolution and whitespace; run the focused Atlas reference
validator because design navigation changed. Record configured-toolchain limitations separately.
No runtime behavior, schema, package owner or service route changed; no broad suite or launch applies.

Result: link/line/whitespace checks PASS; offline focused Atlas validator PASS (32 seconds;
6 tasks executed, 6 up-to-date). Initial sandbox cache-lock denial resolved by scoped retry.
Only documentation changed. No broad suite, application launch, ZIP creation or Patcher dispatch.

## Restored Atlas lifecycle specification — 2026-09-15

Check controlling front door, manual handoff and Kit addendum agree on Atlas ownership, documentation-only
scope, six portfolio names, technical identity preservation, archive distinctions, migration manifests,
unknown-status gates and exact Atlas ZIP provenance. Check relative links, line lengths and whitespace;
run only the focused Atlas reference validator. Broad physical cleanup and Patcher receipt remain pending.

First-return addition: existing-surface authority/reuse proof and exact local open route required;
no duplicate website. Cover default Kit overview, local offline operation, labelled truth states,
static/link/accessibility/browser checks. Missing existing web source remains an explicit handoff gap.

Preparation proof: focused offline Atlas validator PASS (12 seconds, 3 executed/9 up-to-date).
Sally transfer checksum verified and eight draft front doors/index files reviewed; no patch applied.
No web/browser proof claimed: existing web-surface identity remains unresolved.

Queue addendum: verify ten explicit states, source/attempt identity, deterministic eligible selection,
no-provider Prepare, receipt distinction, return removal/history and separate review/landing gates.
Repository declaration is QUEUED; Workboard projection/dispatch must not be claimed without receipt.

Queue proof: focused offline Atlas validator PASS (11 seconds; 3 executed/9 up-to-date);
whitespace checks PASS. Sam contract sent; Workboard projection acknowledgment pending.

## Mark clarification — architecture knowledge, not a missing website

Supersedes earlier web-source gate: validate the full ZIP includes existing architecture/context and
friction records, and the local review view derives from them. No existing HTML source is required.
Queue may prepare; READY_FOR_MARK still needs actual source ZIP/inventory/digest and instructions.

Website clarification: existing Patcher site reuse, nested twist-open component detail and ongoing
inventory visibility required. Site discovery belongs to Patcher work, not queue preparation.
Prior focused Atlas validation passed in 15s; this clarification changes prose only.

Bootstrap contract: Grand Pubah owns creation; prove physical Kit inventory, pinned release/lock and
local validation. Missing approved release or failed checks must not produce setup-complete claims.

ZIP-only ceremony: root README must lead directly to assignment, scope and return criteria; verify all
links after extraction and source manifest/overlay inventory. No separate prompt required.

## atlas-active-designs-project-kit-001 — focused return proof

Actual checks for this Patcher return:

- `PATCHER-SOURCE.json` identifies body `atlas-active-designs-project-kit-001`, attempt 001 and exact source
  `c90aa4cf2c928af10645c665f8b8e4a13804c3ff`.
- Lifecycle inventory enumerates every Markdown file under `design/Active Designs/` from the supplied snapshot;
  unresolved folders remain explicit rather than guessed.
- Static local-link check covers changed Markdown plus `development-atlas/review/` HTML links.
- `node --check development-atlas/review/project-kit.js` and `project-kit-data.js` prove JavaScript syntax.
- Offline review source contains no `http://`, `https://`, CDN, fetch/XHR/WebSocket, or remote font dependency.
- HTML structure check proves one `main`, labelled component section, native `details/summary` disclosure,
  explicit buttons, viewport metadata and non-color status words.
- CSS source includes visible focus treatment and a narrow-layout media query; browser zoom/console/network
  observation remains a manual acceptance check because no browser was launched in this documentation return.

Proposed future validator fixtures remain specification-only in `MOONDANCE-PROJECT-KIT.md`; no executable Kit
validator is claimed.

Owner intake: returned patch applied; focused Atlas and two JS syntax checks PASS; changed-document
links and whitespace PASS. See RETURN-REVIEW.md for corrections, missing design scope and blocked
browser verification. No Main landing, Kit release, broad suite or live-server action.

First-release construction: verify destination bootstrap and Atlas reference identities separated;
patch targets Kit only; full package/manifest/lock/local validation and adoption proof required.
No released-version claim during queuing/preparation.

First Kit candidate: supplied13 focused checks PASS; independent negative probes reproduce four
acceptance failures listed in FIRST-RELEASE-INTAKE.md. Require corrected bootstrap/materialization/update
and provenance proof before Kit integration or dependent Slice readiness.

Mark scope correction: first Kit release is documentation-only. Review coverage, organization,
contracts, templates-as-documents, local navigation and provenance. Executable-tool acceptance gates
are withdrawn; coders implement the documented framework in their own projects.
