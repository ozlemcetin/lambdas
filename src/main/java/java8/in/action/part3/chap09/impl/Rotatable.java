package java8.in.action.part3.chap09.impl;

public interface Rotatable {

	void setRotationAngle(int angleInDegrees);

	int getRotationAngle();

	/*
	 * A default implementation for the method rotateBy
	 * 
	 * Now, any class that implements Rotatable will need to provide an
	 * implementation for setRotationAngle and getRotationAngle but will inherit the
	 * default implementation of rotateBy for free.
	 */
	default void rotateBy(int angleInDegrees) {

		setRotationAngle((getRotationAngle() + angleInDegrees) % 360);
	}
}
