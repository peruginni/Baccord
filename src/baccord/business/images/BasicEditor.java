package baccord.business.images;

import baccord.business.downloader.DownloadItem;
import com.google.inject.Inject;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

/**
 * Basic implementation of editor
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BasicEditor implements Editor, Runnable
{
	private boolean autoStart = false;
	private boolean autoSift = false;
	private Dimension autoResizeDimension = null;
	
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
	
	@Inject
	public void setImageManager(ImageManager imageManager)
	{
		this.imageManager = imageManager;
	}
	
	public boolean getAutoStart()
	{
		return autoStart;
	}
	
	public void setAutoStart(boolean autoStart)
	{
		this.autoStart = autoStart;
	}
	
	public boolean getAutoSift() 
	{
		return autoSift;
	}
	
	public void setAutoSift(boolean autoSift)
	{
		this.autoSift = autoSift;
	}
	
	public Dimension getAutoResizeDimension()
	{
		return this.autoResizeDimension;
	}
	
	public void setAutoResizeDimension(Dimension autoResizeDimension)
	{
		this.autoResizeDimension = autoResizeDimension;
	}
	
	/**
	 * --------------------------------------------------------------------
	 *  Core logic
	 * --------------------------------------------------------------------
	 */
	
	public void add(EditorTask task)
	{
		queue.add(task);
		if(autoStart) {
			startEditing();
		}
	}
	
	public void clear()
	{
		queue.clear();
	}
	
	public Queue<EditorTask> getAllTasks()
	{
		return queue;
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
		ImageManager im = getImageManager();
		
		Dimension dimension = task.getResizeDimension();
		if(dimension != null) {
			im.resize(task.getImage(), dimension.width, dimension.height);
		}
		
		if(task.performSift()) {
			im.performSift(task.getImage());
		}
		
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

	public void update(Observable o, Object o1)
	{
		if(o1 instanceof DownloadItem) {
			DownloadItem item = (DownloadItem)o1;
			Image image = new Image(item.getTarget());
			
			EditorTask task = new EditorTask(image, getAutoSift(), getAutoResizeDimension());
			add(task);
		}
	}
	
}
