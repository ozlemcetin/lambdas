package java8.in.action.part3.chap08;

import java8.in.action.part3.chap08.impl.Customer;
import java8.in.action.part3.chap08.impl.OnlineBanking;
import java8.in.action.part3.chap08.impl.OnlineBankingLambda;

public class CTemplateMethod05 {

	public static void main(String[] args) throws Exception {

		{
			OnlineBanking bank = new OnlineBanking() {

				@Override
				public void makeCustomerHappy(Customer c) {

					System.out.println("Hello " + c.getName());

				}
			};

			bank.processCustomer(1);
		}

		/*
		 * You can now plug in different behaviors directly without subclassing the
		 * OnlineBanking class by passing lambda expressions:
		 */
		{
			OnlineBankingLambda bank = new OnlineBankingLambda();

			bank.processCustomer(1, (Customer c) -> System.out.println("Hello " + c.getName()));

		}
	}

}
