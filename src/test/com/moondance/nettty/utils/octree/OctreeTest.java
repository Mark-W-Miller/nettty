package com.moondance.nettty.utils.octree;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.octree.Octree.dumpTree;

public class OctreeTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() {
    }

    public void testAdd() {
        Octree<OctAddress> octree = new Octree<>(64);
        List<OctAddress> addresses = new ArrayList<>();

        for(SubNode sn:SubNode.values()){
            addresses.add(new OctAddress(sn.offset));
            OctAddress octAdd = new OctAddress(sn.offset) ;
            octAdd.address.scale(2);
            addresses.add(octAdd);
            octAdd = new OctAddress(sn.offset) ;
            octAdd.address.scale(3);
            addresses.add(octAdd);
        }
        out(addresses);
        for(OctAddress add: addresses){
            octree.add(add,add);
        }
        dumpTree(octree);
        for(OctAddress add: addresses) {
            out("Search for add:" + add);
            List<OctAddress> adds = octree.lookup(add);
            List<OctAddress> found = adds.stream().filter(a->a.equals(add)).collect(Collectors.toList());
            assertEquals(adds.size(),found.size());
        }
        for(OctAddress add: addresses) {
            out("ThreeBox Search for add:" + add);
            ThreeBox<OctAddress> threeBox = new ThreeBox<>(octree,add);
            out(threeBox);
            OctAddress found = threeBox.getBoxMap().get(add).get(0);
            assertEquals(add,found);
        }
    }


    public void testFindNeighbors() {
    }
}