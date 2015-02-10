package model;

import java.util.Observable;

public abstract class PrimaryStat extends Observable implements Stat {

	private int value;
	
	public PrimaryStat(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public abstract boolean updateValue(int value);
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.getClass().getName());
		s.append("\n");
		s.append(this.getValue());
		
		return s.toString();
	}
}
