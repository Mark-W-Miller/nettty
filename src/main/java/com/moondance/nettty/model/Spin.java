package com.moondance.nettty.model;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import org.jogamp.java3d.Alpha;
import org.jogamp.java3d.RotationInterpolator;
import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.TransformGroup;
import org.jogamp.vecmath.AxisAngle4d;
import org.jogamp.vecmath.Matrix3d;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Vector3d;

import static com.moondance.nettty.model.SpinSignature.*;
import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.VecUtils.*;

@Getter
@Setter
@ToString
public class Spin implements Comparable, Cloneable {
    private static int nextId = 0 ;
    @ToString.Exclude Particle particle ;
    String id ;
    int shell = 1 ;
    int spinSpeed = 15000 ;
    boolean spinNudge = true ;
    SpinSignature spinSignature = Y_CW;
    Vector3d rotationDeltaVector = null;
    Vector3d rotationAxis = new Vector3d(0d,1d,0d);
    double rotationAngle = Math.PI/2;

    TransformGroup currentSpinTransform ;
    Alpha rotationAlpha ;
    RotationInterpolator rotator ;
    TransformGroup fixedXForm;
    public Spin(int shell,Vector3d rotationAxis) {
        id = "S-" + nextId++;
        this.shell = shell ;
        this.rotationAxis = rotationAxis ;
    }
    public Spin() {
        id = "S-" + nextId++;
    }
    @SneakyThrows
    public Spin clone() {
        Spin clone =  (Spin) super.clone();
        clone.rotationAxis = (Vector3d) rotationAxis.clone();
        clone.rotationDeltaVector =  rotationDeltaVector!= null ? (Vector3d) rotationDeltaVector.clone() : null;
        clone.id = "S-" + nextId++;
        return clone ;
    }

    @Override
    public int compareTo(Object o) {
        Spin that = (Spin) o;
        return Integer.compare(this.shell, that.shell);
    }

    public void setRotationDeltaVectorStr(String string){
        rotationDeltaVector = parseVector3d(string);
    }
    public void setRotationAxisStr(String string){
        rotationAxis = parseVector3d(string);
        rotationAxis.normalize();
    }
    public void setRotationAngleStr(String string){
        rotationAngle = parseAngleStr(string);
    }
    public void setSpinSignatureStr(String string){
        spinSignature = SpinSignature.valueOf(string.trim());
        rotationAxis = spinSignature.getAxis() ;
        rotationAngle = spinSignature.getAngle() ;
    }

    private double parseAngleStr(String string) {
        boolean radians = !string.toLowerCase().startsWith("d");
        if(radians){
            return Double.parseDouble(string);
        } else {
            return Math.toRadians(Double.parseDouble(string.substring(1))) ;
        }
    }

    public void updateTransform() {
//        out("Spin updateTransforms");
        Transform3D yAxisNeg = new Transform3D();
        AxisAngle4d aa = new AxisAngle4d(rotationAxis,-rotationAngle);
        yAxisNeg.setRotation(aa);
        Transform3D yAxis = new Transform3D();
        aa = new AxisAngle4d(rotationAxis,rotationAngle);
        yAxis.setRotation(aa);
//        out("Spin yAxisNeg:\n" + yAxisNeg);
//        out("Spin yAxis:\n" + yAxis);

        rotationAlpha.setIncreasingAlphaDuration(spinSpeed);
        rotator.setAlpha(rotationAlpha);
        rotator.setTransformAxis(yAxis);
        fixedXForm.setTransform(yAxis);
    }

    public void GodPulse() {
        double speedFactor = 1/(shell *0.2);
        double delta = speedFactor * (100 - 400 * Math.random()) ;
//        out("Spin GodPulse delta:" + delta);
        spinSpeed += delta;
        spinSpeed = Math.max(spinSpeed,100);
        spinSpeed = Math.min(spinSpeed,60000);
        if(rotationDeltaVector != null && !rotationDeltaVector.equals(ORIGIN)){
            Transform3D deltaV = new Transform3D();
            Transform3D rot = new Transform3D();

            deltaV.rotX(rotationDeltaVector.getX());
            rot.rotY(rotationDeltaVector.getY());
            deltaV.mul(rot);
            rot.rotZ(rotationDeltaVector.getZ());
            deltaV.mul(rot);
//            out("Spin GodPulse deltaV:\n" + deltaV);
            deltaV.transform(rotationAxis);
//            out("Spin GodPulse after nudge rotationAxis:" + rotationAxis);
        } else {
            if(rotationDeltaVector == null) {
                rotationAxis.x += Math.random() * 0.5;
                rotationAxis.y += Math.random() * 0.5;
                rotationAxis.z += Math.random() * 0.5;
//                out(id + ":Spin GodPulse random nudge rotationAxis:" + rotationAxis);
            } else {
//                out(id + ":Spin GodPulse NO nudge rotationAxis:" + rotationAxis);
            }
        }
        rotationAxis.normalize();
//        out("Spin GodPulse spinSpeed:" + spinSpeed);
//        out("Spin GodPulse rotationAxis:" + rotationAxis);
    }
}
