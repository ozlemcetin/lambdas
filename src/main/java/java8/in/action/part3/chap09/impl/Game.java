package java8.in.action.part3.chap09.impl;

import java.util.Arrays;
import java.util.List;

public class Game {

	public static void main(String... args) {

		// A list of shapes that are resizable
		List<Resizable> resizableShapes = Arrays.asList(new Square(), new Triangle(), new Ellipse());

		paint(resizableShapes);
	}

	public static void paint(List<Resizable> list) {

		/*
		 * Calling the setAbsoluteSize method on each shape
		 */
		list.forEach(resizable -> {

			resizable.setAbsoluteSize(42, 42);
			resizable.draw();

		});
	}

}
