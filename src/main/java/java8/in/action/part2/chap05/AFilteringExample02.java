package java8.in.action.part2.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class AFilteringExample02 {

	public static void main(String[] args) throws Exception {

		// filter

		// distinct
		{

			/*
			 * Streams also support a method called distinct that returns a stream with
			 * unique elements (according to the implementation of the hashCode and equals
			 * methods of the objects produced by the stream).
			 * 
			 * For example, the following code filters all even numbers from a list and
			 * makes sure that there are no duplicates
			 */

			List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4, 4, 4);

			numbers.stream()

					// 2, 2, 4, 4, 4
					.filter(i -> i % 2 == 0)

					// 2, 4
					.distinct()

					.forEach(System.out::println);
		}

		// limit
		{
			/*
			 * Streams support the limit(n) method, which returns another stream that’s no
			 * longer than a given size. The requested size is passed as argument to limit.
			 * If the stream is ordered, the first elements are returned up to a maximum of
			 * n.
			 * 
			 * For example, you can create a List by selecting the first three dishes that
			 * have more than 300 calories as follows:
			 */

			List<Dish> dishes = Inventory.MENU.stream()

					.filter(d -> d.getCalories() > 300)

					.limit(2)

					.collect(Collectors.toList());

			System.out.println(dishes);
		}

		// skip
		{
			/*
			 * Streams support the skip(n) method to return a stream that discards the first
			 * n elements.
			 * 
			 * If the stream has fewer elements than n, then an empty stream is returned.
			 * 
			 * Note that limit(n) and skip(n) are complementary!
			 * 
			 * For example, the following code skips the first two dishes that have more
			 * than 300 calories and returns the rest.
			 */
			
			List<Dish> dishes = Inventory.MENU.stream()

					.filter(d -> d.getCalories() > 300)

					.skip(2)

					.collect(Collectors.toList());

			System.out.println(dishes);
		}
	}
}
