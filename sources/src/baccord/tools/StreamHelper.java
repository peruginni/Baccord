package baccord.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	
	public static String convertToString(InputStream in) throws IOException 
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader bin = new BufferedReader(new InputStreamReader(in));
		String lineIn = null;
		while((lineIn = bin.readLine()) != null) {
			if(lineIn != null) sb.append(lineIn);
		}
		bin.close();
	
		return sb.toString();
	}
			
			
}
