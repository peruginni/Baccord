package baccord.business.search;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class FlickrSearchEngineTest
{
	
	/**
	 * Test of searchByQuery method, of class FlickrSearchEngine.
	 */
	@Test
	public void testSearchByQuery()
	{
		System.out.println("searchByQuery");
		
		FlickrSearchEngine instance = new FlickrSearchEngine();
		SearchQuery searchQuery;
		SearchResult result;
		
		searchQuery = new SearchQuery();
		searchQuery.setKeywords("notre dame");
		result = instance.searchByQuery(searchQuery);
		assertNotNull(result);
		validateResult(result);
		
		searchQuery = new SearchQuery();
		searchQuery.setKeywords("2930alksd20sdlkfj0239jxckvzx293adfnadf2938");
		result = instance.searchByQuery(searchQuery);
		assertNull(result);
		
	}
	
	private void validateResult(SearchResult result)
	{
		if(result == null) return;
		
		if(result.getTotal() > 0) {
			assertFalse(result.getItems().isEmpty());
		}
		
		for(ResultItem item : result.getItems()) {
			assertNotNull(item);
			assertNotNull(item.getId());
			assertNotNull(item.getTitle());
			assertNotNull(item.getFarm());
			assertNotNull(item.getSecret());
			assertNotNull(item.getServer());
			// TODO: ping the image (test that there is no 404)
		}
	}
	
	@Test
	public void testImageUrls()
	{
		FlickrSearchEngine instance = new FlickrSearchEngine();
		SearchQuery searchQuery;
		SearchResult result;
		
		searchQuery = new SearchQuery();
		searchQuery.setKeywords("notre dame");
		searchQuery.setResultsPerPage(1);
		result = instance.searchByQuery(searchQuery);
		assertNotNull(result);
		
		ResultItem item = result.getItems().get(0);
		
		String originalUrl = instance.getImageOriginalPath(item);
		String originalUrlExpected = "http://farm"+item.getFarm()+".static.flickr.com/"
			+item.getServer()+"/"+item.getId()+"_"+item.getSecret()+"_o.jpg";
		assertEquals(originalUrl, originalUrlExpected);
		
		String thumbnailUrl = instance.getImageThumbnailPath(item);
		String thumbnailUrlExpected = "http://farm"+item.getFarm()+".static.flickr.com/"
			+item.getServer()+"/"+item.getId()+"_"+item.getSecret()+"_s.jpg";
		assertEquals(thumbnailUrl, thumbnailUrlExpected);
	}
}
