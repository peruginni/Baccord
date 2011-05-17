
package baccord.business.search;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class FlickrSearchEngine implements SearchEngine
{
	private String key;
	private String secret;
	private String apiDetailsStorage = "./FlickrApi.properties";

	public FlickrSearchEngine()
	{
		loadApiDetails();
	}
	
	private void loadApiDetails()
	{
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(apiDetailsStorage);
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			Properties p = new Properties();
			p.load(bin);
			key = p.getProperty("key");
			secret = p.getProperty("secret");
				
		} catch (FileNotFoundException ex) {
			Logger.getLogger(FlickrSearchEngine.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(FlickrSearchEngine.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				fin.close();
			} catch (IOException ex) {
				Logger.getLogger(FlickrSearchEngine.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public SearchResult searchByQuery(SearchQuery searchQuery)
	{
		SearchResult result = null;
		BufferedReader in = null;
		JSONObject json = null;
		Date date = null;
		String string = null;
		int integer = 0;
		
		try {	
			StringBuilder query = new StringBuilder();
			query.append("http://api.flickr.com/services/rest/");
			query.append("?method=flickr.photos.search");
			query.append("&api_key=");
			query.append(key);
			query.append("&text=");
			query.append(URLEncoder.encode(searchQuery.getKeywords(), "ISO-8859-1"));
			
			if((date = searchQuery.getUploadedFrom()) != null) {
				query.append("&min_upload_date=");
				query.append(date.getTime());
			}
			
			if((date = searchQuery.getUploadedTill()) != null) {
				query.append("&max_upload_date=");
				query.append(date.getTime());
			}
			
			if((date = searchQuery.getTakenFrom()) != null) {
				query.append("&min_taken_date=");
				query.append(date.getTime());
			}
			
			if((date = searchQuery.getTakenTill()) != null) {
				query.append("&max_taken_date=");
				query.append(date.getTime());
			}
			
			//query.append("&license=");
			
			SearchQuery.GeoContext geoContext;
			if((geoContext = searchQuery.getGeoContext()) != null) {
				query.append("&geo_context=");
				int id;
				switch(geoContext) {
					case indoor: id = 1; break;
					case outdoor: id = 2; break;
					case both:
					default: id = 0;
				}
				query.append(id);
			}
			
			if((string = searchQuery.getGpsLatitude()) != null) {
				query.append("&lat=");
				query.append(string);
			}
			
			if((string = searchQuery.getGpsLongitude()) != null) {
				query.append("&lon=");
				query.append(string);
			}
			
			if((integer = searchQuery.getGpsRadius()) != 0) {
				query.append("&radius=");
				query.append(integer);
			}
			
			query.append("&per_page=");
			if((integer = searchQuery.getResultsPerPage()) != 0) {
				query.append(integer);
			} else {
				query.append(100);
			}
			
			if((integer = searchQuery.getPage()) != 0) {
				query.append("&page=");	
				query.append(integer);
			}
			
			query.append("&format=json&nojsoncallback=1");
			
			URL url = new URL(query.toString());
			URLConnection connection = url.openConnection();
			in = new BufferedReader(
				new InputStreamReader(
					connection.getInputStream()
				)
			);
			
			json = (JSONObject) new JSONParser().parse(in);
			
		} catch (ParseException ex) {
			Logger.getLogger(FlickrSearchEngine.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(FlickrSearchEngine.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MalformedURLException ex) {
			Logger.getLogger(FlickrSearchEngine.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(FlickrSearchEngine.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(FlickrSearchEngine.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		if(json != null && ((String)json.get("stat")).equals("ok")) {
			
			JSONObject jsonResult = (JSONObject) json.get("photos");
			
			int total = Integer.parseInt((String)jsonResult.get("total"));
			int page = ((Long)jsonResult.get("page")).intValue();
			int pages = ((Long)jsonResult.get("pages")).intValue();
			int perPage = ((Long)jsonResult.get("perpage")).intValue();
			
			if(total > 0) {
				
				result = new SearchResult(page, pages, perPage, total);
				
				JSONArray photos = (JSONArray)jsonResult.get("photo");
				JSONObject photo = null;
				
				String photoId, photoTitle, photoSecret, photoServer, photoFarm;
				
				for(Object object : photos) {
					photo = (JSONObject) object;
					
					photoId = (String)photo.get("id");
					photoTitle = (String)photo.get("title");
					photoSecret = (String)photo.get("secret");
					photoServer = (String)photo.get("server");
					photoFarm = ((Long)photo.get("farm")).toString();
					
					result.addItem(photoId, photoSecret, photoServer, photoFarm, photoTitle);
				}
				
			}
						
		} else if (json != null) {
			Logger.getLogger(FlickrSearchEngine.class.getName()).log(Level.SEVERE, (String)json.get("message"));
		}
		
		return result;
	}

	public String getImageOriginalUrl(ResultItem item)
	{
		// http://www.flickr.com/services/api/misc.urls.html
		
		StringBuilder result = new StringBuilder();
		result.append("http://farm");
		result.append(item.getFarm());
		result.append(".static.flickr.com/");
		result.append(item.getServer());
		result.append("/");
		result.append(item.getId());
		result.append("_");
		result.append(item.getSecret());
		result.append("_b.jpg");
		
		return result.toString();
	}

	public String getImageThumbnailUrl(ResultItem item)
	{
		// http://www.flickr.com/services/api/misc.urls.html
		
		StringBuilder result = new StringBuilder();
		result.append("http://farm");
		result.append(item.getFarm());
		result.append(".static.flickr.com/");
		result.append(item.getServer());
		result.append("/");
		result.append(item.getId());
		result.append("_");
		result.append(item.getSecret());
		result.append("_s.jpg");
		
		return result.toString();
	}

}
