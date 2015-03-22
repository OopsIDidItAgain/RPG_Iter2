package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ActionMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.ActionMenu;
import com.oopsididitagain.rpg_iter2.models.menus.ActionMenu.Option;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class ActionMenuController extends Controller {

	private static ActionMenuController instance;
	private ActionMenu actionMenu;
	
	private ActionMenuController(ActionMenu actionMenu){
		this.actionMenu = actionMenu;
	}
	
	public static ActionMenuController getInstance() {
		if ( instance == null ){
			instance = new ActionMenuController(new ActionMenu());
		}
		return instance;
	}
	
	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = ActionMenuController.getInstance();

        switch(command){
            case MOVE_NORTH:
            	actionMenu.previousOption();
                break;
            case MOVE_SOUTH:
            	actionMenu.nextOption();
                break;
            case ENTER:
            case USE:
            	Option o = actionMenu.getCurrentOption();
            	switch(o) {
				case Talk:
					//Dialogue part
					break;
				case Attack:
					// TODO Attack logic aka Battle Mode
					break;
				case UseSkill:
					
					break;
				case UseOption:
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
		ActionMenuViewInteraction actionMenuViewInteraction = new ActionMenuViewInteraction(this.actionMenu);
		return actionMenuViewInteraction;
	}
}
