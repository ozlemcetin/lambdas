package java8.in.action.part3.chap12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class CLocalDateTime05 {

	public static void main(String[] args) throws Exception {

		/*
		 * The java.time package includes many new classes to help you: LocalDate,
		 * LocalTime, LocalDateTime, Instant, Duration, and Period.
		 */

		/*
		 * The composite class called LocalDateTime pairs a LocalDate and a LocalTime.
		 * It represents both a date and a time, without a time zone, and can be created
		 * either directly or by combining a date and time, as shown in the next
		 * listing.
		 */
		{
			// 2014-03-18T13:45:20
			LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
			System.out.println(dateTime);
		}

		{
			LocalDate date = LocalDate.now();
			LocalTime time = LocalTime.now();

			LocalDateTime dateTime = LocalDateTime.of(date, time);
			System.out.println(dateTime);
		}

		/*
		 * Note that it’s possible to create a LocalDateTime by passing a time to a
		 * LocalDate, or conversely a date to a LocalTime, using respectively their
		 * atTime or atDate methods.
		 */
		{
			LocalDate date = LocalDate.now();
			LocalTime time = LocalTime.now();

			// LocalDateTime dateTime = date.atTime(13, 45, 20);
			LocalDateTime dateTime = date.atTime(time);
			System.out.println(dateTime);

		}

		{
			LocalDate date = LocalDate.now();
			LocalTime time = LocalTime.now();

			LocalDateTime dateTime = time.atDate(date);
			System.out.println(dateTime);

		}

		/*
		 * You can also extract the LocalDate or LocalTime component from a
		 * LocalDateTime using the toLocalDate and toLocalTime methods:
		 */
		{
			LocalDateTime dateTime = LocalDateTime.now();

			LocalDate date = dateTime.toLocalDate();
			System.out.println(date);

			LocalTime time = dateTime.toLocalTime();
			System.out.println(time);
		}

	}

}
