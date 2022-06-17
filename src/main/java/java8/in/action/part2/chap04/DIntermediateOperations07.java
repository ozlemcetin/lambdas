package java8.in.action.part2.chap04;

import java.util.List;
import java.util.stream.Collectors;

import java8.in.action.model.Inventory;

public class DIntermediateOperations07 {

	public static void main(String[] args) throws Exception {

		{
			/*
			 * Intermediate operations such as filter or sorted return another stream as the
			 * return type.
			 * 
			 * This allows the operations to be connected to form a query.
			 * 
			 * What’s important is that intermediate operations don’t perform any processing
			 * until a terminal operation is invoked on the stream pipeline
			 */

			List<String> names = Inventory.MENU.stream()

					.filter(d -> {

						/*
						 * Printing the dishes as they’re filtered
						 */
						System.out.println("filtering " + d.getName());
						return d.getCalories() > 300;
					})

					.map(d -> {

						/*
						 * Printing the dishes as you extract their names
						 */
						System.out.println("mapping " + d.getName());
						return d.getName();
					})

					.limit(3)

					.collect(Collectors.toList());

			/*
			 * 
			 * filtering pork
			 * 
			 * mapping pork
			 * 
			 * filtering beef
			 * 
			 * mapping beef
			 * 
			 * filtering chicken
			 * 
			 * mapping chicken
			 * 
			 * [pork, beef, chicken]
			 * 
			 * First, despite the fact that many dishes have more than 300 calories, only
			 * the first three are selected! This is because of the limit operation and a
			 * technique called short-circuiting,
			 * 
			 * Second, despite the fact that filter and map are two separate operations,
			 * they were merged into the same pass (we call this technique loop fusion).
			 */
			System.out.println(names);

		}
	}
}
