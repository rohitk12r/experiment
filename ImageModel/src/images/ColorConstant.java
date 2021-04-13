package images;

/**
 * It is constant of color transformation
 */
public interface ColorConstant {
	double[][] SEPIATONE_TRANFORMATION_MATRIX = { { 0.393, 0.769, 0.189 },
												{ 0.349, 0.686, 0.168 },
												{ 0.272, 0.534, 0.131 } 
												};
	double[][] GRAYSCALE_TRANFORMATION_MATRIX = { { 0.2126, 0.7152, 0.0722 },
												{ 0.2126, 0.7152, 0.0722 },
												{ 0.2126, 0.7152, 0.0722 }
										  		};
}
