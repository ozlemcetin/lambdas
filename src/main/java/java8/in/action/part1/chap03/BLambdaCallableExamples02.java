package java8.in.action.part1.chap03;

import java.util.concurrent.Callable;

public class BLambdaCallableExamples02 {

	public static void main(String[] args) throws Exception {

		{
			Callable<String> c = new Callable<String>() {

				@Override
				public String call() throws Exception {
					return "Callable 1";
				}
			};

			process(c);

		}

		{
			Callable<String> c = () -> "Callable 2";

			process(c);
		}

		{
			Callable<String> c = fetch();

			process(c);
		}

		{
			process(() -> "Callable 4");
		}

	}

	public static Callable<String> fetch() {
		return () -> "Callable 3";
	}

	public static void process(Callable<String> c) throws Exception {
		System.out.println(c.call());
	}

}
