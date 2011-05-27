package baccord.ui;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class Dialog
{
	public static void error(Component parent, String message)
	{
		
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
}
