package java8.in.action.part2.chap06A;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class BSummarization05 {

	public static void main(String[] args) throws Exception {

		/*
		 * Quite often, though, you may want to retrieve two or more of these results,
		 * and possibly you’d like to do it in a single operation.
		 * 
		 * In this case, you can use the collector returned by the summarizingInt
		 * factory method.
		 * 
		 * For example, you can count the elements in the menu and obtain the sum,
		 * average, maximum, and minimum of the calories contained in each dish with a
		 * single summarizing operation:
		 */

		IntSummaryStatistics menuStatistics = Inventory.MENU.stream()

				.collect(Collectors.summarizingInt(Dish::getCalories));

		/*
		 * This collector gathers all that information in a class called
		 * IntSummaryStatistics that provides convenient getter methods to access the
		 * results.
		 * 
		 * Printing the menu- Statistic object produces the following output:
		 * 
		 * IntSummaryStatistics{count=9, sum=4300, min=120, average=477.777778, max=800}
		 */

		System.out.println(menuStatistics);

		/*
		 * As usual, there are corresponding summarizingLong and summarizingDouble
		 * factory methods with associated types LongSummaryStatistics and
		 * DoubleSummaryStatistics; these are used when the property to be collected is
		 * a primitive-type long or a double.
		 */
	}

}
