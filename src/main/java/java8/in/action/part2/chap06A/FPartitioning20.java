package java8.in.action.part2.chap06A;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class FPartitioning20 {

	public static void main(String[] args) throws Exception {

		// Advantages of partitioning

		/*
		 * as you already saw for grouping, the partitioningBy factory method has an
		 * overloaded version to which you can pass a second collector, as shown here:
		 */

		{
			Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = Inventory.MENU.stream()

					.collect(Collectors.partitioningBy(

							// Partitioning function
							Dish::isVegetarian,

							// Second collector
							Collectors.groupingBy(Dish::getType)));

			System.out.println(vegetarianDishesByType);
		}

		/*
		 * Here the grouping of the dishes by their type is applied individually to both
		 * of the substreams of vegetarian and nonvegetarian dishes resulting from the
		 * partitioning, producing a two-level Map that’s similar to the one you
		 * obtained when you performed the two-level grouping in section 6.3.1
		 */
		{
			/*
			 * {false={FISH=[Dish [name=prawns, vegetarian=false, calories=400, type=FISH],
			 * Dish [name=salmon, vegetarian=false, calories=450, type=FISH]],
			 * 
			 * MEAT=[Dish [name=pork, vegetarian=false, calories=800, type=MEAT], Dish
			 * [name=beef, vegetarian=false, calories=700, type=MEAT], Dish [name=chicken,
			 * vegetarian=false, calories=400, type=MEAT]]},
			 * 
			 * 
			 * true={OTHER=[Dish [name=french fries, vegetarian=true, calories=530,
			 * type=OTHER], Dish [name=rice, vegetarian=true, calories=350, type=OTHER],
			 * Dish [name=season fruit, vegetarian=true, calories=120, type=OTHER], Dish
			 * [name=pizza, vegetarian=true, calories=550, type=OTHER]]}}
			 */
		}
	}

}
