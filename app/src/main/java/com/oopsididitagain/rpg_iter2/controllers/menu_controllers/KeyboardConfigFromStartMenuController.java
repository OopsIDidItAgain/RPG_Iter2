package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.model_view_interaction.KeyboardConfigMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.KeyboardConfigMenu;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class KeyboardConfigFromStartMenuController extends Controller {
	private static KeyboardConfigFromStartMenuController instance;
	private KeyboardConfigMenu keyboardConfigMenu;
	
	private KeyboardConfigFromStartMenuController(KeyboardConfigMenu keyboardConfigMenu){
		this.keyboardConfigMenu = keyboardConfigMenu;
	}
	
	public static KeyboardConfigFromStartMenuController getInstance() {
		if ( instance == null ){
			instance = new KeyboardConfigFromStartMenuController(new KeyboardConfigMenu());
		}
		return instance;
	}
	
	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = KeyboardConfigFromStartMenuController.getInstance();

        switch(command){
            case MOVE_NORTH:
            	keyboardConfigMenu.previousOption();
                break;
            case MOVE_SOUTH:
            	keyboardConfigMenu.nextOption();
                break;
            case ENTER:
            case USE:
            	Command o = keyboardConfigMenu.getCurrentOption();
                break;
            case EXIT:
            	controller = OptionsFromStartMenuController.getInstance();
            	break;
            default:
            	break;
        }

		return controller;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		KeyboardConfigMenuViewInteraction KeyboardConfigMenuViewInteraction = new KeyboardConfigMenuViewInteraction(this.keyboardConfigMenu);
		return KeyboardConfigMenuViewInteraction;
	}

}
