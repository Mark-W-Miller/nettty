package com.moondance.nettty.utils.octree;

public interface OctMember {
    String shortHand();
    boolean isIn3Box();
    void setIn3Box(boolean in3Box);
    boolean isKill();
}
