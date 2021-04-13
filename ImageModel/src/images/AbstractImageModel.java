package images;

/**
 * This is abstract class which has design to abstract method which are common
 * for all implementation.
 * 
 */
public abstract class AbstractImageModel implements ImageModel {
	/**
	 * This method used to apply filter on images.
	 */
	@Override
	public int applyFilter(int[][][] input, int height, int weight, int depth, double[][] filter) {
		double channel = 0;
		double matrixHeight = filter.length - 1.0;
		int updateHeight = (int) (height - (matrixHeight / 2.0));
		for (int l = 0; l < filter.length; l++) {
			double kernelWidth = filter[l].length - 1.0;
			int updatedWidth = (int) (weight - (kernelWidth / 2.0));
			for (int m = 0; m < filter[l].length; m++) {
				if ((updateHeight >= 0 && updatedWidth >= 0)
						&& (updateHeight < input.length && updatedWidth < input[height].length)) {
					channel = channel + (filter[l][m] * input[updateHeight][updatedWidth][depth]);
				}
				updatedWidth = updatedWidth + 1;
			}
			updateHeight = updateHeight + 1;
		}
		return (int) Math.round(channel);
	}

	@Override
	public int[] transformColors(int[] colors, double[][] transformationMatrix) throws IllegalArgumentException {
		int[] transposedColors = new int[colors.length];
		for (int i = 0; i < transformationMatrix.length; i++) {
			double colorTransposedSum = 0;
			for (int j = 0; j < transformationMatrix[i].length; j++) {
				colorTransposedSum += transformationMatrix[i][j] * colors[j];
			}
			int transposedColor = clamp((int) colorTransposedSum);
			transposedColors[i] = transposedColor;
		}
		return transposedColors;
	}

	@Override
	public int[][][] floydSteinbergDithering(int[][][] image, double channelRound) {
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				int[] oldPixel = image[i][j];
				int[] newPixel = DitherUtils.findClosestPaletteColor(oldPixel, channelRound);
				image[i][j] = clamp(newPixel);
				int[] quantError = DitherUtils.findQuantError(oldPixel, newPixel);
				if (i + 1 < image.length) {
					image[i + 1][j] = clamp(DitherUtils.quantErrorCorrect(image[i + 1][j], topCoefficient, quantError));
				}
				if (i - 1 >= 0 && (j + 1) < image[i - 1].length) {
					image[i - 1][j + 1] = clamp(
							DitherUtils.quantErrorCorrect(image[i - 1][j + 1], topRightCoefficient, quantError));
				}
				if ((j + 1) < image[i].length) {
					image[i][j + 1] = clamp(
							DitherUtils.quantErrorCorrect(image[i][j + 1], rightCoefficient, quantError));
				}
				if (i + 1 < image.length && (j + 1) < image[i + 1].length) {
					image[i + 1][j + 1] = clamp(
							DitherUtils.quantErrorCorrect(image[i + 1][j + 1], bottomRightCoefficient, quantError));
				}
			}
		}
		return image;
	}

	public static int clamp(int c) {
		return Math.max(0, Math.min(255, c));
	}

	public int[] clamp(int[] color) throws IllegalArgumentException {
		if (color == null || color.length == 0) {
			throw new IllegalArgumentException("Colors cannot be null or empty.");
		}
		int[] clampedColor = new int[color.length];
		for (int i = 0; i < color.length; i++) {
			clampedColor[i] = clamp(color[i]);
		}
		return clampedColor;
	}
}
