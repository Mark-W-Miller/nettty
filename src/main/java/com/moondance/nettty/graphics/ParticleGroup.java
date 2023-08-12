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

import static com.moondance.nettty.graphics.Appearences.*;
import static com.moondance.nettty.graphics.GraphicsUtils.makeAxisAt;
import static com.moondance.nettty.utils.DB.DB_RULE;
import static com.moondance.nettty.utils.DB.DB_RULE_TRACE;
import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.VecUtils.makeRotationGroup;
import static com.moondance.nettty.utils.VecUtils.makeTranslationGroup;

public class ParticleGroup  extends BranchGroup {
    Particle particle ;
    public ParticleGroup(Particle particle, Appearance appearence) {
        this.particle = particle;
        particle.setParticleGroup(this);
        if (appearence == null) {
            appearence = getDefaultSpinAppearance();
        }
        if(particle.isSentinel()){
            appearence = makeSpinningTexture(Images.getSpinTextureRock());
        }
        BoundingSphere bounds =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 10000.0);

        setCapability(BranchGroup.ALLOW_DETACH );
        Vector3d vec = new Vector3d();
        vec.set(particle.getPosition());
        TransformGroup trans = makeTranslationGroup(vec);
        trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        particle.setCurrentParticleTransform(trans);
        addChild(trans);
        BranchGroup localAxis = makeAxisAt(new Point3d(),((float)particle.maxShell())/2);
        trans.addChild(localAxis);
        for (Spin spin : particle.getSpins()) {

            spin.setParticle(particle);
            TransformGroup cylinderXForm = makeSpinAxis(getDefaultSpinAppearance(), spin);
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

            TransformGroup rotatorTransformGroup = new TransformGroup();
            rotatorTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            rotatorTransformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
            float min = 0.0f ;
            float max = (float) (Math.PI * 2.0f);
            if(spin.getRotationAngle() < 0){
                min = (float) (Math.PI * 2.0f) ;
                max =  0.0f  ;
            }
            RotationInterpolator rotator = new RotationInterpolator(rotor1Alpha, rotatorTransformGroup, yAxis, min, max);
            rotator.setSchedulingBounds(bounds);
            rotatorTransformGroup.addChild(rotator);
            trans.addChild(rotatorTransformGroup);
            rotatorTransformGroup.addChild(cylinderXForm);

            BranchGroup sphereHolderGroup = new BranchGroup() ;
            spin.setSpinSphereHolderGroup(sphereHolderGroup);
            rotatorTransformGroup.addChild(sphereHolderGroup);
            sphereHolderGroup.setUserData("sphereHolderGroup");

            sphereHolderGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
            sphereHolderGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
            sphereHolderGroup.setCapability(BranchGroup.ALLOW_DETACH);

            BranchGroup sphereGroup = new BranchGroup() ;
            sphereGroup.setUserData("sphereGroup");
            sphereGroup.setCapability(BranchGroup.ALLOW_DETACH);
            sphereGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
            spin.setSpinSphereGroup(sphereGroup);

            sphereHolderGroup.addChild(sphereGroup);

            Sphere sphere = makeSpinSphere(appearence, spin);
            sphereGroup.addChild(sphere);
            sphere.setCapability(BranchGroup.ALLOW_DETACH);


            spin.setCurrentSpinTransform(rotatorTransformGroup);
            spin.setRotationAlpha(rotor1Alpha);
            spin.setRotator(rotator);
        }
    }

    public static Sphere makeSpinSphere(Appearance app, Spin spin) {
        out(DB_RULE_TRACE,"makeSpinSphere:" + spin);
        Sphere sphere;
        sphere = new Sphere(
                ((float)spin.getShell())/2,     // sphere radius
                Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS,  // generate normals
                16,         // 16 divisions radially
                app);      // it's appearance
        sphere.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        sphere.setCapability(Shape3D.ALLOW_BOUNDS_WRITE);
        sphere.setUserData(app);
        return sphere;
    }


    private TransformGroup makeFixedSpinAxis(Appearance appearance, Spin spin) {
        TransformGroup group = makeRotationGroup( spin.getRotationAxis(),spin.getRotationAngle());
        Cylinder cylinder = new Cylinder(0.05f, spin.getShell(), Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE,10 , 10, appearance);
        cylinder.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.addChild(cylinder);
        return group ;
    }

    private static TransformGroup makeSpinAxis(Appearance app, Spin spin) {
        TransformGroup group = makeTranslationGroup(new Vector3d(0,0,0));
        TransformGroup cylinderXForm = makeTranslationGroup(new Vector3d(0,((float) spin.getShell())/4,0));
        group.addChild(cylinderXForm);
        Cylinder cylinder = new Cylinder(0.1f * spin.getShell()/2, ((float)spin.getShell())/2, Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE,10 , 10, app);
        cylinder.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        cylinderXForm.addChild(cylinder);

        TransformGroup ballXForm = makeTranslationGroup(new Vector3d(0,((float)spin.getShell())/2,0));
        group.addChild(ballXForm);

        Sphere sphere = new Sphere(
                0.1f * ((float)spin.getShell())/2,     // sphere radius
                Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS,  // generate normals
                8,         // 16 divisions radially
                app);      // it's appearance
        ballXForm.addChild(sphere);
        return group;
    }


}
