package java8.in.action.part2.chap06A;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class EGrouping16 {

	public static void main(String[] args) throws Exception {

		// ADAPTING THE COLLECTOR RESULT TO A DIFFERENT TYPE

		{

			Map<Dish.Type, Optional<Dish>> mostCaloricByType = Inventory.MENU.stream()

					.collect(Collectors.groupingBy(Dish::getType,

							Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));

			System.out.println(mostCaloricByType);
		}

		/*
		 * Because the Optionals wrapping all the values in the Map resulting from the
		 * last grouping operation aren’t very useful in this case, you may want to get
		 * rid of them.
		 * 
		 * To achieve this, or more generally, to adapt the result returned by a
		 * collector to a different type, you could use the collector returned by the
		 * Collectors.collectingAndThen factory method, as shown in the following
		 * listing.
		 */
		{

			/*
			 * groupingBy is the outermost collector and groups the menu stream into three
			 * substreams according to the different dishes’ types
			 */

			/*
			 * The groupingBy collector wraps the collectingAndThen collector, so each
			 * substream resulting from the grouping operation is further reduced by this
			 * second collector
			 */

			/*
			 * The collectingAndThen collector wraps in turn a third collector, the maxBy
			 * one.
			 */

			/*
			 * The reduction operation on the substreams is then performed by the reducing
			 * collector, but the collectingAndThen collector containing it applies the
			 * Optional::get transformation function to its result.
			 */

			/*
			 * The three transformed values, being the highest-calorie Dishes for a given
			 * type (resulting from the execution of this process on each of the three
			 * substreams), will be the values associated with the respective classification
			 * keys, the types of Dishes, in the Map returned by the groupingBy collector
			 */
			Map<Dish.Type, Dish> mostCaloricByType = Inventory.MENU.stream()

					.collect(

							/*
							 * Classification function
							 * 
							 * The original stream is divided according to the classification function.
							 */
							Collectors.groupingBy(Dish::getType,

									/*
									 * Each substream independently processed by the second collector
									 */
									Collectors.collectingAndThen(

											/*
											 * Wrapped collector
											 * 
											 * The reducing collector -maxBy- returns the most caloric dish wrapped in
											 * an Optional.
											 */
											Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),

											/*
											 * Transformation function
											 * 
											 * collectingAndThen collector returns the value extracted from the former
											 * Optional.
											 */
											Optional::get)

							));

			System.out.println(mostCaloricByType);
		}
	}

}
