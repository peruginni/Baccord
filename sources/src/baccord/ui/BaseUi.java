package baccord.ui;

import javax.swing.JPanel;

/**
 * Base class of all screens
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class BaseUi extends JPanel 
{
	/**
	 * Will put screen to initial state
	 */
	public void init() {}
	
	/**
	 * Will perform events needed right after showing screen to user
	 */
	public void start() {}
	
	/**
	 * Will perform events when this screen is going away
	 */
	public void close() {}
}
