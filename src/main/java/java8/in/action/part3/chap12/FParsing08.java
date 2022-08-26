package java8.in.action.part3.chap12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class FParsing08 {

	public static void main(String[] args) throws Exception {

		{
			LocalDate date = LocalDate.of(2014, 3, 18);
			System.out.println("Date: " + date);

			// 20140318
			String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
			System.out.println("String: " + s1);

			// 2014-03-18
			String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
			System.out.println("String: " + s2);
		}

		{
			LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
			System.out.println(date1);

			LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);
			System.out.println(date2);

			LocalDate date3 = LocalDate.parse("2014-03-18");
			System.out.println(date3);
		}

		/*
		 * In comparison to the old java.util.DateFormat class, all the
		 * DateTimeFormatter instances are thread-safe.
		 * 
		 * Therefore, you can create singleton formatters, like the ones defined by the
		 * DateTimeFormatter constants, and share them among multiple threads.
		 * 
		 * The DateTimeFormatter class also supports a static factory method that lets
		 * you create a formatter from a specific pattern, as shown in the next listing.
		 */
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			{
				/*
				 * Here the LocalDate’s format method produces a String representing the date
				 * with the requested pattern
				 */
				LocalDate date1 = LocalDate.of(2014, 3, 18);
				String formattedDate = date1.format(formatter);

				System.out.println("String: " + formattedDate);
			}

			{

				/*
				 * Next, the static parse method re-creates the same date by parsing the
				 * generated String using the same formatter
				 */
				String formattedDate = "18/03/2014";
				LocalDate date2 = LocalDate.parse(formattedDate, formatter);

				System.out.println("Date: " + date2);
			}

		}

		/*
		 * The ofPattern method also has an overloaded version allowing you to create a
		 * formatter for a given Locale, as shown in the following listing.
		 */
		{
			DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);

			{
				// 18. marzo 2014
				LocalDate date = LocalDate.of(2014, 3, 18);
				String formattedDate = date.format(italianFormatter);

				System.out.println("String: " + formattedDate);
			}

			{
				String formattedDate = "18. marzo 2014";
				LocalDate date2 = LocalDate.parse(formattedDate, italianFormatter);

				System.out.println("Date: " + date2);
			}
		}

		{
			DateTimeFormatter formatterLocaleTR = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("TR"));

			{
				// 18. marzo 2014
				LocalDate date = LocalDate.now();
				String formattedDate = date.format(formatterLocaleTR);

				System.out.println("String: " + formattedDate);
			}

			{
				LocalDateTime dateTime = LocalDateTime.now();
				String dayName = dateTime.atZone(ZoneId.systemDefault()).getDayOfWeek().getDisplayName(TextStyle.FULL,
						new Locale("TR"));

				System.out.println(ZoneId.systemDefault());
				System.out.println(dayName);
			}

		}

		/*
		 * the DateTimeFormatterBuilder class lets you define complex formatters step by
		 * step using meaningful methods. In addition, it provides you with the ability
		 * to have case-insensitive parsing, lenient parsing (allowing the parser to use
		 * heuristics to interpret inputs that don’t precisely match the specified
		 * format), padding, and optional sections of the formatter. For example, you
		 * can programmatically build the same italianFormatter we used in listing 12.11
		 * through the DateTimeFormatterBuilder as follows.
		 */
		{
			DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()

					.appendText(ChronoField.DAY_OF_MONTH)

					.appendLiteral(". ")

					.appendText(ChronoField.MONTH_OF_YEAR)

					.appendLiteral(" ")

					.appendText(ChronoField.YEAR)

					.parseCaseInsensitive()

					.toFormatter(Locale.ITALIAN);
		}

	}

}
