package com.moondance.nettty.utils.octtree;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.Handy.tabs;
import static com.moondance.nettty.utils.octtree.OctTree.dumpTree;

public class OctTreeTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() {
    }

    public void testAdd() {
        OctTree<OctAddress> octTree = new OctTree<>(64);
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
            octTree.add(add,add);
            dumpTree(octTree);
        }
        OctAddress address = new OctAddress(0,0,0,1);
    }


    public void testFindNeighbors() {
    }
}