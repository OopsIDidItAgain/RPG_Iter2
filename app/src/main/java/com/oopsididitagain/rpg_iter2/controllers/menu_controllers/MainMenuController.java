package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import java.io.File;

import javax.swing.JFileChooser;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.MainMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Game;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu.Option;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.IOUtil;

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

		switch (command) {
		case MOVE_SOUTH:
			mainMenu.nextOption();
			break;
		case MOVE_NORTH:
			mainMenu.previousOption();
			break;
		case ENTER:
		case USE:
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
		case Tutorial:
			controllerToReturn = AvatarCreationMenuController.getInstance();
			((AvatarCreationMenuController)controllerToReturn).setTutorial(true);
			break;
		case New:
			controllerToReturn = AvatarCreationMenuController.getInstance();
			((AvatarCreationMenuController)controllerToReturn).setTutorial(false);
			break;
		case ExitGame:
			controllerToReturn = ExitGameController.getInstance();
			break;
		case Load:
			controllerToReturn = loadGame();
			break;
		default:
			break;
		}
	}

	private Controller loadGame() {
		File rootDir = new File(System.getProperty("user.home") + "/OOP_SAVEGAMES");
		rootDir.mkdirs();
		File loadGameDir = null;
		JFileChooser chooser = new JFileChooser(rootDir);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) 
			loadGameDir = chooser.getSelectedFile();

		if (loadGameDir == null) return this;
		
		File grid = new File(loadGameDir, "grid.csv");
		File tileables = new File(loadGameDir, "tileables.csv");
		int level = IOUtil.parseLevel(grid);
		MapDatabase md = new MapDatabase(1, grid, tileables);
		Game g = new Game(md);
		GameController gc = GameController.getInstance();
		gc.setGame(g);
		return gc;
		
		// TODO:
		// Pass this to map database.
	}

	@Override
	public MainMenuViewInteraction populateInteraction() {
		mainMenuView = new MainMenuViewInteraction(mainMenu);
		return mainMenuView;
	}


}
