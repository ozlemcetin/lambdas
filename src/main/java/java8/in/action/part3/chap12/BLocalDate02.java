package java8.in.action.part3.chap12;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class BLocalDate02 {

	public static void main(String[] args) throws Exception {

		/*
		 * The java.time package includes many new classes to help you: LocalDate,
		 * LocalTime, LocalDateTime, Instant, Duration, and Period.
		 */

		/*
		 * The class LocalDate is probably the first one you’ll come across when you
		 * start using the new Date and Time API.
		 * 
		 * An instance of this class is an immutable object representing just a plain
		 * date without the time of day.
		 * 
		 * In particular, it doesn’t carry any information about the time zone.
		 */

		/*
		 * You can create a LocalDate instance using the of static factory method.
		 * 
		 * A LocalDate instance provides many methods to read its most commonly used
		 * values such as year, month, day of the week, and so on, as shown in the
		 * listing that follows.
		 */
		{

			// 2014-03-18
			LocalDate date = LocalDate.of(2014, 3, 18);

			{

				// Date: 2014-03-18
				System.out.println("Date: " + date);

				// Year: 2014
				System.out.println("Year: " + date.getYear());

				// Month: MARCH
				System.out.println("Month: " + date.getMonth());

				// Month Value: 3
				System.out.println("Month Value: " + date.getMonthValue());

				// Day of Week: TUESDAY
				System.out.println("Day of Week: " + date.getDayOfWeek());

				// Day of Month: 18
				System.out.println("Day of Month: " + date.getDayOfMonth());

				// Day of Year: 77
				System.out.println("Day of Year: " + date.getDayOfYear());

			}

			/*
			 * It’s also possible to obtains the current date from the system clock using
			 * the now factory method:
			 */
			{
				LocalDate today = LocalDate.now();
				System.out.println("Today:" + today);
			}

			// Reading LocalDate values using a TemporalField
			{
				LocalDate today = LocalDate.now();
				System.out.println("Today:" + today);

				System.out.println("Year: " + today.get(ChronoField.YEAR));

				System.out.println("Month: " + today.get(ChronoField.MONTH_OF_YEAR));

				System.out.println("Day: " + today.get(ChronoField.DAY_OF_MONTH));

			}
		}
	}

}
