package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.MainMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.utils.MainMenuKeyboardInput;

/**
 * In charge of handling input in main menu_controllers
 *
 * Created by parango on 3/11/15.
 */
public class MainMenuController extends Controller {
	private static MainMenu mainMenu;
	public static MainMenuController instance;
	private MainMenuController(){

	}
	

	public static MainMenuController getInstance() {
		if ( instance == null ){
			instance = new MainMenuController();
			mainMenu = new MainMenu();
		}
		return instance;
	}
	
    


	@Override
	public Controller takeInputAndUpdate(int key) {
		Controller c = this;
		if(key == 1){
			c = ExitGameController.getInstance();
		}if(key == 2){
			
			c = GameController.getInstance();		
		}
		return c;
	}

	@Override
	public MainMenuViewInteraction populateInteraction() {
		MainMenuViewInteraction menuViewInteraction = new MainMenuViewInteraction(this.mainMenu);
		return menuViewInteraction;
	}


	@Override
	public MainMenuKeyboardInput getKeyBoardInput(){
		return new MainMenuKeyboardInput(this.mainMenu);
	}
	
}
