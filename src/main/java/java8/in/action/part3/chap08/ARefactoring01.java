package java8.in.action.part3.chap08;

import java8.in.action.part3.chap08.impl.Task;

public class ARefactoring01 {

	public static void main(String[] args) throws Exception {

		// From anonymous classes to lambda expressions
		{
			/*
			 * The first simple refactoring you should consider is converting uses of
			 * anonymous classes implementing one single abstract method to lambda
			 * expressions
			 */
			// Before, using an anonymous class
			{
				Runnable r1 = new Runnable() {

					@Override
					public void run() {
						System.out.println("running...");

					}
				};

				r1.run();
			}

			/*
			 * After, using a lambda expression
			 */
			{
				Runnable r1 = () -> System.out.println("running...");

				r1.run();
			}

		}

		/*
		 * But converting anonymous classes to lambda expressions can be a difficult
		 * process in certain situations.
		 * 
		 * First, the meanings of this and super are different for anonymous classes and
		 * lambda expressions.
		 * 
		 * Inside an anonymous class, this refers to the anonymous class itself, but
		 * inside a lambda it refers to the enclosing class.
		 * 
		 * Second, anonymous classes are allowed to shadow variables from the enclosing
		 * class. Lambda expressions can’t (they’ll cause a compile error), as shown in
		 * the following code:
		 */
		{

			// an anonymous class
			{
				int a = 10;
				Runnable r1 = new Runnable() {

					@Override
					public void run() {

						// Everthing's fine!
						int a = 2;
						System.out.println(a);

					}
				};

				r1.run();
			}

			// a lambda expression
			{

				int a = 10;
				Runnable r1 = () -> {

					/*
					 * Compile Error: Lambda expression's local variable a cannot redeclare another
					 * local variable defined in an enclosing scope.
					 */
					// int a = 2;
					System.out.println(a);
				};

				r1.run();
			}

		}

		/*
		 * Finally, converting an anonymous class to a lambda expression can make the
		 * resulting code ambiguous in the context of overloading.
		 * 
		 * Indeed, the type of anonymous class is explicit at instantiation, but the
		 * type of the lambda depends on its context
		 */
		{

			// You can now pass an anonymous class implementing Task without a problem:
			{
				doSomething(new Task() {

					@Override
					public void execute() {
						System.out.println("Danger!");

					}
				});
			}

			/*
			 * But converting this anonymous class to a lambda expression results in an
			 * ambiguous method call, because both Runnable and Task are valid target types:
			 */
			{
				/*
				 * Compile Error: The method doSomething(Runnable) is ambiguous for the type
				 * ARefactoring01
				 */

				// doSomething(() -> System.out.println("Danger!"));

				/*
				 * You can solve the ambiguity by providing an explicit cast (Task)
				 */

				doSomething((Task) () -> System.out.println("Danger!"));
			}

		}
	}

	public static void doSomething(Runnable r) {
		r.run();
	}

	public static void doSomething(Task t) {
		t.execute();
	}
}
