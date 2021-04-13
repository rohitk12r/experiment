package images;

/**
 * This is design for color transformation of image.
 */
public interface ColorTransformation {

	public int[][][] tranform(int[][][] rgb, double[][] colorTransformMatrix);

	public int[][][] reduceColorDensity(int[][][] rgb, double channelRound);
}
