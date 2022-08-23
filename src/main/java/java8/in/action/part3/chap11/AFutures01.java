package java8.in.action.part3.chap11;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AFutures01 {

	public static void main(String[] args) throws Exception {

		// Executing a long-lasting operation asynchronously in a Future

		/*
		 * this style of programming allows your thread to perform some other tasks
		 * while the long-lasting operation is executed concurrently in a separate
		 * thread provided by the ExecutorService.
		 * 
		 * Then, when you can’t do any other meaningful work without having the result
		 * of that asynchronous operation, you can retrieve it from the Future by
		 * invoking its get method.
		 */
		{

			/*
			 * Create an Executor-Service allowing you to submit tasks to a thread pool.
			 */

			ExecutorService executorService = Executors.newCachedThreadPool();

			/*
			 * Submit a Callable to the ExecutorService.
			 */

			/*
			 * a Future is just a handle for a value that isn’t yet available but can be
			 * retrieved by invoking its get method after its computation has finally
			 * terminated. As
			 */
			Future<Double> future = executorService.submit(new Callable<Double>() {

				@Override
				public Double call() throws Exception {

					/*
					 * Execute a long operation asynchronously in a separate thread.
					 * 
					 * return doSomeLongComputation();
					 */
					return null;
				}
			});

			/*
			 * Do something else while the asynchronous operation is progressing.
			 */
			{
				// doSomethingElse();
			}

			/*
			 * Retrieve the result of the asynchronous operation, eventually blocking if it
			 * isn’t available yet, but waiting at most for 1 second.
			 */
			try {

				/*
				 * This method immediately returns the result of the operation if it’s already
				 * completed or blocks your thread, waiting for its result to be available.
				 */

				/*
				 * even though there also exists a get method that doesn’t take a parameter,
				 * it’s almost always a good idea to use its overloaded version, accepting a
				 * timeout defining the maximum time your thread has to wait for the Future’s
				 * result, as you did in listing 11.1, instead of waiting indefinitely
				 */
				Double result = future.get(1, TimeUnit.SECONDS);

				System.out.println(result);

			} catch (ExecutionException ee) {
				// the computation threw an exception

			} catch (InterruptedException ie) {
				// the current thread was interrupted while waiting

			} catch (TimeoutException te) {
				// the timeout expired before the Future completion
			}

		}
	}

}
