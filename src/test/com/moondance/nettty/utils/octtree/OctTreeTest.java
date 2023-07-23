package com.moondance.nettty.utils.octtree;

import junit.framework.TestCase;

public class OctTreeTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() {
    }

    public void testAdd() {
        OctTree<OctAddress> octTree = new OctTree(4);
        OctAddress address = new OctAddress(0,0,0,1);
        AddressedData<OctAddress> addressedData = new AddressedData<>(address,address);
        octTree.add(address,address);
        address = new OctAddress(1,0,1,2);
    }

    public void testFindNeighbors() {
    }
}