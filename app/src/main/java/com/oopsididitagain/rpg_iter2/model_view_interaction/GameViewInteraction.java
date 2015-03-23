package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Iterator;
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
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.views.View;


public class GameViewInteraction extends ModelViewInteraction{
  
    private Game game;
    private Avatar avatar;
    private GameMap gameMap;
    private Assets assets; 
    private HashMap<String, Font> fonts = new HashMap<String, Font>();
    
    public GameViewInteraction(Game game){ //Constructor
    	assets = new Assets();
    	this.game = game;
    	this.gameMap = game.getGameMap();
    	this.avatar = game.getAvatar();
    	
    	// put all them fonts in!
    	fonts.put("default", new Font("TimesRoman", Font.PLAIN, 14));
    	fonts.put("inventoryheader", new Font("TimesRoman", Font.BOLD, 20));
    	fonts.put("inventorytext", new Font("TimesRoman", Font.PLAIN, 20));
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
		int height = 565, heightjump = 18, width = 10;
		String[] primaries = avatar.primaryStats();
		String[] deriveds = avatar.derivedStats();
		g.setColor(Color.black);
		for (String s : primaries) {
			if (s.equals("PRIMARY STATS")) g.setFont(fonts.get("inventoryheader"));
			
			if (height > 650) { // new line
				width += 125;
				height = 565 + heightjump + 10;
			}
			
			g.drawString(s, width, height += heightjump);
			
			if (s.equals("PRIMARY STATS")) {g.setFont(fonts.get("inventorytext")); height+=10;}
		}
		
		width = 375; height = 565;
		for (String s : deriveds) {
			if (s.equals("SECONDARY STATS")) g.setFont(fonts.get("inventoryheader"));
			g.drawString(s, width, height += heightjump);
			if (s.equals("SECONDARY STATS")) {g.setFont(fonts.get("inventorytext")); height+=10;}
		}
		
		g.setFont(fonts.get("default"));
	}
	private void drawTile(Graphics g, Tile t, int x, int y){
        SortedSet<Tileable> tileables =  t.getTilebles();
        Entity entity = t.getEntity();

        Image bf = assets.getImage(t.getTerrain().getId());
        g.drawImage(bf, x *50,y *50,50,50,null);



        Iterator<Tileable> tileablesIter = tileables.iterator();
        String tileableToDrawId = tileablesIter.next().getId();
        if(tileableToDrawId.equals("avatar"))
            tileableToDrawId = tileablesIter.next().getId();

        bf = assets.getImage(tileableToDrawId);
        g.drawImage(bf, x *50,y *50,50,50,null);

        Image effect = null;

        if(entity!=null){
        	String id = entity.getId();
        	if(entity.isCurrentlyFlying()){
        		id+="_flying";
        	}
            else if (entity.getDirection() == Direction.NORTHWEST) {
                id += "_northwest";
            }
            else if (entity.getDirection() == Direction.NORTH) {
                id += "_north";
            }
            else if (entity.getDirection() == Direction.NORTHEAST) {
                id += "_northeast";
            }
            else if (entity.getDirection() == Direction.EAST) {
                id += "_east";
            }
            else if (entity.getDirection() == Direction.WEST) {
                id += "_west";
            }
            else if (entity.getDirection() == Direction.SOUTHEAST) {
                id += "_southeast";
            }
            else if (entity.getDirection() == Direction.SOUTH) {
                id += "_south";
            }
            else if (entity.getDirection() == Direction.SOUTHWEST) {
                id += "_southwest";
            }

            if(entity.getEntityStatus().getStatus() == EntityStatus.SLEEPING){
                effect = assets.getImage("sleeping");
            }
            else if(entity.getEntityStatus().getStatus() == EntityStatus.SMELL){
                effect = assets.getImage("smells_bad");
            }
            else if(entity.getEntityStatus().getStatus() == EntityStatus.SAD){
                effect = assets.getImage("sad");
            }


            Image b2 = assets.getImage(id);

            g.drawImage(b2, x *50, y *50,50,50,null);

            if(effect != null){
                g.drawImage(effect, x *50, y *50,50,50,null);
            }

        	
        }
    }
	



}
