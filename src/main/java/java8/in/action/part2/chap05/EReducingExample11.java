package java8.in.action.part2.chap05;

import java8.in.action.model.Inventory;

public class EReducingExample11 {

	public static void main(String[] args) throws Exception {

		/*
		 * How would you count the number of dishes in a stream using the map and reduce
		 * methods?
		 */

		//A chain of map and reduce
		{
			/*
			 * You can solve this problem by mapping each element of a stream into the
			 * number 1 and then summing them using reduce!
			 * 
			 * This is equivalent to counting in order the number of elements in the stream.
			 */

			/*
			 * A chain of map and reduce is commonly known as the map-reduce pattern, made
			 * famous by Google’s use of it for web searching because it can be easily
			 * parallelized.
			 */
			long numberOfDishes = Inventory.MENU.stream()

					.map(d -> 1)

					.reduce(0, Integer::sum);

			System.out.println("Number of dishes: " + numberOfDishes);
		}
		
		//count
		{
			long numberOfDishes = Inventory.MENU.stream()

					.count();

			System.out.println("Number of dishes: " + numberOfDishes);
		}
	}
}
