public class Main {
	public static void main(String args[]) throws InterruptedException {
	//Varianta 1:
	/*
	main
		- Th1(7)
		- Th2(7)
		- ThA(3)
		- G1
			- G3
				- Tha(3)
				- Thb(3)
				- Thc(3)
				- Thd(3)
		-G2
			- Th1(4)
			- Th2(5)
			- Th3(5)
	*/
		ThreadGroup mainGroup =	Thread.currentThread().getThreadGroup();

		ThreadGroup g1 = new ThreadGroup(mainGroup, "G1");
		ThreadGroup g3 = new ThreadGroup(g1, "G3");
		ThreadGroup g2 = new ThreadGroup(mainGroup, "G2");

		// in main
		Thread th1main = startThread(mainGroup, "Th1", 7);
		Thread th2main = startThread(mainGroup, "Th2", 7);
		Thread thAmain = startThread(mainGroup, "ThA", 3);

		// in grupul 3
		Thread tha = startThread(g3, "Tha", 3);
		Thread thb = startThread(g3, "Thb", 3);
		Thread thc = startThread(g3, "Thc", 3);
		Thread thd = startThread(g3, "Thd", 3);

		// in grupul 2
		Thread th1g2 = startThread(g2, "Th1", 4);
		Thread th2g2 = startThread(g2, "Th2", 5);
		Thread th3g2 = startThread(g2, "Th3", 5);

		// print
		mainGroup.list();

		// Stop the workers and wait for them to exit.
		tha.interrupt();
		thb.interrupt();
		thc.interrupt();
		thd.interrupt();
		th1g2.interrupt();
		th2g2.interrupt();
		th3g2.interrupt();
		th1main.interrupt();
		th2main.interrupt();
		thAmain.interrupt();

		tha.join();
		thb.join();
		thc.join();
		thd.join();
		th1g2.join();
		th2g2.join();
		th3g2.join();
		th1main.join();
		th2main.join();
		thAmain.join();

	}
	private static Thread startThread(ThreadGroup group, String name, int priority) {
		Thread thread = new Thread(group, () -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}, name);
		thread.setPriority(priority);
		thread.start();
		return thread;
    }

}
