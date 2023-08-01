package com.moondance.nettty.model;

import org.jogamp.vecmath.Point2d;
import org.jogamp.vecmath.Vector3d;

import java.util.ArrayList;
import java.util.List;

public enum Plane {
    XY(new Vector3d(1d,1d,0d)),
    ZY(new Vector3d(0d,1d,1d)),
    XZ(new Vector3d(1d,0d,1d));
    Vector3d planes ;
    Plane(Vector3d planes){
        this.planes = planes ;
    }

    static public Point2d ijDirs[] = {
            new Point2d(0, 1),
            new Point2d(1, 1),
            new Point2d(1, 0),
            new Point2d(1, -1),
            new Point2d(0, -1),
            new Point2d(-1, -1),
            new Point2d(-1, 0),
            new Point2d(-1, 1)
    };

     public List<Vector3d> makeDirVectors(){
         List<Vector3d> result = new ArrayList<>();
         for(int d=0 ; d<8; d++){
             Point2d deltaIJ = ijDirs[d];
             Vector3d dirVec = new Vector3d();
             result.add(dirVec);
             switch (this) {
                 case XY:
                     dirVec.x = deltaIJ.x ;
                     dirVec.y = deltaIJ.y ;
                     break;
                 case ZY:
                     dirVec.z = deltaIJ.x ;
                     dirVec.y = deltaIJ.y ;
                     break;
                 case XZ:
                     dirVec.x = deltaIJ.x ;
                     dirVec.z = deltaIJ.y ;
                     break;
             }
         }
         return result ;
     }
}
