/*
 * $RCSfile$
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistribution of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL,
 * CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND
 * REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR
 * INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 *
 * $Revision$
 * $Date$
 * $State$
 */

package com.moondance.nettty.graphics;

import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import org.jogamp.java3d.*;
import org.jogamp.vecmath.Vector3d;

import static com.moondance.nettty.graphics.Appearences.makeSpinningTexture;
import static com.moondance.nettty.utils.VecUtils.ORIGIN;

public class NettGroup
        extends BranchGroup {
    Nett nett ;
    public NettGroup(Nett nett) {
        this.nett = nett ;
        nett.setNettGroup(this);
        TransformGroup trans ;
        Vector3d vec = new Vector3d();
        vec.set(ORIGIN);
        Transform3D t3d = new Transform3D();
        t3d.setTranslation(vec);
        setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
        setCapability(BranchGroup.ALLOW_DETACH );
        setCapability( BranchGroup.ALLOW_CHILDREN_WRITE);
        Appearance shaderApp = makeSpinningTexture(Images.getSpinTextureEarth());
        for (Particle particle: nett.getParticles()) {
            particle.setNett(nett);
            BranchGroup removeHolder = new BranchGroup();
            removeHolder.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
            removeHolder.setCapability(BranchGroup.ALLOW_DETACH);
            addChild(removeHolder);
            trans = new TransformGroup(t3d);
            removeHolder.addChild(trans);
            particle.setCurrentParticleTransform(trans);

            ParticleGroup particleGroup = new ParticleGroup(particle,shaderApp);
            particle.setNettGroup(this);
            particle.setRemoveHolder(removeHolder);
            particleGroup.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
            trans.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
            trans.addChild(particleGroup);
        }
    }

    public void removeParticleModels(Particle particle){
        particle.getNettGroup().removeChild(particle.getRemoveHolder());
    }

}
