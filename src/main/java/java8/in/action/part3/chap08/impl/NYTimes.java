package java8.in.action.part3.chap08.impl;

public class NYTimes implements Observer {

	/*
	 * You can now declare different observers (here, the three newspapers) that
	 * produce a different action for each different keyword contained in a tweet:
	 */

	@Override
	public void notify(String tweet) {

		if (tweet != null && tweet.contains("money")) {
			System.out.println("Breaking news in NY! " + tweet);
		}
	}

}
