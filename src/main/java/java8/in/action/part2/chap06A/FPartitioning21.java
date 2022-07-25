package java8.in.action.part2.chap06A;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class FPartitioning21 {

	public static void main(String[] args) throws Exception {

		/*
		 * As another example, you can reuse your earlier code to find the most caloric
		 * dish among both vegetarian and nonvegetarian dishes:
		 */

		{
			Map<Boolean, Optional<Dish>> vegetarianDishesByType = Inventory.MENU.stream()

					.collect(Collectors.partitioningBy(Dish::isVegetarian,

							Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));

			System.out.println(vegetarianDishesByType);
		}

		{
			/*
			 * {false=Optional[Dish [name=pork, vegetarian=false, calories=800, type=MEAT]],
			 * 
			 * true=Optional[Dish [name=pizza, vegetarian=true, calories=550, type=OTHER]]}
			 */
		}

		// collectingAndThen
		{
			Map<Boolean, Dish> vegetarianDishesByType = Inventory.MENU.stream()

					.collect(Collectors.partitioningBy(Dish::isVegetarian,

							Collectors.collectingAndThen(

									Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),

									Optional::get)

			));

			System.out.println(vegetarianDishesByType);
		}
	}

}
