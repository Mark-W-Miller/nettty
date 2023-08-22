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

    Y_CW(normal(0,1,0), Math.PI/2,XY,"+Y",new Vector3d(0,1,0)),
    Y_CCW(normal(0,1,0), -Math.PI/2,XY,"-Y",new Vector3d(0,-1,0)),
    Z_CW(normal(1,0,0),-Math.PI/2,ZY,"+Z",new Vector3d(0,0,1)),
    Z_CCW(normal(-1,0,0),Math.PI/2,ZY,"-Z",new Vector3d(0,0,-1)),
    X_CW(normal(0,0,-1), Math.PI/2,XZ,"+X",new Vector3d(1,0,0)),
    X_CCW(normal(0,0,1),-Math.PI/2,XZ,"-X",new Vector3d(-1,0,0));

    Vector3d axis ;
    Plane primaryPlane ;
    double angle ;
    String shortHand ;
    Vector3d naturalMotion ;

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


    SpinSignature(Vector3d axis, double angle, Plane primaryPlane,String shortHand,Vector3d naturalMotion) {
        this.axis = axis;
        this.angle = angle;
        this.primaryPlane = primaryPlane ;
        this.shortHand = shortHand;
        this.naturalMotion = naturalMotion ;
    }

    public static SpinSignature from(Plane plane, double angle){
        for(SpinSignature spinSig: SpinSignature.values()){
            if(spinSig.getPrimaryPlane() == plane && Math.signum(spinSig.angle) == Math.signum(angle)){
                return spinSig ;
            }
        }
        throw new RuntimeException("Ooops:");
    }
    public SpinSignature getCompSpin(){
        return compSpin.get(this);
    }
}
