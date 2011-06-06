
package baccord.tools;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class TextHelperTest
{

	/**
	 * Test of random method, of class TextHelper.
	 */
	@Test
	public void testRandom()
	{
		System.out.println("random");

		int lenght = 0;
		String result1 = TextHelper.random(lenght);
		String result2 = TextHelper.random(lenght);
		assertNotSame(result1, result2);
		assertTrue(result1.length() == lenght);
		assertTrue(result2.length() == lenght);

		lenght = 8;
		result1 = TextHelper.random(lenght);
		result2 = TextHelper.random(lenght);
		assertNotSame(result1, result2);
		assertTrue(result1.length() == lenght);
		assertTrue(result2.length() == lenght);
	}

}