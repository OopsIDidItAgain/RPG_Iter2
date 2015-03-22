package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.MainMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.utils.keyboardInput.*;

/**
 * In charge of handling input in main menu_controllers
 *
 * Created by parango on 3/11/15.
 */
public class MainMenuController extends Controller {

	public static MainMenuController instance;

    private static MainMenu mainMenu;
    private MainMenuViewInteraction mainMenuView;

	private MainMenuKeyboardInput keyboardInput;

    private Controller controllerToReturn;


	private MainMenuController(){

        this.keyboardInput = new MainMenuKeyboardInput(mainMenu);
	}

	public static MainMenuController getInstance() {
		if ( instance == null ){
			mainMenu = new MainMenu();
			instance = new MainMenuController();
		}
		return instance;
	}

	@Override
	public Controller takeInputAndUpdate(int key) {
		controllerToReturn = this;

        switch(key){
            case 0:
                break;
            case 1:
                mainMenu.selectedOptionUp();
                break;
            case 2:
                break;


            case 3:
                break;
            case 4:
                doSelectedOption();
                break;
            case 5:
                break;


            case 6:
                break;
            case 7:
                mainMenu.selectedOptionDown();
                break;
            case 8:
                break;
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


	@Override
	public MainMenuKeyboardInput getKeyBoardInput(){
		return keyboardInput;
	}

}
