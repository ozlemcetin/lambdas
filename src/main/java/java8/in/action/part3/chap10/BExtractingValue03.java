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
			
			Optional<String> name = optInsurance.map(Insurance::getName);
		}

		{

		}
	}

	public String getCarInsuranceName(Optional<Person> person) {

		return person

				.flatMap(Person::getCar)

				.flatMap(Car::getInsurance)

				.map(Insurance::getName)

				.orElse("Unknown");
	}

}
