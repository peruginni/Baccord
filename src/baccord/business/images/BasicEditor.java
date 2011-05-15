package baccord.business.images;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Basic implementation of editor
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicEditor implements Editor, Runnable
{
	private boolean isEditing;
	private Queue<EditorTask> queue;
	private Thread editingThread;
	private ImageManager imageManager;
	
	public BasicEditor()
	{
		queue = new LinkedList<EditorTask>();
	}
	
	
	/**
	 * --------------------------------------------------------------------
	 *  Properties
	 * --------------------------------------------------------------------
	 */
	
	public ImageManager getImageManager()
	{
		return this.imageManager;
	}
	
	public void setImageManager(ImageManager imageManager)
	{
		this.imageManager = imageManager;
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	public void add(EditorTask task)
	{
		queue.add(task);
		startEditing();
	}

	public boolean isEditing()
	{
		return isEditing;
	}

	public void startEditing()
	{
		if(!isEditing) {
			isEditing = true;
			editingThread = new Thread(this);
			editingThread.start();
		}
	}

	public void stopEditing()
	{
		if(isEditing) {
			isEditing = false;
			editingThread.interrupt();
		}
	}
	
	public void editSingle(EditorTask task)
	{
		
	}

	public void run()
	{
		while(isEditing && !queue.isEmpty()) {
			EditorTask task = queue.peek();
			
			editSingle(task);
			
			queue.poll();
		}
		
		isEditing = false;
	}
	
}
