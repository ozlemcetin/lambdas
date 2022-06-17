package java8.in.action.part2.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class BMappingExample04 {

	public static void main(String[] args) throws Exception {

		/*
		 * A very common data processing idiom is to select information from certain
		 * objects. For example, in SQL you can select a particular column from a table.
		 * 
		 * The Streams API provides similar facilities through the map and flatMap
		 * methods.
		 */

		// Applying a function to each element of a stream
		{

			/*
			 * Streams support the method map, which takes a function as argument. The
			 * function is applied to each element, mapping it into a new element (the word
			 * mapping is used because it has a meaning similar to transforming but with the
			 * nuance of “creating a new version of” rather than “modifying”).
			 * 
			 * For example, in the following code you pass a method reference Dish::getName
			 * to the map method to extract the names of the dishes in the stream:
			 */

			List<String> dishNames = Inventory.MENU.stream()

					.map(Dish::getName)

					.collect(Collectors.toList());

			System.out.println(dishNames);
		}

		/*
		 * Let’s now return to the example where you extracted the name of each dish.
		 * What if you wanted to find out the length of the name of each dish?
		 */

		{
			List<Integer> dishNameLengths = Inventory.MENU.stream()

					.map(Dish::getName)

					.map(String::length)

					.collect(Collectors.toList());

			System.out.println(dishNameLengths);
		}

		/*
		 * Given a list of words, you’d like to return a list of the number of
		 * characters for each word. How would you do it?F
		 */
		{

			List<String> words = Arrays.asList("Manning", "Java8", "Lambdas", "in", "Action");

			List<Integer> lengths = words.stream()

					// The function to apply should take a word an return its length.
					// .map(s -> s.length())
					.map(String::length)

					.collect(Collectors.toList());

			System.out.println(lengths);
		}
	}
}
