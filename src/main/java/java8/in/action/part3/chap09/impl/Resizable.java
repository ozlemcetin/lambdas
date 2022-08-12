package java8.in.action.part3.chap09.impl;

public interface Resizable extends Drawable {

	// API version 1
	int getWidth();

	int getHeight();

	void setWidth(int width);

	void setHeight(int height);

	void setAbsoluteSize(int width, int height);

	/*
	 * Adding a new method for API version 2
	 * 
	 * void setRelativeSize(int wFactor, int hFactor);
	 */

	default void setRelativeSize(int wFactor, int hFactor) {

		setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
	}
}
