package in.action.chap02.impl;

import in.action.model.Apple;

public class AppleSimpleFormatter implements AppleFormatter {

	@Override
	public String accept(Apple apple) {

		return "An apple of " + apple.getWeight() + "g";

	}

}
