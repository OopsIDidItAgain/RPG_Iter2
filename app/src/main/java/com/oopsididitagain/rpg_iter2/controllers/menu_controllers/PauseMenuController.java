package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import java.io.File;

import javax.swing.JFileChooser;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.PauseMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Game;
import com.oopsididitagain.rpg_iter2.models.menus.PauseMenu;
import com.oopsididitagain.rpg_iter2.models.menus.PauseMenu.Option;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.IOUtil;

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
            	case Return:
                	controller = GameController.getInstance();
            		break;
				case ExitGame:
					controller = ExitGameController.getInstance();
					break;
				case Load:
					controller = loadGame();
					break;
				case Options:
					controller = OptionsMenuController.getInstance();
					break;
				case Save:
					GameController.getInstance().saveGame();
					controller = GameController.getInstance();
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
	public ModelViewInteraction populateInteraction() {
		PauseMenuViewInteraction PauseMenuViewInteraction = new PauseMenuViewInteraction(this.pauseMenu);
		return PauseMenuViewInteraction;
	}

	
}
