package java8.in.action.part3.chap08.impl;

public class Validator {

	/*
	 * C) You can then use these different validation strategies in your program:
	 */
	private final ValidationStrategy strategy;

	public Validator(ValidationStrategy strategy) {
		this.strategy = strategy;
	}

	public boolean validate(String s) {

		return strategy.execute(s);

	}

}
