package java8.in.action.part2.chap04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class CStreamsIteration06 {

	public static void main(String[] args) throws Exception {

		// Collections: external iteration
		{
			List<String> names = new ArrayList<>();
			{
				/*
				 * Explicitly iterate the list of menu sequentially.
				 */
				for (Iterator<Dish> iterator = Inventory.MENU.iterator(); iterator.hasNext();) {

					Dish dish = (Dish) iterator.next();

					/*
					 * Extract the name and add it to an accumulator.
					 */
					names.add(dish.getName());

				} // For Loops
			}

			System.out.println(names);
		}

		// Streams: internal iteration
		{

			/*
			 * Parameterize map with the getName method to extract the name of a dish. Start
			 * executing the pipeline of operations; no iteration!
			 */

			/*
			 * In the same way, using an internal iteration, the processing of items could
			 * be transparently done in parallel or in a different order that may be more
			 * optimized.
			 */

			/*
			 * Streams library can automatically choose a data representation and
			 * implementation of parallelism to match your hardware. By contrast, once
			 * you’ve chosen external iteration by writing for-each, then you’ve essentially
			 * committed to self-manage any parallelism.
			 */
			List<String> names = Inventory.MENU.stream()

					.map(Dish::getName)

					.collect(Collectors.toList());

			System.out.println(names);

		}

	}
}
