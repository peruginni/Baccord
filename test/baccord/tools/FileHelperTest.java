
package baccord.tools;

import java.io.IOException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class FileHelperTest
{

	public static String getSystemIndependentPath(String[] args)
	{
		StringBuilder path = new StringBuilder();

		for(int i = 0; i < args.length; i++) {
			path.append(args[i]);
			if(i < args.length - 1) {
				path.append(File.separator);
			}
		}

		return path.toString();
	}

	/**
	 * Test of getSystemIndependentPath method, of class FileHelper.
	 */
	@Test
	public void testGetSystemIndependentPath()
	{
		System.out.println("getSystemIndependentPath");
		String[] dir = {"deep","dir","ectory"};

		String expResult = dir[0] + File.separator + dir[1] + File.separator + dir[2];
		String result = FileHelper.getSystemIndependentPath(dir[0], dir[1], dir[2]);
		assertEquals(expResult, result);
	}

	
	/**
	 * Test of getAbsoluteSystemIndependentPath method, of class FileHelper.
	 */
	@Test
	public void testGetAbsoluteSystemIndependentPath()
	{
		System.out.println("getSystemIndependentPath");
		String[] dir = {"deep","dir","ectory"};

		String expResult = File.separator + dir[0] + File.separator + dir[1] + File.separator + dir[2];
		String result = FileHelper.getSystemAbsoluteIndependentPath(dir[0], dir[1], dir[2]);
		assertEquals(expResult, result);
	}


	/**
	 * Test of mergePath method, of class FileHelper.
	 */
	@Test
	public void testMergePath()
	{
		System.out.println("mergePath");
		String baseDirectory = FileHelper.getSystemAbsoluteIndependentPath("deep","test");
		String filename = "getAbsolutePath.jpg";
		String expResult = baseDirectory + File.separator + filename;
		String result = FileHelper.mergePath(baseDirectory, filename);
		assertEquals(expResult, result);
		
		baseDirectory = "";
		expResult = filename;
		result = FileHelper.mergePath(baseDirectory, filename);
		assertEquals(expResult, result);


	}

	/**
	 * Test of isUniqueFilename method, of class FileHelper.
	 */
	@Test
	public void testIsUniqueFilename() throws IOException
	{
		System.out.println("isUniqueFilename");
		String filename = "isUniqueFilename.jpg";
		File file = new File(filename);
		
		file.createNewFile();
		boolean expResult = false;
		boolean result = FileHelper.isUniqueFilename(filename);
		assertEquals(expResult, result);
		
		file.delete();
		expResult = true;
		result = FileHelper.isUniqueFilename(filename);
		assertEquals(expResult, result);
	}

	/**
	 * Test of generateUniqueFilename method, of class FileHelper.
	 */ @Test
	public void testGenerateUniqueFilename() throws IOException
	{
		System.out.println("generateUniqueFilename");
		String baseDirectory = "test";
		String currentFilename = "generateUniqueFilename.jpg";
		
		String result = FileHelper.generateUniqueFilename(baseDirectory, currentFilename);
		assertEquals(currentFilename, result);

		String absoluteFilePath = FileHelper.mergePath(baseDirectory, currentFilename);
		File file = new File(absoluteFilePath);
		file.createNewFile();
		result = FileHelper.generateUniqueFilename(baseDirectory, currentFilename);
		assertNotSame(absoluteFilePath, result);
		file.delete();
	}

	/**
	 * Test of getExtension method, of class FileHelper.
	 */ @Test
	public void testGetExtension()
	{
		System.out.println("getExtension");
		String filename = "test.jpg";
		String expResult = "jpg";
		String result = FileHelper.getExtension(filename);
		assertEquals(expResult, result);
	}

	/**
	 * Test of getFilename method, of class FileHelper.
	 */ @Test
	public void testGetFilename_String()
	{
		System.out.println("getFilename");
		String filename = "test.jpg";
		String path = "dir" + File.separator + filename;
		String expResult = filename;
		String result = FileHelper.getFilename(path);
		assertEquals(expResult, result);
	}

	/**
	 * Test of getFilename method, of class FileHelper.
	 */
	@Test
	public void testGetFilename_String_String()
	{
		System.out.println("getFilename");
		String filename = "test.jpg";
		String path = "dir" + File.separator + filename;
		String separator = File.separator;
		String expResult = filename;
		String result = FileHelper.getFilename(path, separator);
		assertEquals(expResult, result);
	}

	/**
	 * Test of getFilenameFromUrl method, of class FileHelper.
	 */
	@Test
	public void testGetFilenameFromUrl()
	{
		System.out.println("getFilenameFromUrl");
		String url = "http://ondra.macoszek.cz/img.jpg";
		String expResult = "img.jpg";
		String result = FileHelper.getFilenameFromUrl(url);
		assertEquals(expResult, result);
	}

	/**
	 * Test of getBasename method, of class FileHelper.
	 */ 
	@Test
	public void testGetBasename()
	{
		System.out.println("getBasename");
		String filename = "filename.jpg";
		String expResult = "filename";
		String result = FileHelper.getBasename(filename);
		assertEquals(expResult, result);
		
		filename = FileHelper.mergePath(
			FileHelper.getSystemAbsoluteIndependentPath("home","test"), 
			"filename.jpg"
		);
		expResult = "filename";
		result = FileHelper.getBasename(filename);
		assertEquals(expResult, result);
	}
	 
	 

	/**
	 * Test of getDirectory method, of class FileHelper.
	 */ @Test
	public void testGetDirectory()
	{
		System.out.println("getDirectory");
		String path = FileHelper.getSystemIndependentPath("home","images");
		String filePath = FileHelper.mergePath(path, "filename.jpg");
		String result = FileHelper.getDirectory(filePath);
		assertEquals(path, result);
	}

	 
	@Test
	public void testCopyFile() throws IOException
	{
		System.out.println("copyFile");
		File file1 = new File("./copyFileTest.txt");
		File file2 = new File("./copyFileTest2.txt");
		try {
			file1.delete();
			file2.delete();
			
			file1.createNewFile();
			assertTrue(file1.exists());
			FileHelper.copyFile(file1, file2);
			assertTrue(file2.exists());
			
			// TODO: test equality of content of files
			
		} catch (IOException ex) {
			Logger.getLogger(FileHelperTest.class.getName()).log(Level.SEVERE, null, ex);
			throw ex;
		} finally {
			file1.delete();
			file2.delete();
		}
	}
	 
}