package com.moondance.nettty.utils.octree;

import com.moondance.nettty.model.Particle;
import javafx.geometry.BoundingBox;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.moondance.nettty.graphics.GraphicsUtils.bb2str;
import static com.moondance.nettty.utils.DB.*;
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
        out(DB_OCTWALK,ANSI_YELLOW + "Octree lookup:" + octAddress);
        List<T> result = new ArrayList<>();
        new OctreeWalker<T>(getRoot()) {

            @Override
            public void visitLeaf(OctNode<T> node, int level) {
                if (node.inNode(octAddress)) {
                    out(DB_OCTWALK, tabs(level) + ANSI_GREEN + "octAddress:" + octAddress + " In:" + bb2str(node.makeBoundingBox()));
                    out(DB_OCTWALK, tabs(level) + ANSI_GREEN + "FOUND level:" + level + " " + node);
                    result.addAll(node.getData().stream().filter(om->!om.getData().isKill()).map(AddressedData::getData).collect(Collectors.toList()));
                    result.forEach(p->p.setIn3Box(true));
                    stop("FOUND at:" + node.getCenter() + "\n" + result);
                } else {
                    out(DB_OCTWALK_TRACE, tabs(level) + ANSI_RED + "NOT FOUND level:" + level + " " + node);
                }
            }


            @Override
            public boolean visitBranch(OctNode<T> node, int level) {
                boolean inNode = node.inNode(octAddress);
                out(DB_OCTWALK_TRACE, tabs(level) + "visitBranch inNode:" + inNode + " " + node);
                return inNode;
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

                    if (!node.inNode(ad.getOctAddress())) {
                        err("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!verifyTree NOT IN BOX:" + node + " voxelSize:" + node.getVoxelSize());
                        err(tabs(8) + "verifyTree NOT IN bb:" + bb + "\n" + tabs(8) + " ad:" + ad);
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
                out(DB_OCTREE_DUMP, tabs(level) + ((crowded) ? ANSI_GREEN : ANSI_RED) + "LEAF:" + node + "\n" + formatList(level + 1,node.getData()));
            }

            @Override
            public boolean visitBranch(OctNode<T> node, int level) {
                out(DB_OCTREE_DUMP, tabs(level) + "BRCH:" + node );
                return true;
            }
        };
    }

    public void lookupDB(List<Particle> particles) {
        particles.stream().forEach(part->{
            _lookUpDB(part.makeAddressableData().getOctAddress());
        });
    }

    private void _lookUpDB(OctAddress octAddress) {
        out(DB_OCTREE_DUMP, ANSI_PURPLE + "_lookUpDB:" + octAddress);
        new OctreeWalker<T>(getRoot()) {

            @Override
            public void visitLeaf(OctNode<T> node, int level) {
                if (node.makeBoundingBox().contains(octAddress.addressP3D())) {
                    out(DB_OCTREE_DUMP, tabs(level) + ANSI_GREEN + "visitLeaf In Node BB level:" + level + " Node:" + node + " octAddress:" + octAddress);
                } else {
                    out(DB_OCTREE_DUMP, tabs(level) + ANSI_RED + "visitLeaf NOT In Node BB FOUND level:" + level + " " + node);
                }
            }

            @Override
            public boolean visitBranch(OctNode<T> node, int level) {
                boolean skipNode = node.makeBoundingBox().contains(octAddress.addressP3D());
                out(DB_OCTREE_DUMP, tabs(level) + "visitBranch skipNode:" + skipNode + " " + node);
                return skipNode;
            }
        };
    }

}
