
package baccord.business.sfm;

import baccord.business.images.Image;
import java.util.List;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface StructureFromMotion
{
	public void addImages(List<Image> images);
	public void matchKeypoints(List<Image> images);

	public void start();
	public void stop();
}
