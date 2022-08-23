package java8.in.action.part3.chap10;

import java.util.Optional;

import java8.in.action.part3.chap10.impl.Car;
import java8.in.action.part3.chap10.impl.Insurance;
import java8.in.action.part3.chap10.impl.Person;

public class CCombiningOptionals04 {

	public static void main(String[] args) throws Exception {

		{
			Optional<Insurance> optInsurance = nullSafeFindCheapestInsurance_01(Optional.ofNullable(null),
					Optional.ofNullable(null));

			System.out.println(optInsurance);
		}

		{
			Optional<Insurance> optInsurance = nullSafeFindCheapestInsurance_01(Optional.ofNullable(new Person()),
					Optional.ofNullable(null));

			System.out.println(optInsurance);
		}

		{
			Optional<Insurance> optInsurance = nullSafeFindCheapestInsurance_01(Optional.ofNullable(new Person()),
					Optional.ofNullable(new Car()));

			System.out.println(optInsurance);
		}

	}

	private static Insurance findCheapestInsurance(Person person, Car car) {

		// queries services provided by the different insurance companies
		// compare all those data
		// return cheapestCompany;

		return null;
	}

	/*
	 * Let’s also suppose that you want to develop a null-safe version of this
	 * method taking two optionals as arguments and then returning an
	 * Optional<Insurance> that will be empty if at least one of the values passed
	 * in to it is also empty.
	 * 
	 * The Optional class also provides an isPresent method returning true if the
	 * optional contains a value, so your first attempt could be to implement this
	 * method as follows:
	 */
	public static Optional<Insurance> nullSafeFindCheapestInsurance_00(Optional<Person> person, Optional<Car> car) {

		if (person.isPresent() && car.isPresent()) {

			return Optional.ofNullable(findCheapestInsurance(person.get(), car.get()));

		} else {

			return Optional.empty();
		}
	}

	/*
	 * Quiz 10.1: Combining two optionals without unwrapping them Using a
	 * combination of the map and flatMap methods you learned in this section,
	 * rewrite the implementation of the former nullSafeFindCheapestInsurance()
	 * method in a single statement
	 */

	public static Optional<Insurance> nullSafeFindCheapestInsurance_01(Optional<Person> person, Optional<Car> car) {

		/*
		 * Here you invoke a flatMap on the first optional, so if this is empty, the
		 * lambda expression passed to it won’t be executed at all and this invocation
		 * will just return an empty optional.
		 * 
		 * Conversely, if the person is present, it uses it as the input of a Function
		 * returning an Optional<Insurance> as required by the flatMap method.
		 * 
		 * The body of this function invokes a map on the second optional, so if it
		 * doesn’t contain any car, the Function will return an empty optional and so
		 * will the whole null- SafeFindCheapestInsurance method.
		 * 
		 * Finally, if both the person and the car are present, the lambda expression
		 * passed as argument to the map method can safely invoke the original
		 * findCheapestInsurance method with them.
		 */

		/*
		 * Optional<Optional<Insurance>> optInsurance =
		 * 
		 * person.map(p -> car.map(c -> findCheapestInsurance(p, c)));
		 */
		Optional<Insurance> optInsurance = person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));

		return optInsurance;

	}
}
