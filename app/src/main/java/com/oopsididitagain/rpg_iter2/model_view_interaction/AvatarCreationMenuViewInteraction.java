package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.oopsididitagain.rpg_iter2.models.menus.AvatarCreationMenu;
import com.oopsididitagain.rpg_iter2.models.menus.MenuButton;
import com.oopsididitagain.rpg_iter2.views.View;

public class AvatarCreationMenuViewInteraction extends ModelViewInteraction{
	int width = 600;
	int height = 700;
	int padding = 10;
	
	AvatarCreationMenu avatarCreationMenu;
	MenuButton summonerButton;
	MenuButton smasherButton;
	MenuButton sneakButton;
	
	public AvatarCreationMenuViewInteraction(AvatarCreationMenu avatarCreationMenu){
		this.avatarCreationMenu = avatarCreationMenu;
		this.summonerButton = new MenuButton(avatarCreationMenu.getOptions(0), 0 , 0, width/3, height);
		this.smasherButton = new MenuButton(avatarCreationMenu.getOptions(1),width/3, 0, width/3, height);
		this.sneakButton = new MenuButton(avatarCreationMenu.getOptions(2), (2 * width)/3, 0, width/3, height);

		
		
	}

	@Override
	public void accept(View view) {
		view.visit(this);
		
	}

	@Override
	public void drawModel(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		g.fillRect(0,0,600,700);
		Font f = new Font("arial", Font.BOLD, 30);
		g.setFont(f);
		g.setColor(Color.WHITE);
		
		g.setColor(Color.WHITE);
		g2d.fillRect(smasherButton.x, smasherButton.y, smasherButton.width, smasherButton.height);
		g2d.draw(summonerButton);
		g2d.draw(sneakButton);
		g.drawString("Load Game", summonerButton.x + 30, summonerButton.y + 35);
		g.drawString("Controls", sneakButton.x + 35, sneakButton.y + 35);
		g.setColor(Color.BLACK);
		g.drawString("New Game", smasherButton.x + 30, smasherButton.y + 35);
		
				
	}
}
