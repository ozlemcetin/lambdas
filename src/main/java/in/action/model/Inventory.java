package in.action.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import in.action.chap02.impl.InventoryPredicate;

public class Inventory {

	public static final List<Apple> APPLES = Arrays.asList(new Apple(80, "green"),

			new Apple(150, "green"),

			new Apple(120, "red"),

			new Apple(70, "yellow"),

			new Apple(160, "red"),

			new Apple(85, "brown"));

	public static final List<Transaction> TRANSACTIONS = Arrays.asList(new Transaction(Currency.EUR, 1500.0),

			new Transaction(Currency.USD, 2300.0),

			new Transaction(Currency.GBP, 9900.0),

			new Transaction(Currency.EUR, 1100.0),

			new Transaction(Currency.JPY, 7800.0),

			new Transaction(Currency.CHF, 6700.0),

			new Transaction(Currency.EUR, 5600.0),

			new Transaction(Currency.USD, 4500.0),

			new Transaction(Currency.CHF, 3400.0),

			new Transaction(Currency.GBP, 3200.0),

			new Transaction(Currency.USD, 4600.0),

			new Transaction(Currency.JPY, 5700.0),

			new Transaction(Currency.EUR, 6800.0));

	

}
