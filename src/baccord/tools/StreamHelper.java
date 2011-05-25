package baccord.tools;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Simple utility for working with streams 
 * 
 * Especially for debugging
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class StreamHelper
{
	public static void print(InputStream is) throws IOException
	{
		BufferedInputStream bis = new BufferedInputStream(is);

		int n;
		while((n = bis.read()) != -1) {
			System.out.print((char)n);
		}

		bis.close();
	}
}
