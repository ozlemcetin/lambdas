package java8.in.action.part3.chap11.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class BestPriceFinderWithDiscount {

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

	private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {

		@Override
		public Thread newThread(Runnable r) {

			Thread t = new Thread(r);
			t.setDaemon(true);
			return t;
		}

	});

	public List<String> findPrices_WithDiscountService(String product) {

		return shops.stream()

				/*
				 * Retrieve the nondiscounted price from each shop.
				 */
				.map(shop -> shop.getPrice_WithDiscount(product))

				/*
				 * Transform the Strings returned by the shops in Quote objects.
				 */

				.map(Quote::parse)

				/*
				 * Contact the Discount service to apply the discount on each Quote.
				 */
				.map(DiscountService::applyDiscount)

				.collect(Collectors.toList());
	}

	public List<String> findPrices_WithDiscountService_CompletableFuture(String product) {

		List<CompletableFuture<String>> priceFutures = shops.stream()

				/*
				 * Asynchronously retrieve the nondiscounted price from each shop.
				 */
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice_WithDiscount(product), executor))

				/*
				 * Transform the String returned by a shop into a Quote object when it becomes
				 * available.
				 */
				.map(future -> future.thenApply(Quote::parse))

				/*
				 * Compose the resulting Future with another asynchronous task, applying the
				 * discount code.
				 */
				.map(future -> future.thenCompose(
						quote -> CompletableFuture.supplyAsync(() -> DiscountService.applyDiscount(quote), executor)))

				.collect(Collectors.toList());

		/*
		 * Wait for all the Futures in the stream to be completed and extract their
		 * respective results.
		 */
		return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());

	}
}
