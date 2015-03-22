package com.oopsididitagain.rpg_iter2.models;

public class Trap {

	private boolean detected = false;
	private Position position;
	private boolean sprung = false;
	private int range;
	
	public Trap(Position position, int range) {
		this.position = position;
		this.range = range;
	}
	
	public boolean isDetected() {
		return detected;
	}
	
	public void detect() {
		//TODO: Add probability of successful detection
		detected = true;
	}
	
	public void remove() {
		//TODO: Add probability of successful removal
		sprung = true;
	}
	
}