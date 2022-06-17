package java8.in.action.part1.chap03;

import java.util.function.Predicate;

import java8.in.action.model.Apple;

public class CLambdaPredicateExamples03 {

	public static void main(String[] args) throws Exception {

		{
			Predicate<Apple> p = new Predicate<Apple>() {

				@Override
				public boolean test(Apple t) {
					return "red".equals(t.getColor());
				}
			};

		}

		{
			Predicate<Apple> p = (Apple t) -> "red".equals(t.getColor());
		}

		{
			/*
			 * The example is invalid because the lambda expression (Apple a) -> a.get-
			 * Weight() has the signature (Apple) -> Integer, which is different than the
			 * signature of the method test defined in Predicate<Apple>: (Apple) -> boolean
			 */

			// Predicate<Apple> p = (Apple a) -> a.getWeight();
		}
	}

}
