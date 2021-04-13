package images.controller;

import java.util.ArrayDeque;
import java.util.Queue;

import images.command.ImageProcessingCommand;
import images.util.ClientUtility;

/**
 * The implementation of CommandController.
 *
 */
public class CommandControllerImpl implements CommandController {
	private final Queue<ImageProcessingCommand> commands;

	/**
	 * Command controller constructor.
	 */
	public CommandControllerImpl() {
		this.commands = new ArrayDeque<>();
	}

	@Override
	public void setCommand(ImageProcessingCommand command) throws IllegalArgumentException {
		if (command == null) {
			throw new IllegalArgumentException(this.getClass().getSimpleName() + ": Command cannot be null.");
		}
		commands.add(command);

	}

	@Override
	public int[][][] runCommand(int[][][] image,ClientUtility clientUtility) throws IllegalArgumentException {
		if (image == null) {
			throw new IllegalArgumentException(this.getClass().getSimpleName() + ": Image cannot be null.");
		}
		if (commands.size() > 0) {
			return commands.remove().execute(image,clientUtility);
		} else {
			return image;
		}
	}
}
