package java8.in.action.part3.chap11.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class BestPriceFinder {

	/*
	 * So you’ve been asked to develop a best-price-finder application, and all the
	 * shops you have to query provide only the same synchronous API implemented as
	 * shown at the beginning of section 11.2.
	 * 
	 * In other words, you have a list of shops, like this one:
	 */
	List<Shop> shops = Arrays.asList(new Shop("BestPrice"),

			new Shop("LetsSaveBig"),

			new Shop("MyFavoriteShop"),

			new Shop("BuyItAll"));

	/*
	 * you need to set up an Executor with a fixed number of threads equal to the
	 * number of shops you have to query, so there will be exactly one thread for
	 * each shop.
	 * 
	 * But you must also set an upper limit of 100 threads in order to avoid a
	 * server crash for a larger number of shops, as shown in the following listing.
	 */

	/*
	 * Create a thread pool with a number of threads equal to the minimum between
	 * 100 and the number of shops.
	 */

	private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {

		/*
		 * Note that you’re creating a pool made of daemon threads. A Java program can’t
		 * terminate or exit while a normal thread is executing, so a leftover thread
		 * waiting for a never-satisfiable event causes problems
		 * 
		 * By contrast, marking a thread as a daemon means it can be killed on program
		 * termination. There’s no performance difference
		 */

		@Override
		public Thread newThread(Runnable r) {

			/*
			 * Use daemon threads—they don’t prevent the termination of the program.
			 */
			Thread t = new Thread(r);
			t.setDaemon(true);
			return t;
		}

	});

	/*
	 * You have to implement a method with the following signature, that given the
	 * name of a product returns a List of strings, where each string contains the
	 * name of a shop and the price of the requested product in that shop:
	 */
	public List<String> findPrices(String product) {

		List<String> prices = shops.stream()

				.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))

				.collect(Collectors.toList());

		return prices;
	}

	public List<String> findPrices_parallelStream(String product) {

		List<String> prices = shops.parallelStream()

				.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))

				.collect(Collectors.toList());

		return prices;
	}

	/*
	 * Let’s try to turn all the synchronous invocations to the different shops in
	 * the findPrices method into asynchronous invocations, using what you learned
	 * so far about CompletableFutures.
	 */

	public List<String> findPrices_CompletableFuture(String product) {

		/*
		 * Using this approach, you obtain a List<CompletableFuture<String>>, where each
		 * CompletableFuture in the List will contain the String name of a shop when its
		 * computation is completed.
		 */

		/*
		 * you should now create the CompletableFuture retrieving the price of the
		 * requested product from a given shop as follows:
		 */
		List<CompletableFuture<String>> priceFutures = shops.stream()

				.map(shop -> CompletableFuture.supplyAsync(
						() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), executor))

				.collect(Collectors.toList());

		/*
		 * But because the findPrices method you’re trying to reimplement using
		 * CompletableFutures has to return just a List<String>, you’ll have to wait for
		 * the completion of all these futures and extract the value they contain before
		 * returning the List.
		 * 
		 * To achieve this result, you can apply a second map operation to the original
		 * List<CompletableFuture<String>>, invoking a join on all the futures in the
		 * List and then waiting for their completion one by one.
		 * 
		 * Note that the join method of the CompletableFuture class has the same meaning
		 * as the get method also declared in the Future interface, with the only
		 * difference being that join doesn’t throw any checked exception.
		 * 
		 * By using it you don’t have to bloat the lambda expression passed to this
		 * second map with a try/catch block.
		 */

		/*
		 * if you had processed the stream in a single pipeline, you would have
		 * succeeded only in executing all the requests to different shops synchronously
		 * and sequentially.
		 * 
		 * This is because the creation of each CompletableFuture to interrogate a given
		 * shop would start only when the computation of the previous one had completed,
		 * 
		 * letting the join method return the result of that computation
		 */
		return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());

	}

}
