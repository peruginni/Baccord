
package baccord.business.downloader;

import baccord.exceptions.CannotCreateDirectoryException;
import baccord.exceptions.PathMustNotBeDirectoryException;
import java.util.List;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface DownloadManager
{
	public void setDownloadDirectory(String path);
	public String getDownloadDirectory();
	public void prepareDownloadDirectory() throws CannotCreateDirectoryException, PathMustNotBeDirectoryException;

	public void addUrl(String url);
	public void removeUrl(String url);
	public List<String> getDownloadedUrls();

	public void startDownloading();
	public void stopDownloading();
}
