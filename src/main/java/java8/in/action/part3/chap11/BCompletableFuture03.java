package java8.in.action.part3.chap11;

import java.util.concurrent.Future;

import java8.in.action.part3.chap11.impl.Shop;

public class BCompletableFuture03 {

	public static void main(String[] args) throws Exception {

		Shop shop = new Shop("BestShop");

		long start = System.nanoTime();
		Future<Double> futurePrice = shop.getPriceAsync("myPhone");

		{
			long incocationTime = ((System.nanoTime() - start) / 1_000_000);
			System.out.println("Invocation returned after " + incocationTime + " msecs");
		}

		try {

			System.out.println("Price is " + futurePrice.get());

		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}

		{
			long retrivalTime = ((System.nanoTime() - start) / 1_000_000);
			System.out.println("Price returned after " + retrivalTime + " msecs");
		}

	}

}
