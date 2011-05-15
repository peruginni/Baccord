package baccord.business.search;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class SearchResult
{
	private int page;
	private int pages;
	private int perPage;
	private int total;
	private List<ResultItem> items;
	
	public SearchResult(int page, int pages, int perPage, int total)
	{
		this.page = page;
		this.pages = pages;
		this.perPage = perPage;
		this.total = total;
		
		items = new LinkedList<ResultItem>();
	}
	
	public int getPage()
	{
		return this.page;
	}
	
	public void setPage(int page)
	{
		this.page = page;
	}
	
	public int getPages()
	{
		return this.pages;
	}
	
	public void setPages(int pages)
	{
		this.pages = pages;
	}
	
	public int getPerPage()
	{
		return this.perPage;
	}
	
	public void setPerPage(int perPage)
	{
		this.perPage = perPage;
	}
	
	public int getTotal()
	{
		return this.total;
	}
	
	public void setTotal(int total)
	{
		this.total = total;
	}
	
	public List<ResultItem> getItems()
	{
		return this.items;
	}
	
	public void setItems(List<ResultItem> items)
	{
		this.items = items;
	}
	
	public void addItem(ResultItem item)
	{
		items.add(item);
	}
	
	public void addItem(String id, String secret, String server, String farm, String title)
	{
		ResultItem item = new ResultItem(id, secret, server, farm, title);
		items.add(item);
	}
	
}
