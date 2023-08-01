package com.moondance.nettty.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jogamp.vecmath.Vector3d;

import java.util.HashMap;
import java.util.Map;

import static com.moondance.nettty.model.Axis.*;
import static com.moondance.nettty.model.Plane.*;
import static com.moondance.nettty.utils.VecUtils.normal;

@Getter
public enum SpinSignature {

    Y_CW(normal(0,1,0), Math.PI/2,XY),
    Y_CCW(normal(0,1,0), -Math.PI/2,XY),
    Z_CW(normal(1,0,0),-Math.PI/2,ZY),
    Z_CCW(normal(-1,0,0),Math.PI/2,ZY),
    X_CW(normal(0,0,-1), Math.PI/2,XZ),
    X_CCW(normal(0,0,1),-Math.PI/2,XZ);

    Vector3d axis ;
    Plane primaryPlane ;
    double angle ;
    private static Map<String,SpinSignature> mapFromName = new HashMap<>();
    private static Map<SpinSignature,SpinSignature> compSpin = new HashMap<>();

    static {
        compSpin.put(X_CCW,X_CW);
        compSpin.put(X_CW,X_CCW);

        compSpin.put(Y_CCW,Y_CW);
        compSpin.put(Y_CW,Y_CCW);

        compSpin.put(Z_CW,Z_CCW);
        compSpin.put(Z_CCW,Z_CW);
    }


    SpinSignature(Vector3d axis, double angle, Plane primaryPlane) {
        this.axis = axis;
        this.angle = angle;
        this.primaryPlane = primaryPlane ;
    }

    public SpinSignature getCompSpin(){
        return compSpin.get(this);
    }
}
