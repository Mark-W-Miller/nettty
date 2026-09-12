# Netty Particle Map

An animated visual interpretation of the seven spaces in Mark William Miller's **Project Netty Anon.pdf**. The original Java application remains in `src/`; this browser study is independent and requires no build, package installation, fonts, or network services.

## Open

Open `visualization/index.html` directly in a modern browser, or from the repository root run:

```sh
python3 -m http.server 8765 --bind 127.0.0.1
```

Then visit http://127.0.0.1:8765/visualization/.

## Direct the picture

- Select any of the seven spaces to study its behavior. **All layers together** restores the composite.
- Drag the picture to orbit; scroll to zoom. **Reset view** restores the camera.
- Pause/resume with the round playback button or Space. Tempo controls animation and journey speed.
- **Play the seven-layer journey** gives each space 12 seconds at normal tempo, then returns to the composite. It is an 84-second silent visual sequence, ready to evolve alongside the movie script.
- **Cinema** hides the panels. Escape restores them. Keys 1–7 select spaces; 0 selects the composite.
- Reduced-motion preferences start the animation paused. Playback remains available explicitly.

## Visual direction

All seven layers run on a shared animation clock. A spatial stack separates them for readability in the composite; it is a presentation choice, not a claim that the spaces are physically separated planes. The individual studies use the same field and camera.

| Space | Choreography |
| --- | --- |
| White | Binary points switching on and off with directional impulses |
| Black | A stable grid of alternating spins on three axes |
| Blue | Asymmetric connected nodes, shell particles, chaos-led trains, and mobile ring-like Turing Dust Bunnies |
| Red | Dense nuclei, spinning envelopes, and molecular links |
| Green | Paired helical chains with informational rungs |
| Yellow | Hive-like constructed cells and a rotating mechanical element |
| Orange | Circuit grids, processing cells, and traveling signals |

These are initial artistic motifs. Blue node memory changes, actual read/write interactions, a staged Red Space energy burst/cooling sequence, and the Orange-to-Blue interface are future choreography rather than implemented simulation. The supplied design is the source; explanatory copy is paraphrased. No narration or finished movie export is included.

Implementation: dependency-free Canvas 2D with perspective projection, seeded layouts, and time-based animation. Device pixel ratio is capped at 2. Resize and pointer controls work on desktop and touch screens.
