package java8.in.action.part2.chap07;

import java.util.stream.LongStream;

public class AParallelStreams03 {

	public static void main(String[] args) throws Exception {

		/*
		 * The main cause of errors generated by misuse of parallel streams is the use
		 * of algorithms that mutate some shared state. Here’s another way to implement
		 * the sum of the first n natural numbers but by mutating a shared accumulator:
		 */

		System.out.println("sideEffectSum = " + sideEffectSum(10_000_000));

		/*
		 * each execution returns a different result, all very distant from the correct
		 * value of 50000005000000.
		 * 
		 * This is caused by the fact that multiple threads are concurrently accessing
		 * the accumulator and in particular executing total += value, which, despite
		 * its appearance, isn’t an atomic operation.
		 * 
		 * The origin of the problem is that the method invoked inside the forEach block
		 * has the side effect of changing the mutable state of an object shared among
		 * multiple threads. I
		 * 
		 * t’s mandatory to avoid these kinds of situations if you want to use parallel
		 * Streams without incurring similar bad surprises.
		 */
		System.out.println("sideEffectParallelSum = " + sideEffectSum(1_000_000));

		System.out.println("sideEffectParallelSum = " + sideEffectSum(10_000_000));

		System.out.println("sideEffectParallelSum = " + sideEffectSum(10_000_000));
	}

	/*
	 * This code closely resembles what you’re used to doing when iterating
	 * imperatively a list of numbers:
	 * 
	 * you initialize an accumulator and traverse the elements in the list one by
	 * one, adding them on the accumulator.
	 * 
	 * What’s wrong with this code? Unfortunately, it’s irretrievably broken because
	 * it’s fundamentally sequential.
	 * 
	 * You have a data race on every access of total.
	 * 
	 * And if you try to fix that with synchronization, you’ll lose all your
	 * parallelism. To understand this, let’s try to turn the Stream into a parallel
	 * one:
	 */
	public static long sideEffectSum(long n) {

		Accumulator accumulator = new Accumulator();
		{
			LongStream.rangeClosed(1L, n).forEach(accumulator::add);
		}

		return accumulator.total;
	}

	public static long sideEffectParallelSum(long n) {

		Accumulator accumulator = new Accumulator();
		{
			LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
		}

		return accumulator.total;
	}

}
