package java8.in.action.part3.chap09.impl;

public interface B extends A {

	default void hello() {

		System.out.println("Hello from B");
	}
}
