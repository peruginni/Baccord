
package baccord.business.downloader;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class DownloadItem
{	
	private String source;
	private String target;
	private Status status;

	public enum Status
	{
		WAITING,
		DOWNLOADING,
		DONE,
	}

	public DownloadItem()
	{
		this("", "", Status.WAITING);
	}

	public DownloadItem(String source)
	{
		this(source, "", Status.WAITING);
	}

	public DownloadItem(String source, String target, Status status)
	{
		this.source = source;
		this.target = target;
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

	public Status getStatus()
	{
		return this.status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}
}
