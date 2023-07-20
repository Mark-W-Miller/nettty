package com.moondance.nettty.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jogamp.java3d.Alpha;
import org.jogamp.java3d.RotationInterpolator;
import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.TransformGroup;
import org.jogamp.vecmath.AxisAngle4d;
import org.jogamp.vecmath.Vector3d;

import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.VecUtils.parsePoint3d;
import static com.moondance.nettty.utils.VecUtils.parseVector3d;

@Getter
@Setter
@ToString
public class Spin implements Comparable {
    private static int nextId = 0 ;
    int id ;
    int shell = 1 ;
    int spinSpeed = 15000 ;
    Vector3d rotationAxis = new Vector3d(0d,1d,0d);
    TransformGroup currentSpinTransform ;
    Alpha rotationAlpha ;
    RotationInterpolator rotator ;

    public Spin(int shell,Vector3d rotationAxis) {
        id = nextId++ ;
        this.shell = shell ;
        this.rotationAxis = rotationAxis ;
    }
    public Spin() {
        id = nextId++ ;
    }

    @Override
    public int compareTo(Object o) {
        Spin that = (Spin) o;
        return Integer.compare(this.shell, that.shell);
    }

    public void setRotationAxisStr(String string){
        rotationAxis = parseVector3d(string);
    }

    public void updateTransform() {
//        out("Spin updateTransforms");
        rotationAlpha.setIncreasingAlphaDuration(spinSpeed);
        rotator.setAlpha(rotationAlpha);
        Transform3D yAxis = new Transform3D();
        AxisAngle4d aa = new AxisAngle4d(rotationAxis,Math.PI);
        yAxis.setRotation(aa);
        rotator.setTransformAxis(yAxis);
    }

    public void GodPulse() {
        double delta = 30 - 700 * Math.random() ;
        out("Spin GodPulse delta:" + delta);
        spinSpeed += delta;
        spinSpeed = Math.max(spinSpeed,100);
        spinSpeed = Math.min(spinSpeed,60000);
        rotationAxis.x += Math.random() *0.5;
        rotationAxis.y += Math.random() *0.5;
        rotationAxis.z += Math.random() *0.5;
        rotationAxis.normalize();
        out("Spin GodPulse spinSpeed:" + spinSpeed);
//        out("Spin GodPulse rotationAxis:" + rotationAxis);
    }
}
