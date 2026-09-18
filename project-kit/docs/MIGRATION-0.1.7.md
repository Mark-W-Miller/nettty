# Adopt MPK 0.1.7

This adds freely authored executable behavior modules for Morphs and Metamorphs. Preserve the published
0.1.6 AI architecture correction and prior release archives; the executor addition arrived after 0.1.6
publication. See [executor contract](../contracts/behavior-executors.md),
[example](../examples/behavior-executor.md) and [0.1.6 migration](MIGRATION-0.1.6.md).

- Bind canonical data to exact executor ID/version/digest plus typed configuration/events and scope.
  JavaScript is the primary browser/Tassy language; host ABI/codec support remains explicit work.
- Define registry/manifests, provenance and dependency integrity, input/output/state schemas, lifecycle,
  deterministic schedule/composition and replay declarations. Exchange/import remains separate from run.
- Establish trusted built-in versus imported/untrusted policy. Deny ambient filesystem/network/database/
  secret access. Approve only explicit broker capabilities with scope, logging, budget and cancellation.
- Prove actual enforcement of time/memory/output limits and termination of non-cooperating modules.
  If the host cannot enforce the required boundary, keep imported code inactive. Trust labels do not
  transfer privileges across projects or survive artifact changes without review.
- Persist configuration and accepted serializable durable state; restore from exact dependencies with
  fresh authority checks. Do not store closures or engine-private handles as canonical runtime truth.
- Preserve AI scheduler limits and the ban on inference from render/physics ticks. Module proposals
  still require game authority and checked commits; replay cannot repeat external effects or spending.
- Return repository-level version/digest adoption evidence under existing local guidance. No automatic
  consumer upgrade, module activation, sandbox certification or database change follows from this release.

The small example uses placeholder manifest/schema identities and is not an installable native fixture.
