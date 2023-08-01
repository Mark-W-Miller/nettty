package com.moondance.nettty.model;

import org.jogamp.vecmath.Vector3d;
import org.jogamp.vecmath.Vector3f;

public enum Axis {
    X(new Vector3d(1d,0d,0d)),
    Y(new Vector3d(0d,1d,0d)),
    Z(new Vector3d(0d,0d,1d));
    Vector3d axisVector ;
    Axis(Vector3d axisVector){
        this.axisVector = axisVector ;
    }
}
