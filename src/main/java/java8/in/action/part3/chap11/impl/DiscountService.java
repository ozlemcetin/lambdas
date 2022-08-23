package java8.in.action.part3.chap11.impl;

public class DiscountService {

	public enum Code {

		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

		private final int percentage;

		Code(int percentage) {
			this.percentage = percentage;
		}

		public int getPercentage() {
			return percentage;
		}

	}

	/*
	 * Apply the discount code to the original price.
	 */

	public static String applyDiscount(Quote quote) {

		return String.format("%s price is %.2f", quote.getShopName(),

				DiscountService.apply(quote.getPrice(), quote.getDiscountCode()));
	}

	/*
	 * Simulate a delay in the Discount service response.
	 */

	private static double apply(double price, Code code) {

		{
			ExternalServices.delay();
		}

		return (price * (100 - code.getPercentage()) / 100);
	}
}
