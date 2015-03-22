package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.model_view_interaction.KeyboardConfigMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.KeyboardConfigMenu;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class KeyboardConfigMenuController extends Controller {
	private static KeyboardConfigMenuController instance;
	private KeyboardConfigMenu keyboardConfigMenu;
	
	private KeyboardConfigMenuController(KeyboardConfigMenu keyboardConfigMenu){
		this.keyboardConfigMenu = keyboardConfigMenu;
	}
	
	public static KeyboardConfigMenuController getInstance() {
		if ( instance == null ){
			instance = new KeyboardConfigMenuController(new KeyboardConfigMenu());
		}
		return instance;
	}
	
	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = KeyboardConfigMenuController.getInstance();

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
            	controller = OptionsMenuController.getInstance();
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
