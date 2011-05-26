
/*
 * PickPhotosSearchQuery.java
 *
 * Created on Mar 7, 2011, 4:39:42 PM
 */

package baccord.ui;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class Export extends BaseUi {

    /** Creates new form PickPhotosSearchQuery */
    public Export() {
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

                exportTitle = new javax.swing.JLabel();
                resultsPanel = new javax.swing.JPanel();
                resultsTitle = new javax.swing.JLabel();
                includeBundlerCheckBox = new javax.swing.JCheckBox();
                includeCmvsCheckBox = new javax.swing.JCheckBox();
                includePmvsCheckBox = new javax.swing.JCheckBox();
                resultsButton = new javax.swing.JButton();
                variousPanel = new javax.swing.JPanel();
                variousTitle = new javax.swing.JLabel();
                poissionButton = new javax.swing.JButton();
                partialPmvsButton = new javax.swing.JButton();

                setName("Form"); // NOI18N
                setPreferredSize(new java.awt.Dimension(810, 441));

                org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(baccord.BaccordApp.class).getContext().getResourceMap(Export.class);
                exportTitle.setBackground(resourceMap.getColor("sectionTitle.background")); // NOI18N
                exportTitle.setFont(resourceMap.getFont("sectionTitle.font")); // NOI18N
                exportTitle.setText(resourceMap.getString("exportTitle.text")); // NOI18N
                exportTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
                exportTitle.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                exportTitle.setName("exportTitle"); // NOI18N
                exportTitle.setOpaque(true);

                resultsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
                resultsPanel.setName("resultsPanel"); // NOI18N

                resultsTitle.setFont(resourceMap.getFont("resultsTitle.font")); // NOI18N
                resultsTitle.setText(resourceMap.getString("resultsTitle.text")); // NOI18N
                resultsTitle.setName("resultsTitle"); // NOI18N

                includeBundlerCheckBox.setText(resourceMap.getString("includeBundlerCheckBox.text")); // NOI18N
                includeBundlerCheckBox.setName("includeBundlerCheckBox"); // NOI18N
                includeBundlerCheckBox.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                includeBundlerCheckBoxActionPerformed(evt);
                        }
                });

                includeCmvsCheckBox.setText(resourceMap.getString("includeCmvsCheckBox.text")); // NOI18N
                includeCmvsCheckBox.setName("includeCmvsCheckBox"); // NOI18N

                includePmvsCheckBox.setText(resourceMap.getString("includePmvsCheckBox.text")); // NOI18N
                includePmvsCheckBox.setName("includePmvsCheckBox"); // NOI18N

                resultsButton.setText(resourceMap.getString("resultsButton.text")); // NOI18N
                resultsButton.setName("resultsButton"); // NOI18N

                javax.swing.GroupLayout resultsPanelLayout = new javax.swing.GroupLayout(resultsPanel);
                resultsPanel.setLayout(resultsPanelLayout);
                resultsPanelLayout.setHorizontalGroup(
                        resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(resultsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(resultsTitle)
                                        .addComponent(includePmvsCheckBox)
                                        .addComponent(includeCmvsCheckBox)
                                        .addComponent(includeBundlerCheckBox)
                                        .addComponent(resultsButton))
                                .addContainerGap(39, Short.MAX_VALUE))
                );
                resultsPanelLayout.setVerticalGroup(
                        resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(resultsPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(resultsTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(includeBundlerCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(includeCmvsCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(includePmvsCheckBox)
                                .addGap(12, 12, 12)
                                .addComponent(resultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                variousPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
                variousPanel.setName("variousPanel"); // NOI18N

                variousTitle.setFont(resourceMap.getFont("variousTitle.font")); // NOI18N
                variousTitle.setText(resourceMap.getString("variousTitle.text")); // NOI18N
                variousTitle.setName("variousTitle"); // NOI18N

                poissionButton.setText(resourceMap.getString("poissionButton.text")); // NOI18N
                poissionButton.setName("poissionButton"); // NOI18N

                partialPmvsButton.setText(resourceMap.getString("partialPmvsButton.text")); // NOI18N
                partialPmvsButton.setName("partialPmvsButton"); // NOI18N

                javax.swing.GroupLayout variousPanelLayout = new javax.swing.GroupLayout(variousPanel);
                variousPanel.setLayout(variousPanelLayout);
                variousPanelLayout.setHorizontalGroup(
                        variousPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(variousPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(variousPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(variousTitle)
                                        .addComponent(poissionButton)
                                        .addComponent(partialPmvsButton))
                                .addContainerGap(27, Short.MAX_VALUE))
                );
                variousPanelLayout.setVerticalGroup(
                        variousPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(variousPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(variousTitle)
                                .addGap(18, 18, 18)
                                .addComponent(poissionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(partialPmvsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(35, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(exportTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(resultsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(variousPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(exportTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(variousPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(resultsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                );
        }// </editor-fold>//GEN-END:initComponents

    private void includeBundlerCheckBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_includeBundlerCheckBoxActionPerformed
    {//GEN-HEADEREND:event_includeBundlerCheckBoxActionPerformed
	    // TODO add your handling code here:
    }//GEN-LAST:event_includeBundlerCheckBoxActionPerformed


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel exportTitle;
        private javax.swing.JCheckBox includeBundlerCheckBox;
        private javax.swing.JCheckBox includeCmvsCheckBox;
        private javax.swing.JCheckBox includePmvsCheckBox;
        private javax.swing.JButton partialPmvsButton;
        private javax.swing.JButton poissionButton;
        private javax.swing.JButton resultsButton;
        private javax.swing.JPanel resultsPanel;
        private javax.swing.JLabel resultsTitle;
        private javax.swing.JPanel variousPanel;
        private javax.swing.JLabel variousTitle;
        // End of variables declaration//GEN-END:variables

}
