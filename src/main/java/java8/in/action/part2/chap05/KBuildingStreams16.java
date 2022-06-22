package java8.in.action.part2.chap05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class KBuildingStreams16 {

	public static void main(String[] args) throws Exception {

		/*
		 * So far, you were able to get a stream from a collection using the stream
		 * method. In addition, we showed you how to create numerical streams from a
		 * range of numbers.
		 * 
		 * But you can create streams in many more ways! This section shows how you can
		 * create a stream from a sequence of values, from an array, from a file, and
		 * even from a generative function to create infinite streams!
		 */

		// Streams from values
		{
			/*
			 * You can create a stream with explicit values by using the static method
			 * Stream.of, which can take any number of parameters.
			 * 
			 * For example, in the following code you create a stream of strings directly
			 * using Stream.of.
			 * 
			 * You then convert the strings to uppercase before printing them one by one:
			 */
			Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");

			stream.map(String::toUpperCase).forEach(System.out::println);

		}

		// an empty stream
		{
			/*
			 * You can get an empty stream using the empty method as follows:
			 */

			Stream<String> streamStr = Stream.empty();

			Stream<Object> streamObj = Stream.empty();

		}

		// Streams from arrays
		{
			{
				int[] numbers = { 2, 3, 5, 7, 11, 13 };

				IntStream stream = Arrays.stream(numbers);

				int sum = stream.sum();

				System.out.println("Sum: " + sum);

			}

			{
				String[] words = { "Java 8 ", "Lambdas ", "In ", "Action" };

				Stream<String> stream = Arrays.stream(words);

				stream.map(String::toUpperCase).forEach(System.out::println);

			}

		}

		// Streams from files
		{
			/*
			 * Java’s NIO API (non-blocking I/O), which is used for I/O operations such as
			 * processing a file, has been updated to take advantage of the Streams API.
			 *
			 * Many static methods in java.nio.file.Files return a stream.
			 * 
			 * For example, a useful method is Files.lines, which returns a stream of lines
			 * as strings from a given file. Using what you’ve learned so far, you could use
			 * this method to find out the number of unique words in a file as follows:
			 */

			String pathStr = "C:\\Users\\ocetin4\\Desktop\\Solution\\lambdas\\src\\main\\java\\java8\\in\\action\\part2\\chap05\\MyFile.txt";

			{

				List<String> words = null;
				try (Stream<String> lines = Files.lines(

						Paths.get(pathStr),

						Charset.defaultCharset())) {

					words = lines.flatMap(line -> Arrays.stream(line.split(" "))).collect(Collectors.toList());

				} catch (IOException e) {
				}

				System.out.println(words);
			}

			/*
			 * You use Files.lines to return a stream where each element is a line in the
			 * given file.
			 * 
			 * You then split each line into words by calling the split method on line.
			 * 
			 * Notice how you use flatMap to produce one flattened stream of words instead
			 * of multiple streams of words for each line.
			 * 
			 * Finally, you count each distinct word in the stream by chaining the methods
			 * distinct and count.
			 */
			{
				long uniqueWords = 0;
				try (Stream<String> lines = Files.lines(

						Paths.get(pathStr),

						Charset.defaultCharset())) {

					uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();

				} catch (IOException e) {
				}

				System.out.println("the number of unique words in a file: " + uniqueWords);

			}
		}

	}
}
