package in.action.chap01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import in.action.model.Currency;
import in.action.model.Inventory;
import in.action.model.Transaction;

public class FilterTransactions02 {

	public static void main(String[] args) {

		/*
		 * You’re managing the iteration process yourself. You need to iterate through
		 * each element one by one using a for-each loop and then process the element
		 */
		{
			Map<Currency, List<Transaction>> transactionsByCurrencies = filterExpensiveTransactionsAndGroupByCurrency(
					Inventory.TRANSACTIONS);

			System.out.println(transactionsByCurrencies);

		}

		/*
		 * Using the Streams API, you can solve this problem as follows
		 */

		{
			// import static java.util.stream.Collectors.toList;

			Map<Currency, List<Transaction>> transactionsByCurrencies = Inventory.TRANSACTIONS.stream()

					// Filter expensive transactions.
					.filter((Transaction t) -> t.getValue() > 7000)

					// Group them by currency
					.collect(Collectors.groupingBy(Transaction::getCurrency));

			System.out.println(transactionsByCurrencies);
		}
	}

	/*
	 * let’s say you need to filter expensive transactions from a list and then
	 * group them by currency
	 */

	public static Map<Currency, List<Transaction>> filterExpensiveTransactionsAndGroupByCurrency(
			List<Transaction> transactions) {

		Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
		{

			for (Transaction transaction : transactions) {

				// Filter expensive transactions.
				if (transaction.getValue() > 7000) {

					Currency key = transaction.getCurrency();

					List<Transaction> list = transactionsByCurrencies.get(key);
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
