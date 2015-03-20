package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import com.oopsididitagain.rpg_iter2.assets.Assets;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.models.menus.MenuButton;
import com.oopsididitagain.rpg_iter2.views.View;

public class MainMenuViewInteraction extends ModelViewInteraction{
	private MainMenu mainMenu;
	Assets assets;
	
	private MenuButton newGameButton;
	private MenuButton loadGameButton;
	private MenuButton controlsButton;
	
	public  MainMenuViewInteraction(MainMenu mainMenu) { //constructor
		int width = 600;
		int height = 700;
		int padding = 10;
		
		this.mainMenu = mainMenu;
		this.assets = new Assets();
		this.newGameButton = new MenuButton(mainMenu.getOptions(0), width/2 - 100 , height/2, 200, 50);
		this.loadGameButton = new MenuButton(mainMenu.getOptions(1),width/2 - 100, height/2 + 50 + padding, 200, 50);
		this.controlsButton = new MenuButton(mainMenu.getOptions(2), width/2 - 100, height/2 + 110 + padding, 200, 50);

	}
	@Override
	public void accept(View view) {
		view.visit(this);
		
	}
	@Override
	public void drawModel(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(0,0,600,700);
		Font f = new Font("arial", Font.BOLD, 30);
		g.setFont(f);
		g.setColor(Color.WHITE);
		

		//BufferedImage bf = assets.getBufferedImage(0);
		//g.drawImage(bf, 0, 0, null);
		
		
		switch (mainMenu.getCurrentOption()) {
				
				case 0:
					g.setColor(Color.WHITE);
					g2d.fillRect(newGameButton.x, newGameButton.y, newGameButton.width, newGameButton.height);
					g2d.draw(loadGameButton);
					g2d.draw(controlsButton);
					g.drawString("Load Game", loadGameButton.x + 30, loadGameButton.y + 35);
					g.drawString("Controls", controlsButton.x + 35, controlsButton.y + 35);
					g.setColor(Color.BLACK);
					g.drawString("New Game", newGameButton.x + 30, newGameButton.y + 35);
					
					
				
					break;
		
				case 1:
					g.setColor(Color.WHITE);

					g2d.draw(newGameButton);
					g2d.fillRect(loadGameButton.x, loadGameButton.y, loadGameButton.width, loadGameButton.height);
					g2d.draw(controlsButton);
					g.drawString("New Game", newGameButton.x + 30, newGameButton.y + 35);
					g.drawString("Controls", controlsButton.x + 35, controlsButton.y + 35);
					g.setColor(Color.BLACK);
					g.drawString("Load Game", loadGameButton.x + 30, loadGameButton.y + 35);
					
					break;
					
				case 2:
					g.setColor(Color.WHITE);

					g2d.draw(newGameButton);
					g2d.draw(loadGameButton);
					g2d.fillRect(controlsButton.x, controlsButton.y, controlsButton.width, controlsButton.height);
					g.drawString("New Game", newGameButton.x + 30, newGameButton.y + 35);
					g.drawString("Load Game", loadGameButton.x + 30, loadGameButton.y + 35);
					g.setColor(Color.BLACK);
					g.drawString("Controls", controlsButton.x + 35, controlsButton.y + 35);
				
					break;
				default: 
					g.setColor(Color.WHITE);

					g2d.draw(newGameButton);
					g2d.draw(loadGameButton);
					g2d.draw(controlsButton);
					g.drawString("New Game", newGameButton.x + 30, newGameButton.y + 35);
					g.drawString("Load Game", loadGameButton.x + 30, loadGameButton.y + 35);
					g.drawString("Controls", controlsButton.x + 35, controlsButton.y + 35);
				
					break;
					
				
				

		}
		

		

		
	}


}
