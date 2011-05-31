package baccord.business.images;

import baccord.exceptions.SiftAppMissingException;
import baccord.tools.Observable;
import baccord.tools.Observer;
import java.awt.Dimension;
import java.util.Queue;

/**
 * Ensure effective editing of images. Will queue images to edit and 
 * processing will be done within separate thread.
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface Editor extends Observer, Observable
{
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public ImageManager getImageManager();
	public void setImageManager(ImageManager imageManager);
	
	/**
	 * If true then later in processing will automatically start 
	 * right moment after adding new image to queue
	 * @return 
	 */
	public boolean getAutoStart();
	public void setAutoStart(boolean autoStart);
	
	/**
	 * If true then later in processing will assign to images flag notifying
	 * to perform sift.
	 * @return 
	 */
	public boolean getAutoSift();
	public void setAutoSift(boolean autoSift);
	
	/** 
	 * If not null then later in processing will automatically resize images
	 * @return 
	 */
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
	
	/**
	 * Clear current queue of images to edit;
	 */
	public void clear();
	
	/**
	 * Return all tasks in queue
	 * @return 
	 */
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
