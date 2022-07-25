package java8.in.action.part2.chap06A;

import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class DReductionExample10 {

	public static void main(String[] args) throws Exception {

		// Collectors.reducing
		{

			Integer totalCalories = Inventory.MENU.stream()

					.collect(Collectors.reducing(

							// Initial value
							0,

							// Transformation function
							Dish::getCalories,

							// Aggregating function
							Integer::sum));

			System.out.println("totalCalories: " + totalCalories);

		}

		/*
		 * We already observed in chapter 5 that there’s another way to perform the same
		 * operation without using a collector
		 * 
		 * —by mapping the stream of dishes into the number of calories of each dish and
		 * then reducing this resulting stream with the same method reference used in
		 * the previous version:
		 */

		// reduce
		{

			Integer totalCalories = Inventory.MENU.stream()

					.map(Dish::getCalories)

					.reduce(0, Integer::sum);

			System.out.println("totalCalories: " + totalCalories);

		}

		/*
		 * Finally, and even more concisely, you can achieve the same result by mapping
		 * the stream to an IntStream and then invoking the sum method on it:
		 */

		// reduce
		{

			Integer totalCalories = Inventory.MENU.stream()

					.mapToInt(Dish::getCalories)

					.sum();

			System.out.println("totalCalories: " + totalCalories);

		}
	}

}
