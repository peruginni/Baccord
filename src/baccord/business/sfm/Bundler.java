
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
import baccord.tools.Observable;
import baccord.tools.Observer;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class Bundler extends BaseBusiness implements StructureFromMotion, Runnable, Observer
{
	private static final Logger logger = Logger.getLogger(Bundler.class.getName());

	private Settings settings;
	private Editor editor;
	
	private String bundlerPath;
	private String keypointMatcherPath;
	private String existingBundlePath;
	
	private String outputDirectory;
	private String imageListFocalFilename = "imageListFocal.txt";
	private String imageListExtraFilename = "imageListExtra.txt";
	private String imageListBasicFilename = "imageListBasic.txt";
	private String optionsFilename = "options.txt";
	private String keypointMatchTableFilename = "matchTable.txt";
	private String options;
	private StringBuilder report;
	private List<Image> images;
	private boolean isRunning;
	private Thread thread;
	private Process bundlerProcess;

	public Bundler()
	{
		images = new LinkedList<Image>();
		report = new StringBuilder();
		options = "--output bundle.out" + "\n"
			+ "--output_all bundle_" + "\n"
			+ "--variable_focal_length" + "\n"
			+ "--use_focal_estimate" + "\n"
			+ "--constrain_focal" + "\n"
			+ "--constrain_focal_weight 0.0001" + "\n"
			+ "--estimate_distortion" + "\n"
			+ "--run_bundle" + "\n";
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
		settings.registerObserver(this);
		update(settings, null);
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
	
	public String getBundlerPath()
	{
		return this.bundlerPath;
	}
	
	public void setBundlerPath(String bundlerPath)
	{
		this.bundlerPath = bundlerPath;
	}

	public String getKeypointMatcherPath()
	{
		return keypointMatcherPath;
	}

	public void setKeypointMatcherPath(String path)
	{
		this.keypointMatcherPath = path;
	}
	
	public String getExistingBundlePath()
	{
		return existingBundlePath;
	}
	
	public void setExistingBundlePath(String path)
	{
		this.existingBundlePath = path;
	}
	
	public String getOutputDirectory()
	{
		return outputDirectory;
	}

	public void setOutputDirectory(String path)
	{
		this.outputDirectory = path;
	}

	public String getImageListFocalFilename()
	{
		return imageListFocalFilename;
	}

	public void setImageListFocalFilename(String path)
	{
		this.imageListFocalFilename = path;
	}
	
	public String getImageListExtraFilename()
	{
		return this.imageListExtraFilename;
	}
	
	public void setImageListExtraFilename(String imageListExtraFilename)
	{
		this.imageListExtraFilename = imageListExtraFilename;
	}
	
	public String getImageListBasicFilename()
	{
		return imageListBasicFilename;
	}

	public void setImageListBasicFilename(String path)
	{
		this.imageListBasicFilename = path;
	}

	public String getKeypointMatchTableFilename()
	{
		return keypointMatchTableFilename;
	}

	public void setKeypointMatchTableFilename(String path)
	{
		this.keypointMatchTableFilename = path;
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

	public void addImagesFromDirectory(String directory)
	{
		images.addAll(
			editor.getImageManager().loadImagesFromDirectory(directory, false)
		);
		
		outputDirectory = new File(directory, "bundler").getPath();
	}
	
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
		
		File fileFocal = new File(FileHelper.mergePath(outputDirectory, imageListFocalFilename));
		fileFocal.delete();
		if(!fileFocal.exists()) {
			fileFocal.createNewFile();
		}
		File fileBasic = new File(FileHelper.mergePath(outputDirectory, imageListBasicFilename));
		fileBasic.delete();
		if(!fileBasic.exists()) {
			fileBasic.createNewFile();
		}
		
		Writer writerFocal = new FileWriter(fileFocal, true);
		Writer writerBasic = new FileWriter(fileBasic, true);
		for (Image image : images) {
			String focal = (image.getFocalLength() == 0) 
				? "" 
				: " 0 "+image.getFocalLength();
			writerFocal.write(image.getPath()+focal+"\n");
			writerBasic.write(image.getPath()+"\n");
		}
		
		writerFocal.flush();
		writerFocal.close();
		writerBasic.flush();
		writerBasic.close();
		
		if(existingBundlePath != null) {

			File fileExtra = new File(FileHelper.mergePath(outputDirectory, imageListExtraFilename));
			fileExtra.delete();
			if(!fileExtra.exists()) {
				fileExtra.createNewFile();
			}
			
			FileHelper.copyFile(fileFocal, fileExtra);
			
			fileFocal.delete();
			fileFocal.createNewFile();
		}
	}

	public void createMatchTable() throws IOException, InterruptedException
	{	
		addReport("Matching keypoints started");
		Process p = new ProcessBuilder(
			keypointMatcherPath,
			FileHelper.mergePath(outputDirectory, imageListBasicFilename),
			FileHelper.mergePath(outputDirectory, keypointMatchTableFilename)
		).start();
		
		if(p.waitFor() != 0) {
			addReport("Matching keypoints error");
		}
	}
	
	public void performSfm() throws IOException, InterruptedException
	{	
		File keypointMatchTableFile = new File(outputDirectory, keypointMatchTableFilename);
		File imageListFocalFile = new File(outputDirectory, imageListFocalFilename);
			
		// create options file
		addReport("Creating option file for Bundler");
		File optionsFile = new File(outputDirectory, optionsFilename);
		optionsFile.delete();
		optionsFile.createNewFile();
		
		Writer writer = new FileWriter(optionsFile, true);
		writer.write("--match_table " + keypointMatchTableFile.getCanonicalPath() + "\n");
		
		if(existingBundlePath != null) {
			addReport("Bundler will add images to existing reconstruction");
			writer.write("--bundle " + existingBundlePath + "\n");
			writer.write("--add_images " + imageListFocalFilename + "\n" );
		} 
		
		if(outputDirectory == null) {
			writer.write("--output_dir ." + "\n");
		} else {
			writer.write("--output_dir " + outputDirectory + "\n");
		}
		
		writer.write(options);
		writer.flush();
		writer.close();
		
		addReport("Bundler running");
		bundlerProcess = new ProcessBuilder(
			bundlerPath,
			imageListFocalFile.getCanonicalPath(),
			"--options_file " + optionsFile.getCanonicalPath()
		).start();
		
		if(bundlerProcess.waitFor() != 0) {
			addReport("Bundler error");
		}
	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public void start()
	{
		if(!isRunning) {
			addReport("SfM started");
			isRunning = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop()
	{
		if(isRunning) {
			addReport("SfM stopped");
			isRunning = false;
			thread.interrupt();
			if(bundlerProcess != null) {
				bundlerProcess.destroy();
			}
		}
	}

	public void run()
	{
		if(isRunning) {
			try {
				loadExifInformation();
				createImageList();
				createMatchTable();
				performSfm();
				
			} catch (InterruptedException ex) {
				Logger.getLogger(Bundler.class.getName()).log(Level.SEVERE, null, ex);
				addReport("Error: InterruptedException");
			} catch (IOException ex) {
				Logger.getLogger(Bundler.class.getName()).log(Level.SEVERE, null, ex);
				addReport("Error: IOException");
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
			setBundlerPath(givenSettings.get(Settings.BUNDLER_PATH));
		}
	}
	
}
