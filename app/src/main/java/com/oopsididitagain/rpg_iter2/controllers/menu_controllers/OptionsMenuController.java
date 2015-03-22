package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.OptionsMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.OptionsMenu;
import com.oopsididitagain.rpg_iter2.models.menus.OptionsMenu.Option;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class OptionsMenuController extends Controller {
	private static OptionsMenuController instance;
	private OptionsMenu optionsMenu;
	
	private OptionsMenuController(OptionsMenu optionsMenu){
		this.optionsMenu = optionsMenu;
	}
	
	public static OptionsMenuController getInstance() {
		if ( instance == null ){
			instance = new OptionsMenuController(new OptionsMenu());
		}
		return instance;
	}
	
	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = OptionsMenuController.getInstance();

        switch(command){
            case MOVE_NORTH:
            	optionsMenu.previousOption();
                break;
            case MOVE_SOUTH:
            	optionsMenu.nextOption();
                break;
            case ENTER:
            case USE:
            	Option o = optionsMenu.getCurrentOption();
            	switch(o) {
				case Audio:
					break;
				case Back:
					controller = PauseMenuController.getInstance();
					break;
				case Graphics:
					break;
				case Keyboard:
					controller = KeyboardConfigMenuController.getInstance();
					break;
				default:
					break;
            	}
                break;
            case EXIT:
            	controller = PauseMenuController.getInstance();
            	break;
            default:
            	break;
        }

		return controller;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		OptionsMenuViewInteraction OptionsMenuViewInteraction = new OptionsMenuViewInteraction(this.optionsMenu);
		return OptionsMenuViewInteraction;
	}

}
