package in.action.chap02;

import in.action.model.Apple;

public class AppleRedAndHeavyPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {

		return "red".equals(apple.getColor()) && apple.getWeight() > 150;
	}

}
