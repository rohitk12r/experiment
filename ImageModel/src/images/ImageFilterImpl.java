package images;

/**
 * This is the interface which have added all abstract method for image
 * processing.
 * 
 */
public class ImageFilterImpl extends AbstractImageModel implements ImageFilter {

	@Override
	public int[][][] filter(int[][][] rgb, double[][] filterMatrix) {
		for (int i = 0; i < rgb.length; i++) {
			for (int j = 0; j < rgb[i].length; j++) {
				for (int k = 0; k < rgb[i][j].length; k++) {
					rgb[i][j][k] = applyFilter(rgb, i, j, k, filterMatrix);
					rgb[i][j][k] = clamp(rgb[i][j][k]);
				}
			}
		}
		return rgb;
	}
}
