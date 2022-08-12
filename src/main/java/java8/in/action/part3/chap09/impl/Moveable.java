package java8.in.action.part3.chap09.impl;

public interface Moveable {

	int getX();

	int getY();

	void setX(int x);

	void setY(int y);

	/*
	 * default implementations
	 */
	default void moveHorizontally(int distance) {

		setX(getX() + distance);
	}

	default void moveVertically(int distance) {

		setY(getY() + distance);
	}

}
