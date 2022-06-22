package java8.in.action.part2.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class EReducingExample09 {

	public static void main(String[] args) throws Exception {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		/*
		 * So far, the terminal operations you’ve seen return a boolean (allMatch and so
		 * on), void (forEach), or an Optional object (findAny and so on). You’ve also
		 * been using collect to combine all elements in a stream into a List.
		 * 
		 * In this section, you’ll see how you can combine elements of a stream to
		 * express more complicated queries such as “Calculate the sum of all calories
		 * in the menu,” or “What is the highest calorie dish in the menu?” using the
		 * reduce operation.
		 * 
		 * Such queries combine all the elements in the stream repeatedly to produce a
		 * single value such as an Integer.These queries can be classified as reduction
		 * operations (a stream is reduced to a value).
		 * 
		 * In functional programming-language jargon, this is referred to as a fold
		 * because you can view this operation as repeatedly folding a long piece of
		 * paper (your stream) until it forms a small square, which is the result of the
		 * fold operation.
		 */

		// Summing the elements
		{

			// JAVA 7
			{

				/*
				 * Each element of numbers is combined iteratively with the addition operator to
				 * form a result. You reduce the list of numbers into one number by repeatedly
				 * using addition. There are two parameters in this code:
				 * 
				 * The initial value of the sum variable, in this case 0
				 * 
				 * The operation to combine all the elements of the list, in this case +
				 */
				int sum = 0;
				{
					for (int x : numbers) {
						sum += x;
					}
				}

				System.out.println(sum);

			}

			// JAVA 8
			{

				{
					int result = 0;
					{
						BinaryOperator<Integer> accumulator = (Integer x, Integer y) -> x + y;

						for (int element : numbers)
							result = accumulator.apply(result, element);

					}

					System.out.println(result);
				}

				{
					/*
					 * The reduce operation abstracts over this pattern of repeated application
					 */

					/*
					 * Performs a reduction on the elements of this stream, using the provided
					 * identity value and an associative accumulation function, and returns the
					 * reduced value.
					 */

					/*
					 * Here, the identity element is both an initial seed value for the reduction
					 * and a default result if there are no input elements.
					 * 
					 * The accumulator function takes a partial result and the next element, and
					 * produces a newpartial result.
					 * 
					 * The combiner function combines two partial results to produce a new partial
					 * result.
					 * 
					 * (The combiner is necessary in parallel reductions, where the input is
					 * partitioned, a partial accumulation computed for each partition, and then the
					 * partial results are combined to produce a final result.)
					 */

					int result = numbers.stream()

							.reduce(0, (sum, x) -> sum + x, Integer::sum);

					System.out.println(result);

				}

				{
					/*
					 * reduce takes two arguments:
					 * 
					 * An initial value, here 0.
					 * 
					 * A BinaryOperator<T> to combine two elements and produce a new value; here you
					 * use the lambda (a, b) -> a + b.
					 */

					/*
					 * First, 0 is used as the first parameter of the lambda (a), and 1 is consumed
					 * from the stream and used as the second parameter (b).
					 * 
					 * 0 + 1 produces 1, and it becomes the new accumulated value.
					 * 
					 * Then the lambda is called again with the accumulated value and the next
					 * element of the stream, 2, which produces the new accumulated value, 3.
					 * 
					 * Moving forward, the lambda is called again with the accumulated value and the
					 * next element, 3, which produces 6.
					 * 
					 * * Moving forward, the lambda is called again with the accumulated value and
					 * the next element, 4, which produces 10.
					 * 
					 * Finally, the lambda is called with 10 and the last element of the stream, 5,
					 * which produces the final value, 15.
					 */
					int sum = numbers.stream().reduce(0, (a, b) -> a + b);

					System.out.println("Addition: " + sum);

					int product = numbers.stream().reduce(1, (a, b) -> a * b);

					System.out.println("Product: " + product);
				}

				{
					/*
					 * In Java 8 the Integer class now comes with a static sum method to add two
					 * numbers, which is just what you want instead of repeatedly writing out the
					 * same code as lambda:
					 */

					int sum = numbers.stream().reduce(0, Integer::sum);

					System.out.println("Addition: " + sum);

				}

				{
					/*
					 * Consider the case when the stream contains no elements. The reduce operation
					 * can’t return a sum because it doesn’t have an initial value. This is why the
					 * result is wrapped in an Optional object to indicate that the sum may be
					 * absent
					 */

					Optional<Integer> sum = numbers.stream().reduce(Integer::sum);

					sum.ifPresent(x -> System.out.println(x));
				}

			}
		}
	}
}
