
package baccord.business.search;

import java.util.List;


/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface ImageSearch
{
	public void setSearchEngine(SearchEngine searchEngine);
	public List<Object> searchByQuery(SearchQuery searchQuery);
	public void saveRecentlyUsedKeyword(String keyword);
	public List<String> getRecentlyUsedKeywords();
}
