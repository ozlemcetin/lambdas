package java8.in.action.part1.chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java8.in.action.model.Apple;
import java8.in.action.model.Inventory;
import java8.in.action.model.TransactionWithCurrency;
import java8.in.action.part1.chap02.impl.InventoryPredicate;

public class BFilterTransactions02 {

	public static void main(String[] args) {

		/*
		 * ===
		 */

		// Apples
		System.out.println("Green Apples: " + filter(Inventory.APPLES, new InventoryPredicate<Apple>() {

			@Override
			public boolean test(Apple t) {

				return "green".equals(t.getColor());
			}
		}));

		/*
		 * ===
		 */

		// Red Apples
		System.out.println("Red Apples: " + filter(Inventory.APPLES, (Apple apple) -> "red".equals(apple.getColor())));

		// Expensive Transactions.
		System.out.println(
				"Expensive Transactions: " + filter(Inventory.TRANSACTIONS_WITH_CURRENCY, (TransactionWithCurrency t) -> t.getValue() > 7000));

		// Numbers
		{
			List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

			System.out.println("Even Numbers " + filter(numbers, (Integer a) -> a % 2 == 0));
		}

	}

	/*
	 * You can now use the method filter with a List of bananas, oranges, Integers,
	 * or Strings!
	 */

	// Introducing a type parameter T
	public static <T> List<T> filter(List<T> list, InventoryPredicate<T> p) {

		List<T> result = new ArrayList<>();
		{
			for (T t : list) {

				/*
				 * Does the T match the condition represented by p?
				 */
				if (p.test(t)) {

					result.add(t);
				}
			}
		}
		return result;
	}

}
