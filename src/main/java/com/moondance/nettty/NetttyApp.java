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

import com.moondance.nettty.graphics.Images;
import com.moondance.nettty.graphics.NettGroup;
import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import com.moondance.nettty.scripts.Script;
import com.moondance.nettty.utils.octree.AddressedData;
import com.moondance.nettty.utils.octree.Octree;
import lombok.SneakyThrows;
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.behaviors.vp.OrbitBehavior;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.java3d.utils.universe.ViewingPlatform;
import org.jogamp.vecmath.Color3f;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Vector3d;
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

import static com.moondance.nettty.scripts.Script.loadNett;
import static com.moondance.nettty.graphics.GraphicsUtils.*;
import static com.moondance.nettty.model.Nett.Nettty;
import static com.moondance.nettty.utils.DB.*;
import static com.moondance.nettty.utils.Handy.formatList;
import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.VecUtils.cast;
import static com.moondance.nettty.utils.octree.Octree.dumpTree;

public class NetttyApp extends JFrame
        implements ActionListener {
    TransformGroup mainTransform;
    Octree<Particle> particleOctree;
    BranchGroup contentBranchGroup;
    OrbitBehavior orbit;
    double orbitSensitivity = 10;
    JButton reloadScript;
    JButton GodPulse;
    JButton runNettty;
    JButton stopNettty;
    JButton setHome;
    JButton toggleOctTree;
    JButton goHome;
    JTextField numberOfPulsesPerFrame;
    JTextField numberOfFrames;
    JComboBox scriptFile;
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
    static String CURRENT_REFERENCE = "SentinelDefinitions.yaml";

    List<String> scriptFiles = new ArrayList<>();
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
        this.setSize(new Dimension(1800, 900));
        System.setProperty("sun.awt.noerasebackground", "true");
        Container contentPane = getContentPane();
        scriptFiles = Script.listScriptFiles() ;
        out(DB_SCRIPTS,"Scripts:" + formatList(scriptFiles));
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
        p.add(createControlPanel());
        p.add(createFilePanel());
        p.setLayout(boxlayout);

        contentPane.add("South", p);
    }


    private void configureSimpleUniverse(Canvas3D canvas3D) throws IOException {
        universe = new SimpleUniverse(canvas3D);

        // add mouse behaviors to the viewingPlatform
        ViewingPlatform viewingPlatform = universe.getViewingPlatform();

        orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ALL);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                10500.0);
        orbit.setSchedulingBounds(bounds);
        orbit.setZoomFactor(orbitSensitivity);
        orbit.setTransFactors(orbitSensitivity, orbitSensitivity);
        viewingPlatform.setViewPlatformBehavior(orbit);
        viewingPlatform.setNominalViewingTransform();
        universe.addBranchGraph(createSceneGraph());
        View view = universe.getViewer().getView();
        view.setBackClipDistance(100000);
    }

    public void dumpOrbit(String prefix) {
        Point3d center = new Point3d();
        orbit.getRotationCenter(center);
        Transform3D t3D = new Transform3D();
        Vector3d v3d = new Vector3d();
        t3D.get(v3d);

        orbit.getViewingPlatform().getViewPlatformTransform().getTransform(t3D);
        out(DB_NETTYAPP_ORBIT,prefix + " center:" + tup3dStr(center));
        out(DB_NETTYAPP_ORBIT,prefix + " VP t3D:" + tup3dStr(v3d));
        out(DB_NETTYAPP_ORBIT,prefix + " VP t3D:\n" + t3D);

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


        Nett nettTemplate = loadNett(CURRENT_REFERENCE, CURRENT_SCRIPT);
        particleOctree = makeParticleLadenOctTreeFromTemplate(nettTemplate);
        Nett nett = new Nett(particleOctree);
        particleOctree = makeParticleOctTree(nett);
        dumpTree(particleOctree);

        Transform3D t = new Transform3D();
        t.set(new Vector3f(0.0f, 0.1f, 0.0f));
        t.setScale(0.8);
        mainTransform = new TransformGroup(t);
        mainTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        mainTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        mainTransform.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        mainTransform.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        mainTransform.addChild(makeAxis());
        mainTransform.addChild(content = new NettGroup(nett));
        if(showOctree) {
            mainTransform.addChild(octTreeGroup = makeOctTreeGroup(particleOctree));
        }
        addLights(objRoot);
        objRoot.addChild(mainTransform);
        contentBranchGroup = objRoot;
        return objRoot;
    }

    private void rebuildParticleOctTree() {
        particleOctree = makeParticleOctTree(Nettty);
        dumpTree(particleOctree);
        mainTransform.removeChild(octTreeGroup);
        if (showOctree) {
            mainTransform.addChild(octTreeGroup = makeOctTreeGroup(particleOctree));
            Transform3D t3D = new Transform3D();
            mainTransform.getTransform(t3D);
            out(DB_NETTYAPP_FLOW, "NettyApp rebuildParticleOctTree mainTransform:\n" + t3D);
        }
    }

    Octree<Particle> makeParticleLadenOctTreeFromTemplate(Nett nettty) {
        List<AddressedData> data = new ArrayList<>();

        for (Particle particleTemplate : nettty.getParticles()) {

            if (particleTemplate.getNumCopiesInitial() > 1) {

                int particlesRemaining = particleTemplate.getNumCopiesInitial();
                while(particlesRemaining-- >= 0) {
                    Particle particle = particleTemplate.clone();
                    particle.setPosition(cast(particle.getPosition(), particle.getCast()));
                    data.add(particle.makeAddressableData());
                }
            } else {

                Particle particle = particleTemplate.clone();
                data.add(particle.makeAddressableData());
            }
        }
        out(data);
        double finalMax = maxDimension(data);
        out("Octree Size Initial:" + finalMax);
        Octree<Particle> octree = new Octree<>((int) finalMax * 2);
        for (AddressedData addressedParticle : data) {
            octree.add(addressedParticle);
        }
        octree.verifyTree();
        return octree;
    }

    Octree<Particle> makeParticleOctTree(Nett nettty) {
        double finalMax = maxDimension(nettty);
        out("Octree Size:" + finalMax);
        Octree<Particle> octree = new Octree<>((int) finalMax * 2);
        List<AddressedData> data = new ArrayList<>();

        for (Particle particle : nettty.getParticles()) {
            data.add(particle.makeAddressableData());
        }
//        out(data);
        for (AddressedData addressedParticle : data) {
            octree.add(addressedParticle);
        }
        nettty.setOctree(octree);
        return octree;
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

    private static double maxDimension(List<AddressedData> data) {
        Point3d max = new Point3d();
        for (AddressedData ad : data) {
            max.x = Math.max(max.x, Math.abs(ad.getOctAddress().getAddress().getX()));
            max.y = Math.max(max.y, Math.abs(ad.getOctAddress().getAddress().getY()));
            max.z = Math.max(max.z, Math.abs(ad.getOctAddress().getAddress().getZ()));
        }
        double finalMax = Math.max(max.x, Math.max(max.y, max.z)) * 1.1;
        return finalMax;
    }

    private void reloadScript() throws IOException {
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

    JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Good Pulse"));

        String[] colorVals = {"WHITE", "RED", "GREEN", "BLUE"};
        GodPulse = new JButton("God Pulse:");
        GodPulse.addActionListener(this);
        runNettty = new JButton("Run");
        runNettty.addActionListener(this);
        stopNettty = new JButton("Stop");
        stopNettty.addActionListener(this);
        toggleOctTree = new JButton("Toggle OT");
        toggleOctTree.addActionListener(this);
        numberOfPulsesPerFrame = new JTextField("1", 4);
        numberOfFrames = new JTextField("1000", 4);
        setHome = new JButton("Set Home");
        setHome.addActionListener(this);
        goHome = new JButton("GoHome");
        goHome.addActionListener(this);
        panel.add(GodPulse);
        panel.add(new JLabel("#Per Frame"));
        panel.add(numberOfPulsesPerFrame);
        panel.add(new JLabel("# Cycles"));
        panel.add(numberOfFrames);
        panel.add(runNettty);
        panel.add(stopNettty);
        panel.add(setHome);
        panel.add(goHome);
        panel.add(toggleOctTree);

        return panel;
    }

    JPanel createFilePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Files"));

        reloadScript = new JButton("Reload Script:" + CURRENT_SCRIPT);
        reloadScript.addActionListener(this);

        scriptFile = new JComboBox(scriptFiles.toArray());
        scriptFile.addActionListener(this);
        scriptFile.setSelectedIndex(2);

//        panel.add(new JLabel("Script"));
        panel.add(scriptFile);
        panel.add(reloadScript);
        return panel;
    }

    static private boolean keepRunning = true;
    static private boolean showOctree = true;

    @SneakyThrows
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if (target == reloadScript) {
            try {
                keepRunning = false;
                reloadScript();
                homeTransformation = new Transform3D();
                orbit.getViewingPlatform().getViewPlatformTransform().getTransform(homeTransformation);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (target == scriptFile) {
            keepRunning = false;
            CURRENT_SCRIPT = scriptFiles.get(scriptFile.getSelectedIndex());
            out(DB_SCRIPTS, "New Script File:" + CURRENT_SCRIPT);
            reloadScript.setText("Reload Script:" + CURRENT_SCRIPT);
            reloadScript();
            homeTransformation = new Transform3D();
            orbit.getViewingPlatform().getViewPlatformTransform().getTransform(homeTransformation);
        } else if (target == GodPulse) {
            dumpOrbit("Orbit Before God Pulse");
            int numFrames = getIntFromTextField(numberOfFrames);
            int pulsesPerFrame = getIntFromTextField(numberOfPulsesPerFrame);
            Nettty.GodPulse(pulsesPerFrame);
            rebuildParticleOctTree();
            dumpOrbit("Orbit After God Pulse");
        } else if (target == runNettty) {
            int numFrames = getIntFromTextField(numberOfFrames);
            int pulsesPerFrame = getIntFromTextField(numberOfPulsesPerFrame);
            keepRunning = true;
            new Thread(() -> {
                for (int frames = 0; frames < numFrames; frames++) {
                    if (!keepRunning) {
                        break;
                    }
                    Nettty.GodPulse(pulsesPerFrame);
                    rebuildParticleOctTree();
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        throw new RuntimeException(ex);
//                    }
                }
            }).start();
            out("Spawned thread");
        } else if (target == toggleOctTree) {
            showOctree = !showOctree;
            out("showOctree:" + showOctree);
            rebuildParticleOctTree();
        } else if (target == stopNettty) {
            keepRunning = false;
        } else if (target == setHome) {
            homeTransformation = new Transform3D();
            orbit.getViewingPlatform().getViewPlatformTransform().getTransform(homeTransformation);
        } else if (target == goHome) {
            if (homeTransformation != null) {
                orbit.setHomeTransform(homeTransformation);
                orbit.goHome();
            }
        }
    }

    Transform3D homeTransformation;

    private int getIntFromTextField(JTextField textField) {
        return Integer.parseInt(textField.getText());
    }

    public static void main(String[] args) {
        out("args:" + Arrays.toString(args));
        System.setProperty("sun.awt.noerasebackground", "true");
        if (args.length > 0) {
            CURRENT_SCRIPT = args[0];
        }
        if (args.length > 1) {
            CURRENT_REFERENCE = args[1];
        }
        out("CURRENT_REFERENCE:" + CURRENT_REFERENCE);
        out("CURRENT_SCRIPT:" + CURRENT_SCRIPT);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NetttyApp();
            }
        });
    }

}			   
