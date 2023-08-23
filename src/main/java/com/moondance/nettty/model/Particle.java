package com.moondance.nettty.model;

import com.moondance.nettty.graphics.NettGroup;
import com.moondance.nettty.graphics.ParticleGroup;
import com.moondance.nettty.utils.octree.*;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jogamp.java3d.BranchGroup;
import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.TransformGroup;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Vector3d;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.moondance.nettty.utils.DB.DB_GOD_PULSE;
import static com.moondance.nettty.utils.DB.DB_RULE_TRACE;
import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.VecUtils.*;

@Getter
@Setter
public class Particle implements Comparable<Particle>, Cloneable, Addressable<Particle>, OctMember {
    private static int nextId = 0;
    String id;
    transient String idRef;
    Point3d position = new Point3d();
    Vector3d motionVector = null;
    List<Spin> spins = new ArrayList<>();
    boolean kill = false;
    boolean sentinel = false;
    boolean wiggleWhenWalking = false;
    boolean in3Box = false;
    transient TransformGroup currentParticleTransform;
    transient Nett nett;
    transient int numCopiesInitial = 1;
    transient double cast = 0;
    transient NettGroup nettGroup ;
    transient ParticleGroup particleGroup ;
    transient BranchGroup removeHolder ;
    public Particle() {
        id = "P-" + nextId++;
    }

    @SuppressWarnings("unused")
    static public Particle makeSentinel(Point3d position, SpinSignature spinSignature) {
        Particle particle = new Particle();
        particle.id = "S-" + nextId++;
        particle.position = position;
        Spin spin = new Spin(spinSignature);
        particle.getSpins().add(spin);
        particle.sentinel = true;
        return particle;
    }

    @Override
    @SneakyThrows
    public Particle clone() {
        Particle clone = (Particle) super.clone();
        if(motionVector != null) {
            clone.motionVector = (Vector3d) motionVector.clone();
        }
        clone.position = (Point3d) position.clone();
        clone.spins = spins.stream().map(Spin::clone).collect(Collectors.toList());
        clone.setId("SC-" + nextId++);
        return clone;
    }

    public void populateFromReference(Particle referenceParticle) {
        sentinel = referenceParticle.isSentinel();
        spins = referenceParticle.getSpins().stream().map(Spin::clone).collect(Collectors.toList());
        id = referenceParticle.getId() + "-" + nextId++;
    }

    public SpinSignature getFirstSpinSignature(){
        if(!spins.isEmpty()){
            return spins.get(0).getSpinSignature();
        } else {
            return SpinSignature.Y_CW ;
        }
    }
    public Spin getFirstSpin(){
        if(!spins.isEmpty()){
            return spins.get(0);
        } else {
            return null ;
        }
    }
    @Override
    public int compareTo(Particle o) {
        Particle that = (Particle) o;
        return Integer.compare(this.spins.size(), that.spins.size());
    }

    public void updateTransforms() {
//        out("Particle updateTransforms");
        Vector3d vec = new Vector3d();
        vec.set(getPosition());
        Transform3D t3d = new Transform3D();
        t3d.setTranslation(vec);
        currentParticleTransform.setTransform(t3d);
        for (Spin spin : getSpins()) {
            spin.updateTransform();
        }
    }

    @SuppressWarnings("unused")
    public void setMotionVectorStr(String string) {
        motionVector = parseVector3d(string);
    }

    @SuppressWarnings("unused")
    public void setPositionStr(String string) {
        position = parsePoint3d(string);
    }

    public void bounce(int energy){
        Vector3d change = SubNode.randomDirection();
        change.scale(energy);
        if(motionVector == null) {
            setMotionVector(change);
        } else {
            motionVector.add(change);
        }
        out(DB_RULE_TRACE,"Particle bounce id:" + id + " by:" + motionVector);
    }
    @SuppressWarnings("unused")
    public boolean notProcessed() {
        return motionVector != null || !kill;
    }

    @SuppressWarnings("unused")
    public void GodPulse(int i) {
        if (isSentinel()) {
            spins.forEach(spin -> spin.GodPulse(isSentinel()));
            if (motionVector != null) {
                position.add(motionVector);
                motionVector = null;
            }
        } else {
            if (motionVector != null) {
                position.add(motionVector);
                if (wiggleWhenWalking) {
                    randomize(position, 0.5d);
                }
                out(DB_GOD_PULSE, "Particle GodPulse position:" + position);
            } else {
                position.x += 0.5 - Math.random();
                position.y += 0.5 - Math.random();
                position.z += 0.5 - Math.random();
            }
            spins.forEach(spin -> spin.GodPulse(isSentinel()));
        }
    }

    @Override
    public AddressedData<Particle> makeAddressableData() {
        return new AddressedData<>(new OctAddress(this.getPosition(), 1), this);
    }

    public int maxShell() {
        return spins.stream().mapToInt(s -> s.shell).max().orElse(0);
    }

    @Override
    public String toString() {
        return (kill ? "*" : "") + (sentinel ? "Sentinel" : "Particle") + "{" +
                "id=" + id +
                ", position=" + position +
                ", motionVector=" + motionVector +
                ", spins=" + spins.stream().map(Spin::shortHand).collect(Collectors.joining(":")) +
                '}';
    }

    @Override
    public String shortHand() {
        return spins.stream().map(Spin::shortHand).collect(Collectors.joining());
    }


}
