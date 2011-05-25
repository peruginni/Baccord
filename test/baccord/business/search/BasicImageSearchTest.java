package baccord.business.search;

import baccord.tools.DI;
import java.util.LinkedList;
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
	 * Test of searchByQuery method, of class BasicImageSearch.
	 */
	@Test
	public void testSearchByQuery()
	{
		System.out.println("searchByQuery");
		SearchQuery searchQuery = new SearchQuery();
		searchQuery.setKeywords("notre dame");
		
		ImageSearch instance = DI.get(ImageSearch.class);
		SearchResult result = instance.searchByQuery(searchQuery);
		
		// test if recently used keyword was saved
		assertEquals(instance.getRecentlyUsedKeywords().getLast(), searchQuery.getKeywords());
		
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
		File storage = new File(instance.getStoragePath());
		assertTrue(storage.exists());
		
		instance = new BasicImageSearch();
		LinkedList<String> folders = instance.getRecentlyUsedKeywords();
		assertEquals(folders.getLast(), keyword);
	}

	@Test
	public void testClearRecentlyUsed()
	{
		System.out.println("clearRecentlyused");
		
		ImageSearch instance = DI.get(ImageSearch.class);
		instance.saveRecentlyUsedKeyword("notre dame");
		
		File storage = new File(instance.getStoragePath());
		assertTrue(storage.exists());
		
		instance.clearRecentlyUsed();
		assertFalse(storage.exists());
		
	}
}
