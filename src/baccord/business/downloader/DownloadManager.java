
package baccord.business.downloader;

import baccord.exceptions.CannotCreateDirectoryException;
import baccord.exceptions.PathMustNotBeDirectoryException;
import java.util.List;

/**
 * Manager responsible for downloading files from internet. User adds urls
 * to download into the queue. For downloading uses one extra thread.
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface DownloadManager
{
	public void setDownloadDirectory(String path) throws CannotCreateDirectoryException, PathMustNotBeDirectoryException;
	public String getDownloadDirectory();

	public void add(DownloadItem item);
	public void remove(DownloadItem item);
	public List<DownloadItem> getAll();

	public void downloadSingle(DownloadItem item);
	public boolean isDownloading();
	public void startDownloading();
	public void stopDownloading();
}
