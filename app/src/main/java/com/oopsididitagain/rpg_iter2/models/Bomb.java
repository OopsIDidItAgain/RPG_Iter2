package com.oopsididitagain.rpg_iter2.models;

import java.awt.Color;
import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;

public class Bomb {
	private Position avatarPosition;
	private int radiusEffected;
	
	public Bomb(Position p){
		radiusEffected = 4;
		this.avatarPosition = p;
	}
	
	
	public void update(){
		
		radiusEffected-= 1;
		
	}
	public int getRadius(){
		
		return radiusEffected;
	}
	public Position getPosition(){
		return avatarPosition;
	}
	
	public StatBlob getStatBlob() {
		return new StatBlob(0, 0, 0, 0, 0, 0, 0, -2, 0);
	}
	
	public void render(Graphics g, int r){
		
		if(r < 0){
			return;
		}else{
			drawRender(g, r);
			render(g,  r - 1);
		}
	}
	public void drawRender(Graphics g, int r){
		for(int i = 0; i <= r ; i++){
			for(int j = 0; j <= r; j++){

				
				if(r == 3){
					g.setColor(Color.RED);
				}else if(r == 2){
					g.setColor(Color.GREEN);	
				}else if(r == 1){
					g.setColor(Color.BLUE);	
				}else if(r == 0){
					g.setColor(Color.BLACK);	
				}
				g.fillRect( (avatarPosition.getX() - i)* 50,(avatarPosition.getY() + j) * 50, 50, 50);
				g.fillRect( (avatarPosition.getX() + i)* 50,(avatarPosition.getY() + j) * 50, 50, 50);
				g.fillRect( (avatarPosition.getX() - i)* 50,(avatarPosition.getY() - j) * 50, 50, 50);

				
				g.fillRect( (avatarPosition.getX() + i)* 50,(avatarPosition.getY() - j) * 50, 50, 50);
			}	
	  
		}
		
		
		
	}
	
	


	public boolean isAlive() {
		return radiusEffected >= 0.0f;
	}
}
