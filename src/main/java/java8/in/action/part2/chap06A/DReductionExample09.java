package java8.in.action.part2.chap06A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class DReductionExample09 {

	public static void main(String[] args) throws Exception {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

		// collect
		{
			List<Integer> evens = numbers.stream()

					.filter(i -> (i % 2) == 0)

					.collect(Collectors.toList());

			System.out.println("Evens: " + evens);
		}

		// reduce
		{

			// U identity,
			List<Integer> identity = new ArrayList<>();

			// BiFunction<U, ? super T, U> accumulator,
			BiFunction<List<Integer>, Integer, List<Integer>> accumulator = new BiFunction<List<Integer>, Integer, List<Integer>>() {

				@Override
				public List<Integer> apply(List<Integer> t, Integer u) {

					t.add(u);
					return t;
				}
			};

			// BinaryOperator<U> combiner
			BinaryOperator<List<Integer>> combiner = new BinaryOperator<List<Integer>>() {

				@Override
				public List<Integer> apply(List<Integer> t, List<Integer> u) {

					t.addAll(u);
					return t;
				}
			};

			List<Integer> evens = numbers.stream()

					.filter(i -> (i % 2) == 0)

					.reduce(

							// identity
							identity,

							// accumulator
							accumulator,

							// combiner
							combiner);

			System.out.println("Evens: " + evens);

		}

		/*
		 * This solution has two problems: a semantic one and a practical one.
		 * 
		 * The semantic problem lies in the fact that the reduce method is meant to
		 * combine two values and produce a new one; it’s an immutable reduction.
		 * 
		 * In contrast, the collect method is designed to mutate a container to
		 * accumulate the result it’s supposed to produce.
		 * 
		 * This means that the previous snippet of code is misusing the reduce method
		 * because it’s mutating in place the List used as accumulator.
		 * 
		 * As you’ll see in more detail in the next chapter, using the reduce method
		 * with the wrong semantic is also the cause of a practical problem: this
		 * reduction process can’t work in parallel because the concurrent modification
		 * of the same data structure operated by multiple threads can corrupt the List
		 * itself.
		 * 
		 * In this case, if you want thread safety, you’ll need to allocate a new List
		 * every time, which would impair performance by object allocation.
		 * 
		 * This is the main reason why the collect method is useful for expressing
		 * reduction working on a mutable container but crucially in a parallel-friendly
		 * way, as you’ll learn later in the chapter.
		 */

	}

}
