
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
 * Basic implementation of download manager.
 *
 * Manager responsible for downloading files from internet. User adds urls
 * to download into the queue. For downloading uses one extra thread.
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicDownloadManager extends BaseBusiness implements DownloadManager, Runnable
{
	private List<DownloadItem> items;
	private int currentUrlIndex = 0;
	private String downloadDirectory = "";
	private boolean isDownloading = false;
	private Thread downloadingThread;

	private static final Logger logger = Logger.getLogger(BasicDownloadManager.class.getName());

	public BasicDownloadManager()
	{
		items = new LinkedList<DownloadItem>();
	}

	public void setDownloadDirectory(String path) throws CannotCreateDirectoryException, PathMustNotBeDirectoryException
	{
		File directory = new File(path);

		if(!directory.exists()) {
			if(!directory.mkdirs()) {
				throw new CannotCreateDirectoryException();
			}
		} else {
			if(directory.isFile()) {
				throw new PathMustNotBeDirectoryException();
			}
		}

		downloadDirectory = path;
	}
	
	public String getDownloadDirectory()
	{
		return downloadDirectory;
	}

	public void add(DownloadItem item)
	{
		item.setTarget(downloadDirectory);
		items.add(item);
	}

	public void remove(DownloadItem item)
	{
		items.remove(item);
	}

	public List<DownloadItem> getAll()
	{
		return items;
	}

	public boolean isDownloading()
	{
		return isDownloading;
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

	public void downloadSingle(DownloadItem item)
	{
		try {
			// inspired by http://www.java2s.com/Tutorial/Java/0320__Network/SavebinaryfilefromURL.htm

			// get next url to download from queue
			URL url = new URL(item.getSource());

			// url information
			URLConnection urlConnection = url.openConnection();
			int contentLength = urlConnection.getContentLength();

			// define stream objects
			InputStream raw = urlConnection.getInputStream();
			InputStream in = new BufferedInputStream(raw);

			// array for storing incoming bytes
			byte[] data = new byte[contentLength];

			// read bytes up to lenght specified by http header earlier
			int bytesRead = 0;
			int offset = 0;
			while (offset < contentLength) {
				bytesRead = in.read(data, offset, data.length - offset);
				if(bytesRead == -1) break;
				offset += bytesRead;
			}
			in.close();

			// fail if not complete download
			if(offset != contentLength) {
				throw new IOException("Downloaded only "+offset+" bytes, while expected "+contentLength+" bytes");
			}

			// generate unique filename
			String filename = FileHelper.getFilenameFromUrl(url.getFile());
			filename = FileHelper.generateUniqueFilename(item.getTarget(), filename);
			String absoluteTargetFilename = FileHelper.getAbsoluteFilePath(item.getTarget(), filename);
			
			// create new file for storing downloaded bytes
			File targetFile = new File(absoluteTargetFilename);
			targetFile.createNewFile();

			// write down downloaded bytes
			FileOutputStream out = new FileOutputStream(targetFile);
			out.write(data);
			out.flush();
			out.close();

			item.setTarget(absoluteTargetFilename);

		} catch (MalformedURLException ex) {
			logger.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	public void run()
	{
		currentUrlIndex = 0;

		while(isDownloading) {
			if(currentUrlIndex >= items.size()) {
				stopDownloading();
				break;
			}

			downloadSingle(items.get(currentUrlIndex));

			currentUrlIndex++;
		}
	}

}
