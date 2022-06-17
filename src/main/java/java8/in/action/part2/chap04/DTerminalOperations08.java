package java8.in.action.part2.chap04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class DTerminalOperations08 {

	public static void main(String[] args) throws Exception {

		{
			List<Dish> vegetarianDishes = new ArrayList<>();
			{
				for (Dish dish : Inventory.MENU) {

					if (dish.isVegetarian()) {
						vegetarianDishes.add(dish);
					}
				}
			}

			System.out.println(vegetarianDishes);

		}

		/*
		 * The Streams interface supports a filter method (which you should be familiar
		 * with by now).
		 * 
		 * This operation takes as argument a predicate (a function returning a boolean)
		 * and returns a stream including all elements that match the predicate.
		 * 
		 * For example, you can create a vegetarian menu by filtering all vegetarian
		 * dishes as follows
		 */
		{

			/*
			 * This different way of working with data is useful because you let the Streams
			 * API manage how to process the data.
			 * 
			 * As a consequence, the Streams API can work out several optimizations behind
			 * the scenes. In addition, using internal iteration, the Streams API can decide
			 * to run your code in parallel.
			 * 
			 * Using external iteration, this isn’t possible because you’re committed to a
			 * single-threaded step-by-step sequential iteration.
			 */
			List<Dish> vegetarianDishes = Inventory.MENU.stream()

					// A method reference to check if a dish is vegetarian friendly
					.filter(Dish::isVegetarian)

					.collect(Collectors.toList());

			System.out.println(vegetarianDishes);

		}
	}
}
