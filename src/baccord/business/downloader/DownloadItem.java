
package baccord.business.downloader;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class DownloadItem
{	
	public static final int WAITING = 0;
	public static final int DOWNLOADING = 1;
	public static final int FINISHED = 2;
	public static final int SKIPPED = 3;
	
	private String source;
	private String target;
	private String targetDirectory;
	private int status;

	public DownloadItem()
	{
		this("", "", WAITING);
	}

	public DownloadItem(String source)
	{
		this(source, "", WAITING);
	}

	public DownloadItem(String source, String targetDirectory, int status)
	{
		this.source = source;
		this.targetDirectory = targetDirectory;
		this.status = status;
	}

	public String getSource()
	{
		return this.source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getTarget()
	{
		return this.target;
	}

	public void setTarget(String target)
	{
		this.target = target;
	}

	public String getTargetDirectory()
	{
		return this.targetDirectory;
	}

	public void setTargetDirectory(String targetDirectory)
	{
		this.targetDirectory = targetDirectory;
	}

	public int getStatus()
	{
		return this.status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
	
	public boolean isFinished()
	{
		return status == FINISHED;
	}
	
	public boolean isWaiting()
	{
		return status == WAITING;
	}
	
	public boolean isDownloading()
	{
		return status == DOWNLOADING;
	}
	
	public boolean isSkipped()
	{
		return status == SKIPPED;
	}
}
