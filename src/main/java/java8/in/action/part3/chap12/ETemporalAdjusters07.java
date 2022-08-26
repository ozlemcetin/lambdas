package java8.in.action.part3.chap12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class ETemporalAdjusters07 {

	public static void main(String[] args) throws Exception {

		{
			LocalDate date1 = LocalDate.of(2014, 3, 18);
			System.out.println(date1);

			// 2014-03-23
			LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
			System.out.println(date2);

			// 2014-03-31
			LocalDate date3 = date1.with(TemporalAdjusters.lastDayOfMonth());
			System.out.println(date3);
		}

	}

}
