
package baccord.business.search;

import baccord.tools.ObjectStorage;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageSearch implements ImageSearch
{
	private String storagePath = "./RecentSearchKeywords.dat";
	private int maximumRecentKeywords = 10;
	private Queue<String> recentKeywords;
	
	SearchEngine searchEngine;
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public void setSearchEngine(SearchEngine searchEngine)
	{
		this.searchEngine = searchEngine;
	}
	
	public SearchEngine getSearchEngine()
	{
		if(searchEngine == null) {
			this.searchEngine = new FlickrSearchEngine();
		}
		return this.searchEngine;
	}
	
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	public SearchResult searchByQuery(SearchQuery searchQuery)
	{
		saveRecentlyUsedKeyword(searchQuery.getKeywords());
		return getSearchEngine().searchByQuery(searchQuery);
	}
	
	public void saveRecentlyUsedKeyword(String keyword)
	{
		// add keyword to list
		Queue<String> list = getRecentlyUsedKeywords();
		list.offer(keyword);

		// remove older
		if(list.size() > maximumRecentKeywords) {
			list.poll();
		}

		ObjectStorage.save(list, storagePath);
	}
	
	public Queue<String> getRecentlyUsedKeywords()
	{
		if(recentKeywords == null) {
			try {
				recentKeywords = (Queue<String>) ObjectStorage.load(storagePath);
			} catch (FileNotFoundException ex) {
				// file was probably not yet created
			}

			if(recentKeywords == null) {
				recentKeywords = new LinkedList<String>();
			}
		}
		return recentKeywords;
	}
}
