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

	@Override
	public void drawModel(Graphics g) {
        int Xi = avatar.getPosition().getX() - 5;
        int Yi = avatar.getPosition().getY() - 4;

        int Xf =  avatar.getPosition().getX() + 6;
        int Yf =  avatar.getPosition().getY() + 5;

        for(int y = Yi; y <= Yf; y++ ){
            for(int x = Xi; x<= Xf; x++){
            	if(gameMap.tileInbounds(new Position(x,y))){
	                try{
	
	                    drawTile(g, gameMap.getTileAt(new Position(y, x)), x, y);
	
	                }catch (Exception e){
	                    e.printStackTrace();
	                }
            	}else{
            		   drawEmptyTile(g, x, y);
            		
            	}
            }

        }
        drawSkills(g);
	}
	private void drawEmptyTile(Graphics g, int x, int y){
		g.setColor(Color.BLACK);
		g.fillRect(x* 50, y * 50, 50, 50);
		
		
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
	private void drawTile(Graphics g, Tile t, int x, int y){
        SortedSet<Tileable> tileables =  t.getTilebles();
        Entity entity = t.getEntity();
        	
        Image bf = assets.getImage(tileables.first().getId());
        g.drawImage(bf, x*50,y*50,50,50,null);

        if(entity!=null){
        	String id = entity.getId();
        	if(entity.getEntityStatus().getStatus() == EntityStatus.SLEEPING){
        		id+="_sleeping";
        	}
            Image b2 = assets.getImage(id);
            
            g.drawImage(b2, x*50,y*50,50,50,null);

        	
        }
    }

}
