package java8.in.action.part2.chap05;

import java8.in.action.model.Inventory;

public class AFilteringExample01 {

	public static void main(String[] args) throws Exception {

		{
			/*
			 * Terminal operations produce a result from a stream pipeline.
			 * 
			 * A result is any nonstream value such as a List, an Integer, or even void.
			 * 
			 * For example, in the following pipeline, forEach is a terminal operation that
			 * returns void and applies a lambda to each dish in the source.
			 * 
			 * Passing System.out.println to forEach asks it to print every Dish in the
			 * stream created from menu:
			 */

			Inventory.MENU.stream().forEach(System.out::println);
		}

		//filter
		{
			/*
			 * The last operation in the stream pipeline count returns a long, which is a
			 * non- Stream value. It’s therefore a terminal operation.
			 * 
			 * All previous operations, filter, distinct, limit, are connected and return a
			 * Stream. They are therefore intermediate operations.
			 */

			long count = Inventory.MENU.stream()

					.filter(d -> d.getCalories() > 300)

					.distinct()
					
					.limit(3)
					
					.count();

			System.out.println(count);
		}
	}
}
