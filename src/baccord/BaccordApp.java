/*
 * BaccordApp.java
 */

package baccord;

import baccord.ui.MainWindow;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class BaccordApp extends SingleFrameApplication 
{
	/**
	 * At startup create and show the main frame of the application.
	 */
	@Override protected void startup() 
	{	    
		MainWindow mainWindow = new MainWindow(this);
		mainWindow.init();
		show(mainWindow);
	}

	/**
	 * This method is to initialize the specified window by injecting resources.
	 * Windows shown in our application come fully initialized from the GUI
	 * builder, so this additional configuration is not needed.
	 */
	@Override protected void configureWindow(java.awt.Window root) 
	{
		// empty
	}

	/**
	 * A convenient static getter for the application instance.
	 * @return the instance of BaccordApp
	 */
	public static BaccordApp getApplication() 
	{
		return Application.getInstance(BaccordApp.class);
	}

	/**
	 * Main method launching the application.
	 */
	public static void main(String[] args)
	{
		launch(BaccordApp.class, args);
	}
}
