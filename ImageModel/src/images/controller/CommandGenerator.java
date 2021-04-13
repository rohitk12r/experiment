package images.controller;

import images.command.ImageProcessingCommand;

/**
 * ImageProcessingCommand Generator to generate the command objects.
 * 
 */
public interface CommandGenerator {

  /**
   * Create ImageProcessingCommand based the Command name given in the String.
   * 
   * @param commandString the command name
   * @return the ImageProcessingCommand object
   */
  ImageProcessingCommand createCommand(String commandString);
}
