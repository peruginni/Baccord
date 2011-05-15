package baccord.business.search;

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
	 * Test of setSearchEngine method, of class BasicImageSearch.
	 */
	@Test
	public void testSetSearchEngine()
	{
		System.out.println("setSearchEngine");
		SearchEngine searchEngine = new FlickrSearchEngine();
		BasicImageSearch instance = new BasicImageSearch();
		instance.setSearchEngine(searchEngine);
		assertEquals(instance.getSearchEngine(), searchEngine);
	}

	/**
	 * Test of getSearchEngine method, of class BasicImageSearch.
	 */
	@Test
	public void testGetSearchEngine()
	{
		System.out.println("getSearchEngine");
		BasicImageSearch instance = new BasicImageSearch();
		SearchEngine result = instance.getSearchEngine();
		assertTrue(result instanceof SearchEngine);
	}

	/**
	 * Test of searchByQuery method, of class BasicImageSearch.
	 */
	@Test
	public void testSearchByQuery()
	{
		System.out.println("searchByQuery");
		SearchQuery searchQuery = new SearchQuery();
		searchQuery.setKeywords("notre dame");
		
		BasicImageSearch instance = new BasicImageSearch();
		SearchResult result = instance.searchByQuery(searchQuery);
		
		assertEquals(instance.getRecentlyUsedKeywords().getLast(), searchQuery.getKeywords());
	}

	/**
	 * Test of saveRecentlyUsedKeyword method, of class BasicImageSearch.
	 */
	@Test
	public void testSaveRecentlyUsedKeyword()
	{
		System.out.println("saveRecentlyUsedKeyword");
		String keyword = "notre dame";
		BasicImageSearch instance = new BasicImageSearch();
		
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
		
		ImageSearch instance = new BasicImageSearch();
		instance.saveRecentlyUsedKeyword("notre dame");
		
		File storage = new File(instance.getStoragePath());
		assertTrue(storage.exists());
		
		instance.clearRecentlyUsed();
		assertFalse(storage.exists());
		
	}
}
