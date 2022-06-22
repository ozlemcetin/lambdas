package java8.in.action.part2.chap05;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import java8.in.action.model.Inventory;
import java8.in.action.model.Trader;
import java8.in.action.model.TransactionWithTrader;

public class FPracticeExample12 {

	public static void main(String[] args) throws Exception {

		/*
		 * In this section, you get to practice what you’ve learned about streams so
		 * far. We give a different domain: traders executing transactions. You’re asked
		 * by your manager to find answers to eight queries. Can you do it?
		 * 
		 * 1 Find all transactions in the year 2011 and sort them by value (small to
		 * high).
		 * 
		 * 2 What are all the unique cities where the traders work?
		 * 
		 * 3 Find all traders from Cambridge and sort them by name.
		 * 
		 * 4 Return a string of all traders’ names sorted alphabetically.
		 * 
		 * 5 Are any traders based in Milan?
		 * 
		 * 6 Print all transactions’ values from the traders living in Cambridge.
		 * 
		 * 7 What’s the highest value of all the transactions?
		 * 
		 * 8 Find the transaction with the smallest value.
		 */

		/*
		 * === 1 Find all transactions in the year 2011 and sort them by value (small to
		 * high).
		 */
		{
			List<TransactionWithTrader> transactions2011 = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

					// Pass a predicate to filter to select transactions in year 2011.
					.filter(transaction -> transaction.getYear() == 2011)

					/*
					 * Sort them by using the value of the transaction.
					 * 
					 * (TransactionWithTrader t1, TransactionWithTrader t2) ->
					 * Integer.compare(t1.getValue(), t2.getValue())
					 * 
					 */
					.sorted(Comparator.comparing(TransactionWithTrader::getValue))

					// Collect all the elements of the resulting Stream into a List
					.collect(Collectors.toList());

			/*
			 * [TransactionWithTrader [trader=Trader:Brian in Cambridge, year=2011,
			 * value=300],
			 * 
			 * TransactionWithTrader [trader=Trader:Raoul in Cambridge, year=2011,
			 * value=400]]
			 */
			System.out.println(transactions2011);
		}

		/*
		 * === 2 What are all the unique cities where the traders work?
		 */

		{
			{
				List<String> cities = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

						// Extract the city from each trader associated with the transaction.
						.map(transaction -> transaction.getTrader().getCity())

						// Select only unique cities.
						.distinct()

						.collect(Collectors.toList());

				// [Cambridge, Milan]
				System.out.println(cities);
			}

			{
				/*
				 * You haven’t seen this yet, but you could also drop distinct() and use toSet()
				 * instead, which would convert the stream into a set.
				 */

				Set<String> cities = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

						.map(transaction -> transaction.getTrader().getCity())

						.collect(Collectors.toSet());

				// [Milan, Cambridge]
				System.out.println(cities);
			}

		}

		/*
		 * === 3 Find all traders from Cambridge and sort them by name.
		 */

		{
			List<Trader> traders = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

					// Extract all traders from the transactions.
					.map(transaction -> transaction.getTrader())

					// Select only the traders from Cambridge.
					.filter(trader -> trader.getCity().equals("Cambridge"))

					// Make sure you don’t have any duplicates.
					.distinct()

					// Sort the resulting stream of traders by their names.
					.sorted(Comparator.comparing(Trader::getName))

					.collect(Collectors.toList());

			/*
			 * [Trader:Alan in Cambridge,
			 * 
			 * Trader:Brian in Cambridge,
			 * 
			 * Trader:Raoul in Cambridge]
			 */
			System.out.println(traders);
		}

		/*
		 * === 4 Return a string of all traders’ names sorted alphabetically.
		 */
		{

			{
				/*
				 * Combine each name one by one to form a String that concatenates all the
				 * names.
				 */
				BinaryOperator<String> accumulator = (String result, String name) -> result + " " + name;

				String traderStr = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

						// Extract all the names of the traders Fas a Stream of Strings.
						.map(transaction -> transaction.getTrader().getName())

						.distinct()

						// Sort the names alphabetically.
						.sorted()

						.reduce("", accumulator);

				// Alan Brian Mario Raoul
				System.out.println(traderStr);

			}

			{
				String traderStr = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

						// Extract all the names of the traders Fas a Stream of Strings.
						.map(transaction -> transaction.getTrader().getName())

						.distinct()

						// Sort the names alphabetically.
						.sorted()

						.reduce("", (s1, s2) -> s1 + s2);

				// AlanBrianMarioRaoul
				System.out.println(traderStr);
			}

			{
				/*
				 * Note that this solution isn’t very efficient (all Strings are repeatedly
				 * concatenated, which creates a new String object at each iteration).
				 * 
				 * In the next chapter, you’ll see a more efficient solution that uses joining()
				 * as follows (which internally makes use of a StringBuilder):
				 */
				String traderStr = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

						.map(transaction -> transaction.getTrader().getName())

						.distinct()

						.sorted()

						/*
						 * Returns a Collector that concatenates the input elements into a String, in
						 * encounter order.
						 */
						.collect(Collectors.joining());

				// AlanBrianMarioRaoul
				System.out.println(traderStr);
			}

		}

		/*
		 * === 5 Are any traders based in Milan?
		 */
		{
			{
				Optional<TransactionWithTrader> milanBasedTransaction = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

						.filter(transaction -> transaction.getTrader().getCity().equals("Milan"))

						.findAny();

				// Optional[TransactionWithTrader
				// [trader=Trader:Mario in Milan, year=2012, value=710]]
				System.out.println(milanBasedTransaction);
			}

			{
				boolean isMilanBased = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

						// Pass a predicate to anyMatch to check if there’s a trader from Milan.
						.anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

				// true
				System.out.println(isMilanBased);
			}
		}

		/*
		 * === 6 Print all transactions’ values from the traders living in Cambridge.
		 */
		{
			// 300 1000 400 950
			Inventory.TRANSACTIONS_WITH_TRADERS.stream()

					// Select the transactions where the traders live in Cambridge.
					.filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))

					// Extract the values
					// transaction -> transaction.getValue()
					.map(TransactionWithTrader::getValue)

					// Print each of these trades.
					.forEach(System.out::println);
		}

		/*
		 * === 7 What’s the highest value of all the transactions?
		 */
		{
			{

				BinaryOperator<Integer> accumulator = (Integer result, Integer x) -> Math.max(result, x);

				Optional<Integer> highestValue = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

						.map(transaction -> transaction.getValue())

						.reduce(accumulator);

				// Optional[1000]
				System.out.println(highestValue);

			}

			{
				Optional<Integer> highestValue = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

						// Extract the value of each transaction.
						.map(TransactionWithTrader::getValue)

						// Calculate the max of the resulting stream.
						.reduce(Integer::max);

				// Optional[1000]
				System.out.println(highestValue);

			}
		}

		/*
		 * === 8 Find the transaction with the smallest value.
		 */
		{

			// JAVA 7
			{

				BinaryOperator<TransactionWithTrader> accumulator = (TransactionWithTrader t1,
						TransactionWithTrader t2) -> t1.getValue() < t2.getValue() ? t1 : t2;

				TransactionWithTrader result = null;
				{
					boolean foundAny = false;

					for (TransactionWithTrader element : Inventory.TRANSACTIONS_WITH_TRADERS) {

						if (!foundAny) {

							foundAny = true;
							result = element;

						} else {

							result = accumulator.apply(result, element);
						}

					} // for loop

					// return foundAny ? Optional.of(result) : Optional.empty();

				}

				// TransactionWithTrader
				// [trader=Trader:Brian in Cambridge, year=2011, value=300]
				System.out.println(result);
			}

			// JAVA 8
			{

				/*
				 * Find the smallest transaction by repeatedly comparing the values of each
				 * transaction.
				 */
				Optional<TransactionWithTrader> smallestTransaction = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

						.reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

				System.out.println(smallestTransaction);

			}

			// JAVA 8
			{
				/*
				 * You can do better. A stream supports the methods min and max that take a
				 * Comparator as argument to specify which key to compare with when calculating
				 * the minimum or maximum:
				 */

				Optional<TransactionWithTrader> smallestTransaction = Inventory.TRANSACTIONS_WITH_TRADERS.stream()

						.min(Comparator.comparing(TransactionWithTrader::getValue));

				System.out.println(smallestTransaction);
			}

		}

	}
}
