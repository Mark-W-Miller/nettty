/* Inside-out film composition. Every surface and device has spatial coordinates;
   back objects render before the translucent cortex, front objects after it. */
function embodiedDepth(p) {
  const a = yaw + time * .014;
  return p[1] * Math.sin(pitch) + (p[0] * Math.sin(a) + p[2] * Math.cos(a)) * Math.cos(pitch);
}
function embodiedFace(points, color, opacity) {
  if (opacity < .001) return;
  ctx.globalAlpha = opacity; ctx.fillStyle = color; ctx.beginPath();
  points.forEach((p, i) => { const q = project(p); i ? ctx.lineTo(q[0], q[1]) : ctx.moveTo(q[0], q[1]); });
  ctx.closePath(); ctx.fill();
}
function cortexPoint(u, v, side, pulse) {
  const fold = 1 + side * .024 + .065 * Math.sin(u * 11 + Math.sin(v * 5) * 2) * Math.sin(v * 9 + side * .37);
  return [side * 25 + Math.cos(u) * Math.sin(v) * 34 * fold,
    Math.cos(v) * 48 * fold - 3,
    Math.sin(u) * Math.sin(v) * 47 * fold].map(n => n * pulse);
}
function embodiedDevice(index, visible) {
  const a = index * TAU / 7 + .2;
  const c = [Math.sin(a) * 133, Math.cos(a * 2) * 39, Math.cos(a) * 115];
  const h = [Math.cos(a), 0, -Math.sin(a)], v = [0, 1, 0];
  const tablet = index % 3 === 0;
  const w = (tablet ? 23 : 13) + index * .43, height = (tablet ? 29 : 24) + Math.sin(index * 2.3) * 2;
  const at = (x, y, z = 0) => add(c, add(scale(h, x), add(scale(v, y), [Math.sin(a) * z, 0, Math.cos(a) * z])));
  const corners = [at(-w,-height),at(w,-height),at(w,height),at(-w,height)];
  const back = corners.map(p => add(p, [Math.sin(a) * 3, 0, Math.cos(a) * 3]));
  const front = embodiedDepth(c) < 0;
  const alpha = visible * (front ? .92 : .42);
  const becomeDevice = ramp(time, 168, 180);
  for (let i = 0; i < 4; i++) embodiedFace([corners[i],corners[(i+1)%4],back[(i+1)%4],back[i]], '#4a5968', alpha * becomeDevice);
  embodiedFace(corners, '#101c2d', alpha * becomeDevice);
  stroke([...corners,corners[0]], becomeDevice > .5 ? '#99b7ca' : '#e7bd74', alpha, 1.5);
  for (let row = 0; row < 5; row++) for (let col = 0; col < 4; col++) {
    const p = at((col - 1.5) * w * .46, (row - 2) * height * .3);
    if (col < 3) stroke([p, at((col - .5) * w * .46, (row - 2) * height * .3)], '#eda66c', alpha * .65);
    if (row < 4) stroke([p, at((col - 1.5) * w * .46, (row - 1) * height * .3)], '#c78262', alpha * .38);
    point(p, '#ffb877', 1.1, alpha * (.4 + .6 * Math.sin(time * 2 + row + col + index) ** 2));
  }
  // Familiar speaker and home indicator emerge after the underlying grid boards.
  stroke([at(-3,-height+3),at(3,-height+3)], '#d0e1eb', alpha * becomeDevice, 2);
  stroke([at(-4,height-3),at(4,height-3)], '#aac0d4', alpha * becomeDevice, 1.5);
  const target = scale(c, .4);
  const path = [];
  for (let i = 0; i <= 22; i++) {
    const f = i / 22, p = lerp(target,c,f); p[1] += Math.sin(f * Math.PI) * 15;
    path.push(p);
  }
  stroke(path, '#6eaff2', alpha * .4);
  const packet = (time * .24 + index * .13) % 1;
  point(path[Math.floor(packet * 22)], '#8bcfff', 2, alpha, true);
}
function drawEmbodiedScene() {
  const visible = ramp(time, 132, 144);
  if (visible < .001) return;
  const pulse = 1 + .045 * Math.sin(time * 1.8);
  const tech = ramp(time, 149, 166);
  const devices = Array.from({length:7},(_,i)=>i);
  const depth = i => {const a=i*TAU/7+.2;return embodiedDepth([Math.sin(a)*133,Math.cos(a*2)*39,Math.cos(a)*115]);};
  devices.sort((a,b)=>depth(b)-depth(a));
  devices.filter(i=>depth(i)>=0).forEach(i=>embodiedDevice(i,visible*tech));

  // The Blue thoughtnet is visibly inside, not a diagram alongside the brain.
  const core = particles.slice(0,70).map(p=>scale(p.p,.28*pulse));
  edges.filter(([a,b])=>a<70&&b<70).forEach(([a,b],i)=>{
    stroke([core[a],core[b]],'#328cf5',visible*.55,1);
    point(lerp(core[a],core[b],(time*.4+i*.17)%1),'#98dfff',1.7,visible);
  });
  drawRelayMessages(core,visible,time-140,4);
  core.forEach((p,i)=>point(p,i%5?'#549fff':'#c0e6ff',i%5?1.4:2.7,visible*(.65+.35*Math.sin(time*1.8+i*.05)**2),i%6===0));
  orbit([0,0,0],17*pulse,0,time,'#78caff',visible*.8,1.3);
  orbit([0,0,0],23*pulse,1,-time,'#9bd6ff',visible*.5);

  // Two folded hemispheres, translucent so the source of their pulse stays visible.
  const faces=[];
  for(const side of [-1,1]) for(let u=0;u<28;u++) for(let v=0;v<15;v++) {
    const a=u/28*TAU,b=v/15*Math.PI,du=TAU/28,dv=Math.PI/15;
    const p=[cortexPoint(a,b,side,pulse),cortexPoint(a+du,b,side,pulse),cortexPoint(a+du,b+dv,side,pulse),cortexPoint(a,b+dv,side,pulse)];
    faces.push({p,depth:embodiedDepth(p[0]),u,v});
  }
  faces.sort((a,b)=>b.depth-a.depth);
  faces.forEach(({p,depth,u,v})=>{
    embodiedFace(p,'#2d956f',visible*(depth<0?.12:.055));
    stroke([p[0],p[1],p[2]],'#6bcca0',visible*(depth<0?.4:.15),.65);
    // Red material is woven through the Green tissue, rather than a separate disc.
    if((u+v)%4===0) point(p[0],'#f0848f',1.1,visible*.5);
  });
  const stem=[[0,38,0],[0,57,3],[5,69,5]];
  stroke(stem,'#65c5a0',visible*.6,3);

  // Energy drapes the physical structure. Ordered pulses coexist with excess
  // energy turbulence only after the release; this is the film's energy tension.
  const load=.5+.5*Math.sin(time*.43);
  for(let band=0;band<9;band++) {
    const path=[];
    for(let j=0;j<=70;j++) {
      const a=j/70*TAU;
      const ripple=Math.max(0,load-.35)*Math.sin(a*7-time*1.7+band)*7;
      const r=67+band*2+ripple;
      path.push([Math.cos(a)*r,Math.sin(a)*(51+band)+Math.sin(a*3+band)*4,Math.sin(a+band*.5)*r*.6]);
    }
    stroke(path,band%2?'#ff9fbc':'#e46685',visible*(.08+load*.05),.8);
    // Free energy is drawn inward, while the shell retains a fluctuating surplus.
    point(scale(path[Math.floor((time*.11+band*.1)%1*70)],1-(time*.2+band*.1)%1*.5),'#ffbfdb',1.5,visible*.45);
  }
  devices.filter(i=>depth(i)<0).forEach(i=>embodiedDevice(i,visible*tech));
  ctx.globalAlpha=1;
}

// An orbitable miniature silhouette carries the support → legs → suit metaphor.
function drawAugmentation() {
  const visible = ramp(time, 154, 158) * (1 - ramp(time, 184, 190));
  if (visible < .001) return;
  const powered = ramp(time, 160, 166), suit = ramp(time, 168, 176);
  const at = (x,y,z=0) => [x-100,y+15,z-25];
  const limb = (points,color,alpha,width=2) => stroke(points.map(p=>at(...p)),color,visible*alpha,width);
  orbit(at(0,-31),8,0,0,'#8acfb5',visible*.8,1.5);
  limb([[0,-23],[0,7]],'#83c9ab',.8,3);
  for (const side of [-1,1]) {
    const stride = Math.sin(time*1.8)*5*side;
    limb([[0,-16],[side*15,-7],[side*19,8]],'#83c9ab',.7);
    limb([[0,7],[side*8,23,stride],[side*10,42,-stride],[side*16,42,-stride]],'#91c7b4',.65);
    limb([[side*3,7],[side*10,23,stride],[side*12,42,-stride],[side*18,42,-stride]],'#f6bb79',powered,4);
    point(at(side*10,23,stride),'#8dd5ff',2.4,visible*powered,true);
  }
  limb([[22,-11],[28,42],[32,42]],'#eddab0',(1-powered)*.9,2.5);
  limb([[17,-12],[26,-12]],'#eddab0',(1-powered)*.9,3);
  limb([[-13,-21],[13,-21],[17,-8],[10,8],[-10,8],[-17,-8],[-13,-21]],'#ffc17f',suit,3);
  for (const side of [-1,1]) limb([[side*14,-17],[side*20,-5],[side*22,10]],'#ffc17f',suit,4);
  orbit(at(0,-31),10,0,0,'#ffc17f',visible*suit,2);
  point(at(0,-10,-3),'#90d6ff',4,visible*suit,true);
}

// A single causal cycle: incoming message, local state change, outward replies.
// Retained cycle number changes the receiver's color even between messages.
function drawRelayMessages(positions, visible, clock, count = 3) {
  if (clock < 0 || visible < .001) return;
  const palette = ['#80bfff','#b4e4ff','#bfa9ff','#81e8dd'];
  routes.filter(r => positions[r.a] && positions[r.b]).slice(0,count).forEach((r,i) => {
    if (!positions[r.a] || !positions[r.b]) return;
    const elapsed = clock - i * .65;
    if (elapsed < 0) return;
    const phase = elapsed % 6, cycle = Math.floor(elapsed / 6);
    const center = positions[r.b];
    point(center,palette[(cycle+(phase>=2?1:0))%4],3.5,visible,true);
    if (phase < 2) {
      stroke([positions[r.a],center],'#bba9ff',visible*.6,1.5);
      point(lerp(positions[r.a],center,phase/2),'#dfc6ff',4,visible,true);
    } else if (phase < 2.65) {
      for(let axis=0;axis<3;axis++) orbit(center,3+(phase-2)*19,axis,clock,'#e0f6ff',visible*(1-(phase-2)/.65),1.8);
    } else if (phase < 4.65) {
      const neighbors = edges.filter(([a,b])=>a===r.b||b===r.b).map(([a,b])=>a===r.b?b:a).filter(n=>n!==r.a&&positions[n]).slice(0,3);
      neighbors.forEach(n=>{
        stroke([center,positions[n]],'#72d9ff',visible*.55,1.5);
        point(lerp(center,positions[n],(phase-2.65)/2),'#a9eeff',3,visible,true);
      });
    }
  });
}
