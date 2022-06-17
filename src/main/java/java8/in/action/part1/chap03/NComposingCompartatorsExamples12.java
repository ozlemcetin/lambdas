package java8.in.action.part1.chap03;

import java.util.Comparator;
import java.util.function.Function;

import java8.in.action.model.Apple;
import java8.in.action.model.Inventory;

public class NComposingCompartatorsExamples12 {

	public static void main(String[] args) throws Exception {

		System.out.println("List : " + Inventory.APPLES);

		if (false) {

			/*
			 * You’ve seen that you can use the static method Comparator.comparing to return
			 * a Comparator based on a Function that extracts a key for comparison as
			 * follows:
			 * 
			 * Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
			 * 
			 */

			Function<Apple, Integer> keyExtractor = (Apple a) -> a.getWeight();

			Comparator<Apple> comparator = Comparator.comparing(keyExtractor);

			Inventory.APPLES.sort(comparator);
		}

		if (false) {

			// Sorting by increasing weight
			Inventory.APPLES.sort(Comparator.comparing(Apple::getWeight));

			// Sorting by decreasing weight
			Inventory.APPLES.sort(Comparator.comparing(Apple::getWeight).reversed());
		}

		/*
		 * but what if you find two apples that have the same weight? Which apple should
		 * have priority in the sorted list?
		 * 
		 * You may want to provide a second Comparator to further refine the comparison.
		 * For example, after two apples are compared based on their weight, you may
		 * want to sort them by country of origin.
		 * 
		 * The thenComparing method allows you to do just that.
		 * 
		 * It takes a function as parameter (just like the method comparing) and
		 * provides a second Comparator if two objects are considered equal using the
		 * initial Comparator. You can solve the problem elegantly again:
		 */
		{

			// Sorting by decreasing weight
			Inventory.APPLES.sort(Comparator.comparing(Apple::getWeight)

					// decreasing
					.reversed()

					/*
					 * Sorting further by country when two apples have same weight
					 */
					.thenComparing(Comparator.comparing(Apple::getColor)));

		}

		System.out.println("Sorted : " + Inventory.APPLES);
	}

}
