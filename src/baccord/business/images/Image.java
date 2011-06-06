
package baccord.business.images;

/**
 * Representing image information. Hold path to image and width,height,focal length of image.
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class Image implements Comparable<Image>
{
	private String path;
	private int width;
	private int height;
	private double focalLength;
	
	public Image() 
	{
		
	}
	
	public Image(String path)
	{
		this.path = path;
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
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
	
	public double getFocalLength()
	{
		return this.focalLength;
	}
	
	public void setFocalLength(double focalLength)
	{
		this.focalLength = focalLength;
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Implementation of Comparable
	 * --------------------------------------------------------------------
	 */

	public int compareTo(Image o)
	{
		return path.compareTo(o.getPath());
	}
	
}
