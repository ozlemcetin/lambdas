package in.action.chap03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

import in.action.model.Apple;
import in.action.model.Inventory;

public class KLambdaMethodReferences09 {

	public static void main(String[] args) throws Exception {

		{
			System.out.println(Inventory.APPLES);

			if (false) {

				Inventory.APPLES.sort(new Comparator<Apple>() {

					@Override
					public int compare(Apple a1, Apple a2) {

						return a1.getWeight().compareTo(a2.getWeight());
					}
				});

				System.out.println(Inventory.APPLES);
			}

			if (false) {

				Inventory.APPLES.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

				System.out.println(Inventory.APPLES);

			}

			/*
			 * After (using a method reference and java.util.Comparator.comparing):
			 */
			{

				/*
				 * Method references can be seen as shorthand for lambdas calling only a
				 * specific method. The basic idea is that if a lambda represents “call this
				 * method directly,” it’s best to refer to the method by name rather than by a
				 * description of how to call it.Indeed, a method reference lets you create a
				 * lambda expression from an existing method implementation. But by referring to
				 * a method name explicitly, your code can gain better readability
				 */

				/*
				 * The method reference is shorthand for the lambda expression (Apple a) ->
				 * a.getWeight().
				 */
				Inventory.APPLES.sort(Comparator.comparing(Apple::getWeight));

				System.out.println(Inventory.APPLES);
			}

		}

		{
			/*
			 * Say you’d like to sort a List of strings, ignoring case differences.
			 * 
			 * The sort method on a List expects a Comparator as parameter. You saw earlier
			 * that Comparator describes a function descriptor with the signature (T, T) ->
			 * int.
			 * 
			 * You can define a lambda expression that leverages the method
			 * compareToIgnoreCase in the String class as follows
			 */

			List<String> strings = Arrays.asList("The", "sort", "method", "on", "a", "List", "expects", "a",
					"Comparator", "as", "parameter");

			System.out.println(strings);

			if (false) {

				strings.sort(new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						return o1.compareToIgnoreCase(o2);
					}
				});

			}

			if (false) {

				// Comparator<String> c = (String o1, String o2) -> o1.compareToIgnoreCase(o2);

				strings.sort((String o1, String o2) -> o1.compareToIgnoreCase(o2));

				System.out.println(strings);
			}

			{

				/*
				 * The sort method on a List expects a Comparator as parameter. You saw earlier
				 * that Comparator describes a function descriptor with the signature (T, T) ->
				 * int. You can define a lambda expression that leverages the method
				 * compareToIgnoreCase in the String class as follows (note that
				 * compareToIgnoreCase is predefined in the String class):
				 * 
				 * The lambda expression has a signature compatible with the function descriptor
				 * of Comparator.
				 * 
				 */

				// Comparator<String> c = (String::compareToIgnoreCase);

				strings.sort(String::compareToIgnoreCase);

			}

		}

		{

			List<String> strings = Arrays.asList("1", "2", "3", "4", "5");

			{
				/*
				 * This method takes a String to parse and returns an Integer:
				 * 
				 * (args) -> ClassName.staticMethod(args)
				 * 
				 * ClassName::staticMethod
				 */

				// (String s) -> Integer.parseInt(s);
				Function<String, Integer> stringToIntegerFunc = Integer::parseInt;

				List<Integer> numbers = EPredicateConsumeFunctionExamples05.map(strings, stringToIntegerFunc);

				System.out.println(numbers);

			}

			{
				/*
				 * This lambda uses its first argument to call the method contains on it.
				 * 
				 * (arg0, rest) -> arg0.instanceMethod(rest)
				 * 
				 * arg0 is of type ClassName
				 * 
				 * ClassName::instanceMethod
				 * 
				 */

				/*
				 * This is because the target type describes a function descriptor
				 * (List<String>, String) -> boolean, and List::contains can be unpacked to that
				 * function descriptor.
				 */
				// (List<String> list, String s) -> list.contains(s);
				BiPredicate<List<String>, String> biPredicate = List::contains;

				System.out.println(biPredicate.test(strings, "5"));
				System.out.println(biPredicate.test(strings, "6"));
			}

			{
				/*
				 * 
				 * (args) -> expr.instanceMethod(args)
				 * 
				 * expr::instanceMethod
				 */
			}

		}

	}

	public static <T> boolean contains(List<T> list, T element, BiPredicate<T, T> biPredicate) {

		for (T t : list) {

			if (biPredicate.test(t, element)) {

				return true;
			}

		} // for loop

		return false;
	}

}
