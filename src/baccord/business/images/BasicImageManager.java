package baccord.business.images;

import baccord.exceptions.SiftAppMissingException;
import baccord.tools.FileHelper;
import baccord.tools.ObjectStorage;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifDirectory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageManager implements ImageManager
{
	
	public final static String SIFT_EXTENSION = ".key";
	public final static String PGM_EXTENSION = ".pgm";
	
	private String siftPath;
	
	private HashMap<String, Float> camerasCcdWidths;
	private String cameraCcdWidthsStoragePath = "./cameraCcdWidths.dat";
	
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public void setSiftPath(String path) throws SiftAppMissingException
	{
		File siftFile = new File(path);
		if(!siftFile.exists()) {
			throw new SiftAppMissingException();
		}
		siftPath = path;
	}
	
	public String getSiftPath() throws SiftAppMissingException
	{
		if(siftPath != null) {
			throw new SiftAppMissingException();
		}
		return siftPath;
	}
	
	public HashMap<String, Float> getCameraCcdWidths()
	{
		if(camerasCcdWidths == null) {
			try {
				camerasCcdWidths = (HashMap<String, Float>) ObjectStorage.load(cameraCcdWidthsStoragePath);
				
			} catch (FileNotFoundException ex) {
				// file was probably not yet created
			}

			if(camerasCcdWidths == null) {
				camerasCcdWidths = new HashMap<String, Float>();
				fillDefaultCameraCcdWidths(camerasCcdWidths);
			}
		}
		
		return camerasCcdWidths;
	}
	
	public void setCameraCcdWidths(HashMap<String, Float> map)
	{
		camerasCcdWidths = map;
	}
	
	public void setCcdWidthForCamera(String camera, float width)
	{
		HashMap<String, Float> map = getCameraCcdWidths();
		map.put(camera, new Float(width));
		ObjectStorage.save(map, cameraCcdWidthsStoragePath);
	}
	
	public float getCcdWidthForCamera(String camera)
	{
		HashMap<String, Float> map = getCameraCcdWidths();
		Float result = map.get(camera);
		if(result != null) {
			return result.floatValue();
		} else {
			return 0;
		}
	}
	
	public String getCameraCcdWidthsStoragePath()
	{
		return this.cameraCcdWidthsStoragePath;
	}
	
	public void setCameraCcdWidthsStoragePath(String cameraCcdWidthsStoragePath)
	{
		this.cameraCcdWidthsStoragePath = cameraCcdWidthsStoragePath;
	}
		
	
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	public void resize(Image image, int width, int height)
	{
		// convert dragon.gif    -resize 64x64\>  shrink_dragon.gif
		try {
			Process p = new ProcessBuilder(
				"convert",
				image.getPath(),
				"-resize " + width + "x" + height + "\\>",
				image.getPath()
			).start(); 
			p.waitFor();
			
		} catch (InterruptedException ex) {
			Logger.getLogger(BasicImageManager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(BasicImageManager.class.getName()).log(Level.SEVERE, null, ex);
		} 
	}

	public boolean hasSift(Image image)
	{
		String imageDirectory = FileHelper.getDirectory(image.getPath());
		String imageBasename = FileHelper.getBasename(image.getPath()); 
		File siftFile = new File(FileHelper.mergePath(imageDirectory, imageBasename + SIFT_EXTENSION));
		return siftFile.exists();
	}

	public void performSift(Image image) throws SiftAppMissingException
	{
		String sift = getSiftPath();
		
		String imageJpgPath = image.getPath();
		String imageDirectory = FileHelper.getDirectory(imageJpgPath);
		String imageBasename = FileHelper.getBasename(imageJpgPath);
		String imagePgmPath = FileHelper.mergePath(imageDirectory, imageBasename + PGM_EXTENSION);
		String imageSiftPath = FileHelper.mergePath(imageDirectory, imageBasename + SIFT_EXTENSION);
		
		try {
			/*
			 echo "mogrify -format pgm $IMAGE_DIR/$d; 
			       $SIFT < $pgm_file > $key_file; 
			       rm $pgm_file; 
			       gzip -f $key_file"
			 */
			
			// convert image to pgm 
			Process p = new ProcessBuilder(
				"mogrify",
				"-format pgm " + imageJpgPath
			).start(); 
			p.waitFor();

			// execute sift command and save keyfile
			p = new ProcessBuilder(
				sift + " < " + imagePgmPath + " > " + imageSiftPath
			).start(); 
			p.waitFor();
			
			// remove pgm temp image
			p = new ProcessBuilder(
				"rm", imagePgmPath
			).start(); 
			p.waitFor();
			
			// compress sift file
			p = new ProcessBuilder(
				"gzip", 
				"-f " + imagePgmPath
			).start(); 
			p.waitFor();
			
		} catch (InterruptedException ex) {
			Logger.getLogger(BasicImageManager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(BasicImageManager.class.getName()).log(Level.SEVERE, null, ex);
		} 
	}

	public float getFocalLegth(Image image)
	{
		float result = 0;
		
		File file = new File(image.getPath());
		try {
			Metadata metadata = JpegMetadataReader.readMetadata(file);
			Directory directory = metadata.getDirectory(ExifDirectory.class);
			String make = directory.getString(ExifDirectory.TAG_MAKE);
			String model = directory.getString(ExifDirectory.TAG_MODEL);
			Float focalLegthMm = Float.valueOf(directory.getString(ExifDirectory.TAG_FOCAL_LENGTH).replace("mm", ""));			
			Float ccdWidthMm = getCcdWidthForCamera(make + " " + model);
			int resolutionX = directory.getInt(ExifDirectory.TAG_X_RESOLUTION);
			int resolutionY = directory.getInt(ExifDirectory.TAG_Y_RESOLUTION);
			
			if(focalLegthMm == 0 || ccdWidthMm == 0 || resolutionX == 0) {
				result = 0;
			} 
			
			if(resolutionX < resolutionY) {
				// aspect ratio is wrong
				int tmp = resolutionX;
				resolutionX = resolutionY;
				resolutionY = tmp;
			}
			
			if(result != 0) {
				//$focal_pixels = $res_x * ($focal_mm / $ccd_width_mm);
				result = resolutionX * (focalLegthMm / ccdWidthMm);
				//$line = sprintf("%s.jpg 0 %0.5f", $basename, $SCALE * $focal_pixels);
				result = 1.0f * result;
			}
			
		} catch (MetadataException ex) {
			Logger.getLogger(BasicImageManager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (JpegProcessingException ex) {
			Logger.getLogger(BasicImageManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return result;
	}
	
	/**
	 * Fill map with default values
	 * 
	 * Reused from original bundler perl script extract_focal.pl
	 * 
	 * @param map 
	 */
	public static void fillDefaultCameraCcdWidths(HashMap<String, Float> map)
	{
		map.put("Asahi Optical Co.,Ltd.  PENTAX Optio330RS", new Float(7.176));
		map.put("Canon Canon DIGITAL IXUS 400", new Float(7.176));
		map.put("Canon Canon DIGITAL IXUS 40", new Float(5.76));
		map.put("Canon Canon DIGITAL IXUS 430", new Float(7.176));
		map.put("Canon Canon DIGITAL IXUS 500", new Float(7.176));
		map.put("Canon Canon DIGITAL IXUS 50", new Float(5.76));
		map.put("Canon Canon DIGITAL IXUS 55", new Float(5.76));
		map.put("Canon Canon DIGITAL IXUS 60", new Float(5.76));
		map.put("Canon Canon DIGITAL IXUS 65", new Float(5.76));
		map.put("Canon Canon DIGITAL IXUS 700", new Float(7.176));
		map.put("Canon Canon DIGITAL IXUS 750", new Float(7.176));
		map.put("Canon Canon DIGITAL IXUS 800 IS", new Float(5.76));
		map.put("Canon Canon DIGITAL IXUS II", new Float(5.27));
		map.put("Canon Canon EOS 10D", new Float(22.7));
		map.put("Canon Canon EOS-1D Mark II", new Float(28.7));
		map.put("Canon Canon EOS-1Ds Mark II", new Float(35.95));
		map.put("Canon Canon EOS  20D", new Float(22.5));
		map.put("Canon Canon EOS 20D", new Float(22.5));
		map.put("Canon Canon EOS 300D DIGITAL", new Float(22.66));
		map.put("Canon Canon EOS 30D", new Float(22.5));
		map.put("Canon Canon EOS 350D DIGITAL", new Float(22.2));
		map.put("Canon Canon EOS 400D DIGITAL", new Float(22.2));
		map.put("Canon Canon EOS 40D", new Float(22.2));
		map.put("Canon Canon EOS 5D", new Float(35.8));
		map.put("Canon Canon EOS DIGITAL REBEL", new Float(22.66));
		map.put("Canon Canon EOS DIGITAL REBEL XT", new Float(22.2));
		map.put("Canon Canon EOS DIGITAL REBEL XTi", new Float(22.2));
		map.put("Canon Canon EOS Kiss Digital", new Float(22.66));
		map.put("Canon Canon IXY DIGITAL 600", new Float(7.176));
		map.put("Canon Canon PowerShot A20", new Float(7.176));
		map.put("Canon Canon PowerShot A400", new Float(4.54));
		map.put("Canon Canon PowerShot A40", new Float(5.27));
		map.put("Canon Canon PowerShot A510", new Float(5.76));
		map.put("Canon Canon PowerShot A520", new Float(5.76));
		map.put("Canon Canon PowerShot A530", new Float(5.76));
		map.put("Canon Canon PowerShot A60", new Float(5.27));
		map.put("Canon Canon PowerShot A620", new Float(7.176));
		map.put("Canon Canon PowerShot A630", new Float(7.176));
		map.put("Canon Canon PowerShot A640", new Float(7.176));
		map.put("Canon Canon PowerShot A700", new Float(5.76));
		map.put("Canon Canon PowerShot A70", new Float(5.27));
		map.put("Canon Canon PowerShot A710 IS", new Float(5.76));
		map.put("Canon Canon PowerShot A75", new Float(5.27));
		map.put("Canon Canon PowerShot A80", new Float(7.176));
		map.put("Canon Canon PowerShot A85", new Float(5.27));
		map.put("Canon Canon PowerShot A95", new Float(7.176));
		map.put("Canon Canon PowerShot G1", new Float(7.176));
		map.put("Canon Canon PowerShot G2", new Float(7.176));
		map.put("Canon Canon PowerShot G3", new Float(7.176));
		map.put("Canon Canon PowerShot G5", new Float(7.176));
		map.put("Canon Canon PowerShot G6", new Float(7.176));
		map.put("Canon Canon PowerShot G7", new Float(7.176));
		map.put("Canon Canon PowerShot G9", new Float(7.600));
		map.put("Canon Canon PowerShot Pro1", new Float(8.8));
		map.put("Canon Canon PowerShot S110", new Float(5.27));
		map.put("Canon Canon PowerShot S1 IS", new Float(5.27));
		map.put("Canon Canon PowerShot S200", new Float(5.27));
		map.put("Canon Canon PowerShot S2 IS", new Float(5.76));
		map.put("Canon Canon PowerShot S30", new Float(7.176));
		map.put("Canon Canon PowerShot S3 IS", new Float(5.76));
		map.put("Canon Canon PowerShot S400", new Float(7.176));
		map.put("Canon Canon PowerShot S40", new Float(7.176));
		map.put("Canon Canon PowerShot S410", new Float(7.176));
		map.put("Canon Canon PowerShot S45", new Float(7.176));
		map.put("Canon Canon PowerShot S500", new Float(7.176));
		map.put("Canon Canon PowerShot S50", new Float(7.176));
		map.put("Canon Canon PowerShot S60", new Float(7.176));
		map.put("Canon Canon PowerShot S70", new Float(7.176));
		map.put("Canon Canon PowerShot S80", new Float(7.176));
		map.put("Canon Canon PowerShot SD1000", new Float(5.75));
		map.put("Canon Canon PowerShot SD100", new Float(5.27));
		map.put("Canon Canon PowerShot SD10", new Float(5.75));
		map.put("Canon Canon PowerShot SD110", new Float(5.27));
		map.put("Canon Canon PowerShot SD200", new Float(5.76));
		map.put("Canon Canon PowerShot SD300", new Float(5.76));
		map.put("Canon Canon PowerShot SD400", new Float(5.76));
		map.put("Canon Canon PowerShot SD450", new Float(5.76));
		map.put("Canon Canon PowerShot SD500", new Float(7.176));
		map.put("Canon Canon PowerShot SD550", new Float(7.176));
		map.put("Canon Canon PowerShot SD600", new Float(5.76));
		map.put("Canon Canon PowerShot SD630", new Float(5.76));
		map.put("Canon Canon PowerShot SD700 IS", new Float(5.76));
		map.put("Canon Canon PowerShot SD750", new Float(5.75));
		map.put("Canon Canon PowerShot SD800 IS", new Float(5.76));
		map.put("Canon EOS 300D DIGITAL", new Float(22.66));
		map.put("Canon EOS DIGITAL REBEL", new Float(22.66));
		map.put("Canon PowerShot A510", new Float(5.76));
		map.put("Canon PowerShot S30", new Float(7.176));
		map.put("CASIO COMPUTER CO., new Float(LTD. EX-S500", new Float(5.76));
		map.put("CASIO COMPUTER CO., new Float(LTD. EX-Z1000", new Float(7.716));
		map.put("CASIO COMPUTER CO., new Float(LTD  EX-Z30", new Float(5.76));
		map.put("CASIO COMPUTER CO., new Float(LTD. EX-Z600", new Float(5.76));
		map.put("CASIO COMPUTER CO., new Float(LTD. EX-Z60", new Float(7.176));
		map.put("CASIO COMPUTER CO., new Float(LTD  EX-Z750", new Float(7.176));
		map.put("CASIO COMPUTER CO.,LTD. EX-Z850", new Float(7.176));
		map.put("EASTMAN KODAK COMPANY KODAK CX7330 ZOOM DIGITAL CAMERA", new Float(5.27));
		map.put("EASTMAN KODAK COMPANY KODAK CX7530 ZOOM DIGITAL CAMERA", new Float(5.76));
		map.put("EASTMAN KODAK COMPANY KODAK DX3900 ZOOM DIGITAL CAMERA", new Float(7.176));
		map.put("EASTMAN KODAK COMPANY KODAK DX4900 ZOOM DIGITAL CAMERA", new Float(7.176));
		map.put("EASTMAN KODAK COMPANY KODAK DX6340 ZOOM DIGITAL CAMERA", new Float(5.27));
		map.put("EASTMAN KODAK COMPANY KODAK DX6490 ZOOM DIGITAL CAMERA", new Float(5.76));
		map.put("EASTMAN KODAK COMPANY KODAK DX7630 ZOOM DIGITAL CAMERA", new Float(7.176));
		map.put("EASTMAN KODAK COMPANY KODAK Z650 ZOOM DIGITAL CAMERA", new Float(5.76));
		map.put("EASTMAN KODAK COMPANY KODAK Z700 ZOOM DIGITAL CAMERA", new Float(5.76));
		map.put("EASTMAN KODAK COMPANY KODAK Z740 ZOOM DIGITAL CAMERA", new Float(5.76));
		map.put("EASTMAN KODAK COMPANY KODAK Z740 ZOOM DIGITAL CAMERA", new Float(5.76));
		map.put("FUJIFILM FinePix2600Zoom", new Float(5.27));
		map.put("FUJIFILM FinePix40i", new Float(7.600));
		map.put("FUJIFILM FinePix A310", new Float(5.27));
		map.put("FUJIFILM FinePix A330", new Float(5.27));
		map.put("FUJIFILM FinePix A600", new Float(7.600));
		map.put("FUJIFILM FinePix E500", new Float(5.76));
		map.put("FUJIFILM FinePix E510", new Float(5.76));
		map.put("FUJIFILM FinePix E550", new Float(7.600));
		map.put("FUJIFILM FinePix E900", new Float(7.78));
		map.put("FUJIFILM FinePix F10", new Float(7.600));
		map.put("FUJIFILM FinePix F30", new Float(7.600));
		map.put("FUJIFILM FinePix F450", new Float(5.76));
		map.put("FUJIFILM FinePix F601 ZOOM", new Float(7.600));
		map.put("FUJIFILM FinePix S3Pro", new Float(23.0));
		map.put("FUJIFILM FinePix S5000", new Float(5.27));
		map.put("FUJIFILM FinePix S5200", new Float(5.76));
		map.put("FUJIFILM FinePix S5500", new Float(5.27));
		map.put("FUJIFILM FinePix S6500fd", new Float(7.600));
		map.put("FUJIFILM FinePix S7000", new Float(7.600));
		map.put("FUJIFILM FinePix Z2", new Float(5.76));
		map.put("Hewlett-Packard hp 635 Digital Camera", new Float(4.54));
		map.put("Hewlett-Packard hp PhotoSmart 43x series", new Float(5.27));
		map.put("Hewlett-Packard HP PhotoSmart 618 (V1.1)", new Float(5.27));
		map.put("Hewlett-Packard HP PhotoSmart C945 (V01.61)", new Float(7.176));
		map.put("Hewlett-Packard HP PhotoSmart R707 (V01.00)", new Float(7.176));
		map.put("KONICA MILOLTA  DYNAX 5D", new Float(23.5));
		map.put("Konica Minolta Camera, new Float(Inc. DiMAGE A2", new Float(8.80));
		map.put("KONICA MINOLTA CAMERA, new Float(Inc. DiMAGE G400", new Float(5.76));
		map.put("Konica Minolta Camera, new Float(Inc. DiMAGE Z2", new Float(5.76));
		map.put("KONICA MINOLTA DiMAGE A200", new Float(8.80));
		map.put("KONICA MINOLTA DiMAGE X1", new Float(7.176));
		map.put("KONICA MINOLTA  DYNAX 5D", new Float(23.5));
		map.put("Minolta Co., new Float(Ltd. DiMAGE F100", new Float(7.176));
		map.put("Minolta Co., new Float(Ltd. DiMAGE Xi", new Float(5.27));
		map.put("Minolta Co., new Float(Ltd. DiMAGE Xt", new Float(5.27));
		map.put("Minolta Co., new Float(Ltd. DiMAGE Z1", new Float(5.27));
		map.put("NIKON COOLPIX L3", new Float(5.76));
		map.put("NIKON COOLPIX P2", new Float(7.176));
		map.put("NIKON COOLPIX S4", new Float(5.76));
		map.put("NIKON COOLPIX S7c", new Float(5.76));
		map.put("NIKON CORPORATION NIKON D100", new Float(23.7));
		map.put("NIKON CORPORATION NIKON D1", new Float(23.7));
		map.put("NIKON CORPORATION NIKON D1H", new Float(23.7));
		map.put("NIKON CORPORATION NIKON D200", new Float(23.6));
		map.put("NIKON CORPORATION NIKON D2H", new Float(23.3));
		map.put("NIKON CORPORATION NIKON D2X", new Float(23.7));
		map.put("NIKON CORPORATION NIKON D40", new Float(23.7));
		map.put("NIKON CORPORATION NIKON D50", new Float(23.7));
		map.put("NIKON CORPORATION NIKON D60", new Float(23.6));
		map.put("NIKON CORPORATION NIKON D70", new Float(23.7));
		map.put("NIKON CORPORATION NIKON D70s", new Float(23.7));
		map.put("NIKON CORPORATION NIKON D80", new Float(23.6));
		map.put("NIKON E2500", new Float(5.27));
		map.put("NIKON E2500", new Float(5.27));
		map.put("NIKON E3100", new Float(5.27));
		map.put("NIKON E3200", new Float(5.27));
		map.put("NIKON E3700", new Float(5.27));
		map.put("NIKON E4200", new Float(7.176));
		map.put("NIKON E4300", new Float(7.18));
		map.put("NIKON E4500", new Float(7.176));
		map.put("NIKON E4600", new Float(5.76));
		map.put("NIKON E5000", new Float(8.80));
		map.put("NIKON E5200", new Float(7.176));
		map.put("NIKON E5400", new Float(7.176));
		map.put("NIKON E5600", new Float(5.76));
		map.put("NIKON E5700", new Float(8.80));
		map.put("NIKON E5900", new Float(7.176));
		map.put("NIKON E7600", new Float(7.176));
		map.put("NIKON E775", new Float(5.27));
		map.put("NIKON E7900", new Float(7.176));
		map.put("NIKON E7900", new Float(7.176));
		map.put("NIKON E8800", new Float(8.80));
		map.put("NIKON E990", new Float(7.176));
		map.put("NIKON E995", new Float(7.176));
		map.put("NIKON S1", new Float(5.76));
		map.put("Nokia N80", new Float(5.27));
		map.put("Nokia N80", new Float(5.27));
		map.put("Nokia N93", new Float(4.536));
		map.put("Nokia N95", new Float(5.7));
		map.put("OLYMPUS CORPORATION     C-5000Z", new Float(7.176));
		map.put("OLYMPUS CORPORATION C5060WZ", new Float(7.176));
		map.put("OLYMPUS CORPORATION C750UZ", new Float(5.27));
		map.put("OLYMPUS CORPORATION C765UZ", new Float(5.76));
		map.put("OLYMPUS CORPORATION C8080WZ", new Float(8.80));
		map.put("OLYMPUS CORPORATION X250, new Float(D560Z,C350Z", new Float(5.76));
		map.put("OLYMPUS CORPORATION     X-3, new Float(C-60Z", new Float(7.176));
		map.put("OLYMPUS CORPORATION X400, new Float(D580Z,C460Z", new Float(5.27));
		map.put("OLYMPUS IMAGING CORP.   E-500", new Float(17.3));
		map.put("OLYMPUS IMAGING CORP.   FE115, new Float(X715", new Float(5.76));
		map.put("OLYMPUS IMAGING CORP. SP310", new Float(7.176));
		map.put("OLYMPUS IMAGING CORP.   SP510UZ", new Float(5.75));
		map.put("OLYMPUS IMAGING CORP.   SP550UZ", new Float(5.76));
		map.put("OLYMPUS IMAGING CORP.   uD600, new Float(S600", new Float(5.75));
		map.put("OLYMPUS_IMAGING_CORP.   X450, new Float(D535Z,C370Z", new Float(5.27));
		map.put("OLYMPUS IMAGING CORP. X550, new Float(D545Z,C480Z", new Float(5.76));
		map.put("OLYMPUS OPTICAL CO., new Float(LTD C2040Z", new Float(6.40));
		map.put("OLYMPUS OPTICAL CO., new Float(LTD C211Z", new Float(5.27));
		map.put("OLYMPUS OPTICAL CO., new Float(LTD C2Z,D520Z,C220Z", new Float(4.54));
		map.put("OLYMPUS OPTICAL CO., new Float(LTD C3000Z", new Float(7.176));
		map.put("OLYMPUS OPTICAL CO.,LTD C300Z,D550Z", new Float(5.4));
		map.put("OLYMPUS OPTICAL CO., new Float(LTD C4100Z,C4000Z", new Float(7.176));
		map.put("OLYMPUS OPTICAL CO., new Float(LTD C750UZ", new Float(5.27));
		map.put("OLYMPUS OPTICAL CO., new Float(LTD X-2,C-50Z", new Float(7.176));
		map.put("OLYMPUS SP550UZ", new Float(5.76));
		map.put("OLYMPUS X100, new Float(D540Z,C310Z", new Float(5.27));
		map.put("Panasonic DMC-FX01", new Float(5.76));
		map.put("Panasonic DMC-FX07", new Float(5.75));
		map.put("Panasonic DMC-FX9", new Float(5.76));
		map.put("Panasonic DMC-FZ20", new Float(5.760));
		map.put("Panasonic DMC-FZ2", new Float(4.54));
		map.put("Panasonic DMC-FZ30", new Float(7.176));
		map.put("Panasonic DMC-FZ50", new Float(7.176));
		map.put("Panasonic DMC-FZ5", new Float(5.760));
		map.put("Panasonic DMC-FZ7", new Float(5.76));
		map.put("Panasonic DMC-LC1", new Float(8.80));
		map.put("Panasonic DMC-LC33", new Float(5.760));
		map.put("Panasonic DMC-LX1", new Float(8.50));
		map.put("Panasonic DMC-LZ2", new Float(5.76));
		map.put("Panasonic DMC-TZ1", new Float(5.75));
		map.put("Panasonic DMC-TZ3", new Float(5.68));
		map.put("PENTAX Corporation  PENTAX *ist DL", new Float(23.5));
		map.put("PENTAX Corporation  PENTAX *ist DS2", new Float(23.5));
		map.put("PENTAX Corporation  PENTAX *ist DS", new Float(23.5));
		map.put("PENTAX Corporation  PENTAX K100D", new Float(23.5));
		map.put("PENTAX Corporation PENTAX Optio 450", new Float(7.176));
		map.put("PENTAX Corporation PENTAX Optio 550", new Float(7.176));
		map.put("PENTAX Corporation PENTAX Optio E10", new Float(5.76));
		map.put("PENTAX Corporation PENTAX Optio S40", new Float(5.76));
		map.put("PENTAX Corporation  PENTAX Optio S4", new Float(5.76));
		map.put("PENTAX Corporation PENTAX Optio S50", new Float(5.76));
		map.put("PENTAX Corporation  PENTAX Optio S5i", new Float(5.76));
		map.put("PENTAX Corporation  PENTAX Optio S5z", new Float(5.76));
		map.put("PENTAX Corporation  PENTAX Optio SV", new Float(5.76));
		map.put("PENTAX Corporation PENTAX Optio WP", new Float(5.75));
		map.put("RICOH CaplioG3 modelM", new Float(5.27));
		map.put("RICOH       Caplio GX", new Float(7.176));
		map.put("RICOH       Caplio R30", new Float(5.75));
		map.put("Samsung  Digimax 301", new Float(5.27));
		map.put("Samsung Techwin <Digimax i5, new Float(Samsung #1>", new Float(5.76));
		map.put("SAMSUNG TECHWIN Pro 815", new Float(8.80));
		map.put("SONY DSC-F828", new Float(8.80));
		map.put("SONY DSC-N12", new Float(7.176));
		map.put("SONY DSC-P100", new Float(7.176));
		map.put("SONY DSC-P10", new Float(7.176));
		map.put("SONY DSC-P12", new Float(7.176));
		map.put("SONY DSC-P150", new Float(7.176));
		map.put("SONY DSC-P200", new Float(7.176));
		map.put("SONY DSC-P52", new Float(5.27));
		map.put("SONY DSC-P72", new Float(5.27));
		map.put("SONY DSC-P73", new Float(5.27));
		map.put("SONY DSC-P8", new Float(5.27));
		map.put("SONY DSC-R1", new Float(21.5));
		map.put("SONY DSC-S40", new Float(5.27));
		map.put("SONY DSC-S600", new Float(5.760));
		map.put("SONY DSC-T9", new Float(7.18));
		map.put("SONY DSC-V1", new Float(7.176));
		map.put("SONY DSC-W1", new Float(7.176));
		map.put("SONY DSC-W30", new Float(5.760));
		map.put("SONY DSC-W50", new Float(5.75));
		map.put("SONY DSC-W5", new Float(7.176));
		map.put("SONY DSC-W7", new Float(7.176));
		map.put("SONY DSC-W80", new Float(5.75));
	}
	
}
