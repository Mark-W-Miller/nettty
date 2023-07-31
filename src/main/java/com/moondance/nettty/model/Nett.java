package com.moondance.nettty.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jogamp.vecmath.Point3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.moondance.nettty.utils.VecUtils.parsePoint3d;
import static com.moondance.nettty.utils.octtree.OctTree.dumpTree;

@Getter
@Setter
@ToString
public class Nett {

    public static Nett Nettty = null;
    List<Particle> particles = new ArrayList<>();

    public Nett() {
        Nettty = this;
    }

    public Nett(List<Particle> particles) {
        Nettty = this;
        this.particles = particles;
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

}
