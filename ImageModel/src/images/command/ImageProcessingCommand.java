package images.command;

import images.util.ClientUtility;

/**
 * The command interface to execute models.
 *
 */
public interface ImageProcessingCommand {

	/**
	 * Execute the model.
	 * 
	 * @param rgb input of image
	 * @return edited image
	 */
	int[][][] execute(int[][][] rgb,ClientUtility clientUtility);

}
