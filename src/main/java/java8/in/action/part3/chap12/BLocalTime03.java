package java8.in.action.part3.chap12;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class BLocalTime03 {

	public static void main(String[] args) throws Exception {

		/*
		 * The java.time package includes many new classes to help you: LocalDate,
		 * LocalTime, LocalDateTime, Instant, Duration, and Period.
		 */

		/*
		 * You can create instances of LocalTime using two overloaded static factory
		 * methods named of.
		 * 
		 * The first one accepts an hour and a minute and the second one also accepts a
		 * second.
		 * 
		 * 
		 * Just like the LocalDate class, the LocalTime class provides some getter
		 * methods to access its values, as shown in the following listing.
		 */
		{
			// 13:45:20
			LocalTime time = LocalTime.of(13, 45, 20);
			System.out.println(time);

			System.out.println("Hour:" + time.getHour());
			System.out.println("Hour:" + time.get(ChronoField.HOUR_OF_DAY));

			System.out.println("Minute:" + time.getMinute());
			System.out.println("Minute of Hour:" + time.get(ChronoField.MINUTE_OF_HOUR));

			System.out.println("Second:" + time.getSecond());
			System.out.println("Second of Hour:" + time.get(ChronoField.SECOND_OF_MINUTE));
		}
	}

}
