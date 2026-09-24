# Press-and-hold radial context menu

Status: MPK 0.1.13 release interaction contract curated from Rougish. It supplements the
[terrain navigation contract](terrain-navigation.md); applications keep visible ordinary tool controls.

## Gesture ownership

A stationary primary-button press reserves one gesture owner and shows bounded hold progress. Declare the
hold delay and movement tolerance; Rougish's reference values are 2,000 ms and 6 CSS pixels. Do not
speculatively select, paint or move and then undo.

- Release before the delay performs the ordinary click/dab once.
- Movement beyond tolerance cancels the hold and starts the ordinary drag from the original press point.
- Reaching the delay opens the context palette without changing selection, geometry, dirty state or Undo.
- Branch hover reveals choices. A leaf executes once only after explicit release/click/keyboard activation.
- Center, outside, disabled, cancelled or stale-context activation performs no scene command.
- Completed/cancelled holds consume trailing click/double-click/dab/navigation effects.

Escape, focus or capture loss, pointer cancellation, incompatible extra contact, zoom and owner/document
replacement cancel safely. Recheck captured document, target, selection and revision before any command.
Right orbit, middle pan, wheel zoom and double-right orbit focus retain their mappings. Any deliberate
multi-button editing exception is explicit and tested.

## Persistent palette and commands

The palette may latch after the initiating hold is released, as Rougish does, provided click-away is
consumed and dismisses, owner changes cancel, and no command fires on opening. Toolbar and radial routes
share the same state and command handlers. Parameterized or destructive operations open review with an
explicit Apply; entering a branch never invents values or commits an edit.

Use compact individually labelled radial targets around a visible context/cancel center. Clamp/page near
edges rather than shrinking labels below usability. Back/More navigate without scene mutation. Layer
visibility shows checked state and remains distinct from choosing the editable layer. Disabled choices
explain what is required and do not rely on color alone.

Provide a visible focusable open-menu control plus keyboard traversal, activation, Back and Escape.
Touch/stylus parity requires physical-device evidence for long-press arbitration, scroll, additional
contacts and target size; Pointer Events alone do not prove it.

Acceptance covers short click, drag then pause, stationary hold, latched and one-shot variants, branches,
direct leaves, click-away, viewport edges, disabled/checked states, stale context, all cancellations,
toolbar agreement, retained selection, camera mappings, keyboard and claimed touch devices.

