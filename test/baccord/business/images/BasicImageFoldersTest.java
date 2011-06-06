
package baccord.business.images;

import java.util.List;
import baccord.tools.DI;
import java.io.File;
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
		
		ImageFolders instance = DI.get(ImageFolders.class);
		instance.saveRecentlyUsed("folder");
		
		File storage = new File(instance.getStoragePath());
		assertTrue(storage.exists());
		
		instance.clearRecentlyUsed();
		assertFalse(storage.exists());
		
	}
	
	@Test
	public void testSaveRecentlyUsed()
	{
		System.out.println("saveRecentlyUsed");
		
		String folder = "folder";
		ImageFolders instance = DI.get(ImageFolders.class);
		File storage = new File(instance.getStoragePath());
		storage.delete();
		
		instance.clearRecentlyUsed();
		instance.saveRecentlyUsed(folder);
		assertTrue(storage.exists());
		
		instance = new BasicImageFolders();
		List<String> recentlyUsed = instance.getRecentlyUsed();
		String newest = recentlyUsed.get(0);
		assertEquals(folder, newest);
		
		storage.delete();
		assertFalse(storage.exists());
	}
}