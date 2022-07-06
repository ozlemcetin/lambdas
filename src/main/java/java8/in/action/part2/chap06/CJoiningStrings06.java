package java8.in.action.part2.chap06;

import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class CJoiningStrings06 {

	public static void main(String[] args) throws Exception {

		/*
		 * The collector returned by the joining factory method concatenates into a
		 * single string all strings resulting from invoking the toString method on each
		 * object in the stream. This means you can concatenate the names of all the
		 * dishes in the menu as follows:
		 */

		{
			/*
			 * Note that joining internally makes use of a StringBuilder to append the
			 * generated strings into one.
			 */
			String shortMenu = Inventory.MENU.stream()

					.map(Dish::getName)

					.collect(Collectors.joining());

			// porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon
			System.out.println(shortMenu);
		}

		{
			/*
			 * the joining factory method has an overloaded version that accepts a delimiter
			 * string between two consecutive elements, so you can obtain a comma-separated
			 * list of the dishes’ names with
			 */

			String shortMenu = Inventory.MENU.stream()

					.map(Dish::getName)

					.collect(Collectors.joining(", "));

			// pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon
			System.out.println(shortMenu);

		}

		/*
		 * Until now, we’ve explored various collectors that reduce a stream to a single
		 * value.
		 * 
		 * In the next section, we demonstrate how all the reduction processes of this
		 * form are special cases of the more general reduction collector provided by
		 * the Collectors.reducing factory method
		 */

	}

}
