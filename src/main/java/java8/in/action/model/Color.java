package java8.in.action.model;

public class Color {

	int r;
	int g;
	int b;

	/*
	 * 
	 */
	public Color(int r, int g, int b) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/*
	 * 
	 */

	@Override
	public String toString() {
		return "Color [r=" + r + ", g=" + g + ", b=" + b + "]";
	}

}
