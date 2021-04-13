package images.command;

import images.FilterConstant;
import images.ImageFilter;
import images.util.ClientUtility;

public class ImageFilterCommand implements ImageProcessingCommand {

	private ImageFilter imageFilter;
	private CommandEnum commandEnum;

	public ImageFilterCommand(ImageFilter imageFilter, CommandEnum commandEnum) {
		this.imageFilter = imageFilter;
		this.commandEnum = commandEnum;
	}

	@Override
	public int[][][] execute(int[][][] rgb,ClientUtility clientUtility) {
		int[][][] newrgb = null;
		if (commandEnum.equals(CommandEnum.blur)) {
			newrgb = imageFilter.filter(rgb, FilterConstant.BLUR_FILTER);
		}
		if (commandEnum.equals(CommandEnum.sharpen)) {
			newrgb = imageFilter.filter(rgb, FilterConstant.SHARPEN_FILTER);
		}
		return newrgb;
	}

}
