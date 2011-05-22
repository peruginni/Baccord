package baccord.tools;

import baccord.business.downloader.BasicDownloadManager;
import baccord.business.downloader.DownloadManager;
import baccord.business.images.BasicEditor;
import baccord.business.images.BasicImageFolders;
import baccord.business.images.BasicImageManager;
import baccord.business.images.Editor;
import baccord.business.images.ImageFolders;
import baccord.business.images.ImageManager;
import baccord.business.settings.Settings;
import baccord.business.settings.Settings;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * Dependency injection wrapper
 * 
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public class DI extends AbstractModule
{
	private static Injector injector;

	@Override
	protected void configure()
	{
		// downloader
		bind(DownloadManager.class).to(BasicDownloadManager.class).in(Singleton.class);
		
		// images
		bind(Editor.class).to(BasicEditor.class).in(Singleton.class);
		bind(ImageFolders.class).to(BasicImageFolders.class).in(Singleton.class);
		bind(ImageManager.class).to(BasicImageManager.class).in(Singleton.class);
		
		// settings
		bind(Settings.class).to(Settings.class).in(Singleton.class);
	}
	
	
	public static Injector get()
	{
		if(injector == null) {
			injector = Guice.createInjector(new DI());
		}
		
		return injector;
	}
	
	public static <T> T get(Class<T> t) 
	{
		return get().getInstance(t);
	}
}