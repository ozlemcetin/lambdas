package in.action.chap02;

import java.util.Comparator;

import in.action.model.Apple;
import in.action.model.Inventory;

public class SortApples03 {

	public static void main(String[] args) {

		System.out.println(Inventory.APPLES);

		/*
		 * ==
		 */
		if (false) {

			// sort the inventory by increasing weight using an anonymous class:
			Inventory.APPLES.sort(new Comparator<Apple>() {

				@Override
				public int compare(Apple a1, Apple a2) {

					return a1.getWeight().compareTo(a2.getWeight());
				}
			});

			System.out.println(Inventory.APPLES);

		}

		/*
		 * ==
		 */

		/*
		 * using a lambda expression you can create a custom Comparator object in a more
		 * concise way
		 * 
		 * It looks like you’re just passing the body of the method compare
		 */

		Inventory.APPLES.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

		System.out.println(Inventory.APPLES);
	}

}
