package java8.in.action.part3.chap08;

import java8.in.action.part3.chap08.impl.IsAllLowerCase;
import java8.in.action.part3.chap08.impl.IsNumeric;
import java8.in.action.part3.chap08.impl.ValidationStrategy;
import java8.in.action.part3.chap08.impl.Validator;

public class BStrategy04 {

	public static void main(String[] args) throws Exception {

		/*
		 * The strategy pattern consists of three parts, as illustrated in figure 8.1:
		 * 
		 * An interface to represent some algorithm (the interface Strategy)
		 * 
		 * One or more concrete implementations of that interface to represent multiple
		 * algorithms (the concrete classes ConcreteStrategyA, ConcreteStrategyB)
		 * 
		 * One or more clients that use the strategy objects
		 */

		// Using ValidationStrategy Interface
		{

			Validator numericValidator = new Validator(new IsNumeric());
			// validateSampleDate(numericValidator, " is numeric ? ");

			Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
			// validateSampleDate(lowerCaseValidator, " is all lower case ? ");
		}

		/*
		 * By now you should recognize that ValidationStrategy is a functional interface
		 * (in addition, it has the same function descriptor as Predicate<String>).
		 * 
		 * This means that instead of declaring new classes to implement different
		 * strategies, you can pass lambda expressions directly, which are more concise:
		 */
		{
			{
				Validator numericValidator = new Validator(new ValidationStrategy() {

					@Override
					public boolean execute(String s) {
						return s.matches("\\d+");
					}
				});

			}

			validateSampleDate(new Validator((String s) -> s.matches("\\d+")), " is numeric ? ");

			validateSampleDate(new Validator((String s) -> s.matches("[a-z]+")), " is all lower case ? ");
		}

	}

	public static void validateSampleDate(Validator validator, String str) {

		{
			String s = "aaaa";
			System.out.println(s + str + validator.validate(s));
		}

		{
			String s = "aBCa";
			System.out.println(s + str + validator.validate(s));
		}

		{
			String s = "123";
			System.out.println(s + str + validator.validate(s));
		}
	}

}
