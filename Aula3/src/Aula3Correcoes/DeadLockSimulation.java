package Aula3Correcoes;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockSimulation {
    private final Lock lock1 = new ReentrantLock(); // Cria dois objetos Lock
    private final Lock lock2 = new ReentrantLock();

    public void method1() {
        boolean lock1Acquired = false;
        boolean lock2Acquired = false;

        while (true) {
            try {
                lock1Acquired = lock1.tryLock(); // Tenta adquirir o lock 1
                if (lock1Acquired) {
                    System.out.println("Method 1 acquired lock 1");
                    lock2Acquired = lock2.tryLock(); // Tenta adquirir o lock 2
                    if (lock2Acquired) {
                        System.out.println("Method 1 acquired lock 2");
                        break; // Sai do loop se adquiriu os dois locks
                    }
                }
            } finally {
                if (!lock2Acquired && lock1Acquired) {
                    lock1.unlock(); // Libera o lock 1 se não adquiriu o lock 2
                    System.out.println("Method 1 released lock 1");
                }
            }
            try {
                Thread.sleep(100); // Espera um pouco antes de tentar novamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lock2.unlock(); // Libera o lock 2
        System.out.println("Method 1 released lock 2");

        lock1.unlock(); // Libera o lock 1
        System.out.println("Method 1 released lock 1");
    }

    public void method2() {
        boolean lock1Acquired = false;
        boolean lock2Acquired = false;

        while (true) {
            try {
                lock2Acquired = lock2.tryLock(); // Tenta adquirir o lock 2
                if (lock2Acquired) {
                    System.out.println("Method 2 acquired lock 2");
                    lock1Acquired = lock1.tryLock(); // Tenta adquirir o lock 1
                    if (lock1Acquired) {
                        System.out.println("Method 2 acquired lock 1");
                        break; // Sai do loop se adquiriu os dois locks
                    }
                }
            } finally {
                if (!lock1Acquired && lock2Acquired) {
                    lock2.unlock(); // Libera o lock 2 se não adquiriu o lock 1
                    System.out.println("Method 2 released lock 2");
                }
            }
            try {
                Thread.sleep(100); // Espera um pouco antes de tentar novamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lock1.unlock(); // Libera o lock 1
        System.out.println("Method 2 released lock 1");

        lock2.unlock(); // Libera o lock 2
        System.out.println("Method 2 released lock 2");
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
