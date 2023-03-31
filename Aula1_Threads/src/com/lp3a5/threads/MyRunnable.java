package com.lp3a5.threads;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread iniciou:::"+Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread ended:::"+Thread.currentThread().getName());
    }
    
}
