package in.action.chap03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import in.action.chap03.impl.BufferedReaderProcessor;

public class DLambdaFileExamples04 {

	public static void main(String[] args) throws Exception {

		File file = new File(
				"C://Users//ocetin4//Desktop//Solution//lambdas//src//main//java//in//action//chap03//impl//MyFile.txt");

		{

			// Reads only the first line of the fileF
			System.out.println(processFile(file));

		}

		{
			/*
			 * Remember, lambda expressions let you provide the implementation of the
			 * abstract method of a functional interface directly inline, and they treat the
			 * whole expression as an instance of a functional interface
			 */

			System.out.println(processFile(file, (BufferedReader br) -> br.readLine()));

			System.out.println(processFile(file, (BufferedReader br) -> br.readLine() + br.readLine()));
		}
	}

	public static String processFile(File file) throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			// This is the line that does useful work.
			return br.readLine();
		}
	}

	/*
	 * Yes, you need to parameterize the behavior of processFile.
	 * 
	 * You need a way to pass behavior to processFile so it can execute different
	 * behaviors using a BufferedReader.
	 */

	public static String processFile(File file, BufferedReaderProcessor pf) throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			return pf.process(br);
		}
	}

}
