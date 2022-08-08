package java8.in.action.part3.chap08.impl;

public interface Observer {

	/*
	 * First, you need an Observer interface that groups the different observers.
	 * 
	 * It has just one method called notify that will be called by the subject
	 * (Feed) when a new tweet is available
	 */
	void notify(String tweet);
}
