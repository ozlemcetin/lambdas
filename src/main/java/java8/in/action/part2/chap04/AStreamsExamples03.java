package java8.in.action.part2.chap04;

import java.util.List;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class AStreamsExamples03 {

	public static void main(String[] args) throws Exception {

		{
			/*
			 * Sequence of elements—Like a collection, a stream provides an interface to a
			 * sequenced set of values of a specific element type. Because collections are
			 * data structures, they’re mostly about storing and accessing elements with
			 * specific time/space complexities (for example, an ArrayList vs. a
			 * LinkedList). But streams are about expressing computations such as filter,
			 * sorted, and map that you saw earlier. Collections are about data; streams are
			 * about computations. We explain this idea in greater detail in the coming
			 * sections.
			 * 
			 * Source—Streams consume from a data-providing source such as collections,
			 * arrays, or I/O resources. Note that generating a stream from an ordered
			 * collection preserves the ordering. The elements of a stream coming from a
			 * list will have the same order as the list.
			 * 
			 * Data processing operations—Streams support database-like operations and
			 * common operations from functional programming languages to manipulate data,
			 * such as filter, map, reduce, find, match, sort, and so on. Stream operations
			 * can be executed either sequentially or in parallel.
			 * 
			 * In addition, stream operations have two important characteristics:
			 * 
			 * Pipelining—Many stream operations return a stream themselves, allowing
			 * operations to be chained and form a larger pipeline. This enables certain
			 * optimizations that we explain in the next chapter, such as laziness and
			 * short-circuiting. A pipeline of operations can be viewed as a database-like
			 * query on the data source.
			 * 
			 * 
			 * Internal iteration—In contrast to collections, which are iterated explicitly
			 * using an iterator, stream operations do the iteration behind the scenes for
			 * you. We briefly mentioned this idea in chapter 1 and return to it later in
			 * the next section.
			 */

			/*
			 * “Find names of three high-calorie dishes.”
			 */

			/*
			 * you first get a stream from the list of dishes by calling the stream method
			 * on menu
			 * 
			 * The data source is the list of dishes (the menu) and it provides a sequence
			 * of elements to the stream.
			 * 
			 * Next, you apply a series of data processing operations on the stream: filter,
			 * map, limit, and collect.
			 * 
			 * All these operations except collect return another stream so they can be
			 * connected to form a pipeline, which can be viewed as a query on the source
			 * 
			 * Finally, the collect operation starts processing the pipeline to return a
			 * result (it’s different because it returns something other than a stream—here,
			 * a List).
			 */

			List<String> threeHighCaloricDishNames =

					// Get a stream from menu (the list of dishes)
					Inventory.MENU.stream()

							// Create a pipeline of operations: first filter high-calorie dishes
							.filter(d -> d.getCalories() >= 400)

							// Get the names of the dishes
							.map(Dish::getName)

							// Select only the first three
							.limit(3)

							// Store the results in another List
							.collect(Collectors.toList());

			System.out.println(threeHighCaloricDishNames);
		}
	}
}
