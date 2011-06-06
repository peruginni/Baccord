
/*
 * SfmSetup.java
 *
 * Created on Mar 9, 2011, 1:17:54 PM
 */

package baccord.ui;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class MvsSetup extends BaseUi {

    /** Creates new form SfmSetup */
    public MvsSetup() {
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
                runCmvsPmvsButton = new javax.swing.JButton();
                separatorBottom = new javax.swing.JSeparator();
                bundlerOutputTextField = new javax.swing.JTextField();
                bundlerOutputChangeButton = new javax.swing.JButton();
                bundlerOutputLabel = new javax.swing.JLabel();
                cmvsOptionsPanel = new javax.swing.JPanel();
                cmvsOptionsTitle = new javax.swing.JLabel();
                maximumImagesSpinner = new javax.swing.JSpinner();
                maximumImagesLabel = new javax.swing.JLabel();
                cpuLabel = new javax.swing.JLabel();
                cpuSpinner = new javax.swing.JSpinner();
                pmvsOptionsPanel = new javax.swing.JPanel();
                pmvsOptionsTitle = new javax.swing.JLabel();
                levelSpinner = new javax.swing.JSpinner();
                levelLabel = new javax.swing.JLabel();
                wsizeSpinner = new javax.swing.JSpinner();
                thresholdSpinner = new javax.swing.JSpinner();
                thresholdLabel = new javax.swing.JLabel();
                wsizeLabel = new javax.swing.JLabel();
                minimumImageSpinner = new javax.swing.JSpinner();
                minimumImageLabel = new javax.swing.JLabel();
                cpuPmvsLabel = new javax.swing.JLabel();
                cpuPmvsSpinner = new javax.swing.JSpinner();
                csizeSpinner = new javax.swing.JSpinner();
                csizeLabel = new javax.swing.JLabel();

                setName("Form"); // NOI18N

                org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(baccord.BaccordApp.class).getContext().getResourceMap(MvsSetup.class);
                titleLabel.setBackground(resourceMap.getColor("sectionTitle.background")); // NOI18N
                titleLabel.setFont(resourceMap.getFont("sectionTitle.font")); // NOI18N
                titleLabel.setText(resourceMap.getString("titleLabel.text")); // NOI18N
                titleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
                titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                titleLabel.setName("titleLabel"); // NOI18N
                titleLabel.setOpaque(true);

                runCmvsPmvsButton.setFont(resourceMap.getFont("runCmvsPmvsButton.font")); // NOI18N
                runCmvsPmvsButton.setText(resourceMap.getString("runCmvsPmvsButton.text")); // NOI18N
                runCmvsPmvsButton.setName("runCmvsPmvsButton"); // NOI18N
                runCmvsPmvsButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                runCmvsPmvsButtonMouseClicked(evt);
                        }
                });

                separatorBottom.setName("separatorBottom"); // NOI18N

                bundlerOutputTextField.setText(resourceMap.getString("bundlerOutputTextField.text")); // NOI18N
                bundlerOutputTextField.setName("bundlerOutputTextField"); // NOI18N

                bundlerOutputChangeButton.setText(resourceMap.getString("bundlerOutputChangeButton.text")); // NOI18N
                bundlerOutputChangeButton.setName("bundlerOutputChangeButton"); // NOI18N
                bundlerOutputChangeButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                bundlerOutputChangeButtonMouseClicked(evt);
                        }
                });

                bundlerOutputLabel.setText(resourceMap.getString("bundlerOutputLabel.text")); // NOI18N
                bundlerOutputLabel.setName("bundlerOutputLabel"); // NOI18N

                cmvsOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
                cmvsOptionsPanel.setName("cmvsOptionsPanel"); // NOI18N

                cmvsOptionsTitle.setFont(resourceMap.getFont("cmvsOptionsTitle.font")); // NOI18N
                cmvsOptionsTitle.setText(resourceMap.getString("cmvsOptionsTitle.text")); // NOI18N
                cmvsOptionsTitle.setName("cmvsOptionsTitle"); // NOI18N

                maximumImagesSpinner.setName("maximumImagesSpinner"); // NOI18N

                maximumImagesLabel.setText(resourceMap.getString("maximumImagesLabel.text")); // NOI18N
                maximumImagesLabel.setName("maximumImagesLabel"); // NOI18N

                cpuLabel.setText(resourceMap.getString("cpuLabel.text")); // NOI18N
                cpuLabel.setName("cpuLabel"); // NOI18N

                cpuSpinner.setName("cpuSpinner"); // NOI18N

                javax.swing.GroupLayout cmvsOptionsPanelLayout = new javax.swing.GroupLayout(cmvsOptionsPanel);
                cmvsOptionsPanel.setLayout(cmvsOptionsPanelLayout);
                cmvsOptionsPanelLayout.setHorizontalGroup(
                        cmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cmvsOptionsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(cmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmvsOptionsTitle)
                                        .addGroup(cmvsOptionsPanelLayout.createSequentialGroup()
                                                .addComponent(maximumImagesLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(maximumImagesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(cmvsOptionsPanelLayout.createSequentialGroup()
                                                .addComponent(cpuLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cpuSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                cmvsOptionsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cpuLabel, maximumImagesLabel});

                cmvsOptionsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cpuSpinner, maximumImagesSpinner});

                cmvsOptionsPanelLayout.setVerticalGroup(
                        cmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cmvsOptionsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmvsOptionsTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(cmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(maximumImagesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(maximumImagesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cpuSpinner)
                                        .addComponent(cpuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(57, Short.MAX_VALUE))
                );

                pmvsOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
                pmvsOptionsPanel.setName("pmvsOptionsPanel"); // NOI18N

                pmvsOptionsTitle.setFont(resourceMap.getFont("pmvsOptionsTitle.font")); // NOI18N
                pmvsOptionsTitle.setText(resourceMap.getString("pmvsOptionsTitle.text")); // NOI18N
                pmvsOptionsTitle.setName("pmvsOptionsTitle"); // NOI18N

                levelSpinner.setName("levelSpinner"); // NOI18N

                levelLabel.setText(resourceMap.getString("levelLabel.text")); // NOI18N
                levelLabel.setName("levelLabel"); // NOI18N

                wsizeSpinner.setName("wsizeSpinner"); // NOI18N

                thresholdSpinner.setName("thresholdSpinner"); // NOI18N

                thresholdLabel.setText(resourceMap.getString("thresholdLabel.text")); // NOI18N
                thresholdLabel.setName("thresholdLabel"); // NOI18N

                wsizeLabel.setText(resourceMap.getString("wsizeLabel.text")); // NOI18N
                wsizeLabel.setName("wsizeLabel"); // NOI18N

                minimumImageSpinner.setName("minimumImageSpinner"); // NOI18N

                minimumImageLabel.setText(resourceMap.getString("minimumImageLabel.text")); // NOI18N
                minimumImageLabel.setName("minimumImageLabel"); // NOI18N

                cpuPmvsLabel.setText(resourceMap.getString("cpuPmvsLabel.text")); // NOI18N
                cpuPmvsLabel.setName("cpuPmvsLabel"); // NOI18N

                cpuPmvsSpinner.setName("cpuPmvsSpinner"); // NOI18N

                csizeSpinner.setName("csizeSpinner"); // NOI18N

                csizeLabel.setText(resourceMap.getString("csizeLabel.text")); // NOI18N
                csizeLabel.setName("csizeLabel"); // NOI18N

                javax.swing.GroupLayout pmvsOptionsPanelLayout = new javax.swing.GroupLayout(pmvsOptionsPanel);
                pmvsOptionsPanel.setLayout(pmvsOptionsPanelLayout);
                pmvsOptionsPanelLayout.setHorizontalGroup(
                        pmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pmvsOptionsPanelLayout.createSequentialGroup()
                                .addGroup(pmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pmvsOptionsPanelLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(pmvsOptionsTitle))
                                        .addGroup(pmvsOptionsPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(pmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pmvsOptionsPanelLayout.createSequentialGroup()
                                                                .addComponent(levelLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(levelSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pmvsOptionsPanelLayout.createSequentialGroup()
                                                                .addComponent(thresholdLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(thresholdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pmvsOptionsPanelLayout.createSequentialGroup()
                                                                .addComponent(minimumImageLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(minimumImageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(45, 45, 45)
                                                .addGroup(pmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(wsizeLabel)
                                                        .addComponent(csizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cpuPmvsLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(pmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(csizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cpuPmvsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(wsizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                pmvsOptionsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cpuPmvsSpinner, csizeSpinner, levelSpinner, minimumImageSpinner, thresholdSpinner, wsizeSpinner});

                pmvsOptionsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {levelLabel, minimumImageLabel, thresholdLabel});

                pmvsOptionsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cpuPmvsLabel, csizeLabel, wsizeLabel});

                pmvsOptionsPanelLayout.setVerticalGroup(
                        pmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pmvsOptionsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pmvsOptionsTitle)
                                .addGap(17, 17, 17)
                                .addGroup(pmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pmvsOptionsPanelLayout.createSequentialGroup()
                                                .addComponent(wsizeSpinner)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(csizeSpinner)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cpuPmvsSpinner))
                                        .addGroup(pmvsOptionsPanelLayout.createSequentialGroup()
                                                .addComponent(wsizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(csizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cpuPmvsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                                        .addGroup(pmvsOptionsPanelLayout.createSequentialGroup()
                                                .addGroup(pmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(levelLabel)
                                                        .addComponent(levelSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(thresholdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(thresholdLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pmvsOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(minimumImageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                                        .addComponent(minimumImageSpinner, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addContainerGap())
                );

                pmvsOptionsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cpuPmvsLabel, cpuPmvsSpinner, csizeLabel, csizeSpinner, levelLabel, levelSpinner, minimumImageLabel, minimumImageSpinner, thresholdLabel, thresholdSpinner, wsizeLabel, wsizeSpinner});

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cmvsOptionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(pmvsOptionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(runCmvsPmvsButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bundlerOutputLabel)
                                                .addGap(8, 8, 8)
                                                .addComponent(bundlerOutputTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bundlerOutputChangeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(separatorBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bundlerOutputLabel)
                                        .addComponent(bundlerOutputChangeButton)
                                        .addComponent(bundlerOutputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmvsOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pmvsOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(separatorBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(runCmvsPmvsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
        }// </editor-fold>//GEN-END:initComponents

    private void bundlerOutputChangeButtonMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_bundlerOutputChangeButtonMouseClicked
    {//GEN-HEADEREND:event_bundlerOutputChangeButtonMouseClicked
	    // TODO add your handling code here:
    }//GEN-LAST:event_bundlerOutputChangeButtonMouseClicked

    private void runCmvsPmvsButtonMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_runCmvsPmvsButtonMouseClicked
    {//GEN-HEADEREND:event_runCmvsPmvsButtonMouseClicked
	    // TODO add your handling code here:
    }//GEN-LAST:event_runCmvsPmvsButtonMouseClicked


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton bundlerOutputChangeButton;
        private javax.swing.JLabel bundlerOutputLabel;
        private javax.swing.JTextField bundlerOutputTextField;
        private javax.swing.JPanel cmvsOptionsPanel;
        private javax.swing.JLabel cmvsOptionsTitle;
        private javax.swing.JLabel cpuLabel;
        private javax.swing.JLabel cpuPmvsLabel;
        private javax.swing.JSpinner cpuPmvsSpinner;
        private javax.swing.JSpinner cpuSpinner;
        private javax.swing.JLabel csizeLabel;
        private javax.swing.JSpinner csizeSpinner;
        private javax.swing.JLabel levelLabel;
        private javax.swing.JSpinner levelSpinner;
        private javax.swing.JLabel maximumImagesLabel;
        private javax.swing.JSpinner maximumImagesSpinner;
        private javax.swing.JLabel minimumImageLabel;
        private javax.swing.JSpinner minimumImageSpinner;
        private javax.swing.JPanel pmvsOptionsPanel;
        private javax.swing.JLabel pmvsOptionsTitle;
        private javax.swing.JButton runCmvsPmvsButton;
        private javax.swing.JSeparator separatorBottom;
        private javax.swing.JLabel thresholdLabel;
        private javax.swing.JSpinner thresholdSpinner;
        private javax.swing.JLabel titleLabel;
        private javax.swing.JLabel wsizeLabel;
        private javax.swing.JSpinner wsizeSpinner;
        // End of variables declaration//GEN-END:variables

}