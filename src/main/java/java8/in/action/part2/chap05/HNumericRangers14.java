package java8.in.action.part2.chap05;

import java.util.stream.IntStream;

public class HNumericRangers14 {

	public static void main(String[] args) throws Exception {

		/*
		 * A common use case when dealing with numbers is working with ranges of numeric
		 * values. For example, suppose you’d like to generate all numbers between 1 and
		 * 100. Java 8 introduces two static methods available on IntStream and
		 * LongStream to help generate such ranges: range and rangeClosed.
		 * 
		 * Both methods take the starting value of the range as the first parameter and
		 * the end value of the range as the second parameter. But range is exclusive,
		 * whereas rangeClosed is inclusive. Let’s look at an example:
		 */

		// rangeClosed
		{
			/*
			 * A stream of even numbers from 1 to 100.
			 */

			// Represents the range [1, 100].
			IntStream evenNumbers = IntStream.rangeClosed(1, 100)

					.filter(n -> n % 2 == 0);

			/*
			 * Because count is a terminal operation, it will process the stream and return
			 * the result 50, which is the number of even numbers from 1 to 100, inclusive
			 * 
			 * There are 50 even numbers from 1 to 100.
			 */

			// Count: 50
			System.out.println("Count: " + evenNumbers.count());

			// OptionalInt[2]
			// System.out.println("Min: " + evenNumbers.min());

			// OptionalInt[100]
			// System.out.println("Max: " + evenNumbers.max());
		}

		// range
		{

			// Represents the range [1, 99].
			IntStream evenNumbers = IntStream.range(1, 100)

					.filter(n -> n % 2 == 0);

			// Count: 49
			System.out.println("Count: " + evenNumbers.count());

		}
	}
}
