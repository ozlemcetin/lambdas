package java8.in.action.part2.chap06A;

import java.util.function.Function;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class DReductionExample07 {

	public static void main(String[] args) throws Exception {

		/*
		 * All the collectors we’ve discussed so far are, in reality, only convenient
		 * specializations of a reduction process that can be defined using the reducing
		 * factory method. The Collectors.reducing factory method is a generalization of
		 * all of them.
		 * 
		 * For instance, it’s possible to calculate the total calories in your menu with
		 * a collector created from the reducing method as follows:
		 */

		// Collectors.summingInt
		{
			int totalCalories = Inventory.MENU.stream()

					.collect(Collectors.summingInt(Dish::getCalories));

			System.out.println("totalCalories: " + totalCalories);

		}

		/*
		 * It takes three arguments:
		 * 
		 * The first argument is the starting value of the reduction operation and will
		 * also be the value returned in the case of a stream with no elements, so
		 * clearly 0 is the appropriate value in the case of a numeric sum.
		 * 
		 * The second argument is the same function you used in section 6.2.2 to
		 * transform a dish into an int representing its calorie content.
		 * 
		 * The third argument is a BinaryOperator that aggregates two items into a
		 * single value of the same type. Here, it just sums two ints.
		 */

		// Collectors.reducing
		{

			Function<Dish, Integer> mapper = new Function<Dish, Integer>() {

				@Override
				public Integer apply(Dish t) {

					return t.getCalories();
				}
			};

			Integer totalCalories = Inventory.MENU.stream()

					.collect(Collectors.reducing(0, mapper, (i, j) -> i + j));

			System.out.println("totalCalories: " + totalCalories);

		}

		// Collectors.reducing
		{

			Integer totalCalories = Inventory.MENU.stream()

					.collect(Collectors.reducing(

							// Initial value
							0,

							// Transformation function
							Dish::getCalories,

							// Aggregating function
							Integer::sum));

			System.out.println("totalCalories: " + totalCalories);

		}

	}

}
