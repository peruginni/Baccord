
package baccord.business.images;

import baccord.business.BaseBusiness;
import baccord.tools.ObjectStorage;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageFolders extends BaseBusiness implements ImageFolders
{
	private String storagePath = "/RecentImageFolders.dat";
	private int maximumStoredRecentFolders = 10;
	private Queue<String> recentFolders;

	public void saveRecentlyUsed(String folderPath)
	{
		// add path to list
		Queue<String> list = getRecentlyUsed();
		list.offer(folderPath);

		// remove older
		if(list.size() > maximumStoredRecentFolders) {
			list.poll();
		}

		// save images
		ObjectStorage.save(list, storagePath);
	}

	public Queue<String> getRecentlyUsed()
	{
		if(recentFolders == null) {
			recentFolders = (Queue<String>) ObjectStorage.load(storagePath);

			if(recentFolders == null) {
				recentFolders = new LinkedList<String>();
			}
		}
		return recentFolders;
	}

}
