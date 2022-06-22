package java8.in.action.part2.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class DFindingExample08 {

	public static void main(String[] args) throws Exception {

		// Finding an element
		{

			/*
			 * The findAny method returns an arbitrary element of the current stream. It can
			 * be used in conjunction with other stream operations.
			 * 
			 * For example, you may wish to find a dish that’s vegetarian. You can combine
			 * the filter method and findAny to express this query:
			 */

			Optional<Dish> dish = Inventory.MENU.stream()

					.filter(d -> d.isVegetarian())

					.findAny();

			/*
			 * If a value is contained, it’s printed; otherwise nothing happens.
			 */
			dish.ifPresent(d -> System.out.println(d));
		}

		// Finding the first element
		{
			/*
			 * Some streams have an encounter order that specifies the order in which items
			 * logically appear in the stream (for example, a stream generated from a List
			 * or from a sorted sequence of data). For such streams you may wish to find the
			 * first element. There’s the findFirst method for this, which works similarly
			 * to findAny.
			 * 
			 * For example, the code that follows, given a list of numbers, finds the first
			 * square that’s divisible by 3:
			 */

			List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);

			Optional<Integer> number = someNumbers.stream()

					.map(i -> i * i)

					.filter(i -> i % 3 == 0)

					.findFirst();

			number.ifPresent(i -> System.out.println(i));
		}

		/*
		 * When to use findFirst and findAny You may wonder why we have both findFirst
		 * and findAny.
		 * 
		 * The answer is parallelism. Finding the first element is more constraining in
		 * parallel. If you don’t care about which element is returned, use findAny
		 * because it’s less constraining when using parallel streams.
		 */
	}
}
