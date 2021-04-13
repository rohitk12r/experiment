package images.controller;

import java.util.HashSet;
import java.util.Set;

public class MosaicControllerImpl implements MosaicController {

	private final int seeds;

	public MosaicControllerImpl(int seeds) {
		this.seeds = seeds;
	}

	@Override
	public int[][][] mosic(int[][][] rgb, int width, int height) {

		Set<Integer> randomPixels = generateRandomPixels(width,height);
		int[][][] imageArray = rgb;
		for (int i = 0; i < imageArray.length; i++) {
			for (int j = 0; j < imageArray[i].length; j++) {
				int[] rowCol = findClosestPixel(width, height, randomPixels, i, j);
				imageArray[i][j] = imageArray[rowCol[0]][rowCol[1]];
			}
		}
		return imageArray;
	}

	/**
	 * Find the closest pixel from the randomly generated pixel to current pixel.
	 * 
	 * @param image        the image to edit
	 * @param randomPixels the randomly selected pixels
	 * @param row          the row index of current pixel
	 * @param col          the column index of current pixel
	 * @return the closest pixel's row and column index
	 */
	private int[] findClosestPixel(int w, int h, Set<Integer> randomPixels, int row, int col) {
		int[] closestPixel = new int[] { row, col };
		double closestPixelDistance = Double.MAX_VALUE;
		for (Integer pixel : randomPixels) {
			int[] pixelRowCol = pixelToRowCol(w, h, pixel);
			int rowDistance = Math.abs(pixelRowCol[0] - row);
			int colDistance = Math.abs(pixelRowCol[1] - col);
			double squareSum = Math.pow(rowDistance, 2) + Math.pow(colDistance, 2);
			double distance = Math.sqrt(squareSum);
			if (distance < closestPixelDistance) {
				closestPixel = pixelRowCol;
				closestPixelDistance = distance;
			}
		}
		return closestPixel;
	}

	/**
	 * Selects the random pixels of size numberOfSeeds.
	 * 
	 * @param image the image to edit
	 * @return the randomly selected pixels
	 */
	private Set<Integer> generateRandomPixels(int w, int h) {

		Set<Integer> pixels = new HashSet<>();
		int imagePixelCount = (w * h) - 1;
		while (pixels.size() < this.seeds) {
			double randomValue = Math.random() * imagePixelCount;
			pixels.add((int) randomValue);
		}
		return pixels;
	}

	/**
	 * Converts pixel to row and column and returns the row and column index.
	 * 
	 * @param image the image to edit
	 * @param pixel the pixel of the image
	 * @return returns the array with row and column index
	 */
	private int[] pixelToRowCol(int w, int h, int pixel) {
		int row = pixel / w;
		int col = pixel % h;
		return new int[] { row, col };
	}
}
