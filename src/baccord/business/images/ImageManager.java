
package baccord.business.images;

/**
 *
 * @author Ondřej Macoszek <ondra@macoszek.cz>
 */
public interface ImageManager
{
	/**
	 * Resize given image and save it to its original location.
	 * 
	 * @param image image to resize
	 * @param width target width
	 * @param height target height
	 */
	public void resize(Image image, int width, int height);
	
	/**
	 * Detect if SIFT was performed and keypoint file was created.
	 * 
	 * @param image
	 * @return true if SIFT was performed and keypoint file created, otherwise false
	 */
	public boolean hasSift(Image image);
	
	/**
	 * Using SIFT detector will detect keypoints and store them to file 
	 * in same directory as is the image located.
	 * 
	 * @param image image to detect
	 */
	public void performSift(Image image);
	
	/**
	 * Extract focal length from image. 
	 * 
	 * @param image 
	 */
	public int getFocalLegth(Image image);
}	
