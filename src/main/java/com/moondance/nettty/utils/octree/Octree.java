package com.moondance.nettty.utils.octree;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point3D;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jogamp.vecmath.Point3d;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.moondance.nettty.utils.DB.DB_OCTREE_DUMP;
import static com.moondance.nettty.utils.DB.DB_OCTWALK;
import static com.moondance.nettty.utils.Handy.*;

@Getter
@Setter
@ToString
public class Octree<T extends OctMember> {

    OctNode<T> root;
    int voxelSize;

    public Octree(int voxelSize) {
        this.voxelSize = voxelSize;
        root = new OctNode<>(new OctAddress(), this, voxelSize);
    }

    public void add(AddressedData<T> addressedData) {
        if (!root.makeBoundingBox().contains(addressedData.addressP3D())) {
            err("ROOT Out of bounds address:" + addressedData);
        }
        root.add(addressedData);
    }

    public void add(OctAddress address, T data) {
        root.add(new AddressedData<>(address, data));
    }

    public List<T> lookup(OctAddress octAddress) {
        List<T> result = new ArrayList<>();
        new OctreeWalker<T>(getRoot()) {

            @Override
            public void visitLeaf(OctNode<T> node, int level) {
                out(DB_OCTWALK, tabs(level) + ANSI_RED + "LEAF level:" + level + " " + node);
                if (node.makeBoundingBox().contains(octAddress.addressP3D())) {
                    result.addAll(node.getData().stream().filter(om->!om.getData().isKill()).map(AddressedData::getData).collect(Collectors.toList()));
                    result.forEach(p->p.setIn3Box(true));
                    out(DB_OCTWALK, tabs(level) + ANSI_RED + "FOUND level:" + level + " " + node);
                    stop("FOUND at:" + node.getCenter() + "\n" + result);
                } else {
                    out(DB_OCTWALK, tabs(level) + ANSI_RED + "NOT FOUND level:" + level + " " + node);
                }
            }

            @Override
            public boolean visitBranch(OctNode<T> node, int level) {
                boolean skipNode = node.makeBoundingBox().contains(octAddress.addressP3D());
                out(DB_OCTWALK, tabs(level) + "BRCH skipNode:" + skipNode + " " + node);
                return skipNode;
            }
        };
        return result;
    }

    /**
     * @param address i,j,k of the address of the cell
     * @return a map of all objects within one space, by filling a ThreeBox.
     */
    public ThreeBox<T> findThreeBox(OctAddress address) {

        return new ThreeBox<>(this, address);
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
                    Point3D p3D = new Point3D(p3d.x, p3d.y, p3d.z);
                    if (!bb.contains(p3D)) {
                        err("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!verifyTree NOT IN BOX:" + node + " voxelSize:" + node.getVoxelSize());
                        err(tabs(8) + "verifyTree NOT IN bb:" + bb + "\n" + tabs(8) + " p3D:" + p3D);
                    }
                }
            }
        };
    }

    public static <T extends OctMember> void dumpTree(Octree<T> octree) {
        out("dumpTree:-----------------------------------------------------------" + octree.getVoxelSize());
        new OctreeWalker<T>(octree.getRoot()) {

            @Override
            public void visitLeaf(OctNode<T> node, int level) {
                boolean crowded = node.data.size() > 2;
                out(DB_OCTREE_DUMP, tabs(level) + ((crowded) ? ANSI_GREEN : ANSI_RED) + "LEAF:" + node);
            }

            @Override
            public boolean visitBranch(OctNode<T> node, int level) {
                out(DB_OCTREE_DUMP, tabs(level) + "BRCH:" + node);
                return true;
            }
        };
    }

}
