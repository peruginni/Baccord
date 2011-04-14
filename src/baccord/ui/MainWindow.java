/*
 * MainWindow.java
 */

package baccord.ui;

import java.awt.Component;
import java.awt.Dimension;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;

/**
 * The application's main frame.
 */
public class MainWindow extends FrameView {


	public Dimension toolBarItemMinimalSize = new Dimension(80, 40);

    public MainWindow(SingleFrameApplication app) {
        super(app);
        initComponents();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                epicenter = new javax.swing.JPanel();
                toolbar = new javax.swing.JToolBar();
                pickPhotosButton = new javax.swing.JButton();
                sfmButton = new javax.swing.JButton();
                mvsButton = new javax.swing.JButton();
                exportButton = new javax.swing.JButton();
                settingsButton = new javax.swing.JButton();

                epicenter.setName("epicenter"); // NOI18N
                epicenter.setPreferredSize(new java.awt.Dimension(500, 200));

                toolbar.setRollover(true);
                toolbar.setName("toolbar"); // NOI18N
                toolbar.setPreferredSize(new java.awt.Dimension(259, 40));

                org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(baccord.BaccordApp.class).getContext().getResourceMap(MainWindow.class);
                pickPhotosButton.setText(resourceMap.getString("pickPhotosButton.text")); // NOI18N
                pickPhotosButton.setFocusable(false);
                pickPhotosButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                pickPhotosButton.setMaximumSize(new java.awt.Dimension(80, 40));
                pickPhotosButton.setName("pickPhotosButton"); // NOI18N
                pickPhotosButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                toolbar.add(pickPhotosButton);

                sfmButton.setText(resourceMap.getString("sfmButton.text")); // NOI18N
                sfmButton.setFocusable(false);
                sfmButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                sfmButton.setMaximumSize(new java.awt.Dimension(80, 40));
                sfmButton.setName("sfmButton"); // NOI18N
                sfmButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                toolbar.add(sfmButton);

                mvsButton.setText(resourceMap.getString("mvsButton.text")); // NOI18N
                mvsButton.setFocusable(false);
                mvsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                mvsButton.setMaximumSize(new java.awt.Dimension(80, 40));
                mvsButton.setName("mvsButton"); // NOI18N
                mvsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                toolbar.add(mvsButton);

                exportButton.setText(resourceMap.getString("exportButton.text")); // NOI18N
                exportButton.setFocusable(false);
                exportButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                exportButton.setMaximumSize(new java.awt.Dimension(80, 40));
                exportButton.setName("exportButton"); // NOI18N
                exportButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                toolbar.add(exportButton);

                settingsButton.setText(resourceMap.getString("settingsButton.text")); // NOI18N
                settingsButton.setFocusable(false);
                settingsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                settingsButton.setMaximumSize(new java.awt.Dimension(80, 40));
                settingsButton.setName("settingsButton"); // NOI18N
                settingsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                toolbar.add(settingsButton);

                setComponent(epicenter);
                setToolBar(toolbar);
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel epicenter;
        private javax.swing.JButton exportButton;
        private javax.swing.JButton mvsButton;
        private javax.swing.JButton pickPhotosButton;
        private javax.swing.JButton settingsButton;
        private javax.swing.JButton sfmButton;
        private javax.swing.JToolBar toolbar;
        // End of variables declaration//GEN-END:variables

}
