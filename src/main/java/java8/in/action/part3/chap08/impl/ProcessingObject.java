package java8.in.action.part3.chap08.impl;

public abstract class ProcessingObject<T> {

	/*
	 * Chain of responsibility
	 */

	/*
	 * Generally, this pattern is implemented by defining an abstract class
	 * representing a processing object that defines a field to keep track of a
	 * successor.
	 * 
	 * Once it has finished its work, the processing object hands over its work to
	 * its successor.
	 */
	protected ProcessingObject<T> successor;

	public void setSuccessor(ProcessingObject<T> successor) {
		this.successor = successor;
	}

	public T handle(T input) {

		T r = handleWork(input);

		if (successor != null) {
			return successor.handle(r);
		}

		return r;
	}

	/*
	 * The method handle provides an outline of how to deal with a piece of work
	 * 
	 * Different kinds of processing objects can be created by subclassing the class
	 * ProcessingObject and by providing an implementation for the method
	 * handleWork.
	 */
	abstract protected T handleWork(T input);
}
