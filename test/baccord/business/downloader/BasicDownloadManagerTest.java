
package baccord.business.downloader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.io.File;
import baccord.exceptions.CannotCreateDirectoryException;
import baccord.exceptions.DownloadMaxTimeExceededException;
import baccord.exceptions.PathMustBeDirectoryException;
import baccord.tools.FileHelper;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicDownloadManagerTest
{
	private LinkedList<DownloadItem> testItems;
	private static String defaultDownloadDirectory;

	@BeforeClass
	public static void setUpClass() throws Exception
	{
		defaultDownloadDirectory = FileHelper.getSystemIndependentPath("tmp","deep","download","directory");
	}

	@Before
	public void setUp() 
	{
		testItems = new LinkedList<DownloadItem>();

		// strawberries
		testItems.add(new DownloadItem("https://lh5.googleusercontent.com/_ydViQL_1CPo/SbxF3nt6WgI/AAAAAAAAGTI/PBCmXoeQxbg/P1050678.JPG"));

		// pillow fight
		testItems.add(new DownloadItem("https://lh5.googleusercontent.com/_ydViQL_1CPo/RfqDxbhNO6I/AAAAAAAAA0Q/34aYTbd4QNU/s512/06-1.jpg"));

		// flute
		testItems.add(new DownloadItem("https://lh5.googleusercontent.com/_ydViQL_1CPo/RhqCpvVsq4I/AAAAAAAABE0/Yasnj0P8Cy0/s640/65696199_fa2c77fb73_o.jpg"));
	}

	private void fillWithTestData(DownloadManager instance)
	{
		for(DownloadItem item : testItems) {
			instance.add(item);
		}
	}

	/**
	 * Test of setDownloadDirectory method, of class BasicDownloadManager.
	 */
	@Test
	public void testDownloadDirectory() throws CannotCreateDirectoryException, PathMustBeDirectoryException
	{
		System.out.println("get/set DownloadDirectory");

		DownloadManager instance = new BasicDownloadManager();

		instance.setDownloadDirectory(defaultDownloadDirectory);
		
		assertEquals(defaultDownloadDirectory, instance.getDownloadDirectory());
	}

	/**
	 * Test of add method, of class BasicDownloadManager.
	 */
	@Test
	public void testAdd()
	{
		System.out.println("add");

		DownloadManager instance = new BasicDownloadManager();

		testAdd(instance); // will add items
		testAdd(instance); // will maintain current and add some more 
	}
	
	private void testAdd(DownloadManager instance)
	{
		int initialNumberOfItems = instance.getAll().size();

		fillWithTestData(instance);

		List<DownloadItem> insertedItems = instance.getAll();
		assertEquals(initialNumberOfItems+testItems.size(), insertedItems.size());

		for(int i = insertedItems.size()-1; i >= initialNumberOfItems; i--) {
			DownloadItem foundUrl = insertedItems.get(i);
			DownloadItem expectedUrl = testItems.get(i-initialNumberOfItems);
			assertEquals(foundUrl, expectedUrl);
		}
	}

	/**
	 * Test of remove method, of class BasicDownloadManager.
	 */
	@Test
	public void testRemove()
	{
		System.out.println("remove");
		DownloadItem itemToRemove = testItems.getFirst();
		DownloadManager instance = new BasicDownloadManager();

		fillWithTestData(instance);

		instance.remove(itemToRemove);

		for(DownloadItem url : instance.getAll()) {
			assertNotSame(itemToRemove, url);
		}
	}

	/**
	 * Test of getAllUrls method, of class BasicDownloadManager.
	 */
	@Test
	public void testGetAll() throws CannotCreateDirectoryException, PathMustBeDirectoryException
	{
		System.out.println("getAll");
		DownloadManager instance = new BasicDownloadManager();

		instance.setDownloadDirectory(defaultDownloadDirectory);
		fillWithTestData(instance);

		List result = instance.getAll();

		assertEquals(result.size(), testItems.size());

		for(int i = 0; i < testItems.size(); i++) {
			assertEquals(result.get(i), testItems.get(i));
		}
	}

	/**
	 * Test of downloadSingle method, of class BasicDownloadManager.
	 */
	@Test
	public void testDownloadSingle() 
	{
		System.out.println("downloadSingle");

		DownloadItem item = testItems.getFirst();
		item.setTargetDirectory(defaultDownloadDirectory);

		DownloadManager instance = new BasicDownloadManager();
		
		assertEquals(DownloadItem.Status.WAITING, item.getStatus());
		instance.downloadSingle(item);
		assertEquals(DownloadItem.Status.DONE, item.getStatus());

		File file = new File(item.getTarget());
		assertTrue(file.isFile());
		assertTrue(file.exists());
		file.delete();
		assertFalse(file.exists());
	}

	/**
	 * Test of startDownloading method, of class BasicDownloadManager.
	 */
	@Test
	public void testStartDownloading() throws InterruptedException, DownloadMaxTimeExceededException
	{
		System.out.println("startDownloading");

		DownloadManager instance = new BasicDownloadManager();
		
		try {
			instance.setDownloadDirectory(defaultDownloadDirectory);
		} catch (Exception e) {
			fail(e.toString());
		}

		fillWithTestData(instance);

		assertFalse(instance.isDownloading());
		instance.startDownloading();
		assertTrue(instance.isDownloading());

		while(instance.isDownloading()) {
			Thread.sleep(1000); 
		}
		assertFalse(instance.isDownloading());

		List<DownloadItem> list = instance.getAll();
		File file;
		for(DownloadItem item : list) {
			if(item.getTarget() == null) continue;
			file = new File(item.getTarget());
			assertTrue(file.exists());
			file.delete();
			assertFalse(file.exists());
		}

	}

	/**
	 * Test of stopDownloading method, of class BasicDownloadManager.
	 */
	@Test
	public void testStopDownloading() throws InterruptedException
	{
		System.out.println("stopDownloading");

		DownloadManager instance = new BasicDownloadManager();

		try {
			instance.setDownloadDirectory(defaultDownloadDirectory);
		} catch (Exception e) {
			fail(e.toString());
		}

		fillWithTestData(instance);

		assertFalse(instance.isDownloading());
		instance.startDownloading();
		assertTrue(instance.isDownloading());

		while(instance.isDownloading()) {
			instance.stopDownloading();
			//Thread.sleep(1000);
		}
		assertFalse(instance.isDownloading());

		List<DownloadItem> list = instance.getAll();
		File file;
		for(DownloadItem item : list) {
			if(item.getTarget() == null) continue;
			file = new File(item.getTarget());
			if(file.exists()) {
				file.delete();
				assertFalse(file.exists());
			}
		}
	}

}