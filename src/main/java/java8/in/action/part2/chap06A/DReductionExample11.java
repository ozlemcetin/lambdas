package java8.in.action.part2.chap06A;

import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class DReductionExample11 {

	public static void main(String[] args) throws Exception {

		// Collectors.joining
		{
			String shortMenu = Inventory.MENU.stream()

					.map(Dish::getName)

					.collect(Collectors.joining());

			// pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon
			System.out.println(shortMenu);
		}

		// Collectors.reducing
		{

			/*
			 * Here you just extract the value inside the Optional object using its get
			 * method. this case using the get method is safe only because you’re sure that
			 * the stream of dishes isn’t empty
			 * 
			 * In general, as you’ll learn in chapter 10, it’s safer to unwrap the value
			 * eventually contained in an Optional using a method that also allows you to
			 * provide a default, such as orElse or orElseGet.
			 */

			BinaryOperator<String> op = new BinaryOperator<String>() {

				@Override
				public String apply(String t, String u) {

					return t + u;
				}

			};

			String shortMenu = Inventory.MENU.stream()

					.map(Dish::getName)

					// BinaryOperator<String> op
					.collect(Collectors.reducing(op)).get();

			System.out.println(shortMenu);
		}

		// Collectors.reducing
		{
			String shortMenu = Inventory.MENU.stream()

					.map(Dish::getName)

					.collect(Collectors.reducing((s1, s2) -> s1 + s2)).get();

			System.out.println(shortMenu);
		}

		// Collectors.reducing
		{

			/*
			 * This starts the reduction process with an empty string as the accumulator,
			 * and when traversing the stream of dishes it converts each dish to its name
			 * and appends this name to the accumulator.
			 * 
			 * Note that, as we mentioned, reducing doesn’t need the three arguments to
			 * return an Optional because in the case of an empty stream it can return a
			 * more meaningful value, which is the empty string used as the initial
			 * accumulator value.
			 */

			String shortMenu = Inventory.MENU.stream()

					.collect(Collectors.reducing(

							// U identity,
							"",

							// Function<? super T, ? extends U> mapper,
							Dish::getName,

							// BinaryOperator<U> op
							(s1, s2) -> s1 + s2));

			System.out.println(shortMenu);
		}

	}

}
