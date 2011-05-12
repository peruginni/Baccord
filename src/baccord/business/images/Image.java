
package baccord.business.images;

/**
 *
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class Image
{
	private String path;
	private int width;
	private int height;
	private float focalLength;

	public String getPath()
	{
		return this.path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public int getWidth()
	{
		return this.width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return this.height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}
}
