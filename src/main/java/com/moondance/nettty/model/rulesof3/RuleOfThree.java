package com.moondance.nettty.model.rulesof3;

import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import com.moondance.nettty.utils.octree.ThreeBox;

public interface RuleOfThree {

    void apply(Nett nett, ThreeBox<Particle> threeBox);
}
