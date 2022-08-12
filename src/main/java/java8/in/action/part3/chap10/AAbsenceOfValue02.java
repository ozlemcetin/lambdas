package java8.in.action.part3.chap10;

import java.util.Optional;

import java8.in.action.part3.chap10.impl.Car;

public class AAbsenceOfValue02 {

	public static void main(String[] args) throws Exception {

		/*
		 * Creating Optional objects
		 */
		{
			// EMPTY OPTIONAL

			/*
			 * As mentioned earlier, you can get hold of an empty optional object using the
			 * static factory method Optional.empty:
			 */
			Optional<Car> optCar = Optional.empty();
		}

		{
			// OPTIONAL FROM A NON-NULL VALUE

			/*
			 * If car were null, a NullPointerException would be immediately thrown (rather
			 * than getting a latent error once you try to access properties of the car).
			 */

			Car car = new Car();
			Optional<Car> optCar1 = Optional.of(car);

		}

		{
			// OPTIONAL FROM NULL

			/*
			 * Finally, by using the static factory method Optional.ofNullable, you can
			 * create an Optional object that may hold a null value:
			 */
			Car car = null;
			Optional<Car> optCar = Optional.ofNullable(car);
		}
	}

}
