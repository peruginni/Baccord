package baccord.business.settings;

import baccord.business.BaseBusiness;
import baccord.exceptions.InvalidAppPathException;
import baccord.tools.OS;
import baccord.tools.ObjectStorage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Settings of application. Mainly paths to underlying application.
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class Settings extends BaseBusiness
{
	public static final int BUNDLER_PATH = 0;
	public static final int KEYPOINT_MATCHER_PATH = 1;
	public static final int KEYPOINT_DETECTOR_PATH = 2;
	public static final int PMVS_PATH = 3;
	public static final int CMVS_PATH = 4;
	public static final int IMAGEMAGICK_CONVERT_PATH = 5;
	public static final int IMAGEMAGICK_MOGRIFY_PATH = 6;
	public static final int PLY_EXPLORER_PATH = 7;
	public static final int FILE_EXPLORER_PATH = 8;
	public static final int JHEAD_PATH = 9;
	public static final int GENOPTION_PATH = 10;
	public static final int RADIALUNDISTORT_PATH = 11;
	public static final int BUNDLE2PMVS_PATH = 12;
	public static final int BUNDLE2VIS_PATH = 13;
	
	private String storagePath = "./settings.dat";
	private Map<Integer, String> map;
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public String getStoragePath()
	{
		return this.storagePath;
	}
	
	public void setStoragePath(String storagePath)
	{
		this.storagePath = storagePath;
	}
	
	public Map<Integer, String> getMap()
	{
		if(map == null) {
			try {
				map = (Map<Integer, String>) ObjectStorage.load(storagePath);
			} catch(FileNotFoundException ex) {}

			if(map == null) {
				map = new HashMap<Integer, String>();
				fillDefaults();
			}
		}
		return map;
	}
	
	public void setMap(Map<Integer, String> map)
	{
		this.map = map;
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	public String get(int name) 
	{
		return getMap().get(name);
	}
	
	public void set(int name, String value) throws InvalidAppPathException
	{
		switch(name) {
			case BUNDLER_PATH:
			case KEYPOINT_DETECTOR_PATH:
			case KEYPOINT_MATCHER_PATH:
			case PMVS_PATH:
			case CMVS_PATH:
			case IMAGEMAGICK_CONVERT_PATH:
			case IMAGEMAGICK_MOGRIFY_PATH:
			case PLY_EXPLORER_PATH:
			case JHEAD_PATH:
			case GENOPTION_PATH:
			case RADIALUNDISTORT_PATH:
			case BUNDLE2PMVS_PATH:
			case BUNDLE2VIS_PATH:
				File file = new File(value);
				if(!file.exists() && !file.canExecute()) {
					throw new InvalidAppPathException();
				}
				break;
			case FILE_EXPLORER_PATH:
			default:	
		}
		
		getMap();
		map.put(name, value);
		ObjectStorage.save(map, storagePath);
		notifyObservers();
	}
	
	protected String getCanonicalPath(String path)
	{
		try {
			File f = new File(path);
			return f.getCanonicalPath();
		} catch (IOException ex) {
			Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
			return path;
		}
	}
	
	protected void fillDefaults()
	{
		map.put(IMAGEMAGICK_CONVERT_PATH, "/usr/bin/convert");
		map.put(IMAGEMAGICK_MOGRIFY_PATH, "/usr/bin/mogrify");
		map.put(PLY_EXPLORER_PATH, "/usr/bin/meshlab");
		
		map.put(BUNDLER_PATH, getCanonicalPath("./lib/bundler"));
		map.put(BUNDLE2PMVS_PATH, getCanonicalPath("./lib/Bundle2PMVS"));
		map.put(BUNDLE2VIS_PATH, getCanonicalPath("./lib/Bundle2Vis"));
		map.put(RADIALUNDISTORT_PATH, getCanonicalPath("./lib/RadialUndistort"));
		map.put(GENOPTION_PATH, getCanonicalPath("./lib/genOption"));
		map.put(CMVS_PATH, getCanonicalPath("./lib/cmvs"));
		map.put(PMVS_PATH, getCanonicalPath("./lib/pmvs"));
		map.put(KEYPOINT_DETECTOR_PATH, getCanonicalPath("./lib/sift"));
		map.put(KEYPOINT_MATCHER_PATH, getCanonicalPath("./lib/KeyMatchFull"));
		if(OS.isMacOSX()) {
			map.put(JHEAD_PATH, getCanonicalPath("./lib/jhead"));
			map.put(FILE_EXPLORER_PATH, "open");
		} else if(OS.isWindows()) {
			map.put(JHEAD_PATH, getCanonicalPath("./lib/jhead.exe"));
			map.put(FILE_EXPLORER_PATH, "explorer.exe");
		} else if(OS.isLinux()) {
			map.put(JHEAD_PATH, getCanonicalPath("./lib/jhead"));
			map.put(FILE_EXPLORER_PATH, "nautilus");
		}
	}
}
