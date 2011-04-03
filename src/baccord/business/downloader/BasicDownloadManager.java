
package baccord.business.downloader;

import baccord.business.BaseBusiness;
import baccord.exceptions.CannotCreateDirectoryException;
import baccord.exceptions.PathMustNotBeDirectoryException;
import baccord.tools.FileHelper;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicDownloadManager extends BaseBusiness implements DownloadManager, Runnable
{
	private List<String> urls;
	private int currentUrlIndex;
	private String downloadDirectory;
	private boolean isDownloading;
	private Thread downloadingThread;

	private static final Logger logger = Logger.getLogger(BasicDownloadManager.class.getName());

	public BasicDownloadManager()
	{
		urls = new LinkedList<String>();
	}

	public void setDownloadDirectory(String path)
	{
		downloadDirectory = path;
	}

	public String getDownloadDirectory()
	{
		return downloadDirectory;
	}

	public void addUrl(String url)
	{
		urls.add(url);
	}

	public void removeUrl(String url)
	{
		urls.remove(url);
	}

	public List<String> getAllUrls()
	{
		return urls;
	}

	public void startDownloading()
	{	
		isDownloading = true;
		downloadingThread = new Thread(this);
		downloadingThread.start();
	}

	public void stopDownloading()
	{
		isDownloading = false;
	}

	public void prepareDownloadDirectory() throws CannotCreateDirectoryException, PathMustNotBeDirectoryException
	{
		File directory = new File(downloadDirectory);
		
		if(!directory.exists()) {
			if(directory.mkdirs()) {
				throw new CannotCreateDirectoryException();
			}
		} else {
			if(directory.isFile()) {
				throw new PathMustNotBeDirectoryException();
			}
		}
	}

	public void run()
	{
		currentUrlIndex = 0;

		while(isDownloading) {

			if(currentUrlIndex >= urls.size())

			try {
				// inspired by http://www.java2s.com/Tutorial/Java/0320__Network/SavebinaryfilefromURL.htm

				URL url = new URL(urls.get(currentUrlIndex));
				URLConnection urlConnection = url.openConnection();
				int contentLength = urlConnection.getContentLength();

				InputStream raw = urlConnection.getInputStream();
				InputStream in = new BufferedInputStream(raw);
				byte[] data = new byte[contentLength];

				int bytesRead = 0;
				int offset = 0;
				while (offset < contentLength) {
					bytesRead = in.read(data, offset, data.length - offset);
					if(bytesRead == -1) break;
					offset += bytesRead;
				}
				in.close();

				if(offset != contentLength) {
					throw new IOException("Downloaded only "+offset+" bytes, while expected "+contentLength+" bytes");
				}

				String filename = FileHelper.getFilenameFromUrl(url.getFile());
				filename = FileHelper.generateUniqueFilename(downloadDirectory, filename);
				String downloadedFilename = FileHelper.getAbsoluteFilePath(downloadDirectory, filename);

				File downloadedFile = new File(downloadedFilename);
				downloadedFile.createNewFile();

				FileOutputStream out = new FileOutputStream(downloadedFile);
				out.write(data);
				out.flush();
				out.close();

				currentUrlIndex++;

			} catch (MalformedURLException ex) {
				logger.log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				logger.log(Level.SEVERE, null, ex);
			}

		}

	}

}
