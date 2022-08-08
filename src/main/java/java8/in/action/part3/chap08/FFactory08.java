package java8.in.action.part3.chap08;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import java8.in.action.part3.chap08.impl.Bond;
import java8.in.action.part3.chap08.impl.Loan;
import java8.in.action.part3.chap08.impl.Product;
import java8.in.action.part3.chap08.impl.Stock;

public class FFactory08 {

	public static void main(String[] args) throws Exception {

		{
			Product p = ProductFactory.createProduct("loan");

			System.out.println(p);
		}

		// USING LAMBDA EXPRESSIONS
		{

			/*
			 * You saw in chapter 3 that you can refer to constructors just like you refer
			 * to methods, by using method references. For example, here’s how to refer to
			 * the Loan constructor:
			 */

			{
				Supplier<Product> loanSupplier = new Supplier<Product>() {

					@Override
					public Product get() {
						return new Loan();
					}
				};

				// Supplier<Product> loanSupplier = Loan::new;

			}

			{
				Product p = ProductFactory.createProduct_WithSuppliers("loan");

				System.out.println(p);
			}
		}
	}

	/*
	 * Typically you’d create a Factory class with a method that’s responsible for
	 * the creation of different objects, as shown here:
	 */

	/*
	 * Here, Loan, Stock, and Bond are all subtypes of Product. The createProduct
	 * method could have additional logic to configure each created product. But the
	 * benefit is that you can now create these objects without exposing the
	 * constructor and the configuration to the client, which makes the creation of
	 * products simpler for the client:
	 */

	static private class ProductFactory {

		public static Product createProduct(String name) {

			switch (name) {

			case "loan":
				return new Loan();

			case "stock":
				return new Stock();

			case "bond":
				return new Bond();

			default:
				throw new RuntimeException("No such product " + name);
			}
		}

		final static Map<String, Supplier<Product>> map = new HashMap<>();

		static {

			map.put("loan", Loan::new);
			map.put("stock", Stock::new);
			map.put("bond", Bond::new);
		}

		public static Product createProduct_WithSuppliers(String name) {

			Supplier<Product> p = map.get(name);

			if (p != null) {
				return p.get();
			}

			throw new IllegalArgumentException("No such product " + name);
		}

	}// ProductFactory

}
