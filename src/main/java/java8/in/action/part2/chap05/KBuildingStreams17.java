package java8.in.action.part2.chap05;

import java.util.stream.Stream;

public class KBuildingStreams17 {

	public static void main(String[] args) throws Exception {

		// Streams from functions: creating infinite streams!

		/*
		 * The Streams API provides two static methods to generate a stream from a
		 * function: Stream.iterate and Stream.generate.
		 * 
		 * These two operations let you create what we call an infinite stream: a stream
		 * that doesn’t have a fixed size like when you create a stream from a fixed
		 * collection.
		 * 
		 * Streams produced by iterate and generate create values on demand given a
		 * function and can therefore calculate values forever!
		 * 
		 * It’s generally sensible to use limit(n) on such streams to avoid printing an
		 * infinite number of values.
		 */

		// ITERATE
		{

			/*
			 * The iterate method takes an initial value, here 0, and a lambda (of type
			 * Unary- Operator<T>) to apply successively on each new value produced
			 */

			/*
			 * Here you return the previous element added with 2 using the lambda n -> n +
			 * 2.
			 * 
			 * As a result, the iterate method produces a stream of all even numbers: the
			 * first element of the stream is the initial value 0.
			 * 
			 * Then it adds 2 to produce the new value 2; it adds 2 again to produce the new
			 * value 4 and so on.
			 * 
			 * This iterate operation is fundamentally sequential because the result depends
			 * on the previous application.
			 * 
			 * Note that this operation produces an infinite stream—the stream doesn’t have
			 * an end because values are computed on demand and can be computed forever. We
			 * say the stream is unbounded
			 * 
			 * As we discussed earlier, this is a key difference between a stream and a
			 * collection.
			 * 
			 * You’re using the limit method to explicitly limit the size of the stream.
			 * 
			 * Here you select only the first 10 even numbers.
			 * 
			 * You then call the forEach terminal operation to consume the stream and print
			 * each element individually.
			 */
			Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

			/*
			 * In general, you should use iterate when you need to produce a sequence of
			 * successive values, for example, a date followed by its next date: January 31,
			 * February 1, and so on.
			 */
		}

		{
			/*
			 * The Fibonacci series is famous as a classic programming exercise. The numbers
			 * in the following sequence are part of the Fibonacci series: 0, 1, 1, 2, 3, 5,
			 * 8, 13, 21, 34, 55…. The first two numbers of the series are 0 and 1, and each
			 * subsequent number is the sum of the previous two.
			 * 
			 * The series of Fibonacci tuples is similar; you have a sequence of a number
			 * and its successor in the series: (0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5,
			 * 8), (8, 13), (13, 21)….
			 * 
			 * Your task is to generate the first 20 elements of the series of Fibonacci
			 * tuples using the iterate method!
			 */

			/*
			 * Let us help you get started. The first problem is that the iterate method
			 * takes a UnaryOperator<T> as argument and you need a stream of tuples such as
			 * (0, 1).
			 * 
			 * You can, again rather sloppily, use an array of two elements to represent a
			 * tuple. For example, new int[]{0, 1} represents the first element of the
			 * Fibonacci series (0, 1).
			 * 
			 * This will be the initial value of the iterate method:
			 */

			// Stream.iterate(new int[] { 0, 1 }, f).limit(20)
			// .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

			/*
			 * Remember that iterate will apply the given lambda successively.
			 */

			Stream.iterate(new int[] { 0, 1 },

					(int[] arr) -> new int[] { arr[1], arr[0] + arr[1] })

					.limit(8)

					.forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

			/*
			 * (0,1) (1,1) (1,2) (2,3) (3,5) (5,8) (8,13) (13,21)
			 */
		}

		{
			/*
			 * How does it work? iterate needs a lambda to specify the successor element.
			 * 
			 * In the case of the tuple (3, 5)
			 * 
			 * the successor is (5, 3+5) = (5, 8).
			 * 
			 * The next one is (8, 5+8) = (8, 13)
			 * 
			 * Can you see the pattern? Given a tuple, the successor is (t[1], t[0] + t[1]).
			 * 
			 * 
			 * This is what the following lambda specifies: t -> new int[]{t[1],t[0] +
			 * t[1]}.
			 * 
			 * By running this code you’ll get the series (0, 1), (1, 1), (1, 2), (2, 3),
			 * (3, 5), (5, 8), (8, 13), (13, 21)
			 * 
			 * 
			 */
		}

		{
			/*
			 * Note that if you just wanted to print the normal Fibonacci series, you could
			 * use a map to extract only the first element of each tuple:F
			 */

			Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] })

					.limit(8)

					.map(t -> t[0])

					.forEach(System.out::println);

			// This code will produce the Fibonacci series: 0, 1, 1, 2, 3, 5, 8, 13

		}
	}
}
