
package baccord.business.images;

import java.util.List;

/**
 * Interface for managing operations with image folders.
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface ImageFolders
{
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public String getStoragePath();
	public void setStoragePath(String path);
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
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
