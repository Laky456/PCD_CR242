public class Main {
	public static void main(String args[]) throws InterruptedException {
	//Varianta 1: https://app.diagrams.net/#LGrup1.drawio#%7B%22pageId%22%3A%22QkcJFsA3rlYDPUF23g55%22%7D
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
	ThreadGroup sys = Thread.currentThread().getThreadGroup();
	Thread curr = Thread.currentThread();
	curr.setPriority(3);
	}
}
