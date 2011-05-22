
package baccord.business.sfm;

import baccord.business.BaseBusiness;
import baccord.business.images.Editor;
import baccord.business.images.Image;
import baccord.business.images.ImageManager;
import baccord.business.settings.Settings;
import baccord.tools.FileHelper;
import com.google.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class Bundler extends BaseBusiness implements StructureFromMotion, Runnable, Observer
{
	private Settings settings;
	private Editor editor;
	private String outputDirectory;
	private String imageListFilename = "imageList.txt";
	private String imageListBasicFilename = "imageListBasic.txt";
	private String keypointMatchTableFilename = "matchTable.txt";
	private String keypointMatcherPath;
	private String options;
	private StringBuilder report;
	private List<Image> images;
	private boolean isRunning;
	private Thread thread;

	public Bundler()
	{
		report = new StringBuilder();
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public Settings getSettings()
	{
		return this.settings;
	}
	
	@Inject
	public void setSettings(Settings settings)
	{
		this.settings = settings;
		settings.addObserver(this);
		setKeypointMatcherPath(settings.get(Settings.KEYPOINT_MATCHER_PATH));
	}
	
	public Editor getEditor()
	{
		return editor;
	}

	@Inject
	public void setEditor(Editor editor)
	{
		this.editor = editor;
	}

	public String getOutputDirectory()
	{
		return outputDirectory;
	}

	public void setOutputDirectory(String path)
	{
		this.outputDirectory = path;
	}

	public String getImageListFilename()
	{
		return imageListFilename;
	}

	public void setImageListFilename(String path)
	{
		this.imageListFilename = path;
	}

	public String getKeypointMatchTableFilename()
	{
		return keypointMatchTableFilename;
	}

	public void setKeypointMatchTableFilename(String path)
	{
		this.keypointMatchTableFilename = path;
	}

	public String getKeypointMatcherPath()
	{
		return keypointMatcherPath;
	}

	public void setKeypointMatcherPath(String path)
	{
		this.keypointMatcherPath = path;
	}

	public List<Image> getImages()
	{
		return images;
	}

	public void setImages(List<Image> images)
	{
		this.images = images;
	}
	
	public String getOptions()
	{
		return options;
	}
	
	public void setOptions(String options)
	{
		this.options = options;
	}
	
	public String getReport()
	{
		return report.toString();
	}
	
	public void setReport(String report)
	{
		this.report = new StringBuilder(report);
	}
	
	public void addReport(String line)
	{
		report.append(line);
		report.append("\n");
		notifyObservers();
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */

	public void loadExifInformation()
	{
		addReport("Loading focal information");
		ImageManager imageManager = editor.getImageManager();
		
		for (Image image : images) {
			imageManager.loadExifInformation(image);
		}
	}
	
	public void createImageList() throws IOException
	{
		addReport("Writting down focal information");
		
		File file = new File(FileHelper.mergePath(outputDirectory, imageListFilename));
		if(!file.exists()) {
			file.createNewFile();
		}
		File file2 = new File(FileHelper.mergePath(outputDirectory, imageListBasicFilename));
		if(!file2.exists()) {
			file2.createNewFile();
		}
		
		Writer writer = new FileWriter(file, true);
		Writer writer2 = new FileWriter(file2, true);
		for (Image image : images) {
			String focal = (image.getFocalLength() == 0) 
				? "" 
				: " 0 "+image.getFocalLength();
			writer.write(image.getPath()+focal+"\n");
			writer2.write(image.getPath()+"\n");
		}
		
		writer.flush();
		writer.close();
		writer2.flush();
		writer2.close();
	}

	public void createMatchTable() throws IOException, InterruptedException
	{	
		addReport("Matching keypoints started");
		Process p = new ProcessBuilder(
			keypointMatcherPath,
			FileHelper.mergePath(outputDirectory, imageListBasicFilename),
			FileHelper.mergePath(outputDirectory, keypointMatchTableFilename)
		).start();
		p.waitFor();
	}

	public void isRunning()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void start()
	{
		addReport("SfM started");
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void stop()
	{
		addReport("SfM stopped");
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void run()
	{
		if(isRunning) {
			try {
				loadExifInformation();
				createImageList();
				createMatchTable();
				
				// run bundler process
				
			} catch (InterruptedException ex) {
				Logger.getLogger(Bundler.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(Bundler.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * --------------------------------------------------------------------
	 *  Implementation of Observer
	 * --------------------------------------------------------------------
	 */
	
	public void update(Observable o, Object o1)
	{
		if(o instanceof Settings) {
			Settings givenSettings = (Settings) o;
			setKeypointMatcherPath(givenSettings.get(Settings.KEYPOINT_MATCHER_PATH));
		}
	}

	
}
