package com.moondance.nettty.model;

import com.moondance.nettty.utils.octtree.Addressable;
import com.moondance.nettty.utils.octtree.AddressedData;
import com.moondance.nettty.utils.octtree.OctAddress;
import com.moondance.nettty.utils.octtree.SubNode;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.TransformGroup;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Vector3d;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.VecUtils.*;

@Getter
@Setter
public class Particle implements Comparable, Cloneable, Addressable {
    private static int nextId = 0 ;
    String id ;
    String idRef ;
    Point3d position = new Point3d();
    Vector3d motionVector = new Vector3d(0,0,0);
    List<Spin> spins = new ArrayList<>();
    boolean sentinel = false ;
    double cast = 10;
    TransformGroup currentParticleTransform ;
    Nett nett ;
    int numCopiesInitial = 1 ;
    boolean wiggleWhenWalking = false ;
    public Particle() {
        id = "P-" + nextId++;
    }

    @Override
    @SneakyThrows
    public Particle clone() {
        Particle clone =  (Particle) super.clone();
        clone.motionVector = (Vector3d) motionVector.clone();
        clone.position = (Point3d) position.clone();
        clone.spins = spins.stream().map(spin->spin.clone()).collect(Collectors.toList());
        return clone ;
    }

    public void populateFromReference(Particle referenceParticle) {
        sentinel = referenceParticle.isSentinel();
        spins = referenceParticle.getSpins().stream().map(spin->spin.clone()).collect(Collectors.toList());
        id = referenceParticle.getId() + "-" + nextId ;
    }

    @Override
    public int compareTo(Object o) {
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

    public void setMotionVectorStr(String string){
        motionVector = parseVector3d(string);
    }
    public void setPositionStr(String string){
        position = parsePoint3d(string);
    }


    public void GodPulse(int i) {
        if(isSentinel()){
            Point3d change = SubNode.randomDirection();
            position.add(change);
        } else {
            if (motionVector != null) {
                position.add(motionVector);
                if (wiggleWhenWalking) {
                    randomize(position, 0.5d);
                }
//            out("Particle GodPulse position:" + position);
            } else {
                position.x += 0.5 - Math.random();
                position.y += 0.5 - Math.random();
                position.z += 0.5 - Math.random();
            }
            spins.stream().forEach(spin -> spin.GodPulse());
        }
    }

    @Override
    public AddressedData<Particle> makeAddressableData() {
        return new AddressedData<>(new OctAddress(this.getPosition(),1),this);
    }

    public int maxShell(){
        return spins.stream().mapToInt(s->s.shell).max().orElse(0);
    }

    @Override
    public String toString() {
        return "Particle{" +
                "id=" + id +
                ", spins=" + spins +
                ", motionVector=" + motionVector +
                ", position=" + position +
                ", currentParticleTransform=" + currentParticleTransform +
                ", wiggleWhenWalking=" + wiggleWhenWalking +
                '}';
    }

}
