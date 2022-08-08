package java8.in.action.part3.chap08.impl;

public interface Subject {

	/*
	 * You’re still missing the crucial part: the subject! Let’s define an interface
	 * for him:
	 */

	/*
	 * The subject can register a new observer using the registerObserver method and
	 * notify his observers of a tweet with the notifyObservers method.
	 */
	void registerObserver(Observer o);

	void notifyObservers(String tweet);
}
