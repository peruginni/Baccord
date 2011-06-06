
package baccord.business.search;

import baccord.tools.ObjectStorage;
import com.google.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

/**
 * Basic implementation of ImageSearch
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageSearch implements ImageSearch
{
	private String recentKeywordsStoragePath = "./RecentSearchKeywords.dat";
	private int maximumRecentKeywords = 10;
	private LinkedList<String> recentKeywords;
	
	SearchEngine searchEngine;
	SearchQuery currentQuery;
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public String getRecentKeywordsStoragePath()
	{
		return recentKeywordsStoragePath;
	}
	
	public void setRecentKeywordsStoragePath(String path)
	{
		recentKeywordsStoragePath = path;
	}

	@Inject
	public void setSearchEngine(SearchEngine searchEngine)
	{
		this.searchEngine = searchEngine;
	}
	
	public SearchEngine getSearchEngine()
	{
		return this.searchEngine;
	}
	
	public SearchQuery getCurrentQuery()
	{
		return this.currentQuery;
	}
	
	@Inject
	public void setCurrentQuery(SearchQuery currentQuery)
	{
		this.currentQuery = currentQuery;
	}
	
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	public SearchResult search()
	{
		saveRecentlyUsedKeyword(currentQuery.getKeywords());
		return getSearchEngine().searchByQuery(currentQuery);
	}
	
	public void saveRecentlyUsedKeyword(String keyword)
	{
		getRecentlyUsedKeywords();
		
		// add keyword to list
		recentKeywords.addFirst(keyword);

		// remove older
		if(recentKeywords.size() > maximumRecentKeywords) {
			recentKeywords.removeLast();
		}

		ObjectStorage.save(recentKeywords, recentKeywordsStoragePath);
	}
	
	public List<String> getRecentlyUsedKeywords()
	{
		if(recentKeywords == null) {
			try {
				recentKeywords = (LinkedList<String>) ObjectStorage.load(recentKeywordsStoragePath);
			} catch (FileNotFoundException ex) {
				// file was probably not yet created
			}

			if(recentKeywords == null) {
				recentKeywords = new LinkedList<String>();
			}
		}
		
		return recentKeywords;
	}
	
	public void clearRecentlyUsed()
	{
		File file = new File(recentKeywordsStoragePath);
		if(file.exists()) {
			file.delete();
		}
		recentKeywords = new LinkedList();
	}
	
}
