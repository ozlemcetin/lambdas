package java8.in.action.model;

public class TransactionWithCurrency {

	private final Currency currency;
	private final double value;

	/*
	 * 
	 */
	public TransactionWithCurrency(Currency currency, double value) {
		super();
		this.currency = currency;
		this.value = value;
	}

	/*
	 *
	 */
	public Currency getCurrency() {
		return currency;
	}

	public double getValue() {
		return value;
	}

	/*
	 * 
	 */
	@Override
	public String toString() {
		return "Transaction [currency=" + currency + ", value=" + value + "]";
	}

}
