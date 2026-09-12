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

// A brief journey through living tissue bridges the globe and molecular scale.
function livingTissue() {
  const visible = ramp(time,118,121)*(1-ramp(time,129,132));
  if(visible<.001)return;
  const dive=ramp(time,121,128), growth=1+dive*5;
  const unit=Math.min(width/460,(height-190)/380)*zoom;
  const cx=width*.5, cy=height*.40;
  ctx.save();
  for(let i=10;i>=0;i--) {
    const a=i*2.399, r=i===0?0:90+Math.sqrt(i)*32;
    const x=cx+Math.cos(a)*r*growth*unit,y=cy+Math.sin(a)*r*growth*unit;
    const radius=(i===0?79:39+i)*growth*unit;
    ctx.globalAlpha=visible*(i===0?.65:.35);
    const fill=ctx.createRadialGradient(x-radius*.2,y-radius*.2,0,x,y,radius);
    fill.addColorStop(0,'#163d2a');fill.addColorStop(.85,'#0d2a22');fill.addColorStop(1,'#43986a');
    ctx.fillStyle=fill;ctx.beginPath();ctx.ellipse(x,y,radius,radius*.82,0,0,TAU);ctx.fill();
    ctx.strokeStyle='#7ddd9b';ctx.lineWidth=1.6;ctx.stroke();
    ctx.beginPath();ctx.ellipse(x,y,radius*.96,radius*.78,0,0,TAU);ctx.strokeStyle='#386e54';ctx.stroke();
    // Organelles stay in the cytoplasm and move past the viewer during the dive.
    for(let j=0;j<8;j++) {
      const angle=j*2.4+i, ox=x+Math.cos(angle)*radius*.65,oy=y+Math.sin(angle)*radius*.52;
      ctx.beginPath();ctx.ellipse(ox,oy,radius*.10,radius*.04,angle,0,TAU);ctx.strokeStyle=j%2?'#8aa96d':'#699f89';ctx.stroke();
    }
    const nucleus=radius*.29;
    ctx.globalAlpha=visible*.48;ctx.beginPath();ctx.ellipse(x,y,nucleus,nucleus*.88,0,0,TAU);
    ctx.fillStyle='#12313b';ctx.fill();ctx.strokeStyle='#71bcb0';ctx.stroke();
    ctx.globalAlpha=visible*.25;
    for(let pore=0;pore<12;pore++) {
      const angle=pore*TAU/12;
      ctx.beginPath();ctx.arc(x+Math.cos(angle)*nucleus,y+Math.sin(angle)*nucleus*.88,2*unit,0,TAU);ctx.fillStyle='#97dec5';ctx.fill();
    }
  }
  ctx.restore();
}

// A periodic-table excerpt opens into carbon's four spatial bonding directions.
function elementBridge() {
  const visible=ramp(time,98,101)*(1-ramp(time,112,116));
  if(visible<.001)return;
  const focus=ramp(time,105,111);
  const rows=[['H',1,1],['He',18,1],['Li',1,2],['Be',2,2],['B',13,2],['C',14,2],['N',15,2],['O',16,2],['F',17,2],['Ne',18,2],['Na',1,3],['Mg',2,3],['Al',13,3],['Si',14,3],['P',15,3],['S',16,3],['Cl',17,3],['Ar',18,3],...['K','Ca','Sc','Ti','V','Cr','Mn','Fe','Co','Ni','Cu','Zn','Ga','Ge','As','Se','Br','Kr'].map((symbol,i)=>[symbol,i+1,4])];
  rows.forEach(([symbol,col,row],i)=>{
    const carbon=symbol==='C';
    const initial=[(col-9.5)*15,(row-2.5)*21-23,0];
    const center=carbon?lerp(initial,[0,0,0],focus):scale(initial,1+focus*.8);
    const alpha=visible*(carbon?1:1-focus);
    const unit=Math.min(width/340,(height-190)/300)*zoom;
    const q=[width*.5+center[0]*unit,height*.40+center[1]*unit,unit];
    const size=(carbon?6.7+focus*7:6.7)*unit;
    ctx.globalAlpha=alpha;ctx.fillStyle=carbon?'#163c2c':'#231a24';
    ctx.fillRect(q[0]-size,q[1]-size,size*2,size*2);
    ctx.strokeStyle=carbon?'#83efab':'#c08f9e';ctx.lineWidth=carbon?1.5:.6;
    ctx.strokeRect(q[0]-size,q[1]-size,size*2,size*2);
    ctx.fillStyle=carbon?'#acffbe':'#ecd5d8';ctx.font=`${Math.max(7,size*.95)}px Arial`;ctx.textAlign='center';ctx.textBaseline='middle';ctx.fillText(symbol,q[0],q[1]);
  });
  const c=[0,0,0], strength=visible*focus;
  const sites=[[37,37,37],[-37,-37,37],[-37,37,-37],[37,-37,-37]];
  sites.forEach((p,i)=>{
    stroke([c,p],'#9be7ac',strength,2);
    point(p,'#76ed9c',5,strength,true);
    orbit(p,8, i%3,time*.5+i,'#7cbaff',strength*.6);
    point(lerp(p,c,(time*.3+i*.21)%1),'#85caff',2,strength,true);
  });
  ctx.globalAlpha=visible*(1-focus);ctx.fillStyle='#c4a9b1';ctx.font='10px Arial';ctx.textAlign='center';
  ctx.fillText('ELEMENTS · PERIODIC TABLE · 1–36',width*.5,height*.40+78);
  ctx.globalAlpha=1;
}

// A human likeness is only a translucent outer veil: the working net remains
// the brightest subject. The reaching pose recalls Renaissance ceiling painting.
function humanVeil() {
  const visible=ramp(time,180,193);
  if(visible<.001)return;
  const veil=(points,color,alpha,width=1)=>{
    embodiedFace(points,color,visible*alpha);
    stroke(points,color,visible*alpha*3,width,true);
  };
  // Broad drapery encloses the brain, the energy and the surrounding instruments.
  veil([[-55,-81,-18],[-100,-61,-10],[-145,-10,0],[-159,52,7],[-123,101,5],[-62,126,0],[25,119,0],[102,86,8],[136,31,5],[104,-28,-8],[61,-75,-18]],'#b9a6b3',.045,1.2);
  veil([[-42,-66,-4],[-71,-45,0],[-80,18,4],[-60,77,0],[-21,100,0],[35,90,0],[69,38,0],[56,-39,0],[31,-68,0]],'#d6b7a3',.045);
  // Head, brow, nose and beard: an older human, not an opaque character model.
  veil([[-24,-76,0],[-36,-94,0],[-35,-116,0],[-23,-132,0],[-5,-137,0],[14,-132,0],[25,-120,0],[26,-106,0],[34,-99,0],[25,-95,0],[25,-82,0],[12,-65,0],[-3,-59,0],[-20,-67,0]],'#ddcaba',.10,1.2);
  stroke([[-31,-110,0],[-18,-117,0],[-3,-114,0],[11,-115,0],[21,-110,0]],'#eee0d0',visible*.30,1.2);
  stroke([[8,-105,-1],[18,-106,-1]],'#f5e9d8',visible*.38,1.4);
  for(let i=0;i<7;i++) stroke([[-24+i*7,-85,0],[-19+i*5,-70+i%2*5,0],[-6+i*2,-60,0]],'#d8d0ca',visible*.18,.8);
  for(let i=0;i<5;i++) stroke([[-30-i,-101-i*4,0],[-37-i,-118,0],[-24,-133-i,0],[-5,-137-i,0],[17,-132,0]],'#e1d9d1',visible*.16,.9);
  // One relaxed arm, one reaching hand with a small gap beyond the fingertip.
  veil([[-61,-46,0],[-89,-22,0],[-108,18,0],[-93,33,0],[-72,4,0],[-40,-21,0]],'#d6b7a3',.055);
  veil([[42,-53,0],[67,-45,0],[100,-64,0],[130,-74,0],[150,-72,0],[165,-77,0],[172,-75,0],[152,-65,0],[136,-61,0],[108,-45,0],[72,-23,0],[46,-27,0]],'#e4c8b0',.085,1.1);
  stroke([[137,-68,0],[151,-63,0],[160,-64,0]],'#ead4bd',visible*.27);
  for(let i=0;i<8;i++) stroke([[-124+i*32,49,5],[-112+i*29,85,5],[-80+i*20,110,5]],'#a9a2bb',visible*.10,.7);
}
