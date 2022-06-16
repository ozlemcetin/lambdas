package in.action.chap01;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import in.action.model.Apple;

/**
 * Hello world!
 *
 */
public class AExample00 {

	public static void main(String[] args) {

		sortExample();

		fileExample();

	}

	public static void sortExample() {

		List<Apple> inventory = Arrays.asList(new Apple(80, "green"),

				new Apple(155, "green"),

				new Apple(120, "red"),

				new Apple(70, "yellow"));

		{
			Collections.sort(inventory, new Comparator<Apple>() {

				@Override
				public int compare(Apple o1, Apple o2) {
					return o1.getWeight().compareTo(o2.getWeight());
				}

			});

		}

		// inventory.sort(comparing(Apple::getWeight));

		System.out.println(inventory);

	}

	public static void fileExample() {

		if (false) {

			File[] hiddenFiles = new File(".").listFiles(new FileFilter() {

				@Override
				public boolean accept(File file) {
					// Filtering hidden files!
					return file.isHidden();
				}
			});

		}

		File[] hiddenFiles = new File(".").listFiles(File::isHidden);

		// [.\.classpath, .\.project, .\.settings, .\pom.xml, .\src, .\target]
		System.out.println(Arrays.asList(hiddenFiles));

	}

}
