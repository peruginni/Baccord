
package baccord.business.downloader;

import baccord.exceptions.CannotCreateDirectoryException;
import baccord.exceptions.DownloadMaxTimeExceededException;
import baccord.exceptions.PathMustNotBeDirectoryException;
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
	private String defaultDownloadDirectory;

	public BasicDownloadManagerTest()
	{
		defaultDownloadDirectory = FileHelper.getSystemIndependentPath("deep","download","directory");

		testItems = new LinkedList<DownloadItem>();

		// jahody
		testItems.add(new DownloadItem("https://lh5.googleusercontent.com/_ydViQL_1CPo/SbxF3nt6WgI/AAAAAAAAGTI/PBCmXoeQxbg/P1050678.JPG"));

		// polstarova bitva
		testItems.add(new DownloadItem("https://lh5.googleusercontent.com/_ydViQL_1CPo/RfqDxbhNO6I/AAAAAAAAA0Q/34aYTbd4QNU/s512/06-1.jpg"));

		// fletna
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
	public void testDownloadDirectory() throws CannotCreateDirectoryException, PathMustNotBeDirectoryException
	{
		System.out.println("get/set DownloadDirectory");
		String path = defaultDownloadDirectory;
		DownloadManager instance = new BasicDownloadManager();

		instance.setDownloadDirectory(path);
		
		assertEquals(path, instance.getDownloadDirectory());
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
	public void testGetAll()
	{
		System.out.println("getAll");
		DownloadManager instance = new BasicDownloadManager();

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
	public void testDownloadSingle() throws PathMustNotBeDirectoryException, CannotCreateDirectoryException
	{
		System.out.println("downloadSingle");

		DownloadManager instance = new BasicDownloadManager();
		instance.setDownloadDirectory(defaultDownloadDirectory);
		instance.downloadSingle(testItems.getFirst());
	}

	/**
	 * Test of startDownloading method, of class BasicDownloadManager.
	 */
	@Test
	public void testStartDownloading() throws InterruptedException, DownloadMaxTimeExceededException
	{
		System.out.println("startDownloading");

		DownloadManager instance = new BasicDownloadManager();

		fillWithTestData(instance);
		fail();

		assertFalse(instance.isDownloading());

		//instance.startDownloading();

		assertTrue(instance.isDownloading());

		int waited = 0;
		int maxSecondsToWait = 60;
		while(instance.isDownloading()) {
			if(waited > maxSecondsToWait) {
				throw new DownloadMaxTimeExceededException();
			}
			Thread.sleep(5000); // sleep for five seconds
			waited += 5;
		}

		assertFalse(instance.isDownloading());
	}

	/**
	 * Test of stopDownloading method, of class BasicDownloadManager.
	 */
	@Test
	public void testStopDownloading()
	{
		System.out.println("stopDownloading");
		BasicDownloadManager instance = new BasicDownloadManager();
		instance.stopDownloading();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of run method, of class BasicDownloadManager.
	 */
	@Test
	public void testRun()
	{
		System.out.println("run");
		BasicDownloadManager instance = new BasicDownloadManager();
		instance.run();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}