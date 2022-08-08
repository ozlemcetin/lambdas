package java8.in.action.part3.chap08.impl;

public class HeaderTextProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String input) {

		return "From Raoul, Mario and Alan: " + input;
	}

}
