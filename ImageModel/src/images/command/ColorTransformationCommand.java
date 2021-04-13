package images.command;

import images.ColorConstant;
import images.ColorTransformation;
import images.DitherUtils;
import images.util.ClientUtility;

public class ColorTransformationCommand implements ImageProcessingCommand {

	private final ColorTransformation colorTransformation;
	private CommandEnum commandEnum;
	private int color;

	public ColorTransformationCommand(ColorTransformation colorTransformation, CommandEnum command) {
		this.colorTransformation = colorTransformation;
		this.commandEnum = command;
	}

	public ColorTransformationCommand(ColorTransformation colorTransformation, CommandEnum command, int color) {
		this.colorTransformation = colorTransformation;
		this.commandEnum = command;
		this.color = color;
	}

	@Override
	public int[][][] execute(int[][][] rgb, ClientUtility clientUtility) {
		int[][][] newrgb = null;
		if (commandEnum.equals(CommandEnum.sepiaTone))
			newrgb = colorTransformation.tranform(rgb, ColorConstant.SEPIATONE_TRANFORMATION_MATRIX);
		if (commandEnum.equals(CommandEnum.grayscale))
			newrgb = colorTransformation.tranform(rgb, ColorConstant.GRAYSCALE_TRANFORMATION_MATRIX);
		if (commandEnum.equals(CommandEnum.dither))
			newrgb = colorTransformation.reduceColorDensity(rgb, DitherUtils.colorWithEssence(color));
		return newrgb;
	}
}
