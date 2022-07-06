package java8.in.action.part2.chap06;

import java.util.stream.Collectors;

import java8.in.action.model.Inventory;

public class ACollectorsExample02 {

	public static void main(String[] args) throws Exception {

		/*
		 * As you just learned, collectors (the parameters to the Stream method collect)
		 * are typically used in cases where it’s necessary to reorganize the stream’s
		 * items into a collection.
		 * 
		 * But more generally, they can be used every time you want to combine all the
		 * items in the stream into a single result. This result can be of any type, as
		 * complex as a multilevel map representing a tree or as simple as a single
		 * integer—perhaps representing the sum of all the calories in the menu.
		 * 
		 * let’s count the number of dishes in the menu, using the collector returned by
		 * the counting factory method:
		 */

		// counting
		{
			long howManyDishes = Inventory.MENU.stream()

					.collect(Collectors.counting());

			System.out.println("howManyDishes: " + howManyDishes);

		}

		// count
		{
			long howManyDishes = Inventory.MENU.stream().count();

			System.out.println("howManyDishes: " + howManyDishes);

		}
	}
}
