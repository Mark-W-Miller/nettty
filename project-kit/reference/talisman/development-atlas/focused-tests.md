# Focused-Test Routing

Slice-native source storage uses only FactorySliceDocumentTest synthetic document/media and temporary-DB
proof, plus FactoryNativePackageOperationTest for the shared legacy completion path. It covers exact bytes,
unknown fields, media closure, revisions/CAS, no-change, durable replay, collisions, rollback and readback.
PNG proof includes metadata-first bounds, legal IHDR, bounded full raster/decode, exact byte retention
and CRC-correct malformed raster rejection. Media paths reject Unicode normalization aliases.
No runtime/listener/live import/device proof is implied. Isolated-Main patch applicability is not execution.
See [the owner test plan](<../design/Active Designs/Object Sing & Dance Factory__/TEST-PLAN.md>).

## Canonical suite composition

The canonical `./gradlew test` task covers retained Java product behavior. It does not execute Python
runtime/integration, localization/internationalization, theme-presentation tests, or property-gated
Performance laboratory workloads that have their own allowlisted focused routes.
`config/canonical-test-exclusions.txt` is the explicit category/pattern authority consumed by Gradle;
`CanonicalTestCompositionTest` proves that boundary and representative retention of menu routing,
layout behavior, persistence, Java-native Contours, and product logic. Canonical dashboard totals
therefore describe only this reduced Java product scope.

The category-specific routes below remain useful to their permanent owners for separately authorized
focused verification. Performance laboratory workloads use `performanceAnalysisFocusedWorkload`; they
are not part of canonical Full Test Suite execution.

## Forest shared browser presentation

TF-04 adds the public opt-in CSS/font bundle and static specimen, without product-screen behavior.
`src/test/js/talisman-forest-browser.test.mjs` proves Java palette parity, contrast, WOFF2 hashes,
licences, bounded assets and non-color/accessibility structure. The companion
`src/test/js/talisman-forest-browser-visual.test.mjs` proves isolated desktop/narrow/iPad-sized
layout, 200% text, keyboard focus, forced colors and failed-font fallback with external requests blocked.
`ApplicationServerForestAssetsTest` proves static byte/MIME/CSP delivery on ephemeral loopback and no
feature-gateway invocation. This is not a managed application launch or physical iPad acceptance.
See the [browser contract](<../design/Active Designs/Talisman Forest__/BROWSER-THEME-CONTRACT.md>).

## Talisman Forest themes

TF-01 landed the semantic visual contract and deterministic Swing gallery. TF-02 landed typed Forest
registration, persistence, startup restoration, and isolated screen-fixture installation. TF-03 adopts
those registered roles in shared Java menu/status/control and custom-window presentation without
product-screen conversion. Midnight Slate remains the default and fallback.

Automated headed tests that construct or display actual Talisman screens opt into
`@ForestScreenTestTheme`. The test-only extension installs `TALISMAN_FOREST` before UI construction
and restores effective Swing defaults changed by that install after each test. Truly headless tests,
disabled/manual debug UI routes, and intentional exact-theme semantic tests retain their own scope.

Run only the focused Forest route:

```text
./gradlew --no-daemon talismanForestThemeTest
```

The task selects these focused owners:

- the Forest installer, shared-presentation, and semantic-accent/contrast methods in
  `TalismanThemeInstallerTest`;
- `ApplicationStatusLineTest` for retained status behavior and semantic presentation;
- the menu-less-popup fallback method in `AppAwareMenuBarServiceTest`;
- the two TF-02 Settings enumeration, persistence, reload, startup-style install, and fallback tests;
- `TalismanForestThemeContractTest` for registered-palette reuse and preview restoration;
- `TalismanForestGalleryPanelTest` for typed selection, labeled semantic accents, four-state status,
  async presentation, and rendering;
- `ForestScreenTestThemeAuditTest` for explicit fixture opt-in plus install/restore isolation; and
- representative Settings, window-chrome, monitor, and main-window headed fixtures.

The TF-03 Forest route also proves the shared owner/Shelf/Box/selection/state/role/group accent
vocabulary is pairwise distinct and reaches at least 4.5:1 contrast against the Forest base surface.
Color remains paired with words, borders, icons, or accessibility state; it is never the sole signal.

Review the live gallery or generate offscreen evidence:

```text
./gradlew talismanForestGallery
./gradlew talismanForestGallery \
  -Dtalisman.forest.gallery.snapshot=build/talisman-forest-gallery.png
```

TF-03 does not change canonical test exclusions and does not require a broad or full suite.

## Context Management

The `context-management/` module is registered as root subproject `:context-management`. Run only the
exact task and selector requested by the owning body.

- `ContextEngineTest` owns deterministic hierarchical composition, admitted-target precedence,
  disclosure/redaction and aggregate snapshot budgets, safe omissions, provider merge/conflict, and
  required ancestry behavior.
- `ContextServiceAndMonitorTest` owns installed-current truth, passive listener failure isolation,
  structural monitor diffs, response contracts, classified extension budgets, exact TaliTalk handoff,
  and application-lifetime close/rejection.
- `ApplicationContextPayloadCodecTest` owns CM-01: byte-exact version-one canonical JSON, all three payload
  shapes, order stability, serialized privacy/omission evidence, strict schema rejection, transport
  parser/byte ceilings, disclosure and aggregate extension revalidation, and admitted provider provenance.
- `ContextMonitorPanelTest` owns CM-03 atomic replay projection, off-EDT update handoff, detach/reopen
  reconstruction, late-generation rejection, snapshot release, and presentation node/text bounds.
- `MonitorsWindowTest.hideDetachesAndReopenReplaysCurrentTruth` owns the modeless shell's visible
  detach/reopen delegation over the same Context panel.
- `CombinedProductContentTest`, `ShellUiBehaviorTest.monitorsRequestRunsInjectedOpenAction`, and
  `AppAwareMenuBarServiceTest.monitorsMenuIncludesRequestedMonitorEntries` own the two shared-shell entry
  points and their one typed transient action.
- `ContextActionServiceTest` owns CM-04 one-update/fresh-snapshot semantics, admitted target precedence,
  exact CM-01 Ask AI/TaliTalk delivery, passive monitor convergence, payload/destination failure,
  fixed-capacity safe owner-private evidence, and closed-service rejection.
- `ContextActionSessionTest` owns immediate EDT acknowledgement, submit-time request construction,
  duplicate-action suppression, completion projection, and close-before-queued-capture release.
- `ContextActionPopupTest` conditionally owns the headful pop-up's fixed question bound and visible
  acknowledgement-before-delivery ordering.
- `AssetsManagerContextProviderTest` owns CM-05 narrow deterministic PROJECT metadata, public-policy
  omission, hierarchical composition, fresh Ask AI/TaliTalk payload identity, and passive monitor
  convergence with fake destinations.
- `AssetsManagerContextPilotTest` owns distinct submit-time explicit/pointer/focus/selection roles,
  deduplicated settled-click publication, stale selection/revision and close rejection, and the two
  Created Things Character menu actions.
- `ObjectFactoryContextProviderTest` owns CM-07 deterministic PROJECT hierarchy, exact Object/Native
  Package and Form/Rig/rest-Pose scalars, semantic target precedence, bounded ancestry/selection, explicit
  unavailable-capability truth, and public-policy omission.
- `ObjectFactoryContextPilotTest` owns submit-time Factory stamp revalidation, distinct invocation/focus/
  ordered-selection roles, hidden/closed/stale/unknown rejection, passive Monitor convergence, fake exact
  Ask AI/TaliTalk payload identity, and the two reusable semantic menu actions.
- `ObjectFactoryContextCaptureTest` remains the feature-owner proof for immutable scalar capture, semantic
  multi-selection, target stamping without selection mutation, and hidden/closed/stale rejection.
- `AdventureBattleContextProviderTest` owns CM-06 deterministic Adventure → World → Place ancestry →
  Battle hierarchy, current turn/participant/condition facts, explicit/focus/ordered-selection precedence,
  PROJECT disclosure, deterministic composition, and fail-closed capture bounds/identity relationships.
- `ContextProposalServiceTest` owns CM-08 proposal-only separation, deterministic parameter ordering,
  disclosure and aggregate bounds, exact target/action/descriptor checks, one-use confirmation, changed
  snapshot/owner stamp, authority removal, replay, exact non-dispatching discard identity, exception-safe
  result codes, capacity, and close.
- `ContextProposalSubsystemIntegrationTest` owns the actual CM-06 and CM-07 request seams with explicit
  confirmed-action contracts and fake-only Battle/Factory authorities, including stale snapshot rejection
  and exact target/opaque-stamp delivery.
- `AppServicesStartupTest.contextServiceCompositionAndShutdown` owns the one initially empty registered
  proposal coordinator and dependency-order close alongside the existing Context services/providers.
- `ContextualActionRolloutTest` owns CM-09 adoption by both agreed screen pilots: owner-local fake
  acceptance, foreign-screen rejection without consumption, privacy rejection, stale Character selection,
  stale current snapshot, hidden Factory owner, exact close disposal, and no extra authority dispatch.
- `GmControlContextProviderTest` owns the pure sealed-capture projection, Place ancestry, bounded visible
  objects, Java-admitted labels, distinct explicit/pointer/focus/selection roles, deterministic output,
  PROJECT disclosure, and foreign or wrong-disclosure target rejection.
- `GmControlContextExchangeTest` owns canonical Java encoding, strict object-member normalization, semantic
  list-order preservation, parser/byte/schema/privacy bounds, the shared Java/JavaScript fixture, fresh
  owner recapture, passive Monitor convergence, typed non-applying delta, and empty production authority.
- `AppServicesStartupTest.contextServiceCompositionAndShutdown` additionally owns the one registered GM
  exchange service, provider composition, dependency-order close, and closed-service rejection.

The exact CM-01 command is:

```text
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew --offline --no-daemon \
  :context-management:test \
  --tests 'com.moondance.talisman.context.ApplicationContextPayloadCodecTest'
```

CM-02 composition is owned by these two exact selectors:

```text
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew --offline --no-daemon \
  :context-management:test \
  --tests 'com.moondance.talisman.context.ContextServiceAndMonitorTest.applicationLifetimeCloseClearsTruthAndRejectsNewWork'

JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew --offline --no-daemon \
  :test \
  --tests 'com.moondance.talisman.app.headless.AppServicesStartupTest.contextServiceCompositionAndShutdown'
```

The first proves idempotent close, state/listener clearing, and post-close rejection. The second proves
one typed/registered standard-policy service and AppServices-owned shutdown. They do not
authorize a feature screen, provider/live-data access, application launch, AI call, or broad suite.

CM-03 uses the exact selector block in the Context Management test plan. It does not launch the
application or authorize existing monitor-owner migration.

CM-04 uses the exact selector block in the Context Management test plan, including the existing
`AppServicesStartupTest.contextServiceCompositionAndShutdown` method for one registered action service
and dependency-order close. It does not register a feature screen, launch the application, read live
feature data, or invoke an AI/TaliTalk provider.

CM-05 uses the exact selector block in the Context Management test plan. It does not launch the
application, read or mutate a live database, call an AI/TaliTalk provider, or authorize another Assets,
Adventure/Battle, Object Factory, or destination body.

CM-07 uses the exact four-selector block in the Context Management test plan. It does not launch the
application, read or mutate a live database, advance Factory state/persistence, call an AI/TaliTalk
provider, or authorize CM-06/CM-08+, Adventure/Battle, OF-06, or a destination body.

CM-06 uses the exact two-selector block in the Context Management test plan. It does not read an Adventure,
runtime, or live database; register a screen adapter; launch the application; call a provider; mutate state;
or authorize CM-08+, OF-06, CIC-02, Seasons, or a destination body.

CM-08 uses the exact two-command selector block in the Context Management test plan: four toolkit-neutral
core scenarios, two CM-06/CM-07 fake-authority integration scenarios, and one AppServices startup/shutdown
scenario. It does not launch the application, read live feature state, register a production authority,
mutate or persist data, call a provider, or authorize CM-09, Journey, or another feature body.

CM-09 uses the exact two-command selector block in the Context Management test plan: the four retained
toolkit-neutral proposal scenarios, the four existing pilot scenarios, and one cross-screen rollout
scenario. It does not launch the application, register production identifiers or authority, mutate live
state, call a provider, activate Adventure/Battle screens, or authorize CM-10+ or Journey.

The Shelf Context v1 Java/JavaScript conformance corpus is proposed design only. It has no fixture or
selector yet and does not authorize a browser SDK, Application Server route, feature resolver, provider,
database access, or mutation. The Context Management test plan records the review-gated future vector set.

Run `validateDevelopmentArchitectureAtlas` separately after an Atlas/index edit. This route does not
authorize the root test suite, an application launch, a provider/live-data read, or an AI call.

## Shared semantic-operation contract

- `SemanticOperationContractTest`
  `compositeTargetsPreserveIndependentNamedRevisionFamiliesDefensively` owns exact composite
  target/revision preservation and defensive immutability.
- `SemanticOperationContractTest`
  `descriptorSeparatesSafetyIdempotencyRecoveryRetentionAndPrivateAudience` owns distinct safety,
  headless, review, deadline, cancellation, idempotency, recovery, retention, and privacy declarations.
- `SemanticOperationContractTest.terminalOutcomeIsSeparateFromPhaseAndNonSuccessCannotClaimEffects`,
  `uncertainExternalOutcomeRequiresReconciliationAndNeverClaimsAnEffect`, and
  `partialSuccessRequiresBoundedTypedComponentTruth` own terminal/effect/local-commit/child truth.
- `SemanticOperationContractTest.cancellationResultReferencesTypedScalarsAndTimingCannotContradictTruth`
  owns cancellation/local-commit constraints, exact no-change references, validated scalar values, and
  internally consistent monotonic timing.
- `SemanticOperationContractTest.knownTerminalAndComponentOutcomesCannotHideUnknownLocalCommit` owns the
  rule that known terminal/component outcomes cannot conceal unresolved local commit truth.
- `SemanticOperationContractTest.publicContractOffersNoBlockingCallbackOrUnboundedPayloadEscapeHatch`
  owns the public value/API escape-hatch boundary.
- `BoundedSemanticOperationRegistryTest`
  `capabilityCatalogAndAdmissionKeepUnsupportedRejectionAndDuplicateTruthDistinct` owns registered
  capability lookup and pre-admission/duplicate truth.
- `BoundedSemanticOperationRegistryTest`
  `activeCapacityRejectsNewWorkWithoutEvictingOrTimingOutActiveTruth` owns explicit active capacity
  without eviction or registry-driven timeout.
- `BoundedSemanticOperationRegistryTest`
  `sequenceCheckedHistoryReconstructsProgressAndTerminalResultNeverReopens` owns legal phases, strict
  sequence, reconstruction, and terminal immutability.
- `BoundedSemanticOperationRegistryTest`
  `reviewDeclineIsAnAcceptedTerminalResultNotARejectedSubmission` owns the
  admission-versus-feature-review distinction.
- `BoundedSemanticOperationRegistryTest`
  `cancellationRequestAndAcknowledgementNeverClaimCancellationAndCommitCanWinRace` owns idempotent
  cancellation and the commit-wins race.
- `BoundedSemanticOperationRegistryTest.onlyOwnerPublicationMakesAnEffectiveCancellationTerminal` owns
  owner-authenticated cancellation effect and terminal publication.
- `BoundedSemanticOperationRegistryTest`
  `terminalExpiryIsDistinctAndBlocksSilentIdempotentReplayUntilOwnerRelease` and
  `ownerDurableRehydrationRetainsExactIdentityAndProcessLocalRecoveryIsRejected` own expiry,
  duplicate protection, and truthful recovery scope.
- `BoundedSemanticOperationRegistryTest`
  `wrongScopeCannotEnumeratePrivateOperationsAndCloseDoesNotPublishCancellation` owns exact-scope
  privacy and lifecycle-independent owner work.
- `DevelopmentArchitectureAtlasValidatorTest`
  `semanticOperationBoundaryAllowsOnlyBoundedToolkitNeutralSources` and
  `semanticOperationBoundaryReportsPackageDependencyAndPayloadLines` own the deterministic source
  dependency/exported-payload gate.

Run only `:test` for `SemanticOperationContractTest` and `BoundedSemanticOperationRegistryTest`, then
the offline `validateDevelopmentArchitectureAtlas` task. Do not infer permission for a broad suite.

## Adventure Authoring

- `AdventureApplicationServiceTest` owns immutable worker-backed World/Adventure/snapshot/candidate reads,
  the exact selected-Character identity/Combat/Statistics read and Adventure/reference/Character/source-
  revision rejection,
  UI/headless parity, all four owner-private v1 operations, exact World/Adventure/Place/game-object revision
  guards, exact/no-change committed truth, geometry-free placeholder separation, idempotent reconstruction
  and conflict, concurrent single-winner revision checks, queued/running/too-late cancellation, deadline,
  wrong-scope lookup/cancel, epoch reconstruction, off-EDT repository work, registry privacy/no partial
  graph, and no GM runtime-table effect. It also owns the app-scoped reviewed-session and
  `execute-reviewed-step:v1` contract: approval/current-step reservation, all four landed kinds, exact
  Character identity, exact Assets sessions with null semantic child identity, no-mutation-before-approval,
  partial committed truth,
  Attention Required, linked incomplete-role retry, Continue Without, uncertainty reconciliation,
  reviewed deadline/idempotency/cancellation, reconstruction, privacy, and no duplicate Character.
- The same `AdventureApplicationServiceTest` owns `prepare-reviewed-encounter:v1`: deterministic bounded
  candidates from exact Adventure/catalog/terrain/level-band/Pi inputs, complete review identity, exact
  approve/decline, once-only quantity realization, dedicated aggregate/revision commit, duplicate/conflict,
  pre-admission capacity and duplicate-retention alignment, cancellation/commit race, pretransaction versus
  rollback truth, retained artifact, registry privacy, and no runtime-table effect.
- `AdventureAuthoringPanelTest` owns the real encounter review adapter: immediate activation acknowledgement,
  exact immutable candidate projection, approve/decline/cancel identity, close/reopen reconstruction, and
  rejection of stale Adventure, operation, revision, or review-generation projection.
- `AdventureAuthoringCoreTest` owns the temporary-SQLite World/Adventure aggregate, exact Bronamesk
  Place ancestry, placeholder context, canonical Character-sheet facts, stale revision guard,
  bounded/hash-stamped outbound preview, frozen Sarmi YAML plan decoding, actionable
  malformed/unknown-field rejection, no-preapproval mutation,
  one-at-a-time canonical Character creation, public Assets library-change publication, retryable
  exact Character/role partial state, exact artifacts, and exact final Adventure reference.
- `AdventurePlanCodecTest` owns plural/fifth-level preservation, questions plus exact Answers/replan,
  complete multi-Character visibility, general placeholder Place work, attached-context no-create,
  original-versus-edited Markdown/YAML provenance, and invented-target rejection.
- `CodexAdventurePlanningProviderTest` owns fake-stdio proof that Adventure submits the exact preview
  as schema-free read-only text and decodes the returned human-readable YAML without a live provider.
- `AdventurePlanningWorkbenchSessionTest` owns the exact four-provider order and capability reasons,
  selected-context inspector/turn equality, conversation-versus-candidate admission, edit invalidation,
  image response isolation, explicit acceptance, and independent multi-window state.
- `AdventurePlanningWorkbenchPanelTest` owns provider controls inside the workbench only, unavailable
  provider behavior, context inspection, response-to-candidate review, validation, explicit acceptance,
  grouped control hierarchy, immediate accessible busy truth, in-flight provider cancellation, and close
  without mutation.
- `OpenAiResponsesClientTest` owns exact official `/v1/responses` request shape, bounded output-text
  extraction, authentication/transport/shape failure taxonomy, and secret redaction.
- `TalitalkPlanningProviderSessionTest` owns canonical credential resolution, existing Ollama/Image
  transport reuse, exact inspected-input equality, real image projection, per-window configuration,
  truthful missing/locked/rejected states, provider route validation, and secret-free capability values.
- `AdventureAuthoringPanelTest` owns the real standalone panel projection of
  `Worlds -> Seasons -> Adventures`, absence of provider controls, exact named workbench launch,
  persistent Adventure Contents routing for Character, exact Place/map, and placeholder Place, late
  Character A versus selected B rejection, and preservation of exact Adventure Request/review state,
  accepted-candidate and stale-origin routing, editable review and visible validation failure,
  exact draft/canonical artifact rows,
  A/B state isolation, Save/Discard/Stay, stale selection rejection, persisted/clamped dividers, all
  Icon/Token checkbox combinations, secret-free readiness before mutation, staged candidate
  Keep/Reject, partial retry without duplicate Character/completed role, exact-ID Assets reveal,
  and Request Talk exact selection insertion, no-provider dispatch, off-EDT startup, non-overlap,
  Request/Adventure stale rejection, startup failure, close cancellation, and the public resident
  observation receipt's stable-first/current/exact selection, privacy bounds, coherent READY install,
  empty skip, dirty/pending/Dictation/Do It refusal, revision rejection, supersession, explicit cancel,
  close invalidation, no repository/provider/media mutation, no divider write, async ordinary-control
  projection, and shared-service headless completion after panel detach.
- The panel selector additionally owns reviewed-service detach/reattach, exact session/operation late-result
  rejection, terminal-control projection, encounter review completion after panel detach, and the absence
  of a panel semantic worker/media gateway lifecycle.
- `AdventureCharacterPaneTest` owns the compact identity masthead, immutable Character retention, five
  character-local Focus identities, and focus changes without Character or Adventure mutation. Its CS-02
  selectors own exact read-only Combat/Statistics rendering, deterministic ability modifiers, explicit
  unavailable skills/actions/conditions/resources, wrapping scroll surfaces, and complete en/de/fr/pl/ko
  visible/accessibility vocabulary proof. The panel selector proves immediate loading acknowledgement,
  stale selection rejection, and controlled failure text that never exposes a raw Character-load exception.
  CS-03 selectors cover the primary presentation preview, every exact image-role card, bounded read-only
  descriptions, scrolling, accessibility, and complete five-language vocabulary. CS-04 selectors own the
  bounded timeline and one focused event editor, immediate duplicate-safe Save acknowledgement, exact current
  completion install, late-selection rejection, and complete five-language visible/accessibility vocabulary.
- `AdventureApplicationServiceTest` owns CS-04 coherent immutable History reads and the exact checked create,
  update, and no-change transaction. Its sentinels cover off-EDT execution, stale Adventure/reference/
  Character-source/event revisions, rollback between event and aggregate revision writes, and no partial row.
- `AdventureAuthoringPanelTest` owns CS-04 exact aggregate/History EDT projection and rejection after another
  selected content identity or generation wins.
- `AdventureApplicationServiceTest` owns CS-05 exact source/target Relationship reads and checked create,
  update/no-change, stale-target rejection, off-EDT work, and forced rollback with no partial row.
- `AdventureCharacterPaneTest` owns the bounded Relationships list/focused editor, exact target/provenance,
  immediate duplicate-safe Save, unavailable/bounded truth, late-selection rejection, and complete
  en/de/fr/pl/ko vocabulary. `AdventureAuthoringPanelTest` owns exact current-content projection and rejects
  late Relationship completion after Adventure Contents changes.
- `AdventureCharacterPresentationReaderTest` covers canonical Assets exact-role reads, deterministic
  primary precedence, bounded presentation bytes, stable no-mutation reads, missing roles, and rejection
  when an association changes during capture. `AdventureApplicationServiceTest` retains the final exact
  Character/source revision guard around media and description capture.
- `AdventureCharacterMediaGatewayTest` owns the canonical adapter's exact Assets session, Character
  ID/revision, and role status guard without invoking Assets generation or a provider.
- `AppServicesStartupTest.initializesCoreServices` owns singleton registration/access and shutdown
  composition for `app.adventureApplication` without changing Assets media lifecycle.
- Run only `./gradlew :test --tests '*AdventureApplicationServiceTest' --tests
  '*AdventureAuthoringCoreTest' --tests
  '*AdventurePlanCodecTest' --tests '*CodexAdventurePlanningProviderTest' --tests
  '*OpenAiResponsesClientTest' --tests '*TalitalkPlanningProviderSessionTest' --tests
  '*AdventurePlanningWorkbenchSessionTest' --tests '*AdventurePlanningWorkbenchPanelTest' --tests
  '*AdventureAuthoringPanelTest' --tests '*AdventureCharacterPaneTest'` for Adventure provider-path proof.
  Global `test` selectors are
  unsuitable because the multi-project build also applies them to modules with no Adventure tests.

## GM prepared encounter runtime admission

- `GmPreparedEncounterAdmissionServiceTest` owns UI/headless identity, concurrent duplicate one-winner,
  stable graph IDs, same-key changed-request conflict, exact prepared/catalog/Adventure/runtime guards,
  precommit cancellation and deadline, no replay side effects, fail-closed visibility, explicit
  Player-eligibility input without Present, bounded owner-private registry truth, and full ordered typed
  cohort results with only aggregate/selected registry references.
- `MapRuntimeSessionServiceTest` owns exact runtime guard rejection, one checked save/history/publication,
  one selected leaf, one expanded temporary cohort with derived highlights, closed Place-fit formation,
  and failed-save rollback with unchanged graph, selection, history, and revisions.
- `GmRandomEncounterPanelTest.preparedAdmissionProjectionIsBoundedBusyCapableAndFailClosed` owns the
  bounded exact-ID control, stable acknowledgement control, explicit cancel, and absent-service/Place
  refusal. Service tests own detach-safe app-scoped completion and exact operation reconstruction.
- `AppServicesStartupTest.initializesCoreServices` owns singleton registration/access/shutdown for
  `app.gmPreparedEncounterAdmission` beside `app.adventureApplication` and the runtime session.
- `MapRuntimePresentationProjectorTest.targetedPlayerRegionRejectsSecretsAndFiltersPrivateObjects`
  retains GM-only Player filtering, while
  `BoundedSemanticOperationRegistryTest` retains exact terminal-expiry and no-replay truth.
- Run the complete new service test, only the three `preparedEncounter*` runtime methods, the exact panel,
  startup, Player-filter, and registry-expiry selectors named above, then offline
  `validateDevelopmentArchitectureAtlas`. Do not infer permission for a broad suite.

## GM current-combatant checked movement

- `GmCombatMoveOperationServiceTest` owns exact UI/headless identity, same-key reconstruction,
  changed-fingerprint conflict, per-activation one-winner arbitration, moved and no-change truth,
  cancellation/deadline/commit race, checked persistence rollback, service-epoch no replay, and bounded
  owner-private retained evidence.
- `MapRuntimeSessionServiceTest.checkedCombatMovementSavesBeforeInstallAndSkipsStaleDeferredPublication`
  owns every independent document/Place/presence/Arena/selection/store/position/route/cost stale guard,
  save-before-install, failed-save rollback, newer-state-preserving deferred publication, and no Player
  generation advance. `checkedCombatMovementNoRouteIsNoChangeWithoutSaveOrHistory` owns no-change truth.
- `CombatRuntimeSessionServiceTest.rejectsEveryIndependentCombatMoveGuardBeforeArenaCommit` owns exact
  combat revision/round/segment/activation/Place/current/inspected/not-spent/rules guards before Arena
  commit; `movesOnlyTheCurrentCombatantOnceWithoutEndingItsActivation` owns spent and no activation-end
  truth. `committedMoveRemainsSuccessfulWhenNewerProjectionTruthRejectsDeferredPublish` owns immutable
  commit-wins terminal truth across the post-commit projection race.
- `MapRuntimeComponentsTest.playPanelRoutesHpEffectsAndRulesOwnedActionAvailability` owns real Play-panel
  delegation, stable `Moving…` acknowledgement, duplicate suppression, and typed command guards.
  `AppServicesStartupTest.initializesCoreServices` owns one shared combat session and operation service
  composition.
- `BusDeliveryPolicyTest.fixedTopicCatalogIsExactExplicitAndHasNoRetainedProductionState` retains
  explicit owner-private Combat
  command/event policy. Existing Player-safe projection, Viewer current-state, Walkthrough registry, and
  semantic-operation expiry/no-replay selectors remain compatibility sentinels; Move adds no consumer
  authority.
- Run the complete new operation test and only the exact runtime, combat, panel, startup, bus-policy,
  Player-filter, Viewer, Walkthrough, registry, and offline Atlas selectors named by Body 6B. Do not infer
  permission for a broad suite.

## GM checked staged-map Present

- `GmStagedPresentationOperationServiceTest` owns UI/headless immediate identity, exact-capture same-key
  reconstruction and mismatch conflict, one-generation competing arbitration, every pinned runtime and
  content stale guard, preparation failure, cancellation/deadline/commit truth, detach/restart no replay,
  bounded privacy, and the read-only User Assets resolver proof.
- `MapRuntimePresentationProjectorTest` retains exact permitted-Layer imagery, defensive frozen terrain,
  Player privacy, and current-place source projection. `AppServicesStartupTest.initializesCoreServices`
  owns singleton registration/access/shutdown for `app.gmStagedPresentationOperations`.
- Run the complete new service test, the existing projector class, the exact startup method, retained
  Player delivery/privacy and semantic-registry expiry selectors recorded by Body 6C, then offline
  `validateDevelopmentArchitectureAtlas`. Do not infer permission for a broad suite.

## Switchboard local status dashboard

- `scripts/status-dashboard/test_status_dashboard.py` owns snapshot validation/redaction, status and
  coordination ordering, five-state execution presentation, activity/detail rendering, accessible
  detail close/history/Escape/focus behavior, gated-detail first-content ordering and monitor-error
  classification, exact waiting-user question plus validated pinned-task deep linking with safe
  passive-detail fallback, exact pinned-only visibility (including completed/runtime-loaded owners), one
  document-owned vertical scroll with horizontal-only table overflow, pinned Full Test Suite
  visibility, report-count progress, immutable tested identity,
  completed coverage, assertion-versus-infrastructure failure treatment, stale update retention,
  loopback/token routing, manual-only no-polling status refresh with a fixed duplicate-safe
  Switchboard request, immediate reread, gated-plan click feedback without POST reachability, and
  pre-POST plus running-lifecycle relaunch feedback.
- `scripts/status-dashboard/test_continuation_watchdog.py` owns idle/ungated eligibility, exact-owner
  reuse, blocker/pause exclusions, body/cursor/cooldown loop prevention, and atomic wake/error markers.
- `scripts/status-dashboard/test_master_refresh.py` owns canonical next-Alpha planning, reviewed-note
  ambiguity, no-op landing, single-flight suppression, and durable exact-stage failure/success state.
- Run only `python3 -m unittest discover -s scripts/status-dashboard -p 'test_*.py'` for this local
  administration boundary; it does not perform Git integration or launch Talisman.

## Interface localization

- `scripts/i18n/test_audit_visible_ui.py` owns deterministic visible-literal detection, explicit
  proper-name allowlisting, surface classification, and selectable-locale catalog reporting.
- `LocalizationServiceTest` owns English resource lookup, parameter formatting, unsupported-locale
  fallback, missing-key/literal diagnostics, shared application-menu resource completeness, typed
  English/Korean/German/Polish/French preference persistence, UTF-8 locale loading, mixed-script
  formatting, per-key English fallback, and distinct formatting diagnostics.
- `LocalizationServiceTest.europeanDiagnosticsPreserveLocaleAndInvalidKeyParameters` owns reviewed
  German/Polish/French fallback and invalid-pattern diagnostic text plus exact locale/key parameters.
- `LocalizationServiceTest.europeanWaterMaskChromePreservesSourceRevision` owns reviewed
  German/Polish/French Water-mask option/status chrome, exact source/revision parameters, formatting
  diagnostics, and preserved runtime values.
- `LocalizationServiceTest.europeanArenaResetChromePreservesBackupAndFailureParameters` and
  `ArenaObjectResetMaintenanceControllerTest.localizedPreparedBackupTextPreservesExactSafetyValues`
  own reviewed German/Polish/French maintenance chrome and realized prepared-backup text while
  preserving exact path, byte count, SHA-256, confirmation phrase, and failure parameters.
- `LocalizationServiceTest.koreanArenaResetAndDangerousMaintenanceChromePreservesSafetyParameters`
  owns Korean Dangerous Maintenance/Arena-reset chrome and its exact safety parameters.
- `LocalizedFontResolverTest` owns selected-font preservation, Korean mixed-glyph coverage when an
  installed capable family exists, style/size retention, and non-Korean no-op behavior.
- `TalismanSettingsDialogTest.migratesMaroonAndPersistsASelectedApplicationTheme` realizes the
  restart-to-apply language control alongside existing settings persistence.
- `TalismanSettingsDialogTest.displaysCanonicalProductVersion` owns the bounded non-resizable
  Settings size and stability after asynchronous credential-status completion.
- `TalismanSettingsDialogTest.europeanSettingsDialogsComposeReviewedTitlesAndActions` owns real
  German/Polish/French Settings title/action, widget accessibility, credential status/refresh, and
  bounded dialog composition. `koreanSettingsDialogComposesRestartLanguageChoice` owns the matching
  Korean realized controls plus restart-to-apply selection.
- `AboutTalismanPanelTest.koreanAndEuropeanAboutPanelsComposeLocalizedChromeAndDates` owns real
  Korean/German/Polish/French About accessibility, section chrome, and locale-aware creation dates;
  canonical version/release-note data remains unchanged.
- `ProductWindowsStructureTest.declaresProductWindowsWithStableRoots` owns localized title-key
  metadata for existing product windows and the managed Player Presentation root.
- `ProductWindowsStructureTest.authoringRootKeepsNavigationAndUnifiedInspectorCollapsible` and
  `gmControlLabelsPlacesDrawerAndKeepsTwoDimensionalZoomChromeVisible` own the exact shared “Places”
  caption without a direction glyph on both product roots and the Control standalone/combined 2D
  Zoom-bar height.
- `DTDTSplitterTest.collapsedDrawerCanPaintExactConfiguredLabelWithoutChangingItsAction` owns the
  exact visible drawer caption together with unchanged click, tooltip, and accessibility behavior.
- `AppAwarePopupWindowTest.macFullScreenAndChromeMaximizePrepareBoundsBeforeStateWithLiveJfxPanel`
  owns macOS preinstalled usable-screen bounds before every undecorated-frame maximize request,
  repeated Workspace Layout/chrome maximize-restore, and continued live JFXPanel mouse delivery.
- `TalismanWindowChromeTest.southeastGripUsesOneCursorAndDragRegionAcrossItsVisibleBounds` owns
  shared southeast-grip cursor/drag agreement across its visible region, outer extreme corner, and
  adjacent non-resize interior.
- `MapEditor3DTopBarTest.koreanAuthoringTopBarComposesLocalizedControlsAndTerrain` realizes the
  Authoring 2D command bar under Korean locale and owns localized icon semantics, tooltips,
  accessibility, and domain-safe terrain rendering.
- `MapEditor3DTopBarTest.retiredAuthoringToolbarControlsAreNotComposed` owns the absence of the
  duplicated Display control, numeric zoom readout, and Authoring Spawn 3D button across current and
  compatibility layouts while proving Zoom, New Root, and Top controls remain.
- `MapEditorRegionTreeLocalizationTest` realizes both Authoring and runtime-read-only Region Trees
  under Korean locale and owns localized search, recent-Place controls, authoring actions,
  accessibility, and shared title composition.
- `GeoTablesAcquisitionDialogTest.koreanDialogComposesLocalizedActionsChoicesAndValidation`
  realizes Korean Geo Tables actions, detail rendering, progress accessibility, and invalid-seed
  feedback without changing generation/provenance semantics.
- `MapEditorParentContextLocalizationTest` realizes the shared Authoring Place Overview under Korean
  locale and owns localized identity, empty-state, zoom/Top View accessibility, and 2D overview
  semantics.
- `MapEditorDisplayLayersPopupLocalizationTest` composes the complete popup under Korean and proves
  localized chrome does not translate document-owned Layer labels.
- `HeightmapAdjustmentLocalizationCatalogTest` owns reviewed locale parity, guidance terminology,
  parameter safety, and clean localization diagnostics for the modeless Heightmap palette chrome.
- Localized Talisman-menu construction and canonical About route:
  `AppAwareMenuBarServiceTest.talismanMenuOpensAboutAndExcludesWindowOrMonitorRoutes`
- `ActivityMonitorWindowTest.paintsEmptyAndActiveTextWithMidnightSlateContrast` owns localized
  Activity-monitor title/close/empty/progress presentation alongside its existing contrast proof.
- `ApplicationStatusLineTest.summarizesCurrentActivitiesOnEdtAndDetachesWithItsShell` owns idle,
  determinate, concurrent, detailed-floater action, EDT handoff, and displayability-detach behavior.
- `AppAwareMenuBarServiceTest.europeanSharedMenusComposeReviewedChrome`,
  `ActivityMonitorWindowTest.europeanActivityWindowsComposeReviewedIdleChrome`, and
  `DTDTLocalizationMetadataTest.appliesReviewedEuropeanDtdtTitles` own real German/Polish/French
  shared-menu, Activity idle, and DTDT tab composition.
- `MapEditorSavedSelectionsPopupTest` owns localized Saved Selections chrome/tooltips and preserves
  restore/clear command routing plus user-authored selection labels.
- `CharacterStlInspectorPanelTest` owns localized empty-state and Reset/Fit inspector chrome plus
  pointer-following horizontal/vertical orbit without crossing into DB-owned asset-content parsing.
- `CharacterSheetServiceTest` owns Cleric/Druid Type-template differences, independent deterministic
  Stats/Age generation, manual Age provenance, historical YAML compatibility, reload, and stale
  replacement rollback.
- `CreateCharacterDialogTest.selectedClassSeedsTheSingleYamlInput` owns the reviewed YAML seed and
  no-mutation-before-acceptance invariant.
- `CharacterSheetEditorPanelTest` and
  `UserAssetsWorkspaceTest.selectedItemEditorSavesExactCharacterSheetAndCreatureOverrides` own the
  real Selected Item Character/Creature edit route, inherited-versus-override truth, saved-only
  Generative facts, and checked persistence refresh.
- `CreateCreatureDialogTest` owns Korean mixed-script dialog composition, bounded name/Campaign
  fields, wrapped multiline notes, and preserved SRD definition text without opening a modal dialog.
- `CreateCreatureDialogTest.europeanFormsComposeReviewedLabelsWithBoundedInputs` and
  `LocalizationServiceTest.europeanCreatedThingSurfacesFormatReviewedPatternsAndFallback` own
  German/Polish/French Creature-form composition, 3D/Saved Selection patterns, runtime-data
  parameter retention, bounded fields, and English fallback for later keys.
- `LocalizationServiceTest.europeanAssetPresentationAndGenerationPreserveParametersAndSafetyToken`
  owns German/Polish/French Created Things association and accepted-generation strings, runtime
  association/role retention, exact destructive confirmation token, formatting diagnostics, and
  per-key English fallback.
- `LocalizationServiceTest.generativeIdentityHeaderFormatsReviewedRoleAndTargetPatterns` owns
  English/Korean/German/Polish/French role-specific Create, target-tag, and accessible target/role
  formatting without translating canonical names or target facts.

## Offline Help

- `HelpServiceTest` owns packaged index/topic completeness, aliases, deterministic local search,
  nearest named-context resolution, general-application fallback, Korean catalog selection, UTF-8
  multiline topics, mixed proper names, localized search, catalog schema/content/release alignment,
  conceptual/task/interaction kinds, validated related-topic resolution, and recorded missing-ID
  home fallback.
- `HelpServiceTest.loadsFirstConceptTaskInteractionInventoryWithSearchAndNavigation` owns the first
  broader linked Help inventory slice, its exact three kinds, parent/related graph, Markdown search,
  and stable semantic IDs.
- `HelpServiceTest.loadsAuthoringNavigationInventoryWithStableContextAndSearch` owns the linked
  Authoring concept/task/interaction slice, alias, stable Layers context, related graph, shortcut and
  Recent Places search, and truthful locale-catalog parity.
- `HelpServiceTest.loadsControlPresentationInventoryWithStableContextAndSearch` owns the linked GM
  Control concept/task/interaction slice, Present alias, stable Play context, Player-safe search,
  interaction search, and truthful locale-catalog parity.
- `HelpServiceTest.loadsAssetsInventoryWithStableContextAndSearch` owns the linked Assets concept/task/
  interaction slice, import alias, stable Media context, portable-content and picker search, and
  truthful locale-catalog parity.
- `HelpServiceTest.loadsDeeperControlOperationsInventory` owns Combat search, Object Repository and
  Groups contexts, deletion guidance, play-only Group guidance, and the deeper Control topic graph.
- `HelpServiceTest.loadsDeeperPlayerPresentationInventory` owns the privacy-safe concept, browser task,
  display-only Follow reference, source-revision wording, topic kinds, and locale graph parity.
- `HelpServiceTest.loadsDeeperAuthoringEditingInventory` owns the isolated editing/generation concept,
  checked Accept task, stale-result interaction reference, topic kinds, search, and locale parity.
- `HelpServiceTest.mergesLocaleAwareModuleTopicsIntoSearchContextAndRelatedNavigation` owns
  deterministic module contribution, process-locale delivery, combined search/context/alias/related
  routing, and contributed-module diagnostics.
- `HelpServiceTest.rejectsDuplicateModulesTopicsAndMissingCrossCatalogReferences` owns global
  duplicate module/topic rejection and post-merge parent/related reference validation.
- `HelpTopicLinkButtonTest.carriesSemanticTopicAndDelegatesLocalizedActivation` owns reviewed
  English/Korean/German/Polish/French action labels, stable semantic metadata, and delegation without
  feature-owned Help-window state.
- `UserAssetsSettingsDialogTest.blockedRebindContentRetainsDetailAndOffersSemanticRepairHelp` owns the
  second audited real error composition: exact blocked-rebind detail plus `assets.source-repair` link.
- `HelpServiceTest.loadsDeeperAssetsLifecycleInventory` owns attachment, role-history, stale Generative,
  and persistence-independent VTT guidance plus the deeper Assets topic graph.
- `LocalizationServiceTest.europeanHelpWindowChromePreservesTopicTitles` owns reviewed
  German/Polish/French Help-window title, navigation/search chrome, runtime topic-title formatting,
  and per-key English fallback while untranslated topic catalogs remain truthful.
- `AppAwareMenuBarServiceTest.helpMenuExposesOfflineContentsSearchAndContextActions` owns the
  localized Help menu and visible F1 accelerator.
- `MarkdownDocumentViewerTest.rendersReadOnlyMarkdownAndResetsDocumentPosition` owns the shared
  Help/payload Markdown presentation boundary, read-only contract, display-property inheritance, and
  deterministic top-of-document reset.

## DTDT localization and Help metadata

- `DTDTLocalizationMetadataTest` owns parsed tab localization/Help metadata, localized built titles,
  Korean mixed-script tab titles, and Help client-property propagation through the built Swing tree.
- `DTDTStructureTest.parseViewsRetainsLocalizationAndHelpMetadata` owns compatible view metadata
  parsing. Existing literal-only DTDT resources remain the backward-compatible fallback.

## Credentials and OpenAI consumers

- `SystemCredentialProviderTest` proves environment-over-Keychain precedence, stable exact command
  targeting, status mapping, off-EDT enforcement, exact secret-free guidance, and diagnostics.
- `CredentialProviderBehaviorContract` is the reusable fast provider contract. Its deterministic in-memory
  `SystemCredentialProviderContractTest` proves configured, missing, denied/locked, provider-rejected,
  unavailable, lookup-failure, guidance, environment override/fallback, and no-secret rendering behavior.
- `CredentialStatusManagerTest` proves all canonical identities map to immutable secret-free source/status
  snapshots, lookup stays off the EDT, delivery returns to the EDT, and older refreshes cannot publish.
- `TalismanSettingsDialogTest.displaysCanonicalProductVersion` also realizes the macOS status composition,
  refresh action, and read-only Keychain Access guidance without live Keychain access.
- `OpenAiImageClientTest` proves a provider-resolved generation credential reaches the real HTTP
  client boundary while failure and diagnostic text remain redacted.
- `TalitalkChatPanelTest` and `UserAssetsImageAcquisitionTest` cover provider-specific compatibility
  precedence and the two real product consumption routes.
- `UserAssetsWorkspaceTest.freeSearchControlsExposeActualSitesAndIndependent2d3dSelection`,
  `UserAssetsImageAcquisitionTest.freeSearchChannelsRespectSelectedSitesAndContentTypes`, and
  `FreeSearchSiteStoreTest` own explicit provider/type controls, fixed built-in routing, durable
  enable/disable identity preferences, and legacy endpoint-impersonation rejection.
- `ExternalAssetFederationTest` owns affirmative-license payload admission, unreviewed-provider
  suppression, rate-failure isolation, duplicate collapse, and cancellation propagation.
- `ExternalAssetBenchmarkTest` owns the versioned category/specificity/format/free-paid query matrix
  and the separation between raw observations and optional human provider assessment.
- `SmithsonianOpenAccessProviderTest` owns secure-key separation, exact official request shape,
  per-media CC0 and canonical provenance, advertised GLTF/GLB/OBJ, JPEG/GLB deferral, dependency-model
  link-only behavior, save-time evidence refresh, missing-key/malformed isolation, and cancellation.
- `WikimediaCommonsAssetProviderTest` owns exact official request/User-Agent, file rights and
  creator/attribution provenance, image/current-STL truth, canonical hosts, size/SHA-1 evidence,
  deferred download/refresh, link-only negatives, throttle/malformed isolation, and cancellation.
- `OpenverseAssetProviderTest` owns exact official anonymous/registered request and rate evidence,
  secret-free bearer reuse, creator/source/upstream provenance, unverified license claims, approved
  thumbnails, zero payload access, auth/rate/malformed isolation, and cancellation.
- `SketchfabAssetProviderTest` owns exact official public discovery, creator/license/price/access and
  archive evidence, provider credit, resolved encoded URI, official-v3 pagination/result shape, zero
  payload access, and distinct request/auth/rate/parser/shape isolation.
- `ThingiverseAssetProviderTest` owns fixed official Bearer search, creator/license/format evidence,
  zero payload access, missing-credential isolation, and redaction.
- `MyMiniFactoryAssetProviderTest` owns fixed official API-key search, store/account/format evidence,
  OAuth-only URL rejection, zero payload access, and missing-key/rate isolation.
- `UserAssetsWorkspaceTest.linkOnlySearchResultShowsTruthfulHandoffAndCannotDownload` owns truthful
  link-only details, canonical-listing handoff, download disablement, and access filtering.

## How to use this router

Choose the smallest method or class that owns the changed seam. Add adjacent routes only when the
change crosses their boundary. Host Gradle on Java 21; the configured project toolchain compiles
and runs the focused test on Java 25:

```text
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew --no-daemon test \
  --tests fully.qualified.TestClass.methodName -Djava.awt.headless=false
```

The lists below group exact method names under their test class. Class-only entries intentionally
route to that focused class. Documentation-only changes use the deterministic
[Atlas validation task](validation.md), line-length checks, and `git diff --check`.

## Atlas and index validation

`DevelopmentArchitectureAtlasValidatorTest`

- Current repository references:
  `currentAtlasAndIndexReferencesResolve`
- Stale Markdown diagnostics:
  `staleMarkdownReferencesReportCategoryFileLineAndValue`
- Stale index diagnostics:
  `staleIndexPackagesAndPathsReportOwningRows`
- Root/direct-subproject Java and focused-test discovery:
  `directSubprojectJavaAndTestSourcesResolve`

## Service, command, and operation architecture

### Shelf Contract v1 neutral conformance

The Shelf Contract v1 fixture selector (Talisman source reference: `../src/test/js/shelf-contract-v1.test.mjs`) owns the byte-preserved
historical concept and enum identity, group/label/REGION/layout shapes, automatic sorting and atomic Manual
transition, replacement/takeover, responsive state preservation, whole-envelope persistence rejection, and
the strict separation between accepted Workboard dogfood and required-but-unproven conformance outcomes.
It also feeds the frozen fixture and a historical REGION label without a top Close ID through the explicit
v1 validator.

Run only `node --test src/test/js/shelf-contract-v1.test.mjs`, then the offline Atlas validator. The
selector validates synthetic specification data; it does not prove a Workboard, browser Shelf, DTDT, or
Java runtime and does not authorize P6, J1, or J2.

### DTDT v3 Shelf replacement design

`design/Active Designs/Talisman Shelf__/DTDT-V3-SHELF-DESIGN.md` is design authority for the
future hard cutover. It uses only Shelf, Shelf Label, and Shelf Box as product nouns and marks the v1
contract names and fixture as replacement inputs rather than final v3 conformance evidence. This body has
only Markdown, link, line-length, whitespace, and offline Atlas proof. The v1/v2 source tests below remain
current-runtime evidence until the separately authorized v3 migration removes or replaces them.

TSR-03 replaces v3's earlier Shelf-wide Takeover occupancy with the Workboard V4 standard per-Shelf-Box
Takeover control. Future neutral vectors must prove exact open-group/chronology/expanded-state capture,
peer occlusion without Close, Return-to-group versus leading Close restoration, independent
`FILL_REMAINING` Expand, atomic browser/user-context persistence, nested-Shelf scoping, deterministic focus
and announcements, pressed state, and redraw preservation. Workboard V4's owner, nested Landed, Feature,
Controls, and Server cases are reviewed manual product evidence, not neutral or Java conformance proof.

TSR-04 adds the forward visual-semantic profile. Future neutral validation must reject raw color literals,
missing Forest theme identity, incomplete state-token sets, non-bold required hierarchy, contrast thresholds
below the shared minimums, color-only state cues, and invalid nested inheritance. Browser and desktop visual
evidence must show purposeful and perceptually distinct owner, Shelf, Shelf Label role, related group, Shelf
Box, selection, and active/waiting/warning/complete/error/disabled treatments on a readable Forest canvas.
A color-unavailable review must retain the same hierarchy and state comprehension through words plus
icons, patterns, or explicit border/fill geometry. Existing accepted bodies are not reopened solely for
cosmetic churn.

TSR-06 adds the Workboard Shelf V6 header-control profile. Future neutral responsive vectors must prove
that Close remains pinned, visible, keyboard reachable, and unclipped at inline leading while optional
Takeover remains trailing. Expand/Restore must select `→`/`←` for horizontal and `↓`/`↑` for vertical
from the actual open-box allocation axis, not Shelf Label orientation, and expose the complete action
through equal accessible names and titles. Workboard V6 is reviewed product evidence only; this design
body does not prove neutral schema, another browser consumer, Java parity, or runtime cutover. LTR/RTL may
move the leading cluster but cannot mirror physical arrows; all present action controls remain keyboard
reachable under narrow overflow while Close retains first/no-clipping priority.

### Shelf Contract v2 Object Factory successor

The Shelf Contract v2 selector (Talisman source reference: `../src/test/js/shelf-contract-v2.test.mjs`) admits the exact Object Factory
v2 DTDT, rejects missing/unknown/cross-version identities, and retains the richer reorder/disclosure/content
contradiction checks. Run it with `object-factory-surface.test.mjs`; neither selector mutates v1 data or
proves a non-browser runtime.

The durable presentation-memory selector (Talisman source reference: `../src/test/js/shelf-presentation-memory.test.mjs`) proves the
Shelf-owned `USER_CONTEXT_DURABLE` browser boundary in isolation. It checks the principal-free exact key,
complete v1 open/order/weight/focus/expansion state, and v2's independent selected-presentation identity,
declared component divider weights, and declared disclosure IDs without fake child Shelves or overloaded
Group state. It proves valid whole restore/save, no-write v1 migration, post-v2 downgrade rejection,
optimistic storage revision, and atomic rejection of partial, foreign, definition-stale,
presentation-stale, malformed, and conflicting values. Source inspection excludes HTTP and browser-local
persistence. It does not prove an Application Server route, durable backend, GCS composition, or live
cross-device restore.

The focused Core Morph catalog validator also admits
`morph-puppeteer-workbench.dtdt.json` through the canonical v2 validator and checks its exact nine-item owner
inventory against the embedded workbench definition and DOM. Its isolated Chromium interaction proof covers
multi-open twist labels, keyboard focus and Escape restoration, page-local divider changes, hidden-item
handling, complete hierarchical skeletal/geometry controls, whole-payload presentation-memory rejection, and
zero network side effects. These checks do not prove a production event bus, Java Shelf, Morph mutation, or
database path.

The OF-MORPH-PUPPETEER-48 pass additionally proves pointer and keyboard manual reorder with exact restoration,
one complete Point hierarchy containing 44 Humanoid incoming-length and 111 local-geometry leaves, 3D Point
selection navigation, independent stance inclusion, exact-hit stance projection, normalized inverse-square
weights, draggable unrecorded stance layout, close-to-ordinary-pose fallback, and the absence of network or
console failures. The focused `validate-morph-puppeteer-workbench.mjs` selector pins the artificial-space and
recording-disabled contracts.

### Easy Tale Body 1 authority contract

`EasyTaleAuthorityContractTest`

- Epoch-millisecond encoding and checked ranges:
  `canonicalTimeAcceptsOnlyEpochMillisecondsAndRejectsInvertedRanges`
- Resolved temporal order: `resolvedItemsOrderByStartThenEndThenStableIdentity`
- Exact non-time-based stale guards:
  `presentationCurrentnessUsesExactIdentityRevisionsGenerationAndEpochWithoutTime`
- Same-turn pre-dispatch feedback contract:
  `asynchronousDispatchRequiresFeedbackAndDuplicateDisablementBeforeDispatch`
- Zero-duration Focus and no Timeline playback:
  `focusTimingIsZeroForNormalAndReducedMotionAndTimelineHasNoPlayback`

Run only `:test --tests '*EasyTaleAuthorityContractTest'` for Body 1 source proof, then the offline
Development Architecture Atlas validator. No Easy Tale repository, DTDT runtime, window, provider,
application, or live-data proof belongs to Body 1.

### Easy Tale Body 2 DTDT Focus primitive

- `DTDTFocusWorkspaceStructureTest` owns DTDT v2 vocabulary, nested declaration, version rejection,
  duplicate identity, legal rail state, packing, and one-level nesting validation.
- `DTDTFocusWorkspaceCompatibilityTest` owns unchanged version 1 split/tab construction, Focus rail
  composition with existing splitters/tabs, and the reusable developer fixture.
- `FocusWorkspacePanelTest` owns atomic EDT open/expand/close/compaction, visible/overflow bounds,
  reopen order, dirty close guard, exact restoration, immediate busy accessibility, and disposal.
  It also owns adjacent divider count/visibility, 220-pixel minima, pointer-owned proportions, keyboard
  Left/Right actions, nested 25–75% bounds, close/reopen restoration, all-open selection, and atomic invalid
  snapshot rejection.

### Joined semantic-operation observation

`SemanticOperationObservation`, `SemanticOperationObservationProjector`,
`ObservingSemanticOperationRegistry`, and `SemanticOperationIncidentHub` form the passive owner-private
joined-observation core. `SemanticOperationSafeJoin` is a salted process-local correlation token only.
The decorator always delegates first; the incident hub orders by operation snapshot sequence and never
becomes operation, feature, persistence, retry, cancellation, or business-result authority.

`SemanticOperationObservationTest`

- Bounded safe projection without raw operation or feature payload:
  `projectionKeepsOnlyOwnerPrivateCodesCountsTimingsAndSaltedDigests`
- Process epoch, evidence sequence, and salted join truth:
  `safeJoinIsStableOnlyForOneIdentityAndProcessEpoch`
- Rejection remains pre-admission truth: `rejectedReceiptRemainsPreAdmissionAndCarriesNoAcceptedLifecycle`
- Optional document identity remains truthful: `projectionAcceptsAnOperationWithoutDocumentIdentity`

`SemanticOperationIncidentHubTest`

- Active operations never evict and capacity drops remain factual:
  `activeEvidenceNeverEvictsAndTerminalEvidenceEvictsWithinItsBound`
- Terminal retention and immutable terminal truth:
  `duplicateTerminalDoesNotExtendItsOriginalTtl`
- Snapshot ordering and terminal immutability:
  `snapshotSequencePreventsCrossThreadRegressionAndTerminalMutation`
- Rejection and close: `rejectionIsCountedWithoutAcceptedStateAndCloseClearsWithoutInventingTruth`

`ObservingSemanticOperationRegistryTest`

- Delegate-first exact-value preservation:
  `forwardsExactOwnerHandlesAndResultsThenRecordsLifecycleEvidence`
- Admission/lifecycle/cancellation/terminal projection:
  `duplicateCancellationTerminalAndRejectionTruthStayDistinct`
- Diagnostic failure isolation and query pass-through:
  `delegateFailureProducesNoEvidenceAndProjectionFailureCannotChangeAcceptance`
- Full/closed-hub isolation and close truth:
  `fullOrClosedIncidentHubNeverChangesDelegateTruthAndCloseInventsNoOutcome`

The existing adapter seams retain their own focused classes:

- `ActivityMonitorTest` proves a safe join attaches only to an existing active row and never creates an
  Activity;
- `BusTest` and `BusDeliveryPolicyTest` prove one actual recipient attempt may attach the join without
  sibling, nested-reentrant, RESOURCE, failure, or post-handler leakage;
- `OperationJobMemoryTelemetryTest` proves only a real measured job carries the join and adapter failure
  does not change job accounting;
- `MapPerformanceLogTest` proves only closed factual operation stages accept the join and no missing timing
  or outcome is inferred;
- `AppServicesStartupTest.semanticObservationComposition` proves one central decorator/hub composition and
  feature-before-observation shutdown without manufactured terminal or cancellation evidence.

### Core bus delivery policy and observability

`TalismanBusTopicPolicies` owns the exact trusted APP, DTDT, and USER topic catalog and the mixed Shell
action discriminator. `BusDeliveryPolicy`, `BusTopicPolicy`, `BusTopicPolicyResolver`, and
`BusSubscriptionOptions` own toolkit-neutral delivery kind, trusted resolution, lifecycle, retained-state
stamp, privacy, and exact-subscriber metadata. `BusDeliveryDiagnostic` and `BusObserver` expose bounded
safe transport evidence only; delivery is never feature acceptance, persistence, commit, or success.

`DebugConsole`, `BusMonitorScopeState`, its table/detail projections, and `DataBaseBusService` consume
only redacted diagnostic metadata. They must not receive, stringify, or retain a bus payload, raw
exception, credential, path, URL, image, private domain state, or arbitrary participant identity.

`BusTest`

- Typed subscriber delivery: `publishDeliversToTypeSubscribers`, `publishDeliversToAllSubscribers`
- Subscription disposal and bus-level logging: `closeUnsubscribes`, `publishLogsToBusLevel`
- Mutation drop with no late replay: `mutationWithoutSubscriberDropsAndNeverReplays`
- APP publisher-thread delivery: `appBusDeliversInlineOnPublishThread`
- APP, DTDT, USER, and custom inline ownership: `appDtdtUserAndCustomBusesRemainInline`
- Depth-first reentrant publication: `reentrantInlinePublicationRemainsDepthFirst`
- Contended inline publication behavior:
  `contendedInlinePublishReturnsWithoutWaitingForActiveSubscriber`
- RESOURCE bus worker ownership: `resourceBusDeliversOnDedicatedWorkerThread`
- Same-service FIFO drain: `busServiceDeliversInOrder`
- Metadata-only handler failure evidence: `busServiceRecordsFailedDeliveryWithoutRawFailure`

`BusDeliveryPolicyTest`

- Exact fixed catalog and zero production retention:
  `fixedTopicCatalogIsExactExplicitAndHasNoRetainedProductionState`
- Exact registration and caller-policy rejection:
  `exactRegistrationOverridesPrefixesAndRejectsCallerPolicyChanges`
- Trusted mixed Shell classification: `trustedShellDiscriminatorSeparatesMutationFromEphemeralAction`
- Exact lifecycle and no late UI replay:
  `uiRequestRequiresExactLiveLifecycleAndNeverWaitsForLateSubscriber`
- Reused subscriber identity with a newer lifecycle:
  `newerUiLifecycleSupersedesOldSubscriberIdentity`
- Dispatch-time TTL includes observer work: `diagnosticObserverTimeCountsAgainstUiRequestTtl`
- Current-only targeted state and stale-stamp rejection:
  `retainedStateIsCurrentOnlyTargetedAndRejectsStaleStamps`
- One retained catch-up/live ordering barrier:
  `retainedCatchupAndConcurrentLivePublicationHaveOneOrderingBarrier`
- Owner-declared retained-state expiry:
  `retainedStateExpiresByOwnerDeclaredTtlBeforeLateReplay`
- Per-recipient handler and observer isolation:
  `handlerAndObserverFailuresAreIsolatedPerRecipient`
- Bounded timed diagnostics without payload inspection:
  `diagnosticsAreBoundedTimedAndNeverInspectPrivatePayloads`
- Close admission and entered-handler truth:
  `closeClearsQueuedWorkWithoutInterruptingEnteredHandler`
- Fresh replacement epoch with no inherited state:
  `replacementBusHasNewEpochAndInheritsNoPolicyOrState`
- RESOURCE close clears queued/retained payloads:
  `resourceCloseFromEnteredHandlerClearsQueuedAndRetainedPayloads`
- Closed-service registration rejection:
  `closedServiceRejectsNewPolicyAndObserverRegistration`

The ten compatibility owners are Authoring Screen, Talisman Geography, Selections and Masks, 3D Viewer,
Assets Manager, Adventure Authoring, GM Control Screen, Player Web Browser, Workbench, and Performance.
Their acceptance covers the core policy boundary only. Selection edit-session targeting, late Mask
revision rejection, document-mask currentness, Viewer host/apply guards, pending Viewer consumer leases,
and product-specific lifecycle-epoch additions remain separately owned feature bodies. No production
topic currently opts into core retained state.

`UserAssetsWorkspaceTest`

- Assets-owned current-settings query:
  `adventureCharacterMediaSeamUsesAssetsCurrentSettings`
- Unavailable settings terminal truth:
  `adventureCharacterMediaSeamReportsUnavailableCurrentSettings`
- Exact reviewed Character keep/reveal:
  `adventureCharacterMediaSeamReviewsKeepsAndRevealsExactCharacter`
- Exact running-session cancellation:
  `adventureCharacterMediaSeamCancelsExactRunningRequest`

`ProductDataStateCaptureRegistryTest`

- Off-EDT bounded partial capture, timeout, and stale-result truth:
  `capturesOffEdtAndReturnsPartialTimeoutAndStaleResults`

`PerformanceAnalysisRunFoundationTest`

- Durable guarded operation transitions: `completeRunPersistsEveryGuardedTransition`
- Cancel/release/terminal ordering: `cancellationReleasesThenTerminatesOutstandingScenario`

`MoondanceRelayClientTest`

- Explicit ambiguous external outcome: `ambiguousInvitationResultBlocksSilentRetryUntilReset`

`TalismanWalkthroughTargetTest`

- Allowlisted UI invocation and correlated terminal result:
  `invokesOnlyAllowlistedStableControlsAndReportsCorrelatedOutcome`

`WalkthroughPlaybackEngineTest`

- Exact operation correlation and evidence: `correlatesOperationsAndWritesCheckpoint`
- Deterministic capability failure: `targetCapabilityFailureProducesDeterministicFailedReport`

`MapEditorScopeStateTest.pipelineSmoothPublishesAndFinishesAuthoringActivity` is the smallest
Authoring pipeline-to-Activity seam.
`MapRuntimeSessionServiceTest.cohortDestinationRoutesDistinctMembersWithoutTeleportAndPersistsAsOneCommand`
proves one typed runtime mutation's state/history/persistence boundary.
`CombatRuntimeSessionServiceTest.runsExactPlaceInitiativeSelectionAndRoundResetWithStaleGuards`
proves expected-revision combat state.

## Product release identity

`scripts/test-apply-patch-ledger.sh` owns ZIP-only top/wrapper intake, `changes.md` retention, numbered
sequence order, supplied and legacy-generated change text, normalized collision-safe bundle folders,
delivery-ZIP removal, unsafe/ambiguous/nested/empty rejection before mutation, isolated preservation,
staged refusal, archive recovery, exact commit contents, publication, and retained push failure.
`WorkbenchReleaseServiceTest` owns structured applied-bundle entries added after the reachable release
boundary, exclusion of already released and duplicate entries, exact bundle/change-text presentation,
and one combined patch-summary slot within the existing six-note bound.
`WorkbenchReleaseConfirmationDialogTest` owns a real resizable dialog with bounded default width,
word-wrapped scrollable release content, durable resized bounds, and stale-display clamping.
`WorkbenchDividerPreferencesTest` owns first-use defaults, user drag persistence, reopen restoration,
selection-content stability, and resize clamping for the shared splitter seam.
`PermanentBranchStatePanelTest.metadataContentDividerSurvivesStatusAndRefreshRecomposition` owns the
retained live Branches divider across task-status and Refresh content swaps.
`WorkbenchReleaseServiceTest` also owns exact Main-versus-latest-release readiness, while
`WorkbenchReleaseReadinessAppearanceTest` owns real-button full-surface green paint across every theme
and normal/hover/pressed/focus interaction, plus explicit-text truth and ordinary covered restoration
without executing a release.
`MapPerformanceWorkbenchTabsTest` owns Branches-first startup and the loaded-version window title.
`PermanentBranchStatePanelTest` owns automatic Git composition plus exact linked stored-status lookup
without task turns, and full-card Talisman-main green paint across every supported theme.
`CodexTaskStatusServiceTest` owns the list/read-only startup lookup and absence of resume/turn traffic.
`ReleaseMetadataEditorTest.shiftsAFullFormerTwentyFourEntryHistoryWithoutDroppingTheOldest` owns the
former-cap regression and exact 24-to-25 lossless metadata shift.
`TalismanVersionTest.readsMoreThanTwentyFourHistoricalReleasesWithoutDroppingTheOldest` owns runtime
loading and About-facing retention beyond the former cap.
`WorkbenchAboutDialogTest` owns tracked Workbench version/current-change loading, generated
running-build identity loading, and selectable menu/dialog presentation of Workbench identity,
packaged Talisman release/build, and exact embedded revision. It also owns native macOS/in-window
handler convergence on one modeless presenter, Java-runtime diagnostic demotion, and truthful
unavailable fallback for missing packaged properties.

`TalismanVersionTest.generatedRuntimeIdentityIncludesReleaseAndBuild` owns copying the canonical
root `version.properties` identity and bounded newest-first release history into the runtime identity,
including current-entry equality, retained Alpha 1.49 history, and unique release identifiers.
`TalismanVersionTest.sequentialBuildsAdvanceTheCombinedAlphaRelease` owns deriving one user-facing
release identifier by replacing the raw version's final numeric component with the build number.

`AboutTalismanPanelTest.displaysCombinedCanonicalReleaseAndCreationDate` owns the prominent combined
user-facing product/build identity, creation metadata, absence of a redundant build label, wrapped
current-release bullets, bounded vertical scrolling, and newest-before-older About history.

`AppAwareMenuBarServiceTest.nativeAboutHandlerOpensCanonicalTalismanAbout` owns native macOS/Desktop
handler registration, EDT dispatch, and the canonical About popup identity and release-note route.

`AppAwareMenuBarServiceTest.nativeApplicationMenuOwnsOneAboutSettingsAndQuitRoute` owns the
absence of a duplicate Swing Talisman menu plus canonical native action routing across reconstructed
bars, including About, Settings, and the existing clean Quit lifecycle.

`AppAwareMenuBarServiceTest.startupAboutUsesCanonicalContentAndTheRestoredProductOwner` and
`AppUiBootstrapTest.defersAboutUntilThePrimaryShellIsRestored` own the once-per-launch canonical
startup route, post-restoration ordering, product-window ownership, and non-always-on-top behavior.

`TalismanSettingsDialogTest.displaysCanonicalProductVersion` owns the compact canonical read-only
product identity in the realized Talisman Settings composition.

## Performance Workbench lifecycle

`WorkingRasterDimensionPolicyTest` owns the untouched 1536 production default, read-without-write
fallback for absent/malformed/laboratory-only values, reviewed preset identities and aspect-preserving
scaling, and proof that a normal setting change never rebuilds raster data.

`PerformanceLaboratoryServiceTest` owns synthetic-only establishment, source-byte preservation,
verified lab/baseline manifests, higher-preset manifest-only selection, stale-token rejection,
deliberate reset, scoped experiment-copy cleanup, live-path/symlink rejection, and tamper detection.
`PerformanceWorkingRasterRebuildServiceTest` owns read-only selected-Place preview, source-backed
rebuild versus honest working-raster fallback, unselected-Place preservation, immutable Source and
history retention, stale token/cancellation/capacity rejection, temporary-copy cleanup, checked
publication, injected mid-replacement rollback, and rejection from the Swing event thread.
`PerformanceAnalysisReportServiceTest` owns fixed-section complete/partial/failed/cancelled rendering,
explicit unavailable coverage, stable findings, comparisons, atomic primary replacement, preserved run
artifacts, secret/URL/user-path sanitization, output and artifact bounds, traversal/symlink rejection,
and the invariant that invalid evidence never renders healthy. `MapPerformanceAnalysisReportPanelTest`
owns missing-report explanation plus exact non-editing, non-mutating refresh of the bounded primary file.
`PerformanceAnalysisRunFoundationTest` owns the closed exact workload routes, manifest bounds,
synthetic-only identity, intrusive-authority gate, unknown/duplicate/unsupported rejection, atomic JSON
state, stale generations, ordered phase/scenario transitions, release-before-terminal classification,
idempotent cancellation, partial evidence, symlink/duplicate-run rejection, and exact median/p95/
variation/retained-slope math with warm-up and failed-correctness exclusion.
`PerformanceAnalysisExecutorTest` owns exact 4 GiB admission, closed-manifest repetition order,
synthetic-laboratory preflight, persisted infrastructure failure, and owned cancellation/release.
`PerformanceAnalysisManualRunServiceTest` owns exact build/Main/lab/raster identity in a manual run
manifest and unknown-workload rejection. `MapPerformanceAnalysisReportPanelTest` additionally owns the
six-choice Start/Status/Cancel composition and proves construction alone creates no run or process.
The executor proof also owns atomic sample order/identity, stable requested-metric coverage, explicit
unavailable values, terminal report synthesis, safe artifact linking, and measurement-symlink rejection.
`PerformanceRasterExperimentCatalogTest` owns the exact ordered 16-row matrix and independent high-detail
texture/lower-geometry semantics. `PerformanceRasterExperimentEvidenceTest` owns continuous versus
categorical policy validation and measured/conditional/failed/incomplete viability gates. The manual-run
proof additionally owns exact synthetic laboratory-setting admission and report synthesis from supplied
objective raster evidence without executing a production workload.
`PerformanceJfrProfileContractTest` owns disabled/no-artifact behavior, measured-only intrusive authority,
unique bounded child-JVM arguments, missing/symlink/oversize rejection, deterministic aggregate reading,
and proof that analysis leaves a separately running recording untouched.
`PerformanceAnalysisExecutorTest` additionally owns warm-up exclusion, unique measured profile plans,
disabled/profiled child-command separation, bounded profile observation persistence, safe JFR artifact
links, and allocation/file-I/O report synthesis. `PerformanceAnalysisManualRunServiceTest` owns explicit
sampled-profile manifest authority; `MapPerformanceAnalysisReportPanelTest` owns its unchecked default.
`PerformanceSubsystemCountersTest` owns disabled no-file behavior, exact run-root and symlink rejection,
pre-existing-file preservation, enum-only input validation, concurrent scalar totals, deterministic row
order, bounded output, and atomic refresh. Product-owner instrumentation is intentionally absent in 5C.
`PerformanceScriptedProfileContractTest` owns fixed candidate order, path/digest/context/capability
admission, ordered semantic binding, active/wait/delay/checkpoint timing separation, exact terminal result
correlation, checked checkpoint evidence, and explicit stopped in-flight evidence without execution.
`PerformanceScriptedProfileArtifactTest` owns atomic round-trip plus timing, changed-checkpoint, symlink,
and oversize rejection. `PerformanceAnalysisExecutorTest` owns the exact scripted command/output,
measured observation, report linkage, and post-measurement artifact-change rejection. The scripted smoke
workload test remains explicitly excluded from the canonical suite; `PerformanceAnalysisRunFoundationTest`
and `MapPerformanceAnalysisReportPanelTest` own the fixed seven-entry catalog presentation.
`PerformanceAnalysisRegressionPolicyTest` owns stable/material median and p95 regression, improvement,
unchanged, reviewed absolute-budget, and noisy/insufficient informational classification.
`PerformanceAnalysisBaselineServiceTest` owns exact cross-revision compatibility, incompatible
environment refusal, older previous-run selection, supervised append-only replacement, stale token,
symlink-root safety, fully measured allocation projection, and terminal report projection.
`PerformanceAnalysisTriageServiceTest` owns structured regression deduplication, stable finding identity
across changed evidence/reference runs, one-actionable bounding, permanent-owner routing, correctness
gating, and exact no-action behavior. Baseline/report tests own typed comparison projection and rendered
candidate evidence without a review, baseline, process, or product mutation.
`PerformanceAnalysisFindingReviewServiceTest` owns candidate-only publication, two-step exact decision,
append-only history, report-state projection, changed-evidence staleness, unsupported fixed-state, and
review-root path safety. `MapPerformanceAnalysisReportPanelTest` owns the real Swing finding/decision/
reason Review/Apply composition, edit invalidation, worker handoff, and no-write construction proof.
`PerformanceAnalysisFixVerificationServiceTest` owns explicit compatible improvement, accepted-state
prerequisite, correctness/resource/quality/cost-shift gates, immutable evidence, stale history, retained
review snapshots, and proof-digested fixed history. Baseline tests retain exact-comparison chronology and
compatibility coverage. `MapPerformanceAnalysisReportPanelTest` owns the real Swing before/after Review/
Apply composition and no-write worker boundary.
`MapPerformanceAnalysisReportPanelTest` owns the bounded reason plus two-step review/invalidate/accept
composition without a real ledger mutation. Executor, report-service, and run-foundation tests
retain failed/cancelled report truth, schema rendering, and checked run enumeration compatibility.
`CurrentPlaceRasterMigrationServiceTest`, `LegacyProjectImporterTest`, and
`LayerClarificationFoundationTest` remain the compatibility routes for existing 1536 normalization,
legacy import, and aligned Place raster geometry.

`MapPerformanceWorkbenchSelfUpdaterTest`

- Canonical main update, build, and ready-handoff order:
  `updatesBuildsAndHandsOffBuiltInvocationInOrder`
- Current-JVM retention for update, build, launch, and readiness failure:
  `everyFailureRetainsCurrentWorkbenchAndAllowsRetry`
- Overlapping request rejection:
  `rejectsDuplicateRequestWhileFirstUpdateIsRunning`
- Build-only command and new runtime launch specification:
  `builderUsesOnlyFocusedBuildAndNewlyBuiltLaunchSpec`

`MapPerformanceWorkbenchRestarterTest` owns current-invocation restart, PID/token readiness,
replacement termination on failure, old-JVM exit timing, no-update/no-build restart semantics, and
the exact accessible identities of Workbench and Talisman lifecycle controls, including the separate
no-update Talisman restart. It also proves dialog bounds are
checkpointed before command/launch and that checkpoint failure launches nothing and retains the
current JVM.

`MapPerformanceWorkbenchDialogLifecycleTest` owns modeless New/review display, main-window
interaction, one-instance raise behavior, independent process-persistent bounds, and usable-screen
clamping, including a live-bounds flush before lifecycle recreation.
`MapPerformanceRecentChangesModelessPromptTest` owns delayed New completion and canonical mutation.
`MapPerformanceChecklistReviewDialogTest` retains complete selectable review text, actions, Escape,
independent Approved/More Clarity/Wait controls, blocked presentation, and narrow/wide geometry.
`MapPerformanceWaitStateTest` owns the exact Wait marker round trip, mutation independence, reload,
ordinary-queue behavior, shared Wait-over-Clarity precedence, and compact yellow/green/ordinary
transitions. `MapPerformanceChecklistReviewDialogTest` owns the same live Review transitions and
independent controls. The focused semantic-role assertion in `TalismanThemeInstallerTest` protects
More Clarity and Wait normal, disabled, focus, and selection contrast for every theme.
The same test class owns shared theme-paint contracts: every subtle theme paints a readable gentle
selected-tab fill with a softened two-pixel content-edge accent, while Chaos retains its unfilled
seven-pixel full-strength treatment.
`TalismanThemeInstallerTest.sharedTabRowsReserveSelectedEdgeAboveFollowingControlBarAcrossEveryTheme`
owns the shared tab label-to-edge breathing room, complete selected accent, reserved-seam separator,
and non-overlap with an immediately following control bar for every theme.
`MapPerformanceWorkbenchModelessProductionLifecycleTest` owns the headed launcher/resolver path
through both production dialog factories while both windows are visible, including independent
top-level ownership, main-window interaction, one-instance reuse, the real launcher activation
listener's local-focus decision, and absence of any visible modal dialog.

`ProductDataStateCaptureRegistryTest` owns off-EDT adapter invocation, partial availability,
timeouts, fixed privacy fields, revision-mismatch rejection, defensive PNG bytes, and protocol
round-trip bounds. `ProductDataStateCaptureEndpointTest` owns the real localhost endpoint and
explicit missing-role transport, including removal of GM `MAP_3D` bytes before publication.
`MapPerformanceComplaintDataStateCaptureTest` owns prompt-returning nonblocking execution, safe
partial-result formatting, existing manual-prose preservation, EDT editor application, bounded PNG
staging through the existing attachment model, and the independent GM 3D byte rejection.

`WalkthroughScriptLoaderTest` owns the bounded versioned YAML grammar, rejection of ambiguous or
unknown steps, and real loader/validator coverage plus safe-capability coverage for every runnable
example under `testData/walkthrough/`. `WalkthroughPlaybackEngineTest` owns dry-run execution
separation, exact correlated operation waits, checkpoint writing, pause-frozen timing, Stop/Escape
cancellation, optional-screen skip continuation, and deterministic reports.
`ScriptedWalkthroughWorkbenchPanelTest` owns immutable resident loading, draft detachment, panel-owned
Workbench tab selection, and screen-oriented visited/skipped/failed report attribution.
The resident loader proof rejects generic invocation, window open/close, and checkpoint steps.
`TalismanWalkthroughTargetTest` owns the non-coordinate
allowlist, stable DTDT control resolution, guided real-control invocation, enabled state, and rejection
of unclassified actions. `ShowcasePresentationAdapterTest` owns transient owner-session admission,
exact capture and Assets/Factory readiness translation, allowlisted-versus-skipped target
classification, Viewer-orbit terminal mapping, optional-content skip, cancellation/late-callback
rejection, and the absence of business, provider, persistence, network, filesystem, capture, or video
capability. The resident loader test pins Factory selection before the orbit and an explicit correlated
wait after every asynchronous Assets tab/orbit step. Owner-focused session tests remain with Shell,
Adventure, Assets/Factory, Viewer, and Player.
`CodexAppServerClientTest` retains fake-stdio handshake, request, authentication, unsupported-method,
malformed-stream, process-death, timeout, and secret-redaction coverage after the transport moves to
the shared service package. `CodexExecutableResolverTest` owns explicit override precedence, PATH
discovery, Finder/Desktop macOS bundle fallback, non-macOS behavior, and actionable failure without a
live process. `CodexTaskStatusServiceTest` remains the exact Workbench permanent-owner
no-interference regression. `CodexConversationAdapterTest` owns text-only capability truth, exact new/
resumed dedicated identity, default active-turn refusal, explicit exact active-turn steering without a
competing start, restricted read-only turn composition, structured
output, streamed and stale-event handling, deadline, cancellation/interrupt, malformed output, and
close cleanup using fake stdio only.
`test-install-talisman-desktop-launcher.sh` owns isolated canonical-worktree embedding, exact
Talisman-process guarding, supported Java-host selection, detached Gradle run composition, and log routing
without launching Talisman. `test-install-performance-workbench-desktop-launcher.sh` owns the matching
Workbench process guard, Java-host selection, detached launch composition, and log route.
`CodexTaskLinkDiscoveryTest` owns paginated exact-cwd, pinned interactive-source discovery, unique
mapping, valid-manual preservation, stale unique replacement, missing/ambiguous non-mutation,
permanent-row/generated-worktree filtering, and list-only protocol traffic. The real panel regression
owns its bulk action, persisted mapping result, and concise completion summary.
`WorkbenchOpenChatDialogTest` owns modeless presentation, streamed-to-final transcript replacement,
retained exchanges, new-conversation reset, controls, and cleanup without a live Codex process.
`CodexConversationAdapterTest` additionally owns schema-free plain-text response transport.
Its exact turn-composition proof also rejects the deprecated nested `sandboxPolicy.readOnly.access`
object while retaining the supported read-only policy type.
`CodexTaskChatServiceTest` owns exact mapped identity, ordinary idle `turn/start`, intentional active
`turn/steer`, inherited task configuration, and matching streamed completion with fake stdio only.
`WorkbenchProcessEnvironmentTest` owns Finder-safe Homebrew PATH precedence, inherited-path retention,
deduplication, unchanged non-macOS behavior, validated Java 21 Gradle-host selection, exact
`JAVA_HOME` and Java-bin precedence, and missing-runtime refusal before process start.
`WorkbenchReleaseServiceTest` owns exact Gradle-wrapper classification so focused validation is pinned
without changing the Git command environment.
`WorkbenchActionStatusAreaTest` owns exact diagnostic retention, bounded width, natural wrapping,
vertical detail scrolling, selectable text, theme-error leading failure emphasis, and normal success
presentation at the default Workbench scale.
`MacSystemDictationSessionTest` owns fake-native off-EDT startup, process-wide serialization, isolated
capture, exact identity accepted/stale-rejected delivery on the EDT, cancellation, unsupported,
start-rejected, and failure outcomes. Existing Workbench editor/clarity tests retain the legacy
startup-only Dictation regression without invoking native Dictation.

`ScriptedWalkthroughProbeServerTest` owns the real localhost capabilities/action/operation/state/
checkpoint transport plus its dedicated port. The focused Workbench tab assertion in
`MapPerformanceWorkbenchTabsTest` owns additive tab composition without replacing Controls, Memory,
Performance Analysis, Branches, or Recent Changes. `PermanentBranchWorktreeParserTest` owns all direct
branch-backed live local worktrees, exclusion of Git-marked prunable registrations, and
familiar-area-first ordering. `PermanentBranchStateSourceTest` owns Git's exact prune-before-list
command, live-row retention, exact selected-area mapping, divergence/ancestry semantics, bounded Git
inspection, Finder-safe package-manager PATH for Git hooks and filters, and unavailable errors,
including one frozen pair of main commit IDs per Refresh and
explicit conflict-free versus conflicting merge preflight outcomes. It also owns exact-only discovery
of the named Seasons World Content and Spelunk siblings without enumerating other Git-root projects.
`PermanentBranchStateTest` owns
human area names, plain On main/Ahead/Behind/Diverged
derivation, explicit diverged ahead/behind truth, missing-main-work detection, mismatched-main
rejection, guarded update eligibility, permanently first Spelunk Main, and pinned-main-project then
diverged-first, closest-to-main, most-recent display ordering.
Its familiar-name proof includes the exact `codex/script-manager` to `Talisman Script Manager`
identity, while `PermanentBranchWorktreeParserTest` retains that branch among established areas.
`PermanentBranchUpdaterTest` owns immediate safety
rechecks and exact fast-forward/push arguments. It also owns conflict-free diverged main merge
preflight, owner-prefixed merge arguments, preserved task-branch push, and refusal before mutation
when the preflight reports conflicts. `ActiveDesignDocumentSourceTest` owns selected-worktree
exact-folder all-Markdown selection, including uncommitted files.
`PermanentBranchStatePanelTest` owns manual refresh, human-name rendering, selection retention, dirty
row truth, compact metadata, scrolling Markdown tabs, and stale-content
replacement on failure. It also owns top-level bulk catch-up composition and aggregate failure
reporting, including pending unchecks, persistent Hide Unchecked exclusion, one-shot Show Hidden,
refresh/reopen reset, checked restoration, immediate selected-row removal through the real checkbox
and header button composition, and the always-visible standalone-project contract.
It owns the consistent two-row Main/standalone/task entry structure, full-width status, wrapping action
height at constrained widths, visibility alignment, complete row-action retention, exact Link Task
saved-ID review, accessible linked/unlinked signal, and wording/accessibility without Git implications.
It also owns dirty-only Commit action composition and independent behind/uncommitted row truth.
`PermanentBranchStatusAppearanceTest` owns bold-name markup plus semantic status contrast across every
supported Talisman theme. `PermanentBranchStateTest` owns non-color On Main, Behind, Diverged,
Conflicts with main, and dirty combinations so unknown or conflict-free divergence is never red.
`PermanentBranchChangedFilesSourceTest` owns modified, untracked, deleted, and renamed working paths;
merge-base-relative committed paths; clean-but-ahead state; stale Main; missing worktrees; and Git
failure. `PermanentBranchStatePanelTest` additionally owns selected-row changed-file composition and
selection stability across Refresh.
`PermanentBranchCommitterTest` owns staged/unstaged/untracked and rename parsing, bounded exact review,
permanent-owner message prefix, changed-review refusal, isolated temporary-index staging/commit/reset,
and the absence of push, Main update, merge, rebase, or launch commands.
It also owns one task-status action per row, dynamic status presentation in the selected detail pane,
selection stability, and missing-mapping behavior. `CodexAppServerClientTest` owns fake-stdio
initialize/initialized, request/event transport, timeout, cancellation-safe process death, malformed
messages, authentication/unsupported classification, and secret redaction. `CodexTaskStatusServiceTest`
owns exact reviewed mapping, stored/live reporting, active-turn no-interference, deduplicated deferred
submission at a confirmed idle boundary, waiting-user refusal, cancellation, streaming, and retention.
`MainLandingHistoryParserTest` owns 24-hour prefixed-owner aggregation, newest-first order, commit
counts, and rejection of unowned subjects. `PermanentBranchWorktreeParserTest` owns Main-first order.
`ProjectZipCreatorTest` owns the exact tracked-HEAD Git archive command, project-specific root prefix,
Downloads destination, Full/Talisman-only timestamped filenames, and the compact archive's complete
declared exclusion set—including both Development Atlas locations during its move—without executing
a live user-project archive.
`PermanentBranchUpdaterTest` retains the authoritative per-branch mutation guards.
Those tests also own published strictly-ahead eligibility, the main-needs-work summary, Put on Main
composition, and exact task/main/upstream/fetch/fast-forward/push safety checks.
`ActiveDesignOwnerFoldersTest.canonicalPermanentOwnerFoldersExist` owns the exact ten permanent-owner
folder identities required for design and test-plan routing.

`MemoryTelemetryServiceTest` owns passive large-byte telemetry aggregation, bounded immutable snapshots,
owner lifecycle, jobs/reservations, primitive snapshot serialization, common-adapter reconciliation of
stable entries and independent named caches, and byte estimates. It proves that telemetry records do
not expose or retain payload objects, that repeated stable registrations are labelled created,
unchanged, or replaced without adding current rows, and that current-cache reconciliation records
releases without changing cache behavior.

`MemoryTelemetrySnapshotPublisherTest` owns exact cached/file agreement, atomic replacement cleanup,
and distinct application-instance filenames. `MapPerformanceMemoryEndpointTest` owns the real
localhost cached body plus schema/instance/stale headers and exact-instance control acceptance/refusal.
`MemoryTelemetrySnapshotCodecTest` owns complete JSON control-character escaping and exact diagnostic
text round trip so one telemetry label cannot invalidate a populated Workbench publication.
`MapPerformanceMemorySnapshotSourceTest`
owns attach-before/after publication, multi-instance de-duplication, live preference, visible stale
age, and safe corrupt-file rejection. `MapPerformanceWorkbenchMemoryPanelTest` owns the complete
semantic report sections, matching-PID external footprint/RSS/swapped/remainder supplement, and exact
snapshot save payload. `MacProcessMemorySamplerTest` owns bounded ps/vmmap parsing,
permission-safe partial results, and the 30-second command throttle;
`MapPerformanceWorkbenchTabsTest.composesControlsMemoryBranchesAndRecentChangesWithoutReplacingControls`
owns the Memory tab's stable top-level placement.

`MapPerformanceDiagnosticCaptureTest` owns the fixed normal evidence order, three thread samples,
per-adapter manifest, separate attachments, semantic file-fallback payload, redaction, bounded output,
partial-tool continuation, explicit intrusive/heap-dump boundary, bounded JFR artifact, and portable
zip contents. `MapPerformanceDiagnosticCommandTest` owns the production command boundary's timeout,
separate standard output/error, exit state, and truncation signal without attaching to Talisman.

`OperationJobMemoryTelemetryTest` owns the generic observer's live-job lifecycle, bounded zero-byte
aggregate outcomes and peak estimates, payload-free tokens, duplicate terminal no-op, and close
release. `ControlRuntimeMemoryTelemetryTest` owns both the direct aggregate counters and the real
`MapRuntimeSessionService.projectedArenaObjects()` attachment/projection seam, including revision,
Group, rematerialization, transient-byte, and close facts without projected-object retention.

`MemoryTelemetryServiceTest.ownerWithoutSafeReleasePathBlocksPassiveEvictionEligibility` owns the
metadata-only owner-policy blocker: a clean, unleased authoritative row is not advertised as
reclaimable until its source owner supplies a safe release path.

`LargeBytesResidencyManagerTest.trimsLeastRecentlyUsedEntriesAcrossCachesToTheLowerWatermark` owns
the first managed-cache vertical slice: one process byte total across multiple cache facades, global
access order, high-to-low watermark hysteresis, exact eviction evidence, per-owner notifications, and
zero retained bytes after cache close.
`LargeBytesResidencyManagerTest.manualClearReleasesEveryManagedEntryWithExactEvidenceAndNotifications`
and `MemoryLargeBytesMonitorPanelTest.clearUnpinnedPreviewsAndReportsExactManagedRelease` own the
manual-clear preview/result boundary, cross-cache notification, exact manual eviction evidence, and
zero manager residency without a garbage-collection action. `MapEditor2dMemoryTelemetryTest` owns
matching managed limits, derived-image access ages, independent cache reconciliation, and owner-close
release metadata.
`MemoryLargeBytesMonitorPanelTest.summaryExplainsHeapCapacityProcessAvailabilityAndEmptyRelease`
owns grouped heap readings, explicit unreported process measurements, and the no-eligible-release
result.

`LargeBytesResidencyManagerTest.authoritativePagesStayPinnedThenTrimOldestImmediatelyAfterRelease`
owns the separate authoritative high/low budget, pre-materialization active-chain lease, exact
not-eligible blocker, oldest-unpinned trim, immediate post-release trim, and telemetry-row release.

`MemoryLargeBytesMonitorPanelTest.realizedResidencyHeaderUsesCompleteActiveThemePresentation` owns
the realized monitor-header composition seam: every column uses active table-header background and
foreground, retains its native border, paints both roles, and preserves the sorted-column indicator
through the shared themed renderer.
`MemoryLargeBytesMonitorPanelTest.residencyColumnsExplainLoadStateAndSortFriendlySizesByBytes` owns
the Residency table's identity/category/loaded-size/eligibility lead order, explicit loaded versus
paged-out presentation, plain-language Undo/current/cache retention role, complete header
explanations, and numeric byte sorting behind friendly units.
`MemoryLargeBytesMonitorPanelTest.restoresUserColumnOrderAndWidthsForEveryTable` owns the stable
model-column preference encoding, independent horizontal widths, and round-trip restoration for all
five monitor tables. `MapPerformanceWorkbenchMemoryPanelTest` additionally owns composition of all
six rich monitor tabs and live-instance control routing inside Workbench.
`MemoryLargeBytesMonitorWindowTest` retains focused coverage of the original Talisman monitor's
open/closed state and usable-screen geometry while both presentations coexist.

`MapEditorScopeStateTest.terrainImportCandidateReportsRasterConversionMemoryJob`,
`MapEditorScopeStateTest.pipelineSmoothPublishesAndFinishesAuthoringActivity`, and
`MapEditorScopeStateTest.selectionMaskCalculationCoalescesProgressRefreshes` own the production
Authoring raster conversion,
pipeline, and Selection Mask job-to-aggregate seams. The first observes only detached candidate
conversion and does not alter acquisition acceptance semantics.

## Script Manager packages and runtimes

`ScriptPackageManagerTest`

- Schema-version-3 metadata, nested Python entry points, typed ports, and runtime declaration:
  `parsesPackageMetadataAndNestedPythonEntryPoint`
- Atomic install/replace/discovery/removal plus live catalog revision:
  `installsReplacesDiscoversAndRemovesPythonPackage`
- User override and bundled-entry reveal by exact runtime:
  `userPackageCanOverrideAndRevealBundledRuntime`
- ZIP traversal and recursive catalog-source rejection:
  `rejectsZipTraversalAndSourceContainingCatalog`
- One malformed installed package cannot suppress valid siblings:
  `discoveryIsolatesInvalidPackages`
- Exported Python and Java starter ZIPs contain parseable package manifests and expected entry points:
  `exportsParseablePythonAndJavaStarterPackages`

`ExternalJavaScriptRunnerTest.compilesAndRunsInstalledJavaPackageThroughSdk` owns Java source
compilation, public SDK adaptation, typed Float32 input/output, diagnostics/log publication, and the
content-addressed compilation cache hit. `ScriptContractsTest.bundledRegistryLoadsAllIndexedManifestScripts`
and `FeatureScriptContractsTest.bundledRegistryLoadsOnePackagedFeatureContourScript` retain bundled
schema compatibility. `ScriptWindowRegistryTest.keepsDifferentPlacesOpenFocusesDuplicatesAndClosesDeletedPlaces`
owns the Place-pinned Script Manager lifecycle and current title/menu route.

### Manifest Script semantic-operation route

`AuthoringScriptOperationServiceTest`

This class owns UI-style/direct same-service parity, exact durable result
revisions, scope-private query/cancel, atomic same-scope admission, queued/running cancellation,
cancel-versus-commit and cancel-versus-failure arbitration, legal service close, immutable terminal
reconstruction, and lease/reservation release. Its discoverable selectors are:

- `uiAndHeadlessCallersShareOneServiceAndReceiveExactDurableResults`;
- `noChangeSuccessStillCitesTheExactAuthoritativeDocumentRevision`;
- `wrongScopeCannotObserveOrCancelAnOperation`;
- `busyAdmissionRejectsAndDiscardsTheSecondCapturedRequest`;
- `concurrentSameScopeAdmissionReservesBeforeCaptureAndKeepsTheActiveIndex`;
- `addressedCancellationWhileQueuedPublishesOneNoCommitTerminal`;
- `addressedCancellationWhileRunningPublishesOneNoCommitTerminal`;
- `failureWhileAdapterCancellationSignalIsBlockedResolvesAsCancelled`;
- `workerObservedCancellationEstablishesTheAddressedRequestBeforeTerminal`;
- `cancellationAfterCommitGateIsTooLateAndSuccessRemainsImmutable`;
- `concurrentCancellationAndCommitGateProduceOneLegalTerminal`;
- `serviceCloseUsesTheNormalCancellationTransition`;
- `detachedPresentationCanReattachAndReconstructActiveThenTerminalTruth`.

Accepted package and runtime safety use
`ScriptContractsTest.acceptedScriptPackageFreezesExactBytesAndRejectsLaterPackageDrift`,
`ScriptContractsTest.acceptedIdentityKeepsOneManifestWhenTheCatalogReloadsBeforeFreeze`, and
`ScriptContractsTest.cancelledPythonManifestScriptProducesNoCandidateMutation`. Atomic output and history
use `MapDocumentSessionTest.dependencyScopedScriptCommitAppliesMultipleOutputsAtomically` and
`MapDocumentSessionTest.dependencyScopedScriptCommitPreservesUiChangesAndHasOneScriptUndoEntry`. The
three `MapEditorScopeStateTest` manifest selectors retain production UI commit, display-change, and
navigated-Place behavior. The six legacy pipeline operations remain outside this operation route.

## Document revisions, history, and persistence

`RegionSourceModelTest.backgroundImageMovesToLowestUnlockedOverlayWithoutCopyingItsRaster` owns the
colour-only Background and shared-raster transfer invariant.
`RegionSourceModelTest.deserializationInitializesMissingSourcesAndMigratesLegacyBackgroundColour`
owns one-pass semantic metadata repair and second-pass idempotence for a restored Background.
`MapEditorScopeStateTest.layerClarificationSourcePromotionCreatesMovableBackgroundOverlay` owns the
Source action's unchanged Place/raster geometry plus lowest unlocked Image Overlay result.
`CurrentPlaceRasterMigrationServiceTest` owns the one-preview/verified-backup/one-version combined
1,536-pixel and Background-image conversion, retained history/originals, stale rejection, and exact
postconditions. `TemporaryBattleMapMaterializerTest` owns the same Background/overlay boundary for
runtime-only battle crops.

`MapDocumentSessionTest`

- Undo/Redo and version publication:
  `undoAndRedoPublishNewVersionsAndNewEditsClearRedo`
- Failed durable-history preparation cannot publish a version or discard dirty working state:
  `failedHistoryPreparationCannotPublishOrDiscardDirtyState`
- Isolated conflict and one history entry:
  `isolatedCommitUsesOneHistoryEntryAndRejectsChangedWorkingRevision`
- Dependency-scoped Script merge:
  `dependencyScopedScriptCommitPreservesUiChangesAndHasOneScriptUndoEntry`
- Mask metadata shares an unchanged raster:
  `layerMaskUpdatesShareUnchangedHeightRasterPayloads`
- All structural and raster-bearing history plateaus at two retained steps:
  `historyRetainsOnlyTheTwoMostRecentCompletedTransactions` and
  `rasterBearingHistoryUsesTheSameTwoStepBound`
- Full-snapshot history shares every exact unchanged raster while retaining changed raster versions
  through Undo/Redo: `fullSnapshotHistoryReusesOnlyExactUnchangedRasterPayloads`
- Equal revisions with different bytes remain independent authoritative versions:
  `equalRasterRevisionWithDifferentBytesRemainsAnIndependentUndoVersion`
- Height, Terrain, Geology, and Image payload reuse all require exact content:
  `everyTypedRasterRequiresExactContentForCopyOnWriteReuse`
- Exact Height-raster delta preserves every unrelated Place payload and rejects its stale Layer revision:
  `heightRasterDeltaReplacesOnlyExactRegionAndRejectsStaleRevision`
- Passive document telemetry reports shared-history identity, exact owner protection, and close release:
  `passiveTelemetryMirrorsSharedHistoryPayloadsAndTheirProtection`

`MapWorkspaceMemoryInventoryTest
.countsTypedPayloadsOnceAndDistinguishesSharingFromIndependentCopies` owns typed Height, Terrain,
Geology, encoded Image, and materialized mask byte facts; shared payload de-duplication; independent
mutable-copy counts; and payload-free results. `MapEditorSnapshotMemoryTelemetryTest
.mirrorsPendingBackupPayloadsAndReleasesThemWithoutRetainingSnapshots` owns the independent
child-creation backup owner, dirty rollback safety, completion reconciliation, and close release.

`MapEditorDocumentIntegrationTest.rapidLayerVisibilityChangesPersistOnlyTheLatestState` owns the
coalescing document-worker boundary: repeated exact Layer visibility changes retain only the final
Place/Layer value, create one durable display-safe version, and leave the EDT-facing scope responsive.
`MapEditorDocumentIntegrationTest.olderDocumentWorkerResultCannotReplaceNewerScopeMutation` owns the
source-revision stale-result rejection for ordinary document replacements.
`MapEditorDocumentIntegrationTest.repeatedHeightAdjustmentsPublishRasterDeltasInsteadOfWorkspaceCopies`
owns the 25-Place Height input route: repeated adjustments emit only exact raster commands, no full
workspace replacement, and coalesce into one durable version.

`SqliteMapDocumentRepositoryTest`

- `loadsRastersAsEvictablePagesAndKeepsFailedEditsResident` owns lazy zero-residency load, exact page-in,
  manual page-out/reload, and failed-save dirty-pixel retention.
- `savedRasterUndoAndRedoRemainExactAcrossPageOut` owns successful edit, page-out, exact Undo and Redo,
  and repeated durable reconstruction.
- `repairedSemanticRasterUndoDeflatesToDurablePages` owns the startup-repair regression: the exact
  pre-save semantic rasters become zero-byte durable Undo handles without activating an extra version,
  and Undo remains exact after manager eviction.
- `roundTripsUserLayerOrderStableSemanticIdentityAndNeutralContent` owns persisted user order,
  stable semantic Layer IDs/kinds, neutral raster payloads, and negative effective elevation reload.
- `displayOnlyVersionReusesEveryUnchangedRasterAdmission` owns the display-safe persistence boundary:
  a durable visibility version reloads with its exact Layer state while reusing every unchanged raster
  and selection-mask content admission.

`CurrentPlaceRasterMigrationServiceTest` owns the guarded 1,536-pixel current-Place migration:
preview identity and complete affected counts, verified pre-change backup, type-aligned raster and
selection resampling, one checked successor version, retained immutable prior version/imported
original, complete project integrity, and stale-token rejection before any backup or mutation.

- Captured Source admission, reload, and durable read after filesystem-cache loss:
  `capturedSourceAdmissionPrecedesNormalizedSaveAndReload`
- Typed Geo raster round-trip:
  `roundTripsCategoricalGeologyThroughSchemaFour`
- Typed constructed-spatial Source, Layer, barrier, portal, light, and point-elevation round-trip,
  including a legacy contour in the same document:
  `roundTripsConstructedSpatialFeaturesElevationAndUniversalVttMetadata`

Class routes: `ProjectRasterContentStoreTest` for every raster kind, exact page-in/page-out, and typed
copy-on-write; `AuthoritativePlaceRasterResidencyTest` for child/direct-parent/root protection, sibling
exclusion, navigation lease replacement, and post-save inactive-Region handle installation;
`MapDocumentSourceLivenessTest.failedClosePublishesConservativeUnionAndSuppressesCollection` for
failed close and live-reference collection safety.

`TerrainRegionSemanticBaselineTest` owns exact new-Place semantic baseline order/content;
deterministic, idempotent partial-Place repair; fixed core semantic anchors with preserved auxiliary
slots; legal auxiliary movement boundaries; and identity/position-preserving Reset to Neutral.

`MapEditorScopeStateTest.layersInspectorSeparatesSourcesAndSelectsSemanticTabs`,
`MapEditorScopeStateTest.sourceAndLayerOperationFailuresExplainTheirNoOp`, and
`MapEditorScopeStateTest.backgroundRemainsAuthoritativeWhenOverlayVisibilityChanges` own
Layers/General/Sources/Flavour
composition, explicit auxiliary Add/semantic Reset routes, core removal/reorder rejection, kind/name
labels without draw-order prefixes, and Image Overlay insertion immediately above Background.

`MapEditorScopeStateTest.layersToolbarEditPinsExactTargetWhileRowsBrowseIndependently`,
`MapEditorScopeStateTest.layerEditPinRequiresSaveDiscardOrKeepEditingOnlyForDeliberateSwitchOrClose`,
`MapEditorScopeStateTest.activeLayerEditorShellIsSharedByBothTwoDimensionalHostsAndRetainsExactTarget`,
and `MapEditorScopeStateTest.activeLayerEditorRejectsStaleIdentityAndShowsHiddenTargetOnlyForEditing`
own the single Layers-toolbar Edit pin, independent row browsing/display controls, deliberate exact
switch, dirty Save/Discard/Keep Editing, explicit editor capability, one exact session across retained
2D hosts, ordered top-history dispatch, stale rejection, and editor-only rendering without visibility
mutation.

`MapEditorScopeStateTest.activeLayerWorkingPayloadUndoDiscardAndAcceptStayIsolated`,
`MapEditorScopeStateTest.activeLayerWorkingPayloadFeedsAttachedThreeDPreviewUntilDiscard`,
`MapEditorScopeStateTest.activeLayerAsyncTokensRejectLateResultsAndStaleAcceptPreservesWork`, and
`MapEditorScopeStateTest.activeLayerNavigationOffersSaveDiscardCancelAndRetainsFailedWork`
own the independent working payload, two-step local history, attached 3D preview with canonical
capture isolation, no-mutation Discard, one-transaction Accept, latest-only async result,
stale-conflict retention, exact resolution command, and the reviewed exact-target dirty navigation
decision.

`MapEditorScopeStateTest.parentPullUsesTopWorkingHistoryAndProtectsDirtyLayerAndPlaceNavigation`
owns the Geography Parent-pull integration with the exact open Heightmap working raster, top-ordered
Undo/Redo ahead of Selection history, canonical isolation until Accept, and reviewed dirty Layer and
Place navigation.

`MapEditorSaveActivityOverlayTest.centeredActivityRemainsVisibleUntilSaveCompletion` owns the
centered input-blocking presentation, indeterminate progress state, and exact install/remove lifetime
used while dirty-navigation Save is committing.

`MapEditorScopeStateTest.inPlaceHeightmapProviderRejectsHiddenWorkingRasterOperations` and
`MapEditorScopeStateTest.inPlaceHeightmapAcceptCommitsWorkingRasterAndElevationRangeOnce` own the
in-place nine-operation provider, hidden-target Brush/Apply rejection, working-only local history and
Discard, temporary elevation-range projection, and one-transaction Heightmap Accept boundary.
`MapEditorScopeStateTest.inPlaceHeightmapSmoothRejectsChangedSelectionWithoutInstallingLateResult`
owns off-EDT Smooth Selection-revision cancellation and retention of the last valid working raster.

`MapEditorScopeStateTest.inPlaceGeologyProviderEditsOneWorkingPayloadAndAcceptsOnce` owns the shared
Brush/Selection categorical editing route, working-only history, and one checked Geology Accept.
`MapEditorScopeStateTest.inPlaceGeologyGenerationUsesInternalLatestOnlySeedAndWorkingProvenance`
owns internal seed progression, superseded-result rejection, and working provenance retention.

`MapEditorScopeStateTest.inPlaceTerrainProviderOffersOnlyLandAndAcceptsOneWorkingPayload` owns the
exact nine-category panel, shared Brush/Selection working route, and one checked Terrain Accept.
`MapEditorScopeStateTest.inPlaceTerrainGenerationUsesExactWaterMaskLatestSeedAndWorkingProvenance`
owns five-scale/internal-seed requests, exact Water-mask binding, superseded-result rejection, and
working provenance retention. `MapEditorScopeStateTest.terrainWaterMaskRequestUsesExactIdentityAndRejectsChangedMask`
owns changed-mask rejection on the shared generator contract.

`MapEditorScopeStateTest.layersToolbarEditPinsExactTargetWhileRowsBrowseIndependently` and
`MapEditorScopeStateTest.layerEditPinRequiresSaveDiscardOrKeepEditingOnlyForDeliberateSwitchOrClose`
own the distinct browsed/pinned/pending Layer identities, independent visibility and opacity actions,
Save/Discard continuation to the requested target, Cancel retention, and pinned-row presentation.
`MapEditorScopeStateTest.topToolbarOwnsFourSpatialToolsWithoutRetargetingPinnedEditor` owns the four
explicit selection/brush tools and Micro order without editor retargeting.
`MapEditorScopeStateTest.categoricalBrushUsesExplicitShapeAndClassSafeSelectionClippedSmoothing`
owns square/round footprint truth, Selection clipping, and valid-category-only Terrain/Geology Smooth.
`AdaptiveTopToolbarLayoutTest` owns single-line whole-group compaction, most-recently-used promotion
without suppressing the icon action, and full-width restoration for the experimental top toolbar.
`ImageBrushEngineTest` owns immutable square/round image footprints, Selection-masked neighborhood
operations, and empty-mask safety. `HeightBrushEngineTest` owns explicit Hard versus Feathered edges.

`MapEditorScopeStateTest.inPlaceSurfaceProviderKeepsImportedPixelsWorkingUntilAccept` owns the
in-place control set, working-only imported and painted pixels, Undo/Redo, display-command revision
parity, and one checked Surface Accept. `MapEditorScopeStateTest.inPlaceSurfaceGenerationUsesLatestExactInputsAndReportsAcceptedStaleness`
owns internal-seed latest-only installation, exact Terrain/Geology/cell-scale provenance, and
accepted stale-without-replacement status. `MapEditorScopeStateTest.inPlaceSurfaceParentCaptureUsesFootprintAndRejectsChangedSource`
owns exact ancestor footprint sampling and changed-source rejection.

`MapEditorScopeStateTest.inPlaceImageOverlayOpacityStaysWorkingUntilAcceptAndDirtyRemovalIsBlocked`
owns the compact Image Overlay provider, no-mutation selector refresh, working-only 2D/attached-3D
opacity, one checked Accept, dirty-removal rejection, and clean-removal route.
`MapEditorScopeStateTest.pinnedImageBrushStaysOnEditingLayerWhileAnotherRowIsInspected` owns exact
Image Overlay brush targeting, independent row inspection, working-only Undo/Redo, and checked Save.
`MapEditorScopeStateTest.layerRowsExposeSharedOpacityAndCommitOnceWithoutLowerDetailDuplicate` owns
the Visible/Pointer/Opacity/name row order, exact ten options, structural/raster/feature presentation,
legacy display without mutation, one checked commit/notification, stale rejection, reload, keyboard
route, bounded width, accessibility, and absence of the former lower-detail duplicate.
`SqliteMapDocumentRepositoryTest.roundTripsUserLayerOrderStableSemanticIdentityAndNeutralContent`
owns exact stable Image Overlay identity, kind, image content, and order reload at every legal
content-stack position without moving the core semantic Layers.

`AppWideIconLabelsServiceTest.visibleListRendererControlsParticipateInAppWideIconLabels` owns
bounded discovery of icon-only controls rendered by visible Swing list rows, including the Layer-row
Visible, Pointer Enabled, and Edit group.

`SourceArtifactStoreTest`

- Exact-byte admission and rollback:
  `promotionAdmitsExactBytesBeforeReturningAndRollsBackAdmissionFailure`
- Generation-aware collection:
  `importsNativeImageAndCollectsOnlyAfterACompleteNewGeneration`

`MapEditorSourceBehaviorTest`

- Foreign and malformed rejection:
  `rejectsForeignAndMalformedSourceCommandsWithoutMutation`
- Capture failure without structure mutation:
  `routesParentCaptureFailuresWithoutChangingDocumentStructure`

`MapEditorScopeStateTest`

- Source dependency protection:
  `sourceLifecycleCreatesOnlyCompatibleOverlaysAndProtectsDependencies`
- Source preview identity and progress-notification stability:
  `sourcePreviewIgnoresUnrelatedProgressAndReloadsOnlyForNewIdentity`
- Typed Heightmap capture:
  `typedHeightmapRendersAndCanFeedParentCaptureWithoutPng`
- Stale capture preservation:
  `staleParentCaptureRecipeKeepsChildBytesAndRecapturesValidRemainder`
- Fully invalid capture settles without mutation:
  `allStaleParentCaptureRecipeRejectsWithoutMutationOrPendingComposition`
- Mask capture metadata:
  `parentCaptureSlicesSelectionMasksAndPreservesIndependentMetadata`
- Authoritative Water Mask pixels, alpha, typed identity, history/reload, and Water Level use:
  `parentCaptureUsesAuthoritativeWaterMaskRasterInsteadOfAssociatedHeightmapSource`
- Region appearance editor repaint-before-command ordering and one settled publication:
  `regionsAppearanceTypingPaintsBeforeOneSettledPresentationUpdate`

`HeightmapRasterExportServiceTest`

- Lossless sample/domain/range round-trip:
  `pngRoundTripPreservesUnsignedSamplesDimensionsDomainAndElevationRange`
- Managed-only exact artifact admission:
  `prepareSourceCreatesOnlyLosslessImmutableManagedArtifact`
- Uncommitted and failed admission orphan cleanup:
  `uncommittedPreparationAndFailedAdmissionLeaveNoOrphan`
- Bounded Source labels rather than filesystem destinations:
  `sourceNamesAreBoundedPngLabelsRatherThanFilesystemDestinations`

`MapEditorScopeStateTest`

These public-facade checks also gate the internal `MapEditorHeightmapExportCoordinator` extraction.

`ParentCaptureSelectionDialogTest.nonSquarePreflightIsBoundedStructuredThemedAndActionable` owns
the all-theme bounded/wrapping non-square-cell warning, exact Place/X/Y diagnostic presentation,
ordered Grid Resolution repair steps, standard OK action, and oversized preferred-width cap.

- Place-Source copy/replace, history, preservation, and reload:
  `heightmapMetadataExportsCopyAndReplaceAsManagedPlaceSourcesWithExactHistory`
- Compact metadata action with no disk-first choices:
  `heightmapMetadataShowsOneCompactPlaceSourceActionWithoutDiskChoices`
- Selected Raise/Lower:
  `selectedRaiseAndLowerExpandRangePreserveElevationsAndUndoAsSingleEdits`
- Irregular selection Flatten:
  `flattenHeightmapHonorsIrregularRasterSelectionMask`
- Child-to-parent height write:
  `regionInspectorWritesChildHeightmapToParent`
- Real Parent Overview popup pull for exact persisted child `Snowden North Fort`, asymmetric
  non-integer semantic-raster projection, representative area aggregation, no logical-cell blocks,
  byte-identical outside-footprint samples, 3D canonical-raster consumption, cancellation, exact
  checked commit, child preservation, Undo/Redo, and stale rejection:
  `canvasFootprintPullsChildHeightmapThroughOneReviewedCheckedCommit`
- Observed-local Heightmap presentation, color/opacity-only Background capture, area-aggregated
  child-to-parent reduction, selected parent-to-child pull, and exact-hit dual command composition:
  `typedHeightmapRendersAndCanFeedParentCaptureWithoutPng`,
  `parentCaptureOffersSemanticBackgroundWithoutImageOrSelectionSemantics`,
  `backgroundMetadataShowsColourOpacityAndAuthoritativeRasterSize`,
  `childHeightmapBackwriteAreaAggregatesFinerRasterWithoutInventingExtrema`,
  `selectedChildAreaPullsExactParentHeightmapWithUndoAndStaleRejection`, and
  `surfaceMenuComposesExactDirectionalHeightmapPullsFromPointerHit`
- Independent-child full-parent mapping, irregular freeform-mask interpolation, isolated exact
  Heightmap Undo/Redo, and Selection/history preservation:
  `independentChildFreeformSelectionPullsExactParentHeightmap`
- Geo generation, history, and reload:
  `geoTablesNoImportGenerateCompletesHistoryPersistenceAndReload`
- Geo cancellation without partial content:
  `cancellingGeoTablesGenerationCommitsNoPartialDocumentMutation`

The bounded themed destination chooser is routed by
`NativeFileChooserTest.pngSaveChooserUsesOneBoundedThemedPngDestination`.

Shared Cursor mode icon presentation is owned by three focused checks:

- Distinct resolvable pointer-plus-lasso and pointer-plus-grid production resources at real size:
  `ControlIconCatalogTest.cursorModesUseDistinctRecognizableProductionArtwork`
- Preserved semantic labels, accessibility, tooltips, and 36-pixel presentation:
  `ControlButtonPresentationTest.genericMapToolButtonsPreserveExactIdentities`
- Visible normal, active, and disabled states across every application theme:
  `ControlGalleryPanelTest.cursorModesRetainDistinctReviewStatesAcrossEveryTheme`

- Explicit Cursor Freeform/Cursor Grid/Brush controls, Grid-pointer independence, last-brush
  restoration, and no Mask mode:
  `topBarExplicitCursorModesRestoreLastBrushWithoutChangingLayerPointerState`
- Exact canvas addressing follows the explicit cursor mode rather than Grid visibility/pointer state:
  `canvasSelectionGeometryFollowsExplicitCursorModeInsteadOfGridPointerState`
- Micro pixel/whole-cell Brush addressing and mutation-free toggle:
  `microAddressingMakesBrushPixelPreciseOrWholeGridCellAddressed`
- Legacy persisted Mask-mode normalization:
  `legacyPersistedMaskSelectLoadsAsSelect`
- Grid selection operations and Control/Command toggle across raster and Grid geometry:
  `canvasShiftAddsAndCommandTogglesPointAndGridSelections`
- Exact add/subtract/toggle and mixed logical/raster pixels:
  `canvasRasterOperationsPreserveExactOverlapAndMixedGridPixels`
- Press-time geometry and operation lock:
  `canvasSelectionGestureKeepsMouseDownGeometryAndOperation`
- 2048×1877 five-pixel Reduce output, responsive EDT heartbeat, worker duration/thread evidence,
  current-only install, stale revision rejection, and Selection/new-request/tool/close cancellation:
  `selectionReductionRunsOffEdtAndRejectsStaleOrCancelledWork`
- Name/ID multi-result Search without navigation:
  `regionTreeSearchOpensEveryNameAndIdMatchWithoutNavigating`

`SelectionMaskIndexTest`

- Component calculation and storage:
  `calculationReportsProgressAndStoredIndexRoundTrips`
- Compact component runs:
  `solidComponentPersistsAsCompactRuns`

`MapEditorScopeStateTest`

- Unified direct-hit Selection editor, top-group preview-only smoothing/Cancel/Apply,
  move/scale/local reshape, Escape, one-change Apply, Undo/Redo, and stale scope rejection:
  `canvasUnifiedSelectionEditorPreviewsMoveScaleReshapeSmoothAndCancel`,
  `unifiedSelectionEditAppliesOnceRestoresHistoryAndRejectsStaleScope`
- Editable-canvas padding deselect, protruding-handle priority, and saved Layer/raster/document
  preservation: `canvasEmptyBackgroundClickClearsOnlyTransientSelection`
- Dense exact-mask controls, holes/disjoint contours, translation, scale, local reshape, smoothing,
  and faithful raster authority: `SelectionEditModelTest`
- Exact Contour world/pixel-center fill, even-odd holes/islands, and open/outside/empty rejection:
  `ContourSelectionRasterizerTest`

`MapEditorScopeStateTest`

- Contour Selection union/history, exact Place/Selection/Contour/Heightmap stale rejection, visible
  unavailability reasons, real contour-over-Selection hit/action routing, and zero source mutation:
  `contourSelectionUnionPreservesSourcesHistoryAndRejectsStaleInputs`,
  `contourSelectionDraftExplainsOpenWrongAndHiddenHeightmapInputs`,
  `canvasContourHitPreservesSelectionAndOffersAddContourAction`
- Canonical edited-mask consumption by reviewed Heightmap Apply, isolated Cancel, working Undo/Redo,
  and visible semantic Heightmap authority:
  `contourSelectionFeedsReviewedHeightmapApplyUndoRedoAndDiscard`,
  `inPlaceHeightmapProviderRejectsHiddenWorkingRasterOperations`
- Remove from Mask and reload:
  `removeFromMaskSubtractsExactOverlapRefreshesHistoryAndReload`
- Saved selection history order:
  `savedSelectionOverwriteAndClearAreOrderedDocumentUndoActions`
- Water-mask compatibility:
  `incompatibleWaterMaskLeavesSelectionAndHeightmapUntouched`
- Coordinate selection revision guard:
  `coordinateMaskSelectionUsesTopmostEligibleLayerAndInvalidatesByRevision`
## Created Things maintenance

`CreatedThingPresentationDefaultsTest` is the smallest gate for live exact Type defaults without
instance copies, independent Character overrides/removals, Token/Icon plus Model readiness priority,
Type-sensitive revision projection, Creature sheet snapshot stability, privacy, and EDT rejection.
`TypePresentationProjectionTest` covers inherited labeling, protected shared-Type removal, exact
instance override removal, and the four supported Type default roles.
`UserAssetsWorkspaceTest.typeGenerationKeepsExactDefaultAndRefreshesInheritedSelection` covers the
real expandable Character Class/Creature Type hierarchy, in-place Keep & Attach role reveal,
replacement history, live inherited-instance update without copied rows, and reload reconstruction.
`UserAssetsWorkspaceTest.dataImportSelectsDocumentsAndDefinitionsTreeShowsAllMonsterTemplates`
owns definition-only Type inventory totals while each definition retains its owned media-role
presentation descendants.

`CharacterSheetDocumentTest` covers bounded complete Quick Import, the Sarsus Paladin 7 field lanes,
unknown-extension preservation, exact syntax locations and semantic paths, required canonical
front-matter identity, and Markdown/Quick Import round trip.
`CharacterStandardPackageServiceTest` covers same-transaction creation, exact DB-owned lowercase
sheet bytes, accepted facts/Age, empty Icon/Token/3D Model slots, Token-to-Icon read fallback without
storage aliasing, idempotent reviewed completion, preserved overrides, exact stale-token rejection,
checked document revisions, and multi-Character one-connection listing while a separate SQLite write
reservation proves that read projection performs no schema DDL or lock escalation.
`CreateCharacterDialogTest` covers actionable Quick Import review
without requiring exact installed Types, focus-independent commit of pending Guided level/Age
editors, retained malformed input with exact-field focus, canonical-document adaptation, and no
mutation before acceptance. `CharacterSheetEditorPanelTest` and the focused Character
Card cases in `UserAssetsWorkspaceTest` cover invalid-save preservation, exact revision save/reload,
dirty-only Save enablement, exact post-create Created Things selection, and the three visible
Character surfaces.
`UserAssetsWorkspaceTest.importedCharacterRefreshesGameImagesAndRoutesModelToInteractivePreview`
covers committed imported
Icon/3D associations, the real bounded model thumbnail, and Character model-leaf selection into the
existing interactive Game Images preview. `LocalizationServiceTest` preserves the canonical filename
while proving Character Card/Game Images/Other Images chrome across all shipped locales.
`CreatedThingsStandardPackageProjectionTest` proves Empty versus Managed content missing leaves and
the exact completion summary without a runtime launch.

- Selective preview token, stale rejection, exact owned-row deletion, and Place/runtime/media
  preservation: `CreatedThingsResetServiceTest.resetRequiresExactPreviewAndPreservesPlacesRuntimeAndMedia`.
- Exact mixed Character/association preview, stale rejection, rollback, canonical-byte preservation,
  and protected whole Places: `CreatedThingsDeletionServiceTest`.

## Places and navigation

`MapEditorScopeStateTest`

- Shared Authoring/GM recent activation and no map mutation:
  `recentPlacesShareCanonicalAuthoringAndRuntimeActivationWithoutStateMutation`
- Shared Place Details vertical Geography/Game Objects composition, ordinary divider dragging,
  smaller-side double-click collapse/restore, friendly drawer captions, and retained exact roots:
  `SpaceMetadataTest.panelIsQuietSelectableScrollableAccessibleAndNarrowSafe` and
  `SpaceMetadataTest.authoringColumnBuildsListOverviewMetadataAndRuntimeFactoryUsesGmOwner`
- Selected footprint scale:
  `createSubregionUsesExactSelectionScaleAndParentGridDefaults`
- Pre-commit dialog cancellation, exact footprint, explicit name, and optional captured Layers:
  `canvasSelectionPopupCreatesSubregionFromCurrentSelection` and
  `namedSubregionCommandPreservesDraftedFootprintAndCapturesInOnePersistentMutation`
- Navigation owner:
  `topBarOwnsRegionNavigationAndInspectorDoesNot`
- Downward camera transfer:
  `downwardRegionSelectionPublishesCameraTransferWithoutAnOpen3DView`
- Attachment/link transfer:
  `canvasSelectionPopupListsActualChildrenAndTransfersAttachment`
- Exact marker-only removal, retained child content/navigation absence, and stale-command rejection:
  `canvasFootprintPopupRemovesOnlyTheConfirmedMarkerAndRejectsStaleRemoval`
- Right-double-click 3D target, retired surface-menu 3D-action absence, and reverse current-zoom
  centering without state mutation:
  `rightDoubleClickSynchronizes3DAndContextOmitsRetired3DActions`
- GM pointer-anchored wheel zoom and stale persisted-viewport echo rejection:
  `runtimeWheelZoomRejectsStaleViewportEchoWithoutAnchorWobble`
- Checked current target, invalid target, and stale A→B→A navigation rejection:
  `MapRuntimePresentationProjectorTest.gmChildRegionSelectionUsesOneCheckedNavigationContextAndRejectsStaleReturn`
- Runtime-only selected-footprint highlight:
  `MapEditorRenderSupport3DTest.readOnlyRuntimeCompositionHighlightsOnlyItsSelectedChildRegion`
- Header arrow identity, enablement, grouping, and exact command payload:
  `MapRuntimeComponentsTest.gmControlHeaderIdentifiesScreenAndRoutesBoundedRegionNavigation`
- Production header Parent and pending structural reconciliation commands:
  `MapRuntimeComponentsTest.gmControlHeaderRoutesParentAndExposesPendingMapReconciliation`
- Real production header → application bus → behavior → session → state-return navigation across
  deliberately non-hierarchical storage order:
  `MapRuntimeUiCoordinatorTest.controlHeaderRegionNavigationReturnsThroughOwnedRuntimeState`
- Read-only Authoring footprints and existing double-click navigation:
  `placeOverviewKeepsFootprintsReadOnlyAndDoubleClickNavigatesSiblings`
- Complete exact-Layer GM overview filtering without runtime visibility mutation:
  `runtimeOverviewListsEveryExactContextLayerAsLocalVisibility`
- Runtime Overview ownership of exactly one working local Display Layers action, with no inactive
  Navigation launcher beside it:
  `runtimeOverviewOwnsOneWorkingLocalLayerSelector`
- Real GM Place Overview 3D/combined build progress, cancellation, stale replacement, and accepted
  scene completion through the production route:
  `runtimePlaceOverviewShowsSharedProgressAcrossThreeDAndCombinedBuilds`
- Real Authoring Place Overview camera-group order, separate Layers ownership, 2D disablement, and
  canonical saved-camera transition to Top View:
  `authoringPlaceOverviewTopViewUsesCanonicalCameraAndKeepsLayersSeparate`
## Arena3D capture, threading, and stale identity

`JavaFxRuntimeTest`

- First startup returns promptly to an EDT caller while the native request remains blocked on its
  dedicated daemon; multiple callers observe one shared readiness result:
  `startupRequestLeavesEdtAndSharesReadiness`.
- Concurrent first callers submit exactly one native startup request:
  `concurrentCallersRequestStartupOnlyOnce`.
- A native startup failure completes every isolated caller future exceptionally without reporting
  readiness: `startupFailureCompletesEveryCallerExceptionally`.

`ProgrammedViewControlsTest`

- Six exact-Place slots capture and apply through the host while retaining normalized persistence:
  `savesAndAppliesSixPlaceScopedViewsThroughHost`
- Populated-slot replacement requires confirmation and rejects completion after a Place change:
  `confirmsOverwriteAndRejectsStalePlaceCapture`
- Clear remains Place-scoped and close rejects a late camera capture:
  `clearIsPlaceScopedAndCloseRejectsLateCapture`

`MapRuntimeComponentsTest`

- The embedded GM 3D toolbar composes the canvas-owned programmed Views beside its ordinary camera
  controls and exposes no local Follow or Spawn route:
  `gm3DToolbarComposesProgrammedViewsWithoutLocalFollowOrSpawn`
- The Control header publishes exact targeted/broadcast mode, orientation, divider, Place, and
  document stamps; stale projections are refused and Present refreshes Follow with zero clients:
  `gmFollowPublishesExactStampedLayoutAndPresentRefreshesWithoutClients`

`Map3DViewerRegistryTest`

- The embedded GM canvas exposes one stable programmed-view component, saves against the displayed
  Place, and switches to the next Place's independent slot set:
  `gmEmbeddedSceneFollowsRuntimePlaceAndRejectsStaleConsumerEvents`
- Pinned viewer dialogs retain programmed views while Follow and Authoring Spawn dialogs do not
  compose them:
  `authoringSpawnsUseLatestSizeTemplateWithoutAffectingOther3dRoles`

`Map3DSceneCaptureTest`

- Revision-paired EDT capture:
  `capturesGridStateFlatFallbackAndAppliedRevisionPair`
- Persistent Barrier/Portal/Light capture with effective defaults and visibility:
  `capturesPersistentConstructedFeaturesAsImmutable3DSceneInput`
- EDT requirement:
  `rejectsOffEdtCaptureAndMissingRegion`

`ConstructedSceneGeometryBuilderTest`

- Portal opening split, Place-relative elevation, and Light placement:
  `splitsAssociatedPortalOpeningAndPlacesLightWithTheTerrainTransform`
- Closed portal panel without snapshot mutation:
  `closedPortalAddsPanelWithoutChangingThePersistentSnapshot`

`Map3DSelectionTextureBuilderTest`

- Canonical 2D logical Selection fill and transparent outside pixels:
  `logicalSelectionUsesCanonical2dFillAndLeavesOutsideTransparent`
- Canonical 2D raster Selection pixel parity:
  `rasterSelectionMatchesCanonical2dOverlayPixelForPixel`
- Empty state and cooperative cancellation:
  `emptySelectionHasNoComponentAndCancellationStopsBuild`
- Source-size independence and visible-output bound:
  `largeSourceSelectionBuildsOnlyAtVisibleOutputResolution`
- Steep Selection uses the installed terrain atlas without mutating base pixels:
  `steepSelectionUsesTheExactInstalledTerrainAtlasWithoutChangingBasePixels`
- Shared UI-only versus content/selection invalidation semantics:
  `renderInvalidationSeparatesUiNotificationsFromStaleSelectionContent`

`Map3DSceneControllerTest`

- Persistent wall/opening/Light installation and replacement cleanup:
  `consumesConstructedBarrierPortalAndLightSnapshotsInTheJavaFxScene`
- Passive real-FX resource estimates, completed build removal, and close release:
  `passiveTelemetryCountsAppliedFxResourcesAndReleasesThemOnClose`
- Texture-only scene replacement keeps installed terrain/child-Region meshes and changes the image:
  `textureOnlySceneReplacementReusesTheExistingJavaFxTerrainMesh`
- Terrain hover ignores deliberately non-geographic render UVs and uses the intersected 3D point:
  `terrainHoverPublishesExactPickAndOneExitOnTheFxThread`
- Admitted Arena Object diffuse texture and untextured fallback remain separate terrain consumers:
  `admittedCanonicalModelUsesOwnedDiffuseTextureAndKeepsColorFallback`
- Transient Selection texture apply/hide/restore, scene replacement, and close cleanup:
  `appliesTerrainReachesNonSingularTopViewAndCloses`
  (including a display-bounded overlay smaller than the source terrain texture)
- Target-only camera retention:
  `targetOnlySurfaceRetargetPreservesViewAndRendersTerrainUnderside`
- Drop ray and unavailable scene:
  `embeddedDropPixelUsesCameraRayAndUnavailableSceneReturnsNoResult`
- Cohort preview and one result:
  `cohortOverlayPreviewsLocallyCancelsAndEmitsOneStampedDestination`
- Exact current Peak-label anchor routing and stale-label rejection:
  `peakLabelDoubleClickUsesExactCanonicalAnchorAndRejectsDetachedScene`
- Exact terrain-only Authoring context gesture and detached/nonterrain rejection:
  `contextMenuRoutesOnlyExactCurrentTerrainAndLeavesOtherConsumersUnclaimed`
- Context gesture scene stamps and invalid-coordinate rejection:
  `contextMenuGestureOwnsExactSceneStampAndRejectsInvalidCoordinates`

`TerrainMeshBuilderTest`

- Flat/gentle exact plan-view texture, smooth transition, independent non-overlapping side islands,
  and vertically stable locally matched steep fill:
  `flatTwoByTwoMeshHasUpwardWindingNormalsAndCanonicalUv`,
  `steepFacesOwnIndependentSideProjectedAtlasIslands`
- Fully steep atlas scale and fill remain stable across height exaggeration:
  `fullySteepAtlasScaleAndFillStayStableAcrossHeightExaggeration`
- Exact final multi-layer composite identity:
  `Map3DSceneCaptureTest.terrainSurfaceIdentityOwnsTheFinalMultiLayerComposite`
- Authoring grid stays a separate semantic snapshot and never enters the terrain base texture:
  `Map3DSceneCaptureTest.terrainBaseExcludesGridWhileRetainingItsSeparateSemanticSnapshot`
- Player-safe capture excludes Grid/Legend pixels while retaining exact terrain/Heightmap revisions
  and separate world annotations:
  `PlayerRuntime3DLegendCaptureTest.playerTerrainExcludesGridAndLegendWhileSceneReceivesWorldAnnotations`

`TerrainRenderCacheTest`

- Exact Heightmap geometry reuse across texture-only composition changes, cancellation, eviction,
  and release:
  `reusesExactHeightGeometryAcrossTextureOnlyCompositionChanges`,
  `evictsOldProductsRejectsCancellationAndReleasesAllRetainedBytes`
- Large rectangular composite, negative/smooth/abrupt heights, preparation time, and retained/FX
  estimate:
  `largeRectangularPreparationStaysWithinTheSharedMemoryBudget`

`PerformanceSubsystemCountersTest` owns dormant product mode, private run-path/symlink/pre-existing
output rejection, concurrent enum aggregation, bounded atomic refresh, checked digest reads, and
changed/oversize/duplicate-row artifact rejection. `PerformanceAnalysisExecutorTest` owns the unique
per-repetition child property, checked scalar projection into semantic measurements, and revalidated
terminal report/artifact rendering. The selected Grid, terrain cache, JavaFX mesh, managed-cache, and
raster-store tests retain their original correctness assertions with instrumentation dormant.

`Map3DDropHitTest.terrainIntersectionWinsOverCameraRay` supplies a deliberately unrelated render UV
and proves exact intersected-scene geography remains authoritative for placement.

`Map3DMemoryTelemetryTest
.mirrorsOneControllerBuildResourcesReplacementAndCloseWithoutPayloadRetention` owns the toolkit-free
per-controller role/Place identity, source/image/GPU/mesh/material estimates, build supersession and
completion, stable replacement rows, scene-application/resource-revision discrimination, and
zero-obligation close release.

Other smallest routes:

- Immutable capture data: `SceneSnapshotsTest.textureAndHeightSnapshotsOwnTheirPrimitiveData`.
- Stale GM scene/cohort tuple:
  `GmRuntime3DCohortTest.mapsOnlyTheDisplayedSceneAndRejectsEveryStaleCohortIdentity`.
- Runtime A→B→A rejection:
  `Map3DViewerRegistryTest.gmEmbeddedSceneFollowsRuntimePlaceAndRejectsStaleConsumerEvents`.
- Embedded Authoring context Place/capture/working/composition rejection:
  `MapEditor3DCanvasTest.contextMenuIdentityRejectsEveryStaleDisplayedSceneDimension`.
- Stamped focus contract, pair identity, exact center, and edge clamp:
  `MapEditor3DCanvasTest.stampedFocusContractAndViewportCenteringPreserveExactPairAndClampEdges`.
- Arena3D terrain single-versus-double selection ownership and pointer-cancel cleanup:
  `Map3DSceneControllerTest.terrainDoubleClickPreservesArenaObjectSelectionWhileSingleClickClears`.
- Shared menu retired-3D-action absence, direct right-double-click targeting, reverse center, and
  stale composition rejection:
  `MapEditorScopeStateTest.rightDoubleClickSynchronizes3DAndContextOmitsRetired3DActions`.

## Runtime objects, Groups, and playback

`MapRuntimeSessionServiceTest`

- Complete graph/token preview, stale snapshot rejection, failed-store rollback, one empty save,
  selection/playback/history cleanup:
  `completeArenaResetRequiresExactSnapshotAndCleansRuntimeOnlyAfterOneSave`
- Checked complete repository multi-selection deletion and unselected preservation:
  `checkedRepositoryMultiDeleteIsAtomicAndPreservesUnselectedDefinitions`
- Physical movement, history, and persistence:
  `physicalMovementUsesPlaceScaleTimeBasisHistoryAndPersistence`
- Generated creature source repair, grid conversion, user zero, history, and reload:
  `generatedCreatureMovementRepairIsStampedUndoableAndPreservesUserZero`
- Exact Created Thing presentation reuse/update, presence creation, history, and compatibility
  reload:
  `importsExactCreatedThingOnceWithSourceAppearanceHistoryAndReload`
- Repository-only Character/Creature import plus stale-checked atomic multi-update:
  `repositoryImportAndCheckedMultiUpdateNeverPlaceCreatedThings`
- Pause, restart, and global playback:
  `globalPlaybackAdvancesEveryPlaceAndGroupUntilExplicitPauseThenRestartsPaused`
- Temporary cohort is one history step:
  `temporaryActiveCohortIsOneHistoryStepAndPreservesPlacedMembers`
- Mixed Group presence:
  `activeGroupCreationPreservesMixedMemberPresence`
- Distinct cohort routes and persistence:
  `cohortDestinationRoutesDistinctMembersWithoutTeleportAndPersistsAsOneCommand`
- Off-map placement slots:
  `initialOffMapCohortPlacementUsesSeparatedSlotsAndOneHistoryCommand`
- Group selection facets:
  `groupSelectionSeparatesSemanticGroupFromDerivedPlaceMembers`

Other smallest routes:

- Real sorted Object Repository tree, exact definition-folder expansion, aggregate confirmation,
  inactive zero-Place deletion, and complete typed dispatch:
  `MapRuntimeComponentsTest.objectRepositoryActivatesSelectionAndRevealsActiveObjectsWithoutPlacement`.
- Platform Delete/forward-Delete mapping, real-window text editing, exact focus, empty/parent no-op,
  popup precedence, and Separate-to-Combined rehost retention: `GmFocusedDeleteSupportTest`.
- Real Active Objects leaf-only multi-selection removal/no folder fallthrough and real Groups/member
  binding/empty-selection ownership:
  `MapRuntimeComponentsTest.activeObjectsRetainsHumanCollapseAndAutoOpensForMixedSelection` and
  `MapRuntimeComponentsTest.groupsTabDoubleClickLoadsOrderedMembersAndPublishesAnUpdate`.

- SQLite normalized+legacy atomic reset and protected Place rows:
  `SqliteArenaObjectStoreTest.completeResetChecksLegacySnapshotAndPreservesMapRows`.
- Real headless preview/execute route through session/store ownership:
  `ProjectStorageCommandTest.arenaResetOperatorUsesTheCheckedSessionBoundary`.
- Protected in-app stale/phrase/backup gates, one isolated reset, postflight integrity, and unchanged
  project rows: `ArenaObjectResetMaintenanceServiceTest`.
- Cancel, Korean-to-English key fallback, stale automatic re-preview, verified-backup text, and
  truthful success/error presentation: `ArenaObjectResetMaintenanceControllerTest`. Its
  `ArenaObjectResetMaintenanceControllerTest.failureContentOffersSemanticMaintenanceHelp` case owns
  retained failure detail and the exact `maintenance.arena-reset` Help-link composition.
- Real top-level development-authority menu registration, exact command label, dispatch, and absence
  of a destructive shortcut: `AppAwareDangerousMaintenanceMenuTest`.

- No paused-time catch-up:
  `ArenaObjectPlaybackClockTest.resetDropsPausedTimeAndBackwardOrHugeJumpsCannotSkipTheStepBound`.
- Active Objects partitions:
  `MapRuntimeComponentsTest.activeObjectsShowsDurableGroupWithHomogeneousPresencePartitions`.
- 2D cohort cancel/stale handling:
  `RuntimeCanvasViewTest.cohortCenterDragPreviewsWithoutMutationCancelsAndRejectsPlaceSwitch`.
- Player excludes cohort control:
  `MapRuntimeComponentsTest.cohortOverlayUsesCanonicalGroupSelectionAndNeverProjectsForPlayer`.
- Encounter identity/default projection and provenance-only repair:
  `MapRuntimeComponentsTest.generatedKoboldWarriorUsesCanonicalKoboldSourceMovement` and
  `MapRuntimeComponentsTest.generatedZeroRepairRequiresProvenanceAndExactRuntimeRevision`.
- Nonlinear Playable footprint, physical dominance, and one-cell person floor:
  `ArenaObjectFootprintTest.playableModeCompressesConfiguredSizeNonlinearlyOnCoarsePlaces`.
- Existing pure-physical draft promotion, active-Place effective preview, and saved command:
  `MapRuntimeComponentsTest.repositoryOnlyObjectLoadsAndSavesSharedEditorValues`.
- Generated SRD tactical size and Playable footprint policy:
  `MapRuntimeComponentsTest.selectedEncounterCandidateCreatesRolledActiveObjects`.
- SRD metadata identity and normalized feature-schema migration:
  `IndexedMetadataServiceTest.queriesNumericCrAndNormalizedFacetIntersectionsAndInspectsActualAccess`
  and `SqliteArenaObjectStoreTest.upgradesFeatureSchemaOneWithoutChangingProjectSchemaFour`.
- Prepared SRD 5.2.1 Equipment table extraction, exact taxonomy/provenance/idempotence, malformed
  diagnostics, and reviewed-source stale rejection:
  `SrdLibraryServiceTest.equipmentDocumentAddsVersionedWeaponAndArmorRowsWithExactTaxonomy`,
  `SrdLibraryServiceTest.malformedEquipmentTableFailsPreviewAndImportWithoutPartialDocument`, and
  `SrdLibraryServiceTest.equipmentImportRejectsSourceChangedAfterReviewedPreview`.
- Prepared Equipment preview counts and stable semantic UI hierarchy/reselection:
  `CreatureDataImportPanelPathTest.reviewedImportSummaryShowsTemplateCountAndAddUpdateDisposition`
  and `UserAssetsWorkspaceTest.equipmentRowsProjectIntoStableSemanticFoldersAcrossReimport`.
- Bounded versioned canonical-model/Icon/diffuse-texture/legacy-Silhouette persistence, version-3
  compatibility, one-time legacy STL rewrite, Token standee separation, and Player privacy:
  `ArenaObjectPresentationPayloadTest`
- Bounded format-neutral binary/ASCII STL adaptation, OBJ negative-index quad admission, indexed
  canonicalization, concave position/texture-coordinate/normal seam preservation, and
  degenerate-model rejection, footprint-height-envelope fitting, and canonical STL-to-OBJ
  round-trip geometry/normal preservation:
  `Model3DImportServiceTest`.
- Exact managed-model preview dispatch sends canonical OBJ metadata only through OBJ admission,
  preserves legacy STL admission, and rejects conflicting/unknown format evidence without guessing:
  `Model3DPreviewAdmissionTest`.
- Selected Item canonical-OBJ realization, pointer-following orbit, current-generation replacement,
  and close release:
  `CharacterStlInspectorPanelTest`.
- Created Thing source/Token/Icon/presentation resolution through the checked exact attached or
  legacy-derived canonical OBJ association revision, accepted diffuse-texture admission,
  corrupt-model standee fallback, plus active 2D/3D render projection:
  `UserAssetsWorkspaceTest.associatedStlReportsCanonicalObjPresentationTruth`,
  `ArenaObjectCreatureImportResolverTest.exactCharacterUsesTokenThenIconAndModelWithoutSelectingSilhouette`,
  `ArenaObject2DRendererTest.importedCharacterIconOverridesTokenTemplateAndSilhouetteInTwoDimensions`,
  `Map3DSceneControllerTest.importedTokenRasterFeedsTransparentCutoutMaterial`,
  `Map3DSceneControllerTest.admittedStlReplacesTokenCutoutAndStoredSilhouetteNeverEntersRenderer`,
  `Map3DSceneControllerTest.admittedCanonicalModelUsesOwnedDiffuseTextureAndKeepsColorFallback`,
  `Map3DSceneControllerTest.reusesLargeAdmittedModelMeshesAcrossStateUpdatesAndReleasesObsoleteBuffers`,
  `Map3DSceneControllerTest.canonicalObjKeepsOneGroundedFitAcrossPreviewAndRuntimeMeshBatches`,
  and `SqliteArenaObjectStoreTest.createdThingSourceStatisticsAndTokenImageRoundTrip`.

## Runtime pinning, Player privacy, Follow, and Spawn

`MapRuntimeSessionServiceTest`

- Atomic GM Control snapshot coherence, exact active-Place filtering, root-first hierarchy and
  visible
  marker/Layer truth, selected-first bounded object/resident results, explicit omissions, defensive
  workspace copies, and changed visibility stamps:
  `gmControlSnapshotAtomicallyCapturesBoundedExactPlaceState`
- Display-safe runtime refresh:
  `displaySafeVersionRefreshesRuntimeWithoutResettingEitherViewport`
- Unchanged large-raster reuse:
  `MapRuntimePresentationProjectorTest.arenaMovementReusesPinnedSnapshotAndStaticLargeRasterProjection`
- Structural reconciliation gate:
  `structuralVersionPinsRuntimeUntilExplicitReconciliation`
- Exact pinned-Place navigation guard and retained parent recovery:
  `gmNavigationRejectsPlacesOutsideThePinnedRuntimeSnapshot`
- Explicit Authoring-to-Control structural reconciliation, exact new-root projection, one resulting
  publication, and missing-target no-op:
  `MapRuntimePresentationProjectorTest.authorToControlTransferReconcilesAndPublishesOnlyTheExactNewRootPlace`
- GM Overview exact parent/footprint reconciliation, current context/revision guards, and no partial
  publication while ordinary pinned navigation remains rejected:
  `MapRuntimePresentationProjectorTest.overviewTransferReconcilesAndSelectsTheExactUnpinnedFootprintPlace`
- Real shared Authoring/Control Parent Overview identity plus 2D/3D double-click presentation, exact
  parent/child targets, captured-footprint resolution, revision rejection, camera preservation,
  single-click inspection, and outside-map rejection:
  `MapEditorParentContextLocalizationTest`,
  `MapEditorScopeStateTest.placeOverviewKeepsFootprintsReadOnlyAndDoubleClickNavigatesSiblings` and
  `MapEditorScopeStateTest.runtimePlaceOverviewDoubleClickMatchesAuthoringChildAndParentNavigation`,
  with `Map3DSceneControllerTest.peakLabelDoubleClickUsesExactCanonicalAnchorAndRejectsDetachedScene`
  owning the shared handled-double-click camera invariant.

`PlayerRuntimeDeliveryBoundaryTest`

- Safe type and text graph:
  `playerEventDataGraphEnumeratesOnlySafeDeliveryTypesAndTextKeys`
- Private event rejection:
  `playerCanvasAndLabelsIgnorePrivateGmEvents`

Browser complete-frame routes:

- Browser 2D+3D transport composite dimensions and pane ownership:
  `PlayerPresentationFrameRendererTest.browserCompositeContainsEqualTwoDimensionalAndThreeDimensionalPanes`
- Bounded live-screen refresh, synthetic-fallback exclusion, and identical-PNG suppression:
  `PlayerPresentationFramePublisherTest.runningEndpointRefreshesTheLivePlayerScreenAndSuppressesIdenticalPngs`
- Exact Control Place replacement advances the complete Local frame:
  `MapRuntimeUiCoordinatorTest.exactPresentedPlaceReplacementAdvancesTheLocalPlayerFrame`
- Late older Place state cannot displace the newest Local frame:
  `PlayerPresentationFramePublisherTest.lateOlderPlaceStateCannotReplaceTheNewestLocalFrame`
- Manager Player-safe preview advances to the newest published Place frame:
  `PlayerPresentationManagerPanelTest.localPreviewAdvancesToTheNewestPublishedPlaceFrame`
- Token/version/cache/SSE lifecycle, stable LAN-only restart/new-link/no-LAN truth, and packaged
  2D/3D/combined selector with bounded page/visibility/online recovery:
  `BrowserPlayerScreenServiceTest`
- Compact GM entry, token-free connected-client summary, and singleton manager routing:
  `MapRuntimeComponentsTest.gmPlayerTopBarUsesOnePrivateManagedPresentationEntry`
- One compact GM presentation row keeps status, connected-player count, and Present action in exact
  left-to-right order on one shared vertical centerline:
  `MapRuntimeComponentsTest.gmPlayerTopBarKeepsPresentationStatusAndActionsInOneOrderedRow`
- Managed Local/Internet lifecycle, listing, invitation, endpoint, privacy, and close-without-stop
  behavior: `PlayerPresentationManagerPanelTest.managesLocalAndInternetWithoutOwningTransportLifetime`
- Players-tab explanatory text tracks the retained viewport and wraps without clipping:
  `PlayerPresentationManagerPanelTest.playersExplanationWrapsWithinTheExistingManagerWidth`
- Same-Place frozen/source terrain fidelity plus Place-scoped Control-camera revision admission:
  `MapRuntimePresentationProjectorTest.playerProjectionReceivesOnlyDomainPermittedImagery`,
  `PlayerRuntime3DLegendCaptureTest.browserPlayerUsesFrozenTerrainFidelityAndPlaceScopedControlCamera`
- Complete listing preflight plus visible safe failure/redaction behavior:
  `PlayerPresentationManagerPanelTest.validatesListingAndShowsOnlySafeActionableRelayFailures`
- Resident manager observation allowlist, action-free exact-tab selection, and guarded restoration:
  `PlayerPresentationManagerPanelTest.residentObservationSelectsOnlyExactTabsWithoutInvokingManagerActions`
- User intervention, lease/window replacement, nonresident skip, and manager-close rejection:
  `PlayerPresentationManagerPanelTest.observationRejectsUserInterventionReplacementAndNonresidentManagers`
- Players readiness requires the installed exact-current Player-safe preview generation:
  `PlayerPresentationManagerPanelTest.playersObservationRequiresTheExactCurrentPlayerSafePreview`
- Relay-only separate 2D/3D publication with monotonic pane versions:
  `PlayerPresentationFramePublisherTest.relayOnlyPresentationPublishesSeparateLatestPlayerSafePanes`
- Exact canonical-LAN QR payload plus token-free visible address projection:
  `BrowserPlayerJoinWindowTest.qrJoinKeepsTheAccessTokenOutOfVisibleAddressText`
- Canonical-LAN default-browser action threading and launch-failure status:
  `PlayerPresentationFramePublisherTest.openUrlUsesPrivateRunningStatusAndReportsLauncherFailure`

Internet relay foundation:

- Required environment configuration and credential redaction:
  `MoondanceServerConfigTest.environmentConfigurationRequiresAndRedactsTheGmCredential`
- Exact bearer admission without visible credential material:
  `GmAuthenticatorTest.acceptsOnlyTheConfiguredBearerCredentialWithoutRenderingIt`
- Public-only metadata projection plus unlisted/private exclusion:
  `RelayGameSessionRegistryTest.publicDirectoryProjectsOnlyIntentionalMetadata`
- Distinct reconnect credential, reconnecting state, resume, and bounded grace expiry:
  `RelayGameSessionRegistryTest.disconnectCanResumeOnlyWithTheDistinctSessionCredential`,
  `RelayGameSessionRegistryTest.reconnectGraceExpiresTheListingAndSession`
- Real HTTP/WebSocket health, pre-upgrade authorization, safe directory publication, protocol
  mismatch rejection, and one split endpoint advancing both panes across a later generation and
  same-cookie resubscription:
  `MoondanceRelayApplicationTest.authenticatedGmPublishesOnlySafePublicGameMetadata`,
  `MoondanceRelayApplicationTest.protocolMajorMismatchFailsClearly`,
  `MoondanceRelayApplicationTest.alreadyOpenSplitEndpointReceivesLaterBothPanesAndResubscribes`
- Shared server/desktop binary contract:
  `RelayBinaryFrameCodecTest.completePaneFrameRoundTripsWithoutJsonOrBase64`
- Real desktop-client authentication, control correlation, invitation/endpoint management, separate
  pane upload, reconnect/resume, bounded supersession, stop cleanup, and redaction:
  `MoondanceRelayClientTest.managesARealRelaySessionAndCoalescesFramesAcrossReconnect`,
  `MoondanceRelayClientTest.rejectsBadAuthenticationWithoutRetainingTheCredential`
- Module-independent invitation expiry decoding and ambiguous-create retry guard:
  `MoondanceRelayClientTest.invitationDecodesWithoutOptionalJavaTimeModule`,
  `MoondanceRelayClientTest.ambiguousInvitationResultBlocksSilentRetryUntilReset`
- Opt-in production HTTPS/WSS proof with an environment-only credential, ephemeral public listing,
  invitation redemption, endpoint mode, both Player-safe panes, disconnect, and complete cleanup:
  `MoondanceRelayDeploymentE2ETest.productionRelayCompletesPlayerSafeInvitationFrameModeAndCleanupFlow`
- Public `/join` token admission, clean redirect, same-origin WebSocket, display-only modes,
  protocol envelope/version mapping, reconnect backoff, one-in-flight plus newest-pending pane
  coalescing, stale-version rejection, canonical WSS-only CSP, and queue close release:
  `websites/moondance-web/tests/player-core.test.mjs` through `node --test`.

`PlayerPresentationBundleTest`

- Follow-live versus Spawn-frozen complete safe content, including admitted mesh changes:
  `followUpdatesCompleteSafeContentWhileSpawnFreezesOneStampedPlace`
- Multiple display-only Follow identities, targeted and broadcast mode/layout delivery, exact
  Place/document joining, stale rejection, source request, and close cleanup:
  `multipleDisplayOnlyFollowsApplyOnlyNewestTargetedOrBroadcastSourceAndCloseCleanly`
- Bundle stamp mismatch:
  `bundleRejectsMismatchedPlaceAndRevisionStamp`
- Pending-projection retained-source guard:
  `MapRuntimePresentationProjectorTest.playerSourceRetainsOnlyExactCurrentPlaceWhileUpdatePending`
- Production multiple-popup route, exact current Place, matching combined 2D/3D content, hidden
  Follow controls, and no implicit browser start:
  `PlayerScreenWindowLauncherTest.combinedFollowWaitsForExactCurrentPlaceAndDoesNotStartBrowser`
- Runtime-only Place capture without an Authoring `TerrainRegion`, including axis-specific extent,
  grid offsets, heights, and revision:
  `PlayerRuntime3DLegendCaptureTest.runtimeOnlyRegionCaptureNeedsNoAuthoringTerrainRegion`
- Shared Control/Player heightmap sampling, domain conversion, endpoint preservation, and bounded
  production detail:
  `Map3DSceneCaptureTest.capturesBoundedTextureAndIndependentEndpointPreservingHeightField`,
  `Map3DSceneCaptureTest.convertsHeightDomainsAndKeepsCapturedSamplesImmutable`,
  `Map3DSceneCaptureTest.productionHeightDetailIsStableAcrossCaptureModesAndMicroGrid`

Other smallest routes:

- GM 3D camera/programmed-view composition, narrow/wide toolbar reflow, and absence of retired local
  Follow/Spawn actions:
  `MapRuntimeComponentsTest.gm3DToolbarComposesProgrammedViewsWithoutLocalFollowOrSpawn`.
- Repeated Control Follow presses retain one command identity while the production launcher opens
  distinct display-only windows with exact current Place and combined content:
  `MapRuntimeComponentsTest.gmControlHeaderIdentifiesScreenAndRoutesBoundedRegionNavigation`,
  `PlayerScreenWindowLauncherTest.combinedFollowWaitsForExactCurrentPlaceAndDoesNotStartBrowser`.
- Spawn scene/camera order:
  `Map3DViewerRegistryTest.sourceSpawnAppliesRetainedCompleteSceneBeforeCameraThenFreezes`.
- Multiple same-Place Authoring Spawns, frozen identity, local camera/bounds, close isolation, and the
  future-size template:
  `Map3DViewerRegistryTest.authoringSourceSpawnsRemainDistinctFrozenWindowsForOnePlace`.
- Follow source versus Spawn pin:
  `Map3DViewerRegistryTest.spawnStaysPinnedWhileFollowViewerTracksAuthoringRegionAndOrientation`.
- Hidden Authoring source lifecycle: a hidden scene releases its FX mesh, ignores state changes, and
  the shown canvas builds one current scene while a Spawn stays frozen:
  `Map3DViewerRegistryTest.hiddenAuthoringSceneReleasesItsMeshAndRebuildsOnlyAfterShowing`.
- Runtime Arena3D hide lifecycle: a hidden GM scene releases its FX scene, ignores state changes,
  and the shown canvas builds one fresh scene:
  `Map3DViewerRegistryTest.hiddenGmSceneReleasesItsMeshAndRebuildsOnlyAfterShowing`.
- First-scene visible loading state: the shared Arena3D host retains the exact rendering message and
  indeterminate activity line until a ready scene clears it, while a visible failure replaces the
  activity without leaving its progress line active:
  `Arena3DSceneHostTest.showsRenderingMessageAndIndeterminateProgressUntilSceneIsReady`.
- Current-scene retained showing/rehost state: a duplicate shown callback on one live Authoring scene
  preserves exact scene identity/count and cannot restore the rendering activity:
  `Map3DViewerRegistryTest.currentAuthoringSceneDoesNotRestoreRenderingActivityOnDuplicateShow`.
- The complete source-Spawn and Follow-source routes also own live Follow versus frozen Spawn Peak
  marker styling.
- Retained presentation stale rejection:
  `ProductMap3DPresentationTest.pendingSpawnReceivesLastCompleteSnapshotAndStaleSourceIsRejected`.
- Content-only refresh camera join plus older/duplicate rejection:
  `ProductMap3DPresentationTest.contentRefreshRetainsAuthoritativeCameraAndRejectsDuplicateOrOlderScene`.

## Authoring Capture Data State

`AuthoringProductDataStateCaptureAdapterTest`

- Exact allowlisted Place/view/visible-Layer projection and same-stamp requested 3D PNG:
  `availableSnapshotKeepsOnlyAllowlistedScalarsAndExactRequested3dEvidence`
- Prompt nonblocking stage, request-kind filtering, unavailable/stale/failure mapping, path-safe
  labels, and coherent Place/work/composition validation:
  `unrequestedEvidenceIsOmittedAndCaptureReturnsWithoutWaitingForOwnerCompletion`,
  `unavailableStaleAndFailedOwnerStatesMapToFixedContractIssues`,
  `visibleLayerProjectionExcludesHiddenTransparentDuplicateAndUnsafeText`, and
  `threeDProjectionRequiresExactPlaceWorkingAndCompositionIdentity`
- Fixed failure with no private error propagation:
  `malformedPngOrOwnerFailureReturnsFixedFailureWithoutLeakingDetails`

`MapEditor3DCanvasTest`

- Exact lossless owned PNG encoding and defensive evidence bytes:
  `evidencePixelsEncodeAsExactOwnedPngWithoutRetainingMutableBytes`

Other smallest routes:

- Retained Authoring presentation/camera scalar pairing and stale rejection:
  `ProductMap3DPresentationTest.captureScalarRequiresExactInternalSceneCameraAndProjectionPair`.
- Shared request filtering, exact-stamp rejection, timeouts, and role privacy remain in
  `ProductDataStateCaptureRegistryTest` and `ProductDataStateCaptureEndpointTest`.

## GM Capture Data State

`GmProductDataStateCaptureAdapterTest`

- Safe visible textual fields and zero visual evidence:
  `capturePublishesOnlyVisibleAllowlistedTextAndNoVisualEvidence`
- Coherent 3D scalar stamp with privacy omission:
  `captureUsesExactCoherentThreeDScalarsButStillEmitsNoBytes`
- Closed view and absent context:
  `captureReportsAbsentViewAndMissingContextTruthfully`
- Interleaved/missing-stamp rejection without mutation:
  `captureRejectsMissingOrInterleavedStampsWithoutMutation`

Other smallest routes:

- Internal presentation/camera pairing and sanitized record shape:
  `ProductMap3DPresentationTest.captureScalarRequiresExactInternalSceneCameraAndProjectionPair`; the
  same test proves the complete immutable GM presentation is available only after that exact coherence
  check.
- Actual GM 2D/3D/combined tab and displayable lifecycle:
  `MapRuntimeUiCoordinatorTest.captureSourceTracksActualGmLayoutAndDisplayableLifecycle`.
- Shared request filtering, stamp rejection, and server/consumer GM `MAP_3D` removal remain in
  `ProductDataStateCaptureRegistryTest`, `ProductDataStateCaptureEndpointTest`, and
  `MapPerformanceComplaintDataStateCaptureTest`.

## GM retained render projection

`GmControlRenderProjectionSourceTest`

- Exact stable Overview/2D/3D component identities, same-state generation reconstruction, 2D viewport,
  defensive PNG payload copy, and explicit unavailable component state:
  `capturesStableThreeComponentManifestAndDefensiveAssets`
- Fresh owner and retained-manifest checks reject old asset handles after owner or projection replacement:
  `rejectsOldAssetsAfterOwnerOrProjectionReplacement`
- Coherent GM scene/camera join, normalized camera revision, terrain extent/Grid, and digest-bound texture
  and height-field payloads:
  `capturesExactThreeDCameraTerrainAndDigestBoundAssets`

The focused boundary carries no Player frame, Asset Manager identity, path, repository, mutable UI/Viewer
object, or mutation authority. Browser mount/update/resize/dispose remains a 3D Viewer consumer proof.

## Shared Navigation Menu and Product Actions

Combined product-shell ownership uses these smallest routes:

- `CombinedProductContentTest.retainsExactViewsAndRoutesSelectorsAndControlTab` proves the managed
  Players, Control, Author, Assets, Factory, and Adventure selector order, accessibility identity,
  exact retained roots, and stable Control-Tab cycle.
- `ApplicationWindowManagerTest.migratesExactLiveRootsWithoutDuplicateProductWindows` proves the real
  Adventure Authoring DTDT root remains exact, displayable, selected, and nonduplicated through managed
  title-bar activation and Separate/Combined round trips alongside the other product roots.
- `ApplicationWindowManagerTest.transientCombinedSelectionRestoresWithoutPersistenceAndPreservesIntervention`
  proves exact-shell readiness, no-preference transient selection, overlap rejection, idempotent restore,
  user-intervention preservation, and stale rejection after shell migration.

Shared combined-map arrangement uses these smallest routes:

- `CombinedMapLayoutControllerTest` proves all six mutually exclusive choices, all four explicit
  combined positions, deterministic legacy mode/orientation migration, accessibility, retained
  pane identity, uniform icon-only sizing/catalog identity, collapse-safe swapping,
  persisted reopen, rehosting, and actual-divider double-click selection of the larger semantic
  standalone surface across both axes, including the 2D tie rule and theme reconstruction.
- `ControlIconCatalogTest.standaloneMapLayoutsUseDistinctMatchingProductionArtwork` proves the
  standalone 2D/3D masters are packaged, visually distinct from each other and the arrangement
  artwork, and resolve through the same semantic catalog at production toolbar size.
- `MapEditor3DTopBarTest.workspaceUsesOneSixChoiceSelectorWithoutVisibleModeTabs` proves Authoring's
  retained DTDT cards have no visible legacy tabs and each choice opens the exact root.
  `DTDTBuildContentSplitterTest.builderKeepsRetainedTabCardsWithoutVisibleLegacyHeaders` proves a
  hidden retained-card owner reserves no tab/header/content-border insets around the selected map.
  `MapEditor3DTopBarTest.workspaceToolbarUsesOrderedFixedSizeControlBlocks` proves the retained
  workspace's exact Navigation/View/history/Cursor/Selection order, three navigation controls, five
  ordinary Selection actions, and fixed standard icon dimensions at wide and narrow widths.
  `MapEditor3DTopBarTest.combinedLayoutControlsAreNotOwnedByThe3DTopBar` proves pane-local 3D chrome
  does not duplicate display ownership, while
  `MapRuntimeComponentsTest.gmToolbarUsesSixChoicesAndPersistsExactCombinedArrangement` proves the
  same selector and persistence in real Control chrome.
- `SpaceMetadataTest.authoringColumnBuildsListOverviewMetadataAndRuntimeFactoryUsesGmOwner` proves
  both shared Overview presentations expose the same six accessible choices and remain narrow-safe;
  the focused Overview scene tests prove standalone/combined activation retains live 3D behavior.
- `MapRuntimeComponentsTest.playerShellKeepsLocalModesAndRestoresSpawnLayoutPerPopup` proves desktop
  Player choice/orientation persistence and Follow-owned control hiding.
- `MapEditor3DTopBarTest.combinedDividerSelectionUsesCanonicalStandaloneWorkspaceTabs` proves the
  Authoring callback selects the stable 2D/3D tab IDs rather than positional indices.

Shared Authoring/Control map control-bar separators use these smallest routes:

- `DTDTSplitterTest.fixedControlBarUsesOneSemanticAffordanceAndLeavesContentResizable` proves fixed
  height, drag suppression, default pointer, one thin affordance, first-side collapse/reopen, and
  retained control/content identities.
- `DTDTBuildContentSplitterTest.builderMapsNewPropertiesAndLegacyThresholdIntoOneConfiguration`
  proves the declarative interaction flag and exact semantic target reach one managed configuration.
- `ProductWindowsStructureTest.authoringAndControlMapBarsOwnOnlyFixedSemanticSeparators` proves the
  eight immediate 2D/3D control-bar pairs own the only non-draggable semantic dividers, every local
  bar is 52 pixels high, the four Combined bars declare their exact friendly drawer captions, and all
  four outer docks retain ordinary resize ownership.
- `ApplicationWindowManagerTest.migratesExactLiveRootsWithoutDuplicateProductWindows` proves all four
  real Authoring/Control Combined drawers expose friendly accessible captions while collapse/restore
  preserves the exact internal control names and component instances.
- Existing draggable grip/cursor and combined semantic-divider preservation remain covered by
  `DTDTSplitterTest.dividerUsesResizeCursorForOrientationAndShowsGrip`,
  `DTDTSplitterTest.configuredDoubleClickSurvivesThemeRefreshAndTogglesOppositeSides`, and
  `CombinedMapLayoutControllerTest`.

`AppAwareMenuBarServiceTest`

- Exact Navigation menu order, platform menu accelerators, shared menu/root action identity, and
  rebuild replacement without duplicate dispatch:
  `navigationMenuSharesPlatformShortcutActionsWithTheProductRoot`.
- Exact Command-L/Command-P/Command-F/Command-M menu targets and typed Assets requests:
  `workspaceNavigationMenuUsesExactPlatformShortcutsAndTypedAssetsRequests`.
`AppWideWorkspaceNavigationServiceTest`

- Exact platform-key filtering, one product activation, semantic Authoring targets, and typed Assets
  Files/Media requests: all focused methods in this class.
`MapEditorScopeStateTest`

- Semantic Layers/Place inspector selection without tab-index ownership:
  `layersInspectorSeparatesSourcesAndSelectsSemanticTabs`.
`GmControlNavigationActionsTest`

- Canonical Grid/Regions visibility targets and absent-Place no-op:
  `gridAndRegionsActionsPublishTheirExplicitVisibilityTargets`.
- Embedded Play Layers and shared Navigation routing to one exact Background Layers checkbox, including
  no header popup, no mutation merely from revealing, Play tab activation, and exact-ID dispatch:
  `visibleLayersUseEmbeddedPlaySurfaceAndNavigationReveal`.

`MapRuntimeSessionServiceTest`

- Initial Authoring visibility inheritance, explicit custom all-off independence, and durable
  active-Place/custom visibility restore:
  `initialLayerVisibilityCopiesAuthoringButExplicitAllOffRemainsIndependent` and
  `gmPlaceAndLayerModeSurviveRestartWithoutChangingAuthorOrPlayerState`.
- Per-Place Play overlay memory:
  `playLayerVisibilityIsRememberedIndependentlyForEachPlace`.

`UserPrefsGmRuntimeStateStoreTest`

- Play overlay schema upgrade and exact round-trip: all focused methods in this class.

`MapRuntimePresentationProjectorTest`

- Real visibility-command projection without Arena mutation:
  `playLayerRouteRemovesRenderingAndHitOwnershipOnlyForTheExactPlace`.

`MapRuntimeComponentsTest`

- Control 2D render/hit removal:
  `controlCanvasPlayLayerRemovesObjectsFromRenderingAndHitInput`.

## Universal VTT Phase 1 preview

- `MapRasterDecoderTest` owns practical PNG/JPEG/GIF/BMP/WebP/TIFF availability, bounded
  subsampling, detected format/MIME, and precise unknown-signature failure.
- `UniversalVttReaderTest` owns format-independent canonical normalization of image, logical
  resolution, LOS/object-LOS paths, portals, lights, environment, unknown fields, diagnostics,
  fractional coordinates, WebP/TIFF embedded image decode, bounded image/unknown-value memory,
  cancellation, and required-field failure.
- `UniversalVttImportPanelTest` owns transient-only publication, factual counts, independent layer
  controls, disabled empty layers, latest-request-wins worker behavior, and the absence of
  database/Place-owned fields. It also proves that passive telemetry exposes the decoded-preview
  estimate and canonical geometry counts, then releases them on close.
- `NativeFileChooserTest.universalVttChooserAcceptsOnlyDd2vttFileNames` and
  `NativeFileChooserTest.universalVttSwingFallbackSelectsOneDd2vttFileAndUsesTalismanTheme` own
  the single-file chooser boundary.
- `CreatureAssetManagerStructureTest.declaresTypesAndCreatedThingsWithoutDuplicatingFirstClassFactory`
  owns the DTDT `VTT Import` tab and the absence of a duplicate Assets-side Factory component.
- `UserAssetsWorkspaceTest`'s real Asset Manager composition route owns the seven-tab order,
  construction of `UniversalVttImportPanel`, and unchanged non-expanding acquisition splitter
  behavior.

## Universal VTT Phase 2 mapping

- `CanonicalToPersistentMapMapperTest` owns pure canonical-to-persistent physical scaling,
  FelderHouse-equivalent 59/46/14 Feature counts, defaults, exact Source bindings, conservative portal
  association, embedded-artwork Image Overlay classification, diagnostic propagation, duplicate-point
  normalization, and per-item skip of unusable geometry. It performs no project persistence or Swing
  work.
- `PersistentMapImportServiceTest` owns exact original/image Source admission, one revision-checked
  Place/visual-overlay/constructed-Feature commit with neutral Background and unchanged unrelated
  Place data, exact WebP byte/format preservation, partial accepted-subset commit, durable reload,
  stale rejection, and document-store rollback without partial Place or Source references.
- `UniversalVttImportPanelTest` owns reviewed-import cancel, off-EDT progress, exact revision capture,
  retained-preview failure/retry, diagnostic-bearing partial import, committed success, and exact
  imported-Place action enablement.
- `MapEditorScopeStateTest.selectedFileSourceAddsRenderedOverlayAndShowsPixelMetadata` owns the
  independent image-file route's exact file provenance, Image Overlay classification, and unchanged
  neutral Background.

## User Assets

- `AssetsFactoryObservationSessionTest` owns exact resident window/session epochs, exact-ID and
  expected-revision selection without lookalikes, all bounded readiness states, preference-free
  owner-allowlisted Assets tabs, opaque two-part restore preflight, deliberate-user/stale guards,
  toolkit-neutral public DTOs, and the capability-minimal exact-ready Viewer Showcase-orbit handle.
- `UserAssetsWorkspaceTest.fileImportCommandRunsIngestionOffTheEdt` owns immutable import-request
  capture, truthful busy state, responsive command return, and worker-only heavy ingestion.
- `UserAssetsWorkspaceTest.committedAssociationPatchesMediaLabelWithoutRebuildingTree` owns the
  exact association write-through projection, indexed Media-label event, and changed-row width check
  without a full tree rebuild or full-width scan.
- `UserAssetsWorkspaceTest.managedAttachProjectsExactRoleDeltaWithoutReloadingOwner` owns checked
  transaction-delta projection into the selected owner with no owner-wide attachment reread.
- `UserAssetsWorkspaceTest.committedAttachRefreshesSelectedCharacterWithoutStealingLaterSelection`
  owns the exact Created Thing subtree patch, no unrelated Created Things rebuild, and later-selection
  preservation.
- `UserAssetsWorkspaceTest.generativeContextTracksExactTargetSummaryRoleAndCommand` also owns the
  exact Type attachment subtree patch without a full Types rebuild.

- `FactoryWallGeneratorTest` owns deterministic physical dimensions, version-1 compatibility,
  version-2 orientation and door/window profile extrusion, segmentation without internal caps,
  indexed topology/normals/manifold/no-degenerate validation, in-range physical-repeat atlas UVs,
  material-version recipe identity, and canonical OBJ stability.
- `FactorySurfaceGeneratorTest` owns exact Floor/Ceiling semantic identity, X/Z dimensions and
  Y thickness, optional square-grid multiple admission, bounded rectangular through-openings,
  grid/opening profile extrusion without internal caps, closed topology/normals/no-degenerate
  validation, common physical-repeat UV phase, and deterministic candidate/derivative identity.
- `FactoryFurnitureGeneratorTest` owns exact Rectangular Table dimensions/units, centered and grounded
  tabletop plus four stable straight-leg identities, preserved version-1 OBJ/recipe hashes, and the
  version-2 Round Table faceted-cylinder top, bounded segment count, deterministic outward leg splay,
  five independently closed outward-wound shells, no-degenerate validation, physical-repeat UVs,
  deterministic OBJ/STL identity, path-free Dark Walnut MTL, and exact managed-texture override.
- `ObjectFactoryNativeFormCompilerTest` owns OF-01 canonical-foot/display-unit truth; stable Space Node,
  Part, Surface Region, Material Region, and island identities; zero-to-many attachment; inherited TRS and
  inverse-transpose normals; five closed deterministic arm shells; rear seam and fixed integer UV packing;
  canonical OBJ/MTL/PNG/`regions.json` bytes; equal-input repeatability; dimension-only topology/atlas
  stability; invalid-input rejection; and the checker-textured arm beside its flattened atlas plate.
- `ObjectFactoryRigForwardKinematicsTest` owns OF-02 exact Form-qualified Rig nodes, rigid shell bindings,
  stable Controls/Sites/reference segments, complete channel policies and constraints, rest-pose identity,
  parent-first affine inheritance, locked/derived rejection, bounded translation/positive-scale admission,
  canonical complete Pose byte round trips, immutable Skeleton frames, deterministic FK, and posed mesh/OBJ
  derivative truth without topology, UV, region, material, or Appearance changes.
- `ObjectFactoryBodyFormSessionTest` owns OF-03 typed transient command authority, deterministic OF-01
  recompilation and OF-02 requalification, stable topology/UV semantics across continuous edits,
  Front/Back depth preservation, independent defensive reference bytes/registration, whole-draft
  undo/redo, explicit keep-world/keep-local reparenting, atomic rejection, and close-time release.
- `ObjectFactoryWorkspaceStateTest` owns NJOF-02's complete immutable five-surface defaults, deterministic
  close-focus repair, empty/reopen truth, no focus on a closed surface, revision advance, defensive open
  membership, and invalid-snapshot rejection.
- `ObjectFactoryNativeWorkspacePanelTest` owns NJOF-02's retained exact component identity and mutable local
  state across close/reopen, stable accessible disclosure controls and Box identities, complete empty truth,
  duplicate component rejection, state-listener projection, and idempotent final close.
- `ObjectFactoryBodyFormEditorPanelTest` owns the retained Views/Morph/Shader/Puppeteer/Object composition at
  its expected minimum width, paired Morph Build/Skeleton route, bounded action groups and controls,
  byte-preserving reference survival across workspace/presentation changes, no premature Save affordance,
  panel/session presentation release, and the exact production Factory composition's separate
  `Factory Objects` and `Body Form` modes. Its real
  chooser/worker cases own exact PNG/JPEG bytes/digests, metadata-first edge/total-pixel rejection,
  unsupported/corrupt rejection, stable immediate busy/duplicate-disable truth, monotonic supersession,
  and successful late completion rejection after close with no reference mutation.
- `ObjectFactoryAppearanceSessionTest` owns OF-04 deterministic orthographic projection, explicit hidden/
  rear/off-image unresolved coverage, semantic-region-only propagation totals, compact source/confidence
  evidence, reciprocal atlas/model triangle and region identity, exact compatible PNG byte retention,
  candidate-change invalidation, and close rejection without changing Form/Rig authority.
- `ObjectFactoryAppearancePanelTest` owns the grouped Skin composition with textured Form and flattened
  atlas together, immediate stable projection feedback and duplicate disablement, exact metadata-first
  1024-square PNG intake, and the rendered OF-04 inspection plate at the expected width.
- `FactoryNativePackageServiceTest` owns OF-05A deterministic complete-component capture/reload,
  missing/tampered fail-closed validation, exact append-only Save, stale rejection, distinct Save As source
  provenance, listing, pre-commit candidate rejection, and full rollback without partial assets or rows.
- `FactoryNativePackageOperationTest` owns Native Save New through the existing AppServices Factory
  operation receipt/idempotency/terminal route and exact post-commit package reconstruction.
- `FactoryBodyFormApplicationServerGatewayTest` owns the delegated host-to-Factory Save and Save As seam:
  exact existing/selected-parent guards, stale rejection, asynchronous operation truth, and complete
  package reload against an isolated database without browser recovery or a managed-server process.
- `FactoryBodyFormCaptureGatewayTest` owns the synchronous Factory session-to-transfer prerequisite:
  complete semantic graph application, Java-compiled component/hash integrity, exact Save/Save-As Object
  identity, currentness, idempotency/correlation replay, motion retention for an unchanged Form, and
  missing/foreign/stale/incomplete/closed rejection without a browser, server, database, or provider.
- `ObjectFactoryBodyFormEditorPanelTest` additionally owns immediate Native Save busy truth, duplicate
  disablement, committed identity installation, and late durable-completion rejection after close.
- `ObjectFactoryPoseAuthoringTest` owns OF-06 reachable/unreachable/limit-clamped deterministic IK,
  no-stretch topology/UV preservation, exact six-role/Icon/Front admission, waiting without inference,
  repeatable partial/full-deck candidate evidence, transient review/history, stale Save rejection, complete
  Pose acceptance, and optional Native Package Pose round trip.
- `ObjectFactoryPosePanelTest` owns the fifth grouped Pose composition, fixed deck/manual fallback,
  immediate matcher and Save Pose feedback, duplicate disablement, session/generation/close rejection,
  exact loaded-revision Native Save routing, and the expected-width rendered OF-06 review plate.
- `ObjectFactoryPoseFieldTest` owns OF-07 deterministic one/two/collinear/Delaunay topology, exact/shared-
  edge/hull evaluation, stable cocircular and serialization-order fixtures, sign-invariant quaternion
  log/exp charts, translation/scale interpolation, canonical bytes, invalid geometry, and transient
  session revision/history/close behavior.
- `ObjectFactoryPuppeteerPanelTest` owns the sixth grouped Teach/Perform composition, complete-Pose dots,
  normalized visible weights, OF-08A Record/replay/trim/loop/bake composition, immediate Save feedback,
  duplicate/close guards, live articulated preview, and the expected-width rendered OF-08A review plate.
- `ObjectFactoryPerformancePathTest` owns OF-08A canonical 60-tick resampling, ceiling/hold/same-time rules,
  frame-rate-independent playback, integer trim/split, old-Field pinning, C0 evidence, full parent-local TRS
  Solved Clip bake/reload, and requested error-threshold rejection.
- `ObjectFactoryTemplateEngineTest` owns OF-09A immutable parameter normalization, equal-input bytes, the
  complete 16-node/21-Part segmented biped and exact Rig/Atlas graph, standard/broad/slender compatibility
  signatures, bounded per-region density drift, recomputed sites, explicit structural revision evolution,
  cardinality-neutral reduced-form compilation, rest-FK identity, and the deterministic biped/atlas plate.
- `ObjectFactoryCreatureTemplateLibraryTest` owns OF-09B equal-input quadruped/dragon family compilation,
  complete semantic Form/Rig/Atlas graphs, compatible dimension signatures and bounded density, explicit
  Template-plus-Atlas structural evolution, several Appearance PNGs over one unchanged exact dragon
  Form/Rig/Pose/Behavior signature, and `build/of-09b/basic-dragon-template-review.png`.
- `ObjectFactoryAppearanceGenerationSessionTest` owns OF-10 deterministic reviewed request fingerprints,
  exact input roles, no implicit transport, metadata-first PNG/dimension/model validation, transparent-
  coverage rejection, four-pixel region-safe dilation, cancel/stale/reject preservation, exact-revision
  acceptance, and provenance reconstruction.
- `ObjectFactoryAppearanceGeneratorPanelTest` owns grouped request/provider/cost/preview/decision layout,
  review-only silence, immediate stable Generate feedback, fake-provider preview/accept/reject, close-time
  late-result rejection, and `build/of-10/appearance-generator-review.png`.
- `FactoryNativePackageServiceTest` additionally owns one isolated OF-10 generated-Appearance provenance
  round trip through the existing checked Native Package transaction.
- `ObjectFactoryImportedModelStagingServiceTest` owns OF-P01A inspected-format/extension/path rejection,
  exact source/companion byte retention, canonical-feet normalization, capability/loss inventory, explicit
  collapse versus planarization/faceting metrics, topology-dependent UV regeneration, closed native mesh,
  normalization-aware length-framed identity, deterministic artifacts, complete unsigned receipt, and
  equal-input byte stability.
- `ObjectFactoryImportPanelTest` owns immediate stable Build Candidate feedback, duplicate disablement,
  current-generation adoption, changed-source and close rejection, transient release, disabled OF-P01B
  acceptance, third-mode Factory composition, grouped source/candidate review, and
  `build/of-p01a/import-convert-review.png`.
- `ObjectFactoryBehaviorComposerTest` owns OF-08B canonical Behavior round-trip, exact source/projector/Rig
  pins, inclusive zero/finite/indefinite interval rules, C0 repeat admission, masks/unmasked invariance,
  fractional override/additive TRS, q/-q and exact-pi stability, unique order/root rejection, visible
  conflicts, final limits, bounded step plus upper-body wave, and short whole-body dance.
- `ObjectFactoryBehaviorMixerPanelTest` owns the grouped library/live-preview/ordered-Layer/mask/
  compatibility/conflict projection, immutable edit revision, immediate exact-save feedback, duplicate and
  close guards, and `build/of-08b/biped-behavior-mixer-review.png`.
- `ObjectFactoryRuntimePublicationTest` owns OF-11 exact Character/package/Behavior publication,
  deterministic caller-clocked start/progress/end Pose and posed-OBJ outcomes, unchanged static OBJ/diffuse
  compatibility, four read-only consumer adapters, visible stale/missing/incompatible rejection, and
  bounded tick admission.
- `FactoryRuntimePublicationServiceTest` owns the exact-current durable Native Package read adapter plus
  stale and missing Character rejection without persistence mutation.
- `ObjectFactoryRuntimePublicationPanelTest` owns the grouped Character/Behavior/target/capability/result
  projection, all four consumer targets, close release, and
  `build/of-11/runtime-publication-review.png`.
- `ObjectFactoryLizardMappingBodyTypeTest` owns OF-12A equal-input Lizard Form/Atlas identity, Giant
  Crocodile plus second-family reuse, exact six camera bases and head-up/near-side layouts, strict 2D/3D
  edit separation, direct one-time opposite seeding, exact-Creature/current-Front settled-deck admission,
  waiting/mixed-ancestry rejection, and byte-equal local bake with exact direct, blended, unresolved,
  source-role, asset/pixel digest, confidence, and provenance evidence.
- `SrdMonsterSixViewTextureMappingResourceTest` owns the bounded browser proving-ground contract: independent
  image-observation and geometry point maps, envelope-derived square registration without a hidden inset or
  arbitrary outline enlargement, object-relative horizontal orbit in all three 3D surfaces, and direct
  confirmation-free activation for Copy Opposite, mapping-default save/reset, and selected-image remake.
  OF-SHADER-CAGE-50 adds the registered-image canvas and observed-landmark handle projection, exact Reset and
  source/view recovery boundaries, changed-view paint scheduling, Object sampler connection, and explicit
  absence of a new provider, transport, persistence, or accepted-media authority.
- The same resource selector owns OF-UI-01's workbench and OF-UI-ACTIVATE-05/06's four independent
  Morph/Shader/Object/Puppeteer Boxes, toggle-open/reorder controls, exact item/Box order, retained nodes,
  the sole Object-owned switchable surface/control-point 3D workspace with no Puppeteer viewport,
  threshold/cancel/insertion/keyboard behavior, exact loaded-image Outline, projected Form cylinders and
  ovoids, ten stable generated morphotype cards,
  lineage/revision/control/stance
  disclosure, versioned Morph Form/Skeleton metadata, generated ovoid/cylinder covers, bounded proportion
  and stance intents, manufacture-before-texture truth, independent Outline/Projected rig controls with no
  composite Paint Guide, stable
  `data-of-*` translation identities, preserved six-view Shader behavior, and explicit absence of automatic
  235-Creature mutation.
- `shelf-contract-v1.test.mjs` owns frozen historical v1 vocabulary and input compatibility.
- `shelf-contract-v2.test.mjs` owns OF-UI-DTDT-18's explicit successor identity, fail-closed dispatch,
  policy, content, bounded-child, cycle, and fingerprint validation.
- `object-factory-surface.test.mjs` owns fail-closed four-item Shelf order normalization, deterministic
  earlier/later movement and close/open toggling, triangle-density stability, independent
  surface/Wireframe state, distinct Arachnid standing/mapping foot layout, and the immutable natural splayed
  Mapping projection with readable appendage chains for representative winged quadruped and upright forms.
  OF-UI-MAPPING-18 adds canonical X-across/Y-up/Z-depth projection, exact opposing six-face camera presets,
  unknown-face rejection, and source-bar removal of the redundant heading/current badge.
  OF-UI-COPY-12 adds non-symmetric Front/Back shoulder, hip, front-foot, and rear-foot semantic/screen-side
  proof plus immutable source-preserving round trip. OF-UI-SHELF-14 adds exact fixed-order nested toggles,
  complete combined-envelope admission, and missing/cross-level/stale/duplicate child rejection.
  OF-UI-DTDT-18 adds the checked v2 Object definition, root reorder/twist policy, nested fixed groups,
  declared label/group content, and absence of duplicate top Close controls without redefining v1.
  OF-UI-BODY-FORM-31 proves the direct Shader order introduced in v13 and retained by the v14 contract
  migration, absence of a Type label/group and Body Form child group, direct Body Form
  compendium/current-Morph composition, complete direct-state admission, removal of
  nested Type styling/mounting, and exact bidirectional mirror-pair synchronization without moving an
  unpaired jaw control. It also proves paired segment taper and profile geometry copy while the unpaired jaw
  profile remains unchanged. Resource checks place the Proportions disclosure immediately before Fit, mount
  its region left of the 2D viewport, group segment width/depth endpoints, explain outline fitting, and prove
  proportion recompilation does not request an Object camera fit.
  OF-UI-INTERACTION-32 proves bounded same-segment repeated-press recognition, different/late/reversed-time
  rejection, insertion before ordinary selection, forced committed-Stance rendering, explicit Object Fit,
  unnamed rotated-camera acceptance, complete vertical stance fitting, and a baseline derived from
  projected world zero. It also proves a web-panel triangle maps to one stable panel identity, its exact
  defining controls exclude the other finger panels, descendant traversal is disabled for panel drag,
  thickness mirrors bilaterally, and tessellation emits separated front/back faces with closed edges.
  Resource checks retain a bounded 50-entry Undo path and distinguish browser recovery from database Save.
  OF-UI-TABLET-33 adds resource proof that every peer Shelf projection publishes its open count, tablet CSS
  overrides desktop inline columns, one open peer fills the row, and an odd third peer spans the next row;
  the obsolete fixed named tablet grid is absent.
  OF-UI-BODY-FORM-39 adds pure proof that every open-peer subset is normalized to one complete row, plus
  resource evidence that the stable Object host survives creation into input installation, Body Form's
  chooser stays outside the sole nested catalog scrollport, and Morph Views cannot be covered by it.
  OF-LINKED-VIEWS-27 adds resource proof that Mapping bypasses stored stance snapshots, every shared edit
  uses immediate Object invalidation, Object offers its stance-aware control overlay, and 2D cross-sections
  have an independently named display switch.
  OF-WING-SEAM-29 adds exact normalized group-drag bounds, fixed terminal webbing inset under extreme
  bilateral finger-three edits, post-edit camera-fit invalidation, and zoom below the former clipping floor.
  OF-UI-WHEEL-35 adds bounded wheel-zoom direction and limit proof plus resource evidence that the stable
  Object workspace owns one non-passive capture listener while both replaceable SVG listeners are absent.
  OF-UI-MORPH-37 adds checked v15 Shelf declaration and persistence admission for Morph before Shader,
  independent fixed Morph and Shader child groups, synchronized view controls, neutral-reference-only Morph
  image loading, accepted-Creature-only texture sampling, and shared high-contrast 2D/Object mesh selection.
  OF-MORPH-IDENTITY-38 proves the Adult Dragon catalog identity and its Dragon parent, retains bounded legacy
  Ancient Dragon exchange admission through explicit normalization, and checks that Apply is described as a
  Creature assignment while browser recovery is not presented as database persistence.
  OF-SHADER-CAGE-50 adds immutable-source normalization, deterministic silhouette first fit and triangulation,
  confidence/unresolved admission, bounded non-inverting local offsets, exact reset, stale source/view
  rejection, Shader/Object inverse-sampler parity, narrow cache invalidation, and interaction disposal proof.
  OF-OBJECT-GPU-51 additionally proves all six named view bases agree with the mapping projection/depth
  contract, interactive retained rendering bypasses CPU projection/sort/paint, settled annotation projection
  remains available, Canvas2D fallback is exact, and the Object resource contains no Animation Shelf or
  playback controls while Morph motion still drives Object pose.
- `object-surface-webgl.test.mjs` owns the retained graphics boundary: exact no-duplicate partition into no
  more than six canonical source batches plus unmapped, claim/UV stability, camera-uniform-only and
  pose-position-only upload plans, per-source opacity, unmapped/card colors, unavailable/context-loss fallback,
  restoration eligibility, and buffer/texture disposal. It is pure JavaScript and creates no WebGL, server,
  provider, database, or persistent side effect outside its fakes.
- `ObjectFactoryApplicationServerBundleProposalTest` owns the superseded-proposal marker, canonical Creature
  page ID and route, exact owner bundle/dependency digests, six-Token-role parity fixture, bounded semantic
  capability needs, path/token exclusion, hot-refresh expectation, and absent cutover authority.
- `ObjectFactoryHostedCritterResourceTest` owns OF-UI-MIGRATE-02's separate Gallery/Creature entries and
  OF-UI-ACTIVATE-06's root `SINGLE_CURRENT`/`SELECT_OR_FOCUS` projection, route-sized labeled Boxes, useful
  empty-Creature Gallery path, exact session-retained Creature return route, independent Object Surface
  visibility, disabled New/Save, and absent browser draft/save authority. It also owns
  Morph-Form-first Gallery, compact collection/card/subdeck operation truth, typed bootstrap/opaque media,
  one all-current progress join, stale/close guards, unavailable mutation, and absence of legacy
  endpoints or browser authority.
- `critter-hosted-state.test.mjs` owns receiver-safe default browser bootstrap/read transport, deterministic
  injected transport, pure exact-stamp normalization/revalidation, bounded card projection, operation
  state, bounded Creature-route retention and malformed-value rejection, opaque media URLs, private-CSRF
  exact generation gating/POST/result validation, unavailable
  cancellation, stale result rejection, and close-only abort. Its generation cases are fake-fetch only and
  perform no live admission.
- `FactoryNativePackageServiceTest` additionally owns one isolated OF-08A Field/motion-Pose/Path/Clip
  same-store save/reload case, exact OF-08B arm Behavior membership/reload, full standard-biped
  Clip/Pose/Behavior save/reload/recomposition, missing-source rejection, and missing-authoritative-Path
  fail-closed rejection.
- `ObjectFactoryContextCaptureTest` owns CM-07's scalar-only public record boundary, current
  Object/native-package/model/Form/session/Rig/rest-Pose truth, ordered bounded selection, distinct stamped
  invocation target, semantic ancestry/transform facts, explicit later-capability absence, and hidden/
  closed/unknown/stale rejection without Factory mutation.
- `FactoryStarterMaterialCatalogTest` owns the strict versioned offline manifest, eight stable Poly
  Haven CC0 material identities, family defaults, physical repeat dimensions, reviewed
  Diffuse/OpenGL-Normal/Roughness-or-ARM inventory, exact size/MD5/SHA-256/JPEG verification,
  defensive bytes, and no-network loading boundary.
- `CanonicalModel3DStlWriterTest` owns deterministic geometry-only STL output, coordinate/winding
  inversion, readmission geometry equality, digest stability, and no material claim.
- `FactoryModelExportServiceTest` owns exact-candidate reviewed OBJ-package/STL export, canonical
  directory and per-file fingerprint review, explicit collision replacement, late-file no-overwrite,
  staged commit, stale/interruption/failure rollback, visible retained-backup recovery, defensive
  bytes, and safe relative filenames.
- `FactoryModelAdmissionServiceTest` owns destination-reviewed persistence, exact
  Wall/Floor/Ceiling/
  Rectangular Table/Round Table kind truth, exact material/texture provenance,
  recipe/material/geometry-only-STL
  derivations, no second managed STL, exact-byte reuse with distinct recipe identity, Wall
  compatibility, stale checks, and transaction rollback.
  `FactoryModelAdmissionServiceTest.rectangularTableRetainsFurnitureKindRecipeAndReusableAssetIdentity`
  owns exact furniture recipe/source/managed metadata plus repeat-Accept reuse.
  `FactoryModelAdmissionServiceTest.roundTablePersistsVersionedDarkWalnutWithoutFalseTextureProvenance`
  owns version-2 Round Table reuse, path-free MTL derivation, uniform-color metadata, no false texture
  provenance, and missing-material rollback.
  `FactoryModelAdmissionServiceTest.roundTableManagedTextureAcceptRetainsExactSourceAndDerivedProvenance`
  owns managed-texture Round Table Accept/reuse, exact source revision/provenance, derived diffuse
  lineage, no false uniform-color claim, and no automatic association.
  `FactoryModelAdmissionServiceTest.roundTableStaleAndInjectedFailureRollBackEveryCandidateRow`
  owns late-candidate rejection and forced transactional rollback without changing the exact source
  texture or provenance.
  `FactoryModelAdmissionServiceTest.starterMaterialAdmissionIsAtomicReusableAndRetainsEverySourceMap`
  owns atomic/reusable starter material admission, every map's immutable content/project-asset/source
  provenance, Diffuse-only visible User Asset/material derivation, exact retry, stale rejection, and
  no partial map/model graph.
  `FactoryModelAdmissionServiceTest.reviewedDestinationsReuseExactAssetAndMembershipIdempotently`
  owns stable Objects/Models collection definitions, exact membership, byte/asset reuse, and
  idempotence.
  `FactoryModelAdmissionServiceTest.staleOrFailedDestinationAdmissionRollsBackAssetAndMembership`
  owns atomic rollback.
- `CreatureAssetManagerStructureTest` owns removal of the Factory acquisition tab plus the exact
  first-class Factory product-root declaration and scoped wrapper kind.
- `UserAssetsWorkspaceTest.factoryWallPreviewIsNonPersistentAndAttachPreservesSelection` owns bounded
  controls, non-persistent live preview, valid-only reviewed attachment, no automatic navigation,
  explicit Reveal, and deferred-editor truth.
- The retired Factory-tab real-composition selector has been superseded by
  `FactoryWorkspacePanelTest`, which owns no-pre-confirmation persistence and the current first-class
  composition.
- `FactoryObjectOperationServiceTest` owns the one UI/headless service boundary, exact Object/asset
  references, serial concurrency, same-fingerprint replay, changed-fingerprint conflict, pre-commit
  cancellation, terminal reconstruction, stale rollback, post-commit projection truth, and registry
  privacy. It also distinguishes true `NO_CHANGE` from committed membership repair and proves this
  family never reports a partial effect.
- `FactoryWorkspacePanelTest.saveAndSaveAsUseExactOperationServiceAndPostSaveSelection` owns the real
  panel adapter, exact operation submission, and guarded post-save projection/selection. The panel
  retains candidate/name review and presentation state but no longer invokes the library mutation.
- `FactoryObjectLibraryServiceTest` retains the durable transaction proof for Save New/exact Save/Save
  As, legacy adoption, membership repair, and stale rollback; the operation service does not replace
  those authorities.
- `UserAssetsWorkspaceTest.factoryWallAttachReviewsDestinationAndCancelPreservesPreview` owns the
  Objects default, Models alternative, cancel no-op, and exact destination command.
- `UserAssetsWorkspaceTest.committedFactoryAssetPatchesObjectsCollectionAndReloadsExactLeaf`
  owns exact Objects projection, retained Media selection, no full tree rebuild, and canonical
  collection/asset reconstruction after reload.
- `UserAssetsWorkspaceTest.committedFactoryDeltaSurvivesOlderWorkspaceSnapshot` owns replay of an
  exact post-commit asset/collection delta over an older in-flight full workspace snapshot.
- `UserAssetsWorkspaceTest.factoryWallManagedTexturePreviewsAndAcceptsWithExactProvenance` owns the
  exact managed-image selection, textured Viewer frame, and persisted source identity route.
- `UserAssetsWorkspaceTest.factoryWallCommittedWarningRemainsAttached` owns durable-success truth
  when the later in-memory Media projection fails.
- `UserAssetsWorkspaceTest.factoryWallPreviewStaysBusyUntilItsWorkerCompletes` owns worker busy-state
  and draft-control restoration.
- `UserAssetsWorkspaceTest.factoryWallOpeningsOrientationAndExportsStayBoundToExactPreview` owns
  version-2 orientation/opening controls, exact-preview OBJ/STL export, export-only non-persistence,
  cancellation, draft invalidation, and subsequent checked Accept.
- `UserAssetsWorkspaceTest.factoryWallLateExportCannotRestoreAnInvalidatedPreview` owns generation
  invalidation while a filesystem export is in flight and rejection of its late completion.
- `UserAssetsWorkspaceTest.factorySurfacePreviewUsesCompatibleGridAndExactOpenings` owns bounded
  Floor/Ceiling controls, exact square-grid inputs, one bounded opening, shared Viewer presentation,
  export-only non-persistence, OBJ/STL output, kind-change invalidation, checked Ceiling Accept, and
  exact managed-OBJ Media reveal with kind metadata.
- `UserAssetsWorkspaceTest.factoryRectangularTablePreviewsExportsAndAcceptsThroughSharedModelAuthority`
  owns bounded table/leg/material controls, shared Viewer presentation, OBJ/STL export-only
  non-persistence, exact current candidate guards, checked Rectangular Table Accept, no automatic
  attachment, and exact managed-OBJ Media reveal with furniture metadata.
- `UserAssetsWorkspaceTest.factoryRoundTablePreviewsExportsAndAcceptsThroughSharedModelAuthority` owns
  bounded diameter/facet/splay controls, Dark Walnut Viewer truth, OBJ+MTL versus geometry-only STL
  export, preview-only non-persistence, checked Round Table Accept, no automatic attachment, and exact
  managed-OBJ reveal with recreation metadata.
- `UserAssetsWorkspaceTest.factoryRoundTableRejectsInvalidSegmentsAndIgnoresLatePreview` owns the
  integer-only radial-segment control, local invalid-segment rejection, and exact generation guard
  that prevents a late Round Table preview from restoring Accept eligibility.
- `UserAssetsWorkspaceTest.factoryWallLateExportCannotRestoreAnInvalidatedPreview` owns the shared
  Factory panel's late-export rejection after candidate invalidation; the same monotonic generation
  governs Wall, Floor, Ceiling, Rectangular Table, and Round Table.
- `UserAssetsWorkspaceTest.factoryWallPreviewStaysBusyUntilItsWorkerCompletes` owns the shared Factory
  worker busy-state and draft-control restoration across all model kinds.
- `MacLocalFileApplicationGatewayTest` owns fake-platform proof for exact registered-app discovery,
  preserved ambiguity, unavailable and incompatible states, exact selected-app/caller-file handoff,
  application-revision change rejection, confined bundle identity, Launch Services failure truth,
  and unsupported-platform truth. It never loads the native bridge or launches an application.
- `AssetMemoryTelemetryTest` owns independent Asset thumbnail-cache reconciliation, selected-preview
  byte estimates, owner close, and payload-free release behavior.
- `UserAssetsWorkspaceTest.newerSourceSelectionCancelsStartupScanAndOwnsVisibleResults` also owns the
  passive source-scan job lifecycle across supersession and completion.
- `UserAssetsContentRecoveryServiceTest` is the canonical DB-owned content recovery gate: exact
  SHA-256 admission, partial recovery with honest absence, and stale-preview rejection.
- `CreatedThingPresentationMediaServiceTest` is the worker-only exact managed-byte, confinement,
  hash, privacy, association-revision, and truthful missing/unusable presentation gate.
- `UserAssetsRepositoryMigrationTest` covers transactional legacy row/node preservation while
  managed storage moves to the portable root boundary.
- `PresentationAssociationLifecycleTest` owns active-slot replacement, exact-content idempotence,
  same-byte independent roles, stable never-reused numbering, checked promotion/removal, and stale
  role/association rejection.
- `CharacterAssetPackageServiceTest` and `CreatedThingAttachmentServiceTest` own transactional new
  STL derivation, first attachment of a pre-existing STL, exact source/derived provenance, reuse
  across targets, stale/failure rollback, and exact association/revision OBJ resolution.
- `StlObjPreviewSessionTest` owns the path-free defensive Files STL request, bounded deterministic
  UV/OBJ/material preparation, immutable Viewer frame/color, safe failure, monotonic generation, stale-result
  suppression, paired west-model/east-material composition, off-EDT blocked Make and indeterminate
  button lifecycle, off-EDT blocked Attach and indeterminate button lifecycle, Make-without-attach,
  canonical Created Things model-group prepared Attach,
  separately checked already-managed exact/root-model Attach, and close callback contract.
- `ManagedObjAdmissionServiceTest` owns exact source/OBJ/material/texture identity checks,
  root-relative provenance, UV/material/texture derivations, fallback-color and generation metadata,
  exact derived-texture retrieval, canonical OBJ reuse across targets, optional exact 3D Model
  association, stale selection/target rejection, and full transaction rollback.
- `UnityFsBundleConverterTest` owns digest-pinned Cet/Cerberus read-only conversion, exact reviewed
  UnityFS/serialized profiles, mesh counts, five BC1/BC3 bindings, decoded pixels, deterministic
  OBJ/MTL/PNG/manifest digests, Viewer bounds/batching/diffuse truth, and fail-closed tuple, path, codec,
  truncation, offset, ambiguity, expansion, and cancellation behavior.
- `UnityFsBundleOperationServiceTest` owns exact-source read/recheck, lawful-acquisition gating, private
  staging cleanup, bounded registry evidence, original-parent lineage, atomic managed admission, explicit
  same-source reuse, retained duplicate reconstruction, idempotency conflict, hard-link/symlink rejection,
  stale rollback, and cooperative pre-commit cancellation.
- `ManagedObjAdmissionServiceTest` and `Model3DImportServiceTest` remain compatibility gates for the
  existing checked transaction and canonical Viewer mesh boundary; UnityFS conversion does not replace
  either authority.
- `CreatedThingsStandardPackageProjectionTest.activePresentationLeafKeepsRoleSlotIdentityAcrossReplacement`
  owns stable active-role selection across replacement and distinct retained-history identity.
- `StlObjPreviewSurfaceTest` owns the Viewer callback realization: arbitrary-bounds centering and fit,
  pointer-following horizontal/vertical orbit, Arena3D-consistent middle-drag pan, bounded zoom,
  canonical Reset/Fit with cleared pan, exact uniform color,
  opt-in presentation-only reference grid visibility before a model and through candidate
  replacement/invalidation/failure/camera actions plus close release,
  immutable pitch/yaw/zoom/pan capture and exact generation/selection/model-revision same-host restore
  through changed model bounds, plus stale/wrong/foreign/closed rejection,
  async-ready resident-Showcase adoption, fixed bounded out-and-back yaw, exact camera/material/mesh
  restoration, nonblocking completion, explicit cancellation, and distinct replacement/hide/close/
  not-ready terminal truth,
  exact current-yaw Front capture with pitch lock during orientation review and full orbit afterward,
  ready/failed/empty state, older-frame and mid-build
  invalidation rejection, and
  six ordered neutral snapshots with camera/material restoration, current-generation diffuse-map
  replacement/clear, exact-generation UV surface markers, solid-plus-wire toggle state preservation,
  neutral matte raw-STL texture-workspace initialization, supplied OBJ diffuse retention, model-to-model
  state isolation, and Clear Texture detachment while exact mesh UV coordinates remain unchanged,
  and camera lock retained across asynchronous manual projection-source preparation,
  and interaction/mesh/material/scene release on replacement, invalidation, and idempotent close.
- `UvMeshInspectionTest` owns overlapping-island all-candidate lookup, exact preview-space
  barycentric surface/UV round-trip, and outside/wrong-triangle rejection.
- `TextureMappingPipelineTest` owns deterministic non-dummy UV preparation, preservation of valid
  non-overlapping existing UVs, deterministic replacement of overlapping UVs, UV-indexed material OBJ
  output, atlas emission, and exact bounded provider image order.
- `TextureGenerationReviewSnapshotTest` owns the model-texture dialog's central trust boundary: fixed
  UV evidence and bounded Character references still assemble the exact provider plan, while the
  Images presentation separates associated-image cards from calculated-perspective cards. It covers
  included-reference state, exact approved-Front-first request ordering, deterministic Front-relative
  angles, one exact Front target without an orbit prerequisite, visible deck population, progressive
  card replacement, one fixed-set top action bar above the deck, removal of count editing, explicit
  approved-Front Create enablement, supporting-reference placement, immutable
  settings capture, selected full-resolution preview/copy evidence, single current-map replacement,
  initial/current inspectable atlas presentation, colocated final transaction actions, and
  independent review-image Fit/Zoom that cannot alter the model surface, plus
  per-index/per-Front-revision binding of each calculated view's target plus complementary
  provider/presence masks.
- `TextureProjectionPainterTest` owns exact frontmost triangle/UV screen lookup, circular brush
  interpolation and falloff, separate non-square viewport/source-image registration, source alpha and
  occlusion rejection, exact changed-triangle/texel or no-op evidence, one shared working texture,
  per-stroke Undo/Redo, edit-session reset, neutral pixel clearing without UV replacement, and stale
  view-identity rejection. `StlObjPreviewSurfaceTest` proves that one distinctive known-UV patch in a
  tall host updates both Swing texture state and JavaFX material, then survives overlay removal and
  camera movement.
- `StlObjPreviewContractTest` owns complete ordered-orbit admission relative to a reviewed nonzero
  Front yaw and rejection of incomplete or misordered calculated perspective sets.
- `TextureGenerationWorkflowStateTest` owns current-yaw Front generation without implicit approval or
  perspective creation, explicit approval before the fixed six-view Front-plus-five orbit,
  selected/sequential generation identities, per-result approval, current-only bake
  admission, retry preservation, invalidation, exact per-view projection-evidence identity, rejection
  of Front/neighbor-mask reuse, and late completion rejection.
- `ImageClipboardServiceTest` owns the reusable popup/button Copy Image command, dynamic selected-image
  enablement, off-EDT full-resolution loading, defensive transfer, and alpha preservation.
- `TextureProjectionPipelineTest` owns the dedicated supersampled white-model/true-black silhouette,
  fractional edge coverage, complementary provider edit alpha, signed visibility, occlusion rejection,
  direct/bounded/unresolved UV coverage, and calibrated render-back mismatch measurement.
- `TextureModelCutoutTest` owns returned-image clipping from opaque-model/transparent-background
  presence alpha, including black provider pixels.
- `UserAssetsServiceTest` owns complete managed-delete preview/token inventory, exact association-only
  removal, shared-byte preservation, request-reference cleanup, stale rejection, unreferenced content
  cleanup, and all-row rollback on failure.
`CreatedThingPresentationMediaServiceTest`

- Exact durable identity, transaction-consistent Silhouette/STL payloads, defensive owned bytes,
  durable revision advancement/restart, unrelated-role stability, and read-only behavior:
  `loadsOwnedSilhouetteAndStlAtOneDurableRevisionWithoutSideEffects`
- Missing identity/association plus unsupported, oversized, outside-root, and unreadable states:
  `reportsMissingUnsupportedOversizedAndUnreadableContentTruthfully`
- Public-field privacy and worker-only enforcement:
  `publicPayloadContractIsPrivateAndRejectsUiThreadReads`
- Renderer consumes only the explicit active slot, stays empty after active removal, and follows a
  checked promotion across service recreation:
  `rendererConsumesOnlyExplicitActiveSlotAndReviewedPromotion`

`GmRandomEncounterPanelTest`

- Opt-in filtering uses exact Created Thing ID, `CreatedThingPresentationKind.CREATURE`, and roster
  revision while excluded candidates preserve deterministic cursor advancement:
  `optInFilterUsesOnlyExactCreatureIdentityKindAndRosterRevision`

`UserAssetsProductDataStateCaptureAdapterTest`

- Safe workspace/selection projection, exact Assets revision stamp, and no evidence bytes:
  `capturePublishesOnlyAllowlistedWorkspaceSelectionAndRevisions`
- Prompt asynchronous completion without waiting for the owning EDT read:
  `captureReturnsPromptlyWithoutWaitingForTheOwningEdtRead`
- Path/hash privacy and fixed failure without private details:
  `captureDropsContentHashesAndPathsWithoutLeakingFailureDetails`
- Closed, absent, stale, and failed workspace mapping:
  `captureMapsAbsentAndStaleWorkspaceStatesToFixedIssues`

`UserAssetsServiceTest`

- Scan, ingest, and tags:
  `scanDirectoriesAndIngestSelectedImagesWithBatchTags`
- Current Background occurrence resolution, no deletion, and post-clear deletion:
  `backgroundDependencyBlocksDeletionUntilAuthoringClearsTheImage`
- Exact referenced occurrence versus identical-content source removal:
  `backgroundDependencyBlocksOnlyItsExactDuplicateSourceOccurrence`
- Named stop/restore/restart/locate identity and no-deletion lifecycle:
  `namedSourceStopRestoreAndLocatePreserveIdentityAndImportedState`
- Duplicate name/path, owned-destination, and same-path guards:
  `namedSourceAddRejectsDuplicateNamePathAndOwnedDestination`
- Ambiguous/changed evidence remains explicit and a named scan never falls back to synthetic ownership:
  `ambiguousOrChangedExternalEvidenceRemainsExplicitlyUnassigned`,
  `namedSourceScanOwnsEveryImportedOccurrenceWithoutSyntheticFallback`

`UserAssetsWorkspaceTest`

- One-JVM workspace lifecycle proof closes the embedded browser on the EDT, releases both scoped
  registries, preserves the Factory transient draft, waits for exact Created Thing startup readiness,
  and keeps all four public Character-media flows current:
  `AssetBrowserPanelTest.manageModeShowsMediaStoreAndUserAssetTabs`,
  `FactoryWorkspacePanelTest.browserPreservesTransientDraftAndMapsOnlyOneExactCurrentAsset`,
  `managedAttachProjectsExactRoleDeltaWithoutReloadingOwner`, and the four
  `adventureCharacterMediaSeam*` selectors.
- Content Root leaf selection uses its exact source-contained path while managed mutation confinement
  stays unchanged: `folderLeafNodesRenderWithFolderIconsAndShowFolderSummary`.
- Objects-first Media composition cannot substitute for the exact source node during expansion restore:
  `importedAssetsTreeExpansionPersistsAcrossWorkspaceInstances` and
  `candidateTreeExpansionPersistsAcrossWorkspaceInstances`.
- A second AppServices lifetime restores DTDT state without acquiring Character-media scope from the
  closed prior epoch: `DtdtBusPrefsLifecycleTest.tilePreservesSizeAndStateAndRestoresAfterRestart`.

- Korean User Assets control-bar source/filter labels, actions, bounded tag input, and location
  guidance: `koreanControlBarComposesSourceFiltersActionsAndGuidance`
- Korean shared picker actions, Root/Selected labels, and truthful empty states:
  `koreanPickerComposesActionsLabelsAndEmptyStates`
- Korean clipboard-import guidance, chrome, empty target, mixed-script role rendering, and complete
  resource resolution: `koreanClipboardImportComposesGuidanceChromeAndRoles`
- Korean media-attachment chrome, empty target, mixed-script action, localized role rendering, and
  complete role-key resolution:
  `koreanMediaAttachmentComposesChromeEmptyTargetAndRoleLabels`

`UserAssetsWorkspaceTest`

- Secret-free current Assets settings/readiness plus provider-free public start using the exact
  remembered provider/model/Auto size/seed/endpoint:
  `adventureCharacterMediaSeamUsesAssetsCurrentSettings`
- Missing canonical credential fails before provider work, discloses no secret, and creates no
  candidate:
  `adventureCharacterMediaSeamReportsUnavailableCurrentSettings`
- Public Adventure-facing exact Character generation/review/Keep/reject/reveal seam, canonical
  association identities, and cross-target rejection:
  `adventureCharacterMediaSeamReviewsKeepsAndRevealsExactCharacter`
- Exact in-flight public generation cancellation with no reviewed candidate:
  `adventureCharacterMediaSeamCancelsExactRunningRequest`
- Existing/new Character projection when one historical sheet is unreadable, including preserved
  normalized Dakarn facts, exact assigned Icon, truthful unavailable state, and no stored-text repair:
  `existingAndNewCharacterDetailsSurviveUnreadableSheetAndKeepAssignedMedia`
- Character-aware Context review and exact acquire-command snapshot, including removed legacy
  participant controls and stale DeCharne/Dakarn attachment rejection:
  `generativeReviewTracksExactCharacterWithoutLegacyParticipantsOrStaleAttachment`
- Exact DeCharne/Ice Toungue target summary, provider endpoint, role-sensitive Size/Auto, meaningful
  Seed, compact usage, accessibility identity, and target/revision/role command snapshot:
  `generativeContextTracksExactTargetSummaryRoleAndCommand`
- Provider/model capability sizing rejects a `3840 W × 2160 H` Reference Media dimension before
  provider creation and resolves Token Auto to `1024 W × 1024 H`:
  `UserAssetsImageAcquisitionTest.providerSizePolicyRejectsReferenceDimensionsBeforeNetworkRequest`
- Request-only additional direction remains distinct from the Created Thing editor:
  `selectedItemOwnsEditableInstructionsAndGenerativeDoesNotDuplicateThem`
- Narrow Selected Item and Generative Reference Media/result action bars retain every
  button inside the real panel allocation:
  `narrowSelectedItemAndGenerativeControlBarsKeepActionsVisible`
- Compact prompt dialog draft/rebuild ownership and target-local clearing:
  `generativePromptDialogSavesRequestOnlyEditAndClearsItForAnotherTarget`
- Stable ordered Reference Media, purpose review, and absence of legacy reorder controls:
  `generativeReferenceMediaKeepsStableOrderAndOmitsReorderControls`
- Exact Ice Toungue Creature Icon/Illustration/Silhouette admission, accepted Additional Instructions
  round trip/reload/isolation, stale editor binding, immediate owned-image projection, retained result
  A after failed attempt B, exact-result Keep eligibility, and persistent attributed failure detail:
  `creatureKeepRoundTripsAdditionalInstructionsForEveryImageRole`

`ExternalDocumentPromptSupportTest`

- Bounded UTF-8 external document loading, exact content hash/revision, stale-file rejection,
  oversize/NUL/invalid-UTF-8 rejection, and path-free prompt review.

`GenerativeImageReferenceSupportTest` and `UserAssetsImageAcquisitionTest`

- Mixed managed/external document and image review preserves stable order and purpose in the prompt;
  provider inputs contain images only and usage metadata reports document/image counts truthfully.

`CharacterAssetPackageServiceTest`

- Durable Character document-context inclusion and stale document revision rejection:
  `characterDocumentContextPersistsAndRejectsStaleDocumentRevision`
- Exact DeCharne Markdown/STL target review, DB-owned admission, and filename non-authority:
  `explicitTargetAdmitsMarkdownAndStlAtomicallyWithoutFilenameMatching`
- Changed-file/stale-target rejection with zero partial state:
  `staleTargetOrChangedFileRollsBackTheWholePackage`
- Creature Icon/Illustration/Silhouette managed-row validation plus forced transactional rollback
  with no content/User Asset/project-asset/association orphan:
  `creatureImageRolesValidateAfterAdmissionAndFailureRollsBackOwnedRows`
- Exact Cet PNG deduplication, repeat idempotence, independent multi-Character attachment, and
  distinct duplicate-hash source occurrences:
  `exactImageDeduplicatesAcrossOccurrencesAndTargetsAndRepeatsIdempotently`

`CharacterPackageImportServiceTest`

- Existing Beld plus new Dara use row-captured targets; README, image, and STL route together;
  duplicate image bytes share canonical content but retain two Source occurrences; Context,
  Description, associations, and the concrete Character survive reload:
  `existingBeldAndNewDaraUseCapturedPackageTargetsAndRouteEveryFile`
- Ambiguous exact-name matches pause only that row, and a forced independent package failure rolls
  back its new Character and every admitted row:
  `ambiguityPausesOnlyThatPackageAndFailureRollsBackItsNewCharacter`
- A changed file invalidates the complete preview before any package transaction begins:
  `changedPackageFileRejectsThePreviewBeforeAnyPackageMutation`
- The real Files workspace holds Beld as the global Created Things selection while projecting exact
  existing Beld and Create New Dara package rows with reviewed document/model/image roles:
  `UserAssetsWorkspaceTest.filesPackageReviewCapturesBeldAndDaraTargetsInsteadOfGlobalSelection`

`CharacterDocumentContextTest`

- Conservative source-attributed per-field proposal parsing:
  `parsesOnlyExplicitSupportedFieldsWithSourceRevisions`
- Multi-selection local transfer flavor, semantic drop classification, and wrong-target rejection:
  `multiContributionTransferClassifiesSemanticDropAndRejectsWrongTarget`
- Unified Prompt Context drop preserves source kind/revision and rejects the wrong target:
  `unifiedPromptContextDropPreservesSourceClassificationAndRejectsWrongTarget`
- Ordered/removable revision-backed request context:
  `contributionsRemainOrderedRemovableAndRevisionBacked`

`OpenAiImageClientTest`

- Launcher-neutral missing-key guidance fails before transport without revealing credentials:
  `missingCredentialFailsBeforeTransportWithoutDisplayingAKey`
- HTTP authentication rejection remains distinct from missing configuration and redacts the key:
  `mapsAuthenticationQuotaRateModerationRequestAndTransientFailures`

`FreeSearchResultSaveServiceTest`

- Exact original bytes and source format:
  `savesExactOriginalBytesAndPreservesSourceFormat`
- Reviewed replacement and preflight rollback safety:
  `conflictNeverOverwritesWithoutReviewedReplace`,
  `preflightFailureLeavesEveryDestinationUnchanged`

`UserAssetsWorkspaceTest`

- Real Asset Manager left navigation owns exactly peer Types/Created Things tabs, one contextual
  inspector, one managed Media tree, side-by-side Files/Media previews, and exact-ID reveal into
  right-side Acquisition Media:
  `hostBuildsSharedWorkspaceLeavesAndSplitters`
- Selected preview work cannot wait behind the list/tree thumbnail worker queue:
  `UserAssetsWorkspaceImageSupportTest.selectedPreviewDoesNotWaitBehindThumbnailWorkers`
- Exact Media Content Root selection drives Files, while descendant Media asset selection preserves
  the Files tree without another scan/rebuild:
  `mediaSourceSelectionDrivesReadOnlyFilesContentRootInTheSharedScope`
- Selected 2D / 3D PNG/STL preview, exact-byte filesystem save, retained result, exact-path status,
  and no managed admission or Created Thing attachment:
  `freeSearchPreviewsSelectionAndSavesExactBytesWithoutManagedAdmission`
- Exact Type target name/tags, reviewed role attachment, committed target retention, result retention,
  and unbound-search fallback:
  `freeSearchBindsUsefulTypeTargetAndAttachesWithoutChangingSelection`
- Link-only details, canonical handoff, disabled UI/worker save, and access filter:
  `linkOnlySearchResultShowsTruthfulHandoffAndCannotDownload`

- Real Asset Manager tab switching, resize, and persisted reopen divider stability:
  `acquisitionTabsPreserveMainDividerAcrossSwitchResizeAndReopen`
- Startup scan off EDT:
  `startupSourceScanRunsOffEdtAndLeavesEdtResponsive`
- Superseded scan cancellation:
  `newerSourceSelectionCancelsStartupScanAndOwnsVisibleResults`
- DB-owned workspace materialization cancellation without partial records or false failure logs:
  `UserAssetsServiceTest.cancelledDbOwnedMaterializationAbortsWithoutPartialRecordsOrFailureLog`
- DB-owned immutable materialization reuse:
  `UserAssetsServiceTest.repeatedAssetListingReusesVerifiedOwnedMaterialization`
- Independent managed/disk STL/OBJ previews through the shared interactive Viewer surface and linked
  preview height:
  `UserAssetsWorkspaceTest.selectedManagedAndFilesModelsRenderExactReadOnlyPreviews`,
  `mediaAndFilesPreviewDividersStayVisuallyAligned`
- Shared Markdown payload rendering delegates presentation to `MarkdownDocumentViewer`; existing
  preview keys and generations remain the Assets stale-result gate.
  `UserAssetsWorkspacePreviewMarkdownTest.payloadMarkdownUsesSharedDocumentViewer` owns the concrete
  payload-preview composition route.
- Exact post-import occurrence refresh without a complete second Source scan:
  `filesImportRefreshesExactOccurrenceWithoutCompleteSourceRescan`
- Created Things entity expansion selects and reloads the exact entity:
  `expandingCreatedCharacterSelectsAndReloadsThatExactEntity`
- Created Things refresh rebinds the retained active 3D Model leaf before exact managed-OBJ Attach:
  `rebuiltCreatedThingsLeafRebindsCurrentManagedObjAttachDestination`
- Canonical Character rename keeps the exact sorted-tree identity and sheet caret while stale saves
  remain rejected:
  `selectedCharacterRenamePreservesExactIdentitySortPositionAndCaret`
- Committed association refresh updates the selected Character's full media/details snapshot, exact
  active-role leaf, expansion, viewport, and focus without stealing a deliberate later Created Thing,
  leaf, or folder selection or duplicating the association; failure retains the original view:
  `committedAttachRefreshesSelectedCharacterWithoutStealingLaterSelection`
- Type Keep & Attach updates the exact selected Class/Creature Type in place, preserves the Types
  source and expanded owner, reveals the exact active role, retains replacement history, updates an
  unoverridden instance without copied rows, and reconstructs the hierarchy after reload:
  `typeGenerationKeepsExactDefaultAndRefreshesInheritedSelection`
- Real Generative Keep & Attach retains the exact Created Thing/target while preserving independent
  Media selection and accumulated results:
  `creatureKeepRoundTripsAdditionalInstructionsForEveryImageRole`
- Close cancels scan:
  `closingWorkspaceCooperativelyCancelsStartupScan`
- Exact used-by-Background repair message and selection preservation:
  `backgroundDependencyKeepsWorkspaceSelectionAndExplainsClearAction`
- One Seasons Content Root preserves the exact nested disk hierarchy:
  `singleSeasonsRootPreservesExactDiskHierarchy`

`ExternalAssetFederationTest`

- Exact official search/payload capability recognition and custom-route suspension:
  `onlyExactReviewedOfficialRoutesReceiveApprovedCapabilities`
- Fail-closed license/custom-provider gates and zero denied payload requests:
  `unknownLicenseRemainsVisibleWithoutPayloadDownload`,
  `unrecognizedCreativeCommonsUrlDoesNotBecomeAffirmativeEvidence`,
  `unreviewedProviderNeverInvokesAdapterOrPayloadLoader`,
  `paidAccountAndUnsupportedResultsNeverInvokePayloadLoader`
- Provider-local rate failure, duplicate evidence, and cancellation propagation:
  `rateLimitFailureDoesNotDiscardHealthyProvider`,
  `malformedResponseDoesNotDiscardHealthyProvider`,
  `duplicateCanonicalListingsAreReportedAndCollapsed`,
  `interruptionIsNotConvertedIntoProviderFailure`

`ExternalAssetBenchmarkTest`

- Versioned representative query coverage and raw/human report separation:
  `versionedQuerySetSpansCategoriesSpecificityFormatsAndAccessIntent`,
  `rawReportContainsObservationsWithoutHumanAssessment`

`ExternalAssetPreviewLoaderTest`

- Bounded image-only provider preview decode, positive/negative cache reuse, and shared in-flight
  cancellation: `providerPreviewIsDecodedOnceAndReusedForCardAndSelection`,
  `cancelledOffscreenAndStaleSelectionWatchersCannotPublish`,
  `nonImageAndOversizedResponsesFailClosed`, `unapprovedPreviewEvidenceNeverInvokesTransport`

`ExternalAssetResultPresentationTest`

- Human card labels omit technical identity:
  `cardLabelsAreHumanFacingAndNeverLeakTechnicalIdentity`

`UserAssetsWorkspaceTest`

- The real responsive deck rejects an old selected preview:
  `externalSearchUsesResponsiveCardsAndRejectsStaleProviderPreview`

`FreeSearchSitesDialogTest`

- Manage Providers primary double-click opens the exact official website target while invalid routes
  have no target: `primaryDoubleClickOpensExactProviderWebsite`,
  `customProviderUsesWebsiteOriginAndInvalidAddressHasNoBrowseTarget`
- Disabled code-owned providers remain installed and Add can reveal their fixed route:
  `disabledBuiltInsRemainInstalledAndCanBeRevealedWithoutEditableRoutes`

`OpenverseAssetProviderTest`

- Exact official anonymous and registered metadata queries retain rate and upstream evidence without
  making the aggregator's content URL actionable:
  `anonymousOfficialSearchPreservesClaimsAndRateEvidenceButNeverPayload`,
  `registeredCredentialsUseOneCachedBearerWithoutLeakingSecrets`
- Partial/rejected auth, invalid content/thumbnail URLs, unknown rights, throttling, malformed data,
  and cancellation remain provider-local and payload-free:
  `incompleteCredentialsFailProviderLocallyWithoutAnyRequest`,
  `rejectedRegisteredCredentialsAreRedactedAndDoNotReachSearch`,
  `invalidUpstreamPayloadAndUnknownLicenseRemainPayloadFree`,
  `throttleMalformedAndCancelledSearchesRemainIsolated`
- The real details surface keeps Download disabled, retains the canonical listing, and explains
  upstream license/hosting-platform verification:
  `UserAssetsWorkspaceTest.openverseResultExplainsUpstreamLicenseVerification`

`SketchfabAssetProviderTest`

- Exact public model discovery retains creator, license, price/access, advertised archive, thumbnail,
  attribution, provider credit, and rate evidence without any payload route:
  `exactPublicSearchPreservesCreatorLicenseFormatAndCreditWithoutPayload`
- Paid, non-downloadable, and unknown-access listings remain truthful and payload-free:
  `paidNonDownloadableAndUnknownListingsStayTruthfullyLinkOnly`,
  `nullPublicationLicenseAndArchivesRemainVisibleAsCatalogOnly`
- Rate, authentication, malformed response, and cancellation remain provider-local:
  `rateAuthMalformedAndCancelledSearchesRemainProviderLocal`
- The real control surface shows the same resolved encoded URI used by transport and retains readable
  wrapped control spacing:
  `UserAssetsWorkspaceTest.freeSearchControlsExposeActualSitesAndIndependent2d3dSelection`
- The real details surface keeps Download disabled and explains the approved in-app OAuth handoff:
  `UserAssetsWorkspaceTest.sketchfabResultExplainsProviderOAuthHandoffAndCannotDownload`

`ThingiverseAssetProviderTest`

- Fixed official Bearer search retains creator, license, preview, formats, and canonical handoff with
  no payload: `officialCredentialedSearchPreservesCatalogEvidenceWithoutPayload`
- Missing credentials fail only Thingiverse and never invoke transport:
  `missingCredentialFailsOnlyThisProviderWithoutTransportOrSecretLeak`

`MyMiniFactoryAssetProviderTest`

- Fixed official API-key search retains designer, store, preview, formats, and canonical handoff while
  ignoring OAuth-only download URLs:
  `officialKeySearchPreservesStoreMetadataAndNeverUsesOauthDownloadUrl`
- Missing credentials and rate limits remain provider-local:
  `rateLimitAndMissingCredentialRemainProviderLocal`

`PolyHavenAssetProviderTest`

- Exact official metadata-only search, creator/license/file evidence, and deferred payload:
  `officialSearchPreservesEvidenceAndDefersPayload`
- Review-time manifest, dependency, byte-size, and checksum revalidation plus safe ZIP assembly:
  `transactionDownloadRevalidatesManifestFileSizeAndChecksum`,
  `changedManifestFailsClosedBeforePayloadRequest`,
  `changedDependencyManifestFailsClosedBeforePayloadRequest`
- Off-host and traversal-bearing dependency evidence stays payload-free:
  `unapprovedFileHostRemainsVisibleButLinkOnly`,
  `unsafeDependencyPathLeavesResultLinkOnly`

`ManagedAssetDisplayNamesTest`

- UUID/hash/candidate suffix removal plus deterministic human collision labels:
  `stripsTechnicalIdentityAndAssignsDeterministicHumanCollisionLabels`

`UserAssetsServiceTest`

- Exact read-only naming/collision checkpoint with byte-for-byte durable row preservation:
  `managedNamePreviewReportsCollisionWithoutRewritingMetadata`

`UserAssetsRepositoryMigrationTest`

- Version-one backfill uses explicit Unassigned Source and remains idempotent:
  `backfillsVerifiedAndUnassignedSourcesWithTagsOnlyOnce`
- A source-name conflict rolls back both migrated nodes and marker:
  `migrationFailureRollsBackNodesAndMarker`

`AssetBrowserPanelTest`

- Parallel Media Store exact-cell parity:
  `pickModeMediaStoreDoubleClickUsesOnlyExactPrimaryCell`

`TemporaryBattleMapMaterializerTest`

- Runtime-only crop owns exact terrain and selected Layers without Authoring sources:
  `materializesExactCellLayerAndHeightCropWithoutAuthoringSources`
- Exact selected object/group presences are translated once and shared by 2D and Arena3D:
  `admitsOnlyExactResidentsAndTranslatesThemForTheShared2DAnd3DCanvas`
- Stale document projection stamps are rejected: `rejectsASelectionFromAnotherProjectionVersion`

`TalismanBasicCombatRulesTest`

- Initiative, modifier, and exact presence-ID ordering plus separated repeated activations:
  `schedulesInitiativeOrderAndSeparatesRepeatedCombatantSegments`
- More than ten tickets retain distinct per-actor segments and share segments across actors:
  `distributesMoreThanTenTicketsWithoutDuplicatingAnActorsSegment`
- Capacity above the ten-segment boundary is rejected: `rejectsActivationCapacityAboveTen`

`CombatRuntimeSessionServiceTest`

- Exact-Place admission, stale revision rejection, current/inspected selection, activation completion,
  and next-round reset: `runsExactPlaceInitiativeSelectionAndRoundResetWithStaleGuards`
- Pre-initiative cancellation clears only encounter state:
  `cancelBeforeInitiativeMutatesNoArenaObjects`

`CombatantProfileResolverTest`

- Available Character source HP/defense/initiative are resolved, while malformed or incomplete
  source snapshots receive explicit safe defaults:
  `resolvesCharacterCombatSnapshotAndDefaultsIncompleteSources`

`MapRuntimeComponentsTest`

- The real Play panel renders the combat roster, publishes exact revision commands, and disables
  continuous movement while combat is active:
  `playPanelShowsCombatRosterAndDisablesContinuousMovementDuringEncounter`

`CombatEffectCatalogTest`

- Built-in definitions have stable unique IDs, icons, descriptions, and typed restrictions:
  `loadsUniqueStableDefinitionsWithIconsAndTypedRestrictions`

`TalismanBasicCombatRulesTest`

- No Actions disables every primary action and opposed attack advantage/disadvantage cancel:
  `derivesNoActionsAndCancelsOpposedAttackModifiersFromEffectData`

`CombatRuntimeSessionServiceTest`

- Stale HP rejection, exact-instance removal, immediate availability recalculation, action selection,
  HP correction, and two-round schedule expiry:
  `correctsHpRemovesExactEffectsAndExpiresTwoRoundDisadvantage`
- Target-start, target-end, segment, and round effects expire only on the exact committed transition:
  `expiresEffectsOnlyOnTheirExactCommittedScheduleTransitions`
- Current-only Move spends one activation allowance without ending the activation, rejects a second
  Move and stale layer commands, and retains a truthful result:
  `movesOnlyTheCurrentCombatantOnceWithoutEndingItsActivation`

`MapRuntimeComponentsTest`

- The real Play panel disables rules-blocked actions while preserving checked HP/effect commands and
  restores exact action routing when the effect is absent:
  `playPanelRoutesHpEffectsAndRulesOwnedActionAvailability`
- The compact embedded Layers tree exposes the active Combat → Gradient row and publishes its exact
  revisioned visibility command:
  `playPanelKeepsCombatAndLayersCompactLeftAlignedAndTruthful`

`CombatGradientFieldTest`

- Exact logical heights derive stable uphill direction/grade while flat grids invent no direction.

`RuntimeCanvasViewTest`

- An explicitly supplied Gradient changes GM rendering while the ordinary Player frame remains
  byte-for-byte unchanged: `combatGradientPaintsOnlyWhenExplicitlySuppliedToTheGmSurface`
- Exact combatant reveal centers the retained 2D viewport without changing zoom and rejects a missing
  presence: `revealArenaObjectCentersTheExactCombatantWithoutChangingZoom`

`MapRuntimeComponentsTest`

- A reconstructed GM canvas requests retained combat state and reveals a newly current activation:
  `reconstructedGmCanvasRequestsCombatStateAndRevealsNewActivation`
- Each false-to-true Play showing transition publishes one exact Match Author visibility command:
  `playPanelRefreshesBackgroundLayersFromAuthoringOnEachEntry`
- A roster click publishes the revisioned exact-ID reveal before checked inspection:
  `combatRosterRequestsRevealBeforeCheckedInspection`

`MapRuntimeSessionServiceTest`

- Activation movement consumes physical allowance on a retained route without enabling global
  playback, persists one undoable position change, and leaves unfinished destination state active:
  `combatActivationSpendsPhysicalMovementWithoutStartingGlobalPlayback`

### Switchboard status dashboard

The Python status-dashboard tests own the local Full Test Suite report boundary:

- The existing-report and exact-identity test proves the control is
  Full Test Suite-only and carries the tested identity and report time.
- The active-run previous-report test and missing-report unavailable-state test own truthful active-run and
  unavailable presentation.
- The escaped-index inspection test and safe-report-tree server test own index existence,
  relative assets/subpages, content types, traversal and symlink rejection, no listing, and no writes.

### Application Server

`ManagedHandlerRuntimeTest` owns the generic managed-component seam with synthetic private children only.
`websites/moondance-web/tests/test_managed_static_site_bundle.py` owns Mandy's static consumer of that seam.
It proves exact declared closure and file digests, the no-database contract, exact health and package receipt,
read-only prefix serving, and traversal/off-prefix rejection. Tassy activation and browser presentation remain
separate live acceptance; no broad Java or web suite belongs to this focused package proof.

Its focused methods prove exact-file bundle rejection, the loopback-and-lifecycle-token-gated local operator
admission route, private ready Slice-fixture start, public-prefix dispatch through an ephemeral Tassy
controller, failed replacement preserving the prior child plus explicit rollback, simultaneous two-component
endpoint/process/state/receipt isolation, and controller-close child cleanup. The fixtures use temporary
empty SQLite files and never launch the real Application Server, use a live project database, deploy the
real Slice bundle, or claim component-domain correctness.

`ApplicationServerObjectFactoryPreviewStoreTest` owns rapid Creature-page publication: one complete
snapshot, stable selected bytes after later Factory edits, rollback to Main, and admission from a dirty
Factory source with unrelated manifest drift. It does not prove a browser rendering result or any Factory
domain, persistence, provider, or lifecycle behavior.

`TalismanOnlinePresentationServiceTest` owns the typed Present authority: initial safe state, revisioned
transition, identical replay, changed-fingerprint conflict, stale rejection, and latest-only safe fan-out.
`TalismanOnlineControllerTest` owns the complete private loopback proof: exact database identity, root
login, session/CSRF, one-time Player invitation, decodable QR, token-free redirect, Player page, root
Present, and revised Player-safe SSE delivery.
`TalismanOnlineDatabaseAuthorityTest` owns immutable database admission: direct selector, complete digest,
backup manifest, project generation, SQLite integrity/foreign keys, content-object identity, and fail-closed
selector or digest drift. `TalismanOnlineSeasonsServiceTest` owns deterministic bounded chapter projection,
read-only database truth, stable public IDs, capped real excerpts, immutability, path/source concealment, and
fail-closed missing-work or indirect-file behavior. These four selectors are the Online Server Java gate; do
not substitute a broad suite or a public server probe.

`TalismanOnlineDeploymentArtifactLintTest` owns the retained MW-HOST-00 static deployment review and MW-HOST-02
foundation admission: shell syntax, complete Git/JAR/unit/package/runtime digests, inode-stable shared-lock
acquisition before mutation, unique root-owned transaction staging, identity-checked cleanup, and exact
locked service-account records. It also pins strict runtime/link confinement, the complete pre-start
unit/drop-in/runtime/selector/manifest/JAR rollback gate with stopped-state ambiguity handling, the
credential, private directories, resource/state bounds, inactive unit, and live-baseline safeguards. It
does not simulate systemd, interruption, health failure, release activation, or rollback. The MW-HOST-02
live Linux acceptance probe supplies foundation execution evidence; MW-HOST-03 must still pass the isolated
release/failure harness.

For MW-HOST-03, the same selector also owns the static boundary of the executable private-mount harness,
identity-valid generated fixture, and root-only loopback protocol probe. The Linux harness supplies the
behavioral proof for first activation, healthy replacement, failed-release rollback, interrupted recovery,
unhealthy-current fail-closed behavior, manual rollback, failed rollback, and tampering rejection while
proving live service state unchanged. The host-local probe supplies exact-Origin health, root/GM, one-use
Player invitation, Present, Player-safe SSE, replay, stale, and restart evidence without emitting secrets.
Neither proof authorizes boot enablement or public routing/Caddy/DNS.

For MW-ONLINE-04, the selector additionally owns the one-way local-to-Moondance publisher, root-only remote
database promoter, expected-current guard, content-digest release directory, atomic current/previous
selection, exact verifier-JAR admission, and rollback on service or health failure. It also owns the
one-time hosted-demo-to-online-server foundation migration, including preservation of the hosted release
as non-active legacy material. Live acceptance must use a `ProjectStorageCommand backup`; raw SQLite copy,
reverse synchronization, a network SQLite listener, and browser-owned SQL are prohibited.

For MW-ONLINE-05, the deployment selector additionally owns the fixed local Operator command and root-only
remote action: exact current identities, authenticated SSH, private staging cleanup, closed six-action
vocabulary, boot-disabled activation/restart, packaged Java validation, and predecessor-only application or
database rollback. `test-talisman-online-operator.sh` supplies isolated behavioral proof for all six actions;
it must leave the live process and selectors unchanged. Controller proof additionally requires the
root-only path-free Operator/catalog routes, dynamic real Seasons GM choices, and unchanged one-use
Player/SSE authority.

For MW-TAS-01, `test-talisman-application-server-deployment.sh` owns the separate service unit, canonical
writable database path, boot-disabled lifecycle, loopback bind and IP filter, Level 1 memory envelope,
manifest-shaped release assembly, rollback markers, authenticated Caddy allowlist, and the absence of public
lifecycle, GM-Control, and process-identity routes. `ApplicationServerCommandTest` and the focused hosted-bind
method in `ApplicationServerControllerTest` own propagation and identity for `127.0.0.1`. The root-session
method in `TalismanOnlineControllerTest` owns the authenticated GM-console doorway. Host-side Caddy
validation, private process/database proof, public desktop/narrow page proof, and unchanged Online Player
behavior remain required live evidence; these focused selectors do not substitute for it.
`SrdMonsterArtifactStoreProductionTest` owns relocation of the production Critter root, and deployment lint
owns both hosted root arguments plus the extended cold-start health allowance.

For MW-TAS-04, the focused outer-gate method in `ApplicationServerControllerTest` proves the opt-in mode is
rejected on the normal all-interface LAN bind, an unmarked App Session remains unadmitted, and an exact
loopback marker projects only the fixed hosted GM caller. `ApplicationServerCommandTest` owns explicit option
propagation. `test-talisman-application-server-deployment.sh` owns the boot-disabled hosted option, marker
overwrite on every TAS proxy, current Control/Forest/application asset families, exact public GM routes, and
continued exclusion of admission-request, local-operator, lifecycle, and process-identity routes. Caddy
validation plus public desktop/narrow, one-root-login, current-screen, exact-release, database/media,
listener, and boot-disabled proof remain required live evidence.

`ApplicationServerManifestStoreTest` owns production bundle digest/route/capability/interlock/cutover truth,
hot next-snapshot refresh, and fail-closed unsafe source plus mutation-security validation.
`ApplicationServerControllerTest` owns loopback health/process/page/asset/no-store behavior, exact identity,
exact registered HTML entry and bounded bootstrap, structured unavailable/unknown routes, private
lifecycle-token rejection, same-process hot refresh, and manifest-failure identity availability.
`SrdMonsterApplicationServerGatewayTest` owns exact SRD DTO transport mapping, shared progress selection,
managed media, typed mutation results, and bounded Creature/operation/stale failure codes.
`ApplicationServerBodyFormCaptureTransportTest` owns the isolated Body Form capture adapter: App Session,
CSRF, server/manifest epoch, strict media/body rejection, missing and stale Factory session rejection,
per-workspace identical replay, changed-key conflict, unavailable composition, and byte-exact immutable
Factory envelope pass-through. Its fixture owns the transient Factory session and starts no live server,
database, provider, browser Save wiring, or durable read.
`SrdMonsterApplicationServerParityTest` owns
the owner-side five-read and two-mutation contract. Run only these four selectors, then the offline Atlas
validator.

`SrdMonsterImageBatchPageResourceTest` and `critter-image-batch-state.test.mjs` own the first-class Batch
Shelf owner package. They prove a separate no-Gallery page, progress-only reads, disabled controls with no
mutation transport, page-close polling disposal, active-lane-first bounded rows, owner order for waiting and
terminal rows, bounded caller text, and exact four-asset parity. Registration and live route proof remain
Application Server-owned.

`SrdMonsterImageBatchServiceTest.applicationGenerationFacadeAdmitsOneExactViewAndPreservesIdempotency`
owns the SRD-AS-05 facade seam. A temporary store and latched fake provider prove inert construction,
exact Token Left admission with the accepted Front hash, duplicate operation identity without a second
provider entry, stale rejection before admission, shared polling truth, and deliberate cancellation absence.

OF-GENMETA-11A extends that same exact selector with one optional canonical control package. It proves the
fake provider sees the frozen package/hash and delimited data-only appendix, exact retry causes no second
provider call, and changed metadata under the same operation ID fails nonretryably. The companion
`ObjectFactoryImageControlMetadataPackageTest` owns strict canonical repeatability plus unknown, unsafe,
duplicate, incompatible, trailing, and out-of-range rejection. Both use temporary/fake inputs only.

`object-factory-surface.test.mjs` owns OF-MAPPING-ANATOMY-22 pure silhouette fitting and browser metadata
composition. It proves bounded canonical controls produce explicit confidence and terminal wing/tail boundary
placement, invalid evidence rejects, and the composed package carries the exact contract, Creature, Mapping
stance, camera basis, controls, and envelopes. The direct hosted-resource selectors own the guided
generation composition and alpha build label. No provider, live server process, queue, database, or broad
suite is involved.

OF-SILHOUETTE-GUIDE-30 extends that pure browser test with strict guide-package composition and extends
`critter-hosted-state.test.mjs` with the exact guided wire shape. `ObjectFactorySilhouetteGuidePackageTest`
owns PNG decode, dimension, digest, canonical repeatability, and malformed/mismatched rejection. The existing
application-generation facade selector owns operation hash freezing and replay; its provider selector proves
silhouette-first, Front-second edit ordering and prompt roles. Gateway and controller selectors own exact
transport translation and the unchanged 64-KiB/session/CSRF/epoch boundary. All inputs are generated fixtures;
no live provider, database, server lifecycle, or broad suite is involved.

`SrdMonsterImageBatchServiceTest.applicationReadFacadeExposesFiveReadsWithoutMutation` owns the SRD-side
production activation boundary. Its temporary canonical artifact/database adapters prove catalog, detail,
all/Creature progress, positive durable direct lookup, and opaque digest-checked media while checkpoint
bytes remain exact and provider, registration, verification, role-association, and prompt effects remain
zero. It starts no server and never reads live data.

The same selectors own available Shelf P1/P2 registration, locale/system-theme/server-generation bootstrap,
distinct bounded workspace sessions, expiry/restart invalidation, cookie-secret non-disclosure, available
typed status, and path-free live status. An activated temporary manifest owns 401/200 Critter route proof
plus fake-only POST session/CSRF/epoch/admission/cancellation proof without changing production availability.
Do not substitute a broad suite or inspect/change `8766`.

### SRD Monster image batch

`SrdMonsterImageBatchServiceTest` owns stable historical Monster keys, seven-role projection, reviewed
legacy reuse,
default/model cost gate, one-model Do next/Regenerate-now authoritative boundary, durable role
files/metadata/manifest and rollback
history, retained-result recovery without a provider call, Both comparison selection, decoded alpha truth,
bounded accepted-PNG structure/recorded-alpha reconstruction, unchanged-stamp cache reuse, malformed-PNG
quarantine, current-model legacy-colour exclusion, and exact no-byte/metadata-mutation proof,
loopback controls, rendered Cancel routing plus latched-provider boundary/idempotence, role-distinct prompt
contracts, CANCELLED-to-fresh-Start progress reset/history retention without a preview gate,
authoritative Current/Next publication before provider entry, exact one/two-call preview,
public prompt-evidence absence plus persisted-card disclosure, atomic role-template editing/frozen
provenance, append-only non-gating usage observations, and one-current-
card-per-role gallery projection. `SrdMonsterSharedLaneCoordinatorTest` owns applied ceilings one through six,
distinct atomic reservations, direct priority/deduplication, out-of-order provider completion, private
staging, serialized canonical/database writes, pause/cancel drain, exact direct cancellation, card-local
provider rejection and edited manual retry, the systemic breaker, and socket-free production controller
render/accessibility projection. Its live-ceiling cases prove immediate fill on increase, accepted-call
drain without replacement on decrease, and loopback authority-confirmed Applied projection during active
work. Its restart cases prove the confirmed ceiling is restored before direct scheduling, permits six
distinct subsequent direct lanes without starting a sweep, reads a legacy checkpoint, and fails closed on
an invalid explicit value with zero admission. Its direct-plus-sweep case proves Start admission beside
direct work, reservation deduplication,
free-capacity fill, direct-first backfill, and preserved Pause. It also owns six stable lane projections,
newest-admission operation-feed
ordering, Cockatrice direct admission/Lane 6 claim with stable identity, idempotent client-UUID
reconciliation, no-bytes non-CURRENT wording, pending/applied concurrency truth, and current-corpus
missing-role Generate behavior without disturbing the other role. It also owns read-only startup
compatibility for exact sanitized legacy
moderation evidence, automatic-queue exclusion, safe gallery fields, mandatory edited manual retry, byte
preservation, lookalike rejection, and newer structured-failure precedence. `DatabaseOwnerInterlockTest` owns
competing-owner refusal, checkpoint-stop evidence, and dead-PID plus free-OS-lock stale recovery.
The CIC-02 cases prove one production-adapter reference edit for each non-front direction, automatic and
direct Front-first admission under a six-lane ceiling, exact accepted/database-current Front byte/hash
capture, no premature dependent lane, five independent post-Front lanes, seven detail roles, and
serialized exact-role registration. The database fixture proves 330 actionable Critters including all 95
Animals, stable known Monster keys, and every Token-view association without definition mutation.
The Critter surface selector proves the rendered controller/product name, stable internal routes,
ordered All/Icon/Token/Token Back options, and exact `token_back`/`Token Back` card projection. The
Workboard-owned focused Python fixture separately proves its mixed provenance-independent current feed,
four role filters, missing-role routing, and Critter Gallery/Creation labels.
The narrow dark Provider-queue render selector owns controls-before-queue order, current/lane prominence,
collapsed Prompts/Detailed statistics, removed estimate/usage copy, and accessible interaction-state
styling. The Cyclops fake-provider selector owns source-independent actionability/admission parity, exact
active-front Token reference, single-role registration, and UUID restart reconciliation.
The Critter mapper selector owns its two-pane shell and packaged CSS/JavaScript routes, exact six Token
views with no Icon card, image-only landmark editing, shared Shift-selection, focused-view
Enlarge/Retract/Escape behavior, UV-to-3D preview control, immediate direct QUEUED acknowledgment, and
zero provider admission on page open. A browser fixture verifies the assembled desktop layout and
synchronized selection without real provider or content/database mutation.
`SrdMonsterApplicationServerParityTest` owns the v2 target evidence without starting a server. It pins all
four Critter bundle digests, Batch/Gallery/Creature IDs and routes, true current-asset Gallery, exact reads,
compact progress, opaque media, initial idempotent exact-role generation and direct cancellation successors,
deferred batch controls, retained Critter Java authority, and delegated activation truth. Its browser proof
retains the historical server-mediated direct call while rejecting batch/provider/database/credential paths.
`CanonicalSrdMonsterAssociationServiceTest` owns the isolated 235-only visible collection, zero
definition creation, exact 108-row archival migration, complete active-role hash accounting, reviewed
mapping reuse, conflict/unmatched preservation, rollback-safe idempotence, and one active association
per current role. It also proves one-role replacement on an exact archived definition without changing
that definition or provenance. Its Cyclops selector owns exact noncanonical Creature lookup, current-role
bytes, Token Back association, and zero definition creation. The focused Workboard Python test owns
source-independent direct-route
serialization; the dashboard test owns embedding the real loopback controller. Run only the selectors
recorded in the active
design test plan, then the offline Atlas validator; do not infer permission for a broad suite, application
launch, provider request, or live content/database write.
`DatabaseCritterProjectionServiceTest` owns the isolated production-read proof: one DB-backed active role,
no source-file dependency after admission, exact opaque media/hash success, wrong identity/hash rejection,
and fail-closed catalog/detail/media behavior for an active association whose content join is stale.
`CanonicalCritterClosureServiceTest` owns isolated closure preview/apply proof: accepted-file admission,
exact role-hash verification, zero-reconcile repeat without duplicate content, stale active-slot repair
through the existing lifecycle, and wrong-token rejection. Existing canonical migration tests retain the
clone-only backup preflight/hash evidence; no focused closure test opens the production database.
The same closure test deterministically changes image and metadata files after preview and proves zero DB
mutation, then injects a later-role transaction failure and proves the entire two-role batch rolls back.
### Easy Tale Body 6 immutable sources and import review

Run `EasyTaleImportServiceTest`, `EasyTaleImportRepositoryTest`, and
`EasyTaleFocusShellPanelTest`. They cover declared source formats, immutable editions and exact anchors,
separate derivatives, all candidate kinds, unresolved wording, same/new-edition behavior, explicit review,
stale and cancellation gates, interruption rollback, localized nested Sources rails, and unchanged Focus
timing. Do not substitute a broad suite.
### Easy Tale Body 7 literary characters

Run `EasyTaleCharacterServiceTest` and `EasyTaleFocusShellPanelTest`. They cover incomplete and reviewed
records, exact source occurrence, detail growth, resolved/unresolved timelines, stale calendars,
non-destructive links, rejected duplicate review, approximate mention review, cross-work rejection, and the
localized one-level Character Focus workspace. Do not substitute a broad suite.

### Easy Tale bounded literary corpus

Run `EasyTaleCorpusServiceTest` and `EasyTaleWritingCorpusCommandTest`. They prove exact raw bytes,
DOC/DOCX-and-image-only selection, binary sources without invented text, idempotent batch resume, one
checked work revision, substituted-manifest rejection before extraction, exact source/extractor bounds,
and relational integrity. For an authorized local admission, additionally require 10 documents / 82 images,
92 distinct paths and identities, 404,307,069 source bytes, the pinned manifest digest, no binary extracted
text, stable revision 2 on rerun, and clean SQLite foreign keys. The complete source-owned inventory proof
additionally requires 143 unique rows / 430,023,954 bytes partitioned into 92 admitted and 51 reasoned
exclusions with the exact reviewed-manifest digest. Finder `.DS_Store` observations are ignored before
manifest construction. Do not substitute a broad suite.

### Easy Tale canonical Seasons document materialization

Run only `EasyTaleSeasonsDocumentMaterializerTest` and
`EasyTaleCorpusMaterializationRepositoryTest`. They prove ten distinct documents and 82 preserved images,
exact extraction-manifest and code-point accounting, duplicate-title edition separation, exact edition/
anchor/derivative/manuscript text equality, Winter/Spring/Summer hierarchy, deterministic identity and
idempotent receipt, stale/changed-source rejection, and all-layer rollback on a forced pre-commit failure.
They also require zero created candidate, character, recorded-event, or corpus-event rows. For the reviewed
local command, additionally verify one receipt, revision 3, ten editions and derivatives, equal anchor/block
counts, exact 970,804 text code points in each text projection, 82 unchanged image rows and exact image-byte
total, clean foreign keys, and a successful SQLite integrity result. Do not substitute a broad suite.

### Easy Tale canonical Seasons semantic materialization

Run only `EasyTaleSeasonsSemanticMaterializerTest` and
`EasyTaleSemanticMaterializationRepositoryTest`. They prove exact anchor-backed accepted identities and
aliases, exact appearance/evidence navigation, unresolved absolute time, pending non-identity claims,
spelling review, deterministic digest/idempotence, stale rejection, per-connection foreign-key rollback,
same-count semantic-drift rejection, pre-commit interruption rollback, and exact committed revision truth.
For the reviewed local command,
additionally verify its receipt/count seal, unchanged ten editions/5,715 anchors/82 images and raw byte
totals, zero broken source joins, contiguous source-relative event order, null unresolved event times,
clean foreign keys, successful SQLite integrity, and unchanged revision on exact rerun. Do not substitute
a broad suite.

### Easy Tale typed TAS browser-read projection

Run only `EasyTaleBrowserReadServiceTest`. Its five repository-backed scenarios pin the exact contract and
four capability IDs, canonical work/batch/manifest/source/document/semantic identities, coherent revisions,
deterministic 92-source and Part/Chapter/Scene paging, Unicode-safe 0–4,096-code-point excerpts, immutable
path-free DTOs, the complete story projection, truthful text/binary/review authority, and unchanged database
truth. They also prove the seven-value closed error vocabulary for stale, invalid, unavailable, incomplete,
and noncanonical projections. `EasyTaleFocusShellPanelTest` adds five localized Focus, async preparation,
exact guarded install, stale, and detach scenarios. The direct `easy-tale-state.test.mjs` selector owns the
truthful fixture-free hosted state. These selectors start no server or application and perform no provider
or live database action.

Run `ApplicationServerControllerTest.servesOnlyAuthenticatedBoundedTypedEasyTaleReads` for the host adapter.
It proves the injected typed Story projection is unavailable without an App Session, returns only capability-
stamped browser-safe JSON after bootstrap, rejects duplicated query fields, and reports a typed stale revision
with its safe current revision. `ApplicationServerManifestStoreTest` pins all four host registrations as
DELEGATED; this proof does not activate the managed server, compose an owner repository, or mutate content.

### Easy Tale Body 8 Write workspace

`EasyTaleManuscriptServiceTest` proves exact hierarchy reload, immutable source anchors, event isolation,
non-destructive compilation order, split/merge/reorder with local undo/redo, complete stale recovery, and
the absence of an invented autosave scheduler. `EasyTaleFocusShellPanelTest` proves the localized,
non-nested outline/editor/reference/notice/recovery composition.

### Easy Tale ET-WEB-03 browser command owner

Run only `EasyTaleBrowserCommandServiceTest`. Its four isolated repository scenarios prove the canonical
contract/capability identity, bounded exact-revision save and replay identity, exact committed snapshot,
stale no-mutation result, failed-save recovery review, explicit corrected resubmission, and matching recovery
clear. It starts no server or application, uses no provider, and does not touch the live database. The
separately owned TAS App Session/CSRF/epoch/replay selector remains required before route activation.

### Easy Tale Body 9A provider-free revision authority

Run only `EasyTaleWritingRevisionTest` and `EasyTaleVoiceCommandTest`. They own exact five-scope capture,
explicit material/context privacy, provider/locality provenance without provider construction, cancellation,
malformed and stale outcomes, exact alias and uncertain-name review, canonical spelling confirmation,
reject/selective acceptance, link rebasing, and recorded-history/treatment isolation. They use temporary
repositories only and start no application, server, microphone, provider, network, timer, or live database.

### Easy Tale Body 10 comparative timelines

Run only `EasyTaleTimelineServiceTest`, `EasyTaleTimelineRepositoryTest`,
`EasyTaleBrowserReadServiceTest`, and `EasyTaleFocusShellPanelTest`. They own canonical resolved ordering,
unresolved source ordering and absent
crosshair, independent lane kinds, semantic-scale units, mark traits, synchronized selection identities,
exact-calendar stale rejection, named-set order/revision/rollback/foreign-key behavior, localized accessible
marks, Winter/Spring/Summer structural coverage, and explicit empty Fall. They use temporary repositories
only and do not prove application launch, server transport, provider behavior, playback, or live-data change.

### Easy Tale Body 11 recorded history and continuity

Run only `EasyTaleHistoryRepositoryTest`, `EasyTaleHistoryServiceTest`,
`EasyTaleBrowserReadServiceTest`, and `EasyTaleFocusShellPanelTest`. They own cited proposal, rejection,
approval, commit, immutable event revisions, exact citation/source-date retention, unresolved-date refusal,
stale-calendar/work and foreign-anchor rejection, pre-snapshot rollback, source-claim preservation,
side-by-side conflicts, treatment isolation, participants, continuity, nested History/Review Focus rails,
localized accessibility, immediate feedback, and detached-result discard. They use temporary repositories
only and start no application, server, provider, network, playback clock, timer, or live-data action.

### Core Morph catalog materialization and read projection

Run only `CoreMorphCatalogServiceTest` and `ApplicationServerCoreMorphReadTransportTest`. The service selector
proves source SHA/revision validation, atomic initial materialization, exact no-op repetition,
relational/content
projection, declared twelve-Morph ordering, complete positions and deterministic keyframes, additive
revision-1 database upgrade, defensive bytes, unknown identity, and same-revision drift rejection. The
transport selector proves App-Session admission, bounded
catalog/current
queries, typed not-found, exact generated response fixtures, all twelve catalog entries, and the complete
74-Point Ancient Dragon revision-3 package. The workshop validator separately proves Mapping digit planes,
complete Humanoid capsule geometry and editor parameters, stacked disclosure shelves, and hierarchical
skeletal-link controls.
It starts an ephemeral controller with a temporary database; it does not start or restart the application or
managed server, invoke a provider, copy a Morph, or touch the user's live database.

### Creature-private Morph Save and Base Revision Publication

Run `CoreMorphRevisionServiceTest`, `CoreMorphCatalogServiceTest`, and
`ApplicationServerCoreMorphSaveTransportTest` only for this persistence body. These use temporary databases
and ephemeral local transport, never live Creature records. They cover guarded immutable private/base
append, explicit base replacement, receipt rollback, source-head protection, pending collisions, corrupt
content, older source, idempotent replay, session/CSRF/epoch admission and complete readback.
Browser adapters are covered by `creature-morph-save-session.test.mjs`, `creature-morph-save-ui.test.mjs`,
`creature-morph-save-browser.test.mjs`, and the save cases in `critter-hosted-state.test.mjs`.
The focused headless browser fixture uses a fake transport for actual Save/comparison controls and checks
immediate feedback, equal-camera old/new previews, Cancel/no-write and private-copy invariance. It does not
claim physical iPad proof or durable live Save. Run the Atlas validator after these documentation changes.

### Operations Atlas contract

- Versioned Operations Atlas schema and checked-in conformance vectors:
  `DevelopmentArchitectureAtlasValidatorTest.operationsAtlasExamplesSatisfyVersionedContract`
- Shelf-native Puppeteer route seed and its verified references:
  `DevelopmentArchitectureAtlasValidatorTest.operationsAtlasPuppeteerRouteSeedResolvesVerifiedReferences`
- Truthful unresolved owner/design route gaps:
  `DevelopmentArchitectureAtlasValidatorTest.operationsAtlasRouteGapsRepresentTruthfulMissingOwnerAndDesign`
- False route resolution and evidence masquerading:
  `DevelopmentArchitectureAtlasValidatorTest.operationsAtlasPuppeteerRouteSeedRejectsFalseResolution`
- Malformed fields, IDs, duplicate declarations, scalar values, and repository paths:
  `DevelopmentArchitectureAtlasValidatorTest.operationsAtlasContractRejectsMalformedRecords`
- Source authority, result-stage/equality, session gate, live state, and restart freshness separation:
  `DevelopmentArchitectureAtlasValidatorTest.operationsAtlasContractSeparatesSourceResultAndRuntimeTruth`

These selectors read only local schema and synthetic JSON fixtures. They do not create an operational
record store, inspect or mutate a process, launch the application/server, or make Workboard authoritative.


## Geography numerical input foundation — GCP-01

`NumericalElevationInputTest`

- `inputProof` runs the shared synthetic numerical checks: exact Float32, bounded full-size raster,
  NoData/coverage, coordinate/units/digest rejection, cancellation, and detached labelled W1 context.
- The dependency-free harness runs those same checks with the available JDK; that result must name
  its actual runtime and is not a substitute for the configured Java 25 product test gate.
- `scripts/geography/test_gcp01_acquisition.py` checks only offline acquisition parameters and bounds.
  None of these synthetic checks proves actual provider bytes, real Seasons placement or native import.


## Geography actual-source and bounded height exchange — GCP-01R / GCP-02A

`RealElevationInputTest`

- `realInputProof` exposes the seven actual TIFF/reference checks through the configured product runtime.
  The optional direct harness also verifies the supplied full working crop; source bytes stay explicit.

`EarthHeightPreparationTest`

- `heightPreparationProof` covers 18 shared numerical, resampling, canonical PNG and real-small-crop checks.

`EarthPackageArchiveTest`

- `earthPackageArchiveChecks` covers 30 bounded ZIP/schema/integrity/cancellation cases.

`EarthPackageHeightTest`

- `heightEvidence` covers 24 bounded PNG and source-to-canonical consistency cases. Test Water is synthetic.

`EarthPackageWireTest`

- Thirteen configured Jackson/wire tests cover closed bounded JSON and full exchange height-evidence
  round trip. They are separate from the dependency-free harness and must not be counted as run by it.

Use root `:test` with those five classes and the configured Atlas validation task, never an
unqualified multi-project test task. `scripts/geography/test-gcp02-height.sh` compiles actual canonical
raster/codec prerequisites and runs only the pure-JDK bodies; its optional explicit raw/output arguments
produce one new height-evidence directory. `scripts/geography/verify-gcp01-independent.py` is optional
developer-only GDAL/NumPy/Pillow audit tooling, never a runtime dependency or Seasons importer.

## Geography authored Water and shared source preview — GCP-03

`AuthoredWaterTest`

- `contracts` supplies 29 analytical bank-edit, polygon, coverage, immutability and cancellation cases.

`EarthTerrainPreviewTest`

- `contracts` supplies 20 area-cell registration, shared mesh, physical scale, exaggeration, registered
  preview and cancellation cases.

`EarthPackageWaterTest`

- `contracts` supplies 12 exact recipe/mask/preview pairing, rehashed-substitution and cancellation cases.

`EarthWaterWireTest`

- Fifteen strict configured Jackson and complete source-codec cases cover closed records, unsafe input,
  the real recipe, exact package round trip, source pinning and rehashed content rejection.

`TerrainAreaCellCaptureTest`

- `sharedCaptureDelegatesToAreaCellSamplingWithoutLegacyBehaviorChange` proves the opt-in shared capture.
- `sharedCaptureHonorsCancellationWithoutPublishingSnapshot` proves cancellation at that bridge.

Run these five classes with the root-qualified :test task: 78 new cases, not the already cleared GCP-01/02
suites or unrelated subprojects. Run configured Atlas validation for the documentation/source seam.
`ConfiguredGrandCanyonPackageProbe` then checks the delivered real package through the actual strict
codec using the existing configured test runtime. The read-only root JavaExec task is supplied by
`scripts/geography/gcp03-configured-proof.init.gradle`; it is not an application launch.

`scripts/geography/test-gcp03-water-preview.sh` executes the 61 direct new Java checks and optionally
materializes the full real source and exact archive under a 192 MiB Java heap. It compiles unmodified
shared production mesh mathematics with an explicitly uncallable compile-only preference boundary.
Its optional recipe literal adapter is test-only and is not evidence that product Jackson ran.
`scripts/geography/test_gcp03_probe.py` contains six focused offline checks for that adapter.
Configured product/Jackson/bridge acceptance remains separate from these standalone observations.

## Control retained/saved layout increment

Run only these directly relevant JavaScript selectors:

```text
node --test src/test/js/control-client.test.mjs src/test/js/control-layout.test.mjs
node --test src/test/js/control-shell.test.mjs src/test/js/control-page.test.mjs
```

The first uses actual public Shelf validators/store and Control adapter with synthetic transport. The second
uses the actual shell/page and native browser DOM with synthetic current Place/admission/memory replies.
Set TALISMAN_BROWSER_PATH to an available isolated test browser when needed. Default browser tests load
intercepted page URLs. TALISMAN_CONTROL_BROWSER_MODE=offline explicitly replaces only static import
specifiers with local Blob module URLs and binds test replies; it proves DOM behavior, not served URLs,
production CSP, actual GM HTTP authorization or a physical iPad. No live/test application server is started.

Coverage includes all seven retained arrangements, atomic invalid/unavailable/editor rejection, public
focus, no-op resize, whole save/restore, v1 migration, unknown outcome/conflict read-back, newer local intent,
expired/stale/disposed leases, bounded UTF-8 replies, token-free storage and unchanged P0 admission.
The page fixture reviews 1600/1024/768/360 widths and touch input with crypto.subtle unavailable.
`ApplicationServerManifestStoreTest.productionControlLayoutUsesTheExistingPrivateMemoryRoutesAndPublicShelfModule`
checks real manifest registration/capability metadata; existing Control asset digest assertions track the
same package. Java 25/Gradle and served-browser/device checks remain distinct acceptance gates.

## Control Java-admitted manual Context increment

`GmControlContextWorkspaceBindingTest`

- Actual Java codec/service/gateway with the existing synthetic sealed owner fixture: canonical Copy,
  no-change Paste, both foreign workspace/Box/generation payloads rejected before recapture, unchanged-base
  validation and fresh-owner stale rejection. No mutation or persistence method is called.

`GmControlContextHttpProofTest`

- Actual Controller handler with in-memory HttpExchange: revoke GM during body read and during response
  encoding, require both cookies plus CSRF/instance/version/digest, and emit no private payload on rejection.
  It never binds or starts a server. Local Java 25/Gradle execution remains required.

Run the existing `GmControlContextExchangeTest` beside those two classes and
`ApplicationServerManifestStoreTest`. Browser selectors are control-context.test.mjs (response transport,
raw Paste and correlation), control-context-browser.test.mjs (assembled panel with synthetic transport),
control-client.test.mjs, control-layout.test.mjs, control-page.test.mjs and control-shell.test.mjs.
The latter retain the existing expired-bootstrap layout lease regression. Normal served mode is primary;
explicit offline Chromium checks production DOM/CSS/module bodies, not real Java HTTP, CSP or device clipboard.
Capture representative 1600/1024/768/360 widths. See the increment review and local gates (Talisman source reference: `
<../design/Active Designs/GM Control Screen/CONTROL-CONTEXT-REVIEW.md`).


## Database Viewer

The read-only TAS component viewer has focused native selectors:

- `com.moondance.talisman.app.database.componentviewer.DatabaseViewerNativeReadTest`
- `com.moondance.talisman.app.services.app.applicationserver.DatabaseViewerHttpProofTest`
- `com.moondance.talisman.app.services.app.applicationserver.ApplicationServerManifestStoreTest`

The standalone Java probe, Node tests, owner-derived SQLite fixtures and optional DOM/HTTP harness are
listed with exact commands and limits in the
[Database Viewer contract](<../design/Active Designs/Application Server/DATABASE-VIEWER.md>).
The Python bridge is a synthetic test seam, not a production JDBC driver. These proofs do not launch the
application, migrate a user database, perform provider work or authorize managed host adoption.
