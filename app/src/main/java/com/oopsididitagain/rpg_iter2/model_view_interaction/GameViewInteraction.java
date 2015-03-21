package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.SortedSet;

import com.oopsididitagain.rpg_iter2.assets.Assets;
import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.views.View;

public class GameViewInteraction extends ModelViewInteraction{
    Assets assets;
    GameMap gameMap;
    Avatar avatar;

    public GameViewInteraction(){
    	assets = new Assets();
        gameMap = new GameMap(new MapDatabase("level1"));
        avatar = GameController.getInstance().getAvatar();
    	
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
	}

    private void drawTile(Graphics g, Tile t, int x, int y){
        SortedSet<Tileable> tileables =  t.getTilebles();


        BufferedImage bf = assets.getBufferedImage(tileables.first().getId());
        g.drawImage(bf, x*50,y*50,50,50,null);
    }

}
