package java8.in.action.part2.chap07;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class AParallelStreams01 {

	public static void main(String[] args) throws Exception {

		/*
		 * Let’s suppose you need to write a method accepting a number n as argument and
		 * returning the sum of all the numbers from 1 to the given argument
		 */
		{
			System.out.println(iterativeSum(5));

			System.out.println(sequentialSum(5));
		}

		// Turning a sequential stream into a parallel one
		{

			/*
			 * You can make the former functional reduction process (that is, summing) run
			 * in parallel by turning the stream into a parallel one; call the method
			 * parallel on the sequential stream:
			 */

			System.out.println(parallelSum(5));
		}
	}

	/*
	 * In more traditional Java terms, this code is equivalent to its iterative
	 * counterpart:
	 */

	// iterative style
	public static long iterativeSum(long n) {

		long sum = 0;
		{
			for (long i = 1L; i <= n; i++) {
				sum += i;
			}
		}

		return sum;
	}

	/*
	 * A straightforward (perhaps naïve) approach is to generate an infinite stream
	 * of numbers, limiting it to the passed number, and then reduce the resulting
	 * stream with a BinaryOperator that just sums two numbers, as follows:
	 */

	// sequential reduction
	public static long sequentialSum(long n) {

		long sum = 0;
		{

			// Generate the infinite strea of natural numbers.
			sum = Stream.iterate(1L, i -> i + 1)

					// Limit it to the first n numbers.
					.limit(n)

					// Reduce the stream by summing all the numbers.
					.reduce(0L, Long::sum);
		}

		return sum;
	}

	// parallel reduction
	public static long parallelSum(long n) {

		/*
		 * Stream is internally divided into multiple chunks. As a result, the reduction
		 * operation can work on the various chunks independently and in parallel, as
		 */
		return Stream.iterate(1L, i -> i + 1)

				.limit(n)

				/*
				 * Turn the stream into a parallel one
				 */
				.parallel()

				.reduce(0L, Long::sum);
	}

	/*
	 * The numeric stream is much faster than the earlier sequential version,
	 * generated with the iterate factory method, because the numeric stream avoids
	 * all the overhead caused by all the unnecessary autoboxing and unboxing
	 * operations performed by the nonspecialized stream. This is evidence that
	 * choosing the right data structures is often more important than parallelizing
	 * the algorithm that uses them
	 */
	public static long rangedSum(long n) {

		return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
	}

	public static long parallelRangedSum(long n) {
		
		return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
	}
}
