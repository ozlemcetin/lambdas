package java8.in.action.part3.chap10.impl;

public class Insurance {

	/*
	 * An insurance company must have a name.
	 * 
	 * An insurance company must have a name, so if you find one without, you’ll
	 * have to work out what’s wrong in your data instead of adding a piece of code
	 * covering up this circumstance
	 */
	private String name;

	public Insurance(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}