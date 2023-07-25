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
import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import com.moondance.nettty.utils.octtree.AddressedData;
import com.moondance.nettty.utils.octtree.OctTree;
import com.moondance.nettty.utils.octtree.SubNode;
import lombok.SneakyThrows;
import org.jogamp.java3d.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.moondance.nettty.Script.loadNett;
import static com.moondance.nettty.graphics.GraphicsUtils.makeAxis;
import static com.moondance.nettty.graphics.GraphicsUtils.makeOctTreeGroup;
import static com.moondance.nettty.model.Nett.Nettty;
import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.VecUtils.randomize;

public class NetttyApp extends JFrame
        implements ActionListener {
    TransformGroup mainTransform;
    OctTree<Particle> particleOctTree;
    BranchGroup contentBranchGroup;
    Appearance app;
    JButton reloadScript;
    JButton saveScript;
    JButton GodPulse;
    JButton runNettty;
    JButton stopNettty;
    JTextField numberOfPulsesPerFrame;
    JTextField numberOfFrames;
    JComboBox appMaterialColor;
    JComboBox altAppScoping;
    JComboBox override;
    private NettGroup content = null;
    private Group octTreeGroup = null;
    BoundingSphere worldBounds;
    // Globally used colors
    Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
    Color3f red = new Color3f(1.0f, 0.0f, 0.0f);
    Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
    Color3f blue = new Color3f(0.0f, 0.0f, 1.0f);
    Color3f[] colors = {white, red, green, blue};
    static String CURRENT_SCRIPT = "TwoParticlesNear.yaml";

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
//        p.add(createScopingPanel());
        p.add(createMaterialPanel());
        p.setLayout(boxlayout);

        contentPane.add("South", p);
    }

    private void configureSimpleUniverse(Canvas3D canvas3D) throws IOException {
        universe = new SimpleUniverse(canvas3D);

        // add mouse behaviors to the viewingPlatform
        ViewingPlatform viewingPlatform = universe.getViewingPlatform();

//        // This will move the ViewPlatform back a bit so the
//        // objects in the scene can be viewed.
//        viewingPlatform.setNominalViewingTransform();

        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ALL);
        orbit.goHome();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                10500.0);
        orbit.setSchedulingBounds(bounds);
        orbit.setZoomFactor(50);
        orbit.setTransFactors(20, 20);
        viewingPlatform.setViewPlatformBehavior(orbit);
        viewingPlatform.setNominalViewingTransform();
        universe.addBranchGraph(createSceneGraph());
        View view = universe.getViewer().getView();
        view.setBackClipDistance(100000);
    }

    public void destroy() {
        universe.cleanup();
    }

    BranchGroup createSceneGraph() throws IOException {
        if (contentBranchGroup != null) {
            contentBranchGroup.detach();
        }
        Images.initTextures(this);
        BranchGroup objRoot = new BranchGroup();
        objRoot.setCapability(BranchGroup.ALLOW_DETACH);
        // Create influencing bounds
        worldBounds = new BoundingSphere(
                new Point3d(0.0, 0.0, 0.0),  // Center
                1000.0);                      // Extent


        Nett nettTemplate = loadNett(CURRENT_SCRIPT);
        particleOctTree = makeParticleLadenOctTreeFromTemplate(nettTemplate);
        Nett nett = new Nett(particleOctTree.getAllData());
        particleOctTree = makeParticleOctTree(nett);

        Transform3D t = new Transform3D();
        t.set(new Vector3f(0.0f, 0.1f, 0.0f));
        t.setScale(0.8);
        mainTransform = new TransformGroup(t);
        mainTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        mainTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        mainTransform.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        mainTransform.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        mainTransform.addChild(makeAxis());
        mainTransform.addChild(octTreeGroup = makeOctTreeGroup(particleOctTree));
        mainTransform.addChild(content = new NettGroup(nett));
        addLights(objRoot);
        objRoot.addChild(mainTransform);
        contentBranchGroup = objRoot;
        return objRoot;
    }

    private void rebuildParticleOctTree() {
        particleOctTree = makeParticleOctTree(Nettty);
        mainTransform.removeChild(octTreeGroup);
        mainTransform.addChild(octTreeGroup = makeOctTreeGroup(particleOctTree));
    }

    OctTree<Particle> makeParticleLadenOctTreeFromTemplate(Nett nettty) {
        List<AddressedData> data = new ArrayList<>();

        for (Particle particleTemplate : nettty.getParticles()) {

            for (int ix = 0; ix < 1; ix++) {

                if (particleTemplate.getNumCopiesInitial() > 1) {

                    int particlesRemaining = particleTemplate.getNumCopiesInitial();
                    for (SubNode sn : SubNode.values()) {

                        double scale = 30 * Math.random();
                        Particle particle = particleTemplate.clone();
                        randomize(particle.getPosition(), 0.5d);
                        particle.getPosition().scale(scale);
                        data.add(particle.makeAddressableData());
                        particlesRemaining--;
                        if (particlesRemaining < 1) {
                            break;
                        }
                    }
                } else {

                    Particle particle = particleTemplate.clone();
                    randomize(particle.getPosition(), 0.5d);
                    data.add(particle.makeAddressableData());
                }
            }
        }
        out(data);
        double finalMax = maxDimension(nettty);
        out("Octree Size Initial:" + finalMax);
        OctTree<Particle> octTree = new OctTree<>((int) finalMax * 2);
        for (AddressedData addressedParticle : data) {
            octTree.add(addressedParticle);
        }
        return octTree;
    }

    OctTree<Particle> makeParticleOctTree(Nett nettty) {
        double finalMax = maxDimension(nettty);
        out("Octree Size:" + finalMax);
        OctTree<Particle> octTree = new OctTree<>((int) finalMax * 2);
        List<AddressedData> data = new ArrayList<>();

        for (Particle particle : nettty.getParticles()) {
            data.add(particle.makeAddressableData());
        }
//        out(data);
        for (AddressedData addressedParticle : data) {
            octTree.add(addressedParticle);
        }
        return octTree;
    }

    private static double maxDimension(Nett nettty) {
        Point3d max = new Point3d();
        for (Particle particle : nettty.getParticles()) {
            max.x = Math.max(max.x, Math.abs(particle.getPosition().getX()));
            max.y = Math.max(max.y, Math.abs(particle.getPosition().getY()));
            max.z = Math.max(max.z, Math.abs(particle.getPosition().getZ()));
        }
        double finalMax = Math.max(max.x, Math.max(max.y, max.z)) * 1.1;
        return finalMax;
    }

    private void reloadScript() throws IOException {
        Nett nett = loadNett(CURRENT_SCRIPT);
        content = new NettGroup(nett);
        universe.addBranchGraph(createSceneGraph());
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
        reloadScript = new JButton("Reload Script:" + CURRENT_SCRIPT);
        reloadScript.addActionListener(this);
        saveScript = new JButton("Save Script:" + CURRENT_SCRIPT);
        saveScript.addActionListener(this);
        GodPulse = new JButton("God Pulse:");
        GodPulse.addActionListener(this);
        runNettty = new JButton("Run");
        runNettty.addActionListener(this);
        stopNettty = new JButton("Stop");
        stopNettty.addActionListener(this);
        numberOfPulsesPerFrame = new JTextField("1", 4);
        numberOfFrames = new JTextField("1", 4);
        panel.add(reloadScript);
        panel.add(saveScript);
        panel.add(GodPulse);
        panel.add(new JLabel("#Per Frame"));
        panel.add(numberOfPulsesPerFrame);
        panel.add(new JLabel("# Cycles"));
        panel.add(numberOfFrames);
        panel.add(runNettty);
        panel.add(stopNettty);

        appMaterialColor = new JComboBox(colorVals);
        appMaterialColor.addActionListener(this);
        appMaterialColor.setSelectedIndex(1);
        panel.add(new JLabel("Normal Appearance MaterialColor"));
        panel.add(appMaterialColor);

        return panel;


    }
    static private boolean keepRunning = true;

    @SneakyThrows
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if (target == reloadScript) {
            try {
                reloadScript();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (target == GodPulse) {
            int numFrames = getIntFromTextField(numberOfFrames);
            int pulsesPerFrame = getIntFromTextField(numberOfPulsesPerFrame);
            Nettty.GodPulse(pulsesPerFrame);
            Nettty.updateTransforms();
            rebuildParticleOctTree();
        } else if (target == runNettty) {
            int numFrames = getIntFromTextField(numberOfFrames);
            int pulsesPerFrame = getIntFromTextField(numberOfPulsesPerFrame);
            keepRunning = true ;
            new Thread(() -> {
                for (int frames = 0; frames < numFrames; frames++) {
                    if(!keepRunning){
                        break ;
                    }
                    Nettty.GodPulse(pulsesPerFrame);
                    Nettty.updateTransforms();
                    rebuildParticleOctTree();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }).start();
            out("Spawned thread");
        } else if(target == stopNettty){
            keepRunning = false ;
        }
    }

    private int getIntFromTextField(JTextField textField) {
        return Integer.parseInt(textField.getText());
    }

    public static void main(String[] args) {
        out("args:" + Arrays.toString(args));
        System.setProperty("sun.awt.noerasebackground", "true");
        if (args.length > 0) {
            CURRENT_SCRIPT = args[0];
            out("CURRENT_SCRIPT:" + CURRENT_SCRIPT);
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NetttyApp();
            }
        });
    }

}			   
