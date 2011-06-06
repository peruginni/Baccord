package baccord.tools;

import baccord.business.downloader.DownloadManager;
import com.google.inject.Injector;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class DITest
{
	@Test
	public void testGet_0args()
	{
		System.out.println("get");
		Injector result = DI.get();
		assertNotNull(result);
	}

	@Test
	public void testGet_Class()
	{
		System.out.println("get");
		
		assertNotNull(DI.get(DownloadManager.class));
		assertEquals(DI.get(DownloadManager.class),DI.get(DownloadManager.class));
	}
}
