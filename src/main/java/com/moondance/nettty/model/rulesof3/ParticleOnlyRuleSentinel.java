package com.moondance.nettty.model.rulesof3;

import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import com.moondance.nettty.model.Plane;
import com.moondance.nettty.model.SpinSignature;
import com.moondance.nettty.utils.octree.SubNode;
import com.moondance.nettty.utils.octree.ThreeBox;
import org.jogamp.vecmath.Vector3d;

import java.util.List;

import static com.moondance.nettty.utils.DB.RULE_DB;
import static com.moondance.nettty.utils.Handy.out;

public class ParticleOnlyRuleSentinel extends Rule3 implements RuleOfThree {
    @Override
    public void apply(Nett nett, ThreeBox<Particle> threeBox) {
        List<Particle> data = threeBox.getDataCenter();
        if(data.size() > 1){
            super.apply(nett,threeBox);
            out(RULE_DB,"Particle Pileup:" + threeBox.getAddressOfCenter());
            out(RULE_DB,"         Data:"+ threeBox.getDataCenter());
            Particle p0 = data.get(0);
            for(int ix = 1 ; ix < data.size() ; ix++){
                Particle pN = data.get(ix);
                collide(p0,pN);
            }
        }
    }

    private void collide(Particle p0, Particle pN) {
        if(p0.getFirstSpinSignature() == pN.getFirstSpinSignature()){
            //same combine them into super spin
            out(RULE_DB,"collide SAME ALL combine:" + p0.getFirstSpinSignature() );
            p0.getFirstSpin().incShell(pN.getFirstSpin().getShell());
            pN.setKill(true);
        } else {
            //opposite Bounce
            if(p0.getFirstSpinSignature().getPrimaryPlane() == pN.getFirstSpinSignature().getPrimaryPlane()){
                out(RULE_DB,"collide SAME PLANE:" + p0.getFirstSpinSignature().getPrimaryPlane() );
                int energy = p0.getFirstSpin().getShell() + pN.getFirstSpin().getShell() ;
                p0.bounce(energy/2);
                pN.bounce(energy/2);
            } else {
                //opposite Opposite Bounce each with
                out(RULE_DB,"collide DIFF combine:" + p0.getFirstSpinSignature() );
                int energy = p0.getFirstSpin().getShell() + pN.getFirstSpin().getShell() ;
                Plane plane0 = p0.getFirstSpinSignature().getPrimaryPlane();
                Plane planeN = pN.getFirstSpinSignature().getPrimaryPlane();
                Plane planeX = plane0.crossPlane(planeN);
                p0.getFirstSpin().setSpinSignature(SpinSignature.from(planeX,p0.getFirstSpinSignature().getAngle() ));
                p0.bounce(energy);
                p0.getFirstSpin().setShell(energy/2);
                pN.setKill(true);
            }
        }
    }
}
