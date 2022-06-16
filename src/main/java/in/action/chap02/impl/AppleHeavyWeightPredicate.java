package in.action.chap02.impl;

import in.action.model.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {

		return apple.getWeight() > 150;
	}

}
