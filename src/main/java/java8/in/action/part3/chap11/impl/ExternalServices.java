package java8.in.action.part3.chap11.impl;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ExternalServices {

	private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

	/*
	 * To fake such a long-running method execution, in the rest of this chapter we
	 * simply use a delay method, which introduces an artificial delay of 1 second,
	 * defined in the following listing.
	 */
	public static void delay() {

		try {

			Thread.sleep(1000L);

		} catch (InterruptedException e) {

			throw new RuntimeException(e);
		}
	}

	public static void doSomethingElse() {

		System.out.println("Doing something else...");
	}

	public static double format(double number) {

		synchronized (formatter) {

			return new Double(formatter.format(number));
		}
	}

}
