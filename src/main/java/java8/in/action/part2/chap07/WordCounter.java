package java8.in.action.part2.chap07;

public class WordCounter {

	/*
	 * you’ll have to carry a state consisting of two variables:
	 * 
	 * an int counting the number of words found so far and
	 * 
	 * a boolean to remember if the lastencountered Character was a space or not
	 */
	private final int counter;
	private final boolean lastSpace;

	public WordCounter(int counter, boolean lastSpace) {
		this.counter = counter;
		this.lastSpace = lastSpace;
	}

	/*
	 * The accumulate method traverses the Characters one by one as done by the
	 * iterative algorithm.
	 */

	public WordCounter accumulate(Character c) {

		if (Character.isWhitespace(c)) {

			return lastSpace ? this : new WordCounter(counter, true);

		} else {

			/*
			 * Increase the word counter when the last character is a space and the
			 * currently traversed one isn’t.
			 */
			return lastSpace ? new WordCounter(counter + 1, false) : this;
		}
	}

	/*
	 * Combine two WordCounters by summing their counters.
	 */

	public WordCounter combine(WordCounter wordCounter) {

		/*
		 * Use only the sum of the counters so you don’t care about lastSpace
		 */
		return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);

	}

	public int getCounter() {
		return counter;
	}
}
