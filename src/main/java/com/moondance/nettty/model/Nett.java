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
}
