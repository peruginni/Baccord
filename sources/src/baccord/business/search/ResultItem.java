package baccord.business.search;

/**
 * Represent one single result item. Holds id, secret, secret for original, server, farm and title
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class ResultItem
{
	private String id;
	private String secret;
	private String secretOriginal;
	private String server;
	private String farm;
	private String title;
	
	public ResultItem()
	{
		
	}
	
	public ResultItem(String id, String secret, String secretOriginal, String server, String farm, String title)
	{
		this.id = id;
		this.secret = secret;
		this.secretOriginal = secretOriginal;
		this.server = server;
		this.farm = farm;
		this.title = title;
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
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
	
	public String getSecretOriginal()
	{
		return this.secretOriginal;
	}
	
	public void setSecretOriginal(String secretOriginal)
	{
		this.secretOriginal = secretOriginal;
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
