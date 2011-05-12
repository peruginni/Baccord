
package baccord.business.downloader;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class DownloadItem
{	
	private String source;
	private String target;
	private String targetDirectory;
	private Status status;

	public enum Status
	{
		waiting,
		downloading,
		done,
	}

	public DownloadItem()
	{
		this("", "", Status.waiting);
	}

	public DownloadItem(String source)
	{
		this(source, "", Status.waiting);
	}

	public DownloadItem(String source, String targetDirectory, Status status)
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

	public Status getStatus()
	{
		return this.status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}
}
