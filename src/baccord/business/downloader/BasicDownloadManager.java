
package baccord.business.downloader;

import baccord.business.BaseBusiness;
import baccord.exceptions.CannotCreateDirectoryException;
import baccord.exceptions.PathMustBeDirectoryException;
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
	private Thread downloadThread;

	private static final Logger logger = Logger.getLogger(BasicDownloadManager.class.getName());

	public BasicDownloadManager()
	{
		items = new LinkedList<DownloadItem>();
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */

	public void setDownloadDirectory(String path) throws CannotCreateDirectoryException, PathMustBeDirectoryException
	{
		File directory = new File(path);

		if(!directory.exists()) {
			if(!directory.mkdirs()) {
				throw new CannotCreateDirectoryException();
			}
		} else {
			if(directory.isFile()) {
				throw new PathMustBeDirectoryException();
			}
		}

		downloadDirectory = path;
	}
	
	public String getDownloadDirectory()
	{
		return downloadDirectory;
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */

	public void add(DownloadItem item)
	{
		item.setTargetDirectory(downloadDirectory);
		items.add(item);
	}

	public void add(List<String> listOfUrls)
	{
		for(String url : listOfUrls) {
			DownloadItem item = new DownloadItem(url);
			add(item);
		}
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
		if(!isDownloading) {
			isDownloading = true;
			downloadThread = new Thread(this);
			downloadThread.start();
		}
		
		//if(downloadThread != null && downloadThread.isAlive() == false) {
		//}
		
	}

	public void stopDownloading()
	{
		if(isDownloading) {
			isDownloading = false;
			downloadThread.interrupt();
		}
	}

	public void downloadSingle(DownloadItem item)
	{
		try {
			if(item.getTargetDirectory() == null) {
				item.setTargetDirectory(downloadDirectory);
			}
			
			// inspired by http://www.java2s.com/Tutorial/Java/0320__Network/SavebinaryfilefromURL.htm

			item.setStatus(DownloadItem.Status.downloading);

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
			filename = FileHelper.generateUniqueFilename(item.getTargetDirectory(), filename);
			String absoluteTargetFilename = FileHelper.getAbsoluteFilePath(item.getTargetDirectory(), filename);
			
			// create new file for storing downloaded bytes
			File targetFile = new File(absoluteTargetFilename);
			targetFile.createNewFile();
			
			// write down downloaded bytes
			FileOutputStream out = new FileOutputStream(targetFile);
			out.write(data);
			out.flush();
			out.close();

			item.setTarget(absoluteTargetFilename);
			item.setStatus(DownloadItem.Status.done);

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
