
package baccord.tools;

import java.util.LinkedList;
import java.util.List;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class ObjectStorageTest
{

	/**
	 * Test of save method, of class ObjectStorage.
	 */
	@Test
	public void testSaveLoad() throws Exception
	{
		System.out.println("save string");
		String text = "testString";
		String path = "objectStorageSave.dat";

		File file = new File(path);
		assertFalse(file.exists());

		ObjectStorage.save(text, path);
		assertTrue(file.exists());

		Object result = ObjectStorage.load(path);
		assertEquals(result, text);
		assertEquals((String)result, text);

		file.delete();
	}

}