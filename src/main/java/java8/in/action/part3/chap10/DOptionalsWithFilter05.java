package java8.in.action.part3.chap10;

import java.util.Optional;

import java8.in.action.part3.chap10.impl.Insurance;

public class DOptionalsWithFilter05 {

	public static void main(String[] args) throws Exception {

		Insurance insurance = null;
		{

			insurance = new Insurance("CambridgeInsurance");

			// Often you need to call a method on an object and check some property
			if (insurance != null && "CambridgeInsurance".equals(insurance.getName())) {
				System.out.println("ok");
			}
		}

		/*
		 * This pattern can be rewritten using the filter method on an Optional object,
		 * as follows:
		 */
		{

			/*
			 * The filter method takes a predicate as an argument. If a value is present in
			 * the Optional object and it matches the predicate, the filter method returns
			 * that value; otherwise, it returns an empty Optional object
			 */
			Optional<Insurance> optInsurance = Optional.ofNullable(null);

			optInsurance

					.filter(ins -> "CambridgeInsurance".equals(ins.getName()))

					.ifPresent(ins -> System.out.println("ok"));

		}
	}

}
