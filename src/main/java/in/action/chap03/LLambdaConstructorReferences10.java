package in.action.chap03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import in.action.chap03.impl.TriFunction;
import in.action.model.Apple;
import in.action.model.Color;
import in.action.model.Fruit;
import in.action.model.Orange;

public class LLambdaConstructorReferences10 {

	public static void main(String[] args) throws Exception {

		{
			/*
			 * A constructor reference to the default Apple() constructor.
			 */

			// () -> new Apple();
			Supplier<Apple> supplier = Apple::new;

			/*
			 * Calling Supplier’s get method will produce a new Apple.
			 */
			Apple a1 = supplier.get();
			System.out.println(a1);

		}

		{
			/*
			 * If you have a constructor with signature Apple(int weight), it fits the
			 * signature of the Function interface, so you can do this,
			 * 
			 */

			// (Integer weight) -> new Apple(weight);
			Function<Integer, Apple> function = Apple::new;

			/*
			 * Calling the Function’s apply method with the requested weight will produce a
			 * new Apple object.
			 */
			Apple a1 = function.apply(110);
			System.out.println(a1);

		}

		{
			/*
			 * If you have a two-argument constructor, Apple( int weight, String color), it
			 * fits the signature of the BiFunction interface, so you can do this,
			 */

			// (weight, color) -> new Apple(weight, color);
			BiFunction<Integer, String, Apple> biFunction = Apple::new;

			Apple a1 = biFunction.apply(100, "green");
			System.out.println(a1);
		}

		{
			List<Integer> weights = Arrays.asList(7, 3, 4, 10);

			/*
			 * In the following code, each element of a List of Integers is passed to the
			 * constructor of Apple using a similar map method we defined earlier, resulting
			 * in a List of apples with different weights:
			 */

			// (Integer weight) -> new Apple(weight)
			Function<Integer, Apple> function = Apple::new;

			List<Apple> apples = EPredicateConsumeFunctionExamples05.map(weights, function);

			System.out.println(apples);

		}

		{
			/*
			 * The capability of referring to a constructor without instantiating it enables
			 * interesting applications.
			 * 
			 * For example, you can use a Map to associate constructors with a string value.
			 * 
			 * You can then create a method giveMeFruit that, given a String and an Integer,
			 * can create different types of fruits with different weights:
			 */

			Map<String, Supplier<Fruit>> fruits = new HashMap<>();
			{
				fruits.put("apple", Apple::new);
				fruits.put("orange", Orange::new);
			}

			System.out.println("Give Me Fruit: " + giveMeFruit(fruits, "apple"));
			System.out.println("Give Me Fruit: " + giveMeFruit(fruits, "orange"));
		}

		{
			TriFunction<Integer, Integer, Integer, Color> colorFactory = in.action.model.Color::new;

			Color color = colorFactory.apply(10, 20, 10);

			System.out.println(color);
		}
	}

	public static Fruit giveMeFruit(Map<String, Supplier<Fruit>> fruits, String fruitNameStr) {

		return fruits.get(fruitNameStr.toLowerCase()).get();

	}

}
