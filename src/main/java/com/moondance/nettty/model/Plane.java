package com.moondance.nettty.model;

import lombok.Getter;
import org.jogamp.vecmath.Point2d;
import org.jogamp.vecmath.Vector3d;

import java.util.ArrayList;
import java.util.List;
@Getter
public enum Plane {
    XY(new Vector3d(1d, 1d, 0d),new Vector3d(0d, 0d, 1d)),
    ZY(new Vector3d(0d, 1d, 1d),new Vector3d(1d, 0d, 0d)),
    XZ(new Vector3d(1d, 0d, 1d),new Vector3d(0d, 1d, 0d));
    final private Vector3d planes;
    final private Vector3d cross;

    Plane(Vector3d planes,Vector3d cross) {
        this.planes = planes;
        this.cross = cross ;
    }

    static final public Point2d[] ijDirs = {
            new Point2d(0, 1),
            new Point2d(1, 1),
            new Point2d(1, 0),
            new Point2d(1, -1),
            new Point2d(0, -1),
            new Point2d(-1, -1),
            new Point2d(-1, 0),
            new Point2d(-1, 1)
    };

    public Plane crossPlane(Plane plane){
        switch(this){
            case XY:
                return plane == ZY ? XZ : ZY;
            case ZY:
                return plane == XY ? XZ : XY;
            case XZ:
                return plane == ZY ? XY : ZY;
        }
        throw new RuntimeException("Ooops:" + this);
    }
    public List<Vector3d> makeDirVectors() {
        List<Vector3d> result = new ArrayList<>();
        for (int d = 0; d < 8; d++) {
            Point2d deltaIJ = ijDirs[d];
            Vector3d dirVec = new Vector3d();
            result.add(dirVec);
            switch (this) {
                case XY:
                    dirVec.x = deltaIJ.x;
                    dirVec.y = deltaIJ.y;
                    break;
                case ZY:
                    dirVec.z = deltaIJ.x;
                    dirVec.y = deltaIJ.y;
                    break;
                case XZ:
                    dirVec.x = deltaIJ.x;
                    dirVec.z = deltaIJ.y;
                    break;
            }
        }
        return result;
    }
}
