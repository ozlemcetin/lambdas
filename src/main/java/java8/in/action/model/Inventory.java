package java8.in.action.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {

	/*
	 * APPLES
	 */
	public static final List<Apple> APPLES = Arrays.asList(new Apple(80, "green"),

			new Apple(150, "red"),

			new Apple(150, "green"),

			new Apple(120, "red"),

			new Apple(70, "yellow"),

			new Apple(160, "red"),

			new Apple(85, "brown"));

	/*
	 * TRANSACTIONS
	 */
	public static final List<TransactionWithCurrency> TRANSACTIONS_WITH_CURRENCY = Arrays.asList(

			new TransactionWithCurrency(Currency.EUR, 1500.0),

			new TransactionWithCurrency(Currency.USD, 2300.0),

			new TransactionWithCurrency(Currency.GBP, 9900.0),

			new TransactionWithCurrency(Currency.EUR, 1100.0),

			new TransactionWithCurrency(Currency.JPY, 7800.0),

			new TransactionWithCurrency(Currency.CHF, 6700.0),

			new TransactionWithCurrency(Currency.EUR, 5600.0),

			new TransactionWithCurrency(Currency.USD, 4500.0),

			new TransactionWithCurrency(Currency.CHF, 3400.0),

			new TransactionWithCurrency(Currency.GBP, 3200.0),

			new TransactionWithCurrency(Currency.USD, 4600.0),

			new TransactionWithCurrency(Currency.JPY, 5700.0),

			new TransactionWithCurrency(Currency.EUR, 6800.0));

	/*
	 * MENU
	 */
	public static final List<Dish> MENU = Arrays.asList(

			new Dish("pork", false, 800, Dish.Type.MEAT),

			new Dish("beef", false, 700, Dish.Type.MEAT),

			new Dish("chicken", false, 400, Dish.Type.MEAT),

			new Dish("french fries", true, 530, Dish.Type.OTHER),

			new Dish("rice", true, 350, Dish.Type.OTHER),

			new Dish("season fruit", true, 120, Dish.Type.OTHER),

			new Dish("pizza", true, 550, Dish.Type.OTHER),

			new Dish("prawns", false, 400, Dish.Type.FISH),

			new Dish("salmon", false, 450, Dish.Type.FISH));

	/*
	 * DISH_TAGS
	 */
	public static final Map<String, List<String>> DISH_TAGS = new HashMap<>();

	static {

		DISH_TAGS.put("pork", Arrays.asList("greasy", "salty"));

		DISH_TAGS.put("beef", Arrays.asList("salty", "roasted"));

		DISH_TAGS.put("chicken", Arrays.asList("fried", "crisp"));

		DISH_TAGS.put("french fries", Arrays.asList("greasy", "fried"));

		DISH_TAGS.put("rice", Arrays.asList("light", "natural"));

		DISH_TAGS.put("season fruit", Arrays.asList("fresh", "natural"));

		DISH_TAGS.put("pizza", Arrays.asList("tasty", "salty"));

		DISH_TAGS.put("prawns", Arrays.asList("tasty", "roasted"));

		DISH_TAGS.put("salmon", Arrays.asList("delicious", "fresh"));
	}

	/*
	 * TRADERS
	 */

	public static final Trader TRADER_BRIAN = new Trader("Brian", "Cambridge");
	public static final Trader TRADER_RAOUL = new Trader("Raoul", "Cambridge");
	public static final Trader TRADER_MARIO = new Trader("Mario", "Milan");
	public static final Trader TRADER_ALAN = new Trader("Alan", "Cambridge");

	/*
	 * TRANSACTIONS
	 */

	public static final List<TransactionWithTrader> TRANSACTIONS_WITH_TRADERS = Arrays.asList(

			new TransactionWithTrader(TRADER_BRIAN, 2011, 300),

			new TransactionWithTrader(TRADER_RAOUL, 2012, 1000),

			new TransactionWithTrader(TRADER_RAOUL, 2011, 400),

			new TransactionWithTrader(TRADER_MARIO, 2012, 710),

			new TransactionWithTrader(TRADER_MARIO, 2012, 700),

			new TransactionWithTrader(TRADER_ALAN, 2012, 950));
}
