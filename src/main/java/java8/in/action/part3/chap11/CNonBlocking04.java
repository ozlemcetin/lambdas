package java8.in.action.part3.chap11;

import java.util.List;

import java8.in.action.part3.chap11.impl.BestPriceFinder;

public class CNonBlocking04 {

	public static void main(String[] args) throws Exception {

		BestPriceFinder finder = new BestPriceFinder();

		/*
		 * [BestPrice price is 123.26, LetsSaveBig price is 169.47, MyFavoriteShop price
		 * is 214.13, BuyItAll price is 184.74]
		 * 
		 * Done in 4032 msecs
		 * 
		 * As you may have expected, the time taken by the findPrices method to run is
		 * just a few milliseconds longer than 4 seconds, because the four shops are
		 * queried sequentially and blocking one after the other, and each of them takes
		 * 1 second to calculate the price of the requested product.
		 * 
		 * How can you improve on this result?
		 */
		{
			long start = System.nanoTime();

			List<String> prices = finder.findPrices("myPhone27S");
			System.out.println(prices);

			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Done in " + duration + " msecs");
		}

		/*
		 * Parallelizing requests using a parallel Stream
		 */

		/*
		 * 
		 * 
		 * [BestPrice price is 123.26, LetsSaveBig price is 169.47, MyFavoriteShop price
		 * is 214.13, BuyItAll price is 184.74]
		 * 
		 * Done in 1180 msecs
		 * 
		 * Well done! It looks like this was a simple but very effective idea: now the
		 * four different shops are queried in parallel, so it takes in total just a bit
		 * more than a second to complete. Can you do even better?
		 * 
		 */
		{
			long start = System.nanoTime();

			List<String> prices = finder.findPrices_parallelStream("myPhone27S");
			System.out.println(prices);

			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Done in " + duration + " msecs");
		}

		/*
		 * Making asynchronous requests with CompletableFutures
		 * 
		 * [BestPrice price is 123.26, LetsSaveBig price is 169.47, MyFavoriteShop price
		 * is 214.13, BuyItAll price is 184.74]
		 * 
		 * Done in 2005 msecs
		 * 
		 */
		{
			long start = System.nanoTime();

			List<String> prices = finder.findPrices_CompletableFuture("myPhone27S");
			System.out.println(prices);

			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Done in " + duration + " msecs");
		}

		/*
		 * Parallelism—via Streams or CompletableFutures?
		 * 
		 * You’ve now seen two different ways to do parallel computing on a collection:
		 * either convert it to a parallel stream and use operations like map on it, or
		 * iterate over the collection and spawn operations within a CompletableFuture.
		 * 
		 * 
		 * The latter provides more control using resizing of thread pools, which helps
		 * ensure that your overall computation doesn’t block just because all of your
		 * fixed number of threads are waiting for I/O.
		 */
	}

}
