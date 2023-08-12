package com.moondance.nettty.model.rulesof3;

import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import com.moondance.nettty.utils.octree.ThreeBox;

import static com.moondance.nettty.utils.DB.DB_WRITE_RULE;
import static com.moondance.nettty.utils.Handy.out;

public class WriteStateRule extends Rule3 implements RuleOfThree {
    @Override
    public void apply(Nett nett, ThreeBox<Particle> threeBox) {
        super.apply(nett,threeBox);
        out(DB_WRITE_RULE,threeBox.toString());
    }
}
