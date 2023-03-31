package Exercicio2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncedDeadlock {
	private final Lock lockA = new ReentrantLock();
	private final Lock lockB = new ReentrantLock();
	private final Lock lockC = new ReentrantLock();

	public void myMethod1() {
        System.out.println("MyMethod1 iniciou sua execução");
		
		boolean lockAadquiriu = false;
        boolean lockBadquiriu = false;

        while (true) {
            try {
                lockAadquiriu = lockA.tryLock();
                if (lockAadquiriu) {
                    System.out.println("MyMethod1 adquiriu LockA");
                    lockBadquiriu = lockB.tryLock();
                    if (lockBadquiriu) {
                        System.out.println("MyMethod1 adquiriu LockB");
                        break;
                    }
                }
            } finally {
                if (!lockBadquiriu && lockAadquiriu) {
                    lockA.unlock();
                    System.out.println("MyMethod1 liberou LockA");
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lockB.unlock();
        System.out.println("MyMethod1 liberou LockB");

        lockA.unlock();
        System.out.println("MyMethod1 liberou LockA");
        
        System.out.println("MyMethod1 finalizou sua execução");
	}

	public void myMethod2() {
        System.out.println("MyMethod2 iniciou sua execução");
		
		boolean lockBAdquiriu = false;
        boolean lockCAdquiriu = false;

        while (true) {
            try {
                lockBAdquiriu = lockB.tryLock();
                if (lockBAdquiriu) {
                    System.out.println("MyMethod2 adquiriu LockB");
                    lockCAdquiriu = lockC.tryLock();
                    if (lockCAdquiriu) {
                        System.out.println("MyMethod2 adquiriu LockC");
                        break;
                    }
                }
            } finally {
                if (!lockCAdquiriu && lockBAdquiriu) {
                	lockB.unlock();
                    System.out.println("MyMethod2 liberou LockB");
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lockC.unlock();
        System.out.println("MyMethod2 liberou LockC");

        lockB.unlock();
        System.out.println("MyMethod2 liberou LockB");
        
        System.out.println("MyMethod2 finalizou sua execução");
	}

	public void myMethod3() {
        System.out.println("MyMethod3 iniciou sua execução");
		
		boolean lockCAdquiriu = false;
        boolean lockAAdquiriu = false;

        while (true) {
            try {
                lockCAdquiriu = lockC.tryLock();
                if (lockCAdquiriu) {
                    System.out.println("MyMethod3 adquiriu LockC");
                    lockAAdquiriu = lockA.tryLock();
                    if (lockAAdquiriu) {
                        System.out.println("MyMethod3 adquiriu LockA");
                        break; 
                    }
                }
            } finally {
                if (!lockAAdquiriu && lockCAdquiriu) {
                	lockC.unlock();
                    System.out.println("MyMethod3 liberou LockC");
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lockA.unlock();
        System.out.println("MyMethod3 liberou LockA");

        lockC.unlock();
        System.out.println("MyMethod3 liberou LockC");
        
        System.out.println("MyMethod3 finalizou sua execução");
	}
	
	public static void main(String[] args) {
		SyncedDeadlock simulation = new SyncedDeadlock();

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
