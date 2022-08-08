package java8.in.action.part3.chap08.impl;

import java.util.function.Consumer;

public class OnlineBankingLambda {

	/*
	 * Here we introduce a second argument to the method processCustomer of type
	 * Consumer<Customer> because it matches the signature of the method
	 * makeCustomerHappy defined earlier
	 */
	public void processCustomer(int id, Consumer<Customer> makeCustomerHappyConsumer) {

		Customer c = Database4Customers.getCustomerWithId(id);

		makeCustomerHappyConsumer.accept(c);
	}

}
