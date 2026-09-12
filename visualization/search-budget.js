/* Exhaustive, non-repeating enumeration benchmark; not an evolutionary model. */
(function(root) {
  const earthKg = 5.9722e24, seconds = 4e9 * 365.25 * 86400;
  function budget(massPg) {
    const trials = earthKg / (massPg * 1e-15) * seconds;
    return {trials, bases:Math.log(trials) / Math.log(4)};
  }
  const benchmarkTrials = 4 ** 91;
  const benchmarkMassPg = earthKg * seconds / benchmarkTrials / 1e-15;
  const coverage = (trials, bases) => Math.min(100, trials / 4 ** bases * 100);
  const humanFractionPg = (bodyKg, cellsTrillion) => bodyKg * 1e15 / (cellsTrillion * 1e12) / 5;
  const api = {humanFractionPg, budget, benchmarkTrials, benchmarkMassPg, coverage};
  if (typeof module !== 'undefined') module.exports = api;
  else root.NettySearchBudget = api;
})(typeof globalThis !== 'undefined' ? globalThis : this);
