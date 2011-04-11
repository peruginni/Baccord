
package baccord.tools;

import java.io.File;

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

	public static String getAbsoluteFilePath(String baseDirectory, String filename)
	{
		StringBuilder path = new StringBuilder(baseDirectory);
		path.append(File.separator);
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
		
		File file = new File(getAbsoluteFilePath(baseDirectory, unique));
		
		while(file.exists()) {
			StringBuilder generatedString = new StringBuilder()
				.append(basename)
				.append('-')
				.append(TextHelper.random(5))
				.append('.')
				.append(extension);
			unique = generatedString.toString();

			file = new File(getAbsoluteFilePath(baseDirectory, unique));
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

	public static String getBasename(String filename)
	{
		String extension = getExtension(filename);
		return filename.substring(0, filename.lastIndexOf(extension)-1);
	}

}
