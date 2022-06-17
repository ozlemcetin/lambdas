package java8.in.action.part2.chap05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BMappingExample05 {

	public static void main(String[] args) throws Exception {

		/*
		 * You saw how to return the length for each word in a list using the method
		 * map.
		 * 
		 * Let’s extend this idea a bit further: how could you return a list of all the
		 * unique characters for a list of words?
		 * 
		 * For example, given the list of words ["Hello", "World"] you’d like to return
		 * the list ["H", "e", "l", "o", "W", "r", "d"].
		 */

		List<String> words = Arrays.asList("Hello", "World");
		{

			// charAt
			{

				List<List<Character>> list = words.stream()

						.map(s -> {

							List<Character> chars = new ArrayList<>();
							{
								for (int i = 0; i < s.length(); i++) {
									chars.add(s.charAt(i));
								}
							}

							return chars;
						})

						.collect(Collectors.toList());

				// [[H, e, l, l, o], [W, o, r, l, d]]
				System.out.println(list);

			}

			// split
			{

				List<List<String>> list = words.stream()

						.map(s -> Arrays.asList(s.split("")))

						.collect(Collectors.toList());

				// [[H, e, l, l, o], [W, o, r, l, d]]
				System.out.println(list);

			}

		}

		/*
		 * First, you need a stream of characters instead of a stream of arrays/list.
		 * 
		 * There’s a method called Arrays.stream()that takes an array and produces a
		 * stream, for example
		 * 
		 * String[] arrayOfWords = {"Goodbye", "World"};
		 * 
		 * Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
		 */
		{

			{

				/*
				 * the lambda passed to the map method returns a String[] (an array of String)
				 * for each word.
				 * 
				 * So the stream returned by the map method is actually of type
				 * Stream<String[]>.
				 */

				List<String[]> list = words.stream()

						.map(s -> s.split(""))

						.collect(Collectors.toList());

				// [[Ljava.lang.String;@88bcd751, [Ljava.lang.String;@776508ea]
				System.out.println(list);

			}

			// Arrays.stream()
			{
				/*
				 * What you really want is Stream<String> to represent a stream of characters.
				 */

				List<Stream<String>> list = words.stream()

						// Converts each word into an array of its individual letters
						.map(s -> s.split(""))

						// Makes each array into a separate stream
						.map(Arrays::stream)

						.collect(Collectors.toList());

				// [java.util.stream.ReferencePipeline$Head@6ca2d2d4,
				// java.util.stream.ReferencePipeline$Head@ebd2a3ec]
				System.out.println(list);
			}

		}

		// USING FLATMAP
		{

			{
				List<String> list = words.stream().

				// Converts each word into an array of its individual letters
						map(w -> w.split(""))

						// Flattens each generated stream into a single stream
						.flatMap(Arrays::stream)

						.collect(Collectors.toList());

				// [H, e, l, l, o, W, o, r, l, d]
				System.out.println(list);

			}

			// distinct
			{
				List<String> list = words.stream().

				// Converts each word into an array of its individual letters
						map(w -> w.split(""))

						// Flattens each generated stream into a single stream
						.flatMap(Arrays::stream)

						.distinct()

						.collect(Collectors.toList());

				// [H, e, l, o, W, r, d]
				System.out.println(list);

			}
		}
	}
}
