package java8.in.action.part3.chap12;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class BParse04 {

	public static void main(String[] args) throws Exception {

		try {

			/*
			 * note that these parse methods both throw a DateTimeParseException, which
			 * extends RuntimeException in case the String argument can’t be parsed as a
			 * valid LocalDate or LocalTime.
			 */
			String dateStr = "2014-03-18";
			LocalDate date = LocalDate.parse(dateStr);
			System.out.println(date);

			String timeStr = "13:45:20";
			LocalTime time = LocalTime.parse(timeStr);
			System.out.println(time);

		} catch (DateTimeParseException e) {

		}

	}

}
