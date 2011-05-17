
package baccord.business.search;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		
		// build query
		// URL: http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=48ab6fd2466d9c98f20abe22ddfe2ee0&text=notre+dame&format=json&nojsoncallback=1
		
		

		
		result = new SearchResult(page, pages, perPage, total);
		
		
		return result;
	}

}
