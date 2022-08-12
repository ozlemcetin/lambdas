package java8.in.action.part3.chap09;

import java8.in.action.part3.chap09.impl.Monster;

public class BComposingInterfaces02 {

	public static void main(String[] args) throws Exception {

		{
			/*
			 * monsters can be moveable, rotatable, and resizable:
			 * 
			 * Monster inherits the implementations of rotateBy, moveHorizontally,
			 * moveVertically, and setRelativeSize.
			 */

			Monster m = new Monster();
			m.rotateBy(180);
			m.moveVertically(10);
		}
	}

}
