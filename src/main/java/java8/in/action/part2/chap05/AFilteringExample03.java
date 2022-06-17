package java8.in.action.part2.chap05;

import java.util.List;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class AFilteringExample03 {

	public static void main(String[] args) throws Exception {

		/*
		 * How would you use streams to filter the first two meat dishes?
		 */
		{

			List<Dish> dishes = Inventory.MENU.stream()

					// pork, beef, chicken
					.filter(d -> Dish.Type.MEAT == d.getType())

					.limit(2)

					.collect(Collectors.toList());

			// pork, beef
			System.out.println(dishes);
		}

	}
}
