/* Shared, deterministic film timing. Also loaded by the focused Node checks. */
(function (root) {
  'use strict';
  const chapters = [
    { at: 0, name: 'Pink Zone', subtitle: 'The breathing energy', color: '#efa4b9', title: 'Before particles, a breath.', description: 'Red and white flow inside one another. Almost alike, one held a little tighter: a persistent gradient. The blobs change shape, organize, and gradually find a shared pulse.', motifs: 'TWO CHARACTERISTICS · ONE GRADIENT · A SHARED BREATH' },
    { at: 10, name: 'White Space', subtitle: 'The single twirl', color: '#edf2ff', title: 'One particle. Two counter-pulses.', description: 'As the red and white synchronize, the center spins up and the red wobble quickly pulls inward. One horizontal ring and one vertical ring twirl in counter-sync: one contracts toward zero while the other expands.', motifs: 'ONE GOD PARTICLE · TWO RINGS · OPPOSITE PHASE' },
    { at: 36, name: 'Black Space', subtitle: 'One growing entanglement', color: '#8d9bb5', title: 'The twirl stirs a gravitational floor.', description: 'Spherical regions press into a connected body, leaving imperfect gaps. Like ice deep beneath a glacier, this is one growing entanglement. More and more equal-sized spheres join the body. As their number becomes overwhelming, the camera moves into a gap and the Black body disappears.', motifs: 'MULTIPLYING SPHERES · INTO THE GAPS · A CHANGE OF SCALE' },
    { at: 58, name: 'Blue Space', subtitle: 'The sentient net processor', color: '#66adff', title: 'In the gaps, a mind takes shape.', description: 'Simple nested shells spin up between the spheres. Different spins bind or repel. Asymmetric chains weave a sentient cotton-candy net: stateful nodes joined by information-bearing arcs. Each arrival changes a node; the outgoing pattern carries that change forward.', motifs: 'SHELL AFFINITY · ASYMMETRIC CHAINS · STATE & SIGNAL' },
    { at: 85, name: 'Red Space', subtitle: 'The energy release', color: '#ff7186', title: 'A drop at the center. A wave of fire.', description: 'Blue contracts as energy gathers. The central gravity drops and the Big Bang opens the view: black holes, spinning clouds, stars, and planets. Each proton carries a Blue core. The camera finds one small world in the cosmos.', motifs: 'GRAVITY DROP · EXPANDING PULSE · BLUE CORES INSIDE MATTER' },
    { at: 110, name: 'Green Space', subtitle: 'Life takes form', color: '#69dbac', title: 'From the cosmos, into Earth.', description: 'The view closes in on Earth. Blue drives the organization of life: DNA is an output language, its patterns written into living matter. Life is intricate physical machinery expressing Blue’s intention. The two strands wind into a double helix; Blue agents travel along them and rewrite their patterns.', motifs: 'BLUE PROCESSES · DNA EXPRESSES · LIFE TAKES FORM' },
    { at: 170, name: 'Yellow Space', subtitle: 'Life extends itself', color: '#efd078', title: 'Life builds beyond itself.', description: 'The living field organizes matter into nonliving structures: cells of a hive, tools, machines. The same material flows into new arrangements, extending life’s reach.', motifs: 'LIFE · CONSTRUCTION · TOOLS' },
    { at: 174, name: 'Orange Space', subtitle: 'A new conversation', color: '#ff9c68', title: 'Our computers become thoughtnets.', description: 'The thoughtnet is already here in Blue Space. As Blue’s intention becomes the intricate machinery of life, human intention becomes the ordered machinery of computers. Their local grids remain connected by information-bearing arcs: machinery becoming a thought machine. Orange reaches toward the net that came first.', motifs: 'COMPUTERS → THOUGHTNETS · THE NET CAME FIRST' }
  ];
  const clamp = x => Math.max(0, Math.min(1, x));
  const ramp = (t, a, b) => { const x = clamp((t - a) / (b - a)); return x * x * (3 - 2 * x); };
  function state(t) {
    const age = Math.max(0, t - 36);
    return {
      chapter: chapters.reduce((n, chapter, i) => t >= chapter.at ? i : n, 0),
      pairSettled: ramp(t, 4, 10), twirlScale: 1 - .68 * ramp(t, 10, 18),
      sync: ramp(t, 4, 18), twirl: ramp(t, 10, 18),
      redRetraction: ramp(t, 18, 21),
      black: 1 - Math.exp(-age / 5), blue: ramp(t, 62, 73),
      blackOpacity: 1 - ramp(t, 58, 64), cameraPush: 1 + 4 * ramp(t, 53, 64),
      blackPopulation: Math.max(0, Math.min(2800, Math.floor(pumpTurns(t) - pumpTurns(36)))),
      weave: ramp(t, 67, 84), wave: Math.max(0, (t - 87) * 36),
      matter: ramp(t, 87, 102), cool: ramp(t, 99, 113),
      blueContraction: 1 - .72 * ramp(t, 80, 87), cosmos: ramp(t, 89, 102), earth: ramp(t, 104, 118),
      life: ramp(t, 118, 132), tools: ramp(t, 170, 174),
      computing: ramp(t, 174, 182), thoughtnet: ramp(t, 182, 200), feedback: ramp(t, 200, 212), breath: 1 + .045 * Math.sin(t * 1.2)
    };
  }
  function pairRadius(t, white) {
    return (white ? 42 : 40) + (white ? -1 : 1) * 10 * Math.sin(t * 1.8);
  }
  function pumpTurns(t) {
    const age = Math.max(0, Math.min(t, 64) - 36);
    return Math.max(0, Math.min(t, 36) - 10) * .2 + .2 / .33 * Math.expm1(.33 * age) + Math.max(0,t-64)*6;
  }
  function receivedCount(t, delay) {
    const elapsed = t - 76 - delay;
    return elapsed < 3 ? 0 : Math.floor((elapsed - 3) / 8) + 1;
  }
  function twirlRadius(t, axis) {
    return 65 * (1 + Math.sin(t * 1.8 + axis * Math.PI)) / 2;
  }
  function dnaRevision(t, rung) {
    if (t < 119) return 0;
    let changes = 0;
    for (let agent = 0; agent < 5; agent++) {
      const first = ((rung - 1 - agent * 8) % 40 + 40) % 40 + .65;
      const travel = (t - 119) * 2;
      if (travel >= first) changes += 1 + Math.floor((travel - first) / 40);
    }
    return changes;
  }
  const api = { pairRadius, pumpTurns, dnaRevision, chapters, clamp, ramp, state, receivedCount, twirlRadius, duration: 228 };
  if (typeof module !== 'undefined') module.exports = api;
  else root.NettyEvolution = api;
})(typeof globalThis !== 'undefined' ? globalThis : this);
