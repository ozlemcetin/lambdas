package java8.in.action.part3.chap08;

import java8.in.action.part3.chap08.impl.Feed;
import java8.in.action.part3.chap08.impl.Guardian;
import java8.in.action.part3.chap08.impl.NYTimes;
import java8.in.action.part3.chap08.impl.Observer;

public class DObserver06 {

	public static void main(String[] args) throws Exception {

		/*
		 * Let’s write some code to see how the observer pattern is useful in practice.
		 * 
		 * 
		 * You’ll design and implement a customized notification system for an
		 * application like Twitter.
		 * 
		 * The concept is simple: several newspaper agencies (NY Times, The Guardian,
		 * and Le Monde) are subscribed to a feed of news tweets and may want to receive
		 * a notification if a tweet contains a particular keyword.
		 */

		{
			/*
			 * It’s a pretty straightforward implementation: the feed keeps an internal list
			 * of observers that it can then notify when a tweet arrives.
			 * 
			 * You can now create a demo application to wire up the subject and observers:
			 */
			Feed f = new Feed();
			{
				f.registerObserver(new NYTimes());

				f.registerObserver(new Guardian());

				// f.registerObserver(new LeMonde());

				f.registerObserver(new Observer() {

					@Override
					public void notify(String tweet) {

						if (tweet != null && tweet.contains("wine")) {
							System.out.println("Today cheese, wine and news! " + tweet);
						}

					}
				});
			}

			f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
		}

		// USING LAMBDA EXPRESSIONS
		{
			/*
			 * Notice that the different classes implementing the Observer interface are all
			 * providing implementation for a single method: notify.
			 * 
			 * They’re all just wrapping a piece of behavior to execute when a tweet
			 * arrives!
			 * 
			 * Lambda expressions are designed specifically to remove that boilerplate.
			 * 
			 * Instead of instantiating three observer objects explicitly, you can pass a
			 * lambda expression directly to represent the behavior to execute:
			 */

			Feed f = new Feed();
			{
				f.registerObserver((String tweet) -> {

					if (tweet != null && tweet.contains("money")) {
						System.out.println("Breaking news in NY! " + tweet);
					}

				});

				f.registerObserver((String tweet) -> {

					if (tweet != null && tweet.contains("queen")) {
						System.out.println("Yet another news in London... " + tweet);
					}

				});
			}

			f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
		}

		{
			/*
			 * Should you use lambda expressions all the time? The answer is no!
			 * 
			 * In the example we described, lambda expressions work great because the
			 * behavior to execute is simple, so they’re helpful to remove boilerplate code.
			 * 
			 * But the observers may be more complex: they could have state, define several
			 * methods, and the like. In those situations, you should stick with classes.
			 */
		}
	}

}
