package com.moondance.nettty.utils.octtree;

import javafx.geometry.BoundingBox;
import lombok.Getter;
import lombok.Setter;
import org.jogamp.vecmath.Point3d;

import java.util.ArrayList;
import java.util.List;

import static com.moondance.nettty.graphics.GraphicsUtils.*;
import static com.moondance.nettty.utils.DB.OCTNODE_DB;
import static com.moondance.nettty.utils.Handy.err;
import static com.moondance.nettty.utils.Handy.out;

@Getter
@Setter
public class OctNode<T> {

    OctAddress center;
    OctTree<T> tree;
    double voxelSize;
    boolean branchNode = false; //when true this will only hac=ve children and no data
    List<AddressedData<T>> data = new ArrayList<>();
    List<OctNode<T>> octants = new ArrayList<>(8);

    public OctNode(OctAddress center, OctTree<T> tree, double voxelSize) {
        this.center = center;
        this.tree = tree;
        this.voxelSize = voxelSize;
        for (int ix = 0; ix < 8; ix++) {
            octants.add(null);
        }
    }

    public void add(AddressedData<T> addressedData) {
        out(OCTNODE_DB,"OctNode add add:" + tup3dStr(addressedData.getOctAddress().getAddress()));
        out(OCTNODE_DB,"OctNode add BBB:" + bb2str(makeBoundingBox()));
        BoundingBox bb = makeBoundingBox();
        if (!bb.contains(p3dToP3D(addressedData.getOctAddress().getAddress()))) {
            err("Point not in Octant addressedData:" + tup3dStr(addressedData.getOctAddress().getAddress()));
            err("Point not in Octant  assigned bb:" + bb2str(bb));
        }
        if (voxelSize <= 1 || !branchNode && data.isEmpty() || data.stream().anyMatch(ad -> ad.equals(addressedData))) {
            out(OCTNODE_DB,"OctNode Becomes Leaf:" + tup3dStr(addressedData.getOctAddress().getAddress()));
            data.add(addressedData);
        } else {
            out(OCTNODE_DB,"OctNode Becomes Branch:" + tup3dStr(addressedData.getOctAddress().getAddress()));
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

    private OctNode<T> getOctant(SubNode sn, double voxelSize) {
        if (octants.get(sn.index) == null) {
            double newVoxelSize = voxelSize / 2;
            Point3d scaledOffset = sn.scaledOffset(newVoxelSize);
            Point3d newCenterPoint = (Point3d) center.address.clone();
            newCenterPoint.add(scaledOffset);
            OctAddress newCenter = new OctAddress(newCenterPoint);
            octants.set(sn.index, new OctNode<T>(newCenter, tree, newVoxelSize));
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
                "BB=" + bb2str(makeBoundingBox()) +
                "center=" + tup3dStr(center.address) +
                ", voxelSize=" + voxelSize +
                ", branchNode=" + branchNode +
                ", data=" + data.size() +
                ", octants=" + octantsStr() +
                '}';
    }

    public BoundingBox makeBoundingBox() {
        Point3d c = getCenter().address;
        double half = getVoxelSize() / 2;
        return new BoundingBox(c.x - half, c.y - half, c.z - half, getVoxelSize(), getVoxelSize(), getVoxelSize());
    }
}
