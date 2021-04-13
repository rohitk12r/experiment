package images.controller;

import images.ColorTransformation;
import images.ColorTransformationImpl;
import images.ImageFilter;
import images.ImageFilterImpl;
import images.chunk.ChunkGenerator;
import images.chunk.ChunkGeneratorImpl;
import images.command.ColorTransformationCommand;
import images.command.CommandEnum;
import images.command.ImageFilterCommand;
import images.command.ImageProcessingCommand;
import images.command.MosaicControllerCommand;
import images.command.PixelationControllerCommand;

/**
 * Implementation of CommandGenerator.
 * 
 */
public class CommandGeneratorImpl implements CommandGenerator {

	@Override
	public ImageProcessingCommand createCommand(String commandString) throws IllegalArgumentException {
		ImageProcessingCommand imageProcessingCommand = null;
		ColorTransformation colorTransformation = new ColorTransformationImpl();
		ImageFilter filter = new ImageFilterImpl();
		String[] commandsArray = commandString.split(" ");
		switch (commandsArray[0]) {
		case "dither":
			if (commandsArray.length <= 1) {
				throw new IllegalArgumentException(
						this.getClass().getSimpleName() + ": Dither image must be in format \"dither <noOfColors>\"");
			}

			imageProcessingCommand = new ColorTransformationCommand(colorTransformation, CommandEnum.dither,
					Integer.parseInt(commandsArray[1]));
			break;
		case "sepia-tone":
			imageProcessingCommand = new ColorTransformationCommand(colorTransformation, CommandEnum.sepiaTone);
			break;
		case "grayscale":
			imageProcessingCommand = new ColorTransformationCommand(colorTransformation, CommandEnum.grayscale);
			break;
		case "blur":
			imageProcessingCommand = new ImageFilterCommand(filter, CommandEnum.blur);

			break;
		case "sharpen":
			imageProcessingCommand = new ImageFilterCommand(filter, CommandEnum.blur);
			break;

		case "mosaic":
			imageProcessingCommand = new MosaicControllerCommand(
					new MosaicControllerImpl(Integer.parseInt(commandsArray[1])));
			break;
		case "pixelate":

			if (commandsArray.length < 2 || !commandsArray[1].trim().matches("[0-9]+")
					|| Integer.parseInt(commandsArray[1].trim()) < 1) {
				throw new IllegalArgumentException(this.getClass().getSimpleName()
						+ ": Number of squares must be specified and must be more than zero.");
			}
			ChunkGenerator chunkGenerator = new ChunkGeneratorImpl(Integer.parseInt(commandsArray[1].trim()));
			imageProcessingCommand = new PixelationControllerCommand(
					new PixelationControllerImpl(Integer.parseInt(commandsArray[1].trim())), chunkGenerator);

			break;
		default:
			break;
		}
		return imageProcessingCommand;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CommandGeneratorImpl{");
		sb.append('}');
		return sb.toString();
	}
}
