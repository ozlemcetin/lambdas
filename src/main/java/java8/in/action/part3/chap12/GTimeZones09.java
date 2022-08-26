package java8.in.action.part3.chap12;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class GTimeZones09 {

	public static void main(String[] args) throws Exception {

		{
			ZoneId romeZone = ZoneId.of("Europe/Rome");
			System.out.println(romeZone);

			// Asia/Istanbul
			ZoneId zoneId = TimeZone.getDefault().toZoneId();
			System.out.println(zoneId);
		}

		{

			ZoneId zoneId = ZoneId.of("Asia/Istanbul");

			{
				LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
				ZonedDateTime zdt1 = date.atStartOfDay(zoneId);

				// 2014-03-18T00:00+02:00[Asia/Istanbul]
				System.out.println(zdt1);

			}

			{
				LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
				ZonedDateTime zdt2 = dateTime.atZone(zoneId);

				// 2014-03-18T13:45+02:00[Asia/Istanbul]
				System.out.println(zdt2);
			}

			{

				Instant instant = Instant.now();

				// 2022-08-25T12:07:45.175+03:00[Asia/Istanbul]
				ZonedDateTime zdt3 = instant.atZone(zoneId);
				System.out.println(zdt3);

				// 2022-08-25T12:10:29.074
				LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
				System.out.println(localDateTime);
			}

			{
				LocalDateTime dateTime = LocalDateTime.now();
				ZonedDateTime zdt = dateTime.atZone(zoneId);

				// 2022-08-25-02.54.49.768000
				System.out.println("String: " + zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh.mm.ss.SSSSSS")));

			}
		}

	}

}
