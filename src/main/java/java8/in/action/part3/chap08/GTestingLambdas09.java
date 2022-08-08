package java8.in.action.part3.chap08;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import java8.in.action.part3.chap08.impl.Point;

public class GTestingLambdas09 {

	public static void main(String[] args) throws Exception {

	}

	/*
	 * The following unit test checks whether the method moveRightBy behaves as
	 * expected:
	 */
	@Test
	public void testMoveRightBy() throws Exception {

		Point p1 = new Point(5, 5);
		Point p2 = p1.moveRightBy(10);

		assertEquals(15, p2.getX());
		assertEquals(5, p2.getY());
	}

	/*
	 * Remember that lambda expressions generate an instance of a functional
	 * interface. As a result, you can test the behavior of that instance.
	 * 
	 * Here, you can now call the method compare on the Comparator object
	 * compareByXAndThenY with different arguments to test that its behavior is as
	 * intended:
	 */

	@Test
	public void testComparingTwoPoints() throws Exception {

		Point p1 = new Point(10, 15);
		Point p2 = new Point(10, 20);

		int result = Point.compareByXAndThenY.compare(p1, p2);
		assertEquals(-1, result);
	}

	@Test
	public void testMoveAllPointsRightBy() throws Exception {

		List<Point> points = Arrays.asList(new Point(5, 5), new Point(10, 5));

		Point.moveAllPointsRightBy(points, 10);

		List<Point> expectedPoints = Arrays.asList(new Point(15, 5), new Point(20, 5));

		assertEquals(points, expectedPoints);
	}
}
