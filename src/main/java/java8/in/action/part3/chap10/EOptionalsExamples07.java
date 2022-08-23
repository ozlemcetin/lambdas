package java8.in.action.part3.chap10;

import java.util.Optional;
import java.util.Properties;

public class EOptionalsExamples07 {

	public static void main(String[] args) throws Exception {

		Properties props = new Properties();
		{
			props.setProperty("a", "5");
			props.setProperty("b", "true");
			props.setProperty("c", "-3");
		}

		{
			// assertEquals(5, readDuration(props, "a"));
			System.out.println(readDuration_OptionalStyle(props, "a"));

			// assertEquals(0, readDuration(props, "b"));
			System.out.println(readDuration_OptionalStyle(props, "b"));

			// assertEquals(0, readDuration(props, "c"));
			System.out.println(readDuration_OptionalStyle(props, "c"));

			// assertEquals(0, readDuration(props, "d"));
			System.out.println(readDuration_OptionalStyle(props, "d"));
		}

	}

	/*
	 * when the value of a given property is a String representing a positive
	 * integer, returns that integer, but returns zero in all other cases.
	 */
	public static int readDuration_ImperativeStyle(Properties props, String name) {

		int number = 0;
		{
			// Make sure a property exists with the required name.
			if (props != null && props.containsKey(name)) {

				try {

					// Try to convert the String property
					number = Integer.parseInt(props.getProperty(name));

				} catch (Exception e) {
				}
			}
		}

		// Check if the resulting number is positive.
		return number >= 0 ? number : 0;
	}

	public static int readDuration_OptionalStyle(Properties props, String name) {

		return

		// Optional<String>
		Optional.ofNullable(props.getProperty(name))

				// Optional<Optional<Integer>>
				// .map(EOptionalsExamples06::stringToInt);

				// Optional<Integer>
				.flatMap(EOptionalsExamples06::stringToInt)

				// easily filter away the negative number
				.filter(i -> i > 0)

				/*
				 * the method will return the 0 that’s passed as the default value to the orElse
				 * method; otherwise, it will return the positive integer contained in the
				 * optional.
				 */
				.orElse(0);

	}

}
