package java8.in.action.part1.chap02.impl;

import java8.in.action.model.Apple;

public class AppleRedAndHeavyPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {

		return "red".equals(apple.getColor()) && apple.getWeight() > 150;
	}

}
