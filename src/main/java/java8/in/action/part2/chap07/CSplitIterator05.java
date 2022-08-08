package java8.in.action.part2.chap07;

import java.util.Spliterator;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CSplitIterator05 {

	public static void main(String[] args) throws Exception {

		final String SENTENCE =

				// 7 words
				" Nel mezzo del cammin  di nostra vita   "

						// 6 words
						+ "mi ritrovai in una selva oscura   "

						// 6 words
						+ "ché la dritta via    era smarrita   ";

		// countWordsIteratively
		{

			System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
		}

		// REWRITING THE WORDCOUNTER IN FUNCTIONAL STYLE
		{
			// First, you need to convert the String into a stream
			Stream<Character> stream = IntStream.range(0, SENTENCE.length())

					.mapToObj(new IntFunction<Character>() {

						@Override
						public Character apply(int index) {
							return SENTENCE.charAt(index);
						}
					});

		}

		// REWRITING THE WORDCOUNTER IN FUNCTIONAL STYLE
		{

			// First, you need to convert the String into a stream
			Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);

			System.out.println("Found " + countWords(stream) + " words");
		}

		// MAKING THE WORDCOUNTER WORK IN PARALLEL
		{

			// First, you need to convert the String into a stream
			Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);

			System.out.println("Found " + countWords(stream.parallel()) + " words");
		}

		{
			Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);

			Stream<Character> stream = StreamSupport.stream(spliterator, true);

			System.out.println("Found " + countWords(stream) + " words");
		}

	}

	public static int countWordsIteratively(String s) {

		int counter = 0;
		{

			/*
			 * Traverse all the characters in the String one by one.
			 */
			boolean isLastCharacterSpace = false;

			for (char ch : s.toCharArray()) {

				if (Character.isWhitespace(ch)) {

					isLastCharacterSpace = true;

				} else {

					/*
					 * Increase the word counter when the last character is a space and the
					 * currently traversed one isn’t
					 */
					if (isLastCharacterSpace) {
						counter++;
					}

					isLastCharacterSpace = false;
				}

			} // for loop
		}
		return counter;
	}

	public static int countWords(Stream<Character> stream) {

		WordCounter wordCounter = stream.reduce(

				// U identity,
				new WordCounter(0, true),

				// BiFunction<U, ? super T, U> accumulator,
				WordCounter::accumulate,

				// BinaryOperator<U> combiner
				WordCounter::combine);

		return wordCounter.getCounter();
	}

}
