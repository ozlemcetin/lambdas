package in.action.chap03;

public class JLambdaLocalVariables08 {

	public static void main(String[] args) throws Exception {

		{
			/*
			 * All the lambda expressions we’ve shown so far used only their arguments
			 * inside their body. But lambda expressions are also allowed to use free
			 * variables (variables that aren’t the parameters and defined in an outer
			 * scope) just like anonymous classes can. They’re called capturing lambdas. For
			 * example, the following lambda captures the variable portNumber: int
			 * 
			 * portNumber = 1337;
			 * 
			 * Runnable r = () -> System.out.println(portNumber);
			 */

			String helloStr1 = "Hello!";

			Runnable r = () -> {

				System.out.println(helloStr1);
				System.out.println("Nonetheless, there’s a small twist.");

				String helloStr2 = "There are some restrictions on what you can do with these variables.";
				System.out.println(helloStr2);
			};

			r.run();
		}

		{
			/*
			 * Lambdas are allowed to capture (that is, to reference in their bodies)
			 * instance variables and static variables without restrictions.
			 * 
			 * But local variables have to be explicitly declared final or are effectively
			 * final
			 */

			int a = 10;

			Runnable r = () -> {

				/*
				 * Error: local variables referenced from a lambda expression must be final or
				 * effectively final
				 */
				System.out.println(a);

				int b = 11;
				System.out.println(b);

				/*
				 * they can’t modify the content of local variables of a method in which the
				 * lambda is defined
				 */
				// a = b;
			};

			/*
			 * For example, the following code doesn’t compile because the variable a is
			 * assigned to twice:
			 */
			// a = 15;

			r.run();
		}
	}

}
