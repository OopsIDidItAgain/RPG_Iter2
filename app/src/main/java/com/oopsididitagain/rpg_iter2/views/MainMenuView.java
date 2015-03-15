package com.oopsididitagain.rpg_iter2.views;

import com.oopsididitagain.rpg_iter2.Menu.MainMenu;
import com.oopsididitagain.rpg_iter2.utils.Observer;

/**
 * Main Menu when game sta
 *
 * Created by parango on 3/11/15.
 */
public class MainMenuView implements Observer{
	MainMenu menu = new MainMenu();
	int currentOption;
	public MainMenuView(MainMenu menu){
		this.menu = menu;
		this.currentOption = menu.getCurrentOption();
	}
	@Override
	public void update() {
		currentOption = menu.getOption();
		
		
	}
}
