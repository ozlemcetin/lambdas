package java8.in.action.part2.chap07;

import java.util.function.Function;

public class AParallelStreams02 {

	public static void main(String[] args) throws Exception {

		/*
		 * you can use this harness to check how long the sequential adder function
		 * takes to sum the first 10 million natural numbers:
		 * 
		 */

		/*
		 * iterativeSum done in: 3 msecs
		 * 
		 * sequentialSum done in: 159 msecs
		 * 
		 * parallelSum done in: 607 msecs
		 * 
		 * rangedSum done in: 16 msecs
		 * 
		 * parallelRangedSum done in: 5 msecs
		 * 
		 * 
		 */
		System.out.println(
				"iterativeSum done in: " + measureSumPerf(AParallelStreams01::iterativeSum, 10_000_000) + " msecs");

		System.out.println(
				"sequentialSum done in: " + measureSumPerf(AParallelStreams01::sequentialSum, 10_000_000) + " msecs");

		System.out.println(
				"parallelSum done in: " + measureSumPerf(AParallelStreams01::parallelSum, 10_000_000) + " msecs");

		System.out
				.println("rangedSum done in: " + measureSumPerf(AParallelStreams01::rangedSum, 10_000_000) + " msecs");

		System.out.println("parallelRangedSum done in: "
				+ measureSumPerf(AParallelStreams01::parallelRangedSum, 10_000_000) + " msecs");
	}

	/*
	 * Here this method takes as arguments a function and a long. It applies the
	 * function 10 times on the long passed to the method, registers the time taken
	 * by each execution in milliseconds, and returns the duration of the fastest
	 * one
	 */
	public static long measureSumPerf(Function<Long, Long> adder, long n) {

		long fastest = Long.MAX_VALUE;
		{

			for (int i = 0; i < 10; i++) {

				long start = System.nanoTime();
				{
					long sum = adder.apply(n);
				}
				long duration = (System.nanoTime() - start) / 1_000_000;

				fastest = Math.min(fastest, duration);

			} // for
		}

		return fastest;

	}

}
