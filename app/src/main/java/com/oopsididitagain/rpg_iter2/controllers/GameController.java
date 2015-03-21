package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.InventoryController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.GameViewInteraction;
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
import com.oopsididitagain.rpg_iter2.utils.GameKeyboardInput;
import com.oopsididitagain.rpg_iter2.utils.KeyBoardInput;

/**
 * In charge of handling input while playing game
 *
 * Created by parango on 3/11/15.
 */
public class GameController extends Controller{
	public static GameController instance;
	private Avatar avatar;
	private GameMap gameMap;
	private EntityMapInteraction entityMapInteraction;

	private GameController(){
		Tile[][] tiles = new Tile[50][50];
		StatBlob st = new StatBlob(0, 0, 0, 0, 20, 0, 0, 0, 0);
		Position position = new Position(0,0,Direction.SOUTH);
		this.avatar = new Avatar("hi", position,st);
		Position postion1 = new Position(1,0);
		Npc npc = new Npc("yo",postion1,st);
		for(int i = 0; i!= 50; ++i){
			for(int j = 0; j!= 50; ++j){
				Position p = new Position(i,j);
				tiles[i][j] = new Tile(null, Terrain.GRASS);
				if( i == 1 && j == 0){
					tiles[i][j].add(npc);
				}
				else if( i == 0 && j == 0){
					tiles[i][j].add(avatar);
				}
				
			}
		}
		this.gameMap = new GameMap(tiles);
		
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
		performSkillCommand(command);
		Direction targetDirection = null;
		switch(command){
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
		}
		Position toPosition = avatar.getPosition().createPositionAtDirection(targetDirection);
		if (targetDirection != null) {
			boolean successfulMove = entityMapInteraction.move(avatar, toPosition);
			/*if (successfulMove) {
				for (Npc npc: npcList)
					entityMapInteraction.move(npc, npc.rollDice());
			}*/
			/*
			((ObserverController) c).setEntityMapInteraction(entityMapInteraction);
			((ObserverController) c).setAvatar(avatar);
			((ObserverController) c).setSkill(observe);
			*/

		}
		return c;
	}
	
	

	private void performSkillCommand(int command) {
		Skill skill = avatar.getActiveSkill(command);
		if(skill != null){
			entityMapInteraction.applySkill(avatar,skill);
		}
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
		GameViewInteraction gameInteraction = new GameViewInteraction();
		return gameInteraction;
	}

	@Override
	public KeyBoardInput getKeyBoardInput() {
		// TODO Auto-generated method stub
		return new GameKeyboardInput();
	}

	

}
