# 3D terrain navigation and nested Place continuity

Status: portable documentation requirement for MPK 0.1.12. This contract incorporates the general
parts of Rougish 1.19/1.20 owner feedback. It defines interaction, focus, progressive detail and
transition behavior; it does not claim implementation by a consumer.

## One input language for 3D views

Terrain, building, simulation and other 3D canvases use the same default mapping:

- **Left click** selects. Left drag performs the active selection or explicit editing gesture.
- **Middle drag** pans.
- **Right drag** orbits without changing selection or applying an edit.
- **Double right click** on visible geometry sets the orbit center to the picked world point while
  preserving viewing direction and distance. Double left click remains available for selection,
  editing and explicit child-Place entry.
- **Wheel** zooms toward the visible terrain point under the cursor. When no terrain point can be
  picked, it uses the current orbit target.

Changing tools must not silently remap navigation buttons. Navigation preserves the current selection.
Legacy lock state must not disable the standard mapping. A right-drag orbit suppresses the browser
context menu on the canvas while ordinary context menus elsewhere remain available. Conflicting button
presses, Escape, focus loss, lost pointer capture and Place changes cancel reserved gestures cleanly.
Setting the orbit center must not select, sculpt, lock or fire another double-click action.

## Place identity, registration and coverage

A parent-child Place link records stable domain, parent and child identities, source revisions or
digests, physical footprint, coordinate transform and coverage mask. It also retains the actual link
used for navigation because one child can be registered under more than one parent. Names are labels,
not identities.

Declare units, axes, extents, reference point and elevation, sample spacing and no-data meaning. Apply
the child-to-parent physical transform once. Display-only height exaggeration is applied afterward in
one shared world frame so it cannot change stored elevations or separate a seam.

Coverage means valid geometry or appearance. Missing data remains distinct from flat ground, water or
transparent imagery. A parent supplies broad coverage; a child progressively replaces only its valid
footprint with better detail. Loading a child does not itself navigate, change the active Place, move
the camera or create an authored edit.

## Progressive detail and final surfaces

Show the best available representation immediately, then discover and load direct children in bounded
background work. Priority is:

1. an explicitly requested navigation target;
2. the region under the cursor during zoom or an explicit click;
3. adjoining visible regions;
4. other visible children;
5. remaining background candidates.

Reuse in-flight requests when priority changes. Request deeper descendants only when their projected
size justifies more detail. A single click may raise a child region's priority, but it must not enter
the child or consume an active editing tool's intended action.

The parent retains one composed final surface for each child footprint, rather than importing all of
the child's editable source layers. That surface carries child identity, revision, resolution, bounds
and composition provenance. Keep the best usable result while a replacement loads. A network failure
does not mean deletion; an authoritative deletion does. A newer source revision can replace an older,
more detailed result, but a stale or lower-detail response for the same revision cannot overwrite a
newer result.

Geometry and appearance readiness are separate. Valid child geometry owns its interior and boundary;
the parent continues elsewhere and feathers outward to meet the child. Do not pull the child toward the
coarse parent, show parent triangles through valid child coverage, or insert an artificial trench
between compatible adjacent children. Conflicting boundaries remain visible data conflicts until an
explicit correction. Rendering-only seam repair is derived state; an authored alignment is tracked,
undoable and saved separately.

## Cursor focus and transition states

Wheel zoom preserves the picked world point under the cursor through camera changes, loading pauses
and frame handover. Keep yaw, pitch and perceived scale continuous. Arrival must not reset to a fit view.
Use explicit states with both visual and textual feedback:

| State | Required behavior |
| --- | --- |
| Idle | Current Place remains active; background refinement may continue. |
| Approaching | The attended child is visibly marked before entry and receives higher priority. |
| Loading inward | Keep the parent scene usable and identify the requested child and loading state. |
| Transitioning | Align and blend the ready child over its registered footprint, then transfer the active frame once. |
| Loading outward | Keep the child visible while its full boundary indicates that parent coverage is loading. |
| Failed or cancelled | Clear transient feedback, retain the usable scene, explain the failure and permit retry. |

Color alone is insufficient; loading and focus cues require readable status. Motion cues must respect
reduced-motion settings. Background prefetch must not look like active navigation. Reversal or
cancellation invalidates a stale completion: it may populate cache, but it cannot take over the view.

During outward zoom, begin the parent decision when the current Place's projected tip-to-tip span falls
to one third of the viewport. Project representative boundary points before clipping and define
`S = max(projected_width / viewport_width, projected_height / viewport_height)`; sustained outward intent
at `S <= 1/3` requests the retained parent link once. Points behind the camera cannot create a false
small span. Use direction-aware hysteresis so small reversals do not oscillate Places. With no parent,
continue ordinary zoom. With multiple parents, use the retained navigation link or ask for an explicit
choice rather than selecting one arbitrarily.

Inward and outward transitions retain the detailed child patch while parent coverage fades in or out,
so there is no blank intermediate scene. At full parent opacity, valid parent continuation and child
coverage read as one opaque surface without cracks, slab walls, coarse bleed-through, z-fighting or
dark double blending.

## Storage and authority boundaries

Camera movement, loading, caching and derived final surfaces do not dirty the authored Place or grow
history. Cache entries remain separate from source data. Unsaved edits are protected from eviction and
from late background results. Invalidate a composed child surface when its contributing authored layers,
visibility, opacity, bounds, source revision or composition rules change; camera, selection, compass,
temporary lighting and display exaggeration do not invalidate it.

Browser storage, disk storage and database access are independent capabilities. Canonical save/import
requires the owning native adapter, conditional revision, acceptance receipt and read-back/reopen.
Zooming into a Place never imports it, and a local preview never becomes a canonical database save.

## Acceptance evidence

Use a fixture with parent coverage, adjacent children, missing coverage, slow/failing loads and at least
one parent with multiple possible links. Verify real pointer input and rendered geometry where possible:

- left select/edit, middle pan, right orbit, double-right focus, cursor zoom and cancellation do not
  leak into one another;
- a single child click changes priority without navigation or camera movement;
- inward and outward transitions preserve the cursor anchor, camera direction, active link and best
  child detail, including reversal, failure and warm-cache cases;
- the one-third outward threshold requests the parent once at varied scales, rotations and aspect ratios;
- seams remain continuous at full opacity and exaggerated display height without altering numeric data;
- read-only navigation creates no authored change, while an explicit edit survives save and reopen;
- measured results separate lookup/network, decode, composition, mesh preparation, upload, transition,
  peak memory and longest input stall.

Passing source-level or mocked checks is not live browser, device, database or cross-project acceptance.
