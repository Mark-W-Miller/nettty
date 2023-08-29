package com.moondance.nettty.model.rulesof3;

import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import com.moondance.nettty.model.Plane;
import com.moondance.nettty.model.SpinSignature;
import com.moondance.nettty.utils.MapOfLists;
import com.moondance.nettty.utils.octree.ThreeBox;
import org.jogamp.vecmath.Vector3d;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.moondance.nettty.utils.DB.DB_RULE;
import static com.moondance.nettty.utils.DB.DB_RULE_TRACE;
import static com.moondance.nettty.utils.Handy.*;

public class ParticleOnlyRuleSentinel extends Rule3 implements RuleOfThree {
    @Override
    public void apply(Nett nett, ThreeBox<Particle> threeBox) {
        List<Particle> data = threeBox.getDataCenter();
        if(data.size() > 1){
            super.apply(nett,threeBox);
            out(DB_RULE,"Particle Pileup:" + threeBox.getAddressOfCenter());
            out(DB_RULE,"         Data:\n"+ formatList(threeBox.getDataCenter()));
            MapOfLists<Plane,Particle> planeMap = makeInfluenceMap(data);
            out(DB_RULE_TRACE,"Plane Map:" + planeMap);
            collideThePlanes(planeMap);
        }
    }

    /**
     * Oh, what a method name! Right up (down) there with GodPulse.
     * Who doesn't want to write that?!
     */
    private void collideThePlanes(MapOfLists<Plane,Particle> planeMap){
        planeMap.entrySet().forEach(e-> compressSympathetic(e.getValue()));
        planeMap.entrySet().forEach(e-> repelAntagonistic(e.getKey(),e.getValue()));
    }

    private void repelAntagonistic(Plane plane, List<Particle> particles) {
        out(DB_RULE,"ParticleOnlyRuleSentinel repelAntagonistic plane:" + plane);
        out(DB_RULE,"ParticleOnlyRuleSentinel repelAntagonistic particles:" + formatList(particles));
        MapOfLists<SpinSignature,Particle> particleMap = new MapOfLists<>();
        particles.forEach(part->particleMap.putOne(part.getFirstSpinSignature(),part));
        List<Integer> sizes = particleMap.sizeMap();
        if(particleMap.size() == 2){

        }
    }

    private void compressSympathetic(List<Particle> value) {
        MapOfLists<SpinSignature,Particle> spins = new MapOfLists<>();
        value.forEach(part->spins.putOne(part.getFirstSpinSignature(),part));
        out(DB_RULE_TRACE,"collideBagOfCats Spins Map:" + spins);
        spins.values().forEach(list->{
            if(list.size() > 1) {
                List<Particle> living = doCompression(list);
            }
        });
    }


    /**
     * Two Same become 1
     *      Jump perpendicular to the plane
     *      Create a new counter spin particle in new location
     */

    private List<Particle> doCompression(List<Particle> sympatheticParticles) {
        List<Particle> living = new ArrayList<>();
        int numNew = sympatheticParticles.size();
        boolean odd = sympatheticParticles.size() % 2 == 1 ;
        if(odd)
            numNew--;
        for(int ix = 0; ix < numNew; ix+=2){
            Particle a = sympatheticParticles.get(ix);
            Particle b = sympatheticParticles.get(ix+1);
            b.setKill(true);
            out(DB_RULE_TRACE,"compressSympathetic kill:" + b);
            int shell = a.getFirstSpin().combineShells(b.getFirstSpin().getShell());
            Vector3d crossVec = (Vector3d) a.getFirstSpinSignature().getNaturalMotion().clone();
            crossVec.scale(shell);
            a.setMotionVector(crossVec);
            out(DB_RULE_TRACE,"compressSympathetic merge to:" + a);
        }
        return sympatheticParticles.stream().filter(part-> !part.isKill()).collect(Collectors.toList());
    }

    private MapOfLists<Plane,Particle> makeInfluenceMap(List<Particle> particles){
        MapOfLists<Plane,Particle> map = new MapOfLists<>();
        particles.forEach(part-> map.putOne(part.getFirstSpinSignature().getPrimaryPlane(),part));
        return map ;
    }
    @SuppressWarnings("unused")
    private void collide(Particle p0, Particle pN) {
        out(DB_RULE,ANSI_GREEN  + "!!!!!!!!!!!!! collide p0:" + p0);
        out(DB_RULE,ANSI_GREEN  + "!!!!!!!!!!!!!    with pN:" + pN);
        if(p0.getFirstSpinSignature() == pN.getFirstSpinSignature()){
            //same combine them into super spin
            p0.getFirstSpin().incShell(pN.getFirstSpin().getShell());
            pN.setKill(true);
            out(DB_RULE,ANSI_GREEN  + "collide SAME ALL combine IncShell:" + p0.getFirstSpin().getShell() );
            out(DB_RULE,ANSI_GREEN  + "collide SAME ALL combine Kill:" + pN.getId() );
        } else {
            //opposite Bounce
            if(p0.getFirstSpinSignature().getPrimaryPlane() == pN.getFirstSpinSignature().getPrimaryPlane()){
                out(DB_RULE,ANSI_GREEN  + "collide SAME PLANE:" + p0.getFirstSpinSignature().getPrimaryPlane() );
                int energy = p0.getFirstSpin().getShell() + pN.getFirstSpin().getShell() ;
                p0.bounce(energy/2);
                pN.bounce(energy/2);
                out(DB_RULE,ANSI_GREEN  + "collide SAME PLANE Bounce p0:" + p0.getId() + " by:"  + p0.getMotionVector());
                out(DB_RULE,ANSI_GREEN  + "collide SAME PLANE Bounce pN:" + pN.getId() + " by:"  + pN.getMotionVector());
            } else {
                //opposite Opposite Bounce each with
                out(DB_RULE,ANSI_GREEN  + "collide DIFF combine:" + p0.getFirstSpinSignature() );
                int energy = p0.getFirstSpin().getShell() + pN.getFirstSpin().getShell() ;
                Plane plane0 = p0.getFirstSpinSignature().getPrimaryPlane();
                Plane planeN = pN.getFirstSpinSignature().getPrimaryPlane();
                Plane planeX = plane0.crossPlane(planeN);
                p0.getFirstSpin().setSpinSignature(SpinSignature.from(planeX,p0.getFirstSpinSignature().getAngle() ));
                p0.bounce(energy);
                p0.getFirstSpin().setShell(energy/2);
                pN.setKill(true);
                out(DB_RULE,ANSI_GREEN  + "collide DIFF PLANE Kill:" + pN.getId() );
                out(DB_RULE,ANSI_GREEN  + "collide DIFF PLANE Bounce:" + p0.getId() + " by:"  + p0.getMotionVector());
                out(DB_RULE,ANSI_GREEN  + "collide DIFF PLANE Change spinSig:" + p0.getId() + " new:"  + p0.getFirstSpinSignature());
            }
        }
    }
}
