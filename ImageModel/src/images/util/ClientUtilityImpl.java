package images.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import images.ImageUtilities;

/**
 * Implementation to ClientUtility interface.
 * 
 */
public final class ClientUtilityImpl implements ClientUtility {
	private String fileName;

	public ClientUtilityImpl(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void saveImageFile(int[][][] rgb, String fileName) throws IllegalArgumentException, IOException {
		validateImage(rgb);
		int height = ImageUtilities.getHeight(fileName);
		int width = ImageUtilities.getWidth(fileName);
		ImageUtilities.writeImage(rgb, width, height, fileName);
	}

	@Override
	public int[][][] loadImage(String fileName) throws IllegalArgumentException, IOException {
		validateString(fileName);
		int[][][] imageArray = ImageUtilities.readImage(fileName);
		return imageArray;
	}

	@Override
	public Readable loadTextFile(String fileName) throws IllegalArgumentException, FileNotFoundException {
		validateString(fileName);
		File file = new File(fileName);
		InputStream inputStream = new FileInputStream(file);
		return new InputStreamReader(inputStream);
	}

	@Override
	public void saveTextFile(String data, String fileName, Charset encodingType)
			throws IllegalArgumentException, IOException {
		validateString(data);
		validateString(fileName);
		Writer stream = new OutputStreamWriter(new FileOutputStream(fileName), encodingType);
		stream.write(data);
		stream.close();

	}

	private void validateString(String arg) throws IllegalArgumentException {
		if (arg == null || arg.isEmpty()) {
			throw new IllegalArgumentException(
					this.getClass().getSimpleName() + ": String argument cannot be null and empty");
		}

	}

	private void validateImage(int[][][] rgb) throws IllegalArgumentException {
		if (rgb == null || rgb[0].length == 0) {
			throw new IllegalArgumentException(this.getClass().getSimpleName() + ": Image cannot be null or of size 0");
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ClientUtilityImpl{");
		sb.append('}');
		return sb.toString();
	}

	@Override
	public int getHeight(){
		try {
			return ImageUtilities.getHeight(this.fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getWidth(){
		try {
			return ImageUtilities.getWidth(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
