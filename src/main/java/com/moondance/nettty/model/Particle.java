package com.moondance.nettty.model;

import com.moondance.nettty.utils.MapOfLists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.TransformGroup;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Vector3d;

import java.util.ArrayList;
import java.util.List;

import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.VecUtils.*;

@Getter
@Setter
@ToString
public class Particle implements Comparable{
    private static int nextId = 0 ;
    int id ;
    Nett nett ;
    List<Spin> spins = new ArrayList<>();
    Vector3d motionVector = new Vector3d(0,0,0);
    Point3d position = new Point3d();
    TransformGroup currentParticleTransform ;
    boolean wiggleWhenWalking = false ;
    public Particle() {
        id = nextId++;
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
        if(motionVector != null){
            position.add(motionVector);
            if(wiggleWhenWalking){
                randomize(position,0.5d);
            }
        } else {
            position.x += 0.5 - Math.random();
            position.y += 0.5 - Math.random();
            position.z += 0.5 - Math.random();
        }
        spins.stream().forEach(spin->spin.GodPulse());
    }
}
