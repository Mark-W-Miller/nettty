package com.moondance.nettty.utils.octree;

import com.moondance.nettty.utils.MapOfLists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ThreeBox<T> {
    @ToString.Exclude Octree octree;
    OctAddress addressOfCenter;//address of 'center'
    MapOfLists<OctAddress,T> boxMap = new MapOfLists<>();
    public ThreeBox(Octree octree, OctAddress addressOfCenter) {
        this.octree = octree;
        this.addressOfCenter = addressOfCenter;
        walkTree();
    }

    private void walkTree() {
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    OctAddress octAddress = new OctAddress(addressOfCenter,x,y,z);
                    List<T> list = octree.lookup(octAddress) ;
                    boxMap.put(octAddress,list);
                }
            }
        }
    }
}
