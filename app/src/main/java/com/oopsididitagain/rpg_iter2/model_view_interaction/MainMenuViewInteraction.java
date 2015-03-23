package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu.Option;
import com.oopsididitagain.rpg_iter2.views.View;

public class MainMenuViewInteraction extends ModelViewInteraction{
	private MainMenu mainMenu;
	
	//private MenuButton newGameButton;
	//private MenuButton loadGameButton;
	//private MenuButton controlsButton;
	
	public  MainMenuViewInteraction(MainMenu mainMenu) { //constructor
		int width = 600;
		int height = 700;
		int padding = 10;
		this.mainMenu = mainMenu;
        //numOfButtons = mainMenu.getNumOfOptions();

		//this.newGameButton = new MenuButton(mainMenu.getOptions(0), width/2 - 100 , height/2, 200, 50);
		//this.loadGameButton = new MenuButton(mainMenu.getOptions(1),width/2 - 100, height/2 + 50 + padding, 200, 50);
		//this.controlsButton = new MenuButton(mainMenu.getOptions(2), width/2 - 100, height/2 + 110 + padding, 200, 50);

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
        Option[] options = mainMenu.getOptions();
        for(int i =0; i<options.length; i++){
        	Option option = options[i];
            if (option.equals(mainMenu.getCurrentOption())){
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
