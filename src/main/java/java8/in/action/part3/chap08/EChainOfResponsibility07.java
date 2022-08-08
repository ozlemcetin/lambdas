package java8.in.action.part3.chap08;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import java8.in.action.part3.chap08.impl.HeaderTextProcessing;
import java8.in.action.part3.chap08.impl.ProcessingObject;

public class EChainOfResponsibility07 {

	public static void main(String[] args) throws Exception {

		{
			/*
			 * You can now connect two processing objects to construct a chain of operations
			 */

			ProcessingObject<String> p1 = new HeaderTextProcessing();
			{
				ProcessingObject<String> p2 = new ProcessingObject<String>() {

					@Override
					protected String handleWork(String input) {

						/*
						 * Oops, we forgot the ‘m’ in “lambda”!
						 */
						return input.replaceAll("labda", "lambda");
					}
				};

				/*
				 * Chaining two processing objects
				 */
				p1.setSuccessor(p2);
			}

			/*
			 * Prints “From Raoul, Mario and Alan: Aren’t lambdas really sexy?!!”
			 */
			String result = p1.handle("Aren't labdas really sexy?!!");

			System.out.println(result);
		}

		// USING LAMBDA EXPRESSIONS
		{
			/*
			 * You can represent the processing objects as an instance of Function<String,
			 * String> or more precisely a UnaryOperator<String>.
			 * 
			 * To chain them you just need to compose these functions by using the andThen
			 * method!
			 * 
			 */
			Function<String, String> pipeline = null;
			{
				UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;

				UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");

				/*
				 * Compose the two functions, resulting in a chain of operations.
				 */
				pipeline = headerProcessing.andThen(spellCheckerProcessing);
			}

			String result = pipeline.apply("Aren't labdas really sexy?!!");

			System.out.println(result);
		}

	}

}
