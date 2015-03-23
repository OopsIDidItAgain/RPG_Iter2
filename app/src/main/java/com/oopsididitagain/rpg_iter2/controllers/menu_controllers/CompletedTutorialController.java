package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.BattleController;
import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.controllers.TradeController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.GameOverViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.menus.GameOverMenu;
import com.oopsididitagain.rpg_iter2.models.menus.GameOverMenu.Option;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.Direction;

public class CompletedTutorialController extends Controller {
	// members
	public static CompletedTutorialController instance;
    private static GameOverMenu gameOverMenu;
    private GameOverViewInteraction gameOverMenuView;
	private Avatar avatar;

    private Controller controllerToReturn;

    private CompletedTutorialController() {
		GameController gameController = GameController.getInstance();
		this.avatar = gameController.getAvatar();
	}
    
    private CompletedTutorialController(GameOverMenu gameOverMenu) {
		GameController gameController = GameController.getInstance();
		this.avatar = gameController.getAvatar();
		CompletedTutorialController.gameOverMenu = gameOverMenu;
    }
    
    
    
	@Override
	public Controller takeInputAndUpdate(Command command) {
		controllerToReturn = this;

		switch (command) {
		case MOVE_SOUTH:
			gameOverMenu.nextOption();
			break;
		case MOVE_NORTH:
			gameOverMenu.previousOption();
			break;
		case USE:
        	Option o = gameOverMenu.getCurrentOption();
        	switch(o) {
			case StartOver:
				controllerToReturn = MainMenuController.getInstance();
				break;
			case ExitGame:
				controllerToReturn = ExitGameController.getInstance();
				break;
			default:
				break;
        	}
            break;
		default:
			break;
		}
		
		return controllerToReturn;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		return new GameOverViewInteraction(gameOverMenu, true);
	}

	public static Controller getInstance() {
		if ( instance == null ){
			instance = new CompletedTutorialController(new GameOverMenu());
		}
		return instance;
	}

}
