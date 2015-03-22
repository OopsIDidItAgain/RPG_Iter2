package com.oopsididitagain.rpg_iter2.models.entities;
/*
 * This class holds the current status of the avatar.
 * We can make him sleep play prance cartwheel THE POSSIBILITIES ARE ENDLESS.
 */
public class EntityStatus {
	public static int PLAYING = 0;
	public static int SLEEPING = 1;
	public static int SAD = 2;
	public static int SMELL = 3;
	public static int TRAPPED = 4;
	private int status;
	public EntityStatus(int state){
		this.status = state;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
