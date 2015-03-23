package com.oopsididitagain.rpg_iter2.models;

import java.awt.Graphics;

public class Bomb {

	private int radiusEffected;
	
	public Bomb(Position p){
		radiusEffected = 0;
		
	}
	
	
	public void update(){
		
		radiusEffected++;
		
	}
	public void render(Graphics g){
		
		
		
	}
}
