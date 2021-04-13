package images.chunk;

/**
 * Implementation of ChunkGenerator to generate chunk of the given image.
 */
public class ChunkGeneratorImpl implements ChunkGenerator {
	private int numberOfChunks;

	/**
	 * Constructor to create ChunkGenerator Object.
	 * 
	 * @param numberOfChunks the required number of chunks width wise
	 */
	public ChunkGeneratorImpl(int numberOfChunks) {
		if (numberOfChunks <= 0) {
			throw new IllegalArgumentException(
					this.getClass().getSimpleName() + ": Number of chunks  must be at least 1.");
		}
		this.numberOfChunks = numberOfChunks;
	}

	@Override
	public int[] generateChunk(int[][][] rgb, int h, int w, int row, int col) throws IllegalArgumentException {

		int squareSize = h / numberOfChunks;
		if (squareSize <= 0) {
			return new int[] { 1, 1 };
		}

		int[] superPixel = new int[] { squareSize, squareSize };
		int widthLeft = w - (squareSize * numberOfChunks);
		int widthIndex = (numberOfChunks - 1 - widthLeft) * squareSize;
		if (col > widthIndex) {
			superPixel[1] = squareSize + 1;
		}
		int heightNoOfSquares = h / squareSize;
		int heightLeft = h - (squareSize * heightNoOfSquares);
		int heightIndex = (heightNoOfSquares - 1 - heightLeft) * squareSize;

		if (row > heightIndex) {
			superPixel[0] = squareSize + 1;
		}
		return superPixel;
	}
}
