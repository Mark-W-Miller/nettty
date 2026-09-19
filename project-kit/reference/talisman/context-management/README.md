# Talisman Context Management

Context Management is the shared foundation for contextual **Ask AI** and
**Open in TaliTalk** interactions across Talisman.

An interaction is not just “the selected object.” Its context is a hierarchy
derived from where the user is, what they are doing, the enclosing domain
objects, active participants and state, the selection, and the exact object on
which the interaction was invoked.

```text
Application area
└── Current activity
    └── Domain ancestry
        └── Participants and state
            └── Selection
                └── Explicit Ask AI target
```

This repository currently provides the UI-neutral core, composed once at application startup:

- typed, stable context identities;
- context requests that distinguish pointer, keyboard focus, selection, and
  the explicit invocation target;
- ordered providers owned by individual application areas;
- deterministic composition into immutable snapshots;
- disclosure filtering and aggregate snapshot/payload budgets across role
  references, nodes, omissions, questions, and handoff titles;
- identity-safe omission evidence and provenance without raw denied metadata;
- response contracts describing the permitted answer or action;
- a live Context Monitor model with structural diffs; and
- a TaliTalk handoff that preserves the captured starting snapshot; and
- a strict version-one canonical JSON payload for snapshot, Ask AI, and TaliTalk consumers;
- one application-lifetime contextual action coordinator with bounded owner-private evidence; and
- reusable lifecycle-safe Swing Ask AI/Open in TaliTalk actions and acknowledgement pop-up;
- pure bounded Assets, Adventure/Battle, and Object Factory context providers; and
- one bounded structured-proposal and explicit-confirmation coordinator that delegates final
  currentness and mutation authority to registered feature owners; and
- one reusable guarded screen surface that retains only opaque confirmation ownership and disposes
  pending confirmations when its owner closes.

See [the context contract](docs/CONTEXT-CONTRACT.md),
[the serialized payload contract](docs/APPLICATION-CONTEXT-PAYLOAD-V1.md),
[application integration](docs/APPLICATION-INTEGRATION.md), and
[the monitor/TaliTalk behavior](docs/MONITOR-AND-TALITALK.md).

## Build

The package uses the Java 25 toolchain to match Talisman. With Gradle hosted by
a supported JDK, run:

```shell
gradle test
```

The module is registered as the root `:context-management` subproject. The
Talisman Gradle 8 wrapper should be hosted by JDK 21; it will select the
installed Java 25 compiler through the declared toolchain.

## Status

The Java framework through CM-09 passed independent audit and landed. The module is registered,
`AppServices` owns one standard-policy `ContextService`, and the shared Monitors window passively displays
that service. The landed pilots cover one Created Things Character, Adventure/Battle scalar context, and
the guarded Object Factory Body Form surface. The two live Swing owners share the bounded proposal-
acceptance/lifecycle surface, but production mutation authority and identifiers remain absent.

The generic Shelf Context framework remains documentation only. GCS-01 is separately authorized to build
the narrower manual Control Screen Copy/Paste profile, but that browser adapter is not landed product
behavior yet. No generic browser SDK, provider transport, live-data mutation, or production action is
registered. Its Java-authoritative boundary, proposed v1 envelope family, Easy Tale mockup, and ordered
review-gated bodies live in the Context Management active-design folder. CM-09 does not change that
authority boundary.
