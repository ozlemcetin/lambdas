package java8.in.action.part3.chap09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ADefualtMethods01 {

	public static void main(String[] args) throws Exception {

		{
			/*
			 * default void sort(Comparator<? super E> c){
			 * 
			 * Collections.sort(this, c);
			 * 
			 * }
			 */
			List<Integer> numbers = Arrays.asList(1, 5, 3, 2, 4);

			/*
			 * sort is a default method in the List interface.
			 * 
			 * Notice that you call the Comparator.naturalOrder method.
			 * 
			 * It’s a new static method in the Comparator interface that returns a
			 * Comparator object to sort the elements in natural order (the standard
			 * alphanumerical sort).
			 */
			numbers.sort(Comparator.naturalOrder());

			System.out.println(numbers);

		}

		{
			/*
			 * default Stream<E> stream() {
			 * 
			 * return StreamSupport.stream(spliterator(), false);
			 * 
			 * }
			 */
		}
	}

}
