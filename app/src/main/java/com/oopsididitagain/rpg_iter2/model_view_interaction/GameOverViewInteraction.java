package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import com.oopsididitagain.rpg_iter2.assets.Assets;
import com.oopsididitagain.rpg_iter2.models.menus.GameOverMenu;
import com.oopsididitagain.rpg_iter2.models.menus.GameOverMenu.Option;
import com.oopsididitagain.rpg_iter2.views.View;

public class GameOverViewInteraction extends ModelViewInteraction{
	private GameOverMenu gameOverMenu;
	boolean win = false;
	
	public  GameOverViewInteraction(GameOverMenu gameOverMenu) { //constructor
		int width = 600;
		int height = 700;
		int padding = 10;
		this.gameOverMenu = gameOverMenu;
	}
	
	public GameOverViewInteraction(GameOverMenu gameOverMenu, boolean win) {
		int width = 600;
		int height = 700;
		int padding = 10;
		this.gameOverMenu = gameOverMenu;
		this.win = win;
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
        
        Assets assets = new Assets();
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(0,0,width,height);
		Font f = new Font("arial", Font.BOLD, 30);
		g.setFont(f);
		g.setColor(Color.WHITE);


        int buttonX = width/2 - 100;
        int buttonY = height/2 ;
        Option[] options = gameOverMenu.getOptions();
        
        Image pageJones, dave;
        if (win) {
        	pageJones = assets.getImage("mainman_happy");
        	dave = assets.getImage("congrats_logo");
        }
        else {
        	pageJones = assets.getImage("mainman_sad");
        	dave = assets.getImage("b");
        }
        
		g.drawImage(pageJones, View.pWidth/3, 70, 200, 300, null);
		g.drawImage(dave, View.pWidth/3+70, 5, View.pWidth/2+35, 230, null);

        
        for(int i =0; i<options.length; i++){
        	Option option = options[i];
            if (option.equals(gameOverMenu.getCurrentOption())){
                switch(option) {
	                case StartOver:
	                	g.setColor(Color.RED);
	                	break;
	                case ExitGame:
	                	g.setColor(Color.BLUE);
	                default:
	                	break;
                }
                g2d.fillRect(buttonX,buttonY,200,50);
                g.setColor(Color.BLACK);
                g.drawString(option.toString(), buttonX+30, buttonY+35);
            }else{
                switch(option) {
	                case StartOver:
	                	g.setColor(Color.RED);
	                	break;
	                case ExitGame:
	                	g.setColor(Color.BLUE);
	                default:
	                	break;
	            }                
                g2d.drawRect(buttonX,buttonY,200,50);
                g.drawString(option.toString(), buttonX+30, buttonY+35);
            }
            buttonY+=70;
        }
	}
}
