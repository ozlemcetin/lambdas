package java8.in.action.part1.chap03;

import java.util.Comparator;
import java.util.List;

import java8.in.action.model.Apple;
import java8.in.action.model.Inventory;

public class HLambdaTypeInference07 {

	public static void main(String[] args) throws Exception {

		/*
		 * The Java compiler deduces what functional interface to associate with a
		 * lambda expression from its surrounding context (the target type), meaning it
		 * can also deduce an appropriate signature for the lambda because the function
		 * descriptor is available through the target type.
		 * 
		 * The benefit is that the compiler has access to the types of the parameters of
		 * a lambda expression, and they can be omitted in the lambda syntax. In other
		 * words, the Java compiler infers the types of the parameters of a lambda as
		 * shown here:
		 */

		{
			List<Apple> heavierThan150g = EPredicateConsumeFunctionExamples05.filter(Inventory.APPLES,
					(Apple a) -> a.getWeight() > 150);

			System.out.println(heavierThan150g);

			List<Apple> greenApples = EPredicateConsumeFunctionExamples05.filter(Inventory.APPLES,
					a -> "green".equals(a.getColor()));

			System.out.println(greenApples);
		}

		/*
		 * The benefits of code readability are more noticeable with lambda expressions
		 * that have several parameters
		 */

		{
			// Without type inference
			Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

			// With type inference
			Comparator<Apple> c2 = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
		}
	}

}
