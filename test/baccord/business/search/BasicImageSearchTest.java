package baccord.business.search;

import java.util.List;
import baccord.tools.DI;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageSearchTest
{
	/**
	 * Test of search method, of class BasicImageSearch.
	 */
	@Test
	public void testSearchByQuery()
	{
		System.out.println("searchByQuery");
		SearchQuery searchQuery = new SearchQuery();
		searchQuery.setKeywords("notre dame");
		
		ImageSearch instance = DI.get(ImageSearch.class);
		instance.setCurrentQuery(searchQuery);
		SearchResult result = instance.search();
		
		// test if recently used keyword was saved
		List<String> recentlyUsed = instance.getRecentlyUsedKeywords();
		String newest = recentlyUsed.get(0);
		assertEquals(searchQuery.getKeywords(), newest);
		
		// test if has result
		assertNotNull(result);
		
		assertFalse(result.getItems().isEmpty());
		for (ResultItem resultItem : result.getItems()) {
			assertNotNull(resultItem);
			// more extensively tested in tests for implementation of search engines
		}
	}

	/**
	 * Test of saveRecentlyUsedKeyword method, of class BasicImageSearch.
	 */
	@Test
	public void testSaveRecentlyUsedKeyword()
	{
		System.out.println("saveRecentlyUsedKeyword");
		String keyword = "notre dame";
		ImageSearch instance = DI.get(ImageSearch.class);
		
		instance.clearRecentlyUsed();
		instance.saveRecentlyUsedKeyword(keyword);
		File storage = new File(instance.getRecentKeywordsStoragePath());
		assertTrue(storage.exists());
		
		instance = new BasicImageSearch();
		List<String> recentlyUsed = instance.getRecentlyUsedKeywords();
		String newest = recentlyUsed.get(0);
		assertEquals(keyword, newest);
	}

	@Test
	public void testClearRecentlyUsed()
	{
		System.out.println("clearRecentlyused");
		
		ImageSearch instance = DI.get(ImageSearch.class);
		instance.saveRecentlyUsedKeyword("notre dame");
		
		File storage = new File(instance.getRecentKeywordsStoragePath());
		assertTrue(storage.exists());
		
		instance.clearRecentlyUsed();
		assertFalse(storage.exists());
		
	}
}
