package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.oopsididitagain.rpg_iter2.assets.Assets;
import com.oopsididitagain.rpg_iter2.views.View;

public class GameViewInteraction extends ModelViewInteraction{
    Assets assets;
    public GameViewInteraction(){
    	assets = new Assets();
    	
    }
	@Override
	public void accept(View view) {
		view.visit(this);		
	}

	@Override
	public void drawModel(Graphics g) {
		// TODO Auto-generated method stub
		BufferedImage bf = assets.getBufferedImage(0);
	    g.drawImage(bf, 0, 0, null);
	}

}
