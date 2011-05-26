
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
	
	public String getOutputDirectory();
	public void setOutputDirectory(String path);
	
	public String getImageListFilename();
	public void setImageListFilename(String path);
	
	public String getKeypointMatchTableFilename();
	public void setKeypointMatchTableFilename(String path);
	
	public String getKeypointMatcherPath();
	public void setKeypointMatcherPath(String path);
	
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
	
	public void loadExifInformation();
	public void createImageList() throws IOException;
	public void createMatchTable() throws IOException, InterruptedException;
	
	public boolean isRunning();
	public void start();
	public void stop();
}
