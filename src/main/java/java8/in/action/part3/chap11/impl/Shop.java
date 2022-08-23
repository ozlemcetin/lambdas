package java8.in.action.part3.chap11.impl;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

	private final String name;
	private final Random random;

	public Shop(String name) {

		if (name == null || name.length() <= 2) {
			throw new RuntimeException("Shop's name should be at least 3 characters");
		}

		this.name = name;

		/*
		 * It randomizes the price based on the product name by using the result of
		 * charAt as a number
		 */
		this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
	}

	/*
	 * To start implementing the best-price-finder application, let’s begin by
	 * defining the API that each single shop should provide.
	 * 
	 * First, a shop declares a method that returns the price of a product given its
	 * name:
	 */

	/*
	 * The internal implementation of this method would query the shop’s database
	 * but probably also perform other time-consuming tasks, such as contacting
	 * various other external services (for example, the shop’s suppliers or
	 * manufacturer-related promotional discounts).
	 * 
	 * To fake such a long-running method execution, in the rest of this chapter we
	 * simply use a delay method, which introduces an artificial delay of 1 second,
	 * defined in the following listing.
	 */

	/*
	 * For the purpose of this chapter, the getPrice method can be modeled by
	 * calling delay and then returning a randomly calculated value for the price,
	 * as shown in the next listing.
	 */

	// synchronous API
	public double getPrice(String product) {

		return calculatePrice(product);

	}

	/*
	 * Let’s now suppose that all the shops have agreed to use a centralized
	 * discount service. This service uses five different discount codes, and each
	 * code has a different discount percentage.
	 * 
	 * Also suppose the shops have agreed to change the format of the result of the
	 * get- Price method. It now returns a String in the format
	 * ShopName:price:DiscountCode.
	 */

	// synchronous API
	public String getPrice_WithDiscount(String product) {

		double price = calculatePrice(product);

		DiscountService.Code code = null;
		{
			int randomInt = random.nextInt(DiscountService.Code.values().length);
			code = DiscountService.Code.values()[randomInt];
		}

		return String.format("%s:%s:%s", getName(), ExternalServices.format(price), code);

	}

	/*
	 * Here you create an instance of CompletableFuture, representing an
	 * asynchronous computation and containing a result when it becomes available.
	 * 
	 * Then you fork a different Thread that will perform the actual price
	 * calculation and return the Future instance without waiting for that
	 * long-lasting calculation to terminate.
	 * 
	 * When the price of the requested product is finally available, you can
	 * complete the Completable- Future using its complete method to set the value.
	 * 
	 * Obviously this feature also explains the name of this new Future
	 * implementation. A client of this API can invoke it, as shown in the next
	 * listing.
	 */

	// an asynchronous API
	public Future<Double> getPriceAsync(String product) {

		/*
		 * Create the CompletableFuture that will contain the result of the computation
		 */
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		{

			/*
			 * Execute the computation asynchronously in a different Thread.
			 * 
			 * new Thread(new Runnable() {
			 * 
			 * 
			 * public void run() {
			 * 
			 * 
			 * } });
			 */
			new Thread(() -> {

				try {

					/*
					 * To make the client aware of the reason the shop wasn’t able to provide the
					 * price of the requested product, you have to propagate the Exception that
					 * caused the problem inside the CompletableFuture through its
					 * completeExceptionally method.
					 */
					double value = calculatePrice(product);

					/*
					 * Set the value returned by the long computation on the Future when it becomes
					 * available.
					 */

					/*
					 * If the price calculation completed normally, complete the Future with the
					 * price.
					 */
					futurePrice.complete(value);

				} catch (Exception ex) {

					/*
					 * Otherwise, complete it exceptionally with the Exception that caused the
					 * failure.
					 */
					futurePrice.completeExceptionally(ex);
				}

			}).start();

		}

		/*
		 * Return the Future without waiting for the computation of the result it
		 * contains to be completed
		 */
		return futurePrice;
	}

	public Future<Double> getPriceAsync_WithFactoryMethod(String product) {

		/*
		 * Until now you’ve created CompletableFutures and completed them
		 * programmatically, when it seemed convenient to do so, but the
		 * CompletableFuture class itself comes with lots of handy factory methods that
		 * can make this process far easier and less verbose.
		 * 
		 * For example, the supplyAsync method can let you rewrite the getPriceAsync
		 * method in listing 11.4 with a single statement, as shown in the following
		 * listing.
		 */

		/*
		 * The supplyAsync method accepts a Supplier as argument and returns a
		 * Completable- Future that will be asynchronously completed with the value
		 * obtained by invoking that Supplier.
		 */

		/*
		 * This Supplier will be run by one of the Executors in the ForkJoin- Pool, but
		 * you can specify a different Executor by passing it as a second argument to
		 * the overloaded version of this method
		 */

		/*
		 * Also note that the CompletableFuture returned by the getPriceAsync method in
		 * listing 11.7 is totally equivalent to the one you created and completed
		 * manually in listing 11.6, meaning it provides the same error management you
		 * carefully added.
		 */
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}

	/*
	 * This implies that when the consumer of this API (in this case, the
	 * best-price-finder application) invokes this method, it will remain blocked
	 * and then idle for 1 second while waiting for its synchronous completion:
	 * 
	 * ExternalServices.delay();
	 * 
	 * This is unacceptable, especially considering that the best-price-finder
	 * application will have to repeat this operation for all the shops in its
	 * network
	 * 
	 * 
	 * In the subsequent sections of this chapter, you’ll discover how you can
	 * resolve this problem by consuming this synchronous API in an asynchronous way
	 */
	private double calculatePrice(String product) {

		if (product == null || product.length() <= 1) {
			throw new RuntimeException("Product's name should be at least 2 characters");
		}

		{
			ExternalServices.delay();
		}

		if (false) {

			/*
			 * The client will now be notified with an ExecutionException (which takes an
			 * Exception parameter containing the cause—the original Exception thrown by the
			 * price calculation method).
			 */
			throw new RuntimeException("Product Not Available");
		}

		return (random.nextDouble() * product.charAt(0)) + product.charAt(1);
	}

	public String getName() {
		return name;
	}

}
