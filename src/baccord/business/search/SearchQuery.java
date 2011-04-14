
package baccord.business.search;

import java.util.Date;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class SearchQuery
{
	public String keywords;
	public String gpsLatitude;
	public String gpsLongitude;
	public int gpsRadius;
	public Date takenFrom;
	public Date takenTill;
	public Date uploadedFrom;
	public Date uploadedTill;
	public String license;
	public Context context;
	public int resultsPerPage;

	public enum Context
	{
		indoor("indoor"),
		outdoor("outdoor"),
		both("both");

		public final String name;
		Context(String name)
		{
			this.name = name;
		}
	}

	public String getKeywords()
	{
		return this.keywords;
	}

	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	public String getGpsLatitude()
	{
		return this.gpsLatitude;
	}

	public void setGpsLatitude(String gpsLatitude)
	{
		this.gpsLatitude = gpsLatitude;
	}
	
	public String getGpsLongitude()
	{
		return this.gpsLongitude;
	}

	public void setGpsLongitude(String gpsLongitude)
	{
		this.gpsLongitude = gpsLongitude;
	}
	
	public int getGpsRadius()
	{
		return this.gpsRadius;
	}

	public void setGpsRadius(int gpsRadius)
	{
		this.gpsRadius = gpsRadius;
	}
	
	public Date getTakenFrom()
	{
		return this.takenFrom;
	}

	public void setTakenFrom(Date takenFrom)
	{
		this.takenFrom = takenFrom;
	}
	
	public Date getTakenTill()
	{
		return this.takenTill;
	}

	public void setTakenTill(Date takenTill)
	{
		this.takenTill = takenTill;
	}
	
	public Date getUploadedFrom()
	{
		return this.uploadedFrom;
	}

	public void setUploadedFrom(Date uploadedFrom)
	{
		this.uploadedFrom = uploadedFrom;
	}
	
	public Date getUploadedTill()
	{
		return this.uploadedTill;
	}

	public void setUploadedTill(Date uploadedTill)
	{
		this.uploadedTill = uploadedTill;
	}
	
	public String getLicense()
	{
		return this.license;
	}

	public void setLicense(String license)
	{
		this.license = license;
	}
	
	public Context getContext()
	{
		return this.context;
	}

	public void setContext(Context context)
	{
		this.context = context;
	}

	public int getResultsPerPage()
	{
		return this.resultsPerPage;
	}

	public void setResultsPerPage(int resultsPerPage)
	{
		this.resultsPerPage = resultsPerPage;
	}
}

