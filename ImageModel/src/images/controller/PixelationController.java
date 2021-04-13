package images.controller;

import images.chunk.ChunkGenerator;

public interface PixelationController {

	int[][][] pixelation(int[][][] rgb, int w, int h, ChunkGenerator chunkGenerator);

}
