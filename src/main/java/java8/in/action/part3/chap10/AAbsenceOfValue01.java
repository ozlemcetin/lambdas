package java8.in.action.part3.chap10;

import java8.in.action.part3.chap10.impl.Car;
import java8.in.action.part3.chap10.impl.Insurance;
import java8.in.action.part3.chap10.impl.Person;

public class AAbsenceOfValue01 {

	public static void main(String[] args) throws Exception {

		/*
		 * A common unfortunate practice is to return the null reference to indicate the
		 * absence of a value, here to indicate the absence of a car.
		 * 
		 * As a consequence, the call to getInsurance will return the insurance of a
		 * null reference, which will result in a NullPointerException at run-time and
		 * stop your program from running further.
		 * 
		 * But that’s not all. What if person was null? What if the method getInsurance
		 * returned null too?
		 */
		{

			// System.out.println(getCarInsuranceName1(new Person()));

			// System.out.println(getCarInsuranceName2(new Person()));

			// System.out.println(getCarInsuranceName3(new Person()));

		}

		{
			/*
			 * We argue in this chapter that using null to represent the absence of a value
			 * is the wrong approach. What you need is a better way to model the absence and
			 * presence of a value.
			 */
		}

		{
			/*
			 * It’s important to note that the intention of the Optional class is not to
			 * replace every single null reference. Instead, its purpose is to help you
			 * design more-comprehensible APIs so that by just reading the signature of a
			 * method, you can tell whether to expect an optional value.
			 */
		}
	}

	/*
	 * 
	 * 	public static String getCarInsuranceName1(Person person) {

		return person.getCar().getInsurance().getName();
	}

	 */

	/*
	 * Each null check increases the nesting level of the remaining part of the
	 * invocation chain
	 */

	/*
	 * public static String getCarInsuranceName2(Person person) {

		if (person != null) {

			Car car = person.getCar();

			if (car != null) {

				Insurance insurance = car.getInsurance();

				if (insurance != null) {

					return insurance.getName();
				}
			}
		}
		return "Unknown";
	}

	 * 
	 * public static String getCarInsuranceName3(Person person) {

		
		if (person == null) {
			return "Unknown";
		}

		Car car = person.getCar();
		if (car == null) {
			return "Unknown";
		}

		Insurance insurance = car.getInsurance();
		if (insurance == null) {
			return "Unknown";
		}

		return insurance.getName();
	}
	 * 
	 */
	
	

}
