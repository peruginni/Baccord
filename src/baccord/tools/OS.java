package baccord.tools;

/**
 * OS related information
 * 
 * Inspired by: 
 * http://stackoverflow.com/questions/228477/how-do-i-programmatically-determine-operating-system-in-java
 * http://java.net/projects/swingx/sources/svn/content/trunk/swingx-core/src/main/java/org/jdesktop/swingx/util/OS.java?rev=4027
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class OS
{
	private static final boolean isLinux;
	private static final boolean isMacOsX;
	private static final boolean isWindows;
	
	static {
		String os = System.getProperty("os.name");
		
		if(os != null) {
			os = os.toLowerCase();
		}
		
		isMacOsX = "mac os x".equals(os);
		isLinux = ((os != null) && (os.indexOf("linux") != -1));
		isWindows = ((os != null) && (os.indexOf("windows") != -1));
	}
	
	public static boolean isLinux()
	{
		return isLinux;
	}
	
	public static boolean isMacOSX()
	{
		return isMacOsX;
	}
	
	public static boolean isWindows()
	{
		return isWindows;
	}
}


