# Adopt MPK 0.1.6

This corrects 0.1.5's AI architecture framing. Moondance deliberately uses AI at bounded runtime
decision points. Optional natural-language authoring does not describe the whole runtime. Deterministic
Morph, Place and game machinery executes accepted typed state between calls. Preserve earlier release
archives, root guidance, local supplements, saved worlds and existing identity/console/physics boundaries.

- Read [AI runtime decisions](../contracts/ai-runtime-decisions.md) and the reconciled
  [campaign](../contracts/campaign-simulation.md), [behavior](../contracts/behavior.md) and
  [Derek example](../examples/derek-campaign-heartbeat.md).
- Identify eligible/sleeping actors and version the relevance, elapsed-time, proximity and importance
  policy. Define explicit GM-authorized heartbeat scope and next-due/backlog reporting.
- Bind each decision slot to a minimal typed packet and one direct singular API call. Disable hidden
  retries/tools; validate structured proposals and commit accepted state against current revisions.
- Define stable rules/schema prefix serialization and dynamic state tail. Treat caching as measured
  computation reuse, separate from durable actor memory or retained conversation context.
- Inventory provider/model usage and pricing support. Record ordinary uncached input, cache writes,
  cached reads, output, latency, model and cost per attempt and heartbeat. No assumed free cache or
  inferred zero usage. Retain raw usage and explicit unknown/reconciliation status.
- Implement atomic worst-case reservations and hard per-call, world-tick and daily world/account
  limits before dispatch; include failed/stale calls, concurrent worlds and unknown timeout costs.
  Specify day boundaries, recovery and budget exhaustion. Documentation adoption does not authorize spend.
- Prove deterministic execution between AI calls and replay using retained proposals/draws. Forbid
  calls from rendering/physics ticks. Keep deferred AI decisions visible when budget/connectivity is absent.
- The adoption unit is one Git repository shared by its branches. Record exact checkout revision and
  Kit digest without treating branches as additional consumer projects. Return a deliberate adoption
  receipt under the repository owner's authority.

Existing per-item save APIs do not establish atomic campaign commits or a spending coordinator.
Unsupported capabilities remain explicit implementation gaps. This release makes no consumer adoption,
AI execution/cache benchmark, database update, runtime deployment or browser acceptance claim.
