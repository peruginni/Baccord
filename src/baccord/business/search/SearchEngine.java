
package baccord.business.search;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface SearchEngine
{
	public SearchResult searchByQuery(SearchQuery searchQuery);
}
