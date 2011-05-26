
package baccord.business.images;

import java.util.List;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface ImageFolders
{
	public String getStoragePath();
	public void setStoragePath(String path);
	
	/**
	 * Save given path to recently used direcotires
	 * 
	 * @param folderPath 
	 */
	public void saveRecentlyUsed(String folderPath);
	
	/**
	 * Return recently saved folders
	 * 
	 * @return 
	 */
	public List<String> getRecentlyUsed();
	
	/**
	 * Clear recently used folders
	 */
	public void clearRecentlyUsed();
}
