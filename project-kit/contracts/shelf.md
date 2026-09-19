# Shelf, Card, Box and View Bars

Shelf composition is presentation structure. Existing consumer Shelf/theme/accessibility contracts remain
controlling, but a compliant Moondance/Talisman UI must be describable with the shared words below.

## Everything is a Card

A **Card** is the basic UI/display unit. A Card may stand alone, sit on a Shelf, open a Box inside
itself, open a Box in another target, contain other Cards or represent a tool, view, Morph, Place,
database record or project object. Cards do not have to look identical, but their DTDT identity and
relationships must be inspectable.

A **Shelf Item** is a Card placed on a Shelf. It is commonly clickable/selectable and usually controls
another surface. Shelf Items have labels unless the DTDT specification explicitly allows an icon-only or
label-less presentation; label-less is a declared design choice, not an accidental omission.

## Shelf

A **Shelf** is a structured collection of Cards. A Shelf may render as a row, column, switchable
row/column, Six-View Bar, tool bar, tear-off tool bar, twist-open tree or project-specific control
surface. Its DTDT specification must declare:

- shelf form and orientation;
- target relationship: inside itself, left, right, above, below, named Arena, floating window, whole
  screen or another declared Card/Box;
- label/icon policy;
- selection policy;
- reorderability;
- drag/tear-off behavior;
- persistence and domain/project ownership;
- active Theme roles.

A Shelf can be a pure control surface or a mixed control/display surface. Placement is not normative;
the declared relationship is normative.

## Box and Arena

A **Box** is a display container opened or controlled by a Card. A Box may live inside a Card, inside an
Arena, beside or below a Shelf, as a floating window or nested inside another Box. Boxes may contain
Cards, Views, tools, editors, previews, importer comparisons or other Boxes.

An **Arena** is a named target display region controlled by one or more Shelves or Cards. The common
WorkBoard pattern is a control Shelf at the left and an Arena to the right, but any placement is allowed
when the DTDT target relationship is explicit.

## Six-View Bar

A **Six-View Bar** is a named Shelf pattern with six declared selectable view slots. It is used for 2D,
3D or mixed 2D/3D work. A common layout is:

- 2D left;
- 2D right;
- 2D top;
- 2D bottom;
- 3D view;
- composite, alternate, paired or project-specific view.

The exact six slots are declared by the DTDT specification for the surface. Clicking a slot swaps,
assigns or focuses that View in the associated Box or Arena. The associated target may be one active
View, a pair of Views, a multi-pane arrangement or a rearrangeable workspace.

For example, a poetry/movie project may use a Six-View Bar where poetry and movie panes can appear side
by side, stacked, rearranged or reduced to one active pane. The bar describes the six available view
choices; the target describes how the selected view appears.

A Six-View Bar is compliant only when all six slots, their View identities, target Box/Arena, click/swap
behavior, label policy and debug inspection path are declared.

## Tear-Off Tool Bar

A **Tear-Off Tool Bar** is a Shelf containing explicitly listed tools. A tool may be dragged off the
Shelf and released as a floating window when the DTDT specification permits it. The specification must
declare whether the dragged tool is moved, copied or instanced; whether it may dock back; and where the
floating window may live: inside a parent Box, inside an Arena, within a project workspace or across the
whole screen.

Floating constraints are required. An unrestricted floating window is a declared permission, not a
default.

## Twist-Open Tree Shelf

A **Twist-Open Tree Shelf** is a Shelf whose control surface is also a tree display. Expanding and
collapsing items reveals nested Cards. Selecting an item may control another Box, Arena or View, usually
beside or below the tree but not necessarily. The hierarchy, expansion behavior, selection behavior and
target relationship must be declared.

## Shelf friction test

A UI module passes the Shelf friction test when a reviewer can answer these questions without guessing:

- What Cards, Shelves, Shelf Items, Boxes, Bars and Arenas exist?
- Which Shelf form is being used?
- Which target does each control surface operate?
- Are labels, icons, ordering, reorderability, drag/tear-off behavior and floating constraints explicit?
- Can Control-Tick reveal the DTDT specification for every meaningful structural element?
