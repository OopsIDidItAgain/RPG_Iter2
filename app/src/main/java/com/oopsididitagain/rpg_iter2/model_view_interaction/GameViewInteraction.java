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
        int Xi = 0;
        int Yi = 0;

        int Xf = 9;
        int Yf = 9;

        for(int y = Yi; y <= Yf; y++ ){
            for(int x = Xi; x<= Xf; x++){
                try{

                    drawTile(g, gameMap.getTileAt(new Position(y, x)), x, y);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }
        drawSkills(g);
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
        
        	
        Image bf = assets.getImage(tileables.first().getId());
        g.drawImage(bf, x*50,y*50,50,50,null);

        if(t.getEntity()!=null){
            Image b2 = assets.getImage(t.getEntity().getId());
            g.drawImage(b2, x*50,y*50,50,50,null);

        	
        }
    }

}
