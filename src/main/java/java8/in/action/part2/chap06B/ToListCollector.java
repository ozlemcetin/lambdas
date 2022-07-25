package java8.in.action.part2.chap06B;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

	/*
	 * 1) MAKING A NEW RESULT CONTAINER: THE SUPPLIER METHOD
	 * 
	 * Creates the collection operation starting point
	 */

	@Override
	public Supplier<List<T>> supplier() {

		// return () -> new ArrayList<T>();

		// return ArrayList::new;

		return new Supplier<List<T>>() {

			@Override
			public List<T> get() {

				return new ArrayList<T>();
			}
		};

	}

	/*
	 * 2) ADDING AN ELEMENT TO A RESULT CONTAINER: THE ACCUMULATOR METHOD
	 * 
	 * Accumulates the traversed item, modifying the accumulator in place
	 */

	@Override
	public BiConsumer<List<T>, T> accumulator() {

		// return (list, item) -> list.add(item);

		// return List::add;

		return new BiConsumer<List<T>, T>() {

			@Override
			public void accept(List<T> t, T u) {

				t.add(u);
			}
		};
	}

	/*
	 * 3) APPLYING THE FINAL TRANSFORMATION TO THE RESULT CONTAINER: THE FINISHER
	 * METHOD
	 * 
	 * as in the case of the ToListCollector, the accumulator object already
	 * coincides with the final expected result. As a consequence, there’s no need
	 * to perform a transformation, so the finisher method just has to return the
	 * identity function:
	 * 
	 * Identity function
	 */
	@Override
	public Function<List<T>, List<T>> finisher() {

		// return Function.identity();

		return new Function<List<T>, List<T>>() {

			@Override
			public List<T> apply(List<T> t) {

				return t;
			}
		};
	}

	/*
	 * 4) MERGING TWO RESULT CONTAINERS: THE COMBINER METHOD
	 * 
	 * Modifies the first accumulator, combining it with the content of the second
	 * one
	 */

	@Override
	public BinaryOperator<List<T>> combiner() {

		/*
		 * return (list1, list2) -> {
		 * 
		 * list1.addAll(list2);
		 * 
		 * return list1;
		 * 
		 * };
		 * 
		 */
		return new BinaryOperator<List<T>>() {

			@Override
			public List<T> apply(List<T> t, List<T> u) {

				t.addAll(u);

				// Returns the modified first accumulator
				return t;
			}
		};
	}

	/*
	 * 5) CHARACTERISTICS METHOD
	 * 
	 * Flags the collector as IDENTITY_FINISH and CONCURRENT
	 */
	@Override
	public Set<Characteristics> characteristics() {

		return Collections.unmodifiableSet(
				EnumSet.of(Collector.Characteristics.IDENTITY_FINISH, Collector.Characteristics.CONCURRENT));
	}

}
