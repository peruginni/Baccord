
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
public class SfmSetup extends javax.swing.JPanel {

    /** Creates new form SfmSetup */
    public SfmSetup() {
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

                runButton = new javax.swing.JButton();
                horizontalSeparator = new javax.swing.JSeparator();
                sfmTitle = new javax.swing.JLabel();
                inputImagesTextField = new javax.swing.JTextField();
                inputImagesButton = new javax.swing.JButton();
                inputImagesLabel = new javax.swing.JLabel();
                imageSizeLabel = new javax.swing.JLabel();
                imageSizeComboBox = new javax.swing.JComboBox();
                ccdLabel = new javax.swing.JTextArea();
                ccdButton = new javax.swing.JButton();
                imagesToExistingLabel = new javax.swing.JLabel();
                imagesToExistingTextField = new javax.swing.JTextField();
                imagesToExistingButton = new javax.swing.JButton();
                bundlerOptionsLabel = new javax.swing.JLabel();
                bundlerOptionsScrollPane = new javax.swing.JScrollPane();
                bundlerOptionsTextPane = new javax.swing.JTextPane();

                setName("Form"); // NOI18N

                org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(baccord.BaccordApp.class).getContext().getResourceMap(SfmSetup.class);
                runButton.setText(resourceMap.getString("runButton.text")); // NOI18N
                runButton.setName("runButton"); // NOI18N

                horizontalSeparator.setName("horizontalSeparator"); // NOI18N

                sfmTitle.setBackground(resourceMap.getColor("sectionTitle.background")); // NOI18N
                sfmTitle.setFont(resourceMap.getFont("sectionTitle.font")); // NOI18N
                sfmTitle.setText(resourceMap.getString("sfmTitle.text")); // NOI18N
                sfmTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
                sfmTitle.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                sfmTitle.setName("sfmTitle"); // NOI18N
                sfmTitle.setOpaque(true);

                inputImagesTextField.setText(resourceMap.getString("inputImagesTextField.text")); // NOI18N
                inputImagesTextField.setName("inputImagesTextField"); // NOI18N

                inputImagesButton.setText(resourceMap.getString("inputImagesButton.text")); // NOI18N
                inputImagesButton.setName("inputImagesButton"); // NOI18N

                inputImagesLabel.setText(resourceMap.getString("inputImagesLabel.text")); // NOI18N
                inputImagesLabel.setName("inputImagesLabel"); // NOI18N

                imageSizeLabel.setText(resourceMap.getString("imageSizeLabel.text")); // NOI18N
                imageSizeLabel.setName("imageSizeLabel"); // NOI18N

                imageSizeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Use original", "1024 x 1024 pixels" }));
                imageSizeComboBox.setName("imageSizeComboBox"); // NOI18N

                ccdLabel.setColumns(20);
                ccdLabel.setFont(resourceMap.getFont("ccdLabel.font")); // NOI18N
                ccdLabel.setLineWrap(true);
                ccdLabel.setRows(3);
                ccdLabel.setText(resourceMap.getString("ccdLabel.text")); // NOI18N
                ccdLabel.setBorder(null);
                ccdLabel.setName("ccdLabel"); // NOI18N
                ccdLabel.setOpaque(false);

                ccdButton.setText(resourceMap.getString("ccdButton.text")); // NOI18N
                ccdButton.setName("ccdButton"); // NOI18N

                imagesToExistingLabel.setText(resourceMap.getString("imagesToExistingLabel.text")); // NOI18N
                imagesToExistingLabel.setName("imagesToExistingLabel"); // NOI18N

                imagesToExistingTextField.setText(resourceMap.getString("imagesToExistingTextField.text")); // NOI18N
                imagesToExistingTextField.setName("imagesToExistingTextField"); // NOI18N

                imagesToExistingButton.setText(resourceMap.getString("imagesToExistingButton.text")); // NOI18N
                imagesToExistingButton.setName("imagesToExistingButton"); // NOI18N

                bundlerOptionsLabel.setText(resourceMap.getString("bundlerOptionsLabel.text")); // NOI18N
                bundlerOptionsLabel.setName("bundlerOptionsLabel"); // NOI18N

                bundlerOptionsScrollPane.setName("bundlerOptionsScrollPane"); // NOI18N

                bundlerOptionsTextPane.setName("bundlerOptionsTextPane"); // NOI18N
                bundlerOptionsScrollPane.setViewportView(bundlerOptionsTextPane);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bundlerOptionsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(ccdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                                                                .addComponent(ccdButton))
                                                        .addComponent(sfmTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(inputImagesLabel)
                                                                .addGap(8, 8, 8)
                                                                .addComponent(inputImagesTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(inputImagesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(imageSizeLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                                                                .addComponent(imageSizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(imagesToExistingLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(imagesToExistingTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(imagesToExistingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bundlerOptionsLabel)
                                                .addContainerGap(568, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(horizontalSeparator, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE))
                                        .addContainerGap()))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(sfmTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(inputImagesLabel)
                                        .addComponent(inputImagesButton)
                                        .addComponent(inputImagesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(imageSizeLabel)
                                        .addComponent(imageSizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ccdButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ccdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(imagesToExistingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(imagesToExistingLabel)
                                        .addComponent(imagesToExistingButton))
                                .addGap(18, 18, 18)
                                .addComponent(bundlerOptionsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bundlerOptionsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap(371, Short.MAX_VALUE)
                                        .addComponent(horizontalSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap()))
                );
        }// </editor-fold>//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel bundlerOptionsLabel;
        private javax.swing.JScrollPane bundlerOptionsScrollPane;
        private javax.swing.JTextPane bundlerOptionsTextPane;
        private javax.swing.JButton ccdButton;
        private javax.swing.JTextArea ccdLabel;
        private javax.swing.JSeparator horizontalSeparator;
        private javax.swing.JComboBox imageSizeComboBox;
        private javax.swing.JLabel imageSizeLabel;
        private javax.swing.JButton imagesToExistingButton;
        private javax.swing.JLabel imagesToExistingLabel;
        private javax.swing.JTextField imagesToExistingTextField;
        private javax.swing.JButton inputImagesButton;
        private javax.swing.JLabel inputImagesLabel;
        private javax.swing.JTextField inputImagesTextField;
        private javax.swing.JButton runButton;
        private javax.swing.JLabel sfmTitle;
        // End of variables declaration//GEN-END:variables

}
