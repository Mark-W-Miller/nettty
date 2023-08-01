package com.moondance.nettty.utils.octree;

import junit.framework.TestCase;

public class SubNodeTest extends TestCase {

    public void testFindSubNode() {
        OctAddress origin = new OctAddress();
        assertEquals(SubNode.findSubNode(origin,origin),SubNode.UP_NE);
        for(SubNode sn: SubNode.values()){
            assertEquals(sn,SubNode.findSubNode(origin,new OctAddress(sn.offset,1)));
        }
    }
}