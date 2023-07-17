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

package org.jdesktop.j3d.examples.jcanvas3d;


/**
 * Simple Java 3D example program that displays universes within lightweight swing components, layed in JInternalFrame objects.
 */
import java.awt.Toolkit;

public class JCanvas3DExample extends javax.swing.JFrame implements java.awt.event.ActionListener
{
    
    /**
     * Creates new form JCanvas3DExample
     */
    public JCanvas3DExample()
    {
        initComponents();
        Toolkit.getDefaultToolkit().setDynamicLayout( true );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        splitPane = new javax.swing.JSplitPane();
        scrollPane = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        delayCheckBox = new javax.swing.JCheckBox();
        interactiveCheckBox = new javax.swing.JCheckBox();
        randomCheckBox = new javax.swing.JCheckBox();
        desktopPane = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        splitPane.setDividerLocation(300);
        splitPane.setDividerSize(8);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);
        panel.setLayout(new java.awt.GridBagLayout());

        addButton.setText("Create New Frame");
        addButton.setToolTipText("Adds a new frame containing an universe into the desktop pane");
        addButton.addActionListener(this);

        panel.add(addButton, new java.awt.GridBagConstraints());

        delayCheckBox.setText("Resize Delayed");
        delayCheckBox.setToolTipText("Shows the effect of using a delayed resizing to the internal frames.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panel.add(delayCheckBox, gridBagConstraints);

        interactiveCheckBox.setSelected(true);
        interactiveCheckBox.setText("Interactive Cube");
        interactiveCheckBox.setToolTipText("Tests the use of AWT behaviors on the displayed component.");
        interactiveCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        interactiveCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        interactiveCheckBox.addActionListener(this);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panel.add(interactiveCheckBox, gridBagConstraints);

        randomCheckBox.setText("Random start angle");
        randomCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        randomCheckBox.setEnabled(false);
        randomCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panel.add(randomCheckBox, gridBagConstraints);

        scrollPane.setViewportView(panel);

        splitPane.setLeftComponent(scrollPane);

        desktopPane.setBackground(new java.awt.Color(153, 153, 201));
        desktopPane.setPreferredSize(new java.awt.Dimension(300, 300));
        splitPane.setRightComponent(desktopPane);

        getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-1011)/2, (screenSize.height-733)/2, 1011, 733);
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == addButton) {
            JCanvas3DExample.this.addButtonActionPerformed(evt);
        }
        else if (evt.getSource() == interactiveCheckBox) {
            JCanvas3DExample.this.interactiveCheckBoxActionPerformed(evt);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void interactiveCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interactiveCheckBoxActionPerformed
        randomCheckBox.setEnabled( interactiveCheckBox.isSelected() ? false:true );
    }//GEN-LAST:event_interactiveCheckBoxActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        JInternalWorld iWorld;
        // we create an internal world to be added within the JDesktop.
        iWorld = new JInternalWorld( interactiveCheckBox.isSelected(),
                delayCheckBox.isSelected(),
                randomCheckBox.isSelected() );
        iWorld.setSize( 256, 256 );
        iWorld.setLocation( 50, 50 );
        iWorld.setResizable( true );
        desktopPane.add( iWorld );
        iWorld.setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new JCanvas3DExample().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JCheckBox delayCheckBox;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JCheckBox interactiveCheckBox;
    private javax.swing.JPanel panel;
    private javax.swing.JCheckBox randomCheckBox;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JSplitPane splitPane;
    // End of variables declaration//GEN-END:variables
    
}
