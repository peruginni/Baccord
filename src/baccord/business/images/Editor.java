package baccord.business.images;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface Editor
{
	/**
	 * Will add task to editor queue
	 * @param task 
	 */
	public void add(EditorTask task);
	
	/**
	 * Determine if is currently editing
	 * 
	 * @return 
	 */
	public boolean isEditing();
	
	/**
	 * Start editing. Will start new thread if there is no running one.
	 */
	public void startEditing();
	
	/**
	 * Stop editing
	 */
	public void stopEditing();
	
}
