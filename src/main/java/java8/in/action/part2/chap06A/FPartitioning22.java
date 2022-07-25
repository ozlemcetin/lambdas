package java8.in.action.part2.chap06A;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class FPartitioning22 {

	public static void main(String[] args) throws Exception {

		// Using partitioningBy

		/*
		 * As you’ve seen, like the groupingBy collector, the partitioningBy collector
		 * can be used in combination with other collectors. In particular it could be
		 * used with a second partitioningBy collector to achieve a multilevel
		 * partitioning.
		 */

		{
			Map<Boolean, Map<Boolean, List<Dish>>> map = Inventory.MENU.stream()

					.collect(Collectors.partitioningBy(Dish::isVegetarian,

							Collectors.partitioningBy(dish -> dish.getCalories() > 500)));

			System.out.println(map);

			/*
			 * {false={
			 * 
			 * false=[Dish [name=chicken, vegetarian=false, calories=400, type=MEAT],
			 * 
			 * Dish [name=prawns, vegetarian=false, calories=400, type=FISH],
			 * 
			 * Dish [name=salmon, vegetarian=false, calories=450, type=FISH]],
			 * 
			 * true=[Dish [name=pork, vegetarian=false, calories=800, type=MEAT],
			 * 
			 * Dish [name=beef, vegetarian=false, calories=700, type=MEAT]]},
			 * 
			 * true={
			 * 
			 * false=[Dish [name=rice, vegetarian=true, calories=350, type=OTHER],
			 * 
			 * Dish [name=season fruit, vegetarian=true, calories=120, type=OTHER]],
			 * 
			 * true=[Dish [name=french fries, vegetarian=true, calories=530, type=OTHER],
			 * 
			 * Dish [name=pizza, vegetarian=true, calories=550, type=OTHER]]}}
			 */
		}

		{
			Map<Boolean, Long> map = Inventory.MENU.stream()

					.collect(Collectors.partitioningBy(Dish::isVegetarian,

							Collectors.counting()));

			System.out.println(map);

			/*
			 * {false=5, true=4}
			 */
		}
	}

}
