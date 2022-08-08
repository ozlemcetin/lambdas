package java8.in.action.part2.chap07;

/*
 * 
 * Extend RecursiveTask to create a task usable with the fork/join framework.
 */
public class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {

	/*
	 * The array of numbers to be summed.
	 */
	private final long[] numbers;

	/*
	 * The initial and final positions of the portion of the array processed by this
	 * subtask.
	 */
	private final int start;
	private final int end;

	/*
	 * The size of the array under which this task is no longer Public split into
	 * subtasks.
	 */
	public static final long THRESHOLD = 10_000;

	/*
	 * Public constructor used to create the main task.
	 */

	public ForkJoinSumCalculator(long[] numbers) {

		this(numbers, 0, numbers.length);
	}

	/*
	 * Private constructor used to recursively create subtasks of the main task.
	 */

	private ForkJoinSumCalculator(long[] numbers, int start, int end) {

		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	/*
	 * Override the abstract method of RecursiveTask.
	 */

	@Override
	protected Long compute() {

		/*
		 * The size of the portion of the array summed by this task.
		 */
		int length = end - start;
		{
			if (length <= THRESHOLD) {

				/*
				 * If the size is less than or equal to the threshold, compute the result
				 * sequentially.
				 */
				return computeSequentially();
			}
		}

		/*
		 * Create a subtask to sum the first half of the array.
		 */
		ForkJoinSumCalculator leftTask = null;
		{
			leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);

			/*
			 * Asynchronously execute the newly created subtask using another thread of the
			 * ForkJoinPool.
			 */

			/*
			 * Calling the fork method on a subtask is the way to schedule it on the Fork-
			 * JoinPool.
			 * 
			 * It might seem natural to invoke it on both the left and right subtasks, but
			 * this is less efficient than just directly calling compute on one of them.
			 * 
			 * Doing this allows you to reuse the same thread for one of the two subtasks
			 * and avoid the overhead caused by the unnecessary allocation of a further task
			 * on the pool
			 */
			leftTask.fork();

		}

		/*
		 * Create a subtask to sum the second half of the array.
		 */
		ForkJoinSumCalculator rightTask = null;
		{
			rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);

		}

		/*
		 * Execute this second subtask synchronously, potentially allowing further
		 * recursive splits.
		 */
		Long rightResult = rightTask.compute();

		Long leftResult = leftTask.join();

		{
			/*
			 * Invoking the join method on a task blocks the caller until the result
			 * produced by that task is ready.
			 * 
			 * For this reason, it’s necessary to call it after the computation of both
			 * subtasks has been started.
			 * 
			 * Otherwise, you’ll end up with a slower and more complex version of your
			 * original sequential algorithm because every subtask will have to wait for the
			 * other one to complete before starting
			 */
		}

		/*
		 * The result of this task is the combination of the results of the two
		 * subtasks.
		 */

		return leftResult + rightResult;
	}

	/*
	 * Simple algorithm calculating the result of a subtask when it’s no longer
	 * divisible.
	 */
	private long computeSequentially() {

		long sum = 0;
		{
			for (int i = start; i < end; i++) {

				sum += numbers[i];
			}
		}
		return sum;
	}

}
