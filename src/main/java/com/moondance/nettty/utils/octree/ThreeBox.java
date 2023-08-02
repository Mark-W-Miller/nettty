package com.moondance.nettty.utils.octree;

import com.moondance.nettty.utils.MapOfLists;
import lombok.Getter;
import lombok.Setter;
import org.jogamp.vecmath.Point3d;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ThreeBox<T extends OctMember> {
    Octree octree;
    OctAddress addressOfCenter;//address of 'center'
    MapOfLists<OctAddress, T> boxMap = new MapOfLists<>();

    public ThreeBox(Octree octree, OctAddress addressOfCenter) {
        this.octree = octree;
        this.addressOfCenter = addressOfCenter;
        walkTree();
    }

    public List<T> getDataCenter(){
        return boxMap.get(addressOfCenter);
    }
    private void walkTree() {
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    OctAddress octAddress = new OctAddress(addressOfCenter, x, y, z);
                    List<T> list = octree.lookup(octAddress);
                    boxMap.put(octAddress, list);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ThreeBox @:" + addressOfCenter +
                "\n" + buildBoxMap(boxMap);
    }

    private String buildBoxMap(MapOfLists<OctAddress, T> boxMap) {
        StringBuilder b = new StringBuilder("");
        for (int y = 1; y > -2; y--) {
            for (int z = 1; z > -2; z--) {
                for (int x = -1; x < 2; x++) {
                    OctAddress octAddress = new OctAddress(new Point3d(x, y, z));
                    List<T> data = octree.lookup(octAddress) ;
                    String spinStr = data.stream().map(om->om.shortHand()).collect(Collectors.joining());
                    String finalString = String.format(" %s:%3s ", octAddress.shortHand(), spinStr);
                    b.append(finalString);
                }
                b.append('\n');
            }
            b.append('\n');
        }
        return b.toString();
    }
}
