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
import com.moondance.nettty.model.Spin;
import org.jdesktop.j3d.examples.Resources;
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.Primitive;
import org.jogamp.java3d.utils.geometry.Sphere;
import org.jogamp.java3d.utils.image.TextureLoader;
import org.jogamp.java3d.utils.shader.StringIO;
import org.jogamp.vecmath.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ParticleGroup
        extends Group {

    public ParticleGroup(Particle particle, Appearance app) {
        if (app == null) {
            app = new Appearance();
            Material material = new Material();
            material.setDiffuseColor(new Color3f(0.8f, 0.8f, 0.8f));
            material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f));
            material.setShininess(0.0f);
            app.setMaterial(material);
            TransparencyAttributes opacity = new TransparencyAttributes();
            opacity.setTransparencyMode(TransparencyAttributes.NICEST);
            opacity.setTransparency(0.4f);
            app.setTransparencyAttributes(opacity);
        }
        BoundingSphere bounds =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

        Sphere sphere;
        TransformGroup trans;
        Vector3d vec = new Vector3d();
        vec.set(particle.getPosition());
        Transform3D t3d = new Transform3D();
        t3d.setTranslation(vec);
        for (Spin spin : particle.getSpins()) {
            trans = new TransformGroup(t3d);
            addChild(trans);

            sphere = makeSpinSphere(app, spin);

            TransformGroup rotatorTransform = new TransformGroup();
            rotatorTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            Transform3D yAxis = new Transform3D();
            AxisAngle4d aa = new AxisAngle4d(spin.getRotationAxis(),0*Math.PI);
            yAxis.setRotation(aa);
            Alpha rotor1Alpha = new Alpha(-1, Alpha.INCREASING_ENABLE,
                    0, 0,
                    20000, 0, 0,
                    0, 0, 0);
            RotationInterpolator rotator1 =
                    new RotationInterpolator(rotor1Alpha,
                            rotatorTransform,
                            yAxis,
                            0.0f, (float) Math.PI * 2.0f);
            rotator1.setSchedulingBounds(bounds);
            rotatorTransform.addChild(rotator1);
            trans.addChild(rotatorTransform);

            rotatorTransform.addChild(sphere);
        }
    }

    private static Sphere makeSpinSphere(Appearance app, Spin spin) {
        Sphere sphere;
        sphere = new Sphere(
                spin.getShell(),     // sphere radius
                Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS,  // generate normals
                16,         // 16 divisions radially
                app);      // it's appearance
        sphere.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        return sphere;
    }
}
