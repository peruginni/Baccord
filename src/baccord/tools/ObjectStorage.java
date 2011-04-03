
package baccord.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility for storing objects to files
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class ObjectStorage
{
	private static final Logger logger = Logger.getLogger(ObjectStorage.class.getPackage().getName());

	public static void save(Object object, String path) throws FileNotFoundException
	{
		try {
			FileOutputStream fout = new FileOutputStream(path);
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);

			try {
				oout.writeObject(object);
			} finally {
				oout.close();
			}
		} catch (FileNotFoundException ex) {
			logger.log(Level.SEVERE, "File not found", ex);
			throw ex;
		} catch (IOException ex) {
			logger.log(Level.SEVERE, "Cannot perform input", ex);
		}
	}

	public static Object load(String path) throws FileNotFoundException
	{
		Object object = null;

		try {
			FileInputStream fin = new FileInputStream(path);
			BufferedInputStream bin = new BufferedInputStream(fin);
			ObjectInputStream oin = new ObjectInputStream(bin);

			try {
				object = oin.readObject();
			} finally {
				oin.close();
			}
		} catch (FileNotFoundException ex) {
			logger.log(Level.SEVERE, "File not found", ex);
			throw ex;
		} catch (ClassNotFoundException ex) {
			logger.log(Level.SEVERE, "Serialized class not found", ex);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, "Cannot perform input", ex);
		}

		return object;
	}
}
