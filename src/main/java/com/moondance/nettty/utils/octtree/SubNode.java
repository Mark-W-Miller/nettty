package com.moondance.nettty.utils.octtree;

import org.jogamp.vecmath.Point3i;

public enum SubNode {

    UP_NE(new Point3i(1,1,1), 0),
    UP_SE(new Point3i(1,1,-1),1),
    UP_SW(new Point3i(-1,1,-1),2),
    UP_NW(new Point3i(-1,1,1),3),
    DN_NE(new Point3i(1,-1,1),4),
    DN_SE(new Point3i(1,-1,-1),5),
    DN_SW(new Point3i(-1,-1,-1),6),
    DN_NW(new Point3i(-1,-1,1),7);
    final Point3i offset ;
    final int index ;

    SubNode(Point3i offset, int index) {
        this.index = index ;
        this.offset = offset ;
    }

    public static SubNode findSubNode(OctAddress center, OctAddress point){
        Point3i deltas = center.deltaSigns(point);
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

    public Point3i scaledOffset(int newVoxelSize) {
        Point3i res = (Point3i) offset.clone();
        int scale = (int) (Math.sqrt(3) * (float) (newVoxelSize + 3));
        res.scale(scale);
        return res;
    }
}
