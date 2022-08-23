package java8.in.action.part3.chap10;

import java.util.Optional;

import java8.in.action.part3.chap10.impl.Car;
import java8.in.action.part3.chap10.impl.Insurance;
import java8.in.action.part3.chap10.impl.Person;

public class BExtractingValue03 {

	public static void main(String[] args) throws Exception {

		/*
		 * A common pattern is to extract information from an object. For example, you
		 * may want to extract the name from an insurance company. You’d need to check
		 * whether insurance is null before extracting the name as follows:
		 */

		Insurance insurance = new Insurance("ASA");
		{
			String name = null;
			{
				if (insurance != null) {
					name = insurance.getName();
				}
			}

			System.out.println(name);

		}

		/*
		 * Optional supports a map method for this pattern. It works as follows
		 * 
		 * If the Optional contains a value, then the function passed as argument to map
		 * transforms that value
		 */
		{
			Optional<Insurance> optInsurance = Optional.ofNullable(insurance);

			/*
			 *
			 * Optional<String> name = optInsurance.map(new Function<Insurance, String>() {
			 * 
			 * @Override public String apply(Insurance t) { return t.getName(); } });
			 * 
			 */

			Optional<String> name = optInsurance.map(Insurance::getName);

			System.out.println(name);
		}

		{

			System.out.println(getCarInsuranceName_01(Optional.ofNullable(null)));

			System.out.println(getCarInsuranceName_02(Optional.ofNullable(null)));
		}

	}

	public static String getCarInsuranceName_00(Person person) {

		// return person.getCar().getInsurance().getName();

		return null;
	}

	public static String getCarInsuranceName_01(Optional<Person> person) {

		/*
		 * Again, we can look at a pattern you've used previously with streams: the
		 * flatMap method.
		 * 
		 * With streams, the flatMap method takes a function as an argument, which
		 * returns another stream. This function is applied to each element of a stream,
		 * which would result in a stream of streams.
		 * 
		 * But flatMap has the effect of replacing each generated stream by the contents
		 * of that stream.
		 * 
		 * In other words, all the separate streams that are generated by the function
		 * get amalgamated or flattened into a single stream.
		 * 
		 * What you want here is something similar, but you want to flatten a two-level
		 * optional into one
		 */

		// Optional<Optional<Car>> optCar = optPerson.map(Person::getCar);
		Optional<Car> car = person.flatMap(Person::getCar);

		// Optional<Optional<Insurance>> optInsurance = optCar.map(Car::getInsurance);
		Optional<Insurance> insurance = car.flatMap(Car::getInsurance);

		Optional<String> name = insurance.map(Insurance::getName);

		return name.orElse("Unknown");
	}

	public static String getCarInsuranceName_02(Optional<Person> person) {

		return person

				.flatMap(Person::getCar)

				.flatMap(Car::getInsurance)

				.map(Insurance::getName)

				// A default value if the resulting Optional is empty
				.orElse("Unknown");
	}

}
