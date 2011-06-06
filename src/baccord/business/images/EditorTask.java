package baccord.business.images;

import java.awt.Dimension;

/**
 * Holds informations about changes or actions performed on image
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class EditorTask
{
	private Image image;
	private boolean performSift;
	private Dimension resizeDimension;
	
	public EditorTask(Image image, boolean sift, Dimension dimension)
	{
		this.image = image;
		this.performSift = sift;
		this.resizeDimension = dimension;
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public Image getImage()
	{
		return this.image;
	}
	
	public void setImage(Image image)
	{
		this.image = image;
	}
	
	public boolean performSift()
	{
		return this.performSift;
	}
	
	public void setPerformSift(boolean sift)
	{
		this.performSift = sift;
	}
	
	public Dimension getResizeDimension()
	{
		return this.resizeDimension;
	}
	
	public void setResizeDimension(Dimension resizeDimension)
	{
		this.resizeDimension = resizeDimension;
	}
}
