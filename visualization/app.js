'use strict';
// Visual choreography drawn from design/Project Netty Anon.pdf, not the Java simulation.
const layers = [
  {name:'White Space', subtitle:'The Entanglement of Thought', color:'#e4eeff', title:'The first impulse.', description:'The Plate Spinner taps the field into motion. Binary structures switch on and off, sending directional pulses through the foundational space of thought and change.', motifs:'BINARY STRUCTURES · DIRECTIONAL PULSES · THE PLATE SPINNER'},
  {name:'Black Space', subtitle:'The Gravitational Floor', color:'#8995b0', title:'A floor of interlocking spins.', description:'Alternating spins settle into repeating patterns, interlocking like gears across three dimensions. A gravitational container holds waves and spins in a balanced, Goldilocks state.', motifs:'ALTERNATING SPINS · THREE AXES · A STABLE FRAMEWORK'},
  {name:'Blue Space', subtitle:'The Brain of God', color:'#579dff', title:'The fabric begins to think.', description:'Concentric shells bind into asymmetric chains and stateful nodes. Chaos-led particle trains carry patterns through the fabric; Turing Dust Bunnies move across it, reading and rewriting.', motifs:'SPINNING SHELLS · PARTICLE TRAINS · TURING DUST BUNNIES'},
  {name:'Red Space', subtitle:'The Realm of Matter', color:'#ff667c', title:'Let there be light.', description:'Blue Space prototypes inflate with energy. Hot particles emerge, then gather into atoms and molecular structures as the field cools. Matter retains the influence of the fabric beneath it.', motifs:'ENERGY · PROTONS · ATOMS · CHEMISTRY'},
  {name:'Green Space', subtitle:'The Domain of Life', color:'#59d9ac', title:'Matter learns to remember.', description:'Carbon chains become richer structures. DNA stores and transmits patterns, while the machinery of life reads them. Blue Space shapes the ordering of matter into living systems.', motifs:'CARBON CHAINS · DNA · LIVING INFORMATION'},
  {name:'Yellow Space', subtitle:'Life’s Tools', color:'#f2cf70', title:'Life extends its reach.', description:'Living systems build things beyond themselves: beehives, tools, and machines. Nonliving structures extend life’s influence and form a bridge to artificial systems.', motifs:'BEEHIVES · TOOLS · CONSTRUCTION'},
  {name:'Orange Space', subtitle:'Human Computer Technology', color:'#ff975c', title:'A new conversation begins.', description:'Circuits, computers, and AI express information in the realm of matter. The next chapter imagines a read/write connection between our machines and the Blue Space.', motifs:'CIRCUITS · COMPUTATION · A READ / WRITE INTERFACE'}
];
const $ = id => document.getElementById(id);
const canvas=$('universe'), ctx=canvas.getContext('2d');
let selected=-1, playing=!matchMedia('(prefers-reduced-motion: reduce)').matches, touring=false;
let time=0, tourTime=0, speed=1, yaw=.38, pitch=-.55, zoom=1, width=0,height=0,last=0;
let focus=Array(7).fill(1), spread=1;
const TAU=Math.PI*2;
// A seeded layout keeps shots reproducible between reloads and layer selections.
let seed=7189;
function random(){seed=(seed*1664525+1013904223)>>>0;return seed/4294967296;}
const nodes=Array.from({length:54},()=>{const a=random()*TAU,r=Math.sqrt(random())*150;return [Math.cos(a)*r,(random()-.5)*20,Math.sin(a)*r];});
const edges=[];
for(let i=0;i<nodes.length;i++) for(let j=i+1;j<nodes.length;j++) {const d=Math.hypot(...nodes[i].map((v,k)=>v-nodes[j][k]));if(d<60) edges.push([i,j]);}
const stars=Array.from({length:180},()=>[random(),random(),random()]);
layers.forEach((layer,i)=>{const button=document.createElement('button');button.className='layer';button.style.setProperty('--color',layer.color);button.innerHTML=`<span class="num">0${i+1}</span><span class="swatch"></span><span><strong>${layer.name}</strong><small>${layer.subtitle}</small></span><span class="arrow">↗</span>`;button.setAttribute('aria-pressed','false');button.onclick=()=>{stopTour();select(i);};$('layers').append(button);});
function select(i){selected=i;document.querySelectorAll('.layer').forEach((el,k)=>{el.classList.toggle('active',k===i);el.setAttribute('aria-pressed',String(k===i));});$('all').classList.toggle('active',i===-1);$('all').setAttribute('aria-pressed',String(i===-1));const l=layers[i];$('chapter').textContent=l?`0${i+1} / ${l.subtitle.toUpperCase()}`:'THE COLLECTIVE PICTURE';$('title').textContent=l?l.title:'Everything, all at once.';$('description').textContent=l?l.description:'Seven kinds of behavior occupy one field. Pulses become patterns; patterns become thought, matter, life, tools, and computation.';$('motifs').textContent=l?l.motifs:'PULSE → SPIN → PATTERN → POSSIBILITY';$('scene-label').textContent=l?l.name.toUpperCase():'COMPOSITE FIELD';$('scene-mode').textContent=l?'LAYER STUDY': '07 / 07 LAYERS';$('chapter').style.color=l?l.color:'';}
function updatePlay(){$('play').textContent=playing?'Ⅱ':'▶';$('play').setAttribute('aria-label',playing?'Pause animation':'Play animation');}
function stopTour(){touring=false;$('tour').setAttribute('aria-pressed','false');$('tour').textContent='▶ Play the seven-layer journey';$('progress').style.width='0%';}
$('all').onclick=()=>{stopTour();select(-1);};
$('play').onclick=()=>{playing=!playing;updatePlay();};
$('tour').onclick=()=>{if(touring){stopTour();return;}touring=true;tourTime=0;playing=true;updatePlay();select(0);$('tour').setAttribute('aria-pressed','true');$('tour').textContent='■ Stop journey';};
$('speed').oninput=e=>{speed=Number(e.target.value);$('speed-label').value=`${speed}×`;};
$('reset').onclick=()=>{yaw=.38;pitch=-.55;zoom=1;};
$('cinema').onclick=()=>{document.body.classList.toggle('cinema');$('cinema').textContent=document.body.classList.contains('cinema')?'Exit cinema ↙':'Cinema ↗';};
document.addEventListener('keydown',e=>{if(e.key==='Escape'){document.body.classList.remove('cinema');$('cinema').textContent='Cinema ↗';}if(e.target.matches('input,button,a'))return;if(e.code==='Space'){e.preventDefault();playing=!playing;updatePlay();}if(/^[1-7]$/.test(e.key)){stopTour();select(Number(e.key)-1);}if(e.key==='0'){stopTour();select(-1);}});
let drag=null;
canvas.onpointerdown=e=>{drag=[e.clientX,e.clientY];canvas.setPointerCapture(e.pointerId);};
canvas.onpointermove=e=>{if(!drag)return;yaw+=(e.clientX-drag[0])*.006;pitch=Math.max(-1,Math.min(1,pitch+(e.clientY-drag[1])*.004));drag=[e.clientX,e.clientY];};
canvas.onpointerup=canvas.onpointercancel=()=>{drag=null;};
canvas.addEventListener('wheel',e=>{e.preventDefault();zoom=Math.max(.55,Math.min(2,zoom*Math.exp(-e.deltaY*.001)));},{passive:false});
new ResizeObserver(()=>{const box=canvas.getBoundingClientRect();width=box.width;height=box.height;const dpr=Math.min(devicePixelRatio||1,2);canvas.width=Math.round(width*dpr);canvas.height=Math.round(height*dpr);ctx.setTransform(dpr,0,0,dpr,0,0);}).observe(canvas);
function project(p,layer){const y=p[1]+(layer-3)*43*spread;const x=p[0]*Math.cos(yaw)-p[2]*Math.sin(yaw),z=p[0]*Math.sin(yaw)+p[2]*Math.cos(yaw);const yy=y*Math.cos(pitch)-z*Math.sin(pitch),zz=y*Math.sin(pitch)+z*Math.cos(pitch);const scale=Math.min(Math.min(width/430,(height-210)/370)*(1+.37*(1-spread)),width/390)*zoom;const perspective=800/(800+zz);return [width*.5+x*scale*perspective,height*.40+yy*scale*perspective,scale*perspective];}
let alpha=1,color='#fff';
function line(a,b,l,opacity=.3,w=.7){const p=project(a,l),q=project(b,l);ctx.globalAlpha=alpha*opacity;ctx.strokeStyle=color;ctx.lineWidth=w;ctx.beginPath();ctx.moveTo(p[0],p[1]);ctx.lineTo(q[0],q[1]);ctx.stroke();}
function dot(p,l,r=1.7,opacity=1){const q=project(p,l);ctx.globalAlpha=alpha*opacity;ctx.fillStyle=color;ctx.beginPath();ctx.arc(q[0],q[1],Math.max(.4,r*q[2]),0,TAU);ctx.fill();}
function ring(c,r,l,axis=1,phase=0,opacity=.3){let prev;for(let k=0;k<=48;k++){const a=k/48*TAU;let v=[Math.cos(a)*r,0,Math.sin(a)*r];if(axis===0)v=[0,Math.cos(a)*r,Math.sin(a)*r];if(axis===2)v=[Math.cos(a)*r,Math.sin(a)*r,0];const p=v.map((v,k)=>v+c[k]);if(prev)line(prev,p,l,opacity);prev=p;}let v=[Math.cos(phase)*r,0,Math.sin(phase)*r];if(axis===0)v=[0,Math.cos(phase)*r,Math.sin(phase)*r];if(axis===2)v=[Math.cos(phase)*r,Math.sin(phase)*r,0];dot(v.map((v,k)=>v+c[k]),l,2,Math.min(1,opacity*3));}
function mix(a,b,t){return a.map((v,k)=>v+(b[k]-v)*t);}
function drawLayer(l){
 color=layers[l].color;alpha=focus[l];if(alpha<.005)return;
 // The seven circular fields are a staging device, not physical boundaries.
 ring([0,0,0],171,l,1,time*.08,.14);
 if(l===0){for(let x=-7;x<=7;x++)for(let z=-7;z<=7;z++){if(x*x+z*z>53)continue;const p=[x*20,0,z*20],on=Math.sin(x*2.3+z*1.7+Math.floor(time*2))>.35;dot(p,l,on?1.9:.6,on?.85:.15);if(on){const f=(time*2+x*.17+z*.23)%1;line(p,[p[0]+12*f,-7*f,p[2]+12*f],l,.4);}}}
 if(l===1){for(let x=-3;x<=3;x++)for(let z=-3;z<=3;z++){if(x*x+z*z>12)continue;const p=[x*41,0,z*41],dir=(x+z)%2===0?1:-1;for(let axis=0;axis<3;axis++)ring(p,19,l,axis,time*dir+axis,.25);dot(p,l,1.2,.5);}}
 if(l===2){edges.forEach(([a,b],i)=>{line(nodes[a],nodes[b],l,.25);const f=(time*.22+i*.137)%1;for(let j=0;j<4;j++)dot(mix(nodes[a],nodes[b],(f+j*.038)%1),l,j===0?2.3:1.1,j===0?.95:.45);});nodes.forEach((p,i)=>{dot(p,l,2.2,.8);if(i%4===0){ring(p,5+i%3,l,i%3,time*(i%2?1:-1),.6);ring(p,9,l,(i+1)%3,-time*.7,.32);}});for(let i=0;i<3;i++){const p=[Math.cos(time*.17+i*2.1)*100,Math.sin(time*.3+i)*22,Math.sin(time*.23+i*2.1)*100];ring(p,14,l,0,time,.8);ring(p,10,l,2,-time,.7);ring(p,6,l,1,time*2,.8);for(let j=0;j<6;j++){const a=j*TAU/6;line(p,[p[0]+Math.cos(a)*14,p[1]+Math.sin(a)*14,p[2]],l,.5);}}}
 if(l===3){nodes.filter((_,i)=>i%3===0).forEach((p,i)=>{for(let j=0;j<7;j++)dot([p[0]+Math.cos(j*2.4)*3,p[1]+Math.sin(j*2.4)*3,p[2]+j%3],l,2,.85);for(let a=0;a<3;a++)ring(p,12+i%5,l,a,time*(.6+a*.25)+i,.32);if(i<17)line(p,nodes[(i+1)*3],l,.2);});}
 if(l===4){for(let h=0;h<3;h++){let prevA,prevB;for(let j=0;j<44;j++){const x=(j-22)*6,a=j*.4+time*.4+h;const p=[x,Math.cos(a)*14,(h-1)*76+Math.sin(a)*18],q=[x,-Math.cos(a)*14,(h-1)*76-Math.sin(a)*18];dot(p,l,1.9,.9);dot(q,l,1.9,.6);if(j%2===0)line(p,q,l,.35);if(prevA){line(prevA,p,l,.7,1);line(prevB,q,l,.5,1);}prevA=p;prevB=q;}}}
 if(l===5){for(let x=-3;x<=3;x++)for(let z=-3;z<=3;z++){const c=[x*36,0,(z+(x%2)*.5)*42];if(Math.hypot(c[0],c[2])>145)continue;const grow=.55+.45*Math.sin(time*.45+x*.3+z*.4)**2;let prev;for(let j=0;j<=6;j++){const a=j*TAU/6,p=[c[0]+Math.cos(a)*24,-grow*13,c[2]+Math.sin(a)*24];if(prev)line(prev,p,l,.65);line(p,[p[0],12,p[2]],l,.25);dot(p,l,1,.65);prev=p;}}ring([0,-18,0],30,l,1,-time*.5,.8);}
 if(l===6){for(let x=-3;x<=3;x++)for(let z=-3;z<=3;z++){const p=[x*39,0,z*39];if(Math.hypot(p[0],p[2])>145)continue;const corners=[[-6,0,-6],[6,0,-6],[6,0,6],[-6,0,6]].map(v=>v.map((v,k)=>v+p[k]));corners.forEach((c,i)=>line(c,corners[(i+1)%4],l,.7));dot(p,l,2,Math.sin(time*2+x+z)>.5?1:.2);if(x<3){const q=[p[0]+39,0,p[2]];line(p,q,l,.3);dot(mix(p,q,(time*.5+z*.23+10)%1),l,2,.9);}if(z<3)line(p,[p[0],0,p[2]+39],l,.25);}}
}
function frame(now){const dt=Math.min((now-last)/1000||0,.05);last=now;if(playing){time+=dt*speed;if(touring){tourTime+=dt*speed;const chapter=Math.floor(tourTime/12);if(chapter>=7){stopTour();select(-1);}else{if(selected!==chapter)select(chapter);$('progress').style.width=`${tourTime/84*100}%`;}}}
 const ease=1-Math.exp(-dt*5);spread+=((selected<0?1:0)-spread)*ease;focus=focus.map((v,i)=>v+((selected<0||selected===i?1:0)-v)*ease);
 ctx.clearRect(0,0,width,height);stars.forEach(([x,y,r])=>{ctx.globalAlpha=.1+r*.25;ctx.fillStyle='#a9bce2';ctx.fillRect(x*width,y*height,.5+r,.5+r);});
 ctx.globalCompositeOperation='lighter';for(let l=0;l<7;l++)drawLayer(l);ctx.globalCompositeOperation='source-over';ctx.globalAlpha=1;requestAnimationFrame(frame);
}
select(-1);updatePlay();requestAnimationFrame(frame);
