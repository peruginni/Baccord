package baccord.business.images;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import baccord.exceptions.SiftAppMissingException;
import baccord.tools.FileHelper;
import java.util.HashMap;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicImageManagerTest
{
	private Image image;
	private File testFile = new File("./test/data/image800x600_f1.6.jpg");
	private File sourceFile = new File("./test/data/image800x600_f1.6.jpg");
	
	@Before
	public void setUp() throws IOException 
	{
		FileHelper.copyFile(sourceFile, testFile);
		image = new Image(testFile.getPath());
	}
	
	
	@After
	public void tearDown() 
	{
		testFile.delete();
	}
	
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
		int longerSide = 640;
		int shorterSide = 480;
		BasicImageManager instance = new BasicImageManager();
		instance.resize(image, longerSide, shorterSide);
		
		instance.loadExifInformation(image);
		
		assertTrue(image.getWidth() == longerSide || image.getHeight() == longerSide);
	}

	/**
	 * Test of hasSift and parformSift method, of class BasicImageManager.
	 */
	@Test
	public void testSift()
	{
		System.out.println("hasSift");
		BasicImageManager instance = new BasicImageManager();
		assertFalse(instance.hasSift(image));
		instance.performSift(image);
		assertTrue(instance.hasSift(image));
	}
	
	
	/**
	 * Test of loadExifInformation method, of class BasicImageManager.
	 */
	@Test
	public void testLoadExifInformation()
	{
		System.out.println("fillDefaultCameraCcdWidths");
		BasicImageManager instance = new BasicImageManager();
		
		instance.loadExifInformation(image);

		assertEquals(image.getWidth(), 800);
		assertEquals(image.getHeight(), 600);
		assertEquals(image.getFocalLength(), 1.6, 0.001);
	}

	/**
	 * Test of fillDefaultCameraCcdWidths method, of class BasicImageManager.
	 */
	@Test
	public void testFillDefaultCameraCcdWidths()
	{
		System.out.println("fillDefaultCameraCcdWidths");
		HashMap<String, Float> map = new HashMap<String, Float>();
		BasicImageManager.fillDefaultCameraCcdWidths(map);
		assertFalse(map.isEmpty());
	}
}
