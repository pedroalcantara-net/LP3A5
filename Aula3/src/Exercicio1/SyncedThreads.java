package Exercicio1;

public class SyncedThreads {
	public static void main(String[] args) throws InterruptedException {
		SyncedCounter myCounter = new SyncedCounter();

		Runnable task = () -> {
			for (int i = 0; i < 10001; i++) {
				myCounter.increment(i);
			}
			//resultado esperado da operação é 500500, a soma dos números de 0 a 100
		};

		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(myCounter.getValue()); // resultado esperado é 1001000, sem a variação
	}
}
