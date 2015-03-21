package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.oopsididitagain.rpg_iter2.models.menus.AvatarCreationMenu;
import com.oopsididitagain.rpg_iter2.models.menus.MenuButton;
import com.oopsididitagain.rpg_iter2.views.View;

public class AvatarCreationMenuViewInteraction extends ModelViewInteraction{
	int padding = 10;
	
	AvatarCreationMenu avatarCreationMenu;
	MenuButton summonerButton;
	MenuButton smasherButton;
	MenuButton sneakButton;
	
	public AvatarCreationMenuViewInteraction(AvatarCreationMenu avatarCreationMenu){
		this.avatarCreationMenu = avatarCreationMenu;
		this.summonerButton = new MenuButton(avatarCreationMenu.getOptions(0), 0 , 0, View.WIDTH/3, View.HEIGHT);
		this.smasherButton = new MenuButton(avatarCreationMenu.getOptions(1),View.WIDTH/3, 0, View.WIDTH/3, View.HEIGHT);
		this.sneakButton = new MenuButton(avatarCreationMenu.getOptions(2), (2 * View.WIDTH)/3, 0, View.WIDTH/3, View.HEIGHT);

		
		
	}

	@Override
	public void accept(View view) {
		view.visit(this);
		
	}

	@Override
	public void drawModel(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		g.fillRect(0,0,View.WIDTH,View.HEIGHT);
		Font f = new Font("arial", Font.BOLD, 30);
		g.setColor(Color.WHITE);
		g2d.draw(smasherButton);
		g2d.draw(summonerButton);
		g2d.draw(sneakButton);
		g.setColor(Color.BLUE);
		switch(avatarCreationMenu.getCurrentOption()) {
		case 0: g2d.fillRect(summonerButton.x, summonerButton.y, summonerButton.width, summonerButton.height);
			break;
		case 1: g2d.fillRect(smasherButton.x, smasherButton.y, smasherButton.width, smasherButton.height);
			break;
		case 2: g2d.fillRect(sneakButton.x, sneakButton.y, sneakButton.width, sneakButton.height);
			break;
		default: break;
		}
		g.setFont(f);
		g.setColor(Color.BLACK);
		g.drawString("Summoner", summonerButton.x + 30, summonerButton.y + 35);
		g.drawString("Sneak", sneakButton.x + 35, sneakButton.y + 35);
		g.drawString("Smasher", smasherButton.x + 30, smasherButton.y + 35);
	}
}
