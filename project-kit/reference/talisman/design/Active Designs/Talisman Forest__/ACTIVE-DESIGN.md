# Talisman Forest active design

**Permanent parent owner:** Talisman Forest__

**Current body:** TF-04 follow-up — Morris Roman Black for web and Java

**Current status:** ACTIVE

## TF-04 source and delivery

The owner reconciled the browser body onto current Main `287e12fdb` on 2026-09-08.
The exact published body and landing/equality state are reported through Switchboard.
No product-screen conversion or managed application launch is included.

## Historical TF-03 source identity

- Source input: `talisman-main-for-talisman-forest-tf-03-current-main-2026-09-07-162a9cc48.zip`
- Archive revision: `162a9cc48a3db0704672c11ccab9fefc85d129ca`
- Source ZIP SHA-256:
  `9de05dc490b94d19640c4eae682321e9a46f589689cb4baf7bfb95be71aa50fe`
- ZIP CRC verification: passed before TF-03 work began.
- Root layout: one `talisman-main/` root.
- TF-02 is landed/equal at `3c7b247ac5bc1c84e8924815f1dd8f787815d648` in this source.

## Controlling ordered body ledger

Exactly one body is ACTIVE. The strict dependency order below is mandatory.

1. **TF-01 — DONE / LANDED / EQUAL — Visual contract and Java theme gallery.**
2. **TF-02 — DONE / LANDED / EQUAL — Application theme registration.**
   Landed at `3c7b247ac5bc1c84e8924815f1dd8f787815d648`.
3. **TF-03 — DONE / LANDED / EQUAL — Shared Java presentation adoption.**
   Landed and equal at `896b22679`; Forest 20/20 and Atlas 15/15 passed.
4. **TF-04 — LANDED / EQUAL — Shared browser theme seam; font follow-up ACTIVE.**
   Switchboard narrowed this body to the public theme dependency needed by GCS-01.
   See [Browser theme contract](BROWSER-THEME-CONTRACT.md); no screen-specific conversion.
5. **TF-05 — QUEUED — Cross-product review and hardening.**
6. **TF-DEFAULT — QUEUED — Default-theme decision.**

## TF-03 purpose

TF-03 makes the accepted semantic appearance roles the shared presentation path for Java chrome and
common Swing surfaces. `TalismanThemeInstaller` remains the registration authority;
`TalismanThemeTokens` supplies stable role names; existing shared menu, status, control, scrolling,
splitter, selection, focus, disabled, and custom-window-chrome presentation consumes those installed
roles. This body does not convert a product screen and does not change command, lifecycle, Shelf, or
domain authority.

The existing custom-window path retains its installation decision and window behavior. Its paint now
uses registered semantic roles (with native Swing defaults as pre-install fallbacks) instead of a
second embedded RGB palette. The canonical menu builder remains window-argument driven, so a focused
menu-less popup cannot change top-menu construction. `ApplicationStatusLine` consumes semantic status
surface, foreground, and border roles while retaining Activity Monitor ownership and subscription
behavior.

The TF-01 asynchronous contract remains presentation-only: stable control width, in-control spinner,
specific active verb, duplicate suppression, BUSY accessibility, and equivalent live status. TF-03
does not start workers or take command/lifecycle authority.

## TF-03 global visual direction

Talisman Forest owns a reusable semantic-accent vocabulary for shared browser/desktop presentation.
Shared components and review surfaces should use purposeful color to clarify hierarchy and state rather
than falling back to mostly gray wireframes or gray-on-gray chrome. Bold headings remain part of the
accepted direction. Feature teams consume semantic roles instead of inventing local RGB values.

The shared role set now distinguishes owner, Shelf, Box, selection, active, waiting, complete, error,
three reusable role classes, and related groups. Color is additive evidence only: labels, state words,
icons, borders, busy text, and accessibility metadata remain the primary semantic signals. Text stays
strong enough for normal reading; tiny faint gray labels are not an accepted Forest pattern.

TF-03 applies these roles only where shared presentation already owns appearance: menu selection, base
status surface/border, control-bar tint/border, Shelf/Shelf Label/Shelf Box appearance tokens, and the
Forest review gallery. It does not restyle a product screen, change Shelf behavior, or make Forest the
default theme.

## Landed TF-02 registration record


TF-02 makes the landed Forest appearance a normal selectable application theme. It follows the
existing typed route rather than creating a parallel registry or preference key:

1. `TalismanTheme.TALISMAN_FOREST` is the persisted identifier and provides the user-facing label.
2. Settings continues to enumerate `TalismanTheme.values()` and therefore includes Forest once.
3. `TalismanTheme.save` writes the enum name through the existing `UserPrefs` YAML authority.
4. `TalismanTheme.load` restores Forest from a fresh preferences instance.
5. `TalismanApp` continues to load the selected value and pass it to `TalismanThemeInstaller` during
   startup.
6. `TalismanThemePalette.forTheme` maps Forest to the landed palette used by the normal installer.
7. Missing, retired, malformed, and unknown values continue to fall back to Midnight Slate.
8. Automated headed screen fixtures explicitly install Forest and restore prior Swing defaults.

The Forest identifier is appended to the enum so the order and ordinals of existing themes remain
unchanged. Persistence remains name-based. Forest is not the default.

## Ownership boundary

### Talisman Forest and Themes own

- the typed Forest theme identity, label, palette mapping, and normal Swing installation;
- semantic surfaces, text, borders, focus, selection, status, and window-chrome appearance;
- control, spacing, typography, density, and asynchronous-presentation tokens;
- visual states for Shelf, Shelf Label, and Shelf Box presentation; and
- focused registration, persistence, fallback, gallery, and appearance proof.

### Settings and application startup retain their existing route

`TalitalkSettingsDialog` continues to own selection and Apply behavior. `UserPrefs` remains the
single durable application-preference authority. `TalismanApp` retains startup ordering and installs
whatever `TalismanTheme.load` returns. TF-02 adds no second settings model, file, registry, or startup
path.

### Screen-showing test acceptance

Automated tests that construct or display a real Talisman Swing screen, window, dialog, or popup use
`@ForestScreenTestTheme`. Its test-only JUnit extension calls
`TalismanThemeInstaller.install(TalismanTheme.TALISMAN_FOREST)` before the opted-in headed fixture
constructs UI. The extension snapshots effective Swing defaults changed by that installation and
restores those values after each test so Forest does not leak into a later fixture.

The rule does not change truly headless tests, disabled/manual debug routes, or semantic theme tests
that intentionally install another exact theme for their assertion. Mixed screen-test classes may
still install a specific theme inside a semantic test; that explicit semantic install remains the
asserted theme. This is test acceptance only and is not TF-03 or TF-04 production adoption.

### Shelf retains behavior and composition ownership

Shelf owns item and box identity, order, orientation, nesting, overflow, targets, pointer and keyboard
behavior, drag, divider, open, close, detach, reattach, state derivation, selection, focus movement,
enablement, accessibility interaction semantics, and feature command routing.

`TalismanShelfPresentation` remains appearance-only. TF-02 does not change Shelf listeners, models,
commands, composition, or persistence.

### Other authorities remain unchanged

TF-02 creates no service, provider, Application Server, process-bus, database, or domain-persistence
route. Theme selection is a user preference, not semantic operation acceptance or domain truth.

## Forest palette and preview relationship

`TalismanThemePalette` now owns the landed Forest foundations:

- surface / raised / recessed / input: `#11181B`, `#1D282C`, `#0B1113`, `#0E1517`;
- control / hover: `#243B49`, `#315468`;
- text / muted / disabled: `#F3F4EE`, `#B1BEC2`, `#748186`;
- border / dark / control / highlight: `#52616C`, `#071012`, `#75A7C8`, `#D7E8F2`;
- focus: `#77BCFF`;
- selection / selection text: `#183936`, `#F3F4EE`;
- warning / error / success: `#F0BD7E`, `#FF9AA4`, `#76D4CA`;
- owner / Shelf / Box / selection accents: `#7ED7FF`, `#F0C674`, `#C09AFF`, `#FF8FCB`;
- active / waiting / complete / error accents: `#6FA8FF`, `#F0BD7E`, `#76D4CA`, `#FF9AA4`;
- role A / role B / role C / related-group accents: `#8FD694`, `#D6A6FF`, `#F6D365`,
  `#FFB86B`; and
- window accent: `#F0C674`.

The normal installer consumes this palette through the same switch used by every existing theme.
The TF-01 gallery preview also reads that typed palette, then adds its richer gallery-only semantic
keys and restores all touched defaults on close. The preview does not save or change the selected
application theme.

## Asynchronous activation visual contract

The landed TF-01 appearance contract remains controlling. An asynchronous initiating control must:

1. acknowledge activation in the initiating UI turn;
2. retain the same minimum, preferred, and maximum width in idle and active presentation;
3. keep the busy spinner inside the initiating control;
4. replace the idle label with a specific active verb, such as `Save changes` to `Saving…`;
5. disable duplicate activation before the initiating handler returns;
6. retain visible busy feedback until terminal state or actionable failure replaces it;
7. expose equivalent accessible busy and live status using the same operation wording; and
8. remain understandable without relying on color or spinner animation alone.

The gallery still demonstrates equal-width `Save changes` and `Saving…` controls, an in-control
spinner, duplicate suppression, accessible BUSY state, and a matching live `STATUS_BAR`. TF-02 does
not move lifecycle, threading, cancellation, or result authority into theme code.

## Gallery and visual evidence

The TF-03 gallery keeps the typed application-theme selector and now adds an explicit semantic-accent
review section plus active/waiting/complete/error status cards. Owner, Shelf, Box, selection, role,
group, and state accents are always paired with words and borders. The gallery renders at 1320 by
1580 pixels so the added proof is visible without shrinking text.

Live launch:

```text
./gradlew talismanForestGallery
```

Offscreen evidence:

```text
./gradlew talismanForestGallery \
  -Dtalisman.forest.gallery.snapshot=build/talisman-forest-gallery.png
```

## Implementation-friction report

### Existing abstraction that helped

The existing path already joined `TalismanTheme.values()`, `TalismanTheme.save`,
`TalismanTheme.load`, `UserPrefs`, `TalismanThemePalette.forTheme`, and
`TalismanThemeInstaller.install`. Adding one enum identity and one exhaustive palette branch made
Settings enumeration, Apply, durable YAML storage, fresh reload, and startup installation use Forest
without a Settings or startup refactor.

### Difficult or unclear boundary

TF-01 intentionally kept its palette private to the restoring preview seam. TF-02 needed one Forest
palette authority without moving the gallery-only spacing, typography, Shelf, and asynchronous keys
into the normal installer before TF-03. The narrow boundary is to move only the shared color and
chrome foundations into `TalismanThemePalette`, reuse them from the preview, and leave the richer
preview tokens isolated.

The screen-fixture rule spans packages that do not share a test base class. Installing Forest inline
in every method would duplicate global Swing-state handling and risk leaks between tests. The narrow
test-only solution is one JUnit extension plus explicit class opt-in for audited automated fixtures.
The extension restores only defaults changed by the Forest install. Intentional exact-theme tests
keep their own explicit installs and are not rewritten into Forest assertions.

### Visual-direction friction

The accepted TF-01/TF-02 palette already had strong focus/warning/error/success colors, but it lacked a
shared vocabulary for owner, Shelf, Box, selection, role, and related-group accents. Reusing the same
few colors everywhere would not satisfy the new hierarchy requirement, while embedding new colors in
individual screens would create exactly the disconnected local palettes TF-03 is intended to prevent.
The narrow solution is one Forest-owned semantic-accent record in `TalismanThemePalette`, stable keys in
`TalismanThemeTokens`, and shared installer/preview publication.

The second friction point was preserving accessibility while increasing color. Bright accents are used
as borders, indicators, and labeled emphasis against dark Forest surfaces; foreground copy remains the
existing high-contrast text role. Focused proof requires each bright accent to meet at least 4.5:1
contrast against the Forest base surface, and the gallery never uses color without a textual state or
role label.

## Focused proof

TF-03 proves:

- shared menu, status, control-bar, Shelf, Box, selection, focus, scrolling, splitter, and window-chrome
  presentation consume registered theme roles rather than screen-local colors;
- the Forest accent vocabulary is complete, pairwise distinct, and readable against the base Forest
  surface at a minimum 4.5:1 contrast ratio;
- owner, Shelf, Box, selection, active, waiting, complete, error, role, and related-group accents are
  represented in the deterministic review gallery with words and borders;
- bold section/headline typography and strong body text remain intact;
- the application fallback top menu remains canonical even while a menu-less popup owns focus;
- custom window chrome consumes installed roles without changing native-stoplight policy or window
  lifecycle/interaction ownership;
- the TF-01 asynchronous presentation contract remains stable-width, in-control, operation-specific,
  duplicate-disabled, and accessibility-equivalent; and
- the TF-02 screen-test Forest extension still installs/restores Swing defaults in isolation.

## Explicit exclusions

TF-03 does not:

- make Forest the application default or change the Midnight Slate fallback;
- convert or restyle a TF-04 product screen;
- change Shelf interaction, composition, state derivation, commands, ordering, orientation, persistence,
  or behavior;
- change domain services, providers, process bus, database, persistence, or application semantics;
- add canonical-test exclusions, broad refactors, or unrelated cleanup;
- run a broad or full test suite; or
- begin TF-04.

## Questions reserved for the parent owner

None.

## Permanent owner intake — 2026-09-07

The audited reconciliation bundle and patch matched their supplied SHA-256 identities. The permanent
owner applied the presentation body onto current Main at `f2b821e8c` while preserving newer task-log
entries, corrected Color/Locale imports, and audited all added Java conventions. This owner's design
folder is now `Talisman Forest__`, matching its permanent pinned title. Exact focused results are
recorded in TEST-PLAN.md; Main landing and branch equality remain separate completion gates.

TF-04 scope and proof now live in BROWSER-THEME-CONTRACT.md and the cumulative TEST-PLAN.md.
