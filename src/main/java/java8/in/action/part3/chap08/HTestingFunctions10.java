package java8.in.action.part3.chap08;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

public class HTestingFunctions10 {

	public static void main(String[] args) throws Exception {

	}

	/*
	 * Methods that take a function as argument or return another function
	 * (so-called higher-order functions, explained more in chapter 14) are a little
	 * harder to deal with.
	 * 
	 * One thing you can do if a method takes a lambda as argument is test its
	 * behavior with different lambdas. For example, you can test the filter method
	 * created in chapter 2 with different predicates:
	 */

	@Test
	public void testFilter() throws Exception {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		{
			List<Integer> even = filter(numbers, i -> i % 2 == 0);

			assertEquals(Arrays.asList(2, 4), even);
		}

		{
			List<Integer> smallerThanThree = filter(numbers, i -> i < 3);

			assertEquals(Arrays.asList(1, 2), smallerThanThree);
		}
	}

	/*
	 * You can now use the method filter with a List of bananas, oranges, Integers,
	 * or Strings!
	 */

	// Introducing a type parameter T
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {

		return list.stream()

				.filter(p)

				.collect(Collectors.toList());

	}
}
