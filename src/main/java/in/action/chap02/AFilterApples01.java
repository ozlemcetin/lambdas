package in.action.chap02;

import java.util.ArrayList;
import java.util.List;

import in.action.chap02.impl.AppleFancyFormatter;
import in.action.chap02.impl.AppleFormatter;
import in.action.chap02.impl.AppleGreenColorPredicate;
import in.action.chap02.impl.AppleHeavyWeightPredicate;
import in.action.chap02.impl.ApplePredicate;
import in.action.chap02.impl.AppleRedAndHeavyPredicate;
import in.action.chap02.impl.AppleSimpleFormatter;
import in.action.model.Apple;
import in.action.model.Inventory;

public class AFilterApples01 {

	public static void main(String[] args) {

		/*
		 * ==
		 */

		// Value Parameterization
		System.out.println("Green Apples: " + filterApplesByColor(Inventory.APPLES, "green"));

		System.out.println("Red Apples: " + filterApplesByColor(Inventory.APPLES, "red"));

		/*
		 * ==
		 */

		/*
		 * the ability to tell a method to take multiple behaviors (or strategies) as
		 * parameters and use them internally to accomplish different behaviors.
		 */

		// Classes
		System.out
				.println("Green Apples: " + filterApplesByPredicate(Inventory.APPLES, new AppleGreenColorPredicate()));

		System.out
				.println("Heavy Apples: " + filterApplesByPredicate(Inventory.APPLES, new AppleHeavyWeightPredicate()));

		System.out.println(
				"Red and Heavy Apples: " + filterApplesByPredicate(Inventory.APPLES, new AppleRedAndHeavyPredicate()));

		if (false) {

			/*
			 * ==
			 */

			/*
			 * pass multiple behaviors to your prettyPrintApple method.
			 * 
			 * You do this by instantiating implementations of AppleFormatter and giving
			 * them as arguments to prettyPrintApple:
			 */
			System.out.println("Apples' Colours: ");
			printApplesByFormatter(Inventory.APPLES, new AppleFancyFormatter());

			System.out.println("Apples' Weights: ");
			printApplesByFormatter(Inventory.APPLES, new AppleSimpleFormatter());

		}

		/*
		 * ==
		 */

		/*
		 * Parameterizing the behavior of the method filterApples directly inline!
		 */

		// Anonymous Classes
		System.out.println("Red Apples: " + filterApplesByPredicate(Inventory.APPLES, new ApplePredicate() {

			@Override
			public boolean test(Apple apple) {
				return "red".equals(apple.getColor());
			}
		}));

		/*
		 * ==
		 */

		// Lambdas
		System.out.println("Red Apples: "
				+ filterApplesByPredicate(Inventory.APPLES, (Apple apple) -> "red".equals(apple.getColor())));
	}

	public static List<Apple> filterApplesByColor(List<Apple> list, String color) {

		List<Apple> result = new ArrayList<>();
		{
			for (Apple apple : list) {

				// Select only green apples.
				if (color != null && color.equals(apple.getColor())) {

					result.add(apple);
				}
			}
		}
		return result;
	}

	/*
	 * The predicate object encapsulates the condition to test on an apple.
	 */
	public static List<Apple> filterApplesByPredicate(List<Apple> list, ApplePredicate p) {

		List<Apple> result = new ArrayList<>();
		{
			for (Apple apple : list) {

				/*
				 * Does the apple match the condition represented by p?
				 */
				if (p.test(apple)) {

					result.add(apple);
				}
			}
		}
		return result;
	}

	public static void printApplesByFormatter(List<Apple> list, AppleFormatter f) {

		for (Apple apple : list) {

			System.out.println(f.accept(apple));
		}

	}

}
