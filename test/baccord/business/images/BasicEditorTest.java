package baccord.business.images;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicEditorTest
{
	
	/**
	 * Test of getImageManager method, of class BasicEditor.
	 */
	@Test
	public void testGetImageManager()
	{
		System.out.println("getImageManager");
		BasicEditor instance = new BasicEditor();
		ImageManager expResult = null;
		ImageManager result = instance.getImageManager();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setImageManager method, of class BasicEditor.
	 */
	@Test
	public void testSetImageManager()
	{
		System.out.println("setImageManager");
		ImageManager imageManager = null;
		BasicEditor instance = new BasicEditor();
		instance.setImageManager(imageManager);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of add method, of class BasicEditor.
	 */
	@Test
	public void testAdd()
	{
		System.out.println("add");
		EditorTask task = null;
		BasicEditor instance = new BasicEditor();
		instance.add(task);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of isEditing method, of class BasicEditor.
	 */
	@Test
	public void testIsEditing()
	{
		System.out.println("isEditing");
		BasicEditor instance = new BasicEditor();
		boolean expResult = false;
		boolean result = instance.isEditing();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of startEditing method, of class BasicEditor.
	 */
	@Test
	public void testStartEditing()
	{
		System.out.println("startEditing");
		BasicEditor instance = new BasicEditor();
		instance.startEditing();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of stopEditing method, of class BasicEditor.
	 */
	@Test
	public void testStopEditing()
	{
		System.out.println("stopEditing");
		BasicEditor instance = new BasicEditor();
		instance.stopEditing();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of editSingle method, of class BasicEditor.
	 */
	@Test
	public void testEditSingle()
	{
		System.out.println("editSingle");
		EditorTask task = null;
		BasicEditor instance = new BasicEditor();
		instance.editSingle(task);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of run method, of class BasicEditor.
	 */
	@Test
	public void testRun()
	{
		System.out.println("run");
		BasicEditor instance = new BasicEditor();
		instance.run();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
