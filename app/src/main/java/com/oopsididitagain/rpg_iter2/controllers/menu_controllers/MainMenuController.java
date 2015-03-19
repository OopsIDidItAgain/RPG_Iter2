package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.MainMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.interaction_classes.EntityMapInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;

/**
 * In charge of handling input in main menu_controllers
 *
 * Created by parango on 3/11/15.
 */
public class MainMenuController extends Controller{
	private static MainMenu mainMenu;
	public static MainMenuController instance;
	static boolean cached = false;
	
	private MainMenuController(){

	}

	public static MainMenuController getInstance() {
		if ( instance == null ){
			instance = new MainMenuController();
		}
		mainMenu = new MainMenu();
		cached = false;
		return instance;
	}
	
	@Override
	public Controller takeInputAndUpdate(int key) {
		// TODO Auto-generated method stub
		Controller c = this;
		if(key == 4){
			c = ExitGameController.getInstance();
		}
		return c;
	}

	@Override
	public MainMenuViewInteraction populateInteraction() {
		MainMenuViewInteraction menuViewInteraction = new MainMenuViewInteraction();
		menuViewInteraction.setMenu(this.mainMenu);
		return menuViewInteraction;
	}
}
