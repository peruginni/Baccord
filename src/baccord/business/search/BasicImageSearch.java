
package baccord.business.search;

import baccord.tools.ObjectStorage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageSearch implements ImageSearch
{
	private String storagePath = "./RecentSearchKeywords.dat";
	private int maximumRecentKeywords = 10;
	private LinkedList<String> recentKeywords;
	
	SearchEngine searchEngine;
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public String getStoragePath()
	{
		return storagePath;
	}
	
	public void setStoragePath(String path)
	{
		storagePath = path;
	}

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
		LinkedList<String> list = getRecentlyUsedKeywords();
		list.addLast(keyword);

		// remove older
		if(list.size() > maximumRecentKeywords) {
			list.removeFirst();
		}

		ObjectStorage.save(list, storagePath);
	}
	
	public LinkedList<String> getRecentlyUsedKeywords()
	{
		if(recentKeywords == null) {
			try {
				recentKeywords = (LinkedList<String>) ObjectStorage.load(storagePath);
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
		File file = new File(storagePath);
		if(file.exists()) {
			file.delete();
		}
		recentKeywords = new LinkedList();
	}
	
}
