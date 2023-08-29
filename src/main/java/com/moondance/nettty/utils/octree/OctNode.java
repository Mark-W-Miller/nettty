package com.moondance.nettty.utils.octree;

import javafx.geometry.BoundingBox;
import lombok.Getter;
import lombok.Setter;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Vector3d;

import java.util.ArrayList;
import java.util.List;

import static com.moondance.nettty.graphics.GraphicsUtils.*;
import static com.moondance.nettty.utils.DB.DB_OCTNODE;
import static com.moondance.nettty.utils.Handy.err;
import static com.moondance.nettty.utils.Handy.out;

@Getter
@Setter
public class OctNode<T extends OctMember> {

    OctAddress center;
    Octree<T> tree;
    double voxelSize;
    boolean branchNode = false; //when true this will only have children and no data
    List<AddressedData<T>> data = new ArrayList<>();
    List<OctNode<T>> octants = new ArrayList<>(8);
    BoundingBox boundingBox = null;
    public OctNode(OctAddress center, Octree<T> tree, double voxelSize) {
        this.center = center;
        this.tree = tree;
        this.voxelSize = voxelSize;
        for (int ix = 0; ix < 8; ix++) {
            octants.add(null);
        }
    }

    public void add(AddressedData<T> addressedData) {
        out(DB_OCTNODE,"OctNode add add:" + tup3dStr(addressedData.getOctAddress().getAddress()));
        out(DB_OCTNODE,"OctNode add BBB:" + bb2str(makeBoundingBox()));
        BoundingBox bb = makeBoundingBox();
        if (!inNode(addressedData.getOctAddress())) {
            err("Point not in Octant addressedData:" + tup3dStr(addressedData.getOctAddress().getAddress()));
            err("Point not in Octant  assigned bb:" + bb2str(bb));
        }
//        boolean sameAddressAsOthers = data.stream().anyMatch(ad -> ad.equals(addressedData));
        if (voxelSize <= 1) {
            out(DB_OCTNODE,"OctNode Becomes Leaf:" + tup3dStr(addressedData.getOctAddress().getAddress()));
//            out(DB_OCTNODE,"OctNode Becomes sameAddressAsOthers:" + sameAddressAsOthers);
            branchNode = false;
            data.add(addressedData);
        } else {
            out(DB_OCTNODE,"OctNode Becomes Branch:" + tup3dStr(addressedData.getOctAddress().getAddress()));
            branchNode = true;
            //Octant has data, so all must be sent down, find a new sub voxels for all
            for (AddressedData<T> ad : data) {
                SubNode sn = SubNode.findSubNode(center, ad.getOctAddress());
                OctNode<T> octant = getOctant(sn, voxelSize);
                octant.add(ad);
            }
            data.clear();
            SubNode sn = SubNode.findSubNode(center, addressedData.getOctAddress());
            OctNode<T> octant = getOctant(sn, voxelSize);
            octant.add(addressedData);
        }
    }

    public boolean inNode(OctAddress add) {
        Point3d point = add.getAddress() ;
        makeBoundingBox();
        return point.getX() >= boundingBox.getMinX() &&
                point.getX() < boundingBox.getMaxX() &&
                point.getY() >= boundingBox.getMinY() &&
                point.getY() < boundingBox.getMaxY() &&
                point.getZ() >= boundingBox.getMinZ() &&
                point.getZ() < boundingBox.getMaxZ();

    }

    private OctNode<T> getOctant(SubNode sn, double voxelSize) {
        if (octants.get(sn.index) == null) {
            double newVoxelSize = voxelSize / 2;
            Vector3d scaledOffset = sn.scaledOffset(newVoxelSize);
            Point3d newCenterPoint = (Point3d) center.address.clone();
            newCenterPoint.add(scaledOffset);
            OctAddress newCenter = new OctAddress(newCenterPoint);
            octants.set(sn.index, new OctNode<>(newCenter, tree, newVoxelSize));
            BoundingBox bb = octants.get(sn.index).makeBoundingBox();
            verifyBB(bb);
            octants.get(sn.index).makeBoundingBox();
            return octants.get(sn.index);
        } else {
            return octants.get(sn.index);
        }
    }


    private String octantsStr() {
        StringBuilder builder = new StringBuilder();
        for (OctNode<T> oct : octants) {
            if (oct == null) {
                builder.append("-");
            } else {
                builder.append(oct.branchNode ? "B" : "L");
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "OctNode{" +
                "data=" + data.size() +
                ", branchNode=" + branchNode +
                ", BB=" + bb2str(makeBoundingBox()) +
                "center=" + tup3dStr(center.address) +
                ", voxelSize=" + voxelSize +
                ", octants=" + octantsStr() +
                '}';
    }

    public BoundingBox makeBoundingBox() {
        if(boundingBox!= null){
            return boundingBox ;
        }
        Point3d c = getCenter().address;
        double half = getVoxelSize() / 2;
        return boundingBox = new BoundingBox(c.x - half, c.y - half, c.z - half, getVoxelSize(), getVoxelSize(), getVoxelSize());
    }
}
