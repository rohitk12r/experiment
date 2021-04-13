package images.pattern;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import images.PatternUtilities;
import images.chunk.ChunkGenerator;

/**
 * Implementation of interface PatternGenerator.
 */
public class PatternGeneratorImpl implements PatternGenerator {

	@Override
	public String generatePattern(int[][][] rgb, int h, int w, ChunkGenerator chunkGenerator)
			throws IllegalArgumentException {

		int[][][] imageArray = rgb;
		StringBuilder patternString = new StringBuilder(String.valueOf(h));
		patternString.append("X").append(w).append("\n");

		Map<String, Character> patternMap = PatternUtilities.getCharacterMap();
		Set<String> dmcLegend = new HashSet<>();
		Map<String, Integer[]> map = PatternUtilities.getDMCMap();
		int i = 0;
		while (i < imageArray.length) {
			int rowIncrement = -1;
			int j = 0;

			while (j < imageArray[i].length) {

				String dmc = findClosestColor(imageArray[i][j], map);
				patternString.append(patternMap.get(dmc));
				dmcLegend.add(dmc);
				int[] superPixel = chunkGenerator.generateChunk(rgb, h, w, i, j);
				if (superPixel != null && superPixel.length == 2) {
					j += superPixel[1] > 0 ? superPixel[1] : (j + 1);
					if (rowIncrement == -1) {
						rowIncrement = superPixel[0];
					}
				}
			}

			i += rowIncrement;
			patternString.append("\n");
		}
		dmcLegend = sortDMCLegend(dmcLegend);
		patternString.append(patternSpecification(dmcLegend));

		return patternString.toString();
	}

	private String findClosestColor(int[] rgb, Map<String, Integer[]> map) {

		Double closestColorDistance = Double.MAX_VALUE;
		String closestColor = "";
		for (String key : map.keySet()) {
			double distance = euclideanDistance(rgb, map.get(key));
			if (distance < closestColorDistance) {
				closestColorDistance = distance;
				closestColor = key;
			}
		}
		return closestColor;
	}

	private double euclideanDistance(int[] rgb, Integer[] dmcRGB) {
		double rBar = ((double) rgb[0] + (double) dmcRGB[0]) / 2;
		double deltaRPow = Math.pow((rgb[0] - dmcRGB[0]), 2);
		double deltaGPow = Math.pow((rgb[1] - dmcRGB[1]), 2);
		double deltaBPow = Math.pow((rgb[2] - dmcRGB[2]), 2);

		double rCoefficient = (2 + (rBar / 256)) * deltaRPow;
		double gCoefficient = 4 * deltaGPow;
		double bCoefficient = (2 + ((255 - rBar) / 256)) * deltaBPow;

		return Math.sqrt(rCoefficient + gCoefficient + bCoefficient);
	}

	private String patternSpecification(Set<String> dmcLegend) {
		StringBuilder legendString = new StringBuilder("\nLEGEND\n");

		Map<String, Character> patternMap = PatternUtilities.getCharacterMap();
		for (String dmcKey : dmcLegend) {
			legendString.append(patternMap.get(dmcKey)).append(" DMC-").append(dmcKey).append("\n");
		}
		return legendString.toString();
	}

	private Set<String> sortDMCLegend(Set<String> unSortedDMCLegend) {
		Set<String> sortedDMCLegend = new LinkedHashSet<>();
		Set<Integer> numericDMCSorted = new TreeSet<>();
		Set<String> stringDMCSorted = new TreeSet<>();
		for (String dmc : unSortedDMCLegend) {
			if (dmc.matches("[0-9]+")) {
				numericDMCSorted.add(Integer.parseInt(dmc));
			} else {
				stringDMCSorted.add(dmc);
			}
		}
		for (Integer dmcInt : numericDMCSorted) {
			sortedDMCLegend.add(String.valueOf(dmcInt));
		}
		for (String dmcString : stringDMCSorted) {
			sortedDMCLegend.add(dmcString);
		}
		return sortedDMCLegend;
	}
}
