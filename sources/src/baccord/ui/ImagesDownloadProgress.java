
/*
 * ImagesDownloadProgress.java
 *
 * Created on Mar 7, 2011, 4:41:23 PM
 */

package baccord.ui;

import baccord.BaccordApp;
import baccord.business.downloader.DownloadItem;
import baccord.business.downloader.DownloadManager;
import baccord.business.images.Editor;
import baccord.business.settings.Settings;
import baccord.business.sfm.StructureFromMotion;
import baccord.tools.Observable;
import baccord.tools.Observer;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class ImagesDownloadProgress extends BaseUi implements Observer
{
	@Inject private DownloadManager downloadManager;
	@Inject private Editor editor;
	@Inject private Settings settings;
	@Inject private StructureFromMotion sfm;
	
	private DownloadTableModel tableModel;
	private int remainingEditings = 0;
	private int remainingDownloads = 0;
	
	/** Creates new form ImagesDownloadProgress */
	public ImagesDownloadProgress() 
	{
		initComponents();
	}
	
	@Override
	public void init()
	{
		super.init();
		downloadManager.registerObserver(this);	
		refreshTable();
		refreshRemaining();
	}

	@Override
	public void start()
	{
		super.start();
		editor.registerObserver(this);
	}
	
	@Override
	public void close()
	{
		downloadManager.removeObserver(this);
		editor.removeObserver(this);
		super.close();
	}
	
	public void refreshTable()
	{
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(baccord.BaccordApp.class).getContext().getResourceMap(ImagesDownloadProgress.class);
		tableModel = new DownloadTableModel(downloadManager.getAll());
		progressTable.setModel(tableModel);
		
		progressScrollPane.setViewportView(progressTable);
                progressTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("progressTable.columnModel.title0")); // NOI18N
                progressTable.getColumnModel().getColumn(1).setMinWidth(150);
                progressTable.getColumnModel().getColumn(1).setMaxWidth(150);
                progressTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("progressTable.columnModel.title1")); // NOI18N
	}
	
	public void refreshRemaining()
	{
		remainingDownloads = downloadManager.getRemaining().size();
		remainingEditings = editor.getAllTasks().size();
		
		remainingDownloadsLabel.setText(
			(remainingDownloads > 0)
			? "Remaining " + remainingDownloads + " downloads"
			: "No remaining downloads"
		);
		
		remainingEditingsLabel.setText(
			(remainingEditings > 0)
			? "Remaining " + remainingEditings + " editings"
			: "No remaining editings"
		);
	}
	
	public void update(Observable caller, Object arg)
	{
		if(caller instanceof DownloadManager) {
			refreshTable();
		}
		refreshRemaining();
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
        continueSfmButton = new javax.swing.JButton();
        remainingEditingsLabel = new javax.swing.JLabel();
        progressScrollPane = new javax.swing.JScrollPane();
        progressTable = new javax.swing.JTable();
        remainingDownloadsLabel = new javax.swing.JLabel();

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
        searchResultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchResultsButtonActionPerformed(evt);
            }
        });

        browseDownloadsButton.setText(resourceMap.getString("browseDownloadsButton.text")); // NOI18N
        browseDownloadsButton.setName("browseDownloadsButton"); // NOI18N
        browseDownloadsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseDownloadsButtonActionPerformed(evt);
            }
        });

        continueSfmButton.setFont(resourceMap.getFont("continueSfmButton.font")); // NOI18N
        continueSfmButton.setText(resourceMap.getString("continueSfmButton.text")); // NOI18N
        continueSfmButton.setName("continueSfmButton"); // NOI18N
        continueSfmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueSfmButtonActionPerformed(evt);
            }
        });

        remainingEditingsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        remainingEditingsLabel.setText(resourceMap.getString("remainingEditingsLabel.text")); // NOI18N
        remainingEditingsLabel.setName("remainingEditingsLabel"); // NOI18N

        progressScrollPane.setName("progressScrollPane"); // NOI18N

        progressTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
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

        remainingDownloadsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        remainingDownloadsLabel.setText(resourceMap.getString("remainingDownloadsLabel.text")); // NOI18N
        remainingDownloadsLabel.setName("remainingDownloadsLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(browseDownloadsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchResultsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(remainingEditingsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(remainingDownloadsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(continueSfmButton))
                    .addComponent(progressScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(progressScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchResultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(browseDownloadsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(continueSfmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(remainingDownloadsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(remainingEditingsLabel)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {browseDownloadsButton, continueSfmButton, searchResultsButton});

    }// </editor-fold>//GEN-END:initComponents

    private void browseDownloadsButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_browseDownloadsButtonActionPerformed
    {//GEN-HEADEREND:event_browseDownloadsButtonActionPerformed
	   	try {
			Process p = new ProcessBuilder(
				settings.get(Settings.FILE_EXPLORER_PATH),
				downloadManager.getDownloadDirectory()
			).start(); 
		} catch (IOException ex) {
			Dialog.error(this, "Cannot open file browser");
		} 
    }//GEN-LAST:event_browseDownloadsButtonActionPerformed

    private void continueSfmButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_continueSfmButtonActionPerformed
    {//GEN-HEADEREND:event_continueSfmButtonActionPerformed
	    if(remainingDownloads > 0) {
		    Dialog.notice(this, "Please wait until all images are downloaded");
		    return;
	    }
	    if(remainingEditings > 0) {
		    Dialog.notice(this, "Please wait until all images are edited");
		    return;
	    }
	    
	    sfm.setImageDirectory(downloadManager.getDownloadDirectory());
	    
	    BaccordApp.getApplication().changeScreen(SfmSetup.class);
    }//GEN-LAST:event_continueSfmButtonActionPerformed

    private void searchResultsButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_searchResultsButtonActionPerformed
    {//GEN-HEADEREND:event_searchResultsButtonActionPerformed
	    BaccordApp.getApplication().changeScreen(ImagesSearchResults.class);
    }//GEN-LAST:event_searchResultsButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseDownloadsButton;
    private javax.swing.JButton continueSfmButton;
    private javax.swing.JScrollPane progressScrollPane;
    private javax.swing.JTable progressTable;
    private javax.swing.JLabel remainingDownloadsLabel;
    private javax.swing.JLabel remainingEditingsLabel;
    private javax.swing.JButton searchResultsButton;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

}

class DownloadTableModel extends AbstractTableModel
{
	List<DownloadItem> list;
	String[] columnNames = {"Source path", "Status"};

	public DownloadTableModel(List<DownloadItem> list)
	{
		this.list = list;
	}
	
	public int getRowCount()
	{
		return list.size();
	}

	public int getColumnCount()
	{
		return columnNames.length;
	}

	public Object getValueAt(int row, int col)
	{
		DownloadItem item = list.get(row);
		return (col == 0) 
			? item.getSource()
			: translateStatus(item.getStatus());
	}
	
	public String translateStatus(int status)
	{
		String result;
		switch(status) {
			case DownloadItem.FINISHED: result = "Finished"; break;
			case DownloadItem.DOWNLOADING: result = "Downloading"; break;
			case DownloadItem.WAITING: result = "Waiting"; break;
			case DownloadItem.SKIPPED: result = "Skipped"; break;
			default: result = ""+status;
		}
		return result;
	}
}
