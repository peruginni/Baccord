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
			assertNotNull(item.getId());
			assertNotNull(item.getTitle());
			assertNotNull(item.getFarm());
			assertNotNull(item.getSecret());
			assertNotNull(item.getServer());
		}
	}
}
