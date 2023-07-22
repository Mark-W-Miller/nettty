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

import com.moondance.nettty.model.Particle;
import com.moondance.nettty.model.Spin;
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.Cylinder;
import org.jogamp.java3d.utils.geometry.Sphere;
import org.jogamp.vecmath.*;

import static com.moondance.nettty.utils.VecUtils.makeRotationGroup;
import static com.moondance.nettty.utils.VecUtils.makeTranslationGroup;

public class ParticleGroup  extends Group {
    Particle particle ;
    public ParticleGroup(Particle particle, Appearance appearence) {
        this.particle = particle;
        if (appearence == null) {
            appearence = getAppearance();
        }
        BoundingSphere bounds =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

        Sphere sphere;
        TransformGroup trans;
        Vector3d vec = new Vector3d();
        vec.set(particle.getPosition());
        trans = makeTranslationGroup(vec);
        trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        addChild(trans);
        particle.setCurrentParticleTransform(trans);
        for (Spin spin : particle.getSpins()) {

            spin.setParticle(particle);
            sphere = makeSpinSphere(appearence, spin);
            TransformGroup cylinderXForm = makeSpinAxis(getAppearance(), spin);
            TransformGroup fixedXForm = makeFixedSpinAxis(getAppearanceYPointer(), spin);
            spin.setFixedXForm(fixedXForm);
            trans.addChild(fixedXForm);
            Transform3D yAxis = new Transform3D();
            AxisAngle4d aa = new AxisAngle4d(spin.getRotationAxis(),spin.getRotationAngle());
            yAxis.setRotation(aa);
            Alpha rotor1Alpha = new Alpha(spin.getSpinSpeed()==0 ? 0 : -1, Alpha.INCREASING_ENABLE,
                    0, 0,
                    spin.getSpinSpeed(), 0, 0,
                    0, 0, 0);

            TransformGroup rotatorTransform = new TransformGroup();
            rotatorTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            float min = 0.0f ;
            float max = (float) (Math.PI * 2.0f);
            if(spin.getRotationAngle() < 0){
                min = (float) (Math.PI * 2.0f) ;
                max =  0.0f  ;
            }
            RotationInterpolator rotator = new RotationInterpolator(rotor1Alpha, rotatorTransform, yAxis, min, max);
            rotator.setSchedulingBounds(bounds);
            rotatorTransform.addChild(rotator);
            trans.addChild(rotatorTransform);
            rotatorTransform.addChild(sphere);
            rotatorTransform.addChild(cylinderXForm);
            spin.setCurrentSpinTransform(rotatorTransform);
            spin.setRotationAlpha(rotor1Alpha);
            spin.setRotator(rotator);
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
        sphere.setUserData(spin);
        return sphere;
    }


    private TransformGroup makeFixedSpinAxis(Appearance appearance, Spin spin) {
        TransformGroup group = makeRotationGroup( spin.getRotationAxis(),spin.getRotationAngle());
        Cylinder cylinder = new Cylinder(0.05f, 2*spin.getShell(), Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE,10 , 10, appearance);
        cylinder.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.addChild(cylinder);
        return group ;
    }

    private static TransformGroup makeSpinAxis(Appearance app, Spin spin) {
        TransformGroup group = makeTranslationGroup(new Vector3d(0,0,0));
        TransformGroup cylinderXForm = makeTranslationGroup(new Vector3d(0,((float) spin.getShell())/2,0));
        group.addChild(cylinderXForm);
        Cylinder cylinder = new Cylinder(0.1f, spin.getShell(), Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE,10 , 10, app);
        cylinder.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        cylinderXForm.addChild(cylinder);

        TransformGroup ballXForm = makeTranslationGroup(new Vector3d(0,spin.getShell(),0));
        group.addChild(ballXForm);

        Sphere sphere = new Sphere(
                0.2f,     // sphere radius
                Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS,  // generate normals
                8,         // 16 divisions radially
                app);      // it's appearance
        ballXForm.addChild(sphere);
        return group;
    }

    private static Appearance getAppearance() {
        Appearance app;
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
        return app;
    }

    private static Appearance getAppearanceYPointer() {
        Appearance app;
        app = new Appearance();
        Material material = new Material();
        material.setDiffuseColor(new Color3f(1f, 0.1f, 0.9f));
        material.setSpecularColor(new Color3f(0.0f, 1.0f, 0.0f));
        material.setShininess(1.0f);
        app.setMaterial(material);
        TransparencyAttributes opacity = new TransparencyAttributes();
        opacity.setTransparencyMode(TransparencyAttributes.NICEST);
        opacity.setTransparency(0f);
        app.setTransparencyAttributes(opacity);
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(0.6f, 0.3f, 0.0f);
        app.setCapability(app.ALLOW_COLORING_ATTRIBUTES_WRITE);
        return app;
    }

}
