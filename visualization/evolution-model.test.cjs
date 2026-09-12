const assert = require('node:assert/strict');
const {state, chapters, receivedCount, twirlRadius} = require('./evolution-model.js');
// Seven spaces plus a pre-particle prelude; no premature matter or Blue particles.
assert.equal(chapters.length, 8);
assert.equal(state(17).twirl, 0);
assert.equal(state(35).black, 0);
assert.equal(state(57).blue, 0);
assert.equal(state(86).matter, 0);
// Growth increments decrease exponentially without turning into contraction.
const increments = [40, 50, 60, 70].map(t => state(t + 1).black - state(t).black);
assert(increments.every((v, i) => v > 0 && (i === 0 || v < increments[i - 1])));
// Smooth emergence at chapter boundaries; all older fields persist at the end.
for (const t of chapters.map(c => c.at)) {
  const a = state(t - .0001), b = state(t + .0001);
  for (const key of ['twirl', 'black', 'blue', 'weave', 'matter', 'life', 'tools', 'computing']) {
    assert(Math.abs(a[key] - b[key]) < .001, `${key} discontinuity at ${t}`);
  }
}
for (const key of ['twirl', 'black', 'blue', 'weave', 'matter', 'life', 'tools', 'computing']) assert(state(168)[key] > .99);
assert.notEqual(state(170).breath, state(171).breath);
// Two radii in exact counter-phase, including a collapse to zero.
for (let t = 0; t < 20; t += .1) assert(Math.abs(twirlRadius(t, 0) + twirlRadius(t, 1) - 65) < 1e-10);
assert(twirlRadius(Math.PI / 2 / 1.8, 1) < 1e-10);
// Events are retained between packets and reconstruct identically after seeking.
assert.equal(receivedCount(78.9, 0), 0);
assert.equal(receivedCount(79, 0), 1);
assert.equal(receivedCount(86.9, 0), 1);
assert.equal(receivedCount(87, 0), 2);
assert.equal(receivedCount(79, 1), 0);
const saved = receivedCount(103, .2);
receivedCount(160, .2);
assert.equal(receivedCount(103, .2), saved);
console.log('Evolution checks passed: emergence, slowing growth, persistent spaces, counter-phase twirl, retained signal state.');

assert(state(51).blackPopulation > 2500);
assert(state(40).blackPopulation < state(50).blackPopulation);
assert.equal(state(53).cameraPush, 1);
assert(state(63).cameraPush > 4);
assert.equal(state(64).blackOpacity, 0);
assert.equal(state(100).blackOpacity, 0);
assert(state(86).blueContraction < state(81).blueContraction);
assert.equal(state(104).earth, 0);
assert.equal(state(118).earth, 1);
assert.equal(state(158).thoughtnet, 0);
assert.equal(state(176).thoughtnet, 1);
console.log('Transition checks passed: multiplying Black, camera push, complete Black fade, Blue contraction, Earth approach, thoughtnet emergence.');

const {dnaRevision, duration} = require('./evolution-model.js');
assert.equal(dnaRevision(118, 1), 0);
assert.equal(dnaRevision(119.4, 1), 1);
assert.equal(dnaRevision(119.45, 1), 1);
assert(dnaRevision(140, 1) > dnaRevision(120, 1));
assert.equal(duration, 204);
assert.equal(state(176).feedback, 0);
assert.equal(state(188).feedback, 1);
console.log('DNA edit retention and learning-surface timing checks passed.');
