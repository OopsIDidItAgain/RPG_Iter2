package com.oopsididitagain.rpg_iter2.models.Entities;
/*
 * This class holds the current status of the entity. 
 * We can make him sleep play prance cartwheel THE POSSIBILITIES ARE ENDLESS.
 */
public class EntityStatus {
	public static int PLAYING = 0;
	public static int SLEEPING = 1;
	private int status;
	EntityStatus(int state){
		this.status = state;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
