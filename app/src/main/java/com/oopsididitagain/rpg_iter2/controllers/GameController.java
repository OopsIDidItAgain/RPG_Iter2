package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.ActionMenuController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.InventoryController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.PauseMenuController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.SkillPointAllocationController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.GameViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Game;
import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.interaction_classes.EntityMapInteraction;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.Direction;




/**
 * In charge of handling input while playing game
 *
 * Created by parango on 3/11/15.
 */
public class GameController extends Controller{
	private static GameController instance;
	private Game game;
	private Avatar avatar;
	private GameMap gameMap;
	private EntityMapInteraction entityMapInteraction;

	private GameController(){

	}

	public void setGame(Game g){
		game = g;
		this.avatar = g.getAvatar();
		this.gameMap = g.getGameMap();
		createEntityMapInteraction();

	}
	public static GameController getInstance() {
		if ( instance == null ){
			instance = new GameController();
		}
		return instance;
	}

	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller c = this;
		c = performSkillCommand(command);

		Direction targetDirection = null;
		switch(command){
		case MOVE_SOUTH: 
			targetDirection = Direction.SOUTH;
			break;
		case MOVE_NORTH: 
			targetDirection = Direction.NORTH;
			break;
		case MOVE_WEST: 
			targetDirection = Direction.WEST;
			break;
		case MOVE_EAST: 
			targetDirection = Direction.EAST;
			break;
		case MOVE_SOUTHWEST: targetDirection = Direction.SOUTHWEST; 
			break;
		case MOVE_SOUTHEAST: targetDirection = Direction.SOUTHEAST; 
			break;
		case MOVE_NORTHWEST: targetDirection = Direction.NORTHWEST; 
			break;
		case MOVE_NORTHEAST: targetDirection = Direction.NORTHEAST; 
			break;
		case INVENTORY: 
			c = InventoryController.getInstance();
			break;
		case PAUSE:
			c = PauseMenuController.getInstance();
			break;
		case SKILLALLOCATION:
			c = SkillPointAllocationController.getInstance();
		default:
			break;
		}
		if (targetDirection != null) {
			
			Position toPosition = avatar.getPosition().createPositionAtDirection(targetDirection);
			
		    boolean successfulMove = entityMapInteraction.move(avatar, toPosition);
			
				
			
			if (!successfulMove) {
				Entity e = entityMapInteraction.checkForEntity(avatar, toPosition);
				
				if(e != null){
					
					c =  ActionMenuController.getInstance();
				}
			}
			
		

		}
		return c;
	}
	
	

	private Controller performSkillCommand(Command command) {
		Controller c = this;
		Skill skill = avatar.getActiveSkill(command);
		if(skill != null){
			entityMapInteraction.applySkill(avatar,skill);
			// I think Command.SKILLONE will always point to observe
			if(command == Command.SKILLONE){
				c = ObserverController.getInstance();
				((ObserverController) c).setEntityMapInteraction(entityMapInteraction);
				((ObserverController) c).setAvatar(avatar);
				((ObserverController) c).setSkill(skill);
			}
		}
		return c;
	}

	private void createEntityMapInteraction() {
		entityMapInteraction = new EntityMapInteraction(gameMap);
		
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public void setMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}

	@Override
	public GameViewInteraction populateInteraction() {

		GameViewInteraction gameInteraction = new GameViewInteraction(game);
		return gameInteraction;
	}

}
