
package baccord.business.images;

import java.io.File;
import java.util.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageFoldersTest 
{
	
	@Test
	public void testClearRecentlyUsed()
	{
		System.out.println("clearRecentlyused");
		
		BasicImageFolders instance = new BasicImageFolders();
		instance.saveRecentlyUsed("folder");
		
		File storage = new File(instance.getStoragePath());
		assertTrue(storage.exists());
		
		instance.clearRecentlyUsed();
		assertFalse(storage.exists());
		
	}
	
	/**
	 * Test of saveRecentlyUsed method, of class BasicImageFolders.
	 */ @Test
	public void testSaveRecentlyUsed()
	{
		System.out.println("saveRecentlyUsed");
		String folder = "folder";
		BasicImageFolders instance = new BasicImageFolders();
		
		instance.clearRecentlyUsed();
		instance.saveRecentlyUsed(folder);
		File storage = new File(instance.getStoragePath());
		assertTrue(storage.exists());
		
		instance = new BasicImageFolders();
		Queue<String> folders = instance.getRecentlyUsed();
		assertEquals(folders.element(), folder);
	}

}