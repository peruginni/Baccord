package baccord.business.images;

import baccord.exceptions.SiftAppMissingException;
import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageManagerTest
{

	/**
	 * Test of setSiftPath method, of class BasicImageManager.
	 */
	@Test
	public void testSetSiftPath() throws Exception
	{
		System.out.println("setSiftPath");
		BasicImageManager instance = new BasicImageManager();
		
		boolean siftMissing = false;
		try {
			instance.setSiftPath("");
		} catch(SiftAppMissingException ex) { siftMissing = true; }
		assertTrue(siftMissing);
	}

	/**
	 * Test of getCameraCcdWidths method, of class BasicImageManager.
	 */
	@Test
	public void testGetCameraCcdWidths()
	{
		System.out.println("getCameraCcdWidths");
		BasicImageManager instance = new BasicImageManager();
		HashMap result = instance.getCameraCcdWidths();
		assertNotNull(result);
	}

	/**
	 * Test of setCameraCcdWidths method, of class BasicImageManager.
	 */
	@Test
	public void testSetCameraCcdWidths()
	{
		System.out.println("setCameraCcdWidths");
		BasicImageManager instance = new BasicImageManager();
		
		HashMap<String, Float> map = new HashMap<String, Float>();
		instance.fillDefaultCameraCcdWidths(map);
		
		instance.setCameraCcdWidths(map);
		assertEquals(map, instance.getCameraCcdWidths());
	}

	/**
	 * Test of setCcdWidthForCamera method, of class BasicImageManager.
	 */
	@Test
	public void testSetCcdWidthForCamera()
	{
		System.out.println("setCcdWidthForCamera");
		String camera = "Baccord Test Camera";
		float width = 5.4f;
		BasicImageManager instance = new BasicImageManager();
		instance.setCcdWidthForCamera(camera, width);
		assertEquals(width, instance.getCcdWidthForCamera(camera), 0.001);
	}

	/**
	 * Test of setCameraCcdWidthsStoragePath method, of class BasicImageManager.
	 */
	@Test
	public void testSetCameraCcdWidthsStoragePath()
	{
		System.out.println("setCameraCcdWidthsStoragePath");
		String cameraCcdWidthsStoragePath = "path";
		BasicImageManager instance = new BasicImageManager();
		instance.setCameraCcdWidthsStoragePath(cameraCcdWidthsStoragePath);
		String result = instance.getCameraCcdWidthsStoragePath();
		assertEquals(result, cameraCcdWidthsStoragePath);
	}

	/**
	 * Test of resize method, of class BasicImageManager.
	 */
	@Test
	public void testResize()
	{
		System.out.println("resize");
		Image image = null;
		int width = 0;
		int height = 0;
		BasicImageManager instance = new BasicImageManager();
		instance.resize(image, width, height);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of hasSift method, of class BasicImageManager.
	 */
	@Test
	public void testHasSift()
	{
		System.out.println("hasSift");
		fail("todo create image");
		Image image = null; // crate image
		BasicImageManager instance = new BasicImageManager();
		assertFalse(instance.hasSift(image));
		instance.performSift(image);
		assertTrue(instance.hasSift(image));
	}

	/**
	 * Test of performSift method, of class BasicImageManager.
	 */
	@Test
	public void testPerformSift()
	{
		System.out.println("performSift");
		Image image = null;
		BasicImageManager instance = new BasicImageManager();
		instance.performSift(image);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getFocalLegth method, of class BasicImageManager.
	 */
	@Test
	public void testGetFocalLegth()
	{
		System.out.println("getFocalLegth");
		Image image = null;
		BasicImageManager instance = new BasicImageManager();
		float expResult = 0.0F;
		float result = instance.getFocalLegth(image);
		assertEquals(expResult, result, 0.0);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of fillDefaultCameraCcdWidths method, of class BasicImageManager.
	 */
	@Test
	public void testFillDefaultCameraCcdWidths()
	{
		System.out.println("fillDefaultCameraCcdWidths");
		HashMap<String, Float> map = null;
		BasicImageManager.fillDefaultCameraCcdWidths(map);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
