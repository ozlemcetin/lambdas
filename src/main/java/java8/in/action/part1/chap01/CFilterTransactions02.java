package java8.in.action.part1.chap01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java8.in.action.model.Currency;
import java8.in.action.model.Inventory;
import java8.in.action.model.TransactionWithCurrency;

public class CFilterTransactions02 {

	public static void main(String[] args) {

		/*
		 * You’re managing the iteration process yourself. You need to iterate through
		 * each element one by one using a for-each loop and then process the element
		 */
		{
			Map<Currency, List<TransactionWithCurrency>> transactionsByCurrencies = filterExpensiveTransactionsAndGroupByCurrency(
					Inventory.TRANSACTIONS_WITH_CURRENCY);

			System.out.println(transactionsByCurrencies);

		}

		/*
		 * Using the Streams API, you can solve this problem as follows
		 */

		{
			// import static java.util.stream.Collectors.toList;

			Map<Currency, List<TransactionWithCurrency>> transactionsByCurrencies = Inventory.TRANSACTIONS_WITH_CURRENCY.stream()

					// Filter expensive transactions.
					.filter((TransactionWithCurrency t) -> t.getValue() > 7000)

					// Group them by currency
					.collect(Collectors.groupingBy(TransactionWithCurrency::getCurrency));

			System.out.println(transactionsByCurrencies);
		}
	}

	/*
	 * let’s say you need to filter expensive transactions from a list and then
	 * group them by currency
	 */

	public static Map<Currency, List<TransactionWithCurrency>> filterExpensiveTransactionsAndGroupByCurrency(
			List<TransactionWithCurrency> transactions) {

		Map<Currency, List<TransactionWithCurrency>> transactionsByCurrencies = new HashMap<>();
		{

			for (TransactionWithCurrency transaction : transactions) {

				// Filter expensive transactions.
				if (transaction.getValue() > 7000) {

					Currency key = transaction.getCurrency();

					List<TransactionWithCurrency> list = transactionsByCurrencies.get(key);
					{
						/*
						 * If there isn’t any entry in the grouping Map for this currency, create it.
						 */
						if (list == null) {

							list = new ArrayList<>();
							transactionsByCurrencies.put(key, list);
						}
					}

					/*
					 * Add the currently traversed transaction to the List of transactions with the
					 * same currency.
					 */
					list.add(transaction);
				}

			} // for loop
		}
		return transactionsByCurrencies;
	}
}
