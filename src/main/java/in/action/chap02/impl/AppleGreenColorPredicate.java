package in.action.chap02.impl;

import in.action.model.Apple;

public class AppleGreenColorPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {

		return "green".equals(apple.getColor());
	}

}
