package baccord.business.search;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class ResultItem
{
	private String id;
	private String secret;
	private String server;
	private String farm;
	private String title;
	
	public ResultItem()
	{
		
	}
	
	public ResultItem(String id, String secret, String server, String farm, String title)
	{
		this.id = id;
		this.secret = secret;
		this.server = server;
		this.farm = farm;
		this.title = title;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getSecret()
	{
		return this.secret;
	}
	
	public void setSecret(String secret)
	{
		this.secret = secret;
	}
	
	public String getServer()
	{
		return this.server;
	}
	
	public void setServer(String server)
	{
		this.server = server;
	}
	
	public String getFarm()
	{
		return this.farm;
	}
	
	public void setFarm(String farm)
	{
		this.farm = farm;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
}
