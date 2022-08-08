package java8.in.action.part2.chap07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class BForkJoinFramework04 {

	public static void main(String[] args) throws Exception {

		/*
		 * Writing a method performing a parallel sum of the first n natural numbers is
		 * now pretty straightforward. You just need to pass the desired array of
		 * numbers to the constructor of ForkJoinSumCalculator:
		 */

		System.out.println("ForkJoin sum done in: "
				+ AParallelStreams02.measureSumPerf(BForkJoinFramework04::forkJoinSum, 10_000_000) + " msecs");

	}

	public static long forkJoinSum(long n) {

		/*
		 * Here, you generate an array containing the first n natural numbers using a
		 * Long- Stream.
		 */
		long[] numbers = LongStream.rangeClosed(1L, n).toArray();

		/*
		 * Then you create a ForkJoinTask (the superclass of RecursiveTask), passing
		 * this array to the public constructor of the ForkJoinSumCalculator
		 */
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);

		/*
		 * Finally, you create a new ForkJoinPool and pass that task to its invoke
		 * method
		 * 
		 * 
		 * The value returned by this last method is the result of the task defined by
		 * the Fork- JoinSumCalculator class when executed inside the ForkJoinPool
		 */

		/*
		 * The invoke method of a ForkJoinPool shouldn’t be used from within a
		 * RecursiveTask.
		 * 
		 * Instead, you should always call the methods compute or fork directly; only
		 * sequential code should use invoke to begin parallel computation.
		 */
		return new ForkJoinPool().invoke(task);

	}

}
