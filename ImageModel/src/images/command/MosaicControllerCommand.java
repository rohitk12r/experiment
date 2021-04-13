package images.command;

import images.controller.MosaicController;
import images.util.ClientUtility;

public class MosaicControllerCommand implements ImageProcessingCommand {
	private MosaicController mosaicController;

	public MosaicControllerCommand(MosaicController mosaicController) {
		this.mosaicController = mosaicController;
	}

	@Override
	public int[][][] execute(int[][][] rgb, ClientUtility clientUtility) {
		return mosaicController.mosic(rgb, clientUtility.getHeight(), clientUtility.getWidth());
	}
}
