package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.model_view_interaction.MainMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.utils.Command;

/**
 * In charge of handling input in main menu_controllers
 *
 * Created by parango on 3/11/15.
 */
public class MainMenuController extends Controller {

	public static MainMenuController instance;

    private static MainMenu mainMenu;
    private MainMenuViewInteraction mainMenuView;

    private Controller controllerToReturn;


	private MainMenuController(){

	}

	public static MainMenuController getInstance() {
		if ( instance == null ){
			mainMenu = new MainMenu();
			instance = new MainMenuController();
		}
		return instance;
	}

	@Override
	public Controller takeInputAndUpdate(Command command) {
		controllerToReturn = this;

        switch(command){
            case MOVE_NORTH:
                mainMenu.selectedOptionUp();
                break;
            case USE:
            case ENTER:
                doSelectedOption();
                break;
            case MOVE_SOUTH:
                mainMenu.selectedOptionDown();
                break;
            default: break;
        }

		return controllerToReturn;
	}

    private void doSelectedOption(){
        String selectedOption = mainMenu.getSelectedOption();
        switch(selectedOption){
            case "New Game":
                controllerToReturn = AvatarCreationMenuController.getInstance();
                break;
        }
    }

	@Override
	public MainMenuViewInteraction populateInteraction() {
		mainMenuView = new MainMenuViewInteraction(mainMenu);
		return mainMenuView;
	}


}
