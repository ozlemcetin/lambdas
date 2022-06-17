package java8.in.action.part1.chap02.impl;

import java8.in.action.model.Apple;

public class AppleFancyFormatter implements AppleFormatter {

	@Override
	public String accept(Apple apple) {

		String characteristic = apple.getWeight() > 150 ? "heavy" : "light";

		return "A " + characteristic + " " + apple.getColor() + " apple";
	}

}
