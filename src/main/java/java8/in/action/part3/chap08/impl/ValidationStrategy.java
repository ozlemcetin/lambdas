package java8.in.action.part3.chap08.impl;

public interface ValidationStrategy {

	/*
	 * A) Let’s say you’d like to validate whether a text input is properly
	 * formatted for different criteria (for example, it consists of only lowercase
	 * letters or is numeric).
	 * 
	 * You start by defining an interface to validate the text (represented as a
	 * String):
	 */
	boolean execute(String s);
}
