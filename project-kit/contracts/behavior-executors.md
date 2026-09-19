# Authored executable behavior modules

Morphs and Metamorphs may bind freely authored, exchanged executable behavior modules. JavaScript is
the primary implementation language for browser/Tassy behavior, with explicit host/runtime bindings.
Executors can implement motion generators, Intention/Path policies, interactions, procedural geometry,
material changes, sound synchronization, physics adapters and other declared behaviors. The registry is
extensible; new behavior is not restricted to a closed list of built-ins.

Canonical Morph/Metamorph data names a **versioned executor and typed parameters/events**. It does not
embed engine-private live state, opaque closures or arbitrary unsandboxed JavaScript as a database
execution contract. A source/module artifact can travel as a separate, integrity-pinned dependency;
storing or importing it does not authorize running it. See [Morph](morph.md), [Metamorph](metamorph.md),
[persistence](persistence.md) and the [small example](../examples/behavior-executor.md).

## Registry, manifest and binding

The host resolves an exact executor identity through a registry. Names/versions are immutable bindings
to content digests; never silently select latest or replace code under an accepted version. Record
publisher/author, origin, license, source provenance, dependency closure and review/admission outcome.
A familiar name, self-asserted trust field or signature alone does not confer execution authority.

| Manifest area | Required information |
| --- | --- |
| Identity | Namespaced executor ID, exact version, artifact digest, manifest format/version and provenance |
| Runtime | Language, supported host/ABI versions, declared module entry points, exact dependency identities/digests |
| Types | Parameter/configuration, input event/snapshot, output proposal/event and serializable state schemas/versions; units, coordinates and limits |
| Lifecycle | Supported hooks and their invocation/clock model; initialization, stepping, cancellation, restore/migration and disposal contracts |
| Composition | Read/write channel scope, phase, dependencies, ordering/priority, blend/reduction operators and conflict policy |
| Capabilities | Explicit requested operations and resource/target scope; no default grants; host-assigned execution tier |
| Resources | Maximum invocation/wall time, memory, output size, event rate, dependency depth and outstanding capability work |
| Replay | Deterministic scope/tolerances or explicit nondeterminism; engine/platform versions, clocks, seeds and external observation requirements |

A canonical binding records executor ID/version/digest, binding identity, typed configuration, event
connections and instance/Control Point or stage scope. The host resolves this to an approved manifest,
exact package closure and grants under the current principal/world. Registry lookup does not execute
code. Missing/incompatible dependencies or denied grants leave a visible unsupported/inactive binding.
Import/export preserves artifacts, manifests, typed configuration and provenance; activation remains a
separate local decision. Authors may exchange new executors freely without silently enabling them.

## Typed invocation and lifecycle

Proposed hook names are illustrative, not an implemented shared API:

- `initialize`: validate configuration and admitted scope; create bounded serializable initial state.
- `step` / `onEvent`: consume an immutable typed snapshot, prior executor state, logical clock and
  ordered admitted events. Return typed state/effect proposals, emitted events and capability requests.
- `checkpoint` / `restore`: serialize/validate versioned state and exact dependencies; restoration
  rechecks current authority and guards. `migrate` is explicit, reviewed, bounded and preserves provenance.
- `cancel` / `dispose`: stop owned work and release host handles. Cancellation must remain enforceable
  even when code does not cooperate; a cleanup hook cannot prolong the resource limit indefinitely.

The host checks hook output schemas, sizes, channel ownership, preconditions and exact revisions before
accepting effects. Executor output cannot grant permissions or settle game outcomes outside game rules.
Maintain proposal/accepted/rejected distinctions and event IDs; idempotent application cannot duplicate
durable effects. Do not expose mutable world/database/scene objects directly to a module. Late output
after cancellation, replacement, authority loss or a stale revision is rejected. Keep the last accepted
state on failure; report faults, rejected effects and already committed outcomes.

## Ordering and composition

Each stage/controller declares a versioned schedule: phase, stable event order, executor dependencies
and deterministic tie-breaks. Reject dependency cycles unless an explicit bounded iteration/solver
profile defines convergence and failure. Event feedback is queued to an admitted later phase/step with
fan-out/depth/rate bounds; no recursive unbounded event cascade.

Executors propose writes to declared channels. Resolve overlaps with explicit ownership, order,
priority or typed blend/reduction rules; reject ambiguous competing writers. Honor Motion composition
and physics' single resolved transform owner. A Metamorph-level executor can coordinate multiple Morph
instances only inside its approved scope and atomic update boundary; parent binding does not grant
unrestricted access to children, hidden state or another principal's actors.

## Capability-scoped, sandboxable execution

There is **no ambient filesystem, network, database or secret access**. Neither imported code nor its
dependencies may obtain host globals, credentials or live engine handles through the input boundary.
The manifest requests narrowly named capabilities; the host approves their operation, target, lifetime
and budget. Approval can be denied or revoked. Any external operation passes through a host broker that
validates scope/authority, logs request and outcome, enforces quotas, supports cancellation and returns
only typed permitted observations. Credentials stay in the broker; granting an operation is not granting
its underlying secret. Logs preserve audit identity without leaking secrets or unauthorized world data.

Host-enforced time/memory/output limits apply to loading, initialization, every hook and cleanup, plus
aggregate stage/world limits. Terminate or quarantine non-cooperating execution and cancel its pending
broker work. A returned timeout alone is not proof that execution stopped. CPU/memory isolation and
denial of ambient authority require an actual tested execution boundary; calling a module a sandbox or
passing a narrow argument object does not establish confinement. If the host cannot enforce a tier's
limits/capability isolation, reject that tier's activation rather than execute it with unrestricted access.

| Tier | Admission and execution |
| --- | --- |
| Trusted built-in | Host-maintained exact artifact with reviewed provenance; host approves specific grants. Same typed output, authority, cancellation, resource and audit contracts apply. Any privileged adapter remains behind the broker. |
| Imported/untrusted | Quarantined/inert on import; manifest/dependency/integrity review and host policy approval precede execution in an enforced isolated boundary. No ambient privileges or self-promotion to built-in status. |

Trust is assigned locally for the exact artifact and host policy; exporting a trust label cannot transfer
grants to another project/account. A version update rechecks integrity, compatibility and requested
capabilities. Freely authored does not mean automatically trusted, and saving a Morph must not run code.

## AI and physics boundaries

An executor can implement an Intention/Path policy or submit a typed decision request. It cannot bypass
the [AI scheduler](ai-runtime-decisions.md): eligible slots, minimal packets, singular API calls,
host-owned provider credentials, measured spending and hard tick/daily limits still apply. Modules must
not call AI on render or physics ticks. Deterministic motion/geometry/material/sound hooks may run under
their admitted clocks without inference; material events enqueue later decision work through the host.

Physics modules remain [engine-neutral adapters](physics.md) at their canonical boundary, with explicit
physical state, Place bindings, transform ownership and reproducibility declarations. Authoring a module
does not prove a physics engine, native codec, atomic coordinator or audio/renderer binding exists.

## Persistence and replay

Persist executor identity/version/digest, typed configuration, schema versions and resulting accepted
durable state, plus exact input/event/clock/seed and external-result references needed by the replay
profile. Engine caches, live handles, closures and runtime objects remain disposable. Declared checkpoints
must reconstruct from serializable state; opaque closures cannot be the only saved program or memory.

Deterministic modules declare supported host/platform and numeric tolerance. They use supplied clocks,
ordered inputs and recorded randomness; undeclared wall-clock or random sources invalidate that claim.
Explicitly nondeterministic modules retain outputs/observations for playback and cannot occupy a channel
requiring deterministic re-execution without an accepted recorded-output adapter. AI proposals and
broker results are replay inputs; replay must not repeat network requests, charges or durable writes.
Migrating code/state creates a new pinned revision and acceptance record, not a silent reinterpretation.

## Required proof

Demonstrate registry integrity and dependency closure, typed rejection, hook cancellation including a
non-cooperating module, enforced memory/time limits, denied ambient capabilities, approved broker audit,
deterministic composition/conflict handling, restore/migration, replay and import-without-execution.
The example is documentation only. No executor runtime, sandbox, module admission or consumer upgrade
is implemented or certified by this release.
