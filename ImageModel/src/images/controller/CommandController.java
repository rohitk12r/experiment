
package images.controller;

import images.command.ImageProcessingCommand;
import images.util.ClientUtility;

/**
 * The command controller to set and get the command.
 * 
 */
public interface CommandController {

	/**
	 * Set the command to command controller.
	 * 
	 * @param command the command to set
	 */
	void setCommand(ImageProcessingCommand command);

	/**
	 * Run the command one by one.
	 * 
	 * @return the edited image object.
	 */
	int[][][] runCommand(int[][][] image,ClientUtility clientUtility);

}
