package images;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Image utility class that has methods to read an image from file and write to
 * a file.
 */
public class ImageUtilities {
	/** An enumeration of the different channels in our images. */
	public enum Channel {
		RED, GREEN, BLUE
	}

	/**
	 * Read an image from a file and return it as a 2D array of RGB colors.
	 * 
	 * @param filename The name of the file containing the image to read
	 * @return a 2D array of RGB colors
	 * @throws IOException if there is an error reading the file
	 */
	public static int[][][] readImage(String filename) throws IOException {
		BufferedImage input;

		input = ImageIO.read(new FileInputStream(filename));

		int[][][] result = new int[input.getHeight()][input.getWidth()][Channel.values().length];

		for (int i = 0; i < input.getHeight(); i++) {
			for (int j = 0; j < input.getWidth(); j++) {
				int color = input.getRGB(j, i);
				Color c = new Color(color);
				result[i][j][Channel.RED.ordinal()] = c.getRed();
				result[i][j][Channel.GREEN.ordinal()] = c.getGreen();
				result[i][j][Channel.BLUE.ordinal()] = c.getBlue();
			}
		}
		return result;
	}

	/**
	 * The width of the image in a file.
	 * 
	 * @param filename The name of the file containing an image.
	 * @return The width of the image contained in the file.
	 * @throws IOException if there is an error reading the file
	 */
	public static int getWidth(String filename) throws IOException {
		BufferedImage input;

		input = ImageIO.read(new FileInputStream(filename));

		return input.getWidth();
	}

	/**
	 * The height of the image in a file.
	 * 
	 * @param filename The name of the file containing an image.
	 * @return The height of the image contained in the file.
	 * @throws IOException if there is an error reading the file
	 */
	public static int getHeight(String filename) throws IOException {
		BufferedImage input;

		input = ImageIO.read(new FileInputStream(filename));

		return input.getHeight();
	}

	/**
	 * Write the content of a 2D array of RGB colors to a file.
	 * 
	 * @param rgb      The 2D array of RGB values that will be written to the file
	 * @param width    The width of the image to be written
	 * @param height   The height of the image to be written
	 * @param filename The name of the file containing the image to read
	 * @throws IOException if there is an error reading the file
	 */
	public static void writeImage(int[][][] rgb, int width, int height, String filename) throws IOException {

		BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int r = rgb[i][j][Channel.RED.ordinal()];
				int g = rgb[i][j][Channel.GREEN.ordinal()];
				int b = rgb[i][j][Channel.BLUE.ordinal()];

				// color is stored in 1 integer, with the 4 bytes storing ARGB in that
				// order. Each of r,g,b are stored in 8 bits (hence between 0 and 255).
				// So we put them all in one integer by using bit-shifting << as below
				int color = (r << 16) + (g << 8) + b;
				output.setRGB(j, i, color);
			}
		}
		String extension = filename.substring(filename.indexOf(".") + 1);
		ImageIO.write(output, extension, new FileOutputStream(filename));
	}
}
