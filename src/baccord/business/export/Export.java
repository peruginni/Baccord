package baccord.business.export;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface Export
{
	public String getTargetDirectory();
	public void setTargetDirectory(String dir);
	
	public void getResult(boolean includeBundler, boolean includeCmvs, boolean includePmvs);
	public void getPartialPmvsInputs();
}
