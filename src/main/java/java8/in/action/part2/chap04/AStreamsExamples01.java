package java8.in.action.part2.chap04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class AStreamsExamples01 {

	public static void main(String[] args) throws Exception {

		/*
		 * compare the following code to return the names of dishes that are low in
		 * calories, sorted by number of calories, first in Java 7 and then in Java 8
		 * using streams
		 */

		// JAVA 7
		{
			/*
			 * Filter the elements using an accumulator.
			 */
			List<Dish> lowCalorieDishes = new ArrayList<>();
			{
				for (Dish dish : Inventory.MENU) {

					if (dish.getCalories() < 400) {
						lowCalorieDishes.add(dish);
					}

				} // For Loop
			}

			/*
			 * Sort the dishes with an anonymous class.
			 */
			Collections.sort(lowCalorieDishes, new Comparator<Dish>() {

				@Override
				public int compare(Dish d1, Dish d2) {
					return Integer.compare(d1.getCalories(), d2.getCalories());
				}
			});

			/*
			 * Process the sorted list to select the names of dishes.
			 */
			List<String> lowCaloricDishesName = new ArrayList<>();
			{
				for (Dish dish : lowCalorieDishes) {

					lowCaloricDishesName.add(dish.getName());
				}
			}

			System.out.println(lowCaloricDishesName);
		}

		// JAVA 8
		{

			/*
			 * You chain together several building-block operations to express a complicated
			 * data processing pipeline
			 * 
			 * (you chain the filter by linking sorted, map, and collect operations, as
			 * illustrated in figure 4.1) while keeping your code readable and its intent
			 * clear.
			 * 
			 * The result of the filter is passed to the sorted method, which is then passed
			 * to the map method and then to the collect method.
			 */
			// stream()
			List<String> lowCaloricDishesName = Inventory.MENU.stream()

					// Select dishes that are below 400 calories
					.filter(dish -> dish.getCalories() < 400)

					// Sort them by
					.sorted(Comparator.comparing(Dish::getCalories))

					// Extract the names calories
					.map(Dish::getName)

					// Store all the names in a List.
					.collect(Collectors.toList());

			System.out.println(lowCaloricDishesName);
		}

		{
			/*
			 * To exploit a multicore architecture and execute this code in parallel, you
			 * need only change stream() to parallelStream():
			 */
		}
	}
}
