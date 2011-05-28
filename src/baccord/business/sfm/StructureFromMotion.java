
package baccord.business.sfm;

import baccord.business.images.Editor;
import baccord.business.images.Image;
import baccord.business.settings.Settings;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface StructureFromMotion
{
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public Settings getSettings();
	public void setSettings(Settings settings);
	
	public Editor getEditor();
	public void setEditor(Editor editor);
	
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
	
	public void addImagesFromDirectory(String directory);
	public void loadExifInformation();
	public void createImageList() throws IOException;
	public void createMatchTable() throws IOException, InterruptedException;
	public void performSfm() throws IOException, InterruptedException;
	
	public boolean isRunning();
	public void start();
	public void stop();
}
