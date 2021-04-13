package images.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Utility interface to do the file serialization processes. This interface can
 * write and read the files of type such as Image and text.
 * 
 */
public interface ClientUtility {

	/**
	 * Utility method to save the image to file system.
	 * 
	 * @param image    the image to be saved.
	 * @param fileName the name of the desired file
	 * @throws IOException the IO Exception
	 */
	void saveImageFile(int[][][] rgb, String fileName) throws IllegalArgumentException, IOException;

	/**
	 * Load the image from file System.
	 * 
	 * @param fileName the file name to load the image file
	 * @return the loaded Image
	 * @throws IOException the IO Exception
	 */
	int[][][] loadImage(String fileName) throws IllegalArgumentException, IOException;

	/**
	 * Load the test file from the file system.
	 * 
	 * @param fileName name of desired the file to read
	 * @return the Readable object
	 * @throws FileNotFoundException the file not found exception
	 */
	Readable loadTextFile(String fileName) throws IllegalArgumentException, FileNotFoundException;

	/**
	 * Save the text file into file system.
	 * 
	 * @param data         the data which needs to be saved
	 * @param fileName     the name of the desired file to save
	 * @param encodingType the file encoding type
	 * @throws IOException the input out put exception
	 */
	void saveTextFile(String data, String fileName, Charset encodingType) throws IllegalArgumentException, IOException;

	int getHeight();

	int getWidth();

}
