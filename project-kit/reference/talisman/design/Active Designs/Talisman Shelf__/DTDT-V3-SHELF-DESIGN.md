# DTDT Language Version 3 Shelf Design

Status: reviewed successor design with TSR-03, TSR-04, and TSR-06 amendments; no runtime authorized

Owner: Talisman Shelf__

## 1. Decision

DTDT language version 3 is the sole declarative language for Talisman presentation structure. DTDT v1
and v2 are removed during the application cutover; the final parser, application declarations, tests,
fixtures, and production documentation contain only v3.

DTDT v3 uses exactly three Shelf product nouns:

- **Shelf**: an organizing presentation that owns ordering and open-box behavior.
- **Shelf Label**: the condensed, interactive representation of one Shelf Box on its Shelf.
- **Shelf Box**: the expanded content revealed by opening its Shelf Label.

A Shelf Box may contain another Shelf. That recursion is the complete nesting model. `Shelf Item`,
generic `Box`, `Shelf Workspace`, `Shelf Group`, `surface`, `Focus Workspace`, `Focus Bar`, and `Focus
Rail` are not DTDT v3 product vocabulary.

Implementation classes may use toolkit-specific host or layout names privately. Those names do not enter
the DTDT language, user-facing text, shared behavior contract, accessibility text, or adapter interface.

### 1.1 Adjudicated contract hierarchy

The permanent Talisman Shelf Redesign owner is the Shelf contract adjudicator. Other product, Workboard,
Application Server, Atlas, and patch tasks submit observed behavior, constraints, and proposals. Those
inputs become shared Shelf law only after this owner reconciles them into the canonical contract.

The resulting hierarchy is:

1. Shelf Contract v1 is frozen historical compatibility and is never reinterpreted.
2. Shelf Contract v2 is the truthful current additive browser/DTDT implementation and may gather
   transitional product evidence.
3. DTDT v3 is the normative final Shelf language. It deliberately admits useful v2 behavior, including
   presentation-memory scope, while replacing v1/v2 declaration shape and removed vocabulary.

A product-local adaptation may remain valid for that product without becoming a shared contract. Conflicts
are decided here before a shared schema, fixture, adapter, or runtime is changed.

## 2. Adoption order

1. Land this reviewed successor design while retaining frozen v1 and current additive v2 truth.
2. Add the neutral DTDT v3 schema, validator, examples, and replacement conformance vectors when separately
   authorized. Historical v1 evidence remains immutable rather than being reinterpreted as v3.
3. Adapt Workboard first to the checked v3 declaration and shared vectors, then review the result.
4. Migrate Object Sing & Dance Factory from its current transitional v2 declaration to the same v3
   declaration and vectors.
5. Cut the Java application, DTDT parser, declarations, tests, and documentation to v3 only.

Object Factory's current v2 browser adoption is valid transitional evidence. It does not reverse the order
for v3 adoption and must not be described as a v3 implementation.

## 3. Hard cutover

DTDT v3 is a replacement, not an additive language layer.

- Every accepted document declares `dtdtVersion: 3`.
- A missing version, version 1, or version 2 is invalid after cutover.
- The shipped application has no legacy parser branch, alias, builder, fixture, or declaration.
- Every tracked split, stack, tab, control, leaf, and Focus declaration is rewritten in v3 or removed.
- An offline migration tool may create reviewed v3 candidates, but it is not runtime compatibility.
- Migration reports every declaration and every semantic choice; it never silently guesses.

The cutover must leave a runnable application. The parser cannot reject old declarations while tracked
application roots still require them. The authorized implementation therefore migrates declarations,
parser, builders, tests, help, and dead code as one coherent change or as gated changes whose final state
is v3-only.

This design body changes no parser or runtime source. Until implementation is commissioned, Atlas entries
describing the current v1/v2 runtime remain factual descriptions of code that is marked for removal.

## 4. Language role and authority

A DTDT v3 Shelf declaration owns reconstructible presentation structure:

- stable Shelf, Shelf Label, Shelf Box, focus, accessibility, and content identities;
- definition order and allowed sort modes;
- exact open-box occupancy policy and scope;
- standard per-Shelf-Box Takeover controls and exact return-state restoration;
- pinned leading Close plus allocation-axis Expand/Restore presentation controls;
- Shelf Label and Shelf Box open/close parity;
- purposeful Talisman Forest visual-semantic roles and state distinctions;
- presentation persistence identity and definition revision;
- responsive layout and overflow policy;
- bounded nesting; and
- read-only feature projections and content references.

It does not own feature work. It contains no executable JavaScript, Java, CSS, Swing, or JavaFX code; no
service instance, toolkit object, path, credential, token, or provider configuration; and no database,
queue, cancellation, accepted-operation, or semantic-result authority.

Shelf actions are local checked presentation actions. A Shelf Box content reference continues through its
existing typed Behavior, adapter, service, revision, persistence, cancellation, and result owner.

## 5. Root form

A Shelf is an ordinary DTDT v3 component. A screen containing several Shelves composes several Shelf
components with the normal v3 layout components; it does not introduce a `Shelf Group` abstraction.

```yaml
dtdtVersion: 3
component:
  type: Shelf
  id: WORKBOARD.SHELF.FEATURES
  definitionRevision: 1
  persistenceOwner: WORKBOARD.SHELF.PRESENTATION
  presentationMemory:
    policy: BROWSER_LOCAL
  visualSemantics:
    themeFamily: TALISMAN_FOREST
    shelfAccentBinding: WORKBOARD.VISUAL.SHELF.FEATURES
    hierarchy:
      shelfHeadingWeight: BOLD
      boxHeadingWeight: BOLD
      importantLabelWeight: BOLD
    stateTokens:
      selected: TALISMAN_FOREST.STATE.SELECTED
      active: TALISMAN_FOREST.STATE.ACTIVE
      waiting: TALISMAN_FOREST.STATE.WAITING
      warning: TALISMAN_FOREST.STATE.WARNING
      complete: TALISMAN_FOREST.STATE.COMPLETE
      error: TALISMAN_FOREST.STATE.ERROR
      disabled: TALISMAN_FOREST.STATE.DISABLED
    accessibility:
      normalTextMinimumContrast: 4.5
      largeTextAndGraphicsMinimumContrast: 3.0
      stateCue: TEXT_AND_NON_COLOR_MARKER
  maximumNestedDepth: 1
  sort:
    allowed: [FIXED, RECENT_USE, ACTIVITY, NAME, MANUAL]
    initial: FIXED
    recentUseLimit: 32
  openBoxes:
    mode: MULTI_OPEN
    ordering: NEWEST_NEAREST_OPENER
    allocation:
      single: FILL_AVAILABLE
      multiple: PREFERRED_COMPARISON_EXTENTS
      expanded: FILL_REMAINING
  layout:
    orientation: HORIZONTAL
    visibleLabelRows: 2
    labelOverflow: VERTICAL
    boxOverflow: HORIZONTAL
    minimumBoxMainAxis: 260
    labelRegionBoundary:
      id: WORKBOARD.SPLITTER.LABELS-TO-OPEN-BOXES
      kind: LABEL_REGION_TO_OPEN_BOX_REGION
      orientationSource: SHELF_ORIENTATION
      movementAxis: SHELF_CROSS_AXIS
      labelRegionAnchor: LEADING
      resizedRegion: LABEL_REGION
      preferredCrossAxisExtent: 112
      minimumCrossAxisExtent: 72
      maximumCrossAxisExtent: 240
      collapse: FORBIDDEN
      reset: PREFERRED_EXTENT
      persistence:
        scope: PRESENTATION_MEMORY_POLICY
        orientationProfiles: INDEPENDENT
        fingerprint: DEFINITION_FINGERPRINT
      accessibility:
        nameKey: workboard.shelf.labels-to-open-boxes.accessible
        controlledRegionIds:
          - WORKBOARD.REGION.SHELF-LABELS
          - WORKBOARD.REGION.OPEN-SHELF-BOXES
        keyboardStep: 8
  labels: []
```

`definitionRevision` is a positive owner-controlled schema revision. A validating adapter computes the
canonical definition fingerprint from the complete normalized declaration. Owners do not type or update
that fingerprint by hand.

`persistenceOwner` identifies the one presentation-state owner. `presentationMemory` declares where that
owner may restore validated state. Neither field identifies a feature service, database owner, job, draft,
server session, or domain aggregate.

## 6. Shelf declaration

| Field | Meaning |
| --- | --- |
| `id` | Stable Shelf identity. |
| `definitionRevision` | Positive owner-controlled declaration revision. |
| `persistenceOwner` | Stable presentation-state owner. |
| `presentationMemory` | Explicit browser-local or authenticated-user context restoration route. |
| `visualSemantics` | Forest theme family, hierarchy, status tokens, contrast, and cue requirements. |
| `maximumNestedDepth` | Maximum Shelf levels permitted below this Shelf. |
| `sort` | Allowed and initial Shelf Label ordering. |
| `openBoxes` | Open Shelf Box occupancy, chronology, and cooperative allocation. |
| `layout` | Axis, overflow, minimum Shelf Box geometry, and the label/open-box boundary. |
| `labels` | Non-empty ordered Shelf Label declarations. |

All stable IDs in one loaded document are globally unique. A Shelf Label belongs to exactly one Shelf and
owns exactly one Shelf Box. A nested Shelf has its own Shelf and persistence-owner identities.

A runtime publishes its supported nesting depth before admitting a document. The root declaration's
`maximumNestedDepth` is checked against the deepest nested Shelf below it; nested values cannot exceed the
remaining root allowance. A declaration requiring greater depth rejects completely and is never partially
flattened. The first adoption profile supports depth one. A later reviewed Factory profile may raise the
supported depth without changing vocabulary.

## 7. Sort declaration

The only sort values are `FIXED`, `RECENT_USE`, `ACTIVITY`, `NAME`, and `MANUAL`.

- `sort.allowed` is non-empty and contains `sort.initial`.
- `recentUseLimit` is required only when `RECENT_USE` is allowed.
- Initial `MANUAL` requires one complete `initialManualOrder` containing every Shelf Label once.
- An automatic mode never persists a Manual order before an accepted transition to `MANUAL`.
- The stable Shelf Label ID is the final tie-break after the mode-specific comparison.

A successful pointer or keyboard reorder from an automatic mode atomically materializes the visible
order, applies the move, changes the Shelf to `MANUAL`, and persists once. Cancellation changes no mode,
order, revision, focus, announcement, or bytes.

## 8. Open Shelf Box declaration

The only open-box modes are `MULTI_OPEN` and `EXCLUSIVE_REPLACE`. Takeover is a standard optional
Shelf Box control and presentation state, not an open-box mode. It never changes the declaring Shelf's
ordinary occupancy policy.

Every Shelf also declares one Shelf-owned open-box ordering policy. It is not a Shelf Label or Shelf Box
setting:

- `NEWEST_NEAREST_OPENER` is the default. The most recently opened Shelf Box appears nearest the Shelf
  Labels: leftmost for a horizontal Shelf and topmost for a vertical Shelf.
- `NEWEST_FARTHEST_FROM_OPENER` puts the most recently opened Shelf Box farthest from the Shelf Labels:
  rightmost for a horizontal Shelf and bottommost for a vertical Shelf.

Closing a Shelf Box removes its identity from open chronology. Reopening it makes it newest. Refresh and
valid same-session restoration preserve the exact checked chronology. Shelf Label order and open-box
chronology are independent: reordering labels does not silently rewrite chronology. A nested Shelf remains
adjacent to its owning content boundary and applies its own declared ordering policy.

Open-box allocation is also Shelf-owned and independent of the label/open-box boundary:

- `FILL_AVAILABLE`: one visible Shelf Box fills the available open-box region.
- `PREFERRED_COMPARISON_EXTENTS`: several ordinary open Shelf Boxes keep their bounded preferred main-axis
  extents for comparison.
- `FILL_REMAINING`: one explicitly expanded Shelf Box receives remaining main-axis space while every other
  open peer remains present at its ordinary preferred extent.

Expanding one Shelf Box restores any previously expanded peer to its ordinary extent before expanding the
new identity. It never closes, hides, reconstructs, or displaces another open peer. When the available main
axis cannot satisfy checked minima, the declared open-box overflow policy applies instead of violating
minimums or changing occupancy.

### 8.1 Multi-open

```yaml
openBoxes:
  mode: MULTI_OPEN
```

Opening one Shelf Box does not close another open Shelf Box on that Shelf. Visible Shelf Boxes follow the
Shelf's declared open-box chronology filtered to open identities; Shelf Label order remains independent.

### 8.2 Exclusive replacement

```yaml
openBoxes:
  mode: EXCLUSIVE_REPLACE
  replacementPeers:
    - FACTORY.SHELF-LABEL.BATCH
    - FACTORY.SHELF-LABEL.GALLERY
    - FACTORY.SHELF-LABEL.CREATURE
```

`replacementPeers` is a complete, non-empty set of Shelf Labels on the declaring Shelf. Opening one peer
atomically opens its Shelf Box, closes the other peer Shelf Boxes, moves focus to the opened content, and
announces the replacement. It does not cancel or reset feature work.

### 8.3 Per-Shelf-Box Takeover

```yaml
box:
  id: WORKBOARD.SHELF-BOX.FEATURE
  takeoverControl:
    kind: STANDARD_TAKEOVER
    labelControlId: WORKBOARD.CONTROL.FEATURE.TAKEOVER
    boxHeaderControlId: WORKBOARD.CONTROL.FEATURE.RETURN-TO-GROUP
    labelPlacement: TRAILING
    boxHeaderPlacement: TRAILING
    appearance:
      shape: CIRCLE
      outerFill: WHITE
      centerFill: GREEN
    accessibility:
      enterNameKey: workboard.feature.takeover.accessible
      returnNameKey: workboard.feature.return-to-group.accessible
      enterAnnouncementKey: workboard.feature.takeover.announcement
      returnAnnouncementKey: workboard.feature.return-to-group.announcement
      closeAnnouncementKey: workboard.feature.close-from-takeover.announcement
```

When `takeoverControl` is present, the Shelf Label exposes the standard trailing Takeover dot: a white
circular control with a green center. It is a separate control from ordinary Shelf Label activation. The
open Shelf Box header exposes the same dot at its trailing edge; while takeover is active its accessible
action is **Return to group**. Ordinary Close remains pinned at the header's inline leading edge and never
moves beside or merges with Takeover.

Activating the Shelf Label dot performs one checked atomic transition:

1. snapshot the exact ordinary open Shelf Box set, open chronology, and expanded Shelf Box identity;
2. open the target Shelf Box when needed, making it newest only when it was previously closed;
3. set that Shelf Box as the declaring Shelf's one active takeover identity; and
4. visually occlude every other open peer without closing, disposing, reconstructing, reordering, or
   cancelling it.

The active Shelf Box occupies the Shelf's open-box region because takeover controls visibility. It does not
set, clear, or impersonate the independent `FILL_REMAINING` expanded identity. The saved group continues to
own its original allocation state beneath the occlusion.

Activating the Shelf Box header dot performs **Return to group** atomically. It clears takeover, restores
the exact saved peers and chronology, restores the saved expanded identity, and keeps the target Shelf Box
open. If the target was closed in the saved group, it joins the restored chronology as the newest opening.
Activating the leading Close `×` clears takeover, closes the target, and restores the saved peer group;
the target is excluded even if it had been open in the saved snapshot. A valid non-target expanded identity
is restored, while a saved expanded identity naming the closed target resolves to no expanded Shelf Box.

The label and header dots are two presentations of one logical Takeover control. Their pressed state is
derived from whether their Shelf Box is the active takeover identity: false in the ordinary group and true
during takeover. One Shelf admits at most one active takeover identity and one return snapshot. A nested
Shelf owns an independent state scoped to that nested Shelf and never occludes its parent Shelf or sibling
Shelves.

Entering takeover moves focus to the header **Return to group** control and announces that the target took
over while its peers remain open. Return moves focus to the target's trailing label control and announces
that the group was restored. Close returns focus to the target Shelf Label and announces that the target
closed and the group was restored. A rejected dirty-close guard, stale control identity, invalid snapshot,
or unavailable focus target changes no state, pressed state, announcement, or persisted bytes.

## 9. Shelf Label and Shelf Box declaration

```yaml
- id: FACTORY.SHELF-LABEL.CREATURE
  titleKey: factory.shelf-label.creature
  definitionPosition: 30
  statusBinding: FACTORY.CREATURE.STATUS
  activityRankBinding: FACTORY.CREATURE.ACTIVITY-RANK
  visualRoleBindings:
    ownerAccent: FACTORY.VISUAL.OWNER.OBJECT-FACTORY
    roleAccent: FACTORY.VISUAL.ROLE.CREATURE
    relatedGroupAccent: FACTORY.VISUAL.GROUP.CREATION
    importantLabel: true
  focus:
    label: FACTORY.FOCUS.CREATURE.LABEL
    boxEntry: FACTORY.FOCUS.CREATURE.BOX-ENTRY
    boxClose: FACTORY.FOCUS.CREATURE.BOX-CLOSE
  accessibility:
    labelKey: factory.shelf-label.creature.accessible
    transitionAnnouncementKey: factory.shelf-label.creature.transition
  box:
    id: FACTORY.SHELF-BOX.CREATURE
    visualRoleBindings:
      boxAccent: FACTORY.VISUAL.BOX.CREATURE
    initialOpen: false
    closeControl:
      kind: EXPLICIT_TOP_CLOSE
      placement: INLINE_LEADING_PINNED
    expansionControl:
      kind: ALLOCATION_AXIS_GLYPH
      placement: INLINE_LEADING_AFTER_CLOSE
      horizontal:
        expandGlyph: "→"
        restoreGlyph: "←"
        expandNameKey: factory.creature.expand-right.accessible
        restoreNameKey: factory.creature.restore-from-right.accessible
      vertical:
        expandGlyph: "↓"
        restoreGlyph: "↑"
        expandNameKey: factory.creature.expand-below.accessible
        restoreNameKey: factory.creature.restore-from-below.accessible
    takeoverControl:
      kind: STANDARD_TAKEOVER
      labelControlId: FACTORY.CONTROL.CREATURE.TAKEOVER
      boxHeaderControlId: FACTORY.CONTROL.CREATURE.RETURN-TO-GROUP
      labelPlacement: TRAILING
      boxHeaderPlacement: TRAILING
      appearance:
        shape: CIRCLE
        outerFill: WHITE
        centerFill: GREEN
      accessibility:
        enterNameKey: factory.creature.takeover.accessible
        returnNameKey: factory.creature.return-to-group.accessible
        enterAnnouncementKey: factory.creature.takeover.announcement
        returnAnnouncementKey: factory.creature.return-to-group.announcement
        closeAnnouncementKey: factory.creature.close-from-takeover.announcement
    preferredMainAxisExtent: 420
    content:
      ref: FACTORY.CREATURE.CONTENT
```

The Shelf Label remains the activation, focus, and reorder identity. Its Shelf Box is not an independent
order owner. Activating the Shelf Label and using the Shelf Box's explicit top Close control produce the
same closed state. Close returns focus to the Shelf Label unless the feature owner's reviewed dirty-close
guard rejects before presentation mutation.

Every open Shelf Box places its explicit Close control at the inline leading edge of its header: left in a
left-to-right presentation and right in a right-to-left presentation. The control is pinned, remains in the
keyboard sequence, and is never clipped or scrolled away by a narrow rail. Header title, status, and other
content yield space or truncate before Close becomes unavailable. A leading control cluster may place the
independent Expand/Restore control immediately after Close; the separate Takeover dot stays trailing. When
those optional controls exist, they also remain keyboard reachable under narrow overflow. Close is first in
the leading cluster and retains the strongest no-clipping priority.

Every open Shelf Box may expose one explicit Expand/Restore control. Its visible content is a direction
glyph derived from the actual open-box allocation axis, `REGION_AXIS`, never from Shelf Label orientation:

- horizontal allocation uses `→` for Expand and `←` for Restore; and
- vertical allocation uses `↓` for Expand and `↑` for Restore.

These arrows describe physical `REGION_AXIS` fill and restore directions and are not mirrored by LTR/RTL.
Writing direction changes only which physical header edge is inline leading for the control cluster.

The glyph has a descriptive accessible name and equal `title`: **Expand to fill available space to the
right**, **Restore from rightward fill to comparison size**, **Expand to fill available space below**, or
**Restore from downward fill to comparison size**, as applicable. The visible words `Expand` and `Restore`
do not replace those glyphs. Expansion remains presentation-only, is mutually exclusive within the Shelf,
and survives valid refresh/restoration. Restore returns the Shelf Box to its bounded preferred main-axis
extent.

Expand/Restore and Takeover are independent controls. Expand owns cooperative main-axis allocation among
visible peers. Takeover owns temporary peer occlusion and exact return-state restoration. Neither control
changes the other's identity, and neither is a substitute for the other.

`titleKey`, accessibility keys, and binding IDs are stable references. Localized display values and live
activity ranks are adapter inputs and never enter persisted Shelf state.

Visual bindings are also stable references. They identify semantic owner, role, related-group, Shelf, and
Shelf Box accents; they do not contain raw color literals. A theme adapter resolves them inside the
declared Talisman Forest family and preserves their meaning across browser and desktop presentations.
Related items reuse one reviewed accent family. Different hierarchy levels and operational roles remain
perceptually distinct instead of collapsing into gray-on-gray or receiving arbitrary per-control colors.

Every Shelf Label has one Shelf Box. A destination that performs an action or navigation without expanded
content is an ordinary DTDT control, not a Shelf Label with a fictitious empty Shelf Box.

### 9.1 Optional composite work-item content

A Shelf Box may reference one stable composite work-item component whose checklist aggregates many small,
related operations under one permanent or common owner. The checklist rows are content inside that Shelf
Box; they do not create a Shelf Label/Shelf Box pair per operation and do not alter Shelf occupancy.

An owner may later promote one checklist row into its own independently openable Shelf Label/Shelf Box
pair when that operation needs separate visibility, lifecycle, comparison space, or review. Promotion must
assign stable identities and pass the normal declaration and persistence checks. This is an optional
content and presentation pattern proven by TW-01, not a required Shelf schema shape or business-work
authority.

## 10. Nested Shelf declaration

A Shelf Box contains exactly one ordinary content reference or one nested Shelf.

```yaml
box:
  id: FACTORY.SHELF-BOX.PUPPETEER
  initialOpen: false
  closeControl: EXPLICIT_TOP_CLOSE
  content:
    shelf:
      type: Shelf
      id: FACTORY.SHELF.PUPPETEER
      definitionRevision: 1
      persistenceOwner: FACTORY.SHELF.PRESENTATION
      presentationMemory:
        policy: BROWSER_LOCAL
      visualSemantics:
        inherit: PARENT_SHELF
        shelfAccentBinding: FACTORY.VISUAL.SHELF.PUPPETEER
      maximumNestedDepth: 0
      sort:
        allowed: [FIXED, MANUAL]
        initial: FIXED
      openBoxes:
        mode: MULTI_OPEN
      layout:
        orientation: VERTICAL
        visibleLabelRows: 1
        labelOverflow: VERTICAL
        boxOverflow: VERTICAL
        minimumBoxMainAxis: 180
      labels: []
```

The nested Shelf follows the same schema and behavior as its parent. It has no special rail, workspace,
or secondary nesting vocabulary.

## 11. Layout and responsive behavior

`layout.orientation` is `HORIZONTAL` or `VERTICAL`. Geometry may change at reviewed breakpoints without
changing Shelf membership, sort mode, resolved Shelf Label order, open Shelf Boxes, focused identity, or
authoritative Shelf Box order.

`REGION_AXIS` is the Shelf's main axis along which open Shelf Boxes are allocated. `SHELF_CROSS_AXIS` is
the perpendicular axis separating the Shelf Label region from the open Shelf Box region. These logical
axes remain stable while their physical width/height mapping follows `layout.orientation`.

`visibleLabelRows` is positive. `labelOverflow` and `boxOverflow` name the overflow axes. A normal
comparison profile uses vertical Shelf Label overflow after its visible-row limit and horizontal Shelf
Box overflow below its minimum width.

### 11.1 Label-region/open-box boundary

`layout.labelRegionBoundary` declares the one divider between the Shelf Label region and the open Shelf Box
region. It is distinct from every divider between adjacent open Shelf Boxes.

- `id` is a stable splitter identity unique in the complete document.
- `kind` is exactly `LABEL_REGION_TO_OPEN_BOX_REGION`.
- `orientationSource` is `SHELF_ORIENTATION`. A nested Shelf uses its own declared orientation inside the
  enclosing Shelf Box; that Shelf Box supplies bounds but creates no second orientation authority.
- `movementAxis` is exactly `SHELF_CROSS_AXIS`, never `REGION_AXIS`.
- `labelRegionAnchor` is `LEADING`: top for a horizontal Shelf and left for a vertical Shelf. Dragging can
  resize only the label region; the regions never exchange sides.
- Preferred, minimum, and maximum cross-axis extents are finite, ordered, non-negative values in the
  adapter's declared logical unit.
- Collapse is forbidden. Reset restores the declared preferred extent.
- Pointer drag and axis-correct keyboard operation produce the same clamped presentation result.
- Accessibility identifies the divider, both controlled regions, orientation, current value, minimum,
  maximum, and reset action.

The persisted extent is device-local presentation state keyed by splitter ID, Shelf ID, orientation,
session/lifecycle identity, and current definition fingerprint. Horizontal and vertical extents are
independent profiles. Switching orientation restores that orientation's valid extent or its preferred
default; it never rotates or reinterprets the other profile.

Adjacent-open-box dividers continue to allocate Shelf Boxes along `REGION_AXIS`. The label-region boundary
never changes open-box chronology, occupancy, preferred comparison extents, or expansion identity.

Optional adapter concerns such as divider weights, detached targets, theme, and density live under a
namespaced `extensions` object. An extension cannot change a canonical Shelf outcome. An unknown required
extension rejects the document; an unknown optional extension is ignored without state mutation.

## 12. Presentation bindings and feature authority

- `statusBinding` and `activityRankBinding` are read-only presentation projections.
- Open, close, focus, reorder, sort, and responsive changes are local checked Shelf actions.
- `content.ref` resolves an existing registered DTDT component or feature adapter.
- Feature commands continue through the existing typed Behavior or adapter and owning service.
- Feature reads continue through their existing direct typed query or immutable projection route.
- DTDT v3 creates no generic service bus, database gateway, provider route, or cancellation path.

A browser adapter mutates presentation on its event loop. A Java adapter mutates presentation on the EDT
and captures immutable inputs before worker or JavaFX handoff. No implementation adds a synchronous
EDT-to-JavaFX-to-EDT or JavaFX-to-EDT-to-JavaFX bridge.

### 12.1 Purposeful Forest visual semantics

The root Shelf declares the complete `visualSemantics` contract. A nested Shelf either declares the same
complete contract or explicitly inherits its parent's theme, hierarchy, state tokens, contrast thresholds,
and redundant-cue rule while supplying its own stable `shelfAccentBinding`.

The first shared profile requires all of the following:

- `themeFamily` is `TALISMAN_FOREST`. Dark or neutral surfaces may provide the canvas, but gray-on-gray
  may not carry hierarchy, selection, ownership, relationship, or operational state.
- Shelf, owner, Shelf Label role, related group, and Shelf Box accents are stable semantic bindings.
  Related items reuse a reviewed family; roles that must be told apart remain perceptually distinct.
- Shelf and Shelf Box headings and important labels are bold. Secondary text remains readable and is not
  reduced to tiny, faint gray text.
- Selection and active, waiting, warning, complete, error, and disabled states use distinct semantic
  treatments. Interactive and attention states are reasonably bright; disabled remains readable without
  appearing actionable. Focus remains independently visible and cannot be confused with selection.
- Every operational state displays its word and at least one non-color marker: an icon, pattern, or
  explicit border/fill geometry. Focus and selection also remain identifiable without color.
- Normal text reaches at least 4.5:1 contrast. Large text, meaningful icons, controls, focus indicators,
  and component boundaries reach at least 3:1 against adjacent colors.
- Redraw, orientation, nesting, detachment, restoration, and reduced-motion presentation preserve the
  same semantic mappings. Adapters do not assign a new decorative palette on reconstruction.

DTDT owns these roles, bindings, and minimum distinctions. The Talisman Forest theme owns resolved color,
font, border, focus, and status values. A product adapter supplies stable binding identities and may add a
reviewed domain accent without redefining shared state meanings. The schema contains no RGB, HSL, hex,
toolkit color, or CSS value. The standard Takeover control remains the separately specified white-ring,
green-center control and still requires its accessible name and pressed state.

This contract applies to new work and a screen already being actively redesigned. It does not reopen an
accepted body solely to recolor it. A later functional body touching an accepted surface adopts these
semantics within that body's reviewed scope.

## 13. Validation

An adapter validates the complete normalized document before constructing live presentation. Validation
includes the exact language version, complete allowed-key checking, globally unique stable IDs, one Shelf
per Shelf Label, one Shelf Box per Shelf Label, legal sort and open-box policy, complete peer references,
exact content references, supported nesting, focus and accessibility completeness, persistence identity,
presentation-memory policy and context-key requirements, and required extension support.

The root `visualSemantics` object is required and exact-key checked. It must name the Forest family,
complete hierarchy weights, all required state tokens, numerical contrast thresholds no lower than the
shared minimums, the redundant state-cue rule, and one stable Shelf accent binding. A label's visual-role
bindings and its Box accent are stable references, not color values. Raw color literals, incomplete state
sets, non-bold required hierarchy, color-only state, and nested inheritance without a valid parent reject
the complete document.

An optional `takeoverControl` is admitted only with the exact standard kind, placements, white-circle and
green-center appearance, globally unique label/header control IDs, and complete accessible names and
announcements. Its IDs must differ from the Shelf Box entry, Close, and Expand/Restore identities.

Every Shelf Box Close declaration is admitted only with `INLINE_LEADING_PINNED` placement and a stable
keyboard-focus identity. An expansion declaration is admitted only with the exact allocation-axis glyph
map and descriptive accessible names. A declaration that derives its glyph from label orientation, places
Close at the trailing edge, combines Close with Takeover, or permits narrow-rail clipping rejects whole.

Any failure rejects the whole declaration. No adapter salvages a partial Shelf, order, open set, nested
Shelf, or owner-local alias.

## 14. Runtime state and persistence

Every Shelf declares one presentation-memory policy:

- `BROWSER_LOCAL` stores and restores the complete validated Shelf setup only within the same browser
  profile and origin. It is the Workboard policy.
- `USER_CONTEXT_DURABLE` stores and restores the complete validated Shelf setup for one authenticated
  person and one required stable `applicationContextKey`. It is the Talisman workspace policy and may
  follow that same person between Talisman surfaces, but never another person or unrelated workspace.

Both policies cover the complete reconstructible setup: open and closed Shelf Boxes and disclosures,
resolved order, orientation, safe adjacent-box and label-region divider extents, expanded Shelf Box
identity, bounded recent-use evidence, selected presentation identity, nested-Shelf state, and card/detail
disclosures. Every accepted presentation change updates the applicable payload atomically. A live redraw
or feature-state refresh reapplies accepted presentation state and must not silently close or collapse
anything the person left open.

The v3 presentation state contains the language version, Shelf IDs, persistence owner, definition
fingerprints, sort modes, Manual orders, open Shelf Box IDs, bounded recent-use sequences, open chronology,
expanded Shelf Box identity, bounded open-box extents, per-orientation label-region extents, stable focused
identity, and a monotonic presentation revision. Active takeover state is either null or one complete value:
the active Shelf Box ID plus its return open-box IDs, return chronology, and return expanded Shelf Box ID.
The active value and ordinary Shelf state persist as one atomic envelope under either `BROWSER_LOCAL` or
`USER_CONTEXT_DURABLE`; no partial takeover snapshot is admissible.

Focus announcements, localized names, live activity ranks, toolkit objects, feature state, database rows,
drafts, jobs, credentials, server state, and service results are not persisted. A foreign owner, person,
application context, browser origin, version, definition, ID, order, mode, or takeover value rejects the
complete envelope and installs one declared default. Partial payloads are never partially merged.

A valid redraw or feature refresh reconstructs the same active control, pressed state, active Shelf Box,
occluded peers, return snapshot, expanded identity, and stable focused identity. It never returns the group,
closes a peer, or converts takeover into expansion as a side effect of rendering.

## 15. Accessibility and focus

Every Shelf Label and Shelf Box pair supplies deterministic label, content-entry, and Close identities.
When Takeover is declared, it also supplies deterministic label Takeover and header Return-to-group control
identities, accessible names, pressed state, and transition announcements.
Adapters must preserve stable-identity focus after automatic sorting, move focus into newly opened content,
evacuate focus before replacement or takeover, return focus to the Shelf Label after Close, expose position
and set size, announce automatic movement and open-box transitions, and retain keyboard operation inside
overflow containers.

The leading Close remains visible, focusable, and operable at every supported width. Expand/Restore exposes
its complete action and direction to assistive technology even though the visible face is glyph-only;
Takeover remains independently reachable at the trailing edge. Narrow overflow retains all present action
controls in the keyboard sequence while title and status presentation yield first.

Visual review also proves the declared text and graphics contrast, bold required hierarchy, distinct
focus and selection, readable secondary text, and a word plus non-color marker for every operational
state. A grayscale or equivalent color-unavailable review must retain owner/role/group comprehension and
active, waiting, warning, complete, error, disabled, focus, and selection distinctions.

Toolkit roles and widgets may differ. The semantic outcomes may not.

## 16. Shared conformance

A v3 adapter must execute the replacement v3 conformance vectors through its native reducer and compare
semantic outcomes. Required evidence covers declaration admission, normalization, fingerprint stability,
sorting and ties, automatic-to-Manual reorder and cancellation, multi-open, replacement, per-Box Takeover
entry/Return/Close, open chronology, independent cooperative expansion, both divider classes, both
presentation-memory policies, redraw preservation, whole-envelope persistence rejection, responsive state
preservation, semantic theme binding, owner/Shelf/Label/Box/role/group distinction, all required operational
states, bold hierarchy, contrast, non-color redundancy, focus and announcements, and exact disposal without
retained listeners, subscriptions, timers, or toolkit trees.

The responsive vectors additionally require a pinned inline-leading Close at normal and narrow widths,
trailing Takeover separation, actual allocation-axis `→`/`←` and `↓`/`↑` glyph selection, complete
accessible names/titles, and keyboard reachability without clipped controls.

The existing `SHELF-CONTRACT-V1.md`, `shelf-contract-v1.fixtures.json`, and their test are frozen historical
compatibility evidence, not final v3 authority. Their `ShelfLabelBoxDefinition`, `ShelfGroupDefinition`,
`SurfacePolicy`, generic surface terminology, and v1 identifiers are never reinterpreted. The final v3
runtime removes its v1/v2 admission paths only during the coherent application cutover.

## 17. Sequential application adoption

### 17.1 Workboard first

Each Workboard collection becomes a Shelf. Its compact controls become Shelf Labels; its expanded cards
become Shelf Boxes. The adapter must preserve reviewed user-visible behavior and run the v3 vectors without
widening server or coordination authority.

Workboard V4 supplies reviewed manual evidence for the standard Takeover dot on the owner Shelf Label and
Shelf Box, the nested Landed Shelf, and the Feature, Controls, and Server Shelf Label/Shelf Box pairs. That
evidence fixes the v3 control semantics above; it does not by itself constitute the future neutral schema,
replacement vectors, Java parity, or application cutover.

Workboard Shelf V6 supplies reviewed product evidence for pinned leading Close, trailing Takeover, and
allocation-axis expansion glyphs at wide and narrow rails. That evidence fixes the TSR-06 design semantics;
it does not by itself prove the future neutral schema, replacement vectors, other browser consumers, Java
parity, or application cutover.

### 17.2 Object Factory second

Object Factory currently uses the additive v2 contract. Its later v3 migration waits until the Workboard
v3 result is reviewed and then uses the same v3 schema.

- If Batch, Gallery, and Creature reveal full content, they are Shelf Labels and Shelf Boxes on one
  `EXCLUSIVE_REPLACE` Shelf.
- If they merely navigate without revealing content, they are ordinary controls and not a Shelf.
- Shader, Object, and Puppeteer become Shelf Label and Shelf Box pairs on a `MULTI_OPEN` Shelf.
- A later Puppeteer Shelf nests directly inside its owning Shelf Box.

The Application Server continues to serve owner bytes and typed feature capabilities. It does not become
Shelf declaration, presentation state, feature, or conformance authority.

### 17.3 Java application cutover

Java/DTDT parser, builder, Swing runtime, impression tooling, and application declarations require a
separate authorization. That body inventories every tracked declaration and compatibility class, migrates
or removes each one, and finishes with only v3 syntax and runtime types.

The final receipt proves every tracked DTDT document declares v3; no source accepts missing, v1, or v2
versions; no production type, builder, fixture, Help text, or design authority retains removed language;
every registered root constructs from v3; and focused structural proof plus repository search finds no
surviving compatibility path.

## 18. Relationship to the missing P6 artifact

This design does not reconstruct or claim the missing P6 Java-impression artifact. It defines DTDT v3
directly from the accepted behavioral evidence and the user's language and hard-cutover decisions.

After Workboard evidence exists, the owner and Switchboard decide whether the historical P6 gate is
retired in favor of reviewed v3 artifacts. Until then, P6 remains missing and Java implementation remains
unauthorized.

## 19. Review checklist

- Are Shelf, Shelf Label, and Shelf Box the complete accepted product vocabulary?
- Is a nested Shelf inside a Shelf Box the complete nesting rule?
- Is removal of Shelf Item, generic Box, Shelf Workspace, groups, surfaces, and Focus vocabulary explicit?
- Is the v3-only application cutover explicit enough?
- Are sorting, open-box occupancy, persistence, focus, and accessibility complete?
- Are open-box chronology, cooperative Expand/Restore, per-Box Takeover, and both divider classes complete
  and independent?
- Is Close pinned at inline leading, Takeover trailing, and Expand/Restore derived from allocation axis with
  exact glyph, title, accessible name, narrow-rail, and keyboard behavior?
- Are purposeful Forest accents, bold hierarchy, contrast, and redundant non-color cues complete without
  inventing product-local state meanings?
- Is the optional composite work-item pattern clearly content rather than mandatory Shelf structure?
- Is Workboard-first, Object-Factory-second explicitly scoped to v3 adoption while current v2 remains true?
- Do both presentation-memory policies preserve complete validated state across redraw and fail closed?
- Is feature authority kept outside DTDT presentation state?
- Does Java remain separately authorized after browser evidence?
