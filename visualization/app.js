'use strict';
const {chapters, ramp, state, receivedCount, twirlRadius, duration} = NettyEvolution;
const $ = id => document.getElementById(id);
const canvas = $('universe');
const ctx = canvas.getContext('2d');
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
  const unit = Math.min(width / 460, (height - 190) / 380) * zoom * 780 / (780 + depth);
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
  const visible = scene.twirl * (1 - scene.black * .55);
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
  if (!scene.black) return;
  const opacity = scene.black * (1 - scene.blue * .76);
  cells.forEach((p, i) => {
    const c = scale(p, scene.black * scene.breath);
    const r = 29 * scene.black * scene.breath;
    const projected = project(c), screenRadius = Math.max(.01, r * projected[2]);
    const shade = ctx.createRadialGradient(projected[0] - screenRadius * .3, projected[1] - screenRadius * .3, 0, projected[0], projected[1], screenRadius);
    shade.addColorStop(0, '#65728c'); shade.addColorStop(.7, '#202b40'); shade.addColorStop(1, '#070c17');
    ctx.globalAlpha = opacity * .65; ctx.fillStyle = shade;
    ctx.beginPath(); ctx.arc(projected[0], projected[1], screenRadius, 0, TAU); ctx.fill();
    for (let axis = 0; axis < 3; axis++) orbit(c, r, axis, time * .15 * (i % 2 ? 1 : -1), '#8a9bb9', opacity * .3);
    if (i > 0) {
      const near = cells.findIndex((q, j) => j < i && Math.hypot(...p.map((v, k) => v - q[k])) < 64);
      if (near >= 0) stroke([c, scale(cells[near], scene.black * scene.breath)], '#6b7a99', opacity * .25);
    }
  });
}
function bluePosition(p, i) {
  const free = 1 - scene.weave;
  // Affine shells settle together; opposing spins visibly move apart before weaving.
  return p.p.map((v, k) => v * scene.breath + Math.sin(time * .8 * p.spin + p.phase + k) * (free * 10 + 1.6));
}
function blueFabric(positions) {
  if (!scene.blue) return;
  const visible = scene.blue * (1 - scene.matter * .47);
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
    const born = ramp(time, 58 + i % 10, 64 + i % 10);
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
      pos = lerp(pos, circuit, scene.computing);
    }
  }
  return scale(pos, 1 + Math.sin(time * 1.2) * .025);
}
function materialWorld(bluePositions) {
  if (!scene.matter) return;
  const positions = particles.map((p, i) => matterPosition(p, i, bluePositions[i]));
  particles.forEach((p, i) => {
    // A local wavefront arrival creates each proton around its existing Blue core.
    const arrival = 87 + Math.hypot(...p.p) / 36;
    const born = ramp(time, arrival, arrival + 2);
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
  // Fewer black holes than protons, with visible proton swarms around them.
  holes.forEach((h, i) => {
    const born = ramp(time, 90 + i, 94 + i);
    const q = project(h), r = 7 * q[2];
    ctx.globalAlpha = born; ctx.fillStyle = '#02030a'; ctx.beginPath(); ctx.arc(q[0], q[1], r, 0, TAU); ctx.fill();
    orbit(h, 10 + Math.sin(time * 1.2) * .5, 1, time * .6, '#ffb7a4', born * .8, 1.6);
  });
  for (let i = 0; i < 82; i++) {
    if (i < 42 || scene.tools < 1) {
      if (i % 2 === 0) stroke([positions[i], positions[i + 1]], '#6bc7a7', scene.life * .4 * (i >= 42 ? 1 - scene.tools : 1));
      if (i + 2 < 84) stroke([positions[i], positions[i + 2]], '#73c7ad', scene.life * .6 * (i >= 40 ? 1 - scene.tools : 1));
    }
    if (i >= 42) {
      const next = 42 + Math.floor((i - 42) / 6) * 6 + (i - 42 + 1) % 6;
      stroke([positions[i], positions[next]], '#e6c775', scene.tools * .65 * (i >= 60 ? 1 - scene.computing : 1));
    }
    if (i >= 60 && i < 83) {
      if ((i - 60) % 6 !== 5) stroke([positions[i], positions[i + 1]], '#ffa468', scene.computing * .7);
      if (i + 6 < 84) stroke([positions[i], positions[i + 6]], '#ffa468', scene.computing * .4);
    }
  }
  if (scene.computing) {
    for (let i = 60; i < 84; i += 5) {
      stroke([positions[i], bluePositions[i]], '#ad9acb', scene.computing * .3);
      const f = (time * .25 + i * .17) % 1;
      point(lerp(positions[i], bluePositions[i], f), '#ffb986', 2, scene.computing);
    }
  }
}

function updateCaption() {
  const chapter = state(time).chapter;
  if (chapter === currentChapter) return;
  currentChapter = chapter;
  const c = chapters[chapter];
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
  materialWorld(positions);
  ctx.globalAlpha = 1;
  updateCaption();
  const progress = Math.min(time, duration);
  $('seek').value = progress;
  $('elapsed').textContent = `${Math.floor(progress / 60)}:${String(Math.floor(progress % 60)).padStart(2, '0')}`;
  $('scene-mode').textContent = time >= duration ? 'THE BREATH CONTINUES' : 'CONTINUOUS EVOLUTION';
  requestAnimationFrame(frame);
}
updatePlay(); updateCaption(); requestAnimationFrame(frame);
