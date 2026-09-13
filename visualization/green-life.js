/* Green-life vignettes: a continuous plasmodial body and an illustrative
   four-neighbor ground swarm. These are film behaviors, not biological laws. */
const moldNodes = Array.from({length:100},(_,i)=>{
  const a=i*2.399963, r=Math.sqrt(i/99)*126;
  return [Math.cos(a)*r,Math.sin(a)*r*.59,Math.sin(i*1.7)*8];
});
function drawSlimeMold() {
  const visible=ramp(time,146,148)*(1-ramp(time,156,159));
  if(visible<.001)return;
  const growth=ramp(time,146,153), count=Math.max(1,Math.floor(100*growth));
  const bodies=moldNodes.slice(0,count).map((p,i)=>add(p,[Math.sin(time*1.8+i)*1.4,Math.cos(time*1.3+i)*1.1,0]));
  // Overlapping translucent lobes and thick veins form ONE shared body.
  bodies.forEach((p,i)=>{
    let parent=0, nearest=Infinity;
    for(let j=0;j<i;j++){const d=Math.hypot(...p.map((x,k)=>x-bodies[j][k]));if(d<nearest){nearest=d;parent=j;}}
    if(i) {
      const vein=Array.from({length:9},(_,j)=>{const f=j/8,q=lerp(bodies[parent],p,f);q[1]+=Math.sin(f*Math.PI)*3*Math.sin(i);return q;});
      stroke(vein,'#8c6e19',visible*.55,12);
      stroke(vein,'#dfbc3b',visible*.8,5);
      point(lerp(bodies[parent],p,(time*.35+i*.21)%1),'#fff0a0',1.8,visible*.8);
    }
    point(p,'#d7b236',8+i%4,visible*.07,true);
    // Distributed nuclear pockets, each with a small chromatin squiggle.
    if(i%4===0){
      orbit(p,3.7,2,0,'#f7e292',visible*.8);
      stroke([add(p,[-2,-1,0]),add(p,[0,1.4,0]),add(p,[2,-1,0])],'#8de8b8',visible,1.1);
      point(add(p,[Math.sin(time*2+i)*4,Math.cos(time*2+i)*3,0]),'#71b9ff',1.1,visible*.8);
    }
  });
}
let groundFrame=-1, crawlers=[];
function stepGroundSwarm() {
  const before=crawlers.map(b=>({p:[...b.p],v:[...b.v]}));
  crawlers.forEach((b,i)=>{
    const neighbors=before.map((n,j)=>({n,j,d:Math.hypot(n.p[0]-b.p[0],n.p[2]-b.p[2])})).filter(x=>x.j!==i).sort((a,b)=>a.d-b.d).slice(0,4);
    const force=[0,0,0];
    for(const {n,d} of neighbors)for(const k of [0,2])force[k]+=(n.p[k]-b.p[k])*.0003+(n.v[k]-b.v[k])*.012-(n.p[k]-b.p[k])*3/Math.max(9,d*d);
    const radius=Math.hypot(b.p[0],b.p[2]);
    for(const k of [0,2]) {
      force[k]-=b.p[k]*(.00035+Math.max(0,radius-95)*.0002);
      force[k]+=.065*Math.sin(groundFrame*.09+i*2.4+k);
      b.v[k]+=force[k];
    }
    b.v=scale(b.v,(.7+.35*Math.sin(i+groundFrame*.035)**2)/Math.max(.001,Math.hypot(...b.v)));
    b.p=add(b.p,b.v);b.p[1]=18;
  });groundFrame++;
}
function drawGroundSwarm() {
  const visible=ramp(time,157,160)*(1-ramp(time,168,171));
  if(visible<.001)return;
  const target=Math.floor(Math.max(0,Math.min(time-157,14))*30);
  if(groundFrame<0||target<groundFrame){
    groundFrame=0;crawlers=Array.from({length:36},(_,i)=>({p:[Math.sin(i*2.4)*100,18,Math.cos(i*1.9)*85],v:[Math.sin(i),0,Math.cos(i)]}));
  }
  while(groundFrame<target)stepGroundSwarm();
  // Uneven terrain contours, not a circuit-board grid.
  for(let ring=0;ring<5;ring++) {
    const path=[];for(let j=0;j<=70;j++){const a=j/70*TAU,r=35+ring*24+5*Math.sin(a*5+ring);path.push([Math.cos(a)*r,23,Math.sin(a)*r]);}
    stroke(path,'#698c62',visible*.18,.8);
  }
  crawlers.forEach((b,i)=>{
    const direction=scale(b.v,1/Math.max(.001,Math.hypot(...b.v))), side=[-direction[2],0,direction[0]];
    const at=(x,z,y=0)=>add(b.p,add(scale(side,x),add(scale(direction,z),[0,y,0])));
    point(at(0,-2),'#73bd83',3.5,visible);
    point(at(0,2),'#b2d792',2,visible);
    for(let leg=0;leg<4;leg++)for(const s of [-1,1]){
      const step=Math.sin(time*13+i+leg*Math.PI+s)*1.6;
      stroke([at(s*1.2,leg*1.6-2.5),at(s*5,leg*2-3,-2-Math.max(0,step)),at(s*8,leg*3-4+step,3)],'#b3ca91',visible*.75,.9);
    }
  });
  const focal=crawlers[0];
  crawlers.slice(1).sort((a,b)=>Math.hypot(...a.p.map((x,k)=>x-focal.p[k]))-Math.hypot(...b.p.map((x,k)=>x-focal.p[k]))).slice(0,4).forEach(b=>stroke([focal.p,b.p],'#79bfff',visible*.55,1));
  point(focal.p,'#b5e6ff',2,visible,true);
}
