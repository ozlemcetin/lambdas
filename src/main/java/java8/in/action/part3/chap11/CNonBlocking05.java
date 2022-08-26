package java8.in.action.part3.chap11;

import java.util.List;

import java8.in.action.part3.chap11.impl.BestPriceFinderWithDiscount;

public class CNonBlocking05 {

	public static void main(String[] args) throws Exception {

		BestPriceFinderWithDiscount finder = new BestPriceFinderWithDiscount();

		/*
		 * [BestPrice price is 110.93, LetsSaveBig price is 135.58, MyFavoriteShop price
		 * is 192.72, BuyItAll price is 184.74, ShopEasy price is 167.28]
		 * 
		 * Done in 10028 msecs
		 */

		{
			long start = System.nanoTime();

			List<String> prices = finder.findPrices_WithDiscountService("myPhone27S");
			System.out.println(prices);

			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Done in " + duration + " msecs");
		}

	}

}
