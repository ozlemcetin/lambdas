package java8.in.action.part3.chap08.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Point {

	private final int x;
	private final int y;

	/*
	 * let’s say you add a static field compareByXAndThenY in the Point class that
	 * gives you access to a Comparator object that’s generated from method
	 * references:
	 */
	public final static Comparator<Point> compareByXAndThenY = Comparator.comparing(Point::getX)
			.thenComparing(Point::getY);

	public Point(int x, int y) {

		this.x = x;
		this.y = y;
	}

	/*
	 * 
	 */
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/*
	 * 
	 */
	public Point moveRightBy(int x) {

		return new Point(this.x + x, y);
	}

	public static List<Point> moveAllPointsRightBy(List<Point> points, int x) {

		return points.stream()

				.map(p -> p.moveRightBy(x))

				.collect(Collectors.toList());

	}
}
