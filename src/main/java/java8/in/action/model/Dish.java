package java8.in.action.model;

public class Dish {

	private final String name;

	private final boolean vegetarian;

	private final int calories;

	private final Type type;

	/*
	 * 
	 */
	public Dish(String name, boolean vegetarian, int calories, Type type) {

		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	/*
	 * 
	 */
	public enum Type {

		MEAT, FISH, OTHER
	}

	public enum CaloricLevel {
		DIET, NORMAL, FAT
	}

	/*
	 * 
	 */
	public String getName() {
		return name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Dish [name=" + name + ", vegetarian=" + vegetarian + ", calories=" + calories + ", type=" + type + "]";
	}

}
