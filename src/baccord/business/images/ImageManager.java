
package baccord.business.images;

import baccord.business.settings.Settings;
import java.util.List;
import java.util.Map;

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
	
	public Settings getSettings();
	public void setSettings(Settings settings);
	
	public String getSiftPath();
	public void setSiftPath(String path);
	
	public String getConvertPath();
	public void setConvertPath(String convertPath);
	
	public String getMogrifyPath();
	public void setMogrifyPath(String mogrifyPath);
	
	public Map<String, Float> getCcdWidths();
	public void setCcdWidths(Map<String, Float> map);
	public void addCcdWidth(String camera, float width);
	public float getCcdWidth(String camera);
	
	public String getCcdWidthsStoragePath();
	public void setCcdWidthsStoragePath(String cameraCcdWidthsStoragePath);
	
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
	
	/**
	 * Scan directory for jpg images, for each will load exif information
	 * and return results as list
	 * 
	 * @param directory to scan
	 * @return list of found images
	 */
	public List<Image> loadImagesFromDirectory(String directory, boolean loadExif);
	
}	
