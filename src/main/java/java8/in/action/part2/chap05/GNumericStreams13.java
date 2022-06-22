package java8.in.action.part2.chap05;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class GNumericStreams13 {

	public static void main(String[] args) throws Exception {

		/*
		 * You saw earlier that you could use the reduce method to calculate the sum of
		 * the elements of a stream.
		 * 
		 * For example, you can calculate the number of calories in the menu as follows:
		 */
		{
			/*
			 * The problem with this code is that there’s an insidious boxing cost. Behind
			 * the scenes each Integer needs to be unboxed to a primitive before performing
			 * the summation
			 */

			/*
			 * the method map generates a Stream<T>. Even though the elements of the stream
			 * are of type Integer, the Streams interface doesn’t define a sum method
			 */
			int calories = Inventory.MENU.stream()

					.map(Dish::getCalories)

					.reduce(0, Integer::sum);

			// 4300
			System.out.println(calories);

		}

		/*
		 * Java 8 introduces three primitive specialized stream interfaces to tackle
		 * this issue, Int- Stream, DoubleStream, and LongStream, that respectively
		 * specialize the elements of a stream to be int, long, and double—and thereby
		 * avoid hidden boxing costs.
		 * 
		 * Each of these interfaces brings new methods to perform common numeric
		 * reductions such as sum to calculate the sum of a numeric stream and max to
		 * find the maximum element.
		 * 
		 * In addition, they have methods to convert back to a stream of objects when
		 * necessary. T
		 * 
		 * he thing to remember is that these specializations aren’t more complexity
		 * about streams but instead more complexity caused by boxing—the
		 * (efficiency-based) difference between int and Integer and so on.
		 */

		{
			int calories =

					// Returns a Stream<Dish>
					Inventory.MENU.stream()

							/*
							 * returns an IntStream as the result (rather than a Stream<Integer>).
							 */
							.mapToInt(Dish::getCalories)

							/*
							 * You can then call the sum method defined on the IntStream interface to
							 * calculate the sum of calories!
							 * 
							 * Note that if the stream were empty, sum would return 0 by default.
							 * 
							 * IntStream also supports other convenience methods such as max, min, and
							 * average.
							 */
							.sum();

			// 4300
			System.out.println(calories);
		}

		// boxed
		{
			// Converting a Stream to a numeric stream
			IntStream intStream = Inventory.MENU.stream().mapToInt(Dish::getCalories);

			/*
			 * Converting the numeric stream to a Stream
			 * 
			 * To access the operations defined in the Streams interface that are more
			 * general
			 */
			Stream<Integer> stream = intStream.boxed();
		}

		// max
		{
			/*
			 * For example, you can find the maximal element of an IntStream by calling the
			 * max method, which returns an OptionalInt
			 */

			OptionalInt maxCalories = Inventory.MENU.stream()

					.mapToInt(Dish::getCalories)

					.max();

			/*
			 * You can now process the OptionalInt explicitly to define a default value if
			 * there’s no maximum.
			 * 
			 * Provide an explicit default maximum if there’s no value.
			 */

			int max = maxCalories.orElse(-1);

			System.out.println("Max: " + max);
		}
	}
}
