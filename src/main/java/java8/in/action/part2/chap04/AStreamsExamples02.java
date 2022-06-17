package java8.in.action.part2.chap04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class AStreamsExamples02 {

	public static void main(String[] args) throws Exception {

		/*
		 * Grouping Menu Dishes by Type
		 */

		// JAVA 7
		{
			Map<Dish.Type, List<Dish>> dishesByType = new HashMap<>();

			for (Dish dish : Inventory.MENU) {

				Dish.Type key = dish.getType();

				List<Dish> list = dishesByType.get(key);
				{
					if (list == null) {

						list = new ArrayList<>();

						dishesByType.put(key, list);
					}

				}

				list.add(dish);

			} // For Loop

			System.out.println(dishesByType);

		}

		// JAVA 8
		{
			/*
			 * To summarize, the Streams API in Java 8 lets you write code that’s
			 * 
			 * Declarative—More concise and readable
			 * 
			 * Composable—Greater flexibility
			 * 
			 * 
			 * Parallelizable—Better performance
			 */

			Map<Dish.Type, List<Dish>> dishesByType = Inventory.MENU.stream()

					.collect(Collectors.groupingBy(Dish::getType));

			System.out.println(dishesByType);

		}

	}
}
