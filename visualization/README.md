# Netty — continuous evolution

A 2 minute 48 second animated film study of Mark William Miller's Netty. The Pink Zone precedes the seven spaces; each later form emerges within the same breathing field. At the end the assembled universe keeps moving. There is no automatic reset or cut back to the beginning.

The design PDF is the original brief. Mark's subsequent direction refines it: Pink energy is the red/white pre-particle field; White Space contains one twirl, not a binary particle grid; Black Space is one connected growing entanglement; Blue is a sentient net processor with stateful nodes and information-bearing arcs.

## Watch and direct

Open `visualization/index.html` directly in a browser. Everything works offline, without a build or dependency installation. Alternatively, from the repository root:

```sh
python3 -m http.server 8765 --bind 127.0.0.1
```

Visit http://127.0.0.1:8765/visualization/.

- Playback starts automatically, except when reduced motion is requested.
- Pause with the round button or Space. Change tempo from 0.25× to 2×.
- Scrub the timeline in either direction. Click a chapter to move to its beginning; playback continues if already running.
- Drag to orbit; scroll to zoom; Reset view restores the camera.
- Cinema hides the panels. Escape restores them. Keys 0–7 select Pink through Orange.
- From the first breath restarts the film. At 2:48 the completed scene keeps breathing until paused.
- Switching away from the browser tab pauses elapsed story time.

## Choreography

| Time | Emergence |
| --- | --- |
| 0:00 | Pink: red/white deforming surfaces, slightly different tightness, phases gradually synchronizing |
| 0:18 | White: one central twirl, two perpendicular rings with exactly opposite radial phases; the red wobble retracts to one-fifth its radius over the first three seconds while white keeps breathing |
| 0:36 | Black: connected spherical regions, imperfect gaps, growth with exponentially decreasing rate |
| 0:58 | Blue: simple nested shells born in gaps; binding and repelling motion; asymmetric fibrous chains |
| 1:16 | Blue processing: incoming patterned packets, retained node state changes, transformed outgoing packets |
| 1:25 | Red: central collapse followed by an outward wave; protons form when the front reaches their Blue cores |
| 1:30 onward | A few black holes, many proton swarms; cooling matter with complex nested Blue cores |
| 1:50 | Green: a subset of existing matter continuously moves into paired living chains |
| 2:12 | Yellow: some of those particles move into constructed hive cells; life remains |
| 2:30 | Orange: another subset becomes circuitry, with signals returning toward Blue; all prior spaces remain |

The camera and breathing clock are continuous. Shapes and node states are deterministic functions of film time, so reverse scrubbing reconstructs the same scene. Blue signal arrivals update four-state nodes; their state affects outgoing packet patterns. This is an authored visual processor, not a trained AI. Affinity, material organization, and gravity are choreographed representations.

The existing Java code supplies shell/axis/spin conventions (`Spin.java`, `SpinSignature.java`, `ParticleGroup.java`). An exact original two-ring twirl routine was not located: the new counter-phase motion follows Mark's spoken direction. The roughly 14-sided description of Black Space is represented with touching spherical regions and gaps, not an exact polyhedral tessellation.

## Checks

```sh
node --check visualization/app.js
node visualization/evolution-model.test.cjs
```

Focused checks cover emergence continuity, exponentially slowing Black growth, persistence of earlier spaces, exact counter-phase twirl radii, and reconstructable signal arrival state. Browser review covers the Pink, White, Black, Blue, energy-wave, and final combined scenes, scrubbing, playback, and responsive layout.

The Java / Java3D project remains independent in `src/`. This revision adds no narration or video export.
