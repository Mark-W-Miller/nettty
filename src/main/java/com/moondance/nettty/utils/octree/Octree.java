package com.moondance.nettty.utils.octree;

import com.moondance.nettty.utils.MapOfLists;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point3D;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jogamp.vecmath.Point3d;

import java.util.ArrayList;
import java.util.List;

import static com.moondance.nettty.graphics.GraphicsUtils.p3dToP3D;
import static com.moondance.nettty.utils.DB.OCTTREE_DUMP_DB;
import static com.moondance.nettty.utils.Handy.*;

@Getter
@Setter
@ToString
public class Octree<T> {

    OctNode<T> root;
    int voxelSize;

    public Octree(int voxelSize) {
        this.voxelSize = voxelSize;
        root = new OctNode<>(new OctAddress(), this, voxelSize);
    }

    public void add(AddressedData<T> addressedData) {
        if(!root.makeBoundingBox().contains(p3dToP3D(addressedData.getOctAddress().getAddress()))){
            err("ROOT Out of bounds address:" + addressedData);
        }
        root.add(addressedData);
    }

    public void add(OctAddress address, T data) {
        root.add(new AddressedData<>(address, data));
    }


    /**
     * @param address i,j,k of the address of the cell
     * @param radius  how big of a sphere. which translates to how deep into the tree will it search, and then return all below.
     * @return a map of all objects within the radius keyed on their address.
     */
    public MapOfLists<OctAddress, T> findNeighbors(OctAddress address, double radius) {
        return null;
    }

    public List<T> getAllData() {
        List<T> allData = new ArrayList<>();
        new OctreeWalker<T>(root) {

            @Override
            public void visitLeaf(OctNode<T> node, int level) {
                for (AddressedData<T> ad : node.getData()) {
                    allData.add(ad.getData());
                }
            }

            @Override
            public void visitBranch(OctNode<T> node, int level) {
            }
        };
        return allData;
    }

    public void verifyTree() {
        new OctreeWalker<T>(root) {

            @Override
            public void visitLeaf(OctNode<T> node, int level) {
                BoundingBox bb = node.makeBoundingBox();
                for (AddressedData<T> ad : node.getData()) {

                    Point3d p3d = ad.getOctAddress().getAddress();
                    Point3D p3D = new Point3D(p3d.x,p3d.y,p3d.z);
                    if(!bb.contains(p3D)){
                        err("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!verifyTree NOT IN BOX:" + node + " voxelSize:" + node.getVoxelSize());
                        err(tabs(8) + "verifyTree NOT IN bb:" + bb + "\n" + tabs(8) + " p3D:" + p3D);
                    }
                }
            }

            @Override
            public void visitBranch(OctNode<T> node, int level) {
            }
        };
    }

    public static <T> void dumpTree(Octree<T> octree) {
        out("dumpTree:-----------------------------------------------------------" + octree.getVoxelSize());
        new OctreeWalker<T>(octree.getRoot()){

            @Override
            public void visitLeaf(OctNode<T> node, int level) {
                boolean crowded = node.data.size() > 2 ;
                out(OCTTREE_DUMP_DB,tabs(level) + ((crowded) ? ANSI_GREEN : ANSI_RED) + "LEAF:" + node);
            }

            @Override
            public void visitBranch(OctNode<T> node, int level) {
                out(OCTTREE_DUMP_DB,tabs(level) + "BRCH:" + node);
            }
        };
    }

}
