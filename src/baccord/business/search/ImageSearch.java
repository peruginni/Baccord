
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
	
	public void setSearchEngine(SearchEngine searchEngine);
	public SearchEngine getSearchEngine();
	
	
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
	public SearchResult searchByQuery(SearchQuery searchQuery);
	
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
	public Queue<String> getRecentlyUsedKeywords();
}
