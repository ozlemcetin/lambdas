package java8.in.action.part3.chap08;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class ARefactoring03 {

	public static void main(String[] args) throws Exception {

		/*
		 * Ideally, you should try to convert all code that processes a collection with
		 * typical data processing patterns with an iterator to use the Streams API
		 * instead. Why? The Streams API expresses more clearly the intent of a data
		 * processing pipeline.
		 * 
		 * In addition, streams can be optimized behind the scenes making use of
		 * short-circuiting and laziness, as well as leveraging your multicore
		 * architecture, as we explained in chapter 7.
		 * 
		 */

		{
			/*
			 * For example, the following imperative code expresses two patterns (filtering
			 * and extracting) that are mangled together, which forces the programmer to
			 * carefully understand the whole implementation before figuring out what the
			 * code does
			 */
			{

				List<String> dishNames = new ArrayList<>();
				{
					for (Dish dish : Inventory.MENU) {

						// filtering
						if (dish.getCalories() > 300) {

							// extracting
							dishNames.add(dish.getName());
						}
					}
				}

				System.out.println(dishNames);
			}

			/*
			 * The alternative using the Streams API reads more like the problem statement,
			 * and it can be easily parallelized
			 */
			{
				List<String> dishNames = Inventory.MENU.parallelStream()

						// filtering
						.filter(dish -> dish.getCalories() > 300)

						// extracting
						.map(Dish::getName)

						// collecting
						.collect(Collectors.toList());

				System.out.println(dishNames);
			}
		}

	}

}
