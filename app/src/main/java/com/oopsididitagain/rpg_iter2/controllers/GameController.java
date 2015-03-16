package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.interaction_classes.EntityMapInteraction;

/**
 * In charge of handling input while playing game
 *
 * Created by parango on 3/11/15.
 */
public class GameController extends Controller{
	public static GameController instance;
	Avatar avatar;
	GameMap gameMap;
	EntityMapInteraction entityMapInteraction;

	private GameController(){
		createEntityMapInteraction();

	}

	public static GameController getInstance() {
		if ( instance == null ){
			instance = new GameController();
		}
		return instance;
	}

	@Override
	public Controller takeInputAndUpdate(int key) {
		Controller controller = AvatarCreationController.getInstance();
		switch(key){
		case 1:
			//this skill would not happen here, it would probably happen
			//in "skillSelectionController"
			entityMapInteraction.bargain(avatar);
		case 2:
		
		case 3:
		
		case 4:
			
		}
		return controller;	
	}
	
	private void createEntityMapInteraction() {
		entityMapInteraction = new EntityMapInteraction(gameMap);
		
	}
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public void setMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}

}
