package images;

public class DitherUtils {

	public static int[] findQuantError(int[] oldPixel, int[] newPixel) {
		int[] quantError = new int[oldPixel.length];
		for (int i = 0; i < quantError.length; i++) {

			quantError[i] = oldPixel[i] - newPixel[i];
		}
		return quantError;
	}

	public static int[] quantErrorCorrect(int[] oldColors, double coefficient, int[] quantError) {
		int[] newColors = new int[oldColors.length];
		for (int i = 0; i < newColors.length; i++) {
			newColors[i] = (int) (oldColors[i] + (quantError[i] * coefficient));
		}
		return newColors;
	}

	public static int[] findClosestPaletteColor(int[] oldColors, double channelRound) {
		int[] newColors = new int[oldColors.length];
		for (int i = 0; i < newColors.length; i++) {
			newColors[i] = (int) (Math.round(oldColors[i] / channelRound) * channelRound);
		}
		return newColors;
	}

	public static double colorWithEssence(int color) {
		return Math.floor(256 / (color - 1.0));
	}

	public static double sixteenColorWithEssence() {
		return Math.floor(256 / (16 - 1.0));
	}
}
