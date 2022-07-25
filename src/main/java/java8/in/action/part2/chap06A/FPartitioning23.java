package java8.in.action.part2.chap06A;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FPartitioning23 {

	public static void main(String[] args) throws Exception {

		// Partitioning numbers into prime and nonprime

		/*
		 * Suppose you want to write a method accepting as argument an int n and
		 * partitioning the first n natural numbers into prime and nonprime.
		 * 
		 * But first, it will be useful to develop a predicate that tests to see if a
		 * given candidate number is prime or not:
		 */

		/*
		 * {false=[4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21],
		 * 
		 * true=[1, 2, 3, 5, 7, 11, 13, 17, 19]}
		 * 
		 */
		System.out.println(partitioningNumbersIntoPrime(21));

		System.out.println(partitioningNumbersIntoPrime(51));
		
		
	}

	// Partitioning numbers into prime and nonprime
	public static Map<Boolean, List<Integer>> partitioningNumbersIntoPrime(int n) {

		/*
		 * To partition the first n numbers into prime and nonprime, it’s enough to
		 * create a stream containing those n numbers and reduce it with a
		 * partitioningBy collector using as predicate the isPrime method you just
		 * developed:
		 */
		List<Integer> list = null;
		{
			list = IntStream.rangeClosed(1, n)

					// Stream<Integer>
					.boxed()

					.collect(Collectors.toList());

		}

		Map<Boolean, List<Integer>> map = null;
		{
			map = list.stream()

					.collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
		}

		return map;
	}

	public static boolean isPrime(int candidate) {

		/*
		 * Generate a range of natural numbers starting from and including 2 up to but
		 * excluding candidate.
		 */

		return IntStream.range(2, candidate)
				/*
				 * Return true if the candidate isn’t divisible for any of the numbers in the
				 * stream.
				 */
				.noneMatch(i -> candidate % i == 0);
	}

}
