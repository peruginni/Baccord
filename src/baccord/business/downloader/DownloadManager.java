
package baccord.business.downloader;

import baccord.business.images.Editor;
import baccord.exceptions.CannotCreateDirectoryException;
import baccord.exceptions.PathMustBeDirectoryException;
import baccord.tools.Observable;
import java.util.List;

/**
 * Manager responsible for downloading files from internet. User adds urls
 * to download into the queue. For downloading uses one extra thread.
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface DownloadManager extends Observable
{
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	/**
	 * Before setting download directory will check if path is a directory
	 * and if directory does not exists will create such directory
	 * 
	 * @param path where will be downloaded files stored
	 * 
	 * @throws CannotCreateDirectoryException
	 * @throws PathMustBeDirectoryException 
	 */
	public void setDownloadDirectory(String path) throws CannotCreateDirectoryException, PathMustBeDirectoryException;
	
	/**
	 * Return current download directory
	 * 
	 * @return String
	 */
	public String getDownloadDirectory();

	/**
	 * Return instance of Editor for effective (queued) image editing
	 * 
	 * @return 
	 */
	public Editor getEditor();
	
	/**
	 * Set editor
	 * 
	 * @param editor 
	 */
	public void setEditor(Editor editor);
	
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	/**
	 * Add item to download queue and assing to item its download destination
	 * 
	 * @param item to add to download queue
	 */ 
	public void add(DownloadItem item);
	
	/**
	 * Shortcut for adding lots of urls to download queue.
	 * 
	 * @param listOfUrls
	 */
	public void add(List<String> listOfUrls);
	
	/**
	 * Will remove item from download queue
	 * 
	 * @param item 
	 */
	public void remove(DownloadItem item);
	
	/**
	 * Will clear list of urls
	 */
	public void clear();
	
	/**
	 * Will clear only finished items
	 */
	public void clearFinished();
	
	/**
	 * Get all items in current download queue
	 * 
	 * @return 
	 */
	public List<DownloadItem> getAll();
	
	/**
	 * Get only remaining download items
	 * @return 
	 */
	public List<DownloadItem> getRemaining();

	/**
	 * Will download single file. If item does not have target directory, 
	 * it will be automatically set.
	 * 
	 * @param item
	 */
	public void downloadSingle(DownloadItem item);
	
	/**
	 * Determine if is currently downloading
	 * 
	 * @return 
	 */
	public boolean isDownloading();
	
	/**
	 * Start downloading. Will start new thread if there is no running one.
	 */
	public void start();
	
	/**
	 * Stop downloading
	 */
	public void stop();
}
