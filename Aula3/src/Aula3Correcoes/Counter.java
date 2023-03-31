package Aula3Correcoes;

public class Counter {
	private int value;

    public synchronized void increment() {
        value++;
    }

    public int getValue() {
        return value;
    }
}
