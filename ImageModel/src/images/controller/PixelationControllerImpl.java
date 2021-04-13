package images.controller;

import images.chunk.ChunkGenerator;

public class PixelationControllerImpl implements PixelationController {

	private final int squares;

	public PixelationControllerImpl(int squares) {
		this.squares = squares;
	}

	@Override
	public int[][][] pixelation(int[][][] rgb, int w, int h, ChunkGenerator chunkGenerator) {
		if (w < squares) {
			return rgb;
		}

		int[][][] imageArray = rgb;
		int row = 0;

		while (row < h) {
			int col = 0;
			int superPixelHeight = -1;
			while (col < w) {
				int[] superPixel = chunkGenerator.generateChunk(rgb, h, w, row, col);
				if (superPixel != null && superPixel.length == 2) {
					try {
						applyPixelation(imageArray, rgb, h, w, row, col, superPixel[0], superPixel[1]);
					} catch (Exception e) {
						System.out.println(superPixel);
					}
					
				}
				col += superPixel[1];
				if (superPixelHeight == -1) {
					superPixelHeight = superPixel[0];
				}
			}
			row += superPixelHeight;
		}
		return imageArray;
	}

	private void applyPixelation(int[][][] imageArray, int[][][] rgbValue, int h, int w, int row, int col,
			int heightSquareSize, int widthSquareSize) {

		int rowLim = Math.min((row + heightSquareSize), h);
		int colLim = Math.min((col + widthSquareSize), w);

		int centerRow = row + (heightSquareSize / 2);
		centerRow = centerRow < rowLim ? centerRow : (rowLim - 1);
		int centerCol = col + (widthSquareSize / 2);
		centerCol = centerCol < colLim ? centerCol : (colLim - 1);
		int[] centralRGB = imageArray[centerRow][centerCol];

		for (int i = row; i < rowLim; i++) {
			for (int j = col; j < colLim; j++) {
				int[] rgb = new int[imageArray[i][j].length];
				for (int k = 0; k < rgb.length; k++) {
					rgb[k] = centralRGB[k];
				}
				imageArray[i][j] = rgb;
			}
		}
	}
}
