
package baccord.business.search;

/**
 * Interface for search engines used in image search
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface SearchEngine
{
	/**
	 * Will search according to given query.
	 * 
	 * @param searchQuery
	 * @return null if no results
	 */
	public SearchResult searchByQuery(SearchQuery searchQuery);
	
	/**
	 * Return path to original image
	 * @param item
	 * @return 
	 */
	public String getImageOriginalPath(ResultItem item);
	
	/**
	 * Return path to big image
	 * @param item
	 * @return 
	 */
	public String getImageBigPath(ResultItem item);
	
	/**
	 * Return path to thumbnail of image
	 */
	public String getImageThumbnailPath(ResultItem item);
}
