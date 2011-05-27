
package baccord.business.search;

import java.util.List;


/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface ImageSearch
{
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public String getRecentKeywordsStoragePath();
	public void setRecentKeywordsStoragePath(String path);
	
	public void setSearchEngine(SearchEngine searchEngine);
	public SearchEngine getSearchEngine();
	
	public SearchQuery getCurrentQuery();
	public void setCurrentQuery(SearchQuery currentQuery);
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	/** 
	 * Will search images by query and return found results.
	 * 
	 * @param searchQuery
	 * @return 
	 */
	public SearchResult search();
	
	/**
	 * Save keywords recently used in search query
	 * 
	 * @param keyword 
	 */
	public void saveRecentlyUsedKeyword(String keyword);
	
	/**
	 * Return keywords recently used in search query
	 * 
	 * @return 
	 */
	public List<String> getRecentlyUsedKeywords();
	
	/**
	 * Clear recently used keywords
	 */
	public void clearRecentlyUsed();
}
