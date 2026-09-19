# DTDT UI inspection contract

Declarative UI structure is separate from behavior. Canonical base files remain unchanged by local
extensions, but every compliant UI must be able to explain its structure in DTDT terms.

## Structural DTDT specification

Each meaningful structural element should have a DTDT specification. This includes Cards, Shelves,
Shelf Items, Boxes, Bars, Six-View Bars, Twist-Open Tree Shelves, Tear-Off Tool Bars, Arenas, floating
windows and nested Cards/Boxes.

A DTDT specification records, as applicable:

- stable element identity;
- element type and named pattern;
- parent/child relationship;
- target relationship;
- view slots and active View;
- label and icon policy;
- orientation and layout behavior;
- reorderability, selection, drag and tear-off rules;
- floating-window bounds and docking rules;
- Theme roles;
- project/domain ownership;
- Morph/Place/database identity when bound;
- source/admission status;
- compliance warnings and unimplemented dependencies.

The specification is not merely documentation next to the code. A running compliant UI must be able to
surface the relevant specification for the displayed element.

## Control-Tick debug overlay

Every compliant UI must support a **Control-Tick Debug Overlay**. When Control-Tick is held or toggled,
small debug buttons/tabs appear on meaningful structural parts of the UI.

The controls may be tiny and visually unobtrusive. They are not normal product UI and need not be useful
to an ordinary user. They are authoring, inspection and compliance equipment.

Clicking a debug control opens the DTDT specification for that exact element. For a Six-View Bar, this
means the bar itself and meaningful view slots must be inspectable, even when the buttons are cramped.
For nested Cards and Boxes, the active element and its containment/target path must be inspectable.

## Compliance rule

A UI is not DTDT-compliant unless Control-Tick can expose debug controls for every meaningful structural
element and those controls reveal that element's DTDT specification.

The debug overlay is the friction-test mechanism. A reviewer should be able to turn on Control-Tick,
click a surface, read its specification and compare the visible behavior against the declared structure.
