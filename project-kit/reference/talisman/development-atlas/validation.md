# Development Architecture Atlas Validation

## Run the focused gate

From the task's permanent Talisman worktree, host Gradle on Java 21 and use offline mode:

```text
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew --offline --no-daemon \
  validateDevelopmentArchitectureAtlas
```

The task executes only `DevelopmentArchitectureAtlasValidatorTest`. Gradle may compile required
main/test sources first; it does not execute the broad suite. The validator reads local tracked
files only, performs no network requests, and does not modify the repository. Java 21 hosts Gradle
because this Gradle/Groovy release cannot analyze build scripts on Java 25; the repository's
configured Java 25 toolchain still compiles the sources and launches the test JVM.

## Body 2 focused gate

The exact process-local bus-policy gate runs only the two core contract classes, followed by the
deterministic Atlas validator:

```text
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew --offline --no-daemon :test \
  --tests 'com.moondance.talisman.app.core.bus.BusTest' \
  --tests 'com.moondance.talisman.app.core.bus.BusDeliveryPolicyTest'

JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew --offline --no-daemon \
  validateDevelopmentArchitectureAtlas
```

The first command proves the declared delivery policies, lifecycle/TTL dispatch checks, current-only
retained-state cutover, recipient failure isolation, inline/reentrant/contended and RESOURCE ordering,
bounded metadata diagnostics, and terminal close behavior. The second command proves documentation and
source-boundary consistency. Neither command authorizes a broad suite or application launch.

## Body 8A focused gate

The joined-observability body runs only the observation, registry-decorator, Activity, bus, real memory-job,
typed map-performance, AppServices-composition, and validator classes listed in the living
[Talisman Architecture Operations Atlas](<../design/Active Designs/Talisman Architecture/TALISMAN-ARCHITECTURE-OPERATIONS-ATLAS.md>).
Run one lightweight `memory_pressure` check immediately before that focused batch; unhealthy pressure or
throttled pages pauses the batch rather than widening the test process.

The body proves delegate-first and failure-isolated projection, owner-private bounded evidence, salted
process-local joins, active/terminal incident bounds, attempt-local reentrant-safe bus correlation, factual
existing Activity/memory/performance attachment, and non-authoritative shutdown. It does not run the broad
suite or launch the application.

## What is validated

The validator derives references from structure rather than comparing prose snapshots:

- every local Markdown link in the Atlas and Authoring/runtime product baselines;
- inbound Atlas links from the design entry points and the mandatory path in `AGENTS.project.md`;
- Java owners and members named in inline code, such as `MapEditorScopeState.close`;
- exact command/event constants, including qualified keys such as
  `MapEditorBus.CMD_HEIGHTMAP_EXPORT`;
- snake-case schema seams such as `map_document` against local Java/resource/SQL sources;
- focused-test classes and methods using the grouping rules in [Focused-Test Routing](focused-tests.md);
- package directories and repository/Java paths in `AGENTS.index.md` tables;
- the version-one [Operations Atlas record contract](operations-contract.md), machine schema, and checked-in
  synthetic examples. Structural validation rejects malformed records and semantic validation rejects broken
  references and false source/result/runtime or route claims.
- the complete `services/operation/` production package dependency boundary and exported payload
  signatures. Only the same package, time values, and allowlisted bounded collection utilities are
  permitted; toolkit, transport, execution, credential, database/repository, filesystem/process,
  raw-byte, generic object/map, stream/buffer, and unbounded payload surfaces fail validation;
- the dependency imports and exported `BusObserver` / `BusDeliveryDiagnostic` observation boundary.
  Only time values and allowlisted scalar helpers are admitted; raw `BusMessage` or `BusPayload`,
  arrays, generic objects/collections, exceptions, streams/buffers, toolkit or transport values,
  paths/URLs, database/repository handles, images, and credential/secret/token types fail validation.
- the complete `services/observation/` dependency boundary plus exported
  `SemanticOperationObservation`, `SemanticOperationIncidentHub`, and `SemanticOperationSafeJoin`
  surfaces. Only the shared operation contract, safe join, time values, and allowlisted bounded JDK
  helpers are admitted. Raw operation identity/context/request/receipt/snapshot/result types, target or
  revision values, result/components, toolkit, transport, execution, bus, database/repository,
  credential, filesystem/URL, generic object/map, stream/buffer, byte-array, and unbounded collection
  surfaces fail validation.

Wildcard families such as `CMD_REGION_*` are routing shorthand and are not treated as exact keys.
When a behavior depends on a specific command/event, cite the exact constant in inline code.

## Failure output

All reference and architecture-boundary issues are reported together in deterministic
category/document/line/reference order.
Categories identify the owning correction seam: Markdown link, inline/index path, Java package,
Java class/member, command/event key, schema seam, focused test, required Atlas document, or semantic
operation package/dependency/payload boundary. Bus observation violations use the distinct
`bus-observation-boundary` or `bus-observation-payload` category. Joined-operation violations use
`semantic-observation-boundary`, `semantic-observation-dependency`, or
`semantic-observation-payload`.

The failure ends with the command to rerun. It does not require wording, paragraph order, table prose,
or Mermaid snapshots to remain unchanged.

## Feature-task update workflow

1. Update the affected Atlas/product page in the same coherent body as the ownership or behavior
   change.
2. Keep stable Java owners/members, exact event/command constants, schema seams, and focused-test
   selectors in inline code so the validator can resolve them.
3. Update `AGENTS.index.md`, package documentation, and class documentation when their ownership
   route changes. When the Operations Atlas vocabulary changes, update its contract, schema,
   examples, and contradiction tests together; never weaken only one representation.
4. Run `validateDevelopmentArchitectureAtlas` offline with the supported Java 21 Gradle host and
   configured Java 25 project toolchain, then run the smallest behavioral tests for the feature.
5. If validation fails, change the stale documentation/reference or restore the missing owner. Do
   not weaken validation merely to preserve obsolete prose.

When adding a new Atlas companion Markdown file, place it in
`development-atlas/`; the validator discovers all Markdown pages in that
directory automatically. Add its navigation link to the named Atlas seat and `AGENTS.index.md`.


## TAS Database Viewer focused gate

The [Database Viewer verification section](<../design/Active Designs/Application Server/DATABASE-VIEWER.md>)
contains exact dependency-light commands and the additional configured-host JUnit selectors. The offline
worker proved production query/projection logic against synthetic SQLite, Node client behavior, schema
mappings and browser DOM/layout. It did not run the configured Java 25/Gradle/Atlas gate, native Xerial
locking/timeout checks, production HTTP/CSP loading or live deployment. Keep these truth states separate.
Do not run the broad suite, start TAS, inspect private databases or call providers to repeat offline proofs.
