
package baccord.business.images;

import baccord.business.BaseBusiness;
import baccord.tools.ObjectStorage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageFolders extends BaseBusiness implements ImageFolders
{
	private String storagePath = "./RecentImageFolders.dat";
	private int maximumStoredRecentFolders = 10;
	private LinkedList<String> recentFolders;

	private static final Logger logger = Logger.getLogger(BasicImageFolders.class.getName());

	public String getStoragePath()
	{
		return storagePath;
	}
	
	public void setStoragePath(String path)
	{
		storagePath = path;
	}

	public void saveRecentlyUsed(String folder)
	{
		getRecentlyUsed();
		// add path to list
		
		recentFolders.addFirst(folder);

		// remove older
		if(recentFolders.size() > maximumStoredRecentFolders) {
			recentFolders.removeLast();
		}

		ObjectStorage.save(recentFolders, storagePath);
	}

	public List<String> getRecentlyUsed()
	{
		if(recentFolders == null) {
			try {
				recentFolders = (LinkedList<String>) ObjectStorage.load(storagePath);
			} catch (FileNotFoundException ex) {
				// file was probably not yet created
			}

			if(recentFolders == null) {
				recentFolders = new LinkedList<String>();
			}
		}
		return recentFolders;
	}

	public void clearRecentlyUsed()
	{
		File file = new File(storagePath);
		if(file.exists()) {
			file.delete();
		}
		recentFolders = new LinkedList();
	}
	
}
