/*
 * BaccordApp.java
 */

package baccord;

import baccord.tools.DI;
import baccord.ui.BaseUi;
import baccord.ui.ImagesDashboard;
import baccord.ui.MainWindow;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class BaccordApp extends SingleFrameApplication 
{
	private MainWindow mainWindow;
	private BaseUi currentScreen;
	
	/**
	 * Ensure change of screen in applications user interface epicenter
	 * to given screen. Will automatically load class instance with DI.
	 * 
	 * @param type class name of screen to show
	 * @return 
	 */
	public BaseUi changeScreen(Class type)
	{
		return changeScreen((BaseUi) DI.get(type));
	}
	
	/**
	 * Change of current screen in epicenter to given BaseUi ancestor.
	 * Will ensure calling methods init, start, close in proper time.
	 * (close before assiging new screen, init to initialize new screen
	 * and start after assigning screen to epicenter.)
	 * @param component
	 * @return 
	 */
	public BaseUi changeScreen(BaseUi component)
	{
		if(currentScreen != null) {
			currentScreen.close();
		}
		currentScreen = component;
		component.init();
		mainWindow.changeEpicenterTo(component);
		component.start();
		return component;
	}
	
	/**
	 * Will return current screen shown in epicenter
	 * @return 
	 */
	public BaseUi getCurrentScreen()
	{
		return currentScreen;
	}
	
	/**
	 * At startup create and show the main frame of the application.
	 */
	@Override protected void startup() 
	{	    
		mainWindow = new MainWindow(this);
		changeScreen(ImagesDashboard.class);
		show(mainWindow);
		mainWindow.getFrame().pack();
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
