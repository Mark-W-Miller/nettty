package com.moondance.nettty.utils.octtree;

import org.jogamp.vecmath.Point3i;

public class OctAddress implements Comparable<OctAddress>{
    Point3i address ;
    double radius ;

    public OctAddress(){
        this.address = new Point3i();
        this.radius = 1d ;

    }
    public OctAddress(Point3i address){
        this.address = address;
        this.radius = 1d ;

    }
    public OctAddress(double x, double y, double z, double radius){
        this.address = new Point3i((int) x, (int) y, (int) z);
        this.radius = radius ;
    }
    public OctAddress(int i, int j, int k, double radius){
        this.address = new Point3i(i,j,k);
        this.radius = radius ;
    }
    public OctAddress(Point3i point, double radius){
        this.address = point;
        this.radius = radius ;
    }

    public Point3i deltaSigns(OctAddress point) {
        Point3i res = new Point3i();
        res.sub(point.address,address);
        res.x = Integer.signum(res.x);
        res.y = Integer.signum(res.y);
        res.z = Integer.signum(res.z);
        return res ;
    }

    @Override
    public int compareTo(OctAddress that) {
        return Double.compare(radius,((OctAddress) that).radius);
    }
}
