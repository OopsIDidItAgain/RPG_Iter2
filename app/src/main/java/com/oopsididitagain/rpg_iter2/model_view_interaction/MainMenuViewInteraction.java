package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.views.View;

public class MainMenuViewInteraction extends ModelViewInteraction{
	private MainMenu mainMenu;
	public void setMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		
	}
	@Override
	public void accept(View view) {
		view.visit(this);
		
	}
	@Override
	public void drawModel(Graphics g) {
		g.drawRect(0, 0, 100, 100);
		
	}

}
