
package baccord.business.sfm;

import baccord.business.BaseBusiness;
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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of StructureFromMotion for underlying Bundler
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class Bundler extends BaseBusiness implements StructureFromMotion, Runnable, Observer
{
	private static final Logger logger = Logger.getLogger(Bundler.class.getName());

	private Settings settings;
	private ImageManager imageManager;
	
	private String bundlerPath;
	private String keypointMatcherPath;
	private String existingBundlePath;
	
	private String outputDirectory;
	private String imageListFocalFilename = "imageListFocal.txt";
	private String imageListExtraFilename = "imageListExtra.txt";
	private String imageListKeysFilename = "imageListKeys.txt";
	private String imageListBasicFilename = "imageListBasic.txt";
	private String optionsFilename = "options.txt";
	private String keypointMatchTableFilename = "matchTable.txt";
	private String options;
	private StringBuilder report;
	private List<Image> images;
	private boolean isRunning;
	private Thread thread;
	private Process bundlerProcess;
	private String imageDirectory;

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
	
	public ImageManager getImageManager()
	{
		return imageManager;
	}

	@Inject
	public void setImageManager(ImageManager im)
	{
		this.imageManager = im;
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
	
	
	public String getImageListKeysFilename()
	{
		return imageListKeysFilename;
	}

	public void setImageListKeysFilename(String path)
	{
		this.imageListKeysFilename = path;
	}

	public String getKeypointMatchTableFilename()
	{
		return keypointMatchTableFilename;
	}

	public void setKeypointMatchTableFilename(String path)
	{
		this.keypointMatchTableFilename = path;
	}
	
	public String getImageDirectory()
	{
		return imageDirectory;
	}
	
	public void setImageDirectory(String dir)
	{
		this.imageDirectory = dir;
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
		line = line + "\n";
//		report.append(line);
//		report.append("\n");
		notifyObservers(line);
	}
	
	public void addReport(InputStream in, InputStream er) throws IOException
	{
		BufferedReader bin = new BufferedReader(new InputStreamReader(in));
		BufferedReader ber = new BufferedReader(new InputStreamReader(er));
		String lineIn = null;
		String lineEr = null;
		while( ((lineIn = bin.readLine()) != null)
			|| ((lineEr = bin.readLine()) != null)
		) {
			if(lineIn != null) addReport(lineIn);
			if(lineEr != null) addReport(lineEr);
		}
		bin.close();
		ber.close();
	}
	
	public void addReport(InputStream in) throws IOException
	{
		BufferedReader bin = new BufferedReader(new InputStreamReader(in));
		String lineIn = null;
		while( ((lineIn = bin.readLine()) != null)
		) {
			if(lineIn != null) addReport(lineIn);
		}
		bin.close();
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */

	public void loadImagesFromDirectory(String directory)
	{
		images = new LinkedList<Image>();
		
		images.addAll(
			imageManager.loadImagesFromDirectory(directory, false)
		);
		
		imageDirectory = directory;
		outputDirectory = new File(directory, "bundler").getPath();
	}
	
	public void assureLowerCaseAndSift()
	{	
		addReport("Renaming image filename extensions to lowercase");
		imageManager.batchRenameImagesToLowerCase(images);
		
		addReport("Computing SIFT if not computed before");
		for (Image image : images) {
			if(!imageManager.hasSift(image)) {
				imageManager.performSift(image);
			}
		}
	}
	
	public void loadExifInformation()
	{
		addReport("Loading focal information");
		
		for (Image image : images) {
			imageManager.loadExifInformation(image);
		}
	}
	
	public void createImageList() throws IOException
	{
		addReport("Writting down focal information");
		
		Collections.sort((List)images);
		
		File fileFocal = new File(FileHelper.mergePath(imageDirectory, imageListFocalFilename));
		fileFocal.delete();
		if(!fileFocal.exists()) {
			fileFocal.createNewFile();
		}
		File fileBasic = new File(FileHelper.mergePath(imageDirectory, imageListBasicFilename));
		fileBasic.delete();
		if(!fileBasic.exists()) {
			fileBasic.createNewFile();
		}
		File fileKeys = new File(FileHelper.mergePath(imageDirectory, imageListKeysFilename));
		fileKeys.delete();
		if(!fileKeys.exists()) {
			fileKeys.createNewFile();
		}
		
		
		Writer writerFocal = new FileWriter(fileFocal, true);
		Writer writerBasic = new FileWriter(fileBasic, true);
		Writer writerKeys = new FileWriter(fileKeys, true);
		for (Image image : images) {
			if(image.getFocalLength() == 0) continue;
			
			String focal = (image.getFocalLength() == 0) 
				? "" 
				: " 0 " + image.getFocalLength(); // + String.format("%.5g%n", image.getFocalLength());
			writerFocal.write(image.getPath()+focal+"\n");
			writerBasic.write(image.getPath()+"\n");
			writerKeys.write(image.getPath().replace(".jpg", ".key") +"\n");
		}
		
		writerKeys.flush();
		writerKeys.close();
		writerFocal.flush();
		writerFocal.close();
		writerBasic.flush();
		writerBasic.close();
		
		if(existingBundlePath != null) {

			File fileExtra = new File(FileHelper.mergePath(imageDirectory, imageListExtraFilename));
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
		
//		File matchTable = new File(FileHelper.mergePath(imageDirectory, keypointMatchTableFilename));
//		
//		if(matchTable.exists()) {
//			addReport("Match table \""+keypointMatchTableFilename+"\" exists, therefore skip matching");
//			return;
//		}
		
		ProcessBuilder pb = new ProcessBuilder(
			keypointMatcherPath,
			FileHelper.mergePath(imageDirectory, imageListKeysFilename),
			FileHelper.mergePath(imageDirectory, keypointMatchTableFilename)
		);
		pb.directory(new File(imageDirectory));
		Process p = pb.start();
		
		addReport(p.getInputStream(), p.getErrorStream());
		
		if(p.waitFor() != 0) {
			addReport("Matching keypoints error");
			stop();
		}
	}
	
	public void performSfm() throws IOException, InterruptedException
	{	
		File keypointMatchTableFile = new File(imageDirectory, keypointMatchTableFilename);
		File imageListFocalFile = new File(imageDirectory, imageListFocalFilename);
			
		// create options file
		addReport("Creating option file for Bundler");
		File optionsFile = new File(imageDirectory, optionsFilename);
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
		String command = 
			bundlerPath 
			+ " " + imageListFocalFile.getCanonicalPath() 
			+ " --options_file " + optionsFile.getCanonicalPath()
			+ " > " + outputDirectory + File.separator +"out";
		ProcessBuilder pb = new ProcessBuilder(
			"/bin/sh", "-c",
			command
		);
		pb.directory(new File(imageDirectory));
		bundlerProcess = pb.start();
		
		//StreamHelper.print(bundlerProcess.getInputStream());
		//StreamHelper.print(bundlerProcess.getErrorStream());
		addReport(bundlerProcess.getErrorStream());
		
		if(bundlerProcess.waitFor() != 0) {
			addReport("Bundler error");
			stop();
		}
		
		addReport("Bundler successfully finished");
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
			for (Process process : imageManager.getActiveProcesses()) {
				process.destroy();
			}
		}
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Implementation of Runnable
	 * --------------------------------------------------------------------
	 */
	
	public void run()
	{
		if(isRunning) {
			try {
				File outputDirFile = new File(outputDirectory);
				if(!outputDirFile.exists()) {
					outputDirFile.mkdirs();
				}
				
				addReport("Image directory: "+imageDirectory);
				addReport("Output directory: "+outputDirectory);
				
				assureLowerCaseAndSift();
				loadExifInformation();
				createImageList();
				createMatchTable();
				performSfm();
				stop();
				
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
