package java8.in.action.part3.chap10.impl;

import java.util.Optional;

public class Person {

	/*
	 * A person might or might not own a car, so you declare this field Optional.
	 */
	private Optional<Car> car;

	public Optional<Car> getCar() {
		return car;
	}
}