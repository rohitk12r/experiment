package images.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import images.chunk.ChunkGeneratorImpl;
import images.pattern.PatternGenerator;
import images.util.ClientUtility;

/**
 * Implementation to ImageProcessingController.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
	private Readable in;
	private Appendable out;
	private final CommandController commandController;
	private final CommandGenerator commandGenerator;
	private final PatternGenerator patternGenerator;
	private final ClientUtility clientUtility;

	/**
	 * Constructor to create the ImageProcessingController.
	 *
	 * @param in                the input Readable
	 * @param out               the output Appendable
	 * @param commandController the command Controller
	 * @param commandGenerator  the command generator
	 * @param patternGenerator  the pattern generator
	 * @param clientUtility     the file utility object
	 */
	public ImageProcessingControllerImpl(Readable in, Appendable out, CommandController commandController,
			CommandGenerator commandGenerator, PatternGenerator patternGenerator, ClientUtility clientUtility)
			throws IllegalArgumentException {

		if (in == null || out == null || commandController == null || commandGenerator == null
				|| patternGenerator == null || clientUtility == null) {
			throw new IllegalArgumentException("in, out, commandController, commandGenerator, patternGenerator, "
					+ "and clientUtility must not be null.");
		}

		this.in = in;
		this.out = out;
		this.commandController = commandController;
		this.commandGenerator = commandGenerator;
		this.patternGenerator = patternGenerator;
		this.clientUtility = clientUtility;

	}

	@Override
	public void start() throws IOException, IllegalArgumentException {
		try (Scanner scanner = new Scanner(in)) {
			int[][][] image = null;
			String pattern = "";
			int numberOfChunks = -1;
			while (scanner.hasNext()) {
				String inputString = scanner.nextLine();
				String[] commandsArray = inputString.split(" ");
				if (inputString.trim().isEmpty()) {
					continue;
				}
				if (inputString.contains("load")) {

					if (commandsArray.length <= 1) {

						throw new IllegalArgumentException("Load command must be in format \"load <filename>.\"");

					}
					image = clientUtility.loadImage(commandsArray[1].trim());
					this.out.append(String.format("Image with name %s loaded successfully.", commandsArray[1].trim()))
							.append("\n");
				} else if (inputString.contains("save")) {
					if (commandsArray.length <= 1) {

						throw new IllegalArgumentException("Save command must be in format \"save <filename>.\"");

					}
					String fileName = "./generated-files/" + commandsArray[1].trim();
					if (!(pattern != null && pattern.trim().equals(""))) {
						clientUtility.saveTextFile(pattern, fileName, StandardCharsets.UTF_16LE);
						this.out.append(String.format("Pattern with pattern file name \"%s\", saved successfully.",
								commandsArray[1].trim())).append("\n");
						pattern = "";
					} else {

						clientUtility.saveImageFile(image, fileName);
						this.out.append(
								String.format("Image with name \"%s\" saved successfully.", commandsArray[1].trim()))
								.append("\n");

					}

				} else {

					if (inputString.contains("pattern")) {
						numberOfChunks = numberOfChunks != -1 ? numberOfChunks : 1;

						pattern = patternGenerator.generatePattern(image, clientUtility.getHeight(),
								clientUtility.getWidth(), new ChunkGeneratorImpl(numberOfChunks));
						numberOfChunks = -1;

					} else {

						if (inputString.trim().length() > 0 && inputString.contains("pixelate")) {

							String[] commandArray = extractCommand(inputString);
							if (commandArray.length < 2 || !commandArray[1].trim().matches("[0-9]+")) {
								throw new IllegalArgumentException(
										"Pixelate command must be in format \"pixelate <number_of_squares>.\"");
							}
							numberOfChunks = Integer.parseInt(commandArray[1].trim());

						}

						commandGenerator.createCommand(inputString);
						commandController.setCommand(commandGenerator.createCommand(inputString));
						image = commandController.runCommand(image,clientUtility);
					}
					this.out.append(String.format("Image processing command \"%s\" called successfully.", inputString))
							.append("\n");
				}
			}
		} catch (IOException e) {
			this.out.append("Error while saving reading/ writing the file: ");
			this.out.append(e.getMessage());
			this.out.append("\n");
		} catch (IllegalArgumentException e) {
			this.out.append(e.getMessage());
			this.out.append("\n");
		}
	}

	private String[] extractCommand(String commandString) {
		return commandString.split(" ");
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ImageProcessingControllerImpl{");
		sb.append("in=").append(in);
		sb.append(", out=").append(out);
		sb.append(", commandController=").append(commandController);
		sb.append(", commandGenerator=").append(commandGenerator);
		sb.append(", patternGenerator=").append(patternGenerator);
		sb.append(", clientUtility=").append(clientUtility);
		sb.append('}');
		return sb.toString();
	}
}
