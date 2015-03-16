package com.oopsididitagain.rpg_iter2.models.Stats;

public class Stat {

	// TODO: Add logic to cap @ 100 some things..
	private double value;
	
	public Stat(double value)  {
		this.value = value;
	}
	
	public void add(Stat other) {
		value += other.getValue();
	}
	
	public void add(long value) {
		this.value += value;
	}
	
	public void subtract(Stat other) {
		value -= other.getValue();
	}
	
	public void subtract(long value) {
		this.value -= value;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return Double.toString(value);
	}
	
	public int toInt() { return (int)value; }
	
}