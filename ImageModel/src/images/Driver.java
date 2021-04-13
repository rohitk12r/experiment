package images;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import images.chunk.ChunkGenerator;
import images.chunk.ChunkGeneratorImpl;
import images.controller.CommandController;
import images.controller.CommandControllerImpl;
import images.controller.CommandGenerator;
import images.controller.CommandGeneratorImpl;
import images.controller.ImageProcessingController;
import images.controller.ImageProcessingControllerImpl;
import images.controller.MosaicController;
import images.controller.MosaicControllerImpl;
import images.pattern.PatternGenerator;
import images.pattern.PatternGeneratorImpl;
import images.util.ClientUtility;
import images.util.ClientUtilityImpl;

public class Driver {

	/**
	 * Main method to run the program.
	 * 
	 * @param args string arguments
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		/*
		 * CommandController commandController = new CommandControllerImpl();
		 * 
		 * String fileName = args[0];
		 * System.out.println("Reading the commands from file: " + fileName);
		 * System.out.println("Your commands are being processed...\n" +
		 * "Please wait for a while before I finish your given tasks... ");
		 * 
		 * File file = new File("./" + fileName); try (InputStream inputStream = new
		 * FileInputStream(file)) { InputStreamReader in = new
		 * InputStreamReader(inputStream); StringBuilder out = new StringBuilder();
		 * CommandGenerator commandGenerator = new CommandGeneratorImpl();
		 * PatternGenerator patternGenerator = new PatternGeneratorImpl(); ClientUtility
		 * clientUtility = new ClientUtilityImpl(fileName); ImageProcessingController
		 * controller = new ImageProcessingControllerImpl(in, out, commandController,
		 * commandGenerator, patternGenerator, clientUtility); controller.start();
		 * System.out.println(out.toString()); } catch (FileNotFoundException e) {
		 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
		 */
	testMosaic();	
	}

	private static void testMosaic() throws IOException {
		String imagePath = "C:\\Users\\ns489\\OneDrive\\Desktop\\Rohit\\Docs\\Backup-german\\Shashank/flowers.jpg";
		String mosaicImage = "C:\\Users\\ns489\\OneDrive\\Desktop\\Rohit\\Docs\\Backup-german\\Shashank/flowersMosaicImage6600.jpg";

		MosaicController mosaicController = new MosaicControllerImpl(570);

		int[][][] rgb = ImageUtilities.readImage(imagePath);
		int height = ImageUtilities.getHeight(imagePath);
		int width = ImageUtilities.getWidth(imagePath);

		int[][][] mosaicRGB = mosaicController.mosic(rgb, width, height); //
		ImageUtilities.writeImage(mosaicRGB, width, height, mosaicImage);

	}

}
