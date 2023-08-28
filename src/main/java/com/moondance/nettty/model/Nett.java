package com.moondance.nettty.model;

import com.moondance.nettty.graphics.NettGroup;
import com.moondance.nettty.model.rulesof3.ParticleOnlyRuleSentinel;
import com.moondance.nettty.model.rulesof3.RuleOfThree;
import com.moondance.nettty.utils.octree.OctAddress;
import com.moondance.nettty.utils.octree.Octree;
import com.moondance.nettty.utils.octree.ThreeBox;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jogamp.vecmath.Point3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.moondance.nettty.utils.DB.DB_GOD_PULSE;
import static com.moondance.nettty.utils.DB.DB_GOD_PULSE_TRACE;
import static com.moondance.nettty.utils.Handy.formatList;
import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.VecUtils.parsePoint3d;

@Getter
@Setter
@ToString
public class Nett {

    List<RuleOfThree> ruleOfThrees = new ArrayList<>();
    public static Nett Nettty = null;
    List<Particle> particles = new ArrayList<>();
    Octree<Particle> octree ;
    transient NettGroup nettGroup ;
    @SuppressWarnings("unused")
    public Nett() {
        Nettty = this;
    }

    public Nett(Octree<Particle> octree) {
        Nettty = this;
        this.octree = octree ;
        this.particles = octree.getAllData();
        initRules();
    }

    public void GodPulse(int number) {
        for (int ix = 0; ix < number; ix++) {
            particles.forEach(p->p.setIn3Box(false));
            List<ThreeBox<Particle>> threeBoxs = particles.stream().
                    filter(p->!p.isIn3Box())
                    .map(p->octree.findThreeBox(new OctAddress(p.position)))
                    .collect(Collectors.toList());
            out(DB_GOD_PULSE_TRACE,"ThreeBoxes" + formatList(threeBoxs));
            octree.lookupDB(particles);
            threeBoxs.forEach(this::processThreeBox);
            particles.forEach(particle -> particle.GodPulse(1));
        }
        updateTransforms();
    }

    public void updateTransforms() {
        for (Particle particle : particles) {
            particle.updateTransforms();
        }
    }

    public Map<String, Particle> makeParticleLookup() {
        return particles.stream().collect(Collectors.toMap(Particle::getId, p -> p));
    }

    public void applyReference(Map<String, Particle> reference) {
        particles.forEach(particle -> {
            String refName = particle.getId() ;
            if (particle.getIdRef() != null) {
                refName = refName(particle.getIdRef());
                particle.setPosition(refPos(particle.getIdRef()));
            }
            Particle referenceParticle = reference.get(refName);
            if (referenceParticle != null) {
                particle.populateFromReference(referenceParticle);
            }

        });
    }
    private String refName(String string) {
        return string.substring(0,string.indexOf("("));
    }
    private Point3d refPos(String string) {
        String posStr = string.substring(string.indexOf("(")+1,string.indexOf(")"));
        return parsePoint3d(posStr);
    }

    private void initRules(){
//        ruleOfThrees.add(new WriteStateRule());
        ruleOfThrees.add(new ParticleOnlyRuleSentinel());
    }
    public void processThreeBox(ThreeBox<Particle> threeBox) {
        out(DB_GOD_PULSE,"!!!!!!!!!!!!!!!!!Nett processThreeBox threeBox:" + threeBox);
        for(RuleOfThree rule: ruleOfThrees){
            rule.apply(this,threeBox);
        }
        out(DB_GOD_PULSE_TRACE,"Nett processThreeBox Before Kill particles:\n" + formatList(particles));
        List<Particle> dead = particles.stream().filter(Particle::isKill).collect(Collectors.toList());
        dead.forEach(part-> nettGroup.removeParticleModels(part));
        particles = particles.stream().filter(part->!part.isKill()).collect(Collectors.toList());
        out(DB_GOD_PULSE_TRACE,"Nett processThreeBox After Kill particles:\n" + formatList(particles));
    }
}
