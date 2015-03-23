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

public class GameOverController extends Controller {
	// members
	public static GameOverController instance;
    private static GameOverMenu gameOverMenu;
    private GameOverViewInteraction gameOverMenuView;
	private Avatar avatar;

    private Controller controllerToReturn;

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
				// Position position = new Position(0,0,Direction.SOUTH);
				// StatBlob statBlob = new StatBlob(0, 5, 5, 5, 5, 5, 5, 20, 20);
				// avatar = new Avatar("avatar", position,statBlob );
				
				// AvatarCreationMenuController();
				
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
		return new GameOverViewInteraction(gameOverMenu);
	}

	public static Controller getInstance() {
		if ( instance == null ){
			instance = new GameOverController(new GameOverMenu());
		}
		return instance;
	}

}
