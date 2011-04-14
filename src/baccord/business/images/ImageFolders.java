
package baccord.business.images;

import java.util.Queue;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface ImageFolders
{
	public void saveRecentlyUsed(String folderPath);
	public Queue<String> getRecentlyUsed();
}
