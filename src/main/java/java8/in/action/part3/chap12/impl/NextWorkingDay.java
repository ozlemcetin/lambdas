package java8.in.action.part3.chap12.impl;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextWorkingDay implements TemporalAdjuster {

	/*
	 * Develop a class named NextWorkingDay, implementing the TemporalAdjuster
	 * interface that moves a date forward by one day but skips Saturdays and
	 * Sundays.
	 */

	@Override
	public Temporal adjustInto(Temporal temporal) {

		/*
		 * Read the current day.
		 */
		DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

		/*
		 * Normally add one day
		 */
		int dayToAdd = 1;
		{
			/*
			 * But add three days if today is a Friday.
			 */
			if (dow == DayOfWeek.FRIDAY)
				dayToAdd = 3;

			/*
			 * Or two if it’s a Saturday.
			 */
			else if (dow == DayOfWeek.SATURDAY)
				dayToAdd = 2;
		}

		/*
		 * Return the modified date adding the right number of days.
		 */
		return temporal.plus(dayToAdd, ChronoUnit.DAYS);
	}

}
