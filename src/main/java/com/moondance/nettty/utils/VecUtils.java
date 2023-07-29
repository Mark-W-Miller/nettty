package com.moondance.nettty.utils;

import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.TransformGroup;
import org.jogamp.vecmath.AxisAngle4d;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Tuple3d;
import org.jogamp.vecmath.Vector3d;

import static com.moondance.nettty.utils.Handy.err;

public class VecUtils {
    public static final Point3d ORIGIN = new Point3d();
    public static final Vector3d X_AXIS = new Vector3d(1,0,0);
    public static final Vector3d Y_AXIS = new Vector3d(0,1,0);
    public static final Vector3d Z_AXIS = new Vector3d(0,0,1);

    public static Point3d parsePoint3d(String tripleStr){
        double[] dThree = parseThreeD(tripleStr);
        return new Point3d(dThree[0],dThree[1],dThree[2]);
    }
    public static Vector3d parseVector3d(String tripleStr){
        double[] dThree = parseThreeD(tripleStr);
        Vector3d vec3d = new Vector3d(dThree[0],dThree[1],dThree[2]);
        return vec3d ;
    }

    public static Vector3d normal(double x, double y, double z){
        Vector3d v3d = new Vector3d(x,y,z);
        v3d.normalize();
        return v3d ;
    }
    static double[] parseThreeD(String tripleStr){
        String[] bits = tripleStr.split(" ");
        if(bits.length != 3) {
            bits = tripleStr.split(",");
            if (bits.length != 3) {
                err("Bad triple string:" + tripleStr);
                double[] dThree = {0d,0d,0d};
                return dThree;
            }
        }
        double x = Double.parseDouble(bits[0]);
        double y = Double.parseDouble(bits[1]);
        double z = Double.parseDouble(bits[2]);
        double[] dThree = {x,y,z};
        return dThree;
    }

    public static Tuple3d randomize(Tuple3d tuple, double offset){
        tuple.x += offset - Math.random() ;
        tuple.y += offset - Math.random() ;
        tuple.z += offset - Math.random() ;
        return tuple ;
    }
    public static TransformGroup makeTranslationGroup(Vector3d vec){
        Transform3D t3d = new Transform3D();
        t3d.setTranslation(vec);
        return new TransformGroup(t3d);
    }
    public static TransformGroup makeRotationGroup(Vector3d vec, double angle){
        AxisAngle4d aa = new AxisAngle4d(vec,angle);
        Transform3D t3d = new Transform3D();
        t3d.setRotation(aa);
        return new TransformGroup(t3d);
    }
}
