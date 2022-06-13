package in.action.chap02;

/*
 * This is a functional interface. 
 * 
 * Why? Because Predicate specifies only one abstract method
 */

/*
 * 	In a nutshell, a functional interface is an interface that specifies exactly one abstract
	method. 
	
	You already know several other functional interfaces in the Java API such as
	Comparator and Runnable:
	
	public interface Comparator<T> {
		int compare(T o1, T o2);
	}
	
	public interface Runnable{
		void run();
	}
	
	
	public interface ActionListener extends EventListener{
		void actionPerformed(ActionEvent e);
	}
 */

/*
 * An interface is still a functional interface
 *  if it has many default methods as long as it specifies only one abstract method.
 */
public interface InventoryPredicate<T> {

	boolean test(T t);

}
