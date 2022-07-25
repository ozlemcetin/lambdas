package java8.in.action.part2.chap06A;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Dish.CaloricLevel;
import java8.in.action.model.Inventory;

public class EGrouping13 {

	public static void main(String[] args) throws Exception {

		// Multilevel grouping
		{

			/*
			 * You can achieve multilevel grouping by using a collector created with a
			 * two-argument version of the Collectors.groupingBy factory method, which
			 * accepts a second argument of type collector besides the usual classification
			 * function.
			 * 
			 * So to perform a two-level grouping, you can pass an inner groupingBy to the
			 * outer groupingBy, defining a second-level criterion to classify the stream’s
			 * items, as shown in the next listing.
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

			Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = Inventory.MENU.stream()

					.collect(Collectors.groupingBy(

							// First-level classification function
							Dish::getType,

							// Second-level classification function
							Collectors.groupingBy(classifier)

			));

			System.out.println(dishesByTypeCaloricLevel);
		}

		/*
		 * Here the outer Map has as keys the values generated by the first-level
		 * classification function: “fish, meat, other.”
		 * 
		 * The values of this Map are in turn other Maps, having as keys the values
		 * generated by the second-level classification function: “normal, diet, or
		 * fat.”
		 * 
		 * Finally, the second-level Maps have as values the List of the elements in the
		 * stream returning the corresponding first- and second-level key values when
		 * applied respectively to the first and second classification functions:
		 * “salmon, pizza, etc.”
		 * 
		 * This multilevel grouping operation can be extended to any number of levels,
		 * and an n-level grouping has as a result an n-level Map modeling an n-level
		 * tree structure.
		 */

		/*
		 * In general, it helps to think that groupingBy works in terms of “buckets.”
		 *
		 * The first groupingBy creates a bucket for each key.
		 * 
		 * You then collect the elements in each bucket with the downstream collector
		 * and so on to achieve n-level groupings!
		 */
		{
			/*
			 * {FISH=
			 * 
			 * {DIET=[Dish [name=prawns, vegetarian=false, calories=400, type=FISH]],
			 * 
			 * NORMAL=[Dish [name=salmon, vegetarian=false, calories=450, type=FISH]]},
			 * 
			 * 
			 * MEAT=
			 * 
			 * {DIET=[Dish [name=chicken, vegetarian=false, calories=400, type=MEAT]],
			 * 
			 * NORMAL=[Dish [name=beef, vegetarian=false, calories=700, type=MEAT]],
			 * 
			 * FAT=[Dish [name=pork, vegetarian=false, calories=800, type=MEAT]]},
			 * 
			 * OTHER=
			 * 
			 * {DIET=[Dish [name=rice, vegetarian=true, calories=350, type=OTHER], Dish
			 * [name=season fruit, vegetarian=true, calories=120, type=OTHER]],
			 * 
			 * NORMAL=[Dish [name=french fries, vegetarian=true, calories=530, type=OTHER],
			 * Dish [name=pizza, vegetarian=true, calories=550, type=OTHER]]}}
			 */
		}
	}

}
