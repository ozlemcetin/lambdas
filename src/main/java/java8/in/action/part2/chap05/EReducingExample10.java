package java8.in.action.part2.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EReducingExample10 {

	public static void main(String[] args) throws Exception {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		// MAXIMA
		{
			/*
			 * The lambda is applied step by step to each element of the stream with the
			 * addition operator.
			 * 
			 * So you need a lambda that, given two elements, returns the maximum of them.
			 * 
			 * The reduce operation will use the new value with the next element of the
			 * stream to produce a new maximum until the whole stream is consumed!
			 * 
			 * You can use reduce as follows to calculate the maximum in a stream
			 */
			Optional<Integer> max = numbers.stream().reduce(Integer::max);

			System.out.println("Max: " + max);
		}

		// MINIMA
		{
			Optional<Integer> min = numbers.stream().reduce(Integer::min);

			System.out.println("Min: " + min);
		}
	}
}
