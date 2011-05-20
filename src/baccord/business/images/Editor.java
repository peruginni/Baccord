package baccord.business.images;

import baccord.exceptions.SiftAppMissingException;
import java.awt.Dimension;
import java.util.Observer;
import java.util.Queue;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface Editor extends Observer
{
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public ImageManager getImageManager();
	public void setImageManager(ImageManager imageManager);
	public boolean getAutoSift();
	public void setAutoSift(boolean autoSift);
	public Dimension getAutoResizeDimension();
	public void setAutoResizeDimension(Dimension autoResizeDimension);
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	/**
	 * Will add task to editor queue and will run editing if not running yet
	 * @param task 
	 */
	public void add(EditorTask task);
	public Queue<EditorTask> getAllTasks();
	
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
	
	/**
	 * Edit single editor task
	 */
	public void editSingle(EditorTask task) throws SiftAppMissingException;
	
}
