package baccord.business.settings;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class Settings
{
	private String workingDirectory;
	private String bundlerPath;
	private String keypointMatcherPath;
	private String keypointDetectorPath;
	private String pmvsPath;
	private String cmvsPath;
	
	public String getWorkingDirectory()
	{
		return this.workingDirectory;
	}
	
	public void setWorkingDirectory(String workingDirectory)
	{
		this.workingDirectory = workingDirectory;
	}
	
	public String getBundlerPath()
	{
		return this.bundlerPath;
	}
	
	public void setBundlerPath(String bundlerPath)
	{
		this.bundlerPath = bundlerPath;
	}
	
	public String getKeypointMatcherPath()
	{
		return this.keypointMatcherPath;
	}
	
	public void setKeypointMatcherPath(String keypointMatcherPath)
	{
		this.keypointMatcherPath = keypointMatcherPath;
	}
	
	public String getKeypointDetectorPath()
	{
		return this.keypointDetectorPath;
	}
	
	public void setKeypointDetectorPath(String keypointDetectorPath)
	{
		this.keypointDetectorPath = keypointDetectorPath;
	}
	
	public String getPmvsPath()
	{
		return this.pmvsPath;
	}
	
	public void setPmvsPath(String pmvsPath)
	{
		this.pmvsPath = pmvsPath;
	}
	
	public String getCmvsPath()
	{
		return this.cmvsPath;
	}
	
	public void setCmvsPath(String cmvsPath)
	{
		this.cmvsPath = cmvsPath;
	}
}
