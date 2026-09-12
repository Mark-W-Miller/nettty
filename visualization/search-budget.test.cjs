const assert = require('node:assert/strict');
const {budget, benchmarkTrials, benchmarkMassPg, coverage, humanFractionPg} = require('./search-budget.js');
assert.deepEqual([91,92,93,94].map(n => coverage(benchmarkTrials,n)), [100,25,6.25,1.5625]);
assert(Math.abs(budget(benchmarkMassPg).bases - 91) < 1e-10);
assert(Math.abs(budget(1).bases - 94.47114759465) < 1e-8);
assert.equal(humanFractionPg(70,14),1000);
assert(Math.abs(budget(1000).bases - 89.4882554523) < 1e-8);
assert(Math.abs(budget(4).bases - (budget(1).bases - 1)) < 1e-10);
console.log('Search budget checks passed: units, cell-mass assumptions, powers of four and coverage.');
