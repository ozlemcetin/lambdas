package in.action.chap02;

public class DRunThreads04 {

	public static void main(String[] args) {

		{
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println("Hello world!");

				}
			});

			t.run();
		}

		{
			Thread t = new Thread(() -> System.out.println("Hello lambdas!"));

			t.run();
		}

	}

}
