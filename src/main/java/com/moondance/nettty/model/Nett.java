package com.moondance.nettty.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString
public class Nett {

    public static Nett Nettty = null;
    List<Particle> particles = new ArrayList<>();

    public Nett() {
        Nettty = this ;
    }
    public Nett(List<Particle> particles) {
        Nettty = this ;
        this.particles = particles ;
    }

    public void GodPulse(int number) {
        for(int ix=0;ix<number;ix++){
            particles.stream().forEach(particle->particle.GodPulse(1));
        }
    }

    public void updateTransforms() {
        for (Particle particle : particles) {
            particle.updateTransforms();
        }
    }

}
