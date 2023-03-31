package Exercicio1;

public class SyncedCounter {
	private int value;

	public synchronized void increment(int i) {
		value =  value + i;
	}

	public int getValue() {
		return value;
	}
}
