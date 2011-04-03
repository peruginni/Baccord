
package baccord.business.downloader;

import baccord.business.BaseBusiness;
import baccord.exceptions.CannotCreateDirectoryException;
import baccord.exceptions.PathMustNotBeDirectoryException;
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
	private List<String> downloadedUrls;
	private int currentUrlIndex;
	private String downloadDirectory;
	private boolean isDownloading;
	private Thread downloadingThread;

	private static final Logger logger = Logger.getLogger(BasicDownloadManager.class.getName());

	public BasicDownloadManager()
	{
		downloadedUrls = new LinkedList<String>();
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
		downloadedUrls.add(url);
	}

	public void removeUrl(String url)
	{
		downloadedUrls.remove(url);
	}

	public List<String> getDownloadedUrls()
	{
		return downloadedUrls;
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
		try {

			// while isdownloading


			// partialy inspired by http://www.java2s.com/Tutorial/Java/0320__Network/SavebinaryfilefromURL.htm
			// download bytes from internet
			// write bytes to file
			URL url = new URL(downloadedUrls.get(currentUrlIndex));
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
				throw new IOException("During downloading read only "+offset+" bytes, while expected "+contentLength+" bytes");
			}

			String filename = url.getFile();
			filename = filename.substring(filename.lastIndexOf('/')+1);

			// check if unique name in target folder
			FileOutputStream out = new FileOutputStream(filename);
			out.write(data);
			out.flush();
			out.close();
			
			/*
			URL u = new URL("http://www.java2s.com/binary.dat");
			URLConnection uc = u.openConnection();
			String contentType = uc.getContentType();
			int contentLength = uc.getContentLength();
			if (contentType.startsWith("text/") || contentLength == -1) {
			throw new IOException("This is not a binary file.");
			}
			InputStream raw = uc.getInputStream();
			InputStream in = new BufferedInputStream(raw);
			byte[] data = new byte[contentLength];
			int bytesRead = 0;
			int offset = 0;
			while (offset < contentLength) {
			bytesRead = in.read(data, offset, data.length - offset);
			if (bytesRead == -1)
			break;
			offset += bytesRead;
			}
			in.close();
			if (offset != contentLength) {
			throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
			}
			String filename = u.getFile().substring(filename.lastIndexOf('/') + 1);
			FileOutputStream out = new FileOutputStream(filename);
			out.write(data);
			out.flush();
			out.close();
			 *
			 */
		} catch (MalformedURLException ex) {
			logger.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}

	}

}
