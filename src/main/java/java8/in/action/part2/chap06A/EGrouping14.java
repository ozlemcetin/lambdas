package java8.in.action.part2.chap06A;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class EGrouping14 {

	public static void main(String[] args) throws Exception {

		// Collecting data in subgroups
		{

			/*
			 * In the previous section, you saw that it’s possible to pass a second
			 * groupingBy collector to the outer one to achieve a multilevel grouping.
			 * 
			 * But more generally, the second collector passed to the first groupingBy can
			 * be any type of collector, not just another groupingBy.
			 * 
			 * For instance, it’s possible to count the number of Dishes in the menu for
			 * each type, by passing the counting collector as a second argument to the
			 * groupingBy collector:
			 */

			Map<Dish.Type, Long> dishesByTypeCaloricLevel = Inventory.MENU.stream()

					.collect(Collectors.groupingBy(

							// First-level classification function
							Dish::getType,

							// Second-level classification function
							Collectors.counting()

			));

			System.out.println(dishesByTypeCaloricLevel);
		}

		{
			/*
			 * /* Also note that the regular one-argument groupingBy(f), where f is the
			 * classification function, is in reality just shorthand for groupingBy(f,
			 * toList()).
			 */

			Map<Dish.Type, List<Dish>> dishesByTypeCaloricLevel = Inventory.MENU.stream()

					.collect(Collectors.groupingBy(Dish::getType));

			System.out.println(dishesByTypeCaloricLevel);
		}

	}

}
