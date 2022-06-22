package java8.in.action.part2.chap05;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class KBuildingStreams18 {

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

		// GENERATE
		{
			/*
			 * Similarly to the method iterate, the method generate lets you produce an
			 * infinite stream of values computed on demand.
			 * 
			 * But generate doesn’t apply successively a function on each new produced
			 * value.
			 * 
			 * It takes a lambda of type Supplier<T> to provide new values.
			 * 
			 * Let’s look at an example of how to use it:
			 */

			Stream.generate(Math::random).limit(5).forEach(System.out::println);

			/*
			 * This code will generate a stream of five random double numbers from 0 to 1.
			 * For example, one run gives the following:
			 * 
			 * 0.9410810294106129
			 * 
			 * 0.6586270755634592
			 * 
			 * 0.9592859117266873
			 * 
			 * 0.13743396659487006
			 * 
			 * 0.3942776037651241
			 * 
			 * The static method Math.random is used as a generator for new values.
			 * 
			 * Again you limit the size of the stream explicitly using the limit method;
			 * otherwise the stream would be unbounded!
			 */

		}

		{
			/*
			 * The generate method on IntStream takes an IntSupplier instead of a
			 * Supplier<T>. For example, here’s how to generate an infinite stream of ones:
			 */

			IntStream.generate(() -> 1).limit(5).forEach(System.out::println);
		}

		{
			/*
			 * You saw in the chapter 3 that lambdas let you create an instance of a
			 * functional interface by providing the implementation of the method directly
			 * inline. You can also pass an explicit object as follows by implementing the
			 * getAsInt method defined in the IntSupplier interface
			 */

			/*
			 * The generate method will use the given supplier and repeatedly call the
			 * getAsInt method, which always returns 2.
			 * 
			 * But the difference between the anonymous class used here and a lambda is that
			 * the anonymous class can define state via fields, which the getAsInt method
			 * can modify.
			 * 
			 * FThis is an example of a side effect. All lambdas you’ve seen so far were
			 * side-effect free; they didn’t change any state
			 */
			IntStream.generate(new IntSupplier() {

				@Override
				public int getAsInt() {
					return 2;
				}

			}).limit(5).forEach(System.out::println);
		}

		{
			/*
			 * To come back to our Fibonacci tasks, what you need to do now is create an
			 * IntSupplier that maintains in its state the previous value in the series, so
			 * getAsInt can use it to calculate the next element.
			 * 
			 * In addition, it can update the state of the IntSupplier for the next time
			 * it’s called.
			 * 
			 * The following code shows how to create an IntSupplier that will return the
			 * next Fibonacci element when it’s called:
			 */

			IntSupplier fib = new IntSupplier() {

				private int previous = 0;
				private int current = 1;

				public int getAsInt() {

					int oldPrevious = this.previous;
					int nextValue = this.previous + this.current;

					this.previous = this.current;
					this.current = nextValue;

					return oldPrevious;
				}
			};

			IntStream.generate(fib).limit(10).forEach(System.out::println);

			/*
			 * In the preceding code you create an instance of IntSupplier. This object has
			 * mutable state: it tracks the previous Fibonacci element and the current
			 * Fibonacci element in two instance variables.
			 * 
			 * The getAsInt method changes the state of the object when it’s called so that
			 * it produces new values on each call.
			 * 
			 * In comparison, our approach using iterate was purely immutable: you didn’t
			 * modify existing state but were creating new tuples at each iteration.
			 * 
			 * You'll learn in chapter 7 that you should always prefer an immutable approach
			 * in order to process a stream in parallel and expect a correct result.
			 */

			/*
			 * Note that because you’re dealing with a stream of infinite size, you have to
			 * limit its size explicitly using the operation limit; otherwise, the terminal
			 * operation (in this case forEach) will compute forever. Similarly, you can’t
			 * sort or reduce an infinite stream because all elements need to be processed,
			 * but this would take forever because the stream is infinite!
			 */

		}
	}
}
