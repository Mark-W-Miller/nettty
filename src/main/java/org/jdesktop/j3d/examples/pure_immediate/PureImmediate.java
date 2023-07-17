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

package org.jdesktop.j3d.examples.pure_immediate;

import java.awt.GraphicsConfiguration;

import org.jogamp.java3d.Alpha;
import org.jogamp.java3d.Appearance;
import org.jogamp.java3d.BranchGroup;
import org.jogamp.java3d.Canvas3D;
import org.jogamp.java3d.Geometry;
import org.jogamp.java3d.GraphicsContext3D;
import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.utils.geometry.ColorCube;
import org.jogamp.java3d.utils.universe.SimpleUniverse;

/**
 * Pure immediate mode example program.  In pure immediate mode, the
 * renderer must be stopped on the Canvas being rendered into. In our
 * example, this is done immediately after the canvas is created. A
 * separate thread is started up to do the immediate mode rendering.
 */
public class PureImmediate extends javax.swing.JFrame implements Runnable {


    private SimpleUniverse univ = null;
    private BranchGroup scene = null;

    private Canvas3D canvas;
    private GraphicsContext3D gc = null;
    private Geometry cube = null;
    private Transform3D cmt = new Transform3D();

    // One rotation (2*PI radians) every 6 seconds
    private Alpha rotAlpha = new Alpha(-1, 6000);

    //
    // Renders a single frame by clearing the canvas, drawing the
    // geometry, and swapping the draw and display buffer.
    //
    public void render() {
	if (gc == null) {
	    // Set up Graphics context
	    gc = canvas.getGraphicsContext3D();
	    gc.setAppearance(new Appearance());

	    // Set up geometry
	    cube = new ColorCube(0.4).getGeometry();
	}

	// Compute angle of rotation based on alpha value
	double angle = rotAlpha.value() * 2.0*Math.PI;
	cmt.rotY(angle);
 
	// Render the geometry for this frame
	gc.clear();
	gc.setModelTransform(cmt);
	gc.draw(cube);
	canvas.swap();
    }

    //
    // Run method for our immediate mode rendering thread.
    //
    public void run() {
	System.out.println("PureImmediate.run: starting main loop");
	while (true) {
	    render();
	    Thread.yield();
	}
    }


    private void createUniverse() {
	// Get the preferred graphics configuration for the default screen
	GraphicsConfiguration config =
	    SimpleUniverse.getPreferredConfiguration();

	// Create a Canvas3D using the preferred configuration
	canvas = new Canvas3D(config);
        canvas.stopRenderer();
	// Create simple universe with view branch
	univ = new SimpleUniverse(canvas);

	// This will move the ViewPlatform back a bit so the
	// objects in the scene can be viewed.
	univ.getViewingPlatform().setNominalViewingTransform();

	// Ensure at least 5 msec per frame (i.e., < 200Hz)
	univ.getViewer().getView().setMinimumFrameCycleTime(5);
    }

    /**
     * Creates new form PureImmediate
     */
    public PureImmediate() {
	// Initialize the GUI components
	initComponents();

	// Create Canvas3D and SimpleUniverse; add canvas to drawing panel
	createUniverse();
	drawingPanel.add(canvas, java.awt.BorderLayout.CENTER);
        
        // Start a new thread that will continuously render
	new Thread(this).start();
    }

    // ----------------------------------------------------------------
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        drawingPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PureImmediate");
        drawingPanel.setLayout(new java.awt.BorderLayout());

        drawingPanel.setPreferredSize(new java.awt.Dimension(250, 250));
        getContentPane().add(drawingPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {System.setProperty("sun.awt.noerasebackground", "true"); 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PureImmediate().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel drawingPanel;
    // End of variables declaration//GEN-END:variables
    
}
