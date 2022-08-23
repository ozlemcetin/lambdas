package java8.in.action.part3.chap11.impl;

public class Quote {

	private final String shopName;
	private final double price;
	private final DiscountService.Code discountCode;

	public Quote(String shopName, double price, DiscountService.Code code) {

		this.shopName = shopName;
		this.price = price;
		this.discountCode = code;
	}

	public static Quote parse(String s) {

		// BestPrice:123.26:GOLD
		String[] split = s.split(":");

		String shopName = split[0];

		double price = Double.parseDouble(split[1]);

		DiscountService.Code discountCode = DiscountService.Code.valueOf(split[2]);

		return new Quote(shopName, price, discountCode);
	}

	public String getShopName() {
		return shopName;
	}

	public double getPrice() {
		return price;
	}

	public DiscountService.Code getDiscountCode() {
		return discountCode;
	}

}
