import java.awt.Color;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture picture;
	public SeamCarver(Picture picture) {
		if (picture == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.picture = picture;
	}
	// current picture
	public Picture picture() {
		return null;
	}
	// width of current picture
	public int width() {
		return picture.width();
	}

	// height of current picture
	public int height() {
		return picture.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || y == 0 || picture.width() - 1 == x || picture.height() - 1 == y) {
			return 1000;
		}
		Color top = picture.get(x, y - 1);
		Color bottom = picture.get(x, y + 1);
		Color left = picture.get(x - 1, y);
		Color right = picture.get(x + 1, y);
		int red = right.getRed() - left.getRed();
		int blue = right.getBlue() - left.getBlue();
		int green = right.getGreen() - left.getGreen();
		int horizontal = red * red + green * green + blue * blue;
		int redver = top.getRed() - bottom.getRed();
		int bluever = top.getBlue() - bottom.getBlue();
		int greenver = top.getGreen() - bottom.getGreen();
		int vertical = redver * redver + greenver * greenver + bluever * bluever;
		double energy = Math.sqrt(horizontal + vertical);
		return energy;

	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}