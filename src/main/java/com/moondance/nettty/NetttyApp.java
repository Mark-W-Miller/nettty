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

package com.moondance.nettty;

import com.moondance.nettty.graphics.NettGroup;

import static com.moondance.nettty.Script.loadNett;
import static com.moondance.nettty.model.Nett.*;

import com.moondance.nettty.model.Nett;
import lombok.SneakyThrows;
import org.jdesktop.j3d.examples.Resources;
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.applet.MainFrame;
import org.jogamp.java3d.utils.behaviors.vp.OrbitBehavior;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.java3d.utils.universe.ViewingPlatform;
import org.jogamp.vecmath.Color3f;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Vector3f;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NetttyApp extends JFrame
        implements ActionListener {


    Material material;
    Appearance app;
    JComboBox altAppMaterialColor;
    JComboBox appMaterialColor;
    JComboBox altAppScoping;
    JComboBox override;
    private NettGroup content = null;
    BoundingSphere worldBounds;
    // Globally used colors
    Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
    Color3f red = new Color3f(1.0f, 0.0f, 0.0f);
    Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
    Color3f blue = new Color3f(0.0f, 0.0f, 1.0f);
    Color3f[] colors = {white, red, green, blue};

    private SimpleUniverse universe;

    public NetttyApp() {
        super("Nettty");
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        this.setSize(new Dimension(1000, 600));
        System.setProperty("sun.awt.noerasebackground", "true");
        Container contentPane = getContentPane();

        Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        contentPane.add("Center", canvas3D);

        configureSimpleUniverse(canvas3D);
        buildUI(contentPane);
        this.setVisible(true);
    }

    private void buildUI(Container contentPane) {
        JPanel p = new JPanel();
        BoxLayout boxlayout = new BoxLayout(p,
                BoxLayout.Y_AXIS);
        p.add(createScopingPanel());
        p.add(createMaterialPanel());
        p.setLayout(boxlayout);

        contentPane.add("South", p);
    }

    private void configureSimpleUniverse(Canvas3D canvas3D) throws IOException {
        universe = new SimpleUniverse(canvas3D);

        // add mouse behaviors to the viewingPlatform
        ViewingPlatform viewingPlatform = universe.getViewingPlatform();

        // This will move the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        viewingPlatform.setNominalViewingTransform();

        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                500.0);
        orbit.setSchedulingBounds(bounds);
        viewingPlatform.setViewPlatformBehavior(orbit);
        universe.addBranchGraph(createSceneGraph());
    }

    public void destroy() {
        universe.cleanup();
    }

    BranchGroup createSceneGraph() throws IOException {
        Images.initTextures(this);
        BranchGroup objRoot = new BranchGroup();

        // Create influencing bounds
        worldBounds = new BoundingSphere(
                new Point3d(0.0, 0.0, 0.0),  // Center
                1000.0);                      // Extent

        Transform3D t = new Transform3D();
        // move the object upwards
        t.set(new Vector3f(0.0f, 0.1f, 0.0f));
        // Shrink the object
        t.setScale(0.8);

        TransformGroup trans = new TransformGroup(t);
        trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        trans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        Appearance app1 = new Appearance();
        material = new Material();
        material.setCapability(Material.ALLOW_COMPONENT_WRITE);
        material.setDiffuseColor(new Color3f(1.0f, 0.0f, 0.0f));
        app1.setMaterial(material);
        Nett nett = loadNett("NetttyTest.yaml") ;
        content = new NettGroup(nett);
        trans.addChild(content);

        addLights(objRoot);
        objRoot.addChild(trans);

        return objRoot;
    }

    private void addLights(BranchGroup objRoot) {
        // Add lights
        DirectionalLight light1 = null;
        light1 = new DirectionalLight();
        light1.setEnable(true);
        light1.setColor(new Color3f(0.2f, 0.2f, 0.2f));
        light1.setDirection(new Vector3f(1.0f, 0.0f, -1.0f));
        light1.setInfluencingBounds(worldBounds);
        objRoot.addChild(light1);

        DirectionalLight light2 = new DirectionalLight();
        light2.setEnable(true);
        light2.setColor(new Color3f(0.2f, 0.2f, 0.2f));
        light2.setDirection(new Vector3f(-1.0f, 0.0f, 1.0f));
        light2.setInfluencingBounds(worldBounds);
        objRoot.addChild(light2);

        // Add an ambient light to dimly illuminate the rest of
        // the shapes in the scene to help illustrate that the
        // directional lights are being scoped... otherwise it looks
        // like we're just removing shapes from the scene
        AmbientLight ambient = new AmbientLight();
        ambient.setEnable(true);
        ambient.setColor(new Color3f(1.0f, 1.0f, 1.0f));
        ambient.setInfluencingBounds(worldBounds);
        objRoot.addChild(ambient);
    }

    JPanel createScopingPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Scopes"));

        String[] values = {"Scoped Set1", "Scoped Set2", "Universal Scope"};
        altAppScoping = new JComboBox(values);
        altAppScoping.addActionListener(this);
        altAppScoping.setSelectedIndex(2);
        panel.add(new JLabel("Scoping"));
        panel.add(altAppScoping);


        String[] enables = {"Enabled Set1", "Enabled Set2", "Enabled set1&2", "Disabled set1&2"};

        override = new JComboBox(enables);
        override.addActionListener(this);
        override.setSelectedIndex(3);
        panel.add(new JLabel("Alternate Appearance Override"));
        panel.add(override);

        return panel;

    }

    JPanel createMaterialPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Appearance Attributes"));

        String[] colorVals = {"WHITE", "RED", "GREEN", "BLUE"};

        altAppMaterialColor = new JComboBox(colorVals);
        altAppMaterialColor.addActionListener(this);
        altAppMaterialColor.setSelectedIndex(2);
        panel.add(new JLabel("Alternate Appearance MaterialColor"));
        panel.add(altAppMaterialColor);


        appMaterialColor = new JComboBox(colorVals);
        appMaterialColor.addActionListener(this);
        appMaterialColor.setSelectedIndex(1);
        panel.add(new JLabel("Normal Appearance MaterialColor"));
        panel.add(appMaterialColor);

        return panel;


    }

    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if (target == altAppMaterialColor) {
        } else if (target == altAppScoping) {

        } else if (target == override) {
            int i;
            if (override.getSelectedIndex() == 0) {
            } else if (override.getSelectedIndex() == 1) {
            } else if (override.getSelectedIndex() == 2) {
            } else {
            }

        } else if (target == appMaterialColor) {
            material.setDiffuseColor(colors[appMaterialColor.getSelectedIndex()]);
        }

    }


    public static void main(String[] args) {
        System.setProperty("sun.awt.noerasebackground", "true");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NetttyApp();
            }
        });
    }

}			   
