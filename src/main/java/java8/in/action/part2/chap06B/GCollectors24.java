package java8.in.action.part2.chap06B;

import java.util.List;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class GCollectors24 {

	public static void main(String[] args) throws Exception {

		{
			List<Dish> dishes = Inventory.MENU.stream()

					.collect(Collectors.toList());

			System.out.println(dishes);
		}

		// ToListCollector
		{
			List<Dish> dishes = Inventory.MENU.stream()

					.collect(new ToListCollector<Dish>());

			System.out.println(dishes);
		}
	}

}
