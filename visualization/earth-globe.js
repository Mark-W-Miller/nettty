/* Textured sphere with explicit axial rotation and an independently drifting cloud layer. */
const NettyGlobe = (() => {
  const size = 384, mapWidth = 1024, mapHeight = 512;
  const canvas = document.createElement('canvas'); canvas.width = canvas.height = size;
  const context = canvas.getContext('2d');
  const frame = context.createImageData(size, size);
  const samples = [];
  const cloud = new Float32Array(mapWidth * mapHeight);
  for (let y = 0; y < mapHeight; y++) for (let x = 0; x < mapWidth; x++) {
    const a = x / mapWidth * Math.PI * 2, b = y / mapHeight * Math.PI;
    const flow = Math.sin(a * 7 + Math.sin(b * 8) * 2) + .5 * Math.sin(a * 19 - b * 17) + .25 * Math.sin(a * 41 + b * 33);
    cloud[y * mapWidth + x] = Math.max(0, flow - .55) * .38 * Math.sin(b);
  }
  for (let y = 0; y < size; y++) for (let x = 0; x < size; x++) {
    const nx = (x + .5) / size * 2 - 1, ny = 1 - (y + .5) / size * 2;
    const rr = nx * nx + ny * ny;
    if (rr >= 1) continue;
    const nz = Math.sqrt(1 - rr);
    const ty = ny * .985 + nz * .174, tz = nz * .985 - ny * .174;
    const u = (Math.atan2(nx, tz) / (2 * Math.PI) + .5) * mapWidth;
    const v = Math.min(mapHeight - 1, Math.max(0, Math.floor((.5 - Math.asin(ty) / Math.PI) * mapHeight)));
    const light = .13 + .98 * Math.max(0, -.42 * nx + .3 * ny + .855 * nz);
    samples.push({index:(y * size + x) * 4, u, v, light, rim:Math.pow(1 - nz, 3), alpha:Math.min(1, (1 - rr) * size) * 255});
  }
  let pixels = null, last = NaN;
  const image = new Image();
  image.onload = () => {
    const map = document.createElement('canvas'); map.width = mapWidth; map.height = mapHeight;
    const c = map.getContext('2d'); c.drawImage(image, 0, 0, mapWidth, mapHeight);
    pixels = c.getImageData(0, 0, mapWidth, mapHeight).data;
  };
  image.src = NettyEarthTexture;
  function render(time) {
    if (!pixels) return null;
    // Cache at 24 frames of film time per second; paused frames cost no pixel work.
    const tick = Math.floor(time * 24);
    if (tick === last) return canvas;
    last = tick;
    const rotation = (time - 118) * .11 / (2 * Math.PI) * mapWidth;
    for (const s of samples) {
      const u = ((Math.floor(s.u + rotation) % mapWidth) + mapWidth) % mapWidth;
      const offset = (s.v * mapWidth + u) * 4;
      const cloudU = ((Math.floor(u + time * .7) % mapWidth) + mapWidth) % mapWidth;
      const c = Math.min(.7, cloud[s.v * mapWidth + cloudU]);
      for (let channel = 0; channel < 3; channel++) {
        const surface = pixels[offset + channel] * (1 - c) + 240 * c;
        const air = [30, 95, 185][channel] * s.rim * .38;
        frame.data[s.index + channel] = Math.min(255, surface * s.light + air);
      }
      frame.data[s.index + 3] = s.alpha;
    }
    context.putImageData(frame, 0, 0);
    return canvas;
  }
  return {render};
})();
