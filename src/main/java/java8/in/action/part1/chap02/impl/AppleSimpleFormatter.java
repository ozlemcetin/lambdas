package java8.in.action.part1.chap02.impl;

import java8.in.action.model.Apple;

public class AppleSimpleFormatter implements AppleFormatter {

	@Override
	public String accept(Apple apple) {

		return "An apple of " + apple.getWeight() + "g";

	}

}
