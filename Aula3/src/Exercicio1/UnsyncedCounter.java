package Exercicio1;

public class UnsyncedCounter {
	private int value;

	public void increment(int i) {
		value =  value + i;
	}

	public int getValue() {
		return value;
	}
}
