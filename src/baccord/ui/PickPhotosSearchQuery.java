
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
public class PickPhotosSearchQuery extends javax.swing.JPanel {

    /** Creates new form PickPhotosSearchQuery */
    public PickPhotosSearchQuery() {
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

                panelQueryBuilder = new javax.swing.JPanel();
                labelSearchPhotos = new javax.swing.JLabel();
                dropdownSearchEngine = new javax.swing.JComboBox();
                textfieldKeywords = new javax.swing.JTextField();
                labelKeywords = new javax.swing.JLabel();
                textfieldKeywords1 = new javax.swing.JTextField();
                labelKeywords1 = new javax.swing.JLabel();
                textfieldKeywords2 = new javax.swing.JTextField();
                textfieldKeywords3 = new javax.swing.JTextField();
                jSpinner1 = new javax.swing.JSpinner();
                labelKeywords2 = new javax.swing.JLabel();
                jSpinner2 = new javax.swing.JSpinner();
                labelKeywords3 = new javax.swing.JLabel();
                labelKeywords4 = new javax.swing.JLabel();
                jSpinner3 = new javax.swing.JSpinner();
                labelKeywords5 = new javax.swing.JLabel();
                jSpinner4 = new javax.swing.JSpinner();
                jComboBox1 = new javax.swing.JComboBox();
                labelKeywords6 = new javax.swing.JLabel();
                jComboBox2 = new javax.swing.JComboBox();
                labelKeywords7 = new javax.swing.JLabel();
                labelKeywords8 = new javax.swing.JLabel();
                textfieldKeywords4 = new javax.swing.JTextField();
                jButton1 = new javax.swing.JButton();
                panelDownloadOptions = new javax.swing.JPanel();
                labelDownloadOptions = new javax.swing.JLabel();
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jCheckBox2 = new javax.swing.JCheckBox();
                jCheckBox3 = new javax.swing.JCheckBox();
                jCheckBox4 = new javax.swing.JCheckBox();
                jButton2 = new javax.swing.JButton();
                jPanel3 = new javax.swing.JPanel();
                jCheckBox1 = new javax.swing.JCheckBox();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTextArea1 = new javax.swing.JTextArea();
                jSeparator2 = new javax.swing.JSeparator();
                jPanel2 = new javax.swing.JPanel();
                jButton3 = new javax.swing.JButton();
                jButton4 = new javax.swing.JButton();
                jSeparator1 = new javax.swing.JSeparator();

                setName("Form"); // NOI18N

                panelQueryBuilder.setName("panelQueryBuilder"); // NOI18N

                org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(baccord.BaccordApp.class).getContext().getResourceMap(PickPhotosSearchQuery.class);
                labelSearchPhotos.setBackground(resourceMap.getColor("sectionTitle.background")); // NOI18N
                labelSearchPhotos.setFont(resourceMap.getFont("sectionTitle.font")); // NOI18N
                labelSearchPhotos.setText(resourceMap.getString("labelSearchPhotos.text")); // NOI18N
                labelSearchPhotos.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
                labelSearchPhotos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                labelSearchPhotos.setName("labelSearchPhotos"); // NOI18N
                labelSearchPhotos.setOpaque(true);

                dropdownSearchEngine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "via Flickr", "via Google Image Search" }));
                dropdownSearchEngine.setName("dropdownSearchEngine"); // NOI18N

                textfieldKeywords.setText(resourceMap.getString("textfieldKeywords.text")); // NOI18N
                textfieldKeywords.setName("textfieldKeywords"); // NOI18N

                labelKeywords.setText(resourceMap.getString("labelKeywords.text")); // NOI18N
                labelKeywords.setName("labelKeywords"); // NOI18N

                textfieldKeywords1.setText(resourceMap.getString("textfieldKeywords1.text")); // NOI18N
                textfieldKeywords1.setName("textfieldKeywords1"); // NOI18N

                labelKeywords1.setText(resourceMap.getString("labelKeywords1.text")); // NOI18N
                labelKeywords1.setName("labelKeywords1"); // NOI18N

                textfieldKeywords2.setText(resourceMap.getString("textfieldKeywords2.text")); // NOI18N
                textfieldKeywords2.setName("textfieldKeywords2"); // NOI18N

                textfieldKeywords3.setText(resourceMap.getString("textfieldKeywords3.text")); // NOI18N
                textfieldKeywords3.setName("textfieldKeywords3"); // NOI18N

                jSpinner1.setModel(new javax.swing.SpinnerDateModel());
                jSpinner1.setName("jSpinner1"); // NOI18N

                labelKeywords2.setText(resourceMap.getString("labelKeywords2.text")); // NOI18N
                labelKeywords2.setName("labelKeywords2"); // NOI18N

                jSpinner2.setModel(new javax.swing.SpinnerDateModel());
                jSpinner2.setName("jSpinner2"); // NOI18N

                labelKeywords3.setText(resourceMap.getString("labelKeywords3.text")); // NOI18N
                labelKeywords3.setName("labelKeywords3"); // NOI18N

                labelKeywords4.setText(resourceMap.getString("labelKeywords4.text")); // NOI18N
                labelKeywords4.setName("labelKeywords4"); // NOI18N

                jSpinner3.setModel(new javax.swing.SpinnerDateModel());
                jSpinner3.setName("jSpinner3"); // NOI18N

                labelKeywords5.setText(resourceMap.getString("labelKeywords5.text")); // NOI18N
                labelKeywords5.setName("labelKeywords5"); // NOI18N

                jSpinner4.setModel(new javax.swing.SpinnerDateModel());
                jSpinner4.setName("jSpinner4"); // NOI18N

                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Creative Commons" }));
                jComboBox1.setName("jComboBox1"); // NOI18N

                labelKeywords6.setText(resourceMap.getString("labelKeywords6.text")); // NOI18N
                labelKeywords6.setName("labelKeywords6"); // NOI18N

                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "all", "indoor", "outdoor" }));
                jComboBox2.setName("jComboBox2"); // NOI18N

                labelKeywords7.setText(resourceMap.getString("labelKeywords7.text")); // NOI18N
                labelKeywords7.setName("labelKeywords7"); // NOI18N

                labelKeywords8.setText(resourceMap.getString("labelKeywords8.text")); // NOI18N
                labelKeywords8.setName("labelKeywords8"); // NOI18N

                textfieldKeywords4.setText(resourceMap.getString("textfieldKeywords4.text")); // NOI18N
                textfieldKeywords4.setName("textfieldKeywords4"); // NOI18N

                jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
                jButton1.setName("jButton1"); // NOI18N

                javax.swing.GroupLayout panelQueryBuilderLayout = new javax.swing.GroupLayout(panelQueryBuilder);
                panelQueryBuilder.setLayout(panelQueryBuilderLayout);
                panelQueryBuilderLayout.setHorizontalGroup(
                        panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelQueryBuilderLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelQueryBuilderLayout.createSequentialGroup()
                                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelQueryBuilderLayout.createSequentialGroup()
                                                                .addComponent(labelSearchPhotos, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(dropdownSearchEngine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelQueryBuilderLayout.createSequentialGroup()
                                                                .addComponent(labelKeywords)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(textfieldKeywords, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)))
                                                .addGap(155, 155, 155))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelQueryBuilderLayout.createSequentialGroup()
                                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelQueryBuilderLayout.createSequentialGroup()
                                                                .addComponent(labelKeywords8)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(textfieldKeywords4, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelQueryBuilderLayout.createSequentialGroup()
                                                                .addComponent(labelKeywords6)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                                                .addComponent(labelKeywords7)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelQueryBuilderLayout.createSequentialGroup()
                                                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelQueryBuilderLayout.createSequentialGroup()
                                                                                .addComponent(labelKeywords2)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelQueryBuilderLayout.createSequentialGroup()
                                                                                .addComponent(labelKeywords4)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(labelKeywords5)
                                                                        .addComponent(labelKeywords3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jSpinner2)
                                                                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelQueryBuilderLayout.createSequentialGroup()
                                                                .addComponent(labelKeywords1)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(textfieldKeywords1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(textfieldKeywords2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(textfieldKeywords3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(172, 172, 172))))
                );
                panelQueryBuilderLayout.setVerticalGroup(
                        panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelQueryBuilderLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelSearchPhotos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dropdownSearchEngine, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelKeywords)
                                        .addComponent(textfieldKeywords, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelKeywords1)
                                        .addComponent(textfieldKeywords3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textfieldKeywords1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textfieldKeywords2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelQueryBuilderLayout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(labelKeywords3))
                                        .addGroup(panelQueryBuilderLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(panelQueryBuilderLayout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(labelKeywords2))
                                                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelQueryBuilderLayout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(labelKeywords4))
                                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelQueryBuilderLayout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(labelKeywords5))
                                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(labelKeywords6)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(labelKeywords7)
                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelQueryBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textfieldKeywords4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelKeywords8)
                                        .addComponent(jButton1))
                                .addContainerGap(38, Short.MAX_VALUE))
                );

                panelDownloadOptions.setName("panelDownloadOptions"); // NOI18N

                labelDownloadOptions.setBackground(resourceMap.getColor("sectionTitle.background")); // NOI18N
                labelDownloadOptions.setFont(resourceMap.getFont("sectionTitle.font")); // NOI18N
                labelDownloadOptions.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                labelDownloadOptions.setText(resourceMap.getString("labelDownloadOptions.text")); // NOI18N
                labelDownloadOptions.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
                labelDownloadOptions.setName("labelDownloadOptions"); // NOI18N
                labelDownloadOptions.setOpaque(true);

                jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
                jPanel1.setName("jPanel1"); // NOI18N

                jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
                jLabel1.setName("jLabel1"); // NOI18N

                jCheckBox2.setText(resourceMap.getString("jCheckBox2.text")); // NOI18N
                jCheckBox2.setName("jCheckBox2"); // NOI18N

                jCheckBox3.setText(resourceMap.getString("jCheckBox3.text")); // NOI18N
                jCheckBox3.setName("jCheckBox3"); // NOI18N

                jCheckBox4.setText(resourceMap.getString("jCheckBox4.text")); // NOI18N
                jCheckBox4.setName("jCheckBox4"); // NOI18N

                jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
                jButton2.setName("jButton2"); // NOI18N

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jCheckBox2)
                                        .addComponent(jCheckBox3)
                                        .addComponent(jCheckBox4)
                                        .addComponent(jButton2))
                                .addContainerGap(114, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(jButton2))
                );

                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
                jPanel3.setName("jPanel3"); // NOI18N

                jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
                jCheckBox1.setMargin(new java.awt.Insets(1, 0, 0, 1));
                jCheckBox1.setName("jCheckBox1"); // NOI18N

                jScrollPane1.setName("jScrollPane1"); // NOI18N

                jTextArea1.setColumns(20);
                jTextArea1.setFont(resourceMap.getFont("jTextArea1.font")); // NOI18N
                jTextArea1.setLineWrap(true);
                jTextArea1.setRows(3);
                jTextArea1.setText(resourceMap.getString("jTextArea1.text")); // NOI18N
                jTextArea1.setBorder(null);
                jTextArea1.setName("jTextArea1"); // NOI18N
                jScrollPane1.setViewportView(jTextArea1);

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox1)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(10, Short.MAX_VALUE))
                );

                jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
                jSeparator2.setName("jSeparator2"); // NOI18N

                javax.swing.GroupLayout panelDownloadOptionsLayout = new javax.swing.GroupLayout(panelDownloadOptions);
                panelDownloadOptions.setLayout(panelDownloadOptionsLayout);
                panelDownloadOptionsLayout.setHorizontalGroup(
                        panelDownloadOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelDownloadOptionsLayout.createSequentialGroup()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelDownloadOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelDownloadOptions, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
                                .addContainerGap())
                );
                panelDownloadOptionsLayout.setVerticalGroup(
                        panelDownloadOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelDownloadOptionsLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                .addGap(6, 6, 6))
                        .addGroup(panelDownloadOptionsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelDownloadOptions)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );

                jPanel2.setName("jPanel2"); // NOI18N

                jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
                jButton3.setName("jButton3"); // NOI18N

                jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
                jButton4.setName("jButton4"); // NOI18N

                jSeparator1.setName("jSeparator1"); // NOI18N

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 480, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4)))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelQueryBuilder, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelDownloadOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelQueryBuilder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelDownloadOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
        }// </editor-fold>//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JComboBox dropdownSearchEngine;
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JButton jButton3;
        private javax.swing.JButton jButton4;
        private javax.swing.JCheckBox jCheckBox1;
        private javax.swing.JCheckBox jCheckBox2;
        private javax.swing.JCheckBox jCheckBox3;
        private javax.swing.JCheckBox jCheckBox4;
        private javax.swing.JComboBox jComboBox1;
        private javax.swing.JComboBox jComboBox2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JSeparator jSeparator1;
        private javax.swing.JSeparator jSeparator2;
        private javax.swing.JSpinner jSpinner1;
        private javax.swing.JSpinner jSpinner2;
        private javax.swing.JSpinner jSpinner3;
        private javax.swing.JSpinner jSpinner4;
        private javax.swing.JTextArea jTextArea1;
        private javax.swing.JLabel labelDownloadOptions;
        private javax.swing.JLabel labelKeywords;
        private javax.swing.JLabel labelKeywords1;
        private javax.swing.JLabel labelKeywords2;
        private javax.swing.JLabel labelKeywords3;
        private javax.swing.JLabel labelKeywords4;
        private javax.swing.JLabel labelKeywords5;
        private javax.swing.JLabel labelKeywords6;
        private javax.swing.JLabel labelKeywords7;
        private javax.swing.JLabel labelKeywords8;
        private javax.swing.JLabel labelSearchPhotos;
        private javax.swing.JPanel panelDownloadOptions;
        private javax.swing.JPanel panelQueryBuilder;
        private javax.swing.JTextField textfieldKeywords;
        private javax.swing.JTextField textfieldKeywords1;
        private javax.swing.JTextField textfieldKeywords2;
        private javax.swing.JTextField textfieldKeywords3;
        private javax.swing.JTextField textfieldKeywords4;
        // End of variables declaration//GEN-END:variables

}
