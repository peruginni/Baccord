package baccord.business.images;

import baccord.exceptions.SiftAppMissingException;
import baccord.tools.DI;
import baccord.tools.FileHelper;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import java.util.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicEditorTest
{
	private List<EditorTask> testItems;
	
	private EditorTask task;
	private Image image;
	private File testFile = new File("./test/data/image800x600_f1.6tmp.jpg");
	private File sourceFile = new File("./test/data/image800x600_f1.6.jpg");
	
	@Before
	public void setUp() throws IOException 
	{
		FileHelper.copyFile(sourceFile, testFile);
		image = new Image(testFile.getPath());
		task = new EditorTask(image, true, new Dimension(640, 480));
		
		testItems = new LinkedList<EditorTask>();
		testItems.add(task);
	}
	
	
	@After
	public void tearDown() 
	{
		testFile.delete();
	}

	private void fillWithTestData(Editor editor)
	{
		for(EditorTask item : testItems) {
			editor.add(item);
		}
	}
	
	
	/**
	 * Test of getImageManager method, of class BasicEditor.
	 */
	@Test
	public void testGetSetImageManager()
	{
		System.out.println("get/setImageManager");
		Editor instance = DI.get(Editor.class);
		assertNotNull(instance.getImageManager());
		
		ImageManager expResult = new BasicImageManager();
		instance.setImageManager(expResult);
		ImageManager result = instance.getImageManager();
		assertEquals(expResult, result);
	}

	/**
	 * Test of add method, of class BasicEditor.
	 */
	@Test
	public void testAdd()
	{
		System.out.println("add");
		
		EditorTask task = new EditorTask(new Image(), true, null);
		
		Editor instance = DI.get(Editor.class);
		instance.add(task);
		
		boolean foundTask = false;
		for(EditorTask returnedTask : instance.getAllTasks()) {
			if(returnedTask == task) {
				foundTask = true;
			}
		}
		assertTrue(foundTask);
	}

	/**
	 * Test of startEditing method, of class BasicEditor.
	 */
	@Test
	public void testStartEditing() throws InterruptedException
	{
		Editor instance = DI.get(Editor.class);
		fillWithTestData(instance);
		
		assertFalse(instance.isEditing());
		instance.startEditing();
		assertTrue(instance.isEditing());
		
		while(instance.isEditing()) { 
			Thread.sleep(1000); 
		}
		assertFalse(instance.isEditing());
		
		Queue<EditorTask> tasks = instance.getAllTasks();
		assertNotNull(tasks);
		assertTrue(tasks.isEmpty());
	}

	/**
	 * Test of stopEditing method, of class BasicEditor.
	 */
	@Test
	public void testStopEditing()
	{
		Editor instance = DI.get(Editor.class);
		fillWithTestData(instance);
		
		assertFalse(instance.isEditing());
		instance.startEditing();
		assertTrue(instance.isEditing());
		
		while(instance.isEditing()) { 
			instance.stopEditing();
		}
		assertFalse(instance.isEditing());
	}

	/**
	 * Test of editSingle method, of class BasicEditor.
	 */
	@Test
	public void testEditSingle() throws SiftAppMissingException
	{
		System.out.println("editSingle");
		ImageManager imageManager = DI.get(ImageManager.class);
		Editor instance = DI.get(Editor.class);
		instance.editSingle(task);
		Image taskImage = task.getImage();
		
		if(task.performSift()) {
			assertTrue(imageManager.equals(taskImage));
		} 
		
		if(task.getResizeDimension() != null) {
			Dimension dim = task.getResizeDimension();
			int longerSide = (dim.height > dim.width) ? dim.height : dim.width;
			
			imageManager.loadExifInformation(taskImage);
			assertTrue(image.getWidth() == longerSide || image.getHeight() == longerSide);
		}
		
	}

//	/**
//	 * Test of run method, of class BasicEditor.
//	 */
//	@Test
//	public void testRun()
//	{
//		System.out.println("run");
//		BasicEditor instance = new BasicEditor();
//		instance.run();
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}

}
