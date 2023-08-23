package com.moondance.nettty.model;

import com.moondance.nettty.graphics.Images;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.Sphere;
import org.jogamp.vecmath.AxisAngle4d;
import org.jogamp.vecmath.Vector3d;

import static com.moondance.nettty.graphics.Appearences.makeSpinningTexture;
import static com.moondance.nettty.graphics.ParticleGroup.makeSpinSphere;
import static com.moondance.nettty.model.SpinSignature.Y_CW;
import static com.moondance.nettty.utils.DB.*;
import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.VecUtils.*;

@Getter
@Setter
@ToString
public class Spin implements Comparable<Spin>, Cloneable {
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
    BranchGroup spinSphereHolderGroup;
    BranchGroup spinSphereGroup;
    @SuppressWarnings("unused")
    public Spin(int shell,Vector3d rotationAxis) {
        id = "S-" + nextId++;
        this.shell = shell ;
        this.rotationAxis = rotationAxis ;
    }
    public Spin() {
        id = "S-" + nextId++;
    }

    public Spin(SpinSignature spinSignature) {
        this();
        rotationAxis = spinSignature.getAxis();
        rotationAngle = spinSignature.getAngle() ;
    }

    public String shortHand(){
        return spinSignature.getShortHand() + shell ;
    }
    @SneakyThrows
    public Spin clone() {
        Spin clone =  (Spin) super.clone();
        clone.rotationAxis = (Vector3d) rotationAxis.clone();
        clone.rotationDeltaVector =  rotationDeltaVector!= null ? (Vector3d) rotationDeltaVector.clone() : null;
        clone.id = "S-" + nextId++;
        return clone ;
    }

    public int compareTo(Spin that) {
        return Integer.compare(this.shell, that.shell);
    }
    @SuppressWarnings("unused")
    public void setRotationDeltaVectorStr(String string){
        rotationDeltaVector = parseVector3d(string);
    }
    @SuppressWarnings("unused")
    public void setRotationAxisStr(String string){
        rotationAxis = parseVector3d(string);
        rotationAxis.normalize();
    }
    @SuppressWarnings("unused")
    public void setRotationAngleStr(String string){
        rotationAngle = parseAngleStr(string);
    }
    @SuppressWarnings("unused")
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

    public void incShell(int shell) {
        this.shell += shell ;
    }

    public void updateTransform() {
        out(DB_GOD_PULSE_TRACE,"Spin updateTransform spinSignature:" + spinSignature);
        out(DB_GOD_PULSE_TRACE,"Spin updateTransform rotationAxis:" + rotationAxis);
        out(DB_GOD_PULSE_TRACE,"Spin updateTransform rotationAngle:" + rotationAngle);
        //ROTATION
        Transform3D yAxis = new Transform3D();
        AxisAngle4d aa = new AxisAngle4d(rotationAxis,rotationAngle);
        yAxis.setRotation(aa);

        rotator.setAlpha(rotationAlpha);
        rotator.setTransformAxis(yAxis);
        fixedXForm.setTransform(yAxis);

        //SPEED
        rotationAlpha.setIncreasingAlphaDuration(spinSpeed);
    }

    public void GodPulse(boolean sentinel) {
        if(sentinel){
            handleSentinel();
        } else {
            double speedFactor = 1 / (shell * 0.2);
            double delta = speedFactor * (100 - 400 * Math.random());
            //        out("Spin GodPulse delta:" + delta);
            spinSpeed += delta;
            spinSpeed = Math.max(spinSpeed, 100);
            spinSpeed = Math.min(spinSpeed, 60000);
            if (rotationDeltaVector != null && !rotationDeltaVector.equals(ORIGIN)) {
                Transform3D deltaV = new Transform3D();
                Transform3D rot = new Transform3D();

                deltaV.rotX(rotationDeltaVector.getX());
                rot.rotY(rotationDeltaVector.getY());
                deltaV.mul(rot);
                rot.rotZ(rotationDeltaVector.getZ());
                deltaV.mul(rot);
                out(DB_GOD_PULSE,"Spin GodPulse deltaV:\n" + deltaV);
                deltaV.transform(rotationAxis);
                //            out("Spin GodPulse after nudge rotationAxis:" + rotationAxis);
                rotationAxis.normalize();
            } else {
                if (rotationDeltaVector == null) {
                    rotationAngle += Math.PI/2 ;
                    randomize(rotationAxis, 0.5);
                    rotationAxis.normalize();
                    out(DB_GOD_PULSE,id + ":Spin GodPulse random nudge rotationAxis:" + rotationAxis);
                } else {
                    out(id + ":Spin GodPulse NO nudge rotationAxis:" + rotationAxis);
                }
            }
        }
        Appearance app = (particle.isSentinel()) ?
                makeSpinningTexture(Images.getSpinTextureRock()):
                makeSpinningTexture(Images.getSpinTextureEarth());
        spinSphereHolderGroup.removeChild(spinSphereGroup);
        spinSphereGroup = new BranchGroup() ;
        spinSphereGroup.setCapability(BranchGroup.ALLOW_DETACH);
        spinSphereGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        Sphere spinSphere = makeSpinSphere(app,this);
        spinSphereGroup.addChild(spinSphere);
        spinSphereHolderGroup.addChild(spinSphereGroup);
//        out("Spin GodPulse spinSpeed:" + spinSpeed);
//        out("Spin GodPulse rotationAxis:" + rotationAxis);
    }

    private void handleSentinel() {
        out(DB_GOD_PULSE_TRACE,"Spin GodPulse spinSignature:" + spinSignature);
        out(DB_GOD_PULSE_TRACE,"Spin GodPulse rotationAxis:" + rotationAxis);
        out(DB_GOD_PULSE_TRACE,"Spin GodPulse rotationAngle:" + rotationAngle);
//        spinSignature = spinSignature.getCompSpin();
        rotationAxis = spinSignature.getAxis() ;
        rotationAngle = spinSignature.getAngle() ;
        //make sphere rotate the correct
        float min = 0.0f ;
        float max = (float) (Math.PI * 2.0f);
        if(rotationAngle < 0){
            min = (float) (Math.PI * 2.0f) ;
            max =  0.0f  ;
        }
        rotationAxis.normalize();
        rotator.setMaximumAngle(max);
        rotator.setMinimumAngle(min);
        out(DB_GOD_PULSE_TRACE,"Spin GodPulse new spinSignature:" + spinSignature);
        out(DB_GOD_PULSE_TRACE,"Spin GodPulse new rotationAxis:" + rotationAxis);
        out(DB_GOD_PULSE_TRACE,"Spin GodPulse new rotationAngle:" + rotationAngle);
    }

    public int combineShells(int shell) {
        this.shell = (this.shell + shell)/2 + 1;
        out(DB_RULE_TRACE,"Spin combineShells this.shell:" + this.shell + " with Shell:" + shell);
        return shell ;
    }
}
