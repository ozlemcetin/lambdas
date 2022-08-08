package java8.in.action.part3.chap08.impl;

public class IsAllLowerCase implements ValidationStrategy {

	/*
	 * B) Second, you define one or more implementation(s) of that interface:
	 * 
	 */

	@Override
	public boolean execute(String s) {

		return s.matches("[a-z]+");
	}

}
