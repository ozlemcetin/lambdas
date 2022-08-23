package java8.in.action.part3.chap10;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EOptionalsExamples06 {

	public static void main(String[] args) throws Exception {

		// Wrapping a potentially null value in an optional
		{
			Map<String, Object> map = new HashMap<>();

			// Object value = map.get("key");

			Optional<Object> value = Optional.ofNullable(map.get("key"));

			System.out.println(value);
		}

		// Exceptions vs. Optional
		{

			/*
			 * Throwing an exception is another common alternative in the Java API to
			 * returning a null when, for any reason, a value can’t be provided
			 */

			Optional<Integer> number = stringToInt("123");

			System.out.println(number);
		}
	}

	/*
	 * Our suggestion is to collect several methods similar to this in a utility
	 * class; let’s call it OptionalUtility.
	 * 
	 * In this way, from now on you’ll always be allowed to convert a String into an
	 * Optional<Integer>, using this OptionalUtility.stringToInt method.
	 * 
	 * You can forget that you encapsulated the ugly try/catch logic in it.
	 */

	public static Optional<Integer> stringToInt(String s) {

		try {

			/*
			 * If the String can be converted into an Integer, return an optional containing
			 * it.
			 */
			return Optional.of(Integer.parseInt(s));

		} catch (NumberFormatException e) {

			// Otherwise return an empty optional.
			return Optional.empty();
		}
	}

}
