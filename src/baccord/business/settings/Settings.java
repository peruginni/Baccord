package baccord.business.settings;

import baccord.business.BaseBusiness;
import baccord.tools.ObjectStorage;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
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
	
	private String storagePath = "./settings.dat";
	private Map<Integer, String> map;
	
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
			}
		}
		return map;
	}
	
	public void setMap(Map<Integer, String> map)
	{
		this.map = map;
	}
	
	public String get(int name) 
	{
		return getMap().get(name);
	}
	
	public void set(int name, String value)
	{
		getMap();
		map.put(name, value);
		ObjectStorage.save(map, storagePath);
		notifyObservers();
	}
}
