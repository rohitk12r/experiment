package images.pattern;

import images.chunk.ChunkGenerator;

/**
 * Interface to Generate Pattern.
 */
public interface PatternGenerator {

	String generatePattern(int[][][] rgb, int h, int w, ChunkGenerator chunkGenerator);

}