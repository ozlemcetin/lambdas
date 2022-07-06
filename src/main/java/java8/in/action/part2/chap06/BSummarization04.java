package java8.in.action.part2.chap06;

import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class BSummarization04 {

	public static void main(String[] args) throws Exception {

		/*
		 * The Collectors class provides a specific factory method for summing:
		 * Collectors .summingInt.
		 * 
		 * It accepts a function that maps an object into the int that has to be summed
		 * and returns a collector that, when passed to the usual collect method,
		 * performs the requested summarization.
		 * 
		 * So, for instance, you can find the total number of calories in your menu list
		 * with
		 */

		{

			ToIntFunction<Dish> mapper = new ToIntFunction<Dish>() {

				@Override
				public int applyAsInt(Dish dish) {
					return dish.getCalories();
				}
			};

			int totalCalories = Inventory.MENU.stream()

					.collect(Collectors.summingInt(mapper));

			System.out.println("totalCalories: " + totalCalories);

		}

		{

			/*
			 * While traversing the stream each dish is mapped into its number of calories,
			 * and this number is added to an accumulator starting from an initial value (in
			 * this case the value is 0).
			 */

			int totalCalories = Inventory.MENU.stream()

					.collect(Collectors.summingInt(Dish::getCalories));

			System.out.println("totalCalories: " + totalCalories);

		}

		{
			/*
			 * But there’s more to summarization than mere summing; also available is a
			 * Collectors .averagingInt, together with its averagingLong and averagingDouble
			 * counterparts, to calculate the average of the same set of numeric values:
			 */

			double avgCalories = Inventory.MENU.stream()

					.collect(Collectors.averagingInt(Dish::getCalories));

			System.out.println("avgCalories: " + avgCalories);

		}

		/*
		 * So far, you’ve seen how to use collectors;
		 * 
		 * to count the elements in a stream:
		 * 
		 * .collect(Collectors.counting());
		 * 
		 * 
		 * to find the maximum and minimum values of a numeric property of those
		 * elements:
		 * 
		 * .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
		 * 
		 * 
		 * to calculate their sum and average:
		 * 
		 * .collect(Collectors.summingInt(Dish::getCalories));
		 * 
		 * .collect(Collectors.averagingInt(Dish::getCalories));		 * 
		 * 
		 */
	}

}
