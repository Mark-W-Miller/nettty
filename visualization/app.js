'use strict';
const {chapters, ramp, state, receivedCount, twirlRadius, duration} = NettyEvolution;
const $ = id => document.getElementById(id);
const canvas = $('universe');
const mainContext = canvas.getContext('2d');
let ctx = mainContext;
const blackCanvas = document.createElement('canvas');
const blackContext = blackCanvas.getContext('2d');
const TAU = Math.PI * 2;
let time = 0;
let playing = !matchMedia('(prefers-reduced-motion: reduce)').matches;
let speed = 1, yaw = .25, pitch = -.23, zoom = 1;
let width = 1, height = 1, previous = null, currentChapter = -1;
let seed = 7189;
const random = () => { seed = (seed * 1664525 + 1013904223) >>> 0; return seed / 4294967296; };
const lerp = (a, b, f) => a.map((v, k) => v + (b[k] - v) * f);
const scale = (p, s) => p.map(v => v * s);
const add = (a, b) => a.map((v, k) => v + b[k]);

// The Black body uses one connected close-packed arrangement, with interstitial gaps.
const cells = [];
for (let y = -2; y <= 2; y++) {
  for (let x = -2; x <= 2; x++) {
    for (let z = -2; z <= 2; z++) {
      const p = [(x + (y % 2) * .5) * 57, y * 47, (z + (y % 2) * .5) * 57];
      if (Math.hypot(...p) < 137) cells.push(p);
    }
  }
}
// Population expands outward at a fixed sphere radius. Camera magnification is
// independent of sphere birth: no cell scales up as the population increases.
const blackCells = [];
for (let y = -11; y <= 11; y++) for (let x = -11; x <= 11; x++) for (let z = -11; z <= 11; z++) {
  const p = [(x + (y % 2) * .5) * 14, y * 12, (z + (y % 2) * .5) * 14];
  if (Math.hypot(...p) < 139) blackCells.push(p);
}
blackCells.sort((a, b) => Math.hypot(...a) - Math.hypot(...b));
blackCells.length = Math.min(2800, blackCells.length);
let drawingBlack = false;
// Birth locations are explicitly in gaps between Black spherical regions.
const particles = [];
let attempts = 0;
while (particles.length < 150 && attempts++ < 15000) {
  const p = [(random() - .5) * 280, (random() - .5) * 240, (random() - .5) * 260];
  if (Math.hypot(...p) > 146) continue;
  const nearest = Math.min(...cells.map(c => Math.hypot(...p.map((v, k) => v - c[k]))));
  if (nearest > 28 && nearest < 45) {
    particles.push({ p, shells: 1 + Math.floor(random() * 3), spin: random() > .5 ? 1 : -1, phase: random() * TAU });
  }
}
const edges = [];
particles.forEach((a, i) => {
  const close = particles.map((b, j) => ({ j, distance: Math.hypot(...a.p.map((v, k) => v - b.p[k])) }))
    .filter(b => b.j > i && b.distance < 63).sort((a, b) => a.distance - b.distance).slice(0, 3);
  close.forEach(b => edges.push([i, b.j]));
});
// Signal routes share a stateful intermediate node. Deterministic event counts
// reconstruct retained node state at any film time, including reverse scrubbing.
const routes = edges.filter((_, i) => i % 5 === 0).slice(0, 24).map(([a, b], i) => {
  const next = edges.find(([x, y]) => (x === b && y !== a) || (y === b && x !== a));
  return next ? {a, b, c: next[0] === b ? next[1] : next[0], delay: i * .21} : null;
}).filter(Boolean);
function processorState() {
  const values = particles.map(p => p.shells % 4);
  routes.forEach((r, i) => {
    const received = receivedCount(time, r.delay);
    values[r.b] = (values[r.b] + received * (1 + i % 3)) % 4;
  });
  return values;
}
const holes = [[-80, -28, 35], [91, 32, -35], [10, -78, -68]];
let scene;
function project(p) {
  const angle = yaw + time * .014;
  const x = p[0] * Math.cos(angle) - p[2] * Math.sin(angle);
  const z = p[0] * Math.sin(angle) + p[2] * Math.cos(angle);
  const y = p[1] * Math.cos(pitch) - z * Math.sin(pitch);
  const depth = p[1] * Math.sin(pitch) + z * Math.cos(pitch);
  // The Black-only dolly carries us into its microscopic gaps. Blue is authored
  // at the resulting close-up scale, so it grows into that same screen volume.
  const camera = drawingBlack ? scene.cameraPush : 1;
  const unit = Math.min(width / 460, (height - 190) / 380) * zoom * camera * 780 / (780 + depth);
  return [width * .5 + x * unit, height * .40 + y * unit, unit];
}
function stroke(points, color, opacity = 1, weight = 1, close = false) {
  if (opacity <= .001) return;
  ctx.globalAlpha = Math.min(1, opacity);
  ctx.strokeStyle = color;
  ctx.lineWidth = weight;
  ctx.beginPath();
  points.forEach((p, i) => { const q = project(p); i ? ctx.lineTo(q[0], q[1]) : ctx.moveTo(q[0], q[1]); });
  if (close) ctx.closePath();
  ctx.stroke();
}
function point(p, color, radius = 2, opacity = 1, glow = false) {
  if (opacity <= .001) return;
  const q = project(p), r = Math.max(.25, radius * q[2]);
  ctx.globalAlpha = Math.min(1, opacity);
  if (glow) {
    const g = ctx.createRadialGradient(q[0], q[1], 0, q[0], q[1], r * 5);
    g.addColorStop(0, color); g.addColorStop(.2, color + '99'); g.addColorStop(1, color + '00');
    ctx.fillStyle = g; ctx.fillRect(q[0] - r * 5, q[1] - r * 5, r * 10, r * 10);
  }
  ctx.fillStyle = color;
  ctx.beginPath(); ctx.arc(q[0], q[1], r, 0, TAU); ctx.fill();
}
function orbit(center, radius, axis, phase, color, opacity, weight = .8) {
  const path = [];
  for (let i = 0; i <= 44; i++) {
    const a = i / 44 * TAU;
    const v = axis === 0 ? [0, Math.cos(a) * radius, Math.sin(a) * radius]
      : axis === 1 ? [Math.cos(a) * radius, 0, Math.sin(a) * radius]
      : [Math.cos(a) * radius, Math.sin(a) * radius, 0];
    path.push(add(center, v));
  }
  stroke(path, color, opacity, weight);
  const f = ((phase % TAU) + TAU) % TAU / TAU * 44;
  point(path[Math.floor(f)], color, 1.4, opacity);
}

function pinkField() {
  // Continuous filled surfaces, not particles. A slight red/white tightness difference
  // remains after the phases lock, producing the visible shared breath.
  const visibility = (1 - scene.black * .9) * (1 - scene.blue * .8);
  const center = project([0, 0, 0]);
  for (let band = 0; band < 10; band++) {
    const white = band % 2 === 1;
    const syncPhase = (1 - scene.sync) * band * 1.7;
    const pulse = Math.sin(time * 1.2 + syncPhase);
    const contraction = white ? 1 : 1 - .8 * scene.redRetraction;
    const base = (139 - band * 6) * (white ? .94 : 1) * (1 + .09 * pulse) * contraction;
    ctx.beginPath();
    for (let j = 0; j <= 100; j++) {
      const a = j / 100 * TAU;
      const r = base * (1 + .16 * Math.sin(a * 3 + time * .28 + syncPhase) + .09 * Math.cos(a * 5 - time * .18));
      const x = center[0] + (Math.cos(a) * r + Math.sin(time * .32 + syncPhase) * 15) * center[2];
      const y = center[1] + (Math.sin(a) * r * .9 + Math.cos(time * .25 + syncPhase) * 12) * center[2];
      j ? ctx.lineTo(x, y) : ctx.moveTo(x, y);
    }
    ctx.closePath();
    const g = ctx.createRadialGradient(center[0] - base * .25 * center[2], center[1] - base * .2 * center[2], 0, center[0], center[1], base * 1.3 * center[2]);
    g.addColorStop(0, white ? '#fff5f3' : '#ff4666');
    g.addColorStop(.55, white ? '#f2b7c3' : '#b51d46');
    g.addColorStop(1, white ? '#ffbaca00' : '#6a153b00');
    ctx.globalAlpha = visibility * (white ? .2 : .26);
    ctx.fillStyle = g; ctx.fill();
    ctx.globalAlpha = visibility * .15;
    ctx.strokeStyle = white ? '#ffe9ed' : '#ff5b84'; ctx.lineWidth = 1; ctx.stroke();
  }
}
function twirl() {
  const visible = scene.twirl * (1 - ramp(time, 36, 49)) ;
  if (!visible) return;
  // Exactly two perpendicular rings. Radii sum to a constant and touch zero
  // in opposite phases. Luminous trails show spin even on a circular ring.
  for (let axis = 0; axis < 2; axis++) {
    const radius = twirlRadius(time, axis);
    const size = radius * (1 - scene.black * .58);
    orbit([0, 0, 0], Math.max(.03, size), axis, time * (axis ? -2 : 2), '#f4f3ff', visible, 1.7);
    const trail = [];
    for (let j = 0; j < 65; j++) {
      const ago = j * .018;
      const r = twirlRadius(time - ago, axis) * (1 - scene.black * .58);
      const a = (time - ago) * (axis ? -2 : 2);
      trail.push(axis ? [Math.cos(a) * r, 0, Math.sin(a) * r] : [0, Math.cos(a) * r, Math.sin(a) * r]);
    }
    stroke(trail, axis ? '#ffc9de' : '#ffffff', visible * .7, 2.2);
  }
  point([0, 0, 0], '#fff1f8', 2, visible, true);
}
function blackBody() {
  if (!scene.blackPopulation || scene.blackOpacity <= 0) return;
  drawingBlack = true;
  ctx = blackContext;
  ctx.clearRect(0, 0, width, height);
  const population = blackCells.slice(0, scene.blackPopulation).map((p, i) => ({p, i}));
  const angle = yaw + time * .014;
  population.sort((a, b) => {
    const depth = p => p[1] * Math.sin(pitch) + (p[0] * Math.sin(angle) + p[2] * Math.cos(angle)) * Math.cos(pitch);
    return depth(b.p) - depth(a.p);
  });
  population.forEach(({p, i}) => {
    const c = scale(p, scene.breath);
    const r = 7; // Fixed world-space radius for every sphere throughout its life.
    const q = project(c), screenRadius = r * q[2];
    if (q[0] + screenRadius < 0 || q[0] - screenRadius > width || q[1] + screenRadius < 0 || q[1] - screenRadius > height) return;
    const shade = ctx.createRadialGradient(q[0] - screenRadius * .3, q[1] - screenRadius * .3, 0, q[0], q[1], screenRadius);
    shade.addColorStop(0, '#7a8ba5'); shade.addColorStop(.55, '#344156'); shade.addColorStop(1, '#070c17');
    ctx.globalAlpha = 1;
    ctx.fillStyle = shade;
    ctx.beginPath(); ctx.arc(q[0], q[1], screenRadius, 0, TAU); ctx.fill();
    if (screenRadius > 14 && i % 3 === 0) orbit(c, r, 1, time * .15, '#8a9bb9', .2);
  });
  drawingBlack = false;
  ctx = mainContext;
  ctx.globalAlpha = scene.blackOpacity;
  ctx.drawImage(blackCanvas, 0, 0, width, height);
  ctx.globalAlpha = 1;
}
function bluePosition(p, i) {
  const free = 1 - scene.weave;
  // Affine shells settle together; opposing spins visibly move apart before weaving.
  return p.p.map((v, k) => (v * scene.breath + Math.sin(time * .8 * p.spin + p.phase + k) * (free * 10 + 1.6)) * scene.blueContraction);
}
function blueFabric(positions) {
  if (!scene.blue) return;
  const visible = scene.blue * (1 - scene.matter * .94);
  edges.forEach(([a, b], i) => {
    const bind = ramp(time, 65 + i % 11, 75 + i % 9);
    const A = particles[a], B = particles[b];
    if (A.spin !== B.spin && (a + b) % 3 === 0) {
      if (scene.weave < .98) stroke([positions[a], positions[b]], '#bc85ca', visible * .12 * (1 - scene.weave));
      return;
    }
    const curve = [];
    for (let j = 0; j <= 10; j++) {
      const f = j / 10;
      const p = lerp(positions[a], positions[b], f);
      p[1] += Math.sin(f * Math.PI) * Math.sin(time * .35 + i) * 5;
      curve.push(p);
    }
    stroke(curve, '#388fff', visible * bind * .55, .8);
    for (let strand = 0; strand < 3; strand++) {
      const fiber = curve.map((p, j) => [p[0] + Math.sin(j * .4 + strand + time * .2) * 1.8, p[1] + Math.cos(j * .5 + strand) * 2, p[2] + strand - 1]);
      stroke(fiber, '#4389eb', visible * bind * .2, .5);
    }
    if (bind > .1) {
      const f = (time * .32 + i * .19) % 1;
      point(lerp(positions[a], positions[b], f), '#a7d9ff', 1.4, visible * bind);
    }
  });
  const memory = processorState();
  routes.forEach((r, i) => {
    const elapsed = time - 76 - r.delay;
    if (elapsed < 0) return;
    const phase = elapsed % 8;
    const incoming = phase < 3;
    const processing = phase >= 3 && phase < 3.6;
    const outgoing = phase >= 3.6 && phase < 6.6;
    if (processing) orbit(positions[r.b], 11 + (phase - 3) * 12, 2, time, '#d8edff', visible * .8, 1.5);
    if (incoming || outgoing) {
      const from = incoming ? r.a : r.b, to = incoming ? r.b : r.c;
      const f = incoming ? phase / 3 : (phase - 3.6) / 3;
      stroke([positions[from], positions[to]], '#82b9ff', visible * .35, 1.4);
      const pattern = incoming ? (i + Math.floor(elapsed / 8)) % 4 : memory[r.b];
      for (let bit = 0; bit < 3; bit++) {
        const progress = Math.max(0, f - bit * .055);
        point(lerp(positions[from], positions[to], progress), outgoing ? '#a9e7ff' : '#c4b3ff', bit === 0 ? 2.7 : 1 + ((pattern >> (bit - 1)) & 1), visible, bit === 0);
      }
    }
  });
  particles.forEach((p, i) => {
    const born = ramp(time, 62 + i % 7, 67 + i % 7);
    point(positions[i], ['#669fe6','#93c7ff','#b7d9ff','#e3f1ff'][memory[i]], 1.3 + memory[i] * .4, visible * born, i % 9 === 0);
    if (i % 3 === 0) for (let shell = 0; shell < p.shells; shell++) {
      orbit(positions[i], (3 + shell * 2.7) * born, (i + shell) % 3, time * p.spin / (shell + 1) + p.phase, '#78b9ff', visible * born * .5);
    }
  });
}
function energyWave() {
  if (time < 85 || time > 101) return;
  // The gravity drop is a deliberate story cue, not an inferred mechanism.
  const collapse = 1 - ramp(time, 85, 87);
  if (collapse > 0) {
    orbit([0, 0, 0], 38 * collapse, 1, time, '#ffdce8', .8);
    point([0, 0, 0], '#ffd8e1', 6 * collapse, .7, true);
  }
  if (scene.wave > 0) for (let axis = 0; axis < 3; axis++) {
    orbit([0, 0, 0], scene.wave, axis, time, '#ffb2b5', (1 - ramp(time, 94, 101)) * .7, 2);
    orbit([0, 0, 0], scene.wave * .95, axis, -time, '#ff677e', (1 - ramp(time, 94, 101)) * .35, 6);
  }
}
function matterPosition(p, i, base) {
  const h = holes[i % holes.length];
  const a = p.phase + time * (.14 + i % 4 * .014);
  const r = 19 + (i % 17) * 3.2;
  const swarm = [h[0] + Math.cos(a) * r, h[1] + Math.sin(a * 1.3 + i) * r * .55, h[2] + Math.sin(a) * r];
  let pos = lerp(base, swarm, scene.cool * .85);
  // Retain particle identity while a subset organizes: other matter keeps orbiting.
  if (i < 84) {
    const j = Math.floor(i / 2), side = i % 2 ? Math.PI : 0;
    const a = j * .45 + side + time * .28;
    const helix = [(j - 21) * 6, Math.cos(a) * 24, Math.sin(a) * 24];
    pos = lerp(pos, helix, scene.life);
    if (i >= 42) {
      const n = i - 42, cell = Math.floor(n / 6), corner = n % 6;
      const honey = [(cell % 4 - 1.5) * 39 + Math.cos(corner * TAU / 6) * 23, 36 + Math.sin(corner * TAU / 6) * 23, (Math.floor(cell / 4) - .5) * 43];
      pos = lerp(pos, honey, scene.tools);
    }
    if (i >= 60) {
      const n = i - 60;
      const circuit = [(n % 6 - 2.5) * 26, -54 + Math.floor(n / 6) * 22, 46];
      const net = [Math.sin(n * 2.399) * (62 + n % 5 * 13), Math.cos(n * 1.73) * 65, Math.sin(n * .81) * 67];
      const thought = lerp(circuit, net, scene.thoughtnet);
      pos = lerp(pos, thought, scene.computing);
    }
  }
  return scale(pos, 1 + Math.sin(time * 1.2) * .025);
}
// A deliberately wider astronomical shot after the energy pulse. Three sparse
// spiral systems share the previous matter centers, then one planet fills the view.
function cosmicView() {
  const visibility = scene.cosmos * (1 - scene.earth);
  if (visibility < .001) return;
  const expansion = .3 + .7 * scene.cosmos;
  holes.forEach((h, system) => {
    const c = scale(h, expansion);
    for (let arm = 0; arm < 3; arm++) {
      const points = [];
      for (let n = 0; n < 65; n++) {
        const r = 9 + n * .95;
        const a = arm * TAU / 3 + n * .12 + time * (.045 + system * .008);
        const p = add(c, [Math.cos(a) * r * expansion, Math.sin(a) * r * .27 * expansion, Math.sin(a) * r * .7 * expansion]);
        points.push(p);
        const star = ramp(time, 95 + (n % 7), 100 + (n % 7));
        point(p, n % 4 === 0 ? '#b5cfff' : '#ffd9b0', (.7 + star * (n % 6 === 0 ? 1.5 : .4)), visibility * (.4 + star * .6), n % 13 === 0);
        if (star > .1 && n % 21 === 0) {
          orbit(p, 4, 1, time * .4 + n, '#9aafcc', visibility * star * .4);
        }
      }
      stroke(points, '#b98b9e', visibility * .17, 2);
    }
    const q = project(c);
    ctx.globalAlpha = visibility; ctx.fillStyle = '#01030b';
    ctx.beginPath(); ctx.arc(q[0], q[1], 5 * q[2], 0, TAU); ctx.fill();
    orbit(c, 8, 1, time, '#ffba8e', visibility, 2);
    orbit(c, 11, 1, -time, '#e58c73', visibility * .35, 2);
  });
}
// A stylized globe drawn as a sphere. The selected planet moves continuously
// from the first spiral system to the center as the camera approaches Earth.
const continents = [
  [[-165,65],[-135,72],[-110,65],[-85,50],[-60,48],[-80,25],[-100,15],[-125,38]],
  [[-80,12],[-55,8],[-35,-8],[-48,-28],[-70,-55],[-77,-25]],
  [[-18,35],[10,38],[34,30],[50,12],[40,-15],[18,-35],[5,-25],[-8,5]],
  [[-10,38],[-5,58],[30,70],[60,65],[105,75],[150,60],[170,45],[140,35],[120,8],[90,22],[60,30],[35,38]],
  [[112,-12],[140,-10],[155,-25],[145,-40],[115,-32]],
  [[-52,58],[-25,70],[-40,82],[-65,75]]
];
function earthView() {
  if (time < 101) return;
  const visible = ramp(time, 101, 105) * (1 - scene.tools * .97);
  if (visible < .001) return;
  const start = add(holes[0], [55, 12, 24]);
  const center = lerp(start, [0, 0, 0], scene.earth);
  const q = project(center), radius = (2 + 104 * scene.earth) * q[2];
  const emphasis = visible * (1 - scene.life * .65);
  const halo = ctx.createRadialGradient(q[0], q[1], radius * .9, q[0], q[1], radius * 1.2);
  halo.addColorStop(0, '#559ed744'); halo.addColorStop(1, '#559ed700');
  ctx.globalAlpha = emphasis; ctx.fillStyle = halo;
  ctx.fillRect(q[0] - radius * 1.2, q[1] - radius * 1.2, radius * 2.4, radius * 2.4);
  const ocean = ctx.createRadialGradient(q[0] - radius * .35, q[1] - radius * .3, 0, q[0], q[1], radius);
  ocean.addColorStop(0, '#397dbe'); ocean.addColorStop(.65, '#154775'); ocean.addColorStop(1, '#061122');
  ctx.fillStyle = ocean; ctx.beginPath(); ctx.arc(q[0], q[1], radius, 0, TAU); ctx.fill();
  // Clip all land and cloud strokes to the globe silhouette.
  ctx.save(); ctx.beginPath(); ctx.arc(q[0], q[1], radius, 0, TAU); ctx.clip();
  const rotation = -.15 + (time - 110) * .024;
  const surface = ([lon, lat]) => {
    const a = lon * Math.PI / 180 + rotation, b = lat * Math.PI / 180;
    return [Math.sin(a) * Math.cos(b), -Math.sin(b), Math.cos(a) * Math.cos(b)];
  };
  for (const land of continents) {
    const points = land.map(surface);
    if (points.every(p => p[2] < 0)) continue;
    ctx.beginPath();
    points.forEach((p, i) => {
      const x = q[0] + p[0] * radius, y = q[1] + p[1] * radius;
      i ? ctx.lineTo(x, y) : ctx.moveTo(x, y);
    });
    ctx.closePath(); ctx.fillStyle = '#659779'; ctx.fill();
  }
  ctx.strokeStyle = '#d6e6e9'; ctx.lineWidth = Math.max(.5, radius * .018); ctx.globalAlpha = emphasis * .28;
  for (let band = 0; band < 5; band++) {
    ctx.beginPath();
    for (let j = 0; j <= 30; j++) {
      const x = (j / 30 * 1.6 - .8) * radius;
      const y = (band - 2) * radius * .27 + Math.sin(j * .18 + band + time * .08) * radius * .08;
      j ? ctx.lineTo(q[0] + x, q[1] + y) : ctx.moveTo(q[0] + x, q[1] + y);
    }
    ctx.stroke();
  }
  const night = ctx.createLinearGradient(q[0] - radius, q[1], q[0] + radius, q[1] + radius * .3);
  night.addColorStop(0, '#07132500'); night.addColorStop(.55, '#07132511'); night.addColorStop(1, '#010510bb');
  ctx.globalAlpha = emphasis; ctx.fillStyle = night;
  ctx.fillRect(q[0] - radius, q[1] - radius, radius * 2, radius * 2);
  ctx.restore(); ctx.globalAlpha = 1;
}
function materialWorld(bluePositions) {
  if (!scene.matter) return;
  const positions = particles.map((p, i) => matterPosition(p, i, bluePositions[i]));
  particles.forEach((p, i) => {
    // A local wavefront arrival creates each proton around its existing Blue core.
    const arrival = 87 + Math.hypot(...p.p) / 36;
    let emphasis = 1 - ramp(time, 95, 101) * .97 * (1 - scene.life);
    if (i >= 84) emphasis *= 1 - scene.life * .96;
    else if (i < 42) emphasis *= 1 - scene.tools * .94;
    else if (i < 60) emphasis *= 1 - scene.computing * .94;
    const born = ramp(time, arrival, arrival + 2) * emphasis;
    const pos = positions[i];
    let color = scene.cool > .6 ? ['#ff9393', '#edbd8f', '#e6a5c2'][i % 3] : '#ff637e';
    if (i < 84 && scene.life > .5) color = '#70d9a9';
    if (i >= 42 && i < 84 && scene.tools > .5) color = '#f1ce75';
    if (i >= 60 && i < 84 && scene.computing > .5) color = '#ff9864';
    point(pos, color, 2.2, born, i % 7 === 0);
    if (i % 3 === 0) {
      orbit(pos, 4 + p.shells, i % 3, time * p.spin, color, born * .45);
      // Complex Blue cores stay inside matter, after the outer envelope appears.
      for (let shell = 0; shell < 4; shell++) orbit(pos, 1.1 + shell * .55, shell % 3, time / (shell + 1), '#80c0ff', born * .45);
    }
  });
  for (let i = 0; i < 82; i++) {
    if (i < 42 || scene.tools < 1) {
      if (i % 2 === 0) stroke([positions[i], positions[i + 1]], '#6bc7a7', scene.life * .4 * (1 - scene.tools * .95));
      if (i + 2 < 84) stroke([positions[i], positions[i + 2]], '#73c7ad', scene.life * .6 * (1 - scene.tools * .95));
    }
    if (i >= 42) {
      const next = 42 + Math.floor((i - 42) / 6) * 6 + (i - 42 + 1) % 6;
      stroke([positions[i], positions[next]], '#e6c775', scene.tools * .65 * (1 - scene.computing * .95));
    }
    if (i >= 60 && i < 83) {
      if ((i - 60) % 6 !== 5) stroke([positions[i], positions[i + 1]], '#ffa468', scene.computing * .7 * (1 - scene.thoughtnet));
      if (i + 6 < 84) stroke([positions[i], positions[i + 6]], '#ffa468', scene.computing * .4 * (1 - scene.thoughtnet));
    }
  }
  if (scene.thoughtnet) {
    for (let i = 60; i < 84; i++) {
      for (const step of [1, 7]) {
        const j = 60 + (i - 60 + step) % 24;
        const a = positions[i], b = positions[j];
        const curve = [];
        for (let k = 0; k <= 12; k++) {
          const f = k / 12, p = lerp(a, b, f);
          p[1] += Math.sin(f * Math.PI) * Math.sin(time * .4 + i) * 12;
          curve.push(p);
        }
        stroke(curve, '#dfaaae', scene.thoughtnet * .6);
        const f = (time * .3 + i * .13) % 1;
        point(curve[Math.floor(f * 12)], '#ffbc8e', 2.2, scene.thoughtnet, false);
      }
      orbit(positions[i], 4 + (Math.floor(time * .3 + i) % 3), i % 3, time, '#a9c5ff', scene.thoughtnet * .6);
    }
  }
  if (scene.computing) {
    for (let i = 60; i < 84; i += 5) {
      stroke([positions[i], bluePositions[i]], '#ad9acb', scene.computing * .12);
      const f = (time * .25 + i * .17) % 1;
      point(lerp(positions[i], bluePositions[i], f), '#ffb986', 2, scene.computing * .45);
    }
  }
}

function updateCaption() {
  const chapter = state(time).chapter;
  const transitions = [
    {start:53, end:64, title:'Into the spaces between.', description:'Thousands of equal-sized spheres fill the view. We move inward, toward a gap. The Black body fades away, revealing the place where Blue can begin.'},
    {start:80, end:87, title:'The thoughtnet draws inward.', description:'The breathing Blue fabric contracts. Its chains and signal paths draw closer together as energy gathers toward the center.'},
    {start:87, end:95, title:'The gathered energy opens outward.', description:'A pulse crosses the contracted net. Blue cores take on energy as matter; the view opens with the wave.'},
    {start:95, end:104, title:'From the pulse, a cosmos.', description:'Spinning clouds gather around black holes. Stars brighten within the spirals, with planets around them. Our view is wide now—but one small world is waiting.'},
    {start:104, end:118, title:'One small world fills the view.', description:'We leave the wide cosmos and approach Earth. The spirals recede; oceans, land, and atmosphere resolve. Here the next patterns can become life.'},
    {start:158, end:176, title:'Computers find the shape of thought.', description:'The circuit grid loosens into a flowing thoughtnet. Stateful nodes and information-bearing arcs take the foreground—the organization already present in Blue Space.'}
  ];
  const transition = transitions.find(c => time >= c.start && time < c.end);
  const key = `${chapter}:${transition ? transition.start : 'chapter'}`;
  if (key === currentChapter) return;
  currentChapter = key;
  const c = {...chapters[chapter], ...(transition || {})};
  $('chapter').textContent = chapter === 0 ? 'BEFORE THE SEVEN SPACES' : `0${chapter} / ${c.subtitle.toUpperCase()}`;
  $('title').textContent = c.title;
  $('description').textContent = c.description;
  $('motifs').textContent = c.motifs;
  $('chapter').style.color = c.color;
  $('scene-label').textContent = c.name.toUpperCase();
  document.querySelectorAll('.layer').forEach((el, i) => {
    el.classList.toggle('active', i === chapter);
    el.classList.toggle('emerged', i < chapter);
    el.setAttribute('aria-pressed', String(i === chapter));
  });
}
chapters.forEach((c, i) => {
  const button = document.createElement('button');
  button.className = 'layer'; button.style.setProperty('--color', c.color);
  button.innerHTML = `<span class="num">${i === 0 ? '○' : '0' + i}</span><span class="swatch"></span><span><strong>${c.name}</strong><small>${c.subtitle}</small></span><span class="arrow">↗</span>`;
  button.onclick = () => { time = c.at; updateCaption(); };
  $('layers').append(button);
});
function updatePlay() {
  $('play').textContent = playing ? 'Ⅱ' : '▶';
  $('play').setAttribute('aria-label', playing ? 'Pause evolution' : 'Play evolution');
}
$('play').onclick = () => { playing = !playing; updatePlay(); };
$('restart').onclick = () => { time = 0; playing = true; updatePlay(); updateCaption(); };
$('seek').oninput = e => { time = Number(e.target.value); updateCaption(); };
$('speed').oninput = e => { speed = Number(e.target.value); $('speed-label').value = `${speed}×`; };
$('reset').onclick = () => { yaw = .25; pitch = -.23; zoom = 1; };
$('cinema').onclick = () => {
  document.body.classList.toggle('cinema');
  $('cinema').textContent = document.body.classList.contains('cinema') ? 'Exit cinema ↙' : 'Cinema ↗';
};
document.addEventListener('keydown', e => {
  if (e.key === 'Escape') { document.body.classList.remove('cinema'); $('cinema').textContent = 'Cinema ↗'; }
  if (e.target.matches('input,button,a')) return;
  if (e.code === 'Space') { e.preventDefault(); playing = !playing; updatePlay(); }
  if (/^[0-7]$/.test(e.key)) { time = chapters[Number(e.key)].at; updateCaption(); }
});
let drag = null;
canvas.onpointerdown = e => { drag = [e.clientX, e.clientY]; canvas.setPointerCapture(e.pointerId); };
canvas.onpointermove = e => {
  if (!drag) return;
  yaw += (e.clientX - drag[0]) * .006;
  pitch = Math.max(-1.3, Math.min(1.3, pitch + (e.clientY - drag[1]) * .004));
  drag = [e.clientX, e.clientY];
};
canvas.onpointerup = canvas.onpointercancel = () => { drag = null; };
canvas.addEventListener('wheel', e => { e.preventDefault(); zoom = Math.max(.5, Math.min(2, zoom * Math.exp(-e.deltaY * .001))); }, {passive:false});
new ResizeObserver(() => {
  const box = canvas.getBoundingClientRect(); width = box.width; height = box.height;
  const dpr = Math.min(devicePixelRatio || 1, 2);
  canvas.width = Math.round(width * dpr); canvas.height = Math.round(height * dpr);
  ctx.setTransform(dpr, 0, 0, dpr, 0, 0);
  blackCanvas.width = canvas.width; blackCanvas.height = canvas.height;
  blackContext.setTransform(dpr, 0, 0, dpr, 0, 0);
}).observe(canvas);
// Avoid advancing the film while the tab is hidden, or dropping story time at low FPS.
document.addEventListener('visibilitychange', () => { previous = null; });
function frame(now) {
  if (width <= 1 || height <= 190) { previous = null; requestAnimationFrame(frame); return; }
  const dt = previous === null ? 0 : Math.min((now - previous) / 1000, .25);
  previous = now;
  if (playing && !document.hidden) time += dt * speed;
  scene = state(time);
  ctx.clearRect(0, 0, width, height);
  pinkField(); blackBody();
  const positions = particles.map(bluePosition);
  blueFabric(positions);
  ctx.globalCompositeOperation = 'lighter';
  twirl(); energyWave();
  ctx.globalCompositeOperation = 'source-over';
  cosmicView(); earthView(); materialWorld(positions);
  ctx.globalAlpha = 1;
  updateCaption();
  const progress = Math.min(time, duration);
  $('seek').value = progress;
  $('elapsed').textContent = `${Math.floor(progress / 60)}:${String(Math.floor(progress % 60)).padStart(2, '0')}`;
  $('scene-mode').textContent = time >= 36 && time < 64 ? `${scene.blackPopulation.toLocaleString()} SPHERES · ${time >= 53 ? 'MOVING INSIDE' : 'MULTIPLYING'}` : time >= duration ? 'THE BREATH CONTINUES' : 'CONTINUOUS EVOLUTION';
  requestAnimationFrame(frame);
}
updatePlay(); updateCaption(); requestAnimationFrame(frame);
