package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;

public class Game {

	Avatar avatar;
	GameMap gameMap;
	
	
	public Game(Avatar avatar){
		
		this.avatar = avatar;
		gameMap = new GameMap(new MapDatabase(1));
	}
	public Game( Avatar avatar, GameMap gameMap){
		
		this.avatar = avatar;
		this.gameMap = gameMap;
	}
	
	
	
}
