package baccord.business.images;

import baccord.business.BaseBusiness;
import baccord.business.settings.Settings;
import baccord.exceptions.InvalidAppPathException;
import baccord.tools.FileHelper;
import baccord.tools.ObjectStorage;
import baccord.tools.Observable;
import baccord.tools.Observer;
import com.google.inject.Inject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Basic implementation of ImageManager
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageManager extends BaseBusiness implements ImageManager, Observer
{
	private static final Logger logger = Logger.getLogger(BasicImageManager.class.getName());
	
	private Settings settings;
	private String siftPath;
	private String convertPath;
	private String mogrifyPath;
	private String jheadPath;
	
	private Map<String, Double> ccdWidths;
	private String ccdWidthsStoragePath = "./ccdWidths.dat";
	private String siftExtension = ".key";
	private String pgmExtension = ".pgm";
	private Set<Process> activeProcesses;
	private Pattern jpegPattern = Pattern.compile(".*\\.[jJpPgG]{3}$");

	public BasicImageManager()
	{
		activeProcesses = new HashSet<Process>();
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
	
	public String getSiftPath()
	{
		return siftPath;
	}
	
	public void setSiftPath(String path)
	{
		siftPath = path;
	}
	
	public String getConvertPath()
	{
		return this.convertPath;
	}
	
	public void setConvertPath(String convertPath)
	{
		this.convertPath = convertPath;
	}
	
	public String getMogrifyPath()
	{
		return this.mogrifyPath;
	}
	
	public void setMogrifyPath(String mogrifyPath)
	{
		this.mogrifyPath = mogrifyPath;
	}
	
	public String getJheadPath()
	{
		return this.jheadPath;
	}
	
	public void setJheadPath(String jheadPath)
	{
		this.jheadPath = jheadPath;
	}
	
	public Map<String, Double> getCcdWidths()
	{
		if(ccdWidths == null) {
			try {
				ccdWidths = (HashMap<String, Double>) ObjectStorage.load(ccdWidthsStoragePath);
				
			} catch (FileNotFoundException ex) {
				// file was probably not yet created
			}

			if(ccdWidths == null) {
				ccdWidths = new HashMap<String, Double>();
				fillDefaultCameraCcdWidths(ccdWidths);
			}
		}
		
		return ccdWidths;
	}
	
	public void setCcdWidths(Map<String, Double> map)
	{
		ccdWidths = map;
		ObjectStorage.save(ccdWidths, ccdWidthsStoragePath);
	}
	
	public void addCcdWidth(String camera, double width)
	{
		getCcdWidths();
		ccdWidths.put(camera, new Double(width));
		ObjectStorage.save(ccdWidths, ccdWidthsStoragePath);
	}
	
	public double getCcdWidth(String camera)
	{
		Map<String, Double> map = getCcdWidths();
		Double result = map.get(camera);
		if(result != null) {
			return result.doubleValue();
		} else {
			return 0;
		}
	}
	
	public String getCcdWidthsStoragePath()
	{
		return this.ccdWidthsStoragePath;
	}
	
	public void setCcdWidthsStoragePath(String ccdWidthsStoragePath)
	{
		this.ccdWidthsStoragePath = ccdWidthsStoragePath;
	}
	
	public String getSiftExtension()
	{
		return this.siftExtension;
	}
	
	public void setSiftExtension(String e)
	{
		this.siftExtension = e;
	}
	
	public String getPgmExtension()
	{
		return this.pgmExtension;
	}
	
	public void setPgmExtension(String e)
	{
		this.pgmExtension = e;
	}
	
	public Set<Process> getActiveProcesses()
	{
		return activeProcesses;
	}	
	
	public void addReport(String string)
	{
		notifyObservers(string+"\n");
	}
	
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	public void resize(Image image, int width, int height)
	{
		if(convertPath == null) {
			logger.log(Level.SEVERE, "Missing convert application", new InvalidAppPathException());
			return;
		}
		
		/**
		 * http://www.imagemagick.org/Usage/resize/#shrink
		 * special escaping for windows/cygwin environments - processbuilder automaticale escapes
		 */
		 
		// convert dragon.gif    -resize 64x64\>  shrink_dragon.gif
		Process p = null;
		try {
			//String shrinkLargerFlag = (OS.isWindows()) ? "^>" : ">";
			
			 p = new ProcessBuilder(
				convertPath,
				image.getPath(),
				"-resize",
				width + "x" + height + ">",
				image.getPath()
			).start(); 
			
			activeProcesses.add(p);
			
			if(p.waitFor() != 0)
				logger.log(Level.SEVERE, "Resize error");
			
		} catch (InterruptedException ex) {
			logger.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		} finally {
			activeProcesses.remove(p);
		}
	}

	public boolean hasSift(Image image)
	{
		String imageDirectory = FileHelper.getDirectory(image.getPath());
		String imageBasename = FileHelper.getBasename(image.getPath()); 
		File siftFile = new File(FileHelper.mergePath(imageDirectory, imageBasename + siftExtension + ".gz"));
		return siftFile.exists();
	}

	public void performSift(Image image)
	{
		if(siftPath == null) {
			logger.log(Level.SEVERE, "Missing sift application", new InvalidAppPathException());
			return;
		}
		
		if(mogrifyPath == null) {
			logger.log(Level.SEVERE, "Missing mogrify application", new InvalidAppPathException());
			return;
		}
		
		String imageJpgPath = image.getPath();
		String imageDirectory = FileHelper.getDirectory(imageJpgPath);
		String imageBasename = FileHelper.getBasename(imageJpgPath);
		String imagePgmPath = FileHelper.mergePath(imageDirectory, imageBasename + pgmExtension);
		String imageSiftPath = FileHelper.mergePath(imageDirectory, imageBasename + siftExtension);
		
		Process p = null;
		try {
			addReport("Computing SIFT for " + imageBasename);
			/*
			 echo "mogrify -format pgm $IMAGE_DIR/$d; 
			       $SIFT < $pgm_file > $key_file; 
			       rm $pgm_file; 
			       gzip -f $key_file"
			 */
			
			// convert image to pgm 
			ProcessBuilder pb = new ProcessBuilder(
				mogrifyPath,
				"-format",
				"pgm", 
				imageJpgPath
			);
			p = pb.start(); 
			activeProcesses.add(p);
			if(p.waitFor() != 0) logger.log(Level.SEVERE, "Mogrify error");
			activeProcesses.remove(p);
			
			// execute sift command and save keyfile
			p = new ProcessBuilder(
				"/bin/sh", "-c",
				siftPath +" < " + imagePgmPath + " > " + imageSiftPath
			).start(); 
			activeProcesses.add(p);
			if(p.waitFor() != 0) logger.log(Level.SEVERE, "Sift error");
			activeProcesses.remove(p);
			
			// remove pgm temp image
			p = new ProcessBuilder(
				"rm", imagePgmPath
			).start(); 
			activeProcesses.add(p);
			if(p.waitFor() != 0) logger.log(Level.SEVERE, "Remove tmp image error");
			activeProcesses.remove(p);
			
			// compress sift file
			p = new ProcessBuilder(
				"gzip", 
				"-f",
				imageSiftPath
			).start(); 
			activeProcesses.add(p);
			if(p.waitFor() != 0) logger.log(Level.SEVERE, "Gzip error");
			activeProcesses.remove(p);
			
		} catch (InterruptedException ex) {
			logger.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		} finally {
			activeProcesses.remove(p);
		}
	}

	public void loadExifInformation(Image image)
	{
		double focalLength = 0;
		
		Process p = null;
		try {
			p = new ProcessBuilder(
				jheadPath,
				image.getPath()
			).start(); 
			activeProcesses.add(p);
			
			BufferedReader bin = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String make = null;
			String model = null;
			double focalLengthMm = 0.0f;
			double ccdWidthMm = 0.0f;
			int resolutionX = 0;
			int resolutionY = 0;
			
			String line = null;
			Matcher m = null;
			while((line = bin.readLine()) != null) {
				
				m = Pattern.compile("Camera make\\s*:\\s*(.*)$").matcher(line);
				if(m.find()) {
					make = m.group(1);
				}
				
				m = Pattern.compile("Camera model\\s*:\\s*(.*)$").matcher(line);
				if(m.find()) {
					model = m.group(1);
				}
				
				m = Pattern.compile("Focal length.*?:\\s*([0-9\\.]*?)mm").matcher(line);
				if(m.find()) {
					focalLengthMm = Double.valueOf(m.group(1));
				}
				
				m = Pattern.compile("CCD width.*?:\\s*([0-9\\.]*?)mm").matcher(line);
				if(m.find()) {
					ccdWidthMm = Double.valueOf(m.group(1));
				}
				
				m = Pattern.compile("Resolution.*?:\\s*([0-9]*?)\\s*x\\s*([0-9]*)").matcher(line);
				if(m.find()) {
					resolutionX = Integer.valueOf(m.group(1));
					resolutionY = Integer.valueOf(m.group(2));
				}
				
			}
			bin.close();

			if(p.waitFor() != 0) logger.log(Level.SEVERE, "Jhead error");
			activeProcesses.remove(p);
			
			String cameraName = make + " "+ model;
			double ccdWidthTmp = getCcdWidth(cameraName); 
			if(ccdWidthTmp != 0) {
				ccdWidthMm = ccdWidthTmp;
			} else {
				addReport("Camera \"" + cameraName + "\" not found, therefore used EXIF ccd width ("+ccdWidthMm+")");
			}
			
			if(resolutionX < resolutionY) {
				// aspect ratio is wrong
				int tmp = resolutionX;
				resolutionX = resolutionY;
				resolutionY = tmp;
			}
			
			if(focalLengthMm != 0 && ccdWidthMm != 0 && resolutionX != 0) {
				//$focal_pixels = $res_x * ($focal_mm / $ccd_width_mm);
				double focalLengthD = (double) resolutionX * ((double)focalLengthMm / (double) ccdWidthMm);
				//$line = sprintf("%s.jpg 0 %0.5f", $basename, $SCALE * $focal_pixels);
				
				DecimalFormat df = new DecimalFormat(".#####");
				df.setRoundingMode(RoundingMode.HALF_UP);
				focalLength = Double.valueOf(df.format(focalLengthD));
			}
			
		} catch (InterruptedException ex) {
			logger.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		} finally {
			activeProcesses.remove(p);
		}
		
		image.setFocalLength(focalLength);
	}
	
	public List<Image> loadImagesFromDirectory(String directory, boolean loadExif)
	{
		List<Image> list = new LinkedList<Image>();
		
		File dir = new File(directory);
		if(dir.exists()) {
			File[] files = dir.listFiles(new FilenameFilter() {
				public boolean accept(File directory, String filename)
				{
					Matcher m = jpegPattern.matcher(filename);
					return m.matches();
				}
			});
			
			for (File file : files) {
				try {
					Image image = new Image(file.getCanonicalPath());
					if(loadExif) {
						loadExifInformation(image);
					}
					list.add(image);
				} catch (IOException ex) {
					logger.log(Level.SEVERE, null, ex);
				}
			}
			
		}
		
		return list;
	}
	
	public void batchRenameImagesToLowerCase(List<Image> images)
	{
		File from = null;
		File to = null;
		String path = null;
		String lowercase = null;
		for (Image image : images) {
			path = image.getPath();
			from = new File(path);
			lowercase = FileHelper.extensionToLowerCase(path);
			to = new File(lowercase);
			if(!path.equals(lowercase)) {
				from.renameTo(to);
				image.setPath(lowercase);	
			}
		}
	}
	
	/**
	 * Fill map with default values
	 * 
	 * Reused from original bundler perl script extract_focal.pl
	 * 
	 * @param map 
	 */
	public static void fillDefaultCameraCcdWidths(Map<String, Double> map)
	{
		map.put("Asahi Optical Co.,Ltd.  PENTAX Optio330RS", new Double(7.176));
		map.put("Canon Canon DIGITAL IXUS 400", new Double(7.176));
		map.put("Canon Canon DIGITAL IXUS 40", new Double(5.76));
		map.put("Canon Canon DIGITAL IXUS 430", new Double(7.176));
		map.put("Canon Canon DIGITAL IXUS 500", new Double(7.176));
		map.put("Canon Canon DIGITAL IXUS 50", new Double(5.76));
		map.put("Canon Canon DIGITAL IXUS 55", new Double(5.76));
		map.put("Canon Canon DIGITAL IXUS 60", new Double(5.76));
		map.put("Canon Canon DIGITAL IXUS 65", new Double(5.76));
		map.put("Canon Canon DIGITAL IXUS 700", new Double(7.176));
		map.put("Canon Canon DIGITAL IXUS 750", new Double(7.176));
		map.put("Canon Canon DIGITAL IXUS 800 IS", new Double(5.76));
		map.put("Canon Canon DIGITAL IXUS II", new Double(5.27));
		map.put("Canon Canon EOS 10D", new Double(22.7));
		map.put("Canon Canon EOS-1D Mark II", new Double(28.7));
		map.put("Canon Canon EOS-1Ds Mark II", new Double(35.95));
		map.put("Canon Canon EOS  20D", new Double(22.5));
		map.put("Canon Canon EOS 20D", new Double(22.5));
		map.put("Canon Canon EOS 300D DIGITAL", new Double(22.66));
		map.put("Canon Canon EOS 30D", new Double(22.5));
		map.put("Canon Canon EOS 350D DIGITAL", new Double(22.2));
		map.put("Canon Canon EOS 400D DIGITAL", new Double(22.2));
		map.put("Canon Canon EOS 40D", new Double(22.2));
		map.put("Canon Canon EOS 5D", new Double(35.8));
		map.put("Canon Canon EOS DIGITAL REBEL", new Double(22.66));
		map.put("Canon Canon EOS DIGITAL REBEL XT", new Double(22.2));
		map.put("Canon Canon EOS DIGITAL REBEL XTi", new Double(22.2));
		map.put("Canon Canon EOS Kiss Digital", new Double(22.66));
		map.put("Canon Canon IXY DIGITAL 600", new Double(7.176));
		map.put("Canon Canon PowerShot A20", new Double(7.176));
		map.put("Canon Canon PowerShot A400", new Double(4.54));
		map.put("Canon Canon PowerShot A40", new Double(5.27));
		map.put("Canon Canon PowerShot A510", new Double(5.76));
		map.put("Canon Canon PowerShot A520", new Double(5.76));
		map.put("Canon Canon PowerShot A530", new Double(5.76));
		map.put("Canon Canon PowerShot A60", new Double(5.27));
		map.put("Canon Canon PowerShot A620", new Double(7.176));
		map.put("Canon Canon PowerShot A630", new Double(7.176));
		map.put("Canon Canon PowerShot A640", new Double(7.176));
		map.put("Canon Canon PowerShot A700", new Double(5.76));
		map.put("Canon Canon PowerShot A70", new Double(5.27));
		map.put("Canon Canon PowerShot A710 IS", new Double(5.76));
		map.put("Canon Canon PowerShot A75", new Double(5.27));
		map.put("Canon Canon PowerShot A80", new Double(7.176));
		map.put("Canon Canon PowerShot A85", new Double(5.27));
		map.put("Canon Canon PowerShot A95", new Double(7.176));
		map.put("Canon Canon PowerShot G1", new Double(7.176));
		map.put("Canon Canon PowerShot G2", new Double(7.176));
		map.put("Canon Canon PowerShot G3", new Double(7.176));
		map.put("Canon Canon PowerShot G5", new Double(7.176));
		map.put("Canon Canon PowerShot G6", new Double(7.176));
		map.put("Canon Canon PowerShot G7", new Double(7.176));
		map.put("Canon Canon PowerShot G9", new Double(7.600));
		map.put("Canon Canon PowerShot Pro1", new Double(8.8));
		map.put("Canon Canon PowerShot S110", new Double(5.27));
		map.put("Canon Canon PowerShot S1 IS", new Double(5.27));
		map.put("Canon Canon PowerShot S200", new Double(5.27));
		map.put("Canon Canon PowerShot S2 IS", new Double(5.76));
		map.put("Canon Canon PowerShot S30", new Double(7.176));
		map.put("Canon Canon PowerShot S3 IS", new Double(5.76));
		map.put("Canon Canon PowerShot S400", new Double(7.176));
		map.put("Canon Canon PowerShot S40", new Double(7.176));
		map.put("Canon Canon PowerShot S410", new Double(7.176));
		map.put("Canon Canon PowerShot S45", new Double(7.176));
		map.put("Canon Canon PowerShot S500", new Double(7.176));
		map.put("Canon Canon PowerShot S50", new Double(7.176));
		map.put("Canon Canon PowerShot S60", new Double(7.176));
		map.put("Canon Canon PowerShot S70", new Double(7.176));
		map.put("Canon Canon PowerShot S80", new Double(7.176));
		map.put("Canon Canon PowerShot SD1000", new Double(5.75));
		map.put("Canon Canon PowerShot SD100", new Double(5.27));
		map.put("Canon Canon PowerShot SD10", new Double(5.75));
		map.put("Canon Canon PowerShot SD110", new Double(5.27));
		map.put("Canon Canon PowerShot SD200", new Double(5.76));
		map.put("Canon Canon PowerShot SD300", new Double(5.76));
		map.put("Canon Canon PowerShot SD400", new Double(5.76));
		map.put("Canon Canon PowerShot SD450", new Double(5.76));
		map.put("Canon Canon PowerShot SD500", new Double(7.176));
		map.put("Canon Canon PowerShot SD550", new Double(7.176));
		map.put("Canon Canon PowerShot SD600", new Double(5.76));
		map.put("Canon Canon PowerShot SD630", new Double(5.76));
		map.put("Canon Canon PowerShot SD700 IS", new Double(5.76));
		map.put("Canon Canon PowerShot SD750", new Double(5.75));
		map.put("Canon Canon PowerShot SD800 IS", new Double(5.76));
		map.put("Canon EOS 300D DIGITAL", new Double(22.66));
		map.put("Canon EOS DIGITAL REBEL", new Double(22.66));
		map.put("Canon PowerShot A510", new Double(5.76));
		map.put("Canon PowerShot S30", new Double(7.176));
		map.put("CASIO COMPUTER CO., new Double(LTD. EX-S500", new Double(5.76));
		map.put("CASIO COMPUTER CO., new Double(LTD. EX-Z1000", new Double(7.716));
		map.put("CASIO COMPUTER CO., new Double(LTD  EX-Z30", new Double(5.76));
		map.put("CASIO COMPUTER CO., new Double(LTD. EX-Z600", new Double(5.76));
		map.put("CASIO COMPUTER CO., new Double(LTD. EX-Z60", new Double(7.176));
		map.put("CASIO COMPUTER CO., new Double(LTD  EX-Z750", new Double(7.176));
		map.put("CASIO COMPUTER CO.,LTD. EX-Z850", new Double(7.176));
		map.put("EASTMAN KODAK COMPANY KODAK CX7330 ZOOM DIGITAL CAMERA", new Double(5.27));
		map.put("EASTMAN KODAK COMPANY KODAK CX7530 ZOOM DIGITAL CAMERA", new Double(5.76));
		map.put("EASTMAN KODAK COMPANY KODAK DX3900 ZOOM DIGITAL CAMERA", new Double(7.176));
		map.put("EASTMAN KODAK COMPANY KODAK DX4900 ZOOM DIGITAL CAMERA", new Double(7.176));
		map.put("EASTMAN KODAK COMPANY KODAK DX6340 ZOOM DIGITAL CAMERA", new Double(5.27));
		map.put("EASTMAN KODAK COMPANY KODAK DX6490 ZOOM DIGITAL CAMERA", new Double(5.76));
		map.put("EASTMAN KODAK COMPANY KODAK DX7630 ZOOM DIGITAL CAMERA", new Double(7.176));
		map.put("EASTMAN KODAK COMPANY KODAK Z650 ZOOM DIGITAL CAMERA", new Double(5.76));
		map.put("EASTMAN KODAK COMPANY KODAK Z700 ZOOM DIGITAL CAMERA", new Double(5.76));
		map.put("EASTMAN KODAK COMPANY KODAK Z740 ZOOM DIGITAL CAMERA", new Double(5.76));
		map.put("EASTMAN KODAK COMPANY KODAK Z740 ZOOM DIGITAL CAMERA", new Double(5.76));
		map.put("FUJIFILM FinePix2600Zoom", new Double(5.27));
		map.put("FUJIFILM FinePix40i", new Double(7.600));
		map.put("FUJIFILM FinePix A310", new Double(5.27));
		map.put("FUJIFILM FinePix A330", new Double(5.27));
		map.put("FUJIFILM FinePix A600", new Double(7.600));
		map.put("FUJIFILM FinePix E500", new Double(5.76));
		map.put("FUJIFILM FinePix E510", new Double(5.76));
		map.put("FUJIFILM FinePix E550", new Double(7.600));
		map.put("FUJIFILM FinePix E900", new Double(7.78));
		map.put("FUJIFILM FinePix F10", new Double(7.600));
		map.put("FUJIFILM FinePix F30", new Double(7.600));
		map.put("FUJIFILM FinePix F450", new Double(5.76));
		map.put("FUJIFILM FinePix F601 ZOOM", new Double(7.600));
		map.put("FUJIFILM FinePix S3Pro", new Double(23.0));
		map.put("FUJIFILM FinePix S5000", new Double(5.27));
		map.put("FUJIFILM FinePix S5200", new Double(5.76));
		map.put("FUJIFILM FinePix S5500", new Double(5.27));
		map.put("FUJIFILM FinePix S6500fd", new Double(7.600));
		map.put("FUJIFILM FinePix S7000", new Double(7.600));
		map.put("FUJIFILM FinePix Z2", new Double(5.76));
		map.put("Hewlett-Packard hp 635 Digital Camera", new Double(4.54));
		map.put("Hewlett-Packard hp PhotoSmart 43x series", new Double(5.27));
		map.put("Hewlett-Packard HP PhotoSmart 618 (V1.1)", new Double(5.27));
		map.put("Hewlett-Packard HP PhotoSmart C945 (V01.61)", new Double(7.176));
		map.put("Hewlett-Packard HP PhotoSmart R707 (V01.00)", new Double(7.176));
		map.put("KONICA MILOLTA  DYNAX 5D", new Double(23.5));
		map.put("Konica Minolta Camera, new Double(Inc. DiMAGE A2", new Double(8.80));
		map.put("KONICA MINOLTA CAMERA, new Double(Inc. DiMAGE G400", new Double(5.76));
		map.put("Konica Minolta Camera, new Double(Inc. DiMAGE Z2", new Double(5.76));
		map.put("KONICA MINOLTA DiMAGE A200", new Double(8.80));
		map.put("KONICA MINOLTA  DiMAGE Z3", new Double(5.76));
		map.put("KONICA MINOLTA DiMAGE X1", new Double(7.176));
		map.put("KONICA MINOLTA  DYNAX 5D", new Double(23.5));
		map.put("Minolta Co., new Double(Ltd. DiMAGE F100", new Double(7.176));
		map.put("Minolta Co., new Double(Ltd. DiMAGE Xi", new Double(5.27));
		map.put("Minolta Co., new Double(Ltd. DiMAGE Xt", new Double(5.27));
		map.put("Minolta Co., new Double(Ltd. DiMAGE Z1", new Double(5.27));
		map.put("NIKON COOLPIX L3", new Double(5.76));
		map.put("NIKON COOLPIX P2", new Double(7.176));
		map.put("NIKON COOLPIX S4", new Double(5.76));
		map.put("NIKON COOLPIX S7c", new Double(5.76));
		map.put("NIKON CORPORATION NIKON D100", new Double(23.7));
		map.put("NIKON CORPORATION NIKON D1", new Double(23.7));
		map.put("NIKON CORPORATION NIKON D1H", new Double(23.7));
		map.put("NIKON CORPORATION NIKON D200", new Double(23.6));
		map.put("NIKON CORPORATION NIKON D2H", new Double(23.3));
		map.put("NIKON CORPORATION NIKON D2X", new Double(23.7));
		map.put("NIKON CORPORATION NIKON D40", new Double(23.7));
		map.put("NIKON CORPORATION NIKON D50", new Double(23.7));
		map.put("NIKON CORPORATION NIKON D60", new Double(23.6));
		map.put("NIKON CORPORATION NIKON D70", new Double(23.7));
		map.put("NIKON CORPORATION NIKON D70s", new Double(23.7));
		map.put("NIKON CORPORATION NIKON D80", new Double(23.6));
		map.put("NIKON E2500", new Double(5.27));
		map.put("NIKON E2500", new Double(5.27));
		map.put("NIKON E3100", new Double(5.27));
		map.put("NIKON E3200", new Double(5.27));
		map.put("NIKON E3700", new Double(5.27));
		map.put("NIKON E4200", new Double(7.176));
		map.put("NIKON E4300", new Double(7.18));
		map.put("NIKON E4500", new Double(7.176));
		map.put("NIKON E4600", new Double(5.76));
		map.put("NIKON E5000", new Double(8.80));
		map.put("NIKON E5200", new Double(7.176));
		map.put("NIKON E5400", new Double(7.176));
		map.put("NIKON E5600", new Double(5.76));
		map.put("NIKON E5700", new Double(8.80));
		map.put("NIKON E5900", new Double(7.176));
		map.put("NIKON E7600", new Double(7.176));
		map.put("NIKON E775", new Double(5.27));
		map.put("NIKON E7900", new Double(7.176));
		map.put("NIKON E7900", new Double(7.176));
		map.put("NIKON E8800", new Double(8.80));
		map.put("NIKON E990", new Double(7.176));
		map.put("NIKON E995", new Double(7.176));
		map.put("NIKON S1", new Double(5.76));
		map.put("Nokia N80", new Double(5.27));
		map.put("Nokia N80", new Double(5.27));
		map.put("Nokia N93", new Double(4.536));
		map.put("Nokia N95", new Double(5.7));
		map.put("OLYMPUS CORPORATION     C-5000Z", new Double(7.176));
		map.put("OLYMPUS CORPORATION C5060WZ", new Double(7.176));
		map.put("OLYMPUS CORPORATION C750UZ", new Double(5.27));
		map.put("OLYMPUS CORPORATION C765UZ", new Double(5.76));
		map.put("OLYMPUS CORPORATION C8080WZ", new Double(8.80));
		map.put("OLYMPUS CORPORATION X250, new Double(D560Z,C350Z", new Double(5.76));
		map.put("OLYMPUS CORPORATION     X-3, new Double(C-60Z", new Double(7.176));
		map.put("OLYMPUS CORPORATION X400, new Double(D580Z,C460Z", new Double(5.27));
		map.put("OLYMPUS IMAGING CORP.   E-500", new Double(17.3));
		map.put("OLYMPUS IMAGING CORP.   FE115, new Double(X715", new Double(5.76));
		map.put("OLYMPUS IMAGING CORP. SP310", new Double(7.176));
		map.put("OLYMPUS IMAGING CORP.   SP510UZ", new Double(5.75));
		map.put("OLYMPUS IMAGING CORP.   SP550UZ", new Double(5.76));
		map.put("OLYMPUS IMAGING CORP.   uD600, new Double(S600", new Double(5.75));
		map.put("OLYMPUS_IMAGING_CORP.   X450, new Double(D535Z,C370Z", new Double(5.27));
		map.put("OLYMPUS IMAGING CORP. X550, new Double(D545Z,C480Z", new Double(5.76));
		map.put("OLYMPUS OPTICAL CO., new Double(LTD C2040Z", new Double(6.40));
		map.put("OLYMPUS OPTICAL CO., new Double(LTD C211Z", new Double(5.27));
		map.put("OLYMPUS OPTICAL CO., new Double(LTD C2Z,D520Z,C220Z", new Double(4.54));
		map.put("OLYMPUS OPTICAL CO., new Double(LTD C3000Z", new Double(7.176));
		map.put("OLYMPUS OPTICAL CO.,LTD C300Z,D550Z", new Double(5.4));
		map.put("OLYMPUS OPTICAL CO., new Double(LTD C4100Z,C4000Z", new Double(7.176));
		map.put("OLYMPUS OPTICAL CO., new Double(LTD C750UZ", new Double(5.27));
		map.put("OLYMPUS OPTICAL CO., new Double(LTD X-2,C-50Z", new Double(7.176));
		map.put("OLYMPUS SP550UZ", new Double(5.76));
		map.put("OLYMPUS X100, new Double(D540Z,C310Z", new Double(5.27));
		map.put("Panasonic DMC-FX01", new Double(5.76));
		map.put("Panasonic DMC-FX07", new Double(5.75));
		map.put("Panasonic DMC-FX9", new Double(5.76));
		map.put("Panasonic DMC-FX10", new Double(5.76)); // added
		map.put("Panasonic DMC-FZ20", new Double(5.760));
		map.put("Panasonic DMC-FZ2", new Double(4.54));
		map.put("Panasonic DMC-FZ30", new Double(7.176));
		map.put("Panasonic DMC-FZ50", new Double(7.176));
		map.put("Panasonic DMC-FZ5", new Double(5.760));
		map.put("Panasonic DMC-FZ7", new Double(5.76));
		map.put("Panasonic DMC-LC1", new Double(8.80));
		map.put("Panasonic DMC-LC33", new Double(5.760));
		map.put("Panasonic DMC-LX1", new Double(8.50));
		map.put("Panasonic DMC-LZ2", new Double(5.76));
		map.put("Panasonic DMC-TZ1", new Double(5.75));
		map.put("Panasonic DMC-TZ3", new Double(5.68));
		map.put("PENTAX Corporation  PENTAX *ist DL", new Double(23.5));
		map.put("PENTAX Corporation  PENTAX *ist DS2", new Double(23.5));
		map.put("PENTAX Corporation  PENTAX *ist DS", new Double(23.5));
		map.put("PENTAX Corporation  PENTAX K100D", new Double(23.5));
		map.put("PENTAX Corporation PENTAX Optio 450", new Double(7.176));
		map.put("PENTAX Corporation PENTAX Optio 550", new Double(7.176));
		map.put("PENTAX Corporation PENTAX Optio E10", new Double(5.76));
		map.put("PENTAX Corporation PENTAX Optio S40", new Double(5.76));
		map.put("PENTAX Corporation  PENTAX Optio S4", new Double(5.76));
		map.put("PENTAX Corporation PENTAX Optio S50", new Double(5.76));
		map.put("PENTAX Corporation  PENTAX Optio S5i", new Double(5.76));
		map.put("PENTAX Corporation  PENTAX Optio S5z", new Double(5.76));
		map.put("PENTAX Corporation  PENTAX Optio SV", new Double(5.76));
		map.put("PENTAX Corporation PENTAX Optio WP", new Double(5.75));
		map.put("RICOH CaplioG3 modelM", new Double(5.27));
		map.put("RICOH       Caplio GX", new Double(7.176));
		map.put("RICOH       Caplio R30", new Double(5.75));
		map.put("Samsung  Digimax 301", new Double(5.27));
		map.put("Samsung Techwin <Digimax i5, new Double(Samsung #1>", new Double(5.76));
		map.put("SAMSUNG TECHWIN Pro 815", new Double(8.80));
		map.put("SONY DSC-F828", new Double(8.80));
		map.put("SONY DSC-N12", new Double(7.176));
		map.put("SONY DSC-P100", new Double(7.176));
		map.put("SONY DSC-P10", new Double(7.176));
		map.put("SONY DSC-P12", new Double(7.176));
		map.put("SONY DSC-P150", new Double(7.176));
		map.put("SONY DSC-P200", new Double(7.176));
		map.put("SONY DSC-P52", new Double(5.27));
		map.put("SONY DSC-P72", new Double(5.27));
		map.put("SONY DSC-P73", new Double(5.27));
		map.put("SONY DSC-P8", new Double(5.27));
		map.put("SONY DSC-R1", new Double(21.5));
		map.put("SONY DSC-S40", new Double(5.27));
		map.put("SONY DSC-S600", new Double(5.760));
		map.put("SONY DSC-T9", new Double(7.18));
		map.put("SONY DSC-V1", new Double(7.176));
		map.put("SONY DSC-W1", new Double(7.176));
		map.put("SONY DSC-W30", new Double(5.760));
		map.put("SONY DSC-W50", new Double(5.75));
		map.put("SONY DSC-W5", new Double(7.176));
		map.put("SONY DSC-W7", new Double(7.176));
		map.put("SONY DSC-W80", new Double(5.75));
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
			setSiftPath(givenSettings.get(Settings.KEYPOINT_DETECTOR_PATH));
			setConvertPath(givenSettings.get(Settings.IMAGEMAGICK_CONVERT_PATH));
			setMogrifyPath(givenSettings.get(Settings.IMAGEMAGICK_MOGRIFY_PATH));
			setJheadPath(givenSettings.get(Settings.JHEAD_PATH));
		}
	}
	
}
