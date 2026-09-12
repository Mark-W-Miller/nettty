# Netty — continuous evolution

A 3 minute 24 second animated film study of Mark William Miller's Netty. The Pink Zone precedes the seven spaces; each later form emerges within the same breathing field. Each outgoing form yields the foreground to the next, retaining only faint background hints. Black fades completely after the camera enters it. At the end the thoughtnet keeps moving. There is no automatic reset or cut back to the beginning.

The design PDF is the original brief. Mark's subsequent direction refines it: Pink energy is the red/white pre-particle field; White Space contains one twirl, not a binary particle grid; Black Space is one connected growing entanglement; Blue is a sentient net processor with stateful nodes and information-bearing arcs.

## Watch and direct

Open `visualization/index.html` directly in a browser. Everything works offline, without a build or dependency installation. Alternatively, from the repository root:

```sh
python3 -m http.server 8765 --bind 127.0.0.1
```

Visit http://127.0.0.1:8765/visualization/.

- Playback starts automatically, except when reduced motion is requested.
- Pause with the round button or Space. Change tempo from 0.25× to 16×.
- Scrub the timeline in either direction. Click a chapter to move to its beginning; playback continues if already running.
- Drag to orbit; scroll to zoom; Reset view restores the camera.
- Cinema hides the panels. Escape restores them. Keys 0–7 select Pink through Orange.
- From the first breath restarts the film. At 3:24 the completed scene keeps breathing until paused.
- Switching away from the browser tab pauses elapsed story time.

## Choreography

| Time | Emergence |
| --- | --- |
| 0:00 | Pink: red/white deforming surfaces, slightly different tightness, phases gradually synchronizing |
| 0:18 | White: one central twirl, two perpendicular rings with exactly opposite radial phases; the red wobble retracts to one-fifth its radius over the first three seconds while white keeps breathing |
| 0:36 | Black: up to 2,800 fixed-radius spheres appear outward from the center; number grows with a decreasing rate, never sphere size |
| 0:53 | Camera moves into the Black population; the body fades as one composited image and is completely gone by 1:04 |
| 1:02 | Blue: simple nested shells born in gaps; binding and repelling motion; asymmetric fibrous chains |
| 1:16 | Blue processing: incoming patterned packets, retained node state changes, transformed outgoing packets |
| 1:20 | Blue contracts toward the center, gathering before the release |
| 1:25 | Red: central collapse followed by an outward wave; protons form when the front reaches their Blue cores |
| 1:29 onward | The view opens into spiral systems: black holes, stars, and orbiting planets; earlier particle detail fades |
| 1:44 | One planet moves from a spiral system to the center and grows into the Earth close-up |
| 1:50 | Green: Earth resolves into oceans, land, and atmosphere; from 1:58, two strands wind into a helix as the globe recedes; five Blue agents traverse the strands, pause, and leave persistent rung changes |
| 2:12 | Yellow: some of those particles move into constructed hive cells; life remains |
| 2:30 | Orange: another subset becomes circuitry, with only hints of the earlier spaces; from 2:38 it becomes three slightly offset local grids linked as a thoughtnet |

| 2:56 | A compact Blue structure bends light; observation and response flow between it and the ordered Orange modules |
| 3:12 | Closing title: The Age of Aquarius, with Mark's Netty learning-surface language |

Earth now uses a local NASA Blue Marble texture mapped to a rotating sphere, with atmospheric lighting and independently drifting illustrative clouds. Source and credits: [assets/CREDITS.md](assets/CREDITS.md). Embedded texture data preserves direct offline opening. Sphere pixels are cached at 24 frames per second of film time.

The Big Bang wave decays exponentially rather than stopping abruptly. A temporary Black deck catches the passing energy, and the small central pump remains pulsing afterward.

The breathing clock is continuous. Authored camera magnification handles the Black close-up; the Earth approach combines planet translation and growth. These are cinematic scale transitions, not a physical camera simulation. Shapes and node states are deterministic functions of film time, so reverse scrubbing reconstructs the same scene. Blue signal arrivals update four-state nodes; their state affects outgoing packet patterns. This is an authored visual processor, not a trained AI. Affinity, material organization, and gravity are choreographed representations.

The existing Java code supplies shell/axis/spin conventions (`Spin.java`, `SpinSignature.java`, `ParticleGroup.java`). An exact original two-ring twirl routine was not located: the new counter-phase motion follows Mark's spoken direction. The roughly 14-sided description of Black Space is represented with touching spherical regions and gaps, not an exact polyhedral tessellation.

## Checks

```sh
node --check visualization/app.js
node visualization/evolution-model.test.cjs
```

Focused checks cover emergence continuity, exponentially slowing Black growth, persistence of earlier spaces, exact counter-phase twirl radii, and reconstructable signal arrival state. Browser review covers the multiplying Black body, camera push, cosmic spirals, Earth close-up, and final thoughtnet. Transition checks cover Black disappearance, Blue contraction, Earth approach, and thoughtnet emergence; earlier checks cover scrubbing, playback, and responsive layout.

The Java / Java3D project remains independent in `src/`. This revision adds no narration or video export.

The closing light-bending structure and learning conversation visualize Mark's film concept; they are authored choreography, not a reconstruction of an observed UAP or a live learning system.
