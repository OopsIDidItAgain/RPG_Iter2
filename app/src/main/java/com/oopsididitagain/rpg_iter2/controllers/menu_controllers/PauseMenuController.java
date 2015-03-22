package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.PauseMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.PauseMenu;
import com.oopsididitagain.rpg_iter2.models.menus.PauseMenu.Option;
import com.oopsididitagain.rpg_iter2.utils.Command;

/**
 * Handles input while in pause menu_controllers
 */
public class PauseMenuController extends Controller{

	private static PauseMenuController instance;
	private PauseMenu pauseMenu;
	
	private PauseMenuController(PauseMenu pauseMenu){
		this.pauseMenu = pauseMenu;
	}
	
	public static PauseMenuController getInstance() {
		if ( instance == null ){
			instance = new PauseMenuController(new PauseMenu());
		}
		return instance;
	}
	
	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = PauseMenuController.getInstance();

        switch(command){
            case MOVE_NORTH:
            	pauseMenu.previousOption();
                break;
            case MOVE_SOUTH:
            	pauseMenu.nextOption();
                break;
            case ENTER:
            case USE:
            	Option o = pauseMenu.getCurrentOption();
            	switch(o) {
				case ExitGame:
					controller = ExitGameController.getInstance();
					break;
				case Load:
					// TODO load logic
					break;
				case Options:
					controller = OptionsMenuController.getInstance();
					break;
				case Save:
					// TODO save logic
					break;
				default:
					break;
            	}
                break;
            case EXIT:
            case PAUSE:
            	controller = GameController.getInstance();
            	break;
            default:
            	break;
        }

		return controller;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		PauseMenuViewInteraction PauseMenuViewInteraction = new PauseMenuViewInteraction(this.pauseMenu);
		return PauseMenuViewInteraction;
	}

	
}
