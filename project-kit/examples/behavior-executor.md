# Small executor: a material pulse

Illustrative authoring example, not a native fixture, production module or working sandbox. A Morph
lamp (or a Metamorph-scoped indicator) binds `example.material-pulse` version `1.0.0` at an exact artifact
digest. Its typed configuration is `periodMs: 1000`, `low: 0.2`, `high: 0.8`, targeting the admitted
`lamp.emissiveIntensity` channel. Replacing that version's code is forbidden; revised code gets a new
version/digest. No API, filesystem, network, database or secret capability is requested.

Illustrative registry manifest fields:

```json
{
  "manifestVersion": "example/1",
  "id": "example.material-pulse",
  "version": "1.0.0",
  "artifactDigest": "REPLACE_WITH_VERIFIED_PACKAGE_SHA256",
  "language": "javascript",
  "hostAbi": "example.behavior/1",
  "entry": "pulse.js:step",
  "dependencies": [],
  "schemas": { "config": "pulse-config/1", "input": "pulse-step/1", "output": "pulse-proposal/1", "state": "pulse-state/1" },
  "hooks": ["step"],
  "writes": ["lamp.emissiveIntensity"],
  "composition": { "phase": "material", "order": 10, "conflict": "reject" },
  "requestedCapabilities": [],
  "requestedLimits": { "wallMs": 5, "memoryBytes": 1048576, "outputBytes": 1024 },
  "replay": { "mode": "deterministic", "clock": "host-logical-ms", "tolerance": 0.000001 },
  "provenance": { "author": "example author", "license": "project-approved license required" }
}
```

The placeholder digest/schema/ABI/license names make this non-installable. The package must supply real
schemas and provenance; the host selects and verifies enforceable limits and assigns the imported tier.
Config requires integer `periodMs > 0` and finite `0 <= low <= high <= 1`. Input supplies an admitted
instance, expected revision and safe integer `elapsedMs >= 0` in the declared clock. Output permits only
this channel, finite intensity in the configured range and a serializable integer state checkpoint.

```javascript
// Pure proposal function; host validates inputs and output and enforces execution limits.
export function step({ elapsedMs, subject, expectedRevision }, config) {
  const phase = (elapsedMs % config.periodMs) / config.periodMs;
  const triangle = 1 - Math.abs(2 * phase - 1);
  return {
    subject,
    expectedRevision,
    state: { elapsedMs },
    writes: [{ channel: "lamp.emissiveIntensity",
      value: config.low + (config.high - config.low) * triangle }],
    events: []
  };
}
```

At 250 ms, the proposed intensity is 0.5 for this configuration. The host accepts it only for the
admitted subject/channel and current revision. Two conflicting writers reject under this manifest;
the stage must deliberately supply a supported blend profile to combine them. Cancellation discards
late results and releases the binding through host lifecycle handling; this pure hook needs no resources
or custom cleanup. The host's logical clock/checkpoint preserves phase across pause/restore.

Persist the exact executor/configuration and accepted state, not a function closure. Replaying the same
clock/input reproduces the proposal within the declared tolerance. A UI may evaluate it on admitted
presentation steps, but no AI call occurs. Verify these properties in a real host before claiming support.
