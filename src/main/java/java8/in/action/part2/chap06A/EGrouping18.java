package java8.in.action.part2.chap06A;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Dish.CaloricLevel;
import java8.in.action.model.Inventory;

public class EGrouping18 {

	public static void main(String[] args) throws Exception {

		// OTHER EXAMPLES OF COLLECTORS USED IN CONJUNCTION WITH GROUPINGBY

		/*
		 * Yet another collector, commonly used in conjunction with groupingBy, is one
		 * generated by the mapping method.
		 * 
		 * This method takes two arguments: a function transforming the elements in a
		 * stream and a further collector accumulating the objects resulting from this
		 * transformation.
		 * 
		 * 
		 * Its purpose is to adapt a collector accepting elements of a given type to one
		 * working on objects of a different type, by applying a mapping function to
		 * each input element before accumulating them.
		 * 
		 * 
		 * To see a practical example of using this collector, suppose you want to know
		 * which CaloricLevels are available in the menu for each type of Dish.
		 * 
		 * You could achieve this result combining a groupingBy and a mapping collector
		 * as follows:
		 */

		// which CaloricLevels are available in the menu for each type of Dish.
		{

			/*
			 * Here the transformation function passed to the mapping method maps a Dish
			 * into its CaloricLevel, as you’ve seen before
			 */
			Function<Dish, CaloricLevel> classifier = new Function<Dish, CaloricLevel>() {

				@Override
				public CaloricLevel apply(Dish dish) {

					if (dish.getCalories() <= 400)
						return CaloricLevel.DIET;

					else if (dish.getCalories() <= 700)
						return CaloricLevel.NORMAL;

					else
						return CaloricLevel.FAT;
				}
			};

			{

				/*
				 * The resulting stream of CaloricLevels is then passed to a toSet collector,
				 * analogous to the toList one, but accumulating the elements of a stream into a
				 * Set instead of into a List, to keep only the distinct values
				 */

				/*
				 * As in earlier examples, this mapping collector will then be used to collect
				 * the elements in each substream generated by the grouping function, allowing
				 * you to obtain as a result the following Map:
				 */

				/*
				 * {MEAT=[DIET, NORMAL, FAT],
				 * 
				 * OTHER=[DIET, NORMAL],
				 * 
				 * FISH=[DIET, NORMAL]}
				 * 
				 */
				Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelsByType = Inventory.MENU.stream()

						.collect(Collectors.groupingBy(Dish::getType,

								Collectors.mapping(classifier, Collectors.toSet())));

				System.out.println(caloricLevelsByType);
			}

			/*
			 * Note that in the previous example, there are no guarantees about what type of
			 * Set is returned. But by using toCollection, you can have more control. For
			 * example, you can ask for a HashSet by passing a constructor reference to it:
			 */

			{
				Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelsByType =

						Inventory.MENU.stream()

								.collect(Collectors.groupingBy(Dish::getType,

										Collectors.mapping(classifier, Collectors.toCollection(HashSet::new))));

				System.out.println(caloricLevelsByType);
			}

		}
	}

}
