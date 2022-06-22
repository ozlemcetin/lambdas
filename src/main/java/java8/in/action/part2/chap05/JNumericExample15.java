package java8.in.action.part2.chap05;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JNumericExample15 {

	public static void main(String[] args) throws Exception {

		/*
		 * === create a stream of Pythagorean triples
		 * 
		 * the famous Greek mathematician Pythagoras discovered that certain triples of
		 * numbers (a, b, c) satisfy the formula a * a + b * b = c * c where a, b, and c
		 * are integers. For example, (3, 4, 5) is a valid Pythagorean triple because 3
		 * * 3 + 4 * 4 = 5 * 5 or 9 + 16 = 25
		 */

		// FILTERING GOOD COMBINATIONS
		{
			/*
			 * Let’s assume someone provides you with the first two numbers of the triple: a
			 * and b.
			 * 
			 * How do you know whether that will form a good combination?
			 * 
			 * You need to test whether the square root of a * a + b * b is an integer
			 * number; that is, it has no fractional part, which in Java can be expressed
			 * using expr % 1.0. If it's not an integer, that means c is not an integer.
			 * 
			 * You can express this requirement as a filter operation
			 * 
			 * stream.filter (b -> Math.sqrt(a*a + b*b) % 1 == 0)
			 * 
			 * It’s basically a way to test whether Math.sqrt(a*a + b*b) returns an integer
			 * result.
			 * 
			 * Assuming that surrounding code has given a value for a and assuming stream
			 * provides possible values for b, filter will select only those values for b
			 * that can form a Pythagorean triple with a. You
			 */

			/*
			 * // 2 System.out.println(5 % 3);
			 * 
			 * // 1 System.out.println(4 % 3);
			 * 
			 * // 0 System.out.println(3 % 3);
			 * 
			 * // 0 System.out.println(3 % 1);
			 * 
			 * // 0.0 System.out.println(3 % 1.0);
			 * 
			 * // 0.20000000000000018 System.out.println(3.2 % 1.0);
			 */
		}

		// GENERATING TUPLES
		{
			/*
			 * You can use the map operation to transform each element into a Pythagorean
			 * triple as follows:
			 */

			// stream

			// .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)

			// .map(b -> new int[] {a, b, (int) Math.sqrt(a*a + b*b) });
		}

		// GENERATING B VALUES
		{
			/*
			 * You saw that Stream .rangeClosed allows you to generate a stream of numbers
			 * in a given interval.
			 * 
			 * You can use it to provide numeric values for b, here 1 to 100:
			 */

			{
				/*
				 * Note that you call boxed after the filter to generate a Stream<Integer> from
				 * the IntStream returned by rangeClosed.
				 * 
				 * This is because your map returns an array of int for each element of the
				 * stream.
				 */
				final int a = 3;
				Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)

						.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)

						// boxed
						.boxed()

						.map(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) });

				// Stream<int[]> : [3,4,5]
				pythagoreanTriples.forEach((int[] arr) -> System.out
						.println("Stream<int[]> : [" + arr[0] + "," + arr[1] + "," + arr[2] + "]"));

			}

			{
				/*
				 * You can rewrite this using the method mapToObj of an IntStream, which returns
				 * an object-valued stream
				 */

				final int a = 5;
				Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)

						.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)

						// mapToObj
						.mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) });

				// Stream<int[]> : [5,12,13]
				pythagoreanTriples.forEach((int[] arr) -> System.out
						.println("Stream<int[]> : [" + arr[0] + "," + arr[1] + "," + arr[2] + "]"));
			}

		}

		// GENERATING A VALUES
		{
			/*
			 * You now have a stream that produces Pythagorean triples provided the value a
			 * is known.
			 * 
			 * How can you fix this? Just like with b, you need to generate numeric values
			 * for a! The final solution is as follows:
			 */

			/*
			 * map -> Stream<Stream<int[]>>
			 * 
			 * flatMap -> Stream<int[]>
			 */
			Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 20)

					.boxed()

					.flatMap(a -> IntStream.rangeClosed(a, 20)

							.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)

							// mapToObj
							.mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }));

			pythagoreanTriples
					.forEach((int[] arr) -> System.out.println("[" + arr[0] + "," + arr[1] + "," + arr[2] + "]"));
		}

		{
			/*
			 * Okay, what’s the flatMap about? First, you create a numeric range from 1 to
			 * 100 to generate values for a. For each given value of a you’re creating a
			 * stream of triples.
			 * 
			 * Mapping a value of a to a stream of triples would result in a stream of
			 * streams!
			 * 
			 * The flatMap method does the mapping and also flattens all the generated
			 * streams of triples into a single stream.
			 * 
			 * As a result you produce a stream of triples
			 * 
			 * Note also that you change the range of b to be a to 100.
			 * 
			 * There’s no need to start the range at the value 1 because this would create
			 * duplicate triples (for example, (3, 4, 5) and (4, 3, 5)).
			 */
		}

		{
			/*
			 * The current solution isn’t optimal because you calculate the square root
			 * twice.
			 * 
			 * One possible way to make your code more compact is to generate all triples of
			 * the form (a*a, b*b, a*a+b*b) and then filter the ones that match your
			 * criteria
			 */
			Stream<double[]> pythagoreanTriples2 =

					IntStream.rangeClosed(1, 20).boxed().flatMap(a ->

					IntStream.rangeClosed(a, 100)

							// Produce triples
							.mapToObj(b -> new double[] { a, b, Math.sqrt(a * a + b * b) })

							// The third element of the tuple must be an integer
							.filter(t -> t[2] % 1 == 0));
		}
	}
}
