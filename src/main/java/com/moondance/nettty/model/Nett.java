package com.moondance.nettty.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Nett {

    List<Particle> particles = new ArrayList<>();

    public Nett() {
    }

    public void initNett(){
        particles.add(new Particle());
    }
}
