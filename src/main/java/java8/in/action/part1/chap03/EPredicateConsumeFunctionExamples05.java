package java8.in.action.part1.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EPredicateConsumeFunctionExamples05 {

	public static void main(String[] args) throws Exception {

		{
			List<String> listOfStrings = Arrays.asList("Java", "", "8", "in", null, "action");
			System.out.println("Strings:  " + listOfStrings);

			// Predicate
			{
				List<String> nonEmptyStrings = filter(listOfStrings, (String s) -> s != null && !s.isEmpty());

				System.out.println("Non Empty Strings: " + nonEmptyStrings);
			}

			// Function
			{
				List<Integer> lengthofStrings = map(listOfStrings, (String s) -> s != null ? s.length() : 0);

				System.out.println("Length of Strings: " + lengthofStrings);
			}

		}

		{
			List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
			System.out.println("Numbers: " + listOfNumbers);

			// Predicate
			{
				List<Integer> evenNumbers = filter(listOfNumbers, (Integer i) -> i % 2 == 0);

				System.out.println("Even Numbers: " + evenNumbers);

			}

			// Consumer
			{
				/*
				 * The lambda is the implementation of the accept method from Consumer.
				 */

				System.out.println("Numbers:");
				forEach(listOfNumbers, (Integer i) -> System.out.println(i));
			}

		}

	}

	/*
	 * Those of you who are familiar with Java’s evolution will recall that Java 7
	 * had already introduced the idea of types being inferred from context with
	 * generic inference using the diamond operator (<>) (this idea can be found
	 * even earlier with generic methods). A given class instance expression can
	 * appear in two or more different contexts, and the appropriate type argument
	 * will be inferred as exemplified here:
	 * 
	 * List<String> listOfStrings = new ArrayList<>();
	 * 
	 * 
	 * List<Integer> listOfIntegers = new ArrayList<>()
	 */

	/*
	 * Predicate
	 * 
	 * @FunctionalInterface public interface Predicate<T>{ boolean test(T t); }
	 * 
	 */
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {

		List<T> results = new ArrayList<>();
		{
			for (T t : list) {

				// test
				if (p.test(t)) {

					results.add(t);
				}

			} // for loop
		}
		return results;
	}

	/*
	 * Consumer
	 * 
	 * @FunctionalInterface public interface Consumer<T>{ void accept(T t); }
	 */
	public static <T> void forEach(List<T> list, Consumer<T> c) {

		for (T t : list) {

			// accept
			c.accept(t);

		} // for loop

	}

	/*
	 * Function
	 * 
	 * @FunctionalInterface public interface Function<T, R>{ R apply(T t); }
	 */

	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {

		List<R> results = new ArrayList<>();
		{

			for (T t : list) {

				// apply
				results.add(f.apply(t));

			} // for loop

		}
		return results;
	}

}
