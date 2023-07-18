package com.moondance.nettty.model;

import com.moondance.nettty.utils.MapOfLists;
import lombok.Getter;
import lombok.Setter;
import org.jogamp.vecmath.Vector3d;

@Getter
@Setter
public class Particle implements Comparable{
    private static int nextId = 0 ;
    int id ;
    MapOfLists<Integer,Spin> spins = new MapOfLists<Integer, Spin>();
    Vector3d motionVector = new Vector3d(0,0,0);

    public Particle() {
        id = nextId++;
        spins.add(1,new Spin());
    }

    @Override
    public int compareTo(Object o) {
        Particle that = (Particle) o;
        return Integer.compare(this.spins.size(), that.spins.size());
    }
}
