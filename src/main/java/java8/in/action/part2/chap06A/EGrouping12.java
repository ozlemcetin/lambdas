package java8.in.action.part2.chap06A;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Dish.CaloricLevel;
import java8.in.action.model.Inventory;

public class EGrouping12 {

	public static void main(String[] args) throws Exception {

		/*
		 * suppose you want to classify the dishes in the menu according to their type,
		 * putting the ones containing meat in a group, the ones with fish in another
		 * group, and all others in a third group. You can easily perform this task
		 * using a collector returned by the Collectors.groupingBy factory method as
		 * follows:
		 */
		{
			Map<Dish.Type, List<Dish>> map = Inventory.MENU.stream()

					.collect(Collectors.groupingBy(Dish::getType));

			System.out.println(map);
		}

		/*
		 * For instance, you could decide to classify as “diet” all dishes with 400
		 * calories or fewer, set to “normal” the dishes having between 400 and 700
		 * calories, and set to “fat” the ones with more than 700 calories.
		 * 
		 * you can’t use a method reference in this case, but you can express this logic
		 * in a lambda expression:
		 */
		{

			Function<Dish, String> classifier = new Function<Dish, String>() {

				@Override
				public String apply(Dish t) {

					if (t.getCalories() <= 400) {
						return "DIET";

					} else if (t.getCalories() <= 700) {
						return "NORMAL";

					} else {
						return "FAT";
					}
				}
			};

			Map<String, List<Dish>> dishesByCaloricLevel = Inventory.MENU.stream()

					.collect(Collectors.groupingBy(classifier));

			System.out.println(dishesByCaloricLevel);
		}

		{
			Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Inventory.MENU.stream()

					.collect(Collectors.groupingBy(

							dish -> {

								if (dish.getCalories() <= 400)
									return CaloricLevel.DIET;

								else if (dish.getCalories() <= 700)
									return CaloricLevel.NORMAL;

								else
									return CaloricLevel.FAT;
							}));

			System.out.println(dishesByCaloricLevel);
		}
	}

}
