
package baccord.business.images;

import baccord.business.settings.Settings;
import baccord.tools.Observable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Ensure essential manipulation with images, such as resizing, detecting 
 * focal length, performing sift, testing if image has sift already computed.
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface ImageManager extends Observable
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
	
	public String getJheadPath();
	public void setJheadPath(String jheadPath);
	
	public Map<String, Double> getCcdWidths();
	public void setCcdWidths(Map<String, Double> map);
	public void addCcdWidth(String camera, double width);
	public double getCcdWidth(String camera);
	
	public String getCcdWidthsStoragePath();
	public void setCcdWidthsStoragePath(String cameraCcdWidthsStoragePath);
	
	public String getSiftExtension();
	public void setSiftExtension(String e);
	public String getPgmExtension();
	public void setPgmExtension(String e);
	
	public Set<Process> getActiveProcesses();
	
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
	
	/**
	 * Rename multiple given images to lowercase
	 * @param images 
	 */
	public void batchRenameImagesToLowerCase(List<Image> images);
	
}	
