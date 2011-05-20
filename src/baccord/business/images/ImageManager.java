
package baccord.business.images;

import baccord.exceptions.SiftAppMissingException;
import java.util.HashMap;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface ImageManager
{
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public void setSiftPath(String path) throws SiftAppMissingException;
	public String getSiftPath();
	
	public HashMap<String, Float> getCameraCcdWidths();
	public void setCameraCcdWidths(HashMap<String, Float> map);
	
	public void setCcdWidthForCamera(String camera, float width);
	public float getCcdWidthForCamera(String camera);
	
	public String getCameraCcdWidthsStoragePath();
	public void setCameraCcdWidthsStoragePath(String cameraCcdWidthsStoragePath);
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	/**
	 * Resize given image and save it to its original location.
	 * 
	 * @param image image to resize
	 * @param width target width
	 * @param height target height
	 */
	public void resize(Image image, int width, int height);
	
	/**
	 * Detect if SIFT was performed and keypoint file was created.
	 * 
	 * @param image
	 * @return true if SIFT was performed and keypoint file created, otherwise false
	 */
	public boolean hasSift(Image image);
	
	/**
	 * Using SIFT detector will detect keypoints and store them to file 
	 * in same directory as is the image located.
	 * 
	 * @param image image to detect
	 */
	public void performSift(Image image);
	
	/**
	 * Given entity will be filled with information about dimensiona and focal lenght
	 * 
	 * @param image 
	 */
	public void loadExifInformation(Image image);
	
}	
