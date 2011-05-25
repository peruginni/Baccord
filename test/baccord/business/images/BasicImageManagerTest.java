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
	private File testFile = new File("./test/data/image800x600_f72.5tmp.jpg");
	private File sourceFile = new File("./test/data/image800x600_f72.5.jpg");
	
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
	
	@Test
	public void testGetCcdWidths()
	{
		System.out.println("getCcdWidths");
		ImageManager instance = DI.get(ImageManager.class);
		Map result = instance.getCcdWidths();
		assertNotNull(result);
	}
	
	@Test
	public void testSetCcdWidths()
	{
		System.out.println("setCcdWidths");
		ImageManager instance = DI.get(ImageManager.class);
		File file = new File(instance.getCcdWidthsStoragePath());
		file.delete();
		
		HashMap<String, Float> map = new HashMap<String, Float>();
		BasicImageManager.fillDefaultCameraCcdWidths(map);
		
		instance.setCcdWidths(map);
		assertEquals(map, instance.getCcdWidths());
		
		assertTrue(file.exists());
		file.delete();
		assertFalse(file.exists());
	}

	@Test
	public void testAddCcdWidth()
	{
		System.out.println("setCcdWidthForCamera");
		String camera = "Baccord Test Camera";
		float width = 5.4f;
		
		ImageManager instance = DI.get(ImageManager.class);
		File file = new File(instance.getCcdWidthsStoragePath());
		file.delete();
		
		instance.addCcdWidth(camera, width);
		assertEquals(width, instance.getCcdWidth(camera), 0.001);
		
		assertTrue(file.exists());
		file.delete();
		assertFalse(file.exists());
	}

	@Test
	public void testSetCcdWidthsStoragePath()
	{
		System.out.println("setCcdWidthsStoragePath");
		String cameraCcdWidthsStoragePath = "path";
		ImageManager instance = DI.get(ImageManager.class);
		instance.setCcdWidthsStoragePath(cameraCcdWidthsStoragePath);
		String result = instance.getCcdWidthsStoragePath();
		assertEquals(result, cameraCcdWidthsStoragePath);
	}

	@Test
	public void testResize()
	{
		System.out.println("resize");
		int longerSide = 640;
		int shorterSide = 480;
		ImageManager instance = DI.get(ImageManager.class);
		
		if(instance.getConvertPath() == null) {
			fail("Convert app missing");
		}
		
		if(instance.getMogrifyPath() == null) {
			fail("Mogrify app missing");
		}
		
		instance.resize(image, longerSide, shorterSide);
		instance.loadExifInformation(image);
		
		assertTrue(image.getWidth() == longerSide || image.getHeight() == longerSide);
	}

	@Test
	public void testSift()
	{
		System.out.println("hasSift");
		ImageManager instance = DI.get(ImageManager.class);
		
		if(instance.getSiftPath() == null) {
			fail("Sift app missing");
		}
		
		assertFalse(instance.hasSift(image));
		instance.performSift(image);
		assertTrue(instance.hasSift(image));
	}
	
	@Test
	public void testLoadExifInformation()
	{
		System.out.println("fillDefaultCameraCcdWidths");
		ImageManager instance = DI.get(ImageManager.class);
		
		instance.loadExifInformation(image);

		assertEquals(800, image.getWidth());
		assertEquals(600, image.getHeight());
		assertEquals(72.5, image.getFocalLength(), 0.001);
	}
	
	@Test
	public void testFillDefaultCameraCcdWidths()
	{
		System.out.println("fillDefaultCameraCcdWidths");
		HashMap<String, Float> map = new HashMap<String, Float>();
		BasicImageManager.fillDefaultCameraCcdWidths(map);
		assertFalse(map.isEmpty());
	}
}
