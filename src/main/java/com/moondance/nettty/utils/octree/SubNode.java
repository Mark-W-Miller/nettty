package com.moondance.nettty.utils.octree;

import org.jogamp.vecmath.Point3d;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum SubNode {

    UP_NE(new Point3d(1, 1, 1), 0),
    UP_SE(new Point3d(1, 1, -1), 1),
    UP_SW(new Point3d(-1, 1, -1), 2),
    UP_NW(new Point3d(-1, 1, 1), 3),
    DN_NE(new Point3d(1, -1, 1), 4),
    DN_SE(new Point3d(1, -1, -1), 5),
    DN_SW(new Point3d(-1, -1, -1), 6),
    DN_NW(new Point3d(-1, -1, 1), 7);
    final public Point3d offset;
    final public int index;
    static List<SubNode> dirs = Arrays.asList(UP_NE, UP_SE, UP_SW, UP_NW, DN_NE, DN_SE, DN_SW, DN_NW);

    SubNode(Point3d offset, int index) {
        this.index = index;
        this.offset = offset;
    }

    public static SubNode findSubNode(OctAddress center, OctAddress point) {
        Point3d deltas = center.deltaSigns(point);
        //N-S z
        //E-W x
        //UP-D
        if (deltas.y >= 0) {
            //UP_
            if (deltas.z >= 0) {
                //UP_N
                if (deltas.x >= 0) {
                    return UP_NE;
                } else {
                    return UP_NW;
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
            if (deltas.z >= 0) {
                //DN_N
                if (deltas.x >= 0) {
                    return DN_NE;
                } else {
                    return DN_NW;
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

    public static Point3d randomDirection() {
        int dir = ThreadLocalRandom.current().nextInt(0, 8);
        return (Point3d) dirs.get(dir).offset.clone();
    }

    public Point3d scaledOffset(double newVoxelSize) {
        Point3d res = (Point3d) offset.clone();

        double scale = newVoxelSize / 2;
        res.scale(scale);
        return res;
    }
}
