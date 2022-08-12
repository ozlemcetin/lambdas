package java8.in.action.part3.chap09.impl;

public interface A {

	default void hello() {

		System.out.println("Hello from A");
	}

	static void goodbye() {

		System.out.println("Goodbye from A");
	}

}
