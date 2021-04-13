package images;

/**
 * This is the interface which have added all abstract method for image
 * processing.
 * 
 */
public interface ImageModel {
	/**
	 * This it the folder where all out image stored.
	 */
	String output = "C:/Users/RohitSharma/Desktop/test/Shashank/";

	double topCoefficient = 7.0 / 16.0;
	double rightCoefficient = 5.0 / 16.0;
	double topRightCoefficient = 3.0 / 16.0;
	double bottomRightCoefficient = 1.0 / 16.0;

	/**
	 * This method used to apply filter on images.
	 * 
	 */
	public int applyFilter(int[][][] input, int height, int weight, int depth, double[][] filter);

	/**
	 * It is algorithm for applying dithering on image
	 */
	public int[][][] floydSteinbergDithering(int[][][] image, double channelRound);

	/**
	 * It is used for transformation of image color
	 * 
	 */
	public int[] transformColors(int[] colors, double[][] transformationMatrix);

}
