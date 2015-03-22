package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.PauseMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.AvatarCreationMenu;
import com.oopsididitagain.rpg_iter2.models.menus.PauseMenu;
import com.oopsididitagain.rpg_iter2.models.menus.PauseMenu.Option;
import com.oopsididitagain.rpg_iter2.utils.Commands;
import com.oopsididitagain.rpg_iter2.utils.keyboardInput.KeyBoardInput;

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
	public Controller takeInputAndUpdate(int key) {
		Controller controller = PauseMenuController.getInstance();

        switch(key){
            case Commands.MOVE_NORTH:
            	pauseMenu.previousOption();
                break;
            case Commands.MOVE_SOUTH:
            	pauseMenu.nextOption();
                break;
            case Commands.ENTER:
            case Commands.USE:
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
            case Commands.EXIT:
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

	@Override
	public KeyBoardInput getKeyBoardInput() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
