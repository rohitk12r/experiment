package images.chunk;

/**
 * Generator to generate chuck of the given image.
 */
public interface ChunkGenerator {

  int[] generateChunk(int [][][] image,int h,int w, int row, int col);

}
