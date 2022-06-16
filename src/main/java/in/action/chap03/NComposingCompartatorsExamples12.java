package in.action.chap03;

import java.util.Comparator;
import java.util.function.Function;

import in.action.model.Apple;
import in.action.model.Inventory;

public class NComposingCompartatorsExamples12 {

	public static void main(String[] args) throws Exception {

		System.out.println("List : " + Inventory.APPLES);

		{
			// public class AppleComparator implements Comparator<Apple> {
			//
			// public int compare(Apple a1, Apple a2) {
			// return a1.getWeight().compareTo(a2.getWeight());
			// }
			// }

			// inventory.sort(new AppleComparator());
		}

		/*
		 * We say that the behavior of sort is parameterized: its behavior will be
		 * different based on different ordering strategies passed to it.
		 */
		if (false) {

			// Use an anonymous class
			Inventory.APPLES.sort(new Comparator<Apple>() {

				@Override
				public int compare(Apple a1, Apple a2) {
					return a1.getWeight().compareTo(a2.getWeight());
				}
			});

		}

		if (false) {

			/*
			 * the Comparator represents a function descriptor (T, T) -> int
			 */

			// Passing Code - Lambda
			Inventory.APPLES.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

			/*
			 * the Java compiler could infer the types of the parameters of a lambda
			 * expression by using the context in which the lambda appears
			 */
			Inventory.APPLES.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
		}

		if (false) {

			/*
			 * Comparator has a static helper method called comparing that takes a Function
			 * extracting a Comparable key and produces a Comparator object (we explain why
			 * interfaces can have static methods in chapter 9).
			 * 
			 * It can be used as follows (note that you now pass a lambda with only one
			 * argument: the lambda specifies how to extract the key to compare with from an
			 * apple):
			 */

			Function<Apple, Integer> keyExtractor = (Apple a) -> a.getWeight();

			Comparator<Apple> comparator = Comparator.comparing(keyExtractor);

			Inventory.APPLES.sort(comparator);
		}

		{
			// Inventory.APPLES.sort(Comparator.comparing((Apple a) -> a.getWeight()));

			/*
			 * “sort inventory comparing the weight of the apples.”
			 */
			Inventory.APPLES.sort(Comparator.comparing(Apple::getWeight));
		}

		System.out.println("Sorted : " + Inventory.APPLES);
	}

}
