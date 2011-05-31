package baccord.business.export;

/**
 * Manager for all processes around exporting results to user specified target directories
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface Export
{
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	public String getTargetDirectory();
	public void setTargetDirectory(String dir);
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	public void getResult(boolean includeBundler, boolean includeCmvs, boolean includePmvs);
	public void getPartialPmvsInputs();
}
