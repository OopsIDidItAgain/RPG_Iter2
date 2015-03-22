package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.InventoryController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.PauseMenuController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.GameViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Game;
import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.Terrain;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.effects.Observe;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.interaction_classes.EntityMapInteraction;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.Commands;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.PositionOutOfBoundsException;
import com.oopsididitagain.rpg_iter2.utils.keyboardInput.*;




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
	private GameKeyboardInput keyboardInput;

	private GameController(){
		this.keyboardInput = new GameKeyboardInput();

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
	public Controller takeInputAndUpdate(int command) {
		Controller c = this;
		if(command == 2){
			System.out.println("hi");
		}
		c = performSkillCommand(command);

		Direction targetDirection = null;
		switch(command){
		case 7: 
			targetDirection = Direction.SOUTH;
			break;
		case 6: 
			targetDirection = Direction.NORTH;
			break;
		case 5: 
			targetDirection = Direction.WEST;
			break;
		case 4: 
			targetDirection = Direction.EAST;
			break;
		case Commands.MOVE_EAST: targetDirection = Direction.EAST; 
			break;
		case Commands.MOVE_WEST: targetDirection = Direction.WEST; 
			break;
		case Commands.MOVE_NORTH: targetDirection = Direction.NORTH; 
			break;
		case Commands.MOVE_SOUTH: targetDirection = Direction.SOUTH; 
			break;
		case Commands.MOVE_SOUTH_WEST: targetDirection = Direction.SOUTHWEST; 
			break;
		case Commands.MOVE_SOUTH_EAST: targetDirection = Direction.SOUTHEAST; 
			break;
		case Commands.MOVE_NORTH_WEST: targetDirection = Direction.NORTHWEST; 
			break;
		case Commands.MOVE_NORTH_EAST: targetDirection = Direction.NORTHEAST; 
			break;
		case Commands.INVENTORY: 
			c = InventoryController.getInstance();
			break;
		case Commands.PAUSE:
			c = PauseMenuController.getInstance();
			break;
		}
		if (targetDirection != null) {
			
			Position toPosition = avatar.getPosition().createPositionAtDirection(targetDirection);
			
		    entityMapInteraction.move(avatar, toPosition);
			
				// TODO Auto-generated catch block
			
			/*if (successfulMove) {
				for (Npc npc: npcList)
					entityMapInteraction.move(npc, npc.rollDice());
			}*/
				// TODO Auto-generated catch block
			
		

		}
		return c;
	}
	
	

	private Controller performSkillCommand(int command) {
		Controller c = this;
		Skill skill = avatar.getActiveSkill(command);
		if(skill != null){
			
			entityMapInteraction.applySkill(avatar,skill);
			if(command == 1){
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

	@Override
	public KeyBoardInput getKeyBoardInput() {
		return keyboardInput;
	}

	

}
