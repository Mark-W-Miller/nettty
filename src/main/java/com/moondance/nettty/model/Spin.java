package com.moondance.nettty.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jogamp.vecmath.Vector3d;

import static com.moondance.nettty.utils.VecUtils.parsePoint3d;
import static com.moondance.nettty.utils.VecUtils.parseVector3d;

@Getter
@Setter
@ToString
public class Spin implements Comparable {
    private static int nextId = 0 ;
    int id ;
    int shell = 1 ;
    Vector3d rotationAxis = new Vector3d(0d,1d,0d);

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

}
