package com.lp3a5.threads;

public class Exercicio2 {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new RunnableEx(), "t1");
		Thread t2 = new Thread(new RunnableEx(), "t2");
		Thread t3 = new Thread(new RunnableEx(), "t3");

		System.out.println("Inicio das Threads");
		
		t1.start();
		t2.start();
		//t2.join();
		t3.start();
		//t3.join();
		t1.join();
		t2.join();
		t3.join();

		System.out.println("Fim das Threads");
		
	}
}

class RunnableEx implements Runnable {

	@Override
	public void run() {
		//System.out.println("Thread iniciou:::" + Thread.currentThread().getName());
		try {
			Thread.sleep(10000);
			System.out.println("String da Thread " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("Thread ended:::" + Thread.currentThread().getName());
	}

}
