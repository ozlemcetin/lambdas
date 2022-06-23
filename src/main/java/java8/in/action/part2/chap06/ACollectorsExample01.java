package java8.in.action.part2.chap06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java8.in.action.model.Currency;
import java8.in.action.model.Inventory;
import java8.in.action.model.TransactionWithCurrency;

public class ACollectorsExample01 {

	public static void main(String[] args) throws Exception {

		/*
		 * You learned in the previous chapter that streams help you process collections
		 * with database-like operations. You can view Java 8 streams as fancy lazy
		 * iterators of sets of data.
		 * 
		 * They support two types of operations: intermediate operations such as filter
		 * or map and terminal operations such as count, findFirst, forEach, and reduce.
		 * 
		 * Intermediate operations can be chained to convert a stream into another
		 * stream. These operations don’t consume from a stream; their purpose is to set
		 * up a pipeline of streams.
		 * 
		 * By contrast, terminal operations do consume from a stream—to produce a final
		 * result (for example, returning the largest element in a stream). They can
		 * often shorten computations by optimizing the pipeline of a stream.
		 * 
		 * We already used the collect terminal operation on streams in chapters 4 and
		 * 5, but we employed it there mainly to combine all the elements of a Stream
		 * into a List.
		 * 
		 * In this chapter, you’ll discover that collect is a reduction operation, just
		 * like reduce, that takes as argument various recipes for accumulating the
		 * elements of a stream into a summary result.
		 * 
		 * These recipes are defined by a new Collector interface, so it’s important to
		 * distinguish Collection, Collector, and collect!
		 */

		{
			/*
			 * Imagine a scenario where you have a List of Transactions, and you want to
			 * group them based on their nominal currency.
			 * 
			 */

			/*
			 * {GBP=
			 * 
			 * [Transaction [currency=GBP, value=9900.0], Transaction [currency=GBP,
			 * value=3200.0]],
			 * 
			 * CHF=
			 * 
			 * [Transaction [currency=CHF, value=6700.0], Transaction [currency=CHF,
			 * value=3400.0]],
			 * 
			 * EUR=
			 * 
			 * [Transaction [currency=EUR, value=1500.0], Transaction [currency=EUR,
			 * value=1100.0], Transaction [currency=EUR, value=5600.0], Transaction
			 * [currency=EUR, value=6800.0]],
			 * 
			 * JPY=
			 * 
			 * [Transaction [currency=JPY, value=7800.0], Transaction [currency=JPY,
			 * value=5700.0]],
			 * 
			 * 
			 * USD=
			 * 
			 * [Transaction [currency=USD, value=2300.0], Transaction [currency=USD,
			 * value=4500.0], Transaction [currency=USD, value=4600.0]]}
			 */

		}

		{
			/*
			 * In pre-lambda Java, even a simple use case like this is cumbersome to
			 * implement, as shown in the following listing.
			 */

			// JAVA 7
			{

				/*
				 * Create the Map where the grouped transaction will be accumulated.
				 */
				Map<Currency, List<TransactionWithCurrency>> map = new HashMap<>();
				{

					// Iterate the List of Transactions.
					for (TransactionWithCurrency transaction : Inventory.TRANSACTIONS_WITH_CURRENCY) {

						// Extract the Transaction’s currency.
						Currency key = transaction.getCurrency();

						List<TransactionWithCurrency> list = map.get(key);
						{
							/*
							 * If there’s no entry in the grouping Map for this currency, create it.
							 */
							if (list == null) {

								list = new ArrayList<>();
								map.put(key, list);
							}
						}

						/*
						 * Add the currently traversed Transaction to the List of Transactions with the
						 * same currency.
						 */
						list.add(transaction);

					} // for loop
				}

				System.out.println(map);
			}

			// JAVA 8
			{
				// “Group a list of transactions by their currency.”

				Map<Currency, List<TransactionWithCurrency>> map = Inventory.TRANSACTIONS_WITH_CURRENCY.stream()

						.collect(Collectors.groupingBy(TransactionWithCurrency::getCurrency));

				System.out.println(map);
			}

		}

	}
}
