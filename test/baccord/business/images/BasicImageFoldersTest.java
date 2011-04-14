
package baccord.business.images;

import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageFoldersTest {

    public BasicImageFoldersTest() {
    }

	@BeforeClass
	public static void setUpClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownClass() throws Exception
	{
	}

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

	/**
	 * Test of saveRecentlyUsed method, of class BasicImageFolders.
	 */ @Test
	public void testSaveRecentlyUsed()
	{
		System.out.println("saveRecentlyUsed");
		String folder = "";
		BasicImageFolders instance = new BasicImageFolders();
		instance.saveRecentlyUsed(folder);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getRecentlyUsed method, of class BasicImageFolders.
	 */ @Test
	public void testGetRecentlyUsed()
	{
		System.out.println("getRecentlyUsed");
		BasicImageFolders instance = new BasicImageFolders();
		Queue expResult = null;
		Queue result = instance.getRecentlyUsed();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}