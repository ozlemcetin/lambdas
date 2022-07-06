package java8.in.action.part2.chap06;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import java8.in.action.model.Dish;
import java8.in.action.model.Inventory;

public class ACollectorsExample03 {

	public static void main(String[] args) throws Exception {

		/*
		 * Suppose you want to find the highest-calorie dish in the menu. You can use
		 * two collectors, Collectors.maxBy and Collectors.minBy, to calculate the
		 * maximum or minimum value in a stream. These two collectors take a Comparator
		 * as argument to compare the elements in the stream.
		 * 
		 * Here you create a Comparator comparing dishes based on their calorie content
		 * and pass it to Collectors.maxBy:
		 */

		{
			Comparator<Dish> comparator = new Comparator<Dish>() {

				@Override
				public int compare(Dish o1, Dish o2) {
					return Integer.compare(o1.getCalories(), o2.getCalories());
				}
			};

			Optional<Dish> mostCalorieDish = Inventory.MENU.stream()

					.collect(Collectors.maxBy(comparator));

			System.out.println(mostCalorieDish);

		}

		{
			Comparator<Dish> comparator = Comparator.comparing(Dish::getCalories);

			Optional<Dish> mostCalorieDish = Inventory.MENU.stream()

					.collect(Collectors.maxBy(comparator));

			System.out.println(mostCalorieDish);

		}

		{

			Optional<Dish> mostCalorieDish = Inventory.MENU.stream()

					.collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));

			System.out.println(mostCalorieDish);

		}

		/*
		 * You may wonder what the Optional<Dish> is about. To answer this we need to
		 * ask the question ”What if menu were empty?” There’s no dish to return! Java 8
		 * introduces Optional, which is a container that may or may not contain a
		 * value. Here it perfectly represents the idea that there may or may not be a
		 * dish returned.
		 */
	}
}
