package baccord.ui;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Shortcuts for dialogs
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class Dialog
{
	public static void error(Component parent, String message)
	{
		JOptionPane.showMessageDialog(
			parent,
			message, 
			"Error",
			JOptionPane.ERROR_MESSAGE
		);
	}
	
	public static void notice(Component parent, String message)
	{
		JOptionPane.showMessageDialog(
			parent,
			message, 
			"Notice",
			JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	/**
	 * Will show dialog for choosing path
	 * 
	 * @param parent
	 * @param selectionMode
	 * @return 
	 */
	public static String askForPath(Component parent, int selectionMode) 
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(selectionMode);

		String path = null;
		if(fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
			File selectedFolder = fileChooser.getSelectedFile();
			try {
				path = selectedFolder.getCanonicalPath();
			} catch (IOException ex) {
				Dialog.error(parent, "Error during getting canonical path for selected folder");
			}
		}
		
		return path;
	}
}
