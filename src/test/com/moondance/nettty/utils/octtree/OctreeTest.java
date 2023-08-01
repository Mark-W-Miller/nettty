package com.moondance.nettty.utils.octtree;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.octtree.Octree.dumpTree;

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
            dumpTree(octree);
        }
        OctAddress address = new OctAddress(0,0,0,1);
    }


    public void testFindNeighbors() {
    }
}