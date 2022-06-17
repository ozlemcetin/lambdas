package java8.in.action.part1.chap03;

import java.util.List;
import java.util.function.Predicate;

import java8.in.action.model.Apple;
import java8.in.action.model.Inventory;

public class PComposingPredicatesExamples13 {

	public static void main(String[] args) throws Exception {

		/*
		 * The Predicate interface includes three methods that let you reuse an existing
		 * Predicate to create more complicated ones: negate, and, and or.
		 * 
		 * For example, you can use the method negate to return the negation of a
		 * Predicate, such as an apple that is not red:
		 */

		if (false) {

			Predicate<Apple> redApplePredicate = new Predicate<Apple>() {

				@Override
				public boolean test(Apple a) {

					return "red".equals(a.getColor());
				}

			};

			List<Apple> redApples = EPredicateConsumeFunctionExamples05.filter(Inventory.APPLES, redApplePredicate);

			System.out.println(redApples);

		}

		Predicate<Apple> redApple = (Apple a) -> "red".equals(a.getColor());
		{
			System.out.println(EPredicateConsumeFunctionExamples05.filter(Inventory.APPLES, redApple));
		}

		/*
		 * Produces the negation of the existing Predicate object redApple
		 */
		Predicate notRedApple = redApple.negate();
		{
			System.out.println(EPredicateConsumeFunctionExamples05.filter(Inventory.APPLES, notRedApple));
		}

		/*
		 * Chaining two predicates to produce another Predicate object
		 */
		Predicate redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
		{
			System.out.println(EPredicateConsumeFunctionExamples05.filter(Inventory.APPLES, redAndHeavyApple));
		}

		/*
		 * You can combine the resulting predicate one step further to express apples
		 * that are red and heavy (above 150 g) or just green apples:
		 */

		/*
		 * Note that the precedence of methods and and or is managed from left to right
		 * using their positions in the chain.
		 * 
		 * So a.or(b).and(c) can be seen as (a || b) && c.
		 */

		Predicate redAndHeavyAppleOrGreen = redApple

				.and(a -> a.getWeight() > 150)

				.or(a -> "green".equals(a.getColor()));

		{
			System.out.println(EPredicateConsumeFunctionExamples05.filter(Inventory.APPLES, redAndHeavyAppleOrGreen));
		}
	}

}
