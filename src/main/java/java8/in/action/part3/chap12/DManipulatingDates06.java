package java8.in.action.part3.chap12;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class DManipulatingDates06 {

	public static void main(String[] args) throws Exception {

		/*
		 * to create a modified version of an existing LocalDate is changing one of its
		 * attributes, using one of its withAttribute methods
		 */

		// 2014-03-18
		LocalDate date = LocalDate.of(2014, 3, 18);
		System.out.println(date);

		{
			System.out.println("===");

			// 2011-03-18
			LocalDate date2 = date.withYear(2011);
			System.out.println(date2);

			// 2014-04-18
			LocalDate date3 = date.withMonth(4);
			System.out.println(date3);

			// 2014-03-25
			LocalDate date4 = date.withDayOfMonth(25);
			System.out.println(date4);

			/*
			 * You can also do this with the more generic with method, taking a
			 * TemporalField as first argument,
			 */

			// 2014-09-18
			LocalDate date5 = date.with(ChronoField.MONTH_OF_YEAR, 9);
			System.out.println(date5);

			/*
			 * More precisely, the get and with methods let you respectively read and modify
			 * the value of a field of a Temporal object
			 */
		}

		/*
		 * It’s even possible to manipulate a LocalDate in a declarative manner.
		 */
		{

			System.out.println("===");

			// 2014-03-25
			LocalDate date2 = date.plusWeeks(1);
			System.out.println(date2);

			// 2011-03-18
			LocalDate date3 = date.minusYears(3);
			System.out.println(date3);

			// 2014-09-18
			LocalDate date4 = date.plus(6, ChronoUnit.MONTHS);
			System.out.println(date4);
		}

		{

			System.out.println("===");

			// 2014-09-18
			LocalDate date2 = date.with(ChronoField.MONTH_OF_YEAR, 9);
			System.out.println(date2);

			// 2016-03-08
			LocalDate date3 = date.plusYears(2).minusDays(10);
			System.out.println(date3);

			// 2011-03-18
			LocalDate date4 = date.withYear(2011);
			System.out.println(date4);
		}

	}

}
