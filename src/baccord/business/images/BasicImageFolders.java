
package baccord.business.images;

import baccord.business.BaseBusiness;
import baccord.tools.ObjectStorage;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageFolders extends BaseBusiness implements ImageFolders
{
	private String storagePath = "/RecentImageFolders.dat";
	private int maximumStoredRecentFolders = 10;
	private Queue<String> recentFolders;

	private static final Logger logger = Logger.getLogger(BasicImageFolders.class.getName());

	public void saveRecentlyUsed(String folder)
	{
		// add path to list
		Queue<String> list = getRecentlyUsed();
		list.offer(folder);

		// remove older
		if(list.size() > maximumStoredRecentFolders) {
			list.poll();
		}

		ObjectStorage.save(list, storagePath);
	}

	public Queue<String> getRecentlyUsed()
	{
		if(recentFolders == null) {
			try {
				recentFolders = (Queue<String>) ObjectStorage.load(storagePath);
			} catch (FileNotFoundException ex) {
				// file was probably not yet created
			}

			if(recentFolders == null) {
				recentFolders = new LinkedList<String>();
			}
		}
		return recentFolders;
	}

}
