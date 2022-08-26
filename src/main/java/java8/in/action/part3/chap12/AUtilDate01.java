package java8.in.action.part3.chap12;

import java.util.Date;

public class AUtilDate01 {

	public static void main(String[] args) throws Exception {

		/*
		 * In Java 1.0 the only support for date and time was the java.util.Date class.
		 * 
		 * Despite its name, this class doesn’t represent a date but a point in time
		 * with milliseconds precision.
		 * 
		 * Even worse, the usability of this class is harmed by some nebulous design
		 * decisions like the choice of its offsets: the years start from 1900, whereas
		 * the months start at index 0.
		 * 
		 * This means that if you want to represent the release date of Java 8, which is
		 * March 18, 2014, you have to create an instance of Date as follows:
		 */
		{
			// Thu Mar 18 00:00:00 TRT 2004
			Date date = new Date(114, 2, 18);
			System.out.println(date);
		}

		/*
		 * in Java 1.1 many of the Date class's methods were deprecated, and it was
		 * replaced with the alternative java.util.Calendar class.
		 */

		/*
		 * For instance, months also start at index 0 (at least Calendar got rid of the
		 * 1900 offset for the year).
		 * 
		 * 
		 * Even worse, the presence of both the Date and Calendar classes increases
		 * confusion among developers. Which one should you use?
		 * 
		 * 
		 */

	}

}
