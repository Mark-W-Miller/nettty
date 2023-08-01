package com.moondance.nettty.utils.octree;

import javafx.geometry.Point3D;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.jogamp.vecmath.Point3d;

import static com.moondance.nettty.graphics.GraphicsUtils.p3dToP3D;
import static com.moondance.nettty.graphics.GraphicsUtils.tup3dStr;

@Getter
@Setter
@EqualsAndHashCode
public class OctAddress implements Comparable<OctAddress>{
    public Point3d address ;
    public double radius ;

    public OctAddress(){
        this.address = new Point3d();
        this.radius = 1d ;

    }
    public OctAddress(Point3d address){
        this.address = (Point3d) address.clone();
        this.radius = 1d ;

    }
    public OctAddress(double x, double y, double z, double radius){
        this.address = new Point3d((int) x, (int) y, (int) z);
        this.radius = radius ;
    }
    public OctAddress(int i, int j, int k, double radius){
        this.address = new Point3d(i,j,k);
        this.radius = radius ;
    }
    public OctAddress(Point3d point, double radius){
        this.address = point;
        this.radius = radius ;
    }

    public OctAddress(OctAddress addressOfCenter, int x, int y, int z) {
        address = (Point3d) addressOfCenter.address.clone();
        address.add(new Point3d(x,y,z));
        radius = addressOfCenter.radius ;
    }

    public Point3D addressP3D(){
        return p3dToP3D(address);
    }

    public Point3d deltaSigns(OctAddress point) {
        Point3d res = new Point3d();
        res.sub(point.address,address);
        res.x = Math.signum(res.x);
        res.y = Math.signum(res.y);
        res.z = Math.signum(res.z);
        return res ;
    }

    @Override
    public int compareTo(OctAddress that) {
        return Double.compare(fastSize(),that.fastSize());
    }

    private double fastSize(){
        return Math.abs(address.x) + Math.abs(address.y) + Math.abs(address.z)  ;
    }
    @Override
    public String toString() {
        return "OctAddress:" + tup3dStr(address) ;
    }
}
