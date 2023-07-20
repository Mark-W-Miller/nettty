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

import com.moondance.nettty.Images;
import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.image.TextureLoader;
import org.jogamp.java3d.utils.shader.StringIO;
import org.jogamp.vecmath.Color3f;
import org.jogamp.vecmath.Color4f;
import org.jogamp.vecmath.Vector3d;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.moondance.nettty.utils.VecUtils.ORIGIN;

public class NettGroup
        extends Group {

    public NettGroup(Nett nett) {

        TransformGroup trans ;
        Vector3d vec = new Vector3d();
        vec.set(ORIGIN);
        Transform3D t3d = new Transform3D();
        t3d.setTranslation(vec);
        Appearance shaderApp = makeShaderAppearance();
        for (Particle particle: nett.getParticles()) {
            trans = new TransformGroup(t3d);
            addChild(trans);

            ParticleGroup particleGroup = new ParticleGroup(particle,shaderApp);
            particleGroup.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
            trans.addChild(particleGroup);
        }
    }

    static Color3f eColor = new Color3f(0.0f, 0.0f, 0.0f);
    static Color3f sColor = new Color3f(1.0f, 1.0f, 1.0f);
    static Color3f objColor = new Color3f(0.6f, 0.6f, 0.6f);

    public static Appearance makeShaderAppearance(){
//        TextureLoader loader = new TextureLoader("K:\\3d\\Arizona.jpg",
//                "LUMINANCE", new Container());
        Texture texture = Images.getSpinTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));

        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        Appearance app = new Appearance();
        app.setTexture(texture);
        app.setTextureAttributes(texAttr);

        //set up the material
        app.setMaterial(new Material(objColor, eColor, objColor, sColor, 1.0f));


        TransparencyAttributes opacity = new TransparencyAttributes();
        opacity.setTransparencyMode(TransparencyAttributes.NICEST);
        opacity.setTransparency(0.4f);
        app.setTransparencyAttributes(opacity);
        return app ;
    }
}
