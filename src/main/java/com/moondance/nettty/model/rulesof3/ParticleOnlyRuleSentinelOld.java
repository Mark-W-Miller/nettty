package com.moondance.nettty.model.rulesof3;

import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import com.moondance.nettty.model.Plane;
import com.moondance.nettty.model.SpinSignature;
import com.moondance.nettty.utils.octree.ThreeBox;

import java.util.List;

import static com.moondance.nettty.utils.DB.DB_RULE;
import static com.moondance.nettty.utils.Handy.*;

public class ParticleOnlyRuleSentinelOld extends Rule3 implements RuleOfThree {
    @Override
    public void apply(Nett nett, ThreeBox<Particle> threeBox) {
        List<Particle> data = threeBox.getDataCenter();
        if(data.size() > 1){
            super.apply(nett,threeBox);
            out(DB_RULE,"Particle Pileup:" + threeBox.getAddressOfCenter());
            out(DB_RULE,"         Data:\n"+ formatList(threeBox.getDataCenter()));
            Particle p0 = data.get(0);
            for(int ix = 1 ; ix < data.size() ; ix++){
                Particle pN = data.get(ix);
                collide(p0,pN);
            }
        }
    }

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
