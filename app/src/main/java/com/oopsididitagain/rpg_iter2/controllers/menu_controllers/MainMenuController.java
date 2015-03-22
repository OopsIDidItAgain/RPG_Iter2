package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.MainMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu.Option;
import com.oopsididitagain.rpg_iter2.utils.Commands;
import com.oopsididitagain.rpg_iter2.utils.keyboardInput.*;

/**
 * In charge of handling input in main menu_controllers
 *
 * Created by parango on 3/11/15.
 */
public class MainMenuController extends Controller {

	private static MainMenuController instance;

	private static MainMenu mainMenu;
	private MainMenuViewInteraction mainMenuView;

	private MainMenuKeyboardInput keyboardInput;

	private Controller controllerToReturn;

	private MainMenuController() {

		this.keyboardInput = new MainMenuKeyboardInput(mainMenu);
	}

	public static MainMenuController getInstance() {
		if (instance == null) {
			mainMenu = new MainMenu();
			instance = new MainMenuController();
		}
		return instance;
	}

	@Override
	public Controller takeInputAndUpdate(int key) {
		controllerToReturn = this;

		switch (key) {
		case Commands.MOVE_SOUTH:
			mainMenu.nextOption();
			break;
		case Commands.MOVE_NORTH:
			mainMenu.previousOption();
			break;
		case Commands.ENTER:
		case Commands.USE:
			doSelectedOption();
			break;
		default:
			break;
		}

		return controllerToReturn;
	}

	private void doSelectedOption() {
		Option selectedOption = mainMenu.getCurrentOption();
		switch (selectedOption) {
		case New:
			controllerToReturn = AvatarCreationMenuController.getInstance();
			break;
		case ExitGame:
			break;
		case Load:
			break;
		case Options:
			break;
		default:
			break;
		}
	}

	@Override
	public MainMenuViewInteraction populateInteraction() {
		mainMenuView = new MainMenuViewInteraction(mainMenu);
		return mainMenuView;
	}

	@Override
	public MainMenuKeyboardInput getKeyBoardInput() {
		return keyboardInput;
	}

}
