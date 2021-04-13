package images.command;

import images.chunk.ChunkGenerator;
import images.controller.PixelationController;
import images.util.ClientUtility;

public class PixelationControllerCommand implements ImageProcessingCommand {

	private PixelationController pixelationController;
	private ChunkGenerator chunkGenerator;

	public PixelationControllerCommand(PixelationController pixelationController, ChunkGenerator chunkGenerator) {
		this.pixelationController = pixelationController;
		this.chunkGenerator = chunkGenerator;
	}

	@Override
	public int[][][] execute(int[][][] rgb, ClientUtility clientUtility) {
		return pixelationController.pixelation(rgb, clientUtility.getWidth(), clientUtility.getHeight(),
				chunkGenerator);
	}

}
