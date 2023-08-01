package com.moondance.nettty.scripts.writers;

import com.moondance.nettty.model.SpinSignature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jogamp.vecmath.Point3d;

import static com.moondance.nettty.utils.VecUtils.simpleTupleStr;

@Getter
@Setter
@AllArgsConstructor
public class ParticleOut {
    String idRef ;

    public static ParticleOut makeSentinel(Point3d center, SpinSignature spinSignature) {
        return new ParticleOut(spinSignature + "(" + simpleTupleStr(center) + ")");
    }
}
