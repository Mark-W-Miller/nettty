package com.moondance.nettty.model;

import com.moondance.nettty.utils.MapOfLists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Vector3d;

import java.util.ArrayList;
import java.util.List;

import static com.moondance.nettty.utils.VecUtils.parsePoint3d;
import static com.moondance.nettty.utils.VecUtils.parseVector3d;

@Getter
@Setter
@ToString
public class Particle implements Comparable{
    private static int nextId = 0 ;
    int id ;
    List<Spin> spins = new ArrayList<>();
    Vector3d motionVector = new Vector3d(0,0,0);
    Point3d position = new Point3d();

    public Particle() {
        id = nextId++;
    }

    @Override
    public int compareTo(Object o) {
        Particle that = (Particle) o;
        return Integer.compare(this.spins.size(), that.spins.size());
    }

    public void setMotionVectorStr(String string){
        motionVector = parseVector3d(string);
    }
    public void setPositionStr(String string){
        position = parsePoint3d(string);
    }
}
