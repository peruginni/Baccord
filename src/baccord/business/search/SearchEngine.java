
package baccord.business.search;

import java.util.List;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface SearchEngine
{
	public List<Object> searchByQuery(SearchQuery searchQuery);
}
