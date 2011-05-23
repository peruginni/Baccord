package baccord.business.images;

import baccord.tools.DI;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import org.junit.Before;
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
	 * Test of getCameraCcdWidths method, of class BasicImageManager.
	 */
	@Test
	public void testGetCameraCcdWidths()
	{
		System.out.println("getCameraCcdWidths");
		ImageManager instance = DI.get(ImageManager.class);
		Map result = instance.getCameraCcdWidths();
		assertNotNull(result);
	}

	/**
	 * Test of setCameraCcdWidths method, of class BasicImageManager.
	 */
	@Test
	public void testSetCameraCcdWidths()
	{
		System.out.println("setCameraCcdWidths");
		ImageManager instance = DI.get(ImageManager.class);
		
		HashMap<String, Float> map = new HashMap<String, Float>();
		BasicImageManager.fillDefaultCameraCcdWidths(map);
		
		instance.setCameraCcdWidths(map);
		assertEquals(map, instance.getCameraCcdWidths());
	}

	/**
	 * Test of setCcdWidthForCamera method, of class BasicImageManager.
	 */
	@Test
	public void testAddCcdWidth()
	{
		System.out.println("setCcdWidthForCamera");
		String camera = "Baccord Test Camera";
		float width = 5.4f;
		ImageManager instance = DI.get(ImageManager.class);
		instance.addCcdWidth(camera, width);
		assertEquals(width, instance.getCcdWidth(camera), 0.001);
	}

	/**
	 * Test of setCameraCcdWidthsStoragePath method, of class BasicImageManager.
	 */
	@Test
	public void testSetCameraCcdWidthsStoragePath()
	{
		System.out.println("setCameraCcdWidthsStoragePath");
		String cameraCcdWidthsStoragePath = "path";
		ImageManager instance = DI.get(ImageManager.class);
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
		ImageManager instance = DI.get(ImageManager.class);
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
		ImageManager instance = DI.get(ImageManager.class);
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
		ImageManager instance = DI.get(ImageManager.class);
		
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
