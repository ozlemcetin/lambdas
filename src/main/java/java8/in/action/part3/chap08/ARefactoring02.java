package java8.in.action.part3.chap08;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import java8.in.action.model.Apple;
import java8.in.action.model.Dish;
import java8.in.action.model.Dish.CaloricLevel;
import java8.in.action.model.Inventory;

public class ARefactoring02 {

	public static void main(String[] args) throws Exception {

		// From lambda expressions to method references
		{

			/*
			 * A method name states more clearly the intent of your code. For example, in
			 * chapter 6 we showed you the following code to group dishes by caloric levels:
			 */
			{
				{

					Function<Dish, Dish.CaloricLevel> classifier = new Function<Dish, Dish.CaloricLevel>() {

						@Override
						public CaloricLevel apply(Dish t) {

							if (t.getCalories() <= 400)
								return CaloricLevel.DIET;

							else if (t.getCalories() <= 700)
								return CaloricLevel.NORMAL;

							else
								return CaloricLevel.FAT;
						}
					};

					Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Inventory.MENU.stream()

							.collect(Collectors.groupingBy(classifier));

					System.out.println(dishesByCaloricLevel);
				}

				/*
				 * The lambda expression is extracted into a method.
				 */
				{
					Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Inventory.MENU.stream()

							.collect(Collectors.groupingBy(Dish::getCaloricLevel));

					System.out.println(dishesByCaloricLevel);

				}

				{
					/*
					 * {DIET=[Dish [name=chicken, vegetarian=false, calories=400, type=MEAT], Dish
					 * [name=rice, vegetarian=true, calories=350, type=OTHER], Dish [name=season
					 * fruit, vegetarian=true, calories=120, type=OTHER], Dish [name=prawns,
					 * vegetarian=false, calories=400, type=FISH]],
					 * 
					 * FAT=[Dish [name=pork, vegetarian=false, calories=800, type=MEAT]],
					 * 
					 * NORMAL=[Dish [name=beef, vegetarian=false, calories=700, type=MEAT], Dish
					 * [name=french fries, vegetarian=true, calories=530, type=OTHER], Dish
					 * [name=pizza, vegetarian=true, calories=550, type=OTHER], Dish [name=salmon,
					 * vegetarian=false, calories=450, type=FISH]]}
					 */
				}

			}
		}

		/*
		 * In addition, consider making use of helper static methods such as comparing
		 * and maxBy when possible. These methods were designed for use with method
		 * references!
		 */
		{

			/*
			 * You need to think about the implementation of comparison
			 */
			{
				Inventory.APPLES.sort(new Comparator<Apple>() {

					@Override
					public int compare(Apple a1, Apple a2) {

						return a1.getWeight().compareTo(a2.getWeight());
					}
				});

				System.out.println(Inventory.APPLES);
			}

			{
				Inventory.APPLES.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

				System.out.println(Inventory.APPLES);
			}

			/*
			 * Reads like the problem statement.
			 */
			{
				Inventory.APPLES.sort(Comparator.comparing(Apple::getWeight));

				System.out.println(Inventory.APPLES);
			}
		}

		/*
		 * Moreover, for many common reduction operations such as sum, maximum there are
		 * built-in helper methods that can be combined with method references.
		 * 
		 * For example, we showed that using the Collectors API you can find the maximum
		 * or sum in a clearer way than using a combination of a lambda expression and a
		 * lower-level reduce operation. Instead of writing
		 */
		{

			{
				int totalCalories = Inventory.MENU.stream()

						.map(Dish::getCalories)

						.reduce(0, (c1, c2) -> c1 + c2);

				System.out.println(totalCalories);
			}

			/*
			 * Here we use the collector summingInt (names go a long way in documenting your
			 * code):
			 */
			{
				int totalCalories = Inventory.MENU.stream()

						.collect(Collectors.summingInt(Dish::getCalories));

				System.out.println(totalCalories);
			}
		}
	}

}
