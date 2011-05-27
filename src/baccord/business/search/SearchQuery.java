
package baccord.business.search;

import java.util.Date;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class SearchQuery
{
	private String keywords;
	private String gpsLatitude;
	private String gpsLongitude;
	private int gpsRadius;
	private Date takenFrom;
	private Date takenTill;
	private Date uploadedFrom;
	private Date uploadedTill;
	private String license;
	private GeoContext geoContext;
	private int page;
	private int resultsPerPage;

	public enum GeoContext
	{
		both("both"),
		indoor("indoor"),
		outdoor("outdoor");

		public final String name;
		GeoContext(String name)
		{
			this.name = name;
		}
	}
	
	// http://www.flickr.com/services/api/flickr.photos.licenses.getInfo.html
	public enum License
	{
		ccAtribution("Attribution License"),
		ccAtributionNoDerivs("Attribution-NoDerivs License"),
		ccAtributionNonCommercialNoDerivs("Attribution-NonCommercial-NoDerivs License"),
		ccAtributionNonCommercial("Attribution-NonCommercial License"),
		ccAtributionNonCommercialShareAlike("Attribution-NonCommercial-ShareAlike License"),
		ccAtributionShareAlike("Attribution-ShareAlike License"),
		noKnownRestrictions("No known copyright restrictions");
		
		public final String name;
		License(String name)
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
		if(gpsLatitude != null && gpsLatitude.isEmpty()) {
			gpsLatitude = null;
		}
		this.gpsLatitude = gpsLatitude;
	}
	
	public String getGpsLongitude()
	{
		return this.gpsLongitude;
	}

	public void setGpsLongitude(String gpsLongitude)
	{
		if(gpsLongitude != null && gpsLongitude.isEmpty()) {
			gpsLongitude = null;
		}
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
	
	public GeoContext getGeoContext()
	{
		return this.geoContext;
	}

	public void setGeoContext(GeoContext context)
	{
		this.geoContext = context;
	}
	
	public int getPage()
	{
		return this.page;
	}
	
	public void setPage(int page)
	{
		this.page = page;
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

