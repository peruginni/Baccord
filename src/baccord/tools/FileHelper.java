
package baccord.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class FileHelper
{
	public static String getSystemIndependentPath(String ... dirs)
	{
		StringBuilder path = new StringBuilder();

		for(int i = 0; i < dirs.length; i++) {
			path.append(dirs[i]);
			if(i < dirs.length - 1) {
				path.append(File.separator);
			}
		}

		return path.toString();
	}
	
	public static String getSystemAbsoluteIndependentPath(String ... dirs)
	{
		return File.separator + getSystemIndependentPath(dirs);
	}

	public static String mergePath(String baseDirectory, String filename)
	{
		StringBuilder path = new StringBuilder(baseDirectory);
		if(!baseDirectory.isEmpty()) {
			path.append(File.separator);
		}
		path.append(filename);
		return path.toString();
	}

	public static boolean isUniqueFilename(String filename)
	{
		File file = new File(filename);
		return !file.exists();
	}

	public static String generateUniqueFilename(String baseDirectory, String currentFilename)
	{
		String unique = currentFilename;
		String basename = getBasename(currentFilename);
		String extension = getExtension(currentFilename);
		
		File file = new File(mergePath(baseDirectory, unique));
		
		while(file.exists()) {
			StringBuilder generatedString = new StringBuilder()
				.append(basename)
				.append('-')
				.append(TextHelper.random(5))
				.append('.')
				.append(extension);
			unique = generatedString.toString();

			file = new File(mergePath(baseDirectory, unique));
		}

		return unique;
	}
	
	public static String getExtension(String filename)
	{
		int extensionStart = filename.lastIndexOf('.');

		if(extensionStart != -1) {
			extensionStart += 1; // move cursor after dot
			return filename.substring(extensionStart);
		}
		
		return "";
	}
	
	public static String getFilename(String path)
	{
		return getFilename(path, File.separator);
	}

	public static String getFilename(String path, String separator)
	{
		int filenameStart = path.lastIndexOf(separator);

		if(filenameStart != -1) {
			filenameStart += 1; // move cursor after dot
			return path.substring(filenameStart);
		}

		return path;
	}

	public static String getFilenameFromUrl(String url)
	{
		return getFilename(url, "/");
	}

	public static String getBasename(String path)
	{
		int basenameStart = path.lastIndexOf(File.separator);
		if(basenameStart != -1) {
			path = path.substring(basenameStart+1);
		}
		String extension = getExtension(path);
		return path.substring(0, path.lastIndexOf(extension)-1);
	}
	
	public static String getDirectory(String pathWithFilename)
	{
		int directoryEnd = pathWithFilename.lastIndexOf(File.separator);

		if(directoryEnd != -1) {
			return pathWithFilename.substring(0, directoryEnd);
		}

		return "";
	}
	
	/**
	 * Inspired by http://stackoverflow.com/questions/106770/standard-concise-way-to-copy-a-file-in-java/115086#115086
	 * 
	 * @param source
	 * @param target
	 * @throws IOException 
	 */
	public static void copyFile(File source, File target) throws IOException
	{
		if(!target.exists()) {
			target.createNewFile();
		}
		
		FileChannel sourceChannel = null;
		FileChannel targetChannel = null;
		
		try {
			sourceChannel = new FileInputStream(source).getChannel();
			targetChannel = new FileOutputStream(target).getChannel();
			
			targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		} finally {
			if(sourceChannel != null) {
				sourceChannel.close();
			}
			if(targetChannel != null) {
				targetChannel.close();
			}
		}
		
	}
	
	public static boolean exists(String path)
	{
		return (new File(path)).exists();
	}
	
	
	/**
	 * Inspired by http://www.java2s.com/Code/Java/File-Input-Output/Comparebinaryfiles.htm
	 * 
	 * @param first
	 * @param second
	 * @return 
	 */
	public static boolean isBinaryEqual(File first, File second)
	{
		boolean result = true;
		
		InputStream firstIs = null;
		InputStream secondIs = null;
		BufferedInputStream firstBis = null;
		BufferedInputStream secondBis = null;
		
		int bufferSize = 100;
		
		try {
			firstIs = new FileInputStream(first);
			secondIs = new FileInputStream(second);
			firstBis = new BufferedInputStream(firstIs, bufferSize);
			secondBis = new BufferedInputStream(secondIs, bufferSize);
			
			int firstByte;
			int secondByte;
			
			while(true) {
				firstByte = firstBis.read();
				secondByte = secondBis.read();
				
				if(firstByte != secondByte) {
					result = false;
					break;
				}
				
				if(firstByte < 0 && secondByte < 0) {
					break;
				}
			}	
			
		} catch (IOException ex) {
			Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if(firstBis != null) firstBis.close();
			} catch (IOException ex) {
				Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				if(secondBis != null) try {
					secondBis.close();
				} catch (IOException ex) {
					Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		
		return result;
	}

	
}
