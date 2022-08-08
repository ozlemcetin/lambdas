package java8.in.action.part3.chap08.impl;

public class IsNumeric implements ValidationStrategy {

	@Override
	public boolean execute(String s) {

		return s.matches("\\d+");
	}

}
