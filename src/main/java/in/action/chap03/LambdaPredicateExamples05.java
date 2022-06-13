package in.action.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaPredicateExamples05 {

	public static void main(String[] args) throws Exception {

		{
			List<String> listOfStrings = Arrays.asList("A", "", "B", "C", null, "D");

			List<String> nonEmptyStrings = filter(listOfStrings, (String s) -> s != null && !s.isEmpty());

			System.out.println(nonEmptyStrings);

		}
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {

		List<T> results = new ArrayList<>();
		{
			for (T t : list) {

				if (p.test(t)) {

					results.add(t);
				}

			} // for loop
		}
		return results;
	}

}
