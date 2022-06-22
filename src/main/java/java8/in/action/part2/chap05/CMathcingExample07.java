package java8.in.action.part2.chap05;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class CMathcingExample07 {

	public static void main(String[] args) throws Exception {

		/*
		 * Another common data processing idiom is finding whether some elements in a
		 * set of data match a given property.
		 * 
		 * The Streams API provides such facilities through the allMatch, anyMatch,
		 * noneMatch, findFirst, and findAny methods of a stream
		 */

		// Checking to see if a predicate matches at least one element
		{
			/*
			 * The anyMatch method can be used to answer the question “Is there an element
			 * in the stream matching the given predicate?”
			 * 
			 * For example, you can use it to find out whether the menu has a vegetarian
			 * option:
			 */

			/*
			 * The anyMatch method returns a boolean and is therefore a terminal operation.c
			 */
			if (Inventory.MENU.stream().anyMatch(Dish::isVegetarian)) {

				System.out.println("The menu is (somewhat) vegetarian friendly!!");
			}

		}

		// Checking to see if a predicate matches all elements
		{

			/*
			 * The allMatch method works similarly to anyMatch but will check to see if all
			 * the elements of the stream match the given predicate. For example, you can
			 * use it to find out whether the menu is healthy (that is, all dishes are below
			 * 1000 calories):
			 */

			boolean isHealthy = Inventory.MENU.stream()

					.allMatch(d -> d.getCalories() < 1000);

			System.out.println(isHealthy);
		}

		// NONEMATCH
		{
			/*
			 * The opposite of allMatch is noneMatch. It ensures that no elements in the
			 * stream match the given predicate.
			 * 
			 * For example, you could rewrite the previous example as follows using
			 * noneMatch
			 */

			boolean isHealthy = Inventory.MENU.stream()

					.noneMatch(d -> d.getCalories() >= 1000);

			System.out.println(isHealthy);
		}

	}
}
