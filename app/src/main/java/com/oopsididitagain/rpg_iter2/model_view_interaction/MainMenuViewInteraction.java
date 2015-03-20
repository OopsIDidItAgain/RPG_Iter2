package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.oopsididitagain.rpg_iter2.assets.Assets;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.views.View;

public class MainMenuViewInteraction extends ModelViewInteraction{
	private MainMenu mainMenu;
	Assets assets;
	public void setMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		this.assets = new Assets();
		
	}
	@Override
	public void accept(View view) {
		view.visit(this);
		
	}
	@Override
	public void drawModel(Graphics g) {
		BufferedImage bf = assets.getBufferedImage("avatar");
		g.drawImage(bf, 0, 0, null);
		
		
	}

}
