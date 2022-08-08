package java8.in.action.part3.chap08.impl;

public abstract class OnlineBanking {

	/*
	 * The processCustomer method provides a sketch for the online banking
	 * algorithm: fetch the customer given its ID and then make the customer happy.
	 * 
	 * 
	 * Different branches can now provide different implementations of the method
	 * makeCustomerHappy by subclassing the OnlineBanking class.
	 */

	public void processCustomer(int id) {

		Customer c = Database4Customers.getCustomerWithId(id);

		// calling an abstract method
		makeCustomerHappy(c);

	}

	public abstract void makeCustomerHappy(Customer c);

}
