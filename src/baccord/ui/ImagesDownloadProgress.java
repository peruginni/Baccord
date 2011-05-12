
/*
 * ImagesDownloadProgress.java
 *
 * Created on Mar 7, 2011, 4:41:23 PM
 */

package baccord.ui;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class ImagesDownloadProgress extends javax.swing.JPanel {

    /** Creates new form ImagesDownloadProgress */
    public ImagesDownloadProgress() {
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

                title = new javax.swing.JLabel();
                searchResultsButton = new javax.swing.JButton();
                browseDownloadsButton = new javax.swing.JButton();
                downloadButton = new javax.swing.JButton();
                remainingDownloadsLabel = new javax.swing.JLabel();
                progressScrollPane = new javax.swing.JScrollPane();
                progressTable = new javax.swing.JTable();

                setName("Form"); // NOI18N
                setPreferredSize(new java.awt.Dimension(810, 441));

                org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(baccord.BaccordApp.class).getContext().getResourceMap(ImagesDownloadProgress.class);
                title.setBackground(resourceMap.getColor("sectionTitle.background")); // NOI18N
                title.setFont(resourceMap.getFont("sectionTitle.font")); // NOI18N
                title.setText(resourceMap.getString("title.text")); // NOI18N
                title.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
                title.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                title.setName("title"); // NOI18N
                title.setOpaque(true);

                searchResultsButton.setText(resourceMap.getString("searchResultsButton.text")); // NOI18N
                searchResultsButton.setName("searchResultsButton"); // NOI18N

                browseDownloadsButton.setText(resourceMap.getString("browseDownloadsButton.text")); // NOI18N
                browseDownloadsButton.setName("browseDownloadsButton"); // NOI18N

                downloadButton.setText(resourceMap.getString("downloadButton.text")); // NOI18N
                downloadButton.setName("downloadButton"); // NOI18N

                remainingDownloadsLabel.setText(resourceMap.getString("remainingDownloadsLabel.text")); // NOI18N
                remainingDownloadsLabel.setName("remainingDownloadsLabel"); // NOI18N

                progressScrollPane.setName("progressScrollPane"); // NOI18N

                progressTable.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null},
                                {null, null},
                                {null, null},
                                {null, null}
                        },
                        new String [] {
                                "Title", "Status"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                progressTable.setName("progressTable"); // NOI18N
                progressScrollPane.setViewportView(progressTable);
                progressTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("progressTable.columnModel.title0")); // NOI18N
                progressTable.getColumnModel().getColumn(1).setMinWidth(150);
                progressTable.getColumnModel().getColumn(1).setMaxWidth(150);
                progressTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("progressTable.columnModel.title1")); // NOI18N

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(progressScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(268, 268, 268)
                                                .addComponent(searchResultsButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                                                .addComponent(remainingDownloadsLabel)))
                                .addContainerGap())
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(title, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(browseDownloadsButton)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 432, Short.MAX_VALUE)
                                                        .addComponent(downloadButton)))
                                        .addContainerGap()))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchResultsButton)
                                        .addComponent(remainingDownloadsLabel))
                                .addGap(18, 18, 18)
                                .addComponent(progressScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                .addGap(75, 75, 75))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 328, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(downloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(browseDownloadsButton))
                                        .addContainerGap()))
                );
        }// </editor-fold>//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton browseDownloadsButton;
        private javax.swing.JButton downloadButton;
        private javax.swing.JScrollPane progressScrollPane;
        private javax.swing.JTable progressTable;
        private javax.swing.JLabel remainingDownloadsLabel;
        private javax.swing.JButton searchResultsButton;
        private javax.swing.JLabel title;
        // End of variables declaration//GEN-END:variables

}
