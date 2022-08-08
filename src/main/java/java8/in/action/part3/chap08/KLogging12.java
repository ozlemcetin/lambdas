package java8.in.action.part3.chap08;

import java.util.Arrays;
import java.util.List;

public class KLogging12 {

	public static void main(String[] args) throws Exception {

		List<Integer> numbers = Arrays.asList(2, 3, 4, 5);

		/*
		 * Unfortunately, once you call forEach, the whole stream is consumed
		 */
		if (false) {

			numbers.stream()

					// 19 20 21 22
					.map(x -> x + 17)

					// 20 22
					.filter(x -> x % 2 == 0)

					// 20
					.limit(1)

					.forEach(System.out::println);

		}

		// peek
		{

			numbers.stream()

					/*
					 * Print the current element consumed from the source.
					 */
					.peek(x -> System.out.println("from stream: " + x))

					.map(x -> x + 17)

					/*
					 * Print the result of the map operation.
					 */
					.peek(x -> System.out.println("after map: " + x))

					.filter(x -> x % 2 == 0)

					/*
					 * Print the number selected after the filter operation.
					 */
					.peek(x -> System.out.println("after filter: " + x))

					.limit(1)

					/*
					 * Print the number selected after the limit operation.
					 */
					.peek(x -> System.out.println("after limit: " + x))

					.forEach(System.out::println);
		}
	}

}
