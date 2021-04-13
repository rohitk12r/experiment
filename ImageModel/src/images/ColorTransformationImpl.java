package images;

public class ColorTransformationImpl extends AbstractImageModel implements ColorTransformation {

	@Override
	public int[][][] tranform(int[][][] rgb, double[][] colorTransformMatrix) {
		int height = rgb.length;
		int width = rgb[0].length;
		int[][][] transformedImage = new int[height][width][3];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int[] imageColors = rgb[i][j];
				int[] transformedImageColors = transformColors(imageColors, colorTransformMatrix);
				transformedImage[i][j] = transformedImageColors;
			}
		}
		return transformedImage;
	}

	@Override
	public int[][][] reduceColorDensity(int[][][] rgb, double channelRound) {
		return floydSteinbergDithering(rgb, channelRound);
	}
}
