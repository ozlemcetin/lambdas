package java8.in.action.model;

public class TransactionWithTrader {

	private Trader trader;
	private int year;
	private int value;

	/*
	 * 
	 */

	public TransactionWithTrader(Trader trader, int year, int value) {
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	/*
	 * 
	 */
	public Trader getTrader() {
		return this.trader;
	}

	public int getYear() {
		return this.year;
	}

	public int getValue() {
		return this.value;
	}

	/*
	 * 
	 */

	@Override
	public String toString() {
		return "TransactionWithTrader [trader=" + trader + ", year=" + year + ", value=" + value + "]";
	}

}