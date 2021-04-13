package images;

public interface ImageFilter {

	/**
	 * This method is used for filter the image to blur image.
	 * 
	 * @param filePath this is the complete path of the image.
	 */
	public int[][][] filter(int[][][] rgb, double[][] filterMatrix);

}
