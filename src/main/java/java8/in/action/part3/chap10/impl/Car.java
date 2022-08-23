package java8.in.action.part3.chap10.impl;

import java.util.Optional;

public class Car {

	/*
	 * A car might or might not be insured, so you declare this field Optional.
	 */
	private Optional<Insurance> insurance;

	public Optional<Insurance> getInsurance() {
		return insurance;
	}
}