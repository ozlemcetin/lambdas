package java8.in.action.part3.chap09.impl;

public class D implements B, C {

	public static void main(String... args) {

		new D().hello();

	}

	@Override
	public void hello() {
		System.out.println("Hello from D");

	}

}
