package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.oopsididitagain.rpg_iter2.models.menus.GameOverMenu;
import com.oopsididitagain.rpg_iter2.models.menus.GameOverMenu.Option;
import com.oopsididitagain.rpg_iter2.views.View;

public class GameOverViewInteraction extends ModelViewInteraction{
	private GameOverMenu gameOverMenu;
	
	//private MenuButton newGameButton;
	//private MenuButton loadGameButton;
	//private MenuButton controlsButton;
	
	public  GameOverViewInteraction(GameOverMenu gameOverMenu) { //constructor
		int width = 600;
		int height = 700;
		int padding = 10;
		this.gameOverMenu = gameOverMenu;
	}
	@Override
	public void accept(View view) {
		view.visit(this);	
	}
	
	@Override
	public void drawModel(Graphics g) {

        int width = 600;
        int height = 700;
        int padding = 10;

		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(0,0,width,height);
		Font f = new Font("arial", Font.BOLD, 30);
		g.setFont(f);
		g.setColor(Color.WHITE);


        int buttonX = width/2 - 100;
        int buttonY = height/2 ;
        Option[] options = gameOverMenu.getOptions();
        for(int i =0; i<options.length; i++){
        	Option option = options[i];
            if (option.equals(gameOverMenu.getCurrentOption())){
                g.setColor(Color.WHITE);
                g2d.fillRect(buttonX,buttonY,200,50);
                g.setColor(Color.BLACK);
                g.drawString(option.toString(), buttonX+30, buttonY+35);
            }else{
                g.setColor(Color.WHITE);
                g2d.drawRect(buttonX,buttonY,200,50);
                g.drawString(option.toString(), buttonX+30, buttonY+35);
            }
            buttonY+=70;
        }
	}
}
