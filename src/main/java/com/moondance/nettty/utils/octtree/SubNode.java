package com.moondance.nettty.utils.octtree;

import org.jogamp.vecmath.Point3d;

public enum SubNode {

    UP_NE(new Point3d(1,1,1), 0),
    UP_SE(new Point3d(1,1,-1),1),
    UP_SW(new Point3d(-1,1,-1),2),
    UP_NW(new Point3d(-1,1,1),3),
    DN_NE(new Point3d(1,-1,1),4),
    DN_SE(new Point3d(1,-1,-1),5),
    DN_SW(new Point3d(-1,-1,-1),6),
    DN_NW(new Point3d(-1,-1,1),7);
    final public Point3d offset ;
    final public int index ;

    SubNode(Point3d offset, int index) {
        this.index = index ;
        this.offset = offset ;
    }

    public static SubNode findSubNode(OctAddress center, OctAddress point){
        Point3d deltas = center.deltaSigns(point);
        //N-S z
        //E-W x
        //UP-D
        if(deltas.y >= 0){
            //UP_
            if(deltas.z >= 0){
                //UP_N
                if(deltas.x >= 0){
                    return UP_NE ;
                } else {
                    return UP_NW ;
                }
            } else {
                //UP_S z<0
                if (deltas.x >= 0) {
                    return UP_SE;
                } else {
                    return UP_SW;
                }
            }
        } else {
            //DN_
            if(deltas.z >= 0){
                //DN_N
                if(deltas.x >= 0){
                    return DN_NE ;
                } else {
                    return DN_NW ;
                }
            } else {
                //DN_S z<0
                if (deltas.x >= 0) {
                    return DN_SE;
                } else {
                    return DN_SW;
                }
            }
        }
    }

    public Point3d scaledOffset(int newVoxelSize) {
        Point3d res = (Point3d) offset.clone();

        int scale = newVoxelSize/2;
        res.scale(scale);
        return res;
    }
}
