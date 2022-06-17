package java8.in.action.part1.chap03;

public class ALambdaRunnableExamples01 {

	public static void main(String[] args) {

		{
			Runnable r1 = new Runnable() {

				@Override
				public void run() {
					System.out.println("Hello World!");
				}
			};

			process(r1);

		}

		/*
		 * The signature of the abstract method of the functional interface essentially
		 * describes the signature of the lambda expression.
		 */

		/*
		 * For example, the Runnable interface can be viewed as the signature of a
		 * function that accepts nothing and returns nothing (void) because it has only
		 * one abstract method called run, which accepts nothing and returns nothing
		 * (void).
		 */
		{
			/*
			 * The following code is valid because Runnable is a functional interface
			 * defining only one abstract method, run:
			 */
			Runnable r1 = () -> System.out.println("Hello Lambda!");

			process(r1);

		}

		{
			process(() -> System.out.println("a lambda passed directly"));

		}

		{
			/*
			 * The example is valid because the lambda () -> {} has the signature () ->
			 * void, which matches the signature of the abstract method run defined in
			 * Runnable. Note that running this code will do nothing because the body of the
			 * lambda is empt
			 */
			process(() -> {
				// do nothing
			});
		}

	}

	public static void process(Runnable r) {
		r.run();
	}

}
