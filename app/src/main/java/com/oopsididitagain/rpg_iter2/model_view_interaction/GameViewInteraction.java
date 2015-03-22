package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.SortedSet;

import com.oopsididitagain.rpg_iter2.assets.Assets;
import com.oopsididitagain.rpg_iter2.models.Game;
import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.views.View;


public class GameViewInteraction extends ModelViewInteraction{
  
    private Game game;
    private Avatar avatar;
    private GameMap gameMap;
    private Assets assets; 
    
    public GameViewInteraction(Game game){ //Constructor
    	assets = new Assets();
    	this.game = game;
    	this.gameMap = game.getGameMap();
    	this.avatar = game.getAvatar();
    }
	@Override
	public void accept(View view) {
		view.visit(this);		
	}

	
	//******* METHODS FOR DRAWING GAME MAP 
	@Override
	public void drawModel(Graphics g) {
		int left = avatar.getX() - 5;
		int right = avatar.getX() + 6;
		int top = avatar.getY() - 4 ;
		int bottom = avatar.getY() + 6;
		
		drawTopLeft(g, top, left);
		drawBottomLeft(g, bottom, left);
		drawTopRight(g, top, right);
		drawBottomRight(g, bottom, right);
       
        drawSkills(g);
        drawStats(g);
        
	}
private void drawTopRight(Graphics g, int top, int right){
		
		int x = 5;
		int y = 0;
		for(int i = avatar.getX(); i <= right; i++){
			y= 0;
			for(int j = top; j <= avatar.getY(); j++){
				
				if(gameMap.tileInbounds(new Position(j,i))){
					drawTile(g, gameMap.getTileAt(new Position(j,i)), x, y );
				}else{
					
					drawEmptyTile(g, x, y);	
				}
				y++;
			}
			x++;
		}
	}
private void drawBottomRight(Graphics g, int bottom, int right){
		
		int x = 5;
		int y = 4;
		for(int i = avatar.getX(); i <= right; i++){
			 y = 4;
			for(int j = avatar.getY(); j <= bottom; j++){
				
				if(gameMap.tileInbounds(new Position(j,i))){
					drawTile(g, gameMap.getTileAt(new Position(j,i)), x, y );
				}else{
					
					drawEmptyTile(g, x, y);	
				}
				y++;
			}
			x++;
		}
	}
private void drawBottomLeft(Graphics g, int bottom, int left){
		
		int x = 0;
		int y = 4;
		for(int i = left; i < avatar.getX(); i++){
			 y = 4;
			for(int j = avatar.getY(); j < bottom; j++){
				
				if(gameMap.tileInbounds(new Position(j,i))){
					drawTile(g, gameMap.getTileAt(new Position(j,i)), x, y );
				}else{
					
					drawEmptyTile(g, x, y);	
				}
				y++;
			}
			x++;
		}
	}
	private void drawTopLeft(Graphics g, int top, int left){
		
		int x = 0;
		int y = 0;
		for(int i = left; i < avatar.getX(); i++){
			 y = 0;
			for(int j = top; j < avatar.getY(); j++){

				//System.out.println(x + ", " + y);

				if(gameMap.tileInbounds(new Position(j,i))){
					drawTile(g, gameMap.getTileAt(new Position(j,i)), x, y );
				}else{
					
					drawEmptyTile(g, x, y);	
				}
				y++;
			}
			x++;
		}
	}
	private void drawEmptyTile(Graphics g, int x, int y){
		g.setColor(Color.BLACK);
		
		

		g.fillRect(x * 50, y  * 50, 50, 50);

		
	}

    private void drawSkills(Graphics g) {
		LinkedList<String> skills = avatar.getActiveSkillList();
		g.setColor(Color.black);
		g.fillRect(0, 500, View.pWidth, 60);
		int startingWidth = 0;
		int i = 1;
		for(String s: skills){
			g.setColor(Color.white);
			g.drawString(i + ": " + s, startingWidth, 530);
			startingWidth += 100;
			++i;
		}
		
	}
    private void drawStats(Graphics g) {
		LinkedList<String> skills = avatar.getActiveSkillList();
		g.setColor(Color.yellow);
		g.fillRect(0, 560, View.pWidth, View.pHeight - 560);
		int startingWidth = 0;
		String stats = avatar.StatToString();
		g.setColor(Color.black);
		g.drawString(stats, 0, 600);
		
	}
	private void drawTile(Graphics g, Tile t, int x, int y){
        SortedSet<Tileable> tileables =  t.getTilebles();
        Entity entity = t.getEntity();
        	
        Image bf = assets.getImage(tileables.first().getId());
        g.drawImage(bf, x *50,y *50,50,50,null);

        if(entity!=null){
        	String id = entity.getId();
        	if(entity.getEntityStatus().getStatus() == EntityStatus.SLEEPING){
        		id+="_sleeping";
        	}
        	if(entity.getEntityStatus().getStatus() == EntityStatus.SMELL){
        		id+="_badSmell";
        	}
        	if(entity.getEntityStatus().getStatus() == EntityStatus.SAD){
        		id+="_sad";
        	}
            Image b2 = assets.getImage(id);
            
            g.drawImage(b2, x *50, y *50,50,50,null);

        	
        }
    }
	



}
