
package baccord.tools;

import java.util.Random;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class TextHelper
{
	public static String random(int lenght)
	{
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder output = new StringBuilder();

		Random random = new Random();

		while(output.length() != lenght) {
			output.append(
				characters.charAt(random.nextInt(characters.length()))
			);
		}
		
		return output.toString();
	}
}
