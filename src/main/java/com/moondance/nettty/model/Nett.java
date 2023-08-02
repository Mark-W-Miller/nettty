package com.moondance.nettty.model;

import com.moondance.nettty.graphics.NettGroup;
import com.moondance.nettty.model.rulesof3.ParticleOnlyRuleSentinel;
import com.moondance.nettty.model.rulesof3.RuleOfThree;
import com.moondance.nettty.model.rulesof3.WriteStateRule;
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

import static com.moondance.nettty.utils.DB.GOD_PULSE_DB;
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
    public Nett() {
        Nettty = this;
    }

    public Nett(Octree octree) {
        Nettty = this;
        this.octree = octree ;
        this.particles = octree.getAllData();
        initRules();
    }

    public void GodPulse(int number) {
        for (int ix = 0; ix < number; ix++) {
            particles.stream().forEach(particle -> particle.GodPulse(1));
        }
        updateTransforms();
    }

    public void updateTransforms() {
        for (Particle particle : particles) {
            particle.updateTransforms();
        }
    }

    public Map<String, Particle> makeParticleLookup() {
        return particles.stream().collect(Collectors.toMap(p -> p.getId(), p -> p));
    }

    public void applyReference(Map<String, Particle> reference) {
        particles.stream().forEach(particle -> {
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
        String name = string.substring(0,string.indexOf("("));
        return name;
    }
    private Point3d refPos(String string) {
        String posStr = string.substring(string.indexOf("(")+1,string.indexOf(")"));
        return parsePoint3d(posStr);
    }

    private void initRules(){
        ruleOfThrees.add(new ParticleOnlyRuleSentinel());
//        ruleOfThrees.add(new WriteStateRule());
    }
    public void processThreeBox(Particle particle) {
        ThreeBox<Particle> threeBox = octree.findThreeBox(new OctAddress(particle.position));
        for(RuleOfThree rule: ruleOfThrees){
            rule.apply(this,threeBox);
        }
        out(GOD_PULSE_DB,"Nett processThreeBox Before Kill particles:\n" + formatList(particles));
        particles.stream().filter(part->!part.isKill()).forEach(part->{

        });
        List<Particle> dead = particles.stream().filter(part->part.isKill()).collect(Collectors.toList());
        dead.stream().forEach(part->{
            nettGroup.removeParticleModels(part);
        });
        particles = particles.stream().filter(part->!part.isKill()).collect(Collectors.toList());
        out(GOD_PULSE_DB,"Nett processThreeBox After Kill particles:\n" + formatList(particles));
    }
}
