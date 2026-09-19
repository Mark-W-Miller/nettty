# Moondance North Star: The Right Line

## Materialized worlds, stories, and experiences

Status: enduring vision and parked design direction. This document sits above individual products,
architectures, journeys, and work plans. It authorizes no implementation body, provider work, hardware
commitment, deployment, or owner wake-up.

## Executive overview

Moondance aims to let one richly structured world or story become many useful experiences without turning
each medium into a separate system. Talisman, Easy Tale, EasyScreen, games, adventures, fiction, animation,
film previsualization, physical fabrication, VTT play, augmented tabletop, local applications, and web
delivery are expressions of the same idea: build a trustworthy semantic skeleton once, then materialize it
through replaceable layers of presentation.

Moondance does not impose online or offline, physical or digital, modest or cinematic as a doctrine. It
chooses the **right line**: the arrangement of physical, local, browser, desktop, shared-screen, augmented,
and remote facilities that produces the best experience for a particular group, creator, audience, and
moment.

The whole vision is useful context for local decisions. The more an implementer understands the intended
whole, the more likely a small contract, identity, renderer, workflow, or persistence choice will serve
several futures rather than accidentally foreclose them. Aspiration must remain clearly distinct from
current implementation truth.

## How to navigate this north star

1. [Enduring vision](#1-enduring-vision) states what Moondance ultimately seeks to make possible.
2. [Architectural principles](#2-architectural-principles) identifies the ideas that keep its breadth
   coherent.
3. [Illustrative applications](#3-illustrative-applications) shows how those principles serve different
   creative forms.
4. [Current capabilities](#4-current-capabilities) defines the separate evidence hook for reality today.
5. [Future possibilities](#5-future-possibilities) records plausible directions without scheduling them.
6. [Using the vision](#6-using-the-vision) explains how later designers should apply it.

## 1. Enduring vision

### The right line

The desired future is not necessarily fully immersive 3D or a replacement for tabletop play. Many players
want a real table, face-to-face conversation, dice, cards, hand-painted miniatures, physical terrain, and
ordinary human gestures. Technology should enchant that table, not replace it.

The inexpensive, cheap and cheerful physical tier is first-class. A group may play on a built board with
painted miniatures and only the GM's tablet. Another group may use a shared television, browsers, remote
participants, or augmented overlays. AI may assist creation of complex Morphs or Motions without becoming a
requirement at the table.

### One world, many projections

The same authoritative Place, Morph, Motion, appearance, visibility, context, and game-state model should be
capable of driving:

- fully physical tabletop play;
- a GM tablet with otherwise minimal electronics;
- shared television or VTT-table play ("TV gaming");
- browser clients;
- the Talisman desktop application and embedded server;
- remote Moondance play;
- headsets or lightweight augmented-reality glasses;
- printed 2D and 3D material; and
- narrative, animated, and cinematic presentation.

These are projections and materializations of one world, not separate game implementations.

## 2. Architectural principles

### Skeleton and patina

Moondance content begins with a strong **skeleton**: semantic structure, relationships, geometry, Morphs,
Motion, characters, Places, blocking, script, intent, context, and state. Presentation is **patina** layered
over that skeleton: textures, visual fidelity, voices, lighting, scenic dressing, and photorealism.

Patina can be replaced and improved much more cheaply than faulty underlying structure. A durable,
renderer-independent work can therefore rise or fall in fidelity according to purpose, audience, equipment,
and budget without losing its identity or meaning.

### Portable authority

The authoritative world and story must remain independent of any renderer. Place, Morph, Motion, context,
identity, revision, and visibility contracts should stay portable. Visibility is projected separately for
each participant. Augmented reality, print, browser, desktop, and film are consumers of authority, not rival
authorities.

### Materialization as a general capability

One structured work may materialize as an ordinary screen UI, 2D or 3D experience, browser or desktop
application, paper or PDF, plastic or 3D-printed objects and terrain, augmented tabletop, immersive
experience, animation, film or television previsualization, and ultimately a finished presentation.
Printable and physical outputs are legitimate targets, not lossy afterthoughts.

A GM should eventually be able to construct an adventure in Talisman and materialize a complete play
package: scalable maps and PDFs, printable miniatures and terrain, VTT or shared-display packages, and data
and assets for remote or augmented play. Ordering physical packages through suitable providers may later be
useful, but is not implied or authorized here.

### Do not freeze the future around today's hardware

The architecture must not be constrained by present rendering, memory, network, or wearable limitations.
Glasses and rendering hardware will improve and become cheaper. Premium or elite delivery can begin before
mass adoption, provided the canonical model remains portable and lower-cost materializations remain sound.

Preserve the LAN path for responsiveness, autonomy, and tabletop use. Prefer browser delivery where it
provides sufficient performance and removes unnecessary native-application burden. Do not declare LAN,
desktop, browser, or cloud universally correct in advance. Phones may become the natural bridge between
glasses and the world server. Talisman's desktop application, embedded server, browser services, and
Moondance hosting should remain compatible projections of shared contracts.

### Breadth without fragmentation

Talisman and Moondance aim to support many forms without becoming many unrelated systems. Shared languages,
canonical structures, stable identities, explicit context, and renderer-independent authority make that
breadth tractable. A new delivery surface should extend the family of projections rather than silently fork
the world, story, or workflow.

## 3. Illustrative applications

### Tabletop and games

Physical maps, painted miniatures, dice, cards, shared screens, browsers, remote players, and augmented
overlays can coexist along the right line for a group. A participant may move between those forms while the
same authoritative game state and participant-specific visibility remain coherent.

### Easy Tale and EasyScreen

Easy Tale (Easytail) and EasyScreen are important examples. Creators can write stories, block scenes,
assign scripts and performances to characters, and render the result at the fidelity justified by available
technology and intended use. The same foundations can support authored fiction, animation, pitches, games,
D&D adventures, and film or miniseries development.

Concrete creative proving grounds include a five-year miniseries spanning 1968 through 1972 around San
Francisco and a Lee Harvey Oswald project. The aim is to develop structure, performance, Place, continuity,
and presentation far enough that prospective producers can see the work rather than having to imagine it
from a proposal alone.

### Physical and augmented tabletop

When suitable glasses are available, each player's glasses will likely use that player's phone for
computation and connectivity. Phones can connect to an authoritative local or remote Talisman or Moondance
session. Overlays may add:

- animated creatures tied to physical bases;
- fire, smoke, weather, water, lighting, and magic;
- moving or opening Morph objects;
- labels and measurements;
- player-private information and character-specific visibility;
- fog of war and intention previews; and
- spatial sound associated with Places, Morphs, stances, and Motions.

A shared physical coordinate system and stable multi-user anchoring are core technical requirements.
Augmented reality remains an additional renderer over the same world, never a fork of world authority.

## 4. Current capabilities

This north star deliberately contains no undated claim about what is implemented now. Current behavior must
remain grounded in source, the Development Architecture Atlas, focused proof, releases, and observed runtime
evidence.

A separate dated **Where We Are Now** assessment should periodically compare current Talisman and Moondance
capabilities with this vision. That assessment should name evidence, gaps, constraints, and the date of
observation. It may recommend priorities, but it must not rewrite the north star or promote future
possibilities into present truth.

## 5. Future possibilities

Future materializations may include richer shared-screen play, inexpensive printed packages, integrated
physical ordering, phone-assisted glasses, spatial sound, participant-private augmented visibility,
high-fidelity animation, and production-grade cinematic presentation. These possibilities can arrive at
different times and price points.

They are neither promises nor a queue. Their architectural value is to test whether today's canonical model
remains expressive, portable, and free of unnecessary renderer or deployment assumptions.

## 6. Using the vision

Future designers and implementers should use this document to ask:

- Does this choice strengthen the semantic skeleton or merely hard-code today's patina?
- Does authority remain independent of the renderer and delivery environment?
- Can the same identity, context, Place, Morph, Motion, and state survive another materialization?
- Are physical, printable, local, and affordable forms treated as legitimate outcomes?
- Can participant-specific visibility and privacy remain correct across delivery forms?
- Does the design preserve a graceful path among physical, local digital, augmented, and remote use?
- Is a proposed future being labelled honestly rather than described as current capability?

The north star should improve local judgment, not become an excuse for speculative infrastructure. Build the
smallest coherent thing that serves present needs while preserving the shared skeleton and the freedom to
choose a different right line later.

The parked
[Root Morph sign-language design](../Object%20Sing%20&%20Dance%20Factory__/SIGN-LANGUAGE-MORPH-CAPABILITY.md)
design illustrates this principle: compact native Morph structure remains authoritative while language,
foreign notation, reasoning engines, renderers, and materialized databases remain checked edge concerns.
