
package baccord.business.sfm;

import baccord.business.images.Image;
import baccord.business.images.ImageManager;
import baccord.business.settings.Settings;
import baccord.tools.Observable;
import java.io.IOException;
import java.util.List;

/**
 * Manager wrapping structure from motion software. Will give control over starting
 * stopping process. Will be able to set options. Also give prescription of all necessary
 * steps to be done in order to successfully wrap SfM software.
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface StructureFromMotion extends Observable
{
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public Settings getSettings();
	public void setSettings(Settings settings);
	
	public ImageManager getImageManager();
	public void setImageManager(ImageManager im);
	
	public String getBundlerPath();
	public void setBundlerPath(String bundlerPath);

	public String getKeypointMatcherPath();
	public void setKeypointMatcherPath(String path);
	
	public String getExistingBundlePath();
	public void setExistingBundlePath(String path);
	
	public String getOutputDirectory();
	public void setOutputDirectory(String path);
	
	public String getImageListFocalFilename();
	public void setImageListFocalFilename(String path);
	
	public String getImageListExtraFilename();
	public void setImageListExtraFilename(String imageListExtraFilename);
	
	public String getImageListBasicFilename();
	public void setImageListBasicFilename(String path);
	
	public String getImageDirectory();
	public void setImageDirectory(String dir);
	
	public String getImageListKeysFilename();
	public void setImageListKeysFilename(String path);
	
	public String getKeypointMatchTableFilename();
	public void setKeypointMatchTableFilename(String filename);
	
	public List<Image> getImages();
	public void setImages(List<Image> images);
	
	public String getOptions();
	public void setOptions(String options);
	
	public String getReport();
	public void setReport(String report);
	public void addReport(String line);
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	/**
	 * Load images from given directory into Image object in list. Will also save
	 * given directory as image directory.
	 * 
	 * @param directory 
	 */
	public void loadImagesFromDirectory(String directory);
	
	/**
	 * Ensure lowercase of image files extensions and perform sift if necessary
	 */
	public void assureLowerCaseAndSift();
	
	/**
	 * Will load focal length and other exif information for all images in list
	 */
	public void loadExifInformation();
	
	/**
	 * Will create file with list of images necessary for wrapped underlying software
	 * 
	 * @throws IOException 
	 */
	public void createImageList() throws IOException;
	
	/**
	 * Perform matching between found keypoints and write them into file
	 * 
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void createMatchTable() throws IOException, InterruptedException;
	
	/** 
	 * Perform structure from motion itself
	 * 
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void performSfm() throws IOException, InterruptedException;
	
	/**
	 * Test if process of sfm is running
	 * @return 
	 */
	public boolean isRunning();
	
	/**
	 * Start SFM process
	 */
	public void start();
	
	/**
	 * Stop current SFM process
	 */
	public void stop();
}
