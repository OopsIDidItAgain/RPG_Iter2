package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.GameOverViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.MainMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.menus.ActionMenu;
import com.oopsididitagain.rpg_iter2.models.menus.GameOverMenu;
import com.oopsididitagain.rpg_iter2.models.menus.InventoryMenu;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class GameOverController extends Controller {
	// members
	public static GameOverController instance;
    private static GameOverMenu gameOverMenu;
    private GameOverViewInteraction gameOverMenuView;
	private Avatar avatar;

	
    private GameOverController() {
		GameController gameController = GameController.getInstance();
		this.avatar = gameController.getAvatar();
	}
    
    private GameOverController(GameOverMenu gameOverMenu) {
		GameController gameController = GameController.getInstance();
		this.avatar = gameController.getAvatar();
		GameOverController.gameOverMenu = gameOverMenu;
    }
    
    
    
	@Override
	public Controller takeInputAndUpdate(Command command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		// TODO Auto-generated method stub
		return new GameOverViewInteraction(gameOverMenu);
	}

	public static Controller getInstance() {
		// TODO Auto-generated method stub
		if ( instance == null ){
			instance = new GameOverController(new GameOverMenu());
		}
		return instance;
	}

}
