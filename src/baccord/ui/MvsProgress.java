
/*
 * SfmProgress.java
 *
 * Created on Mar 9, 2011, 1:17:54 PM
 */

package baccord.ui;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class MvsProgress extends javax.swing.JPanel {

    /** Creates new form SfmProgress */
    public MvsProgress() {
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

                titleLabel = new javax.swing.JLabel();
                continueButton = new javax.swing.JButton();
                stopButton = new javax.swing.JButton();
                browseOutputButton = new javax.swing.JButton();
                progressScrollPane = new javax.swing.JScrollPane();
                progressTextPane = new javax.swing.JTextPane();

                setName("Form"); // NOI18N

                org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(baccord.BaccordApp.class).getContext().getResourceMap(MvsProgress.class);
                titleLabel.setBackground(resourceMap.getColor("sectionTitle.background")); // NOI18N
                titleLabel.setFont(resourceMap.getFont("sectionTitle.font")); // NOI18N
                titleLabel.setText(resourceMap.getString("titleLabel.text")); // NOI18N
                titleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
                titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                titleLabel.setName("titleLabel"); // NOI18N
                titleLabel.setOpaque(true);

                continueButton.setText(resourceMap.getString("continueButton.text")); // NOI18N
                continueButton.setName("continueButton"); // NOI18N

                stopButton.setText(resourceMap.getString("stopButton.text")); // NOI18N
                stopButton.setName("stopButton"); // NOI18N

                browseOutputButton.setText(resourceMap.getString("browseOutputButton.text")); // NOI18N
                browseOutputButton.setName("browseOutputButton"); // NOI18N

                progressScrollPane.setName("progressScrollPane"); // NOI18N

                progressTextPane.setName("progressTextPane"); // NOI18N
                progressScrollPane.setViewportView(progressTextPane);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(progressScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(titleLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(stopButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(browseOutputButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 336, Short.MAX_VALUE)
                                                .addComponent(continueButton)))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(titleLabel)
                                        .addComponent(stopButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(progressScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(browseOutputButton)
                                        .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                );
        }// </editor-fold>//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton browseOutputButton;
        private javax.swing.JButton continueButton;
        private javax.swing.JButton jButton4;
        private javax.swing.JButton jButton5;
        private javax.swing.JScrollPane progressScrollPane;
        private javax.swing.JTextPane progressTextPane;
        private javax.swing.JButton stopButton;
        private javax.swing.JLabel titleLabel;
        // End of variables declaration//GEN-END:variables

}
