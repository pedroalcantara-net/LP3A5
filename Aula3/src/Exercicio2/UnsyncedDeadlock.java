package Exercicio2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Aula3Erros.DeadLockSimulation;

public class UnsyncedDeadlock {
	private final Lock lockA = new ReentrantLock();
	private final Lock lockB = new ReentrantLock();
	private final Lock lockC = new ReentrantLock();

	public void myMethod1() {
        System.out.println("MyMethod1 iniciou sua execução");
		
		lockA.lock();
		System.out.println("MyMethod1 adquiriu LockA");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		lockB.lock();
		System.out.println("MyMethod1 adquiriu LockB");

		lockB.unlock();
		System.out.println("MyMethod1 liberou LockB");

		lockA.unlock();
		System.out.println("MyMethod1 liberou LockA");
		
        System.out.println("MyMethod1 finalizou sua execução");
	}

	public void myMethod2() {
        System.out.println("MyMethod2 iniciou sua execução");
		
		lockB.lock();
		System.out.println("MyMethod2 adquiriu LockB");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		lockC.lock();
		System.out.println("MyMethod2 adquiriu LockC");

		lockC.unlock(); 
		System.out.println("MyMethod2 liberou LockC");

		lockB.unlock();
		System.out.println("MyMethod2 liberou LockB");
		
        System.out.println("MyMethod2 finalizou sua execução");
	}

	public void myMethod3() {
        System.out.println("MyMethod3 iniciou sua execução");
        
		lockC.lock(); 
		System.out.println("MyMethod3 adquiriu LockB");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		lockA.lock(); 
		System.out.println("MyMethod3 adquiriu LockA");

		lockA.unlock(); 
		System.out.println("MyMethod3 liberou LockA");

		lockC.unlock(); 
		System.out.println("MyMethod3 liberou LockB");
		
        System.out.println("MyMethod3 finalizou sua execução");
	}
	
	public static void main(String[] args) {
		UnsyncedDeadlock simulation = new UnsyncedDeadlock();

		Thread t1 = new Thread(() -> {
			simulation.myMethod1();
		});

		Thread t2 = new Thread(() -> {
			simulation.myMethod2();
		});
		
		Thread t3 = new Thread(() -> {
			simulation.myMethod3();
		});

		t1.start();
		t2.start();
		t3.start();
	}
}
