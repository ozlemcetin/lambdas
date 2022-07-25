package java8.in.action.part2.chap06A;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class EGrouping17 {

	public static void main(String[] args) throws Exception {

		// OTHER EXAMPLES OF COLLECTORS USED IN CONJUNCTION WITH GROUPINGBY

		/*
		 * More generally, the collector passed as second argument to the groupingBy
		 * factory method will be used to perform a further reduction operation on all
		 * the elements in the stream classified into the same group.
		 * 
		 * For example, you could also reuse the collector created to sum the calories
		 * of all the dishes in the menu to obtain a similar result, but this time for
		 * each group of Dishes:
		 */
		{

			Long sum = Inventory.MENU.stream()

					.collect(Collectors.summingLong(Dish::getCalories));

			System.out.println(sum);
		}

		{

			Map<Dish.Type, List<Dish>> map = Inventory.MENU.stream()

					.collect(Collectors.groupingBy(Dish::getType));

			System.out.println(map);
		}

		{

			Map<Dish.Type, Long> map = Inventory.MENU.stream()

					.collect(Collectors.groupingBy(

							/*
							 * Classification function
							 * 
							 * The original stream is divided according to the classification function.
							 */
							Dish::getType,

							/*
							 * Each substream independently processed by the second collector.
							 */
							Collectors.summingLong(Dish::getCalories)));

			System.out.println(map);
		}
	}

}
