package java8.in.action.part1.chap03.impl;

@FunctionalInterface
public interface TriFunction<T, U, V, R> {

	R apply(T t, U u, V v);
}
