package in.action.chap03;

import java.security.PrivilegedAction;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

import in.action.model.Apple;

public class FLambdaTypeChecking06 {

	public static void main(String[] args) throws Exception {

		/*
		 * When we first mentioned lambda expressions, we said that they let you
		 * generate an instance of a functional interface. Nonetheless, a lambda
		 * expression itself doesn’t contain the information about which functional
		 * interface it’s implementing
		 * 
		 * The type of a lambda is deduced from the context in which the lambda is used
		 */

		{

			/*
			 * For example, both interfaces Callable and PrivilegedAction described earlier
			 * represent functions that accept nothing and return a generic type T.
			 */
			Callable<Integer> c1 = () -> 42;
			System.out.println(c1.call());

			PrivilegedAction<Integer> p1 = () -> 42;
			System.out.println(p1.run());

			Callable<String> c2 = () -> "Hello World!";
			System.out.println(c2.call());

			PrivilegedAction<String> p2 = () -> "Hello World!";
			System.out.println(p2.run());

		}

		{
			Comparator<Apple> c = new Comparator<Apple>() {

				@Override
				public int compare(Apple a1, Apple a2) {

					return a1.getWeight().compareTo(a2.getWeight());
				}
			};

			// Lambda
			Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

			// ToIntBiFunction
			ToIntBiFunction<Apple, Apple> c2 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

			// BiFunction
			BiFunction<Apple, Apple, Integer> c3 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

		}

		{

			/*
			 * The context of the lambda expression is Object (the target type). But Object
			 * isn’t a functional interface. To fix this you can change the target type to
			 * Runnable, which represents a function descriptor () -> void:
			 */

			// Object o = () -> {System.out.println("Tricky example"); };

			Runnable r = () -> {
				System.out.println("Tricky example!");
			};

			r.run();

		}
	}

}
