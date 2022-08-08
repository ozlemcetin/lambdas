package java8.in.action.part3.chap08;

import java.util.Arrays;
import java.util.List;

import java8.in.action.part3.chap08.impl.Point;

public class IDebugging11 {

	public static void main(String[] args) throws Exception {

		if (false) {

			List<Point> points = Arrays.asList(new Point(12, 2), null);

			points.stream()

					.map(p -> p.getX())

					.forEach(System.out::println);
		}

		/*
		 * What does $0 in this line mean?
		 */
		{

			/*
			 * 12
			 * 
			 * Exception in thread "main" java.lang.NullPointerException
			 * 
			 * at java8.in.action.part3.chap08.IDebugging11.lambda$0(IDebugging11.java:16)
			 * 
			 * at
			 * java8.in.action.part3.chap08.IDebugging11$$Lambda$5.000000000F2D5750.apply(
			 * Unknown Source)
			 * 
			 * at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:204)
			 * 
			 * at
			 * java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:
			 * 959)
			 * 
			 */
		}

		/*
		 * Note that if a method reference refers to a method declared in the same class
		 * as where it’s used, then it will appear in the stack trace. For instance, in
		 * the following example
		 */
		{

			List<Integer> numbers = Arrays.asList(1, 2, 3);

			numbers.stream()

					.map(IDebugging11::divideByZero)

					.forEach(System.out::println);

		}

		/*
		 * divideByZero appears in the stack trace!
		 */
		{
			/*
			 * Exception in thread "main" java.lang.ArithmeticException: sıfıra bölme
			 * 
			 * at
			 * java8.in.action.part3.chap08.IDebugging11.divideByZero(IDebugging11.java:68)
			 * 
			 * at
			 * java8.in.action.part3.chap08.IDebugging11$$Lambda$1.000000000EFA2020.apply(
			 * Unknown Source)
			 * 
			 * at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:204)
			 */
		}
	}

	public static int divideByZero(int n) {

		return n / 0;
	}

}
