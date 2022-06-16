package in.action.chap01;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import in.action.model.Apple;
import in.action.model.Inventory;

public class BFilterApples01 {

	public static void main(String[] args) {

		{
			/*
			 * ==
			 */
			System.out.println("Green Apples: " + filterGreenApples(Inventory.APPLES));

			System.out.println("Heavy Apples: " + filterHeavyApples(Inventory.APPLES));

			
			/*
			 * ==
			 */

			System.out.println("Green Apples: " + filterApples(Inventory.APPLES, BFilterApples01::isGreenApple));

			System.out.println("Heavy Apples: " + filterApples(Inventory.APPLES, BFilterApples01::isHeavyApple));

			/*
			 * ==
			 */

			System.out.println(
					"Green Apples: " + filterApples(Inventory.APPLES, (Apple a) -> "green".equals(a.getColor())));

			System.out.println("Heavy Apples: " + filterApples(Inventory.APPLES, (Apple a) -> a.getWeight() > 150));

			/*
			 * ==
			 */
			System.out.println("Brown Apples: "
					+ filterApples(Inventory.APPLES, (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor())));
		}

		{

			// filter red apples from a list sequentially
			List<Apple> result = Inventory.APPLES.stream()

					.filter((Apple a) -> "red".equals(a.getColor()))

					.collect(Collectors.toList());

			System.out.println("Red Apples: " + result);

		}

		{

			// filter red apples from a list in parallel
			List<Apple> result = Inventory.APPLES.parallelStream()

					.filter((Apple a) -> "red".equals(a.getColor()))

					.collect(Collectors.toList());

			System.out.println("Red Apples: " + result);

		}

	}

	public static List<Apple> filterGreenApples(List<Apple> list) {

		List<Apple> result = new ArrayList<>();
		{
			for (Apple apple : list) {

				// Select only green apples.
				if ("green".equals(apple.getColor())) {

					result.add(apple);
				}
			}
		}
		return result;
	}


	public static List<Apple> filterHeavyApples(List<Apple> list) {

		// An accumulator list for apples.
		List<Apple> result = new ArrayList<>();
		{
			for (Apple apple : list) {

				if (apple.getWeight() > 150) {

					result.add(apple);
				}
			}
		}
		return result;
	}

	public static boolean isGreenApple(Apple apple) {

		return "green".equals(apple.getColor());
	}

	public static boolean isHeavyApple(Apple apple) {

		return apple.getWeight() > 150;
	}

	public static List<Apple> filterApples(List<Apple> list, Predicate<Apple> p) {

		List<Apple> result = new ArrayList<>();
		{
			for (Apple apple : list) {

				/*
				 * Does the apple match the condition represented by p?
				 */
				if (p.test(apple)) {

					result.add(apple);
				}
			}
		}
		return result;
	}
}
