package java8.in.action.part3.chap11;

import java.util.concurrent.Future;

import java8.in.action.part3.chap11.impl.Shop;
import java8.in.action.part3.chap11.impl.ExternalServices;

public class BCompletableFuture02 {

	public static void main(String[] args) throws Exception {

		Shop shop = new Shop("CandyShop");

		long startTime = System.nanoTime();

		/*
		 * Query the shop to retrieve the price of a product.
		 */

		/*
		 * Because the shop provides an asynchronous API, this invocation almost
		 * immediately returns the Future, through which the client can retrieve the
		 * product’s price at a later time
		 */

		/*
		 * This allows the client to do other tasks, like querying other shops, instead
		 * of remaining blocked waiting for the first shop to produce the requested
		 * result
		 */
		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");

		{
			long invocationTime = ((System.nanoTime() - startTime) / 1_000_000);
			System.out.println("Invocation returned after " + invocationTime + " msecs");
		}

		/*
		 * Do some more tasks, like querying other shops
		 */
		{
			ExternalServices.doSomethingElse();
		}

		/*
		 * while the price of the product is being calculated
		 */

		/*
		 * Read the price from the Future or block until it won’t be available
		 */

		/*
		 * Later, when there are no other meaningful jobs that the client could do
		 * without having the product price, it can invoke get on the Future.
		 * 
		 * By doing so the client either unwraps the value contained in the Future (if
		 * the asynchronous task is already finished) or remains blocked until that
		 * value is available.
		 */
		{

			try {

				double price = futurePrice.get();

				System.out.printf("Price is %.2f%n", price);

			} catch (Exception e) {

				throw new RuntimeException(e);
			}

		}

		{
			long retrievalTime = ((System.nanoTime() - startTime) / 1_000_000);
			System.out.println("Price returned after " + retrievalTime + " msecs");
		}

	}

}
