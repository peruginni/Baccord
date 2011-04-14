
package baccord.business.search;

import java.util.List;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageSearch implements ImageSearch
{

	public void setSearchEngine(SearchEngine searchEngine)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public List<Object> searchByQuery(SearchQuery searchQuery)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void saveRecentlyUsedKeyword(String keyword)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public List<String> getRecentlyUsedKeywords()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
