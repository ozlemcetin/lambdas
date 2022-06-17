package java8.in.action.part2.chap04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BStreamsTraversableOnlyOnce05 {

	public static void main(String[] args) throws Exception {

		{
			/*
			 * Note that, similarly to iterators, a stream can be traversed only once. After
			 * that a stream is said to be consumed. You can get a new stream from the
			 * initial data source to traverse it again just like for an iterator (assuming
			 * it’s a repeatable source like a collection; if it’s an I/O channel, you’re
			 * out of luck).
			 * 
			 * For example, the following code would throw an exception indicating the
			 * stream has been consumed:
			 */

			List<String> titles = Arrays.asList("Java8", "In", "Action");

			Stream<String> stream = titles.stream();

			/*
			 * Prints each word in the title.
			 */
			stream.forEach(System.out::println);

			/*
			 * java.lang.IllegalStateException: stream has already been operated upon or
			 * closed.
			 */
			stream.forEach(System.out::println);
		}
	}
}
