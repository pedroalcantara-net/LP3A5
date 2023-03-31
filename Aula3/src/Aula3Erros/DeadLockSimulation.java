package Aula3Erros;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockSimulation {
	private final Lock lock1 = new ReentrantLock(); // Cria dois objetos Lock
	private final Lock lock2 = new ReentrantLock();

	public void method1() {
		lock1.lock(); // Adquire o lock 1
		System.out.println("Method 1 adquiriu lock 1");

		try {
			Thread.sleep(1000); // Espera um segundo
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		lock2.lock(); // Adquire o lock 2
		System.out.println("Method 1 adquiriu lock 2");

		lock2.unlock(); // Libera o lock 2
		System.out.println("Method 1 liberou lock 2");

		lock1.unlock(); // Libera o lock 1
		System.out.println("Method 1 liberou lock 1");
	}

	public void method2() {
		lock2.lock(); // Adquire o lock 2
		System.out.println("Method 2 adquiriu lock 2");

		try {
			Thread.sleep(1000); // Espera um segundo
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		lock1.lock(); // Adquire o lock 1
		System.out.println("Method 2 adquiriu lock 1");

		lock1.unlock(); // Libera o lock 1
		System.out.println("Method 2 liberou lock 1");

		lock2.unlock(); // Libera o lock 2
		System.out.println("Method 2 liberou lock 2");
	}

	public static void main(String[] args) {
		DeadLockSimulation simulation = new DeadLockSimulation();

		Thread thread1 = new Thread(() -> {
			simulation.method1();
		});

		Thread thread2 = new Thread(() -> {
			simulation.method2();
		});

		thread1.start();
		thread2.start();
	}
}
