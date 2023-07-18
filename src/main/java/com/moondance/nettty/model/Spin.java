package com.moondance.nettty.model;

import lombok.Getter;
import lombok.Setter;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Vector3d;

@Getter
@Setter
public class Spin implements Comparable {
    private static int nextId = 0 ;
    int id ;
    int level = 1 ;
    Point3d position = new Point3d();
    Vector3d rotationAxis = new Vector3d(0d,1d,0d);

    public Spin() {
        id = nextId++ ;
    }

    @Override
    public int compareTo(Object o) {
        Spin that = (Spin) o;
        return Integer.compare(this.level, that.level);
    }
}
