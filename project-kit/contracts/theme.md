# Theme contract

Themes are first-class DTDT design definitions. A Theme lights up UI structure using shared semantic
roles instead of one-off screen colors.

Themes apply to Cards, Shelves, Shelf Items, Boxes, Arenas, Six-View Bars, Tree Shelves, Tear-Off Tool
Bars, floating windows, database importer screens, project UIs, debug overlays and status/review states.

## Talisman Forest family

The Talisman Forest direction is a theme family with at least two formal variants:

- **Forest Green**
- **Forest Blue**

Both variants use the same semantic roles. They differ in palette, not meaning. A project can switch
between Forest Green and Forest Blue without changing DTDT structure, status semantics or accessibility
requirements.

Required semantic roles include:

- active selection;
- owner/domain group;
- shelf background;
- card surface;
- box surface;
- arena background;
- waiting/review;
- complete/accepted;
- warning;
- error;
- debug overlay;
- importer new object;
- importer update candidate;
- importer conflict;
- importer quarantined/rejected object.

Meaning must never depend on color alone. Pair semantic color with words, icons, borders, shapes,
patterns or other non-color cues. Preserve focus indicators and strong contrast.

## Local administrator tools

The Talisman Application Server database importer is a local-area/system-administrator tool, but it is
still a compliant DTDT UI. It uses the same Cards, Shelves, Boxes, Arenas, Control-Tick inspection and
Theme roles as other compliant project UIs.
