package java8.in.action.part2.chap06A;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class FPartitioning19 {

	public static void main(String[] args) throws Exception {

		/*
		 * Partitioning is a special case of grouping: having a predicate (a function
		 * returning a boolean), called a partitioning function, as a classification
		 * function.
		 * 
		 * The fact that the partitioning function returns a boolean means the resulting
		 * grouping Map will have a Boolean as a key type and therefore there can be at
		 * most two different groups—one for true and one for false.
		 * 
		 * For instance, if you’re vegetarian or have invited a vegetarian friend to
		 * have dinner with you, you may be interested in partitioning the menu into
		 * vegetarian and non-vegetarian dishes:
		 */

		// Partitioning
		{

			Map<Boolean, List<Dish>> partitionedMenu = Inventory.MENU.stream()

					.collect(Collectors.partitioningBy(new Predicate<Dish>() {

						@Override
						public boolean test(Dish t) {
							return t.isVegetarian();
						}
					}));

			System.out.println(partitionedMenu);
		}

		// Partitioning
		{

			Map<Boolean, List<Dish>> partitionedMenu = Inventory.MENU.stream()

					.collect(Collectors.partitioningBy(Dish::isVegetarian));

			System.out.println(partitionedMenu);
		}

		/*
		 * {false=[Dish [name=pork, vegetarian=false, calories=800, type=MEAT], Dish
		 * [name=beef, vegetarian=false, calories=700, type=MEAT], Dish [name=chicken,
		 * vegetarian=false, calories=400, type=MEAT], Dish [name=prawns,
		 * vegetarian=false, calories=400, type=FISH], Dish [name=salmon,
		 * vegetarian=false, calories=450, type=FISH]],
		 * 
		 * true=[Dish [name=french fries, vegetarian=true, calories=530, type=OTHER],
		 * Dish [name=rice, vegetarian=true, calories=350, type=OTHER], Dish
		 * [name=season fruit, vegetarian=true, calories=120, type=OTHER], Dish
		 * [name=pizza, vegetarian=true, calories=550, type=OTHER]]}
		 */

		// Partitioning
		{

			Map<Boolean, List<Dish>> partitionedMenu = Inventory.MENU.stream()

					.collect(Collectors.partitioningBy(Dish::isVegetarian));

			/*
			 * So you could retrieve all the vegetarian dishes by getting from this Map the
			 * value indexed with the key true:
			 */
			System.out.println(partitionedMenu.get(true));
		}

		/*
		 * Note that you could achieve the same result by just filtering the stream
		 * created from the menu List with the same predicate used for partitioning and
		 * then collecting the result in an additional List:
		 */

		List<Dish> partitionedList =

				Inventory.MENU.stream()

						.filter(Dish::isVegetarian)

						.collect(Collectors.toList());

		System.out.println(partitionedList);
	}

}
