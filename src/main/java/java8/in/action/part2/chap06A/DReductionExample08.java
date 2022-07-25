package java8.in.action.part2.chap06A;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class DReductionExample08 {

	public static void main(String[] args) throws Exception {

		/*
		 * you could find the highest-calorie dish using the one-argument version of
		 * reducing as follows:
		 */

		// Collectors.maxBy
		{
			{
				Optional<Dish> highestCalorieDish = Inventory.MENU.stream()

						.collect(Collectors.maxBy(new Comparator<Dish>() {

							@Override
							public int compare(Dish o1, Dish o2) {
								return Integer.compare(o1.getCalories(), o2.getCalories());
							}
						}));

				System.out.println("highestCalorieDish: " + highestCalorieDish);
			}

			{
				Optional<Dish> highestCalorieDish = Inventory.MENU.stream()

						.collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));

				System.out.println("highestCalorieDish: " + highestCalorieDish);
			}

		}

		// Collectors.reducing
		{

			/*
			 * You can think of the collector created with the one-argument reducing factory
			 * method as a particular case of the three-argument method, which uses the
			 * first item in the stream as a starting point and an identity function (that
			 * is, a function doing nothing more than returning its input argument as is) as
			 * a transformation function.
			 * 
			 * This also implies that the one-argument reducing collector won’t have any
			 * starting point when passed to the collect method of an empty stream and, as
			 * we explained in section 6.2.1, for this reason it returns an Optional<Dish>
			 * object.
			 */
			{
				Optional<Dish> highestCalorieDish = Inventory.MENU.stream()

						.collect(Collectors.reducing(new BinaryOperator<Dish>() {

							@Override
							public Dish apply(Dish t, Dish u) {

								return t.getCalories() > u.getCalories() ? t : u;
							}
						}));

				System.out.println("highestCalorieDish: " + highestCalorieDish);
			}

			{
				Optional<Dish> highestCalorieDish = Inventory.MENU.stream()

						.collect(Collectors.reducing((Dish t, Dish u) -> t.getCalories() > u.getCalories() ? t : u));

				System.out.println("highestCalorieDish: " + highestCalorieDish);
			}

		}
	}

}
