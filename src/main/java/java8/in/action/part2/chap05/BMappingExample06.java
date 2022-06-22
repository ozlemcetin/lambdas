package java8.in.action.part2.chap05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BMappingExample06 {

	public static void main(String[] args) throws Exception {

		/*
		 * Given a list of numbers, how would you return a list of the square of each
		 * number?
		 * 
		 * For example, given [1, 2, 3, 4, 5] you should return [1, 4, 9, 16, 25].
		 */
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		{
			/*
			 * You can solve this problem by using map with a lambda that takes a number and
			 * returns the square of the number:
			 */

			List<Integer> squares = numbers.stream()

					.map(i -> i * i)

					.collect(Collectors.toList());

			System.out.println(squares);

		}

		/*
		 * Given two lists of numbers, how would you return all pairs of numbers?
		 * 
		 * For example, given a list [1, 2, 3] and a list [3, 4] you should return [(1,
		 * 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)].
		 * 
		 * For simplicity, you can represent a pair as an array with two elements
		 */

		List<Integer> list1 = Arrays.asList(1, 2, 3);

		List<Integer> list2 = Arrays.asList(3, 4);

		{
			// JAVA 7
			{
				List<List<Integer>> results = new ArrayList<>();
				{

					for (Integer l1 : list1) {

						for (Integer l2 : list2) {

							results.add(Arrays.asList(l1, l2));

						} // for loop

					} // for loop
				}

				System.out.println(results);
			}

			// JAVA 8
			{
				// [java.util.stream.ReferencePipeline$3@abc44514,
				// java.util.stream.ReferencePipeline$3@56f6b064,
				// java.util.stream.ReferencePipeline$3@d8a242b3]
				{
					List<Stream<List<Integer>>> stream = list1.stream()

							.map(i ->

							list2.stream().map(j -> Arrays.asList(j, i)))

							.collect(Collectors.toList());

					System.out.println(stream);

				}

				// flatMap
				{
					List<List<Integer>> results = list1.stream()

							.flatMap(i ->

							list2.stream().map(j -> Arrays.asList(i, j)))

							.collect(Collectors.toList());

					// [[1, 3], [1, 4], [2, 3], [2, 4], [3, 3], [3, 4]]
					System.out.println(results);

				}
			}
		}

		/*
		 * How would you extend the previous example to return only pairs whose sum is
		 * divisible by 3? For example, (2, 4) and (3, 3) are valid.
		 */

		{

			/*
			 * You saw earlier that filter can be used with a predicate to filter elements
			 * from a stream. Because after the flatMap operation you have a stream of int[]
			 * that represent a pair, you just need a predicate to check to see if the sum
			 * is divisible by 3:
			 */
			List<List<Integer>> results = list1.stream()

					.flatMap(i ->

					list2.stream()

							.filter(j -> (i + j) % 3 == 0)

							.map(j -> Arrays.asList(i, j)))

					.collect(Collectors.toList());

			// [[2, 4], [3, 3]]
			System.out.println(results);
		}
	}
}
