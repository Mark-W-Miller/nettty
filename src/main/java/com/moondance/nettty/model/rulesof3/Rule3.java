package com.moondance.nettty.model.rulesof3;

import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import com.moondance.nettty.utils.octree.ThreeBox;

import static com.moondance.nettty.utils.DB.DB_WRITE_RULE_TRACE;
import static com.moondance.nettty.utils.Handy.out;

public class Rule3 implements RuleOfThree {
    @Override
    public void apply(Nett nett, ThreeBox<Particle> threeBox) {
        out(DB_WRITE_RULE_TRACE,threeBox.toString());
    }
}
