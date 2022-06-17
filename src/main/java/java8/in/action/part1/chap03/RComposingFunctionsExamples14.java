package java8.in.action.part1.chap03;

import java.util.function.Function;

import java8.in.action.model.Letter;

public class RComposingFunctionsExamples14 {

	public static void main(String[] args) throws Exception {

		{
			/*
			 * For example, given a function f that increments a number (x -> x + 1) and
			 * 
			 * another function g that multiples a number by 2,
			 * 
			 * you can combine them to create a function h that first increments a number
			 * and then multiplies the result by 2:
			 */

			Function<Integer, Integer> f = x -> (x + 1);

			Function<Integer, Integer> g = x -> x * 2;

			/*
			 * In math you’d write g(f(x)) or (g o f)(x).
			 */
			Function<Integer, Integer> h1 = f.andThen(g);

			System.out.println("g(f(x)): " + h1.apply(5));

			/*
			 * You can also use the method compose similarly to first apply the function
			 * given as argument to compose and then apply the function to the result.
			 * 
			 * For example, in the previous example using compose, it would mean f(g(x))
			 * instead of g(f(x)) using andThen:
			 */

			/*
			 * In math you’d write f(g(x)) or (f o g)(x).
			 */

			Function<Integer, Integer> h2 = f.compose(g);

			System.out.println("f(g(x)): " + h2.apply(5));

		}

		{
			/*
			 * You can now create various transformation pipelines by composing the utility
			 * methods, for example, creating a pipeline that first adds a header, then
			 * checks spelling, and finally adds a footer,
			 */

			Function<String, String> addHeader = Letter::addHeader;

			Function<String, String> pipeline = addHeader

					.andThen(Letter::checkSpelling)

					.andThen(Letter::addFooter);

		}

	}

}
