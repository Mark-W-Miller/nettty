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

    public static Point3d parsePoint3d(String tripleStr){
        double[] dThree = parseThreeD(tripleStr);
        return new Point3d(dThree[0],dThree[1],dThree[2]);
    }
    public static Vector3d parseVector3d(String tripleStr){
        double[] dThree = parseThreeD(tripleStr);
        Vector3d vec3d = new Vector3d(dThree[0],dThree[1],dThree[2]);
        vec3d.normalize();
        return vec3d ;
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

    public static TransformGroup makeTranslationGroup(Vector3d vec){
        Transform3D t3d = new Transform3D();
        t3d.setTranslation(vec);
        return new TransformGroup(t3d);
    }
    public static TransformGroup makeRotationGroup(Vector3d vec){
        AxisAngle4d aa = new AxisAngle4d(vec,0);
        Transform3D t3d = new Transform3D();
        t3d.setRotation(aa);
        return new TransformGroup(t3d);
    }
}
