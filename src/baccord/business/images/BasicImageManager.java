package baccord.business.images;

import java.util.HashMap;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageManager implements ImageManager
{
	
	public final static String SIFT_EXTENSION = ".sift";
	
	private String imageMagickPath;
	private String jheadPath;
	private String siftPath;
	
	private HashMap<String, Float> camerasCcdWidths;
	
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public void setImageMagickPath(String path)
	{
		imageMagickPath = path;
	}
	
	public String getImageMagickPath()
	{
		return imageMagickPath;
	}
	
	public void setJheadPath(String path)
	{
		jheadPath = path;
	}
	
	public String getJheadPath()
	{
		return jheadPath;
	}
	
	public void setSiftPath(String path)
	{
		siftPath = path;
	}
	
	public String getSiftPath()
	{
		return siftPath;
	}
	
	public HashMap<String, Float> getCameraCcdWidths()
	{
		if(camerasCcdWidths == null) {
			// load from file and set to property
			
			// if file not exists, create new instance
		}
		
		return camerasCcdWidths;
	}
	
	public void setCameraCcdWidths(HashMap<String, Float> map)
	{
		camerasCcdWidths = map;
	}
	
	public void setCcdWidthForCamera(String camera, float width)
	{
		HashMap<String, Float> map = getCameraCcdWidths();
		map.put(camera, new Float(width));
		// save map to file as properties
	}
	
	public float getCcdWidthForCamera(String camera)
	{
		HashMap<String, Float> map = getCameraCcdWidths();
		return map.get(camera).floatValue();
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	public void resize(Image image, int width, int height)
	{
		// execute command on imagemagick
	}

	public boolean hasSift(Image image)
	{
		// create path to sift file
		// check if file is present
	}

	public void performSift(Image image)
	{
		// convert image to pgm
		
		// execute command on sift
		
		// remove pgm temp image
	}

	public int getFocalLegth(Image image)
	{
		// call jhead for exif info
		// or some java tool instead of jhead
		
		
	}
	
}
